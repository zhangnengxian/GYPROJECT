package cc.dfsoft.project.biz.base.statisticquery.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;

@Controller
@RequestMapping(value="/construction")
public class ConstructionController {
	
	/**部门service*/
	@Resource
	DepartmentService  departmentService;
	
	@RequestMapping(value = "/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("staffName",SessionUtil.getLoginInfo().getStaffName());
		List manageOfficeList =departmentService.queryManagementOffice();
		modelView.addObject("manageOfficeList",manageOfficeList);	//施工管理处
		modelView.setViewName("construction/construction");
		return modelView;
	}
}
