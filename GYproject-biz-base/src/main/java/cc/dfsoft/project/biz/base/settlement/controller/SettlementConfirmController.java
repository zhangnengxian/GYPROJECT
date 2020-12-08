package cc.dfsoft.project.biz.base.settlement.controller;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.charge.service.ChargeService;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.contract.service.SupplementalContractService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.settlement.dto.SettlementDeclarationReq;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.enums.SubQuantitiesStatusEnum;
import cc.dfsoft.project.biz.base.settlement.service.SettlementDeclarationService;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesReq;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;
import cc.dfsoft.project.biz.base.subpackage.service.IntelligentMeterContractService;
import cc.dfsoft.project.biz.base.subpackage.service.SubQuantitiesService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;

import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
/**
 *
 * 描述:终审确认
 */
@Controller
@RequestMapping(value="/settlementConfirm")
public class SettlementConfirmController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SettlementConfirmController.class);
	/** 报审服务接口 */
	@Resource
	SettlementDeclarationService settlementDeclarationService;
	@Resource
	ManageRecordService manageRecordService;
	@Resource
	DocTypeService docTypeService;
	@Resource
	SignatureService signatureService;
	/** 分包工程量服务接口*/
	@Resource
	SubQuantitiesService subQuantitiesService;
	@Resource
	ProjectService projectService;
	@Resource
	IFinanceInfoService financeInfoService;
	@Resource
	ContractService contractService;
	@Resource
	SupplementalContractService supplementalContractService;

	@Resource
	IntelligentMeterContractService imContractService;
	@Resource
	AccrualsRecordService accrualsRecordService;
	@Resource
	ChargeService chargeService;
	/**
	 * 打开报审列表
	 * @return
	 */
	@RequestMapping(value="/reportConfirm")
	public ModelAndView reportConfirm(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("settlement/reportConfirm");
		return modelView;
	}
	/**
	 * 打开初审列表
	 * @return
	 */
	@RequestMapping(value="/startConfirm")
	public ModelAndView startConfirmAuditPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("settlement/startConfirm");
		return modelView;
	}
	/**
	 * 打开终审列表
	 * @return
	 */
	@RequestMapping(value="/endConfirm")
	public ModelAndView endConfirmAuditPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("curStepId",StepEnum.REPORT_DONE_CONFIRM.getValue());
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("settlement/endConfirm");
		return modelView;
	}


	/**
	 * 报审列表条件查询
	 * @author zhangjj
	 * @param request
	 * @param SettlementDeclarationReq
	 * @return
	 */
	@RequestMapping(value = "/querySettlement")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request, SettlementDeclarationReq req){
		try {
			if(req.getConfirmState().equals("reportConfirm")){
				//req.setProjStatusId(ProjStatusEnum.REPORT_CONFIRM.getValue());
				//req.setStepId(StepEnum.REPORT_CONFIRM.getValue());
			}else if(req.getConfirmState().equals("startConfirm")){
				//req.setProjStatusId(ProjStatusEnum.REPORT_START_CONFIRM.getValue());
				//req.setStepId(StepEnum.REPORT_START_CONFIRM.getValue());
			}else if(req.getConfirmState().equals("endConfirm")){
				req.setProjStatusId(ProjStatusEnum.REPORT_DONE_CONFIRM.getValue());
				req.setStepId(StepEnum.REPORT_DONE_CONFIRM.getValue());
			}

			req.setSortInfo(request);

			return settlementDeclarationService.querySettlementForAudit(req);
		} catch (Exception e) {
			e.printStackTrace();
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
		String confirmState = request.getParameter("confirmSort");
		modelView.addObject("settlementDeclaration",null);
		//审核详述查询
		try {
			SettlementDeclaration sd= settlementDeclarationService.getSettlementDeclaration(projId);
			if(sd!=null){
				Object jsonObj = JSONObject.toJSON(sd);
				modelView.addObject("settlementDeclaration",jsonObj);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelView.addObject("confirmSort",request.getParameter("confirmSort"));
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));   //当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			    //是否审核过
		modelView.addObject("loginInfo",SessionUtil.getLoginInfo());			    //放入登录人信息
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		modelView.addObject("mrResult",MrResultEnum.values());					//审核结果
		if(confirmState.equals("reportConfirm")){
			modelView.setViewName("settlement/reportConfirmAuditPage");
		}else if(confirmState.equals("startConfirm")){
			modelView.setViewName("settlement/startConfirmAuditPage");
		}else if(confirmState.equals("endConfirm")){
			modelView.setViewName("settlement/endConfirmAuditPage");
		}

		return modelView;
	}

	/**
	 * 管理记录列表查询
	 * @author zhangjj
	 * @createTime 2016-09-21
	 * @param manageRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			if(StringUtil.isNoneBlank(manageRecordQueryReq.getSettlementState())){
				String state=manageRecordQueryReq.getSettlementState();
				if(state.equals("report")){
					//manageRecordQueryReq.setStepId(StepEnum.REPORT_CONFIRM.getValue());	//报审确认
				}else if(state.equals("start")){
					manageRecordQueryReq.setStepId(StepEnum.SETTLEMENT_REPORT_START.getValue());	//初审确认
				}else if(state.equals("end")){
					manageRecordQueryReq.setStepId(StepEnum.REPORT_DONE_CONFIRM.getValue());	//终审确认
				}
				return manageRecordService.queryManageRecordSign(manageRecordQueryReq);
			}else{
				return null;
			}

		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}

	/**
	 * 报审确认
	 * @author zhangjj
	 * @createTime 2016-09-23
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){

		try {
			/*DocType docType = docTypeService.findByStepId(StepEnum.REPORT_CONFIRM.getValue());
			manageRecordService.auditSave(manageRecord,docType==null?"":docType.getId(),
					docType==null?"":docType.getGrade(),StepEnum.REPORT_CONFIRM.getValue(),Constants.MODULE_CODE_SETTLEMENT);
			signatureService.saveOrUpdateSign(manageRecord.getSign(), manageRecord.getProjId(), manageRecord.getMrId(), manageRecord.getClass().getName(), true);
			//signatureService.saveOrUpdateSign(manageRecord.getSign(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(),SettlementDeclaration.class.getClass().getName(), false);
			*/return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("报审确认失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}

	}
	/**
	 * 初审确认
	 * @author zhangjj
	 * @createTime 2016-09-26
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/startAuditSave")
	@ResponseBody
	public String startAuditSave(@RequestBody(required = true) ManageRecord manageRecord){

		try {
			//先判断当前级别是否已审核，已审核则不能再次审核
        	ManageRecord manageRecordHistory =  manageRecordService.queryManRdHistory(manageRecord.getProjId(),manageRecord.getBusinessOrderId(),StepEnum.SETTLEMENT_REPORT_START.getValue(),manageRecord.getMrAuditLevel(),"0");
        	if(manageRecordHistory != null){
    			return Constants.OPERATE_RESULT_SUCCESS;
        	}
			manageRecordService.auditSettlementStartSave(manageRecord,"","",StepEnum.SETTLEMENT_REPORT_START.getValue(),Constants.MODULE_CODE_SETTLEMENT);
			//signatureService.saveOrUpdateSign(manageRecord.getSign(), manageRecord.getProjId(), manageRecord.getMrId(), manageRecord.getClass().getName(), true);
			//signatureService.saveOrUpdateSign(manageRecord.getSign(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(),SettlementDeclaration.class.getClass().getName(), false);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("结算初审失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}

	}
	/**
	 * 终审确认
	 * @author zhangjj
	 * @createTime 2016-09-26
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/endAuditSave")
	@ResponseBody
	public ResultMessage endAuditSave(@RequestBody(required = true) ManageRecord manageRecord){
		ResultMessage resultMessage = new ResultMessage();
		try {
			//先判断当前级别是否已审核，已审核则不能再次审核
        	ManageRecord manageRecordHistory =  manageRecordService.queryManRdHistory(manageRecord.getProjId(),manageRecord.getBusinessOrderId(),StepEnum.REPORT_DONE_CONFIRM.getValue(),manageRecord.getMrAuditLevel(),"0");
        	if(manageRecordHistory != null){
        		resultMessage.setRet_type(Constants.FAIL_CODE);
    			resultMessage.setRet_message(Constants.OPERATE_RESULT_SUCCESS);
    			return resultMessage;
        	}
			manageRecordService.auditSettlementEndSave(manageRecord,"","",WorkFlowActionEnum.REPORT_DONE_CONFIRM.getActionCode(),Constants.MODULE_CODE_SETTLEMENT);
			//signatureService.saveOrUpdateSign(manageRecord.getSign(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(),SettlementDeclaration.class.getName(), false);

			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_SUCCESS);
			return resultMessage;

		} catch (ExpressException e) {
			logger.error("报审确认失败！",e);
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		} catch (Exception e) {
			logger.error("报审确认失败！",e);
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}

	}
	/**
	 * 打开终审列表
	 * @return
	 */
	@RequestMapping(value="/settleSearchPopPage")
	public ModelAndView settleSearchPopPage(String state){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("state", state);
		modelView.setViewName("settlement/settleSearchPopPage");
		return modelView;
	}
	/**
	 * 终审工程条件列表查询
	 * @param request
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/querySettlementEnd")
	@ResponseBody
	public Map<String,Object> querySettlementEndProject(HttpServletRequest request, SettlementDeclarationReq req){
		try {
			req.setProjStatusId(ProjStatusEnum.REPORT_DONE_CONFIRM.getValue());
			req.setStepId(StepEnum.REPORT_DONE_CONFIRM.getValue());
			req.setSortInfo(request);
			return settlementDeclarationService.querySettlementForEnd(req,"");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 分包工程量条件查询
	 * @param subQuantitiesReq
	 * @return
	 */
	@RequestMapping(value ="/queryQuantities")
	@ResponseBody
	public Map<String, Object> queryQuantityStandard(SubQuantitiesReq subQuantitiesReq){
//		if(subQuantitiesReq.getSettlementState().equals("1")){
//			subQuantitiesReq.setAuditStatus(AuditStatusEnum.DONE_FIRST.getValue());
//		}else if(subQuantitiesReq.getSettlementState().equals("2")){
//			subQuantitiesReq.setAuditStatus(AuditStatusEnum.DONE_SECOND.getValue());
//		}else{
//			subQuantitiesReq.setAuditStatus(AuditStatusEnum.DONE_THIED.getValue());
//		}
		try {
			subQuantitiesReq.setSqStatus(SubQuantitiesStatusEnum.SETTLEMENT.getValue());
			return subQuantitiesService.querySubQuantityStandard(subQuantitiesReq);
		} catch (Exception e) {
			logger.error("分步分项名称查询失败！", e);
			return null;
		}
	}

	/**
	 * 工程量标准查询弹框
	 * @return
	 */
	@RequestMapping(value="/standardPop")
	public ModelAndView securityTopPage(HttpServletRequest request){
		String id=request.getParameter("id");
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projId", id);
		modelView.setViewName("settlement/confirmQuantityStandardPop");
		return modelView;
	}
	/**
	 * 根据工程ID查询结算详述
	 * @author liaoyq
	 * @createTime 2017-8-28
	 * @param id 工程ID
	 * @return  SettlementDeclaration
	 */
	@RequestMapping(value="/viewSettlement")
	@ResponseBody
	public SettlementDeclaration viewSettlement(String id){
		SettlementDeclaration settlementDeclaration = new SettlementDeclaration();
		try {
			settlementDeclaration= settlementDeclarationService.getSettlementDeclaration(id);
			//查询安装合同
			Contract  contract = contractService.findByProjId(settlementDeclaration.getProjId());
			if(contract!=null){
				settlementDeclaration.setConNo(contract.getConNo());
				settlementDeclaration.setContractAmount(contract.getContractAmount()!=null?contract.getContractAmount():new BigDecimal(0));
				settlementDeclaration.setMaterialIsRegister(StringUtil.isNotBlank(contract.getMaterialIsRegister())?contract.getMaterialIsRegister():"");
				settlementDeclaration.setMaterialRemark(StringUtil.isNotBlank(contract.getMaterialRemark())?contract.getMaterialRemark():"");
			}
			//查询补充协议todo：
			List<SupplementalContract> supCons = supplementalContractService.findByProjId(settlementDeclaration.getProjId());
			if(supCons!=null && supCons.size()>0){
				BigDecimal totalAmount = new BigDecimal(0);
				for(SupplementalContract supCon :supCons){
					totalAmount = totalAmount.add(supCon.getScAmount());
				}
				settlementDeclaration.setSupConAmount(totalAmount.toString());
			}else{
				settlementDeclaration.setSupConAmount("0");
			}
			//智能表合同款
			IntelligentMeterContract imc = imContractService.findContractByprojId(settlementDeclaration.getProjId());
			if(imc!=null){
				settlementDeclaration.setImcAmount(imc.getTotalCost().toString());
			}else{
				settlementDeclaration.setImcAmount("0");
			}
			//应收款、预付款
			List<AccrualsRecord> accRecords = accrualsRecordService.findbyProjIdType(settlementDeclaration.getProjId(), settlementDeclaration.getProjNo(), null,null);
			if(accRecords!=null && accRecords.size()>0){
				BigDecimal totalAmount = new BigDecimal(0);
				BigDecimal subApplyAmount = new BigDecimal(0);
				for(AccrualsRecord accRecord :accRecords){
					if(ARFlagEnum.RECEIVE_ACCOUNT.getValue().equals(accRecord.getArFlag().toString())){
						totalAmount = totalAmount.add(accRecord.getArCost());
					}else{
						subApplyAmount = subApplyAmount.add(accRecord.getArCost());
					}
				}
				settlementDeclaration.setTotalAmount(totalAmount.toString());
				settlementDeclaration.setSubApplyAmount(subApplyAmount.toString());
			}else{
				settlementDeclaration.setTotalAmount("0");
				settlementDeclaration.setSubApplyAmount("0");
			}
			//实收款、实付款
			List<CashFlow> cashFlows = chargeService.queryCashFlowByProjIdType(settlementDeclaration.getProjId(),null,null);
			if(cashFlows!=null && cashFlows.size()>0){
				BigDecimal finalAmount = new BigDecimal(0);
				BigDecimal subAmount = new BigDecimal(0);
				for(CashFlow cashFlow :cashFlows){
					if(ARFlagEnum.RECEIVE_ACCOUNT.getValue().equals(cashFlow.getCfFlag().toString())){
						finalAmount = finalAmount.add(cashFlow.getCfAmount());
					}else{
						subAmount = subAmount.add(cashFlow.getCfAmount());
					}
				}
				settlementDeclaration.setFinalAmount(finalAmount.toString());//实收
				settlementDeclaration.setSubAmount(subAmount.toString());//实付
			}else{
				settlementDeclaration.setFinalAmount("0");
				settlementDeclaration.setSubAmount("0");//实付
			}

			return settlementDeclaration;
		} catch (Exception e) {
			logger.error("结算详述查询失败！", e);
			return settlementDeclaration;
		}

	}
}
