package cc.dfsoft.project.biz.base.plan.controller;

import cc.dfsoft.project.biz.base.accept.service.AccepAuditService;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * 安装任务
 */
@Controller
@RequestMapping(value="/insttasks")
public class InsttasksController {
    /** 日志实例 */
    private static Logger logger= LoggerFactory.getLogger(InsttasksController.class);
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectDao projectDao;

    @Resource
    WorkFlowService workFlowService;
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
        modelView.addObject("notThrough",ProjStatusEnum.TO_AUDIT_INSTASKS.getValue());

        modelView.setViewName("plan/insttasksList");
        return modelView;
    }

    /**
     * 查询工程列表
     * @author
     * @createTime
     * @param
     * @return
     */
    @RequestMapping(value = "/queryInsttasks")
    @ResponseBody
    public Map<String,Object> queryInsttasks(ProjectQueryReq req){
        try {
            req.setProjStatusId(ProjStatusEnum.TO_INST_TASKS.getValue());//待安装任务
/*            req.setStepId(StepEnum.PROJECT_INST_TASKS.getValue());//待安装任务
            req.setTimeLimit(StepEnum.PROJECT_INST_TASKS.getStepDay());//逾期天数*/
            return projectDao.queryProject(req);
        } catch (Exception e) {
            logger.error("查询工程列表失败！",e);
            return null;
        }
    }


    /**
     * 打开右侧
     * @return
     */
    @RequestMapping(value="/viewPage")
    public ModelAndView viewPage(HttpServletRequest request){
        ModelAndView modelView=new ModelAndView();
        modelView.addObject("stepId", StepEnum.AUDIT_INST_TASKS.getValue());
        modelView.setViewName("plan/insttasksListRight");
        return modelView;
    }

    /**
     * 弹屏查询
     * @return
     */
    @RequestMapping(value = "/instTasksSearchPopPage")
    public ModelAndView instTasksSearchPopPage() {
        ModelAndView modelview = new ModelAndView();
        modelview.setViewName("plan/insttasksSearchPopPage");
        return modelview;
    }

    /**
     * 选中行时，查询详述
     */
    @RequestMapping(value="/viewInsttasks")
    @ResponseBody
    public Project viewInsttasks(@RequestParam(required=true)String id){
        try{
            Project project= projectService.getInsttasks(id);
            return project;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存安装计划
     * @author wangang
     * @createTime 2018-12-12
     * @param
     * @return
     */
    @RequestMapping(value = "/saveInstTasks")
    @ResponseBody
    public String saveInstTasks(@RequestBody(required = true) Project project){
        try {
            projectService.saveInstTasks(project);
            return Constants.OPERATE_RESULT_SUCCESS;
        }catch(HibernateOptimisticLockingFailureException e ){
            logger.error("版本冲突，需刷新页面！", e);
            return "already";
        }catch (Exception e) {
            logger.error("安装计划信息保存失败！", e);
            return Constants.OPERATE_RESULT_FAILURE;
        }
    }


}
