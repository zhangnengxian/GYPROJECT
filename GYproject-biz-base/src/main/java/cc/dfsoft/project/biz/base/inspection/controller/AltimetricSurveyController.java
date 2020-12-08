package cc.dfsoft.project.biz.base.inspection.controller;

import java.util.List;
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

import cc.dfsoft.project.biz.base.inspection.dto.AltimetricSurveyReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListASReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.InspectionClumEnum;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.AltimetricSurveyService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;



@Controller
@RequestMapping(value="/altimetricSurvey")
public class AltimetricSurveyController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(AltimetricSurveyController.class);
	
	/** 工程报验服务接口 */
	@Resource
	ProjectChecklistService proCheckListService;
	@Resource
	AltimetricSurveyService altimetricSurveyService;
	@Resource
	ConstructionPlanService constructionPlanService;
	/**
	 * 高程测量主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("inspection/altimetricSurvey");
		modelView.addObject("checkType",ProjectChecklistTypeEnum.ALTIMETRIC_SURVEY.getValue());
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.addObject("constPCpost",PostTypeEnum.DEPUTY_DIRECTOR.getValue());
		modelView.addObject("constructionQcpost",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());
		modelView.addObject("asPersonpost",PostTypeEnum.SURVEYOR.getValue());
		modelView.addObject("suJgjpost",PostTypeEnum.SUJGJ.getValue());
		modelView.addObject("inspectionClumEnum",InspectionClumEnum.values());
		return modelView;
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2016-7-20
	 * @param id 工程id
	 * @return ProjectCheckList
	 */
	@RequestMapping(value="/viewAltimetric")
	@ResponseBody
	public ProjectChecklist viewAltimetric(HttpServletRequest request,@RequestParam(required=true) String id){
		ProjectChecklist list=proCheckListService.viewProjectCheckList(id);
		return list;
	}
	
	/**
	 * 工程报验单列表查询
	 * @param ProCheckListReq
	 * @author zhangjj
	 * @createTime 2016-07-06
	 * @return
	 */
	@RequestMapping(value = "/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq proCheckListReq){
		try {
			proCheckListReq.setSortInfo(request);
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.ALTIMETRIC_SURVEY.getValue());
			Map<String,Object> map= proCheckListService.queryPrProjectChecklist(proCheckListReq);
	        return map;
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 高程测量记录列表查询
	 * @param ProCheckListReq
	 * @author zhangjj
	 * @createTime 2016-07-06
	 * @return
	 */
	@RequestMapping(value = "/queryAltimSurvey")
	@ResponseBody
	public Map<String, Object> queryAltimSurvey(HttpServletRequest request,AltimetricSurveyReq altimetricSurveyReq){
		try {
			altimetricSurveyReq.setSortInfo(request);
			Map<String,Object> map= altimetricSurveyService.queryAltimSurvey(altimetricSurveyReq);
	        return map;
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 根据工程编号获取施工计划
	 * @param projNo
	 * @return
	 */
	@RequestMapping(value = "/queryConstructPlan")
	@ResponseBody
	public Object queryConstructPlan(String projNo){
		try{
		List<ConstructionPlan> list= constructionPlanService.findByProjNo(projNo);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
	       
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			
		}
		return null;
	}
	
	/**
	 * 保存报验单
	 * @author zhangjj
	 * @param projectChecklist
	 * @return
	 */
	@RequestMapping(value = "/saveCheckList")
	@ResponseBody
	public String saveCheckList(@RequestBody ProjectChecklist projectChecklist){ 
		try {
			return proCheckListService.saveProCheckList(projectChecklist,Constants.MODULE_CODE_ALTIMETRIC_SURVEY);
		} catch (Exception e) {
			logger.error("保存报验单失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 保存工程测量记录(批量增加)
	 * @author zhangjj
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/saveAltimSurvey")
	@ResponseBody
	public String saveAltimSurvey(@RequestBody ProjectCheckListASReq projectCheckListASReq){ 
		try {
			altimetricSurveyService.saveAltimSurvey(projectCheckListASReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("保存工程测量记录失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

}
