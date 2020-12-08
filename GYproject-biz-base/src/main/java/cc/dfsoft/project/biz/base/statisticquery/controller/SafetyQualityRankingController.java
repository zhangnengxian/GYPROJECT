package cc.dfsoft.project.biz.base.statisticquery.controller;

import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
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
 * 安全质量排行榜
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/safetyQualityRanking")
public class SafetyQualityRankingController {
	
	/**业务合作伙伴*/
	@Resource
	BusinessPartnersService businessPartnersService;
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/main")
	public ModelAndView main()throws ParseException {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projectType",projTypeService.queryAllList());//工程类型
		modelView.addObject("contributionMode", contributionModeService.queryAllList());//出资方式
		modelView.addObject("staffName",SessionUtil.getLoginInfo().getStaffName());
		BusinessPartnersReq cubq=new BusinessPartnersReq();
		cubq.setUnitType(UnitTypeEnum.CONSTRUCTION_UNIT.getValue());
		Map<String, Object> map=businessPartnersService.queryBusinessPartners(cubq);
		List<BusinessPartners>culist=(List<BusinessPartners>) map.get("data");//
		modelView.addObject("culist",culist);	//分包单位
		modelView.addObject("corpId", loginInfo.getCorpId());//分公司ID
		modelView.addObject("departmentList",departmentService.queryListByDeptType(loginInfo));//子公司

		modelView.setViewName("project/statisticquery/projectSafetyQualityRanking");

		return modelView;
	}
}
