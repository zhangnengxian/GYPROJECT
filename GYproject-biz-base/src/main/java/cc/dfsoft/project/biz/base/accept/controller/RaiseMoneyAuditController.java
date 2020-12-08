
package cc.dfsoft.project.biz.base.accept.controller;


import cc.dfsoft.project.biz.base.accept.service.RaiseMoneyAuditService;
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
 * @desc: 提资审核
 * @author: zhangnx
 * @date: 10:19 2018/9/14
 * @version: V1.0
 */
@Controller
@RequestMapping(value="/rasieMoneyAuditController")
public class RaiseMoneyAuditController {

    private static  Logger logger = LoggerFactory.getLogger(RaiseMoneyAuditController.class);

    @Resource
    RaiseMoneyAuditService raiseMoneyAuditService;
    @Resource
    ManageRecordService manageRecordService;
    @Resource
    DocTypeService docTypeService;



   /**
    * @desc: 主页面
    * @author: zhangnx
    * @date: 10:19 2018/9/14
    */
    @RequestMapping(value="/main")
    public ModelAndView main(){
        ModelAndView modelView=new ModelAndView();
        modelView.setViewName("accept/raiseMoneyAudit");
        return modelView;
    }

    /**
     * @desc: 获取待审核工程列表
     * @author: zhangnx
     * @date: 10:20 2018/9/14
     */
    @RequestMapping(value = "/getAuditProject")
    @ResponseBody
    public Map<String,Object> getAuditProject(HttpServletRequest request,ProjectQueryReq req){
        try {
            req.setProjStatusId(ProjStatusEnum.TO_RAISEMONEY_AUDIT.getValue());//待提资审核
            req.setStepId(StepEnum.RAISEMONEY_AUDIT.getValue());//提资审核
            req.setTimeLimit(StepEnum.RAISEMONEY_AUDIT.getStepDay());//逾期天数
            return raiseMoneyAuditService.getAuditProject(req);
        } catch (Exception e) {
            logger.error("查询工程列表失败！",e);
            return null;
        }
    }



    /**
     * @desc: 打开审核屏
     * @author: zhangnx
     * @date: 10:20 2018/9/14
     */
    @RequestMapping(value="/auditPage")
    public ModelAndView audit(HttpServletRequest request){

        ModelAndView modelView=new ModelAndView();
        String projId = request.getParameter("projId");
        //审核详述查询
        modelView.addObject("data",raiseMoneyAuditService.getProjectById(projId));
        modelView.addObject("currentLevel",request.getParameter("currentLevel"));			//当前审核级别
        modelView.addObject("isAudit",request.getParameter("isAudit"));				 		//是否审核过
        modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间

        LoginInfo loginInfo = SessionUtil.getLoginInfo();
        modelView.addObject("loginInfo",loginInfo);											//登录人信息
        modelView.addObject("mrResult", MrResultEnum.values());					 			//审核结果
        modelView.addObject("stepId",StepEnum.RAISEMONEY_APPLY.getValue());			//上一个页面的步骤id
        modelView.setViewName("accept/raiseMoneyAuditPage");
        return modelView;
    }

    /**
     * @desc: 审核保存
     * @author: zhangnx
     * @date: 10:20 2018/9/14
     */
    @RequestMapping(value = "/auditSave")
    @ResponseBody
    public String auditSave(@RequestBody(required = true) ManageRecord manageRecord){
       try {
            manageRecordService.accepAudit(manageRecord,"","", WorkFlowActionEnum.RAISEMONEY_AUDIT.getActionCode(), Constants.MODULE_CODE_PROJECTAPPROVAL);
            return Constants.OPERATE_RESULT_SUCCESS;
        } catch (Exception e) {
            logger.error("提资申请审核意见保存失败！",e);
            return Constants.OPERATE_RESULT_FAILURE;
        }
    }



    /**
     * @desc: 管理记录查询
     * @author: zhangnx
     * @date: 10:21 2018/9/14
     */
    @RequestMapping(value = "/queryManageRecord")
    @ResponseBody
    public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
        try {
            manageRecordQueryReq.setStepId(StepEnum.RAISEMONEY_AUDIT.getValue());	//方案审核
            return manageRecordService.queryManageRecord(manageRecordQueryReq);
        } catch (Exception e) {
            logger.error("管理记录列表查询失败！",e);
            return null;
        }
    }









}
	  
	
	
