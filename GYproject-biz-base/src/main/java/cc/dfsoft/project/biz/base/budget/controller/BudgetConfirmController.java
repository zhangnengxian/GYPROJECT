package cc.dfsoft.project.biz.base.budget.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.enums.BudgetCostTypeEnum;
import cc.dfsoft.project.biz.base.budget.enums.BudgetMethodEnum;
import cc.dfsoft.project.biz.base.budget.service.BudgetAdjustService;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 
 * 描述:确定预算方式控制层
 * @author liaoyq
 * @createTime 2018年9月8日
 */
@Controller
@RequestMapping(value="/budgetConfirm")
public class BudgetConfirmController {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(BudgetConfirmController.class);
	
	/** 预算服务接口 */
	@Resource
	BudgetService budgetService;
	
	/** 预算调整服务接口*/
	@Resource
	BudgetAdjustService budgetAdjustService;
	
	@Resource
	ProjectService projectService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("civilType", BudgetCostTypeEnum.getObjByType(ProjLtypeEnum.CIVILIAN.getValue()));
		modelView.addObject("publicType", BudgetCostTypeEnum.getObjByType(ProjLtypeEnum.PUBLIC.getValue()));
		modelView.addObject("civilVal", ProjLtypeEnum.CIVILIAN.getValue());
		modelView.addObject("publicVal", ProjLtypeEnum.PUBLIC.getValue());
		modelView.setViewName("budget/budgetConfirm");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("budgetMethodEnum", BudgetMethodEnum.values());
		modelView.setViewName("budget/budgetConfirmRight");
		return modelView;
	}
	
	/**
	 * 待预算方式确认的工程列表查询
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_BUDGET_CONFIRM.getValue()); //待预算方式确定
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.BUDGET_CONFIRM.getValue());
			Map<String,Object> map= projectService.queryProjectByTime(projectQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询工程详述
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewProject")
	@ResponseBody
	public Project viewProject(@RequestParam(required=true) String id){
		Project pro=projectService.viewProject(id);
		return pro;
	}
	
	/**
	 * 保存工程信息
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/saveProBudgetMethod")
	@ResponseBody
	public String saveProBudgetMethod(@RequestBody(required = true) Project proj){
		try {
			projectService.saveProBudgetMethod(proj);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("工程信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
