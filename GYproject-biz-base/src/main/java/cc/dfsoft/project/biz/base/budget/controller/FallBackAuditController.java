package cc.dfsoft.project.biz.base.budget.controller;

import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.project.dto.FallbackApplyReq;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.FallbackApply;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.FallbackApplyService;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import org.apache.commons.lang3.StringUtils;
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

@Controller
@RequestMapping(value="/fallBackAudit")
public class FallBackAuditController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(FallBackAuditController.class);
	
	
	@Resource
	ManageRecordService manageRecordService;
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	/** 延期申请*/
	@Resource
	FallbackApplyService fallbackApplyService;
	
	/**工程信息*/
	@Resource
	ProjectService projectService;
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年12月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("budget/fallBackAudit");
		return modelView;
	}
	
	/**
	 * 打开审核页面
	 * @author fuliwei
	 * @createTime 2017年12月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/auditPage")
	@ResponseBody
	public ModelAndView viewAuditPage(HttpServletRequest request) {
		ModelAndView modelView = new ModelAndView();
		String faId = request.getParameter("faId");
		
		//根据主键id查询回退申请
		FallbackApply fa = fallbackApplyService.findByFaId(faId);

		if(fa!=null){
			modelView.addObject("projId",fa.getProjId());
		}
		modelView.addObject("fa",fa);	
		modelView.addObject("faId",fa.getFaId());
		//根据工程id查设计信息
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));			//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 			//是否审核过
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);											//登录人信息
		
		//回退步骤
		modelView.addObject("stepIds",StepEnum.values());									//回退步骤
		modelView.setViewName("budget/fallBackAuditPage");
		return modelView;
	}
	

	
	
	/**
	 * 查询待审核列表
	 * @author fuliwei
	 * @createTime 2017年12月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,FallbackApplyReq projectQueryReq){
		projectQueryReq.setMenuId("110310");
		try {
			projectQueryReq.setSortInfo(request);
			if(StringUtils.isBlank(projectQueryReq.getAuditState())){
				projectQueryReq.setAuditState(AuditResultEnum.APPLYING.getValue());//默认加载审核中的
			}
			Map<String,Object> map=fallbackApplyService.queryAuditFallBack(projectQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 回退审核历史信息
	 * @author fuliwei
	 * @createTime 2017年12月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepOutWorkflowEnum.FALLBACK_AUDIT.getValue());	//回退审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("回退审核历史查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 回退审核保存
	 * @author fuliwei
	 * @createTime 2017年12月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/auditSave")
	@ResponseBody
	public ResultMessage auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		ResultMessage resultMessage = new ResultMessage();
		try {
			manageRecordService.auditFallbackSave(manageRecord,"","",manageRecord.getStepId(),Constants.MODULE_CODE_SUBCONTRACT);
			
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_SUCCESS);
			return resultMessage;
		} catch (ExpressException e) {
			logger.error("回退审核保存失败！",e);
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		}catch (Exception e) {
			logger.error("回退审核保存失败！",e);
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
	}
	
	/**
	 * 打开查询屏
	 * @author fuliwei
	 * @createTime 2017年12月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/projectSearchPopPage")
	public ModelAndView projectSearchPopPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("budget/fallBackAuditSearchPopPage");
		return modelAndView;
	}
	
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年12月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryFallbackApply")
	@ResponseBody
	public FallbackApply queryByProjId(String id){
		 try {
			 FallbackApply fa = fallbackApplyService.findByFaId(id);
			 fa.setFallbackStepId(StepEnum.valueof(fa.getFallbackStepId()).getMessage());
			 fa.setOriginalStepId(StepEnum.valueof(fa.getOriginalStepId()).getMessage());
			 return fa;
		} catch (Exception e) {
			logger.error("回退申请详述查询失败！", e);
		}
		 return null;
	}
}
