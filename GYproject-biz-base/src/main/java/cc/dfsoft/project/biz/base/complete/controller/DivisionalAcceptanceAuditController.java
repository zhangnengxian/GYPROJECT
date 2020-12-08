package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.complete.dto.DivisionalAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptanceApply;
import cc.dfsoft.project.biz.base.complete.service.DivisionalAcceptanceApplyService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

/**
 * 分部验收审核
 * @author fuliwei
 *
 */

@Controller
@RequestMapping(value="/divisionalAcceptanceAudit")
public class DivisionalAcceptanceAuditController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(DivisionalAcceptanceAuditController.class);
	
	/**审核记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**分部验收申请服务接口*/
	@Resource
	DivisionalAcceptanceApplyService divisionalAcceptanceApplyService;
	
	/**审核单据服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("complete/divisionalAcceptanceAudit");
		return modelView;
	}
	
	/**
	 * 打开审核页面
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/auditPage")
	public ModelAndView viewAuditPage(HttpServletRequest request) throws ParseException {
		ModelAndView modelView = new ModelAndView();
		String daaId = request.getParameter("daaId");
		String menuId = request.getParameter("menuId");
		String projId = "";
		
		//根据主键id查询分部申请
		
		DivisionalAcceptanceApply daa=divisionalAcceptanceApplyService.findById(projId, daaId);
		modelView.addObject("daa",daa);
		modelView.addObject("projId",projId);
		modelView.addObject("daaId",daaId);
		modelView.addObject("amount",request.getParameter("amount"));
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);								 //登录人信息
		//配置不同审核页面的规则：corpId_menuId
		String key=daa.getCorpId()+"_"+menuId;
		Object object = Constants.getSysConfigByKey(key);
		if(object!=null){
			modelView.addObject("viewPageUrl",object.toString()); //不同的页面配置
		}							
		modelView.setViewName("complete/divisionalAcceptanceAuditPage");
		return modelView;
	}
	
	
	/**
	 * 弹出搜索
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("complete/divisionalAcceptanceAuditSearchPop");
		return modelview;
	}
	
	/**
	 * 分部验收审核列表查询
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryDivisionalAcceptanceAudit")
	@ResponseBody
	public Map<String,Object> queryDivisionalAcceptanceAudit(DivisionalAcceptanceReq req){
		try {
			if(StringUtils.isBlank(req.getAuditState())){
				req.setAuditState(AuditResultEnum.APPLYING.getValue());//默认加载审核中的
			}
			return divisionalAcceptanceApplyService.queryDivisionalAcceptanceAudit(req);
		} catch (Exception e) {
			logger.error("分部验收申请列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 分部验收审核历史列表查询
	 * @author fuliwei
	 * @createTime 2017年8月10日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepOutWorkflowEnum.DIVISIONAL_ACCEPTANCE_AUDIT.getValue());	//分部验收审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	
	/***
	 * 审核记录保存
	 * @author fuliwei
	 * @createTime 2017年8月10日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		try {
			manageRecordService.auditDivisionalAcceptanceSave(manageRecord,"","",StepOutWorkflowEnum.DIVISIONAL_ACCEPTANCE_AUDIT.getValue(),Constants.MODULE_CODE_COMPLETE);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("分部审核记录保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
}
