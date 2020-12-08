package cc.dfsoft.project.biz.base.settlement.controller;

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
import cc.dfsoft.project.biz.base.settlement.dto.TurnFixedApplyReq;
import cc.dfsoft.project.biz.base.settlement.service.TurnFixedApplyService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.ExceptionUtil;


@Controller
@RequestMapping(value="/turnFixedPrint")
public class TurnFixedPrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(TurnFixedPrintController.class);
	
	/**转固申请服务接口*/
	@Resource
	TurnFixedApplyService turnFixedApplyService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);
		modelView.setViewName("settlement/turnFixedPrint");
		return modelView;
	}
	
	/**
	 * 列表条件查询
	 * @param request
	 * @param turnFixedApplyReq
	 * @return
	 */
	@RequestMapping(value = "/queryTurnFixedApply")
	@ResponseBody
	public Map<String,Object> queryTurnFixedApply(HttpServletRequest request,TurnFixedApplyReq turnFixedApplyReq){
		try {
			turnFixedApplyReq.setSortInfo(request);
			Map<String,Object> map = turnFixedApplyService.queryTurnFixedApplyPrint(turnFixedApplyReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", ExceptionUtil.getMessage(e));
			return null;
		}
	}
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/turnFixedSearchPop")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		//modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelview.setViewName("settlement/turnFixedSearchPop");
		return modelview;
	}
	/**
	 * 标记打印
	 * @param projId
	 * @return
	 */
	@RequestMapping(value = "/signTurnFixedPrint")
	@ResponseBody
	public String signTurnFixedPrint(@RequestBody(required = true) String projId){
		try {
			turnFixedApplyService.signTurnFixedPrint(projId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("打印合同标记失败！", ExceptionUtil.getMessage(e));
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
