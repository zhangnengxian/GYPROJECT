package cc.dfsoft.project.biz.base.baseinfo.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.baseinfo.enums.ExternalUnitTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
/**
 * 企业合作
 * @author cui
 *
 */
@Controller
@RequestMapping(value="/businessPartners")
public class BusinessPartnersController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(BusinessPartnersController.class);
	
	/**企业合作伙伴服务接口*/
	@Resource
	BusinessPartnersService businessPartnersService;

	
	/**
	 * 打开主页面
	 * @author cui
	 *  
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		//modelView.addObject("unitType",UnitTypeEnum.getObjByType("2"));
		//List<UnitTypeEnum> e=UnitTypeEnum.getObjByType("2");
		modelView.setViewName("baseinfo/businessPartners/businessPartners");
		return modelView;
	}
	
	/**
	 * @author cui
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewBusinessPartnersPage")
	public ModelAndView viewBusinessPartnersPage() {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("unitType",UnitTypeEnum.getObjByType("2"));
		modelView.addObject("deptType",DeptTypeEnum.SUBCOMPANY.getValue());
		modelView.setViewName("baseinfo/businessPartners/businessPartnersRight");
		return modelView;		
	}
	
	/**
	 * 单位条件查询
	 * @author cui
	 * @createTime
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryBusinessPartners")
	@ResponseBody
	public Map<String,Object> queryBusinessPartners(BusinessPartnersReq businessPartnersReq){
		try {
			Map<String,Object> map=businessPartnersService.queryBusinessPartners(businessPartnersReq);
			return map;
		} catch (Exception e) {
			logger.error("单位信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 详述
	 * @author cui
	 * @createTime 2016-7-11
	 * @param id 监理单位id
	 * @return Contract
	 * @throws ParseException 
	 */
	@RequestMapping(value="/viewBusinessPartners")
	@ResponseBody
	public BusinessPartners viewBusinessPartners(@RequestParam(required=true) String id) {
		BusinessPartners businessPartners=businessPartnersService.viewBusinessPartnersById(id);
		return businessPartners;
	}
	
	/**
	 * 弹出搜索屏
	 * @author cui
	 * @createTime 2016-7-11
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/businessPartnersSearchPopPage")
	public ModelAndView businessPartnersSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("unitType",UnitTypeEnum.getObjByType("2"));
		modelview.setViewName("baseinfo/businessPartners/businessPartnersSearchPopPage");
		return modelview;
	}
	
	/**
	 * 保存监理单位
	 * @author cui
	 * @createTime 2016-7-11
	 * @param  
	 * @return 
	 */
	@RequestMapping(value = "/saveBusinessPartners")
	@ResponseBody
	public void saveBusinessPartners(@RequestBody(required = true) BusinessPartners businessPartners){
		try {
			businessPartnersService.saveBusinessPartners(businessPartners);
		} catch (Exception e) {
			logger.error("单位信息保存失败！", e);
		}
	}
	
	/**
	 * 删除监理单位
	 * @author cui
	 * @createTime 2016-7-11
	 * 
	 */
	@RequestMapping(value = "/delBusinessPartners")
	public void delBusinessPartners(@RequestParam(required=true) String id) {
		try{
			businessPartnersService.deleteBusinessPartners(id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 获取业务合作伙伴树结构信息
	 * @return
	 */
	@RequestMapping(value = "/getBusPartTreeData")
	@ResponseBody
	public List<Map<String, Object>> getDeptTreeData(String unitType){
		try{
			List<Map<String, Object>> maplist = businessPartnersService.getDataTree(unitType);
			return maplist;
		}catch(Exception e){
			logger.error("获取业务合作伙伴树结构信息发生异常。", e);
			return null;
		}
	}
}

	
	
	
	
	