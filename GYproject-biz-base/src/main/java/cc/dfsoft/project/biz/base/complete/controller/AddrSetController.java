package cc.dfsoft.project.biz.base.complete.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.project.entity.ProjectType;


/**
 * 服务信息初始化
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="addrSet")
public class AddrSetController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(AddrSetController.class);
	
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("complete/addrSet");
		return modelView;
	}
	
	
	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("complete/addrSetRight");
		return modelview;
		
	}
	
	/**
	 * 打开安装记录右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewInstallPage")
	public ModelAndView viewInstallPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("complete/installRecordRight");
		return modelview;
		
	}
	
	
	/**
	 * 列表条件查询
	 * @param request
	 * @param jointAcceptanceReq
	 * @return
	 *//*
	@RequestMapping(value = "/queryJointAcceptance")
	@ResponseBody
	public Map<String,Object> queryJointAcceptancer(HttpServletRequest request,JointAcceptanceReq jointAcceptanceReq){
		try {
			jointAcceptanceReq.setSortInfo(request);
			Map<String,Object> map = jointAcceptanceService.queryJointAcceptance(jointAcceptanceReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}*/
	
	
	/**
	 * 弹出创建地址
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/addrBatchPopPage")
	public ModelAndView addrBatchPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("complete/addrBatchPopPage");
		return modelview;
	}
	
	
	/**
	 * 弹出创建地址
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/meterTypePopPage")
	public ModelAndView meterTypePopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("complete/meterTypePopPage");
		return modelview;
	}
}
