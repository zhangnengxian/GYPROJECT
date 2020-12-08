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

import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.InspectionClumEnum;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;


/**
 * 施工测量放线
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/surveyLining")
public class SurveyLiningController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(SurveyLiningController.class);
	
	/**工程报验单服务接口*/
	@Resource
	ProjectChecklistService projectChecklistService;
	
	/**施工计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;
	
	/**
	 * 测量放线主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("pcDesId",ProjectChecklistTypeEnum.SURVEY_LINING.getValue());//测量放线
		modelView.addObject("inspectionClumEnum",InspectionClumEnum.values());
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);
		modelView.addObject("constructionQaePost",PostTypeEnum.MANAGEMENTQAE.getValue()); //质保师
		modelView.addObject("suJgjPost",PostTypeEnum.SUJGJ.getValue()); //现场监理师
		modelView.addObject("surveryorPost",PostTypeEnum.SURVEYOR.getValue()); //测量员
		modelView.setViewName("inspection/surveyLining");
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
	 * @createTime 2016-07-19
	 * @param listQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProjectList")
	@ResponseBody
	public Map<String,Object> queryProjectList(HttpServletRequest request,ProjectChecklistQueryReq listQueryReq){
		try {
			listQueryReq.setSortInfo(request);
			listQueryReq.setPcDesId(ProjectChecklistTypeEnum.SURVEY_LINING.getValue());
			return projectChecklistService.queryPrProjectChecklist(listQueryReq);
		} catch (Exception e) {
			logger.error("报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2016-7-19
	 * @param id 报验单pc_id
	 * @return ProjectCheckList
	 */
	@RequestMapping(value="/viewSurveyLining")
	@ResponseBody
	public ProjectChecklist viewSurveyResult(HttpServletRequest request,@RequestParam(required=true) String id){
		ProjectChecklist list=projectChecklistService.viewProjectCheckList(id);
		return list;
	}
	
	/**
	 * 测量放线保存
	 * @author fuliwei
	 * @createTime  2016-7-19
	 * @param checkList
	 * @return String
	 */
	@RequestMapping(value = "/saveSurveyLining")
	@ResponseBody
	public String saveSurveyLining(@RequestBody(required = true) ProjectChecklist checkList){
		try {
			return projectChecklistService.saveProCheckList(checkList,Constants.MODULE_CODE_SURVEY_LINING);
		} catch (Exception e) {
			logger.error("测量放线信息区保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
