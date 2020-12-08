package cc.dfsoft.project.biz.base.budget.controller;

import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.service.SubBudgetService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
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
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/budgetPie")
public class BudgetPieController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(BudgetPieController.class);
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	
	/**预算服务接口*/
	@Resource
	BudgetService budgetService;
	/**施工预算服务接口*/
	@Resource
	SubBudgetService subBudgetService;
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
	/**施工计划服务接口*/
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	/**
	 * 
	 * @author fuliwei
	 * @createTime 2017年5月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.addObject("marketingCenter",DeptDivideEnum.MARKETING_CENTER.getValue());//营销中心
		modelView.addObject("preSettlementGroup",DeptDivideEnum.PRE_SETTLEMENT_GROUP.getValue());//预结算组
		modelView.addObject("customerServiceCenter", DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());//客服中心
		modelView.setViewName("subcontract/budgetPie");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @author fuliwei
	 * @createTime 2017年5月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("subcontract/budgetPieRight");
		return modelView;
	}
	
	/**
	 * 待预算派工工程列表查询
	 * @author fuliwei
	 * @createTime 2017年5月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_DETERMINE_DISPATCH.getValue()); //待预算派工
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.QUALITIES_DISPATCH.getValue());
			Map<String,Object> map= projectService.queryProjectByTime(projectQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年5月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewDispatchResult")
	@ResponseBody
	public Project viewDispatchResult(@RequestParam(required=true) String id){
		Project pro=projectService.viewProject(id);
		ConstructionPlan cp=constructionPlanDao.findByProjId(id);
		if(pro!=null && cp!=null){
			departmentService.queryDepartment(pro.getDeptId());
			pro.setDeptDivide(departmentService.queryDepartment(pro.getDeptId()).getDeptDivide());
			pro.setCuName(cp.getCuName());
		}
		return pro;
	}
	
	
	/**
	 * 预算审核员列表查询
	 * @param designerQueryReq
	 * @createTime 2016-07-1
	 * @return
	 */
	@RequestMapping(value = "/queryDesigner")
	@ResponseBody
	public Map<String,Object> queryDesigner(HttpServletRequest request, DesignerQueryReq designerQueryReq){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			designerQueryReq.setSortInfo(request);
			Department department=departmentService.queryDepartmentByDivide(designerQueryReq.getDeptDivide(),loginInfo.getDeptId());
			if (department!=null && StringUtils.isNotBlank(department.getDeptId())) {
				designerQueryReq.setDeptId(departmentService.queryDepartmentByDivide(designerQueryReq.getDeptDivide(),loginInfo.getDeptId()).getDeptId());
			}
			designerQueryReq.setCorpId(loginInfo.getCorpId());								//所属公司
			designerQueryReq.setPostType(PostTypeEnum.BUDGET_MEMBER.getValue());  			//预算员
			designerQueryReq.setProjStatus(ProjStatusEnum.TO_AUDIT_AMOUNT.getValue());		//待施工预算审核
			result= subBudgetService.queryBudgeter(designerQueryReq);
			return result;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;	
		}
	}
	
	
	/**
	 * 更新工程信息
	 * @author fuliwei
	 * @createTime 2016-6-29
	 * @return String 
	 */
	@RequestMapping(value = "/updateProject")
	@ResponseBody
	public String saveSurveyInfo(@RequestBody(required = true) DesignDispatchDto designDispatchReq){
		try {
			budgetService.update(designDispatchReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("派工区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
}
