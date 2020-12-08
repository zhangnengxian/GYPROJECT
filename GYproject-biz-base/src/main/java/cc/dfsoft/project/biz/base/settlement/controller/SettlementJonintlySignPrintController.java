package cc.dfsoft.project.biz.base.settlement.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.settlement.dto.SettlementDeclarationReq;
import cc.dfsoft.project.biz.base.settlement.service.SettlementJonintlySignService;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 汇签单打印
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/settlementJonintlySignPrint")
public class SettlementJonintlySignPrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SettlementJonintlySignController.class);
	
	/**结算汇签*/
	@Resource
	SettlementJonintlySignService settlementJonintlySignService;
	
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.setViewName("settlement/settlementJonintlySignPrint");
		return modelAndView;
	}
	
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/acceptanceSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelview.setViewName("settlement/settlementJonintlySignPrintSearchPop");
		return modelview;
	}
	
	/**
	 * 汇签单打印
	 * @author fuliwei
	 * @createTime 2017年9月27日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/querySettlementJonintlySign")
	@ResponseBody
	public Map<String,Object> querySettlementJonintlySign(HttpServletRequest request,SettlementDeclarationReq req){
		try {
			req.setSortInfo(request);
			if(StringUtils.isBlank(req.getIsPrint())){
				req.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());
			}
			return settlementJonintlySignService.querySettlementJonintlySign(req);
		} catch (Exception e) {
			logger.error("汇签单打印列表查询失败！",e);
			return null;
		}
	}
	
	
	/**
	 * 验收单打印标记
	 * @author fuliwei
	 * @createTime 2017年8月14日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/signDivisionalAcceptancePrint")
	@ResponseBody
	public String signDivisionalAcceptancePrint(@RequestBody(required = true) String sjsId){
		try {
			settlementJonintlySignService.signSettlementJonintly(sjsId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("打印合同标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
}
