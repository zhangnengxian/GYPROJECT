package cc.dfsoft.project.biz.base.design.controller;

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
import cc.dfsoft.project.biz.base.design.service.OfficialDrawingService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;

/**
 * 白图审核
 * @author fulw
 *
 */
@Controller
@RequestMapping(value="/whiteMapAudit")
public class WhiteMapAuditController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(WhiteMapAuditController.class);
	
	
	/**管理记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	/**工程*/
	@Resource
	ProjectService projectService;
	
	/** 正式出图服务接口 */
	@Resource
	OfficialDrawingService officialDrawingService;
	
	/**
	 * 白图审核
	 * @author fuliwei  
	 * @date 2018年11月9日  
	 * @version 1.0
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("design/whiteMapAudit");
		return modelView;
	}
	
	
	/**
	 * 打开审核页面
	 * @author fuliwei  
	 * @date 2018年11月9日  
	 * @version 1.0
	 */
	@RequestMapping(value="/auditPage")
	public ModelAndView audit(HttpServletRequest request){
		
		ModelAndView modelView=new ModelAndView();
		String projId = request.getParameter("projId");
		Project pro=projectService.queryProjectById(projId);
		//审核详述查询
		modelView.addObject("project",pro);
		modelView.addObject("currentLevel",request.getParameter("currentLevel"));			//当前审核级别
		modelView.addObject("isAudit",request.getParameter("isAudit"));				 		//是否审核过
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);											//登录人信息
		modelView.addObject("mrResult",MrResultEnum.values());					 			//审核结果
		modelView.addObject("stepId",StepEnum.SURVEY_RESULT_REGISTER.getValue());			//上一个页面的步骤id
		modelView.setViewName("design/whiteMapAuditPage");
		return modelView;
	}
	
	/**
	 * 弹出查询屏
	 * @author fuliwei  
	 * @date 2018年11月9日  
	 * @version 1.0
	 */
	@RequestMapping(value="/surveySearchPage")
	public ModelAndView surveySearchPage(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("design/surveySearchPopPage");
		return modelView;
	}
	
	/**
	 * 工程列表条件查询
	 * @author fuliwei  
	 * @date 2018年11月9日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/queryProject")
    @ResponseBody
    public Map<String,Object> queryProject(HttpServletRequest request, ProjectQueryReq projectQueryReq){
        try {
            projectQueryReq.setProjStatusId(ProjStatusEnum.TO_WHITE_MAP_AUDIT.getValue());  //待白图审核
            projectQueryReq.setSortInfo(request);
           Map<String,Object> map=projectService.queryAuditProject(projectQueryReq,"",StepEnum.WHITE_MAP_AUDIT.getValue());
            return map;
        } catch (Exception e) {
            logger.error("工程信息查询失败！", e);
            return null;
        }
    }
	/**
	 * 审批历史
	 * @author fuliwei  
	 * @date 2018年11月9日  
	 * @version 1.0
	 */
	 @RequestMapping(value = "/queryManageRecord")
    @ResponseBody
    public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
        try {
            manageRecordQueryReq.setStepId(StepEnum.WHITE_MAP_AUDIT.getValue());
            return manageRecordService.queryManageRecord(manageRecordQueryReq);
        } catch (Exception e) {
            logger.error("管理记录列表查询失败！",e);
            return null;
        }
    }
	
	 /**
	  * 白图审核保存
	  * @author fuliwei  
	  * @date 2018年11月9日  
	  * @version 1.0
	  */
	 @RequestMapping(value = "/auditSave")
    @ResponseBody
    public String firstAuditSave(@RequestBody(required = true) ManageRecord manageRecord){
        try {
            DocType docType = docTypeService.findByStepId(StepEnum.WHITE_MAP_AUDIT.getValue());
            officialDrawingService.saveWhiteMapAudit(manageRecord,docType == null?"":docType.getId(),
                    docType == null?"":docType.getGrade(), WorkFlowActionEnum.WHITE_MAP_AUDIT.getActionCode(), Constants.MODULE_CODE_BUDGET);
            return Constants.OPERATE_RESULT_SUCCESS;
        } catch (Exception e) {
            logger.error("白图审核保存失败！",e);
            return Constants.OPERATE_RESULT_FAILURE;
        }
    }
	
}
