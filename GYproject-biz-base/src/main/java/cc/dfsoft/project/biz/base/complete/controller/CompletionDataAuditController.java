package cc.dfsoft.project.biz.base.complete.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.complete.entity.DataAcceptance;
import cc.dfsoft.project.biz.base.complete.service.DataAcceptanceService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
/**
 * 竣工资料审核
 * @author fuliwei
 *
 */

@Controller
@RequestMapping(value="/completionDataAudit")
public class CompletionDataAuditController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(CompletionDataAuditController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	@Resource
	ManageRecordService manageRecordService;
	
	/**资料验收服务接口*/
	@Resource
	DataAcceptanceService dataAcceptanceService;
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("complete/completionDataAudit");
		return modelView;
	}
	
	/**
	 * 打开审核页面
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/auditPage")
	public ModelAndView viewAuditPage(HttpServletRequest request) {
		ModelAndView modelView = new ModelAndView();
		String projId = request.getParameter("projId");
		Project project=dataAcceptanceService.findProjectById(projId);
		//根据工程id查设计信息
		modelView.addObject("projId",projId);
		modelView.addObject("project",project);
		
		DataAcceptance da=dataAcceptanceService.findByProjId(projId);
		modelView.addObject("da",da);	//资料审核
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);								 //登录人信息
		modelView.setViewName("complete/completionDataAuditPage");
		return modelView;
	}
	
	/**
	 * 工程列表条件查询
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_COMPLETION_DATA_AUDIT.getValue());  //待资料审核
			projectQueryReq.setSortInfo(request);
			Map<String,Object> map=projectService.queryAuditProject(projectQueryReq,"",StepEnum.COMPLETION_DATA_AUDIT.getValue());
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 审核历史列表查询
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepEnum.COMPLETION_DATA_AUDIT.getValue());	//待资料审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 资料审核保存
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		try {
			manageRecordService.auditDataSave(manageRecord,"","",WorkFlowActionEnum.COMPLETION_DATA_AUDIT.getActionCode(),Constants.MODULE_CODE_COMPLETE);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("资料审核保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
}
