package cc.dfsoft.project.biz.base.statisticquery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;

/**
 * 施工部门计划
 * @author cui
 *
 */
@Controller
@RequestMapping(value="/managementOfficePlan")
public class ManagementOfficePlanController {
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projStatus", ProjStatusEnum.values());	//工程状态
		modelView.addObject("staffName",SessionUtil.getLoginInfo().getStaffName());
		modelView.setViewName("project/statisticquery/projectManagementOfficePlanReport");
		return modelView;
	}
}
