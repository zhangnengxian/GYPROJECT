package cc.dfsoft.project.biz.base.settlement.controller;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementJonintlySign;
import cc.dfsoft.project.biz.base.settlement.service.SettlementJonintlySignService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import java.text.ParseException;

/**
 * 结算汇签审核
 * @author wangang
 *
 */
@Controller
@RequestMapping(value="/auditSettlementJonintlySign")
public class AuditSettlementJonintlySignController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(AuditSettlementJonintlySignController.class);
	
	/**结算汇签*/
	@Resource
	SettlementJonintlySignService settlementJonintlySignService;

	/**审核记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;

	/**审核单据服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	/**
	 * 结算汇签
	 * @author wangang
	 * @createTime 2018.12.24
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("post", loginInfo.getPost());
		modelView.addObject("equipmentTechnician", PostTypeEnum.EQUIPMENT_TECHNICIAN.getValue());
		modelView.setViewName("settlement/auditSettlementJonintlySign");
		return modelView;
	}
	
	/**
	 * 弹屏查询
	 * @author wangang
	 * @return
	 */
	@RequestMapping(value = "/settlementJonintlySignPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("settlement/settlementJonintlySignPop");
		return modelview;
	}
	
	/**
	 * 工程列表
	 * @author wangang
	 * @createTime 2018年12月25日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq req){
		try {
			req.setSortInfo(request);
			/*if(StringUtils.isBlank(req.getSignStatus())){
				//默认为未完成汇签的
				req.setSignStatus(AuditResultEnum.NOT_APPLY.getValue());
			}*/
			//req.setSignStatus("2");
			return settlementJonintlySignService.queryProject(req);
		} catch (Exception e) {
			logger.error("工程列表查询失败！",e);
			return null;
		}
	}

	/**
	 * 打开审核页面
	 * @author wangang
	 * @createTime 2018年12月25日
	 */
	@RequestMapping(value = "/auditPage")
	public ModelAndView viewAuditPage(HttpServletRequest request) throws ParseException {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("projId",request.getParameter("projId"));
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);								 //登录人信息
		modelView.addObject("settlementJonintlySign",settlementJonintlySignService.findById(request.getParameter("projId")));
		modelView.setViewName("settlement/auditSettlementJonintSignAuditPage");
		return modelView;
	}

	/**
	 * 结算汇签审核历史列表查询
	 * @author wangang
	 * @createTime 2018年12月25日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq,@RequestParam(required = false) String projId){
		try {
			manageRecordQueryReq.setStepId(StepOutWorkflowEnum.AUDIT_SETTLEMENT_JONINTLY_SIGN.getValue());	//结算汇签审核
			manageRecordQueryReq.setProjId(projId);
			//manageRecordQueryReq.setFlag("1");
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}


	/***
	 * 保存结算汇签审核结果
	 * @author wangang
	 * @createTime 2018年12月25日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		try {
			DocType docType = docTypeService.findByStepId(StepOutWorkflowEnum.AUDIT_SETTLEMENT_JONINTLY_SIGN.getValue());
			manageRecordService.auditSettlementJonintSignAuditSave(manageRecord,docType == null?"":docType.getId(),
					docType == null?"":docType.getGrade(),StepOutWorkflowEnum.AUDIT_SETTLEMENT_JONINTLY_SIGN.getValue(),Constants.MODULE_CODE_SETTLEMENT);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("结算汇签审核记录保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	


	
}
