package cc.dfsoft.project.biz.base.project.service.impl;

import cc.dfsoft.project.biz.base.accept.enums.MaterialFlagEnum;
import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPassEnum;
import cc.dfsoft.project.biz.base.contract.enums.IsIntelligentConPayEnum;
import cc.dfsoft.project.biz.base.project.dao.FallbackApplyDao;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.FallbackApplyReq;
import cc.dfsoft.project.biz.base.project.entity.FallbackApply;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.IsAuditEnum;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.FallbackApplyService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.UpdateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffDto;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 回退申请接口实现
 * @author Yuanyx
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class FallbackApplyServiceImpl implements FallbackApplyService {

	/** 回退申请Dao*/
	@Resource
	FallbackApplyDao fallbackApplyDao;
	
	/** 工程Dao*/
	@Resource
	ProjectDao projectDao;
	
	/** 管理记录服务接口 */
	@Resource
	ManageRecordDao manageRecordDao;

	@Resource
	StaffDao staffDao;

	@Resource
	WorkFlowService workFlowService;
	@Resource
	OperateRecordService operateRecordService;
	@Resource
	DocTypeDao docTypeDao;
	@Resource
	IFinanceInfoService iFinanceInfoService;
	@Resource
	OperateRecordDao operateRecordDao;
	@Resource
	ContractDao contractDao;
	@Resource
	ProjectService projectService;
	@Resource
	SignNoticeService signNoticeService;



	@Override
	public Map<String, Object> queryFallbackApply(FallbackApplyReq req) throws ParseException{
		Map<String, Object> map=fallbackApplyDao.queryFallbackApply(req);
		List<FallbackApply> data = (List<FallbackApply>) map.get("data");
		if (data != null && data.size() > 0) {
			for(FallbackApply fa :data ){
				//待办人
				fa.setTodoer(operateRecordDao.queryTodoer(fa.getFaId()));
			}
		}
		return map;
	}



	@Override
	public FallbackApply findById(String id) {
		return fallbackApplyDao.get(id);
	}



	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED,rollbackFor = {Exception.class})
	public void saveFallbackApply(FallbackApply fallbackApply) throws Exception {
		if(StringUtils.isBlank(fallbackApply.getFaId())){
			fallbackApply.setFaId(IDUtil.getUniqueId(Constants.FALLBACK_APPLY));
		}
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		fallbackApply.setApplyTime(fallbackApplyDao.getDatabaseDate()); //操作时间
		fallbackApply.setApplyOperator(loginInfo.getStaffName());		//申请人名称
		fallbackApply.setApplyOperatorId(loginInfo.getStaffId());		//申请人id
		fallbackApply.setAuditState(AuditResultEnum.APPLYING.getValue());//审核状态
		
		
		Project pro=projectDao.get(fallbackApply.getProjId());
		if(pro!=null){
			fallbackApply.setCorpId(pro.getCorpId());
			fallbackApply.setProjName(pro.getProjName());
			fallbackApply.setProjNo(pro.getProjNo());
			fallbackApply.setOriginalStepId(WorkFlowActionEnum.getStepCodeByStatusCode(pro.getProjStatusId()));
		}

		boolean flag = true;

		if (IsAuditEnum.NO_Aidut.getValue().equals(fallbackApply.getIsAudit())){//直接回退不需要审核
			fallbackApply.setAuditState(AuditResultEnum.PASSED.getValue());
			//manageRecordService.fallBackNew(pro,fallbackApply);//2019-01-10 fuliwei，回退新方法
			this.rollBackProcessingRelatedContent(pro,fallbackApply);
			flag = false;
		}

		fallbackApplyDao.saveOrUpdate(fallbackApply);


		if(flag){//生成回退审核人待办
			createNotice(pro,fallbackApply);
		}

	}




	/**
	* @Description: 回退处理相关内容
	* @author zhangnx
	* @date 2019/8/22 22:47
	*/
	@Override
	public boolean rollBackProcessingRelatedContent(Project pro, FallbackApply fallbackApply) {
		if (pro==null || fallbackApply==null) return false;

		if (StepEnum.CONTRACT_END.getValue().equals(fallbackApply.getFallbackStepId())){//退单
			return this.stopProjectHandle(pro,fallbackApply);
		}

		try {//回退到不同步骤的处理
			String todoer = operateRecordService.rollBackHandle(pro.getProjId(),fallbackApply);

			//重新获取最新的数据(在rollBackHandle()方法中多次更新过工程信息)
			Project project = projectDao.get(pro.getProjId());
			if (project == null) return true;
			project.setProjStatusId(WorkFlowActionEnum.getStatusCodeByStepCode(fallbackApply.getFallbackStepId()));//工程状态
			project.setSignBack(fallbackApply.getFallbackStepId());//回退标致
			project.setToDoer(todoer);//待办人
			projectDao.saveOrUpdate(project);
		}catch (Exception e){
			e.printStackTrace();
		}

		return true;
	}



	/**
	 * @Description: 终止工程（退单）
	 * @author zhangnx
	 * @date 2019/8/22 22:11
	 */
	private boolean stopProjectHandle(Project pro,FallbackApply fallbackApply) {
		pro.setBackReason(BackReasonEnum.OTHERS.getValue());	    //退单原因
		pro.setBackDate(projectDao.getDatabaseDate());			    //退单日期
		pro.setBackRemarks(fallbackApply.getFallbackReason());	    //退单备注
		pro.setFinishedDate(projectDao.getDatabaseDate()); 		    //结束日期
		pro.setProjStatusId(WorkFlowActionEnum.getStatusCodeByStepCode(fallbackApply.getFallbackStepId())); //设置工程状态
		pro.setSignBack("1");
		//清空代办人
		pro.setToDoer("已退单");
		projectDao.saveOrUpdate(pro);
		operateRecordDao.cancelTodo(pro.getProjId());//待办事项置为无效

		signNoticeService.signNoticeSetInvalid(null,pro.getProjId(),null,null,null);//签字通知置无效

		Contract contract = contractDao.viewContractByprojId(pro.getProjId());
		boolean isCall = projectService.isToCall(pro.getProjId(), WebServiceTypeEnum.PROJ_ACCEPT_UPDATE.getValue());
		boolean isBorrowMaterial = MaterialFlagEnum.YES.getValue().equals(pro.getMaterialFlag());
		boolean isPass = contract != null && ContractIsPassEnum.ALREADY_PASS.getValue().equals(contract.getIsPass());

		//退单的如果是借料工程、已签订安装合同的工程调用接口删除工程信息
		if(isCall && (isBorrowMaterial || isPass)){

			String jb = FinanceOperateTypeEnum.PROJ_ACCEPT_UPDATE.getValue();
			String insert = UpdateTypeEnum.INSERT.getValue();
			String isIntellPay = IsIntelligentConPayEnum.OTHER_CON_PAY.getValue();

			this.callNC(pro.getProjId(),jb,insert,isIntellPay);
		}

		return true;
	}



	/**
	 * @Description: 接口调用
	 * @author zhangnx
	 * @date 2019/8/22 22:11
	 */
	@Override
	public ResultMessage callNC(String projId,String opType,String execType,String markType){
		try {
			String msg = iFinanceInfoService.synProjectInfoClient(projId, opType, execType, markType);
			JSONObject jsonbean = JSONObject.fromObject(msg);
			ResultMessage resultMessage = (ResultMessage) JSONObject.toBean(jsonbean, ResultMessage.class);
			if (resultMessage != null && resultMessage.getRet_type().equals(ResultTypeEnum.FAIL.getValue())) {
				throw new ExpressException(resultMessage.getRet_type(), resultMessage.getRet_message());
			}
			return resultMessage;
		}catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}




	/**
 * @MethodDesc: 回退时生成审核人待办
 * @Author zhangnx
 * @Date 2019/7/4 16:04
 */
	public void   createNotice(Project pro ,FallbackApply fallbackApply) {
		List<StaffDto> dtoList = obtainOperater(pro, fallbackApply.getOriginalStepId(),fallbackApply.getFallbackStepId());//获取待办人
		if (dtoList!=null && dtoList.size()>0){
			for (StaffDto s:dtoList) {//生成待办
				OperateRecord o=new OperateRecord();

				String modifyStatus="1".equals(s.getGrade())?"2":"0";//将一级审核设置为待办状态：其他设置为未办状态
				o.setModifyStatus(modifyStatus);

				String backStepName = StepEnum.getNameByCode(fallbackApply.getFallbackStepId());
				String OriginalStepName = StepEnum.getNameByCode(fallbackApply.getOriginalStepId());

				o.setStepName("回退审核："+OriginalStepName+"->"+backStepName);

				o.setGrade(s.getGrade());
				o.setBusinessOrderId(fallbackApply.getFaId());

				operateRecordService.saveUpdateOperateRecord(pro,s,o);
			}
		}
	}





/**
 * @MethodDesc: 获取待办人
 * @Author zhangnx
 * @Date 2019/7/4 16:02
 */
	public List<StaffDto>  obtainOperater(Project pro,String originalStepId,String fallbackStepId){
		if (pro==null) return null;
		List<StaffDto> dtoList=new ArrayList<>();

		String level = docTypeDao.getAuditLevel(pro,originalStepId, fallbackStepId);
		int grade=StringUtil.isNotBlank(level)?Integer.parseInt(level):1;

		for (int i = 1; i <=grade; i++) {//循环查询每级的审核人

			/******************步骤、工程类型、出资方式查询******************************/
			StaffDto staffDto=staffDao.obtainOperater(pro.getCorpId(),pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),fallbackStepId,i);
			if (staffDto!=null){ dtoList.add(staffDto); continue; }

			//如果该公司下未有角色则查询全局的
			 staffDto=staffDao.obtainOperater(pro.getCorpId(),"0",pro.getProjectType(),pro.getContributionMode(),fallbackStepId,i);
			if (staffDto!=null){ dtoList.add(staffDto); continue; }


			/**********************步骤、工程类型查询*************************8******/
			staffDto=staffDao.obtainOperater(pro.getCorpId(),pro.getCorpId(),pro.getProjectType(),"0",fallbackStepId,i);
			if (staffDto!=null){ dtoList.add(staffDto); continue; }

			//如果该公司下未有角色则查询全局的
			staffDto=staffDao.obtainOperater(pro.getCorpId(),"0",pro.getProjectType(),"0",fallbackStepId,i);
			if (staffDto!=null){ dtoList.add(staffDto); continue; }


			/**************************步骤查询************************************/
			staffDto=staffDao.obtainOperater(pro.getCorpId(),pro.getCorpId(),"0","0",fallbackStepId,i);
			if (staffDto!=null){ dtoList.add(staffDto); continue; }

			//如果该公司下未有角色则查询全局的
			staffDto=staffDao.obtainOperater(pro.getCorpId(),"0","0","0",fallbackStepId,i);
			if (staffDto!=null){ dtoList.add(staffDto); continue; }
		}

		return dtoList;

	}



	/**
	 * 查询待审核列表
	 * @author fuliwei
	 * @createTime 2017年12月5日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryAuditFallBack(FallbackApplyReq req) throws ParseException {
		String auditLevel="1";
		//Map<String, Object> result=fallbackApplyDao.queryFallbackApply(req);//老方法
		Map<String, Object> result=fallbackApplyDao.queryRollBackAuditList(req);//新写的方法
		List<FallbackApply> data = (List<FallbackApply>) result.get("data");
		
		if (data != null && data.size() > 0) {
			/**
			 * -1 未审核 0 审核未通过 1 审核通过 2待审核 若该
			 * 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0",
			 * "level3":"2"};
			 */
			for (int i = 0; i < data.size(); i++) {
				//工程类型 出资方式
				Project pro=projectDao.get(data.get(i).getProjId());
				data.get(i).setProjectType(pro.getProjectTypeDes());			//工程类型
				data.get(i).setConstructionMode(pro.getContributionModeDes());//出资方式

				String level = docTypeDao.getAuditLevel(pro,data.get(i).getOriginalStepId() ,data.get(i).getFallbackStepId());
				if (StringUtil.isNotBlank(level)){
					data.get(i).setLevel(level);// 设置审核总级数（设计信息2级审核）
					auditLevel=level;
				}
				data.get(i).setLevel(auditLevel);// 设置审核总级数（设计信息2级审核）
				
				
				data.get(i).setProjScaleDes(pro.getProjScaleDes());
				data.get(i).setProjStatusId(pro.getProjStatusId());
				Map<String, String> levelBtn = new HashMap<String, String>();
				for (int n = 1; n < Integer.parseInt(auditLevel) + 1; n++) {
					if (n == 1) {
						levelBtn.put("level" + n, "2");// 待审
					} else {
						levelBtn.put("level" + n, "-1");// 未审
					}
				}
				List<ManageRecord> mrls = manageRecordDao.findByStepIdBusinessOrderId(data.get(i).getFaId(), data.get(i).getFallbackStepId(),MrResultEnum.PASSED.getValue());
				if (mrls != null && mrls.size() > 0) {
					// 遍历循环，获取审核是否通过
					for (ManageRecord mr : mrls) {
						levelBtn.put("level" + mr.getMrAuditLevel(), mr.getMrResult());
					}
					if (mrls.size() < Integer.parseInt(auditLevel)) {
						levelBtn.put("level" + (mrls.size() + 1), "2");
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		}
		return result;
	}


	
	@Override
	public FallbackApply findByFaId(String id) {
		FallbackApply fa= fallbackApplyDao.get(id);
		if(fa!=null){
			Project pro=projectDao.get(fa.getProjId());
			fa.setProjectType(pro.getProjectTypeDes());			//工程类型
			fa.setConstructionMode(pro.getContributionModeDes());//出资方式
			fa.setProjScaleDes(pro.getProjScaleDes());
		}
		return fa;
	}




	/**
	 * 用回退前步骤id 和回退后步骤id 查2个步骤间的步骤id
	 * @param pro
	 * @param originalStepId  回退前步骤id
	 * @param fallbackStepId  回退后步骤id
	 * @return
	 */
	@Override
	public List<String> findStepIds(Project pro, String originalStepId, String fallbackStepId) {


		if(StringUtil.isNotBlank(originalStepId) &&StringUtil.isNotBlank(fallbackStepId)){
			//用回退后的步骤id去查他的下一个StepId 如果匹配，则返回，否则继续查找
			String nextStepId=workFlowService.queryAssistProgressStatusId(pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), fallbackStepId,
					true, WorkFlowTypeEnum.MAIN_PROGRESS.getValue(), null);
		}


		return null;
	}
}
