package cc.dfsoft.project.biz.base.design.controller;

import cc.dfsoft.project.biz.base.design.dto.DrawingsDirectoryReq;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.design.service.OfficialDrawingService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
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
import java.util.Map;
/**
 * 收正式图
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/officialDrawing")
public class OfficialDrawingController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(OfficialDrawingController.class);
	
	/** 正式出图服务接口 */
	@Resource
	OfficialDrawingService officialDrawingService;
	
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
		modelView.setViewName("design/officialDrawing");
		return modelView;
	}
	
	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author zhangmeng
	 * @createTime 2016-07-06
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_FORMAL_DRAW.getValue());
			projectQueryReq.setStepId(StepEnum.OFFICIAL_DRAWING.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
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
		modelview.setViewName("design/officialDrawingRight");
		return modelview;
		
	}
	
	/**
	 * 打开查询弹框
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		//提示修改
		modelview.setViewName("design/projectSearchPopPage");
		return modelview;
		
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月26日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewProject")
	@ResponseBody
	public  DesignInfo viewProject(@RequestParam(required=true) String id){
		DesignInfo di=designInfoService.queryById(id);
		return di;
	}
	
	
	
	/**
	 * 材料目录列表查询
	 * @param PageSortReq
	 * @return
	 */
	@RequestMapping(value = "/queryMaterialDirectory")
	@ResponseBody
	public Map<String, Object> queryMaterialDirectory(HttpServletRequest request,DrawingsDirectoryReq pageSortReq) {
		try {
			pageSortReq.setSortInfo(request);
			Map<String, Object> map=officialDrawingService.queryMaterialDirectory(pageSortReq);
		    return map;
		} catch (Exception e) {
			logger.error("材料列表查询失败！", e);
		}
		return null;
	}
	
	/**
	 * 打开文件导入页面
	 * 
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
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @param fileName
	 * @return
	 */
	@RequestMapping(value = "/importExcel")
	@ResponseBody
	public JSONObject ImportExcel(HttpServletRequest request,String projId,@RequestParam(value = "files[]") MultipartFile files[]) {
		try {
			// ["序号","材料汇总表","材料","单位","数量","备注"];
			String[] params = { "dmNo","dmName","dmSpec", "dmUnit", "dmNum", "remark", "flag"};
			JSONArray jsonarr= ExcelXlsxUtil.importExcelJson(files[0], "材料目录", 0, 2, 0, params);			 			 
			 if (null != jsonarr && jsonarr.size() > 0) {
				 officialDrawingService.batInsertCostSum(jsonarr,projId);
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
	 * 图纸签收确认
	 * @author fuliwei
	 * @createTime 2017年8月26日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateState")
	@ResponseBody
	public String updateState(@RequestBody(required = true) DesignInfo designInfo){ 
		try {
			officialDrawingService.updateState(designInfo);
			 return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("图纸签收操作区保存数据失败！");
			return Constants.OPERATE_RESULT_FAILURE;
		}
		
	}
	
	
}
