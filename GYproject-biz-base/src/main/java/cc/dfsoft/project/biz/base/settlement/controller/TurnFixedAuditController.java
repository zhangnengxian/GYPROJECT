package cc.dfsoft.project.biz.base.settlement.controller;

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
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.settlement.dto.TurnFixedApplyReq;
import cc.dfsoft.project.biz.base.settlement.entity.TurnFixedApply;
import cc.dfsoft.project.biz.base.settlement.service.TurnFixedApplyService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
/**
 * 转固申请审核
 * @author fuliwei
 *
 */

@Controller
@RequestMapping(value="/turnFixedAudit")
public class TurnFixedAuditController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(TurnFixedAuditController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	/** 审核记录接口*/
	@Resource
	ManageRecordService manageRecordService;
	/** 转固申请接口*/
	@Resource
	TurnFixedApplyService turnFixedApplyService;
	
	/** 签字接口*/
	@Resource
	SignatureService signatureService;
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
		modelView.setViewName("settlement/turnFixedAudit");
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
		//根据工程id查转固信息
		modelView.addObject("turnFixedApply",turnFixedApplyService.findByProjId(projId));
		modelView.addObject("projId",projId);
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());//当前数据库时间
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);								 //登录人信息
		modelView.setViewName("settlement/turnFixedAuditPage");
		return modelView;
	}
	/**
	 * 转固信息查询弹出屏
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/turnFixedSearchPopPage")
	public ModelAndView surveySearchPage(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("settlement/turnFixedSearchPopPage");
		return modelView;
	}
	/**
	 * 转固审核列表条件查询
	 * @param turnFixedApplyReq
	 * @return
	 */
	@RequestMapping(value = "/queryTurnFixedApply")
	@ResponseBody
	public Map<String,Object> queryTurnFixedApply(TurnFixedApplyReq turnFixedApplyReq){
		try {
			turnFixedApplyReq.setProjStatus(ProjStatusEnum.TURN_FIXED_AUDIT.getValue());//待转固审核
			turnFixedApplyReq.setTimeLimit(StepEnum.TURN_FIXED_AUDIT.getStepDay());//逾期天数
			return turnFixedApplyService.queryTurnFixedApply(turnFixedApplyReq);
		} catch (Exception e) {
			logger.error("转固申请信息列表查询失败！",e);
			return null;
		}
	}
	/**
	 * 审核历史列表查询
	 * @param manageRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepEnum.TURN_FIXED_AUDIT.getValue());//转固审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("审核历史列表查询失败！",e);
			return null;
		}
	}
	/**
	 * 保存审核信息
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		
		try {
			DocType docType = docTypeService.findByStepId(StepEnum.TURN_FIXED_AUDIT.getValue());
			manageRecordService.auditSave(manageRecord,docType==null?"":docType.getId(),
					docType==null?"":docType.getGrade(),WorkFlowActionEnum.TURN_FIXED_AUDIT.getActionCode(),Constants.MODULE_CODE_SETTLEMENT);
			signatureService.saveOrUpdateSign(manageRecord.getMenuId(),manageRecord.getSign(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(),TurnFixedApply.class.getName(), false);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("转固审核保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
		
	}
}
