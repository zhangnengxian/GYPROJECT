package cc.dfsoft.project.biz.base.contract.controller;


import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.dto.IntelligentSupplementReq;
import cc.dfsoft.project.biz.base.contract.service.IntelligentSupplementService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.enums.AuditStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @ClassDesc: 智能表合并补充协议审核
 * @Author zhangnx
 * @Date 2019/7/27 19:46
 * Version:1.0
 */
@Controller
@RequestMapping(value="intelligentSupplementAudit")
public class IntelligentSupplementAuditController {
	@Resource
	IntelligentSupplementService intelligentSupplementService;
	@Resource
	IncrementDao incrementDao;
	@Resource
	ManageRecordService manageRecordService;


	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(ModelAndView modelView){
		modelView.setViewName("contract/intelligentSupplementAuditMain");
		return modelView;
	}


	/**
	 * @MethodDesc: 待审协议列表查询
	 * @Author zhangnx
	 * @Date 2019/7/26 17:08
	 */
	@RequestMapping(value = "/queryToAuditSupplement")
	@ResponseBody
	public Map<String, Object> queryToAuditSupplement(IntelligentSupplementReq req) {
		req.setIsStatus(AuditStatusEnum.AUDIT_IN_PROGRESS.getCode());//待审核
		try {
			Map<String, Object> map = intelligentSupplementService.queryToAuditSupplement(req);
			return map;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * @MethodDesc: 打开审核屏
	 * @Author zhangnx
	 * @Date 2019/7/29 13:33
	 */
	@RequestMapping(value="/auditPage")
	public ModelAndView audit(HttpServletRequest request,ModelAndView modelView){
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		modelView.addObject("auditTime",sdf.format(new Date()));   //系统时间
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		modelView.addObject("loginInfo", SessionUtil.getLoginInfo());			 //放入登录人信息
		modelView.addObject("mrResult", MrResultEnum.values());					 //审核结果
		modelView.addObject("isId",request.getParameter("isId"));				 //合同id
		modelView.setViewName("contract/isAuditPage");
		return modelView;
	}


	/**
	 * @MethodDesc: 审核记录查询
	 * @Author zhangnx
	 * @Date 2019/7/29 13:59
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq req){
		try {
			req.setStepId(StepOutWorkflowEnum.INTELLIGENT_SUPPLEMENT_AUDIT.getValue());	//智能表合同补充协议审核
			return manageRecordService.queryManageRecord(req);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public boolean auditSave(@RequestBody ManageRecord manageRecord){
		String stepId=StepOutWorkflowEnum.INTELLIGENT_SUPPLEMENT_AUDIT.getValue();
		try {
			String res= manageRecordService.isAuditSave(manageRecord,stepId,stepId,stepId);
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}





}
