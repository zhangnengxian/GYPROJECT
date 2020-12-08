package cc.dfsoft.project.biz.base.budget.controller;

import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.project.biz.base.project.service.RelationShipService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.util.SessionUtils;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/budgetDispatch")
public class BudgetDispatchController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(BudgetDispatchController.class);
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	
	/**预算服务接口*/
	@Resource
	BudgetService budgetService;
	
	@Resource
	RelationShipService relationShipService;
	@Resource
	ProjectTypeService projectTypeService;
	
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
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
		modelView.addObject("marketingCenter", DeptDivideEnum.MARKETING_CENTER.getValue());//营销中心
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.addObject("preSettlementGroup",DeptDivideEnum.PRE_SETTLEMENT_GROUP.getValue());//预结算组
		modelView.addObject("customerServiceCenter", DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());//客服中心
		modelView.setViewName("budget/budgetDispatch");
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
		//modelView.addObject("projTypeId", ProjectTypeEnum.values());
		modelView.setViewName("budget/budgetDispatchRight");
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
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_BUDGET_DISPATCH.getValue()); //待预算派工
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.BUDGET_DISPATCH.getValue());
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
		return pro;
	}
	
	
	/**
	 * 设计员列表查询
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
			
			//查询是否配置sql
			List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+designerQueryReq.getMenuId());
			if(list!=null && list.size()>0){
				designerQueryReq.setDeptId("");
			}else{
				designerQueryReq.setDeptId(departmentService.queryDepartmentByDivide(designerQueryReq.getDeptDivide(), null).getDeptId());
			}
			
			designerQueryReq.setCorpId(loginInfo.getCorpId());								//所属公司
			designerQueryReq.setPostType(PostTypeEnum.BUDGET_MEMBER.getValue());  			//预算员
			designerQueryReq.setProjStatus(ProjStatusEnum.TO_BUDGET_RESULT_REGISTRATION.getValue());	//待预算结果登记
			result= budgetService.queryBudgeter(designerQueryReq);
			return result;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 预算派工
	 * 更新工程信息
	 * @author liaoyq
	 * @createTime 2017-08-11
	 * @return String 
	 */
	@RequestMapping(value="/updateProject")
	@ResponseBody
	public String saveBudgeterInfo(@RequestBody(required = true) DesignDispatchDto designDispatchReq){
		try {
			budgetService.updateProject(designDispatchReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("预算派工区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 预算派工页面  打开查询屏
	 * @author liaoyq
	 * @createTime 2017-08-16
	 * @return
	 */
	@RequestMapping(value="/projectSearchPopPage")
	public ModelAndView projectSearchPopPage(){
		ModelAndView modelAndView = new ModelAndView();
		List<ProjectType> projType=projectTypeService.queryAllList();
		modelAndView.addObject("projLtype", projType);

		modelAndView.setViewName("budget/budgetDispatchSearchPopPage");
		return modelAndView;
	}
}
