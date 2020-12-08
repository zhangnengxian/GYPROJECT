package cc.dfsoft.project.biz.base.charge.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.entity.Customer;
import cc.dfsoft.project.biz.base.baseinfo.service.CustomerService;
import cc.dfsoft.project.biz.base.charge.dto.ChargeDto;
import cc.dfsoft.project.biz.base.charge.dto.ChargeReq;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ARPayStatusEnum;
import cc.dfsoft.project.biz.base.charge.enums.ARStatusEnum;
import cc.dfsoft.project.biz.base.charge.enums.BillStateEnum;
import cc.dfsoft.project.biz.base.charge.enums.BillTypeEnum;
import cc.dfsoft.project.biz.base.charge.service.ChargeService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * 收费
 * @author zhangjj
 *
 */
@Controller
@RequestMapping(value="/charge")
public class ChargeController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ChargeController.class);
	/** 收费服务接口 */
	@Resource
	ChargeService chargeService;
	/** 员工服务接口 */
	@Resource
	StaffService staffService;
	/**工程服务接口 */
	@Resource
	ProjectService projectService;
	/**客户服务接口 */
	@Resource
	CustomerService customerService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("charge/chargePage");
		return modelView;
	}
	@RequestMapping(value="/queryMain")
	public ModelAndView queryMain(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("collectionType", CollectionTypeEnum.values());//收付类型
		modelView.setViewName("charge/chargeQueryPage");
		return modelView;
	}
	@RequestMapping(value="/queryViewPage")
	public ModelAndView queryViewPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("charge/chargeQueryRight");
		return modelView;
	}
	/**
	 * 弹出搜索
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/chargeSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projStatus", ProjStatusEnum.getThanValue(ProjStatusEnum.TO_SIGN_CONTRACT.getValue(), ""));
		modelview.addObject("arFlagEnum", ARFlagEnum.values());//收付标志
		modelview.addObject("arStatusEnum",ARStatusEnum.values());//待收、已收
		modelview.addObject("arPayStatusEnum",ARPayStatusEnum.values());//待付、已付
		modelview.addObject("collectionType", CollectionTypeEnum.values());//收付类型
		modelview.setViewName("charge/chargeSearchPopPage");
		return modelview;
	}
	/**
	 * 弹出搜索
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/querySearchPopPage")
	public ModelAndView querySearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projStatus", ProjStatusEnum.getThanValue(ProjStatusEnum.TO_SIGN_CONTRACT.getValue(), ""));
		modelview.addObject("arFlagEnum", ARFlagEnum.values());//收付标志
		modelview.addObject("arStatusEnum",ARStatusEnum.values());//待收、已收
		modelview.addObject("billType",BillTypeEnum.values());//票据类型
		modelview.addObject("billState",BillStateEnum.values());//票据状态
		modelview.setViewName("charge/querySearchPopPage");
		return modelview;
	}
	/**
	 * 收费屏右侧操作页面
	 * @return
	 */
	@RequestMapping(value = "/viewDetail")
	@ResponseBody
	public Object viewDetail(String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//取得登录人信息
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			String account="";
			String openBank="";
			String custId="";
			String dutyParagraph="";
			String unitAddress="";
			String custName="";
			if (loginInfo!=null) {
				map.put("cfOperator",loginInfo.getStaffId());
				map.put("staffName",loginInfo.getStaffName());
			}
			Project project=projectService.queryProjectById(id);
			if(null!=project&&null!=project.getCustId()){
				Customer customer=customerService.queryCustomerById(project.getCustId());
				if(null!=customer){
					account=customer.getAccount();
					openBank=customer.getOpenBank();
					custId=customer.getCustId();
					dutyParagraph=customer.getDutyParagraph();
					unitAddress=customer.getUnitAddress();
					custName=project.getCustName();
				}
			}
			map.put("account",account);
			map.put("openBank",openBank);
			map.put("custId",custId);
			map.put("dutyParagraph",dutyParagraph);
			map.put("unitAddress",unitAddress);
			map.put("custName",custName);
			
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("cfOperator",104);
			map.put("staffName","李方");
		}
	
		return map;
	}

	/**
	 * 收费屏右侧操作页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("colTypeEnum",CollectionTypeEnum.toJson());	
		modelview.setViewName("charge/chargePopPage");
		return modelview;
	}
	/**
	 * 应收应付记录列表查询
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryAccruRecord")
	@ResponseBody
	public Map<String,Object> queryAccruRecord(HttpServletRequest request,ChargeReq chargeReq){
		try {
			chargeReq.setSortInfo(request);			
			//chargeReq.setArAtatus(ARStatusEnum.TO_BE_CHARGE.getValue());
			//chargeReq.setArFlag(ARFlagEnum..getValue());
			chargeReq.setArOver("1");
			Map<String,Object>  map=chargeService.queryAccrualsRecord(chargeReq);
			return map;
		} catch (Exception e) {
			logger.error("应收应付记录列表查询失败！", e);
			return null;
		}
	}
	/**
	 * 应收应付记录列表查询
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryAccrualsRecordNew")
	@ResponseBody
	public Map<String,Object> queryAccrualsRecordNew(HttpServletRequest request,ChargeReq chargeReq){
		try {
			chargeReq.setSortInfo(request);			
			Map<String,Object>  map=chargeService.queryAccrualsRecordNew(chargeReq);
			return map;
		} catch (Exception e) {
			logger.error("应收应付记录列表查询失败！", e);
			return null;
		}
	}
	/**
	 * 实收实付记录列表查询
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryCashFlowNew")
	@ResponseBody
	public Map<String,Object> queryCashFlowNew(HttpServletRequest request,ChargeReq chargeReq){
		try {
			chargeReq.setSortInfo(request);			
			Map<String,Object>  map=chargeService.queryCashFlowNew(chargeReq);
			return map;
		} catch (Exception e) {
			logger.error("实收实付记录列表查询！", e);
			return null;
		}
	}
	/**
	 * 
	 * @param request
	 * @param chargeReq
	 * @return
	 */
	@RequestMapping(value = "/cancleCharge")
	@ResponseBody
	public ResultMessage cancleCharge(@RequestParam(required=true)String arId,@RequestParam(required=true)String cfId){
		ResultMessage resultMessage = new ResultMessage();
		try {
			chargeService.cancleCharge(arId,cfId);
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_SUCCESS);
			return resultMessage;
		} catch (ExpressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("收付款登记失败！");
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("收付款登记失败！");
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
	}
	/**
	 * 应收应付记录列表查询
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryAllAccruRecord")
	@ResponseBody
	public Map<String,Object> queryAllAccruRecord(HttpServletRequest request,ChargeReq chargeReq){
		try {
			chargeReq.setSortInfo(request);			
			Map<String,Object>  map=chargeService.queryAccrualsRecord(chargeReq);
			return map;
		} catch (Exception e) {
			logger.error("应收应付记录列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 收付流水列表查询
	 * @return
	 */
	@RequestMapping(value = "/queryCashFlow")
	@ResponseBody
	public Map<String,Object> queryCashFlow(HttpServletRequest request,ChargeReq chargeReq){
		try {
			chargeReq.setSortInfo(request);
			//chargeReq.setCfFlag(ARFlagEnum.RECEIVE_ACCOUNT.getValue());			
			Map<String, Object> map= chargeService.queryCashFlow(chargeReq);
			return map;
		} catch (Exception e) {
			logger.error("收付流水列表查询！", e);
			return null;
		}
	}

	/**
	 * 增加实收实付流水时，调用财务接口，将系统的实收流水传递给财务
	 * @param accrualsId 应收应付记录Id
	 * @param cashFlow 收付流水对象
	 * @return
	 */



	@RequestMapping(value = "/insertCashFlow")
	@ResponseBody
	public ResultMessage insertCashFlow(@RequestBody(required=true) ChargeDto chargeDto){
		ResultMessage resultMessage = new ResultMessage();
		try {
			String result = Constants.OPERATE_RESULT_SUCCESS;
			if(chargeDto.getCashFlow()!=null){
			   result = chargeService.insertCashFlow(chargeDto.getArId(), chargeDto.getCashFlow(),chargeDto.getOverChange());
			   //首付款未交清标记
			   chargeService.projSign( chargeDto.getCashFlow().getProjId());

			}else{
			   chargeDto.getAccrualsRecord().setArId(chargeDto.getArId());
			   chargeService.updateAccrualsRecord(chargeDto.getAccrualsRecord());
			   //首付款未交清标记
			   chargeService.projSign(chargeDto.getAccrualsRecord().getProjId());
			}
			//补充协议款交清标记
			if (CollectionTypeEnum.SUPPLEMENTAL_CONTRACT.getValue().equals(chargeDto.getAccrualsRecord()!=null?chargeDto.getAccrualsRecord().getArType():"")){//补充协议款
				chargeService.projSignSup(chargeDto.getCashFlow().getProjId());
			}
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(result);
			return resultMessage;
		} catch (ExpressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("收付款登记失败！");
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("收付款登记失败！");
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
	}

	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author zhangjj
	 * @createTime 2016-07-11
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			//工程状态待施工、施工中、待自检、待预验收、待联合验收、待结算报审、报审确认、待结算初审、初审确认、待结算终审、终审确认、资料发送、资料反馈、资料确认
			//String[] projStatus = {ProjStatusEnum.TO_CONSTRUCTION.getValue(),ProjStatusEnum.DURING_CONSTRUCTION.getValue(),ProjStatusEnum.TO_SELF_CHECK.getValue(),ProjStatusEnum.TO_PRE_INSPECTION.getValue(),ProjStatusEnum.UNION_PRE_INSPECTION.getValue(),ProjStatusEnum.SETTLEMENT_REPORT.getValue(),ProjStatusEnum.REPORT_CONFIRM.getValue(),ProjStatusEnum.SETTLEMENT_REPORT_START.getValue(),ProjStatusEnum.REPORT_START_CONFIRM.getValue(),ProjStatusEnum.SETTLEMENT_REPORT_DONE.getValue(),ProjStatusEnum.REPORT_DONE_CONFIRM.getValue(),ProjStatusEnum.COMPLETION_TRANSFER.getValue(),ProjStatusEnum.DATA_FEEDBACK.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue()};
			String[] projStatus =new String[]{};
			//List<ProjStatusEnum> list=ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_SIGN_CONTRACT.getValue(),ProjStatusEnum.ALREADY_COMPLETED.getValue());
			List<ProjStatusEnum> list=ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_DATA_ACQUISITION.getValue(), "");
			List<String> statusList=new ArrayList<String>();
			
			for(ProjStatusEnum pe:list){
				statusList.add(pe.getValue());
			}
			
			
			Iterator<String> it = statusList.iterator();  
			
			while(it.hasNext()){
				String str = it.next();  
				if(/*str.equals(ProjStatusEnum.ALREADY_FINISH.getValue())||*/str.equals(ProjStatusEnum.AUDIT_PROCEDURES.getValue())||
						str.equals(ProjStatusEnum.STATION_CONSTRUCTION.getValue())||str.equals(ProjStatusEnum.GETSTATION_SETTLEMENT.getValue())||
						str.equals(ProjStatusEnum.TERMINATION.getValue())||str.equals(ProjStatusEnum.ALREADY_HANDED_OVER.getValue())){
					it.remove();
				}
			}
			
			projectQueryReq.setProjStuList(statusList);
			
			//String[] projStatus = {ProjStatusEnum.TO_SIGN_CONTRACT.getValue(),ProjStatusEnum.TO_AUDIT_CONTRACT.getValue(),ProjStatusEnum.TO_MAKE_PLAN.getValue(),ProjStatusEnum.TO_PLAN_AUDIT.getValue(),ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue(),ProjStatusEnum.TO_AUDIT_AMOUNT.getValue(),ProjStatusEnum.TO_SIGNED_SUBCONTRACT.getValue(),ProjStatusEnum.TO_CONSTRUCTION.getValue(),ProjStatusEnum.DURING_CONSTRUCTION.getValue(),ProjStatusEnum.TO_SELF_CHECK.getValue(),ProjStatusEnum.AERATE_APPLY.getValue(),ProjStatusEnum.UNION_PRE_INSPECTION.getValue(),ProjStatusEnum.SETTLEMENT_REPORT.getValue(),ProjStatusEnum.REPORT_CONFIRM.getValue(),ProjStatusEnum.SETTLEMENT_REPORT_START.getValue(),ProjStatusEnum.REPORT_START_CONFIRM.getValue(),ProjStatusEnum.SETTLEMENT_REPORT_DONE.getValue(),ProjStatusEnum.REPORT_DONE_CONFIRM.getValue(),ProjStatusEnum.COMPLETION_TRANSFER.getValue(),ProjStatusEnum.DATA_FEEDBACK.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue()};
			Map<String,Object> map = projectService.queryProjectView(projectQueryReq, projStatus);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	@RequestMapping(value = "/queryChargeDetail")
	@ResponseBody
	public Map<String,Object> queryChargeDetail(String id){
		try {
			if(StringUtil.isNotBlank(id)){
				Map<String,Object> map=new HashMap<String,Object>();
				Project pro=projectService.queryProjectById(id);
				map.put("project", pro);
				Customer customer =customerService.queryCustomerById(pro.getCustId());
				map.put("customer", customer);
				return map;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HashMap<String,Object>();
	}
}

