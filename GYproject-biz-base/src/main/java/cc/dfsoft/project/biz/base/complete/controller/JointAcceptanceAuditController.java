package cc.dfsoft.project.biz.base.complete.controller;

import java.text.ParseException;
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

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.complete.service.JointAcceptanceApplyService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 
 * 描述:联合验收审核
 * @author liaoyq
 * @createTime 2018年9月9日
 */

@Controller
@RequestMapping(value="/jointAcceptanceAudit")
public class JointAcceptanceAuditController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(JointAcceptanceAuditController.class);
	
	/**审核记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**联合验收申请服务接口*/
	@Resource
	JointAcceptanceApplyService jointAcceptanceApplyService;
	
	/**审核单据服务接口*/
	@Resource
	DocTypeService docTypeService;
	@Resource
	ProjectService projService;
	
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
		modelView.setViewName("complete/jointAcceptanceAudit");
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
		String projId = request.getParameter("projId");
		
		//根据主键id查询联合申请
		Project project = projService.queryProjectById(projId);
		JointAcceptance ja=jointAcceptanceApplyService.findById(projId);
		modelView.addObject("ja",ja);
		modelView.addObject("project",project);
		modelView.addObject("projId",projId);
		modelView.addObject("jaId",ja.getJaId());
		modelView.addObject("amount",request.getParameter("amount"));
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);								 //登录人信息
		modelView.setViewName("complete/jointAcceptanceAuditPage");
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
	 * 联合验收审核列表查询
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryJointAcceptanceAudit")
	@ResponseBody
	public Map<String,Object> queryJointAcceptanceAudit(HttpServletRequest request,ProjectQueryReq req){
		try {
			req.setSortInfo(request);
			req.setProjStatusId(ProjStatusEnum.TO_UNION_PRE_INSPECTION_AUDIT.getValue());//待联合验收审核
			return jointAcceptanceApplyService.queryJointAcceptanceAudit(req);
		} catch (Exception e) {
			logger.error("联合验收审核列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 联合验收审核历史列表查询
	 * @author fuliwei
	 * @createTime 2017年8月10日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepEnum.UNION_PRE_INSPECTION_AUDIT.getValue());	//联合验收审核
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
			manageRecordService.auditJointAcceptanceSave(manageRecord,"","",StepEnum.UNION_PRE_INSPECTION_AUDIT.getValue(),Constants.MODULE_CODE_COMPLETE);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("联合审核记录保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
