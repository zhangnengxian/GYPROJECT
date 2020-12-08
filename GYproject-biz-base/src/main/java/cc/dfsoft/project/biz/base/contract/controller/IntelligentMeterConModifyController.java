package cc.dfsoft.project.biz.base.contract.controller;

import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.entity.PayType;
import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.contract.service.PayTypeService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;
import cc.dfsoft.project.biz.base.subpackage.service.IntelligentMeterContractService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import java.util.List;

/**
 * 智能表合同修改
 * @author cui
 *
 */
@Controller
@RequestMapping(value="/intelligentMeterConModify")
public class IntelligentMeterConModifyController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(IntelligentMeterConModifyController.class);

	@Resource
	IntelligentMeterContractService meterContractService;

	/** 审核记录*/
	@Resource
	ManageRecordService manageRecordService;

	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/** 安装合同接口*/
	@Resource
	ContractService contractService;
	
	@Resource
	PayTypeService payTypeService;

	@Resource
	ProjectDao projectDao;
	@Resource
	IncrementDao incrementDao;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
//		modelView.addObject("notThrough",Constants.MODULE_CODE_CONTRACT);
//		modelView.addObject("stepId",StepOutWorkflowEnum.CONTRACT_MODIFY_AUDIT.getValue());
//		modelView.addObject("curStepId",StepOutWorkflowEnum.CONTRACT_MODIFY.getValue());
		modelView.setViewName("contract/intelligentMeterConModify");
		return modelView;
	}

	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("loginInfo",SessionUtil.getLoginInfo());			//放入登录人信息
		modelView.addObject("sysDate",manageRecordService.getDatabaseDate()); 			//系统时间
		modelView.addObject("payTypes", payTypeService.findByContractType(ContractTypeEnum.RESIDENT.getValue(),SessionUtil.getLoginInfo().getCorpId()));//民用合同付款方式
		modelView.addObject("increment",incrementDao.queryAll());//税率
		modelView.setViewName("contract/imcModifyRight");
		return modelView;
	}

	/**
	 * 弹出搜索屏
	 * @author cui
	 * @createTime 2017-11-15
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/imcModifySearchPopPage")
	public ModelAndView imcModifySearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("contract/imcModifySearchPopPage");
		return modelview;
	}
	
//	/**
//	 * 合同列表查询
//	 * @param request
//	 * @param imcReq
//	 * @return
//	 */
//	@RequestMapping(value = "/queryImcContract")
//	@ResponseBody
//	public Map<String,Object> queryContract(HttpServletRequest request,IntelligentMeterContractReq imcReq){
//		try {
//			imcReq.setFlag(IntelligentMeterConPushEnum.PUSHED.getValue());//已推送的智能表合同
//			imcReq.setSortInfo(request);
//			Map<String,Object> map=meterContractService.queryContract(imcReq);
//			return map;
//		} catch (Exception e) {
//			logger.error("智能表合同信息查询失败！", e);
//			return null;
//		}
//	}
//
//	/**
//	 * 详述
//	 * @author fuliwei
//	 * @createTime 2016-6-28
//	 * @param id 工程id
//	 * @return Project
//	 */
//	@RequestMapping(value="/viewContract")
//	@ResponseBody
//	public Contract viewContract(@RequestParam(required=true) String id){
//		Project pro=projectService.viewProject(id);
//		Contract contract = contractService.findByProjId(id);
//		contract.setCustName(pro.getCustName());  //客户名称
//		contract.setCustPhone(pro.getCustPhone());//甲方联系方式
//		contract.setProjectTypeDes(pro.getProjectTypeDes());//工程类型
//		contract.setContributionModeDes(pro.getContributionModeDes());//出资方式
//		contract.setDeptName(pro.getDeptName());//业务部门
//		contract.setSurveyer(pro.getSurveyer());//勘察员
//		LoginInfo loginInfo = SessionUtil.getLoginInfo();
//		contract.setConAgent(loginInfo.getStaffName());
//		return contract;
//	}
	
	/**
	 * 保存合同
	 * @author cui
	 * @createTime 2017-11-8
	 * @param  imcContract 合同
	 * @return String 
	 */
	@RequestMapping(value = "/modifyImcContract")
	@ResponseBody
	public String modifyImcContract(@RequestBody(required = true) IntelligentMeterContract imcContract){
		try {
			return meterContractService.modifyImcContract(imcContract);
		} catch (Exception e) {
			logger.error("智能表合同信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 联查出资类型选框
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPayType")
	public List<PayType> queryPayType(String id,String corpId){
		try{
			return payTypeService.findByContractType(id,corpId);
		}catch(Exception e){
			logger.error("联查付款方式失败！",e);
			return null;
		}
	}
}
