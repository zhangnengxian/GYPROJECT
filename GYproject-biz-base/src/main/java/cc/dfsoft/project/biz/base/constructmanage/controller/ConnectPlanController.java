package cc.dfsoft.project.biz.base.constructmanage.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.budget.service.ChangeManagementService;
import cc.dfsoft.project.biz.base.constructmanage.dto.DigRoadQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.project.biz.base.constructmanage.entity.DigRoad;
import cc.dfsoft.project.biz.base.constructmanage.entity.TouchPlan;
import cc.dfsoft.project.biz.base.constructmanage.service.DigRoadService;
import cc.dfsoft.project.biz.base.constructmanage.service.TouchPlanService;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.FileUtil;

/**
 * 碰口方案
 * 
 * @author pengtt
 * @createTime 2016-08-03
 */
@Controller
@RequestMapping(value = "/touchPlan")
public class ConnectPlanController {

	/** 日志实例 */
	public static Logger logger = LoggerFactory.getLogger(ConnectPlanController.class);

	/** 工程服务接口 */
	@Resource
	ProjectService projectService;

	/** 施工合同服务接口 */
	@Resource
	ContractService contractService;

	/** 碰口方案服务接口 */
	@Resource
	TouchPlanService touchPlanService;

	/** 路况服务接口 */
	@Resource
	DigRoadService digRoadService;
	
	@Resource
	ChangeManagementService changeManagementService;

	/**
	 * 打开主页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("constructionPrincipal", PostTypeEnum.SUB_FIELDPRINCIPAL.getValue());
		modelView.addObject("imgUrl", Constants.DISK_PATH + Constants.SIGN_DISK_PATH); // img
		modelView.addObject("drawUrl1",Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH); 	//img url																	// url
		modelView.setViewName("constructmanage/connectPlan");
		return modelView;
	}

	/**
	 * 碰口方案详述
	 * 
	 * @return
	 */
	@RequestMapping(value = "/touchPlanDetail")
	@ResponseBody
	public TouchPlan touchPlanDetail(HttpServletRequest request,@RequestParam(required = true) String id) {
		String menuDes=request.getParameter("menuDes");
		TouchPlan touchPlan=touchPlanService.touchPlanDetail(id,menuDes);
		return touchPlan;
	}

	/**
	 * 开挖路况详述
	 * 
	 * @return
	 */
	@RequestMapping(value = "/digRoadDetail")
	@ResponseBody
	public Map<String, Object> digRoadDetail(HttpServletRequest request, DigRoadQueryReq digRoadQueryReq) {
		try {
			digRoadQueryReq.setSortInfo(request);
			return digRoadService.digRoadDetail(digRoadQueryReq);
		} catch (Exception e) {
			logger.error("开挖路况详述查询失败!", e);
			return null;
		}
	}

	/**
	 * 保存碰口方案(图片)
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/saveTouchPlanFile")
	@ResponseBody
	public JSONObject saveTouchPlanFile(HttpServletRequest request,UploadResult changeManagement,@RequestParam(value = "files[]", required = false) MultipartFile[] files) {
		System.out.println("**************************************************");
		try {
			changeManagementService.saveChangeConnect(request,changeManagement, files);
			JSONArray js = new JSONArray();
			JSONObject jso = new JSONObject();
			JSONObject json = new JSONObject();
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", "" + "deleteImport?fileName=" + files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);
			json.put("result", Constants.OPERATE_RESULT_SUCCESS);
			json.put("drawUrl", request.getAttribute("drawUrl"));
			return json;
		} catch (Exception e) {
			logger.error("碰口方案保存失败!", e);
			JSONObject json = new JSONObject();
			json.put("result", Constants.OPERATE_RESULT_FAILURE);
			return json;
		}
		
	}
	
	
	/**
	 * 保存碰口方案
	 * 
	 * @param
	 * @return
	 */
	
	@RequestMapping(value = "/saveTouchPlanAll")
	@ResponseBody
	public String saveTouchPlan(@RequestBody TouchPlan touchPlan){
		try {
			touchPlanService.saveTouchPlan(touchPlan);
			return touchPlan.getTpId();
		} catch (Exception e) {
			logger.error("碰口方案保存失败!",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
   }


	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public JSONObject uploadFile(HttpServletRequest request,AccessoryList accDto,@RequestParam(value = "files[]", required = false) MultipartFile[] files) {
		try {
			//accessoryService.uploadAccessory(request, accDto, files);
			JSONArray js = new JSONArray();
			JSONObject jso = new JSONObject();
			JSONObject json = new JSONObject();
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", "" + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);

			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FileUtil.deleteFile(accDto.getAlPath());
			e.printStackTrace();
			logger.error("保存失败",e);
		} 
	 
		return null;
		
	}
	
	
	/**
	 * 碰口方案保存(没有图片)
	 * @author zhangmeng
	 * @createTime  2016-9-30
	 * @param changeManagement
	 * @return String
	 */
	@RequestMapping(value = "/saveTouchPlanTo")
	@ResponseBody
	public String saveDesignAlteration(HttpServletRequest request,@RequestBody(required=true) UploadResult changeManagement){
		try {
			changeManagementService.saveChangeConnect(request,changeManagement, null);	
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("变更信息保存失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	
	/**
	 * 保存开挖路况
	 * 
	 * @author cui
	 * @param digRoadList
	 * @return
	 */
	@RequestMapping(value = "/saveDigRoad")
	@ResponseBody
	public String saveDigRoad(@RequestBody List<DigRoad> digRoadList) {
		try {
			touchPlanService.saveDigRoad(digRoadList);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("开挖路况保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
