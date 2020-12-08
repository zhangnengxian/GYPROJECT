package cc.dfsoft.project.biz.base.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.constructmanage.service.TouchPlanService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * 工程施工工程列表
 * @author pengtt
 * @createTime 2016-07-29
 */
@Controller
@RequestMapping(value="/projectConstructList")
public class ProjectConstructListController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ProjectConstructListController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**碰口方案*/
	@Resource
	TouchPlanService touchPlanService;
	/**业务合作伙伴*/
	@Resource
	BusinessPartnersService businessPartnersService;
	
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	/**工程类型接口*/
	@Resource
	ProjectTypeService projTypeService;
	/**出资方式接口*/
	@Resource
	ContributionModeService contributionModeService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("to_construction",ProjStatusEnum.TO_CONSTRUCTION.getValue()); //待施工
		modelView.addObject("during_construction",ProjStatusEnum.DURING_CONSTRUCTION.getValue()); //施工中
		modelView.setViewName("project/list/projectConstructList");
		return modelView;
	}
	
	/** 
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author pengtt
	 * @createTime 2016-07-29
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){

		if (StringUtil.isBlank(projectQueryReq.getMenuId()))projectQueryReq.setMenuId("12");

		try {
			projectQueryReq.setSortInfo(request);
			//工程状态待施工、施工中、待自检、待预验收、待联合验收、待结算报审、报审确认、待结算初审、初审确认、待结算终审、终审确认、资料发送、资料反馈、资料确认
			//施工预算到已竣工
			//String[] projStatus = {ProjStatusEnum.TO_CONSTRUCTION.getValue(),ProjStatusEnum.DURING_CONSTRUCTION.getValue(),ProjStatusEnum.TO_SELF_CHECK.getValue(),ProjStatusEnum.TO_PRE_INSPECTION.getValue(),ProjStatusEnum.UNION_PRE_INSPECTION.getValue(),ProjStatusEnum.SETTLEMENT_REPORT.getValue(),ProjStatusEnum.REPORT_CONFIRM.getValue(),ProjStatusEnum.SETTLEMENT_REPORT_START.getValue(),ProjStatusEnum.REPORT_START_CONFIRM.getValue(),ProjStatusEnum.SETTLEMENT_REPORT_DONE.getValue(),ProjStatusEnum.REPORT_DONE_CONFIRM.getValue(),ProjStatusEnum.COMPLETION_TRANSFER.getValue(),ProjStatusEnum.DATA_FEEDBACK.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue()};
			String[] projStatus =new String[]{};
			List<ProjStatusEnum> list=ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue());
			List<String> statusList=new ArrayList<String>();
			
			for(ProjStatusEnum pe:list){
				statusList.add(pe.getValue());
			}
			//当工程编号、名称不为空时，可默认查询已竣工的
			if(StringUtil.isNotBlank(projectQueryReq.getProjNo()) || StringUtil.isNotBlank(projectQueryReq.getProjName())){
				statusList.add(ProjStatusEnum.ALREADY_COMPLETED.getValue());
			}
			projectQueryReq.setProjStuList(statusList);
			Map<String,Object> map = projectService.queryProjectView(projectQueryReq,projStatus);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	@RequestMapping(value="/constructSearchPopPage")
	public ModelAndView constructSearchPopPage() throws Exception{
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projStatus", ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_CONSTRUCTION.getValue(),ProjStatusEnum.ALREADY_COMPLETED.getValue()));
		modelView.addObject("projLtype", projTypeService.queryAllList());
		modelView.addObject("contributionMode", contributionModeService.queryAllList());
		BusinessPartnersReq cubq=new BusinessPartnersReq();
		cubq.setUnitType(UnitTypeEnum.CONSTRUCTION_UNIT.getValue());
		Map<String, Object> map=businessPartnersService.queryBusinessPartners(cubq);
		List culist=(List) map.get("data");//
		modelView.addObject("culist",culist);	//分包单位
		modelView.setViewName("project/list/constructSearchPopPage");
		return modelView;
	}
	
	@RequestMapping(value="/docPopPage")
	public ModelAndView docPopPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("project/list/docPopPage");
		return modelView;
	}
}
