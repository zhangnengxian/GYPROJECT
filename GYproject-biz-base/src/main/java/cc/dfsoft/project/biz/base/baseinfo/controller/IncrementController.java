package cc.dfsoft.project.biz.base.baseinfo.controller;

import java.text.ParseException;
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

import cc.dfsoft.project.biz.base.baseinfo.dto.IncrementReg;
import cc.dfsoft.project.biz.base.baseinfo.service.IncrementService;
import cc.dfsoft.project.biz.base.contract.entity.Increment;

@Controller
@RequestMapping(value="/increment")
public class IncrementController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(IncrementController.class);
	
	/**企业合作伙伴服务接口*/
	@Resource
	IncrementService incrementService;

	
	/**
	 * 打开主页面
	 * @author cui
	 *  
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("baseinfo/increment/increment");  //实现主页面的跳转
		return modelView;
	}	
	/**
	 * 右边页面的跳转
	 * @return
	 */
	@RequestMapping(value = "/viewIncrementPage")
	public ModelAndView viewBusinessPartnersPage() {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("baseinfo/increment/incrementRight"); //实现右边页面的跳转
		return modelView;		
	}
	
	/**
	 * 查询税率息
	 * @author cui
	 * @createTime
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryIncrementInfo")
	@ResponseBody
	public Map<String,Object> queryIncrementInfo(IncrementReg incrementReg){
		try {
			Map<String,Object> map=incrementService.queryIncrementInfo(incrementReg); //查询税率信息
			return map;
		} catch (Exception e) {
			logger.error("税率信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 详述
	 * @author cui
	 * @createTime 2016-7-11
	 * @param id 税率id
	 * @return Contract
	 * @throws ParseException 
	 */
	@RequestMapping(value="/viewIncrementDetail")
	@ResponseBody
	public Increment viewIncrementDetail(@RequestParam(required=true) String id) {
		Increment increment=incrementService.viewIncrementDetailById(id);   //调用IncrementService中的viewIncrementDetail方法
		return increment;
	}
	/**
	 * 删除税率
	 * @author cui
	 * @createTime 2016-7-11
	 * 
	 */
	@RequestMapping(value = "/delIncrement")
	public void delIncrement(@RequestParam(required=true) String id) {
		try{
			incrementService.deleteIncrement(id);
       }catch(Exception e){
			e.printStackTrace();    
		}
	}
/**
	 * 保存税率信息
	 * @author cui
	 * @createTime 2016-7-11
	 * @param  
	 * @return 
	 */
	@RequestMapping(value = "/saveIncrement")
	@ResponseBody
	public void saveIncrement(@RequestBody(required = true) Increment increment){
		try {
			incrementService.saveIncrement(increment);
		} catch (Exception e) {
			logger.error("税率信息保存失败！", e);
		}
	}
}
