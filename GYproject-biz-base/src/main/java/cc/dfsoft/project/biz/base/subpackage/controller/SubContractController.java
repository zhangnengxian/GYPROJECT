package cc.dfsoft.project.biz.base.subpackage.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.project.biz.base.project.entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.baseinfo.service.ConstructionUnitService;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.enums.PayTypeSCEnum;
import cc.dfsoft.project.biz.base.design.controller.SurveyResultRegisterController;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dto.ConstructionUnitReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.enums.SubContractMethodEnum;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractService;
import cc.dfsoft.project.biz.base.subpackage.service.SubQuantitiesService;
import cc.dfsoft.project.biz.base.subpackage.service.SubSafeContractService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;

/**
 * 分包合同
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/subContract")
public class SubContractController {
	
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
	SubContractService	subContractService;	
	/**安全协议服务接口*/
	@Resource
	SubSafeContractService subSafeContractService;
	@Resource
	ConstructionUnitService constructionUnitService;   
	/** 审核记录*/
	@Resource
	ManageRecordService manageRecordService;
	/**审核级别*/
	@Resource
	DocTypeService docTypeService;
	/**分包工程量服务接口*/
	@Resource
	SubQuantitiesService subQuantitiesService;
	@Resource
	IFinanceInfoService financeInfoService;
	@Resource
	IncrementDao incrementDao;

	
	@Resource
	BusinessPartnersDao businessPartnersDao;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("cuUnitTypeId", UnitTypeEnum.CONSTRUCTION_UNIT.getValue());//分包单位
		modelView.addObject("deptTypeId", UnitTypeEnum.GAS_COMPANY.getValue());//燃气集团
		modelView.addObject("notThrough",Constants.MODULE_CODE_CONTRACT);
		modelView.addObject("curStepId",StepEnum.SUB_CONTRACT.getValue());
		LoginInfo login=SessionUtil.getLoginInfo();
		modelView.addObject("deptDivide",login.getDeptDivide());
		modelView.addObject("customerServiceCenter",DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());//部门划分:客服中心
		modelView.setViewName("subcontract/subContract");
		return modelView;
	}
	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String projId,String corpId,String menuId,String projectType) {

		String key = corpId+"_"+projectType+"_"+menuId;   //根据当前工程所在公司ID，工程类型，菜单ID加载右侧页面
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "subContractRight";  //默认加载集团施工合同页面
		LoginInfo loginInfo = SessionUtil.getLoginInfo();  //取得当前登录人信息
		if(obj !=null){  //若不为空返回右侧页面jsp
			viewUrl = obj.toString();
		}
		if(obj==null){  //若为空则默认加载该公司下的第一个右侧jsp页面
			key = loginInfo.getCorpId()+"_"+11+"_"+"110603";
		    obj = Constants.getSysConfigByKey(key);
		    if(obj != null){
		    	 viewUrl = obj.toString();
		    }
		}
		if(obj == null){  //若登录人为第三方人员，默认加载所在第一个公司下的第一个右侧页面
			String detptId = loginInfo.getDeptId();  //得到当前登录人部门ID
			BusinessPartners businessPartners = businessPartnersDao.get(detptId);
			if(businessPartners!=null){
				String[] bpCorpId = businessPartners.getCorpId().split(",");
				key = bpCorpId[0]+"_"+"11"+"_"+"110603";
				  obj = Constants.getSysConfigByKey(key);
				    if(obj != null){
				    	 viewUrl = obj.toString();
				    }
			}
		}
		
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());			//放入登录人信息
		modelview.addObject("payTypeSCEnum",PayTypeSCEnum.values()); 			//支付方式
		modelview.addObject("subContractMethod",SubContractMethodEnum.values());//建筑服务
		modelview.addObject("sysDate",manageRecordService.getDatabaseDate()); 	//系统时间
		modelview.addObject("increment",incrementDao.queryAll());//税率
		modelview.addObject("differenceId",Constants.PUBLIC_SIGN);//施工合同签订标识

		modelview.setViewName("subcontract/"+viewUrl);
		return modelview;
	}

	/**
	 * 工程列表条件查询
	 * @author cui
	 * @createTime 2017-8-4
	 * @param 	projectQueryReq
	 * @return	Map<String,Object>
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_SIGNED_SUBCONTRACT.getValue());//状态：待签施工合同
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.SUB_CONTRACT.getValue());//步骤：施工合同
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 详述
	 * @author cui
	 * @createTime 2017-8-4
	 * @param id 工程id
	 * @return SubContract
	 */
	@RequestMapping(value="/viewSubContract")
	@ResponseBody
	public SubContract viewSubContract(@RequestParam(required=true) String id){
		return subContractService.viewSubContractByProjId(id);
	}

	/**
	 * 弹出搜索屏
	 * @author fuliwei
	 * @createTime 2016-7-6
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("doType", "0");//分合同签订查看
		//modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("subcontract/subContractSearchPopPage");
		return modelview;
	}
	
	/**
	 * 获取分包单位（弹窗）
	 * @author fuliwei
	 * @createTime 2016-7-6
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/constructionUnitPop")
	public ModelAndView meterTypePop(){
		
		ModelAndView modelview = new ModelAndView();
		/*List<Manufacturer> list= meterOptService.findByMeterType(meterType);
		modelview.addObject("card",MeterTypeEnum.CARD.getValue());
		modelview.addObject("remotetrans", MeterTypeEnum.REMOTETRANS.getValue());
		modelview.addObject("list", list);*/
		modelview.setViewName("subcontract/constructionUintPop");
		return modelview;
	}
	
	/**
	 * 分包单位列表查询
	 * @param constructionUnitReq
	 * @return
	 */
	@RequestMapping(value = "/queryConstructionUnit")
	@ResponseBody
	public Map<String, Object> queryConstructionUnit(HttpServletRequest request,ConstructionUnitReq constructionUnitReq) {
		try {
			constructionUnitReq.setSortInfo(request);
			Map<String, Object> map=constructionUnitService.queryConstructionUnit(constructionUnitReq);
		    return map;
		} catch (Exception e) {
			logger.error("分包单位查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存分包合同
	 * @author cui
	 * @createTime 2017-8-4
	 * @param  subContract 分包合同
	 * @return String 
	 */
	@RequestMapping(value = "/saveSubContract")
	@ResponseBody
	public ResultMessage saveSubContract(@RequestBody(required = true) SubContract subContract){
		ResultMessage resultMessage = new ResultMessage();
		try {
			String res = subContractService.saveSubContract(subContract);
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(res);
			return resultMessage;
		}catch (ExpressException e) {
			logger.error("分包合同签订区信息保存失败！", e);
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		}
		catch (Exception e) {
			logger.error("分包合同签订区信息保存失败！", e);
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
	}

}
