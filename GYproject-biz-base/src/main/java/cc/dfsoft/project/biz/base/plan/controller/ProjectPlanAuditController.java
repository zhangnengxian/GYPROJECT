package cc.dfsoft.project.biz.base.plan.controller;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.contract.enums.PayTypeEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.enums.ConstructionPlanQueryReq;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import com.alibaba.fastjson.JSONObject;
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
 * 工程计划审核
 * @author pengtt
 *
 */
@Controller
@RequestMapping(value="/projectPlanAudit")
public class ProjectPlanAuditController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ProjectPlanAuditController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	@Resource
	ContractService contractService;
	
	/**施工计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService ;
	
	/**管理记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("plan/projectPlanAudit");
		return modelView;
	}
	
	
	/**
	* 打开审核屏
	* @return ModelAndView
	*/
	@RequestMapping(value="/auditPage")
	public ModelAndView audit(HttpServletRequest request){
		
		ModelAndView modelView=new ModelAndView();
		String cpId = request.getParameter("cpId");
		//审核详述查询
		ConstructionPlan constructionPlan=constructionPlanService.detail(cpId);
		if(constructionPlan!=null ){ 
		   Object jsonObj = JSONObject.toJSON(constructionPlan); 
		   modelView.addObject("constructionPlan",jsonObj);
		}
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));			//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));						//是否审核过
		modelView.addObject("loginInfo",SessionUtil.getLoginInfo());						//放入登录人信息
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		modelView.addObject("mrResult",MrResultEnum.values());								//审核结果
		modelView.addObject("payTypeEnum",PayTypeEnum.values());			    //付款方式
		
		modelView.setViewName("plan/projectPlanAuditPage");
		return modelView;
	}
	
	/**
	 * 计划查询弹出屏
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/planSearchPopPage")
	public ModelAndView planSearchPopPage(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("plan/planSearchPopPage");
		return modelView;
	}
	
	
	
	/**
	 * 施工计划条件查询
	 * @author pengtt
	 * @createTime
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryConstructionPlan")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ConstructionPlanQueryReq constructionPlanQueryReq){
		
		try {
			constructionPlanQueryReq.setProjStatus(ProjStatusEnum.TO_PLAN_AUDIT.getValue());
			constructionPlanQueryReq.setSortInfo(request);
			//constructionPlanQueryReq.setTimeLimit(StepEnum.PROJECT_PLAN_AUDIT.getStepDay());
			constructionPlanQueryReq.setStepId(StepEnum.PROJECT_PLAN_AUDIT.getValue());
			return constructionPlanService.queryConstructionPlanForAudit(constructionPlanQueryReq);
		} catch (Exception e) {
			logger.error("待审核计划信息查询失败！", e);
			return null;
		}
	}
	
	
	
	/**
	 * 保存施工计划
	 * @author zhangmeng
	 * @createTime 2016-7-4
	 * @param  
	 * @return 
	 */
	@RequestMapping(value = "/savePlan")
	@ResponseBody
	public String savePlan(@RequestBody(required = true) ConstructionPlan constructionPlan){
		try {
			return constructionPlanService.savePlan(constructionPlan);
		} catch (Exception e) {
			logger.error("计划编制区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 计划审核保存
	 * @author pengtt
	 * @createTime 2016-06-27
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public ResultMessage auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		ResultMessage resultMessage = new ResultMessage();
		try {
			String res= manageRecordService.auditPlanSave(manageRecord,"","",StepEnum.PROJECT_PLAN_AUDIT.getValue(),Constants.MODULE_CODE_CONSTRUCTIONPLAN);
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(res);
			return resultMessage;
		}catch (ExpressException e) {
			logger.error("计划审核失败！",e);
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		}catch (Exception e) {
			logger.error("计划审核失败！",e);
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
		
	}
	
	
	/**
	 * 管理记录列表查询
	 * @author pengtt
	 * @createTime 2016-07-06
	 * @param manageRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepEnum.PROJECT_PLAN_AUDIT.getValue());	//计划审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
}
