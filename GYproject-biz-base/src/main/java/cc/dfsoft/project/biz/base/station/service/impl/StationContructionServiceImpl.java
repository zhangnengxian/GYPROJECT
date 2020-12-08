package cc.dfsoft.project.biz.base.station.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.complete.dao.JointAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.constructmanage.dao.ProgressDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.Progress;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.station.dto.StationProceduresDto;
import cc.dfsoft.project.biz.base.station.service.StationContructionService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * Created by cui on 2017/8/28.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class StationContructionServiceImpl implements StationContructionService {

    @Resource
    ProjectDao projectDao;
    @Resource
    ProjectService projectService;
    @Resource
    WorkFlowService workFlowService;
    @Resource
    ProgressDao progressDao;
    /** 业务操作记录服务接口 */
    @Resource
    OperateRecordService operateRecordService;
    @Resource
    JointAcceptanceDao jointAcceptanceDao;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void stationContructionSave(StationProceduresDto stationProceduresDto) throws ParseException {
        //保存工程进度
        Progress progress = new Progress();
        progress.setProgressId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
        progress.setProjId(stationProceduresDto.getProjId());
        progress.setNuitProject(stationProceduresDto.getNuitProject()); //工序
        progress.setFinishProgress(stationProceduresDto.getFinishProgress());//完成进度
        progress.setRegisterDate(progressDao.getDatabaseDate());
        progressDao.save(progress);
        //保存工程信息-施工资料
        Project project = projectDao.get(stationProceduresDto.getProjId());
        project.setContructionData(stationProceduresDto.getContructionData());//施工资料
        String pqr=progress.getFinishProgress();
        if(pqr!=null&&pqr!=""){
            project.setTotalProgress(pqr.substring(0, pqr.length()-1));//工程总进度
        }
        if(stationProceduresDto.isFlag()==true){
            String statusId=workFlowService.queryProjStatusId(project.getCorpId(), project.getProjectType(),project.getContributionMode(), WorkFlowActionEnum.STATION_CONSTRUCTION.getActionCode(), true);
            project.setProjStatusId(statusId);//工程状态
            
            
         // 形成操作记录
            String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
            operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(),
                    StepEnum.STATION_CONSTRUCTION.getValue(), StepEnum.STATION_CONSTRUCTION.getMessage(), "");
            
            
        }
        projectDao.update(project);
        //保存验收信息
        List<JointAcceptance> jointAcceptances = jointAcceptanceDao.findById(stationProceduresDto.getProjId());
        if(null!=jointAcceptances && jointAcceptances.size()>0){
            JointAcceptance jointAcceptance = jointAcceptances.get(0);
            jointAcceptance.setJaDate(stationProceduresDto.getJaDate());//验收时间
            jointAcceptance.setPilotRunDate(stationProceduresDto.getPilotRunDate());//试运行时间
            jointAcceptanceDao.update(jointAcceptance);
        }else{
            JointAcceptance jointAcceptance1 = new JointAcceptance();
            jointAcceptance1.setJaId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
            jointAcceptance1.setProjId(stationProceduresDto.getProjId());
            jointAcceptance1.setProjNo(stationProceduresDto.getProjNo());
            jointAcceptance1.setJaDate(stationProceduresDto.getJaDate());//验收时间
            jointAcceptance1.setPilotRunDate(stationProceduresDto.getPilotRunDate());//试运行时间
            jointAcceptanceDao.save(jointAcceptance1);
        }
    }

}
