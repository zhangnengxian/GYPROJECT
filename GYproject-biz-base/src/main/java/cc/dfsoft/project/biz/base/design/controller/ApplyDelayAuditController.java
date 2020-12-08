package cc.dfsoft.project.biz.base.design.controller;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.project.dto.ApplyDelayReq;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ApplyDelay;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.service.ApplyDelayService;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 延期审批
 * @author liaoyq
 * 
 * todo:后台逻辑未完成
 */
@Controller
@RequestMapping(value="/applyDelayAudit")
public class ApplyDelayAuditController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ApplyDelayAuditController.class);
	
	/**延期申请*/
	@Resource
	ApplyDelayService applyDelayService;
	
	/**审核记录*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**审核单据*/
	@Resource
	DocTypeService docTypeService;
	
	/**
	* 延期审批列表页
	* @return ModelAndView
	*/
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("design/applyDelayAudit");
		return modelView;
	}
	/**
	 * 打开延期审批屏
	 * @return
	 */
	@RequestMapping(value="/delayCheckPage")
	public ModelAndView delayCheckPage(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		String daId = request.getParameter("daId");
		//审核详述查询
		ApplyDelay applyDelay=applyDelayService.findById(daId);
		modelView.addObject("applyDelay",applyDelay);
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));			//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));				 		//是否审核过
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);											//登录人信息
		modelView.addObject("mrResult",MrResultEnum.values());					 			//审核结果
		modelView.setViewName("design/delayCheckPage");
		return modelView;
	}
	/**
	 * 工程延期信息
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("design/delayCheckPageLeft");
		return modelView;
	}
	/**
	 * 打开延期审批查询屏
	 */
	@RequestMapping(value="/delaySearchPopPage")
	public ModelAndView delaySearchPopPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("design/delaySearchPopPage");
		return modelView;
	}
	
	/**
	 * 延期审核列表
	 * @author fuliwei
	 * @createTime 2017年9月1日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/queryApplyDelayAudit")
	@ResponseBody
	public Map<String,Object> queryApplyDelayAudit(HttpServletRequest request,ApplyDelayReq req){
		try {
			req.setSortInfo(request);
			if(StringUtils.isBlank(req.getAuditState())){
				req.setAuditState(AuditResultEnum.APPLYING.getValue());//默认加载审核中的
			}
			Map<String,Object> map=applyDelayService.queryApplyDelayAudit(req);
			return map;
		} catch (Exception e) {
			logger.error("延期审核列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 审核历史
	 * @author fuliwei
	 * @createTime 2017年9月1日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepOutWorkflowEnum.AUDIT_DELAY_APPLY.getValue());	//审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 延期审核
	 * @author fuliwei
	 * @createTime 2017年9月1日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		try {
			manageRecordService.auditApplyDelaySave(manageRecord,"","",StepOutWorkflowEnum.AUDIT_DELAY_APPLY.getValue(),Constants.MODULE_CODE_DELAY);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("延期审核记录保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

}
