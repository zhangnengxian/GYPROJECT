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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.inspection.dto.GroundResistanceTestReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListASReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListGRTReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.GroundResistanceTestService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 接地电阻测试
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/groundResistanceTest")
public class GroundResistanceTestController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(GroundResistanceTestController.class);
	
	/**工程报验单服务接口*/
	@Resource
	ProjectChecklistService projectChecklistService;
	
	/**施工计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;
	
	/**接地测试服务接口*/
	@Resource
	GroundResistanceTestService groundResistanceTestService;
	/**
	 * 电器绝缘电阻主页面
	 * @author fuliwei
	 * @createTime 2017年2月6日
	 * @param 
	 * @return ModelAndView
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.addObject("drawUrl1",Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH); 	//图片 url
		modelView.addObject("pcDesId",ProjectChecklistTypeEnum.GROUND_RESISTANCE_TEST.getValue());
		modelView.addObject("qualitativeCheckMember",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());//质检员
		modelView.addObject("constructionQaePost",PostTypeEnum.MANAGEMENTQAE.getValue()); //质保师
		modelView.addObject("sujgj",PostTypeEnum.SUJGJ.getValue());//监理
		modelView.addObject("path",Constants.DISK_PATH+"sign/");
		modelView.setViewName("inspection/groundResistanceTest");
		return modelView;
	}
	
	/**
	 * 工程报验单条件查询
	 * @author fuliwei
	 * @createTime 2017-02-6
	 * @param listQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryProjectList(HttpServletRequest request,ProjectChecklistQueryReq listQueryReq){
		try {
			listQueryReq.setSortInfo(request);
			listQueryReq.setPcDesId(ProjectChecklistTypeEnum.GROUND_RESISTANCE_TEST.getValue());
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
	@RequestMapping(value="/viewGroundResistanceTest")
	@ResponseBody
	public ProjectChecklist viewGroundResistanceTest(HttpServletRequest request,@RequestParam(required=true) String id){
		String menuDes=request.getParameter("menuDes");
		ProjectChecklist list=projectChecklistService.viewProjectCheckListFile(id,menuDes);
		return list;
	}
	
	/**
	 * 接地测试记录列表查询
	 * @param request
	 * @param altimetricSurveyReq
	 * @return
	 */
	@RequestMapping(value = "/queryGroundResistanceTest")
	@ResponseBody
	public Map<String, Object> queryGroundResistanceTest(HttpServletRequest request,GroundResistanceTestReq groundResistanceTestReq){
		try {
			groundResistanceTestReq.setSortInfo(request);
			Map<String,Object> map= groundResistanceTestService.queryGroundResistanceTestSurvey(groundResistanceTestReq);
	        return map;
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 保存接地测试记录（批量增加）
	 * @param projectCheckListASReq
	 * @return
	 */
	@RequestMapping(value = "/saveGroundResistanceTestSurvey")
	@ResponseBody
	public String saveGroundResistanceTestSurvey(@RequestBody ProjectCheckListGRTReq projectCheckListGRTReq){ 
		try {
			groundResistanceTestService.saveGroundResistanceTestSurvey(projectCheckListGRTReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("保存工程测量记录失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 报验单保存(有图片)
	 * @param request
	 * @param enginneringVisa
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/saveGroundResistanceTestFile")
	@ResponseBody
	public JSONObject saveDesignAlterationFile(HttpServletRequest request,UploadResult proCheckList,@RequestParam(value = "files[]") MultipartFile[] files){
		JSONArray js = new JSONArray();
		JSONObject jso = new JSONObject();
		JSONObject json = new JSONObject();
		try {
			String pcId = projectChecklistService.saveProCheckListFile(request,proCheckList, files,Constants.GROUND_RESISTANCE_TEST);				
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", "" + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);
			json.put("operateId", pcId);
			json.put("result", Constants.OPERATE_RESULT_SUCCESS);
			return json;
		} catch (Exception e) {
			logger.error("变更信息保存失败！", e);
		}
		json.put("result", Constants.OPERATE_RESULT_FAILURE);
		return json;
	}

	/**
	 * 报验单保存(无图片)
	 * @param request
	 * @param enginneringVisa
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/saveGroundResistanceTest")
	@ResponseBody
	public JSONObject saveGroundResistanceTest(HttpServletRequest request,@RequestBody(required=true) UploadResult proCheckList){
		JSONObject json = new JSONObject();
		try {
			String pcId =projectChecklistService.saveProCheckListFile(request,proCheckList, null,Constants.GROUND_RESISTANCE_TEST);
			json.put("operateId",pcId);
			json.put("result",Constants.OPERATE_RESULT_SUCCESS);
			return json;
		} catch (Exception e) {
			logger.error("接地电阻测试报验区保存失败！", e);
		}
		json.put("result", Constants.OPERATE_RESULT_FAILURE);
		return json;
	}
}
