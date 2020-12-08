package cc.dfsoft.project.biz.base.constructmanage.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.budget.dto.MaterialListQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.MaterialPlanReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialPlan;
import cc.dfsoft.project.biz.base.constructmanage.enums.MaterialPlanFeedBackEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.MaterialPlanDetailService;
import cc.dfsoft.project.biz.base.constructmanage.service.MaterialPlanService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 施工-材料计划反馈
 * @author fuliwei 
 * @createTime 2017-01-31
 *
 */
@Controller
@RequestMapping(value="/materialFeedBack")
public class MaterialFeedBackController {
	/**日志实例*/
	private static Logger logger = LoggerFactory.getLogger(MaterialFeedBackController.class);
	
	/**材料计划*/
	@Resource
	MaterialPlanService materialPlanService;
	
	/**材料计划明细*/
	@Resource
	MaterialPlanDetailService materialPlanDetailService;
	
	/**业务合作伙伴*/
	@Resource
	BusinessPartnersService businessPartnersService;
	
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
		modelView.setViewName("constructmanage/materialPlanFeedBack");
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
		modelview.setViewName("constructmanage/materialPlanFeedBackRight");
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
		BusinessPartnersReq cubq=new BusinessPartnersReq();
		cubq.setUnitType(UnitTypeEnum.CONSTRUCTION_UNIT.getValue());
		Map<String, Object> map;
		try {
			map = businessPartnersService.queryBusinessPartners(cubq);
			List culist=(List) map.get("data");
			modelview.addObject("culist",culist);	//分包单位
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelview.addObject("isFeedBack", MaterialPlanFeedBackEnum.values());//是否反馈
		modelview.setViewName("constructmanage/materialPlanFeedBackSearchPop");
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
			if(StringUtils.isBlank(materialPlanReq.getIsFeedBack())){
				materialPlanReq.setIsFeedBack(MaterialPlanFeedBackEnum.HAVE_NOT_FEED_BACK.getValue());
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
	 * 弹出材料查询 
	 * @author fuliwei
	 * @createTime 2017-1-18
	 * @param 
	 * @return ModelAndView
	 * @throws ParseException 
	 */
	@RequestMapping(value="/materialList")
	public ModelAndView materialList(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("isFeedBack", MaterialPlanFeedBackEnum.values());//是否反馈
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
			return materialPlanDetailService.queryMaterialPlanDetailList(materialListQueryReq);
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
}
