package cc.dfsoft.project.biz.base.accept.controller;

import cc.dfsoft.project.biz.base.accept.service.AccepAuditService;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 受理申请审核
 */
@Controller
@RequestMapping("/acceptAudit")
public class AcceptAuditController {
    /** 日志实例 */
    private static Logger logger= LoggerFactory.getLogger(AcceptAuditController.class);
    @Autowired
    private AccepAuditService accepAuditService;
    @Resource
    ManageRecordService manageRecordService;

    /**单据类型服务接口*/
    @Resource
    DocTypeService docTypeService;

    /**
     * 打开主页面
     * @return
     */
    @RequestMapping(value="/main")
    public ModelAndView main(){
        ModelAndView modelView=new ModelAndView();
        modelView.addObject("curStepId", StepEnum.PROJECT_ACCEPT_AUDIT.getValue());
        modelView.addObject("projNo", SessionUtils.getProjNo());
        modelView.setViewName("accept/acceptAudit");
        return modelView;
    }

    /**
     * 查询工程列表
     * @author
     * @createTime
     * @param
     * @return
     */
    @RequestMapping(value = "/queryProject")
    @ResponseBody
    public Map<String,Object> querySurveyInfo(ProjectQueryReq req){
        try {
            req.setProjStatusId(ProjStatusEnum.PROJECT_ACCEPT_AUDIT.getValue());//待审核
            req.setStepId(StepEnum.PROJECT_ACCEPT_AUDIT.getValue());//待审核
            req.setTimeLimit(StepEnum.PROJECT_ACCEPT_AUDIT.getStepDay());//逾期天数
            return accepAuditService.getAccepAuditList(req);
        } catch (Exception e) {
            logger.error("查询工程列表失败！",e);
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
        String projId = request.getParameter("projId");
        //审核详述查询
        modelView.addObject("data",accepAuditService.getProjectById(projId));
        modelView.addObject("currentLevel",request.getParameter("currentLevel"));			//当前审核级别
        modelView.addObject("isAudit",request.getParameter("isAudit"));				 		//是否审核过
        modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
        LoginInfo loginInfo = SessionUtil.getLoginInfo();
        modelView.addObject("loginInfo",loginInfo);											//登录人信息
        modelView.addObject("mrResult", MrResultEnum.values());					 			//审核结果
        modelView.addObject("stepId",StepEnum.PROJECT_ACCEPT.getValue());			//上一个页面的步骤id
        modelView.setViewName("accept/acceptAuditPage");
        return modelView;
    }

    /**
     * 保存审核信息
     * @param manageRecord
     * @return
     */
    @RequestMapping(value = "/auditSave")
    @ResponseBody
    public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){

        try {
           manageRecordService.accepAudit(manageRecord,"","", WorkFlowActionEnum.PROJECT_ACCEPT_AUDIT.getActionCode(), Constants.MODULE_CODE_PROJECTAPPROVAL);
            return Constants.OPERATE_RESULT_SUCCESS;
        } catch (Exception e) {
            logger.error("受理申请审核意见保存失败！",e);
            return Constants.OPERATE_RESULT_FAILURE;
        }

    }

    /**
     * 管理记录列表查询
     * @author pengtt
     * @createTime
     * @param manageRecordQueryReq
     * @return
     */
    @RequestMapping(value = "/queryManageRecord")
    @ResponseBody
    public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
        try {
            manageRecordQueryReq.setStepId(StepEnum.PROJECT_ACCEPT_AUDIT.getValue());	//方案审核
            return manageRecordService.queryManageRecord(manageRecordQueryReq);
        } catch (Exception e) {
            logger.error("管理记录列表查询失败！",e);
            return null;
        }
    }

    /**
     * 查询弹出
     * @param request
     * @return
     */
    @RequestMapping(value="/surveySearchPage")
    public ModelAndView subContractAuditSearchPage(HttpServletRequest request){
        ModelAndView modelView=new ModelAndView();
        modelView.setViewName("accept/acceptAuditSearchPopPage");
        return modelView;
    }
}
