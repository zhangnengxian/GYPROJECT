package cc.dfsoft.project.biz.base.mapper;


import cc.dfsoft.project.biz.base.baseinfo.entity.ProjStatus;
import cc.dfsoft.project.biz.base.mapper.entity.SendTaskLog;
import cc.dfsoft.project.biz.base.mapper.service.SendTaskLogService;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IPUtil;
import cc.dfsoft.uexpress.common.util.JpushUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;
import com.fr.stable.core.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LThread extends Thread {
    private static Logger logger = LoggerFactory.getLogger(LThread.class);
    private OperateRecordService operateRecordService = null;
    private ProjectService projectService = null;
    private StaffService staffService = null;
    private SendTaskLogService sendTaskLogService = null;

    //在线程中无法通过RequestContextHolder获取request对象。需要从切面类中传递
    HttpSession session = null;

    private String menuName = "";

    private String description = "";

    private String message = "";

    private String uri = "";


    public LThread(OperateRecordService operateRecordService, ProjectService projectService, StaffService staffService,
                   SendTaskLogService sendTaskLogService, HttpSession session, String menuName, String description, String message, String uri) {
        this.operateRecordService = operateRecordService;
        this.projectService = projectService;
        this.staffService = staffService;
        this.sendTaskLogService = sendTaskLogService;
        this.session = session;
        this.menuName = menuName;
        this.description = description;
        this.message = message;
        this.uri = uri;
    }

    /**
     * 启动线程
     */
    public void run() {
        sendTask(menuName,description);
    }

    /**
     * 发送待办
     * @param menuName
     * @param description
     */
    private void sendTask(String menuName, String description){
        //设备号信息集合
        List<Map<String, Object>> users = null;
        String messages = "";
        String t = (String) session.getAttribute("SENDSTAKTYPE");
        String type = "";
        if(StringUtil.isBlank(t)){
            return;
        }else {
            type = t;
            session.removeAttribute("SENDSTAKTYPE");
        }
        List<Map<String, Object>> messagelist = Constants.getConstantsMapByKey(Constants.SEND_TASK_MESSAGE);
        if(messagelist !=null || messagelist.size()>0){
            for(Map<String,Object> mp : messagelist){
                if(message.equals(String.valueOf(mp.get("ID")))){
                    messages = String.valueOf(mp.get("CNVALUE"));
                    break;
                }
            }
        }
        if(messages == ""){
            //消息默认形式
            messages = "您好:【%u】,工程系统【%p】工程在【%s】环节有待处理的任务,请及时处理。";
        }
        //登录人
        LoginInfo loginInfo = (LoginInfo) session.getAttribute(Constants.SESSION_LOGININFO);
        Project project = null;
        try {
            if("1".equals(type)){//cerateOperateWorkFlowRecord
                project = (Project) session.getAttribute("PROJECTRESULT");
                String stepid = (String) session.getAttribute("STEPID");
               /* HashMap<String,Object> rul =operateRecordService.cerateOperateWorkFlowRecord(project,stepid,loginInfo );
                //修改待办人
                projectService.updateToDoerById(String.valueOf(rul.get("NAME")),project.getProjId());
                if(StringUtil.isNotBlank(String.valueOf(rul.get("ID")))){
                    String [] ids = String.valueOf(rul.get("ID")).split(",");
                    users =  staffService.getUserByIds(ids);

                }*/
            }
            //循环发送消息
            if(users!=null && users.size()>0){
                for(Map<String,Object> m :users){
                    if(String.valueOf(m.get("REGISTRATIONID"))!=null && !"null".equals(String.valueOf(m.get("REGISTRATIONID")))
                            &&!"".equals(String.valueOf(m.get("REGISTRATIONID")))){
                        try {
                            //极光推送待办信息
                            messages = messages.replaceAll("%u",String.valueOf(m.get("STAFF_NAME"))).replaceAll("%p",project.getProjName())
                                    .replaceAll("%s", ProjStatusEnum.valueof(project.getProjStatusId()).getMessage());
                            String rul = JpushUtils.jpush(String.valueOf(m.get("REGISTRATIONID")),messages);
                            if("success".equals(rul)){
                                SendTaskLog sendTaskLog = new SendTaskLog();
                                sendTaskLog.setId(UUID.randomUUID().toString().replaceAll("-",""));
                                sendTaskLog.setUri(uri);
                                sendTaskLog.setUser(String.valueOf(m.get("LOGIN_ACCOUNT")));
                                sendTaskLog.setUsername(String.valueOf(m.get("STAFF_NAME")));
                                sendTaskLog.setResults("success");
                                sendTaskLog.setMessage(messages);
                                sendTaskLog.setCreatetime(sendTaskLogService.getDatabaseTime());
                                sendTaskLog.setLocalhostip(IPUtil.getLinuxLocalIp());
                                sendTaskLog.setMenuname(menuName);
                                sendTaskLog.setDescription(description);
                                sendTaskLogService.saveLog(sendTaskLog);
                            }else{
                                SendTaskLog sendTaskLog = new SendTaskLog();
                                sendTaskLog.setId(UUID.randomUUID().toString().replaceAll("-",""));
                                sendTaskLog.setUri(uri);
                                sendTaskLog.setUser(String.valueOf(m.get("LOGIN_ACCOUNT")));
                                sendTaskLog.setUsername(String.valueOf(m.get("STAFF_NAME")));
                                sendTaskLog.setResults("error");
                                sendTaskLog.setMessage(messages);
                                sendTaskLog.setCreatetime(sendTaskLogService.getDatabaseTime());
                                sendTaskLog.setLocalhostip(IPUtil.getLinuxLocalIp());
                                sendTaskLog.setError(rul);
                                sendTaskLog.setMenuname(menuName);
                                sendTaskLog.setDescription(description);
                                sendTaskLogService.saveLog(sendTaskLog);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
