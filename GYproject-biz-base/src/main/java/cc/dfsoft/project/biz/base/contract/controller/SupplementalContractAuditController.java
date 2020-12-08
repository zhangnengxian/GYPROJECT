package cc.dfsoft.project.biz.base.contract.controller;

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

import cc.dfsoft.project.biz.base.accept.service.ProjectApplicationService;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.CustomerService;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.dto.SupplementalContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.contract.service.PayTypeService;
import cc.dfsoft.project.biz.base.contract.service.SupplementalContractService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.SessionUtils;
/**
 * 
 * @author wmy
 *补充协议审核
 */
@Controller
@RequestMapping(value="/supplementalContractAudit")
public class SupplementalContractAuditController {
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
	SupplementalContractService supplementalContractService;
	//打开主页面
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("stepId", StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_AUDIT.getValue());
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("contract/supplementalContractAudit");
		return modelView;
	}
	/**
	 * 查询待审核协议列表
	 * @author wmy
	 * @createTime 2017年12月26日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/querySupplemental")
	@ResponseBody
	public Map<String,Object> queryContract(HttpServletRequest request,SupplementalContractQueryReq supplementalContractQueryReq){
		try {
			supplementalContractQueryReq.setSortInfo(request);
			supplementalContractQueryReq.setTimeLimit(StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_AUDIT.getStepDay());//设置限制时间
			supplementalContractQueryReq.setStepId(StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_AUDIT.getValue());//设置步骤
			Map<String,Object> map=supplementalContractService.querySupplementalforAudit(supplementalContractQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
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
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		modelView.addObject("loginInfo",SessionUtil.getLoginInfo());			 //放入登录人信息
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		modelView.addObject("mrResult",MrResultEnum.values());					 //审核结果
		modelView.addObject("scId",request.getParameter("scId"));				 //合同id
		SupplementalContract supplementContract=supplementalContractService.findByScId(request.getParameter("scId"));
		modelView.addObject("projId",supplementContract.getProjId());
		modelView.addObject("projNo",supplementContract.getProjNo());
		modelView.setViewName("contract/supplementalContractAuditPage");
		return modelView;
	}
	/**
	 * @author wmy
	 * 协议详述
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(HttpServletRequest request) {
		
		ModelAndView modelview = new ModelAndView();
		String id=request.getParameter("scId");
		SupplementalContract supplementalContract=supplementalContractService.findByScId(request.getParameter("scId"));
		Project pro=projectService.viewProject(supplementalContract.getProjId());
		supplementalContract.setProjectTypeDes(pro.getProjectTypeDes());//工程类型描述
		supplementalContract.setContributionModeDes(pro.getContributionModeDes());//出资方式
		supplementalContract.setDeptName(pro.getDeptName());//业务部门
		modelview.addObject("supplementalContract",supplementalContract);//放入协议对象
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());//放入登录人信息
		modelview.addObject("projType",pro.getProjectType());//工程类型
		modelview.setViewName("contract/supplementalContractAuditPageLeft");					
		return modelview;
	}
	/**
	 * 管理记录列表查询
	 * @author wmy
	 * @createTime 2016-07-11
	 * @param manageRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_AUDIT.getValue());	//合同审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	/**
	 * 协议审核保存
	 * @author wmy
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
			String result =  manageRecordService.supplementalContractAuditSave(manageRecord,"","",StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_AUDIT.getValue());
			resultMessage.setRet_type("0");
			resultMessage.setRet_message(result);
			return resultMessage;
		} catch (ExpressException e) {
			logger.error("合同审核失败！",e);
			resultMessage.setRet_type("1");
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		} catch (Exception e) {
			logger.error("合同审核失败！",e);
			resultMessage.setRet_type("-1");
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
		
	}
	/**
	 * 打开协议审核查询页面
	 * @author wmy
	 * @return
	 */
	@RequestMapping(value="/searchPopPage")
	public ModelAndView contractAuditSearchPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("contract/supplementalContractAuditSearchPage");
		return modelView;
	}
	
}
