package cc.dfsoft.project.biz.base.baseinfo.controller;

import java.util.List;
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
import cc.dfsoft.project.biz.base.baseinfo.service.ConnectContentService;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectContent;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.controller.AccessoryItemController;
import cc.dfsoft.uexpress.common.constant.Constants;


@Controller
@RequestMapping(value="/connectContent")
public class ConnectContentController {
	
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(AccessoryItemController.class);
	
	/**
	 * 碰口内容
	 */
	@Resource
	ConnectContentService connectContentService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		List<UnitTypeEnum> list = UnitTypeEnum.getObjByType("3");
		modelView.addObject("maintypeDeses", list);
		modelView.setViewName("baseinfo/connectContent/connectContent");
		return modelView;
	}
	
	/**
	 * 打开右侧
	 * @return
	 */
	@RequestMapping(value="/connectContentView")
	public ModelAndView accessoryItemView(){
		ModelAndView modelView=new ModelAndView();
		List<UnitTypeEnum> list = UnitTypeEnum.getObjByType("2");
		modelView.addObject("typeDeses", list);
		modelView.setViewName("baseinfo/connectContent/connectContentRight");
		return modelView;
	}
	
	
	/**
	 * 碰口内容列表条件查询
	 * @param 
	 * @author 刘博
	 * @createTime 2016-08-03
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryConnectContent")
	@ResponseBody
	public Map<String,Object> queryConnectContent(HttpServletRequest request,ConnectContentReq connectContentReq){
		try {
			connectContentReq.setSortInfo(request);
			Map<String,Object> map = connectContentService.queryConnectContent(connectContentReq);
			return map;
		} catch (Exception e) {
			logger.error("碰口内容查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 删除碰口内容
	 * @author 刘博
	 * @createTime 2016-08-03
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteConnectContent")
	public String deleteConnectContent(String id){
		try{
			connectContentService.deleteConnectContent(id);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("碰口内容删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 保存碰口内容
	 * @author 刘博
	 * @createTime 2016-7-15
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/saveConnectContent")
	public String saveOrUpdateConnectContent(@RequestBody(required = true) ConnectContent connectContent){
		try{
			return connectContentService.saveOrUpdateConnectContent(connectContent);
		}catch(Exception e){
			logger.error("碰口内容保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
