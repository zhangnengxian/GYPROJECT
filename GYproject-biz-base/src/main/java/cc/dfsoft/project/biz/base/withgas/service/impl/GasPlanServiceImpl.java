package cc.dfsoft.project.biz.base.withgas.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.withgas.dao.GasPlanDao;
import cc.dfsoft.project.biz.base.withgas.dao.GasProjectDao;
import cc.dfsoft.project.biz.base.withgas.dto.GasPlanReq;
import cc.dfsoft.project.biz.base.withgas.dto.GasProjectReq;
import cc.dfsoft.project.biz.base.withgas.entity.GasPlan;
import cc.dfsoft.project.biz.base.withgas.entity.GasProject;
import cc.dfsoft.project.biz.base.withgas.enums.GasProjectStatusEnum;
import cc.dfsoft.project.biz.base.withgas.service.GasPlanService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 带气工程服务接口实现
 * @author cui
 * @createTime 2017-08-8
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class GasPlanServiceImpl implements GasPlanService {

    /**
     * 带气工程Dao
     */
    @Resource
    GasProjectDao gasProjectDao;

    /**带气计划Dao*/
    @Resource
    GasPlanDao gasPlanReqDao;

    /**工程Dao*/
    @Resource
    ProjectDao projectDao;

    /**分包合同Dao*/
    @Resource
    SubContractDao subContractDao;
    @Resource
    OperateRecordService operateRecordService;

    @Override
    public Map<String, Object> queryGasProject(GasProjectReq gasProjectReq) throws ParseException {
//
//        if(StringUtil.isBlank(gasProjectReq.getGpId())){
//            Map<String, Object> map = gasProjectDao.queryGasProject(gasProjectReq);
//            List<GasProject> gasProjects = new ArrayList<>();
//            List<String> statusList=new ArrayList();
//            List<ProjStatusEnum> enums=ProjStatusEnum.getThanValue(ProjStatusEnum.UNION_PRE_INSPECTION.getValue(), ProjStatusEnum.ALREADY_COMPLETED.getValue());
//            for(ProjStatusEnum projStatusEnum:enums){
//                statusList.add(projStatusEnum.getValue());
//            }
//            ProjectQueryReq projectQueryReq = new ProjectQueryReq();
//            List<Project> projects = (List<Project>) projectDao.queryProjectByStatus(projectQueryReq,statusList).get("data");
//
//            for(Project project:projects){
//                GasProject gasProject1 = gasProjectDao.get("projId" ,project.getProjId());
//                if(gasProject1==null){
//                    GasProject gasProject = new GasProject();
//                    gasProject.setProjLtypeId(project.getProjLtypeId());
//                    gasProject.setProjectTypeDes(project.getProjectTypeDes());
//                    gasProject.setCustName(project.getCustName());
//                    gasProject.setProjName(project.getProjName());
//                    gasProject.setProjAddr(project.getProjAddr());
//                    gasProject.setProjNo(project.getProjNo());
//                    gasProject.setProjId(project.getProjId());
//                    gasProject.setProjScaleDes(project.getProjScaleDes());
//                    SubContract subContract = subContractDao.findSubContractByprojId(project.getProjId());
//                    if(subContract!=null){
//                        gasProject.setGasComLegalRepresent(subContract.getGasComLegalRepresent());
//                        gasProject.setScNo(subContract.getScNo());
//                        gasProject.setCuName(subContract.getCuName());
//                    }
//                    gasProjects.add(gasProject);
//                }
//            }
//            map.put("data",gasProjects);
//            return map;
//        }
        return gasProjectDao.queryGasProject(gasProjectReq);
    }

    @Override
    public Map<String, Object> queryGasPlan(GasPlanReq gasPlanReqReq) {
        return gasPlanReqDao.queryGasPlan(gasPlanReqReq);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String saveGasPlan(GasPlanReq gasPlanReq) {
        LoginInfo loginInfo = SessionUtil.getLoginInfo();

        try {
            GasPlan gasPlan = gasPlanReq.getGasPlan();
            gasPlan.setCorpId(loginInfo.getCorpId());
            if(StringUtils.isBlank(gasPlan.getGpId())){
                gasPlan.setGpId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTTOUCH));
            }
            gasPlanReqDao.saveOrUpdate(gasPlan);
            gasProjectDao.deleteByGpId(gasPlan.getGpId());

            if(gasPlanReq.getGasProjects().size()>0){
                //批量插入记录数据
                List<GasProject> gasProjects = gasPlanReq.getGasProjects();
                List<GasProject> listNew = new ArrayList<>();
                List<String> projIds=new ArrayList<>();
                for(GasProject gp : gasProjects){
                    projIds.add(gp.getProjId());
                    if(gp.getGasProjStatus().equals(GasProjectStatusEnum.GAS_PLAN.getValue())){
                        gp.setGasProjStatus(GasProjectStatusEnum.GAS_OPEN.getValue());
                    }
                    gp.setGpId(gasPlan.getGpId());
                    listNew.add(gp);
                  //待办todo：
                    Project p = projectDao.get(gp.getProjId());
            		operateRecordService.cerateCurOperateRecord(p,gp.getGasProjStatus(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue(),gp.getGpId(),new Staff(),null,true);
                }
                //将编已编制计划的工程的待办置为无效
                operateRecordService.noticeSetInvalid(projIds,WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(),AssistProgressTypeEnum.GAS_PROGRESS.getValue());

                gasProjectDao.batchInsertOrUpdateObjects(listNew);
            }
            return Constants.OPERATE_RESULT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Date getDatabaseDate() {
        return gasPlanReqDao.getDatabaseDate();
    }
}
