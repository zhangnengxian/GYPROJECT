package cc.dfsoft.project.biz.base.baseinfo.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.dto.ConnectContentReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.VisaQuantities;
import cc.dfsoft.project.biz.base.baseinfo.enums.UnitEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.VisaQuantitiesService;
import cc.dfsoft.uexpress.common.constant.Constants;


/**
 * 签证工程量标准
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/visaQuantities")
public class VisaQuantitiesController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(VisaQuantitiesController.class);
	
	@Resource
	VisaQuantitiesService visaQuantitiesService;
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017-2-4
	 * @param 
	 * @return ModelAndView
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("baseinfo/visaQuantities/visaQuantities");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @author fuliwei
	 * @createTime 2017年2月4日
	 * @param 
	 * @return ModelAndView
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView accessoryItemView(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("unit", UnitEnum.values());
		modelView.setViewName("baseinfo/visaQuantities/visaQuantitiesRight");
		return modelView;
	}
	
	/**
	 * 查询签证工程量标准列表
	 * @author fuliwei
	 * @createTime 2017年2月4日
	 * @param ConnectContentReq
	 * @return Map<String,Object> 
	 */
	@RequestMapping(value = "/queryVisaQuantitiesList")
	@ResponseBody
	public Map<String,Object> queryVisaQuantitiesList(HttpServletRequest request,ConnectContentReq connectContentReq){
		try {
			connectContentReq.setSortInfo(request);
			Map<String,Object> map = visaQuantitiesService.queryVisaQuanlities(connectContentReq);
			return map;
		} catch (Exception e) {
			logger.error("签证工程量标准查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 删除签证工程量
	 * @author fuliwei
	 * @createTime 2017年2月4日
	 * @param id
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteVisaQuantities")
	public String deleteVisaQuantities(String id){
		try{
			visaQuantitiesService.deleteById(id);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("签证工程量标准删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 保存签证工程量
	 * @author fuliwei
	 * @createTime 2017年2月4日
	 * @param visaQuantities
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/saveVisaQuantities")
	public String saveVisaQuantities(@RequestBody(required = true) VisaQuantities visaQuantities){
		try{
			return visaQuantitiesService.saveVisaQuantities(visaQuantities);
		}catch(Exception e){
			logger.error("签证工程量标准保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
}
