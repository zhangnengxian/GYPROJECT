package cc.dfsoft.project.biz.base.budget.controller;

import cc.dfsoft.project.biz.base.budget.dto.BudgetReq;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
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
import java.util.Map;
/**
 * 图纸签收/审核
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/drawingSignAndAudit")
public class DrawingSignAndAuditController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(DrawingSignAndAuditController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**设计信息服务接口*/
	@Resource
	DesignInfoService designInfoService;
	
	/**管理记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	

	/**预算服务接口*/
	@Resource
	BudgetService budgetService;
	

	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("budget/drawingAudit");
		return modelView;
	}
	
	/**
	 * 打开审核页面
	 * @return
	 */
	@RequestMapping(value = "/auditPage")
	public ModelAndView viewAuditPage(HttpServletRequest request) {
		ModelAndView modelView = new ModelAndView();
		String projId=request.getParameter("projId");
		Project project=projectService.viewProject(projId);
		if(project!=null ){
			modelView.addObject("project", project);//查project
			//modelView.addObject("areaDes", AreaEnum.valueof(project.getArea()).getMessage());
		}
		//根据工程id查设计信息
		modelView.addObject("designInfo", designInfoService.queryById(projId));//project查设计信息
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);											//登录人信息
		modelView.setViewName("budget/drawingAuditPage");
		return modelView;
	}
	
	
	/**
	 * 弹出搜索
	 * @author fuliwei
	 * @createTime 2016-7-1
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("budget/drawingAuditSearchPopPage");
		return modelview;
	}
	
	
	/**
	 * 工程列表条件查询
	 * @author fuliwei
	 * @param projectQueryReq
	 * @createTime 2016-07-01
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_DRAWING.getValue());  //待审图
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.DRAWING_SIGN_AUDIT.getValue());
			String grade = "";
			Map<String,Object> map=projectService.queryAuditProject(projectQueryReq,grade,StepEnum.DRAWING_SIGN_AUDIT.getValue());
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2016-7-1
	 * @param id
	 * @return pro
	 */
	@RequestMapping(value="/viewProject")
	@ResponseBody
	public Project viewProject(HttpServletRequest request,@RequestParam(required=true) String id){
		Project pro=projectService.viewProject(id);
		return pro;
	}
	
	/**
	 * 图纸审核页面审核历史列表查询
	 * @author 
	 * @createTime 2016-07-05
	 * @param manageRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepEnum.DRAWING_SIGN_AUDIT.getValue());	//图纸审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 图纸审核保存
	 * @author fuliwei
	 * @createTime 2016-07-05
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		try {
			String result=manageRecordService.auditDrawingSave(manageRecord,"","",WorkFlowActionEnum.DRAWING_SIGN_AUDIT.getActionCode(),Constants.MODULE_CODE_DESIGN);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("图纸审核保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 预算员列表查询
	 * @param designerQueryReq
	 * @createTime 2016-07-1
	 * @return
	 */
	@RequestMapping(value = "/queryBudgeter")
	@ResponseBody
	public Map<String,Object> queryBudgeter(HttpServletRequest request, DesignerQueryReq designerQueryReq){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			designerQueryReq.setSortInfo(request);
			designerQueryReq.setPostType(PostTypeEnum.BUDGET_MEMBER.getValue());  			//预算员
			designerQueryReq.setProjStatus(ProjStatusEnum.TO_DRAWING.getValue());	//待图纸确认二级
			result= budgetService.queryBudgeter(designerQueryReq);
			return result;
		} catch (Exception e) {
			logger.error("预算员查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 更新工程信息
	 * @author fuliwei
	 * @createTime 2017-1-4
	 * @return String 
	 */
	@RequestMapping(value = "/updateProject")
	@ResponseBody
	public String saveSurveyInfo(@RequestBody(required = true) BudgetReq BudgetReq){
		try {
			budgetService.updateProject(BudgetReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("派工区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
