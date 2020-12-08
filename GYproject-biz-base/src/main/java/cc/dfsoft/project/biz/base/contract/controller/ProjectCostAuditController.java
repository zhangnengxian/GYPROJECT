package cc.dfsoft.project.biz.base.contract.controller;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.contract.dao.ButtonDispalyDao;
import cc.dfsoft.project.biz.base.contract.entity.ProjectCost;
import cc.dfsoft.project.biz.base.contract.enums.ChangeReasonEnum;
import cc.dfsoft.project.biz.base.contract.service.ProjectCostService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ScaleDetailQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.project.biz.base.project.service.ScaleDetailService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 描述:工程造价审核列表
 * @author liaoyq
 * @createTime 2017年8月16日
 */

@Controller
@RequestMapping(value="/projectCostAudit")
public class ProjectCostAuditController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ProjectCostAuditController.class);
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	
	@Resource
	ProjectCostService projectCostService;
	
	@Resource
	ManageRecordService manageRecordService;
	
	@Resource
	ProjectTypeService projectTypeService;
	@Resource
	ScaleDetailService scaleDetailService;
	@Resource
	DocTypeService docTypeService;
	
	@Resource
	BudgetService budgetService;
	
	@Resource
	ButtonDispalyDao buttonDisplayDao;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("contract/projectCostAudit");
		return modelView;
	}
	/**
	 * 工程列表条件查询
	 * @author liaoyq
	 * @createTime 2017年8月17日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_PROJECT_COST_AUDIT.getValue());  //待工程造价审核
			projectQueryReq.setSortInfo(request);
			Map<String,Object> map=projectCostService.queryAuditProject(projectQueryReq,StepEnum.PROJECT_COST_AUDIT.getValue());
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 弹出搜索屏
	 * @author liaoyq
	 * @createTime 2017年8月17日
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("contract/projectCostSearchPopPage");
		return modelview;
	}
	
	
	/**
	 * 工程造价审核
	 * @author liaoyq
	 * @createTime 2017年8月17日
	 * @param projectCostReq
	 * @return
	 */
	@RequestMapping(value="/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		try {
			ProjectCost projectCost = projectCostService.queryByProjId(manageRecord.getProjId());
			manageRecordService.auditProjectCostSave(manageRecord,"","",WorkFlowActionEnum.PROJECT_COST_AUDIT.getActionCode(),Constants.MODULE_CODE_AUDIT);
			
			 return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("工程预算审核保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 进入工程造价审核屏
	 * @author liaoyq
	 * @createTime 2017年8月17日
	 * @return
	 */
	@RequestMapping(value="auditPage")
	@ResponseBody
	public ModelAndView auditPage(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		String projId = request.getParameter("projId");
		//根据工程id查设计信息
		modelAndView.addObject("projId",projId);
		//modelView.addObject("amount",request.getParameter("amount"));
		//modelView.addObject("subQualities", subQuantitiesService.queryById(projId));//project查设计信息
		modelAndView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelAndView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		modelAndView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelAndView.addObject("loginInfo",loginInfo);	//登录人信息
		if(StringUtils.isNotBlank(loginInfo.getCorpId())){  //根据公司ID或者部门ID查询实体(AuditDisplay)
			modelAndView.addObject("buttonDisplay",buttonDisplayDao.get(loginInfo.getCorpId()));	
		}else{
			modelAndView.addObject("buttonDisplay",buttonDisplayDao.get(loginInfo.getDeptId()));	
		}
		List<ProjectType> list=projectTypeService.queryAllList();
		modelAndView.addObject("projLtype", list);
		modelAndView.addObject("changeReasonEnum", ChangeReasonEnum.values());
		modelAndView.addObject("backReason", BackReasonEnum.values());//退单原因
		//modelAndView.addObject("projCostAuditTypeEnum", ProjCostAuditTypeEnum.values());//审批范围
		List<DataFilerSetUpDto> auditRange= Constants.getDataFilterMapByKey(request.getParameter("corpId")+"_"+request.getParameter("projectType")+"_"+request.getParameter("menuId"));  //根据公司Id菜单ID查找审批范围
		if(auditRange != null && auditRange.size() >0){
			modelAndView.addObject("auditRange", auditRange);  //审批范围
		}
		modelAndView.addObject("stepId", StepEnum.PROJECT_COST_AUDIT.getValue());//造价审核
		modelAndView.setViewName("contract/projectCostAuditPage");
		return modelAndView;
	}
	
	/**
	 * 审核历史列表查询
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepEnum.PROJECT_COST_AUDIT.getValue());	//工程总造价审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("工程预算审核历史查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 查询工程详述
	 * @author liaoyq
	 * @createTime 2017年8月17日
	 * @param request
	 * @param id 工程ID
	 * @return  工程信息(包含工程造价信息)
	 */
	@RequestMapping(value="/queryPro")
	@ResponseBody
	public ProjectCost viewContract(HttpServletRequest request,@RequestParam(required=true) String id){
		Project project = projectService.queryProjectById(id);
		Budget budget=budgetService.queryBudgeByprojId(id);
		if(budget!=null){
			if(budget.getInspectionCost()!=null){
				project.setInspectionCost(budget.getInspectionCost());
			}
			if(budget.getSuCost()!=null){
				project.setSuCost(budget.getSuCost());
			}
			if(budget.getUnforeseenCost()!=null){
				project.setUnforeseenCost(budget.getUnforeseenCost());
			}
			if(budget.getMaterialCost()!=null){
				project.setMaterialCost(budget.getMaterialCost());
			}
			if(budget.getDesignCost()!=null){
				project.setDesignCost(budget.getDesignCost());
			}
			if(budget.getGasTimes()!=null){
				project.setGasTimes(budget.getGasTimes());
			}
			if(budget.getCivilCost() != null){
				project.setStoreCost(budget.getCivilCost());
			}
			if(budget.getAnnunciatorCost() != null){
				project.setEuAmount(budget.getAnnunciatorCost());//报警器费用
			}
		}
		ProjectCost projectCost = projectCostService.queryByProjId(id);
		if(projectCost!=null && StringUtils.isNotBlank(projectCost.getProjCostAuditType())){
			project.setProjCostAuditType(projectCost.getProjCostAuditType());
			if(StringUtils.isNotBlank(projectCost.getReduceGasTimes())){
				project.setReduceGasTimes(projectCost.getReduceGasTimes());
			}
		}else if(projectCost == null ){   //如果为空则新建一个对象
		 projectCost =  new ProjectCost();
		}
		
		projectCost.setProject(project);
		return projectCost;
	}
	/**
	 * 工程规模明细
	 * @param projectQueryReq
	 * @author 
	 * @createTime 2016-12-18
	 * @return
	 */
	@RequestMapping(value = "/queryScaleDetail")
	@ResponseBody
	public Map<String,Object> queryScaleDetail(HttpServletRequest request,ScaleDetailQueryReq scaleDetailQueryReq){
		try {
			scaleDetailQueryReq.setSortInfo(request);
			return scaleDetailService.queryScaleDetail(scaleDetailQueryReq);
		} catch (Exception e) {
			logger.error("工程规模明细信息查询失败！", e);
			return null;
		}
	}
}
