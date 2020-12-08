package cc.dfsoft.project.biz.base.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 欢迎屏
 * @author yehaiqing
 *
 */
@Controller
@RequestMapping(value="/welcome")
public class WelcomeController {
	
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("common/welcome");
		return modelview;
	}
	
}
