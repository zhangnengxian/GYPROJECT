package cc.dfsoft.project.biz.base.contract.controller;

import cc.dfsoft.project.biz.base.accept.service.ProjectApplicationService;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.CustomerService;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.dto.ContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.enums.*;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.contract.service.PayTypeService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordLogService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dto.JsonDateValueProcessor;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.Map;
/**
 * 合同审核
 * @author pengtt
 * @createTime 2016-07-07
 *
 */
@Controller
@RequestMapping(value="/contractModifyAudit")
public class ContractModifyAuditController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ContractModifyAuditController.class);
	
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
	
	@Resource
	IFinanceInfoService financeInfoService;

	@Resource
	ProjectDao projectDao;
	@Resource
	IncrementDao incrementDao;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.addObject("stepId", StepEnum.CONTRACT_AUDIT.getValue());
		modelView.setViewName("contract/contractModifyAudit");
		return modelView;
	}
	
	/**
	 * 合同列表条件查询
	 * @author pengtt
	 * @createTime 2016-07-08
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryContract")
	@ResponseBody
	public Map<String,Object> queryContract(HttpServletRequest request,ContractQueryReq contractQueryReq){
		try {
			contractQueryReq.setSortInfo(request);
			//contractQueryReq.setTimeLimit(StepEnum.CONTRACT_AUDIT.getStepDay());
			contractQueryReq.setStepId(StepOutWorkflowEnum.CONTRACT_MODIFY_AUDIT.getValue());
			contractQueryReq.setModifyStatus(ModifyStatusEnum.TO_AUDIT.getValue());
			Map<String,Object> map=contractService.queryContractforModifyAudit(contractQueryReq);
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
		modelView.setViewName("contract/e");
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
		modelView.addObject("conId",request.getParameter("conId"));				 //合同id
		modelView.addObject("projId",request.getParameter("projId"));

		modelView.setViewName("contract/constructModifyAuditPage");
		return modelView;
	}
	
	/**
	 * @methodDesc: 打开合同详细页面
	 * @author: zhangnx
	 * @date: 9:19 2018/9/19
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String conId,String menuId,String projId) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String corpId=loginInfo.getCorpId();//默认当前用户的corpId;
		String projectType = ProjLtypeEnum.CIVILIAN.getValue();//默认民用工程类型

		Contract contractDetaile = contractService.getContract(conId);

		Project pro=projectService.viewProject(projId);
		if (pro!=null){
			contractDetaile.setProjectTypeDes(pro.getProjectTypeDes());//工程类型描述
			contractDetaile.setProjectType(pro.getProjectType());//工程类型代码
			contractDetaile.setContributionModeDes(pro.getContributionModeDes());//出资方式
			contractDetaile.setDeptName(pro.getDeptName());//业务部门
			corpId=pro.getCorpId();
			projectType=pro.getProjectType();
		}

		String key = projectType+"_"+corpId+"_"+menuId;
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "";
		if(obj !=null){
			viewUrl = obj.toString();
		}


		JSONObject contractJsonStr = new JSONObject();
		if (contractDetaile!=null){
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());//处理JSONObject序列化后的Date类型
			contractJsonStr=JSONObject.fromObject(contractDetaile,jsonConfig);//将java对象转换为json对象
		}

		ModelAndView modelview = new ModelAndView();
		modelview.addObject("customerServiceSenter", DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());//客服中心
		modelview.addObject("modificationGroup", DeptDivideEnum.MODIFICATION_GROUP.getValue());//改管组
		modelview.addObject("payType", payTypeService.findByContractType(null,corpId));//付款方式
		modelview.addObject("contractMethod",ContractMethodEnum.values());//承包方式
		modelview.addObject("contractType",ContractTypeEnum.values());//合同类型
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());//放入登录人信息
		modelview.addObject("sysDate",projectDao.getDatabaseDate());//系统时间
		modelview.addObject("increment",incrementDao.queryAll());//税率
		modelview.addObject("differenceId",Constants.PUBLIC_SIGN);//合同签订标识
		modelview.addObject("contractJsonStr",contractJsonStr);//合同详细
		modelview.setViewName("contract/"+viewUrl);
		//民用户工程获取每户单价
		modelview.addObject("unitCost",Constants.getSysConfigByKey(corpId+"_"+menuId));//民用合同单价
		modelview.addObject("budgetRuleEnum", BudgetRuleEnum.values());//预算制度

		return modelview;
	}


	/**
	 * 管理记录列表查询
	 * @author pengtt
	 * @createTime 2016-07-11
	 * @param manageRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepOutWorkflowEnum.CONTRACT_MODIFY_AUDIT.getValue());	//合同修改审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 合同审核保存
	 * @author pengtt
	 * @createTime 2016-07-11
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public ResultMessage auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		ResultMessage resultMessage = new ResultMessage();
		try {
			String result = manageRecordService.contractModifyAuditSave(manageRecord,"","",StepOutWorkflowEnum.CONTRACT_MODIFY_AUDIT.getValue());
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(result);
			return resultMessage;
		} catch (ExpressException e) {
			logger.error("合同审核失败！",e);
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		} catch (Exception e) {
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			logger.error("合同审核失败！",e);
			 return resultMessage;
		}
		
	}
}
