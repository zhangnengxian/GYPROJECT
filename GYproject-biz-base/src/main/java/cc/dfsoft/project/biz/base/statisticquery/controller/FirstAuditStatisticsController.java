package cc.dfsoft.project.biz.base.statisticquery.controller;

import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
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
 * 初审人员审核工作统计表
 * @author cui
 *
 */
@Controller
@RequestMapping(value="/firstAuditStatistics")
public class FirstAuditStatisticsController {
	
	/**业务合作伙伴*/
	@Resource
	BusinessPartnersService businessPartnersService;
	
	/**员工service*/
	@Resource
	StaffService staffService;
	
	/**部门service*/
	@Resource
	DepartmentService  departmentService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main() throws ParseException {
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("staffName",SessionUtil.getLoginInfo().getStaffName());
		BusinessPartnersReq cubq=new BusinessPartnersReq();
		cubq.setUnitType(UnitTypeEnum.CONSTRUCTION_UNIT.getValue());
		Map<String, Object> map=businessPartnersService.queryBusinessPartners(cubq);
		List culist=(List) map.get("data");//
		modelView.addObject("culist",culist);	//分包单位
		
		BusinessPartnersReq subq=new BusinessPartnersReq();
		subq.setUnitType(UnitTypeEnum.SUPERVISION_UNIT.getValue());
		Map<String, Object> suMap=businessPartnersService.queryBusinessPartners(subq);
		List sulist=(List) suMap.get("data");//
		modelView.addObject("sulist",sulist);	//监理单位
		
		BusinessPartnersReq iubq=new BusinessPartnersReq();
		iubq.setUnitType(UnitTypeEnum.INSPECTION_UNIT.getValue());
		Map<String, Object> iuMap=businessPartnersService.queryBusinessPartners(iubq);
		List iulist=(List) iuMap.get("data");//
		modelView.addObject("iulist",iulist);	//探伤单位
		
		List builderList =staffService.queryPost(PostTypeEnum.BUILDER.getValue());
		modelView.addObject("builderList",builderList);//施工员
		
		modelView.setViewName("project/statisticquery/firstAuditStatistics");
		return modelView;
	}
}
