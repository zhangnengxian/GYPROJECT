
package cc.dfsoft.project.biz.base.accept.controller;


import cc.dfsoft.project.biz.base.accept.entity.ProjectApplication;
import cc.dfsoft.project.biz.base.accept.enums.ProjectStatusEnum;
import cc.dfsoft.project.biz.base.accept.service.ProjectApplicationService;
import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.enums.*;
import cc.dfsoft.project.biz.base.baseinfo.service.CorrelationService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ScaleDetailQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.*;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
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
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程立项
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/projectAccept")
public class ProjectAcceptController {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ProjectAcceptController.class);

	/** 工程服务接口 */
	@Resource
	ProjectService projectService;

	/** 工程规模明细服务接口 */
	@Resource
	ScaleDetailService scaleDetailService;

	/** 立项申请单信息服务接口*/
	@Resource
	ProjectApplicationService projectApplicationService;
	/** 工程服务接口 */
	@Resource
	ProjectTypeService projectTypeService;

	/**工程类型*/
	@Resource
	RelationShipService relationShipService;

	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	/** 关联关系服务接口*/
	@Resource
	CorrelationService correlationService;

	/**工作流服务接口*/
	@Resource
	WorkFlowService workFlowService;
	@Resource
	ContributionModeService contributionModeService;
	/**工程信息同步接口*/
	@Resource
	IFinanceInfoService iFinanceService;


	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("notThrough",Constants.MODULE_CODE_PROJECTAPPROVAL);
		modelView.addObject("stepId",StepEnum.PROJECT_ACCEPT_AUDIT.getValue());
		modelView.setViewName("accept/projectAccept");
		return modelView;
	}

	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(HttpServletRequest request) {
		ModelAndView modelview = new ModelAndView();

		LoginInfo loginInfo=SessionUtil.getLoginInfo();

		//查业务部门
		CorrelationReq req=new CorrelationReq();
		req.setCorType(CorrelationTypeEnum.PROJECT_METHOD.getValue());
		req.setCorrelateInfoId(ProjectMethodEnum.PROJECT_APPLY.getValue());
		//req.setAcceptType("");
		if(DeptDivideEnum.COMPREHENSIVE_GROUP.getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.TECHNOLOGY_GROUP.getValue().equals(loginInfo.getDeptDivide())){
			req.setAcceptScaleType(ScaleTypeEnum.SMALL_SCALE.getValue());//综合组
		}else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
			req.setAcceptScaleType(ScaleTypeEnum.LARGE_SCALE.getValue());//客服中心
		}else{
			req.setAcceptCorrelatedInfoId(loginInfo.getDeptId());//只用于-立项时设置部门id
		}


		List<Correlation> department=correlationService.findCorType(req);
		modelview.addObject("department",department);//程组部门
		//查工程类型 因分公司可能一个部门受理多种工程类型 取消该写法
		List<Correlation> list=correlationService.findProjType(CorrelationTypeEnum.DEPT_PROJTYPE.getValue(),department);
		modelview.addObject("projLtype", list);						//工程大类

		List<ProjectType> listCheck=projectTypeService.queryProjectTypeList("1");
		modelview.addObject("listCheck", listCheck);

		modelview.addObject("backReason", BackReasonEnum.values());	//退单原因

		//查所有出资方式
		CorrelationReq req1=new CorrelationReq();
		req1.setCorType(CorrelationTypeEnum.PROJTYPE_CONTRIBUTION.getValue());
		req1.setCorrelateInfoId("");
		req1.setAcceptType(AcceptTypeEnum.OUT_PLAN.getValue());
		List<Correlation> contributionMode=correlationService.findCorType(req1);
		modelview.addObject("contributionMode",contributionMode);	//出资方式
		modelview.addObject("corpName", loginInfo.getCorpName());		//所属公司名称
		modelview.addObject("corpId", loginInfo.getCorpId());		//所属公司ID

		List<ProjectType> projectType=projectTypeService.queryAllList();
		modelview.addObject("projectType",projectType);//工程类型

		String menuId=request.getParameter("menuId");
		String key = loginInfo.getCorpId()+"_"+menuId;

		isBorrowMaterial(modelview,key);//嵌入是否借料
		isTransfer(modelview,key);//嵌入是否立项转办--->贵安需求
		rightPageLoad(modelview,key);//加载右侧页面
		innstalltype(modelview,key);//报装类型

		return modelview;

	}

	public void isTransfer(ModelAndView model,String key){ //是否立项转办
		Object transfeFlagObj = Constants.getSysConfigByKey(key+"_transfeFlag");
		if(transfeFlagObj !=null){
			model.addObject("transfeFlag", transfeFlagObj.toString());
		}
	}

	public void isBorrowMaterial(ModelAndView model,String key){//嵌入是否借料
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "";
		if(obj !=null){
			viewUrl = obj.toString();
			model.addObject("viewUrl", viewUrl);
		}
	}

	public void rightPageLoad(ModelAndView model,String key){//右侧页面加载
		String resultPage="projectAcceptRight";
		Object jsp = Constants.getSysConfigByKey(key+"rightPage");
		if (jsp!=null){
			resultPage=jsp.toString();
		}
		model.setViewName("accept/"+resultPage);
	}

	public void innstalltype(ModelAndView model,String key){//报装类型
		Object jsp = Constants.getSysConfigByKey(key+"_innstalltype");
		model.addObject("innstalltype", jsp);
	}


	/**
	 * 弹出搜索
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		List<ProjectType> list=projectTypeService.queryAllList();
		modelview.addObject("projLtype", list);
		//modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("accept/projectSearchPopPage");
		return modelview;
	}
	/**
	 * 立项受理页面弹出搜索
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/searchPopPage")
	public ModelAndView searchPopPage() {
		ModelAndView modelview = new ModelAndView();
		List<ProjectType> list=projectTypeService.queryAllList();
		modelview.addObject("projLtype", list);
		modelview.addObject("projStatus",ProjectStatusEnum.values());
		modelview.setViewName("accept/projSearchPopPage");
		return modelview;
	}
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2016-7-9
	 * @param id 工程id
	 * @return Project
	 */
	@RequestMapping(value="/viewProjectAccept")
	@ResponseBody
	public Project viewProjectAccept(HttpServletRequest request,@RequestParam(required=true) String id){
		Project pro=projectService.viewProject(id);
		//通过id查立项申请信息
		ProjectApplication application=projectApplicationService.queryById(id);
		if(application!=null){
			pro.setPaNo(application.getPaNo());
		}
		return pro;
	}

	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author pengtt
	 * @createTime 2016-06-21
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){

		Map<String,Object> map = new HashMap<>();
		try {
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setAcceptType(ProjectMethodEnum.PROJECT_APPLY.getValue());//受理申请
			projectQueryReq.setProjStatusId(ProjStatusEnum.PROJECT_ACCEPT.getValue());
			map = projectService.queryAcceptProject(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}

		List<Project> projectList= (List<Project>) map.get("data");
		for (Project p:projectList) {
			//System.out.println(p.getSignBack());
		}


		return map;
	}

	/**
	 * 工程规模明细
	 * @param projectQueryReq
	 * @author pengtt
	 * @createTime 2016-06-22
	 * @return
	 */
	@RequestMapping(value = "/queryScaleDetail")
	@ResponseBody
	public Map<String,Object> queryScaleDetail(HttpServletRequest request,ScaleDetailQueryReq scaleDetailQueryReq){
		try {
			scaleDetailQueryReq.setSortInfo(request);
			return scaleDetailService.queryScaleDetail(scaleDetailQueryReq);
		} catch (Exception e) {
			logger.error("工程规模明细信息查询失败！", e);
			return null;
		}
	}

	@RequestMapping(value = "/queryScaleDetailAjax")
	@ResponseBody
	public Object queryScaleDetailAjax(@RequestBody ScaleDetailQueryReq scaleDetailQueryReq){
		try {
			return scaleDetailService.queryScaleDetail(scaleDetailQueryReq).get("data");
		} catch (Exception e) {
			logger.error("工程规模明细信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 立项保存
	 * @param
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/totalSave")
	@ResponseBody
//	@SendTask(menuName="受理申请",description="受理申请推送",message="")
	public ResultMessage totalSave(@RequestBody(required = true) Project project){
		ResultMessage resultMessage = new ResultMessage();
		LoginInfo login=SessionUtil.getLoginInfo();
		try {
			String projStatusId = "";
			if(StringUtil.isNotBlank(project.getSaveType())){
				//推送时
				if("push".equals(project.getSaveType())){
					projStatusId=workFlowService.queryProjStatusId(login.getCorpId(), project.getProjectType(),project.getContributionMode(), WorkFlowActionEnum.PROJECT_ACCEPT.getActionCode(), true);
				}else{
					projStatusId =ProjStatusEnum.PROJECT_ACCEPT.getValue();
				}
			}
			project.setProjStatusId(projStatusId);
			project.setDeptTransfer(project.getDeptId());

			String res = projectService.acceptTotalSave(project,false);
			resultMessage.setRet_message(res);
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			return resultMessage;
		}catch (ExpressException e) {
			logger.error("工程立项保存失败！", e);
			resultMessage.setRet_message(e.getMessage());
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			return resultMessage;
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("工程立项保存失败！", e);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			resultMessage.setRet_type(Constants.FAIL_CODE);
			return resultMessage;
		}

	}




	/**
	 * 获取工程编号
	 * @return
	 */
	@RequestMapping(value = "/getProjNo")
	@ResponseBody
	public String getProjNo(){
		return projectService.getProjMaxNo("","","");
	}
	/**
	 * 退单通知确认
	 * @param project
	 * @return
	 */
	@RequestMapping(value = "/informSave")
	@ResponseBody
	public String informSave(@RequestBody(required = true) String projId){
		try {
			return projectService.informSave(projId);
		} catch (Exception e) {
			logger.error("立项受理区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}



