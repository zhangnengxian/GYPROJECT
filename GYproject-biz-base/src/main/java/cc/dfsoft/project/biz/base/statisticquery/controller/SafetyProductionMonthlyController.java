package cc.dfsoft.project.biz.base.statisticquery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;

/**
 * 安全生产月报
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/safetyProductionMonthly")
public class SafetyProductionMonthlyController {
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("staffName",SessionUtil.getLoginInfo().getStaffName());
		modelView.setViewName("project/statisticquery/safetyProductionMonthly");
		return modelView;
	}
}
