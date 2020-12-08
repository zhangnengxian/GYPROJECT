package cc.dfsoft.project.biz.base.station.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementDeclarationDao;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.station.dto.StationProceduresDto;
import cc.dfsoft.project.biz.base.station.service.StationSettlementService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
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
public class StationSettlementServiceImpl implements StationSettlementService {

    @Resource
    ProjectDao projectDao;
    @Resource
    ProjectService projectService;
    @Resource
    DesignInfoDao designInfoDao;
    @Resource
    SubContractDao subContractDao;
    @Resource
    SettlementDeclarationDao settlementDeclarationDao;
    @Resource
    WorkFlowService workFlowService;
    /** 业务操作记录服务接口 */
    @Resource
    OperateRecordService operateRecordService;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void stationSettlementSave(StationProceduresDto stationProceduresDto) throws ParseException {

        //保存结算信息
        SettlementDeclaration settlementDeclaration = settlementDeclarationDao.findByProjId(stationProceduresDto.getProjId());
        if(null!=settlementDeclaration){
            settlementDeclaration.setEndDeclarationCost(stationProceduresDto.getEndDeclarationCost());
            settlementDeclarationDao.update(settlementDeclaration);
        }else{
            SettlementDeclaration sd1 = new SettlementDeclaration();
            sd1.setSdId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
            sd1.setProjId(stationProceduresDto.getProjId());
            sd1.setEndDeclarationCost(stationProceduresDto.getEndDeclarationCost());//结算金额
            settlementDeclarationDao.save(sd1);
        }
        //保存工程信息
        Project project = projectDao.get(stationProceduresDto.getProjId());
        project.setSettlementDate(projectDao.getDatabaseDate());//结算时间
        project.setSettlementData(stationProceduresDto.getSettlementData());//结算资料
        project.setFinalAccountData(stationProceduresDto.getFinalAccountData());//决算资料
        if(stationProceduresDto.isFlag()==true){
            String statusId=workFlowService.queryProjStatusId(project.getCorpId(), project.getProjectType(),project.getContributionMode(), WorkFlowActionEnum.GETSTATION_SETTLEMENT.getActionCode(), true);
            project.setProjStatusId(statusId);

        // 形成操作记录
        String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
        operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(),
                StepEnum.GETSTATION_SETTLEMENT.getValue(), StepEnum.GETSTATION_SETTLEMENT.getMessage(), "");
        }
        projectDao.update(project);
    }

}
