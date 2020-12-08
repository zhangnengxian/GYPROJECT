package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.withgas.dto.GasProjectReq;
import cc.dfsoft.project.biz.base.withgas.entity.GasProject;
import cc.dfsoft.project.biz.base.withgas.enums.GasProjectStatusEnum;
import cc.dfsoft.project.biz.base.withgas.service.GasProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
/**
 * 开通计划审核
 * @author fuliwei
 *
 */

@Controller
@RequestMapping(value="/gasAudit")
public class GasAuditController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(GasAuditController.class);
	
	/**工程服务接口*/
	@Resource
	GasProjectService gasProjectService;

	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	@Resource
	ManageRecordService manageRecordService;
	
	/**
	 * 打开主页面
	 * @author cui
	 * @createTime 2017-8-10
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(ModelAndView modelView){
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("complete/gasAudit");
		return modelView;
	}
	
	/**
	 * 打开审核页面
	 * @author cui
	 * @createTime 2017-8-10
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/auditPage")
	public ModelAndView viewAuditPage(HttpServletRequest request) {
		ModelAndView modelView = new ModelAndView();
		String gprojId = request.getParameter("gprojId");
		String menuId = request.getParameter("menuId");
		//根据工程id查设计信息
		GasProject gp=gasProjectService.findById(gprojId);
		modelView.addObject("gasProject", JSONObject.toJSON(gasProjectService.findById(gprojId)));
		modelView.addObject("amount",request.getParameter("amount"));
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);											//登录人信息
		modelView.addObject("mrResult", MrResultEnum.values());					 			//审核结果
		
		String key = gp.getCorpId()+"_"+menuId;
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "";
		if(obj !=null){
			viewUrl = obj.toString();
			modelView.addObject("viewUrl", viewUrl);					 					//嵌入审核页面
		}
		
		modelView.setViewName("complete/gasAuditPage");
		return modelView;
	}
	
	/**
	 * 工程列表条件查询
	 * @author cui
	 * @createTime 2017-8-10
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryGasProject")
	@ResponseBody
	public Map<String,Object> queryGasProject(HttpServletRequest request,GasProjectReq gasProjectReq){
		try {
			gasProjectReq.setGasProjStatus(GasProjectStatusEnum.GAS_AUDIT.getValue());  //暂写--待审核
			return gasProjectService.queryGasAudit(gasProjectReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 审核历史列表查询
	 * @author cui
	 * @createTime 2017-8-10
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepOutWorkflowEnum.GAS_PROJECT_AUDIT.getValue());//通气审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}

	/**
	 * 通气审核保存
	 * @author cui
	 * @createTime 2017-8-10
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		try {
			gasProjectService.auditPass(manageRecord);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("通气审核保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
