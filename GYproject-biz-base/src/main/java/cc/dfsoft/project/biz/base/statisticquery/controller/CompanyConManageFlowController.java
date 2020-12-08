package cc.dfsoft.project.biz.base.statisticquery.controller;

import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 工程管理台账
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/companyConManageFlow")
public class CompanyConManageFlowController {
	
	/**业务合作伙伴*/
	@Resource
	BusinessPartnersService businessPartnersService;
	
	/**员工service*/
	@Resource
	StaffService staffService;
	
	/**部门service*/
	@Resource
	DepartmentService  departmentService;
	@Resource
	ProjectTypeService projTypeService;

	@Resource
	ContributionModeService contributionModeService;
	/**
	 * 打开主页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/main")
	public ModelAndView main() throws ParseException {

		LoginInfo loginInfo=SessionUtil.getLoginInfo();

		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projStatus", ProjStatusEnum.values());	//工程状态
		modelView.addObject("backReason", BackReasonEnum.values());//退单原因
		modelView.addObject("projLtype",ProjLtypeEnum.values());//工程大类
		modelView.addObject("staffName",SessionUtil.getLoginInfo().getStaffName());
		BusinessPartnersReq cubq=new BusinessPartnersReq();
		cubq.setUnitType(UnitTypeEnum.CONSTRUCTION_UNIT.getValue());
		Map<String, Object> map=businessPartnersService.queryBusinessPartners(cubq);
		List<BusinessPartners> culist=(List<BusinessPartners>) map.get("data");//
		modelView.addObject("culist",culist);	//分包单位
		
		BusinessPartnersReq subq=new BusinessPartnersReq();
		subq.setUnitType(UnitTypeEnum.SUPERVISION_UNIT.getValue());
		Map<String, Object> suMap=businessPartnersService.queryBusinessPartners(subq);
		List<BusinessPartners> sulist=(List<BusinessPartners>) suMap.get("data");//
		modelView.addObject("sulist",sulist);	//监理单位
		
		BusinessPartnersReq iubq=new BusinessPartnersReq();
		iubq.setUnitType(UnitTypeEnum.INSPECTION_UNIT.getValue());
		Map<String, Object> iuMap=businessPartnersService.queryBusinessPartners(iubq);
		List<BusinessPartners> iulist=(List<BusinessPartners>) iuMap.get("data");//
		modelView.addObject("iulist",iulist);	//探伤单位
		
		List<Staff> builderList =staffService.queryPost(PostTypeEnum.BUILDER.getValue());
		modelView.addObject("builderList",builderList);//施工员

		modelView.addObject("projectType", projTypeService.queryAllList());//工程类型
		modelView.addObject("contributionMode",contributionModeService.queryAllList());	//出资方式
		modelView.addObject("corpId", loginInfo.getCorpId());//分公司ID
		modelView.addObject("departmentList",departmentService.queryListByDeptType(loginInfo));//子公司

		modelView.setViewName("project/statisticquery/companyConManageFlow");
		return modelView;
	}
}
