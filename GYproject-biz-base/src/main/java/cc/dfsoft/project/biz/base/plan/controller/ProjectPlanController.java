package cc.dfsoft.project.biz.base.plan.controller;

import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.enums.CorrelationTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.CorrelationService;
import cc.dfsoft.project.biz.base.charge.dao.AccrualsRecordDao;
import cc.dfsoft.project.biz.base.charge.dto.ChargeReq;
import cc.dfsoft.project.biz.base.charge.enums.ARStatusEnum;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.design.controller.SurveyResultRegisterController;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.*;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 * 工程计划
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/projectPlan")
public class ProjectPlanController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SurveyResultRegisterController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	/**合同接口*/
	@Resource
	ContractService contractService;
	/**计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService ;
	/**审核记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;

	/** 关联关系服务接口*/
	@Resource
	CorrelationService correlationService;
	@Resource
	ContractDao contractDao;
	@Resource
	AccrualsRecordDao accrualsRecordDao;
	@Autowired
	private ProjectChecklistService projectCheService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.addObject("suUnitTypeId",  UnitTypeEnum.SUPERVISION_UNIT.getValue());//监理单位
		modelView.addObject("notThrough",Constants.MODULE_CODE_CONSTRUCTIONPLAN);
		modelView.addObject("stepId",StepEnum.PROJECT_PLAN_AUDIT.getValue());
		modelView.addObject("curStepId",StepEnum.PROJECT_PLAN.getValue());
		modelView.setViewName("plan/projectPlan");
		return modelView;
	}
	/**
	 * 打开右侧编制计划页面
	 * @return
	 */
	@RequestMapping(value = "/viewPlanningPage")
	public ModelAndView viewPlanningPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("builder",PostTypeEnum.BUILDER.getValue());					//现场管理员
		modelview.addObject("gasCompany", UnitTypeEnum.CONSTRUCTION_UNIT.getValue());	// 分包单位
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelview.addObject("corpId", loginInfo.getCorpId());							// 分包公司Id
		modelview.addObject("customerServiceCenter", DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());//客服中心
		modelview.addObject("unitType", UnitTypeEnum.GAS_COMPANY.getValue());			// 燃气公司
		modelview.setViewName("plan/planEstablishRight");
		return modelview;
		
	}
	/**
	 * 工程列表条件查询
	 * @author zhangmeng
	 * @createTime
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_MAKE_PLAN.getValue());
			projectQueryReq.setStepId(StepEnum.PROJECT_PLAN.getValue());
			Map<String,Object> map=projectService.queryProjectByTime(projectQueryReq);
			// 符合当前状态的工程列表
			List<Project> list = (List<Project>) map.get("data");
			if(list!=null && list.size()>0){
				for(Project pro : list){
					Contract contract = contractDao.viewContractByprojId( pro.getProjId());
					if(contract!=null){
						//获取实收首付款
						ChargeReq chargeReq = new ChargeReq();
						chargeReq.setArAtatus(ARStatusEnum.ALREADY_CHARGE.getValue());
						chargeReq.setArType(CollectionTypeEnum.INITIAL_PAYMENT.getValue());
						chargeReq.setProjId(pro.getProjId());
						Map<String,Object> maps=accrualsRecordDao.queryAccrualsRecord(chargeReq);
						if(maps!=null){
							List lists=(List) maps.get("data");
							if(lists!=null&&lists.size()>0){
								pro.setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//下计划时已收款的工程标红
							}
						}
					}
				}
			}
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 打开左侧计划详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewAuditLeftPage")
	public ModelAndView viewAuditLeftPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("plan/planAuditLeft");
		return modelview;
		
	}
	
	/**
	 * 详述
	 * @author zhangmeng
	 * @createTime 2016-7-4
	 * @param id 工程id
	 * @return Contract
	 */
	@RequestMapping(value="/viewPlan")
	@ResponseBody
	public ConstructionPlan viewContract(@RequestParam(required=true) String id){
		ConstructionPlan dto;
		try {
			dto = constructionPlanService.converDTO(id);
			if(dto!=null){
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("详述查询失败！", e);
		}
		return new ConstructionPlan();
	}
	/**
	 * 弹出搜索屏
	 * @author zhangmeng
	 * @createTime 2016-7-04
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		//业务部门
		CorrelationReq req=new CorrelationReq();
		req.setCorType(CorrelationTypeEnum.PROJECT_METHOD.getValue());
		List<Correlation> department=correlationService.findCorType(req);
		modelview.addObject("department",department);//业务部门
		modelview.setViewName("plan/projectSearchPopPage");
		return modelview;
	}
	/**
	 * 保存施工计划
	 * @author zhangmeng
	 * @createTime 2016-7-4
	 * @param  
	 * @return 
	 */
	@RequestMapping(value = "/savePlan")
	@ResponseBody
	public String savePlan(@RequestBody(required = true) ConstructionPlan constructionPlan){
		try {
			return constructionPlanService.savePlan(constructionPlan);
		} catch (Exception e) {
			logger.error("计划编制区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 打开右侧审核计划页面
	 * @return
	 */
	@RequestMapping(value = "/viewAuditPage")
	public ModelAndView viewAuditPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("plan/planAuditRight");
		return modelview;
		
	}
	
	@RequestMapping(value = "/dispatch")
	public ModelAndView dispatch() {
		ModelAndView modelview = new ModelAndView();		
		//modelview.addObject("constructList",constructionPlanService.countProjectNum("c.management_office"));
		//modelview.addObject("cuList",constructionPlanService.countProjectNum("c.cu_name"));
		modelview.setViewName("plan/planDispatch");
		return modelview;
		
	}
	/**
	 * 打开管理按操作审核信息屏幕
	 * @author ht.hu
	 * @return
	 */
	@RequestMapping(value = "/auditInformation")
	public ModelAndView auditInformation(String projId, String stepId){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("projId",projId);
		modelView.addObject("stepId",stepId);
		modelView.addObject("stepIds",manageRecordService.queryStepIdByProjId(projId));
		modelView.setViewName("plan/auditInformationPop");
		return modelView;
	}
	/**
	 * 根据工程ID 操作步骤ID 查询未通过的审核信息
	 * @author ht.hu
	 * @param projId
	 * @param StepId
	 * @return
	 */
	@RequestMapping(value="/queryAuditInformation")
	@ResponseBody
	public ManageRecord queryAuditInformation(String projId, String StepId,String businessOrderId){

		try {
			//如果是报验单审核信息，值根据businessorderid条件查询
			ProjectChecklist data = null;
			//判空
			if(StringUtil.isNotBlank(businessOrderId)){
				data = projectCheService.viewProjectCheckList(businessOrderId);
			}
			//报验单存在，则应该为报验单审核意见，值根据业务单id查询
			if(data !=null){
				return manageRecordService.queryAuditInsInformation(businessOrderId);

			}
		}catch (Exception e){
			e.printStackTrace();
		}

		if(StringUtils.isNotBlank(businessOrderId)&& StringUtils.isNotBlank(StepId)){
			return manageRecordService.queryBusAuditInformation(businessOrderId, StepId);
		}else if(StringUtils.isNotBlank(projId) && StringUtils.isNotBlank(StepId)){
			return manageRecordService.queryAuditInformation(projId, StepId);
		}

		
		return null;
	}
	
}
