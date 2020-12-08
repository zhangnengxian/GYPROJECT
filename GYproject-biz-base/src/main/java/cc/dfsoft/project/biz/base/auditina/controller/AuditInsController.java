package cc.dfsoft.project.biz.base.auditina.controller;

import cc.dfsoft.project.biz.base.auditina.dto.AuditInsReq;
import cc.dfsoft.project.biz.base.auditina.service.AuditInsService;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

/**
 * 报验单审核
 */
@Controller
@RequestMapping(value="/auditIns")
public class AuditInsController {
    private static Logger logger = LoggerFactory.getLogger(AuditInsController.class);
    @Autowired
    private AuditInsService auditInsService;
    @Autowired
    private ManageRecordService manageRecordService;
    @Autowired
    private ProjectChecklistService proCheckListService;
    @Autowired
    private DocTypeService docTypeService;

    @RequestMapping(value="/main")
    public ModelAndView main() {
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("auditIns/auditInsJsp");
        return modelView;
    }

    /**
     * 获取需要审核的数据
     * @param req
     * @return
     */
    @RequestMapping(value = "/getDataList")
    @ResponseBody
    public Map<String,Object> getDataList(AuditInsReq req){
        try {
            req.setTimeLimit(StepEnum.PROJECT_ACCEPT_AUDIT.getStepDay());//逾期天数
            return auditInsService.getDataList(req);
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
        String pcid = request.getParameter("projId");
        //审核详述查询
        ProjectChecklist data = proCheckListService.viewProjectCheckListFile(pcid,"报验审核");
        modelView.addObject("pcid",pcid);
        modelView.addObject("currentLevel",request.getParameter("currentLevel"));			//当前审核级别
        modelView.addObject("isAudit",request.getParameter("isAudit"));				 		//是否审核过
        modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
        LoginInfo loginInfo = SessionUtil.getLoginInfo();
        modelView.addObject("loginInfo",loginInfo);											//登录人信息
        modelView.addObject("mrResult", MrResultEnum.values());					 			//审核结果
        modelView.addObject("imgUrl", Constants.DISK_PATH+Constants.SIGN_DISK_PATH);
        modelView.addObject("data", data);
        modelView.setViewName("auditIns/auditInsPage");
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
            manageRecord.setBusinessOrderId(manageRecord.getPcId());
            manageRecordService.saveAuditIns(manageRecord, Constants.MODULE_CODE_PROCESS);
            return Constants.OPERATE_RESULT_SUCCESS;
        }catch (ExpressException ex) {
            logger.error("报验审核："+ex.getMessage(),ex);
            return ex.getErrorCode();
        }catch (Exception e) {
            logger.error("报验审核意见保存失败！",e);
            return Constants.OPERATE_RESULT_FAILURE;
        }
    }

    /**
     * 审核历史列表查询
     * @author 
     * @createTime 
     * @param
     * @return
     */
    @RequestMapping(value = "/queryManageRecord")
    @ResponseBody
    public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
        try {
            return manageRecordService.queryManageRecord(manageRecordQueryReq);
        } catch (Exception e) {
            logger.error("报验审核历史查询失败！",e);
            return null;
        }
    }
}
