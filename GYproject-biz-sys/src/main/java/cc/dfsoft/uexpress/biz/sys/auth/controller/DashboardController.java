package cc.dfsoft.uexpress.biz.sys.auth.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统维护首屏控制器
 * @author ferrinweb
 *
 */
@Controller
@RequestMapping("/system")
public class DashboardController {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(DashboardController.class);

	
	/**
	 * 登陆首屏页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/dashboard")
	public ModelAndView meterInfo(Model model) {
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("system/dashboard");
		return modelView;
	}
}
