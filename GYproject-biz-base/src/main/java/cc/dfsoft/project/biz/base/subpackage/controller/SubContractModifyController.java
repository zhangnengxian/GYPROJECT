package cc.dfsoft.project.biz.base.subpackage.controller;

import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fr.report.core.A.s;

import cc.dfsoft.project.biz.base.baseinfo.service.ConstructionUnitService;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.enums.ModifyStatusEnum;
import cc.dfsoft.project.biz.base.contract.enums.PayTypeSCEnum;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dto.ConstructionUnitReq;
import cc.dfsoft.project.biz.base.subpackage.dto.SubContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.enums.SubContractMethodEnum;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractService;
import cc.dfsoft.project.biz.base.subpackage.service.SubQuantitiesService;
import cc.dfsoft.project.biz.base.subpackage.service.SubSafeContractService;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 
 * 描述:施工合同修改
 * @author liaoyq
 * @createTime 2018年1月22日
 */
@Controller
@RequestMapping(value="/subContractModify")
public class SubContractModifyController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SubContractModifyController.class);
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
		modelView.setViewName("subcontract/subContractModify");
		return modelView;
	}

	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String projId,String corpId,String menuId) {

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


		ModelAndView modelview = new ModelAndView();
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());			//放入登录人信息
		modelview.addObject("payTypeSCEnum",PayTypeSCEnum.values()); 			//支付方式
		modelview.addObject("subContractMethod",SubContractMethodEnum.values());//建筑服务
		modelview.addObject("sysDate",manageRecordService.getDatabaseDate()); 	//系统时间
		modelview.addObject("increment",incrementDao.queryAll());//税率
		modelview.addObject("differenceId",Constants.PUBLIC_MODIFY);//施工合同修改标识
		modelview.setViewName("subcontract/"+viewUrl);
		//modelview.setViewName("subcontract/subContractModifyRight");
		return modelview;
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
			subContractQueryReq.setModifyState("-1");//分包合同修改，查询所有已推送的合同
			Map<String,Object> map=subContractService.querySubContract(subContractQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("分包合同信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 详述
	 * @author liaoyq
	 * @createTime 2018-1-23
	 * @param id 工程id
	 * @return SubContract
	 * @throws ParseException 
	 */
	@RequestMapping(value="/viewSubContract")
	@ResponseBody
	public SubContract viewSubContract(@RequestParam(required=true) String id){
		SubContract subContract = null;
		try {
			subContract = subContractService.findSubContractByScId(id);
		} catch (ParseException e) {
			e.printStackTrace();
		}
			return subContract;
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
	 * 保存分包合同修改信息
	 * @author liaoyq
	 * @createTime 2017-8-4
	 * @param  subContract 分包合同
	 * @return String 
	 */
	@RequestMapping(value = "/modifySubContract")
	@ResponseBody
	public ResultMessage modifySubContract(@RequestBody(required = true) SubContract subContract){
		ResultMessage resultMessage = new ResultMessage();
		try {
			String res = subContractService.modifySubContract(subContract);
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(res);
			return resultMessage;
		} catch (Exception e) {
			logger.error("分合同信息修改失败！", e);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
	}
	
	
}
