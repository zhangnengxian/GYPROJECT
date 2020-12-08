package cc.dfsoft.project.biz.base.statisticquery.controller;

import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
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
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 安全质量检查
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/safetyQualityInspection")
public class SafetyQualityInspectionController {
	
	/**业务合作伙伴*/
	@Resource
	BusinessPartnersService businessPartnersService;
	@Resource
	ProjectTypeService proTypeService;
	@Resource
	ContributionModeService contributionModeService;
	@Resource
	ProjectService projectService;
	@Resource
	DepartmentService departmentService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main() throws ParseException {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		ModelAndView modelView=new ModelAndView();
		Map<String,String> deptInfo = projectService.getDeptInfoForStatistic();
		modelView.addObject("unitType", deptInfo.get("unitType"));
		modelView.addObject("deptId", deptInfo.get("deptId"));
		modelView.addObject("projectType",proTypeService.queryAllList());//工程类型
		modelView.addObject("contributionMode", contributionModeService.queryAllList());//出资方式
		modelView.addObject("projStatus", ProjStatusEnum.values());	//工程状态
		modelView.addObject("backReason", BackReasonEnum.values());//退单原因
		modelView.addObject("staffName",SessionUtil.getLoginInfo().getStaffName());
		BusinessPartnersReq cubq=new BusinessPartnersReq();
		cubq.setUnitType(UnitTypeEnum.CONSTRUCTION_UNIT.getValue());
		Map<String, Object> map=businessPartnersService.queryBusinessPartners(cubq);
		List culist=(List) map.get("data");//
		modelView.addObject("culist",culist);	//分包单位
		modelView.addObject("corpId", loginInfo.getCorpId());//分公司ID
		modelView.addObject("departmentList",departmentService.queryListByDeptType(loginInfo));//子公司
		modelView.setViewName("project/statisticquery/projectSafetyQualityReport");
		return modelView;
	}
}
