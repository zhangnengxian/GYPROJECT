package cc.dfsoft.project.biz.base.station.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.CustomerDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.Customer;
import cc.dfsoft.project.biz.base.complete.dao.JointAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.constructmanage.dao.ProgressDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.Progress;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementDeclarationDao;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.station.dto.StationProceduresDto;
import cc.dfsoft.project.biz.base.station.service.StationAcceptService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * Created by cui on 2017/8/28.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class StationAcceptServiceImpl implements StationAcceptService {

    @Resource
    ProjectDao projectDao;
    @Resource
    ProjectService projectService;
    /** 业务操作记录服务接口 */
    @Resource
    OperateRecordService operateRecordService;
    @Resource
    DesignInfoDao designInfoDao;
    @Resource
    SubContractDao subContractDao;
    @Resource
    SettlementDeclarationDao settlementDeclarationDao;
    @Resource
    JointAcceptanceDao jointAcceptanceDao;
    @Resource
    ProgressDao progressDao;
    @Resource
    DepartmentDao departmentDao;
    @Resource
    CustomerDao customerDao;
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String acceptSave(Project project) {
        LoginInfo login = SessionUtil.getLoginInfo();

        if (StringUtils.isBlank(project.getProjId())) {
            // 该工程编号已存在
            if (projectDao.findByProjNo(project.getProjNo()).size() > 0) {
                return "exist";
            }
            // 新增对象 PROJ_SOURCE
            String projId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);// 生成唯一ID
            project.setProjId(projId);
            project.setCorpId(login.getCorpId());
            project.setTenantId(SessionUtil.getTenantId());//暂时存上
            project.setOrgId(login.getOrgId());
            project.setCorpName(login.getCorpName());
            String projNo = projectService.getProjMaxNo(login.getCorpId(), project.getProjectType(), project.getContributionMode());
            if (projNo.equals("noneNumber")) {
                return "noneNumber";
            }
            if("1".equals(project.getArea())){//遵义新蒲新区的工程，工程编号前加“新”
				projNo = Constants.ZUNYI_XINPU_PROJNO_PREX+projNo;
			}
            project.setProjNo(projNo);
            project.setArea(StringUtil.isNotBlank(project.getArea())?project.getArea():"");
            project.setAcceptDate(projectDao.getDatabaseDate()); // 受理日期
//            project.setProjSource(ProjSourceEnum.HALL.getValue()); // 受理来源

            // 形成操作记录
            String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
            operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(),
                    StepEnum.STATION_ACCEPT.getValue(), StepEnum.STATION_ACCEPT.getMessage(), "");
        }
        Customer customer;
		if(StringUtils.isNotBlank(project.getCustId())){
			customer = new Customer();
			customer = customerDao.get(project.getCustId());
			
		}else{
			//没有客户ID的，默认燃气公司为客户
			Department department = departmentDao.get(project.getCorpId());
			customer = customerDao.queryByCustCode(department.getDeptOutCode());
			if(customer==null){
				customer = new Customer();
				customer.setCustId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));//客户ID
				customer.setCustName(department.getDeptName());//客户名称
				customer.setCustCode(department.getDeptOutCode());//客户编码
				customerDao.saveOrUpdate(customer);
			}
			project.setCustId(customer.getCustId());
		}
        projectDao.saveOrUpdate(project);
        return Constants.OPERATE_RESULT_SUCCESS;
    }

    @Override
    public Map<String, Object> queryAcceptProject(ProjectQueryReq projectQueryReq) throws ParseException {
        return projectDao.queryProject(projectQueryReq);
    }

    @Override
    public StationProceduresDto viewStationProject(String projId) throws ParseException {
        Project p = projectDao.get(projId);
        //场站数据的一个集合（有好办法时待优化。。。）
        StationProceduresDto sp = new StationProceduresDto();
        //工程信息
        sp.setProjId(p.getProjId());
        sp.setProjNo(p.getProjNo());
        sp.setProjStatusDes(p.getProjStatusDes());
        sp.setProjName(p.getProjName());
        sp.setProjAddr(p.getProjAddr());
        sp.setProjScaleDes(p.getProjScaleDes());
        sp.setCorpName(p.getCorpName());
        sp.setDeptId(p.getDeptId());
        sp.setAcceptDate(p.getAcceptDate());
        sp.setProjectType(p.getProjectType());
        sp.setContributionMode(p.getContributionMode());
        sp.setTotalInvestment(p.getTotalInvestment());
        sp.setProtocolStartingDate(p.getProtocolStartingDate());
        sp.setProjectDuration(p.getProjectDuration());
        sp.setAcceptReason(p.getAcceptReason());
        sp.setProjLtypeId(p.getProjLtypeId());
        sp.setSettlementDate(p.getSettlementDate());
        //地勘信息
        sp.setExplorationUnit(p.getExplorationUnit());
        sp.setEuNo(p.getEuNo());
        sp.setEuAmount(p.getEuAmount());
        sp.setEuDeadline(p.getEuDeadline());
        sp.setEuDate(p.getEuDate());
        //场站资料
        sp.setAcceptData(p.getAcceptData());
        sp.setProceduresData(p.getProceduresData());
        sp.setContructionData(p.getContructionData());
        sp.setSettlementData(p.getSettlementData());
        sp.setFinalAccountData(p.getFinalAccountData());
        //设计信息
        DesignInfo di = designInfoDao.queryInfoByProjId(projId);
        if(di!=null){
            sp.setDuName(di.getDuName());
            sp.setDuDeadline(di.getDuDeadline());
            sp.setDuCompleteDate(di.getDuCompleteDate());
        }
        //合同信息
        SubContract sc = subContractDao.findSubContractByprojId(projId);
        if(sc!=null){
            sp.setCuName(sc.getCuName());
            sp.setScAmount(sc.getScAmount());
            sp.setScPlannedTotalDays(sc.getScPlannedTotalDays());
            sp.setScSignDate(sc.getScSignDate());
        }
        //结算信息
        SettlementDeclaration sd = settlementDeclarationDao.findByProjId(projId);
        if(null!=sd) {
            sp.setEndDeclarationCost(sd.getEndDeclarationCost());
        }
        //验收信息
        List<JointAcceptance> jas = jointAcceptanceDao.findById(projId);
        if(null!=jas && jas.size()>0){
            JointAcceptance ja = jas.get(0);
            sp.setJaDate(ja.getJaDate());//验收时间
            sp.setPilotRunDate(ja.getPilotRunDate());//试运行时间
        }
        //工程进度
        Progress progress = progressDao.queryGraphicProgress(projId);
        sp.setNuitProject(progress.getNuitProject());
        sp.setFinishProgress(progress.getFinishProgress());
        return sp;
    }

}
