package cc.dfsoft.project.biz.base.withgas.controller;

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

import cc.dfsoft.project.biz.base.withgas.dto.GasProjectReq;
import cc.dfsoft.project.biz.base.withgas.entity.GasProject;
import cc.dfsoft.project.biz.base.withgas.enums.GasProjectStatusEnum;
import cc.dfsoft.project.biz.base.withgas.service.GasProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * 交付时间登记
 * @author fulw
 *
 */
@Controller
@RequestMapping(value="/deliverDateRegister")
public class DeliverDateRegisterController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(DeliverDateRegisterController.class);
	
	
	/**通气工程表*/
	@Resource
	GasProjectService gasProjectService;
	
	
	/**
	 * 打开主页面
	 * @author fuliwei  
	 * @date 2018年9月14日  
	 * @version 1.0
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("withgas/deliverDateRegister");
		return modelView;
	}
	
	
	/**
	 * 打开右侧页面
	 * @author fuliwei  
	 * @date 2018年9月14日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelView = new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);
		modelView.setViewName("withgas/deliverDateRegisterRight");
		return modelView;
	}
	
	
	/**
	 * 查询列表
	 * @author fuliwei  
	 * @date 2018年9月14日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request, GasProjectReq gasProjectReq){
		try {
			if(StringUtil.isBlank(gasProjectReq.getGasProjStatus())){
				//默认加载待登记交付时间的数据
				gasProjectReq.setGasProjStatus(GasProjectStatusEnum.DELOVER_DATE_REGISTER.getValue());
			}
			gasProjectReq.setSortInfo(request);
			return gasProjectService.queryGasProject(gasProjectReq);
		} catch (Exception e) {
			logger.error("待通气工程列表查询查询失败！", e);
			return null;
		}
	}
	
	
	/**
	 * 保存交付时间
	 * @author fuliwei  
	 * @date 2018年9月14日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/saveGasProject")
	@ResponseBody
	public String saveGasProject(@RequestBody(required = true) GasProject gasProject){
		try {
			return gasProjectService.saveDeliverDateRegister(gasProject);
		} catch (Exception e) {
			logger.error("通气工程信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
