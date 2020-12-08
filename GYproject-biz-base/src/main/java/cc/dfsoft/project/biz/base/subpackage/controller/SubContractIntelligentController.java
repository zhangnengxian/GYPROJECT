package cc.dfsoft.project.biz.base.subpackage.controller;

import java.util.ArrayList;
import java.util.List;
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

import cc.dfsoft.project.biz.base.baseinfo.service.ConstructionUnitService;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.enums.IsIntelligentConPayEnum;
import cc.dfsoft.project.biz.base.contract.enums.PayTypeSCIntelligentEnum;
import cc.dfsoft.project.biz.base.design.controller.SurveyResultRegisterController;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dto.SubConIntelligentReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContractIntelligent;
import cc.dfsoft.project.biz.base.subpackage.enums.SubConIntelligentCashEnum;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractIntelligentService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceContractTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.ExceptionUtil;

/**
 * 分包合同(智能表合同)
 * 信息中心和表厂签订
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/subContractIntelligent")
public class SubContractIntelligentController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SurveyResultRegisterController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**施工计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;	
	/**分包协议服务接口*/
	@Resource
	SubContractIntelligentService subContractIntelligentService;	
	@Resource
	ConstructionUnitService constructionUnitService;   
	@Resource
	ManageRecordService manageRecordService;

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
		modelView.addObject("curStepId",StepEnum.SUB_CONTRACT.getValue());
		modelView.setViewName("subcontract/subContractIntelligent");
		return modelView;
	}
	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());			//放入登录人信息
		modelview.addObject("payTypeSCIEnum",PayTypeSCIntelligentEnum.values()); //支付方式
		modelview.addObject("cashEnum",SubConIntelligentCashEnum.values()); //每户金额
		modelview.addObject("sysDate",manageRecordService.getDatabaseDate()); 			//系统时间
		modelview.addObject("gasCompany", UnitTypeEnum.INTELLIGENT_METER_UNIT.getValue());// 智能表公司	
		modelview.addObject("increment",incrementDao.queryAll());//税率
		modelview.setViewName("subcontract/subContractIntelligentRight");
		return modelview;
	}

	/**
	 * 工程列表条件查询
	 * @author liaoyq
	 * @createTime 2017-10-11
	 * @param 	projectQueryReq
	 * @return	Map<String,Object>
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			//安装和签订后，到施工中的民用 智能表工程列表
			projectQueryReq.setIsIntelligentMeter("1");//是智能表标记
			List<String> projStuList =  new ArrayList<String>();
			projStuList.add(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue());
			projStuList.add(ProjStatusEnum.TO_DETERMINE_DISPATCH.getValue());
			projStuList.add(ProjStatusEnum.TO_AUDIT_AMOUNT.getValue());
			projStuList.add(ProjStatusEnum.TO_SIGNED_SUBCONTRACT.getValue());
			projStuList.add(ProjStatusEnum.TO_SIGNED_SUBCONTRACT.getValue());
			projStuList.add(ProjStatusEnum.TO_CONSTRUCTION.getValue());
			projStuList.add(ProjStatusEnum.DURING_CONSTRUCTION.getValue());
			projectQueryReq.setProjStuList(projStuList);//施工计划下达后到施工完成的工程 
			projectQueryReq.setSortInfo(request);
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", ExceptionUtil.getMessage(e));
			return null;
		}
	}

	/**
	 * 工程列表条件查询
	 * @author liaoyq
	 * @createTime 2017-10-11
	 * @param 	projectQueryReq
	 * @return	Map<String,Object>
	 */
	@RequestMapping(value = "/queryProjects")
	@ResponseBody
	public Map<String,Object> queryProjects(HttpServletRequest request,SubConIntelligentReq sciReq){
		try {
			//安装和签订后，到施工中的民用 智能表工程列表
			sciReq.setIsIntelligentMeter("1");//是智能表标记
			//todo:
			sciReq.setProjStatusIdStart(ProjStatusEnum.TO_AUDIT_CONTRACT.getValue());//
			sciReq.setProjStatusIdEnd(ProjStatusEnum.DURING_CONSTRUCTION.getValue());
			sciReq.setSortInfo(request);
			return subContractIntelligentService.queryProjects(sciReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", ExceptionUtil.getMessage(e));
			return null;
		}
	}
	/**
	 * 详述
	 * @author liaoyq
	 * @createTime 2017-10-11
	 * @param id 工程id
	 * @return SubContract
	 */
	@RequestMapping(value="/viewSubConIntelligent")
	@ResponseBody
	public SubContractIntelligent viewSubConIntelligent(@RequestParam(required=true) String id){
		return subContractIntelligentService.viewSubConIntelligentByProjId(id);
	}

	/**
	 * 弹出搜索屏
	 * @author liaoyq
	 * @createTime 2017-10-11
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		//modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("subcontract/subContractSearchPopPage");
		return modelview;
	}
	
	
	
	/**
	 * 保存分包合同
	 * @author liaoyq
	 * @createTime 2017-10-11
	 * @param  subContract 分包合同
	 * @return String 
	 */
	@RequestMapping(value = "/saveSubContractIntelligent")
	@ResponseBody
	public ResultMessage saveSubContract(@RequestBody(required = true) SubContractIntelligent scIntelligent){
		ResultMessage resultMessage = new ResultMessage();
		try {
			String res = subContractIntelligentService.saveSubContractIntelligent(scIntelligent);
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(res);
			return resultMessage;
		} catch (ExpressException e) {
			logger.error("智能表分包合同签订区信息保存失败！", ExceptionUtil.getMessage(e));
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		} catch (Exception e) {
			logger.error("智能表分包合同签订区信息保存失败！", ExceptionUtil.getMessage(e));
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
	}

}
