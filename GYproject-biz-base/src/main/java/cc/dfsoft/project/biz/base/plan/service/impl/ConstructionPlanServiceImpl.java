package cc.dfsoft.project.biz.base.plan.service.impl;

import cc.dfsoft.project.biz.base.accept.enums.MaterialFlagEnum;
import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkDayDto;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.FestivalService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.budget.dao.QuantitiesDao;
import cc.dfsoft.project.biz.base.budget.entity.Quantities;
import cc.dfsoft.project.biz.base.charge.dao.AccrualsRecordDao;
import cc.dfsoft.project.biz.base.charge.dto.ChargeReq;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.enums.ARStatusEnum;
import cc.dfsoft.project.biz.base.common.dao.ReportVersionDao;
import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.service.BusinessCommunicationService;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractSuIsPrintEnum;
import cc.dfsoft.project.biz.base.contract.enums.IsIntelligentConPayEnum;
import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import cc.dfsoft.project.biz.base.messagesync.service.SynchronizedService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.enums.ConstructionPlanQueryReq;
import cc.dfsoft.project.biz.base.plan.enums.IsDispatchEnum;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dao.*;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.*;
import cc.dfsoft.project.biz.base.project.enums.*;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.impl.ManageReordServiceImpl;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.UpdateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.project.biz.ifs.material.enums.MaterialTableTypeEnum;
import cc.dfsoft.project.biz.ifs.material.service.MaterialNcService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.BeanUtil;
import cc.dfsoft.uexpress.common.util.FestivalUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ConstructionPlanServiceImpl implements ConstructionPlanService {

	@Resource
	ProjectDao projectDao;

	@Resource
	ConstructionPlanDao constructionPlanDao;

	/** 业务操作记录 */
	@Resource
	OperateRecordDao operateRecordDao;

	/** 业务操作记录服务接口 */
	@Resource
	OperateRecordService operateRecordService;
	/**
	 * 预算工程量Dao
	 */
	@Resource
	QuantitiesDao quantitiesDao;

	@Resource
	ManageRecordDao managerecorddao;

	@Resource
	ContractDao contractDao;

	@Resource
	DepartmentDao departmentDao;

	/** 材料清单Dao */
	@Resource
	AccrualsRecordDao accrualsRecordDao;
	@Resource
	SystemSetDao systemSetDao;
	@Resource
	WorkFlowService workFlowService;
	@Resource
	ProjectService projectService;

	/** 通知Dao */
	@Resource
	NoticeDao noticeDao;
	@Resource
	ProjectSignDao projectSignDao;
	@Resource
	FestivalService festivalService;

	@Resource
	DocTypeService docTypeService;

	@Resource
	ReportVersionDao reportVersionDao;

	@Resource
	BusinessPartnersDao businessPartnersDao;

	@Resource
	StaffDao staffDao;

	@Resource
	ManageReordServiceImpl manageReordServiceImpl;
	@Resource
	IFinanceInfoService financeInfoService;
	@Resource
	MaterialNcService materialNcService;
	@Resource
	BusinessCommunicationService buscomSeevice;
	@Resource
	SynchronizedService synchronizedService;
	/**
	 * 根据工程编号查询
	 *
	 * @author
	 * @createTime 2016-7-4
	 * @param ProjNo
	 * @return List<Contract>
	 */
	@Override
	public List<ConstructionPlan> findByProjNo(String ProjNo) {
		return constructionPlanDao.findByProjNo(ProjNo);
	}

	/**
	 * 保存计划
	 * @throws Exception 
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public String savePlan(ConstructionPlan constructionPlan) throws Exception {
		if (StringUtils.isNoneBlank(constructionPlan.getProjNo())) {
			if (StringUtils.isBlank(constructionPlan.getCpId()) && !constructionPlanDao.isExist(constructionPlan.getProjId())) {
				constructionPlan.setCpId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONSTRUCTIONPLAN)); // 生成主键ID
			}
			constructionPlan.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue()); // 未打印
			constructionPlan.setSuIsPrint(ContractSuIsPrintEnum.HAVE_NOT_PRINT.getValue());// 监理任务单未打印
			constructionPlan.setCuIsDispatch(IsDispatchEnum.NOT_DISPATCH.getValue());// 施工未派遣
			constructionPlan.setSuIsDispatch(IsDispatchEnum.NOT_DISPATCH.getValue());// 监理未派遣
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			String staffId = loginInfo.getStaffId();
			String staffName = loginInfo.getStaffName();
			String deptName = loginInfo.getDeptName();
			String deptId = loginInfo.getDeptId();
			Date databaseDate = constructionPlanDao.getDatabaseDate();
			constructionPlan.setCpArriveDate(databaseDate); // 编制时间
			constructionPlan.setCpDocumentDeptid(deptId); // 编制部门 ID
			constructionPlan.setCpDocumentDeptName(deptName); // 编制部门
			constructionPlan.setCpDocumenter(staffName); // 编制人
			constructionPlan.setCpDocumenterId(staffId); // 编制人ID
			if (constructionPlan.getIsinitialPayment().equals("false")) {
				StringBuffer remark = new StringBuffer("");
				constructionPlan.setRemark(remark.append(constructionPlan.getRemark()).append("--首付款未交齐").toString());
				// 标记生成未交钱工程
				ProjectSign projectSign = projectSignDao.findOnly(constructionPlan.getProjId(),
						ProjectSignTypeEnum.UNPAID.getValue());
				if (projectSign == null) {
					projectSign = new ProjectSign();
				}
				projectSign.setProjId(constructionPlan.getProjId());
				projectSign.setSignType(ProjectSignTypeEnum.UNPAID.getValue());// 未交钱工程
				projectSign.setStatus(IsSignEnum.IS_SIGN.getValue());
				projectSignDao.save(projectSign);
			}
			// 更新工程信息
			Project project = projectDao.get(constructionPlan.getProjId());
			constructionPlan.setCorpId(project.getCorpId());

			//现场代表
			if(StringUtil.isNotBlank(constructionPlan.getBuilder())){
				//更新施工预算初审、资料审核、结算初审等操作记录 查配置
				operateRecordService.updateAboutOperateRecord(project, StepEnum.PROJECT_PLAN.getValue(),constructionPlan.getBuilderId(), constructionPlan.getBuilder());
			}

			if(StringUtil.isNotBlank(constructionPlan.getCuId())){
				BusinessPartners bp=businessPartnersDao.get(constructionPlan.getCuId());
				StaffQueryReq req=new StaffQueryReq();
				req.setPost(PostTypeEnum.BUDGET_MEMBER.getValue());
				req.setDeptId(constructionPlan.getCuId());
				req.setBelongCorpId(project.getCorpId());//所属公司id
				Map<String,Object> map=staffDao.queryManageStaffList(req);
				List<Staff> staffList=(List<Staff>) map.get("data");
				String staffBudgetName=new String();
				String staffBudgetId=new String();
				if(staffList!=null && staffList.size()>0){
					if(staffList.size()>2){
						for(int i=0;i<=staffList.size()-2;i++){
							staffBudgetId=staffBudgetId+staffList.get(i).getStaffId()+",";
						}
						staffBudgetId=staffBudgetId+staffList.get(staffList.size()-1).getStaffId();
						staffBudgetName=staffBudgetName+"施工预算员";
					}else if(staffList.size()>1){
						staffBudgetId=staffBudgetId+staffList.get(0).getStaffId()+","+staffList.get(1).getStaffId()+",";
						staffBudgetName=staffBudgetName+staffList.get(0).getStaffName()+","+staffList.get(1).getStaffName();
					}else{
						staffBudgetId=staffBudgetId+staffList.get(0).getStaffId();
						staffBudgetName=staffBudgetName+staffList.get(0).getStaffName()+"";
					}
				}else{
					staffBudgetName=staffBudgetName+"施工预算员";
				}
				//更新施工预算、施工结算等操作记录 查配置
				operateRecordService.updateConAboutOperateRecord(project, StepEnum.PROJECT_PLAN.getValue(),staffBudgetId,staffBudgetName,PostTypeEnum.BUDGET_MEMBER.getValue());
			}
			
			// 保存计划
			constructionPlanDao.saveOrUpdate(constructionPlan);

			if (constructionPlan.getPlannedStartDate() != null) {
				project.setStartDate(constructionPlan.getPlannedStartDate());// 开工日期
			}
			String statusId = workFlowService.queryProjStatusId(project.getCorpId(), project.getProjectType(),
					project.getContributionMode(), WorkFlowActionEnum.PROJECT_PLAN.getActionCode(), true);
			project.setProjStatusId(statusId);
			// 清空未通过标示
			if (Constants.MODULE_CODE_CONSTRUCTIONPLAN.equals(project.getSignBack())) {
				project.setSignBack(null);
			}

			// 形成操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONSTRUCTIONPLAN); // 生成唯一ID
			String toder=operateRecordService.createOperateRecord(orId, constructionPlan.getProjId(), constructionPlan.getProjNo(),
					StepEnum.PROJECT_PLAN.getValue(), StepEnum.PROJECT_PLAN.getMessage(), "");
			project.setToDoer(toder);//待办人
			
			if(!ProjStatusEnum.TO_PLAN_AUDIT.getValue().equals(statusId)){
				String saveStatus=projectService.saveProjectPlanInfo(project.getProjId());   //审核通过将工程计划信息保存到工程表

				operateRecordService.saveOperateRecord(project.getProjId());//生成派遣通知
				//判断如果工程不需要计划审核，则调用计划审核通过后的方法
				project.setCpArriveDate(constructionPlanDao.getDatabaseDate());
				//公司出资的不存在借料的工程，在计划审核时先同步工程信息，再调用物料计划单接口 todo:
				String msg = "";
				ResultMessage resultMessage = new ResultMessage();
				if(ContributionMode.COMPANY_TYPE.contains(","+project.getContributionMode()+",") && !MaterialFlagEnum.YES.getValue().equals(project.getMaterialFlag())&&projectService.isToCall(project.getProjId(),WebServiceTypeEnum.CONTRACT_SIGN.getValue())){
					//1.同步工程信息
					msg= financeInfoService.synProjectInfoClient(project.getProjId(), FinanceOperateTypeEnum.CONTRACT_SIGN.getValue(), UpdateTypeEnum.INSERT.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
					JSONObject jsonbean = JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projectService.isSynchronization(project.getProjId(),WebServiceTypeEnum.CONTRACT_SIGN.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
				}
				if(projectService.isToCall(project.getProjId(),WebServiceTypeEnum.MATERIAL.getValue())){
					//计划审核通过，调用物料计划单接口todo:
					msg = materialNcService.synProjectInfoClient(project.getProjId(),null,MaterialTableTypeEnum.MATERIAL.getValue(),"1",UpdateTypeEnum.INSERT.getValue(),WebServiceTypeEnum.MATERIAL.getValue());
					JSONObject jsonbean = JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projectService.isSynchronization(project.getProjId(),WebServiceTypeEnum.MATERIAL.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
				}
				//调用鸿巨接口（施工任务单新增/修改）返回信息
				ResultMsg resultMsg = synchronizedService.callSynchronizedConstructionTask(project.getProjId(), WebServiceTypeEnum.CONSTRUCTION_TASK.getValue());
				if (resultMsg!=null && resultMsg.getCode()!=0){
					throw new ExpressException(resultMsg.getCode()+"",resultMsg.getMsg());
				}
			}
			// 更新工程记录
			projectDao.saveOrUpdate(project); 
						
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	/**
	 * 分包页面根据工程id查询施工计划
	 *
	 * @author
	 * @createTime 2016-7-5
	 * @param id
	 *            工程id
	 * @return ConstructionPlan 施工计划
	 */
	@Override
	public ConstructionPlan viewPlanById(String id) {
		ConstructionPlan constructionPlan = constructionPlanDao.viewPlanById(id);
		Project project = projectDao.get(id);
		if (null != constructionPlan) {
			if (StringUtils.isNotBlank(project.getCustContact())) { // 用户联系人
				constructionPlan.setCustContact(project.getCustContact());
			}
			if (StringUtils.isNotBlank(project.getCustPhone())) { // 用户联系电话
				constructionPlan.setCustPhone(project.getCustPhone());
			}

			if (StringUtils.isNotBlank(project.getDuName())) { // 设计单位
				constructionPlan.setDuName(project.getDuName());
			}
			if (StringUtils.isNotBlank(project.getDesigner())) { // 设计员
				constructionPlan.setDuDesigner(project.getDesigner());
			}
			if (StringUtils.isNotBlank(project.getProjectTypeDes())) { // 工程类型
				constructionPlan.setProjectTypeDes(project.getProjectTypeDes());
			}
			if (StringUtils.isNotBlank(project.getContributionModeDes())) { // 出资方式
				constructionPlan.setContributionModeDes(project.getContributionModeDes());
			}
			if (StringUtils.isNotBlank(project.getDeptName())) { // 部门名称
				constructionPlan.setDeptName(project.getDeptName());
			}
			if (StringUtils.isNotBlank(project.getDeptId())) { // 部门id
				constructionPlan.setDeptId(project.getDeptId());
			}
		}
		return constructionPlan;
	}

	@Override
	public Map<String, Object> queryConstructionPlan(ConstructionPlanQueryReq constructionPlanQueryReq)
			throws ParseException {
		Map<String, Object> map = constructionPlanDao.queryConstructionPlan(constructionPlanQueryReq);

		List<ConstructionPlan> list = (List) map.get("data");

		if (list != null && list.size() > 0) {
			for (ConstructionPlan cp : list) {

				List<ProjectSign> projectSignList = projectSignDao.findByProjIdAndStatus(cp.getProjId(),
						IsSignEnum.IS_SIGN.getValue());
				if (projectSignList != null && projectSignList.size() > 0) {
					cp.setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());// 是特殊工程
				}

				Project pro = projectDao.get(cp.getProjId());
				if (pro != null) {
					cp.setProjectType(pro.getProjectType());
					cp.setProjStatusId(pro.getProjStatusId());
				}
				if (StringUtils.isNotBlank(cp.getCuIsDispatch())
						&& cp.getCuIsDispatch().equals(IsDispatchEnum.IS_DISPATCH.getValue())) {
					cp.setCuIsDispatch(IsDispatchEnum.IS_DISPATCH.getMessage());
				} else {
					cp.setCuIsDispatch(IsDispatchEnum.NOT_DISPATCH.getMessage());
				}
				if (StringUtils.isNotBlank(cp.getSuIsDispatch())
						&& cp.getSuIsDispatch().equals(IsDispatchEnum.IS_DISPATCH.getValue())) {
					cp.setSuIsDispatch(IsDispatchEnum.IS_DISPATCH.getMessage());
				} else {
					cp.setSuIsDispatch(IsDispatchEnum.NOT_DISPATCH.getMessage());
				}
			}
		}

		return map;
	}

	private Map<String, Object> queryConstrutPlanByTime(ConstructionPlanQueryReq constructionPlanQueryReq)
			throws ParseException {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		// SystemSet systemSet=systemSetDao.get("stepId", req.getStepId());
		SystemSet systemSet = systemSetDao.querySystemSetByStepId(constructionPlanQueryReq.getStepId(),
				loginInfo.getCorpId());
		if (systemSet != null && StringUtil.isNotBlank(systemSet.getTimeSpan())) {
			constructionPlanQueryReq.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		}
		// 最后一个操作记录的工程记录列表（符合状态的当前工程）
		List<Map<String, Object>> ors = operateRecordDao.getOptRecordByTime(constructionPlanQueryReq.getProjStatus());
		Map<String, Object> map = constructionPlanDao.queryConstructionPlan(constructionPlanQueryReq);
		// 符合当前状态的工程列表
		List<ConstructionPlan> list = (List<ConstructionPlan>) map.get("data");
		List<ConstructionPlan> listNew = new ArrayList<ConstructionPlan>();
		// 时间限制（单位天）
		Integer timel = constructionPlanQueryReq.getTimeLimit() != null ? constructionPlanQueryReq.getTimeLimit() : 0;
		long secondsLimit = -1l;
		if (timel != null) {
			secondsLimit = timel * 24 * 60 * 60;
		}
		for (ConstructionPlan conp : list) {
			for (Map<String, Object> or : ors) {
				if (or.get("PROJ_ID").equals(conp.getProjId())) {
					// 业务操作记录中时间
					Date oldTime = (Date) or.get("OPERATE_TIME");
					// 当前时间
					Date nowTime = projectDao.getDatabaseDate();
					// 获取两个日期之间的工作日天数
					long workDays = 0;
					try {
						workDays = FestivalUtil.calLeaveDays(oldTime, nowTime,
								festivalService.getCacheMap(Constants.WORKDAYS),
								festivalService.getCacheMap(Constants.HOLIDAYS));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					long seconds = workDays * 24 * 60 * 60;
					// 用于时限进度展示
					WorkDayDto workDayDto = new WorkDayDto();
					workDayDto.setDaysLimit(String.valueOf(timel));
					workDayDto.setWorkDays(String.valueOf(workDays));
					workDayDto.setHaveDays(String.valueOf(timel - workDays));
					conp.setWorkDayDto(workDayDto);
					// 如果当前时间-上个步骤的操作时间大于时间限制段则为超时
					if (secondsLimit > 0 && seconds > secondsLimit) {
						conp.setOverdue(true);
						continue;
					}
				}
			}

			Project pro = projectDao.get(conp.getProjId());
			if (pro != null) {
				conp.setProjectType(pro.getProjectType());
			}

			listNew.add(conp);

		}

		map.put("data", listNew);
		return map;
	}



	@Override
	public Map<String, Object> queryConstructionPlanForAudit(ConstructionPlanQueryReq constructionPlanQueryReq)
			throws ParseException {
		Map<String, Object> result = this.queryConstrutPlanByTime(constructionPlanQueryReq);
		List<ConstructionPlan> data = (List<ConstructionPlan>) result.get("data");

		DocType docType = new DocType();
		String grade = "";
		if (data != null && data.size() > 0) {
			/**
			 * -1 未审核 0 审核未通过 1 审核通过 2待审核 若该
			 * 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0",
			 * "level3":"2"};
			 */
			// 遍历循环 设置审核级别
			for (int i = 0; i < data.size(); i++) {

				// 查询所有的有效的
				List<ProjectSign> projectSignList = projectSignDao.findByProjIdAndStatus(data.get(i).getProjId(),
						IsSignEnum.IS_SIGN.getValue());

				if (projectSignList != null && projectSignList.size() > 0) {
					data.get(i).setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());// 是特殊工程
				}

				Project pro = projectDao.get(data.get(i).getProjId());
				docType = docTypeService.findByStepId(StepEnum.PROJECT_PLAN_AUDIT.getValue(), pro.getCorpId(),
						pro.getProjectType(), pro.getContributionMode());
				if (docType != null && StringUtils.isNotBlank(docType.getGrade())) {
					grade = docType.getGrade();
				} else {
					grade = "0";
				}
				data.get(i).setLevel(grade);// 设置审核总级数（合同审核2级审核）

				//已交钱-》有配置：审核级=单据级-配置数
				grade = manageReordServiceImpl.paidReduceLevel(pro, data.get(i).getIsinitialPayment(), constructionPlanQueryReq.getMenuId(), grade);

				data.get(i).setLevel(grade); // 设置审核总级数（勘察信息3级审核）
				Map<String, String> levelBtn = new HashMap();
				// 以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for (int n = 1; n < Integer.parseInt(grade) + 1; n++) {
					if (n == 1) {
						levelBtn.put("level" + n, "2");// 待审
					} else {
						levelBtn.put("level" + n, "-1");// 未审
					}
				}
				ManageRecordQueryReq mrq = new ManageRecordQueryReq();
				mrq.setBusinessOrderId(data.get(i).getCpId());
				mrq.setStepId(StepEnum.PROJECT_PLAN_AUDIT.getValue());
				mrq.setProjId(data.get(i).getProjId());
				// List<ManageRecord> mrls = (List<ManageRecord>)
				// managerecorddao.queryManageRecord(mrq).get("data");


				List<ManageRecord> mrls = managerecorddao.findByStepIdProjIdIsPass(data.get(i).getProjId(), StepEnum.PROJECT_PLAN_AUDIT.getValue(), MrResultEnum.PASSED.getValue());
				if (mrls != null && mrls.size() > 0) {
					// 遍历循环，获取审核是否通过
					for (ManageRecord mr : mrls) {
						levelBtn.put("level" + mr.getMrAuditLevel(), mr.getMrResult());
					}
					if (mrls.size() < Integer.parseInt(grade)) {
						levelBtn.put("level" + (mrls.size() + 1), "2");
					}
				}
				//某个人配置的某个公司下的级别不能审核（stffId_corpId_menuId）
				LoginInfo loginInfo = SessionUtil.getLoginInfo();
				DataFilerSetUpDto dfsud = Constants.isConfig(loginInfo.getStaffId() + "_" +data.get(i).getCorpId() + "_"+constructionPlanQueryReq.getMenuId());
				if (dfsud!=null){
					if (StringUtils.isNotBlank(dfsud.getSupSql())){
						if((mrls != null && mrls.size() > 0) && mrls.size()>=Integer.parseInt(dfsud.getSupSql())){
							levelBtn.put("level" +dfsud.getSupSql(), "1");
						}else {
							levelBtn.put("level" +dfsud.getSupSql(), "-1");
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
	public ConstructionPlan detail(String cpId) {
		ConstructionPlan constructionPlan = constructionPlanDao.get(cpId);
		if (null != constructionPlan && StringUtils.isNotBlank(constructionPlan.getProjId())) {
			Project project = projectService.viewProject(constructionPlan.getProjId());
			if (null != project) {
				if (StringUtils.isNotBlank(project.getCustContact())) { // 用户联系人
					constructionPlan.setCustContact(project.getCustContact());
				}
				if (StringUtils.isNotBlank(project.getCustPhone())) { // 用户联系电话
					constructionPlan.setCustPhone(project.getCustPhone());
				}

				if (StringUtils.isNotBlank(project.getDuName())) { // 设计单位
					constructionPlan.setDuName(project.getDuName());
				}
				if (StringUtils.isNotBlank(project.getDesigner())) { // 设计员
					constructionPlan.setDuDesigner(project.getDesigner());
				}
				if (StringUtils.isNotBlank(project.getProjectTypeDes())) { // 工程类型
					constructionPlan.setProjectTypeDes(project.getProjectTypeDes());
				}
				if (StringUtils.isNotBlank(project.getContributionModeDes())) { // 出资方式
					constructionPlan.setContributionModeDes(project.getContributionModeDes());
				}
				if (StringUtils.isNotBlank(project.getDeptName())) { // 部门名称
					constructionPlan.setDeptName(project.getDeptName());
				}
				if (StringUtils.isNotBlank(project.getCorpName())) { // 分公司名称
					constructionPlan.setCorpName(project.getCorpName());
				}
			}
		}
		return constructionPlan;
	}

	@Override
	public List<Map<String, Object>> countProjectNum(String name) {
		return constructionPlanDao.countProjectNum(name);

	}

	/**
	 * 根据工程编号获取施工计划编制显示对象
	 *
	 * @param projID
	 * @return
	 */
	// @Override
	// public ConstructionPlanDTO converDTO(String projID){
	// Contract contract = contractDao.viewContractByprojId( projID);
	//
	// ConstructionPlanDTO dto = ConstructionPlanDTOConverter.convert(contract);
	// if (dto == null ) {
	// return null;
	// }
	// Project project = projectDao.get(projID);
	// if (StringUtils.isNotBlank(project.getCustContact())) { //用户联系人
	// dto.setCustContact(project.getCustContact());
	// }
	// if (StringUtils.isNotBlank(project.getCustPhone())) { //用户联系电话
	// dto.setCustPhone(project.getCustPhone());
	// }
	//
	// if (StringUtils.isNotBlank(project.getBudgeter())) { // 预算员
	// dto.setCostBudgeter(project.getBudgeter());
	// }
	// if (StringUtils.isNotBlank(project.getCostMember())) { // 造价员
	// dto.setCostMember(project.getCostMember());
	// }
	// if (StringUtils.isNotBlank(project.getDuName())) { // 设计单位
	// dto.setDuName(project.getDuName());
	// }
	// if (StringUtils.isNotBlank(project.getDesigner())) { // 设计员
	// dto.setDuDesigner(project.getDesigner());
	// }
	// //设计单位负责人
	// contract.setDuDirector(departmentDao.get(project.getDuId()).getPrincipal());
	//
	// return dto;
	// }

	/**
	 * 根据工程编号获取施工计划编制显示对象
	 *
	 * @param projID
	 * @return
	 */
	@Override
	public ConstructionPlan converDTO(String projID) {

		ConstructionPlan constructionPlan = constructionPlanDao.viewPlanById(projID);
		Contract contract = contractDao.viewContractByprojId(projID);
		List<DataFilerSetUpDto> builderList = Constants.getDataFilterMapByKey(SessionUtil.getLoginInfo().getCorpId()+"_"+"110501");  //根据条件查询是否有配置
		ChargeReq chargeReq = new ChargeReq();
		Project project = projectDao.get(projID);
		Department department = departmentDao.get(project.getDeptId());
		chargeReq.setArAtatus(ARStatusEnum.ALREADY_CHARGE.getValue());
		chargeReq.setArType(CollectionTypeEnum.INITIAL_PAYMENT.getValue());
		if (null != constructionPlan) {
			chargeReq.setProjId(constructionPlan.getProjId());
		} else {
			constructionPlan = new ConstructionPlan();
			constructionPlan.setProjId(project.getProjId());
			constructionPlan.setProjNo(project.getProjNo());
			constructionPlan.setProjName(project.getProjName());
			constructionPlan.setProjAddr(project.getProjAddr());
			constructionPlan.setProjScaleDes(project.getProjScaleDes());
			if (null != contract) {
				constructionPlan.setContractAmount(contract.getContractAmount());
				constructionPlan.setProjTimeLimit(contract.getTimeLimit());
				constructionPlan.setFirstPayment(contract.getFirstPayment());
				chargeReq.setProjId(contract.getProjId());
			}
			if(builderList != null && builderList.size() >0){  //若有配置，则现场代表默认是踏勘员
				constructionPlan.setBuilderId(project.getSurveyerId());
				constructionPlan.setBuilder(project.getSurveyer());
			}
		}

		if (StringUtils.isNotBlank(project.getCustContact())) { // 用户联系人
			constructionPlan.setCustContact(project.getCustContact());
		}
		if (StringUtils.isNotBlank(project.getCustPhone())) { // 用户联系电话
			constructionPlan.setCustPhone(project.getCustPhone());
		}

		if (StringUtils.isNotBlank(project.getDuName())) { // 设计单位
			constructionPlan.setDuName(project.getDuName());
		}
		if (StringUtils.isNotBlank(project.getDesigner())) { // 设计员
			constructionPlan.setDuDesigner(project.getDesigner());
		}
		if (StringUtils.isNotBlank(project.getProjectTypeDes())) { // 工程类型
			constructionPlan.setProjectTypeDes(project.getProjectTypeDes());
		}
		if (StringUtils.isNotBlank(project.getContributionModeDes())) { // 出资方式
			constructionPlan.setContributionModeDes(project.getContributionModeDes());
		}
		if (StringUtils.isNotBlank(project.getDeptName())) { // 部门名称
			constructionPlan.setDeptName(project.getDeptName());
		}
		if (StringUtils.isNotBlank(project.getDeptId())) { // 部门id
			constructionPlan.setDeptId(project.getDeptId());
		}

		constructionPlan.setDeptDivide(department.getDeptDivide());
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
			constructionPlan.setDownPayment(amount);
			constructionPlan.setIsinitialPayment(isPay);
		}
		return constructionPlan;
	}

	@Override
	public void saveNotePlan(ConstructionPlan constructionPlan) {
		constructionPlanDao.saveOrUpdate(constructionPlan);
	}

	@Override
	public ConstructionPlan findByProjId(String id) {
		ConstructionPlan plan = constructionPlanDao.viewPlanById(id);
		if (plan != null) {
			Quantities quan = quantitiesDao.findByprojId(plan.getProjId());
			if (quan != null) {
				plan.setQuAmount(quan.getQuAmount());
			}
		}
		return plan;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveConstructionDispatch(ConstructionPlan constructionPlan)
			throws HibernateOptimisticLockingFailureException {
		if (StringUtils.isNotBlank(constructionPlan.getCuPmId())) {
			constructionPlan.setCuIsDispatch(IsDispatchEnum.IS_DISPATCH.getValue());
		}
		if (StringUtils.isNotBlank(constructionPlan.getSuJgjId())) {
			constructionPlan.setSuIsDispatch(IsDispatchEnum.IS_DISPATCH.getValue());
		}
		Project project = projectDao.get(constructionPlan.getProjId());

		if("110507".equals(constructionPlan.getMenuId())){
			if(StringUtils.isNotBlank(constructionPlan.getManagementQaeId())){
				//将开工报告、自检的待办人改为施工员
				operateRecordService.updateConAboutOperateRecord(project, constructionPlan.getMenuId(), constructionPlan.getManagementQaeId(), constructionPlan.getManagementQae(),PostTypeEnum.CONSTRUCTION.getValue());
			}
			if(StringUtils.isNotBlank(constructionPlan.getCuPmId())){
				//将设置的步骤待办人改为项目经理
				operateRecordService.updateConAboutOperateRecord(project, constructionPlan.getMenuId(), constructionPlan.getCuPmId(), constructionPlan.getCuPm(),PostTypeEnum.CU_PM.getValue());
			}

			//资料员
			if(StringUtils.isNotBlank(constructionPlan.getDocumenterId())){
				//将设置的步骤待办人改为资料员
				operateRecordService.updateConAboutOperateRecord(project, constructionPlan.getMenuId(), constructionPlan.getDocumenterId(), constructionPlan.getDocumenter(),PostTypeEnum.BUSINESSASSISTANT.getValue());
			}


		}else if("110508".equals(constructionPlan.getMenuId())){
			if(StringUtils.isNotBlank(constructionPlan.getSuJgjId())){
				//预验收、募投初审一级
				operateRecordService.updateConAboutOperateRecord(project, constructionPlan.getMenuId(), constructionPlan.getSuJgjId(), constructionPlan.getSuJgj(),PostTypeEnum.SUJGJ.getValue());
			}
			if(StringUtils.isNotBlank(constructionPlan.getSuCseId())){
				//募投初审二级
				operateRecordService.updateConAboutOperateRecord(project, constructionPlan.getMenuId(), constructionPlan.getSuCseId(), constructionPlan.getSuCse(),PostTypeEnum.SUCSE.getValue());
			}
		}
		//避免页面有些字段没有传递，先取计划信息，再更新
		ConstructionPlan constructionPlan2 = constructionPlanDao.get("projId", constructionPlan.getProjId());
		if (constructionPlan2!=null){
			String origsuJgjId = constructionPlan2.getSuJgjId();//原来的监理
			String currSuJgjId = constructionPlan.getSuJgjId();//现任监理

			if (StringUtils.isNotBlank(currSuJgjId) && !currSuJgjId.equals(origsuJgjId)){//监理改派时更新业务沟通
				buscomSeevice.updateBuscomNotice(constructionPlan.getProjId(),origsuJgjId,currSuJgjId,constructionPlan.getSuJgj());
			}
		}

		BeanUtil.copyNotNullProperties(constructionPlan2, constructionPlan);
		constructionPlanDao.update(constructionPlan2);

		if (project != null) {
			// 生成通知记录
			Notice notice = new Notice();
			notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONSTRUCTIONPLAN));
			notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue()); // 将通知置为有效;
			notice.setNoticeTime(noticeDao.getDatabaseDate());
			notice.setNoticeTitle("派遣通知");
			notice.setNoticeContent(project.getProjName() + "工程已下单监理任务给您,请及时查看");
			notice.setProjId(project.getProjId());
			notice.setCorpId(project.getCorpId());
			noticeDao.saveOrUpdate(notice);

			//更新操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONSTRUCTIONPLAN);
			if (constructionPlan.getMenuId().equals("110507")) {
				operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), StepEnum.CONSTRUCTION_DISPATCH.getValue(), StepEnum.CONSTRUCTION_DISPATCH.getMessage(), "");
			}else if (constructionPlan.getMenuId().equals("110508")){
				operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), StepEnum.SUPERVISION_DISPATCH.getValue(), StepEnum.SUPERVISION_DISPATCH.getMessage(), "");
			}
		}

		//调用鸿巨接口（施工任务单新增/修改）返回信息
		ResultMsg resultMsg = synchronizedService.callSynchronizedConstructionTask(project.getProjId(), WebServiceTypeEnum.CONSTRUCTION_TASK.getValue());
		if (resultMsg!=null && resultMsg.getCode()!=0){
			throw new ExpressException(resultMsg.getCode()+"",resultMsg.getMsg());
		}

		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void signConstructionTaskPrint(String projId) {
		ConstructionPlan constructionPlan = constructionPlanDao.viewPlanById(projId);
		if (constructionPlan != null) {
			// 标记已打印
			constructionPlan.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			constructionPlanDao.update(constructionPlan);
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void signSupervisorTaskPrint(String projId) {
		ConstructionPlan constructionPlan = constructionPlanDao.viewPlanById(projId);
		if (constructionPlan != null) {
			// 标记已打印
			constructionPlan.setSuIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			constructionPlanDao.update(constructionPlan);
		}

	}

	@Override
	public String findPrintDataByProjId(String projId, String type) {
		String result = "";
		String rvId = "";
		// 根据工程ID查询任务单信息
		ConstructionPlan constructionPlan = constructionPlanDao.findByProjId(projId);
		// 任务单报表
		String arrayStr = CompletionDataPrintEnum.CON_TASK.getCptUrl();
		// 2、使用JSONArray
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(arrayStr);
		if (jsonArray != null && jsonArray.size() > 0 && constructionPlan != null) {
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(jsonArray.get(0));
			CompletionDataPrintDto dto = (CompletionDataPrintDto) net.sf.json.JSONObject.toBean(jsonObject,
					CompletionDataPrintDto.class);
			// 根据公司ID、菜单ID去查找cpt文件名
			String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
			String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单
			ReportVersionReq reportVersionReq = new ReportVersionReq();  //创建实体
			if(StringUtils.isNotBlank(menuId) && StringUtils.isNotBlank(constructionPlan.getCorpId())){
				reportVersionReq.setCorpId(constructionPlan.getCorpId());
				reportVersionReq.setMenuId(menuId);
			}
			try {
				List<ReportVersion> versions = reportVersionDao.queryReportVersions(reportVersionReq);  //根据实体reportVersionReq查找报表版本
				rvId = versions.get(0).getRvId();
				String key = constructionPlan.getCorpId()+"_"+menuId+"_"+rvId;   //组装key值进行报表查找
				Object obj = Constants.getSysConfigByKey(key);
				if(obj !=null){
					int beginIndex = dto.getReportlet().indexOf("/");
					int endIndex = dto.getReportlet().lastIndexOf("/");
					String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
					dto.setReportlet(reportlet+obj.toString());
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = "{reportlet:'" + dto.getReportlet() + "',planId:'" + constructionPlan.getCpId();
			// +"',loginName:'"+constructionPlan.getProjId()
			// +"',loginPhone:'" + constructionPlan.getProjId();
			result += "'}";
			return result;
		}
		return null;
	}

	/**
	 * 查询可付款申请工程
	 *
	 * @author fuliwei
	 * @createTime 2018年6月11日
	 * @param
	 * @return
	 */
	@Override
	public Map<String, Object> queryPayProject(PaymentApplyReq paymentApplyReq) {
		/*
		 * Map<String, Object>
		 * conMap=constructionPlanDao.queryPayProject(paymentApplyReq);
		 * List<ConstructionPlan> recordList=(List<ConstructionPlan>)
		 * conMap.get("data"); Map<String, Object> map = new HashMap<String,
		 * Object>(); map.put("data", recordList); map.put("recordsFiltered",
		 * recordList.size()); map.put("recordsTotal",recordList.size());
		 */
		return constructionPlanDao.queryPayProject(paymentApplyReq);
	}

	/**
	 * 保存报审手续
	 * @author fuliwei
	 * @date 2018年11月14日
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveConProc(SubBudget subBudget) {
		Project pro= projectDao.get(subBudget.getProjId());
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.CONSTRUCTION_PROC.getActionCode(), true);
		pro.setProjStatusId(statusId);								   //报审手续登记

		pro.setConstructionProcRemark(subBudget.getRemark());		  //报审手续登记

		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN);//生成唯一ID
		String toder=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(),StepEnum.CONSTRUCTION_PROC.getValue(), StepEnum.CONSTRUCTION_PROC.getMessage(), "");
		pro.setToDoer(toder);//待办人
		projectDao.update(pro);
	}

	@Override
	public Staff findCuBudgeter(String cuId,String corpId ) {
		Staff staff = new Staff();
		StaffQueryReq req=new StaffQueryReq();
		req.setPost(PostTypeEnum.BUDGET_MEMBER.getValue());
		req.setDeptId(cuId);
		req.setBelongCorpId(corpId);//所属公司id
		Map<String,Object> map=staffDao.queryManageStaffList(req);
		List<Staff> staffList=(List<Staff>) map.get("data");
		String staffBudgetName=new String();
		String staffBudgetId=new String();
		if(staffList!=null && staffList.size()>0){
			if(staffList.size()>2){
				for(int i=0;i<=staffList.size()-2;i++){
					staffBudgetId=staffBudgetId+staffList.get(i).getStaffId()+",";
				}
				staffBudgetId=staffBudgetId+staffList.get(staffList.size()-1).getStaffId();
				staffBudgetName=staffBudgetName+"施工预算员";
			}else if(staffList.size()>1){
				staffBudgetId=staffBudgetId+staffList.get(0).getStaffId()+","+staffList.get(1).getStaffId()+",";
				staffBudgetName=staffBudgetName+staffList.get(0).getStaffName()+","+staffList.get(1).getStaffName();
			}else{
				staffBudgetId=staffBudgetId+staffList.get(0).getStaffId();
				staffBudgetName=staffBudgetName+staffList.get(0).getStaffName()+"";
			}
		}else{
			staffBudgetName=staffBudgetName+"施工预算员";
		}
		staff.setStaffId(staffBudgetId);
		staff.setStaffName(staffBudgetName);
		return staff;
	}
	/**
	 * 查询监理单位资料员
	 */
	@Override
	public Staff findbusUnitOperator(String busUnitId,String corpId, String post) {
		Staff staff = new Staff();
		StaffQueryReq req=new StaffQueryReq();
		req.setPost(post);
		req.setDeptId(busUnitId);
		req.setBelongCorpId(corpId);//所属公司id
		Map<String,Object> map=staffDao.queryManageStaffList(req);
		List<Staff> staffList=(List<Staff>) map.get("data");
		String staffBudgetName=new String();
		String staffBudgetId=new String();
		if(staffList!=null && staffList.size()>0){
			if(staffList.size()>2){
				for(int i=0;i<=staffList.size()-2;i++){
					staffBudgetId=staffBudgetId+staffList.get(i).getStaffId()+",";
				}
				staffBudgetId=staffBudgetId+staffList.get(staffList.size()-1).getStaffId();
				staffBudgetName=staffBudgetName+"操作人";
			}else if(staffList.size()>1){
				staffBudgetId=staffBudgetId+staffList.get(0).getStaffId()+","+staffList.get(1).getStaffId()+",";
				staffBudgetName=staffBudgetName+staffList.get(0).getStaffName()+","+staffList.get(1).getStaffName();
			}else{
				staffBudgetId=staffBudgetId+staffList.get(0).getStaffId();
				staffBudgetName=staffBudgetName+staffList.get(0).getStaffName()+"";
			}
		}else{
			staffBudgetName=staffBudgetName+"操作人";
		}
		staff.setStaffId(staffBudgetId);
		staff.setStaffName(staffBudgetName);
		return staff;
	}
}
