package cc.dfsoft.project.biz.base.contract.controller;

import cc.dfsoft.project.biz.base.contract.service.ProjectCostService;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.project.biz.base.project.service.RelationShipService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * 描述:造价派工
 * @author liaoyq
 * @createTime 2018年9月1日
 */
@Controller
@RequestMapping(value="/costDispatchingController")
public class CostDispatchingController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(CostDispatchingController.class);
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;

	@Resource
	ProjectCostService projectCostService;
	
	@Resource
	RelationShipService relationShipService;
	@Resource
	ProjectTypeService projectTypeService;
	
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
	/**
	 * 
	 * @author fuliwei
	 * @createTime 2017年5月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("contract/costDispatch");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @author fuliwei
	 * @createTime 2017年5月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("contract/costDispatchRight");
		return modelView;
	}
	
	/**
	 * 待造价派工工程列表查询
	 * @author fuliwei
	 * @createTime 2017年5月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_COST_DISPATCH.getValue()); //待造价派工
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.PROJECT_COST_DISPATCH.getValue());//造价派工
			Map<String,Object> map= projectService.queryProjectByTime(projectQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年5月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewDispatchResult")
	@ResponseBody
	public Project viewDispatchResult(@RequestParam(required=true) String id){
		Project pro=projectService.viewProject(id);
		return pro;
	}
	
	
	/**
	 * 造价员列表查询
	 * 配置数据过滤规则:部门ID_造价员职务_菜单ID
	 * @param designerQueryReq
	 * @createTime 2016-07-1
	 * @return
	 */
	@RequestMapping(value = "/querySalesma")
	@ResponseBody
	public Map<String,Object> queryDesigner(HttpServletRequest request, DesignerQueryReq designerQueryReq){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			designerQueryReq.setSortInfo(request);
			
			//查询是否配置sql
			List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+PostTypeEnum.SALESMA.getValue()+"_"+designerQueryReq.getMenuId());
			if(list!=null && list.size()>0){
				designerQueryReq.setDeptId(list.get(0).getSupSql());
			}else{
				designerQueryReq.setDeptId(departmentService.queryDepartmentByDivide(designerQueryReq.getDeptDivide(), null).getDeptId());
			}
			
			designerQueryReq.setCorpId(loginInfo.getCorpId());								//所属公司
			designerQueryReq.setPostType(PostTypeEnum.SALESMA.getValue());  			//造价员
			designerQueryReq.setProjStatus(ProjStatusEnum.TO_DETERMINE_COST.getValue());	//待造价确定
			result= projectCostService.queryCoster(designerQueryReq);
			return result;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 造价派工
	 * 更新工程信息
	 * @author liaoyq
	 * @createTime 2017-08-11
	 * @return String 
	 */
	@RequestMapping(value="/updateProject")
	@ResponseBody
	public String saveBudgeterInfo(@RequestBody(required = true) DesignDispatchDto designDispatchReq){
		try {
			projectCostService.updateProject(designDispatchReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("造价派工区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 造价派工页面  打开查询屏
	 * @author liaoyq
	 * @createTime 2017-08-16
	 * @return
	 */
	@RequestMapping(value="/projectSearchPopPage")
	public ModelAndView projectSearchPopPage(){
		ModelAndView modelAndView = new ModelAndView();
		List<ProjectType> projType=projectTypeService.queryAllList();
		modelAndView.addObject("projLtype", projType);

		modelAndView.setViewName("budget/budgetDispatchSearchPopPage");
		return modelAndView;
	}
}
