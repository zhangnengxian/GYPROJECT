package cc.dfsoft.project.biz.base.project.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.entity.WorkFlow;
import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.common.dao.MenuStepDao;
import cc.dfsoft.project.biz.base.common.entity.MenuStep;
import cc.dfsoft.project.biz.base.complete.service.DataAcceptanceService;
import cc.dfsoft.project.biz.base.complete.service.JointAcceptanceService;
import cc.dfsoft.project.biz.base.complete.service.PreinspectionService;
import cc.dfsoft.project.biz.base.complete.service.SelfInspectionListService;
import cc.dfsoft.project.biz.base.constructmanage.dao.BusinessCommunicationDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.ConstructionOrganizationDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.ConstructionWorkDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.WorkReportDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.BusinessCommunicationReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.*;
import cc.dfsoft.project.biz.base.constructmanage.enums.BcSendTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.BcStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.CompleteReportService;
import cc.dfsoft.project.biz.base.constructmanage.service.ConstructionOrganizationService;
import cc.dfsoft.project.biz.base.constructmanage.service.ConstructionWorkService;
import cc.dfsoft.project.biz.base.constructmanage.service.WorkReportService;
import cc.dfsoft.project.biz.base.contract.enums.ModifyStateEnum;
import cc.dfsoft.project.biz.base.contract.enums.ModifyStatusEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.design.service.SurveyInfoService;
import cc.dfsoft.project.biz.base.inspection.dao.StrengthTestDao;
import cc.dfsoft.project.biz.base.inspection.entity.StrengthTest;
import cc.dfsoft.project.biz.base.maintain.entity.AbandonedRecord;
import cc.dfsoft.project.biz.base.maintain.entity.ResultInfo;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dao.*;
import cc.dfsoft.project.biz.base.project.dto.OperateRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.OperateWorkFlowReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.*;
import cc.dfsoft.project.biz.base.project.enums.OperateWorkFlowEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.enums.StepLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.settlement.service.SettlementDeclarationService;
import cc.dfsoft.project.biz.base.subpackage.service.SubBudgetService;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractService;
import cc.dfsoft.uexpress.biz.sys.auth.dao.MenuDao;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Menu;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffDto;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 操作记录服务接口实现
 * @author pengtt
 * @createTime 2016-06-28
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class OperateRecordServiceImpl implements OperateRecordService {

	/**
	 * 操作记录dao
	 */
	@Resource
	OperateRecordDao operateRecordDao;
	/**
	 * 延期信息dao
	 */
	@Resource
	ApplyDelayDao applyDelayDao;

	@Resource
	DesignInfoDao designInfoDao;

	@Resource
	ConstructionPlanDao constructionPlanDao;
	@Resource
	StaffDao staffDao;
	@Resource
	WorkReportDao workReportDao;
	/**
	 * 工程Dao
	 */
	@Resource
	ProjectDao projectDao;

	/**
	 * 工作流
	 */
	@Resource
	WorkFlowService workFlowService;

	/**
	 * 操作记录标准
	 */
	@Resource
	OperateWorkFlowDao operateWorkFlowDao;
	/**
	 * 审核设置
	 */
	@Resource
	DocTypeService docTypeService;

	/**
	 * 菜单步骤设置表
	 */
	@Resource
	MenuStepDao menuStepDao;

	/**
	 * 菜单dao
	 */
	@Resource
	MenuDao menuDao;

	@Resource
	ManageRecordDao manageRecordDao;

	@Resource
	BusinessCommunicationDao businessCommunicationDao;
	@Resource
	ConstructionWorkDao constructionWorkDao;
	@Resource
	ConstructionOrganizationDao constructionOrganizationDao;
	@Resource
	StrengthTestDao strengthTestDao;
	@Resource
	ConstructionPlanService constructionPlanService;

	@Resource
	SubBudgetService subBudgetService;
	@Resource
	SubContractService subContractService;
	@Resource
	ConstructionWorkService constructionWorkService;
	@Resource
	ConstructionOrganizationService constructionOrganizationService;
	@Resource
	WorkReportService workReportService;
	@Resource
	DocTypeDao docTypeDao;
    @Resource
    ContractService contractService;
    @Resource
    DesignInfoService designInfoService;
    @Resource
    BudgetService budgetService;
    @Resource
    SettlementDeclarationService settlementDeclarationService;
	@Resource
	SelfInspectionListService selfInspectionListService;
	@Resource
	PreinspectionService preinspectionService;
	@Resource
	DataAcceptanceService dataAcceptanceService;
	@Resource
	JointAcceptanceService jointAcceptanceService;
	@Resource
	CompleteReportService completeReportService;
	@Resource
	AbandonedRecordService abandonedRecordService;

	/**
	 * 操作记录
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String createOperateRecord(String orId, String projId, String projNo, String stepId, String stepName, String remark) {
		//操作记录调用方法，将当前的置为已办，将下一步骤置为待办
		Boolean nextOrLastStepId = true;
		if (StringUtil.isBlank(remark) || "true".equals(remark)) {
			nextOrLastStepId = true;
		} else {
			nextOrLastStepId = false;
		}

		//取得登录人信息
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		//将当前的置为已办
		OperateRecordQueryReq req = new OperateRecordQueryReq();
		req.setProjId(projId);
		req.setStepId(stepId);
		req.setIsValid("1");    //有效
		//req.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());//待办
		Map<String, Object> map = operateRecordDao.queryOperateRecordList(req);
		List<OperateRecord> list = (List<OperateRecord>) map.get("data");
		Project pro = projectDao.get(projId);

		LoginInfo login = SessionUtil.getLoginInfo();
		if (list != null && list.size() > 0) {//有操作记录标准，则生成记录
			for (OperateRecord o : list) {
				if (OperateWorkFlowEnum.WAIT_DONE.getValue().equals(o.getModifyStatus())) {
					o.setOperater(login.getStaffName());
					o.setOperateCsr1("," + login.getStaffId() + ",");
					o.setOperateDept1(login.getDeptId());
					o.setOperateTime(operateRecordDao.getDatabaseDate());
					o.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
					operateRecordDao.saveOrUpdate(o);
				}
			}

		} else {
			//走原来生成业务操作记录的方法
			OperateRecord operateRecord = new OperateRecord();
			operateRecord.setOrId(orId);
			operateRecord.setProjId(projId);
			operateRecord.setProjNo(projNo);
			operateRecord.setStepId(stepId);
			operateRecord.setStepName(stepName);
			operateRecord.setRemark(remark);
			operateRecord.setOperateTime(operateRecordDao.getDatabaseDate());
			operateRecord.setOperateCsr1("," + loginInfo.getStaffId() + ",");
			operateRecord.setOperater(loginInfo != null ? loginInfo.getStaffName() : "1");
			operateRecord.setOperateDept1(loginInfo != null ? loginInfo.getDeptId() : "1");
			operateRecord.setIsValid("1");//有效记录
			operateRecord.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
			operateRecord.setCorpId(pro.getCorpId());
			operateRecord.setProjectType(pro.getProjectType());
			operateRecord.setContributionMode(pro.getContributionMode());
			operateRecord.setOpType(WorkFlowTypeEnum.MAIN_PROGRESS.getValue());
			operateRecordDao.save(operateRecord);
		}

		//判断是否有配置 更新其他历史记录 如安顺受理申请，需更新用户回复等步骤 前面不加0_
		List<DataFilerSetUpDto> listData = Constants.getDataFilterMapByKey(login.getCorpId() + "_" + pro.getProjectType() + "_" + pro.getContributionMode() + "_" + stepId + "_0");
		if (listData != null && listData.size() > 0) {
			if (StringUtils.isNotBlank(listData.get(0).getSupSql())) {
				String[] stepIds = listData.get(0).getSupSql().split(",");
				for (int i = 0; i < stepIds.length; i++) {
					if (stepIds[i].indexOf("_") != -1) {
						//包含审核级别
						String grade = stepIds[i].substring(stepIds[i].length() - 1, stepIds[i].length());            //取最后一位审核级别
						String stepAuditId = stepIds[i].substring(0, stepIds[i].length() - 2);
						OperateRecord otherOr = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepAuditId, grade, OperateWorkFlowEnum.NOT_DONE.getValue());
						if (otherOr != null) {
							otherOr.setOperateCsr1("," + loginInfo.getStaffId() + ",");
							otherOr.setOperater(loginInfo != null ? loginInfo.getStaffName() : "1");
							operateRecordDao.saveOrUpdate(otherOr);
						}
					} else {
						OperateRecord otherOr = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepIds[i], null, OperateWorkFlowEnum.NOT_DONE.getValue());
						//查询操作记录
						if (otherOr != null) {
							otherOr.setOperateCsr1("," + loginInfo.getStaffId() + ",");
							otherOr.setOperater(loginInfo != null ? loginInfo.getStaffName() : "1");
							operateRecordDao.saveOrUpdate(otherOr);
						}
					}

				}
			}
		}

		//用最初的stepId 查询他的下一个stepId;
		String nextStepId = workFlowService.queryAssistProgressStatusId(pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepId, nextOrLastStepId, WorkFlowTypeEnum.MAIN_PROGRESS.getValue(), null);
		if (nextOrLastStepId) {
			//下一个置为待办
			req.setStepId(nextStepId);
			//req.setModifyStatus(OperateWorkFlowEnum.NOT_DONE.getValue());
			Map<String, Object> nextMap = operateRecordDao.queryOperateRecordList(req);
			List<OperateRecord> nextList = (List<OperateRecord>) nextMap.get("data");

			if (nextList != null && nextList.size() > 0) {
				for (OperateRecord ofr : nextList) {
					//如果下一步骤为审核步骤
					if (StringUtil.isNotBlank(ofr.getGrade())) {
						//将第一级审核置为待办
						//用corp_id,project_type,contribution_mode,step_id,grade,isValid查询第一级审核
						OperateRecord auditOperateRecord = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), nextStepId, "1", OperateWorkFlowEnum.NOT_DONE.getValue());
						if (auditOperateRecord != null) {
							auditOperateRecord.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());//审核一级置为待办
							operateRecordDao.saveOrUpdate(auditOperateRecord);
							return auditOperateRecord.getOperater();
						}
						return "";
					} else {
						//下一步骤不是审核步骤 直接置为待办
						ofr.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());
						operateRecordDao.saveOrUpdate(ofr);
						//待办人
						return ofr.getOperater();
					}
				}
			} else {
				return pro.getManagementQae();
			}
		} else {
			//上一个待办 nextStepId(上一个步骤)
			//查标准 以及已操作人  再生成待办操作记录
			OperateWorkFlowReq reqFlow = new OperateWorkFlowReq();
			//操作标准流程查询条件 查询数据库标准操作记录 如勘察派工应该谁做
			reqFlow.setCorpId(pro.getCorpId());
			reqFlow.setProjectType(pro.getProjectType());
			reqFlow.setContributionMode(pro.getContributionMode());
			reqFlow.setStepId(nextStepId);
			List<OperateWorkFlow> owfList = operateWorkFlowDao.queryListByReq(reqFlow);

			if (owfList != null && owfList.size() > 0) {
				OperateWorkFlow owf = owfList.get(0);
				if (owf == null) {
					return "";
				}
				OperateRecord owfr = this.getOperateWorkFlowRecord(pro, owf, OperateWorkFlowEnum.WAIT_DONE.getValue());
				if (owfr == null) {
					owfr = new OperateRecord();
				}
				//查上一个已办的操作记录 将操作人回置
				OperateRecord or = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), nextStepId, "", OperateWorkFlowEnum.HAVE_DONE.getValue());
				if (or != null) {
					owfr.setOperateCsr1(or.getOperateCsr1());
					owfr.setOperater(or.getOperater());
				}
				operateRecordDao.saveOrUpdate(owfr);
				return owfr.getOperater();
			}
		}
		return "";
	}


	@Override
	public Map<String, Object> queryOperateRecord(OperateRecordQueryReq operateRecordQueryReq) throws ParseException {
		Map<String, Object> m = operateRecordDao.queryOperateRecordBySql(operateRecordQueryReq);
		return m;
	}


	@Override
	public List<Map<String, Object>> querySchedule(String projId) {
		//分组查询出每个环节所花费的时间
		List<Map<String, Object>> list = operateRecordDao.querySchedule(projId);
		//用于存放返回结果集
		List<Map<String, Object>> result = new ArrayList();
		boolean flag = false;
		if (list.size() > 0) {
			/*for(Map m:list){
				Map<String,Object> outSide = new HashMap();
				Map<String,Object> inSide = new HashMap();
				long maxTime = ((Timestamp) m.get("MAXTIME")).getTime();
				long minTime = ((Timestamp) m.get("MINTIME")).getTime();
				String type = (String) m.get("TYPE");
				inSide.put("from", "/Date("+minTime+")/"); 		//开始时间
				inSide.put("to", "/Date("+maxTime+")/"); 		//开始时间
				inSide.put("label", StepLtypeEnum.valueof(type).getMessage()); //环节
				if(flag){
					inSide.put("customClass", "ganttGreen");
					flag = false;
				}else{
					flag = true;
					inSide.put("customClass", "ganttOrange");
				}
				List<Map<String,Object>> insides = new ArrayList();
				insides.add(inSide);
				outSide.put("values", insides);
				outSide.put("name", StepLtypeEnum.valueof(type).getMessage());
				outSide.put("desc", StepLtypeEnum.valueof(type).getMessage());
				result.add(outSide);
			}*/
			for (int i = 0; i < list.size(); i++) {

				Map<String, Object> map = list.get(i);
				//以下两个map用于组装数据为页面所需格式
				Map<String, Object> outSide = new HashMap<String, Object>();
				Map<String, Object> inSide = new HashMap<String, Object>();
				Map<String, Object> inSide1 = new HashMap<String, Object>();
				Map<String, Object> realSide = new HashMap<String, Object>();
				Map<String, Object> realOutSide = new HashMap<String, Object>();
				/*Map<String,Object> delaySide = new HashMap<String,Object>();*/
				long delayDay = 0l;
				long designDay = 5l;
				//todo:返回map中是小写
				long maxTime =1;
				if (map.get("maxTime") != null) {
					maxTime = ((Timestamp) map.get("maxTime")).getTime();
				}
				long minTime =1;
				if (map.get("minTime") != null) {
					minTime = ((Timestamp) map.get("minTime")).getTime();
				}

				String stepId = map.get("stepId").toString();
				if (map.get("delayDay") != null) {
					String delay = map.get("delayDay").toString();
					if (delay != "") {
						BigDecimal designDaa = new BigDecimal(delay);
						delayDay = designDaa.longValue();
					}
				}
				if (map.get("TIMESPAN") != null) {
					String time = map.get("TIMESPAN").toString();
					if (time != "") {
						//designDay = Long.parseLong(time);
						BigDecimal designDaa = new BigDecimal(time);
						designDay = designDaa.longValue();
					}
				}
				String type = (String) map.get("type");
/*				long maxTime = ((Timestamp) map.get("MAXTIME")).getTime();
				long minTime = ((Timestamp) map.get("MINTIME")).getTime();
				String stepId=map.get("STEPID").toString();
				if(map.get("DELAYDAY")!=null){
					String delay=map.get("DELAYDAY").toString();
					if(delay!=""){
						delayDay = Long.parseLong(delay);
					}
				}
				if(map.get("TIMESPAN")!=null){
					String time=map.get("TIMESPAN").toString();
					if(time!=""){
						designDay = Long.parseLong(time);
					}
				}
				String type = (String) map.get("TYPE");
*/
				//若此环节为立项，则开始时间为分组查询出来的最小时间
				if (type != null) {
					if (!type.equals(StepLtypeEnum.PROJECT_ACCEPT.getValue())) {
						//则查询此环节中最早形成业务记录的步骤id，获取该步骤id上一步所形成的业务记录的最早操作时间，即为此环节操作的开始时间
						Map<String, Object> stepMap = operateRecordDao.findByProjIdType(projId, type);
						if (stepMap != null) {
							//获取上一步操作id;
							String lastStepId = WorkFlowUtil.upDownStepId((String) stepMap.get("stepId"), false);
							Map<String, Object> startTimeMap = operateRecordDao.findByProjIdType(projId, lastStepId);
							if (startTimeMap != null) {
								minTime = ((Timestamp) startTimeMap.get("operateTime")).getTime();
							}
						}
					}
					//设计时长
					if (type.equals(StepLtypeEnum.PROJECT_DESIGN.getValue())) {
						DesignInfo designInfo = designInfoDao.queryInfoByProjId(projId);
						if (designInfo != null && StringUtil.isNotBlank(designInfo.getAcquisitionDays())) {
							BigDecimal designDaa = new BigDecimal(designInfo.getAcquisitionDays());
							designDay = designDaa.longValue();
						}
					}
					//施工-设计时长
					if (type.equals(StepLtypeEnum.CONSTRUCTION.getValue())) {
						ConstructionPlan constructionPlan = constructionPlanDao.findByProjId(projId);
						if (constructionPlan != null && StringUtil.isNotBlank(constructionPlan.getProjTimeLimit()) && CheckUtil.checkNumber(constructionPlan.getProjTimeLimit())) {
							BigDecimal designDaa = new BigDecimal(constructionPlan.getProjTimeLimit());
							designDay = designDaa.longValue();
						} else {
							WorkReport wr = workReportDao.get("projId", projId);
							if (wr != null && StringUtil.isNotBlank(wr.getTimeLimit()) && CheckUtil.checkNumber(wr.getTimeLimit())) {
								BigDecimal designDaa = new BigDecimal(wr.getTimeLimit());
								designDay = designDaa.longValue();
							}
						}
					}
					//竣工-设计时长todo:
				}

				realSide.put("from", "/Date(" + (minTime - 86400000) + ")/");        //开始时间（由于时区原原因，造成的时间错后一天，固减去一天的时间）
				realSide.put("to", "/Date(" + (maxTime - 86400000) + ")/");        //结束时间（由于时区原原因，造成的时间错后一天，固减去一天的时间）
				if(StepLtypeEnum.valueof(type)!=null){
					realSide.put("label", StepLtypeEnum.valueof(type).getMessage()); //环节
				}


				inSide.put("from", "/Date(" + (minTime - 86400000) + ")/");        //开始时间（由于时区原原因，造成的时间错后一天，固减去一天的时间）
				inSide.put("to", "/Date(" + (minTime + (86400000 * (designDay - 1))) + ")/");

				inSide.put("label", StepLtypeEnum.valueof(type).getMessage()); //环节
				inSide1.put("from", "/Date(" + (minTime + (86400000 * (designDay - 1))) + ")/");        //开始时间（由于时区原原因，造成的时间错后一天，固减去一天的时间）
				inSide1.put("to", "/Date(" + (minTime + (86400000 * (designDay - 1)) + (86400000 * (delayDay))) + ")/");        //结束时间（由于时区原原因，造成的时间错后一天，固减去一天的时间）
				inSide1.put("dataObj", stepId);
				inSide1.put("label", "延期"); //环节
				inSide1.put("desc", getTable(stepId, projId)); //环节 table table-striped table-bordered nowrap  dataTable no-footer dtr-inline
				inSide1.put("customClass", "ganttOrange");
				inSide.put("customClass", "ganttGreen");
				List<Map<String, Object>> insides = new ArrayList();
				List<Map<String, Object>> realsides = new ArrayList();
				insides.add(inSide1);
				insides.add(inSide);

				outSide.put("values", insides);
				outSide.put("name", StepLtypeEnum.valueof(type).getMessage());
				DecimalFormat df = new DecimalFormat("######0.0");
				//double days = (Double.parseDouble((maxTime-minTime)+"")/(24*60*60*1000));
				outSide.put("desc", "设计时长：" + df.format(designDay + delayDay) + "天");
				result.add(outSide);
				realsides.add(realSide);
				realOutSide.put("values", realsides);
				realOutSide.put("name", "");
				double days = (Double.parseDouble((maxTime - minTime) + "") / (24 * 60 * 60 * 1000));
				realOutSide.put("desc", "实际时长：" + df.format(days) + "天");
				result.add(realOutSide);
			}
		}
		return result;
	}

	private String getTable(String stepId, String projId) {
		List<ApplyDelay> list = applyDelayDao.findADelayByStepId(stepId, projId);
		StringBuffer str = new StringBuffer();
		str.append("<table id='projectScheduleTable' class='table table-striped table-bordered nowrap  dataTable no-footer dtr-inline' >");
		str.append("<thead><tr role='row'><th>任务名称</th><th>延期时长(天)</th><th>延期原因</th><th>申请人</th><th>申请日期</th></tr>");
		str.append("<tbody>");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (ApplyDelay ad : list) {
			StepEnum se = StepEnum.valueof(ad.getStepId());
			str.append("<tr><td>" + se.getMessage() + "</td><td class='text-right'>" + ad.getDelayPeriod() + "</td><td>" + ad.getDelayReason() + "</td><td>" + ad.getAdOperator() + "</td><td>" + format.format(ad.getOptTime()) + "</td></tr>");
		}

		str.append("</tbody></table>");

		return str.toString();


	}

	@Override
	public List<Map<String, Object>> queryScheduleFlow(String projId) {

		List<Map<String, Object>> list = operateRecordDao.queryScheduleFlow(projId);
		for (int i = 1; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			Object TIME = list.get(i - 1).get("operate_time");
			map.put("start_time", list.get(i - 1).get("operate_time"));
			Object time2 = map.get("start_time");
		}
		return list;
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String createOperateRecordByOther(String orId, String projId,
											 String projNo, String stepId, String stepName, String remark, Project pro) {
		/*//默认登录人是网络受理人
		Staff staff = staffDao.get("wanglsl");
		OperateRecord operateRecord = new OperateRecord();
		operateRecord.setOrId(orId);
		operateRecord.setProjId(projId);
		operateRecord.setProjNo(projNo);
		operateRecord.setStepId(stepId);
		operateRecord.setStepName(stepName);
		operateRecord.setRemark(remark);
		operateRecord.setOperateTime(operateRecordDao.getDatabaseDate());
		operateRecord.setOperateCsr1(staff!=null?staff.getStaffId():"wanglsl");
		operateRecord.setOperateDept1(staff!=null?staff.getDeptId():"11");
		operateRecord.setIsValid("1");//有效记录
		operateRecordDao.save(operateRecord);*/

		Staff staff = staffDao.get("wanglsl");
		//先查询之前的是否生成过操作记录 有的公司有受理审核的环节 会退回
		OperateRecordQueryReq req = new OperateRecordQueryReq();
		req.setProjId(pro.getProjId());

		Map<String, Object> map = operateRecordDao.queryOperateRecordList(req);
		List<OperateRecord> oplist = (List<OperateRecord>) map.get("data");

		if (oplist != null && oplist.size() > 0) {
			//生成过则删除原来的
			operateRecordDao.batchDeleteObjects(oplist);
		}

		//LoginInfo login=SessionUtil.getLoginInfo();

		//用最初的stepId 查询他的下一个stepId，例如现在是1101(受理申请)，获取得到1102 (勘察派工)
		String nextStepId = workFlowService.queryAssistProgressStatusId(pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepId,
				true, WorkFlowTypeEnum.MAIN_PROGRESS.getValue(), null);

		OperateRecord owfr = new OperateRecord();
		OperateRecord returnoOwfr = new OperateRecord();
		List<OperateRecord> owfrList = new ArrayList<OperateRecord>();


		OperateWorkFlowReq reqFlow = new OperateWorkFlowReq();
		//操作标准流程查询条件 查询数据库标准操作记录 如勘察派工应该谁做
		reqFlow.setCorpId(pro.getCorpId());
		reqFlow.setProjectType(pro.getProjectType());
		reqFlow.setContributionMode(pro.getContributionMode());
		List<OperateWorkFlow> list = operateWorkFlowDao.queryListByReq(reqFlow);

		if (list != null && list.size() > 0) {
			for (OperateWorkFlow owf : list) {
				//当前第一步
				if (stepId.equals(owf.getStepId())) {
					//把第一步置为已办
					owfr = this.getOperateWorkFlowRecord(pro, owf, OperateWorkFlowEnum.HAVE_DONE.getValue());
					/*owfr.setOperater(login.getStaffName());
					owfr.setOperateCsr1(","+login.getStaffId()+",");
					owfr.setOperateDept1(login.getDeptId());*/
					owfr.setOperateCsr1(staff != null ? staff.getStaffId() : "wanglsl");
					owfr.setOperateDept1(staff != null ? staff.getDeptId() : "11");
					owfr.setOperater("网络受理");
					owfr.setOperateTime(operateRecordDao.getDatabaseDate());
					owfrList.add(owfr);
				} else if (nextStepId.equals(owf.getStepId())) {
					//当前第二步
					//待办
					//如果下一步骤为审核步骤
					if (StringUtil.isNotBlank(owf.getGrade())) {
						if ("1".equals(owf.getGrade())) {
							//如果是一级 则置为待办
							owfr = this.getOperateWorkFlowRecord(pro, owf, OperateWorkFlowEnum.WAIT_DONE.getValue());
							returnoOwfr = owfr;
						} else {
							//否则是未办
							owfr = this.getOperateWorkFlowRecord(pro, owf, OperateWorkFlowEnum.NOT_DONE.getValue());
						}
					} else {
						//如果下一步骤不是审核步骤，则置为待办
						owfr = this.getOperateWorkFlowRecord(pro, owf, OperateWorkFlowEnum.WAIT_DONE.getValue());
						returnoOwfr = owfr;
					}

					owfrList.add(owfr);
				} else {
					//其他的均为未办
					owfr = this.getOperateWorkFlowRecord(pro, owf, OperateWorkFlowEnum.NOT_DONE.getValue());
					owfrList.add(owfr);
				}
			}


			//判断是否有配置 更新其他历史记录 如安顺受理申请，需更新用户回复等步骤  1101_11_1_1102(query_dept_id),0(menu_id)
			List<DataFilerSetUpDto> listData = Constants.getDataFilterMapByKey(pro.getCorpId() + "_" + pro.getProjectType() + "_" + pro.getContributionMode() + "_" + stepId + "_0");
			if (listData != null && listData.size() > 0) {

				if (StringUtils.isNotBlank(listData.get(0).getSupSql())) {
					String[] stepIds = listData.get(0).getSupSql().split(",");
					for (OperateRecord or : owfrList) {
						if (Arrays.asList(stepIds).contains(or.getStepId())) {
							//or.setOperateCsr1(","+login.getStaffId()+",");
							//or.setOperater(login.getStaffName());
							or.setOperateCsr1(staff != null ? staff.getStaffId() : "wanglsl");
							or.setOperateDept1(staff != null ? staff.getDeptId() : "11");
							or.setOperater("网络受理");
						}
					}
				}
			}


			//批量插入所有的操作记录
			operateRecordDao.batchInsertObjects(owfrList);
		} else {
			//未配置操作流程
			OperateRecord operateRecord = new OperateRecord();
			operateRecord.setOrId(orId);
			operateRecord.setProjId(pro.getProjId());
			operateRecord.setProjNo(pro.getProjNo());
			operateRecord.setStepId(stepId);
			operateRecord.setStepName(StepEnum.valueof(stepId).getMessage());
			operateRecord.setOperateTime(operateRecordDao.getDatabaseDate());
			/*operateRecord.setOperateCsr1(","+login.getStaffId()+",");
			operateRecord.setOperater(login!=null?login.getStaffName():"1");
			operateRecord.setOperateDept1(login!=null?login.getDeptId():"1");*/
			operateRecord.setOperateCsr1(staff != null ? staff.getStaffId() : "wanglsl");
			operateRecord.setOperateDept1(staff != null ? staff.getDeptId() : "11");
			operateRecord.setOperater("网络受理");
			operateRecord.setIsValid("1");//有效记录
			operateRecord.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
			operateRecord.setCorpId(pro.getCorpId());
			operateRecord.setProjectType(pro.getProjectType());
			operateRecord.setContributionMode(pro.getContributionMode());
			operateRecord.setOpType(WorkFlowTypeEnum.MAIN_PROGRESS.getValue());
			operateRecordDao.save(operateRecord);
		}

		return returnoOwfr.getOperater();
	}

	/**
	 * 关联操作查询业务操作记录
	 *
	 * @author fuliwei
	 * @date 2018年10月6日
	 * @version 1.0
	 */
	@Override
	public Map<String, Object> queryOperateRecordList(OperateRecordQueryReq req) {
		Map<String, Object> map = operateRecordDao.queryOperateRecordList(req);
		List<OperateRecord> recordList = new ArrayList<>();
		List<OperateRecord> orList = (List<OperateRecord>) map.get("data");
		if (orList != null && orList.size() > 0) {
			for (OperateRecord or : orList) {
				if (StringUtil.isNotBlank(or.getProjId())) {
					Project pro = projectDao.get(or.getProjId());
					if (pro.getProjStatusId().equals(ProjStatusEnum.TERMINATION.getValue())) {//统计已终止的工程
						recordList.add(or);
					}
					or.setProjName(pro.getProjName());      //工程名称
				}
				/*Staff staff=new Staff();
				if(StringUtil.isNotBlank(or.getOperateCsr1())){
					staff=staffDao.get(or.getOperateCsr1());
					if(staff!=null){
						or.setOperateCsr1(staff.getStaffName());//操作人
					}
				}*/
				//menu 信息关联
				if (StringUtil.isNotBlank(or.getStepId())) {
					String stepId = or.getStepId();
					if (AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue().equals(or.getOpType())) {
						stepId = or.getOpType() + "-" + stepId;
					} else if (AssistProgressTypeEnum.PAYMENT.getValue().equals(or.getOpType())) {
						stepId = or.getOpType() + "-" + stepId;
					} else if (AssistProgressTypeEnum.CHANGE_PROGRESS.getValue().equals(or.getOpType())) {
						stepId = or.getOpType() + "-" + stepId;
					} else if (AssistProgressTypeEnum.GAS_PROGRESS.getValue().equals(or.getOpType())) {
						stepId = or.getOpType() + "-" + stepId;
					} else if (AssistProgressTypeEnum.DESIGN_CHANGE_PROGRESS.getValue().equals(or.getOpType())) {
						stepId = or.getOpType() + "-" + stepId;
					} else if (AssistProgressTypeEnum.RECTIFY_NOTICE.getValue().equals(or.getOpType())) {
						stepId = or.getOpType() + "-" + stepId;
					}else if (AssistProgressTypeEnum.DIVISIONAL_ACCEPT.getValue().equals(or.getOpType())) {
						stepId = or.getOpType() + "-" + stepId;
					}
					MenuStep ms = menuStepDao.findByStepId(stepId);
					if (ms != null) {
						Menu menu = menuDao.get(ms.getMeunId());
						or.setMenuId(menu.getMenuId());
						or.setUrl(menu.getUrl());
					}
				}
			}
		}


		if (recordList.size() > 0 && recordList != null) {
			for (OperateRecord o : recordList) {//移除已经终止的工程
				for (int i = 0; i < orList.size(); i++) {
					if (orList.get(i).getProjId().equals(o.getProjId())) {
						orList.remove(i);
					}
				}
			}

		}

		//查探伤委托
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		BusinessCommunicationReq businessCommunicationReq = new BusinessCommunicationReq();
		businessCommunicationReq.setBcRecipientId(loginInfo.getStaffId());            //接收人id
		businessCommunicationReq.setBcSendType(BcSendTypeEnum.RECIPIENT.getValue());    //1：发送，2：接收
		businessCommunicationReq.setBcStatus(BcStatusEnum.TO_REPLY.getValue());            //1：待通知，2：待回复，3：已回复
		//businessCommunicationReq.setBcTypeDetail("2011");								//探伤委托
		OperateRecord operateRecord;

		//业务沟通通知
		List<BusinessCommunication> list = businessCommunicationDao.queryBusinessCommunicationList(businessCommunicationReq);
		if (list != null && list.size() > 0) {
			for (BusinessCommunication bc : list) {
				operateRecord = new OperateRecord();
				operateRecord.setProjNo(bc.getProjNo());
				operateRecord.setProjName(bc.getProjName());
				operateRecord.setStepDes("业务沟通");//无损检测
				operateRecord.setStepName("业务沟通");//无损检测
				operateRecord.setMenuId("120213");
				operateRecord.setUrl("#businessCommunication/main");
				operateRecord.setOperater(loginInfo.getStaffName());
				orList.add(operateRecord);
			}
		}


		return map;
	}

	/**
	 * 立项时创建操作记录表,只在立项时调用
	 *
	 * @author fuliwei
	 * @date 2018年9月7日
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public HashMap<String, Object> cerateOperateWorkFlowRecord(Project pro, String stepId, LoginInfo login, String wfTypeId, String wfAssistTypeId) {
		//先查询之前的是否生成过操作记录 有的公司有受理审核的环节 会退回
		/*OperateRecordQueryReq req = new OperateRecordQueryReq();
		req.setProjId(pro.getProjId());
		//List<OperateWorkFlowRecord> oplist=operateWorkFlowRecordDao.queryListByReq(req);

		Map<String, Object> map = operateRecordDao.queryOperateRecordList(req);
		List<OperateRecord> oplist = (List<OperateRecord>) map.get("data");

		if (oplist != null && oplist.size() > 0) {
			//生成过则删除原来的
			operateRecordDao.batchDeleteObjects(oplist);
		}*/
		operateRecordDao.deleteByProjId(pro.getProjId());

//		LoginInfo login=SessionUtil.getLoginInfo();

		//用最初的stepId 查询他的下一个stepId，例如现在是1101(受理申请)，获取得到1102 (勘察派工)
		String nextStepId = workFlowService.queryAssistProgressStatusId(pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepId,
				true, wfTypeId, wfAssistTypeId);

		OperateRecord owfr = new OperateRecord();
		OperateRecord returnoOwfr = new OperateRecord();
		List<OperateRecord> owfrList = new ArrayList<OperateRecord>();


		OperateWorkFlowReq reqFlow = new OperateWorkFlowReq();
		//操作标准流程查询条件 查询数据库标准操作记录 如勘察派工应该谁做
		reqFlow.setCorpId(pro.getCorpId());
		reqFlow.setProjectType(pro.getProjectType());
		reqFlow.setContributionMode(pro.getContributionMode());
		//流程类型主流程 或者辅助流程
		reqFlow.setOpType(WorkFlowTypeEnum.MAIN_PROGRESS.getValue().equals(wfTypeId) ? wfTypeId : wfAssistTypeId);
		List<OperateWorkFlow> list = operateWorkFlowDao.queryListByReq(reqFlow);
		if (list != null && list.size() > 0) {
			for (OperateWorkFlow owf : list) {
				//当前第一步
				if (stepId.equals(owf.getStepId())) {
					//把第一步置为已办
					owfr = this.getOperateWorkFlowRecord(pro, owf, OperateWorkFlowEnum.HAVE_DONE.getValue());
					owfr.setOperater(login.getStaffName());
					owfr.setOperateCsr1("," + login.getStaffId() + ",");
					owfr.setOperateDept1(login.getDeptId());
					owfr.setOperateTime(operateRecordDao.getDatabaseDate());
					owfrList.add(owfr);
				} else if (nextStepId.equals(owf.getStepId())) {
					//当前第二步
					//待办
					//如果下一步骤为审核步骤
					if (StringUtil.isNotBlank(owf.getGrade())) {
						if ("1".equals(owf.getGrade())) {
							//如果是一级 则置为待办
							owfr = this.getOperateWorkFlowRecord(pro, owf, OperateWorkFlowEnum.WAIT_DONE.getValue());
							returnoOwfr = owfr;
						} else {
							//否则是未办
							owfr = this.getOperateWorkFlowRecord(pro, owf, OperateWorkFlowEnum.NOT_DONE.getValue());
						}
					} else {
						//如果下一步骤不是审核步骤，则置为待办
						owfr = this.getOperateWorkFlowRecord(pro, owf, OperateWorkFlowEnum.WAIT_DONE.getValue());
						returnoOwfr = owfr;
					}

					owfrList.add(owfr);
				} else {
					//其他的均为未办
					owfr = this.getOperateWorkFlowRecord(pro, owf, OperateWorkFlowEnum.NOT_DONE.getValue());
					owfrList.add(owfr);
				}
			}


			//不同部门立的项目，待办人不一样时，处理待办人
			DataFilerSetUpDto upDto = Constants.isConfig(login.getCorpId() + "_" + pro.getProjectType() + "_110101");
			if (upDto != null && StringUtil.isNotBlank(upDto.getSupSql())) {
				String[] stepIds = upDto.getSupSql().split(",");
				for (int i = 0; i < stepIds.length; i++) {
					for (OperateRecord o : owfrList) {
						if (stepIds[i].equals(o.getStepId()) && StringUtil.isNotBlank(o.getOperateCsr1())) {
							String[] staffIds = o.getOperateCsr1().split(",");
							for (int j = 0; j < staffIds.length; j++) {
								Staff staff = staffDao.get(staffIds[j]);
								if (staff != null && StringUtil.isNotBlank(staff.getDeptId()) && staff.getDeptId().equals(pro.getDeptId())) {
									o.setOperateCsr1("," + staff.getStaffId() + ",");
									o.setOperater(staff.getStaffName());
								}
							}
						}
					}
				}
			}

			//判断是否有配置 更新其他历史记录 如安顺受理申请，需更新用户回复等步骤  1101_11_1_1102(query_dept_id),0(menu_id)

			List<DataFilerSetUpDto> listData = null;
			listData = Constants.getDataFilterMapByKey(login.getCorpId() + "_" + pro.getProjectType() + "_" + pro.getContributionMode() + "_" + stepId + "_0");
			if (listData == null || listData.size() < 1) {
				listData = Constants.getDataFilterMapByKey(login.getCorpId() + "_" + pro.getProjectType() + "_" + stepId + "_0");
			}

			if (listData != null && listData.size() > 0) {
				if (StringUtils.isNotBlank(listData.get(0).getSupSql())) {
					String[] stepIds = listData.get(0).getSupSql().split(",");
					for (OperateRecord or : owfrList) {
						for (int i = 0; i < stepIds.length; i++) {
							if (stepIds[i].indexOf("_") != -1) {   //包含审核级别
								String grade = stepIds[i].substring(stepIds[i].length() - 1, stepIds[i].length());            //取最后一位审核级别
								String stepAuditId = stepIds[i].substring(0, stepIds[i].length() - 2);  //具有审核级别的stepID
								//若当前记录的stepId等于配置的具有审核级别的stepID,且审核级别相同则更新操作记录
								if (StringUtils.isNotBlank(stepAuditId) && StringUtils.isNotBlank(grade) && grade.equals(or.getGrade()) && stepAuditId.equals(or.getStepId())) {
									or.setOperateCsr1("," + login.getStaffId() + ",");
									or.setOperater(login.getStaffName());
								}
							}
						}

						if (Arrays.asList(stepIds).contains(or.getStepId())) {
							or.setOperateCsr1("," + login.getStaffId() + ",");
							or.setOperater(login.getStaffName());
						}
					}
				}
			}
			//新方法--开始

			//新方法--结束

			//批量插入所有的操作记录
			operateRecordDao.batchInsertObjects(owfrList);
		} else {
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
			//未配置操作流程
			OperateRecord operateRecord = new OperateRecord();
			operateRecord.setOrId(orId);
			operateRecord.setProjId(pro.getProjId());
			operateRecord.setProjNo(pro.getProjNo());
			operateRecord.setStepId(stepId);
			if (StepEnum.valueof(stepId) != null) {
				operateRecord.setStepName(StepEnum.valueof(stepId).getMessage());
			} else if (StepOutWorkflowEnum.valueof(stepId) != null) {
				operateRecord.setStepName(StepOutWorkflowEnum.valueof(stepId).getMessage());
			}
			operateRecord.setOperateTime(operateRecordDao.getDatabaseDate());
			operateRecord.setOperateCsr1("," + login.getStaffId() + ",");
			operateRecord.setOperater(login != null ? login.getStaffName() : "1");
			operateRecord.setOperateDept1(login != null ? login.getDeptId() : "1");
			operateRecord.setIsValid("1");//有效记录
			operateRecord.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
			operateRecord.setCorpId(pro.getCorpId());
			operateRecord.setProjectType(pro.getProjectType());
			operateRecord.setContributionMode(pro.getContributionMode());
			operateRecord.setOpType(WorkFlowTypeEnum.MAIN_PROGRESS.getValue());
			operateRecordDao.save(operateRecord);
		}
		HashMap<String, Object> rul = new HashMap<>();
		rul.put("NAME", returnoOwfr.getOperater());
		rul.put("ID", returnoOwfr.getOperateCsr1());
		return rul;
	}


	public OperateRecord getOperateWorkFlowRecord(Project pro, OperateWorkFlow owf, String modifyStatus) {
		OperateRecord owfr = new OperateRecord();
		//第一个
		owfr.setOrId(IDUtil.getUniqueId(Constants.STANDARD));
		owfr.setProjectType(pro.getProjectType());            //工程类型
		owfr.setContributionMode(pro.getContributionMode());//出资方式
		owfr.setCorpId(pro.getCorpId());
		owfr.setProjId(pro.getProjId());                    //工程id
		owfr.setProjNo(pro.getProjNo());                    //工程编号
		owfr.setStepName(StringUtil.isNotBlank(owf.getStepDes()) ? owf.getStepDes() : owf.getStepName());
		owfr.setOwfId(owf.getOwfId());                        //标准操作流id
		owfr.setOpType(owf.getOpType());
		owfr.setStepId(owf.getStepId());
		owfr.setGrade(owf.getGrade());
		owfr.setOpType(owf.getOpType());
		owfr.setModifyStatus(modifyStatus);
		owfr.setOperateCsr1(owf.getOpereaterId());            //操作人id
		owfr.setOperater(owf.getOpereater());                ////操作人
		owfr.setIsValid("1");//有效
		//部门id未存入，在实际生产操作记录时存入
		return owfr;
	}

	/**
	 * 生成下一个操作通知(只在派工页面调用)
	 *
	 * @author fuliwei
	 * @date 2018年9月9日
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createNextOperateRecord(String orId, String projId, String projNo, String stepId, String stepName, String remark, String operateId, String operater) {
		//该方法将当前的置为已办  将下一步骤置为待办


		//取得登录人信息
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		//将当前的置为已办
		OperateRecordQueryReq req = new OperateRecordQueryReq();
		req.setProjId(projId);
		req.setStepId(stepId);
		req.setIsValid("1");    //有效
		Map<String, Object> map = operateRecordDao.queryOperateRecordList(req);
		List<OperateRecord> list = (List<OperateRecord>) map.get("data");
		Project pro = projectDao.get(projId);
		LoginInfo login = SessionUtil.getLoginInfo();
		if (list != null && list.size() > 0) {
			OperateRecord owfr = list.get(0);
			owfr.setOperater(login.getStaffName());
			owfr.setOperateCsr1("," + login.getStaffId() + ",");
			owfr.setOperateDept1(login.getDeptId());
			owfr.setOperateTime(operateRecordDao.getDatabaseDate());
			owfr.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
			operateRecordDao.saveOrUpdate(owfr);
		} else {

			//如果没有找到下一条记录  则走原来生成业务操作记录的方法
			OperateRecord operateRecord = new OperateRecord();
			operateRecord.setOrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
			operateRecord.setProjId(projId);
			operateRecord.setProjNo(projNo);
			operateRecord.setStepId(stepId);
			operateRecord.setStepName(stepName);
			operateRecord.setRemark(remark);
			operateRecord.setOperateTime(operateRecordDao.getDatabaseDate());
			operateRecord.setOperateCsr1("," + loginInfo.getStaffId() + ",");
			operateRecord.setOperateDept1(loginInfo != null ? loginInfo.getDeptId() : "1");
			operateRecord.setIsValid("1");//有效记录
			operateRecord.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
			operateRecord.setCorpId(pro.getCorpId());
			operateRecord.setProjectType(pro.getProjectType());
			operateRecord.setContributionMode(pro.getContributionMode());
			operateRecord.setOpType(WorkFlowTypeEnum.MAIN_PROGRESS.getValue());
			operateRecordDao.save(operateRecord);
		}


		//下一个置为待办
		//用最初的stepId 查询他的下一个stepId;
		String nextStepId = workFlowService.queryAssistProgressStatusId(pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepId,
				true, WorkFlowTypeEnum.MAIN_PROGRESS.getValue(), null);
		req.setStepId(nextStepId);
		req.setIsValid("1");
		Map<String, Object> nextMap = operateRecordDao.queryOperateRecordList(req);
		List<OperateRecord> nextList = (List<OperateRecord>) nextMap.get("data");


		Boolean flag = true;
		if (nextList != null && nextList.size() > 0) {
			OperateRecord ofr = nextList.get(0);
			for (OperateRecord or : nextList) {
				if (StringUtils.isNotBlank(or.getGrade()) && "1".equals(or.getGrade())) {
					//如果是一级审核 置为待办 如预算审核派工的下一个环节是预算审核一级
					or.setOperater(operater);        //代办人
					or.setOperateCsr1("," + operateId + ",");    //代办人id
					or.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());
					operateRecordDao.saveOrUpdate(or);
					flag = false;
					break;
				}
			}
			if (flag) {
				//如果是派工环节，如预算派工，则传派工的人员，否则传null
				ofr.setOperater(operater);        //代办人
				ofr.setOperateCsr1("," + operateId + ",");    //代办人id
				ofr.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());
				operateRecordDao.saveOrUpdate(ofr);
			}
		}


		//判断是否有配置 更新其他历史记录 如安顺受理申请，需更新用户回复等步骤 前面不加0_
		List<DataFilerSetUpDto> listData = Constants.getDataFilterMapByKey(login.getCorpId() + "_" + pro.getProjectType() + "_" + pro.getContributionMode() + "_" + stepId + "_0");
		if (listData != null && listData.size() > 0) {
			if (StringUtils.isNotBlank(listData.get(0).getSupSql())) {
				String[] stepIds = listData.get(0).getSupSql().split(",");
				for (int i = 0; i < stepIds.length; i++) {
					if (stepIds[i].indexOf("_") != -1) {
						//包含审核级别
						String grade = stepIds[i].substring(stepIds[i].length() - 1, stepIds[i].length());            //取最后一位审核级别
						String stepAuditId = stepIds[i].substring(0, stepIds[i].length() - 2);
						OperateRecord otherOr = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepAuditId, grade, OperateWorkFlowEnum.NOT_DONE.getValue());
						if (otherOr != null) {
							otherOr.setOperateCsr1("," + operateId + ",");
							otherOr.setOperater(operater);
							operateRecordDao.saveOrUpdate(otherOr);
						}
					} else {
						OperateRecord otherOr = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepIds[i], null, OperateWorkFlowEnum.NOT_DONE.getValue());
						//查询操作记录
						if (otherOr != null) {
							otherOr.setOperateCsr1("," + operateId + ",");
							otherOr.setOperater(operater);
							operateRecordDao.saveOrUpdate(otherOr);
						}
					}

				}
			}
		}


	}

	/**
	 * 处理操作记录，只在当前环节派其他人员时调用，如现场踏勘选择设计员后处理设计员相关的记录
	 *
	 * @author fuliwei
	 * @date 2018年10月7日
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateAboutOperateRecord(Project pro, String stepId, String operateCsr1, String operater) {
		//判断是否有配置 更新其他历史记录 如安顺受理申请，需更新用户回复等步骤 区分其他设置，前面加上"0_"
		List<DataFilerSetUpDto> listData = Constants.getDataFilterMapByKey("0_" + pro.getCorpId() + "_" + pro.getProjectType() + "_" + pro.getContributionMode() + "_" + stepId + "_0");
		if (listData != null && listData.size() > 0) {
			if (StringUtils.isNotBlank(listData.get(0).getSupSql())) {
				String[] stepIds = listData.get(0).getSupSql().split(",");
				for (int i = 0; i < stepIds.length; i++) {
					if (stepIds[i].indexOf("_") != -1) {
						//包含审核级别
						String grade = stepIds[i].substring(stepIds[i].length() - 1, stepIds[i].length());            //取最后一位审核级别
						String stepAuditId = stepIds[i].substring(0, stepIds[i].length() - 2);
						OperateRecord otherOr = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepAuditId, grade, OperateWorkFlowEnum.NOT_DONE.getValue());
						if (otherOr != null) {
							otherOr.setOperateCsr1("," + operateCsr1 + ",");
							otherOr.setOperater(operater);
							operateRecordDao.saveOrUpdate(otherOr);
						}
					} else {
						OperateRecord otherOr = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepIds[i], null, OperateWorkFlowEnum.NOT_DONE.getValue());
						//查询操作记录
						if (otherOr != null) {
							otherOr.setOperateCsr1("," + operateCsr1 + ",");
							otherOr.setOperater(operater);
							operateRecordDao.saveOrUpdate(otherOr);
						}
					}
				}
			}
		}
	}

	/**
	 * 将历史记录置为无效，生成新的有效的操作记录，将上一步骤置为待办
	 *
	 * @author fuliwei
	 * @date 2018年10月17日
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String batUpdateHistoryRecordByBoId(String projId, String businessorderId, String stepId) {

		//1.将历史记录置为无效 一级不通过 则将一级、二级
		operateRecordDao.batUpdateHistoryRecordByBoId(projId, businessorderId, stepId);


		//2.生成有效的新数据
		List<OperateRecord> owfrList = new ArrayList<OperateRecord>();
		OperateRecord owfr;

		Project pro = projectDao.get(projId);
		//按步骤查询所有的操作记录
		DocType docType = docTypeService.findByStepId(stepId, pro.getCorpId(), pro.getProjectType(), pro.getContributionMode());
		if (docType != null) {
			Integer grade = Integer.valueOf(docType.getGrade());//审核级别
			for (int i = 1; i <= grade; i++) {
				// stepId,工程信息,busId,garde,最大时间
				OperateRecord or = operateRecordDao.findByMaxTime(projId, pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepId, String.valueOf(i), null);
				if (or != null) {
					owfr = new OperateRecord();
					owfr.setOrId(IDUtil.getUniqueId(Constants.STANDARD));
					owfr.setProjectType(pro.getProjectType());            //工程类型
					owfr.setContributionMode(pro.getContributionMode());//出资方式
					owfr.setCorpId(pro.getCorpId());
					owfr.setProjId(pro.getProjId());                    //工程id
					owfr.setProjNo(pro.getProjNo());                    //工程编号

					owfr.setOwfId(or.getOwfId());                        //标准操作流id
					owfr.setStepId(or.getStepId());
					if (StepEnum.valueof(or.getStepId()) != null) {
						owfr.setStepName(StepEnum.valueof(or.getStepId()).getMessage());
					} else if (StepOutWorkflowEnum.valueof(or.getStepId()) != null) {
						owfr.setStepName(StepOutWorkflowEnum.valueof(or.getStepId()).getMessage());
					}
					owfr.setGrade(or.getGrade());
					owfr.setOpType(or.getOpType());
					owfr.setModifyStatus(OperateWorkFlowEnum.NOT_DONE.getValue());        //未办
					owfr.setOperateCsr1(or.getOperateCsr1());            //操作人id
					owfr.setOperater(or.getOperater());                    //操作人
					owfr.setIsValid("1");//有效
					owfrList.add(owfr);
				}
			}
			operateRecordDao.batchInsertObjects(owfrList);
		}


		//3.将上一步骤置为待办
		//用最初的stepId 查询他的上一个stepId;
		String lastStepId = workFlowService.queryAssistProgressStatusId(pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepId,
				false, WorkFlowTypeEnum.MAIN_PROGRESS.getValue(), null);
		OperateRecord orMax = operateRecordDao.findByMaxTime(projId, pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), lastStepId, null, null);

		if (orMax != null) {
			owfr = new OperateRecord();
			owfr.setOrId(IDUtil.getUniqueId(Constants.STANDARD));
			owfr.setProjectType(pro.getProjectType());            //工程类型
			owfr.setContributionMode(pro.getContributionMode());//出资方式
			owfr.setCorpId(pro.getCorpId());
			owfr.setProjId(pro.getProjId());                    //工程id
			owfr.setProjNo(pro.getProjNo());                    //工程编号

			owfr.setOwfId(orMax.getOwfId());                        //标准操作流id
			owfr.setStepId(orMax.getStepId());
			owfr.setStepName(StepEnum.valueof(orMax.getStepId()).getMessage());
			owfr.setGrade(orMax.getGrade());
			owfr.setOpType(orMax.getOpType());
			owfr.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());        //待办
			owfr.setOperateCsr1(orMax.getOperateCsr1());            //操作人id
			owfr.setOperater(orMax.getOperater());                    //操作人
			owfr.setIsValid("1");//有效
			operateRecordDao.saveOrUpdate(owfr);
			return owfr.getOperater();
		}
		return null;
	}

	/**
	 * 查下一个审核操作记录 审核部分通过时调用
	 *
	 * @author fuliwei
	 * @date 2018年10月17日
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public OperateRecord findNextGrade(String projId, String corpId, String projectType, String conMode, String stepId,
									   String grade, String modifyStatus, String busId) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//将当前的置为已办
		OperateRecord thisOr = operateRecordDao.findByGread(projId, corpId, projectType, conMode, stepId, grade, null);
		if (thisOr != null) {
			thisOr.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
			thisOr.setBusinessOrderId(busId);//业务单id
			thisOr.setOperateTime(operateRecordDao.getDatabaseDate());
			thisOr.setOperater(loginInfo.getStaffName());
			thisOr.setOperateCsr1("," + loginInfo.getStaffId() + ",");    //代办人id
			thisOr.setOperateDept1(loginInfo.getDeptId());
			operateRecordDao.saveOrUpdate(thisOr);
		}


		//下个审核操作记录  如当前为一级审核 下个为二级审核 置为代办
		String nextGrade = String.valueOf(Integer.valueOf(grade) + 1);
		Project pro = projectDao.get(projId);

		OperateRecord or = operateRecordDao.findByGread(projId, corpId, projectType, conMode, stepId, nextGrade, null);
		if (or != null) {
			//置为待办
			or.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());
			or.setBusinessOrderId(busId);//业务单id
			operateRecordDao.saveOrUpdate(or);
			//待办人
			return or;
		} else {
			//查询标准
			OperateWorkFlow owf = operateWorkFlowDao.findByGrade(corpId, projectType, conMode, stepId, nextGrade, WorkFlowTypeEnum.MAIN_PROGRESS.getValue());
			if (owf != null) {
				OperateRecord owfr = new OperateRecord();
				owfr = this.getOperateWorkFlowRecord(pro, owf, OperateWorkFlowEnum.WAIT_DONE.getValue());
				owfr.setBusinessOrderId(busId);//业务单id
				operateRecordDao.saveOrUpdate(owfr);
				return owfr;
			}
		}
		return null;
	}

	/**
	 * 处理施工操作记录，如选择项目经理后处理相关的记录，只按工程类型，不算出资方式
	 *
	 * @author fuliwei
	 * @date 2018年10月7日
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateConAboutOperateRecord(Project pro, String stepId, String operateCsr1, String operater, String postId) {
		//判断是否有配置 更新其他历史记录 如安顺受理申请，需更新用户回复等步骤 区分其他设置，前面加上"0_"
		List<DataFilerSetUpDto> listData = Constants.getDataFilterMapByKey(postId + "_" + pro.getCorpId() + "_" + pro.getProjectType() + "_" + pro.getContributionMode() + "_" + stepId + "_0");
		if (listData != null && listData.size() > 0) {
			if (StringUtils.isNotBlank(listData.get(0).getSupSql())) {
				String[] stepIds = listData.get(0).getSupSql().split(",");
				for (int i = 0; i < stepIds.length; i++) {
					if (stepIds[i].indexOf("_") != -1) {
						//包含审核级别
						String grade = stepIds[i].substring(stepIds[i].length() - 1, stepIds[i].length());            //取最后一位审核级别
						String stepAuditId = stepIds[i].substring(0, stepIds[i].length() - 2);
						OperateRecord otherOr = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepAuditId, grade, OperateWorkFlowEnum.NOT_DONE.getValue());
						if (otherOr != null) {
							otherOr.setOperateCsr1("," + operateCsr1 + ",");
							otherOr.setOperater(operater);
							operateRecordDao.saveOrUpdate(otherOr);
						} else {
							//查待办的
							OperateRecord waitOr = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepAuditId, grade, OperateWorkFlowEnum.WAIT_DONE.getValue());
							if (waitOr != null) {
								waitOr.setOperateCsr1("," + operateCsr1 + ",");
								waitOr.setOperater(operater);
								operateRecordDao.saveOrUpdate(waitOr);
							}
						}
					} else {
						OperateRecord otherOr = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepIds[i], null, OperateWorkFlowEnum.NOT_DONE.getValue());
						//查询操作记录
						if (otherOr != null) {
							otherOr.setOperateCsr1("," + operateCsr1 + ",");
							otherOr.setOperater(operater);
							operateRecordDao.saveOrUpdate(otherOr);
						} else {
							OperateRecord waitOr = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepIds[i], null, OperateWorkFlowEnum.WAIT_DONE.getValue());
							if (waitOr != null) {
								waitOr.setOperateCsr1("," + operateCsr1 + ",");
								waitOr.setOperater(operater);
								operateRecordDao.saveOrUpdate(waitOr);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 将历史记录置为无效  退回到第一级审核
	 *
	 * @author fuliwei
	 * @date 2018年10月17日
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String batUpdateHistoryRecordByBoIdToFirst(String projId, String businessorderId, String stepId) {
		//1.将历史记录置为无效
		operateRecordDao.batUpdateHistoryRecordByBoId(projId, businessorderId, stepId);


		//2.生成有效的新数据
		List<OperateRecord> owfrList = new ArrayList<OperateRecord>();
		OperateRecord owfr;

		Project pro = projectDao.get(projId);
		//按步骤查询所有的操作记录
		DocType docType = docTypeService.findByStepId(stepId, pro.getCorpId(), pro.getProjectType(), pro.getContributionMode());
		String todoer = "";
		if (docType != null) {
			Integer grade = Integer.valueOf(docType.getGrade());//审核级别
			for (int i = 1; i <= grade; i++) {
				// stepId,工程信息,busId,garde,最大时间
				OperateRecord or = operateRecordDao.findByMaxTime(projId, pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepId, String.valueOf(i), null);
				if (or != null) {
					owfr = new OperateRecord();
					owfr.setOrId(IDUtil.getUniqueId(Constants.STANDARD));
					owfr.setProjectType(pro.getProjectType());            //工程类型
					owfr.setContributionMode(pro.getContributionMode());//出资方式
					owfr.setCorpId(pro.getCorpId());
					owfr.setProjId(pro.getProjId());                    //工程id
					owfr.setProjNo(pro.getProjNo());                    //工程编号

					owfr.setOwfId(or.getOwfId());                        //标准操作流id
					owfr.setStepId(or.getStepId());
					owfr.setStepName(or.getStepName());
					owfr.setGrade(or.getGrade());
					owfr.setOpType(or.getOpType());
					if (i == 1) {
						owfr.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());        //退回到一级 ，一级为待办
						//todoer=or.getOperateCsr1();
						todoer = or.getOperater();//待办人 2019-04-08 fulw
					} else {
						owfr.setModifyStatus(OperateWorkFlowEnum.NOT_DONE.getValue());        //未办
					}

					owfr.setOperateCsr1(or.getOperateCsr1());            //操作人id
					owfr.setOperater(or.getOperater());                    //操作人
					owfr.setIsValid("1");//有效
					owfrList.add(owfr);
				}
			}
			operateRecordDao.batchInsertObjects(owfrList);
		}
		return todoer;
	}

	/**
	 * 处理历史数据-点击按钮后作废
	 *
	 * @author fuliwei
	 * @date 2018年11月5日
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateHistoryOperateRecord(String projId, String corpId) {
		//先查一个工程的
		Project proj = projectDao.get(projId);

		//查询已办的操作记录
		OperateRecordQueryReq req = new OperateRecordQueryReq();
		req.setProjId(projId);
		req.setIsValid("1");    //有效
		Map<String, Object> map = operateRecordDao.queryOperateRecordList(req);
		//操作记录
		List<OperateRecord> list = (List<OperateRecord>) map.get("data");
		/*Staff staff=new Staff();
		String [] stepIds= new String[]{"110101","1104","11042","1202","1303","14011","1403","","1502","160201","1605","1804","18042","1902","1904"};
		List<String> listStepIds=ArraysUtil.asList(stepIds);
		if(list!=null && list.size()>0){
			for(OperateRecord or: list){
				DocType docType = docTypeService.findByStepId(or.getStepId(),proj.getCorpId(),proj.getProjectType(),proj.getContributionMode());
				if(listStepIds.contains(or.getStepId())){
					//是审核的
					or.setGrade(docType.getGrade());
				}
				staff=staffDao.get(or.getOperateCsr1());			//操作人id
				or.setOperater(staff.getStaffName());   			//操作人名称
				or.setProjectType(proj.getProjectType());			//工程类型
				or.setContributionMode(proj.getContributionMode()); //出资方式
				or.setCorpId(proj.getCorpId());						//分公司id
				or.setOpType("1");
				operateRecordDao.saveOrUpdate(or);

				//OWF_ID暂时不处理
			}
		}*/

		//查询已处理的审核记录 暂时不处理
		//受理审核 踏勘审核  提资审核 图纸审核 预算审核 工程造价审批 合同审核 计划审核 预算初审 施工合同审核 资料审核 联合验收审核 结算初审 终审确认
		/*
		for(int i=0;i<stepIds.length;i++){
			//查审核通过的审核记录
			List<ManageRecord> listManage=manageRecordDao.findByStepIdProjId(projId, stepIds[i]);
			if(listManage!=null && listManage.size()>0){

			}
		}*/


		//查标准和已办，区分出哪些未办

		OperateWorkFlowReq reqFlow = new OperateWorkFlowReq();
		//操作标准流程查询条件 查询数据库标准操作记录 如勘察派工应该谁做
		reqFlow.setCorpId(proj.getCorpId());
		reqFlow.setProjectType(proj.getProjectType());
		reqFlow.setContributionMode(proj.getContributionMode());
		List<OperateWorkFlow> listStandard = operateWorkFlowDao.queryListByReq(reqFlow);

		//返回值
		List<OperateRecord> owfrList = new ArrayList<OperateRecord>();
		List<OperateRecord> returnList = new ArrayList<OperateRecord>();
		OperateRecord owfr = new OperateRecord();


		//标准
		for (OperateWorkFlow owf : listStandard) {
			owfr = this.getOperateWorkFlowRecord(proj, owf, OperateWorkFlowEnum.NOT_DONE.getValue());
			owfrList.add(owfr);
			returnList.add(owfr);
		}

		//循环操作记录，删除相同的
		for (OperateRecord owf : owfrList) {
			String stepId = owf.getStepId();
			for (OperateRecord or : list) {
				String orStepId = or.getStepId();
				if (stepId.equals(orStepId)) {
					returnList.remove(owf);
					continue;
				}
			}
		}

		//批量生成
		operateRecordDao.batchInsertObjects(returnList);
	}

	/**
	 * 处理历史审核记录
	 *
	 * @author fuliwei
	 * @date 2018年11月6日
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateHistoryManagERecord(String projId, String corpId) {

		Project pro = projectDao.get(projId);

		String[] stepIds = new String[]{"110101", "1104", "11042", "1202", "1303", "14011", "1403", "1502", "160201", "1605", "1804", "18042", "1902", "1904"};
		for (int i = 0; i < stepIds.length; i++) {
			//查审核通过的审核记录
			String stepId = stepIds[i];
			DocType docType = docTypeService.findByStepId(stepId, pro.getCorpId(), pro.getProjectType(), pro.getContributionMode());
			int ij = 0;
			int grade = 1;
			if (docType != null) {
				grade = Integer.valueOf(docType.getGrade());
			} else {
				continue;
			}


			//查已生成的审核记录

			for (int j = 1; j <= grade - 1; j++) {
				//查操作记录 如果没生成 则生成
				OperateRecord or = new OperateRecord();
				//用审核级别去查审核记录
				ManageRecord listManage = manageRecordDao.findFirstMrCsr(projId, stepIds[i], String.valueOf(j));
				//查对应的操作记录
				or = operateRecordDao.findByGread(projId, corpId, pro.getProjectType(), pro.getContributionMode(), stepIds[i], String.valueOf(j), "");
				if (or != null && listManage != null) {
					or.setOperateTime(listManage.getMrTime());//审核时间
					or.setGrade(docType.getGrade());//最大审核级别
					operateRecordDao.saveOrUpdate(or);//将已生成的操作记录回写
					ij++;//测试用
				} else if (listManage != null) {
					OperateRecord owfr = new OperateRecord();
					owfr.setOrId(IDUtil.getUniqueId(Constants.STANDARD));
					owfr.setProjectType(pro.getProjectType());            //工程类型
					owfr.setContributionMode(pro.getContributionMode());//出资方式
					owfr.setCorpId(pro.getCorpId());
					owfr.setProjId(pro.getProjId());                    //工程id
					owfr.setProjNo(pro.getProjNo());                    //工程编号
					owfr.setStepName(StepEnum.valueof(stepIds[i]).getMessage());
					//owfr.setOwfId(owf.getOwfId());					//标准操作流id
					owfr.setOpType(WorkFlowTypeEnum.MAIN_PROGRESS.getValue());
					owfr.setStepId(stepIds[i]);
					owfr.setGrade(String.valueOf(j));
					owfr.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
					owfr.setOperateCsr1(listManage.getMrCsr());            //操作人id
					Staff staff = staffDao.get(listManage.getMrCsr());
					owfr.setOperateTime(listManage.getMrTime());        //审核时间
					owfr.setOperater(staff.getStaffName());                //操作人
					owfr.setIsValid("1");//有效
					operateRecordDao.saveOrUpdate(owfr);
				}
			}
		}
	}

	/**
	 * 处理历史数据(批量处理)-点击按钮后作废
	 *
	 * @throws ParseException
	 * @author fuliwei
	 * @date 2018年11月18日
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateHistoryOperateRecordList(String projectType, String corpId) throws ParseException {
		// TODO Auto-generated method stub

		//把所有的工程查出来
		ProjectQueryReq projectQueryReq = new ProjectQueryReq();
		projectQueryReq.setCorpId(corpId);
		projectQueryReq.setProjectType(projectType);
		Map<String, Object> map = projectDao.queryHistoryProject(projectQueryReq);
		List<Project> list = (List<Project>) map.get("data");
		for (Project pro : list) {
			this.updateHistoryOperateRecord(pro.getProjId(), pro.getCorpId());//批量处理操作记录
		}
	}

	/**
	 * 处理施工计划
	 *
	 * @author fuliwei
	 * @date 2018年11月18日
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateHistoryConsPlan(String projId, String corpId) {
		// TODO Auto-generated method stub
		ConstructionPlan constructionPlan = constructionPlanDao.viewPlanById(projId);
		Project project = projectDao.get(projId);
		//现场代表
		if (StringUtil.isNotBlank(constructionPlan.getBuilder())) {
			//更新施工预算初审、资料审核、结算初审等操作记录 查配置
			this.updateAboutOperateRecord(project, StepEnum.PROJECT_PLAN.getValue(), constructionPlan.getBuilderId(), constructionPlan.getBuilder());
		}

		if (StringUtils.isNotBlank(constructionPlan.getManagementQaeId())) {
			//将开工报告、自检的待办人改为施工员
			this.updateConAboutOperateRecord(project, "110507", constructionPlan.getManagementQaeId(), constructionPlan.getManagementQae(), PostTypeEnum.CONSTRUCTION.getValue());
		}
		if (StringUtils.isNotBlank(constructionPlan.getCuPmId())) {
			//将设置的步骤待办人改为项目经理
			this.updateConAboutOperateRecord(project, "110507", constructionPlan.getCuPmId(), constructionPlan.getCuPm(), PostTypeEnum.CU_PM.getValue());
		}

		//资料员
		if (StringUtils.isNotBlank(constructionPlan.getDocumenterId())) {
			//将设置的步骤待办人改为资料员
			this.updateConAboutOperateRecord(project, "110507", constructionPlan.getDocumenterId(), constructionPlan.getDocumenter(), PostTypeEnum.BUSINESSASSISTANT.getValue());
		}


		//监理
		if (StringUtils.isNotBlank(constructionPlan.getSuJgjId())) {
			//预验收、募投初审一级
			this.updateConAboutOperateRecord(project, "110508", constructionPlan.getSuJgjId(), constructionPlan.getSuJgj(), PostTypeEnum.SUJGJ.getValue());
		}
		if (StringUtils.isNotBlank(constructionPlan.getSuCseId())) {
			//募投初审二级
			this.updateConAboutOperateRecord(project, "110508", constructionPlan.getSuCseId(), constructionPlan.getSuCse(), PostTypeEnum.SUCSE.getValue());
		}

	}

	/**
	 * 批量处理审核记录
	 *
	 * @author fuliwei
	 * @date 2018年11月18日
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateHistoryManagERecordList(String projectType, String corpId) throws ParseException {
		// TODO Auto-generated method stub

		//把所有的工程查出来
		ProjectQueryReq projectQueryReq = new ProjectQueryReq();

		projectQueryReq.setCorpId(corpId);
		projectQueryReq.setProjectType(projectType);
		Map<String, Object> map = projectDao.queryHistoryProject(projectQueryReq);
		List<Project> list = (List<Project>) map.get("data");
		for (Project pro : list) {
			this.updateHistoryManagERecord(pro.getProjId(), pro.getCorpId());//批量处理操作记录
		}

	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateHistoryConsPlanList(String projectType, String corpId) throws ParseException {
		// TODO Auto-generated method stub
		//把所有的工程查出来
		ProjectQueryReq projectQueryReq = new ProjectQueryReq();

		projectQueryReq.setCorpId(corpId);
		projectQueryReq.setProjectType(projectType);
		Map<String, Object> map = projectDao.queryHistoryProject(projectQueryReq);
		List<Project> list = (List<Project>) map.get("data");
		for (Project pro : list) {
			this.updateHistoryConsPlan(pro.getProjId(), pro.getCorpId());//批量处理施工计划
		}
	}


	/**
	 * 处理每个工程的待办人
	 *
	 * @author fuliwei
	 * @date 2018年11月18日
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateToder(String projId, String corpId) {
		Project pro = projectDao.get(projId);
		String stepId = WorkFlowActionEnum.byStatusCode(pro.getProjStatusId()).getActionCode();
		String nextStepId = workFlowService.queryAssistProgressStatusId(pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepId,
				true, WorkFlowTypeEnum.MAIN_PROGRESS.getValue(), null);
		//查非审核步骤的
	}


	@Override
	public String createOperateRecordNext(String orId, String projId, String projNo, String stepId, String stepName,
										  String remark, String StepId2) {
		//操作记录调用方法，将当前的置为已办，将下一步骤置为待办
		Boolean nextOrLastStepId = true;
		if (StringUtil.isBlank(remark) || "true".equals(remark)) {
			nextOrLastStepId = true;
		} else {
			nextOrLastStepId = false;
		}
		//取得登录人信息
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		//将当前的置为已办
		OperateRecordQueryReq req = new OperateRecordQueryReq();
		req.setProjId(projId);
		req.setStepId(stepId);
		req.setIsValid("1");    //有效
		req.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());//待办
		Map<String, Object> map = operateRecordDao.queryOperateRecordList(req);
		List<OperateRecord> list = (List<OperateRecord>) map.get("data");
		Project pro = projectDao.get(projId);

		LoginInfo login = SessionUtil.getLoginInfo();
		if (list != null && list.size() > 0) {
			//有操作记录标准，则生成记录
			OperateRecord owfr = list.get(0);
			owfr.setOperater(login.getStaffName());
			owfr.setOperateCsr1("," + login.getStaffId() + ",");
			owfr.setOperateDept1(login.getDeptId());
			owfr.setOperateTime(operateRecordDao.getDatabaseDate());
			owfr.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
			operateRecordDao.saveOrUpdate(owfr);
		} else {
			//走原来生成业务操作记录的方法
			OperateRecord operateRecord = new OperateRecord();
			operateRecord.setOrId(orId);
			operateRecord.setProjId(projId);
			operateRecord.setProjNo(projNo);
			operateRecord.setStepId(stepId);
			operateRecord.setStepName(stepName);
			operateRecord.setRemark(remark);
			operateRecord.setOperateTime(operateRecordDao.getDatabaseDate());
			operateRecord.setOperateCsr1("," + loginInfo.getStaffId() + ",");
			operateRecord.setOperater(loginInfo != null ? loginInfo.getStaffName() : "1");
			operateRecord.setOperateDept1(loginInfo != null ? loginInfo.getDeptId() : "1");
			operateRecord.setIsValid("1");//有效记录
			operateRecord.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
			operateRecord.setCorpId(pro.getCorpId());
			operateRecord.setProjectType(pro.getProjectType());
			operateRecord.setContributionMode(pro.getContributionMode());
			operateRecord.setOpType(WorkFlowTypeEnum.MAIN_PROGRESS.getValue());
			operateRecordDao.save(operateRecord);
		}

		//判断是否有配置 更新其他历史记录 如安顺受理申请，需更新用户回复等步骤 前面不加0_
		List<DataFilerSetUpDto> listData = Constants.getDataFilterMapByKey(login.getCorpId() + "_" + pro.getProjectType() + "_" + pro.getContributionMode() + "_" + stepId + "_0");
		if (listData != null && listData.size() > 0) {
			if (StringUtils.isNotBlank(listData.get(0).getSupSql())) {
				String[] stepIds = listData.get(0).getSupSql().split(",");
				for (int i = 0; i < stepIds.length; i++) {
					if (stepIds[i].indexOf("_") != -1) {
						//包含审核级别
						String grade = stepIds[i].substring(stepIds[i].length() - 1, stepIds[i].length());            //取最后一位审核级别
						String stepAuditId = stepIds[i].substring(0, stepIds[i].length() - 2);
						OperateRecord otherOr = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepAuditId, grade, OperateWorkFlowEnum.NOT_DONE.getValue());
						if (otherOr != null) {
							otherOr.setOperateCsr1("," + loginInfo.getStaffId() + ",");
							otherOr.setOperater(loginInfo != null ? loginInfo.getStaffName() : "1");
							operateRecordDao.saveOrUpdate(otherOr);
						}
					} else {
						OperateRecord otherOr = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepIds[i], null, OperateWorkFlowEnum.NOT_DONE.getValue());
						//查询操作记录
						otherOr.setOperateCsr1("," + loginInfo.getStaffId() + ",");
						otherOr.setOperater(loginInfo != null ? loginInfo.getStaffName() : "1");
						operateRecordDao.saveOrUpdate(otherOr);
					}

				}
			}
		}

		//用最初的stepId 查询他的下一个stepId;
		String nextStepId = workFlowService.queryAssistProgressStatusId(pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), StepId2,
				nextOrLastStepId, WorkFlowTypeEnum.MAIN_PROGRESS.getValue(), null);
		if (nextOrLastStepId) {
			//下一个置为待办
			req.setStepId(nextStepId);
			req.setModifyStatus(OperateWorkFlowEnum.NOT_DONE.getValue());
			Map<String, Object> nextMap = operateRecordDao.queryOperateRecordList(req);
			List<OperateRecord> nextList = (List<OperateRecord>) nextMap.get("data");

			if (nextList != null && nextList.size() > 0) {
				OperateRecord ofr = nextList.get(0);
				//如果下一步骤为审核步骤
				if (StringUtil.isNotBlank(ofr.getGrade())) {
					//将第一级审核置为待办
					//用corp_id,project_type,contribution_mode,step_id,grade,isValid查询第一级审核
					OperateRecord auditOperateRecord = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), nextStepId, "1", OperateWorkFlowEnum.NOT_DONE.getValue());
					if (auditOperateRecord != null) {
						auditOperateRecord.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());//审核一级置为待办
					}
					operateRecordDao.saveOrUpdate(auditOperateRecord);
					return auditOperateRecord.getOperater();
				} else {
					//下一步骤不是审核步骤 直接置为待办
					ofr.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());
					operateRecordDao.saveOrUpdate(ofr);
					//待办人
					return ofr.getOperater();
				}
			}
		} else {
			//上一个待办 nextStepId(上一个步骤)
			//查标准 以及已操作人  再生成待办操作记录
			OperateWorkFlowReq reqFlow = new OperateWorkFlowReq();
			//操作标准流程查询条件 查询数据库标准操作记录 如勘察派工应该谁做
			reqFlow.setCorpId(pro.getCorpId());
			reqFlow.setProjectType(pro.getProjectType());
			reqFlow.setContributionMode(pro.getContributionMode());
			reqFlow.setStepId(nextStepId);
			List<OperateWorkFlow> owfList = operateWorkFlowDao.queryListByReq(reqFlow);

			if (owfList != null && owfList.size() > 0) {
				OperateWorkFlow owf = owfList.get(0);
				OperateRecord owfr = new OperateRecord();

				owfr = this.getOperateWorkFlowRecord(pro, owf, OperateWorkFlowEnum.WAIT_DONE.getValue());
				//查上一个已办的操作记录 将操作人回置
				OperateRecord or = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), nextStepId, "", OperateWorkFlowEnum.HAVE_DONE.getValue());
				if (or != null) {
					owfr.setOperateCsr1(or.getOperateCsr1());
					owfr.setOperater(or.getOperater());
				}
				operateRecordDao.saveOrUpdate(owfr);
				return owfr.getOperater();
			}
		}

		return null;
	}

	/**
	 * 查询工作通知
	 *
	 * @param projNo
	 * @param projectType
	 * @throws Exception
	 */
	@Override
	public OperateRecord queryWorkNotice(String projNo, String stepId, String projectType) throws Exception {
		// TODO Auto-generated method stub
		OperateRecord operateRecord = operateRecordDao.queryWorkNotice(projNo, stepId, projectType);
		return operateRecord;
	}


	/**
	 * @MethodDesc: 生成施工派遣、监理派遣通知
	 * @Author zhangnx
	 * @Date 2019/1/21 10:59
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOperateRecord(String projId) {

		Project project = projectDao.get(projId);

		if (project != null) {
			List<Staff> staffList = staffDao.findStaffListByBelongCorpIdAndPost(project.getCorpId(), project.getSuId(), project.getCuId());

			List<String> postList = Arrays.asList("53,13,65".split(","));//项目经理、总监、专业监理师

			DataFilerSetUpDto config = Constants.isConfig(project.getCorpId() + "_110507_110508");
			if (config != null && StringUtil.isNotBlank(config.getSupSql())) {
				postList = Arrays.asList(config.getSupSql().split(","));
			}

			List<Staff> diffPostStaffList = diffPostStaffList(staffList, postList);//拼接相同职务的人员

			List<Staff> diffUnitStaffList = diffUnitStaffList(diffPostStaffList, project.getSuId());//拼接相同单位的人员

			if (diffUnitStaffList != null) {
				for (Staff s : diffUnitStaffList) {
					OperateRecord ord = new OperateRecord();
					if (StringUtils.isNotBlank(s.getDeptId()) && s.getDeptId().equals(project.getSuId())) {//监理单位
						ord.setStepId(StepEnum.SUPERVISION_DISPATCH.getValue());
						ord.setStepName(StepEnum.SUPERVISION_DISPATCH.getMessage());
						addOperateRecord(project, ord, s);

					} else {//施工单位
						ord.setStepId(StepEnum.CONSTRUCTION_DISPATCH.getValue());
						ord.setStepName(StepEnum.CONSTRUCTION_DISPATCH.getMessage());
						addOperateRecord(project, ord, s);
					}
				}
			}
		}
	}


	/**
	 * @MethodDesc: 添加操作记录
	 * @Author zhangnx
	 * @Date 2019/1/17 10:41
	 */
	public boolean addOperateRecord(Project project, OperateRecord ord, Staff s) {


		List<OperateRecord> ordList = operateRecordDao.findListByProjStepStatusId(project.getProjId(), ord.getStepId(), null);
		if (ordList != null && ordList.size() > 0) {
			OperateRecord or = ordList.get(0);
			String operateDept5 = or.getOperateDept5();
			if (StringUtil.isNotBlank(operateDept5) && (operateDept5.equals(project.getSuId()) || operateDept5.equals(project.getCuId()))) {//单位未改变
				return true;

			} else {//改变单位
				or.setIsValid("1");//有效
				or.setOperateCsr1("," + s.getStaffId());
				or.setOperater("," + s.getStaffName());
				or.setModifyStatus("2");
				or.setOperateDept5(s.getDeptId());
				operateRecordDao.saveOrUpdate(or);
				return true;
			}
		}

		ord.setOrId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONSTRUCTIONPLAN));
		ord.setProjId(project.getProjId());
		ord.setProjNo(project.getProjNo());
		ord.setProjName(project.getProjName());
		ord.setProjectType(project.getProjectType());
		ord.setCorpId(project.getCorpId());
		ord.setIsValid("1");//有效
		ord.setOperateCsr1("," + s.getStaffId());
		ord.setOperater("," + s.getStaffName());
		ord.setModifyStatus("2");
		ord.setOperateDept5(s.getDeptId());

		try {
			operateRecordDao.save(ord);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}


	/**
	 * @MethodDesc: 拼接相同职务的人员
	 * @Author zhangnx
	 * @Date 2019/1/17 10:35
	 */
	public List<Staff> diffPostStaffList(List<Staff> staffList, List<String> postList) {
		List<Staff> sList = new ArrayList<>();

		boolean staffListisNotNull = (staffList != null && staffList.size() > 0) ? true : false;
		boolean postListisNotNull = (postList != null && postList.size() > 0) ? true : false;

		if (staffListisNotNull && postListisNotNull) {

			for (String post : postList) {
				if (StringUtil.isBlank(post)) continue;

				Staff s = null;
				for (Staff staff : staffList) {
					if (StringUtil.isNotBlank(staff.getPost()) && staff.getPost().contains(","+post+",")) {
						if (s == null) s = new Staff();
						s.setStaffId(StringUtil.isNotBlank(s.getStaffId()) ? s.getStaffId() + staff.getStaffId() + "," : staff.getStaffId() + ",");
						s.setStaffName(StringUtil.isNotBlank(s.getStaffName()) ? s.getStaffName() + staff.getStaffName() + "," : staff.getStaffName() + ",");
						s.setDeptId(StringUtil.isNotBlank(staff.getDeptId()) ? staff.getDeptId() : s.getDeptId());
					}
				}
				if (s != null) sList.add(s);
			}
		}
		return sList;
	}

	/**
	 * @MethodDesc: 拼接相同单位分的人员
	 * @Author zhangnx
	 * @Date 2019/1/17 10:41
	 */
	public List<Staff> diffUnitStaffList(List<Staff> diffPostStaffList, String suId) {
		List<Staff> finalList = new ArrayList<>();
		if (diffPostStaffList != null && diffPostStaffList.size() > 0) {
			Staff suStaff = null;//监理单位
			Staff cuStaff = null;//施工单位

			for (Staff s : diffPostStaffList) {
				if (StringUtil.isNotBlank(suId) && suId.equals(s.getDeptId())) {//拼接监理单位
					if (suStaff == null) {
						suStaff = new Staff();
					}
					suStaff.setStaffId(StringUtil.isNotBlank(suStaff.getStaffId()) ? suStaff.getStaffId() + s.getStaffId() : s.getStaffId());
					suStaff.setStaffName(StringUtil.isNotBlank(suStaff.getStaffName()) ? suStaff.getStaffName() + s.getStaffName() : s.getStaffName());
					suStaff.setDeptId(StringUtil.isNotBlank(suStaff.getDeptId()) ? suStaff.getDeptId() : s.getDeptId());

				} else {//拼接施工单位
					if (cuStaff == null) {
						cuStaff = new Staff();
					}
					cuStaff.setStaffId(StringUtil.isNotBlank(cuStaff.getStaffId()) ? cuStaff.getStaffId() + s.getStaffId() : s.getStaffId());
					cuStaff.setStaffName(StringUtil.isNotBlank(cuStaff.getStaffName()) ? cuStaff.getStaffName() + s.getStaffName() : s.getStaffName());
					cuStaff.setDeptId(StringUtil.isNotBlank(cuStaff.getDeptId()) ? cuStaff.getDeptId() : s.getDeptId());
				}
			}

			if (suStaff != null) {//将拼接时的两个逗号替换成一个逗号
				suStaff.setStaffId(suStaff.getStaffId().replace(",,", ","));
				suStaff.setStaffName(suStaff.getStaffName().replace(",,", ","));
				finalList.add(suStaff);
			}
			if (cuStaff != null) {//将拼接时的两个逗号替换成一个逗号
				cuStaff.setStaffId(cuStaff.getStaffId().replace(",,", ","));
				cuStaff.setStaffName(cuStaff.getStaffName().replace(",,", ","));
				finalList.add(cuStaff);
			}
		}

		return finalList;
	}


	/**
	 * 工程一栏处查看操作历史
	 *
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> queryOperateRecordHistory(OperateRecordQueryReq req) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = operateRecordDao.queryOperateRecordList(req);
		List<OperateRecord> operateRecords = (List<OperateRecord>) map.get("data");
		if (operateRecords.size() > 0 && operateRecords != null) {
			Project project = projectDao.get(operateRecords.get(0).getProjId());
			for (OperateRecord operateRecord : operateRecords) {
				operateRecord.setProjName(project.getProjName());
			}
		}
		return map;
	}

	/**
	 * 主流程跨流程待办
	 */
	@Override
	public String updateCurStepActivNextStep(String orId, String projId,
											 String projNo, String curStep, String curStepMessage,
											 String remark, String nextStepId) {
		//修改当前步骤的操作记录为已办
		//取得登录人信息
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		OperateRecordQueryReq req = new OperateRecordQueryReq();
		req.setProjId(projId);
		req.setStepId(curStep);
		req.setIsValid("1");    //有效
		req.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());//待办
		Map<String, Object> map = operateRecordDao.queryOperateRecordList(req);
		List<OperateRecord> list = (List<OperateRecord>) map.get("data");
		Project pro = projectDao.get(projId);

		LoginInfo login = SessionUtil.getLoginInfo();
		if (list != null && list.size() > 0) {
			//有操作记录标准，则生成记录
			OperateRecord owfr = list.get(0);
			owfr.setOperater(login.getStaffName());
			owfr.setOperateCsr1("," + login.getStaffId() + ",");
			owfr.setOperateDept1(login.getDeptId());
			owfr.setOperateTime(operateRecordDao.getDatabaseDate());
			owfr.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
			operateRecordDao.saveOrUpdate(owfr);
		} else {
			//走原来生成业务操作记录的方法
			OperateRecord operateRecord = new OperateRecord();
			operateRecord.setOrId(orId);
			operateRecord.setProjId(projId);
			operateRecord.setProjNo(projNo);
			operateRecord.setStepId(curStep);
			operateRecord.setStepName(curStepMessage);
			operateRecord.setRemark(remark);
			operateRecord.setOperateTime(operateRecordDao.getDatabaseDate());
			operateRecord.setOperateCsr1("," + loginInfo.getStaffId() + ",");
			operateRecord.setOperater(loginInfo != null ? loginInfo.getStaffName() : "1");
			operateRecord.setOperateDept1(loginInfo != null ? loginInfo.getDeptId() : "1");
			operateRecord.setIsValid("1");//有效记录
			operateRecord.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
			operateRecord.setCorpId(pro.getCorpId());
			operateRecord.setProjectType(pro.getProjectType());
			operateRecord.setContributionMode(pro.getContributionMode());
			operateRecord.setOpType(WorkFlowTypeEnum.MAIN_PROGRESS.getValue());
			operateRecordDao.save(operateRecord);
		}
		//激活下一待办
		//下一个置为待办
		req.setStepId(nextStepId);
		req.setModifyStatus(OperateWorkFlowEnum.NOT_DONE.getValue());
		Map<String, Object> nextMap = operateRecordDao.queryOperateRecordList(req);
		List<OperateRecord> nextList = (List<OperateRecord>) nextMap.get("data");

		if (nextList != null && nextList.size() > 0) {
			OperateRecord ofr = nextList.get(0);
			if (ofr == null) {
				return "";
			}
			//如果下一步骤为审核步骤
			if (StringUtil.isNotBlank(ofr.getGrade())) {
				//将第一级审核置为待办
				//用corp_id,project_type,contribution_mode,step_id,grade,isValid查询第一级审核
				OperateRecord auditOperateRecord = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), nextStepId, "1", OperateWorkFlowEnum.NOT_DONE.getValue());
				if (auditOperateRecord != null) {
					auditOperateRecord.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());//审核一级置为待办
					operateRecordDao.saveOrUpdate(auditOperateRecord);
					return auditOperateRecord.getOperater();
				}
				return "";
			} else {
				//下一步骤不是审核步骤 直接置为待办
				ofr.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());
				operateRecordDao.saveOrUpdate(ofr);
				//待办人
				return ofr.getOperater();
			}
		}
		return "";
	}


	@Override
	public List<OperateRecord> findListByProjStepStatusId(String projId, String stepId, String status) {
		return operateRecordDao.findListByProjStepStatusId(projId, stepId, status);
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void nextDealtNotice(Project project, String stepId, String stepName, String operaterId, String operater) {
		boolean b = currentTableHaveData(project.getProjId(), stepId);
		List<OperateRecord> list = operateRecordDao.findListByProjStepStatusId(project.getProjId(), stepId, null);

		if (b) {
			if (list != null && list.size() > 0) {
				for (OperateRecord o : list) {
					o.setOperateCsr1(operaterId);
					o.setOperater(operater);
					operateRecordDao.saveOrUpdate(o);
				}
			}
		} else {//当前步骤没数据时才生成，避免下一个步骤已经操作之后上一个步骤又点保存就会更新下一个步骤的通知
			if (list != null && list.size() > 0) {
				for (OperateRecord o : list) {
					o.setIsValid("0");
					operateRecordDao.saveOrUpdate(o);
				}
			}
			OperateRecord o = new OperateRecord();
			o.setOrId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
			o.setProjId(project.getProjId());
			o.setProjNo(project.getProjNo());
			o.setProjName(project.getProjName());
			o.setProjectType(project.getProjectType());
			o.setContributionMode(project.getContributionMode());
			o.setOpType("1");
			o.setIsValid("1");//有效
			o.setModifyStatus("2");
			o.setStepId(stepId);
			o.setStepName(stepName);
			o.setCorpId(project.getCorpId());
			o.setOperateCsr1(operaterId);
			o.setOperater(operater);
			operateRecordDao.save(o);
		}
	}

	public boolean currentTableHaveData(String projId, String stepId) {
		if (StepEnum.TECHNOLOGYTELL.getValue().equals(stepId)) {//会审交底
			ConstructionWork cw = constructionWorkDao.get("projId", projId);
			return cw != null ? true : false;

		} else if (StepEnum.CONSTRUCTIONORGANIZATION.getValue().equals(stepId)) {//施工组织
			ConstructionOrganization co = constructionOrganizationDao.get("projId", projId);
			return co != null ? true : false;

		} else if (StepEnum.CONSTRUCTION.getValue().equals(stepId)) {//开工报告
			WorkReport wr = workReportDao.get("projId", projId);
			return wr != null ? true : false;

		} else if (StepEnum.DURING_CONSTRUCTION.getValue().equals(stepId)) {//试压记录
			StrengthTest st = strengthTestDao.get("projId", projId);
			return st != null ? true : false;
		}
		return false;
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void noticeSetInvalid(String projId, String stepId, String status) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		List<OperateRecord> oprList = operateRecordDao.findListByProjStepStatusId(projId, stepId, status);
		if (oprList != null && oprList.size() > 0) {
			for (OperateRecord o : oprList) {//待办通知置为已办
				o.setOperateCsr1("," + loginInfo.getStaffId() + ",");
				o.setOperater(loginInfo.getStaffName());
				o.setModifyStatus("1");
				o.setOperateTime(operateRecordDao.getDatabaseDate());
				operateRecordDao.saveOrUpdate(o);
			}
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void noticeSetInvalid(List<String> projIds, String stepId, String opType) {
		operateRecordDao.noticeSetInvalid(projIds,stepId,opType);
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateIsValid(String busOrderId, String opType) {
		operateRecordDao.updateIsValid(busOrderId, opType);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUpdateOperateRecord(Project pro, StaffDto staffDto, OperateRecord o) {
		o.setOrId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		o.setProjId(pro.getProjId());
		o.setProjNo(pro.getProjNo());
		o.setProjName(pro.getProjName());
		o.setIsValid("1");//有效
		o.setStepId(StepOutWorkflowEnum.FALLBACK_AUDIT.getValue());//回退审核
		o.setOpType(WorkFlowTypeEnum.MAIN_PROGRESS.getValue());//设置成流程，因为设置成辅流程待办无法跳转
		o.setCorpId(pro.getCorpId());
		o.setOperateCsr1("," + staffDto.getStaffId() + ",");
		o.setOperater(staffDto.getStaffName());
		operateRecordDao.save(o);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updateNextNotice(String businessOrderId, String auditLevel, boolean isUpdateNextNotice) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		int currentLeve = StringUtils.isNotBlank(auditLevel) ? Integer.parseInt(auditLevel) : 0;

		List<OperateRecord> oList = operateRecordDao.queryOperateRecordList(businessOrderId, StepOutWorkflowEnum.FALLBACK_AUDIT.getValue(), null, null);
		if (oList == null || oList.size() < 1) return false;

		for (OperateRecord o : oList) {
			int grade = StringUtil.isNotBlank(o.getGrade()) ? Integer.parseInt(o.getGrade()) : 0;
			if (currentLeve == grade) {//将当前级别的操作记录设置为已办
				o.setOperateCsr1(loginInfo.getStaffId());
				o.setOperater(loginInfo.getStaffName());
				o.setOperateTime(new Date());
				o.setModifyStatus("1");
				operateRecordDao.saveOrUpdate(o);
			}
			if (isUpdateNextNotice && currentLeve + 1 == grade) {//审核通过将下一级的置为待办
				o.setModifyStatus("2");
				operateRecordDao.saveOrUpdate(o);
			}
		}
		return true;
	}


	/**
	 * 生成当前步骤的待办事项
	 *
	 * @param
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void cerateCurOperateRecord(Project project, String stepId,String wfTypeId, String wfAssistTypeId, String busOrderId, Staff staff, String grade, boolean flag) {
		//查询上一待办变为已办
		operateRecordDao.updateIsDone(project, null, wfAssistTypeId, busOrderId, null);
		//生成待办
		if (flag) {
			OperateRecord operateRecord = new OperateRecord();
			//或者初始化待办
			//先根据公司ID、工程类型、出资方式查询
			//再根据公司ID、工程类型查询
			//最后根据公司ID查询
			OperateWorkFlowReq req = new OperateWorkFlowReq();
			req.setOpType(wfAssistTypeId);//流程类型
			req.setStepId(stepId); //步骤ID
			req.setCorpId(project.getCorpId());//分公司ID
			req.setProjectType(StringUtil.isNotBlank(project.getProjectType()) ? project.getProjectType() : "");//工程类型
			req.setContributionMode(StringUtil.isNotBlank(project.getContributionMode()) ? project.getContributionMode() : "");//出资方式
			if (StringUtil.isNotBlank(grade)) {
				req.setGrade(grade);//审核级别
			}
			List<OperateWorkFlow> operateWorkFlows = operateWorkFlowDao.queryListByReq(req);
			if (operateWorkFlows == null || operateWorkFlows.size() < 1) {
				req.setContributionMode(Constants.CONTRIBUTION_MODE);//出资方式
				operateWorkFlows = operateWorkFlowDao.queryListByReq(req);
			}
			if (operateWorkFlows == null || operateWorkFlows.size() < 1) {
				req.setContributionMode(Constants.CONTRIBUTION_MODE);//出资方式
				req.setProjectType(Constants.PROJECT_TYPE);//工程类型
				operateWorkFlows = operateWorkFlowDao.queryListByReq(req);
			}
			if (operateWorkFlows == null || operateWorkFlows.size() < 1) {
				req.setCorpId(Constants.PROJECT_TYPE);//工程类型
				req.setContributionMode(Constants.CONTRIBUTION_MODE);//出资方式
				req.setProjectType(Constants.PROJECT_TYPE);//工程类型
				operateWorkFlows = operateWorkFlowDao.queryListByReq(req);
			}





			if (operateWorkFlows != null && operateWorkFlows.size() > 0) {
				BeanUtils.copyProperties(operateWorkFlows.get(0), operateRecord);
				operateRecord.setProjId(project.getProjId());
				operateRecord.setProjNo(project.getProjNo());


				//待办人
				String toderId=operateWorkFlows.get(0).getOpereaterId();
				if (StringUtil.isNotBlank(toderId)) {
					String[] staffIds = toderId.split(",");
					List<Staff> staffList = staffDao.queryStaffListByIds(staffIds);
					if (staffList!=null && staffList.size()>0){
						for (Staff s:staffList) {
							if (StringUtils.isNotBlank(s.getDeptTransfer()) && s.getDeptTransfer().contains(project.getDeptTransfer())){
								operateRecord.setOperateCsr1(","+s.getStaffId()+",");
								operateRecord.setOperater(s.getStaffName());
							}
						}
					}
					if (StringUtils.isBlank(operateRecord.getOperateCsr1())){
						operateRecord.setOperateCsr1(operateWorkFlows.get(0).getOpereaterId());
						operateRecord.setOperater(operateWorkFlows.get(0).getOpereater());
					}

				} else {
					operateRecord.setOperateCsr1("," + staff.getStaffId() + ",");
					operateRecord.setOperater(StringUtil.isNotBlank(staff.getStaffName()) ? staff.getStaffName() : "");
				}


				operateRecord.setCorpId(project.getCorpId());//分公司ID
				operateRecord.setProjectType(StringUtil.isNotBlank(project.getProjectType()) ? project.getProjectType() : "");//工程类型
				operateRecord.setContributionMode(StringUtil.isNotBlank(project.getContributionMode()) ? project.getContributionMode() : "");//出资方式
				operateRecord.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());
				operateRecord.setIsValid("1");
				operateRecord.setOrId(IDUtil.getUniqueId(Constants.STANDARD));
				if (StringUtil.isNotBlank(busOrderId)) {
					operateRecord.setBusinessOrderId(busOrderId);
				}
				operateRecordDao.save(operateRecord);
			}
		}
	}


















	/**
	 * @MethodDesc: 回退处理
	 * @Author zhangnx
	 * @Date 2019/7/30 17:24
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String rollBackHandle(String projId,FallbackApply fa) {

		Project project = projectDao.get(projId);
		if (project == null) return "";
		String origStepId= WorkFlowActionEnum.getStepCodeByStatusCode(project.getProjStatusId());

		WorkFlow workFlow = workFlowService.queryWorkFlowCode(project.getCorpId(), project.getProjectType(), project.getContributionMode(), WorkFlowTypeEnum.MAIN_PROGRESS.getValue());
		if (workFlow == null) return "";

		//获取（回退到的步骤—>当前步骤）的stepId
        List<String> stepIds = ArraysUtil.splitStartToEndCodeTranslateList(workFlow.getWorkFlowCode(), fa.getFallbackStepId(), origStepId);

		//相关表数据处理
		 this.dataProcessingOfRelevantTables(projId,fa,stepIds);

		//主流程处理代办和审核记录
		String todoer = this.rollBackMainToDoAndAuditRecordHandle(projId, fa.getFallbackStepId(), stepIds);

		//辅流程代办处理
		this.rollBackAuxiliaryToDoHandle(projId, fa.getOriginalStepId(), fa.getFallbackStepId(), stepIds);

		//生成回退步骤的下一审核信息(方便看回退原因)
		this.createAuditRecord(projId, fa, stepIds);

		return todoer;
	}



    /**
     * @Description: 回退处理相关表数据
     * @author zhangnx
     * @date 2019/8/22 18:42
     */
    private boolean dataProcessingOfRelevantTables(String projId,FallbackApply fa,List<String> stepIds) {
        if (stepIds == null) return true;

		if (stepIds.contains(StepEnum.SURVEY_RESULT_REGISTER.getValue())){//回退到现场踏勘或踏勘前
			boolean b=surveyInfoService.rollBackContainsSurvey(projId,fa.getFallbackReason());
		}

		if (stepIds.contains(StepEnum.CONSTRUCT_CONTRACT.getValue())) {//回退到合同签订或签订前
            boolean b = contractService.rollBackContainsContract(projId, fa.getFallbackReason());
        }


        if (stepIds.contains(StepEnum.DESIGN_DRAWING.getValue())){//回退到设计出图或设计出图前
            boolean b=designInfoService.rollBackContainsSurvey(projId,fa.getFallbackReason());
        }


        if (stepIds.contains(StepEnum.BUDGET_RESULT_REGISTER.getValue())){//回退到预算记录或预算记录前
            boolean b=budgetService.rollBackContainsBudget(projId,fa.getFallbackReason());
        }

		if (stepIds.contains(StepEnum.PROJECT_PLAN.getValue())) {//回退到计划或计划前
			rollBackContainsPlan(projId, fa);
		}

        if (stepIds.contains(StepEnum.QUALITIES_DECLARATION.getValue())){//回退到施工预算或施工预算前
            boolean b=subBudgetService.rollBackContainsSubBudget(projId,fa.getFallbackReason());
        }

        if (stepIds.contains(StepEnum.QUALITIES_DECLARATION.getValue())){//回退到施工合同或施工合同前
            subContractService.rollBackContainsSubContract(projId,fa.getFallbackReason());
        }

		if (stepIds.contains(StepEnum.SELF_CHECK.getValue())){//回退到工程自检或工程自检前
			boolean b=selfInspectionListService.rollBackContainsSelfInspectionList(projId,fa.getFallbackReason());//自检
			boolean c=completeReportService.rollBackCompleteReport(projId,fa.getFallbackReason());//竣工报告
		}

		if (stepIds.contains(StepEnum.PRE_INSPECTION.getValue())){//回退到预验收或预验收前
			boolean b=preinspectionService.rollBackContainsPreinspection(projId,fa.getFallbackReason());
		}

		if (stepIds.contains(StepEnum.COMPLETION_DATA_APPLY.getValue())){//回退到资料申请或资料验收申请前
			boolean b=dataAcceptanceService.rollBackContainsDataAcceptance(projId,fa.getFallbackReason());
		}

		if (stepIds.contains(StepEnum.UNION_PRE_INSPECTION.getValue())){//回退到联合验收或联合验收前
			boolean b=jointAcceptanceService.rollBackContainsJointAcceptance(projId,fa.getFallbackReason());
		}

        if (stepIds.contains(StepEnum.SETTLEMENT_REPORT.getValue())){//回退到结算报审或结算报审前
            boolean b=settlementDeclarationService.rollBackContainsSettlementDeclaration(projId,fa.getFallbackReason());
        }

		if (stepIds.contains(StepEnum.REPORT_DONE_CONFIRM.getValue())){//回退到终审确认或终审确认前
			
		}

        return true;
    }

    @Resource
    SurveyInfoService surveyInfoService;
    /**
    * @Description: 回退到计划处理
    * @author zhangnx
    * @date 2019/8/23 12:33
    */
    public boolean rollBackContainsPlan(String projId,FallbackApply fa){

        boolean b2 = constructionWorkService.delBackupsConstructionWork(projId,fa.getFallbackReason());//删除并备份交底记录

        boolean b3 = constructionOrganizationService.delBackupsConstructionOrganization(projId,fa.getFallbackReason());//删除并备份施工组织记录

        boolean b4 = workReportService.delBackupsWorkReport(projId,fa.getFallbackReason());//删除并备开工报告记录

        return true;
    }





    /**
     * @Description: 回退：将（当前步骤—>回退步骤）之间的操作记录、审核记录置为无效，并生成新的操作记录
     * @author zhangnx
     * @date 2019/8/22 14:01
     */
    private String rollBackMainToDoAndAuditRecordHandle(String projId, String fallBackStepId, List<String> stepIds) {

		String todoer="";
		Project proj = projectDao.get(projId);
		if (proj==null || stepIds==null || stepIds.size()<1) return todoer;
		List<OperateWorkFlow> owfList=operateWorkFlowDao.queryOperateWorkFlowList(proj.getCorpId(),proj.getProjectType(),proj.getContributionMode(),stepIds);
		if (owfList==null || owfList.size()<1) return todoer;

        manageRecordDao.backSetAuditRecordInValid(projId, stepIds); //将原来的审核记录置为无效
        operateRecordDao.backSetToDoInValid(projId, stepIds);//将原来的待办置为无效

		for (OperateWorkFlow owf: owfList) {//生成新的操作记录
			OperateRecord or = new OperateRecord();
			or.setStepName(owf.getStepName() + ("(回退)"));

			//处理操作人
			if (StringUtils.isNotBlank(owf.getOpereaterId())){
				or.setOperateCsr1(owf.getOpereaterId());
				or.setOperater(owf.getOpereater());

			}else {//操作人为空
				OperateRecord record = operateRecordDao.queryOperater(projId, owf.getStepId(), owf.getGrade());
				if (record!=null && StringUtils.isNotBlank(record.getOperateCsr1())){

					String operateId = record.getOperateCsr1().trim();
					String firstSubStr = operateId.substring(0, 1);
					String endSubstr = operateId.substring(operateId.length()-1);

					if (",".equals(firstSubStr) && ",".equals(endSubstr)){
						or.setOperateCsr1(record.getOperateCsr1());
					}else {
						or.setOperateCsr1(","+record.getOperateCsr1()+",");
					}
					or.setOperater(record.getOperater());
				}
			}

			//处理待办状态
			if (StringUtils.isNotBlank(fallBackStepId) && fallBackStepId.equals(owf.getStepId())){//回退到的步骤置为待办
				if (StringUtils.isNotBlank(owf.getGrade()) && !"1".equals(owf.getGrade())){//回退到的步骤是审核非一级置为未办
					or.setModifyStatus(OperateWorkFlowEnum.NOT_DONE.getValue());
				}else {//待办
					or.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());
				}
			}else {//未办
				or.setModifyStatus(OperateWorkFlowEnum.NOT_DONE.getValue());
			}

			this.saveOperateRecordDao(or,owf,proj);
		}

		List<OperateRecord> todoerList = operateRecordDao.findListByProjStepStatusId(projId, fallBackStepId, "2");
		if (todoerList!=null){
			todoer=todoerList.get(0).getOperater();
		}

		return todoer;
    }



	private void saveOperateRecordDao(OperateRecord or, OperateWorkFlow owf, Project proj) {
		or.setOrId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		or.setProjId(proj.getProjId());
		or.setProjNo(proj.getProjNo());
		or.setStepId(owf.getStepId());
		or.setCorpId(owf.getCorpId());
		or.setOpType(owf.getOpType());
		or.setGrade(owf.getGrade());
		or.setProjectType(owf.getProjectType());
		or.setContributionMode(owf.getContributionMode());
		or.setIsValid("1");//设置有效
		operateRecordDao.save(or);
	}



	/**
	 * @Description: 回退处理辅助流程代办
	 * @author zhangnx
	 * @date 2019/8/22 13:59
	 */
	private boolean rollBackAuxiliaryToDoHandle(String projId, String currentStepId, String fallBackStepId, List<String> stepIds) {

		if (stepIds == null) return false;


		if (stepIds.contains(StepEnum.PROJECT_PLAN_AUDIT.getValue())) {//回退到计划或计划前：将监理派遣、施工派遣、会审交底、施工组织、开工报告待办置为无效
			List<String> stepId = new ArrayList<>();

			stepId.add(StepEnum.CONSTRUCTION_DISPATCH.getValue());
			stepId.add(StepEnum.SUPERVISION_DISPATCH.getValue());
			stepId.add(StepEnum.CONSTRUCTION.getValue());
			stepId.add(StepEnum.CONSTRUCTIONORGANIZATION.getValue());
			stepId.add(StepEnum.TECHNOLOGYTELL.getValue());

			operateRecordDao.backSetToDoInValid(projId, stepId);
		}


		if (stepIds.contains(StepEnum.SELF_CHECK.getValue())){//回退到自检或自检前：将竣工报告待办置为无效
			List<String> stepId = new ArrayList<>();
			stepId.add(StepEnum.COMPLETED_REPORT.getValue());
			operateRecordDao.backSetToDoInValid(projId, stepId);
		}


		return true;
	}



    /**
     * @Description: 生成回退步骤的下一审核信息(离回退步骤最近的审核)->方便看回退原因
     * @author zhangnx
     * @date 2019/8/22 19:46
     */
    private boolean createAuditRecord(String projId,FallbackApply fa, List<String> stepIds) {

        String nextAuditStepId = "";
        if (stepIds != null && StringUtil.isNotBlank(fa.getFallbackStepId())) {
            for (String stepId : stepIds) {
                if (docTypeDao.isAuditStep(stepId)) {
                    nextAuditStepId = stepId;
                    continue;
                }
            }

            ManageRecord manageRecord = new ManageRecord();
            if (fa != null) {
                manageRecord.setBusinessOrderId(fa.getFaId());
                manageRecord.setMrAopinion(fa.getFallbackReason());    //回退原因
                manageRecord.setMrCsr(fa.getApplyOperatorId());        //退回申请人即审核人
            }
            DocType docType = docTypeService.findByStepId(fa.getOriginalStepId());
            manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));//审核记录主键id
            manageRecord.setDocTypeId(docType != null ? docType.getId() : "");
            manageRecord.setStepId(nextAuditStepId);
            manageRecord.setProjId(projId);
            manageRecord.setMrTime(manageRecordDao.getDatabaseDate());        //审核时间即退回申请时间
            manageRecord.setFlag("0");                                        //默认为无效
            manageRecord.setMrResult("0");                                    //不通过
        }
        return true;

    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void noticeSuReport(String businessOrderId,Project pro, boolean noticeFlag, String stepId) {
		if(pro == null){
			return;
		}
		//查询监理评估报告待办
		OperateRecordQueryReq req=new OperateRecordQueryReq();
		req.setProjId(pro.getProjId());
		req.setStepId(stepId);
		req.setBusinessOrderId(businessOrderId);
		req.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());//待办
		List<OperateRecord> list = operateRecordDao.queryOperateRecords(req);
		if((list == null || list.size()<1) && noticeFlag){
			//生成待办
			OperateRecord operateRecord = new OperateRecord();
			operateRecord.setOrId(IDUtil.getUniqueId(Constants.STANDARD));//主键ID
			operateRecord.setProjId(pro.getProjId());//工程ID
			operateRecord.setProjNo(pro.getProjNo());//工程编号
			operateRecord.setStepId(stepId);//菜单ID
			operateRecord.setOpType("2"); // 流程类型 2表示副流程
			// 业务单Id,
			operateRecord.setBusinessOrderId(businessOrderId); 
			if(StepEnum.valueof(stepId)!=null){
				operateRecord.setStepName(StepEnum.valueof(stepId).getMessage()+":请上传监理评估报告。");
			}else if(StepOutWorkflowEnum.valueof(stepId)!=null){
				operateRecord.setStepName(StepOutWorkflowEnum.valueof(stepId).getMessage() + ":请上传监理评估报告。");
			}else{
				Menu menu = menuDao.get(stepId);
				if(menu!=null){
					operateRecord.setStepName(menu.getMenuName() + ":请上传监理评估报告。");
				}
			}
			operateRecord.setIsValid("1");//有效
			operateRecord.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());//待办
			//代办人监理单位资料员
			Staff staff = constructionPlanService.findbusUnitOperator(pro.getSuId(),pro.getCorpId(),PostTypeEnum.BUSINESSASSISTANT.getValue());
			if(staff == null || StringUtil.isBlank(staff.getStaffId())){
				//无资料员，则通知监理人员传递
				operateRecord.setOperater(pro.getSuJgj());
				operateRecord.setOperateCsr1(","+pro.getSuJgjId()+",");
			}else{
				operateRecord.setOperater(staff.getStaffName());
				operateRecord.setOperateCsr1("," + staff.getStaffId() + ",");
			}
			operateRecordDao.save(operateRecord);
		}
	}
	/**
	 * 查询所有的待办
	 * @author fuliwei
	 * @date 2019/8/26
	 */
	@Override
	public List<Map<String, Object>> queryOperateRecordTodo() {
		return operateRecordDao.queryOperateRecordTodo();
	}


	@Override
	public String queryTodoer(String businessId) {
		String todoer=operateRecordDao.queryTodoer(businessId);
		return todoer;
	}


	/**
	* @ Description: 工程恢复
	* @ author zhangnx
	* @ date 2019/11/20 16:06
	**/
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ResultInfo recoveryProject(AbandonedRecord abandonedRecord) {

		Project project = projectDao.get(abandonedRecord.getProjId());
		if (project==null){
			return new ResultInfo("4000","没找到工程！");
		}
		String projStatusId = project.getProjStatusId();
		if (!ProjStatusEnum.TERMINATION.getValue().equals(project.getProjStatusId())){//不是终止得工程
			return new ResultInfo("4000","该工程不是【终止】的工程不能做恢复操作！");
		}

		WorkFlow workFlow = workFlowService.queryWorkFlowCode(project.getCorpId(), project.getProjectType(), project.getContributionMode(), WorkFlowTypeEnum.MAIN_PROGRESS.getValue());
		if (workFlow==null || org.apache.commons.lang.StringUtils.isBlank(workFlow.getWorkFlowCode())) {
			return new ResultInfo("4000","没找到工作流！");
		}

		String[] codeArr = workFlow.getWorkFlowCode().split(",");
		//获取当前步骤的上一个步骤
		String  preStepId= ArraysUtil.translateSearch(Arrays.asList(codeArr), abandonedRecord.getStepId(), -1);
		List<OperateRecord> operateRecordList = operateRecordDao.findListByStepId(abandonedRecord.getProjId(), preStepId);
		if (operateRecordList!=null && operateRecordList.size()>0){
			for (OperateRecord o:operateRecordList) {
				//如果上一个步骤的操作时间为空说明上一个步骤还未操作过
				if (o.getOperateTime()==null){
					return new ResultInfo("300","不能恢复到【"+StepEnum.getNameByCode(abandonedRecord.getStepId())+"】,原来【"+StepEnum.getNameByCode(preStepId)+"】还未操作过！");
				}
			}
		}
		int i = ArraysUtil.searchPosition(codeArr, abandonedRecord.getStepId());
		codeArr = Arrays.copyOfRange(codeArr, i, codeArr.length + 1);
		//将恢步骤的后面步骤置为未办
		operateRecordDao.recoveryIsValid(project.getProjId(),codeArr);

		//将当前步骤置为待办
		String todoer=this.handleCurrentStepId(project.getProjId(),abandonedRecord.getStepId());
		project.setToDoer(todoer);
		project.setProjStatusId(WorkFlowActionEnum.getStatusCodeByStepCode(abandonedRecord.getStepId()));
		projectDao.update(project);
		abandonedRecordService.saveAbandonedRecord(abandonedRecord.getProjId(),abandonedRecord.getProjId(),abandonedRecord.getStepId(),abandonedRecord.getAbandonedReason());

		return new ResultInfo("200","恢复成功！");
	}



	private String handleCurrentStepId(String projId, String stepId) {
		List<OperateRecord> operateRecordList = operateRecordDao.findListByStepId(projId, stepId);
		if (operateRecordList==null && operateRecordList.size()<1){
			return null;
		}

		for (OperateRecord o:operateRecordList) {
			if (StringUtils.isBlank(o.getGrade())){//非审核步骤
				operateRecordDao.recoverySetTodoer(o.getProjId(),o.getStepId(),null);
				return o.getOperater();
			}else {
				if ("1".equals(o.getGrade())){//是审核步骤将1级审核置为待办
					operateRecordDao.recoverySetTodoer(o.getProjId(),o.getStepId(),o.getGrade());
					return o.getOperater();
				}
			}
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createWxOperateRecord(String projId, String projNo, String stepId, String stepName, String remark,
										String todoer, String todoerId, String modifyStatus,String corpId,String projectType,String contributionMode) {
		OperateRecord operateRecord = new OperateRecord();
		operateRecord.setOrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
		operateRecord.setProjId(projId);
		operateRecord.setProjNo(projNo);
		operateRecord.setStepId(stepId);
		operateRecord.setStepName(stepName);
		operateRecord.setRemark(remark);
		if(OperateWorkFlowEnum.HAVE_DONE.getValue().equals(modifyStatus)){
			operateRecord.setOperateTime(operateRecordDao.getDatabaseDate());
		}

		operateRecord.setOperater(todoer);
		operateRecord.setOperateCsr1(todoerId);
		operateRecord.setIsValid("1");//有效记录
		operateRecord.setModifyStatus(modifyStatus);
		operateRecord.setCorpId(corpId);
		operateRecord.setProjectType(projectType);
		operateRecord.setContributionMode(contributionMode);
		operateRecord.setOpType(WorkFlowTypeEnum.MAIN_PROGRESS.getValue());
		operateRecordDao.save(operateRecord);
	}


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void changeBudgeterTodo(String projId, String orgBudgeterAuditId, String staffId, String staffName) {
        operateRecordDao.changeBudgeterTodo(projId,orgBudgeterAuditId,staffId,staffName);
    }
}
