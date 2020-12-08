package cc.dfsoft.project.biz.base.inspection.controller;

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

import cc.dfsoft.project.biz.base.inspection.dto.ControlDebuggingReq;
import cc.dfsoft.project.biz.base.inspection.dto.InsulationResistanceTestReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.ControlDebuggingService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 *  控制系统调试
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/controlDebugging")
public class ControlDebuggingController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ControlDebuggingController.class);
	
	/**工程报验单服务接口*/
	@Resource
	ProjectChecklistService projectChecklistService;
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**施工计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;
	
	/**控制系统调试服务接口*/
	@Resource
	ControlDebuggingService controlDebuggingService;
	
	/**
	 * 控制系统调试主页面
	 * @author fuliwei
	 * @createTime 2017年2月6日
	 * @param 
	 * @return ModelAndView
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("pcDesId",ProjectChecklistTypeEnum.CONTROL_DEBUGGING.getValue());
		modelView.addObject("qualitativeCheckMember",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());//质检员
		modelView.addObject("sujgj",PostTypeEnum.SUJGJ.getValue());//监理
		modelView.addObject("path",Constants.DISK_PATH+"sign/");
		modelView.setViewName("inspection/controlDebugging");
		return modelView;
	}
	
	/**
	 * 工程报验单条件查询
	 * @author fuliwei
	 * @createTime 2017-02-6
	 * @param listQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProjectList")
	@ResponseBody
	public Map<String,Object> queryProjectList(HttpServletRequest request,ProjectChecklistQueryReq listQueryReq){
		try {
			listQueryReq.setSortInfo(request);
			listQueryReq.setPcDesId(ProjectChecklistTypeEnum.CONTROL_DEBUGGING.getValue());
			return projectChecklistService.queryPrProjectChecklist(listQueryReq);
		} catch (Exception e) {
			logger.error("报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017-02-6
	 * @param id 工程id
	 * @return ProjectCheckList
	 */
	@RequestMapping(value="/viewControlDebugging")
	@ResponseBody
	public ProjectChecklist viewControlDebugging(HttpServletRequest request,@RequestParam(required=true) String id){
		ProjectChecklist list=projectChecklistService.viewProjectCheckList(id);
		return list;
	}
	
	/**
	 * 控制系统调试保存
	 * @author fuliwei
	 * @createTime  2016-7-20
	 * @param checkList
	 * @return String
	 */
	@RequestMapping(value = "/saveControlDebugging")
	@ResponseBody
	public String saveControlDebugging(@RequestBody(required = true) ProjectChecklist checkList){
		try {
			return projectChecklistService.saveProCheckList(checkList,Constants.CONTROL_DEBUGGING);
		} catch (Exception e) {
			logger.error("控制系统调试报验区保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 控制系统调试记录列表查询
	 * @author fuliwei
	 * @createTime 2017-2-6
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryControlDebugging")
	@ResponseBody
	public Map<String, Object> queryControlDebugging(HttpServletRequest request,ControlDebuggingReq req) {
		try {
			req.setSortInfo(request);
		    return controlDebuggingService.queryControlDebugging(req);
		} catch (Exception e) {
			logger.error("控制系统调试记录列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存控制系统调试记录(批量增加)
	 * @author fuliwei
	 * @createTime 2017-2-6
	 * @param req
	 * @return void
	 */
	@RequestMapping(value = "/saveControlDebuggingRecord")
	@ResponseBody
	public String saveControlDebuggingRecord(@RequestBody ControlDebuggingReq req){
		try {
			controlDebuggingService.saveControlDebugging(req);;
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("保存控制系统调试记录失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	};
}
