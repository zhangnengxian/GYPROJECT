package cc.dfsoft.project.biz.base.constructmanage.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.uexpress.common.util.SessionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.budget.service.ChangeManagementService;
import cc.dfsoft.project.biz.base.constructmanage.dto.EngineeringVisaQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.EngineeringVisa;
import cc.dfsoft.project.biz.base.constructmanage.enums.EngineeringTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.EngineeringVisaService;
import cc.dfsoft.project.biz.base.contract.controller.ContractAuditController;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;

/**
 * 
 * 描述:签证量审核
 * @author liaoyq
 * @createTime 2019年8月28日
 */
@Controller
@RequestMapping(value="/engineeringQuantyAudit")
public class EngineeringQuantyAuditController {
	
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(EngineeringQuantyAuditController.class);
	
	/**变更记录服务接口*/
	@Resource
	ChangeManagementService changeManagementService;

	/**签证记录服务接口*/
	@Resource
	EngineeringVisaService engineeringVisaService;
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	/**管理记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	
	@Resource
	BusinessPartnersService businessPartnersService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("constructmanage/engineeringQuantyAudit");
		return modelView;
	}
	
	
	/**
	 * 签证审核列表条件查询
	 * @param request
	 * @param engineeringVisaQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryVisaAudit")
	@ResponseBody
	public Map<String,Object> queryVisaAudit(HttpServletRequest request,EngineeringVisaQueryReq engineeringVisaQueryReq){
		try {
			engineeringVisaQueryReq.setSortInfo(request);
			Map<String, Object> map = engineeringVisaService.queryVisaQuantyAudit(engineeringVisaQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("待签证量审核列表查询失败！", e);
			return null;
		}
	}
	
	
	/**
	* 打开审核屏
	* @return ModelAndView
	*/
	@RequestMapping(value="/auditPage")
	public ModelAndView audit(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("evType", EngineeringTypeEnum.values());//签证类型
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		modelView.addObject("loginInfo",SessionUtil.getLoginInfo());			 //放入登录人信息
		modelView.addObject("auditTime",engineeringVisaService.getDatabaseDate().getTime());	//当前数据库时间
		modelView.addObject("mrResult",MrResultEnum.values());					 //审核结果
		modelView.addObject("evId",request.getParameter("evId"));				 //签证id
		EngineeringVisa engineeringVisa = engineeringVisaService.viewEngineeringVisa(request.getParameter("evId"));
		modelView.addObject("engineeringVisa",engineeringVisa);				 //签证id
		
		modelView.addObject("cuPm", PostTypeEnum.DEPUTY_DIRECTOR.getValue());//副处长
		modelView.addObject("suPrincipal", PostTypeEnum.SUJGJ.getValue());//现场监理师
		modelView.addObject("designer", PostTypeEnum.DESIGNER.getValue());//设计员
		modelView.addObject("auditer", PostTypeEnum.DIRECTOR.getValue());//处长
		modelView.addObject("approvaler", PostTypeEnum.CHIEF_ENGINEER.getValue());//总工
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.addObject("drawUrl1",Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH); 	//img url
		modelView.addObject("accessorySource", AccessorySourceEnum.COLLECT_FILE.getValue());//附件来源类型
		modelView.setViewName("constructmanage/engineeringQuantyAuditPage");
		return modelView;
	}
	
	/**
	 * 查询弹屏  
	 * @return
	 */
	@RequestMapping(value="/searchPopPage")
	public ModelAndView touchPlanAuditSearchPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("subcontract/engineerAuditSearchPopPage");  //签证审核弹窗页面
		return modelView;
	}
	/**
	 * 签证记录详述
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(HttpServletRequest request) {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("evId",request.getParameter("evId"));
		modelview.setViewName("constructmanage/engineeringVisaAuditPageLeft");					
		return modelview;
	}
	/**
	 * 签证审核记录保存
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public ResultMessage auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		ResultMessage resultMessage = new ResultMessage();
		try {
			String res = manageRecordService.engineeringQuantyAuditSave(manageRecord,"","",StepOutWorkflowEnum.ENGINEERING_QUANTY_AUDIT.getValue());
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(res);
			return resultMessage;
		} catch (ExpressException e) {
			logger.error("签证审核记录保存失败！",e);
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		}catch (Exception e) {
			logger.error("签证审核记录保存失败！",e);
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
	}
	/**
	 * 条件查询审核记录
	 * @param manageRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepOutWorkflowEnum.ENGINEERING_AUDIT.getValue());	
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
}
