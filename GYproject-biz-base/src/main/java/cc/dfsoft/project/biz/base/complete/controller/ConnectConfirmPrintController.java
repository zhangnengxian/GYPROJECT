package cc.dfsoft.project.biz.base.complete.controller;

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

import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.complete.dto.CompletionTransferReq;
import cc.dfsoft.project.biz.base.complete.service.ConnectConfirmService;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 资料交接确认
 * @createTime 2016-09-26
 * @author cui
 *
 */

@Controller
@RequestMapping(value="/connectConfirmPrint")
public class ConnectConfirmPrintController {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ConnectConfirmPrintController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**管理记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**资料交接服务接口*/
	@Resource
	ConnectConfirmService connectConfirmService;
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/mian")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		modelView.setViewName("complete/connectConfirmPrint");
		return modelView;
	}
	
	/**
	 * 弹出搜索屏
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelview.setViewName("complete/filingDataPopPage");
		return modelview;
	}
	
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPlanningPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("complete/connectConfirmRight");
		return modelview;
	}
	
	/**
	 * 交接单打印标记
	 * @author cui
	 * @createTime 2017-2-15
	 * @param  String projId
	 * @return String 
	 */
	@RequestMapping(value = "/signConnectConfirmPrint")
	@ResponseBody
	public String signConnectConfirmPrint(@RequestBody(required = true) String projId){
		try {
			connectConfirmService.signConnectConfirmPrint(projId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("打印合同标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 交接单列表
	 * @param CompletionTransferReq
	 * @author cui
	 * @createTime 2016-09-26
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryFilingData")
	@ResponseBody
	public Map<String,Object> queryFilingData(HttpServletRequest request,CompletionTransferReq completionTransferReq){
		try {
	        completionTransferReq.setSortInfo(request);
	 		Map<String,Object> map=connectConfirmService.queryFilingData(completionTransferReq);
			return map;
		} catch (Exception e) {
			logger.error("交接单信息查询失败！", e);
			return null;
		}
	}
	
//	/**
//	 * 管理记录列表查询
//	 * @author zhangmeng
//	 * @createTime 2016-09-19
//	 * @param manageRecordQueryReq
//	 * @return
//	 */
//	@RequestMapping(value = "/queryManageRecord")
//	@ResponseBody
//	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
//		try {
//			manageRecordQueryReq.setStepId(StepEnum.CONNECT_CONFIRM.getValue());	//资料交接确认
//			return manageRecordService.queryManageRecord(manageRecordQueryReq);
//		} catch (Exception e) {
//			logger.error("管理记录列表查询失败！",e);
//			return null;
//		}
//	}
//	
//	/**
//	 * 资料交接确认保存
//	 * @author 张萌
//	 * @createTime 2016-09-19
//	 * @param manageRecord
//	 * @return
//	 */
//	@RequestMapping(value = "/auditSave")
//	@ResponseBody
//	public String auditSave(@RequestBody(required = true) FilingData dataRrcord){
//		
//		try {
//			connectConfirmService.saveConnect(dataRrcord);
//			return Constants.OPERATE_RESULT_SUCCESS;
//		} catch (Exception e) {
//			logger.error("交接信息确认失败！",e);
//			return Constants.OPERATE_RESULT_FAILURE;
//		}
//		
//	}
}
