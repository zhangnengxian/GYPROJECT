package cc.dfsoft.project.biz.base.settlement.controller;

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

import cc.dfsoft.project.biz.base.budget.service.ChangeManagementService;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.project.biz.base.constructmanage.entity.EngineeringVisa;
import cc.dfsoft.project.biz.base.constructmanage.enums.ChangeTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.EngineeringVisaService;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.service.SupplementalContractService;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.service.SettlementAuditService;
import cc.dfsoft.project.biz.base.settlement.service.SettlementDeclarationService;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesDto;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesReq;
import cc.dfsoft.project.biz.base.subpackage.service.SubQuantitiesService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 结算报审
 * @author ht.hu
 *
 */
@Controller
@RequestMapping(value="/settlementDeclaration")
public class SettlementDeclarationController {
	
	/** 日志实例*/
	private static Logger logger = LoggerFactory.getLogger(SettlementDeclarationController.class);
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	
	@Resource
	ConstructionPlanService constructionPlanService;
	
	@Resource
	SettlementAuditService settlementAuditService;
	
	@Resource
	SettlementDeclarationService settlementDeclarationService;
	
	/** 分包工程量服务接口*/
	@Resource
	SubQuantitiesService subQuantitiesService;
	
	@Resource
	ChangeManagementService cmService;
	
	@Resource
	SupplementalContractService supContractService;
	@Resource
	EngineeringVisaService engineeringVisaService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("notThrough",Constants.MODULE_CODE_SETTLEMENT);
		modelView.addObject("stepId",StepEnum.SETTLEMENT_REPORT_START.getValue());
		modelView.addObject("stepIds",StepEnum.SETTLEMENT_REPORT_START.getValue()+Constants.SPLIT_CODE+StepEnum.REPORT_DONE_CONFIRM.getValue());
		modelView.addObject("curStepId",StepEnum.SETTLEMENT_REPORT.getValue());
		modelView.setViewName("settlement/settlementDeclaration");
		return modelView;
	}
	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelview.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
		modelview.addObject("compilerSignPost",PostTypeEnum.BUDGET_MEMBER.getValue());//施工结算编制人-预算员
		String cuAuditPost = PostTypeEnum.CONSTRUCTION.getValue();
		String cuPrincipalPost = PostTypeEnum.CU_PM.getValue();
		if(loginInfo.getPost().contains(PostTypeEnum.MINISTER.getValue())){//部长
			cuAuditPost = PostTypeEnum.MINISTER.getValue();
		}
		if(loginInfo.getPost().contains(PostTypeEnum.VICE_GENERAL_MANAGER.getValue())){//副总经理
			cuAuditPost = PostTypeEnum.VICE_GENERAL_MANAGER.getValue();
		}
		modelview.addObject("cuAuditPost",cuAuditPost);//施工审核人-施工员
		modelview.addObject("cuPrincipalPost",cuPrincipalPost);//施工负责人-项目经理
		modelview.setViewName("settlement/settlementDeclarationRight");
		return modelview;
		
	}
	
	/**
	 * 弹出搜索
	 * @return
	 */
	@RequestMapping(value="/declarationSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("settlement/declarationSearchPopPage");
		return modelview;
	}
	
	
	/**
	 * 工程列表条件查询
	 * @author ht.hu
	 * @param request
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/querySettlementDeclarationProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request, ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.SETTLEMENT_REPORT.getValue());
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.SETTLEMENT_REPORT.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 详述
	 * @author ht.hu
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewCost")
	@ResponseBody
	public SettlementDeclaration viewContract(String id){
		SettlementDeclaration result = new SettlementDeclaration();
		try {
			result = settlementDeclarationService.getSettlementDeclaration(id);
			//是否存在用户变更
			List<ChangeManagement> list = cmService.noCancelChangeManagement(id,ChangeTypeEnum.USER_CHANGE.getValue());
			if(list!=null && list.size()>0){
				result.setIsHaveCM(String.valueOf(list.size()));
			}
			List<SupplementalContract> supContracts = supContractService.findByProjId(id);
			if(supContracts!=null && supContracts.size()>0){
				result.setSupContract(supContracts);
			}
			//是否存在没有作废没有审核完成的签证
			List<EngineeringVisa> listEngi = engineeringVisaService.noCancelEngineeringVisa(id);
			if(listEngi!=null && listEngi.size()>0){
				result.setIsHaveEngi(String.valueOf(listEngi.size()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 分包工程量条件查询
	 * @param subQuantitiesReq
	 * @return
	 */
	@RequestMapping(value = "/queryQuantityStandard")
	@ResponseBody
	public Map<String, Object> queryQuantityStandard(SubQuantitiesReq subQuantitiesReq){
		//subQuantitiesReq.setAuditStatus(AuditStatusEnum.DECLARATION.getValue());
		try {
			return subQuantitiesService.querySubQuantityStandard(subQuantitiesReq);
		} catch (Exception e) {
			logger.error("分步分项名称查询失败！", e);
			return null;
		}
	}
	/**
	 * 报审保存
	 * @param sd
	 * @return
	 * @author ht.hu
	 */
	@RequestMapping(value = "/totalSave")
	@ResponseBody
	public String totalSave(HttpServletRequest request,@RequestBody(required = true)SettlementDeclaration sd){
		try {
			//Project project = projectService.viewProject(sd.getProjId());
			//project.setProjStatusId(WorkFlowUtil.workFlowStatus(WorkFlowActionEnum.SETTLEMENT_REPORT_START.getActionCode()));
			return settlementDeclarationService.saveSettlementDeclaration(sd);
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("报审区信息保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 保存报审工程量
	 * @author cui
	 */
	
	@RequestMapping(value = "/insertSubQualities")
	@ResponseBody
	public String insertSubQualities(@RequestBody SubQuantitiesDto qdto){
		
		try {
			return settlementDeclarationService.batInsertSubQualities(qdto);
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
		modelView.setViewName("settlement/quantityStandardPop");
		return modelView;
	}
	/**
	 * 结算报审推送
	 * @param id 结算表ID
	 * @return
	 */
	@RequestMapping(value="/pushSettlement")
	@ResponseBody
	public String pushSettlement(@RequestBody(required = true)SettlementDeclaration sd){
		try {
			if(sd.getCompilerSign()==null || sd.getCuAudit()==null || sd.getCuPrincipal()==null){   //判断结算报审推送是否签字
				return "signatureIsNull"; 
			}else{
				return settlementDeclarationService.pushSettlement(sd);
			}
			
		}catch (Exception e) {
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
}
