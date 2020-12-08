package cc.dfsoft.project.biz.base.design.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 设计调整
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/designAdjust")
public class DesignAdjustController {
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("accept/dataCollection");
		return modelView;
	}
}
