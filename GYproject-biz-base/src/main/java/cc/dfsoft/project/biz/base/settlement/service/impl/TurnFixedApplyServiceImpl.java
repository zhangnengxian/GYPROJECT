package cc.dfsoft.project.biz.base.settlement.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionList;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementDeclarationDao;
import cc.dfsoft.project.biz.base.settlement.dao.TurnFixedApplyDao;
import cc.dfsoft.project.biz.base.settlement.dto.TurnFixedApplyReq;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.entity.TurnFixedApply;
import cc.dfsoft.project.biz.base.settlement.service.TurnFixedApplyService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONSerializer;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class TurnFixedApplyServiceImpl implements TurnFixedApplyService {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(TurnFixedApplyServiceImpl.class);
	/** 转固Dao*/
	@Resource
	TurnFixedApplyDao turnFixedApplyDao;
	/** 工程Dao*/
	@Resource
	ProjectDao projectDao;
	/** 计划Dao*/
	@Resource
	ConstructionPlanDao constructionPlanDao;
	/** 施工合同Dao*/
	@Resource
	SubContractDao subContractDao;
	/** 结算表Dao*/
	@Resource
	SettlementDeclarationDao settlementDeclarationDao;
	/** 工作流接口*/
	@Resource
	WorkFlowService workFlowService;
	/** 操作记录接口*/
	@Resource
	OperateRecordService operateRecordService;
	/** 审核级别Dao*/
	@Resource
	DocTypeDao docTypeDao;
	/** 系统参数Dao*/
	@Resource
	SystemSetDao systemSetDao;
	/** 操作记录Dao*/
	@Resource
	OperateRecordDao operateRecordDao;
	/** 审核记录Dao*/
	@Resource
	ManageRecordDao manageRecordDao;
	
	/** 签字服务接口*/
	@Resource
	SignatureService signatureService;
	@Override
	public TurnFixedApply viewTurnFixedApply(String projId) throws ParseException {
		TurnFixedApply tfa = new TurnFixedApply();
		List<TurnFixedApply> lists = turnFixedApplyDao.findByProjId(projId);
		Project proj = projectDao.get(projId);
		//转固信息已存在
		if(lists != null && lists.size() > 0){
			tfa = lists.get(0);
			return tfa;
		}
		//转固信息不存在
		ConstructionPlan constructionPlan = constructionPlanDao.viewPlanById(projId);
		SubContract subContract = subContractDao.findSubContractByprojId(projId);
		SettlementDeclaration settlementDeclaration = settlementDeclarationDao.findByProjId(projId);
		tfa.setProjId(proj.getProjId()); 								//工程id
		tfa.setProjNo(proj.getProjNo()); 								//工程编号
		tfa.setProjName(proj.getProjName()); 							//工程名称
		tfa.setProjAddr(proj.getProjAddr()); 							//工程地点
		if(null!=constructionPlan){
		tfa.setCuName(constructionPlan.getCuName());					//施工单位
		}
		if(null!=subContract){
		tfa.setScNo(subContract.getScNo());								//施工合同号
		}
		if(null!=settlementDeclaration){
		tfa.setProjCost(settlementDeclaration.getEndDeclarationCost().subtract(settlementDeclaration.getEndMaterialsCost()));//工程款：终审金额-终审主材费
		tfa.setBrokenCost(settlementDeclaration.getAuxiliaryMaterialAmount());//破路费
		tfa.setMaterialCost(settlementDeclaration.getEndMaterialsCost());//材料费
		}
		tfa.setCorpId(proj.getCorpId());								//分公司Id
		tfa.setCorpName(proj.getCorpName());							//分公司名称
		tfa.setProjectTypeDes(proj.getProjectTypeDes());				//工程类型
		tfa.setDeptName(proj.getDeptName());							//业务部门
		tfa.setContributionModeDes(proj.getContributionModeDes());		//出资方式
		return tfa;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveTurnFixedApply(TurnFixedApply turnFixedApply) throws Exception {
		boolean state = false;
		if(StringUtils.isBlank(turnFixedApply.getTfaId())){
			turnFixedApply.setTfaId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
			turnFixedApply.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());
			state = true;
		}
		//保存转固信息
		turnFixedApplyDao.saveOrUpdate(turnFixedApply);
		signatureService.saveOrUpdateSign("menuId+menuNane+28",turnFixedApply.getSign(), turnFixedApply.getProjId(), turnFixedApply.getTfaId(), turnFixedApply.getClass().getName(),state);
		if(turnFixedApply.getFlag().equals("1")){
			//更新工程信息
			Project pro=projectDao.get(turnFixedApply.getProjId()); //通过工程id查找Project
			String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.TURN_FIXED_APPLY.getActionCode(), true);
			pro.setProjStatusId(statusId); 								//设置工程状态
			projectDao.saveOrUpdate(pro);
			//形成操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT);
			operateRecordService.createOperateRecord(orId, turnFixedApply.getProjId(), pro.getProjNo(), StepEnum.TURN_FIXED_APPLY.getValue(), StepEnum.TURN_FIXED_APPLY.getMessage(), "");
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> queryTurnFixedApplyByTime(TurnFixedApplyReq turnFixedApplyReq) throws ParseException {
		//最后一个操作记录的工程记录列表（符合状态的当前工程）
		List<Map<String, Object>> ors=operateRecordDao.getOptRecordByTime(turnFixedApplyReq.getProjStatus());
		Map<String, Object> map=turnFixedApplyDao.queryTurnFixedApply(turnFixedApplyReq);
		//符合当前状态的工程列表
		List<TurnFixedApply> list=(List<TurnFixedApply>) map.get("data");
		List<TurnFixedApply> listNew=new ArrayList<TurnFixedApply>();
		//时间限制（单位天）
		Integer timel=turnFixedApplyReq.getTimeLimit();	
		long secondsLimit=-1l;
		if(timel!=null){
			secondsLimit=timel*24*60*60;
		}
		for(TurnFixedApply sio :list){
			for(Map<String, Object> or:ors){
				if(or.get("PROJ_ID").equals(sio.getProjId())){
					//业务操作记录中时间
					Date oldTime=(Date) or.get("OPERATE_TIME");									
					logger.info("oldTime=="+String.valueOf(secondsLimit));
					//当前时间
					Date nowTime=projectDao.getDatabaseDate();
					long seconds=(nowTime.getTime()-oldTime.getTime())/1000;
					logger.info("newTime=="+String.valueOf(seconds));
					//如果当前时间-上个步骤的操作时间大于时间限制段则为超时
					if(secondsLimit>0&&seconds>secondsLimit){
						sio.setOverdue(true);
						continue;
					}
				}
			}
			listNew.add(sio);
			
		}
		
		map.put("data",listNew);
		return map;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryTurnFixedApply(TurnFixedApplyReq turnFixedApplyReq) throws ParseException {
		String grade=null;
		DocType docType= docTypeDao.findByStepId(StepEnum.TURN_FIXED_AUDIT.getValue());
		grade=docType==null?"":docType.getGrade();		
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
        //SystemSet systemSet=systemSetDao.get("stepId", req.getStepId());
        SystemSet systemSet=systemSetDao.querySystemSetByStepId(turnFixedApplyReq.getStepId(),loginInfo.getCorpId());
		if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
			turnFixedApplyReq.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		}
		Map<String,Object> result = this.queryTurnFixedApplyByTime(turnFixedApplyReq);
		List<TurnFixedApply> data = (List<TurnFixedApply>) result.get("data");
		
		if(data!=null && data.size()>0){
			for(TurnFixedApply turnFixedApply:data){
				
			
					Project project=projectDao.get(turnFixedApply.getProjId());
					if(project!=null){
						turnFixedApply.setProjectType(project.getProjectType());
					}
				
			}
		}
		
		//按步骤id进行查询 获取单据类型
		//Project pro=new Project();
		if(data!=null && data.size()>0){
			/**
			 * -1 未审核  0 审核未通过  1 审核通过  2待审核
			 * 若该 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0","level3":"2"};
			 * */
			//遍历循环  设置审核级别
			for(int i = 0;i<data.size();i++){
				/*if(data.get(i).getPressureType().equals(PressureTypeEnum.HIGH_PRESSURE.getValue())){
					data.get(i).setLevel(grade4);	//设置审核总级数（勘察信息3级审核）
					grade=grade4;
				}else{
					data.get(i).setLevel(grade1);
					grade=grade1;
				}
				*/
				data.get(i).setLevel(grade);
				
				Map<String,String> levelBtn = new HashMap<String,String>();
				//以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for(int n=1;n<Integer.parseInt(grade)+1;n++){
					if(n==1){
						levelBtn.put("level"+n, "2");// 待审
					}else{
						levelBtn.put("level"+n, "-1");//未审
					}
				}
				ManageRecordQueryReq mrq = new ManageRecordQueryReq();
				mrq.setBusinessOrderId(data.get(i).getTfaId());
				mrq.setStepId(StepEnum.TURN_FIXED_AUDIT.getValue());
				mrq.setProjId(data.get(i).getProjId());
				List<ManageRecord> mrls = manageRecordDao.findByStepIdProjIdIsPass(data.get(i).getProjId(),StepEnum.TURN_FIXED_AUDIT.getValue(),MrResultEnum.PASSED.getValue());
				if(mrls!=null && mrls.size()>0){
					//String size = mrls.size()+"";
					if(mrls.size()<Integer.parseInt(grade)){
						//遍历循环，获取审核是否通过
						for(ManageRecord mr:mrls){
							levelBtn.put("level"+mr.getMrAuditLevel(),mr.getMrResult());
						}
						if(mrls.size()<Integer.parseInt(grade)){
							levelBtn.put("level"+(mrls.size()+1), "2");
						}
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		}
		return result;
	}

	@Override
	public TurnFixedApply findByProjId(String projId) {
		return turnFixedApplyDao.get("projId", projId);
	}

	@Override
	public Map<String, Object> queryTurnFixedApplyPrint(TurnFixedApplyReq turnFixedApplyReq) throws ParseException {
		//return turnFixedApplyDao.queryTurnFixedApplyPrint(turnFixedApplyReq);
		Map<String, Object> map=turnFixedApplyDao.queryTurnFixedApplyPrint(turnFixedApplyReq);
		
		List<TurnFixedApply> list=(List<TurnFixedApply>) map.get("data");
		
		if(list!=null && list.size()>0){
			for(TurnFixedApply turnFixedApply:list){
				
				
					Project project=projectDao.get(turnFixedApply.getProjId());
					if(project!=null){
						turnFixedApply.setProjectType(project.getProjectType());
					}
				
			}
		}
		
		return map;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void signTurnFixedPrint(String projId) {
		List<TurnFixedApply> turnFixedApplies = turnFixedApplyDao.findByProjId(projId);
		if(turnFixedApplies!=null&&turnFixedApplies.size()>0){
			//标记已打印
			turnFixedApplies.get(0).setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			turnFixedApplyDao.update(turnFixedApplies.get(0));
		}
	}
}
