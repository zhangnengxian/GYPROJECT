package cc.dfsoft.project.biz.base.settlement.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.enums.SubQuantitiesStatusEnum;
import cc.dfsoft.project.biz.base.settlement.service.SettlementAuditDoneService;
import cc.dfsoft.project.biz.base.settlement.service.SettlementAuditService;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesDto;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesReq;
import cc.dfsoft.project.biz.base.subpackage.service.SubQuantitiesService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 结算终审
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/settlementAuditDone")
public class SettlementAuditDoneController {
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**结算报审服务接口*/
	@Resource
	SettlementAuditService settlementAuditStartService;
	
	/**结算终审服务接口*/
	@Resource
	SettlementAuditDoneService settlementAuditDoneService;
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(SettlementAuditDoneController.class);
	
	/** 分包工程量服务接口*/
	@Resource
	SubQuantitiesService subQuantitiesService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("settlement/settlementAuditDone");
		return modelView;
	}
	
	/**
	 * 打开右侧结算终审
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("MANAGER",PostTypeEnum.MANAGER.getValue());//造价合同处结算员
		modelview.addObject("SETTLEMENT_CLERK",PostTypeEnum.BUDGET_MEMBER.getValue());//造价合同处结算员
		modelview.addObject("SUB_BUDGETER",PostTypeEnum.SUB_BUDGETER.getValue());//分包预算员
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("settlement/settlementAuditDoneRight");
		return modelview;
	}
	
	/**
	 * 工程列表
	 */
	@RequestMapping(value = "/queryAuditDone")
	@ResponseBody
	public Map<String,Object> queryAuditDone(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			//projectQueryReq.setProjStatusId(ProjStatusEnum.SETTLEMENT_REPORT_DONE.getValue());				  //待预算初审
			projectQueryReq.setSortInfo(request);
			return projectService.queryProject(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/projectSearchDonePopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("settlement/projectSearchDonePopPage");
		return modelview;
	}
	/**
	 * 结算终审详述
	 * @return
	 */
	@RequestMapping(value = "/auditDoneDetail")
	@ResponseBody
	public SettlementDeclaration auditDoneDetail(@RequestParam(required=true) String id){
		try {
			return settlementAuditDoneService.auditStartDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 分包工程量条件查询(弃用)
	 * @param subQuantitiesReq
	 * @return
	 */
	@RequestMapping(value = "/queryQuantityStandard")
	@ResponseBody
	public Map<String, Object> queryQuantityStandard(SubQuantitiesReq subQuantitiesReq){
		//subQuantitiesReq.setAuditStatus(AuditStatusEnum.DONE_FIRST.getValue());
		try {
			subQuantitiesReq.setSqStatus(SubQuantitiesStatusEnum.SETTLEMENT.getValue());
			return subQuantitiesService.querySubQuantityStandard(subQuantitiesReq);
		} catch (Exception e) {
			logger.error("分步分项名称查询失败！", e);
			return null;
		}
	}
	/**
	 * 保存更新结算审核
	 * @author cui
	 * @createTime 2016-08-17
	 * @param  
	 * @return 
	 */
	@RequestMapping(value = "/saveAuditDone")
	@ResponseBody
	public String saveAuditDone(@RequestBody(required = true) SettlementDeclaration settlementDeclaration){
		try {
			settlementAuditStartService.saveAuditDone(settlementDeclaration);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("结算终审信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 保存终审工程量(弃用)
	 * @author cui
	 */
	
	@RequestMapping(value = "/insertSubQualities")
	@ResponseBody
	public String insertSubQualities(@RequestBody SubQuantitiesDto qdto){
		
		try {
			settlementAuditDoneService.batInsertSubQualities(qdto);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
}
