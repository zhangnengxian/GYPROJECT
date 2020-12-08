package cc.dfsoft.project.biz.base.subpackage.controller;

import java.math.BigDecimal;
import java.util.List;
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

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.budget.controller.DrawingSignAndAuditController;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.contract.service.SupplementalContractService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesReq;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;
import cc.dfsoft.project.biz.base.subpackage.enums.CostTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.service.IntelligentMeterContractService;
import cc.dfsoft.project.biz.base.subpackage.service.SubBudgetService;
import cc.dfsoft.project.biz.base.subpackage.service.SubQuantitiesService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.SessionUtils;

/**
 * 工程量审定
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/qualitiesJudgement")
public class QualitiesJudgementController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(DrawingSignAndAuditController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**分包工程量服务接口*/
	@Resource
	SubQuantitiesService subQuantitiesService;
	
	/**施工预算服务接口*/
	@Resource
	SubBudgetService subBudgetService;
	
	@Resource
	ManageRecordService manageRecordService;
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	@Resource
	ContractService contractService;
	@Resource
	SupplementalContractService supplementalContractService;
	@Resource
	IntelligentMeterContractService imContractService;
	@Resource
	AccrualsRecordService accrualsRecordService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("curStepId",StepEnum.QUALITIES_JUDGEMENT.getValue());
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("subcontract/qualitiesAudit");
		return modelView;
	}
	
	/**
	 * 打开审核页面
	 * @return
	 */
	@RequestMapping(value = "/auditPage")
	public ModelAndView viewAuditPage(HttpServletRequest request) {
		ModelAndView modelView = new ModelAndView();
		String projId = request.getParameter("projId");
		//根据工程id查设计信息
		modelView.addObject("projId",projId);
		modelView.addObject("subBudget", subBudgetService.viewSubBudget(projId));
		//查询安装合同
		Contract  contract = contractService.viewContractByprojId(projId);
		modelView.addObject("contract",contract);
		//查询补充协议
		List<SupplementalContract> supCons = supplementalContractService.findByProjId(projId);
		if(supCons!=null && supCons.size()>0){
			BigDecimal totalAmount = new BigDecimal(0);
			for(SupplementalContract supCon :supCons){
				totalAmount = totalAmount.add(supCon.getScAmount());
			}
			modelView.addObject("supConAmount",totalAmount.toString());
		}else{
			modelView.addObject("supConAmount","0");
		}
		//智能表合同款
		IntelligentMeterContract imc = imContractService.findContractByprojId(projId);
		if(imc!=null){
			modelView.addObject("imcAmount",imc.getTotalCost().toString());
		}else{
			modelView.addObject("imcAmount","0");
		}
		
		modelView.addObject("costType",CostTypeEnum.values());
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);											//登录人信息
		modelView.setViewName("subcontract/qualitiesAuditPage");
		return modelView;
	}
	
	/**
	 * 打开右侧页面修改
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("subcontract/qualitiesJudgementRight");
		return modelview;
	}
	
	/**
	 * 工程列表条件查询
	 * @author fuliwei
	 * @param projectQueryReq
	 * @createTime 2016-07-08
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_AUDIT_AMOUNT.getValue());  //待工程量审定
			projectQueryReq.setSortInfo(request);
			Map<String,Object> map=projectService.queryAuditProject(projectQueryReq,"",StepEnum.QUALITIES_JUDGEMENT.getValue());
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 工程量清单列表查询	fuliwei
	 * @createTime 2016-07-08
	 * @param PageSortReq
	 * @return	Map<String, Object>
	 */
	@RequestMapping(value = "/querySubQuantities")
	@ResponseBody
	public Map<String, Object> querySubQuantities(HttpServletRequest request,SubQuantitiesReq subQuantitiesReq) {
		try {
			subQuantitiesReq.setSortInfo(request);
			Map<String, Object> map=subQuantitiesService.queryQuantityStandard(subQuantitiesReq);
		    return map;
		} catch (Exception e) {
			logger.error("分包单位工程量清单查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 
	 * @author fuliwei
	 * @createTime 2016-07-08
	 * @param manageRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepEnum.QUALITIES_JUDGEMENT.getValue());
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 工程量审定保存
	 * @author	fuliwei
	 * @createTime	2016-07-08
	 * @param	 manageRecord
	 * @return	String
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		try {
			//先判断当前级别是否已审核，已审核则不能再次审核
        	ManageRecord manageRecordHistory =  manageRecordService.queryManRdHistory(manageRecord.getProjId(),manageRecord.getBusinessOrderId(),StepEnum.QUALITIES_JUDGEMENT.getValue(),manageRecord.getMrAuditLevel(),"0");
        	if(manageRecordHistory != null){
        		return "exist";
        	}
			subBudgetService.auditSave(manageRecord,"","",WorkFlowActionEnum.QUALITIES_JUDGEMENT.getActionCode(),Constants.MODULE_CODE_SUBCONTRACT);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("工程量审定保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 弹出搜索屏
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("subcontract/quantityJudgementSearchPopPage");
		return modelview;
	}
	
}
