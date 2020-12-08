package cc.dfsoft.uexpress.biz.sys.dept.controller;

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

import cc.dfsoft.uexpress.biz.sys.dept.dto.LocationSetUpQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.LocationSetUp;
import cc.dfsoft.uexpress.biz.sys.dept.enums.UnitTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.LocationSetUpService;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 定位设置
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/locationSetUp")
public class LocationSetUpController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(LocationSetUpController.class);
	
	/**定位设置服务接口*/
	@Resource
	LocationSetUpService locationSetUpService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("department", UnitTypeEnum.values());
		modelView.setViewName("baseinfo/locationSetUp/locationSetUp");
		return modelView;
	}
	
	/**
	 * 打开右侧
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView scoreStandardView(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("department", UnitTypeEnum.values());
		modelView.setViewName("baseinfo/locationSetUp/locationSetUpRight");
		return modelView;
	}
	
	/**
	 * 定位设置列表条件查询
	 * @author fuliwei
	 * @createTime 2017年2月24日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/quertLocationSetUp")
	@ResponseBody
	public Map<String,Object> quertLocationSetUpList(HttpServletRequest request,LocationSetUpQueryReq req){
		try {
			req.setSortInfo(request);
			return locationSetUpService.quertLocationSetUpList(req);
		} catch (Exception e) {
			logger.error("定位设置列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存定位设置
	 * @author fuliwei
	 * @createTime 2017年2月24日
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveLocationSetUp")
	public String saveLocationSetUp(@RequestBody(required = true) LocationSetUp location){
		try{
			return locationSetUpService.saveLocationSetUp(location);
		}catch(Exception e){
			logger.error("定位设置保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 删除定位设置
	 * @author fuliwei
	 * @createTime 2017年2月24日
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delLocationSetUp")
	public String delLocationSetUp(String lsuId){
		try{
			locationSetUpService.delLocationSetUp(lsuId);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("定位设置删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
