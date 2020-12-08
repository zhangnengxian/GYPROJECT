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

import cc.dfsoft.project.biz.base.inspection.dto.PressureQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListASReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPressureReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.PressureService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 试压记录
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/pressure")
public class PressureController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(PressureController.class);
	
	/**工程报验单服务接口*/
	@Resource
	ProjectChecklistService projectChecklistService;
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**施工计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;
	
	/**试压记录*/
	@Resource
	PressureService pressureService;
	
	/**
	 * 试压记录主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("pcDesId",ProjectChecklistTypeEnum.PRESSURE.getValue());//试压记录
	    modelView.addObject("managementqae",PostTypeEnum.MANAGEMENTQAE.getValue());//质保师职务
		modelView.addObject("qualitativeCheckMember",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());//质检员
		modelView.addObject("deputyDirector",PostTypeEnum.DEPUTY_DIRECTOR.getValue());//副处长
		modelView.addObject("sujgj",PostTypeEnum.SUJGJ.getValue());//监理
		modelView.addObject("path",Constants.DISK_PATH+"sign/");
		modelView.setViewName("inspection/pressure");
		return modelView;
	}
	
	/**
	 * 右侧页面
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("");
		return modelView;
	}
	
	/**
	 * 工程报验单条件查询
	 * @author fuliwei
	 * @createTime 2016-07-21
	 * @param listQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProjectList")
	@ResponseBody
	public Map<String,Object> queryProjectList(HttpServletRequest request,ProjectChecklistQueryReq listQueryReq){
		try {
			listQueryReq.setSortInfo(request);
			listQueryReq.setPcDesId(ProjectChecklistTypeEnum.PRESSURE.getValue());
			return projectChecklistService.queryPrProjectChecklist(listQueryReq);
		} catch (Exception e) {
			logger.error("报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2016-7-21
	 * @param id 工程id
	 * @return ProjectCheckList
	 */
	@RequestMapping(value="/viewCheckList")
	@ResponseBody
	public ProjectChecklist viewCheckList(HttpServletRequest request,@RequestParam(required=true) String id){
		ProjectChecklist list=projectChecklistService.viewProjectCheckList(id);
		return list;
	}
	
	/**
	 * 试压报验单保存
	 * @author fuliwei
	 * @createTime  2016-7-20
	 * @param checkList
	 * @return String
	 */
	@RequestMapping(value = "/savePressure")
	@ResponseBody
	public String savePressure(@RequestBody(required = true) ProjectChecklist checkList){
		try {
			//return projectChecklistService.savePressure(checkList);
			return projectChecklistService.saveProCheckList(checkList,Constants.MODULE_CODE_PRESSURE);
		} catch (Exception e) {
			logger.error("试压记录报验区保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 试压记录列表查询
	 * @author fuliwei
	 * @createTime 2016-7-20
	 * @param pressureQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryPressure")
	@ResponseBody
	public Map<String, Object> queryPressure(HttpServletRequest request,PressureQueryReq pressureQueryReq) {
		try {
			pressureQueryReq.setSortInfo(request);
		    return pressureService.queryPressure(pressureQueryReq);
		} catch (Exception e) {
			logger.error("试压记录列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存试压记录(批量增加)
	 * @author fuliwei
	 * @createTime 2016-7-21
	 * @param list
	 * @return String
	 */
	@RequestMapping(value = "/savePressureRecord")
	@ResponseBody
	public String savePressureRecord(@RequestBody ProjectCheckListPressureReq req){ 
		try {
			pressureService.savePressureRecord(req);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("保存试压记录失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
