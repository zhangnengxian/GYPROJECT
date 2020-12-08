package cc.dfsoft.project.biz.base.station.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.station.dto.StationProceduresDto;
import cc.dfsoft.project.biz.base.station.service.StationProceduresService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * Created by cui on 2017/8/28.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class StationProceduresServiceImpl implements StationProceduresService {

    @Resource
    ProjectDao projectDao;
    @Resource
    ProjectService projectService;
    @Resource
    DesignInfoDao designInfoDao;
    @Resource
    SubContractDao subContractDao;
    @Resource
    WorkFlowService workFlowService;
    /** 业务操作记录服务接口 */
    @Resource
    OperateRecordService operateRecordService;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void stationProceduresSave(StationProceduresDto stationProceduresDto) throws ParseException {

        //保存设计信息
        DesignInfo designInfo = designInfoDao.queryInfoByProjId(stationProceduresDto.getProjId());
        if(null!=designInfo){
            designInfo.setDuId(stationProceduresDto.getDuId());//设计院id
            designInfo.setDuName(stationProceduresDto.getDuName());//设计院名称
            designInfo.setDuCompleteDate(stationProceduresDto.getDuCompleteDate());//设计完成时间
            designInfo.setDuDeadline(stationProceduresDto.getDuDeadline());//设计时限
            designInfoDao.update(designInfo);
        }else{
            DesignInfo designInfo1 = new DesignInfo();
            designInfo1.setDiId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
            designInfo1.setProjId(stationProceduresDto.getProjId());
            designInfo1.setDuId(stationProceduresDto.getDuId());//设计院id
            designInfo1.setDuName(stationProceduresDto.getDuName());//设计院名称
            designInfo1.setDuCompleteDate(stationProceduresDto.getDuCompleteDate());//设计完成时间
            designInfo1.setDuDeadline(stationProceduresDto.getDuDeadline());//设计时限
            designInfo1.setTenantId(stationProceduresDto.getTenantId());
            designInfo1.setOrgId(stationProceduresDto.getOrgId());
            designInfoDao.save(designInfo1);
        }
        //保存施工信息
        SubContract subContract = subContractDao.findSubContractByprojId(stationProceduresDto.getProjId());
        if(null!=subContract){
            subContract.setCuId(stationProceduresDto.getCuId());
            subContract.setCuName(stationProceduresDto.getCuName());
            subContract.setScAmount(stationProceduresDto.getScAmount());
            subContract.setScPlannedTotalDays(stationProceduresDto.getScPlannedTotalDays());
            subContract.setScSignDate(stationProceduresDto.getScSignDate());
            subContractDao.update(subContract);
        }else{
            SubContract subContract1 = new SubContract();
            subContract1.setScId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
            subContract1.setScNo(stationProceduresDto.getProjNo());
            subContract1.setProjId(stationProceduresDto.getProjId());
//            subContract.setCuId(stationProceduresDto.getCuId());
            subContract1.setCuName(stationProceduresDto.getCuName());
            subContract1.setScAmount(stationProceduresDto.getScAmount());
            subContract1.setScPlannedTotalDays(stationProceduresDto.getScPlannedTotalDays());
            subContract1.setScSignDate(stationProceduresDto.getScSignDate());
            subContractDao.save(subContract1);
        }
        
      //保存工程信息-地勘信息
        Project project = projectDao.get(stationProceduresDto.getProjId());
        project.setExplorationUnit(stationProceduresDto.getExplorationUnit()); //地勘单位
        project.setEuAmount(stationProceduresDto.getEuAmount());//地勘合同金额
        project.setEuDate(stationProceduresDto.getEuDate());//地勘完工日期
        project.setEuNo(stationProceduresDto.getEuNo());//地勘合同号
        project.setEuDeadline(stationProceduresDto.getEuDeadline());//地勘时限
        project.setProceduresData(stationProceduresDto.getProceduresData());//建审资料
        if(stationProceduresDto.isFlag()==true) {
            String statusId = workFlowService.queryProjStatusId(project.getCorpId(), project.getProjectType(), project.getContributionMode(), WorkFlowActionEnum.AUDIT_PROCEDURES.getActionCode(), true);
            project.setProjStatusId(statusId);
            
            // 形成操作记录
            String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
            operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(),
                    StepEnum.AUDIT_PROCEDURES.getValue(), StepEnum.AUDIT_PROCEDURES.getMessage(), "");
            //调用工程信息同步接口todo:
            
            //调用施工合同接口todo:
        }
        projectDao.update(project);
    }

}
