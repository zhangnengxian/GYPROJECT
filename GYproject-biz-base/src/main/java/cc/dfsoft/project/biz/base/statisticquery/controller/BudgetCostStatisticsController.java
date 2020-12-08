package cc.dfsoft.project.biz.base.statisticquery.controller;

import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 预算造价统计
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/budgetCostStatistics")
public class BudgetCostStatisticsController {
	
	@Resource
	StaffService staffService;
	@Resource
	ProjectTypeService projTypeService;

	@Resource
	ContributionModeService contributionModeService;
	@Resource
	DepartmentService departmentService;

	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){

		LoginInfo loginInfo=SessionUtil.getLoginInfo();

		ModelAndView modelView=new ModelAndView();
		List budgetList =staffService.queryPost(PostTypeEnum.BUDGET_MEMBER.getValue());
		modelView.addObject("budgetList",budgetList);
		modelView.addObject("projLtype",ProjLtypeEnum.values());//工程大类
		modelView.addObject("staffName",SessionUtil.getLoginInfo().getStaffName());
		modelView.addObject("projectType", projTypeService.queryAllList());//工程类型
		modelView.addObject("contributionMode",contributionModeService.queryAllList());	//出资方式
		modelView.addObject("corpId", loginInfo.getCorpId());//分公司ID
		modelView.addObject("departmentList",departmentService.queryListByDeptType(loginInfo));//子公司

		modelView.setViewName("project/statisticquery/budgetCostStatistics");
		return modelView;
	}
}
