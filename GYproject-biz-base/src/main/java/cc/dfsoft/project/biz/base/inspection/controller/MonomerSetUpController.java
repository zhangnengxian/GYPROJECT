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

import cc.dfsoft.project.biz.base.inspection.dto.AltimetricSurveyReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListMSUReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.CheckItemsEnum;
import cc.dfsoft.project.biz.base.inspection.enums.CheckResultEnum;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.MonomerSetUpService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 单体调校
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/monomerSetUp")
public class MonomerSetUpController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(AltimetricSurveyController.class);
	
	/** 工程报验服务接口 */
	@Resource
	ProjectChecklistService proCheckListService;
	@Resource
	MonomerSetUpService monomerSetUpService;
	@Resource
	ConstructionPlanService constructionPlanService;
	@RequestMapping(value="/main")
	
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("pcDesId",ProjectChecklistTypeEnum.MONOMER_SET_UP.getValue());			//单体调校
		modelView.addObject("checkItems",CheckItemsEnum.values());									//检查项目
		modelView.addObject("result",CheckResultEnum.values());										//检查结果
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);					//img url
		modelView.addObject("technician",PostTypeEnum.TECHNICIAN.getValue());						//技术员
		modelView.addObject("constructionQcpost",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());	//质检员
		modelView.addObject("subPrincipal",PostTypeEnum.SUB_PRINCIPAL.getValue());					//分包负责人
		modelView.addObject("suJgjpost",PostTypeEnum.SUJGJ.getValue());								//现场监理师
		modelView.setViewName("inspection/monomerSetUp");
		return modelView;
	}
	/**
	 * 工程报验单列表查询
	 * @param request
	 * @param proCheckListReq
	 * @return
	 */
	@RequestMapping(value = "/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq proCheckListReq){
		try {
			proCheckListReq.setSortInfo(request);
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.MONOMER_SET_UP.getValue());
			Map<String,Object> map= proCheckListService.queryPrProjectChecklist(proCheckListReq);
	        return map;
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 详述
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewAltimetric")
	@ResponseBody
	public ProjectChecklist viewAltimetric(HttpServletRequest request,@RequestParam(required=true) String id){
		ProjectChecklist list=proCheckListService.viewProjectCheckList(id);
		return list;
	}
	/**
	 * 单体调校记录列表查询
	 * @param request
	 * @param altimetricSurveyReq
	 * @return
	 */
	@RequestMapping(value = "/queryMonomerSetUpSurvey")
	@ResponseBody
	public Map<String, Object> queryMonomerSetUpSurvey(HttpServletRequest request,AltimetricSurveyReq altimetricSurveyReq){
		try {
			altimetricSurveyReq.setSortInfo(request);
			Map<String,Object> map= monomerSetUpService.queryMonomerSetUp(altimetricSurveyReq);
	        return map;
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 单体调校记录保存
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/saveMonomerSetUpRecord")
	@ResponseBody
	public String saveMonomerSetUpRecord(@RequestBody ProjectCheckListMSUReq req){ 
		try {
			monomerSetUpService.saveMonomerSetUps(req);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("保存单体调校记录失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
