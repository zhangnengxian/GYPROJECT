package cc.dfsoft.project.biz.base.plan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.project.biz.base.plan.dao.InstTasksDao;
import cc.dfsoft.project.biz.base.plan.entity.InstTasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.contract.enums.PayTypeEnum;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.enums.ConstructionPlanQueryReq;
import cc.dfsoft.project.biz.base.plan.service.AuditInstTasksService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;

/**
 * 安装任务审核
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/auditInstTasks")
public class AuditInstTasksController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(AuditInstTasksController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	@Autowired
	AuditInstTasksService auditInstTasksSercice;
	@Autowired
	ManageRecordService manageRecordService;
	@Autowired
	DocTypeService docTypeService;
	@Autowired
	InstTasksDao instTasksDao;

	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("plan/auditInsTasks");
		return modelView;
	}
	/**
	 * 获取审核数据列表
	 * @param request
	 * @param constructionPlanQueryReq
	 * @return
	 */
	@RequestMapping(value = "/getAuditDataList")
	@ResponseBody
	public Map<String,Object> getAuditDataList(HttpServletRequest request,ProjectQueryReq req){
		
		try {
			req.setProjStatusId(ProjStatusEnum.TO_AUDIT_INSTASKS.getValue());
			req.setSortInfo(request);
			req.setStepId(StepEnum.AUDIT_INST_TASKS.getValue());
			return auditInstTasksSercice.getAuditDataList(req);
		} catch (Exception e) {
			logger.error("待安装任务审核信息查询失败！", e);
			return null;
		}
	}
	
	/**
	* 打开审核屏
	* @return ModelAndView
	*/
	@RequestMapping(value="/auditPage")
	public ModelAndView audit(HttpServletRequest request){
		
		ModelAndView modelView=new ModelAndView();
		String projId = request.getParameter("projId");
		//审核详述查询
		Project data= projectService.getData(projId);
		if(data!=null ){ 
		   Object jsonObj = JSONObject.toJSON(data); 
		   modelView.addObject("data",jsonObj);
		}
		List<InstTasks> listInstTasks = instTasksDao.findByProjId(projId);
		if(listInstTasks!=null&&listInstTasks.size()>0){
			modelView.addObject("instTasks",listInstTasks.get(0));
		}
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));			//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));						//是否审核过
		modelView.addObject("loginInfo",SessionUtil.getLoginInfo());							//放入登录人信息
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		modelView.addObject("mrResult",MrResultEnum.values());								//审核结果
		modelView.addObject("payTypeEnum",PayTypeEnum.values());			    //付款方式
		
		modelView.setViewName("plan/auditInsTaskPage");
		return modelView;
	}
	
	/**
	 * 保存审核记录
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public ResultMessage auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		ResultMessage resultMessage = new ResultMessage();
		try {
			String res= manageRecordService.auditInstTask(manageRecord);
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(res);
			return resultMessage;
		}catch (Exception e) {
			logger.error("安装任务审核失败！",e);
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
		
	}
	
	
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepEnum.AUDIT_INST_TASKS.getValue());	//计划审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	
	
	@RequestMapping(value="/instSearchPopPage")
	public ModelAndView instSearchPopPage(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("plan/instSearchPopPage");
		return modelView;
	}
}
