package cc.dfsoft.project.biz.base.project.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.accept.dao.RaiseMoneyDao;
import cc.dfsoft.project.biz.base.accept.entity.ProjectApplication;
import cc.dfsoft.project.biz.base.accept.entity.RaiseMoney;
import cc.dfsoft.project.biz.base.accept.enums.MaterialFlagEnum;
import cc.dfsoft.project.biz.base.accept.service.ProjectApplicationService;
import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.CustomerDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.TimeLimitDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkDayDto;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.entity.Customer;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.entity.TimeLimit;
import cc.dfsoft.project.biz.base.baseinfo.entity.WorkFlow;
import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.CorrelationTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.ProjectMethodEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.TimeLimitTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.CorrelationService;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.FestivalService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.entity.GovAuditCost;
import cc.dfsoft.project.biz.base.budget.enums.BudgetMethodEnum;
import cc.dfsoft.project.biz.base.budget.enums.GovAuditCostTypeEnum;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.budget.service.GovAuditCostService;
import cc.dfsoft.project.biz.base.change.enums.ListPagingUtil;
import cc.dfsoft.project.biz.base.charge.dao.AccrualsRecordDao;
import cc.dfsoft.project.biz.base.charge.dto.ChargeReq;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ARStatusEnum;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.complete.dao.JointAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.constructmanage.dao.CompleteReportDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.CompleteReport;
import cc.dfsoft.project.biz.base.constructmanage.entity.WorkReport;
import cc.dfsoft.project.biz.base.constructmanage.service.ConstructionOrganizationService;
import cc.dfsoft.project.biz.base.constructmanage.service.WorkReportService;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.dao.SupplementalContractDao;
import cc.dfsoft.project.biz.base.contract.dto.ProjectCostReq;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.enums.IsIntelligentConPayEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.contract.service.ProjectCostService;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.dao.SurveyInfoDao;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.entity.SurveyInfo;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.inspection.dto.MeasurementReq;
import cc.dfsoft.project.biz.base.inspection.entity.Measurement;
import cc.dfsoft.project.biz.base.inspection.service.MeasurementService;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.messagesync.conrtact.ContractInfo;
import cc.dfsoft.project.biz.base.messagesync.conrtact.ProjectContractInfo;
import cc.dfsoft.project.biz.base.messagesync.conrtact.ProjectInfo;
import cc.dfsoft.project.biz.base.messagesync.conrtact.SubContractInfo;
import cc.dfsoft.project.biz.base.messagesync.conrtact.SupplementalContractInfo;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.dao.InstTasksDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.entity.InstTasks;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.dao.ApplyDelayDao;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectScaleDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectSignDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectTypeDao;
import cc.dfsoft.project.biz.base.project.dao.SignatureDao;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.OperateRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectViewDto;
import cc.dfsoft.project.biz.base.project.dto.SignatureQueryDto;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.entity.ContributionMode;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectScale;
import cc.dfsoft.project.biz.base.project.entity.ProjectSign;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.entity.ScaleDetail;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.AreaEnum;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.OperateWorkFlowEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjSourceEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjectScaleTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.enums.StepLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ScaleDetailService;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.service.SettlementDeclarationService;
import cc.dfsoft.project.biz.base.statisticquery.dto.EchartsDto;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.service.SubBudgetService;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.UpdateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.project.biz.ifs.log.dao.WebServiceSetDao;
import cc.dfsoft.project.biz.ifs.log.dao.WebserviceLogDao;
import cc.dfsoft.project.biz.ifs.log.entity.WebServiceSet;
import cc.dfsoft.project.biz.ifs.project.dto.ProjectAcceptDto;
import cc.dfsoft.project.biz.ifs.project.enums.OperateTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.BusinessTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.BeanUtil;
import cc.dfsoft.uexpress.common.util.FestivalUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import cc.dfsoft.uexpress.common.util.WorkFlowUtil;


/**
 * 工程服务接口实现
 *
 * @author pengtt
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ProjectServiceImpl implements ProjectService {

	@Resource
	ProjectDao projectDao;

	@Resource
	ScaleDetailService scaleDetailService;

	/** 业务操作记录服务接口 */
	@Resource
	OperateRecordService operateRecordService;

	/** 审核记录服务接口 */
	@Resource
	ManageRecordDao managerecorddao;

	/** 业务操作记录服务接口 */
	@Resource
	OperateRecordDao operateRecordDao;

	/** 施工计划 */
	@Resource
	ConstructionPlanDao constructionPlanDao;

	/** 签字记录 */
	@Resource
	SignatureDao signatureDao;

	/** 客户 */
	@Resource
	CustomerDao customerDao;
	/** 附件*/
	@Resource
	AccessoryDao accessoryDao;


	/**合同*/
	@Resource
	ContractDao contractDao;

	@Resource
	ContractService contractService;
	/**合同*/
	@Resource
	SubContractDao subContractDao;

	@Resource
	SystemSetDao systemSetDao;
	@Resource
	ProjectTypeDao projectTypeDao;
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;

	@Resource
	WorkFlowService workFlowService;

	@Resource
	DocTypeService docTypeService;
	@Resource
	ProjectCostService projectCostService;
	@Resource
	InstTasksDao instTasksDao;
	

	/**设计信息Dao*/
	@Resource
	DesignInfoDao designInfoDao;
	
	/** 关联服务接口*/
	@Resource
	CorrelationService correlationService;
	/** 关联服务接口*/
	@Resource
	ApplyDelayDao applyDelayDao;
	

	/**员工Dao*/
	@Resource
	StaffDao staffDao;
	
	/** 时限Dao*/
	@Resource
	TimeLimitDao timeLimitDao;
	
	/**工程标记*/
	@Resource
	ProjectSignDao projectSignDao;
	
	/** 工程细类Dao*/
	@Resource
	ProjectScaleDao projectScaleDao;
	/** 业务合作伙伴Dao*/
	@Resource
	BusinessPartnersDao businessPartnersDao;
	@Resource
	IFinanceInfoService financeInfoService;
	@Resource
	DepartmentDao departmentDao;
	@Resource
	IFinanceInfoService iFinanceService;
	@Resource
	WebServiceSetDao webServiceSetDao;
	
	@Resource
	FestivalService festivalService;
	@Resource
	ProjectApplicationService projectApplicationService;
	@Resource
	SubBudgetService subBudgetService;
	@Resource
	SettlementDeclarationService settlementDeclarationService;
	@Resource
	GovAuditCostService govAuditCostService;
	@Resource
	ManageRecordService manageRecordService;
	@Resource
	StaffService staffService;
	@Resource
	SubContractService subContractService;
	@Resource
	WorkReportService workReportService;
	@Resource
	CompleteReportDao completeReportDao;
	@Resource
	JointAcceptanceDao jointAcceptanceDao;
	@Resource
	BudgetService budgetService;
	
	@Resource
	MeasurementService measurementService;
	@Resource
	RaiseMoneyDao raiseMoneyDao;
	
	@Resource
	AccrualsRecordDao accrualsRecordDao;

	@Resource
	SurveyInfoDao surveyInfoDao;
	@Resource
	WebserviceLogDao webserviceLogDao;
	@Resource
	ConstructionOrganizationService constructionOrganizationService;
	@Resource
	AbandonedRecordService abandonedRecordService;
	@Resource
	SupplementalContractDao supplementalContractDao;

	@Override
	public Map<String, Object> queryProject(ProjectQueryReq projectQueryReq) throws ParseException {

		projectQueryReq.setProjStuList(obtainProjStatusList(projectQueryReq));

		if ("projStatusDes".equals(projectQueryReq.getSortName())) {
			projectQueryReq.setSortName("projStatusId");
		}
		Map<String , Object> map = projectDao.queryProject(projectQueryReq);
		List<Project> listProject = (List<Project>)map.get("data");
		if(listProject !=null && listProject.size() > 0){
			for (Project project : listProject) {
				 List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(project.getCorpId()+"_"+"130138");
			   if(list != null && list.size() >0){  //判断是否配置有计量表打印
				   MeasurementReq measurementReq = new MeasurementReq();  //新建一个实体辅助类
					measurementReq.setProjId(project.getProjId());         //设置工程ID
					try {
						Map<String, Object> measurementMap = measurementService.queryMeasurement(measurementReq);
						List<Measurement> listMeasurement= (List<Measurement>)measurementMap.get("data");
						if(listMeasurement !=null && listMeasurement.size() > 0 ){
							project.setIsMeasurement("1");  //具有计量表
						}else{
							project.setIsMeasurement("0");  //无计量表
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
			   }
				
			}
		}
		return map;

	}


	public List<String> obtainProjStatusList(ProjectQueryReq req){
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		boolean startNotBlank = StringUtils.isNotBlank(req.getStartStatusId());
		boolean endNotBlank = StringUtils.isNotBlank(req.getEndStatusId());

		if (startNotBlank && endNotBlank){
			//将工程状态转为操作步骤
			String startStep = WorkFlowActionEnum.getStepCodeByStatusCode(req.getStartStatusId());
			String endStep = WorkFlowActionEnum.getStepCodeByStatusCode(req.getEndStatusId());

			DataFilerSetUpDto dfd = Constants.isConfig(loginInfo.getCorpId() + "_workFlow_0");
			if (dfd!=null){
				return workFlowCodeToStatus(startStep,endStep,dfd.getSupSql());
			}else {
				//默认使用该公司下的公建-用户出资-主流程的工作流
				WorkFlow workFlow= workFlowService.queryWorkFlowCode( loginInfo.getCorpId(),ProjLtypeEnum.PUBLIC.getValue(),"1","1");
				if (workFlow!=null) {
					return workFlowCodeToStatus(startStep, endStep, workFlow.getWorkFlowCode());
				}
			}

		}else if (startNotBlank){
			req.setProjStatusId(req.getStartStatusId());
		}else if (endNotBlank){
			req.setProjStatusId(req.getEndStatusId());
		}
		return null;
	}



	public List<String> workFlowCodeToStatus(String startStep,String endStep,String workFlowCode){
		List<String > resultList=new ArrayList<>();
		if (StringUtils.isNotBlank(workFlowCode)){

			String [] workFlowArr=workFlowCode.split(",");
			int startPosition = Arrays.binarySearch(workFlowArr, startStep);
			int endPosition = Arrays.binarySearch(workFlowArr, endStep);

			if (startPosition>endPosition) return null;

			//获取开始状态到结束状态之间的工作流
			workFlowArr=Arrays.copyOfRange(workFlowArr, startPosition<0?0:startPosition, endPosition<0?workFlowArr.length:endPosition+1);

			for (String s:workFlowArr) {//将操作步骤转为工程状态add到List
				String statusCode = WorkFlowActionEnum.getStatusCodeByStepCode(s);
				resultList.add(statusCode);
			}
		}
		return resultList;
	}








	@Override
	public Map<String, Object> queryProjectByTime(ProjectQueryReq projectQueryReq) throws ParseException {
		/*
		List listc=cashFlowDao.queryCashFlawByProjID(projectQueryReq.getProjId());
		System.err.println(listc);*/
		// 最后一个操作记录的工程记录列表（符合状态的当前工程）
		List<Map<String, Object>> ors;
		
		Map<String, Object> map = projectDao.queryProject(projectQueryReq);
		// 符合当前状态的工程列表
		List<Project> list = (List<Project>) map.get("data");
		
		for (Project pro : list) {
			SystemSet systemSet=systemSetDao.querySystemSetByStepId(projectQueryReq.getStepId(),pro.getCorpId());
			//SystemSet systemSet=systemSetDao.get("stepId", projectQueryReq.getStepId());
			if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
			projectQueryReq.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
			}
			// 当前步骤
			ors = operateRecordDao.getOptRecordByTime(projectQueryReq.getProjStatusId());

			// 时间限制（单位天）
			long timel = projectQueryReq.getTimeLimit()!=null?projectQueryReq.getTimeLimit():0;
			long secondsLimit=-1l;
			if(timel!=0){
				secondsLimit=timel*24*60*60;
			}
			List<ProjectSign> projectSignList=projectSignDao.findByProjIdAndStatus(pro.getProjId(),IsSignEnum.IS_SIGN.getValue());
			if(projectSignList!=null && projectSignList.size()>0){
				pro.setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//是特殊工程
			}
			SubContract sc = subContractDao.findSubContractByprojId(pro.getProjId());
			if(sc!=null){
				pro.setScNo(sc.getScNo());//合同编号
				pro.setCuName(sc.getCuName());//施工单位
			}

			Contract contract = contractDao.viewContractByprojId(pro.getProjId());
			if(contract!=null){
				pro.setConNo(contract.getConNo());//合同编号
			}
			for (Map<String, Object> or : ors) {
				if (or.get("PROJ_ID").equals(pro.getProjId())) {
					// 业务操作记录中时间
					Date oldTime = (Date) or.get("OPERATE_TIME");
					//oldTime = FestivalUtil.getChangeDate(oldTime);//业务操作时间
					// 当前时间
					Date nowTime = projectDao.getDatabaseDate();
					//获取两个日期之间的工作日天数
					long workDays = 0;
					if(oldTime!=null){
						try {
							workDays = FestivalUtil.calLeaveDays(oldTime, nowTime, festivalService.getCacheMap(Constants.WORKDAYS), festivalService.getCacheMap(Constants.HOLIDAYS));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					long seconds = workDays*24*60*60;
					//如果是施工预算审核，那么原定时长为
					if(StepEnum.QUALITIES_JUDGEMENT.getValue().equals(projectQueryReq.getStepId()) && pro.getSubmitAmount()!=null){
						String timeLimitType = null;
						if(pro.getSubmitAmount().compareTo(new BigDecimal(50000))<0){
							timeLimitType = TimeLimitTypeEnum.TIME_LIMIT13.getValue();
						}else if(pro.getSubmitAmount().compareTo(new BigDecimal(100000))<0){
							timeLimitType = TimeLimitTypeEnum.TIME_LIMIT14.getValue();
						}else if(pro.getSubmitAmount().compareTo(new BigDecimal(500000))<0){
							timeLimitType = TimeLimitTypeEnum.TIME_LIMIT15.getValue();
						}else if(pro.getSubmitAmount().compareTo(new BigDecimal(1000000))<0){
							timeLimitType = TimeLimitTypeEnum.TIME_LIMIT16.getValue();
						}else{
							timeLimitType = TimeLimitTypeEnum.TIME_LIMIT17.getValue();
						}
						TimeLimit  timeLimit = timeLimitDao.findByType(timeLimitType);
						if(null!=timeLimit){
							pro.setAcquisitionDays(timeLimit.getTlDuration().toString());
							if(timeLimit.getTlDuration().compareTo(BigDecimal.ZERO)>0){
								long days = timeLimit.getTlDuration().setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
								if(days>0){
									secondsLimit=days*24*60*60;
								 }
								timel = days;
							}
						}
					}
					//该步骤延期申请已通过的时长之和
					long delyDays =applyDelayDao.getDelyDays(pro.getProjId(),projectQueryReq.getStepId()).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
					if(delyDays>0){
						secondsLimit+=(delyDays*24*60*60);
						timel +=delyDays;
					}
					//用于时限进度展示
					WorkDayDto workDayDto = new WorkDayDto();
					workDayDto.setDaysLimit(String.valueOf(timel));
					workDayDto.setWorkDays(String.valueOf(workDays));
					workDayDto.setHaveDays(String.valueOf(timel-workDays));
					pro.setWorkDayDto(workDayDto);
					// 如果当前时间-上个步骤的操作时间大于时间限制段则为超时
					if (secondsLimit>0&&seconds > secondsLimit) {
						long differ = seconds - secondsLimit;
						int i = (int) Math.ceil(differ / (24 * 60 * 60));
						pro.setOverDay(i);
						pro.setOverdue(true);
						continue;

					}
				}
			}

		}
		return map;
	}

	@Override
	public List<Project> findByProjNo(String projNo) {

		return projectDao.findByProjNo(projNo);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
	public String acceptTotalSave(Project project, boolean flag) throws Exception {
		Project projectResult = project;
		ResultMessage resultMessage = new ResultMessage();
		int isCall = 1;//调用接口
		//ProjectApplication application = new ProjectApplication();
		LoginInfo login=SessionUtil.getLoginInfo();
		if (StringUtils.isBlank(project.getProjId())) { // 新增
			// 该工程编号已存在
			if (this.findByProjNo(project.getProjNo()).size() > 0) {
				return "exist";
			}
			// 新增对象 PROJ_SOURCE
			String projId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);// 生成唯一ID
			projectResult.setProjId(projId);
			projectResult.setCorpId(login.getCorpId());
			projectResult.setTenantId(SessionUtil.getTenantId());//暂时存上
			projectResult.setOrgId(login.getOrgId());
			projectResult.setCorpName(login.getCorpName());
			String projNo = this.getProjMaxNo(login.getCorpId(),projectResult.getProjectType(),projectResult.getContributionMode());
			if(projNo.equals("noneNumber")){
				return "noneNumber";
			}
			if("1".equals(project.getArea())){//遵义新蒲新区的工程，工程编号前加“新”
				projNo = Constants.ZUNYI_XINPU_PROJNO_PREX+projNo;
			}
			projectResult.setProjNo(projNo);
			projectResult.setArea(StringUtil.isNotBlank(project.getArea())?project.getArea():"");
			projectResult.setAcceptType(ProjectMethodEnum.PROJECT_APPLY.getValue());
			//projectResult.setFeedbackState(FeedbackStateEnum.TO_FEEDBACK.getValue());//反馈状态
			projectResult.setAcceptDate(projectDao.getDatabaseDate()); // 受理日期
			projectResult.setProjSource(ProjSourceEnum.HALL.getValue()); // 受理来源
			//projectResult.setGoodsCompleteStatus(GoodsCompleteStatusEnum.HAVE_NOT_FINISHIED.getValue());//未发完货
			
			projectResult.setAccepter(login.getStaffName());
			projectResult.setAccepterId(login.getStaffId());
			projectResult.setSurveyerId(login.getStaffId());
			projectResult.setSurveyer(login.getStaffName());
			projectResult.setToDoer(login.getStaffName());//未推送时待办人
			
			isCall = 1;//新增
			
		} else { // 修改
			projectResult = projectDao.get(project.getProjId());
			if(MaterialFlagEnum.YES.getValue().equals(projectResult.getMaterialFlag())){
				isCall = 2;//修改
			}
			Department department=departmentService.queryDepartment(project.getCorpId());
			Correlation correlation = correlationService.findCode(CorrelationTypeEnum.PROJTYPE_CONTRIBUTION.getValue(),project.getProjectType(),project.getContributionMode(),project.getCorpId());
			if(correlation==null ||StringUtil.isBlank(correlation.getContributionCode())){
				//默认取1101
				correlation = correlationService.findCode(CorrelationTypeEnum.PROJTYPE_CONTRIBUTION.getValue(),project.getProjectType(),project.getContributionMode(),Constants.START_REPORT_CPT_CORP_MODE);
			}
			String projMaxNo;
			String projNo;
			if("1".equals(project.getArea())){//遵义新蒲新区的工程，工程编号前加“新”
				projMaxNo = project.getProjNo().substring(department.getDeptInnerCode().length()+3, project.getProjNo().length()-1);
			}else{
				projMaxNo = project.getProjNo().substring(department.getDeptInnerCode().length()+2, project.getProjNo().length()-1);
			}
			StringBuffer pro = new StringBuffer("");
			String code="";
			if(correlation!=null){
				code=correlation.getContributionCode();
			}
			if(code.equals("")){
				return "noneNumber";
			}
			projNo = pro.append(department.getDeptInnerCode()).append(code).append(projMaxNo).append("0").toString();
			if("1".equals(project.getArea())){//遵义新蒲新区的工程，工程编号前加“新”
				projNo = Constants.ZUNYI_XINPU_PROJNO_PREX+projNo;
			}

			if (StringUtils.isBlank(projectResult.getDeptTransfer())){
				projectResult.setDeptTransfer(projectResult.getDeptId());
			}
			projectResult.setProjNo(projNo);
			projectResult.setArea(StringUtil.isNotBlank(project.getArea())?project.getArea():"");
			projectResult.setProjName(project.getProjName());
			projectResult.setCustName(project.getCustName()); 				// 申请单位
			projectResult.setCustId(project.getCustId());					// 申请单位id
			projectResult.setProjInfoFlag(project.getProjInfoFlag()); 		// 告知书是否发放
			projectResult.setProjLtypeId(project.getProjLtypeId()); 		// 工程大类id
			projectResult.setCustContact(project.getCustContact()); 		// 联系人
			projectResult.setCustPhone(project.getCustPhone()); 			// 联系电话
			projectResult.setProjSubConSta(project.getProjSubConSta()); 	// 主建设阶段
			projectResult.setProjAddr(project.getProjAddr()); 				// 工程地点
			//projectResult.setProjLongitude(project.getProjLongitude()); 	// 经度
			//projectResult.setProjLatitude(project.getProjLatitude()); 	// 纬度
			projectResult.setProjectType(project.getProjectType());			//工程类型
			projectResult.setProjectTypeDes(project.getProjectTypeDes());
			projectResult.setContributionMode(project.getContributionMode());
			projectResult.setContributionModeDes(project.getContributionModeDes());//出资方式
			projectResult.setDeptId(project.getDeptId());					//业务部门
			projectResult.setDeptName(project.getDeptName());
			projectResult.setProjectRemark(project.getProjectRemark());                   //备注
			projectResult.setIsFunds(StringUtil.isNotBlank(project.getIsFunds())?project.getIsFunds():"");
			projectResult.setProjSubConSta(StringUtil.isNotBlank(project.getProjSubConSta())?project.getProjSubConSta():"");
			projectResult.setIsBidding(StringUtil.isNotBlank(project.getIsBidding())?project.getIsBidding():"");
			projectResult.setMaterialFlag(StringUtil.isNotBlank(project.getMaterialFlag())?project.getMaterialFlag():"");
			projectResult.setTransfeFlag(StringUtil.isNotBlank(project.getTransfeFlag())?project.getTransfeFlag():"");
			projectResult.setIsIndoor(StringUtil.isNotBlank(project.getIsIndoor())?project.getIsIndoor():"");
			projectResult.setArea(project.getArea());

			if(StringUtil.isNotBlank(project.getProjStatusId())){			//工程状态
				projectResult.setProjStatusId(project.getProjStatusId());
			}
			if (flag&&project.getFlag().equals("1")) { // 如果是立项确认则需要产生业务操作记录
				// 形成操作记录
				String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
				operateRecordService.createOperateRecord(orId, projectResult.getProjId(), projectResult.getProjNo(),
						StepEnum.PROJECT_CONFIRM.getValue(), StepEnum.PROJECT_CONFIRM.getMessage(), "");
			}
		}
		List<ScaleDetail> scaleDetails = project.getScaleDetails();
		String totalDes = new String();
		
		if (scaleDetails != null && scaleDetails.size() > 0) {
			String[] LtypeId = project.getProjLtypeId().split(",");
			List<ProjectType> list=projectTypeDao.getAll();
			for (int i = 0; i < LtypeId.length; i++) {
				StringBuffer scaleDes = new StringBuffer(); // 总体规模描述
				String ltype = LtypeId[i];				
				String ltypeDes=this.getTypeDes(ltype,list);
				//String ltypeDes = ProjLtypeEnum.valueof(ltype).getMessage();
				scaleDes.append(ltypeDes).append("——");
				// for(ScaleDetail sdetail:scaleDetails){
				for (int k = 0; k < scaleDetails.size(); k++) {
					ScaleDetail sdetail = scaleDetails.get(k);
					sdetail.setScaleId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
					sdetail.setProjId(projectResult.getProjId());
					sdetail.setProjNo(projectResult.getProjNo());
					if (sdetail.getProjLtypeId().equals(ltype)) {
						scaleDes.append(sdetail.getProjStypeDes());
						if (sdetail.getHouseNum() != null && sdetail.getHouseNum() > 0) {
							scaleDes.append(sdetail.getHouseNum()).append("户,");
						}
						if (sdetail.getSearNum() != null && sdetail.getSearNum() > 0) {
							scaleDes.append(sdetail.getSearNum()).append("座 ,");
						}
						if (sdetail.getNum() != null && sdetail.getNum() > 0) {
							scaleDes.append(sdetail.getNum()).append("台,");
						}
						if (sdetail.getTonnage() != null && sdetail.getTonnage() > 0) {
							scaleDes.append(sdetail.getTonnage()).append("吨,");
						}
						//干线 改管
						if (sdetail.getPipeDiameter() != null) {
							scaleDes.append("管径").append(sdetail.getPipeDiameter()).append(",");
						}
						if (sdetail.getPipeLength() != null) {
							scaleDes.append("长度").append(sdetail.getPipeLength()).append("千米,");
						}
						if (sdetail.getFinishLength() != null) {
							scaleDes.append("完成").append(sdetail.getFinishLength()).append("千米,");
						}
						if (sdetail.getConstructionRatio() != null) {
							scaleDes.append("建设比例").append(sdetail.getConstructionRatio()).append(",");
						}
						if (sdetail.getGasConsumption() != null && sdetail.getGasConsumption() > 0) {
							scaleDes.append("预计用气量 ").append(sdetail.getGasConsumption()).append(" m³/h,");
						}
					}
				}
				totalDes = totalDes + ((scaleDes.toString()).substring(0, scaleDes.toString().length() - 1)) + ";";
			}
		}
		projectResult.setProjScaleDes(totalDes); // 总体规模描述
		Map<String, Object> mapSD = new HashMap<String, Object>();
		mapSD.put("data", scaleDetails);
		mapSD.put("projId", projectResult.getProjId());
		//新增或更新客户对象
			Customer customer;
			if(StringUtils.isNotBlank(projectResult.getCustId())){
				customer = new Customer();
				customer = customerDao.get(projectResult.getCustId());
			}else{
				//没有客户ID的，默认燃气公司为客户
				Department department = departmentDao.get(projectResult.getCorpId());
				customer = customerDao.queryByCustCode(department.getDeptOutCode());
				if(customer==null){
					customer = new Customer();
					customer.setCustId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));//客户ID
					customer.setCustName(department.getDeptName());//客户名称
					customer.setCustCode(department.getDeptOutCode());//客户编码
					customer.setCorpId(projectResult.getCorpId());
					customerDao.saveOrUpdate(customer);
				}
				projectResult.setCustId(customer.getCustId());
				
			}
			
		if("push".equals(project.getSaveType())){
			// 形成操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
			//String toDoer =operateRecordService.createOperateRecord(orId, projectResult.getProjId(), projectResult.getProjNo(),StepEnum.PROJECT_ACCEPT.getValue(), StepEnum.PROJECT_ACCEPT.getMessage(), "");
			//初始化主流程操作记录
			HashMap<String, Object> toDoer =operateRecordService.cerateOperateWorkFlowRecord(projectResult,StepEnum.PROJECT_ACCEPT.getValue(),login,WorkFlowTypeEnum.MAIN_PROGRESS.getValue(),null);

			projectResult.setToDoer(String.valueOf(toDoer.get("NAME")));//待办人
		}	
		if(StringUtil.isNotBlank(project.getSurveyBuilder())){
			projectResult.setSurveyBuilder(project.getSurveyBuilder());    //踏勘可选现场代表
		}
		if(StringUtil.isNotBlank(project.getSurveyBuilderId())){
			projectResult.setSurveyBuilderId(project.getSurveyBuilderId()); //踏勘可选现场代表
		}
		projectDao.saveOrUpdate(projectResult);	
		
		// 调用工程规模明细处理方法
		scaleDetailService.batMaintain(mapSD);
		String operateType = FinanceOperateTypeEnum.PROJ_ACCEPT_INSERT.getValue();
		String serviceNo=WebServiceTypeEnum.PROJ_ACCEPT_INSERT.getValue();
		if(isCall == 1){
			operateType = FinanceOperateTypeEnum.PROJ_ACCEPT_INSERT.getValue();
			serviceNo=WebServiceTypeEnum.PROJ_ACCEPT_INSERT.getValue();
		}else{
			operateType = FinanceOperateTypeEnum.PROJ_ACCEPT_UPDATE.getValue();
			serviceNo=WebServiceTypeEnum.PROJ_ACCEPT_UPDATE.getValue();
		}
		//如果退单，则工程已调用过删除了，NC已不存在次项目，不再调用接口
		if(this.isToCall(projectResult.getProjId(), serviceNo) && StringUtils.isBlank(project.getBackReason()) && MaterialFlagEnum.YES.getValue().equals(project.getMaterialFlag())) {
			//立项时-存在借料todo:
			//调用nc系统工程信息同步接口
			String res = iFinanceService.synProjectInfoClient(projectResult.getProjId(), operateType, null,IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
			JSONObject jsonbean = JSONObject.fromObject(res);
			//返回信息-当接口返回失败时，抛出异常
			resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
			if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && this.isSynchronization(projectResult.getProjId(), serviceNo)){
				//回滚事物
				throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
			}
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	/**
	 * 工程详述
	 * 
	 * @param id
	 *            地址id
	 * @return
	 */
	@Override
	public Project viewProject(String id) {
		Project  pro = projectDao.get(id);
		List<SurveyInfo> surveyInfo = surveyInfoDao.findByProjId(id);
		if(surveyInfo != null && surveyInfo.size() > 0){
			if(StringUtils.isNotBlank(surveyInfo.get(0).getSurveyDes())){
				pro.setSurveyDes(surveyInfo.get(0).getSurveyDes());  //获取技踏勘结果
			}
			if(StringUtils.isNotBlank(surveyInfo.get(0).getTechnicalSuggestion())){
				pro.setTechnicalSuggestion(surveyInfo.get(0).getTechnicalSuggestion()); //过去技术意见
			}
		}
		DesignInfo di=designInfoDao.queryInfoByProjId(id);
		String acquisitionDay = "";
		if(di!=null){
			pro.setOcoDate(di.getOcoDate());//设计委托日期
			BigDecimal delyDays =applyDelayDao.getDelyDays(id,StepEnum.DESIGN_DRAWING.getValue());
			if(StringUtil.isNotBlank(di.getAcquisitionDays())){//委托天数
				acquisitionDay = di.getAcquisitionDays();
				BigDecimal acquisitionDays = new BigDecimal(di.getAcquisitionDays());
				delyDays = delyDays.add(acquisitionDays);
			}
			 int days = delyDays.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
			 if(days>0){
				 //计算计划设计完成日期（只计算工作日）
				 Date date = FestivalUtil.calLeaveDate(di.getOcoDate(), days, festivalService.getCacheMap(Constants.WORKDAYS), festivalService.getCacheMap(Constants.HOLIDAYS));
				if(date!=null){
					pro.setDuPlCompleteDate(date);
				}
				//pro.setDuPlCompleteDate(DateUtil.addDay(di.getOcoDate(), days));
			}
			
		}
		//已有设计计划天数，取已有，否则根据工程量计算
		if(StringUtil.isNotBlank(acquisitionDay)){
			pro.setAcquisitionDays(acquisitionDay);
		}else{
			//查出该工程的所有规模
			List<ScaleDetail> scaleDetails = scaleDetailService.findByprojId(pro.getProjId());
			//判断工程类型
			//民用
			if(ProjLtypeEnum.CIVILIAN.getValue().equals(pro.getProjLtypeId())){
				//求户数的和
				if(null!=scaleDetails&&scaleDetails.size()>0){
					Integer houseNumber = 0;
					for(ScaleDetail scaleDetail : scaleDetails){
						if(null!=scaleDetail.getHouseNum()){
							houseNumber+=scaleDetail.getHouseNum();
						}
					}
					String timeLimitType = null;
					if(houseNumber<=100){
						timeLimitType = TimeLimitTypeEnum.TIME_LIMIT1.getValue();
					}else if(houseNumber<=500){
						timeLimitType = TimeLimitTypeEnum.TIME_LIMIT2.getValue();
					}else if(houseNumber<2000){
						timeLimitType = TimeLimitTypeEnum.TIME_LIMIT3.getValue();
					}else{
						timeLimitType = TimeLimitTypeEnum.TIME_LIMIT4.getValue();
					}
					TimeLimit  timeLimit = timeLimitDao.findByType(timeLimitType);
					if(null!=timeLimit){
						pro.setAcquisitionDays(timeLimit.getTlDuration().toString());
					}
				}
			//公建
			}else if(ProjLtypeEnum.PUBLIC.getValue().equals(pro.getProjLtypeId())){
				if(null!=scaleDetails&&scaleDetails.size()>0){
					Integer houseNumber = 0;
					for(ScaleDetail scaleDetail : scaleDetails){
						if(null!=scaleDetail.getHouseNum()){
							houseNumber+=scaleDetail.getHouseNum();
						}
						if(null!=scaleDetail.getSearNum()){
							houseNumber+=scaleDetail.getSearNum();
						}
						if(null!=scaleDetail.getNum()){
							houseNumber+=scaleDetail.getNum();
						}
					}
					String timeLimitType = null;
					//查出细类
					ProjectScale projectScale = projectScaleDao.get(scaleDetails.get(0).getProjStypeId());
					
					String s=projectScale.getScaleType();
					//细类是大锅灶
					if(ProjectScaleTypeEnum.CAULDRON.getValue().equals(projectScale.getScaleType())){
						if(houseNumber<=10){
							timeLimitType = TimeLimitTypeEnum.TIME_LIMIT5.getValue();
						}else{
							timeLimitType = TimeLimitTypeEnum.TIME_LIMIT6.getValue();
						}
					//细类是工业燃气炉/锅炉
					}else if(ProjectScaleTypeEnum.FURNACE.getValue().equals(projectScale.getScaleType())){
						if(houseNumber<=4){
							timeLimitType = TimeLimitTypeEnum.TIME_LIMIT7.getValue();
						}else{
							timeLimitType = TimeLimitTypeEnum.TIME_LIMIT8.getValue();
						}
					}
					TimeLimit  timeLimit = timeLimitDao.findByType(timeLimitType);
					if(null!=timeLimit){
						pro.setAcquisitionDays(timeLimit.getTlDuration().toString());
					}
				}
			//改管或干线
			}else if(ProjLtypeEnum.PIPE.getValue().equals(pro.getProjLtypeId())||ProjLtypeEnum.TRUNK.getValue().equals(pro.getProjLtypeId())){
				//求长度的和
				if(null!=scaleDetails&&scaleDetails.size()>0){
					BigDecimal pipiLength = BigDecimal.ZERO;
					for(ScaleDetail scaleDetail : scaleDetails){
						if(scaleDetail.getPipeLength()!=null){
							pipiLength = pipiLength.add(scaleDetail.getPipeLength());
						}
					}
					String timeLimitType = null;
					if(pipiLength.compareTo(new BigDecimal(1.5))<0){
						timeLimitType = TimeLimitTypeEnum.TIME_LIMIT9.getValue();
					}else if(pipiLength.compareTo(new BigDecimal(4))<0){
						timeLimitType = TimeLimitTypeEnum.TIME_LIMIT10.getValue();
					}else if(pipiLength.compareTo(new BigDecimal(8))<0){
						timeLimitType = TimeLimitTypeEnum.TIME_LIMIT11.getValue();
					}else{
						timeLimitType = TimeLimitTypeEnum.TIME_LIMIT12.getValue();
					}
					TimeLimit  timeLimit = timeLimitDao.findByType(timeLimitType);
					if(null!=timeLimit){
						pro.setAcquisitionDays(timeLimit.getTlDuration().toString());
					}
				}
			}
		}
		Department department = departmentService.queryDepartment(pro.getDeptId());
		if (department!=null) {
			pro.setDeptDivide(department.getDeptDivide());
		}
		return pro;
	}

	/**
	 * 图纸审核页面工程列表加载
	 * 
	 * @author fuliwei
	 * @createTime 2016-7-6
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryAuditProject(ProjectQueryReq projectQueryReq, String grade, String stepId)
			throws ParseException {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		projectQueryReq.setStepId(stepId);
		Map<String, Object> result = this.queryProjectByTime(projectQueryReq);
		List<Project> data = (List<Project>) result.get("data");
		List<ConstructionPlan> cp = constructionPlanDao.getAll();
		for(int i=0 ; i<cp.size(); i++){
			for(int j=0; j<data.size(); j++){
				if(cp.get(i).getProjId().equals(data.get(j).getProjId())){
					//data.get(j).setManagementOffice(cp.get(i).getManagementOffice());
					data.get(j).setCuName(cp.get(i).getCuName());
				}
			}
		}
		if (data != null && data.size() > 0) {
			/**
			 * -1 未审核 0 审核未通过 1 审核通过 2待审核 若该
			 * 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0",
			 * "level3":"2"};
			 */
			for (int i = 0; i < data.size(); i++) {
				
				
				//查询所有的有效的
				List<ProjectSign> projectSignList=projectSignDao.findByProjIdAndStatus(data.get(i).getProjId(),IsSignEnum.IS_SIGN.getValue());
				
				if(projectSignList!=null && projectSignList.size()>0){
					data.get(i).setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//是特殊工程
				}
				
				DocType docType = docTypeService.findByStepId(stepId,data.get(i).getCorpId(),data.get(i).getProjectType(),data.get(i).getContributionMode());
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					grade=docType.getGrade();
				}else{
					grade="0";
				}
				data.get(i).setLevel(grade);// 设置审核总级数（设计信息2级审核）
				
				Map<String, String> levelBtn = new HashMap<String, String>();

				for (int n = 1; n < Integer.parseInt(grade) + 1; n++) {
					if (n == 1) {
						levelBtn.put("level" + n, "2");// 待审
					} else {
						levelBtn.put("level" + n, "-1");// 未审
					}
				}
				List<ManageRecord> mrls = managerecorddao.findByStepIdProjIdIsPass(data.get(i).getProjId(), stepId,
						MrResultEnum.PASSED.getValue());
				if (mrls != null && mrls.size() > 0) {
					// 遍历循环，获取审核是否通过
					for (ManageRecord mr : mrls) {
						levelBtn.put("level" + mr.getMrAuditLevel(), mr.getMrResult());
					}
					if (mrls.size() < Integer.parseInt(grade)) {
						levelBtn.put("level" + (mrls.size() + 1), "2");
					}
					//哪个公司的工程某个人不能审核某个级别
					List<DataFilerSetUpDto> datafiltes = Constants.getDataFilterMapByKey(data.get(i).getCorpId()+"_"+loginInfo.getStaffId()+"_"+(mrls.size() + 1)+"_"+projectQueryReq.getMenuId());
					if(datafiltes!=null && datafiltes.size()>0){
						levelBtn.put("level" + (mrls.size() + 1), "-1");
					}else{
						levelBtn.put("level" + (mrls.size() + 1), "2");
					}
				}else{
					//施工预算初审，幕投类项目现场代表不能一级初审
					if(StepEnum.QUALITIES_JUDGEMENT_FIRST.getValue().equals(stepId) && ContributionMode.INVESTMENT_CODE_TYPE.equals(data.get(i).getContributionMode()) && loginInfo.getPost().contains(PostTypeEnum.BUILDER.getValue())){
						levelBtn.put("level1", "-1");
					}
					//施工预算初审，非幕投类项目监理不能一级初审
					if(StepEnum.QUALITIES_JUDGEMENT_FIRST.getValue().equals(stepId) && !ContributionMode.INVESTMENT_CODE_TYPE.equals(data.get(i).getContributionMode()) && loginInfo.getPost().contains(PostTypeEnum.SUJGJ.getValue())){
						levelBtn.put("level1", "-1");
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			//如果是在预算审核环节
			if(ProjStatusEnum.TO_AUDIT_AMOUNT.getValue().equals(projectQueryReq.getProjStatusId())){
				//循环处理完的数据
				Iterator<Project> it = data.iterator();
				while(it.hasNext()){
				    Project project = it.next();
				    JSONObject jb = JSONObject.fromObject(project.getMrAuditLevel());
				    Map<String, String> map = (Map<String, String>)jb;
				    //如果记录是一级待审，并且预结算员不是自己，就删了该记录
				    if(map.get("level1").equals("2")&&!project.getBudgeterAuditId().equals(loginInfo.getStaffId())){
				        it.remove();
				    }
				}
			}
			//如果是在预算初审环节
			if(ProjStatusEnum.TO_AUDIT_AMOUNT_FIRST.getValue().equals(projectQueryReq.getProjStatusId())){
				//循环处理完的数据
				Iterator<Project> it = data.iterator();
				while(it.hasNext()){
				    Project project = it.next();
				    JSONObject jb = JSONObject.fromObject(project.getMrAuditLevel());
				    Map<String, String> map = (Map<String, String>)jb;
				    //如果记录是一级待审，并且现场代表不是自己，就删了该记录

					boolean isEq = project.getBuilderId()!=null?!project.getBuilderId().equals(loginInfo.getStaffId()):false;

					if("2".equals(map.get("level1")) && isEq &&loginInfo.getPost().contains(PostTypeEnum.BUILDER.getValue())){
				        it.remove();
				    }
				}
			}
			result.put("data", data);
		}
		return result;
	}

	/**
	 * 录入工程造价信息
	 * @throws ParseException 
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
	public void updateProjectCost(ProjectCostReq projectCostReq) throws Exception{
		Project pro = projectDao.get(projectCostReq.getProjId()); // 根据Id查project
		// 更新project
		pro.setConfirmTotalCost(projectCostReq.getConfirmTotalCost());	//确定造价
		pro.setChangeReason(projectCostReq.getChangeReason());         //变动原因
		pro.setCostRemark(projectCostReq.getCostRemark());             //造价备注
		pro.setAffirmCostDate(projectDao.getDatabaseDate());	       //确定日期
		String statusId = WorkFlowUtil.workFlowStatus(WorkFlowActionEnum.PROJECT_COST.getActionCode());
		String auditLevel="";
		// 若存在退单原因，则认为该操作为退单操作
		if (StringUtils.isNotBlank(projectCostReq.getBackReason())) {
			statusId = WorkFlowUtil.workFlowStatus(""); 		// 工程状态--终止
			pro.setBackReason(projectCostReq.getBackReason()); 	// 退单原因
			pro.setBackRemarks(projectCostReq.getBackRemarks());//退单备注
			pro.setBackDate(projectDao.getDatabaseDate()); 		// 退单日期
			pro.setFinishedDate(projectDao.getDatabaseDate()); 	// 结束日期
			//清空代办人
			pro.setToDoer("");
			//待办事项置为无效
			operateRecordDao.cancelTodo(pro.getProjId());
			//退单的如果是借料工程调用接口删除工程信息，todo:
			if (MaterialFlagEnum.YES.getValue().equals(pro.getMaterialFlag()) && this.isToCall(pro.getProjId(),WebServiceTypeEnum.PROJ_ACCEPT_DELETE.getValue())) {
				ResultMessage resultMessage = new ResultMessage();
				//调用nc系统工程信息同步接口
				String res = financeInfoService.synProjectInfoClient(pro.getProjId(), FinanceOperateTypeEnum.PROJ_ACCEPT_DELETE.getValue(), UpdateTypeEnum.INSERT.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
				JSONObject jsonbean = JSONObject.fromObject(res);
				//返回信息-当接口返回失败时，抛出异常
				resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
				if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && this.isSynchronization(pro.getProjId(),WebServiceTypeEnum.PROJ_ACCEPT_DELETE.getValue())){
					//回滚事物
					throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
				}
			}
		}else{//未退单，则判断审核范围
			//存在审核范围,说明工程总造价与确认造价不一致，需要审核
			
//			if(StringUtils.isNotBlank(projectCostReq.getProjCostAuditType())){
				//获取标准审核级别(二级审核)
				//DocType docType = docTypeDao.findByStepId(StepEnum.PROJECT_COST_AUDIT.getValue());
				DocType docType = docTypeService.findByStepId(StepEnum.PROJECT_COST_AUDIT.getValue(),pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
				
//				if(docType!=null){
//					auditLevel = docType.getGrade().equals(ProjCostAuditTypeEnum.valueof(projectCostReq.getProjCostAuditType()).getAuditLevel())?docType.getGrade():ProjCostAuditTypeEnum.valueof(projectCostReq.getProjCostAuditType()).getAuditLevel();
//				}
				//获取审核级别
				//auditLevel=ProjCostAuditTypeEnum.valueof(projectCostReq.getProjCostAuditType()).getAuditLevel();
				if(docType!=null){
					auditLevel = docType.getGrade();
				}
				//如果分公司配置了工程造价菜单的配置，则根据相应的审核配置
				if(StringUtil.isNotBlank(projectCostReq.getProjCostConfig())){
					auditLevel="";
				}
				//获取工程造价的下一工程状态
				statusId = workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode() , WorkFlowActionEnum.PROJECT_COST.getActionCode(), true);
				 /*//生成审核通知
				 Notice notice=noticeDao.findByProjIdAndType(pro.getProjId(), NoticeAuditTypeEnum.COST_AUDIT.getValue());//造价审核
				 if(notice!=null){
					 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());//将通知置为有效;
					 notice.setGrade(NoticeMenuEnum.COST_AUDIT1.getGrade());	 //造价一级审核
					 notice.setNoticeContent(NoticeMenuEnum.COST_AUDIT1.getMessage());//待造价审核一级
					 notice.setNoticeTime(noticeDao.getDatabaseDate());
				 }else{
					 notice=new Notice();
					 notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
					 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());		//将通知置为有效;
					 notice.setGrade(NoticeMenuEnum.COST_AUDIT1.getGrade());	 		//一级审核
					 notice.setAuditType(NoticeMenuEnum.COST_AUDIT1.getType());
					 notice.setNoticeTime(noticeDao.getDatabaseDate());
					 notice.setNoticeTitle("审核通知");
					 notice.setNoticeContent(NoticeMenuEnum.SURVEY_AUDIT1.getMessage());//待造价审核一级
					 notice.setNoticeType("2");
					 notice.setProjId(pro.getProjId());
					 notice.setCorpId(pro.getCorpId());
				 }
				 
				 noticeDao.saveOrUpdate(notice);*/
			
			
			
//			}else{//不需要工程造价审核
//				//获取工程造价审核的下一个工程状态
//				statusId = workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode() , WorkFlowActionEnum.PROJECT_COST_AUDIT.getActionCode(), true);
//			}
			//更新工程造价
			projectCostService.findAndSaveOrUpdate(auditLevel,projectCostReq);
		}
		pro.setProjStatusId(statusId); // 改变project状态
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(pro.getCostMember())){
			pro.setCostMember(loginInfo != null ? loginInfo.getStaffName() : ""); // 造价员
		}
		if(StringUtil.isBlank(pro.getCostMemberId())){
			pro.setCostMemberId(loginInfo != null ? loginInfo.getStaffId() : ""); // 造价员ID
		}
		
		
		//更新规模明细 -- 去掉工程规模
		/*List<ScaleDetail> scaleDetails = projectCostReq.getScaleDetails();
		String totalDes = new String();
		
		if (scaleDetails != null && scaleDetails.size() > 0) {
			String[] LtypeId = projectCostReq.getProjLtypeId().split(",");
			List<ProjectType> list=projectTypeDao.getAll();
			for (int i = 0; i < LtypeId.length; i++) {
				StringBuffer scaleDes = new StringBuffer(); // 总体规模描述
				String ltype = LtypeId[i];				
				String ltypeDes=this.getTypeDes(ltype,list);
				//String ltypeDes = ProjLtypeEnum.valueof(ltype).getMessage();
				scaleDes.append(ltypeDes).append("——");
				// for(ScaleDetail sdetail:scaleDetails){
				for (int k = 0; k < scaleDetails.size(); k++) {
					ScaleDetail sdetail = scaleDetails.get(k);
					sdetail.setScaleId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
					sdetail.setProjId(pro.getProjId());
					if (sdetail.getProjLtypeId().equals(ltype)) {
						scaleDes.append(sdetail.getProjStypeDes());
						if (sdetail.getHouseNum() != null && sdetail.getHouseNum() > 0) {
							scaleDes.append(sdetail.getHouseNum()).append("户,");
						}
						if (sdetail.getSearNum() != null && sdetail.getSearNum() > 0) {
							scaleDes.append(sdetail.getSearNum()).append("座 ,");
						}
						if (sdetail.getNum() != null && sdetail.getNum() > 0) {
							scaleDes.append(sdetail.getNum()).append("台,");
						}
						if (sdetail.getTonnage() != null && sdetail.getTonnage() > 0) {
							scaleDes.append(sdetail.getTonnage()).append("吨,");
						}
						if (sdetail.getGasConsumption() != null && sdetail.getGasConsumption() > 0) {
							scaleDes.append("预计用气量 ").append(sdetail.getGasConsumption()).append(" m³,");
						}
					}
				}
				totalDes = totalDes + ((scaleDes.toString()).substring(0, scaleDes.toString().length() - 1)) + ";";
			}
		}
		pro.setProjScaleDes(totalDes); // 总体规模描述
		
		Map<String, Object> mapSD = new HashMap<String, Object>();
		mapSD.put("data", scaleDetails);
		mapSD.put("projId", pro.getProjId());
		
		pro.setProjLtypeId(projectCostReq.getProjLtypeId());
		*/
		
		// 调用工程规模明细处理方法
		//scaleDetailService.batMaintain(mapSD);

		if(StringUtils.isNotBlank(projectCostReq.getStepId()) && StepEnum.PROJECT_COST.getValue().equals(projectCostReq.getStepId())){
			// 形成操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);
			String todoer=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(),
					StepEnum.PROJECT_COST.getValue(), StepEnum.PROJECT_COST.getMessage(), "");
			pro.setToDoer(todoer);//待办人
			projectDao.update(pro); // 更新工程对象
		}
		//造价审核 不生成操作记录
	}

	@Override
	public ProjectViewDto diaryViewProject(String ProjId) {
		ProjectViewDto pvd = new ProjectViewDto();
		Project proj = projectDao.get(ProjId);
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		if (proj != null) {
			pvd.setProject(proj);
			List<ConstructionPlan> plans = constructionPlanDao.findByProjNo(proj.getProjNo());
			if (plans != null && plans.size() > 0) {
				ConstructionPlan constructionPlan = plans.get(0);
				pvd.setConstructionPlan(constructionPlan);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			pvd.setDlDate(sdf.format(projectDao.getDatabaseDate()));
			pvd.setDlRecorder(loginInfo.getStaffName());
			pvd.setStaffId(loginInfo.getStaffId());
		}
		return pvd;
	}

	@Override
	public String getProjNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String start = "T-" + sdf.format(projectDao.getDatabaseDate());
		return start + projectDao.getProjNo();
	}

	@Override
	public String getProjMaxNo(String deptId,String projType,String contributionModelId) {
		Department department=departmentService.queryDepartment(deptId);
		
		Correlation correlation = correlationService.findCode(CorrelationTypeEnum.PROJTYPE_CONTRIBUTION.getValue(),projType,contributionModelId,deptId);
		if(correlation==null ||StringUtil.isBlank(correlation.getContributionCode())){
			//默认取1101
			correlation = correlationService.findCode(CorrelationTypeEnum.PROJTYPE_CONTRIBUTION.getValue(),projType,contributionModelId,Constants.START_REPORT_CPT_CORP_MODE);
		}
		String projMaxNo;
		String projNo;
		try {
			projMaxNo = projectDao.generateMaxProjNo(deptId);//1707001
			StringBuffer pro = new StringBuffer("");
			String code="";
			if(correlation!=null){
				code=correlation.getContributionCode();
			}
			if(code.equals("")){
				return "noneNumber";
			}
			projNo = pro.append(department.getDeptInnerCode()).append(code).append(projMaxNo).append("0").toString();
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
		return projNo;
	}

	@Override
	public Project queryProjectById(String ProjId) {
		// TODO Auto-generated method stub
		Project project = projectDao.get(StringUtil.isNotBlank(ProjId)?ProjId:"");
		if(project!=null){
			//遵义，获取报建手续上一步的推送日期
			String statusId = workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode() , WorkFlowActionEnum.CONSTRUCTION_PROC.getActionCode(), false);
			if(StringUtil.isNotBlank(statusId) && !WorkFlowActionEnum.CONTRACT_END.getActionCode().equals(statusId)){
				OperateRecordQueryReq operateRecordQueryReq = new OperateRecordQueryReq();
				operateRecordQueryReq.setProjId(project.getProjId());
				operateRecordQueryReq.setStepId(WorkFlowActionEnum.byStatusCode(statusId).getActionCode());
				OperateRecord operateRecord = operateRecordDao.queryEndOperateRecord(operateRecordQueryReq);
				if(operateRecord!=null){
					project.setConstructionProcPreDate(operateRecord.getOperateTime());
				}
			}
		}
		return project;
	}

	@Override
	public Map<String, Object> queryProjectView(ProjectQueryReq projectQueryReq, String[] projStatus)
			throws ParseException {
		if ("projStatusDes".equals(projectQueryReq.getSortName())) {
			projectQueryReq.setSortName("projStatusId");
		}
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		Map<String, Object> result = projectDao.queryProject(projectQueryReq, projStatus);

		if (projectQueryReq.getRedFlag()!=null&&projectQueryReq.getRedFlag()){//查询标红工程
			return redProjectMap(result,projectQueryReq);
		}

		List<Project> projectList = (List<Project>) result.get("data");
		
		/*Map<String, Object> map=projectDao.queryProjectAcceptance(projectQueryReq, projStatus);
		
		List<Project> list=(List<Project>) map.get("data");*/
		
		
		if (projectList != null && projectList.size() > 0) {
			for (int i = 0; i < projectList.size();i++) {
				
				List<ProjectSign> projectSignList=projectSignDao.findByProjIdAndStatus(projectList.get(i).getProjId(),IsSignEnum.IS_SIGN.getValue());
				if(projectSignList!=null && projectSignList.size()>0){
					projectList.get(i).setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//是特殊工程
				}
				
				//施工派遣的人员信息已存入工程表，此处代码去掉
				/*List<ConstructionPlan> cps = constructionPlanDao.findByProjNo(projectList.get(i).getProjNo());
				if (cps != null && cps.size() > 0) {
					if (StringUtils.isNotBlank(cps.get(0).getSuName())) {
						projectList.get(i).setSuName(cps.get(0).getSuName()); // 监理公司
					}
					if(StringUtils.isNotBlank(cps.get(0).getBuilder())){
						projectList.get(i).setBudiler(cps.get(0).getBuilder()); // 现场代表
					}
					if(StringUtils.isNotBlank(cps.get(0).getCuName())){
						projectList.get(i).setCuName(cps.get(0).getCuName());	//分包单位
					}
					if(StringUtils.isNotBlank(cps.get(0).getCuName())){
						projectList.get(i).setCuName(cps.get(0).getCuName());
					}
					if(StringUtils.isNotBlank(cps.get(0).getCuPm())){
						projectList.get(i).setCuPm(cps.get(0).getCuPm());				//项目经理
					}
					if(StringUtils.isNotBlank(cps.get(0).getManagementQae())){
						projectList.get(i).setManagementQae(cps.get(0).getManagementQae());	//施工员
					}
					if(StringUtils.isNotBlank(cps.get(0).getSaftyOfficer())){
						projectList.get(i).setSaftyOfficer(cps.get(0).getSaftyOfficer());	//安全员
					}
					if(StringUtils.isNotBlank(cps.get(0).getTechnician())){
						projectList.get(i).setTechnician(cps.get(0).getTechnician());		//质检员
					}
					if(StringUtils.isNotBlank(cps.get(0).getManagementWacf())){
						projectList.get(i).setManagementWacf(cps.get(0).getManagementWacf());//材料员
					}
					if(StringUtils.isNotBlank(cps.get(0).getDocumenter())){
						projectList.get(i).setDocumenter(cps.get(0).getDocumenter());    //资料员
					}
					if(StringUtils.isNotBlank(cps.get(0).getTestLeader())){
						projectList.get(i).setTestLeader(cps.get(0).getTestLeader());    //班组长
					}
					if(StringUtils.isNotBlank(cps.get(0).getWelder())){
						projectList.get(i).setWelder(cps.get(0).getWelder());    //焊工
					}
					if(StringUtils.isNotBlank(cps.get(0).getSuCse())){
						projectList.get(i).setSuCse(cps.get(0).getSuCse());					//总监
					}
					if(StringUtils.isNotBlank(cps.get(0).getSuJgj())){
						projectList.get(i).setSuJgj(cps.get(0).getSuJgj());					//现场监理
					}
				}*/
				
				if(StringUtils.isNotBlank(projectList.get(i).getProjectType())){
					ProjectType projectType = projectTypeDao.get(projectList.get(i).getProjectType());
					if(projectType!=null){
						projectList.get(i).setContractType(projectType.getContractType());
					}
				}
				if(StringUtils.isNotBlank(projectList.get(i).getProjId())){
					//查找设计信息
					DesignInfo designInfo=designInfoDao.queryInfoByProjId(projectList.get(i).getProjId());
					if(designInfo!=null){
						projectList.get(i).setDesignDrawingNo(designInfo.getDesignDrawingNo());
					}
				}
				
				if(StringUtils.isNotBlank(projectList.get(i).getProjId())&&StringUtil.isNotBlank(projectList.get(i).getDeptId())){
					Department dep = departmentService.queryDepartment(projectList.get(i).getDeptId());
					if(null!=dep&&StringUtil.isNoneBlank(dep.getDeptDivide())){
						projectList.get(i).setDeptDivide(dep.getDeptDivide());
					}
				}
				
				projectList.get(i).setPost(loginInfo.getPost());
				//获取工程的进场状态
				projectList.set(i,constructionOrganizationService.isAllowWorkStart(projectList.get(i)));
			}
			result.put("data", projectList);
		}
		return result;
	}

	public Map<String,Object> redProjectMap(Map<String, Object> map, ProjectQueryReq req){
		List<Project> projectList =(List<Project>) map.get("data");
		List<ProjectSign> projectSignList = projectSignDao.findListByStatus(IsSignEnum.IS_SIGN.getValue());
		List<Project> dataList=new ArrayList<>();

		for (ProjectSign ps:projectSignList) {
			for (Project p:projectList) {
				if (p.getProjId().equals(ps.getProjId())){//特殊工程
					p.setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());
					dataList.add(p);
				}
			}
		}
		List newList = new ArrayList(new HashSet(dataList));//去重
		return ResultUtil.pageResult(newList.size(), 0, ListPagingUtil.listPaging(newList,req.getStart(),req.getLength()));

	}

	@Override
	public Map<String, Object> querySummaryProject(ProjectQueryReq projectQueryReq) throws ParseException {
		int starts = (Integer.parseInt(projectQueryReq.getCurrentPage()) - 1)
				* Integer.parseInt(projectQueryReq.getSize());
		projectQueryReq.setStart(starts);
		projectQueryReq.setLength(Integer.parseInt(projectQueryReq.getSize()));
		return projectDao.queryProject(projectQueryReq);
	}

	@Override
	public Map<String, Object> queryDistributionProject(ProjectQueryReq projectQueryReq) throws ParseException {
		return projectDao.queryDistributionProject(projectQueryReq);
	}

	@Override
	public Map<String, Object> everyAreaProjectNum() {

		// 返回结果集
		Map<String, Object> result = new HashMap();
		// 步骤名称数组
		String[] legendData = StepLtypeEnum.getMessages();
		// 区域名称数组
		String[] yAxisData = AreaEnum.getMessages();
		// legendData
		result.put("legendData", legendData);
		// yaxisData
		result.put("yAxisData", yAxisData);
		List<Map<String, Object>> queryData = projectDao.everyAreaProjectNum();
		List<EchartsDto> echartsDtos = new ArrayList();
		Map<String, int[]> initTotalData = new HashMap();
		
		//c33430  2e4454  60a1a9  d58364  93c7af  749f83  cb8723  bda39b  6e7074
		String[]colorItem = {"#c33430","#749f83","#2e4454","#60a1a9","#d58364","#93c7af","#cb8723","#bda39b","#6e7074"};

		
		// 各区各环节工程数量初始值为0 
		for (int i = 0; i < StepLtypeEnum.values().length; i++) {
			Map m = new HashMap();
			Map m2 = new HashMap();// 区域工程数量初始值为0
			int[] initData = new int[AreaEnum.values().length];
			for (int j = 0; j < initData.length; j++) {
				initData[j] = 0;
			}
			initTotalData.put(StepLtypeEnum.values()[i].getValue(), initData);
			EchartsDto edo = new EchartsDto();
			edo.setName(StepLtypeEnum.values()[i].getValue());
			edo.setStack("总量");
			edo.setType("bar");
			//m.put("color", StepLtypeEnum.values()[i].getColor());
			m.put("show","false");
			m.put("position","left");
			m2.put("normal", m);
			edo.setLabel(m2);
			edo.setBarGap("10%");
			edo.setBarCategoryGap("50%");
			
			
			if(i < colorItem.length)
			{
				m = new HashMap();
				Map m3 = new HashMap();
				m.put("color",colorItem[i]);
				m3.put("normal", m);
				
				edo.setItemStyle(m3);
			}
			
			echartsDtos.add(edo);
		}
		// 遍历循环 各区赋值
		for (int m = 0; m < queryData.size(); m++) {
			String area = (String) queryData.get(m).get("AREA");
			String stepId = (String) queryData.get(m).get("STEPID");
			int num = Integer.parseInt((String) ("" + queryData.get(m).get("TOTALNUM")));
			if(StringUtil.isNotBlank(area)){
			int area_index = Integer.parseInt(area.substring(area.length() - 1)) - 1;
			initTotalData.get(stepId)[area_index] = num;
			}
		}
		// 遍历循环 步骤id翻译为描述
		for (int n = 0; n < echartsDtos.size(); n++) {
			EchartsDto edo = echartsDtos.get(n);
			String stepId = edo.getName();
			edo.setData(initTotalData.get(stepId));
			edo.setName(StepLtypeEnum.valueof(stepId).getMessage());
		}
		result.put("series", echartsDtos);
		return result;
	}

	@Override
	public Map<String, Object> queryProjectcostSummary(ProjectQueryReq projectQueryReq) throws ParseException {

		return projectDao.queryProjectcostSummary(projectQueryReq);
	}

	@Override
	public Map<String, Object> backReasonProjectNum() {
		// 返回结果集
		Map<String, Object> result = new HashMap();
		// 数据查询
		List<Map<String, Object>> queryData = projectDao.backReasonProjectNum();
		// 退单原因名称数组
		String[] legendData = BackReasonEnum.getMessages();
		// legendData
		result.put("legendData", legendData);
		List<Map<String, Object>> data = new ArrayList();
		for (int m = 0; m < queryData.size(); m++) {
			Map<String, Object> mdata = new HashMap();
			String message=(String) queryData.get(m).get("NAMEID");
			String name = BackReasonEnum.valueof(message).getMessage();
			mdata.put("value", queryData.get(m).get("VALUE"));
			mdata.put("name", name);
			data.add(mdata);
		}
		result.put("seriesData", data);
		return result;
		/*
		 * if(queryData == null || legendData.length != queryData.size()){
		 * List<Map<String,Object>> initData = new ArrayList(); for(int
		 * i=0;i<legendData.length;i++){ Map m = new HashMap(); m.put("name",
		 * legendData[i]); m.put("value", 0); initData.add(m); } if(queryData !=
		 * null){ for(Map<String,Object> qdata:queryData){ for(int
		 * m=0;m<initData.size();m++){ Map<String,Object> mdata =
		 * initData.get(m); String name =
		 * BackReasonEnum.valueof((String)qdata.get("NAMEID")).getMessage();
		 * if(mdata.get("name").equals(name)){ mdata.put("value",
		 * qdata.get("VALUE")); } } } } result.put("seriesData", initData);
		 * return result; }else{ List<Map<String,Object>> data = new
		 * ArrayList(); for(int m=0;m<queryData.size();m++){ Map<String,Object>
		 * mdata = new HashMap(); String name =
		 * BackReasonEnum.valueOf((String)queryData.get(m).get("NAMEID")).
		 * getMessage(); mdata.put("value", (int)queryData.get(m).get("VALUE"));
		 * mdata.put("name",name); data.add(mdata); } result.put("seriesData",
		 * data); return result; }
		 */
	}

	@Override
	public Map<String, Object> paymentNum() {
		// 返回结果集
		Map<String, Object> result = new HashMap();
		// 数据查询
		List<Map<String, Object>> queryData = projectDao.paymentNum();
		Format f = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(projectDao.getDatabaseDate());
		c.add(Calendar.YEAR, -1);
		String[] months = new String[12];
		for (int i = 0; i < months.length; i++) {
			c.add(Calendar.MONTH, 1);
			String date = f.format(c.getTime());
			months[i] = date;
		}
		// xAxisData
		result.put("xAxisData", months);
		// 实收
		Object[] collectData = { 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00 };
		// 实付
		Object[] payData = { 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00 };
		for (int n = 0; n < months.length; n++) {
			for (Map<String, Object> qdata : queryData) {
				if (qdata.get("ARDATE").equals(months[n])) {
					// 实付
					if ((qdata.get("ARFLAG") + "").equals(ARFlagEnum.ACCOUNTS_PAY.getValue())) {
						payData[n] = qdata.get("COST");
					} else {
						// 实收
						collectData[n] = qdata.get("COST");
					}
				}
			}
		}
		result.put("collectData", collectData);
		result.put("payData", payData);
		return result;
	}

	@Override
	public Map<String, Object> projectScheduleStatistics() {
		List<Map<String, Object>> queryData = projectDao.projectScheduleStatistics();
		Map initdata = new HashMap();
		List<EchartsDto> echartsDtos = new ArrayList();
		// 用于页面获取数据用A替换0%-30% B替换30%-50% 依此类推
		String[] xdata = { "A", "B", "C", "D", "E", "F" };
		String[] ydata = { "施工管理一处", "施工管理二处", "施工管理三处", "施工管理四处", "施工管理五处" };
		for (String d : xdata) {
			Object[] sdata = { 0.0, 0.0, 0.0, 0.0, 0.0 };
			initdata.put(d, sdata);
		}
		if (queryData != null && queryData.size() > 0) {
			for (Map m : queryData) {
				for (int j = 0; j < ydata.length; j++) {
					if (m.get("MANAGEOFFICE").equals(ydata[j])) {
						if (initdata.get("" + m.get("PROGRESS")) != null) {
							((Object[]) initdata.get("" + m.get("PROGRESS")))[j] = m.get("PROGRESSVALUE");
						}
					}
				}
			}
		}
		return initdata;
	}

	@Override
	public Map<String, Object> queryProjectByParam(ProjectQueryReq projectQueryReq, List<String> projStuList)throws ParseException {
		if ("projStatusDes".equals(projectQueryReq.getSortName())) {
			projectQueryReq.setSortName("projStatusId");
		}
		return projectDao.queryProjectByStatus(projectQueryReq,projStuList);
	}

	@Override
	public List<Project> queryProjectSign(ProjectQueryReq projectQueryReq) throws ParseException {
		List<Project> projects = (List<Project>) this.queryDistributionProject(projectQueryReq).get("data");
		SignatureQueryDto sqd = new SignatureQueryDto();
		sqd.setMenuId(projectQueryReq.getSideBarID());//设置查询条件 签字环节
		projectQueryReq.setSideBarID("");
		if(projects!=null && projects.size()>0){
			for(int i=0;i<projects.size();i++){
				sqd.setProjId(projects.get(i).getProjId());
				List<Signature> signs = signatureDao.findByProperties(sqd);
				projects.get(i).setSigns(signs);
			}
			return projects; 
		}
		return new ArrayList<Project>();
	}

	@Override
	public Map<String, Object> queryProjectAcceptance(ProjectQueryReq projectQueryReq, String[] projStatus)
			throws ParseException {
		
		
		Map<String, Object> map=projectDao.queryProjectAcceptance(projectQueryReq, projStatus);
		
		List<Project> list=(List<Project>) map.get("data");
		
		if(list!=null && list.size()>0){
			for(Project pro:list){
				List<ProjectSign> projectSignList=projectSignDao.findByProjIdAndStatus(pro.getProjId(),IsSignEnum.IS_SIGN.getValue());
				if(projectSignList!=null && projectSignList.size()>0){
					pro.setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//是特殊工程
				}
			}
		}
		
		return map;
	}

	@Override
	public Map<String, Object> queryAcceptProject(ProjectQueryReq projectQueryReq) throws ParseException {
		List<String> projStuList = new ArrayList<String>();
		/*if(projectQueryReq.getProjStatus()==null||projectQueryReq.getProjStatus().equals(ProjectStatusEnum.TO_SURVEY.getValue())){
			projStuList.add(ProjStatusEnum.TO_SURVEY.getValue());
		}else if(projectQueryReq.getProjStatus().equals(ProjectStatusEnum.BACK_NO_INFORM.getValue())){
			projectQueryReq.setBackInform(false);
			projStuList.add(ProjStatusEnum.TERMINATION.getValue());
		}else{
			List<ProjStatusEnum> enums = ProjStatusEnum.getThanValue(ProjStatusEnum.TO_SIGN_CONTRACT.getValue(),ProjStatusEnum.ALREADY_COMPLETED.getValue());
			for(ProjStatusEnum projStatusEnum :enums){
				projStuList.add(projStatusEnum.getValue());
			}
		}*/
		projectQueryReq.setProjStuList(projStuList);//工程状态
		return projectDao.queryAcceptProject(projectQueryReq);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String informSave(String projId) {
		Project project = projectDao.get(projId);
		project.setBackInform(true);
		projectDao.saveOrUpdate(project);
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	public Map<String, Object> queryProjectPicture(ProjectQueryReq projectQueryReq) throws ParseException {
		int starts = (Integer.parseInt(projectQueryReq.getCurrentPage()) - 1)
				* Integer.parseInt(StringUtils.isNotBlank(projectQueryReq.getSize())?projectQueryReq.getSize():"0");
		projectQueryReq.setStart(starts);
		projectQueryReq.setLength(Integer.parseInt(projectQueryReq.getSize()));
		Map<String, Object> map=projectDao.queryProject(projectQueryReq);
		if(map!=null&&map.get("data")!=null){
			List<Project> list=(List<Project>) map.get("data");	
			//查询相应工程的附件
			List<String> projIds = new ArrayList<String>();
			for(Project pro:list){
				projIds.add(pro.getProjId());
			}
			List<AccessoryList> accList=accessoryDao.getAll(projIds,"-1");
			if(list==null){
				list=new ArrayList<Project>();
			}
			if(accList==null){
				accList=new ArrayList<AccessoryList>();
			}
			for(Project pro:list){
				for(AccessoryList al:accList){
					if(pro.getProjId().equals(al.getProjId())&&al.getStep()!=null&&"-1".equals(al.getStep())){
						pro.setPictureUrl(al.getAlPath());
						continue;
					}
				}
			}
			
		}
		return map;
	}
private String getTypeDes(String id,List<ProjectType> list){
	for(ProjectType pt:list){
		if(id.equals(pt.getProjTypeId())){
			return pt.getProjTypeDes();
		}
	}
	return "";
}
	
	/**
	 * 要更正的工程列表
	 * @param projectQueryReq
	 * @return
	 */
	@Override
	public Map<String, Object> queryModifyProject(ProjectQueryReq projectQueryReq) throws ParseException {
		return projectDao.queryProject(projectQueryReq);
	}
	
	/**
	 * 工程列表条件查询-报表系统
	 * @param 
	 * @author 
	 * @createTime 2017-01-4
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> queryProjStatis(ProjectQueryReq projectQueryReq, String[] projStatus)
			throws ParseException {
		if ("projStatusDes".equals(projectQueryReq.getSortName())) {
			projectQueryReq.setSortName("projStatusId");
		}
		Map<String, Object> result = projectDao.queryProjStatis(projectQueryReq, projStatus);
		List<Project> projectList = (List<Project>) result.get("data");
		if (projectList != null && projectList.size() > 0) {
			for (int i = 0; i < projectList.size(); i++) {
				List<ConstructionPlan> cps = constructionPlanDao.findByProjNo(projectList.get(i).getProjNo());
				if (cps != null && cps.size() > 0) {
					if (StringUtils.isNotBlank(cps.get(0).getSuName())) {
						projectList.get(i).setSuName(cps.get(0).getSuName()); // 监理公司
					}
//					if (StringUtils.isNotBlank(cps.get(0).getManagementOffice())) {
//						projectList.get(i).setManagementOffice(cps.get(0).getManagementOffice()); // 施工管理处
//					}
					if(StringUtils.isNotBlank(cps.get(0).getCuName())){
						projectList.get(i).setCuName(cps.get(0).getCuName());
					}
				}
			}
			result.put("data", projectList);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateFeedInfo(String projId, String procureFeedback) {
		Project pro = projectDao.get(projId);
		//pro.setProcureFeedback(procureFeedback);
		//pro.setFeedbackState(FeedbackStateEnum.FEEDBACKED.getValue());
		//pro.setProcureFeedbackDate(projectDao.getDatabaseDate());
		projectDao.update(pro);
	}

	@Override
	public Map<String, Object> queryProjectByNetTime(ProjectQueryReq projectQueryReq) throws ParseException {
		// 最后一个操作记录的工程记录列表（符合状态的当前工程）
		List<Map<String, Object>> ors;
		//SystemSet systemSet=systemSetDao.get("stepId", projectQueryReq.getStepId());
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		SystemSet systemSet=systemSetDao.querySystemSetByStepId(projectQueryReq.getStepId(),loginInfo.getCorpId());
		if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
		projectQueryReq.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		}
		if ("projStatusDes".equals(projectQueryReq.getSortName())) {
			projectQueryReq.setSortName("projStatusId");
		}
		Map<String, Object> map = projectDao.queryProject(projectQueryReq);
		// 符合当前状态的工程列表
		List<Project> list = (List<Project>) map.get("data");
		// 当前步骤
		ors = operateRecordDao.getOptRecordByTime(projectQueryReq.getProjStatusId());

		// 时间限制（单位天）
		Integer timel = projectQueryReq.getTimeLimit();
		long secondsLimit=-1l;
		if(timel!=null){
			secondsLimit=timel*24*60*60;
		}
		for (Project pro : list) {
			for (Map<String, Object> or : ors) {
				if (or.get("PROJ_ID").equals(pro.getProjId())) {
					// 业务操作记录中时间
					Date oldTime = (Date) or.get("OPERATE_TIME");
					//oldTime = FestivalUtil.getChangeDate(oldTime);//业务操作时间
					// 当前时间
					Date nowTime = projectDao.getDatabaseDate();
					//获取两个日期之间的工作日天数
					long workDays = 0;
					try {
						workDays = FestivalUtil.calLeaveDays(FestivalUtil.getChangeDate(oldTime), nowTime, festivalService.getCacheMap(Constants.WORKDAYS), festivalService.getCacheMap(Constants.HOLIDAYS));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					long seconds = workDays*24*60*60;
					//如果是设计出图环节，那么设计时长为设计信息中取得
					if(StepEnum.DESIGN_DRAWING.getValue().equals(projectQueryReq.getStepId())){
						DesignInfo di=designInfoDao.queryInfoByProjId(pro.getProjId());
						if(di!=null){
							if(StringUtil.isNotBlank(di.getAcquisitionDays())){//委托天数
								BigDecimal acquisitionDays = new BigDecimal(di.getAcquisitionDays());
								int days = acquisitionDays.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
								if(days>0){
									secondsLimit=days*24*60*60;
								 }
							}
						}
					}
					//该步骤延期申请已通过的时长之和
					Integer delyDays =applyDelayDao.getDelyDays(pro.getProjId(),projectQueryReq.getStepId()).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
					if(delyDays>0){
						secondsLimit+=(delyDays*24*60*60);
					}
					// 如果当前时间-上个步骤的操作时间大于时间限制段则为超时
					if (secondsLimit>0&&seconds >= secondsLimit) {
						long differ = seconds - secondsLimit;
						int i = (int) Math.ceil(differ / (24 * 60 * 60));
						pro.setOverDay(i);
						pro.setOverdue(true);
						continue;

					}
				}
			}
		}
		return map;
	}
	
	/**
	 * 工程材料出货完成标记
	 * @author fuliwei
	 * @createTime 2017年2月19日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void signProject(String projId) {
		Project pro=projectDao.get(projId);
		//pro.setGoodsCompleteStatus(GoodsCompleteStatusEnum.ALREADY_FINISHIED.getValue());
		projectDao.update(pro);
	}




	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String acceptPlanTotalSave(Project project,String menuId,boolean flag) throws IOException {
		Project projectResult = project;
		LoginInfo login=SessionUtil.getLoginInfo();
		if (StringUtils.isBlank(project.getProjId())) { // 新增
			// 该工程编号已存在
			if (this.findByProjNo(project.getProjNo()).size() > 0) {
				return "exist";
			}
			// 新增对象 PROJ_SOURCE
			String projId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);// 生成唯一ID
			projectResult.setProjId(projId);
			projectResult.setCorpId(login.getCorpId());
			projectResult.setTenantId(SessionUtil.getTenantId());//暂时存上
			projectResult.setOrgId(login.getOrgId());
			projectResult.setCorpName(login.getCorpName());
			isConfigAddAccepter(projectResult,menuId);//如果有配置则将立项人设置为受理人
			String projNo = this.getProjMaxNo(login.getCorpId(),projectResult.getProjectType(),projectResult.getContributionMode());
			if(projNo.equals("noneNumber")){
				return "noneNumber";
			}
			if("1".equals(project.getArea())){//遵义新蒲新区的工程，工程编号前加“新”
				projNo = Constants.ZUNYI_XINPU_PROJNO_PREX+projNo;
			}
			projectResult.setProjNo(projNo);
			projectResult.setArea(StringUtil.isNotBlank(project.getArea())?project.getArea():"");
			projectResult.setAcceptType(ProjectMethodEnum.PLAN_PROJECT.getValue());
			projectResult.setAcceptDate(projectDao.getDatabaseDate()); // 受理日期
			projectResult.setProjSource(ProjSourceEnum.HALL.getValue()); // 受理来源
			
			// 形成操作记录 待办功能修改代码
			/*String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
			operateRecordService.createOperateRecord(orId, projectResult.getProjId(), projectResult.getProjNo(),
					StepEnum.PLAN_PROJECT_ACCEPT.getValue(), StepEnum.PLAN_PROJECT_ACCEPT.getMessage(), "");*/
			HashMap<String, Object> toDoer =operateRecordService.cerateOperateWorkFlowRecord(projectResult,StepEnum.PROJECT_ACCEPT.getValue(),login,WorkFlowTypeEnum.MAIN_PROGRESS.getValue(),null);
			projectResult.setToDoer(String.valueOf(toDoer.get("NAME")));//待办人
		} else { // 修改
			projectResult = projectDao.get(project.getProjId());
			projectResult.setProjName(project.getProjName());
			projectResult.setCustName(project.getCustName()); 				// 申请单位
			projectResult.setCustId(project.getCustId());					// 申请单位id
			projectResult.setProjInfoFlag(project.getProjInfoFlag()); 		// 告知书是否发放
			projectResult.setProjLtypeId(project.getProjLtypeId()); 		// 工程大类id
			projectResult.setCustContact(project.getCustContact()); 		// 联系人
			projectResult.setCustPhone(project.getCustPhone()); 			// 联系电话
			projectResult.setProjSubConSta(project.getProjSubConSta()); 	// 主建设阶段
			projectResult.setProjAddr(project.getProjAddr()); 				// 工程地点
			projectResult.setProjectType(project.getProjectType());			//工程类型
			projectResult.setProjectTypeDes(project.getProjectTypeDes());
			projectResult.setContributionMode(project.getContributionMode());
			projectResult.setContributionModeDes(project.getContributionModeDes());//出资方式
			projectResult.setDeptId(project.getDeptId());					//业务部门
			projectResult.setDeptName(project.getDeptName());
			isConfigAddAccepter(projectResult,menuId);//如果有配置则将立项人设置为受理人
			if (flag&&project.getFlag().equals("1")) { // 如果是立项确认则需要产生业务操作记录
				// 形成操作记录
				String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
				operateRecordService.createOperateRecord(orId, projectResult.getProjId(), projectResult.getProjNo(),
						StepEnum.PROJECT_CONFIRM.getValue(), StepEnum.PROJECT_CONFIRM.getMessage(), "");
			}
		}
		List<ScaleDetail> scaleDetails = project.getScaleDetails();
		String totalDes = new String();
		
		if (scaleDetails != null && scaleDetails.size() > 0) {
			String[] LtypeId = project.getProjLtypeId().split(",");
			List<ProjectType> list=projectTypeDao.getAll();
			for (int i = 0; i < LtypeId.length; i++) {
				StringBuffer scaleDes = new StringBuffer(); // 总体规模描述
				String ltype = LtypeId[i];				
				String ltypeDes=this.getTypeDes(ltype,list);
				//String ltypeDes = ProjLtypeEnum.valueof(ltype).getMessage();
				scaleDes.append(ltypeDes).append("——");
				// for(ScaleDetail sdetail:scaleDetails){
				for (int k = 0; k < scaleDetails.size(); k++) {
					ScaleDetail sdetail = scaleDetails.get(k);
					sdetail.setScaleId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
					sdetail.setProjId(projectResult.getProjId());
					sdetail.setProjNo(projectResult.getProjNo());
					if (sdetail.getProjLtypeId().equals(ltype)) {
						scaleDes.append(sdetail.getProjStypeDes());
						if (sdetail.getHouseNum() != null && sdetail.getHouseNum() > 0) {
							scaleDes.append(sdetail.getHouseNum()).append("户,");
						}
						if (sdetail.getSearNum() != null && sdetail.getSearNum() > 0) {
							scaleDes.append(sdetail.getSearNum()).append("座 ,");
						}
						if (sdetail.getNum() != null && sdetail.getNum() > 0) {
							scaleDes.append(sdetail.getNum()).append("台,");
						}
						if (sdetail.getTonnage() != null && sdetail.getTonnage() > 0) {
							scaleDes.append(sdetail.getTonnage()).append("吨,");
						}
						//干线 改管
						if (sdetail.getPipeDiameter() != null) {
							scaleDes.append("管径").append(sdetail.getPipeDiameter()).append(",");
						}
						if (sdetail.getPipeLength() != null) {
							scaleDes.append("长度").append(sdetail.getPipeLength()).append("千米,");
						}
						if (sdetail.getFinishLength() != null) {
							scaleDes.append("完成").append(sdetail.getFinishLength()).append("千米,");
						}
						if (sdetail.getConstructionRatio() != null) {
							scaleDes.append("建设比例").append(sdetail.getConstructionRatio()).append(",");
						}
						if (sdetail.getGasConsumption() != null && sdetail.getGasConsumption() > 0) {
							scaleDes.append("预计用气量 ").append(sdetail.getGasConsumption()).append(" m³,");
						}
					}
				}
				totalDes = totalDes + ((scaleDes.toString()).substring(0, scaleDes.toString().length() - 1)) + ";";
			}
		}
		projectResult.setProjScaleDes(totalDes); // 总体规模描述
		Map<String, Object> mapSD = new HashMap<String, Object>();
		mapSD.put("data", scaleDetails);
		mapSD.put("projId", projectResult.getProjId());
		//新增或更新客户对象
				Customer customer;
				if(StringUtils.isNotBlank(projectResult.getCustId())){
					customer = new Customer();
					customer = customerDao.get(projectResult.getCustId());
					
				}else{
					//没有客户ID的，默认燃气公司为客户
					Department department = departmentDao.get(projectResult.getCorpId());
					customer = customerDao.queryByCustCode(department.getDeptOutCode());
					if(customer==null){
						customer = new Customer();
						customer.setCustId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));//客户ID
						customer.setCustName(department.getDeptName());//客户名称
						customer.setCustCode(department.getDeptOutCode());//客户编码
						customerDao.saveOrUpdate(customer);
					}
					projectResult.setCustId(customer.getCustId());
					
				}
		try {
			projectDao.saveOrUpdate(projectResult);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 调用工程规模明细处理方法
		scaleDetailService.batMaintain(mapSD);
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	public  void isConfigAddAccepter(Project project,String menuId){
		LoginInfo login=SessionUtil.getLoginInfo();

		String key=login.getCorpId()+"_"+menuId;
		List<DataFilerSetUpDto> configList = Constants.getDataFilterMapByKey(key);

		if(configList!=null&&configList.size()>0){//如果有配置则将立项人设置为受理人
			project.setAccepterId(login.getStaffId());
			project.setAccepter(login.getStaffName());
			project.setSurveyerId(login.getStaffId());
			project.setSurveyer(login.getStaffName());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryProjectByTimeForJoint(ProjectQueryReq projectQueryReq) throws ParseException {
		// 最后一个操作记录的工程记录列表（符合状态的当前工程）
		List<Map<String, Object>> ors;
		//SystemSet systemSet=systemSetDao.get("stepId", projectQueryReq.getStepId());
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		SystemSet systemSet=systemSetDao.querySystemSetByStepId(projectQueryReq.getStepId(),loginInfo.getCorpId());
		if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
		projectQueryReq.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		}
		Map<String, Object> map = projectDao.queryProjectForJoint(projectQueryReq);
		// 符合当前状态的工程列表
		List<Project> list = (List<Project>) map.get("data");
		// 当前步骤
		ors = operateRecordDao.getOptRecordByTime(projectQueryReq.getProjStatusId());
		// 时间限制（单位天）
		Integer timel = projectQueryReq.getTimeLimit();
		long secondsLimit=-1l;
		if(timel!=null){
			secondsLimit=timel*24*60*60;
		}
		for (Project pro : list) {
			List<ProjectSign> projectSignList=projectSignDao.findByProjIdAndStatus(pro.getProjId(),IsSignEnum.IS_SIGN.getValue());
			if(projectSignList!=null && projectSignList.size()>0){
				pro.setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//是特殊工程
			}
			SubContract sc = subContractDao.findSubContractByprojId(pro.getProjId());
			if(sc!=null){
				pro.setScNo(sc.getScNo());//合同编号
				pro.setCuName(sc.getCuName());//施工单位
				pro.setStartDate(sc.getScPlannedStartDate());        //开工日期
				pro.setPlanCompleteDate(sc.getScPlannedEndDate());      //竣工日期
			}
			ConstructionPlan constructionPlan = constructionPlanDao.findByProjId(pro.getProjId());
			if(constructionPlan!=null){
				pro.setSuJgj(constructionPlan.getSuJgj());                       //监理
				pro.setBuilder(constructionPlan.getBuilder());                   //现场代表
				pro.setCuPm(constructionPlan.getCuPm());                         //项目经理
				pro.setManagementQae(constructionPlan.getManagementQae());       //施工员
			}
			Contract contract = contractDao.viewContractByprojId(pro.getProjId());
			if(contract!=null){
				pro.setConNo(contract.getConNo());//合同编号
			}

			//整改状态
			if(StringUtils.isNotBlank(pro.getProjChanges())){
				if(pro.getProjChanges().equals("1")){
					pro.setProjChangesDes("合格");
				}
				if(pro.getProjChanges().equals("0")){
					pro.setProjChangesDes("整改");
				}
				if(pro.getProjChanges().equals("2")){
					pro.setProjChangesDes("整改合格");
				}
				if(pro.getProjChanges().equals("3")){
					pro.setProjChangesDes("验收中");
				}
			}

			for (Map<String, Object> or : ors) {
				if (or.get("PROJ_ID").equals(pro.getProjId())) {
					// 业务操作记录中时间
					Date oldTime = (Date) or.get("OPERATE_TIME");

					// 当前时间
					Date nowTime = projectDao.getDatabaseDate();
					long seconds = (nowTime.getTime() - oldTime.getTime()) / 1000;
					// 如果当前时间-上个步骤的操作时间大于时间限制段则为超时
					if (secondsLimit>0&&seconds > secondsLimit) {
						long differ = seconds - secondsLimit;
						int i = (int) Math.ceil(differ / (24 * 60 * 60));
						pro.setOverDay(i);
						pro.setOverdue(true);
						continue;

					}
				}
			}

		}
		return map;
	}
	
	/**
	 * 查询工程派遣情况
	 * @author fuliwei
	 * @createTime 2017年12月27日
	 * @param 
	 * @return
	 */
	@Override
	public Project viewProjectDispatch(String projId, String stepId) {
		Project pro=projectDao.get(projId);
		if(pro!=null){
			if(StepEnum.DESIGN_DISPATCH.getValue().equals(stepId) && StringUtils.isNotBlank(pro.getSurveyerId())){
				//勘察派工  查踏勘员
				Staff staff=staffDao.get(pro.getSurveyerId());
				pro.setPictureUrl(staff.getPhotoUrl());
			}else if(StepEnum.BUDGET_DISPATCH.getValue().equals(stepId) && StringUtils.isNotBlank(pro.getBudgeterId())){
				//预算派工
				Staff staff=staffDao.get(pro.getBudgeterId());
				pro.setPictureUrl(staff.getPhotoUrl());
			}else if(StepEnum.QUALITIES_DISPATCH.getValue().equals(stepId) && StringUtils.isNotBlank(pro.getBudgeterAuditId())){
				Staff staff=staffDao.get(pro.getBudgeterAuditId());
				pro.setPictureUrl(staff.getPhotoUrl());
			}else if(StepEnum.AUDIT_DONE_DISPATCH.getValue().equals(stepId) && StringUtils.isNotBlank(pro.getSettlementerId())){
				Staff staff=staffDao.get(pro.getSettlementerId());
				pro.setPictureUrl(staff.getPhotoUrl());
			}
			return pro;
		}
		return pro;
	}

	@Override
	public Map<String, String> getDeptInfoForStatistic() {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String post=loginInfo.getPost();
		String [] postArray=post.split(",");
		String unitType=UnitTypeEnum.GAS_COMPANY.getValue();
		String deptId = loginInfo.getDeptId();
		Map<String, String> deptInfo = new HashMap<String,String>();
		 //分包方单位人员登录时
		BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		 if(bp!=null){//1业务合作伙伴
			 unitType=bp.getUnitType();
		 }else{//1燃气集团
			 if(!DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide())){//2非设计院
				 if(BusinessTypeEnum.BUSINESS_GROUP.getValue().equals(loginInfo.getBusinessType())){//3业务部门 
					 deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.BUSINESS_GROUPS.getInitVal().length());
				 }else{//3非业务部门
					 if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){//4客服中心
						 if(StringUtil.isNotBlank(post) && (post.contains(PostTypeEnum.RECORDER.getValue())||post.contains(PostTypeEnum.CHIEF_ENGINEER.getValue()))){
							 deptId =loginInfo.getDeptId();
						 }else{
							 deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.BUSINESS_GROUPS.getInitVal().length());
						 }
					 }else{//4非客服中心
						 if(DeptDivideEnum.CIVIL_GROUP.getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MARKETING_CENTER.getValue().equals(loginInfo.getDeptDivide())||
								 DeptDivideEnum.TRUNK_GROUP .getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MODIFICATION_GROUP.getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MARKET_DEVELOPMENT_DEPARTMENT.getValue().equals(loginInfo.getDeptDivide())){
							 //工程部门 民用 公建 改管 干线  时长发展部
							 deptId=loginInfo.getDeptId();
						 }else{
							 deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
						 }
					 }	
				 }
			 }else{//2设计院
				 deptId="110101";
			 }
		 }
		 deptInfo.put("unitType", unitType);
		 deptInfo.put("deptId", deptId);
		 return deptInfo;
	}
	
	/**
	 * 立项日期大于某个日期返回true，否则返回false
	 * @author liaoyq
	 * @createTime 2018年4月20日
	 * @param projId
	 * @return
	 * @throws ParseException
	 */

	@Override
	public boolean isToCall(String projId,String serviceNo){
		//工程立项时间在配置之后的
		Project project = projectDao.get(projId);
		//查询接口是否开启
		List<WebServiceSet> list = webServiceSetDao.queryIsOpenByNO(serviceNo,project.getCorpId());
		if(list == null || list.size()<1){
			return false;
		}
		WebServiceSet serviceSet = list.get(0);
		if(!"1".equals(serviceSet.getWebServiceFlag())){//接口未启用
			return false;
		}
		//合同签订日期
		Contract contract = contractDao.viewContractByprojId(projId);
		if(contract!=null && contract.getSignDate()!=null && contract.getSignDate().compareTo(serviceSet.getConSignDate())>=0){
			return true;
		}
		if(project!=null && project.getAcceptDate()!=null && project.getAcceptDate().compareTo(serviceSet.getProjAcceptDate())>=0){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * 注释：查看接口是否是实时同步
	 * @author liaoyq
	 * @createTime 2019年10月10日
	 * @param projId
	 * @param serviceNo
	 * @return
	 *
	 */
	@Override
	public boolean isSynchronization(String projId,String serviceNo){
		//工程立项时间在配置之后的
		Project project = projectDao.get(projId);
		//查询接口是否开启
		List<WebServiceSet> list = webServiceSetDao.queryIsOpenByNO(serviceNo,project.getCorpId());
		if(list == null || list.size()<1){
			return false;
		}
		WebServiceSet serviceSet = list.get(0);
		if(serviceSet.getIsSync()==1){
			return true;
		}
		return false;
	}



	/**
	 * 查询工程状态之间的耗时情况
	 * 两个状态之间的设置的时限累加的到总的时限
	 * 获取开始状态和结束状态的操作日志：如果开始状态的操作日志没有，则不获取时限，如果开始状态已有操作日志，判断结束状态是否有操作日志，没有则获取系统当前时间
	 * 获取两个状态之间的工程信息
	 * 并根据日期计算已用工作日时间
	 */
	@Override
	public Map<String, Object> queryProjectLimitTime(
			ProjectQueryReq projectQueryReq) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isBlank(projectQueryReq.getStartStatusId())) {
			return map;
		}
		if (StringUtil.isBlank(projectQueryReq.getEndStatusId())){
			return map;
		}
		String[] projStatus =new String[]{};
		List<ProjStatusEnum> list=ProjStatusEnum.getThanValueNew(projectQueryReq.getStartStatusId(),projectQueryReq.getEndStatusId());
		List<String> statusList=new ArrayList<String>();//状态列表
		List<String> stepList = new ArrayList<String>();//步骤列表
		for(int i=0;i<list.size();i++){
			statusList.add(list.get(i).getValue());
			stepList.add(WorkFlowActionEnum.byStatusCode(list.get(i).getValue()).getActionCode());
		}
		projectQueryReq.setProjStuList(statusList);
		//获取状态之间的工程信息
		map = projectDao.queryProject(projectQueryReq, projStatus);
		List<Project> projectList = (List<Project>) map.get("data");
		
		//获取步骤配置时限
		List<SystemSet> sysSets = systemSetDao.queryByStepIds(stepList);
		//步骤之间设置的总时限天数
		long timeDays=0;
		if(sysSets!=null && sysSets.size()>0){
			for(SystemSet st : sysSets){
				if(StringUtil.isNotBlank(st.getTimeSpan())){
					timeDays = timeDays+Long.parseLong(st.getTimeSpan());
				}else{
					timeDays = timeDays+0;
				}
			}
		}
		long secondsLimit=-1l;
		if(timeDays!=0){
			secondsLimit=timeDays*24*60*60;
		}
		// 步骤
		List<Map<String,Object>> startOrs = operateRecordDao.getOptRecordByTime(projectQueryReq.getStartStatusId());
		List<Map<String,Object>> endOrs = operateRecordDao.getOptRecordByTime(projectQueryReq.getEndStatusId());

		//
		if (projectList != null && projectList.size() > 0) {
			for (int i = 0; i < projectList.size();i++) {
				for (Map<String, Object> or : startOrs) {
					if (or.get("PROJ_ID").equals(projectList.get(i).getProjId())) {
						// 业务操作记录中时间
						Date oldTime = (Date) or.get("OPERATE_TIME");
						//oldTime = FestivalUtil.getChangeDate(oldTime);//业务操作时间
						// 当前时间
						Date nowTime = projectDao.getDatabaseDate();
						if(endOrs!=null && endOrs.size()>0){
							Map<String, Object> opr = endOrs.get(0);
							nowTime = (Date) opr.get("OPERATE_TIME");
						}
						//获取两个日期之间的工作日天数
						long workDays = 0;
						try {
							workDays = FestivalUtil.calLeaveDays(oldTime, nowTime, festivalService.getCacheMap(Constants.WORKDAYS), festivalService.getCacheMap(Constants.HOLIDAYS));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						long seconds = workDays*24*60*60;
						//如果是施工预算审核，那么原定时长为
						if(StepEnum.QUALITIES_JUDGEMENT.getValue().equals(projectQueryReq.getStepId())){
							String timeLimitType = null;
							if(projectList.get(i).getSubmitAmount().compareTo(new BigDecimal(50000))<0){
								timeLimitType = TimeLimitTypeEnum.TIME_LIMIT13.getValue();
							}else if(projectList.get(i).getSubmitAmount().compareTo(new BigDecimal(100000))<0){
								timeLimitType = TimeLimitTypeEnum.TIME_LIMIT14.getValue();
							}else if(projectList.get(i).getSubmitAmount().compareTo(new BigDecimal(500000))<0){
								timeLimitType = TimeLimitTypeEnum.TIME_LIMIT15.getValue();
							}else if(projectList.get(i).getSubmitAmount().compareTo(new BigDecimal(1000000))<0){
								timeLimitType = TimeLimitTypeEnum.TIME_LIMIT16.getValue();
							}else{
								timeLimitType = TimeLimitTypeEnum.TIME_LIMIT17.getValue();
							}
							TimeLimit  timeLimit = timeLimitDao.findByType(timeLimitType);
							if(null!=timeLimit){
								projectList.get(i).setAcquisitionDays(timeLimit.getTlDuration().toString());
								if(timeLimit.getTlDuration().compareTo(BigDecimal.ZERO)>0){
									long days = timeLimit.getTlDuration().setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
									if(days>0){
										secondsLimit=days*24*60*60;
									 }
									timeDays = days;
								}
							}
						}
						//该步骤延期申请已通过的时长之和
						long delyDays =applyDelayDao.getDelyDays(projectList.get(i).getProjId(),projectQueryReq.getStepId()).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
						if(delyDays>0){
							secondsLimit+=(delyDays*24*60*60);
							timeDays +=delyDays;
						}
						//用于时限进度展示
						WorkDayDto workDayDto = new WorkDayDto();
						workDayDto.setDaysLimit(String.valueOf(timeDays));
						workDayDto.setWorkDays(String.valueOf(workDays));
						workDayDto.setHaveDays(String.valueOf(timeDays-workDays));
						projectList.get(i).setWorkDayDto(workDayDto);
						// 如果当前时间-上个步骤的操作时间大于时间限制段则为超时
						if (secondsLimit>0&&seconds > secondsLimit) {
							long differ = seconds - secondsLimit;
							int k = (int) Math.ceil(differ / (24 * 60 * 60));
							projectList.get(i).setOverDay(k);
							projectList.get(i).setOverdue(true);
							continue;

						}
					}
				}
			}
		}
		return map;
	}
	/**
	 * 签订合同时保存工程规模详细
	 * wang.hui.jun
	 * 2018.06.26
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
	public String SaveProjectScale(Project project, boolean flag) throws Exception {
		Project projectResult = project;
		Contract contract = contractService.findByProjId(project.getProjId());  //通过工程id得到实体
		ResultMessage resultMessage = new ResultMessage();

		List<ScaleDetail> scaleDetails = project.getScaleDetails();
		String totalDes = new String();
		Integer totalHouseNum = 0;
		if (scaleDetails != null && scaleDetails.size() > 0) {
			String[] LtypeId = project.getProjLtypeId().split(",");
			List<ProjectType> list=projectTypeDao.getAll();
			for (int i = 0; i < LtypeId.length; i++) {
				StringBuffer scaleDes = new StringBuffer(); // 总体规模描述
				String ltype = LtypeId[i];				
				String ltypeDes=this.getTypeDes(ltype,list);
				//String ltypeDes = ProjLtypeEnum.valueof(ltype).getMessage();
				scaleDes.append(ltypeDes).append("——");
				// for(ScaleDetail sdetail:scaleDetails){
				for (int k = 0; k < scaleDetails.size(); k++) {
					ScaleDetail sdetail = scaleDetails.get(k);
					sdetail.setScaleId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
					sdetail.setProjId(projectResult.getProjId());
					sdetail.setProjNo(projectResult.getProjNo());
					if (sdetail.getProjLtypeId().equals(ltype)) {
						scaleDes.append(sdetail.getProjStypeDes());
						if (sdetail.getHouseNum() != null && sdetail.getHouseNum() > 0) {
							scaleDes.append(sdetail.getHouseNum()).append("户,");
						}
						if (sdetail.getSearNum() != null && sdetail.getSearNum() > 0) {
							scaleDes.append(sdetail.getSearNum()).append("座 ,");
						}
						if (sdetail.getNum() != null && sdetail.getNum() > 0) {
							scaleDes.append(sdetail.getNum()).append("台,");
						}
						if (sdetail.getTonnage() != null && sdetail.getTonnage() > 0) {
							scaleDes.append(sdetail.getTonnage()).append("吨,");
						}
						//干线 改管
						if (sdetail.getPipeDiameter() != null) {
							scaleDes.append("管径").append(sdetail.getPipeDiameter()).append(",");
						}
						if (sdetail.getPipeLength() != null) {
							scaleDes.append("长度").append(sdetail.getPipeLength()).append("千米,");
						}
						if (sdetail.getFinishLength() != null) {
							scaleDes.append("完成").append(sdetail.getFinishLength()).append("千米,");
						}
						if (sdetail.getConstructionRatio() != null) {
							scaleDes.append("建设比例").append(sdetail.getConstructionRatio()).append(",");
						}
						if (sdetail.getGasConsumption() != null && sdetail.getGasConsumption() > 0) {
							scaleDes.append("预计用气量 ").append(sdetail.getGasConsumption()).append(" m³/h,");
						}
					}
					if((sdetail.getHouseNum()!=null)){
						totalHouseNum = totalHouseNum+sdetail.getHouseNum();   //取得总户数
					}
				}
				totalDes = totalDes + ((scaleDes.toString()).substring(0, scaleDes.toString().length() - 1)) + ";";
				
			}
		}
		projectResult.setProjScaleDes(totalDes); // 总体规模描述
		contract.setProjScaleDes(totalDes);   // 总体规模描述
		contract.setHousehold(String.valueOf(totalHouseNum));   //安装户数
		Map<String, Object> mapSD = new HashMap<String, Object>();
		mapSD.put("data", scaleDetails);
		mapSD.put("projId", projectResult.getProjId());
		projectDao.saveOrUpdate(projectResult);	
		contractDao.saveOrUpdate(contract);
		// 保存规模详细
		scaleDetailService.batMaintain(mapSD);
        
		return Constants.OPERATE_RESULT_SUCCESS;
	}
    
	/**
	 * 
	 * 根据工程ID取工程计划表中的信息然后转存到工程表中
	 * @author wanghuijun
	 * @createTime 2018年7月26日
	 * @param projId
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveProjectPlanInfo(String projId) throws Exception {
		/**
		 * 若工程Id不为空,根据工程Id查询出工程计划信息,并且将施工
		 * 单位派遣信息和监理单位派遣信息存入工程表
		 */
		if(StringUtil.isNotBlank(projId)){
		   ConstructionPlan constructionPlan = constructionPlanDao.get("projId",projId);
		   Project project = projectDao.get(projId);
		   if(constructionPlan!=null && project!=null){
			   if(StringUtil.isNotBlank(constructionPlan.getBuilderId())){  //现场代表ID赋值
				   project.setBuilderId(constructionPlan.getBuilderId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getBuilder())){   //现场代表赋值
				   project.setBudiler(constructionPlan.getBuilder());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getCuId())){  //施工单位ID赋值
				   project.setCuId(constructionPlan.getCuId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getCuName())){  //施工单位名称赋值
				   project.setCuName(constructionPlan.getCuName());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getCuLegalRepresent())){  //施工单位负责人名字赋值
				   project.setCuLegalRepresent(constructionPlan.getCuLegalRepresent());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getCuLegalRepresentId())){  //施工单位负责人ID赋值
				   project.setCuLegalRepresentId(constructionPlan.getCuLegalRepresentId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getCuPm())){  //施工单位项目经理赋值
				   project.setCuPm(constructionPlan.getCuPm());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getCuPmId())){  //施工单位项目经理ID赋值
				   project.setCuPmId(constructionPlan.getCuPmId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getManagementQaeId())){  //施工单位施工员ID赋值
				   project.setManagementQaeId(constructionPlan.getManagementQaeId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getManagementQae())){  //施工单位施工员赋值
				   project.setManagementQae(constructionPlan.getManagementQae());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getManagementWacfId())){  //施工单位材料员ID赋值
				   project.setManagementWacfId(constructionPlan.getManagementWacfId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getManagementWacf())){  //施工单位材料员赋值
				   project.setManagementWacf(constructionPlan.getManagementWacf());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getDocumenterId())){  //资料员ID赋值
				   project.setDocumenterId(constructionPlan.getDocumenterId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getDocumenter())){  //资料员赋值
				   project.setDocumenter(constructionPlan.getDocumenter());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getSaftyOfficerId())){  //安全员ID赋值
				   project.setSaftyOfficerID(constructionPlan.getSaftyOfficerId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getSaftyOfficer())){  //安全员赋值
				   project.setSaftyOfficer(constructionPlan.getSaftyOfficer());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getTechnicianId())){  //质检员ID赋值
				   project.setTechnicianId(constructionPlan.getTechnicianId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getTechnician())){  //质检员赋值
				   project.setTechnician(constructionPlan.getTechnician());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getTestLeaderId())){  //班组长ID赋值
				   project.setTestLeaderId(constructionPlan.getTestLeaderId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getTestLeader())){  //班组长赋值
				   project.setTestLeader(constructionPlan.getTestLeader());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getSuId())){  //监理单位ID赋值
				   project.setSuId(constructionPlan.getSuId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getSuName())){  //监理单位名称赋值
				   project.setSuName(constructionPlan.getSuName());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getSuDirector())){  //监理单位负责人
				   project.setSuDirector(constructionPlan.getSuDirector());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getSuCseId())){  //监理单位总监理师ID
				   project.setSuCseId(constructionPlan.getSuCseId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getSuCse())){  //监理单位总监理师
				   project.setSuCse(constructionPlan.getSuCse());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getSuJgjId())){  //监理单位现场监理师ID
				   project.setSuJgjId(constructionPlan.getSuJgjId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getSuJgj())){  //监理单位现场监理师
				   project.setSuJgj(constructionPlan.getSuJgj());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getSuProfessionalId())){  //监理单位专业监理师ID
				   project.setSuProfessionalId(constructionPlan.getSuProfessionalId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getSuProfessional())){  //监理单位专业监理师
				   project.setSuProfessional(constructionPlan.getSuProfessional());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getSuRepresentativeId())){  //监理单位总监代表ID
				   project.setSuRepresentativeId(constructionPlan.getSuRepresentativeId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getSuRepresentative())){  //监理单位总监代表
				   project.setSuRepresentative(constructionPlan.getSuRepresentative());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getWelderId())){  //焊工ID
				   project.setWelderId(constructionPlan.getWelderId());
			   }
			   if(StringUtil.isNotBlank(constructionPlan.getWelder())){  //焊工
				   project.setWelder(constructionPlan.getWelder());
			   }
			   projectDao.saveOrUpdate(project);  //保存工程信息
			   return Constants.OPERATE_RESULT_SUCCESS;
		   }
		 
		}
		
		return Constants.OPERATE_RESULT_FAILURE;
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Project acceptService(Project project,ProjectAcceptDto acceptDto,String operateType) {
		
		//默认工程类型（公建）、出资方式（用户出资）、受理部门Id、受理日期（当前日期）、受理人ID、受理人、受理方式
		project.setProjectType(Project.PRO_TYPE_GJ);//工程类型
		project.setProjLtypeId(Project.PRO_TYPE_GJ);
		project.setProjectTypeDes(Project.PRO_TYPE_DES_GJ);//类型描述
		project.setContributionMode(Project.PRO_CON_MODE_GJ);//出资方式
		project.setContributionModeDes(Project.PRO_CON_MODE_DES_GJ);//出资方式描述
		
		String statusId=workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),WorkFlowActionEnum.PROJECT_ACCEPT.getActionCode(), true);
		project.setProjStatusId(statusId);//工程状态受理的下一个

		//是否配置
		List<DataFilerSetUpDto> datalist = Constants.getDataFilterMapByKey(project.getCorpId()+"_"+Constants.MODULE_CODE_PROJECTAPPROVAL);
		Boolean flag = true;
		String todoer="";
		String todoerId="";

		if(datalist!=null && datalist.size()>0){
			if(StringUtils.isNotBlank(datalist.get(0).getSupSql())){
				project.setProjStatusId(datalist.get(0).getSupSql());//工程状态配置
				todoer= datalist.get(0).getQueryDeptName();
				todoerId = datalist.get(0).getRemark();
				flag = false;
			}
		}

		if(operateType.equals(OperateTypeEnum.INSERT.getValue())){
			// 新增工程
			String projId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);// 生成唯一ID
			project.setProjId(projId);
			//project.setTenantId(SessionUtil.getTenantId());//暂时存上
			String projNo = this.getProjMaxNo(project.getCorpId(),project.getProjectType(),project.getContributionMode());
			if(projNo.equals("noneNumber")){
				return project;
			}
			if("1".equals(project.getArea())){//遵义新蒲新区的工程，工程编号前加“新”
				projNo = Constants.ZUNYI_XINPU_PROJNO_PREX+projNo;
			}
			project.setProjNo(projNo);
			project.setArea(StringUtil.isNotBlank(project.getArea())?project.getArea():"");
			project.setAcceptType(ProjectMethodEnum.PROJECT_APPLY.getValue());
			project.setAcceptDate(projectDao.getDatabaseDate()); // 受理日期
					
			// 形成受理申请的操作记录
			//后期子公司，在受理后还有受理审核，后期再进行调整
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
			if(flag){
				String toDoer=operateRecordService.createOperateRecordByOther(orId, project.getProjId(), project.getProjNo(),StepEnum.PROJECT_ACCEPT.getValue(), StepEnum.PROJECT_ACCEPT.getMessage(), "",project);
				project.setToDoer(toDoer);
			}else{
				operateRecordService.createWxOperateRecord(project.getProjId(), project.getProjNo(),StepEnum.MARKET_DISPATCH.getValue(), StepEnum.MARKET_DISPATCH.getMessage(),"报装受理",
						todoer,","+todoerId+",",OperateWorkFlowEnum.WAIT_DONE.getValue(),project.getCorpId(),project.getProjectType(),project.getContributionMode());
				project.setToDoer(todoer);
			}
			projectDao.save(project);
		}/*else if(projAcceptInfo.getOperate_type().equals(OperateTypeEnum.UPDATE.getValue())){
			
		}else if(projAcceptInfo.getOperate_type().equals(OperateTypeEnum.DELETE.getValue())){
			
		}*/
		return project;
		
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	  public void saveProBudgetMethod(Project proj)
	  {
	    Project project = (Project)this.projectDao.get(proj.getProjId());
	    String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET);
	    if (project != null)
	    {
	      project.setBudgetMethod(proj.getBudgetMethod());
	      project.setBudgetMethodReMark(proj.getBudgetMethodReMark());
	      String stepId = BudgetMethodEnum.CORP_BUDGET.getStepId();
	      String todoer="";
	      if (BudgetMethodEnum.THREE_BUDGET.getValue().equals(proj.getBudgetMethod()))
	      {
	        stepId = BudgetMethodEnum.THREE_BUDGET.getStepId();//第三方做预算  1301   预算派工,下一步骤为预算记录
	        project.setBudgeter(project.getSurveyer());
	        project.setBudgeterId(project.getSurveyerId());
	        //预算方式确认的操作记录置为无效，把预算记录的操作记录置为有效
	        todoer= this.operateRecordService.createOperateRecordNext(orId, project.getProjId(), project.getProjNo(), BudgetMethodEnum.CORP_BUDGET.getStepId(), StepEnum.valueof(stepId).getMessage(), "",stepId);
	      }
	      /*else if (BudgetMethodEnum.SELF_BUDGET.getValue().equals(proj.getBudgetMethod()))
	      {
	        stepId = BudgetMethodEnum.SELF_BUDGET.getStepId();//自己预算 12031 预算方式确定 下一步骤为预算派工
	      }*/
	      else
	      {
	        stepId = BudgetMethodEnum.CORP_BUDGET.getStepId();//自己预算 12031 预算方式确定 下一步骤为预算派工
	        todoer= this.operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepId, StepEnum.valueof(stepId).getMessage(), "");
	      } 
	      String status = this.workFlowService.queryProjStatusId(project.getCorpId(), project.getProjectType(), project.getContributionMode(), stepId, Boolean.valueOf(true));
	      project.setProjStatusId(status);
	     project.setToDoer(todoer);
	     this.projectDao.update(project);
	    }
	  }

	@Override
	public Project viewDetial(Project project) throws ParseException {
		ProjectApplication pa=projectApplicationService.queryById(project.getProjId());
		if(pa!=null){
			project.setPaNo(pa.getPaNo());
		}
		//查询工程分包预算审核人审核时间
		/*if(StringUtils.isNotBlank(project.getBudgeterAudit())){//分包预算审核员
			ManageRecord mr = manageRecordService.findByMrCsrId(project.getProjId(),StepEnum.QUALITIES_JUDGEMENT.getValue(),project.getBudgeterAuditId());
			if(mr!=null){
				
				project.setBudgeterAuditDate(mr.getMrTime());
			}
		}*/
	    Budget budget=budgetService.queryBudgeByprojId(project.getProjId());
		if(budget!=null){
			project.setBudgetMethodReMark(budget.getRemark());
		}

		//分包预算员
		SubBudget subBudget = subBudgetService.viewSubBudget(project.getProjId());
		if(subBudget!=null){
			if(StringUtils.isNotBlank(subBudget.getSuBudgeter())){
				project.setSuBudgeter(subBudget.getSuBudgeter());//分包预算员
				project.setSuBudgeterDate(subBudget.getSbDate());//分包预算日期
			}
			if(subBudget.getTotalCost()!=null){
				project.setBudgetCost(subBudget.getTotalCost());//分包预算价（定额）
			}else if(subBudget.getTotalQuota()!=null){
				project.setBudgetCost(subBudget.getTotalQuota());//分包预算价（清单）
			}
			if(subBudget.getTotalCostAudit()!=null){
				project.setBudgetSettlementCost(subBudget.getTotalCostAudit());//分包预算审定价（定额）
			}else if(subBudget.getTotalQuotaAudit()!=null){
				project.setBudgetSettlementCost(subBudget.getTotalQuotaAudit());//分包预算审定价（清单）
			}
		}
		//结算
		SettlementDeclaration settlementDeclaration=settlementDeclarationService.getSettlementDeclaration(project.getProjId());
		if(settlementDeclaration!=null){
			if(settlementDeclaration.getSendDeclarationCost()!=null){
				project.setSendSettlementCost(settlementDeclaration.getSendDeclarationCost());//结算报审价
			}
			if(settlementDeclaration.getEndDeclarationCost()!=null){
				project.setEndSettlementCost(settlementDeclaration.getEndDeclarationCost());//结算审定价
			}
		}
		//政府预算审定
		GovAuditCost govAuditCost=govAuditCostService.queryByProjIdAndType(project.getProjId(), GovAuditCostTypeEnum.BUDGET.getValue());
		if(govAuditCost!=null&&govAuditCost.getAuthorizedCost()!=null){
			project.setGovBudgetCost(govAuditCost.getAuthorizedCost());//预算审定价
		}
		GovAuditCost govAuditCost1=govAuditCostService.queryByProjIdAndType(project.getProjId(), GovAuditCostTypeEnum.SETTLEMENT.getValue());
		if(govAuditCost1!=null&&govAuditCost1.getAuthorizedCost()!=null){
			project.setGovSettlementCost(govAuditCost1.getAuthorizedCost());//结算审定价
		}
		//获取分包预算一级审核人
		ManageRecord mr = manageRecordService.findFirstMrCsr(project.getProjId(),StepEnum.QUALITIES_JUDGEMENT.getValue(),"1");
		if(mr!=null){
			Staff staff = staffService.getStaff(mr.getMrCsr());
			project.setBudgeterAudit(staff!=null?staff.getStaffName():"");
			project.setBudgeterAuditDate(mr.getMrTime());
		}
		//计划竣工日期-计划只存工期
		/*ConstructionPlan conPlan = constructionPlanService.findByProjId(projId);
		if(conPlan!=null&&StringUtils.isNotBlank(conPlan.getProjTimeLimit())){
			project.setPlanCompleteDate(DateUtil.addDay(conPlan.getPlannedStartDate(),Integer.parseInt(conPlan.getProjTimeLimit())));
		}*/
		//分包合同的计划竣工日期
		SubContract subContract = subContractService.findSubContractByprojId(project.getProjId());
		if(subContract!=null){
			project.setStartDate(subContract.getScPlannedStartDate());//计划开工日期
			project.setPlanCompleteDate(subContract.getScPlannedEndDate());
		}
		//实际开工日期
		WorkReport wr = workReportService.findByProjId(project.getProjId());
		if(wr!=null){
			project.setAcStartDate(wr.getPlannedStartDate());
		}
		
		//实际竣工日期
		//竣工报告
		List<CompleteReport> crs = completeReportDao.findByProjId(project.getProjId());
		if(crs!=null && crs.size()>0){
			CompleteReport cr=crs.get(0);
			project.setCompletedDate(cr.getRealEndDate());//实际竣工日期
		}
		//联合验收日期
		List<JointAcceptance> jointAcceptance=jointAcceptanceDao.findById(project.getProjId());
		if(jointAcceptance!=null&&jointAcceptance.size()>0){
			project.setJointAcceptanceDate(jointAcceptance.get(0).getJaDate());
		}
		if(project!=null && StringUtil.isNotBlank(project.getAccepterId())){
			Staff staff=staffDao.get(project.getAccepterId());
			project.setAccepterPhone(staff.getPhone());
		}
		//提资信息
		RaiseMoney raiseMoney = raiseMoneyDao.get("projId", project.getProjId());
		if(raiseMoney!=null){
			//获取最后一个审核日期
			ManageRecordQueryReq queryReq = new ManageRecordQueryReq();
			queryReq.setProjId(project.getProjId());
			queryReq.setStepId(StepEnum.RAISEMONEY_AUDIT.getValue());;
			ManageRecord mRecord = manageRecordService.findEndMrCsr(queryReq);
			if(mRecord!=null && mRecord.getMrTime()!=null){
				project.setRaiseMoneyAuditDate(mRecord.getMrTime());
			}
			//得到提资信息
			if(raiseMoney.getApplyDate()!=null){
				project.setRaiseMoneyApplyDate(raiseMoney.getApplyDate());
			}
			if(raiseMoney.getCustReposeDate()!=null){
				project.setRaiseMoneyResponseDate(raiseMoney.getCustReposeDate());
			}
		}
		//受理审核日期
		//获取最后一个审核日期
		ManageRecordQueryReq queryReq = new ManageRecordQueryReq();
		queryReq.setProjId(project.getProjId());
		queryReq.setStepId(StepEnum.PROJECT_ACCEPT_AUDIT.getValue());;
		ManageRecord mRecord = manageRecordService.findEndMrCsr(queryReq);
		if(mRecord!=null && mRecord.getMrTime()!=null){
			project.setAcceptAuditDate(mRecord.getMrTime());
		}
		//获取踏勘派工日期
		OperateRecordQueryReq req = new OperateRecordQueryReq();
		req.setProjId(project.getProjId());
		req.setStepId(StepEnum.DESIGN_DISPATCH.getValue());
		req.setIsValid("1");;
		OperateRecord operateRecord = operateRecordDao.queryEndOperateRecord(req);
		if(operateRecord!=null && operateRecord.getOperateTime()!=null){
			project.setSurveyDisDate(operateRecord.getOperateTime());
		}
		return project;
	}

	@Override
	public Date queryDuPlCompletedDate(DesignInfo di) {
		if(di!=null){
			//pro.setOcoDate(di.getOcoDate());//设计委托日期
			BigDecimal delyDays =applyDelayDao.getDelyDays(di.getProjId(),StepEnum.DESIGN_DRAWING.getValue());
			if(StringUtil.isNotBlank(di.getAcquisitionDays())){//委托天数
				//acquisitionDay = di.getAcquisitionDays();
				BigDecimal acquisitionDays = new BigDecimal(di.getAcquisitionDays());
				delyDays = delyDays.add(acquisitionDays);
			}
			int days = delyDays.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
			if(days>0){
				 //计算计划设计完成日期（只计算工作日）
				return FestivalUtil.calLeaveDate(di.getOcoDate(), days, festivalService.getCacheMap(Constants.WORKDAYS), festivalService.getCacheMap(Constants.HOLIDAYS));
			}
		}
		return null ;
	}

	@Override
	public Map<String, Object> toDesignDispatchProjectList(ProjectQueryReq projectQueryReq) {
		return projectDao.toDesignDispatchProjectList(projectQueryReq);
	}


	@Override
	public Project getData(String projId) {
		Project proj=projectDao.get(projId);
		//return projectDao.getData(projId);
		
		ChargeReq chargeReq = new ChargeReq();
		chargeReq.setArAtatus(ARStatusEnum.ALREADY_CHARGE.getValue());
		chargeReq.setArType(CollectionTypeEnum.INITIAL_PAYMENT.getValue());
		chargeReq.setProjId(projId);
		
		Contract contract = contractDao.viewContractByprojId(projId);
		if(contract!=null){
			if (null != contract) {
				// 获取实收首付款
				Map<String, Object> map = accrualsRecordDao.queryAccrualsRecord(chargeReq);
				BigDecimal amount = new BigDecimal(0.00);
				String isPay = "false";
				if (map != null && map.get("data") != null) {
					List list = (List) map.get("data");
					if (list != null && list.size() > 0) {
						AccrualsRecord al = (AccrualsRecord) list.get(0);
						amount = al.getReceiveAmount();
						if (amount.equals(al.getArCost())) {
							isPay = "true";
						}
					}
				}
				proj.setBudgetCost(contract.getFirstPayment());//应收首付款
				proj.setConfirmTotalCost(amount);//实收
			}
		}
		return proj;
	}

	@Override
	public Project getInsttasks(String projId) {
		Project project = projectDao.get(projId);
		List<AccrualsRecord> listAccrualsRecord = accrualsRecordDao.getDataByProjNew(projId,"13");
		if(listAccrualsRecord!=null && listAccrualsRecord.size()>0){
			project.setArCost(listAccrualsRecord.get(0).getArCost());
			project.setReceiveAmount(listAccrualsRecord.get(0).getReceiveAmount());
		}
		List<InstTasks> listInstTasks = instTasksDao.findByProjId(projId);
		if(listInstTasks!=null && listInstTasks.size()>0){
			InstTasks instTasks = listInstTasks.get(0);
			project.setInstTaskNote(instTasks.getRemark());//安装任务备注
			project.setOrderMan(instTasks.getOrderMan());//下单人
			project.setInstTaskDate(instTasks.getOrderDate());//下单时间
			project.setMeterType(instTasks.getMeterType());//表具型号
		}
		return project;
	}

	@Override
	public void updateProject(Project project) {
		projectDao.update(project);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void saveInstTasks(Project project) {
		Project old = projectDao.findByProjNo(project.getProjNo()).get(0);
		Date date = projectDao.getDatabaseDate();
		Timestamp timestamp = new Timestamp(date.getTime());
		/*old.setInstTaskDate(timestamp);
		old.setInstTaskNote(project.getInstTaskNote());*/
		String statusId=workFlowService.queryProjStatusId(old.getCorpId(),old.getProjectType(),old.getContributionMode(), WorkFlowActionEnum.PROJECT_INST_TASKS.getActionCode(), true);
		old.setProjStatusId(statusId);//工程状态受理的下一个

		//保存安装任务表
		InstTasks instTasks = new InstTasks();
		List<InstTasks> listInstTasks=instTasksDao.findByProjId(project.getProjId());
		if(listInstTasks!=null&&listInstTasks.size()>0){
			InstTasks oldInstTasks = listInstTasks.get(0);
			oldInstTasks.setMeterType(project.getMeterType());
			oldInstTasks.setRemark(project.getInstTaskNote());
			oldInstTasks.setOrderDate(timestamp);
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			oldInstTasks.setOrderMan(loginInfo.getStaffName());
			instTasksDao.update(oldInstTasks);
		}else{
			String id = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);//生成唯一ID  待改正
			instTasks.setId(id);
			instTasks.setProjId(project.getProjId());
			instTasks.setProjNo(project.getProjNo());
			instTasks.setMeterType(project.getMeterType());
			instTasks.setRemark(project.getInstTaskNote());
			instTasks.setOrderDate(timestamp);
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			instTasks.setOrderMan(loginInfo.getStaffName());
			instTasksDao.save(instTasks);
		}


		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);//生成唯一ID
		//生成操作记录和待办人
		String toder=operateRecordService.createOperateRecord(orId, old.getProjId(), old.getProjNo(), StepEnum.PROJECT_INST_TASKS.getValue(), StepEnum.PROJECT_INST_TASKS.getMessage(), "");
		old.setToDoer(toder);

		this.updateProject(old);
		//return projectDao.findByProjNo(projNo);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateToDoerById(String doer, String projId) {
        projectDao.updateToDoerById(doer,projId);
	}

	@Override
	public Map<String, Object> queryProjectMap(ProjectQueryReq probDoctReq) {
		return projectDao.queryProjectMap(probDoctReq);
	}



	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
	public boolean updateProjectRelationInfo(Project project) {
		Project proj = projectDao.get(project.getProjId());
		if (project==null||proj==null)return false;

		if (!project.getDesignerId().equals(proj.getDesignerId())){//设计员更改 ->更改相关信息
			boolean b = updateDesignerRelationInfo(project,proj.getDesignerId());

		}else if (!project.getBuilderId().equals(proj.getBuilderId())){//现场代表更改 ->更改相关信息
			boolean b = updateBuilderRelationInfo(project,proj.getBuilderId());
		}

		boolean isProj = this.updateProjectInfo(project,proj);//更新工程表
		boolean isCon = this.updateContractInfo(project);//更新合同信息

		try {
			boolean nc = callSynchroNC(proj.getProjId());//NC调用同步
		} catch (Exception e) {
			e.printStackTrace();
		}


		//规模修改
		Map<String, Object> mapSD = new HashMap<String, Object>();
		mapSD.put("data", project.getScaleDetails());
		mapSD.put("projId", project.getProjId());
		try {
			scaleDetailService.batMaintain(mapSD);
		}catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}


	@Override
	public Project findById(String projId) {
		return projectDao.get(projId);
	}

	@Override
	public String addDeptIdLikeQuery(String deptId) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		StringBuilder sql=new StringBuilder();
		sql.append("proj_id in(select cp.proj_id from project cp where 1=1 " );
		sql.append(" and (cp.dept_transfer like '").append(deptId).append("%'");
		String deptTransfer = loginInfo.getDeptTransfer();
		if (StringUtils.isNotBlank(deptTransfer)){
			String[] deptTransfers = deptTransfer.split(",");
			for (int i = 0; i <deptTransfers.length ; i++) {
				sql.append("or cp.dept_transfer like '").append(deptTransfers[i]).append("%'");
			}
		}
		sql.append("))");
		return sql.toString();
	}


	@Override
	public String deptIdFilterStr(String deptId) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String deptTransfer = loginInfo.getDeptTransfer();

		StringBuilder sql=new StringBuilder();
		sql.append(" (dept_transfer like '").append(deptId).append("%'");
		if (StringUtils.isNotBlank(deptTransfer)){
			String[] deptTransfers = deptTransfer.split(",");
			for (int i = 0; i <deptTransfers.length ; i++) {
				sql.append("or dept_transfer like '").append(deptTransfers[i]).append("%'");
			}
		}
		sql.append(")");
		return sql.toString();
	}



	/**
	 * @MethodDesc: NC接口调用
	 * @Author zhangnx
	 * @Date 2019/4/18 15:26
	 */
	public boolean callSynchroNC(String projId) throws Exception {
		ResultMessage resultMessage = new ResultMessage();
		int i = webserviceLogDao.getWebServiceLogListByProjIdResultType(projId, "0");
		if (i<1) return false;

		String res =iFinanceService.synProjectInfoClient(projId, null, null, null);
		JSONObject jsonbean = JSONObject.fromObject(res);
		resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
		if(resultMessage!=null && resultMessage.getRet_type().equals(ResultTypeEnum.FAIL.getValue())){
			throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
		}
		return true;
	}
	
	
	
	
	/**
	 * @MethodDesc: 修改现场代表修改相关信息
	 * @Author zhangnx
	 * @Date 2019/4/18 15:26
	 */
	public boolean updateBuilderRelationInfo(Project project,String builderId) {
		ConstructionPlan constructionPlan = constructionPlanDao.findByProjId(project.getProjId());
		if (constructionPlan!=null){
			constructionPlan.setBuilderId(project.getBuilderId());
			constructionPlan.setBuilder(project.getBudiler());
			constructionPlanDao.update(constructionPlan);
		}
		//更改未办或待办人的通知
		List<OperateRecord> operateRecordList=operateRecordDao.getOptRecordListByProjIdOrOperator(project.getProjId(),builderId);
		if (operateRecordList==null||operateRecordList.size()<1)return false;
		for (OperateRecord o:operateRecordList) {
			if (!o.getModifyStatus().equals("1")){
				o.setOperateCsr1(","+project.getBuilderId()+",");
				o.setOperater(project.getBudiler());
				operateRecordDao.update(o);
			}
		}
		return true;
	}

	
	
	/**
	 * @MethodDesc: 修改设计修改相关信息
	 * @Author zhangnx
	 * @Date 2019/4/18 15:26
	 */
	public boolean updateDesignerRelationInfo(Project project,String designerId) {

		DesignInfo designInfo = designInfoDao.queryInfoByProjId(project.getProjId());
		if (designInfo!=null) {
			designInfo.setDesignerId(project.getDesignerId());
			designInfo.setDesigner(project.getDesigner());
			designInfoDao.update(designInfo);
		}
		//更改未办或待办人的通知
		List<OperateRecord> operateRecordList=operateRecordDao.getOptRecordListByProjIdOrOperator(project.getProjId(),designerId);
		if (operateRecordList==null||operateRecordList.size()<1)return false;
		for (OperateRecord o:operateRecordList) {
			if (!o.getModifyStatus().equals("1")){
				o.setOperateCsr1(","+project.getDesignerId()+",");
				o.setOperater(project.getDesigner());
				operateRecordDao.update(o);
			}
		}
		return true;
	}

	
	
	
	/**
	 * @MethodDesc: 更新工程信息
	 * @Author zhangnx
	 * @Date 2019/4/18 15:27
	 */
	private boolean updateProjectInfo(Project project,Project proj) {
		proj.setProjName(project.getProjName());
		proj.setProjAddr(project.getProjAddr());
		//受理人
		proj.setAccepterId(project.getAccepterId());
		proj.setAccepter(project.getAccepter());
		//踏勘员
		proj.setSurveyerId(project.getSurveyerId());
		proj.setSurveyer(project.getSurveyer());
		//设计员
		proj.setDesignerId(project.getDesignerId());
		proj.setDesigner(project.getDesigner());
		//现场代表
		proj.setBuilderId(project.getBuilderId());
		proj.setBudiler(project.getBudiler());
		//客户信息
		proj.setCustId(project.getCustId());
		proj.setCustName(project.getCustName());
		proj.setCustPhone(project.getCustPhone());
		proj.setCustContact(project.getCustContact());

		proj.setProjScaleDes(getTotalDes(project)); // 总体规模描述

		List<OperateRecord> operateRecordList=operateRecordDao.queryListByProjIdAndStatus(proj.getProjId(),"2");
		if (operateRecordList!=null&&operateRecordList.size()>0){//设置待办人
			proj.setToDoer(operateRecordList.get(0).getOperater());
		}

		projectDao.update(proj);

		return true;
	}


	/**
	* @Description: 更新合同信息
	* @author zhangnx
	* @date 2019/9/11 9:43
	*/
	private boolean updateContractInfo(Project project) {
		Contract contract = contractDao.viewContractByprojId(project.getProjId());
		if (contract==null) return true;

		contract.setCustId(project.getCustId());
		contract.setCustName(project.getCustName());
		contract.setCustPhone(project.getCustPhone());
		try {
			contractDao.update(contract);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}



	
	/**
	 * @MethodDesc: 工程规模拼接
	 * @Author zhangnx
	 * @Date 2019/4/18 15:27
	 */
	public String getTotalDes(Project project) {
		List<ScaleDetail> scaleDetailList = project.getScaleDetails();
		if (scaleDetailList == null || scaleDetailList.size() < 1) return null;
		String totalDes = new String();

		StringBuffer scaleDes = new StringBuffer(project.getProjectTypeDes()).append("——"); // 总体规模描述
		for (ScaleDetail sdetail:scaleDetailList) {
			sdetail.setScaleId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
			sdetail.setProjId(project.getProjId());
			sdetail.setProjNo(project.getProjNo());

			scaleDes.append(sdetail.getProjStypeDes());
			if (sdetail.getHouseNum() != null && sdetail.getHouseNum() > 0) {
				scaleDes.append(sdetail.getHouseNum()).append("户,");
			}
			if (sdetail.getSearNum() != null && sdetail.getSearNum() > 0) {
				scaleDes.append(sdetail.getSearNum()).append("座 ,");
			}
			if (sdetail.getNum() != null && sdetail.getNum() > 0) {
				scaleDes.append(sdetail.getNum()).append("台,");
			}
			if (sdetail.getTonnage() != null && sdetail.getTonnage() > 0) {
				scaleDes.append(sdetail.getTonnage()).append("吨,");
			}

			//干线 改管
			if (sdetail.getPipeDiameter() != null) {
				scaleDes.append("管径").append(sdetail.getPipeDiameter()).append(",");
			}
			if (sdetail.getPipeLength() != null) {
				scaleDes.append("长度").append(sdetail.getPipeLength()).append("千米,");
			}
			if (sdetail.getFinishLength() != null) {
				scaleDes.append("完成").append(sdetail.getFinishLength()).append("千米,");
			}
			if (sdetail.getConstructionRatio() != null) {
				scaleDes.append("建设比例").append(sdetail.getConstructionRatio()).append(",");
			}
			if (sdetail.getGasConsumption() != null && sdetail.getGasConsumption() > 0) {
				scaleDes.append("预计用气量 ").append(sdetail.getGasConsumption()).append(" m³/h,");
			}
		}

		totalDes = totalDes + ((scaleDes.toString()).substring(0, scaleDes.toString().length() - 1)) + ";";
		return totalDes;
	}


	@Override
	public String queryProjectFlow(Project pro) {
		if(pro!=null){
			WorkFlow workFlow = workFlowService.queryWorkFlowCode(pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), WorkFlowTypeEnum.MAIN_PROGRESS.getValue());
			if(workFlow!=null){
				return workFlow.getWorkFlowCode();
			}
		}
		return null;
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String changeBudgeter(String projId, String staffId) {
		Staff staff = staffDao.get(staffId);
		Project project = projectDao.get(projId);

		String orgBudgeterAuditId = project.getBudgeterAuditId();
		String orgBudgeterAudit = project.getBudgeterAudit();


		String resultMsg="未找到工程或未找到人员！";

		if (project==null || staff==null){return resultMsg;}
		project.setBudgeterAuditId(staffId);
		project.setBudgeterAudit(staff.getStaffName());
		projectDao.update(project);

		operateRecordService.changeBudgeterTodo(projId,orgBudgeterAuditId,staffId,staff.getStaffName());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = sdf.format(new Date());
        String originalData=formatDate+"预算员由"+orgBudgeterAudit+"改为："+staff.getStaffName();
        abandonedRecordService.saveAbandonedRecord(projId,projId,"","更改预算员",originalData);

        resultMsg="预算员由【"+orgBudgeterAudit+"】改为:【"+staff.getStaffName()+"】成功！";

        return resultMsg;
	}

	@Override
	public Map<String, Object> queryProjectContractService(ProjectQueryReq queryReq) throws ParseException {
		//工程状态区间
		queryReq.setProjStuList(obtainProjStatusList(queryReq));
		
		Map<String, Object> map = projectDao.queryProjectSerivce(queryReq);
		List<Project> list = (List<Project>) map.get("data");
		//组装数据
		List<ProjectContractInfo> projConInfoList = new ArrayList<ProjectContractInfo>();
		if(list!=null && list.size()>0){
			for(Project project : list){
				//传递数据对象
				ProjectContractInfo projectContractInfo = new ProjectContractInfo();
				
				ProjectInfo projectInfo = new  ProjectInfo();
				//工程信息组装
				BeanUtil.copyNotNullProperties(projectInfo, project);
				projectContractInfo.setProject(projectInfo);
				//获取安装合同信息
				ContractInfo contractInfo = new ContractInfo();
				Contract contract = contractDao.get("projId", project.getProjId());
				if(contract!=null){
					BeanUtil.copyNotNullProperties(contractInfo,contract);
					projectContractInfo.setContract(contractInfo);
				}
				//获取补充协议信息
				List<SupplementalContractInfo> supConInfoList = this.getSupplementalContracts(project.getProjId());
				projectContractInfo.setSupplementalContract(supConInfoList);
				//获取施工合同信息
				SubContractInfo subContractInfo = new SubContractInfo();
				SubContract subCon = subContractDao.get("projId", project.getProjId());
				if(subCon!=null){
					BeanUtil.copyNotNullProperties(subContractInfo, subCon);
					projectContractInfo.setSubContract(subContractInfo);
				}
				
				projConInfoList.add(projectContractInfo);
			}
		}
		map.put("data", projConInfoList);
		return map;
	}


	private List<SupplementalContractInfo> getSupplementalContracts(
			String projId) {
		if(StringUtil.isNotBlank(projId)){
			List<SupplementalContract> supConList = supplementalContractDao.findByProjId(projId);
			if(supConList!=null && supConList.size() > 0){
				List<SupplementalContractInfo> supConInfoList = new ArrayList<SupplementalContractInfo>();
				for(SupplementalContract supplementalContract : supConList){
					SupplementalContractInfo contractInfo = new SupplementalContractInfo();
					BeanUtil.copyNotNullProperties(contractInfo,supplementalContract);
					supConInfoList.add(contractInfo);
				}
				return supConInfoList;
			}
			
		}
		return null;
	}

}


