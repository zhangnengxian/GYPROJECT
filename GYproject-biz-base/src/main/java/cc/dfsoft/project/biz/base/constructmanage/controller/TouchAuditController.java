package cc.dfsoft.project.biz.base.constructmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * 开工报告
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/touchAudit")
public class TouchAuditController {
   
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("constructmanage/touchAudit");
		return modelView;
	}
	
}
