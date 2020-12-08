package cc.dfsoft.project.biz.base.budget.controller;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.project.biz.base.subpackage.service.SubQuantitiesService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
/**
 * 预算审核
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/budgetAudit")
public class BudgetAuditController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(BudgetAuditController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**分包工程量服务接口*/
	@Resource
	SubQuantitiesService subQuantitiesService;
	
	@Resource
	ManageRecordService manageRecordService;
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	@Resource
	BudgetService budgetService;
	
	@Resource
	ProjectTypeService projectTypeService;
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("budget/budgetAudit");
		return modelView;
	}
	
	/**
	 * 打开审核页面
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/auditPage")
	@ResponseBody
	public ModelAndView viewAuditPage(HttpServletRequest request) {
		ModelAndView modelView = new ModelAndView();
		String projId = request.getParameter("projId");
		//根据工程id查设计信息
		modelView.addObject("projId",projId);
		//modelView.addObject("amount",request.getParameter("amount"));
		//modelView.addObject("subQualities", subQuantitiesService.queryById(projId));//project查设计信息
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);										//登录人信息
		modelView.setViewName("budget/budgetAuditPage");
		return modelView;
	}
	
	/**
	 * 工程列表条件查询
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_BUDGET_AUDIT.getValue());  //待预算审核
			projectQueryReq.setSortInfo(request);
			Map<String,Object> map=budgetService.queryAuditProject(projectQueryReq,"",StepEnum.BUDGET_AUDIT.getValue());
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
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
			manageRecordQueryReq.setStepId(StepEnum.BUDGET_AUDIT.getValue());	//预算审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("工程预算审核历史查询失败！",e);
			return null;
		}
	}
	/**
	 * 保存预算审批
	 * @author liaoyq
	 * @createTime 2017-8-15
	 */
	@RequestMapping(value="/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		try {
			manageRecordService.auditBudgetSave(manageRecord,"","",WorkFlowActionEnum.BUDGET_AUDIT.getActionCode(),Constants.MODULE_CODE_BUDGET);
			
			 return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("工程预算审核保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 打开查询屏
	 * @author liaoyq
	 * @createTime 2017-8-16
	 */
	@RequestMapping(value="/projectSearchPopPage")
	public ModelAndView projectSearchPopPage(){
		ModelAndView modelAndView = new ModelAndView();
		List<ProjectType> projType=projectTypeService.queryAllList();
		modelAndView.addObject("projLtype", projType);
		modelAndView.setViewName("budget/budgetAuditSearchPopPage");
		return modelAndView;
	}
}
