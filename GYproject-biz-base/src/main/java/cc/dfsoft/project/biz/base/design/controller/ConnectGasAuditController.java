package cc.dfsoft.project.biz.base.design.controller;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.design.dto.SurveyInfoQueryReq;
import cc.dfsoft.project.biz.base.design.entity.SurveyInfo;
import cc.dfsoft.project.biz.base.design.service.SurveyInfoService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 接气方案审批
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/connectGasAudit")
public class ConnectGasAuditController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ConnectGasAuditController.class);
	
	/**勘察信息服务接口*/
	@Resource
	SurveyInfoService surveyInfoService;
	
	/**管理记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	/**工程*/
	@Resource
	ProjectService projectService;
	/**
	* 打开主页面
	* @return ModelAndView
	*/
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("design/connectGasAudit");
		return modelView;
	}
	
	/**
	* 打开审核屏
	* @return ModelAndView
	*/
	@RequestMapping(value="/auditPage")
	public ModelAndView audit(HttpServletRequest request){
		
		String menuId = request.getParameter("menuId");
		String projId = request.getParameter("projId");
		
		SurveyInfo si=surveyInfoService.viewSurveyInfo(projId);
		Project pro=projectService.viewProject(projId);
		if(StringUtil.isNotBlank(pro.getSurveyBuilder())){
			si.setSurveyBuilder(pro.getSurveyBuilder());    //踏勘可选现场代表
		}
		String key = pro.getCorpId()+"_"+pro.getProjectType()+"_"+menuId;
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "";
		if(obj !=null){
			viewUrl = obj.toString();
		}else{
			viewUrl = "connectGasAuditPage";
		}
		
		ModelAndView modelView=new ModelAndView();
		
		//审核详述查询
		modelView.addObject("surveyInfo",si);
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));			//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));				 		//是否审核过
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);											//登录人信息
		modelView.addObject("mrResult",MrResultEnum.values());					 			//审核结果
		modelView.addObject("stepId",StepEnum.SURVEY_RESULT_REGISTER.getValue());			//上一个页面的步骤id
		modelView.setViewName("design/"+viewUrl);
		return modelView;
	}
	
	/**
	 * 勘察信息查询弹出屏
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/surveySearchPage")
	public ModelAndView surveySearchPage(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("design/surveySearchPopPage");
		return modelView;
	}
	
	
	/**
	 * 勘察信息列表查询
	 * @author pengtt
	 * @createTime 2016-06-27
	 * @param surveyInfoQueryReq
	 * @return
	 */
	@RequestMapping(value = "/querySurveyInfo")
	@ResponseBody
	public Map<String,Object> querySurveyInfo(SurveyInfoQueryReq surveyInfoQueryReq){
		try {
			surveyInfoQueryReq.setProjStatus(ProjStatusEnum.TO_APPROVAL.getValue());//待审核
			surveyInfoQueryReq.setStepId(StepEnum.CONNECT_GAS_AUDIT.getValue());//踏勘审核
			surveyInfoQueryReq.setTimeLimit(StepEnum.CONNECT_GAS_AUDIT.getStepDay());//逾期天数
			return surveyInfoService.querySurveyInfoForAudit(surveyInfoQueryReq);
		} catch (Exception e) {
			logger.error("勘察信息列表查询失败！",e);
			return null;
		}
	}
	
	
	/**
	 * 管理记录列表查询
	 * @author pengtt
	 * @createTime 2016-06-27
	 * @param manageRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepEnum.CONNECT_GAS_AUDIT.getValue());	//方案审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 接气方案审核保存
	 * @author pengtt
	 * @createTime 2016-06-27
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		
		try {
			
			manageRecordService.auditConnectGasAudit(manageRecord,"","",WorkFlowActionEnum.PROGRAMME_AUDIT.getActionCode(),Constants.MODULE_CODE_PROJECTAPPROVAL);
			//surveyInfoService.auditPass(manageRecord); 
			
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("接气方案确认失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
		
	}
}
