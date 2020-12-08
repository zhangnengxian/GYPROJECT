package cc.dfsoft.project.biz.base.subpackage.controller;

import cc.dfsoft.project.biz.base.contract.controller.ContractAuditController;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.service.PaymentApplyService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 申请打印
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/applyPrintController")
public class ApplyPrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ContractAuditController.class);
	
	@Resource
	PaymentApplyService paymentApplyService;
	@Resource
	ProjectService projectService;

	/**
	 * 打开主页面
	 * @return
	 *
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);
		modelView.setViewName("subcontract/applyPrint");
		return modelView;
	}

	//查询列表付款申请列表
	@RequestMapping(value = "/getpaymentpplyList")
	@ResponseBody
	public Map<String,Object> getpaymentpplyList(HttpServletRequest request,PaymentApplyReq paymentApplyReq){

		try {
			paymentApplyReq.setSortInfo(request);
			paymentApplyReq.setAuditState("3");//申请通过的状态
			Map<String,Object> map=paymentApplyService.getpaymentpplyMap(paymentApplyReq);
			return map;
		} catch (Exception e) {
			logger.error("付款申请息查询失败！", e);
			return null;
		}
	}
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/subContractSearchPopPage")
	public ModelAndView subContractSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("subcontract/subContractPrintSearchPopPage");
		return modelview;
	}


	/**
	  * @methodDesc: 查询cpt
	  * @author: zhangnx
	  * @date 10:00 2018/9/28
	  */
	@RequestMapping(value = "/queryCptUrl")
	@ResponseBody
	public String queryCptUrl(String projId,String menuId){
		LoginInfo loginInfo= SessionUtil.getLoginInfo();
		Project project=projectService.queryProjectById(projId);

		StringBuilder key=new StringBuilder();//key值为:分公司ID+菜单ID
		key.append(project!=null?project.getCorpId():loginInfo.getCorpId()).append("_").append(menuId);
		Object obj = Constants.getSysConfigByKey(key.toString());

		String cptURL="gy_CapitalExpenditureApplication.cpt";
		return obj!=null?obj.toString():cptURL;

	}


}
