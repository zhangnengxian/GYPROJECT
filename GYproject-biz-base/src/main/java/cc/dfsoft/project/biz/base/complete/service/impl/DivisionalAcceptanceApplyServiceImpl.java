package cc.dfsoft.project.biz.base.complete.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.charge.dao.AccrualsRecordDao;
import cc.dfsoft.project.biz.base.charge.dto.ChargeReq;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.complete.dao.DivisionalAcceptanceApplyDao;
import cc.dfsoft.project.biz.base.complete.dao.DivisionalAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.dto.DivisionalAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptanceApply;
import cc.dfsoft.project.biz.base.complete.enums.AcceptanceAtateEnum;
import cc.dfsoft.project.biz.base.complete.service.DivisionalAcceptanceApplyService;
import cc.dfsoft.project.biz.base.constructmanage.entity.WorkReport;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.WorkReportService;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.NoticeDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectSignDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Notice;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectSign;
import cc.dfsoft.project.biz.base.project.enums.*;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
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
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class DivisionalAcceptanceApplyServiceImpl implements DivisionalAcceptanceApplyService {
	
	/**工程Dao*/
	@Resource
	ProjectDao  projectDao;
	
	/**分部验收申请Dao*/
	@Resource
	DivisionalAcceptanceApplyDao divisionalAcceptanceApplyDao; 
	
	/**施工计划Dao*/
	@Resource
	ConstructionPlanDao  constructionPlanDao;
	
	/**合同Dao*/
	@Resource
	ContractDao contractDao;
	
	/**业务单类型*/
	@Resource
	DocTypeDao docTypeDao;
	
	/**系统设置*/
	@Resource
	SystemSetDao systemSetDao;
	
	/**审核记录*/
	@Resource
	ManageRecordDao manageRecordDao;
	
	/**设计信息*/
	@Resource
	DesignInfoDao  designInfoDao;
	
	/**分部验收*/
	@Resource
	DivisionalAcceptanceDao divisionalAcceptanceDao;
	
	/**开工报告*/
	@Resource
	WorkReportService workReportService;
	
	/**通知Dao*/
	@Resource
	NoticeDao noticeDao;

	@Resource
	AccrualsRecordDao accrualsRecordDao;

	@Resource
	ProjectSignDao projectSignDao;
	
	@Resource
	DocTypeService docTypeService;
	
	@Resource
	OperateRecordService operateRecordService;
	/**
	 * 查询左侧工程列表
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryProject(ProjectQueryReq req) throws ParseException {
		List<String> projStuList = new ArrayList();
		List<ProjStatusEnum> enums = ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_SELF_CHECK.getValue(),ProjStatusEnum.TO_COMPLETION_DATA_AUDIT.getValue());
		for(ProjStatusEnum projStatusEnum :enums){
			projStuList.add(projStatusEnum.getValue());
		}
		req.setProjStuList(projStuList);//工程状态,待自检-待资料审核之间
		
		
		Map<String, Object> map=projectDao.queryProject(req);
		
		List<Project> list=(List<Project>) map.get("data");
		
		if(list!=null && list.size()>0){
			for(Project project:list){
				//查询所有的有效的
				List<ProjectSign> projectSignList=projectSignDao.findByProjIdAndStatus(project.getProjId(),IsSignEnum.IS_SIGN.getValue());
				if(projectSignList!=null && projectSignList.size()>0){
					project.setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//是特殊工程
				}
			}
		}
		
		return projectDao.queryProject(req);
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	@Override
	public DivisionalAcceptanceApply findById(String projId,String daaId) throws ParseException {
		DivisionalAcceptanceApply da=divisionalAcceptanceApplyDao.get(daaId);//分部验收申请
		Project pro = projectDao.get(projId);								 //工程
		ConstructionPlan cp=constructionPlanDao.viewPlanById(projId);        //计划
		DesignInfo di=designInfoDao.queryInfoByProjId(projId);
		LoginInfo login=SessionUtil.getLoginInfo();
		
		
		if(null!=da){
			if(pro!=null){
				da.setDeptName(pro.getDeptName());						//业务部门
				da.setCorpName(pro.getCorpName());						//分公司名称
				da.setContributionModeDes(pro.getContributionModeDes());//出资方式
				da.setProjectTypeDes(pro.getProjectTypeDes());
			}
			return da;
		}else{
			DivisionalAcceptanceApply das = new DivisionalAcceptanceApply();
			if(null!=pro){
				das.setAcceptDate(pro.getAcceptDate());		            //报建时间
				das.setProjId(projId);
				das.setProjName(pro.getProjName());
				das.setProjNo(pro.getProjNo());
				das.setProjAddr(pro.getProjAddr());
				das.setCustName(pro.getCustName());
				das.setProjScaleDes(pro.getProjScaleDes());
				das.setDeptName(pro.getDeptName());						//业务部门
				das.setCorpName(pro.getCorpName());						//分公司名称
				das.setContributionModeDes(pro.getContributionModeDes());//出资方式
				das.setProjectTypeDes(pro.getProjectTypeDes());
			}
			if(di!=null){
				das.setOcoDate(di.getOcoDate());						//设计委托时间
			}
			
			
			if(cp!=null){
				das.setCuName(cp.getCuName());
				das.setSuName(cp.getSuName());
				das.setCpArriveDate(cp.getCpArriveDate());				//计划编制时间
			}
			Contract con=contractDao.viewContractByprojId(projId);
			
			if(con!=null){
				das.setConNo(con.getConNo());
				//das.setPlannedEndDate(con.getPlannedEndDate());			//计划竣工时间
			}
			
			if(StringUtils.isNotBlank(projId)){
				WorkReport wr=workReportService.workReportDetail(projId,"");
				if(wr!=null){
					das.setPlannedEndDate(wr.getPlannedEndDate());			//计划竣工时间
					das.setStartDate(wr.getSuDate());						//进场时间
				}
			}
			
			
			das.setApplyer(login.getStaffName());						//申请人
			
			return das;
		}
	}
	
	/**
	 * 验收申请保存
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveDivisionalAcceptanceApply(DivisionalAcceptanceApply divisionalAcceptanceApply) {
		boolean flag = false;
		if(StringUtils.isBlank(divisionalAcceptanceApply.getDaaId())){
			divisionalAcceptanceApply.setDaaId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
			flag=true;
		}
		
		Project pro=projectDao.get(divisionalAcceptanceApply.getProjId());
		if(pro!=null){
			divisionalAcceptanceApply.setCorpId(pro.getCorpId());
			divisionalAcceptanceApply.setOrgId(pro.getOrgId());
			divisionalAcceptanceApply.setTenantId(pro.getTenantId());
		}
		
		LoginInfo login=SessionUtil.getLoginInfo();
		divisionalAcceptanceApply.setApplyer(login.getStaffName());
		divisionalAcceptanceApply.setApplyerId(login.getStaffId());
		
		
		divisionalAcceptanceApply.setAuditState(AuditResultEnum.NOT_APPLY.getValue());	//未推送
		divisionalAcceptanceApply.setAcceptanceState(AcceptanceAtateEnum.NOT_ACCEPTANCE.getValue());//未验收
		divisionalAcceptanceApplyDao.saveOrUpdate(divisionalAcceptanceApply);

		//标记生成款项未结清工程
		ChargeReq chargeReq = new ChargeReq();
		chargeReq.setProjId(divisionalAcceptanceApply.getProjId());
		chargeReq.setArFlag(ARFlagEnum.RECEIVE_ACCOUNT.getValue());
		chargeReq.setArOver("1");
		Map<String,Object> map =accrualsRecordDao.queryAccrualsRecord(chargeReq);
		List<AccrualsRecord> accrualsRecords = (List<AccrualsRecord>) map.get("data");
		if(accrualsRecords!=null && accrualsRecords.size()>0){
			ProjectSign projectSign = projectSignDao.findOnly(divisionalAcceptanceApply.getProjId(),ProjectSignTypeEnum.INCOMPLETE_COST.getValue());
			if(projectSign==null){
				ProjectSign projectSign1 = new ProjectSign();
				projectSign1.setProjId(divisionalAcceptanceApply.getProjId());
				projectSign1.setSignType(ProjectSignTypeEnum.INCOMPLETE_COST.getValue());//款项未结清工程
				projectSign1.setStatus(IsSignEnum.IS_SIGN.getValue());
				projectSignDao.save(projectSign1);
			}
		}
		//新增，则生成待推送的待办通知
		if(flag){
			Staff staff = new Staff();
			staff.setStaffId(SessionUtil.getLoginInfo().getStaffId());
			staff.setStaffName(SessionUtil.getLoginInfo().getStaffName());
			operateRecordService.cerateCurOperateRecord(projectDao.get(divisionalAcceptanceApply.getProjId()), divisionalAcceptanceApply.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.DIVISIONAL_ACCEPT.getValue(),divisionalAcceptanceApply.getDaaId(),staff,null,true);
	
		}
	}
	/**
	 * 推送到审核中
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void pushDivisionalAcceptanceApply(String daId) {
		LoginInfo login=SessionUtil.getLoginInfo();
		DivisionalAcceptanceApply divisionalAcceptanceApply=divisionalAcceptanceApplyDao.get(daId);
		if(divisionalAcceptanceApply!=null){
			divisionalAcceptanceApply.setApplyDate(divisionalAcceptanceApplyDao.getDatabaseDate());
			divisionalAcceptanceApply.setAuditState(AuditResultEnum.APPLYING.getValue());//审核中
			divisionalAcceptanceApply.setSignBack("");
			divisionalAcceptanceApply.setApplyer(login.getStaffName());
			divisionalAcceptanceApply.setApplyerId(login.getStaffId());
			divisionalAcceptanceApplyDao.saveOrUpdate(divisionalAcceptanceApply);
			//生成审核待办通知
			Staff staff = new Staff();
			Project pro=projectDao.get(divisionalAcceptanceApply.getProjId());
			operateRecordService.cerateCurOperateRecord(pro, divisionalAcceptanceApply.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.DIVISIONAL_ACCEPT.getValue(),divisionalAcceptanceApply.getDaaId(),staff,"1",true);
		}
	}
	
	/**
	 * 查询分部验收申请列表
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryDivisionalAcceptanceApply(DivisionalAcceptanceReq req) throws ParseException {
		
		Map<String, Object> map= divisionalAcceptanceApplyDao.queryDivisionalAcceptanceApply(req);
		List<DivisionalAcceptanceApply> list=(List<DivisionalAcceptanceApply>) map.get("data");
		
		
		if(list!=null && list.size()>0){
			for(DivisionalAcceptanceApply divisionalAcceptanceApply:list){
				
				
					Project project=projectDao.get(divisionalAcceptanceApply.getProjId());
					if(project!=null){
						divisionalAcceptanceApply.setProjectType(project.getProjectType());
					}
				
				
				//查询所有的有效的
				List<ProjectSign> projectSignList=projectSignDao.findByProjIdAndStatus(divisionalAcceptanceApply.getProjId(),IsSignEnum.IS_SIGN.getValue());
				if(projectSignList!=null && projectSignList.size()>0){
					divisionalAcceptanceApply.setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//是特殊工程
				}
			}
		}
		
		if(list!=null && list.size()>0){
			for(DivisionalAcceptanceApply daa:list){
				Project pro =projectDao.get(daa.getProjId());
				if(pro!=null){
					daa.setProjStateDes(ProjStatusEnum.valueof(pro.getProjStatusId()).getMessage());
					
				}
			}
			
		}
		map.put("data", list);
		return map;
	}
	
	
	/**
	 * 查询分部验收审核列表
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryDivisionalAcceptanceAudit(DivisionalAcceptanceReq req) throws ParseException {
		String grade=null;
		//DocType docType= docTypeDao.findByStepId(StepOutWorkflowEnum.DIVISIONAL_ACCEPTANCE_AUDIT.getValue());
		//grade=docType==null?"":docType.getGrade();	
		DocType docType=new DocType();
		//SystemSet systemSet=systemSetDao.get("stepId", req.getStepId());
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
        SystemSet systemSet=systemSetDao.querySystemSetByStepId(req.getStepId(),loginInfo.getCorpId());
		if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
			req.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		}
		
		Map<String, Object> result= divisionalAcceptanceApplyDao.queryDivisionalAcceptanceApply(req);
		List<DivisionalAcceptanceApply> data = (List<DivisionalAcceptanceApply>) result.get("data");
		
		Project pro=new Project();
		if(data!=null && data.size()>0){
			for(int i = 0;i<data.size();i++){
				
				Project project=projectDao.get(data.get(i).getProjId());
				if(project!=null){
					data.get(i).setProjectType(project.getProjectType());
				}
				
				
				//查询所有的有效的
				List<ProjectSign> projectSignList=projectSignDao.findByProjIdAndStatus(data.get(i).getProjId(),IsSignEnum.IS_SIGN.getValue());
				
				if(projectSignList!=null && projectSignList.size()>0){
					data.get(i).setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//是特殊工程
				}
				
				DivisionalAcceptanceApply da=data.get(i);
				docType = docTypeService.findByStepId(StepOutWorkflowEnum.DIVISIONAL_ACCEPTANCE_AUDIT.getValue(),project.getCorpId(),project.getProjectType(),project.getContributionMode());
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					grade=docType.getGrade();
				}else{
					grade="0";
				}
				data.get(i).setLevel(grade);// 设置审核总级数（合同审核2级审核）
				Map<String,String> levelBtn = new HashMap();
				//以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for(int n=1;n<Integer.parseInt(grade)+1;n++){
					if(n==1){
						levelBtn.put("level"+n, "2");// 待审
					}else{
						levelBtn.put("level"+n, "-1");//未审
					}
				}
				List<ManageRecord> mrls = manageRecordDao.findByStepIdBusinessOrderId(data.get(i).getDaaId(),StepOutWorkflowEnum.DIVISIONAL_ACCEPTANCE_AUDIT.getValue(),MrResultEnum.PASSED.getValue());
				if(mrls!=null && mrls.size()>0){
					String size = mrls.size()+"";
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
				
				pro=projectDao.get(data.get(i).getProjId());
				if(pro != null){
					data.get(i).setProjectTypeDes(pro.getProjectTypeDes());//工程类型描述
					data.get(i).setContributionModeDes(pro.getContributionModeDes());//出自方式描述
				}
			}
			result.put("data", data);
		}
		return result;
	}

	@Override
	public void updateDivisionalAcceptanceApply(DivisionalAcceptanceApply divisApply) {
		divisionalAcceptanceApplyDao.saveOrUpdate(divisApply);
	}

	@Override
	public Integer countDivisonalAcceptanceRecord(String projId) throws Exception {
		// TODO Auto-generated method stub
		return divisionalAcceptanceApplyDao.countDivisonalAcceptanceRecord(projId);
	}
}
