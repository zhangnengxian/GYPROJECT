package cc.dfsoft.project.biz.base.contract.controller;

import cc.dfsoft.project.biz.base.accept.controller.ProjectAcceptController;
import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.contract.dto.ProjectCostReq;
import cc.dfsoft.project.biz.base.contract.entity.ProjectCost;
import cc.dfsoft.project.biz.base.contract.service.ProjectCostService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ScaleDetailQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.project.biz.base.project.service.ScaleDetailService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;
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
 * 确定工程造价
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value="/projectCost")
public class ProjectCostController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ProjectAcceptController.class);
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	
	/**
	 * 预算服务接口
	 */
	@Resource
	BudgetService budgetService;
	
	/**工程规模服务接口*/
	@Resource
	ProjectTypeService projectTypeService;
	
	@Resource
	ScaleDetailService scaleDetailService;
	@Resource
	ProjectCostService projectcostService;
	
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.addObject("notThrough",Constants.MODULE_CODE_AUDIT);
		modelView.addObject("stepId",StepEnum.PROJECT_COST_AUDIT.getValue());
		modelView.setViewName("contract/projectCost");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String corpId,String projectType,String menuId) {
		ModelAndView modelview = new ModelAndView();
		List<ProjectType> list=projectTypeService.queryAllList();
		modelview.addObject("projLtype", list);
		List<DataFilerSetUpDto> changeReason= Constants.getDataFilterMapByKey(corpId+"_"+"0"+"_"+menuId);  //查询变更原因
		if(changeReason != null && changeReason.size() > 0){
			modelview.addObject("changeReasonEnum", changeReason); //变更原因
		}else{
			List<DataFilerSetUpDto> changeReason1= Constants.getDataFilterMapByKey(1101+"_"+"0"+"_"+menuId);  //查询变更原因.默认查找集团公司变更原因
			modelview.addObject("changeReasonEnum", changeReason1); //变更原因
		}
		modelview.addObject("backReason", BackReasonEnum.values());//退单原因
		modelview.addObject("customerServiceCenter",DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());//部门划分:客服中心
		List<DataFilerSetUpDto> auditRange= Constants.getDataFilterMapByKey(corpId+"_"+projectType+"_"+menuId);  //根据公司Id菜单ID查找审批范围
		if(auditRange != null && auditRange.size() >0){
			modelview.addObject("auditRange", auditRange);  //审批范围
		}
		//modelview.addObject("contractType", ContractTypeEnum.values());
		modelview.addObject("thisStepId",StepEnum.PROJECT_COST.getValue());
		modelview.setViewName("contract/projectCostRight");
		return modelview;
	}
	
	/**
	 * 工程列表查询
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);//用于排序查询
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_DETERMINE_COST.getValue());//待确定造价
			projectQueryReq.setStepId(StepEnum.PROJECT_COST.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 弹出搜索屏
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("contract/searchPopPage");
		return modelview;
	}
	
	/**
	 * 详述
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewCost")
	@ResponseBody
	public Project viewContract(HttpServletRequest request,@RequestParam(required=true) String id){
		Project project = projectService.queryProjectById(id);
		Budget budget=budgetService.queryBudgeByprojId(id);
		if(budget!=null){
			if(budget.getInspectionCost()!=null){
				project.setInspectionCost(budget.getInspectionCost());
			}
			if(budget.getSuCost()!=null){
				project.setSuCost(budget.getSuCost());
			}
			if(budget.getDesignCost()!=null){
				project.setDesignCost(budget.getDesignCost());
			}
			if(budget.getMaterialCost()!=null){
				project.setMaterialCost(budget.getMaterialCost());
			}
			if(budget.getUnforeseenCost()!=null){
				project.setUnforeseenCost(budget.getUnforeseenCost());
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
		ProjectCost projectCost = projectcostService.queryByProjId(id);
		if(projectCost!=null && StringUtils.isNotBlank(projectCost.getProjCostAuditType())){
			project.setProjCostAuditType(projectCost.getProjCostAuditType());
			if(StringUtils.isNotBlank(projectCost.getReduceGasTimes())){
				project.setReduceGasTimes(projectCost.getReduceGasTimes());
			}
		}
		
		Department dep = departmentService.queryDepartment(project.getDeptId());
		if(null!=dep&&StringUtil.isNoneBlank(dep.getDeptDivide())){
			project.setDeptDivide(dep.getDeptDivide());
		}
		//获取配置表中如果有配置：规则：分公司ID_菜单ID
		String menuId = request.getParameter("menuId");
		Object object = Constants.getSysConfigByKey(project.getCorpId()+"_"+menuId);
		if(object!=null){
			project.setProjCostConfig(object.toString());//如果造价配置不为空，则表示审核级别要根据docType中的配置获取，可以不用选择审批范围
		}
		return project;
	}
	
	/**
	 * 保存
	 * @param projectCostReq
	 * @return
	 */
	@RequestMapping(value = "/saveProject")
	@ResponseBody
	public ResultMessage saveDesignInfo(@RequestBody(required = true) ProjectCostReq projectCostReq){
		ResultMessage resultMessage = new ResultMessage();
		try {
			projectService.updateProjectCost(projectCostReq);
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_SUCCESS);
			return resultMessage;
		} catch (ExpressException e) {
			logger.error("工程造价信息保存失败！", e);
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		} catch (Exception e) {
			logger.error("工程造价信息保存失败！", e);
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
	}
	
	/**
	 * 工程规模明细
	 * @param scaleDetailQueryReq
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
