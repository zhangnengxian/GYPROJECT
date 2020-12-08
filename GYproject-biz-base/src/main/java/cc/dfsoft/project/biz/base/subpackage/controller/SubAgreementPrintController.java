package cc.dfsoft.project.biz.base.subpackage.controller;

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

import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dto.SubSupplyContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.service.SubSupplyContractService;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 分包补充协议打印
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/subAgreementPrint")
public class SubAgreementPrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SubAgreementPrintController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**分包补充协议服务接口*/
	@Resource
	SubSupplyContractService subSupplyContractService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		modelView.setViewName("subcontract/subAgreementPrint");
		return modelView;
	}
	/**
	 * 补充协议列表查询
	 * @param request
	 * @param subContractQueryReq
	 * @return
	 */
	@RequestMapping(value = "/querySubSupplyContract")
	@ResponseBody
	public Map<String,Object> querySubSupplyContract(HttpServletRequest request,SubSupplyContractQueryReq subSupplyContractQueryReq){
		try {
			subSupplyContractQueryReq.setSortInfo(request);
			Map<String,Object> map=subSupplyContractService.querySubSupplyContract(subSupplyContractQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("分包合同信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/saSearchPopPage")
	public ModelAndView subContractSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
//		modelview.addObject("payType", PayTypeEnum.values());//付款方式
		modelview.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelview.setViewName("subcontract/subAgreementSearchPopPage");
		return modelview;
	}

	/**
	 * 协议打印标记
	 * @author cui
	 * @createTime 2017-2-15
	 * @param  String projId
	 * @return String 
	 */
	@RequestMapping(value = "/signSubAgreementPrint")
	@ResponseBody
	public String signSubAgreementPrint(@RequestBody(required = true) String projId){
		try {
			subSupplyContractService.signSubAgreementPrint(projId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("打印合同标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
