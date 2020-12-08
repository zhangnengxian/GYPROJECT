package cc.dfsoft.project.biz.base.contract.controller;

import cc.dfsoft.project.biz.base.accept.service.ProjectApplicationService;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.CustomerService;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.enums.*;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.contract.service.PayTypeService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.OperateRecordLog;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordLogService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dto.IntelligentMeterContractReq;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;
import cc.dfsoft.project.biz.base.subpackage.service.IntelligentMeterContractService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.SessionUtils;

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
 * 智能表合同修改审核
 * @author cui
 * @createTime 2017-11-8
 *
 */
@Controller
@RequestMapping(value="/imcModifyAudit")
public class ImcModifyAuditController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ImcModifyAuditController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**合同服务接口*/
	@Resource
	ContractService contractService;
	
	/**管理记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	@Resource
	ProjectApplicationService projectApplicationService;
	
	@Resource
	PayTypeService payTypeService;
	
	@Resource
	CustomerService customerService;
	
	@Resource
	OperateRecordLogService operateRecordLogService;

	/**合同服务接口*/
	@Resource
	IntelligentMeterContractService imcService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("stepId", StepEnum.CONTRACT_AUDIT.getValue());
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("contract/imcModifyAudit");
		return modelView;
	}
	
	/**
	 * 合同列表条件查询
	 * @author cui
	 * @createTime 2017-11-09
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryContract")
	@ResponseBody
	public Map<String,Object> queryContract(HttpServletRequest request, IntelligentMeterContractReq imcQueryReq){
		try {
			imcQueryReq.setSortInfo(request);
			//imcQueryReq.setTimeLimit(StepEnum.CONTRACT_AUDIT.getStepDay());
			imcQueryReq.setStepId(StepOutWorkflowEnum.INTELLIGENT_MODIFY_AUDIT.getValue());
			imcQueryReq.setModifyStatus(ModifyStatusEnum.TO_AUDIT.getValue());
			Map<String,Object> map=imcService.queryContractforModifyAudit(imcQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 打开合同审核查询页面
	 * @createTime 2016-07-11
	 * @return
	 */
	@RequestMapping(value="/searchPopPage")
	public ModelAndView contractAuditSearchPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("payType", PayTypeEnum.values());		//付款方式
		modelView.setViewName("contract/contractAuditSearchPage");
		return modelView;
	}
	
	/**
	* 打开审核屏
	* @return ModelAndView
	*/
	@RequestMapping(value="/auditPage")
	public ModelAndView audit(HttpServletRequest request){
		
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		modelView.addObject("loginInfo",SessionUtil.getLoginInfo());			 //放入登录人信息
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		modelView.addObject("mrResult",MrResultEnum.values());					 //审核结果
		modelView.addObject("imcId",request.getParameter("imcId"));				 //合同id
		modelView.setViewName("contract/imcModifyAuditPage");
		return modelView;
	}
	
	/**
	 * 合同详述
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(HttpServletRequest request) {
		
		ModelAndView modelview = new ModelAndView();
		IntelligentMeterContract contract = imcService.getImContract(request.getParameter("imcId"));
		Project pro=projectService.queryProjectById(contract.getProjId());
		contract.setProjectTypeDes(pro.getProjectTypeDes());//工程类型
		contract.setContributionModeDes(pro.getContributionModeDes());//出资方式
		contract.setDeptName(pro.getDeptName());//业务部门
		contract.setProjScaleDes(pro.getProjScaleDes()); //工程规模
		modelview.addObject("contract",contract);								//放入合同对象
		OperateRecordLog operateRecordLog = operateRecordLogService.findLatelyLog(OperateTypeEnum.IMCCONTRACT_MODIFY.getValue(),contract.getImcId());
		modelview.addObject("operateContent", operateRecordLog.getOperateContent());//内容
		modelview.addObject("payType", payTypeService.findByContractType(ContractTypeEnum.RESIDENT.getValue(),null));//付款方式
		modelview.addObject("contractMethod",ContractMethodEnum.values());//承包方式
		modelview.addObject("contractType",ContractTypeEnum.values());//合同类型
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());//放入登录人信息
		modelview.setViewName("contract/imcAuditLeft");
		return modelview;
	}
	
	/**
	 * 管理记录列表查询
	 * @author cui
	 * @createTime 2017-11-9
	 * @param manageRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepOutWorkflowEnum.INTELLIGENT_MODIFY_AUDIT.getValue());	//智能表合同修改审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 合同审核保存
	 * @author cui
	 * @createTime 2017-11-8
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public ResultMessage auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		ResultMessage resultMessage = new ResultMessage();
		try {
			//智能表合同修改审核调用有接口
			String res= manageRecordService.imcModifyAuditSave(manageRecord,"","",StepOutWorkflowEnum.INTELLIGENT_MODIFY_AUDIT.getValue());
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(res);
			return resultMessage;
		} catch (ExpressException e) {
			logger.error("智能表合同审核失败！",e);
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		}catch (Exception e) {
			logger.error("智能表合同审核失败！",e);
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
		
	}
}
