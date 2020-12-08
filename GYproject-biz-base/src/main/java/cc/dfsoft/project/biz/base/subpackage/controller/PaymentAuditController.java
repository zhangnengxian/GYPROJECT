package cc.dfsoft.project.biz.base.subpackage.controller;

import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.plan.enums.IsDispatchEnum;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.entity.PaymentApply;
import cc.dfsoft.project.biz.base.subpackage.enums.FeeTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.service.PaymentApplyService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.SessionUtils;
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
import java.text.ParseException;
import java.util.Map;

/**
 * 付款审核
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/paymentAudit")
public class PaymentAuditController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(PaymentAuditController.class);
	
	/**审核记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**付款申请服务接口*/
	@Resource
	PaymentApplyService paymentApplyService;
	
	/**审核单据服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	/**工程服务接口*/
	@Resource
	ProjectService  projectService;
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年8月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("businessOrderId",SessionUtils.getBusinessOrderId());
		modelView.setViewName("subcontract/paymentAudit");
		return modelView;
	}
	
	/**
	 * 打开审核页面
	 * @author fuliwei
	 * @createTime 2017年8月21日
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/auditPage")
	public ModelAndView viewAuditPage(HttpServletRequest request) throws ParseException {
		ModelAndView modelView = new ModelAndView();
		String paId = request.getParameter("paId");
		String projId = "";
		
		//根据主键id查询付款申请
		PaymentApply pa=paymentApplyService.findById(projId, paId);
		
		if(pa!=null){
			if(StringUtils.isNotBlank(pa.getProjId())){
				Project pro=projectService.queryProjectById(pa.getProjId());
				pa.setCorpName(pro.getCorpName());
				pa.setDeptName(pro.getDeptName());
			}
		}
		
		modelView.addObject("pa",pa);
		modelView.addObject("projId",projId);
		modelView.addObject("paId",paId);
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);								 //登录人信息
		modelView.addObject("consFeeType",FeeTypeEnum.CONSFEE.getValue());		 //工程费
		modelView.addObject("feeType",pa.getFeeType());					 		//费用类型
		modelView.setViewName("subcontract/paymentAuditPage");
		return modelView;
	}
	
	
	/**
	 * 弹出搜索
	 * @author fuliwei
	 * @createTime 2017年8月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("feeTypeEnum",FeeTypeEnum.values());//费用类型
		modelview.setViewName("subcontract/paymentAuditSearchPopPage");
		return modelview;
	}
	
	
	/**
	 * 付款审核列表查询
	 * @author fuliwei
	 * @createTime 2017年8月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryPaymentAudit")
	@ResponseBody
	public Map<String,Object> queryPaymentAudit(PaymentApplyReq req){
		try {
			if(StringUtils.isBlank(req.getAuditState())){
				req.setAuditState(AuditResultEnum.APPLYING.getValue());//默认加载审核中的
			}
			req.setIsDispatch(IsDispatchEnum.IS_DISPATCH.getValue()); //已派工的
			return paymentApplyService.queryPaymentApplyAudit(req);
		} catch (Exception e) {
			logger.error("付款申请列表查询失败！",e);
			return null;
		}
	}
	
	

	/**
	 * 付款审核历史列表查询
	 * @author fuliwei
	 * @createTime 2017年8月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepOutWorkflowEnum.PAYMENT_AUDIT.getValue());	//付款审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	
	
	/***
	 * 付款审核记录保存
	 * @author fuliwei
	 * @createTime 2017年8月10日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		try {
			manageRecordService.auditPaymentApplySave(manageRecord,"","",StepOutWorkflowEnum.PAYMENT_AUDIT.getValue(),Constants.MODULE_CODE_SUBCONTRACT);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("付款审核记录保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
