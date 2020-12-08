package cc.dfsoft.project.biz.base.contract.controller;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.charge.service.ChargeService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.dto.ContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.entity.PayType;
import cc.dfsoft.project.biz.base.contract.enums.BudgetRuleEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractMethodEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.contract.enums.ModifyStatusEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.contract.service.PayTypeService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 合同修改
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/contractModify")
public class ContractModifyController {
	
	/** 日志实例 */
	private static Logger logger= LoggerFactory.getLogger(ContractModifyController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/** 安装合同接口*/
	@Resource
	ContractService contractService;
	
	@Resource
	PayTypeService payTypeService;

	@Resource
	ProjectDao projectDao;
	@Resource
	IncrementDao incrementDao;
	@Resource
	DepartmentService departmentService;
	@Resource
	ChargeService chargeService;
	@Resource
	AccrualsRecordService accrualsRecordService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("notThrough", Constants.MODULE_CODE_CONTRACT);
		modelView.addObject("stepId", StepOutWorkflowEnum.CONTRACT_MODIFY_AUDIT.getValue());
		modelView.addObject("curStepId",StepOutWorkflowEnum.CONTRACT_MODIFY.getValue());
		modelView.setViewName("contract/contractModify");
		return modelView;
	}
	
	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String projId,String projectType,String corpId,String menuId) {
		ModelAndView modelview = new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(corpId)){
			corpId = loginInfo.getCorpId();//默认当前用户ID
		}
		if(StringUtil.isBlank(projectType)){
			projectType = ProjLtypeEnum.CIVILIAN.getValue();//默认民用工程类型
		}
		String key = projectType+"_"+corpId+"_"+menuId;
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "";
		if(obj !=null){
			viewUrl = obj.toString();
		}else{
			viewUrl = "";
		}
		modelview.addObject("payType", payTypeService.findByContractType(null,corpId));//付款方式
		modelview.addObject("contractMethod", ContractMethodEnum.values());//承包方式
		modelview.addObject("contractType", ContractTypeEnum.values());//合同类型
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());//放入登录人信息
		modelview.addObject("sysDate",projectDao.getDatabaseDate());//系统时间
		modelview.addObject("increment",incrementDao.queryAll());//税率
		modelview.addObject("differenceId",Constants.PUBLIC_MODIFY);//合同修改
		modelview.setViewName("contract/"+viewUrl);
		//民用户工程获取每户单价
		modelview.addObject("budgetRuleEnum", BudgetRuleEnum.values());//预算制度
		//modelview.setViewName("contract/contractModifyRight");
		return modelview;
	}
	/**
	 * 弹出搜索屏
	 * @author fuliwei
	 * @createTime 2016-6-28
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/contractModifySearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.addObject("modifyStatus", ModifyStatusEnum.values());
		modelview.setViewName("contract/contractModifySearchPopPage");
		return modelview;
	}
	
	/**
	 * 合同列表查询
	 * @param request
	 * @param contractQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryContract")
	@ResponseBody
	public Map<String,Object> queryContract(HttpServletRequest request, ContractQueryReq contractQueryReq){
		try {
			contractQueryReq.setSortInfo(request);
			List<String> projStuList = new ArrayList<String>();
			List<ProjStatusEnum> projStusEnum =ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_DATA_ACQUISITION.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue());
			for (ProjStatusEnum projStatusEnum : projStusEnum) {
				projStuList.add(projStatusEnum.getValue());
			}
			contractQueryReq.setProjStuList(projStuList);
			// contractQueryReq.setModifyStatus("all");
			//查询条件IS_PASS:1；MODIFY_STATUS:null的数据
			Map<String,Object> map=contractService.queryContractPrint(contractQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 详述
	 * @author fuliwei
	 * @createTime 2016-6-28
	 * @param id 工程id
	 * @return Project
	 */
	@RequestMapping(value="/viewContract")
	@ResponseBody
	public Contract viewContract(@RequestParam(required=true) String id){
		Project pro=projectService.viewProject(id);
		Contract contract = contractService.findByProjId(id);
		contract.setCustName(pro.getCustName());  //客户名称
		contract.setCustPhone(pro.getCustPhone());//甲方联系方式
		contract.setProjectTypeDes(pro.getProjectTypeDes());//工程类型
		contract.setContributionModeDes(pro.getContributionModeDes());//出资方式
		contract.setDeptName(pro.getDeptName());//业务部门
		contract.setSurveyer(pro.getSurveyer());//勘察员
		Department dept = departmentService.queryDepartment(pro.getDeptId());
		if(null!=dept){
			contract.setDeptDivide(dept.getDeptDivide());
		}
//		if(null != pro.getCustId()){
//			Customer c = customerService.queryCustomerById(pro.getCustId());
//			contract.setOpenBank(c.getOpenBank());
//			contract.setUnitAddress(c.getUnitAddress());
//			contract.setDutyParagraph(c.getDutyParagraph());
//			contract.setAccount(c.getAccount());
//		}

		//判断当前合同是否已存在已收流水
		//实收款、实付款
		String cashFlowRemark="已收款：";
		List<CashFlow> cashFlows = chargeService.queryCashFlowByProjIdType(pro.getProjId(), ARFlagEnum.RECEIVE_ACCOUNT.getValue(),contract.getConNo());
		if(cashFlows!=null){
			contract.setCashFlows(cashFlows);
			BigDecimal receiveAmount = new BigDecimal(0);
			for (CashFlow cashFlow : cashFlows) {
				receiveAmount = receiveAmount.add(cashFlow.getCfAmount());//实收金额
			}
			cashFlowRemark += receiveAmount +"元";
		}
		//应收款、预付款
		List<AccrualsRecord> accRecords = accrualsRecordService.findbyProjIdType(pro.getProjId(), pro.getProjNo(), ARFlagEnum.RECEIVE_ACCOUNT.getValue(),contract.getConNo());
		if(accRecords!=null){
			contract.setAccRecords(accRecords);
		}
		contract.setCashFlowRemark(cashFlowRemark);
		return contract;
	}
	
	/**
	 * 保存合同
	 * @author fuliwei
	 * @createTime 2016-6-28
	 * @param  contract 合同
	 * @return String 
	 */
	@RequestMapping(value = "/modifyContract")
	@ResponseBody
	public String modifyContract(@RequestBody(required = true) Contract contract){
		try {
			return contractService.modifyContract(contract);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("合同签订区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 联查出资类型选框
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPayType")
	public List<PayType> queryPayType(String id,String corpId){
		try{
			return payTypeService.findByContractType(id,corpId);
		}catch(Exception e){
			logger.error("联查付款方式失败！",e);
			return null;
		}
	}
}
