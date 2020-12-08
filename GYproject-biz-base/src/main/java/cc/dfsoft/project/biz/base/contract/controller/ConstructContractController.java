package cc.dfsoft.project.biz.base.contract.controller;

import cc.dfsoft.project.biz.base.baseinfo.service.CustomerService;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.charge.service.ChargeService;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.entity.PayType;
import cc.dfsoft.project.biz.base.contract.enums.BudgetRuleEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractMethodEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.contract.service.PayTypeService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ScaleDetailQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ScaleDetailService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 施工合同
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/constructContract")
public class ConstructContractController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ConstructContractController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	@Resource
	ContractService contractService;
	
	@Resource
	AccrualsRecordService accrualsRecordService;
	
	@Resource
	CustomerService customerService;
	
	@Resource
	PayTypeService payTypeService;

	@Resource
	ProjectDao projectDao;
	

	@Resource
	DepartmentService departmentService;
	
	@Resource
	IncrementDao incrementDao;
	
	@Resource
	ChargeService chargeService;
	
	@Resource
	ScaleDetailService scaleDetailService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.addObject("notThrough",Constants.MODULE_CODE_CONTRACT);
		modelView.addObject("stepId",StepEnum.CONTRACT_AUDIT.getValue());
		modelView.addObject("curStepId",StepEnum.CONSTRUCT_CONTRACT.getValue());
		modelView.setViewName("contract/constructContract");
		return modelView;
	}
	
	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String projId,String projectType,String corpId,String menuId) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		//key规则为：工程类型 + 分公司ID + 菜单ID
		String TCMkey=(StringUtil.isBlank(projectType)?ProjLtypeEnum.CIVILIAN.getValue():projectType)+"_"+(StringUtil.isBlank(corpId)?loginInfo.getCorpId():corpId)+"_"+menuId;
		//key规则为：分公司ID + 菜单ID
		String CMkey=(StringUtil.isBlank(corpId)?loginInfo.getCorpId():corpId)+"_"+menuId;

		Object resultPage = Constants.getSysConfigByKey(TCMkey);
		if (resultPage==null){
			resultPage=Constants.getSysConfigByKey(CMkey);
		}

		String defalutPage = "constructContractRight";
		String resultViewUrl=resultPage!=null?resultPage.toString():defalutPage;

		ModelAndView modelview = new ModelAndView();
		modelview.addObject("customerServiceSenter", DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());//客服中心
		modelview.addObject("modificationGroup", DeptDivideEnum.MODIFICATION_GROUP.getValue());//改管组
		modelview.addObject("payType", payTypeService.findByContractType(null,StringUtil.isBlank(corpId)?loginInfo.getCorpId():corpId));//付款方式
		modelview.addObject("contractMethod",ContractMethodEnum.values());//承包方式
		modelview.addObject("contractType",ContractTypeEnum.values());//合同类型
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());//放入登录人信息
		modelview.addObject("sysDate",projectDao.getDatabaseDate());//系统时间
		modelview.addObject("increment",incrementDao.queryAll());//税率
		modelview.addObject("differenceId",Constants.PUBLIC_SIGN);//合同签订标识
		modelview.setViewName("contract/"+resultViewUrl);
		//民用户工程获取每户单价
		String unitCostStr=(StringUtil.isBlank(corpId)?loginInfo.getCorpId():corpId)+"_"+menuId;
		modelview.addObject("unitCost",Constants.getSysConfigByKey(unitCostStr));//民用合同单价
		modelview.addObject("budgetRuleEnum",BudgetRuleEnum.values());//预算制度
		return modelview;
	}
	
	/**
	 * 弹出搜索屏
	 * @author fuliwei
	 * @createTime 2016-6-28
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/constractSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("contract/constractSearchPopPage");
		return modelview;
	}
	
	/**
	 * 工程列表条件查询
	 * @author fuliwei
	 * @createTime
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_SIGN_CONTRACT.getValue());//待签合同
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.CONSTRUCT_CONTRACT.getValue());
			Map<String,Object> map=projectService.queryProjectByTime(projectQueryReq);
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
	public Contract viewContract(String id){
		Project pro=projectService.viewProject(id);
		Contract contract = contractService.findByProjId(id);
		contract.setProjectTypeDes(pro.getProjectTypeDes());//工程类型
		contract.setContributionModeDes(pro.getContributionModeDes());//出资方式
		contract.setDeptName(pro.getDeptName());//业务部门
		contract.setSurveyer(pro.getSurveyer());//勘察员
		contract.setProjectType(pro.getProjectType());  //工程类型
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
		List<CashFlow> cashFlows = chargeService.queryCashFlowByProjIdType(pro.getProjId(),ARFlagEnum.RECEIVE_ACCOUNT.getValue(),contract.getConNo());
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
	@RequestMapping(value = "/saveContract")
	@ResponseBody
	public String saveContract(@RequestBody(required = true) Contract contract){
			contract.setSignDate(new Date());//保存合同时签订时间为当前时间
		try {
			return contractService.saveContract(contract);
		} catch (Exception e) {
			logger.error("合同签订区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 弹出收付流水屏
	 * @author pengtt
	 * @createTime 2016-8-11
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/accrualsRecordPopPage")
	public ModelAndView accrualsRecordPopPage(String arId) {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("artype", CollectionTypeEnum.values()); //款项类型
		modelview.addObject("arflag", ARFlagEnum.values());  		//收付标志
		modelview.addObject("accrualsRecord",accrualsRecordService.get(arId));
		modelview.setViewName("contract/constractAccrualsRecord");
		return modelview;
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
/**
	 * 查询工程规模详细
	 * wang.hui.jun
	 * 
	 */
	@RequestMapping(value = "/queryScaleDetail")
	@ResponseBody
	public Map<String, Object> queryScaleDetail(HttpServletRequest request, ScaleDetailQueryReq scaleDetailQueryReq){
		try{
			scaleDetailQueryReq.setSortInfo(request);
			return scaleDetailService.queryScaleDetail(scaleDetailQueryReq);
		}catch (Exception e){
			logger.error("工程规模明细查询失败！",e);
			return null;
		}
}}
