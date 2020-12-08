package cc.dfsoft.project.biz.base.project.controller;

import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 工程报验的工程列表
 * @author pengtt
 * @createTime 2016-07-29
 */
@Controller
@RequestMapping(value="/projectInspectList")
public class ProjectInspectListController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ProjectInspectListController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**业务合作伙伴*/
	@Resource
	BusinessPartnersService businessPartnersService;
	
	/**部门服务接口*/
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
		modelView.setViewName("project/list/projectInspectList");
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

		if (StringUtil.isBlank(projectQueryReq.getMenuId())){
			projectQueryReq.setMenuId("13");
		}

		try {
			projectQueryReq.setSortInfo(request);
			//工程状态施工中、待自检、待预验收、待联合验收、待结算报审、报审确认、待结算初审、初审确认、待结算终审、终审确认、资料发送、资料反馈、资料确认
			//String[] projStatus = {ProjStatusEnum.DURING_CONSTRUCTION.getValue(),ProjStatusEnum.TO_SELF_CHECK.getValue(),ProjStatusEnum.TO_PRE_INSPECTION.getValue(),ProjStatusEnum.UNION_PRE_INSPECTION.getValue(),ProjStatusEnum.SETTLEMENT_REPORT.getValue(),ProjStatusEnum.REPORT_CONFIRM.getValue(),ProjStatusEnum.SETTLEMENT_REPORT_START.getValue(),ProjStatusEnum.REPORT_START_CONFIRM.getValue(),ProjStatusEnum.SETTLEMENT_REPORT_DONE.getValue(),ProjStatusEnum.REPORT_DONE_CONFIRM.getValue(),ProjStatusEnum.COMPLETION_TRANSFER.getValue(),ProjStatusEnum.DATA_FEEDBACK.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue()};
			
			
			String[] projStatus =new String[]{};
			List<ProjStatusEnum> list=ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue(),ProjStatusEnum.SETTLEMENT_REPORT.getValue());
			List<String> statusList=new ArrayList<String>();
			
			for(ProjStatusEnum pe:list){
				statusList.add(pe.getValue());
			}
			//当工程编号、名称不为空时，可默认查询已竣工的
			if(StringUtil.isNotBlank(projectQueryReq.getProjNo()) || StringUtil.isNotBlank(projectQueryReq.getProjName())){
				list=ProjStatusEnum.getThanValueNew(ProjStatusEnum.SETTLEMENT_REPORT.getValue(),ProjStatusEnum.ALREADY_COMPLETED.getValue());
				for(ProjStatusEnum pe:list){
					statusList.add(pe.getValue());
				}
			}
			projectQueryReq.setProjStuList(statusList);
			Map<String,Object> map = projectService.queryProjectView(projectQueryReq,projStatus);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	@RequestMapping(value="/inspectSearchPopPage")
	public ModelAndView constructSearchPopPage() throws Exception{
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projStatus", ProjStatusEnum.getThanValue(ProjStatusEnum.DURING_CONSTRUCTION.getValue(),ProjStatusEnum.ALREADY_COMPLETED.getValue()));
		BusinessPartnersReq cubq=new BusinessPartnersReq();
		cubq.setUnitType(UnitTypeEnum.CONSTRUCTION_UNIT.getValue());
		modelView.addObject("projLtype", projTypeService.queryAllList());
		modelView.addObject("contributionMode", contributionModeService.queryAllList());
		Map<String, Object> map=businessPartnersService.queryBusinessPartners(cubq);
		List culist=(List) map.get("data");//
		modelView.addObject("culist",culist);	//分包单位

		modelView.setViewName("project/list/constructSearchPopPage");
		return modelView;
	}

	/**
	 * 判断工程状态 施工中-联合验收
	 * @param projId
	 * @author cui
	 * @createTime 2018-01-22
	 * @return Boolean
	 */
	@RequestMapping(value = "/findProStatus")
	@ResponseBody
	public Boolean findProStatus(String projId){
		try {
			Project p = projectService.queryProjectById(projId);
			List<ProjStatusEnum> list=ProjStatusEnum.getThanValueNew(ProjStatusEnum.DURING_CONSTRUCTION.getValue(),ProjStatusEnum.UNION_PRE_INSPECTION.getValue());

			List<String> statusList=new ArrayList<String>();
			for(ProjStatusEnum pe:list){
				statusList.add(pe.getValue());
			}

			return statusList.contains(p!=null?p.getProjStatusId():null);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
}
