package cc.dfsoft.project.biz.base.statisticquery.controller;

import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 退单工程
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/chargeBack")
public class ChargeBackController {
	
	/**
	 * 打开主页面
	 * @return
	 */

	@Resource
	ProjectTypeService projTypeService;

	@Resource
	ContributionModeService contributionModeService;
	@Resource
	ProjectService projectService;
	@Resource
	DepartmentService departmentService;



	@RequestMapping(value="/main")
	public ModelAndView main(){
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		ModelAndView modelView=new ModelAndView();
		Map<String,String> deptInfo = projectService.getDeptInfoForStatistic();
		modelView.addObject("unitType", deptInfo.get("unitType"));
		modelView.addObject("deptId", deptInfo.get("deptId"));
		modelView.addObject("loginDeptId", loginInfo.getDeptId());
		modelView.addObject("corpId", loginInfo.getCorpId());//分公司ID
		modelView.addObject("projStatus", ProjStatusEnum.values());	//工程状态
		modelView.addObject("backReason", BackReasonEnum.values());//退单原因
		modelView.addObject("staffName",SessionUtil.getLoginInfo().getStaffName());
		modelView.addObject("projectType", projTypeService.queryAllList());//工程类型
		modelView.addObject("contributionMode",contributionModeService.queryAllList());	//出资方式
		modelView.addObject("departmentList",departmentService.queryListByDeptType(loginInfo));//分公司
		modelView.setViewName("project/statisticquery/projectChargeBackReport");
		return modelView;
	}
}
