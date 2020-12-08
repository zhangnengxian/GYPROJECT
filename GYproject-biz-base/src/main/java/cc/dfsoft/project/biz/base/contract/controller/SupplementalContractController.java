package cc.dfsoft.project.biz.base.contract.controller;

import cc.dfsoft.project.biz.base.budget.dto.BudgetReq;
import cc.dfsoft.project.biz.base.budget.enums.BudgetTypeEnum;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.dto.ContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.contract.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.contract.service.SupplementalContractService;
import cc.dfsoft.project.biz.base.design.controller.SurveyResultRegisterController;
import cc.dfsoft.project.biz.base.design.enums.DesignChangeStateEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.SessionUtils;
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
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 补充协议
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/supplementalContract")
public class SupplementalContractController {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SurveyResultRegisterController.class);

	/** 施工合同服务*/
	@Resource
	ContractService contractService;

	/** 补充协议服务*/
	@Resource
	SupplementalContractService supplmentalContractService;

	/**预算服务*/
	@Resource
	BudgetService budgetService;

	/**工程dao*/
	@Resource
	ProjectDao projectDao;
	@Resource
	IncrementDao incrementDao;

	/**审核记录*/
	@Resource
	ManageRecordService manageRecordService;

	@Autowired
	private ProjectService projectService;

	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("contract/supplementalContract");
		return modelView;
	}

	/**
	 * 加载右侧屏
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String projId,String corpId,String menuId) {
		Project project = projectService.queryProjectById(projId);

		String projType=project!=null?project.getProjectType():"11";
		corpId=project!=null?project.getCorpId():"1101";

		//详细页面：工程类型+分公司ID+菜单ID
		String key = projType+"_"+corpId+"_"+menuId;
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "";
		if(obj !=null){
			viewUrl = obj.toString();
		}else{
			viewUrl = "supplementalContractRight";
		}

		//每户金额：分公司ID+菜单ID
		String houseAmountKey = corpId+"_"+menuId;
		Object houseAmountObj = Constants.getSysConfigByKey(houseAmountKey);

		ModelAndView modelview = new ModelAndView();
		modelview.addObject("resident", ContractTypeEnum.RESIDENT.getValue());	//合同类型-居民户
		modelview.addObject("sysDate",manageRecordService.getDatabaseDate());   //系统时间
		modelview.addObject("stepId",StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_AUDIT.getValue());
		modelview.addObject("increment",incrementDao.queryAll());//税率
		modelview.addObject("differenceId",Constants.PUBLIC_SIGN);//补充协议签订标志
		modelview.addObject("houseAmount",houseAmountObj);//每户金额
//		modelview.setViewName("contract/supplementalContractRight");
		modelview.setViewName("contract/"+viewUrl);
		return modelview;

	}
	/**
	 * 合同列表查询-已作废
	 * @param request
	 * @param contractQueryReq
	 * @return
	 */
	@RequestMapping(value = "/querySupplementContract")
	@ResponseBody
	public Map<String,Object> queryContract(HttpServletRequest request,ContractQueryReq contractQueryReq){
		try {
			List<String> projStuList = new ArrayList();
			projStuList.add(ProjStatusEnum.TO_MAKE_PLAN.getValue());
			projStuList.add(ProjStatusEnum.TO_PLAN_AUDIT.getValue());
			projStuList.add(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue());
			projStuList.add(ProjStatusEnum.TO_AUDIT_AMOUNT.getValue());
			projStuList.add(ProjStatusEnum.TO_SIGNED_SUBCONTRACT.getValue());
			projStuList.add(ProjStatusEnum.TO_CONSTRUCTION.getValue());
			projStuList.add(ProjStatusEnum.DURING_CONSTRUCTION.getValue());
			contractQueryReq.setProjStuList(projStuList);//工程状态,合同签订以后
			contractQueryReq.setSortInfo(request);
			Map<String,Object> map=contractService.queryContractPrint(contractQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("合同信息查询失败！", e);
			return null;
		}
	}


	/**
	 * 查询合同列表
	 * @author fuliwei
	 * @createTime 2017年11月2日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryAdjustBudget")
	@ResponseBody
	public Map<String,Object> queryAdjustBudget(HttpServletRequest request,BudgetReq req){
		try {
			if(StringUtils.isBlank(req.getIsSignSuContrct())){
				req.setIsSignSuContrct(IsSignEnum.HAVE_NOT_SIGN.getValue());//未签订
			}
			//req.setChangeState(ChangeStateEnum.HANDLED.getValue());//已处理

			if(StringUtils.isBlank(req.getDesignChangeType())){
				//默认待签补充协议
				req.setDesignChangeType(DesignChangeStateEnum.WAIT_SUPPLEMENT_CONTRACT.getValue());
			}

			req.setBudgetTypeId(BudgetTypeEnum.ADJUSTED.getValue());//调整预算
			return budgetService.queryAdjustBudget(req);

			//查合同列表
			/*contractQueryReq.setSortInfo(request);
			List<String>projStuList = new ArrayList<String>();
			List<ProjStatusEnum> projStusEnum =ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_OUT_SKETCH.getValue(),ProjStatusEnum.REPORT_DONE_CONFIRM.getValue());
			for (ProjStatusEnum projStatusEnum : projStusEnum) {
				projStuList.add(projStatusEnum.getValue());
			}
			contractQueryReq.setProjStuList(projStuList);
			contractQueryReq.setModifyStatus("all");
			Map<String,Object> map=contractService.queryContractPrint(contractQueryReq);
			return map;*/
		}catch (Exception e) {
			logger.error("调整预算查询失败！", e);
			return null;
		}
	}
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/supplementalContractSearchPopPage")
	public ModelAndView contractSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		//modelview.addObject("payType", PayTypeEnum.values());//付款方式
		modelview.addObject("isSign", IsSignEnum.values());//是否签订补充协议
		modelview.setViewName("contract/supplementalContractSearchPopPage");
		return modelview;
	}
	/**
	 * 详述
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewsSupplementalContract")
	@ResponseBody
	public SupplementalContract viewSupplementalContract(@RequestParam(required=true) String id){
		try {
			return supplmentalContractService.viewSupplementalContract(id);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 补充协议保存
	 * @param contract
	 * @return
	 */
	@RequestMapping(value = "/saveSupplementalContract")
	@ResponseBody
	public String saveContract(@RequestBody(required = true) SupplementalContract supplementalContract){
		try {
			return supplmentalContractService.saveSupplementalContract(supplementalContract);
		} catch (Exception e) {
			logger.error("补充协议签订区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
