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

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.dto.SettlementDeclarationReq;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.enums.SubQuantitiesStatusEnum;
import cc.dfsoft.project.biz.base.settlement.service.SettlementAuditService;
import cc.dfsoft.project.biz.base.settlement.service.SettlementDeclarationService;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesDto;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesReq;
import cc.dfsoft.project.biz.base.subpackage.service.SubQuantitiesService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.SessionUtils;

/**
 * 结算初审
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/settlementAuditStart")
public class SettlementAuditStartController {
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**结算报审服务接口*/
	@Resource
	SettlementAuditService settlementAuditStartService;
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(SettlementAuditStartController.class);
	
	/** 分包工程量服务接口*/
	@Resource
	SubQuantitiesService subQuantitiesService;
	
	@Resource
	DocTypeService docTypeService;
	@Resource
	ManageRecordService manageRecordService;
	@Resource
	SettlementDeclarationService  settlementDeclarationService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("notThrough",Constants.MODULE_CODE_SETTLEMENT);
		//modelView.addObject("stepId",StepEnum.REPORT_START_CONFIRM.getValue());
		modelView.addObject("curStepId",StepEnum.SETTLEMENT_REPORT_START.getValue());
//	    modelView.setViewName("settlement/settlementAuditStart");
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("settlement/startConfirm");
		return modelView;
	}
	
	/**
	 * 打开右侧结算初审
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("SETTLEMENT_CLERK",PostTypeEnum.BUDGET_MEMBER.getValue());//造价合同处结算员
		modelview.addObject("SUB_BUDGETER",PostTypeEnum.SUB_BUDGETER.getValue());//分包预算员
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("settlement/settlementAuditStartRight");
		return modelview;
	}
	/**
	 * 进入初审审核页面
	 * @return
	 */
	@RequestMapping(value = "/auditPage")
	public ModelAndView auditPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("SETTLEMENT_CLERK",PostTypeEnum.BUDGET_MEMBER.getValue());//造价合同处结算员
		modelview.addObject("SUB_BUDGETER",PostTypeEnum.SUB_BUDGETER.getValue());//分包预算员
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("settlement/startConfirmAuditPage");
		return modelview;
	}
	
	/**
	 * 工程列表-废弃
	 */
	@RequestMapping(value = "/queryAuditStart")
	@ResponseBody
	public Map<String,Object> queryAuditStart(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.SETTLEMENT_REPORT_START.getValue());				  //待预算初审
			projectQueryReq.setSortInfo(request);
			//projectQueryReq.setSideBarID("110808");
			//projectQueryReq.setStepId(StepEnum.SETTLEMENT_REPORT_START.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 待结算初审工程列表
	 */
	@RequestMapping(value = "/queryAuditStarts")
	@ResponseBody
	public Map<String,Object> queryAuditStarts(HttpServletRequest request,SettlementDeclarationReq req){
		try {
			req.setProjStatusId(ProjStatusEnum.SETTLEMENT_REPORT_START.getValue());				  //待结算初审
			req.setStepId(StepEnum.SETTLEMENT_REPORT_START.getValue());
			req.setSortInfo(request);
			return settlementDeclarationService.querySettlementAuditStart(req);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 弹出搜索
	 * @author cui
	 * @createTime 2016-8-19
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("settlement/projectSearchPopPage");
		return modelview;
	}
	
	/**
	 * 结算初审详述
	 * @return
	 */
	@RequestMapping(value = "/auditStartDetail")
	@ResponseBody
	public SettlementDeclaration auditStartDetail(@RequestParam(required=true) String id){
		try {
			return settlementAuditStartService.auditStartDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new SettlementDeclaration();
		}
	}
	/**
	 * 分包工程量条件查询(放弃)
	 * @param subQuantitiesReq
	 * @return
	 */
	@RequestMapping(value = "/queryQuantityStandard")
	@ResponseBody
	public Map<String, Object> queryQuantityStandard(SubQuantitiesReq subQuantitiesReq){
		//subQuantitiesReq.setAuditStatus(AuditStatusEnum.START.getValue());
		try {
			subQuantitiesReq.setSqStatus(SubQuantitiesStatusEnum.SETTLEMENT.getValue());
			return subQuantitiesService.querySubQuantityStandard(subQuantitiesReq);
		} catch (Exception e) {
			logger.error("分步分项名称查询失败！", e);
			return null;
		}
	}
	/**
	 * 保存结算初审
	 * @author cui
	 * @createTime 2016-08-17
	 * @param  
	 * @return 
	 */
	@RequestMapping(value = "/saveAuditStart")
	@ResponseBody
	public String saveAuditStart(@RequestBody(required = true) SettlementDeclaration settlementDeclaration){
		try {
			settlementAuditStartService.saveAuditStart(settlementDeclaration);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("结算初审信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 保存初审工程量(放弃)
	 * @author cui
	 */
	
	@RequestMapping(value = "/insertSubQualities")
	@ResponseBody
	public String insertSubQualities(@RequestBody SubQuantitiesDto qdto){
		
		try {
			settlementAuditStartService.batInsertSubQualities(qdto);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	
	
	/**
	 * 工程量标准查询弹框
	 * @return
	 */
	@RequestMapping(value="/standardPop")
	public ModelAndView securityTopPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("settlement/auditStartQuantityStandardPop");
		return modelView;
	}
	/**
	 * 结算初审审核保存
	 * @param qdto
	 * @return
	 */
	@RequestMapping(value="startAuditSave")
	@ResponseBody
	public String firstAuditSave(@RequestBody(required = true) ManageRecord manageRecord){
		try {
			//先判断当前级别是否已审核，已审核则不能再次审核
        	ManageRecord manageRecordHistory =  manageRecordService.queryManRdHistory(manageRecord.getProjId(),manageRecord.getBusinessOrderId(),StepEnum.SETTLEMENT_REPORT_START.getValue(),manageRecord.getMrAuditLevel(),"0");
        	if(manageRecordHistory != null){
        		return "exist";
        	}
			manageRecordService.auditSettlementStartSave(manageRecord,"","",StepEnum.SETTLEMENT_REPORT_START.getValue(),Constants.MODULE_CODE_SETTLEMENT);
			
			return Constants.OPERATE_RESULT_SUCCESS;
			
		} catch (Exception e) {
			logger.error("结算初审保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
