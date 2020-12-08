package cc.dfsoft.project.biz.base.statisticquery.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;

/**
 * 操作记录统计
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/operateRecordSatistic")
public class OperateRecordSatisticController {
	
	/**部门Service*/
	@Resource
	DepartmentService departmentService;
	@Resource
	ProjectTypeService projTypeService;

	@Resource
	ContributionModeService contributionModeService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("staffName",SessionUtil.getLoginInfo().getStaffName());
		modelView.addObject("setpName",StepEnum.values());
		List<Department> departmentList=departmentService.queryAllList();
		modelView.addObject("department", departmentList);
		modelView.addObject("projectType", projTypeService.queryAllList());//工程类型
		modelView.addObject("contributionMode",contributionModeService.queryAllList());	//出资方式
		modelView.setViewName("project/statisticquery/operateRecordSatistic");
		return modelView;
	}
}
