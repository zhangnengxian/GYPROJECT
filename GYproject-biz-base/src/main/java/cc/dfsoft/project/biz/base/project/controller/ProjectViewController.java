package cc.dfsoft.project.biz.base.project.controller;

import cc.dfsoft.project.biz.base.accept.service.ProjectApplicationService;
import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.baseinfo.service.CorrelationService;
import cc.dfsoft.project.biz.base.budget.dto.MaterialListQueryReq;
import cc.dfsoft.project.biz.base.budget.dto.ProjCostSummaryReq;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.budget.enums.BudgetCostTypeEnum;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.budget.service.ChangeManagementService;
import cc.dfsoft.project.biz.base.budget.service.GovAuditCostService;
import cc.dfsoft.project.biz.base.budget.service.MaterialListService;
import cc.dfsoft.project.biz.base.complete.dao.JointAcceptanceDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.CompleteReportDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.project.biz.base.constructmanage.entity.EngineeringVisa;
import cc.dfsoft.project.biz.base.constructmanage.enums.EngineeringTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.EngineeringVisaService;
import cc.dfsoft.project.biz.base.constructmanage.service.WorkReportService;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.enums.ChangeReasonEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractMethodEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.contract.enums.PayTypeSCEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.contract.service.PayTypeService;
import cc.dfsoft.project.biz.base.design.dto.DrawingsDirectoryReq;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.entity.SurveyInfo;
import cc.dfsoft.project.biz.base.design.enums.DesignStationTypeEnum;
import cc.dfsoft.project.biz.base.design.enums.FeedbackStateEnum;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.design.service.DesignDrawingService;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.design.service.SurveyInfoService;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dto.*;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.*;
import cc.dfsoft.project.biz.base.project.service.*;
import cc.dfsoft.project.biz.base.settlement.service.SettlementDeclarationService;
import cc.dfsoft.project.biz.base.subpackage.dto.SubContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.service.SubBudgetService;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractService;
import cc.dfsoft.project.biz.base.subpackage.service.SubQuantitiesService;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.log.service.WebserviceLogService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.util.ExcelXlsxUtil;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 工程一览
 * @author pengtt
 * @createTime 2016-06-21
 */
@Controller
@RequestMapping(value="/projectView")
public class ProjectViewController {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ProjectViewController.class);

	/**工程服务接口*/
	@Resource
	ProjectService projectService;

	/**工程明细服务接口*/
	@Resource
	ScaleDetailService scaleDetailService;

	/**附件清单服务接口*/
	@Resource
	AccessoryService accessoryService;

	/**勘察信息服务接口*/
	@Resource
	SurveyInfoService  surveyInfoService;

	/**设计信息服务接口*/
	@Resource
	DesignInfoService designInfoService;

	@Resource
	DesignDrawingService designDrawingService;

	/**预算总表服务接口*/
	@Resource
	BudgetService budgetService;

	/**施工合同服务接口*/
	@Resource
	ContractService contractService;

	/**施工计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;

	/**分包协议服务接口*/
	@Resource
	SubContractService subContractService;

	/**分包质量服务接口*/
	@Resource
	SubQuantitiesService subQuantitiesService;

	/**管理记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;

	/**操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;

	/**立项调查表*/
	@Resource
	ProjectApplicationService projectApplicationService;

	/**业务合作伙伴*/
	@Resource
	BusinessPartnersService businessPartnersService;

	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;

	/** 付款方式接口*/
	@Resource
	PayTypeService payTypeService;

	/**关联关系服务接口*/
	@Resource
	CorrelationService correlationService;
	@Resource
	ProjectTypeService projTypeService;
	/** 业务合作伙伴Dao*/
	@Resource
	BusinessPartnersDao businessPartnersDao;
	@Resource
	StaffService staffService;
	/**结算报审 */
	@Resource
	SettlementDeclarationService settlementDeclarationService;
	/**政府预算审定*/
	@Resource
	GovAuditCostService govAuditCostService;
	@Resource
	ContributionModeService contributionModeService;
	@Resource
	WorkReportService workReportService;
	@Resource
	SubBudgetService subBudgetService;
	@Resource
	JointAcceptanceDao jointAcceptanceDao;
	/**竣工报告*/
	@Resource
	CompleteReportDao completeReportDao;
	@Resource
	MaterialListService materialListService;
	@Resource
	WebserviceLogService webserviceLogService;

	@Resource
	ChangeManagementService changeManagementService;
	@Resource
	EngineeringVisaService engineeringVisaService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("corpId",SessionUtil.getLoginInfo().getCorpId());
		modelView.addObject("groupCompany",DeptTypeEnum.GROUP_COMPANY.getInitVal());
		modelView.setViewName("project/view/projectView");
		return modelView;
	}

	/**
	 * 规模图表统计页面
	 * @return
	 */
	@RequestMapping(value="/scaleStatistic")
	public ModelAndView scaleStatistic(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("project/detail/projectScaleStatistic");
		return modelView;
	}

	/**
	 * 工程进度图表统计页面
	 * @return
	 */
	@RequestMapping(value="/scheduleStatistic")
	public ModelAndView scheduleStatistic(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("project/detail/projectScheduleStatistic");
		return modelView;
	}

	/**
	 * 施工进度图表统计页面
	 * @return
	 */
	@RequestMapping(value="/projectDetailconsSchedule")
	public ModelAndView constructionSchedule(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projId", request.getParameter("projId"));
		modelView.setViewName("project/view/projectDetailConsSchedule");
		return modelView;
	}


	/**
	 * 资金图表统计页面
	 * @return
	 */
	@RequestMapping(value="/capitalStatistic")
	public ModelAndView capitalStatistic(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("project/detail/projectCapitalStatistic");
		return modelView;
	}

	/**
	 * 弹出搜索
	 * @author fuliwei
	 * @createTime 2016-7-12
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() throws Exception{
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", projTypeService.queryAllList());
		modelview.addObject("contributionMode", contributionModeService.queryAllList());
		modelview.addObject("area", AreaEnum.values());
		//modelview.addObject("projStatusId", ProjStatusEnum.values());
		modelview.addObject("projStatusId", ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_SURVEY.getValue(),ProjStatusEnum.TERMINATION.getValue()));
		modelview.addObject("feedbackState", FeedbackStateEnum.values());
		modelview.addObject("designStationId",DesignStationTypeEnum.values());
		BusinessPartnersReq cubq=new BusinessPartnersReq();
		cubq.setUnitType(UnitTypeEnum.CONSTRUCTION_UNIT.getValue());
		Map<String, Object> map=businessPartnersService.queryBusinessPartners(cubq);
		List culist=(List) map.get("data");//
		modelview.addObject("culist",culist);	//分包单位
/*
		List manageOfficeList =departmentService.queryManagementOffice();
		modelview.addObject("manageOfficeList",manageOfficeList);	//施工管理处
*/
		modelview.setViewName("project/view/projectGlobalViewSearchPopPage");
		return modelview;
	}

	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author fuliwei
	 * @createTime 2016-07-12
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setHomePageRole(ProjStatusEnum.TO_DETERMINE_COST.getValue());
			Map<String,Object> map = projectService.queryProject(projectQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 弹出综合详述页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/detailPage")
	public ModelAndView detailPage(HttpServletRequest request){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("customerServiceCenter", DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());
		modelView.addObject("marketDevelopmentDepartment", DeptDivideEnum.MARKET_DEVELOPMENT_DEPARTMENT.getValue());
		modelView.addObject("gasCompany", DeptDivideEnum.GAS_COMPANY.getValue());
		modelView.addObject("loginDeptDivide", departmentService.getLoginDeptDivide());
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		BusinessPartners businessPartners = businessPartnersDao.get(loginInfo.getDeptId());
		String businessPartnersType = "-1";
		if(null!=businessPartners){
			businessPartnersType = businessPartners.getUnitType();
		}
		modelView.addObject("businessPartnersType", businessPartnersType);
		modelView.addObject("constructionUnit", UnitTypeEnum.CONSTRUCTION_UNIT.getValue());
		modelView.addObject("supervisionUnit", UnitTypeEnum.SUPERVISION_UNIT.getValue());
		modelView.addObject("projId", request.getParameter("projId"));
		//获取隐藏配置信息
		//key规则：部门ID_hidden_菜单ID(没有菜单ID则为0)
		List<DataFilerSetUpDto> deptFilter = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+Constants.HIDDEN_CONFIG+"_"+"0");
		if(deptFilter!=null && deptFilter.size()>0){
			modelView.addObject("hiddenConfig",deptFilter.get(0).getSupSql());
		}

		//按人员过滤数据 如财务部只能看合同价
		List<DataFilerSetUpDto> staffFilter = Constants.getDataFilterMapByKey(loginInfo.getStaffId()+"_"+Constants.HIDDEN_CONFIG+"_"+"0");
		if(staffFilter!=null && staffFilter.size()>0){
			modelView.addObject("staffRemoveClass",staffFilter.get(0).getSupSql());
		}
		modelView.setViewName("project/view/projectDetailPage");
		return modelView;
	}

	/**
	 * 弹出成本控制表
	 * @author fuliwei
	 * @createTime 2017年4月27日
	 * @param
	 * @return
	 */
	@RequestMapping(value="/costDetailPage")
	public ModelAndView costDetailPage(HttpServletRequest request){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("projId", request.getParameter("projId"));
		modelView.setViewName("project/view/projectCostDetailPage");
		return modelView;
	}

	/**
	 * 工程详述信息查询
	 * @param projId
	 * @author pengtt
	 * @return
	 */
	@RequestMapping(value="/projectDetail")
	@ResponseBody
	public Project projectDetail(String projId){
		try {
			Project project= projectService.viewProject(projId);
			project = projectService.viewDetial(project);
			return project;
		} catch (Exception e) {
			logger.error("工程详述明细查询失败！", e);
			return null;
		}
	}
	/**
	 * 工程综合详述------工程基础信息页面
	 * @author pengtt
	 * @createTime 2016-07-12
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/projectDetailBase")
	public ModelAndView projectDetailBase(HttpServletRequest request){

		ModelAndView modelView = new ModelAndView();
		modelView.addObject("projLtype", ProjLtypeEnum.values());
		modelView.addObject("area", AreaEnum.values());
		modelView.addObject("projStatus", ProjStatusEnum.values());//工程状态
		modelView.addObject("projSource", ProjSourceEnum.values());//受理来源
		modelView.addObject("changeReason", ChangeReasonEnum.values());//变动原因
		modelView.addObject("backReason", BackReasonEnum.values());//退单原因
		modelView.setViewName("project/view/projectDetailBase");
		return modelView;
	}

	/**
	 * 工程明细条件查询
	 * @param scaleDetailQueryReq
	 * @author pengtt
	 * @createTime 2016-07-12
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryScaleDeatil")
	@ResponseBody
	public Map<String,Object> queryScaleDeatil(HttpServletRequest request,ScaleDetailQueryReq scaleDetailQueryReq){
		try {
			scaleDetailQueryReq.setSortInfo(request);
			Map<String,Object> map = scaleDetailService.queryScaleDetailCommon(scaleDetailQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("综合详述工程明细查询失败！", e);
			return null;
		}
	}

	/**
	 * 附件列表查询
	 * @author pengtt
	 * @createTime 2016-07-12
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryAccessoryList")
	@ResponseBody
	public Map<String, Object> queryAccessoryList(HttpServletRequest request,AccessoryQueryReq accessoryQueryReq) {
		accessoryQueryReq.setSortInfo(request);
		if("stepDes".equals(accessoryQueryReq.getSortName())){
			accessoryQueryReq.setSortName("stepId");
		}
		return  accessoryService.queryAccessoryList(accessoryQueryReq);
	}

	/**
	 * 工程综合详述------勘察信息
	 * @author pengtt
	 * @createTime 2016-07-12
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/projectDetailSurvey")
	public ModelAndView projectDetailSurvey(HttpServletRequest request){

		ModelAndView modelView = new ModelAndView();
		List<SurveyInfo> surveyInfos = surveyInfoService.findByProjId(request.getParameter("projId"));
		if(surveyInfos!=null && surveyInfos.size()>0){
			modelView.addObject("surveyInfo", surveyInfos.get(0));//放入勘察信息对象
		}
		modelView.setViewName("project/view/projectDetailSurvey");
		return modelView;
	}

	/**
	 * 工程综合详述------设计信息
	 * @author pengtt
	 * @createTime 2016-07-12
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/projectDetailDesignInfo")
	public ModelAndView projectDetailDesignInfo(HttpServletRequest request){

		ModelAndView modelView = new ModelAndView();
		DesignInfo designInfo = designInfoService.queryById(request.getParameter("projId"));
		if(designInfo!=null){
			modelView.addObject("designInfo", designInfo);//放入设计信息对象
		}
		modelView.setViewName("project/view/projectDetailDesignInfo");
		return modelView;
	}

	/**
	 * 图纸列表查询
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @param request
	 * @param pageSortReq
	 * @return
	 */
	@RequestMapping(value="/queryDrawDirectory")
	@ResponseBody
	public Map<String, Object> queryDrawDirectory(HttpServletRequest request,DrawingsDirectoryReq pageSortReq) {
		try {
			pageSortReq.setSortInfo(request);
			Map<String, Object> map = designDrawingService.queryDrawDirectory(pageSortReq);
			return map;
		} catch (Exception e) {
			logger.error("综合详述图纸列表查询失败！", e);
			return null;
		}
	}

	/**
	 * 工程综合详述------预算总表信息
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/projectDetailBudget")
	public ModelAndView projectDetailBudget(HttpServletRequest request){
		ModelAndView modelView = new ModelAndView();
		try {
			Budget budget = budgetService.queryBudgeByprojId(request.getParameter("projId"));
			if(budget!=null){
				modelView.addObject("budget", budget);	//放入预算总表信息对象
			}
		} catch (Exception e) {
			logger.error("工程综合详述预算总表查询失败！", e);
		}
		modelView.addObject("costType",BudgetCostTypeEnum.values());//费用类型
		modelView.setViewName("project/view/projectDetailBudget");
		return modelView;
	}

	/**
	 * 单位工程费用汇总表查询
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @return
	 */
	@RequestMapping(value = "/queryCostSummary")
	@ResponseBody
	public Map<String,Object> queryCostSummary(HttpServletRequest request,ProjCostSummaryReq projCostSummaryReq){
		try {
			projCostSummaryReq.setSortInfo(request);
			return budgetService.queryCostSummary(projCostSummaryReq);
		} catch (Exception e) {
			logger.error("单位工程费用汇总表查询失败！", e);
			return null;
		}
	}

	/**
	 * 工程综合详述---施工合同页面
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @return
	 */
	@RequestMapping(value = "/projectDetailContractPage")
	public ModelAndView projectDetailContractPage() {

		ModelAndView modelview = new ModelAndView();
		//modelview.addObject("payType", PayTypeEnum.values());//付款方式
		modelview.addObject("payType", payTypeService.findAllByContractType());//付款方式
		modelview.addObject("contractMethod",ContractMethodEnum.values());		//承包方式
		modelview.addObject("contractType",ContractTypeEnum.values());			//合同类型
		modelview.addObject("payTypeSCEnum",PayTypeSCEnum.values());			//支付方式
		modelview.setViewName("project/view/projectDetailContract");
		return modelview;

	}


	/**
	 * 变更签证
	 * @author fuliwei
	 * @createTime 2017年9月4日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/projectChangePage")
	public ModelAndView projectChangePage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("project/view/projectChangePage");
		return modelview;

	}

	/**
	 * @MethodDesc: 变更详细页面
	 * @Author zhangnx
	 * @Date 2019/3/28 16:58
	 */
	@RequestMapping(value = "/viewChangeDetailPage")
	public ModelAndView viewChangeDetailPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("project/view/changeDetailPage");
		return modelview;

	}

	/**
	 * @MethodDesc: 变更详细
	 * @Author zhangnx
	 * @Date 2019/3/28 17:01
	 */
	@RequestMapping(value = "/findChangeDetail")
	@ResponseBody
	public ChangeManagement findChangeDetail(@RequestParam(required = true) String id) {
		ChangeManagement changeManagement=changeManagementService.viewChangeManagement(id,"变更记录");
		return changeManagement;
	}

	/**
	 * @MethodDesc: 签证详细页面
	 * @Author zhangnx
	 * @Date 2019/3/28 16:59
	 */
	@RequestMapping(value = "/viewVisaDetailPage")
	public ModelAndView viewVisaDetailPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelview.addObject("drawUrl1",Constants.DISK_PATH); 	//图片 url
		modelview.addObject("evType", EngineeringTypeEnum.values());//签证类型
		modelview.addObject("accessorySource", AccessorySourceEnum.COLLECT_FILE.getValue());//附件来源类型
		modelview.setViewName("project/view/visaDetailPage");
		return modelview;

	}
	/**
	 * @MethodDesc: 变更详细
	 * @Author zhangnx
	 * @Date 2019/3/28 17:01
	 */
	@RequestMapping(value = "/findVisaDetail")
	@ResponseBody
	public EngineeringVisa findVisaDetail(@RequestParam(required = true) String id) {
		EngineeringVisa engineeringVisa = engineeringVisaService.viewEngineeringVisa(id, "签证记录");
		return engineeringVisa;
	}



	/**
	 * 工程综合详述---施工合同查询
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/projectDetailContract")
	@ResponseBody
	public Contract projectDetailContract(HttpServletRequest request,@RequestParam(required=true) String id){
		try {
			return contractService.viewContractByprojId(id);

		} catch (Exception e) {
			logger.error("综合详施工合同查询失败！", e);
			return null;
		}

	}

	/**
	 * 分包协议详述
	 * @param request
	 * @param id 工程id
	 * @author pengtt
	 * @createTime 2016-08-08
	 * @return
	 */
	@RequestMapping(value = "/projectDetailSubContract")
	@ResponseBody
	public SubContract subcontract(HttpServletRequest request,@RequestParam(required=true) String id){
		try {
			return subContractService.findSubContractByprojId(id);
		} catch (Exception e) {
			logger.error("综合详施工合同查询失败！", e);
			return null;
		}

	}

	/**
	 * 工程综合详述---施工计划页面
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @return
	 */
	@RequestMapping(value = "/projectDetailPlanPage")
	public ModelAndView projectDetailPlanPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("project/view/projectDetailPlan");
		return modelview;
	}

	/**
	 * 工程综合详述---施工计划查询
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/projectDetailPlan")
	@ResponseBody
	public ConstructionPlan projectDetailPlan(HttpServletRequest request,@RequestParam(required=true) String id){
		try {
			return constructionPlanService.viewPlanById(id);
		} catch (Exception e) {
			logger.error("综合详施工计划查询失败！", e);
			return null;
		}

	}

	/**
	 * 工程综合详述---分包协议
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @return
	 */
	@RequestMapping(value = "/projectDetailSubcontractPage")
	public ModelAndView projectDetailSubcontractPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("project/view/projectDetailSubcontract");
		return modelview;
	}

	/**
	 * 分包协议列表查询
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @return
	 */
	@RequestMapping(value = "/querySubcontract")
	@ResponseBody
	public Map<String,Object> querySubcontract(HttpServletRequest request,SubContractQueryReq subContractReq){
		try {
			subContractReq.setSortInfo(request);
			return subContractService.querySubContract(subContractReq);
		} catch (Exception e) {
			logger.error("分包协议列表查询失败！", e);
			return null;
		}
	}

	/**
	 * 工程综合详述---分包质量
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @return
	 */
	@RequestMapping(value = "/projectDetailSubQuantitiesPage")
	public ModelAndView projectDetailSubQuantitiesPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("project/view/projectDetailSubQuantities");
		return modelview;
	}

	/**
	 * 分包质量列表查询
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @return
	 */
	@RequestMapping(value = "/querySubQuantities")
	@ResponseBody
	public Map<String,Object> querySubQuantities(HttpServletRequest request,SubQuantitiesReq subQuantitiesReq){
		try {
			subQuantitiesReq.setSortInfo(request);
			return subQuantitiesService.queryQuantityStandard(subQuantitiesReq);
		} catch (Exception e) {
			logger.error("分包质量列表查询失败！", e);
			return null;
		}
	}

	/**
	 * 工程综合详述---管理记录
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @return
	 */
	@RequestMapping(value = "/projectDetailManageRecordPage")
	public ModelAndView projectDetailManageRecordPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("step", StepEnum.values());//操作步骤
		modelview.setViewName("project/view/projectDetailManageRecord");
		return modelview;
	}

	/**
	 * 工程综合详述---工程进度
	 * @author yehq
	 * @createTime 2016-08-4
	 * @return
	 */
	@RequestMapping(value = "/projectDetailSchedulePage")
	public ModelAndView projectDetailSchedulePage(HttpServletRequest request) {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projId", request.getParameter("projId"));

		Project pro=projectService.queryProjectById(request.getParameter("projId"));

		CorrelationReq req=new CorrelationReq();
		req.setCorrelateInfoId(pro.getProjectType());
		req.setCorrelatedInfoId(pro.getContributionMode());

		List<Correlation> coList=correlationService.findCorType(req);

		if(coList!=null && coList.size()>0){
			modelview.addObject("code", coList.get(0).getContributionCode());//出资方式
		}
		modelview.addObject("corpId",pro.getCorpId());						 //分公司id
		//获取到工程流程
		modelview.addObject("projectFlow",projectService.queryProjectFlow(pro));
		//步骤枚举类
		modelview.addObject("stepEnum",StepEnum.values());
		modelview.setViewName("project/view/projectDetailSchedule");
		return modelview;
	}

	/**
	 * 工程综合详述----管理记录列表查询
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @param manageRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(HttpServletRequest request,ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setSortInfo(request);
			if("stepDes".equals(manageRecordQueryReq.getSortName())){
				manageRecordQueryReq.setSortName("stepId");
			}
			if("docTypeDes".equals(manageRecordQueryReq.getSortName())){
				manageRecordQueryReq.setSortName("docTypeId");
			}
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}

	/**
	 * 工程综合详述---操作记录
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @return
	 */
	@RequestMapping(value = "/projectDetailoperateRecordPage")
	public ModelAndView projectDetailOperateRecordPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("step", StepEnum.values());//操作步骤
		modelview.setViewName("project/view/projectDetailOperateRecord");
		return modelview;
	}

	/**
	 * 工程综合详述----管理记录列表查询
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryOperateRecord")
	@ResponseBody
	public Map<String,Object> queryOperateRecord(HttpServletRequest request,OperateRecordQueryReq operateRecordQueryReq){
		try {
			operateRecordQueryReq.setSortInfo(request);
			if("stepName".equals(operateRecordQueryReq.getSortName())){
				operateRecordQueryReq.setSortName("stepId");
			}
			return operateRecordService.queryOperateRecord(operateRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}


	/**
	 * 工程进度---甘特图数据查询
	 * @author pengtt
	 * @createTime 2016-08-09
	 * @param projId
	 * @return
	 */
	@RequestMapping(value = "/querySchedule")
	@ResponseBody
	public List<Map<String, Object>> querySchedule(String projId){
		try {
			return operateRecordService.querySchedule(projId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Map<String, Object>>();
		}
	}
	/**
	 * 工程进度---工程进度流程图
	 * @author zhangjj
	 * @createTime 2016-10-14
	 * @param projId
	 * @return
	 */
	@RequestMapping(value = "/queryScheduleFlow")
	@ResponseBody
	public List<Map<String, Object>> queryScheduleFlow(String projId){
		try {
			return operateRecordService.queryScheduleFlow(projId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 项目位置---工程信息
	 * @author ferrinweb
	 * @createTime 2016-07-13
	 * @return
	 */
	@RequestMapping(value = "/projectLocation")
	public ModelAndView projectLocation() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("project/view/constructionMonitoring");
		return modelview;
	}


	/**
	 * 查询工程派遣信息
	 * @author fuliwei
	 * @createTime 2017年12月27日
	 * @param
	 * @return
	 */
	@RequestMapping(value="/projectDispatchDetail")
	@ResponseBody
	public Project projectDispatchDetail(String projId,String stepId){
		try {
			Project project= projectService.viewProjectDispatch(projId,stepId);
			return project;
		} catch (Exception e) {
			logger.error("工程详述明细查询失败！", e);
			return null;
		}
	}

	/**
	 * 打开审核记录查询页面
	 * @author fuliwei
	 * @createTime 2018年5月17日
	 * @param
	 * @return
	 */
	@RequestMapping(value="/manageRecordMain")
	public ModelAndView main(String stepId,String projId){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("stepId", stepId);
		modelView.addObject("projId", projId);
		modelView.setViewName("project/view/manageRecordList");
		return modelView;
	}

	/***
	 * 查询审核记录
	 * @author fuliwei
	 * @createTime 2018年5月17日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecordList")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setLevel(Constants.FAIL_CODE);//-1表示审核级别不能为空
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	/**
	 * todo:待完成
	 * 查询工程状态之间的耗时情况
	 * 两个状态之间的设置的时限累加的到总的时限
	 * 获取开始状态和结束状态的操作日志：如果开始状态的操作日志没有，则不获取时限，如果开始状态已有操作日志，判断结束状态是否有操作日志，没有则获取系统当前时间
	 * 获取两个状态之间的工程信息
	 * 并根据日期计算已用工作日时间
	 * @author liaoyq
	 * @createTime 2018年5月25日
	 * @param request
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryProjectLimitTime")
	@ResponseBody
	public Map<String,Object> queryProjectLimitTime(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			Map<String,Object> map = projectService.queryProjectLimitTime(projectQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	/***
	 * 材料清单列表查询
	 * @param request
	 * @param materialListQueryReq
	 * @return
	 * wang.hui.jun 20180626
	 */
	@RequestMapping(value = "/queryMaterialList")
	@ResponseBody
	public Map<String,Object> queryMaterialList(HttpServletRequest request,MaterialListQueryReq materialListQueryReq){
		try {
			materialListQueryReq.setSortInfo(request);
			return materialListService.queryMaterialList(materialListQueryReq);
		} catch (Exception e) {
			logger.error("材料领用---材料清单列表查询失败！", e);
			return null;
		}
	}

	@RequestMapping(value = "/exportExcel")
	public void exportExcel (HttpServletResponse response,HttpServletRequest request, String projId){
		try {
			String title = "材料记录"; //设置标题
			String[] excelHeader = {"材料名称","规格型号","单位","设计总量","领用总量","使用总量","是否由物资购买"};   //设置每一列名字
			String[] propertyNames = {"materialName","materialSpec","materialUnit","materialNum","getAnum","useAnum","flagDes"}; //属性名和列名相对应
			Integer[] headerWidth = {4000,4000,8000,8000,4000,4000,4000};  //设置每列的宽度
			Map<String,String> map = new HashMap<String,String>();
			map.put("fileName", "feeMaterial");  //设置文件名
			map.put("remarkInfoHeight", "0"); //设置备注列的高度
			String sheetName="材料记录";  //设置工作表名称
			List<MaterialList> dataList = materialListService.getExportDataList(projId);
			String data = JSONArray.toJSONString(dataList);
			ExcelXlsxUtil.exportExcel(response, title,sheetName, excelHeader, headerWidth, propertyNames, map, JSONArray.parseArray(data));
		} catch (Exception e) {
			logger.error("模板导出失败",e);
		}
	}
	
	/**
	 *  接口同步页面
	 * @author liaoyq
	 * @createTime 2018年8月7日
	 * @return
	 */
	@RequestMapping(value = "/serviceListPop")
	public ModelAndView serviceListPop(){
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("webServiceTypeEnum",WebServiceTypeEnum.values());
		modelview.setViewName("project/view/serviceListPop");
		return modelview;
	}
	
	/**
	 * 
	 * 注释：查询接口信息
	 * @author liaoyq
	 * @createTime 2019年9月27日
	 * @param request
	 * @param accessoryQueryReq
	 * @return
	 *
	 */
	@RequestMapping(value = "/queryServiceList")
	@ResponseBody
	public Map<String, Object> queryServiceList(HttpServletRequest request,WebserviceLogReq weLogReq) {
		weLogReq.setSortInfo(request);
		return  webserviceLogService.queryWebServiceLog(weLogReq);
	}
}