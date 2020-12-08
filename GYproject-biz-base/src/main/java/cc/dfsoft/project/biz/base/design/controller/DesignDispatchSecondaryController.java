package cc.dfsoft.project.biz.base.design.controller;

import cc.dfsoft.project.biz.base.accept.service.ProjectApplicationService;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.RelationShipService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
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
import java.util.Map;

/**
 * 勘察派工
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/designDispatchSecondary")
public class DesignDispatchSecondaryController {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(DesignDispatchController.class);

	/** 工程服务接口 */
	@Resource
	ProjectService projectService;

	/** 设计信息服务 */
	@Resource
	DesignInfoService designInfoService;

	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年7月21日
	 * @param
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("design/designDispatchSecondary");
		return modelView;
	}

	/**
	 * 打开右侧页面
	 * @author fuliwei
	 * @createTime 2017年7月21日
	 * @param
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("deptType",DeptTypeEnum.SUBCOMPANY.getValue());
		modelView.setViewName("design/designDispatchSecondaryRight");
		return modelView;
	}

	/**
	 * 弹出搜索
	 * @author fuliwei
	 * @createTime 2017年7月21日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		//List<RelationShip> relationShip=relationShipService.queryRelationShip();
		//modelview.addObject("projLtype", relationShip);


		modelview.addObject("post", PostTypeEnum.SURVEYER.getValue());
		modelview.setViewName("design/designDispatchSearchPopPage");
		return modelview;
	}

	/**
	 * 工程列表查询
	 * @author fuliwei
	 * @createTime 2017年7月20日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.DESIGN_DISPATCH.getValue());//用步骤id去查
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_SURVEY.getValue());//待勘察派工状态查询
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
	 * @createTime 2017年7月21日
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
	 * 派工保存
	 * @author fuliwei
	 * @createTime 2017年7月21日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/updateProject")
	@ResponseBody
	public String saveSurveyInfo(@RequestBody(required = true) DesignDispatchDto designDispatchReq){
		try {
			designInfoService.update(designDispatchReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("派工区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 踏勘员列表查询
	 * @author fuliwei
	 * @createTime 2017年7月21日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/querySurveyer")
	@ResponseBody
	public Map<String,Object> querySurveyer(HttpServletRequest request, DesignerQueryReq designerQueryReq){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			designerQueryReq.setSortInfo(request);
			designerQueryReq.setPostType(PostTypeEnum.SURVEYER.getValue());  				//勘察员
			designerQueryReq.setProjStatus(ProjStatusEnum.TO_RESULT_REGISTRY.getValue());	//待现场踏勘任务 
			LoginInfo login=SessionUtil.getLoginInfo();
			//加配置 
			String key = login.getDeptId()+"_"+designerQueryReq.getMenuId();
			Object obj = Constants.getSysConfigByKey(key);
			if(obj !=null){
				//有配置 取工程的deptId
				designerQueryReq.setDeptId(obj.toString());
			}else{
				//取当前登录人deptId
				designerQueryReq.setDeptId(login.getDeptId());
			}

			result= designInfoService.querySurveyer(designerQueryReq);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
}
