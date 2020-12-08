package cc.dfsoft.project.biz.base.subpackage.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.project.biz.base.subpackage.dto.ConstructionUnitReq;
import cc.dfsoft.project.biz.base.subpackage.dto.SubContractDTO;
import cc.dfsoft.uexpress.common.dto.JsonDateValueProcessor;
import cc.dfsoft.uexpress.common.util.BeanUtil;
import cc.dfsoft.uexpress.common.util.SessionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.ConstructionUnitService;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.enums.ContractMethodEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.contract.enums.IsIntelligentConPayEnum;
import cc.dfsoft.project.biz.base.contract.enums.ModifyStatusEnum;
import cc.dfsoft.project.biz.base.contract.enums.OperateTypeEnum;
import cc.dfsoft.project.biz.base.contract.enums.PayTypeSCEnum;
import cc.dfsoft.project.biz.base.contract.service.PayTypeService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.OperateRecordLog;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordLogService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.enums.SubContractMethodEnum;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractService;
import cc.dfsoft.project.biz.base.subpackage.service.SubQuantitiesService;
import cc.dfsoft.project.biz.base.subpackage.service.SubSafeContractService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.UpdateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
/**
 * 
 * 描述:施工合同修改审核
 * @author liaoyq
 * @createTime 2018年1月22日
 */@Controller
@RequestMapping(value="/scModifyAudit")
public class ScModifyAuditController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ScModifyAuditController.class);
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
	OperateRecordLogService operateRecordLogService;
	@Resource
	PayTypeService payTypeService;
	@Resource
	IncrementDao incrementDao;
	@Resource
	SubContractDao subContractDao;
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
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("subcontract/scModifyAudit");
		return modelView;
	}
	
	/**
	 * 工程分合同列表
	 * @author liaoyq
	 * @createTime 2018年1月22日
	 * @param
	 * @return	Map<String,Object>
	 */
	@RequestMapping(value = "/querySubContract")
	@ResponseBody
	public Map<String,Object> queryContract(HttpServletRequest request,SubContractQueryReq subContractQueryReq){
		try {
			subContractQueryReq.setSortInfo(request);
			subContractQueryReq.setModifyState(ModifyStatusEnum.TO_AUDIT.getValue());//待修改审核的合同列表
			subContractQueryReq.setStepId(StepOutWorkflowEnum.SC_MODIFY_AUDIT.getValue());//合同修改审核
			Map<String,Object> map=subContractService.querySubContractAudit(subContractQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("分包合同信息查询失败！", e);
			return null;
		}
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
		modelview.addObject("doType", "1");//分合同修改查询
		modelview.setViewName("subcontract/subContractSearchPopPage");
		return modelview;
	}
	/**
	* 打开审核屏
	* @return ModelAndView
	*/
	@RequestMapping(value="/auditPage")
	public ModelAndView audit(HttpServletRequest request){
		
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		modelView.addObject("loginInfo",SessionUtil.getLoginInfo());			 //放入登录人信息
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		modelView.addObject("mrResult",MrResultEnum.values());					 //审核结果
		modelView.addObject("scId",request.getParameter("scId"));				 //合同id
		modelView.addObject("corpId",request.getParameter("corpId"));        //合同id
		modelView.addObject("projId",request.getParameter("projId"));		//工程id
		modelView.setViewName("subcontract/scModifyAuditPage");
		return modelView;
	}
	
	/**
	 * @methodDesc: 施工合同详细
	 * @author: zhangnx
	 * @date: 14:14 2018/9/20
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(HttpServletRequest request) throws ParseException {

		String menuId=request.getParameter("menuId");//菜单ID
		String scId=request.getParameter("scId");//合同ID
		String projId=request.getParameter("projId");//合同ID

		Project project = projectService.queryProjectById(projId);
		//详细页面：工程类型+分公司ID+菜单ID
		String key = project.getProjectType()+"_"+project.getCorpId()+"_"+menuId;
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "";
		if(obj !=null){
			viewUrl = obj.toString();
		}else{
			viewUrl = "subContractRight";
		}


		SubContract subContract =subContractService.findSubContractByScId(scId);
		JSONObject scJsonStr = new JSONObject();
		if (subContract!=null){
			//SubContract sc=new SubContract();
			//BeanUtil.copyNotNullProperties(sc,subContract);//将subContract转成sc
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());//处理JSONObject序列化后的Date类型
			scJsonStr=JSONObject.fromObject(subContract,jsonConfig);//将java对象转换为json对象
		}
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());			//放入登录人信息
		modelview.addObject("payTypeSCEnum",PayTypeSCEnum.values()); 			//支付方式
		modelview.addObject("subContractMethod",SubContractMethodEnum.values());//建筑服务
		modelview.addObject("sysDate",manageRecordService.getDatabaseDate()); 	//系统时间
		modelview.addObject("increment",incrementDao.queryAll());//税率
		modelview.addObject("scJsonStr",scJsonStr);//施工合同详细
		modelview.setViewName("subcontract/"+viewUrl);
		//modelview.setViewName("subcontract/scAuditPageLeft");

		return modelview;
	}
	
	/**
	 * 管理记录列表查询
	 * @author pengtt
	 * @createTime 2016-07-11
	 * @param manageRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepOutWorkflowEnum.SC_MODIFY_AUDIT.getValue());	//合同修改审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 合同审核保存
	 * @author pengtt
	 * @createTime 2016-07-11
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public ResultMessage auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		ResultMessage resultMessage = new  ResultMessage();
		try {
			String result = manageRecordService.suContractModifyAuditSave(manageRecord,"","",StepOutWorkflowEnum.SC_MODIFY_AUDIT.getValue());
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(result);
			return resultMessage;
		} catch (ExpressException e) {
			logger.error("分合同修改审核失败！",e);
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		} catch (Exception e) {
			logger.error("分合同修改审核失败！",e);
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
		
	}
	
}
