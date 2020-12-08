package cc.dfsoft.project.biz.base.statisticquery.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;

/**
 * 
 * 描述:报验信息统计表
 * @author liaoyq
 * @createTime 2018年9月27日
 */
@Controller
@RequestMapping(value="/projectCheckListStatistics")
public class ProjectCheckListStatisticsController {

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
		//工程状态
		modelView.addObject("projStatus", ProjStatusEnum.getThanValue(ProjStatusEnum.DURING_CONSTRUCTION.getValue(),ProjStatusEnum.ALREADY_COMPLETED.getValue()));
		modelView.addObject("projLtype",ProjLtypeEnum.values());//工程大类
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
		modelView.addObject("builderList",builderList);//现场代表
		
		modelView.setViewName("project/statisticquery/projectCheckListStatistics");
		return modelView;
	}
}
