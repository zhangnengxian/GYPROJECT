package cc.dfsoft.project.biz.base.accept.controller;

import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.enums.AcceptTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.CorrelationTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.ProjectMethodEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.CorrelationService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/planProjectAccept")
public class PlanProjectAcceptController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(PlanProjectAcceptController.class);
	private final String menuId="110106";//计划立项
	/** 工程类型服务接口 */
	@Resource
	ProjectTypeService projectTypeService;
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;

	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;

	/**出资方式服务接口*/
	@Resource
	ContributionModeService contributionModeService;

	/** 关联关系服务接口*/
	@Resource
	CorrelationService correlationService;

	/**工作流*/
	@Resource
	WorkFlowService workFlowService;


	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月20日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("accept/planProjectAccept");
		return modelView;
	}
	
	/**
	 * 打开右侧详述页面
	 * @author fuliwei
	 * @createTime 2017年6月20日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(HttpServletRequest request) {
		ModelAndView modelview = new ModelAndView();
		//查业务部门
		CorrelationReq req=new CorrelationReq();
		req.setCorType(CorrelationTypeEnum.PROJECT_METHOD.getValue());
		req.setCorrelateInfoId(ProjectMethodEnum.PLAN_PROJECT.getValue());
		req.setAcceptType("");
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		req.setAcceptCorrelatedInfoId(loginInfo.getDeptId());//只用于-立项时设置部门id
		
		List<Correlation> department=correlationService.findCorType(req);
		modelview.addObject("department",department);//工程组部门
		//查工程类型
		List<Correlation> list=correlationService.findProjType(CorrelationTypeEnum.DEPT_PROJTYPE.getValue(),department);
		modelview.addObject("projLtype", list);						//工程大类

		//查所有出资方式
		CorrelationReq req1=new CorrelationReq();
		req1.setCorType(CorrelationTypeEnum.PROJTYPE_CONTRIBUTION.getValue());
		req1.setCorrelateInfoId("");
		req1.setAcceptType(AcceptTypeEnum.INNER_PLAN.getValue());
		List<Correlation> contributionMode=correlationService.findCorType(req1);
		modelview.addObject("contributionMode",contributionMode);	//出资方式

		LoginInfo login= SessionUtil.getLoginInfo();
		modelview.addObject("corpName", login.getCorpName());		//所属公司名称
		
		modelview.addObject("surveyStatusId", ProjStatusEnum.TO_SURVEY.getValue());//勘察派工状态
		modelview.setViewName("accept/planProjectAcceptRight");
		String menuId=request.getParameter("menuId");
		String key = loginInfo.getCorpId()+"_"+menuId;

		isDifference(modelview,key);//嵌入不同信息页面
		return modelview;
	}
	//不同计划立项页面的信息
	public void isDifference(ModelAndView model,String key){//嵌入不同信息页面
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "";
		if(obj !=null){
			viewUrl = obj.toString();
			model.addObject("viewUrl", viewUrl);
		}
	}
	
	/**
	 * 工程列表条件查询
	 * @author fuliwei
	 * @createTime 2017年6月20日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			//projectQueryReq.setProjStatusId(ProjStatusEnum.TO_SURVEY.getValue());
			projectQueryReq.setSortInfo(request);
			return projectService.queryAcceptProject(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 计划立项保存
	 * @author fuliwei
	 * @createTime 2017年9月9日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/totalSave")
	@ResponseBody
//	@SendTask(menuName="计划立项",description="计划立项",message="")
	public String totalSave(@RequestBody(required = true) Project project){

		try {
			LoginInfo login=SessionUtil.getLoginInfo();
			String projStatusId=workFlowService.queryProjStatusId(login.getCorpId(), project.getProjectType(),project.getContributionMode(), WorkFlowActionEnum.PROJECT_ACCEPT.getActionCode(), true);
			project.setProjStatusId(projStatusId);
			project.setDeptTransfer(project.getDeptId());
			return projectService.acceptPlanTotalSave(project,menuId,false);
		} catch (Exception e) {
			logger.error("计划立项受理区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}


}
