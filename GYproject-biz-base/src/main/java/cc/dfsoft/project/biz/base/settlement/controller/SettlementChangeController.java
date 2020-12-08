package cc.dfsoft.project.biz.base.settlement.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.budget.dto.BudgetChangeReq;
import cc.dfsoft.project.biz.base.budget.service.BudgetAdjustService;
import cc.dfsoft.project.biz.base.change.dto.MaterialChangeReq;
import cc.dfsoft.project.biz.base.change.service.MaterialChangeService;
import cc.dfsoft.project.biz.base.contract.dto.SupplementalContractQueryReq;
import cc.dfsoft.project.biz.base.contract.service.SupplementalContractService;


@Controller
@RequestMapping(value="/settlementChange")
public class SettlementChangeController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SettlementChangeController.class);
	
	/**预算调整服务接口*/
	@Resource
	BudgetAdjustService budgetAdjustService;
	
	/**补充协议服务接口*/
	@Resource
	SupplementalContractService supplementalContractService;
	
	/**材料清单服务接口*/
	@Resource
	MaterialChangeService materialChangeService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("settlement/settlementChange");
		return modelView;
	}
	
	/**
	 * 预算总表查询
	 * @param request
	 * @param budgetChangeReq
	 * @return
	 */
	@RequestMapping(value = "/queryBudgetChange")
	@ResponseBody
	public Map<String,Object> queryBudgetChange(HttpServletRequest request,BudgetChangeReq budgetChangeReq){
		try {
			budgetChangeReq.setSortInfo(request);
			budgetChangeReq.setBudgetTypeId("1");
			return budgetAdjustService.queryBudgetAdjust(budgetChangeReq);
		} catch (Exception e) {
			logger.error("变更信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 补充协议列表查询
	 * @param request
	 * @param projId
	 * @param supplementalContractQueryReq
	 * @return
	 */
	@RequestMapping(value = "/querySupplementContract")
	@ResponseBody
	public Map<String,Object> queryDesignAlteration(HttpServletRequest request,SupplementalContractQueryReq supplementalContractQueryReq){
		try {
			supplementalContractQueryReq.setSortInfo(request);
			return supplementalContractService.querySupplementalContract(supplementalContractQueryReq);
		} catch (Exception e) {
			logger.error("变更信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 材料变更列表
	 * @param request
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryMaterialChange")
	@ResponseBody
	public Map<String,Object> queryMaterialList(HttpServletRequest request,MaterialChangeReq materialChangeReq){
		try {
			materialChangeReq.setSortInfo(request);
			return materialChangeService.queryMaterialChange(materialChangeReq);
		} catch (Exception e) {
			logger.error("签证信息查询失败！", e);
			return null;
		}
	}
}
