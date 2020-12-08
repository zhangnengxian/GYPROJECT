package cc.dfsoft.project.biz.base.subpackage.controller;

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

import cc.dfsoft.project.biz.base.baseinfo.enums.ProjectTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.ConstructionUnitService;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.contract.enums.IsIntelligentConPayEnum;
import cc.dfsoft.project.biz.base.contract.service.PayTypeService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dto.IntelligentMeterContractReq;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;
import cc.dfsoft.project.biz.base.subpackage.enums.IntelligentMeterConPushEnum;
import cc.dfsoft.project.biz.base.subpackage.service.IntelligentMeterContractService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceContractTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.UpdateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.enums.ProjContructTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * 
 * 描述:智能表合同控制层
 * @author liaoyq
 * @createTime 2017年9月16日
 */
@Controller
@RequestMapping(value="/intelligentMeterCon")
public class IntelligentMeterContractController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(IntelligentMeterContractController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**施工计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;	
	/**智能表合同服务接口*/
	@Resource
	IntelligentMeterContractService	meterContractService;	
	
	@Resource
	ConstructionUnitService constructionUnitService;   
	/** 审核记录*/
	@Resource
	ManageRecordService manageRecordService;
	/**审核级别*/
	@Resource
	DocTypeService docTypeService;
	@Resource
	PayTypeService payTypeService;
	
	@Resource
	IFinanceInfoService financeInfoService;

	@Resource
	IncrementDao incrementDao;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("deptTypeId", UnitTypeEnum.GAS_COMPANY.getValue());//燃气集团
		modelView.addObject("notThrough",Constants.MODULE_CODE_CONTRACT);
		modelView.addObject("curStepId",StepEnum.CONSTRUCT_CONTRACT.getValue());
		modelView.setViewName("subcontract/intelligentMeterCon");
		return modelView;
	}
	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String projId,String corpId,String menuId) {
		if(StringUtil.isBlank(projId)){
			corpId = SessionUtil.getLoginInfo().getCorpId();
		}else{
			Project proj = projectService.viewProject(projId);
			corpId = proj.getCorpId();
		}
		//String key = proj.getCorpId()+"_"+menuId;
		String key = corpId+"_"+menuId;
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "";
		if(obj !=null){
			viewUrl = obj.toString();
		}else{
			viewUrl = "intelligentMeterConRight";
		}
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());			//放入登录人信息
		modelview.addObject("sysDate",manageRecordService.getDatabaseDate()); 			//系统时间
		modelview.addObject("payTypes", payTypeService.findByContractType(ContractTypeEnum.RESIDENT.getValue(),corpId));//民用合同付款方式 
		modelview.addObject("increment", incrementDao.queryAll());//税率
//		modelview.setViewName("subcontract/intelligentMeterConRight");
		modelview.setViewName("subcontract/"+viewUrl);
		return modelview;
	}

	/**
	 * 查询工程列表：待签施工合同的智能表工程
	 * @author liaoyq
	 * @createTime 2017-9-16
	 * @param request
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryImc")
	@ResponseBody
	public Map<String,Object> queryImc(HttpServletRequest request,IntelligentMeterContractReq imcReq){
		try {
			//imcReq.setProjStatusId(ProjStatusEnum.TO_SIGNED_SUBCONTRACT.getValue());//状态：待签施工合同
			//智能表工程判断
			//imcReq.setIsIntelligentMeter(1);--子公司不一定选择
			imcReq.setProjLtypeId("11");//民用工程
			if(StringUtil.isBlank(imcReq.getFlag())){//推送状态为空，默认显示未推送的工程
				imcReq.setFlag(IntelligentMeterConPushEnum.NO_PUSH.getValue());
			}
			//imcReq.setProjLtypeId("15");
			imcReq.setSortInfo(request);
			return meterContractService.queryProjectImc(imcReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 详述
	 * @author liaoyq
	 * @createTime 2017-9-16
	 * @param id 工程id
	 * @return SubContract
	 */
	@RequestMapping(value="/viewIntelligentMeterCon")
	@ResponseBody
	public IntelligentMeterContract viewIntelligentMeterCon(@RequestParam(required=true) String id){
		return meterContractService.viewContractByProjId(id);
	}

	/**
	 * 弹出搜索屏
	 * @author liaoyq
	 * @createTime 2017-9-16
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("imcPushEnum", IntelligentMeterConPushEnum.values());
		modelview.setViewName("subcontract/intelligentMeterConSearchPopPage");
		return modelview;
	}
	
	/**
	 * 保存智能表合同
	 * @author liaoyq
	 * @createTime 2017-9-16
	 * @param  subContract 智能表合同
	 * @return String 
	 */
	@RequestMapping(value = "/saveIMContract")
	@ResponseBody
	public ResultMessage saveIMContract(@RequestBody(required = true) IntelligentMeterContract contract){
		ResultMessage resultMessage = new ResultMessage();
		try {
			String res = meterContractService.saveContract(contract);
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(res);
			return resultMessage;
		} catch (ExpressException e) {
			logger.error("智能表合同签订区信息保存失败！", e);
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		} catch (Exception e) {
			logger.error("智能表合同签订区信息保存失败！", e);
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
	}

}
