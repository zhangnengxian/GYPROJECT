package cc.dfsoft.project.biz.base.charge.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.charge.dto.ChargeReq;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ARPayStatusEnum;
import cc.dfsoft.project.biz.base.charge.service.ChargeService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;

/**
 * 付款登记
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/payAmount")
public class PayAmountController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(PayAmountController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**收付接口接口*/
	@Resource
	ChargeService chargeService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("charge/payAmountPage");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value="/queryViewPage")
	public ModelAndView queryViewPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("charge/payAmountRightPage");
		return modelView;
	}
	
	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author fuliwei
	 * @createTime 2017-01-11
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			//付款查询
			projectQueryReq.setArFlag(ARFlagEnum.ACCOUNTS_PAY.getValue());
			//String[] projStatus = {ProjStatusEnum.TO_SIGN_CONTRACT.getValue(),ProjStatusEnum.TO_AUDIT_CONTRACT.getValue(),ProjStatusEnum.TO_MAKE_PLAN.getValue(),ProjStatusEnum.TO_PLAN_AUDIT.getValue(),ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue(),ProjStatusEnum.TO_AUDIT_AMOUNT.getValue(),ProjStatusEnum.TO_SIGNED_SUBCONTRACT.getValue(),ProjStatusEnum.TO_CONSTRUCTION.getValue(),ProjStatusEnum.DURING_CONSTRUCTION.getValue(),ProjStatusEnum.TO_SELF_CHECK.getValue(),ProjStatusEnum.TO_PRE_INSPECTION.getValue(),ProjStatusEnum.AERATE_APPLY.getValue(),ProjStatusEnum.UNION_PRE_INSPECTION.getValue(),ProjStatusEnum.SETTLEMENT_REPORT.getValue(),ProjStatusEnum.REPORT_CONFIRM.getValue(),ProjStatusEnum.SETTLEMENT_REPORT_START.getValue(),ProjStatusEnum.REPORT_START_CONFIRM.getValue(),ProjStatusEnum.SETTLEMENT_REPORT_DONE.getValue(),ProjStatusEnum.REPORT_DONE_CONFIRM.getValue(),ProjStatusEnum.COMPLETION_TRANSFER.getValue(),ProjStatusEnum.DATA_FEEDBACK.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue()};
			
			
			String[] projStatus =new String[]{};
			List<ProjStatusEnum> list=ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_MAKE_PLAN.getValue(), ProjStatusEnum.ALREADY_COMPLETED.getValue());
			List<String> statusList=new ArrayList<String>();
			
			for(ProjStatusEnum pe:list){
				statusList.add(pe.getValue());
			}
			projectQueryReq.setProjStuList(statusList);
			
			
			Map<String,Object> map = projectService.queryProjectView(projectQueryReq, projStatus);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 应付记录列表查询
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryAccruRecord")
	@ResponseBody
	public Map<String,Object> queryAccruRecord(HttpServletRequest request,ChargeReq chargeReq){
		try {
			chargeReq.setSortInfo(request);
			//应付流水
			chargeReq.setArFlag(ARFlagEnum.ACCOUNTS_PAY.getValue());
			chargeReq.setArOver("1");
			Map<String,Object>  map=chargeService.queryAccrualsRecord(chargeReq);
			return map;
		} catch (Exception e) {
			logger.error("应付记录列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 实付记录列表查询
	 * @return
	 */
	@RequestMapping(value = "/queryCashFlow")
	@ResponseBody
	public Map<String,Object> queryCashFlow(HttpServletRequest request,ChargeReq chargeReq){
		try {
			chargeReq.setSortInfo(request);
			chargeReq.setCfFlag(ARFlagEnum.ACCOUNTS_PAY.getValue());;			
			Map<String, Object> map= chargeService.queryCashFlow(chargeReq);
			return map;
		} catch (Exception e) {
			logger.error("实付流水列表查询！", e);
			return null;
		}
	}
	
	/**
	 * 弹出搜索
	 * @author fulw
	 * @createTime 2017-1-11
	 * @param 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/chargeSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projStatus", ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_CONSTRUCTION.getValue(), ""));
		modelview.addObject("arStatusEnum",ARPayStatusEnum.values());//待付、已付
		modelview.addObject("collectionType", CollectionTypeEnum.getObjByType("3"));//收款类型
		modelview.setViewName("charge/payAmountPopPage");
		return modelview;
	}
	
}
