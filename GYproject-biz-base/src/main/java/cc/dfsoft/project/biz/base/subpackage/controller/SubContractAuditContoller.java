package cc.dfsoft.project.biz.base.subpackage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.contract.enums.PayTypeSCEnum;
import cc.dfsoft.project.biz.base.design.dto.SurveyInfoQueryReq;
import cc.dfsoft.project.biz.base.design.service.SurveyInfoService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.subpackage.dto.SubAuditReq;
import cc.dfsoft.project.biz.base.subpackage.enums.SubContractMethodEnum;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractAuditService;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.SessionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;

import java.util.Map;

/**
 * 施工合同审核
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/subContractAudit")
public class SubContractAuditContoller {

	private static Logger logger = LoggerFactory.getLogger(SubContractAuditContoller.class);

	@Autowired
	private SubContractAuditService subContractAuditService;

	@Resource
	SurveyInfoService surveyInfoService;

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
		modelView.addObject("notThrough",Constants.MODULE_CODE_CONTRACT);
		modelView.addObject("curStepId",StepEnum.SUB_CONTRACT_AUDIT.getValue());
		modelView.addObject("projNo", SessionUtils.getProjNo());
		LoginInfo login=SessionUtil.getLoginInfo();
		modelView.setViewName("subcontract/subContractAudit");
		return modelView;
	}

	/**
	 * 查询需要审核合同信息
	 * @param surveyInfoQueryReq
	 * @return
	 */
	@RequestMapping(value = "/querySubContractAuditList")
	@ResponseBody
	public Map<String,Object> querySubContractAuditList(SubAuditReq subAuditReq){
		try {
			subAuditReq.setProjStatusId(ProjStatusEnum.TO_AUDIT_SUBCONTRACT.getValue());//施工合同待审核
			subAuditReq.setStepId(StepEnum.SUB_CONTRACT_AUDIT.getValue());//施工合同审核
			subAuditReq.setTimeLimit(StepEnum.SUB_CONTRACT_AUDIT.getStepDay());//逾期天数
			return subContractAuditService.querySubContractAuditList(subAuditReq);
		} catch (Exception e) {
			logger.error("勘察信息列表查询失败！",e);
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
		String scId = request.getParameter("scId");
		//审核详述查询
		modelView.addObject("data",subContractAuditService.getSubContractAuditById(scId));
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));			//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));				 		//是否审核过
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);											//登录人信息
		modelView.addObject("mrResult",MrResultEnum.values());					 			//审核结果
		modelView.addObject("stepId",StepEnum.SUB_CONTRACT.getValue());			//上一个页面的步骤id
		modelView.addObject("subContractMethod", SubContractMethodEnum.values());//建筑服务
		modelView.addObject("payTypeSCEnum", PayTypeSCEnum.values()); 			//支付方式
		modelView.setViewName("subcontract/subContractAuditPage");
		return modelView;
	}


	/**
	 * 施工合同审核查询弹出屏
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/subContractAuditSearchPage")
	public ModelAndView subContractAuditSearchPage(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("subcontract/suAuditSearchPopPage");
		return modelView;
	}

	/**
	 * 管理记录列表查询
	 * @author pengtt
	 * @createTime
	 * @param manageRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepEnum.SUB_CONTRACT_AUDIT.getValue());	//方案审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}

	/**
	 * 保存施工合同审核信息
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		ResultMessage resultMessage = new ResultMessage();
		try {
			manageRecordService.subContractAudit(manageRecord,"","", WorkFlowActionEnum.SUB_CONTRACT_AUDIT.getActionCode(),Constants.MODULE_CODE_SUBCONTRACT);
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_SUCCESS);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch (ExpressException e) {
			logger.error("分包合同审核保存失败！", e);
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return Constants.OPERATE_RESULT_FAILURE;
		}
		catch (Exception e) {
			logger.error("分包合同审核保存失败！", e);
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
