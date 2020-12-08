package cc.dfsoft.project.biz.base.subpackage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.plan.enums.IsDispatchEnum;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.entity.PaymentApply;
import cc.dfsoft.project.biz.base.subpackage.enums.FeeTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.service.PaymentApplyService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import cc.dfsoft.uexpress.common.constant.Constants;


@Controller
@RequestMapping(value="/costApplyDispatch")
public class CostApplyDispatchController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(CostApplyDispatchController.class);
	
	/**付款申请*/
	@Resource
	PaymentApplyService paymentApplyService;
	
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
	@Resource
	StaffService staffService;
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年12月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("preSettlementGroup",DeptDivideEnum.PRE_SETTLEMENT_GROUP.getValue());			//预结算
		modelView.addObject("customerServiceCenter", DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());	//客服中心
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo", loginInfo);
		modelView.setViewName("subcontract/costApplyDispatch");
		return modelView;
	}
	
	
	/**
	 * 打开右屏
	 * @author fuliwei
	 * @createTime 2017年12月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("deptType",DeptTypeEnum.GROUP_COMPANY.getValue());
		modelView.addObject("feeType",FeeTypeEnum.values());			//费用类型
		modelView.setViewName("subcontract/costApplyDispatchRight");
		return modelView;
	}
	
	
	/**
	 * 弹出搜索
	 * @author fuliwei
	 * @createTime 2017年12月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("isDispatch", IsDispatchEnum.values());
		modelview.addObject("feeType",FeeTypeEnum.values());			//费用类型
		modelview.setViewName("subcontract/costApplyAuditSearchPopPage");
		return modelview;
	}
	
	
	/**
	 * 查询待派工列表
	 * @author fuliwei
	 * @createTime 2017年12月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryPaymentApply")
	@ResponseBody
	public Map<String,Object> queryPaymentApply(HttpServletRequest request, PaymentApplyReq req){
		try {
			req.setSortInfo(request);
			if(StringUtils.isBlank(req.getIsDispatch())){
				req.setIsDispatch(IsDispatchEnum.NOT_DISPATCH.getValue());//默认加载未派遣的
			}
			req.setAuditState(AuditResultEnum.APPLYING.getValue());		//审核中
			Map<String,Object> map= paymentApplyService.queryPaymentApply(req);
			return map;
		} catch (Exception e) {
			logger.error("费用申请信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年12月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewPaymentApply")
	@ResponseBody
	public PaymentApply viewPaymentApply(HttpServletRequest request,@RequestParam(required=true) String id){
		PaymentApply pa=paymentApplyService.findById(id);
		
		Department dept=departmentService.queryDepartment(pa.getOrgId());
		if(dept!=null){
			pa.setDevide(dept.getDeptDivide());
		}	
		
		
		return pa;
	}
	
	
	/**
	 * 派工保存
	 * @author fuliwei
	 * @createTime 2017年12月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updatePaymentApply")
	@ResponseBody
	public String saveSurveyInfo(@RequestBody(required = true) DesignDispatchDto designDispatchReq){
		try {
			paymentApplyService.saveCostPaymentDispatch(designDispatchReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("派工区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 加载预算员列表
	 * @author fuliwei
	 * @createTime 2017年12月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryAuditer")
	@ResponseBody
	public Map<String,Object> queryAuditer(HttpServletRequest request, DesignerQueryReq designerQueryReq){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			designerQueryReq.setSortInfo(request);
			designerQueryReq.setDeptId(departmentService.queryDepartmentByDivide(designerQueryReq.getDeptDivide(),loginInfo.getDeptId()).getDeptId());
			designerQueryReq.setCorpId(loginInfo.getCorpId());								//所属公司
			if(loginInfo.getCorpId().equals("1120")){
				designerQueryReq.setPostType(PostTypeEnum.BUILDER.getValue());  			//现场代表
			}else{
				designerQueryReq.setPostType(PostTypeEnum.BUDGET_MEMBER.getValue());  			//预结算员
			}
			designerQueryReq.setProjStatus(AuditResultEnum.APPLYING.getValue());			//审核中的
			result= paymentApplyService.queryAuditer(designerQueryReq);
			return result;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
}
