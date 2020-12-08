package cc.dfsoft.project.biz.base.constructmanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cc.dfsoft.project.biz.base.budget.dto.MaterialListQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.MaterialPlanReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialPlan;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialPlanDetail;
import cc.dfsoft.project.biz.base.constructmanage.enums.MaterialPlanFeedBackEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.MaterialPlanDetailService;
import cc.dfsoft.project.biz.base.constructmanage.service.MaterialPlanService;
import cc.dfsoft.project.biz.base.plan.enums.ProcurementPlanExport;
import cc.dfsoft.project.biz.base.plan.enums.ProjectOperateEnum;
import cc.dfsoft.project.biz.base.subpackage.entity.SubQuantities;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.ExcelXlsxUtil;
/**
 * 施工-材料计划
 * @author fuliwei 
 * @createTime 2017-01-18
 *
 */
@Controller
@RequestMapping(value="/materialPlan")
public class MaterialPlanController {
	
	/**日志实例*/
	private static Logger logger = LoggerFactory.getLogger(MaterialPlanController.class);
	
	/**材料计划*/
	@Resource
	MaterialPlanService materialPlanService;
	
	/**材料计划明细*/
	@Resource
	MaterialPlanDetailService materialPlanDetailService;
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017-1-18
	 * @param 
	 * @return ModelAndView
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("constructmanage/materialPlan");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @author fuliwei
	 * @createTime 2017-1-18
	 * @param 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("constructmanage/materialPlanRight");
		return modelview;
	}
	
	/**
	 * 弹屏查询
	 * @author fuliwei
	 * @createTime 2017-1-31
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/materialPlanSearchPopPage")
	public ModelAndView subContractSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("isFeedBack", MaterialPlanFeedBackEnum.values());//是否反馈
		
		modelview.addObject("isExport", ProcurementPlanExport.values());//是否导出
		modelview.setViewName("constructmanage/materialPlanSearchPop");
		return modelview;
	}
	
	/**
	 * 材料计划列表查询
	 * @author fuliwei
	 * @createTime 2017-1-18
	 * @param  
	 * @return
	 */
	@RequestMapping(value = "/queryMaterialPlan")
	@ResponseBody
	public Map<String,Object> queryMaterialPlan(HttpServletRequest request,MaterialPlanReq materialPlanReq){
		try {
			materialPlanReq.setSortInfo(request);
			materialPlanReq.setIsFeedBack(MaterialPlanFeedBackEnum.HAVEN_FEED_BACK.getValue());//已反馈
			if(StringUtils.isBlank(materialPlanReq.getIsExport())){
				materialPlanReq.setIsExport(ProcurementPlanExport.HAVE_NOT_EXPORT.getValue());//默认加载未导出
			}
			Map<String,Object> map=materialPlanService.queryMaterialPlan(materialPlanReq);
			return map;
		} catch (Exception e) {
			logger.error("材料计划列表查询查询失败！", e);
			return null;
		}
	}
	
	/**
	 * MaterialPlan详述
	 * @author fuliwei
	 * @createTime 2017-1-18
	 * @param id 主键id
	 * @return MaterialPlan
	 */
	@RequestMapping(value="/viewMaterial")
	@ResponseBody
	public MaterialPlan findById(@RequestParam(required=true) String id){;
		MaterialPlan MaterialPlan = materialPlanService.findById(id);
		return MaterialPlan;
	}
	
	/**
	 * 弹出材料列表
	 * @author fuliwei
	 * @createTime 2017-1-18
	 * @param 
	 * @return ModelAndView
	 */
	@RequestMapping(value="/materialList")
	public ModelAndView materialList(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("constructmanage/materialPlanPop");
		return modelView;
	}
	
	/**
	 * 材料计划明细列表查询
	 * @author fuliwei
	 * @createTime 2017-1-30
	 * @param materialListQueryReq
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryMaterialList")
	@ResponseBody
	public Map<String,Object> queryMaterialList(HttpServletRequest request,MaterialListQueryReq materialListQueryReq){
		try {
			materialListQueryReq.setSortInfo(request);
			return materialPlanDetailService.queryMaterialPlanList(materialListQueryReq);
		} catch (Exception e) {
			logger.error("材料计划列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存材料计划
	 * @author fuliwei
	 * @createTime 2017-1-31
	 * @param mp
	 * @return String
	 */
	@RequestMapping(value = "/saveMaterialPlan")
	@ResponseBody
	public String saveMaterialPlan(@RequestBody(required = true) MaterialPlan mp){
		try {
			materialPlanService.saveMaterialPlan(mp);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("材料计划保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 分包工程量导出Excel文件
	 * @author fuliwei
	 * @createTime 2016-12-31
	 * @param String  projId
	 * @return
	 */
	@RequestMapping(value = "/exportExcel")
	public void exprotExcel(HttpServletResponse response,String  mpId){
		try {
			//材料领用计划
			List<MaterialPlanDetail> list=materialPlanDetailService.exprotExcel(mpId);
			String jsonString = JSON.toJSONString(list);
			JSONArray myJsonArray = JSONArray.parseArray(jsonString);
			String title = "材料领用计划";
			String[] excelHeader = {"材料名称","规格型号","单位","设计总量","领用总量","本次可领用量","欠量","合格证","到货时间"};
			String[] propertyNames = {"materialName","materialSpec","materialUnit","materialNum","getAnum","planNum","oweNum","certificateComplete","goodTime"};
			Integer[] headerWidth = {9000,2500,2500,2500,2500,4500,2500,2500,3500};
			Map<String,String> map = new HashMap<String,String>();
			map.put("fileName", "material-plan-Detail");
			map.put("remarkInfoHeight", "0");
			ExcelXlsxUtil.export(response, title, excelHeader, headerWidth, propertyNames, map, myJsonArray);

		} catch (Exception e) {
			logger.error("材料领用计划导出失败！", e);
		}
		
	}
	
	/**
	 * 材料计划导出标记
	 * @author fuliwei
	 * @createTime 2017-12-30
	 * @param  String projId
	 * @return String 
	 */
	@RequestMapping(value = "/signMaterialPlan")
	@ResponseBody
	public String signMaterialPlan(@RequestBody(required = true) String mpId){
		try {
			materialPlanService.signMaterialPlan(mpId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("材料计划导出标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
}
