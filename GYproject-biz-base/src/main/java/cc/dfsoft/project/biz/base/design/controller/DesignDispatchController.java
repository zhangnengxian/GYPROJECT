package cc.dfsoft.project.biz.base.design.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.accept.entity.ProjectApplication;
import cc.dfsoft.project.biz.base.accept.service.ProjectApplicationService;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.AreaEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 设计派遣(一级)
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/designDispatch")
public class DesignDispatchController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(DesignDispatchController.class);
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	
	/** 设计信息服务 */
	@Resource
	DesignInfoService designInfoService;
	
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
	/**立项申请表接口*/
	@Resource
	ProjectApplicationService projectApplicationService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("design/designDispatch");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("deptType",DeptTypeEnum.SUBCOMPANY.getValue());
		modelView.setViewName("design/designDispatchRight");
		return modelView;
	}
	
	/**
	 * 弹出搜索
	 * @author fuliwei
	 * @createTime 2016-6-27
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.addObject("area",AreaEnum.values());
		modelview.setViewName("design/designDispatchSearchPopPage");
		return modelview;
	}
	
	/**
	 * 工程列表查询
	 * @param projectQueryReq
	 * @createTime 2016-06-24
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_SURVEY.getValue()); //待一级勘察状态查询
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.DESIGN_DISPATCH.getValue());
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
	 * @createTime 2016-6-29
	 * @param id 工程id
	 * @return Project
	 */
	@RequestMapping(value="/viewDispatchResult")
	@ResponseBody
	public Project viewDispatchResult(@RequestParam(required=true) String id){
		Project pro=projectService.viewProject(id);
		
		ProjectApplication pa=projectApplicationService.queryById(id);
		if(pa!=null){
			pro.setPaNo(pa.getPaNo());
		}
		return pro;
	}
	
	/**
	 * 更新工程信息
	 * @author fuliwei
	 * @createTime 2016-6-29
	 * @return String 
	 */
	@RequestMapping(value = "/updateProject")
	@ResponseBody
	public String saveSurveyInfo(@RequestBody(required = true) DesignDispatchDto designDispatchReq){
		try {
			designInfoService.updateProject(designDispatchReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("派工区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 设计员列表查询
	 * @param DesignerQueryReq
	 * @createTime 2016-07-1
	 * @return
	 *//*
	@RequestMapping(value = "/queryDesigner")
	@ResponseBody
	public Map<String,Object> queryDesigner(HttpServletRequest request, DesignerQueryReq designerQueryReq){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			designerQueryReq.setSortInfo(request);
			designerQueryReq.setPostType(PostTypeEnum.DESIGNER.getValue());  				//设计员
			designerQueryReq.setProjStatus(ProjStatusEnum.TO_RESULT_REGISTRY.getValue());	//待结果登记 
			designerQueryReq.setProjStatusId(ProjStatusEnum.TO_OUT_SKETCH.getValue()); 		//待出草图 
			result= designInfoService.queryDesigner(designerQueryReq);
			return result;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}*/
	
	
	/**
	 * 获取部门（弹窗页面）
	 * @author pengtt
	 * @createTime 2016-7-8
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/deptPop")
	public ModelAndView deptPop(String deptType){
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("deptType", deptType);
		List<Department> d = departmentService.getListMh();
		modelview.addObject("allDesign", d);
		modelview.setViewName("design/designDeptPop");
		return modelview;
	}
	
	/**
	 * 部门选择列表查询
	 * @param PageSortReq
	 * @return
	 */
	@RequestMapping(value = "/queryDept")
	@ResponseBody
	public Map<String, Object> queryDept(HttpServletRequest request,DepartmentReq departmentReq) {
		try {
			departmentReq.setSortInfo(request);
			Map<String, Object> map = departmentService.queryDepartmentListByDesign(departmentReq);
		    return map;
		} catch (Exception e) {
			logger.error("部门选择列表查询失败！", e);
			return null;
		}
	}
	
	
}
