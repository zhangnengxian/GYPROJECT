package cc.dfsoft.project.biz.base.contract.controller;

import cc.dfsoft.project.biz.base.accept.service.ProjectApplicationService;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.CustomerService;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.dto.ContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.enums.BudgetRuleEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractMethodEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.contract.enums.PayTypeEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.contract.service.PayTypeService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dto.JsonDateValueProcessor;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
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
@RequestMapping(value="/contractAudit")
public class ContractAuditController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ContractAuditController.class);
	
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
		modelView.addObject("stepId", StepEnum.CONTRACT_AUDIT.getValue());
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("contract/contractAudit");
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
			contractQueryReq.setProjStatusId(ProjStatusEnum.TO_AUDIT_CONTRACT.getValue());//待审合同
			contractQueryReq.setSortInfo(request);
			//contractQueryReq.setTimeLimit(StepEnum.CONTRACT_AUDIT.getStepDay());
			contractQueryReq.setStepId(StepEnum.CONTRACT_AUDIT.getValue());
			Map<String,Object> map=contractService.queryContractforAudit(contractQueryReq);
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
	* 审核屏左侧页面配置规则：工程类型_公司ID_菜单ID
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
		modelView.addObject("projId",request.getParameter("projId")); //工程ID
		modelView.setViewName("contract/constructAuditPage");
		/**
		 * todo:
		 */
		/*//根据数据库配置，得到不同公司下配置的左侧页面
		String menuId = request.getParameter("menuId");
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//默认当前用户所在公司
		String corpId = loginInfo.getCorpId();
		//默认民用工程类型
		String projType = ProjLtypeEnum.CIVILIAN.getValue();
		String projId = request.getParameter("projId");
		Project project = new Project();
		if(StringUtil.isNotBlank(projId)){
			project = projectService.queryProjectById(projId);
		}
		if(project!=null && StringUtil.isNotBlank(project.getProjectType())){
			corpId = project.getCorpId();
			projType = project.getProjectType();
		}
		String key = projType+"_"+corpId+"_"+menuId;
		Object obj = Constants.getSysConfigByKey(key);
		if(obj!=null){
			modelView.addObject("conAuditPageLeft",obj.toString());	
		}else{
			modelView.addObject("conAuditPageLeft","guiyang_conAuditPageLeft");	
		}*/
		return modelView;
	}
	
	/**
	 * 合同详述
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String conId,String menuId,String projId) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Project pro=projectService.viewProject(projId);

		StringBuilder key=new StringBuilder();//key为：工程类型_分公司ID_菜单ID
		key.append(pro!=null?pro.getProjectType():ProjLtypeEnum.CIVILIAN.getValue()).append("_").append(pro!=null?pro.getCorpId():loginInfo.getCorpId()).append("_").append(menuId);
		Object obj = Constants.getSysConfigByKey(key.toString());

		String viewUrl="constructContractRight";
		StringBuilder resultViewUrl=new StringBuilder(obj!=null?obj.toString():viewUrl);

		ModelAndView modelview = new ModelAndView();
		modelview.addObject("customerServiceSenter", DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());//客服中心
		modelview.addObject("modificationGroup", DeptDivideEnum.MODIFICATION_GROUP.getValue());//改管组
		modelview.addObject("payType", payTypeService.findByContractType(null,pro!=null?pro.getCorpId():loginInfo.getCorpId()));//付款方式
		modelview.addObject("contractMethod",ContractMethodEnum.values());//承包方式
		modelview.addObject("contractType",ContractTypeEnum.values());//合同类型
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());//放入登录人信息
		modelview.addObject("sysDate",projectDao.getDatabaseDate());//系统时间
		modelview.addObject("increment",incrementDao.queryAll());//税率
		modelview.addObject("differenceId",Constants.PUBLIC_SIGN);//合同签订标识
		modelview.addObject("contractJsonStr",obtainContractDetail(conId,pro));//合同详细
		modelview.setViewName("contract/"+resultViewUrl);
		//民用户工程获取每户单价
		StringBuilder unitCostStr=new StringBuilder(pro!=null?pro.getCorpId():loginInfo.getCorpId()).append("_").append(menuId);
		modelview.addObject("unitCost",Constants.getSysConfigByKey(unitCostStr.toString()));//民用合同单价
		modelview.addObject("budgetRuleEnum", BudgetRuleEnum.values());//预算制度
		return modelview;
	}



	public String obtainContractDetail(String conId,Project pro){
		try {
			Contract contractDetaile = contractService.getContract(conId);
			if (pro!=null&&contractDetaile!=null){
				contractDetaile.setProjectTypeDes(pro.getProjectTypeDes());//工程类型描述
				contractDetaile.setProjectType(pro.getProjectType());//工程类型代码
				contractDetaile.setContributionModeDes(pro.getContributionModeDes());//出资方式
				contractDetaile.setDeptName(pro.getDeptName());//业务部门
			}

			JSONObject contractJsonStr = new JSONObject();
			if (contractDetaile!=null){
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());//处理JSONObject序列化后的Date类型
				contractJsonStr=JSONObject.fromObject(contractDetaile,jsonConfig);//将java对象转换为json对象
			}

           String jsonStr= contractJsonStr.toString();
			//将转义字符替换掉
			return jsonStr.replaceAll("(\\\\r\\\\n|\\\\r|\\\\n|\\\\n\\\\r)","");

		}catch (Exception e){
			logger.error("合同详细查询失败！",e);
			return null;
		}
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
			manageRecordQueryReq.setStepId(StepEnum.CONTRACT_AUDIT.getValue());	//合同审核
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
			//返回值为"true"，则不是审核通过
			String result =  manageRecordService.contractAuditSave(manageRecord,"","",WorkFlowActionEnum.CONTRACT_AUDIT.getActionCode());
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(result);
			return resultMessage;
		} catch (ExpressException e) {
			logger.error("合同审核失败！",e);
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		} catch (Exception e) {
			logger.error("合同审核失败！",e);
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
		
	}
}
