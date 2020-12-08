package cc.dfsoft.project.biz.base.settlement.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.dto.DrawingApplyReq;
import cc.dfsoft.project.biz.base.settlement.entity.DrawingApply;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.settlement.service.DrawingApplyService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;

/**
 * 竣工图审核
 * @author liaoyq
 * @todo 后台逻辑未完成
 */
@Controller
@RequestMapping(value="/auditCompleteDrawing")
public class AuditCompleteDrawingController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(AuditCompleteDrawingController.class);
	
	/**管理记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	/**电子图审核服务接口*/
	@Resource
	DrawingApplyService drawingApplyService;
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("settlement/auditCompleteDrawingList");
	    return modelAndView;
	}
	
	/**
	 * 打开审核屏
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewAuditPage")
	public ModelAndView viewAuditPage(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		String projId = request.getParameter("projId");
		
		DrawingApply da=drawingApplyService.findByProjId(projId);
		if(da!=null ){
			modelView.addObject("da", da);//查DrawingApply
		}
		
		//审核详述查询
		modelView.addObject("projId",request.getParameter("projId"));						//工程id
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));			//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));				 		//是否审核过
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);											//登录人信息
		modelView.addObject("mrResult",MrResultEnum.values());	
		modelView.setViewName("settlement/auditCompleteDrawing");						//审核结果
		return modelView;
	}
	/**
	 * 打开审核页面右屏
	 * @return
	 */
	@RequestMapping(value="/viewAuditPageLeft")
	public ModelAndView viewAuditPageLeft(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("settlement/auditCompleteDrawingLeft");
		return modelAndView;
	}
	/**
	 * 打开查询屏
	 * @return
	 */
	
	@RequestMapping(value="viewSearchPop")
	public ModelAndView viewSearchPop(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("settlement/auditCompleteDrawingSearchPop");
		return modelAndView;
	}
	
	
	/**
	 * 电子图审核列表查询
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryDrawingApplyAudit")
	@ResponseBody
	public Map<String,Object> queryDrawingApplyAudit(DrawingApplyReq req){
		try {
			if(StringUtils.isBlank(req.getAuditState())){
				req.setAuditState(AuditResultEnum.APPLYING.getValue());//默认加载审核中的
			}
			return drawingApplyService.queryDrawingApply(req);
		} catch (Exception e) {
			logger.error("电子图审核列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 电子图审核页面审核历史列表查询
	 * @author 
	 * @createTime 2016-07-05
	 * @param manageRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			manageRecordQueryReq.setStepId(StepOutWorkflowEnum.AUDIT_COMPLETE_DRAWING.getValue());	//图纸审核
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 电子图审核保存
	 * @author fuliwei
	 * @createTime 2016-07-05
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){
		try {
			manageRecordService.auditDrawingApplySave(manageRecord,"","",StepOutWorkflowEnum.AUDIT_COMPLETE_DRAWING.getValue(),Constants.MODULE_CODE_SETTLEMENT);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("电子图审核保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	
}
