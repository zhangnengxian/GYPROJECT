package cc.dfsoft.project.biz.base.contract.controller;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.dao.SupplementalContractModifyHistoryDao;
import cc.dfsoft.project.biz.base.contract.dto.SupplementalContractQueryReq;
import cc.dfsoft.project.biz.base.contract.dto.supplementalContractModifyHistoryReq;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.contract.enums.ModifyStatusEnum;
import cc.dfsoft.project.biz.base.contract.service.SupplementalContractService;
import cc.dfsoft.project.biz.base.design.controller.SurveyResultRegisterController;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 补充协议修改
 * @author 王会军
 *
 */
@Controller
@RequestMapping(value="/agreementModify")
public class agreementModifyController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SurveyResultRegisterController.class);
	
	/**审核记录*/
	@Resource
	ManageRecordService manageRecordService;
	
	@Resource
	IncrementDao incrementDao;
	
	@Resource
	SupplementalContractService  supplementalContractService;

	@Resource
	ProjectService projectService;

	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
     public ModelAndView agreementModify(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contract/agreementModify");
		return modelAndView;
    	 
     }
	/**
	 * 打开右侧页面
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
			viewUrl = "supplementalContractRight";
		}

		//每户金额：分公司ID+菜单ID
		String houseAmountKey = project.getCorpId()+"_"+menuId;
		Object houseAmountObj = Constants.getSysConfigByKey(houseAmountKey);

		ModelAndView modelview = new ModelAndView();
		modelview.addObject("resident", ContractTypeEnum.RESIDENT.getValue());	//合同类型-居民户
		modelview.addObject("sysDate",manageRecordService.getDatabaseDate());   //系统时间
		modelview.addObject("stepId",StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_AUDIT.getValue());
		modelview.addObject("increment",incrementDao.queryAll());//税率
		modelview.addObject("differenceId",Constants.PUBLIC_MODIFY);//补充协议修改标志
		modelview.addObject("houseAmount",houseAmountObj);//每户金额
		//modelview.setViewName("contract/agreementModifyRight");
		modelview.setViewName("contract/"+viewUrl);
		return modelview;
		
	}
	/**
	 * 查询已签补充协议列表
	 */
	@RequestMapping(value="/queryAgreementList")
	@ResponseBody
	public Map<String, Object> queryAgreementList(HttpServletRequest request,SupplementalContractQueryReq supplContractReq){
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		supplContractReq.setCorpId(loginInfo.getCorpId());
		try {

			Map map = supplementalContractService.queryAgreementList(supplContractReq);
			return map;
		}catch (Exception e) {
			logger.error("补充协议列表查询失败！", e);
			return null;
		}
		
	}
	/**
	 * 选中行时，查询详述
	 * 查询补充协议详述
	 */
	@RequestMapping(value="/querySupplementalContractDetail")
	@ResponseBody
	public SupplementalContract viewsSupplementalContract(@RequestParam(required=true,value="id")String scId){
		try{
			SupplementalContract supplementalContract = supplementalContractService.searchSupplementalContract(scId);
		    return supplementalContract;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	/**
	 * 弹屏幕搜索
	 * @return
	 */
	@RequestMapping(value="/agreementModifyPopPage")
	public ModelAndView contractSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("modifyStatus", ModifyStatusEnum.values());
		modelview.setViewName("contract/agreementModifyPopPage");
		return modelview;
	}
	/**
	 * 补充协议修改保存
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/saveSupplementalAgreement")
	@ResponseBody
	public String saveSupplementalAgreement(@RequestBody(required = true) SupplementalContract supplementalContract){
		try {
			return 	supplementalContractService.saveSupplementalAgreement(supplementalContract);  //更新协议
		
		} catch (Exception e) {
			logger.error("补充协议修改信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}


	
}
