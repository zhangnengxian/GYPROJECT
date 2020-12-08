package cc.dfsoft.project.biz.base.subpackage.controller;

import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.dto.SubContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.PaymentApply;
import cc.dfsoft.project.biz.base.subpackage.service.PaymentApplyService;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 付款申请
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/paymentApply")
public class PaymentApplyController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(PaymentApplyController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**分包合同服务接口*/
	@Resource
	SubContractService subContractService;
	
	/**付款申请服务接口*/
	@Resource
	PaymentApplyService paymentApplyService;

	@Resource
	BusinessPartnersService businessPartnersService;
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("notThrough",Constants.MODULE_CODE_SUBCONTRACT);
		modelView.addObject("stepId",StepOutWorkflowEnum.PAYMENT_AUDIT.getValue());	
		modelView.setViewName("subcontract/paymentApply");
		return modelView;
	}
	
	/**
	 * 打开右侧详述页面
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());			//放入登录人信息
		modelview.setViewName("subcontract/paymentApplyRight");
		return modelview;
	}
	
	/**
	 * 弹出搜索屏
	 * @author fuliwei
	 * @createTime 2017年8月28日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("subcontract/paymentApplySearchPopPage");
		return modelview;
	}
	
	/**
	 * 分包合同列表
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/querySubContract")
	@ResponseBody
	public Map<String,Object> queryContract(HttpServletRequest request,SubContractQueryReq subContractQueryReq){
		try {
			subContractQueryReq.setSortInfo(request);
			Map<String,Object> map=subContractService.querySubContract(subContractQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("分包合同列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存付款申请
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/savePaymentApply")
	@ResponseBody
	public String savePaymentApply(@RequestBody(required = true) PaymentApply paymentApply){
		try {
			paymentApplyService.savePaymentApply(paymentApply);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("付款申请保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 推送付款申请
	 * @author fuliwei
	 * @createTime 2017年8月17日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/pushPaymentApply")
	@ResponseBody
	public String pushPaymentApply(@RequestBody(required = true) String paId){
		try {
			paymentApplyService.pushPaymentApply(paId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("付款申请保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}




	/**
	 * @MethodDes: 付款申请作废
	 * @author zhangnx
	 * @date 2019/8/8 17:14
	 */

	@RequestMapping(value = "/deletePaymentApply")
	@ResponseBody
	public String deletePaymentApply(@RequestBody(required = true) PaymentApply pa){
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		pa.setDeletePer(loginInfo.getStaffName());
		pa.setDeleteDate(new Date());
		try {
			paymentApplyService.saveUpdate(pa);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("付款申请保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}



	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/viewPaymentApply")
	@ResponseBody
	public PaymentApply viewPaymentApply(@RequestParam(required=true) String projId,String paId) throws ParseException{
		try {
			return paymentApplyService.findById(projId, paId);
		} catch (java.text.ParseException e) {
			logger.error("查询失败！");
		}
		return null;
	}
	
	/**
	 * 付款申请列表
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryPaymentApply")
	@ResponseBody
	public Map<String,Object> queryPaymentApply(HttpServletRequest request,PaymentApplyReq paymentApplyReq){
		try {
			List<String> list = new ArrayList<String>();
			for (AuditResultEnum e:AuditResultEnum.values()) {
				list.add(e.getValue());
			}
			paymentApplyReq.setAuditList(list);
			paymentApplyReq.setSortInfo(request);
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			BusinessPartners businessPartners = businessPartnersService.viewBusinessPartnersById(loginInfo.getDeptId());
			/*if(businessPartners!=null){
				if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(businessPartners.getUnitType())){
					//监理单位监理费申请
					paymentApplyReq.setPayType(PayTypeEnum.SUPERVISOR.getValue());
				}else if(UnitTypeEnum.TEST_UNIT.getValue().equals(businessPartners.getUnitType())){
					//检测单位
					paymentApplyReq.setPayType(PayTypeEnum.DETECTION.getValue());
				}else if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(businessPartners.getUnitType())){
					//施工单位
					paymentApplyReq.setPayType(PayTypeEnum.CONSTRUCTION.getValue());
				}else {
					// TODO: 2017/12/1
				}
			}*/
			Map<String,Object> map=paymentApplyService.queryPaymentApply(paymentApplyReq);
			return map;
		} catch (Exception e) {
			logger.error("付款申请列表查询失败！", e);
			return null;
		}
	}
}
