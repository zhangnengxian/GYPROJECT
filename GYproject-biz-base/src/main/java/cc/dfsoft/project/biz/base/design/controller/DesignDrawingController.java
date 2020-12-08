package cc.dfsoft.project.biz.base.design.controller;

import cc.dfsoft.project.biz.base.common.enums.ButtonLimitsEnum;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.dto.DrawingsDirectoryReq;
import cc.dfsoft.project.biz.base.design.entity.DrawingsDirectory;
import cc.dfsoft.project.biz.base.design.service.DesignDrawingService;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.AreaEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.ExcelXlsxUtil;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
/**
 * 设计草图
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/designDrawing")
public class DesignDrawingController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(DesignDrawingController.class);
	
	/** 设计草图服务接口 */
	@Resource
	DesignDrawingService designDrawingService;
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	
	/**设计信息服务接口*/
	@Resource
	DesignInfoService designInfoService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.addObject("changeDesigner",ButtonLimitsEnum.CHANGE_DESIGNER.getType());
		modelView.addObject("conformDesigner",ButtonLimitsEnum.CONFORM_DESIGNER.getType());
		modelView.addObject("importDesigner",ButtonLimitsEnum.IMPORT_DESIGNER.getType());
		modelView.addObject("projStatus", ProjStatusEnum.TO_OUT_SKETCH.getValue());
		modelView.addObject("notThrough",Constants.MODULE_CODE_DESIGN);
		modelView.addObject("stepId",StepEnum.DRAWING_SIGN_AUDIT.getValue());
		modelView.addObject("curStepId",StepEnum.DESIGN_DRAWING.getValue());
		modelView.addObject("customerServiceCenter",DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());
		modelView.setViewName("design/designDrawing");
		return modelView;
	}
	
	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author pengtt
	 * @createTime 2016-06-21
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.DESIGN_DRAWING.getValue());
			return projectService.queryProjectByNetTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("design/designDrawingRight");
		return modelview;
		
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017-7-27
	 * @param id 工程id
	 * @return Project
	 */
	@RequestMapping(value="/viewProject")
	@ResponseBody
	public Project viewDispatchResult(@RequestParam(required=true) String id){
		return designInfoService.findByProjId(id);
	}
	
	/**
	 * 图纸目录列表查询
	 * @param PageSortReq
	 * @return
	 */
	@RequestMapping(value = "/queryDrawDirectory")
	@ResponseBody
	public Map<String, Object> queryDrawDirectory(HttpServletRequest request,DrawingsDirectoryReq pageSortReq) {
		try {
			pageSortReq.setSortInfo(request);
			Map<String, Object> map=designDrawingService.queryDrawDirectory(pageSortReq);
		    return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 新增图纸目录---没用
	 * @param DrawingsDirectory
	 * @return
	 */
	@RequestMapping(value = "/insertDrawDirectory")
	@ResponseBody
	public String insertDrawDirectory(@RequestBody(required = true) DrawingsDirectory drawingsDirectory) {
		try {
			
		   String returnVal=designDrawingService.judgeRepeat(drawingsDirectory);
		   //returnVal为""时图号和名称均不重复
		   if(returnVal.equals("")){
			//保存新纪录
			designDrawingService.insertDrawDirectory(drawingsDirectory);
			
			return Constants.OPERATE_RESULT_SUCCESS;
		   }else{
			   //returnVal为drawNo图号重复，drawName图名重或者drawNodrawName两者均重复
			  return returnVal; 
		   }
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	
	
	
	/**
	 * 打开查询弹框
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.addObject("area", AreaEnum.values());
		modelview.setViewName("design/draw-pro-search");
		return modelview;
		
	}
	
	/**
	 * 推送到图纸审核
	 * @param proReq
	 * @return
	 */
	@RequestMapping(value = "/updateState")
	@ResponseBody
	public String updateState(@RequestBody(required = true)ProjectQueryReq  proReq){ 
		try {
			designDrawingService.updateState(proReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("设计出图保存失败！");
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 改派保存
	 * @param projId
	 * @return
	 */
	@RequestMapping(value = "/updateProject")
	@ResponseBody
	public String updateProject(@RequestBody(required = true) DesignDispatchDto designDispatchReq){ 
		try {
			designDrawingService.updateProject(designDispatchReq);
			 return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	
	/**
	 * 打开文件导入页面
	 * @return
	 */
	@RequestMapping(value = "/importPop")
	public ModelAndView exportPop(String url) {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("url", url);
		modelview.setViewName("budget/importPop");
		return modelview;
	}
	
	/**
	 * 图纸批量导入
	 * @author fuliwei
	 * @createTime 2016-7-25
	 * @param
	 * @return void
	 */
	@RequestMapping(value = "/importExcel")
	@ResponseBody
	public JSONObject ImportExcel(HttpServletRequest request,String projId,@RequestParam(value = "files[]") MultipartFile files[]) {
		try {
			// ["序号","目录","图号","图幅","实际张数","折合张数","备注"];
			String[] params = { "ddNum", "drawDirect", "drawingNo", "mapSheet", "drawQuantity","convertIntoNum","remark"};
			JSONArray jsonarr= ExcelXlsxUtil.importExcelJson(files[0], "图纸目录", 0, 2, 0, params);			 			 
			 if (null != jsonarr && jsonarr.size() > 0) {
				 designDrawingService.batInsertCostSum(jsonarr,projId);
			}
			String url = request.getRequestURL().toString();
			int i = url.lastIndexOf("/");
			url = url.substring(0, i + 1);
			JSONArray js = new JSONArray();
			JSONObject jso = new JSONObject();
			JSONObject json = new JSONObject();
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", url + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);

			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 采购计划保存--没用
	 * @param projId
	 * @return
	 */
	@RequestMapping(value = "/saveProcurePlan")
	@ResponseBody
	public String saveProcurePlan(@RequestBody(required = true) String projId){ 
		try {
			designDrawingService.saveProcurePlan(projId);
			 return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	
	
	/**
	 * 踏勘员列表查询
	 * @author fuliwei
	 * @createTime 2017年7月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryDesigner")
	@ResponseBody
	public Map<String,Object> querySurveyer(HttpServletRequest request, DesignerQueryReq designerQueryReq){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			designerQueryReq.setSortInfo(request);
			designerQueryReq.setPostType(PostTypeEnum.DESIGNER.getValue());  			//设计人
			designerQueryReq.setProjStatus(ProjStatusEnum.TO_OUT_SKETCH.getValue());	//待设计出图 
			result= designInfoService.querySurveyer(designerQueryReq);
			return result;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
}
