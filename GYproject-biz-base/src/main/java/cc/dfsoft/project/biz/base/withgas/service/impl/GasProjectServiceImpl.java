package cc.dfsoft.project.biz.base.withgas.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.budget.service.ChangeManagementService;
import cc.dfsoft.project.biz.base.complete.dao.DivisionalAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.dao.JointAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.dto.DivisionalAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptance;
import cc.dfsoft.project.biz.base.complete.enums.AcceptanceAtateEnum;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.project.biz.base.constructmanage.enums.ChangeTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.design.enums.DesignChangeStateEnum;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectSignDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectTypeDao;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectSign;
import cc.dfsoft.project.biz.base.project.enums.IsGasEnum;
import cc.dfsoft.project.biz.base.project.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjectSignTypeEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.enums.SubBudgetIsPrintEnum;
import cc.dfsoft.project.biz.base.withgas.dao.GasPlanDao;
import cc.dfsoft.project.biz.base.withgas.dao.GasProjectDao;
import cc.dfsoft.project.biz.base.withgas.dto.GasProjectReq;
import cc.dfsoft.project.biz.base.withgas.entity.GasPlan;
import cc.dfsoft.project.biz.base.withgas.entity.GasProject;
import cc.dfsoft.project.biz.base.withgas.enums.GasProjectStatusEnum;
import cc.dfsoft.project.biz.base.withgas.service.GasProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONSerializer;

/**
 * 工程服务接口实现
 *
 * @author cui
 * @createTime 2017-08-8
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class GasProjectServiceImpl implements GasProjectService {

	/**
	 * 审核级别Dao
	 */
	@Resource
	DocTypeDao docTypeDao;

	/**
	 * 带气工程Dao
	 */
	@Resource
	GasProjectDao gasProjectDao;

	/**
	 * 管理记录dao
	 */
	@Resource
	ManageRecordDao managerecorddao;

	/**
	 * 管理记录服务接口
	 */
	@Resource
	ManageRecordService manageRecordService;

	@Resource
	StaffDao staffDao;

	@Resource
	DepartmentDao departmentDao;

	@Resource
	ProjectTypeDao projectTypeDao;

	@Resource
	SubContractDao subContractDao;

	@Resource
	ProjectDao projectDao;

	@Resource
	DivisionalAcceptanceDao divisionalAcceptanceDao;

	@Resource
	JointAcceptanceDao jointAcceptanceDao;

	@Resource
	ContractDao contractDao;

	@Resource
	ConstructionPlanDao constructionPlanDao;

	@Resource
	GasPlanDao gasPlanDao;
    
	@Resource
	DocTypeService docTypeService;
	
	@Resource
	ProjectSignDao projectSignDao;
	@Resource
	ChangeManagementService cmService;
	
	/**工作流*/
	@Resource
	WorkFlowService workFlowService;
	@Resource
	OperateRecordService operateRecordService;
	
	
	@Override
    public Map<String, Object> queryGasAudit(GasProjectReq gasProjectReq) throws ParseException {

        String grade = null;
        //DocType docType = docTypeDao.findByStepId(StepOutWorkflowEnum.GAS_PROJECT_AUDIT.getValue());//暂写
        //grade = docType == null ? "" : docType.getGrade();
        
        DocType docType =new DocType();
        
        // 逾期天数 todo。。。。
//        SystemSet systemSet=systemSetDao.get("stepId", gasProjectQueryReq.getStepId());
//        if(systemSet!=null&& StringUtil.isNotBlank(systemSet.getTimeSpan())){
//            gasProjectQueryReq.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
//        }

        Map<String, Object> result = gasProjectDao.queryGasProject(gasProjectReq);
        List<GasProject> data = (List<GasProject>) result.get("data");
        //按步骤id进行查询 获取单据类型
        if (data != null && data.size() > 0) {
            /**
             * -1 未审核  0 审核未通过  1 审核通过  2待审核
             * 若该 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0","level3":"2"};
             * */
            //遍历循环  设置审核级别
            for (int i = 0; i < data.size(); i++) {
            	
				//查询所有的有效的
            	//去掉虚拟合同的标记
            	List<String> projectSignType = new ArrayList<String>();
				projectSignType.add(ProjectSignTypeEnum.SPECIAL.getValue());
				projectSignType.add(ProjectSignTypeEnum.UNPAID.getValue());
				projectSignType.add(ProjectSignTypeEnum.INCOMPLETE_COST.getValue());
				projectSignType.add(ProjectSignTypeEnum.NO_SUP_MONEY.getValue());
				List<ProjectSign> projectSignList=projectSignDao.findByProjIdAndStatus(data.get(i).getProjId(),IsSignEnum.IS_SIGN.getValue(),projectSignType);
				
				if(projectSignList!=null && projectSignList.size()>0){
					data.get(i).setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//是特殊工程
				}

                if(StringUtil.isNotBlank(data.get(i).getProjLtypeId())){
                    data.get(i).setProjectTypeDes(projectTypeDao.get(data.get(i).getProjLtypeId()).getProjTypeDes());
                }
                data.get(i).setGasProjStatusDes(GasProjectStatusEnum.valueof(data.get(i).getGasProjStatus()).getMessage());
                if(StringUtil.isNotBlank(data.get(i).getPreparDept())){
                    data.get(i).setPreparDeptDes(departmentDao.get(data.get(i).getPreparDept()).getDeptName());
                }
                if(StringUtil.isNotBlank(data.get(i).getPreparer())){
                    data.get(i).setPreparerDes(staffDao.get(data.get(i).getPreparer()).getStaffName());
                }
                Project pro=projectDao.get(data.get(i).getProjId());
				if(pro!=null){
					data.get(i).setProjectType(pro.getProjectType());
				}
                
				docType = docTypeService.findByStepId(StepOutWorkflowEnum.GAS_PROJECT_AUDIT.getValue(),pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
				
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					data.get(i).setLevel(docType.getGrade());// 设置审核总级数（通气审核2级审核）
					grade=docType.getGrade();
				}else{
					grade="0";
				}

                Map<String, String> levelBtn = new HashMap();
                //以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
                for (int n = 1; n < Integer.parseInt(grade) + 1; n++) {
                    if (n == 1) {
                        levelBtn.put("level" + n, "2");// 待审
                    } else {
                        levelBtn.put("level" + n, "-1");//未审
                    }
                }
//                ManageRecordQueryReq mrq = new ManageRecordQueryReq();
//                mrq.setBusinessOrderId(data.get(i).getGprojId());
//                mrq.setStepId("18041");
//                mrq.setProjId(data.get(i).getProjId());
                List<ManageRecord> mrls = managerecorddao.findByStepIdBusinessOrderId(data.get(i).getGprojId(), StepOutWorkflowEnum.GAS_PROJECT_AUDIT.getValue(), MrResultEnum.PASSED.getValue());
                if (mrls != null && mrls.size() > 0) {
                    String size = mrls.size() + "";
                    if (mrls.size() < Integer.parseInt(grade)) {
                        //遍历循环，获取审核是否通过
                        for (ManageRecord mr : mrls) {
                            levelBtn.put("level" + mr.getMrAuditLevel(), mr.getMrResult());
                        }
                        if (mrls.size() < Integer.parseInt(grade)) {
                            levelBtn.put("level" + (mrls.size() + 1), "2");
                        }
                    }
                }
                data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
            }
            result.put("data", data);
        }
        return result;
    }

	@Override
	public GasProject findById(String gprojId) {
		return gasProjectDao.get(gprojId);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void auditPass(ManageRecord manageRecord) throws ParseException {
		DocType docType = docTypeDao.findByStepId(StepOutWorkflowEnum.GAS_PROJECT_AUDIT.getValue());
		manageRecordService.gasAuditSave(manageRecord, docType == null ? "" : docType.getId(),
				docType == null ? "" : docType.getGrade(), StepOutWorkflowEnum.GAS_PROJECT_AUDIT.getValue(),
				Constants.MODULE_CODE_PROJECTTOUCH);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveGasProject(GasProject gasProject) {
		Project p = projectDao.get(gasProject.getProjId());
		boolean flag = false;
		if (StringUtils.isBlank(gasProject.getGprojId())) {
			gasProject.setGprojId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTTOUCH));
			flag = true;
		}
		if (StringUtils.isNotBlank(gasProject.getAcceptId())) {
			// 标记分布验收工程已开通
			DivisionalAcceptance d = divisionalAcceptanceDao.get(gasProject.getAcceptId());
			d.setIsGas(IsGasEnum.IS_GAS.getValue());
			divisionalAcceptanceDao.update(d);
		} else {
			// 标记联合验收工程已开通
			p.setIsGas(IsGasEnum.IS_GAS.getValue());
			projectDao.update(p);
		}
		// 如果无需开通计划
		if (StringUtil.isNotBlank(gasProject.getGasProjStatus())
				&& gasProject.getGasProjStatus().equals(GasProjectStatusEnum.WHETHER_OPEN.getValue())) {
			gasProjectDao.saveOrUpdate(gasProject);
			//待办todo：
			operateRecordService.cerateCurOperateRecord(p,gasProject.getGasProjStatus(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue(),gasProject.getGpId(),new Staff(),null,false);
			
			return Constants.OPERATE_RESULT_SUCCESS;
		}
		String deptId = projectDao.get(gasProject.getProjId()).getDeptId();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Department dept = departmentDao.queryDepartmentByDivide(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue(),
				loginInfo.getDeptId());
		if (dept!=null && dept.getDeptId().substring(0, 6).equals(deptId)) {
			// 客服中心的直接生成一条计划
			GasPlan gasPlan = new GasPlan();
			gasPlan.setGpId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTTOUCH));
			gasPlan.setGpName(gasProject.getProjName());
			gasPlan.setGpNo(gasProject.getProjNo());
			gasPlan.setGpName(gasProject.getProjName());
			gasPlan.setCreater(SessionUtil.getLoginInfo().getStaffName());
			gasPlan.setCreateDate(gasProjectDao.getDatabaseDate());
			gasPlan.setDeptId(deptId);
			gasPlanDao.save(gasPlan);
			// 保存通气信息
			gasProject.setGasProjStatus(GasProjectStatusEnum.GAS_OPEN.getValue());
			gasProject.setGpId(gasPlan.getGpId());
			gasProjectDao.saveOrUpdate(gasProject);
			//待办todo：
			operateRecordService.cerateCurOperateRecord(p,gasProject.getGasProjStatus(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue(),gasProject.getGpId(),new Staff(),null,true);
			
			return Constants.OPERATE_RESULT_SUCCESS;

		}
		// 保存通气信息
		gasProject.setCorpId(p.getCorpId());
		
		//判断分公司是否配置辅助流程 
		String status=workFlowService.queryAssistProgressStatusId(p.getCorpId(), null, null, GasProjectStatusEnum.OPEN_PROJECT.getValue(),
				true, WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue());
		if(WorkFlowActionEnum.CONTRACT_END.getActionCode().equals(status)){
			//未配置流 按原来的走
			gasProject.setGasProjStatus(GasProjectStatusEnum.GAS_PLAN.getValue());
		}else{
			gasProject.setGasProjStatus(status);
		}
		gasProject.setIsPrint(SubBudgetIsPrintEnum.HAVE_NOT_PRINT.getValue());//默认未打印
		gasProjectDao.saveOrUpdate(gasProject);
		//待办todo：
		String grade = null;
		if(GasProjectStatusEnum.GAS_AUDIT.getValue().equals(gasProject.getGasProjStatus())){
			grade = "1";
		}
		operateRecordService.cerateCurOperateRecord(p,gasProject.getGasProjStatus(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue(),gasProject.getGpId(),new Staff(),grade,true);
		
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	public Map<String, Object> queryGasProject(GasProjectReq gasProjectReq) throws ParseException {
		Map<String, Object> map = gasProjectDao.queryGasProject(gasProjectReq);
		List<GasProject> lists = (List<GasProject>) map.get("data");
		if (lists != null && lists.size() > 0) {
			for (GasProject gasProject : lists) {
				Project project = projectDao.get(gasProject.getProjId());
				if (project != null) {
					gasProject.setProjectType(project.getProjectType());
				} // 查询所有的有效的
				List<ProjectSign> projectSignList = projectSignDao.findByProjIdAndStatus(gasProject.getProjId(),
						IsSignEnum.IS_SIGN.getValue());
				if (projectSignList != null && projectSignList.size() > 0) {
					gasProject.setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());// 是特殊工程
				}
			}
		}

		if (lists != null) {
			for (GasProject gp : lists) {
				if (StringUtil.isNotBlank(gp.getProjLtypeId())) {
					gp.setProjectTypeDes(projectTypeDao.get(gp.getProjLtypeId()).getProjConstructDes());
				}
				gp.setGasProjStatusDes(GasProjectStatusEnum.valueof(gp.getGasProjStatus()).getMessage());
				if (StringUtil.isNotBlank(gp.getPreparDept())) {
					gp.setPreparDeptDes(departmentDao.get(gp.getPreparDept()).getDeptName());
				}
				if (StringUtil.isNotBlank(gp.getPreparer())) {
					gp.setPreparerDes(staffDao.get(gp.getPreparer()).getStaffName());
				}
			}
		}
		return map;
	}

	/**
	 * 查询待通气工程
	 * 
	 * @param gasProjectReq
	 * @return
	 */
	@Override
	public Map<String, Object> queryAllowGasProject(GasProjectReq gasProjectReq) throws ParseException {
		
		/*if(StringUtil.isBlank(gasProjectReq.getGasProjStatus())){
			if(StringUtils.isNotBlank(gasProjectReq.getMenuId())){
				String menuId=gasProjectReq.getMenuId();
				MenuStatusSetUp msu=menuStatusSetUpService.queryByMenuId(menuId);
				if(msu!=null){
					List<String> statusList=new ArrayList<String>();
					String[] projStatusIds=msu.getProjStatusId().split(",");
					for(String projStatusId:projStatusIds){
						statusList.add(projStatusId);
					}
					gasProjectReq.setGasStatusList(statusList);
				}
			}
		}*/
		
		
		
		
		Map<String, Object> map = gasProjectDao.queryGasProject(gasProjectReq);
		// 查gasProject表里待开通的工程
		List<GasProject> gasProjects = (List<GasProject>) map.get("data");

		for (GasProject g : gasProjects) {
			Project project = projectDao.get(g.getProjId());  //根据工程ID 查找工程
			if(project != null){
			    g.setCorpId(project.getCorpId());
			}
			g.setGasProjStatusDes(GasProjectStatusEnum.valueof(g.getGasProjStatus()).getMessage());
			g.setProjectTypeDes(projectTypeDao.get(g.getProjLtypeId()).getProjConstructDes());

		}
		// 查联合验收通过的可以带气的工程
		List<Project> projects = projectDao.findByGas(IsGasEnum.NOT_GAS.getValue());
		// if(projects!=null){
		// for(Project p : projects){
		// //获取最后一条联合验收记录验收时间
		// JointAcceptance ja = jointAcceptanceDao.findByProjId(p.getProjId());
		//
		// }
		// }

		// 查分布验收工程带气的
		DivisionalAcceptanceReq divisionalAcceptanceReq = new DivisionalAcceptanceReq();
		divisionalAcceptanceReq.setAcceptanceState(AcceptanceAtateEnum.ALREADY_ACCEPTANCE.getValue());// 已验收的
		List<DivisionalAcceptance> das = (List<DivisionalAcceptance>) divisionalAcceptanceDao
				.queryDivisionalAcceptance(divisionalAcceptanceReq).get("data");
		if (null != das && das.size() > 0) {
			for (DivisionalAcceptance da : das) {
				if (StringUtils.isBlank(da.getIsGas())) {// 要带气的分布验收工程
					Project project1 = projectDao.get(da.getProjId());
					if (!project1.getProjStatusId().equals(ProjStatusEnum.ALREADY_COMPLETED.getValue())) {// 未竣工
						Project project2 = new Project();
						project2.setProjLtypeId(project1.getProjLtypeId());
						project2.setProjectTypeDes(project1.getProjectTypeDes());
						project2.setCustName(project1.getCustName());
						project2.setProjName(project1.getProjName());
						project2.setProjAddr(project1.getProjAddr());
						project2.setProjNo(project1.getProjNo());
						project2.setCorpName(project1.getCorpName());
						project2.setProjId(project1.getProjId());
						project2.setProjScaleDes(project1.getProjScaleDes());
						project2.setAcceptanceDate(da.getAcceptanceDate());
						project2.setInsideProjNo(da.getDaId()); // 传一下验收Id
						project2.setArea("1");
						projects.add(project2);
					}
				}
			}
		}

		for (Project project : projects) {
			GasProject gasProject = new GasProject();
			Contract contract = contractDao.viewContractByprojId(project.getProjId());  //根据工程ID取得记录
			gasProject.setAcceptDate(project.getAcceptanceDate());// 验收时间
			gasProject.setAcceptId(project.getInsideProjNo()); // 传验收Id
			if ("1".equals(project.getArea())) { //
				gasProject.setAcceptType("分部验收");
			} else {
				gasProject.setAcceptType("联合验收");
			}
			gasProject.setCustAddr(project.getProjAddr());  //用户地址
			gasProject.setCustContcat(project.getCustContact()); //联系人
			gasProject.setCustPhone(project.getCustPhone());  //联系电话
			gasProject.setContractAmount(project.getContractAmount());  //合同金额
			if(contract != null){ 
				if("11".equals(project.getProjectType())){  //判断是否是民用工程
					gasProject.setInstallationNumber(contract.getHousehold());  //居民安装户数
				}else{
					gasProject.setInstallationNumber("1");  //非居民安装户数
				}
				
			}
		
			//此处写入已结算金额
			gasProject.setCorpId(project.getCorpId());
			gasProject.setProjLtypeId(project.getProjLtypeId());
			gasProject.setProjectTypeDes(project.getProjectTypeDes());
			gasProject.setCustName(project.getCustName());
			gasProject.setProjName(project.getProjName());
			gasProject.setProjAddr(project.getProjAddr());
			gasProject.setProjNo(project.getProjNo());
			gasProject.setDeptName(project.getCorpName());
			gasProject.setProjId(project.getProjId());
			gasProject.setProjScaleDes(project.getProjScaleDes());
			gasProject.setGasProjStatus(GasProjectStatusEnum.OPEN_PROJECT.getValue());
			gasProject.setGasProjStatusDes(GasProjectStatusEnum.OPEN_PROJECT.getMessage());
			SubContract subContract = subContractDao.findSubContractByprojId(project.getProjId());
			// 计划
			ConstructionPlan constructionPlan = constructionPlanDao.findByProjId(gasProject.getProjId());
			if (constructionPlan != null) {
				gasProject.setGasComLegalRepresent(constructionPlan.getBuilder());
			}
			if (subContract != null) {
				gasProject.setScNo(subContract.getScNo());
				gasProject.setCuName(subContract.getCuName());
			}
			gasProjects.add(gasProject);
		}

		if (gasProjects != null && gasProjects.size() > 0) {
			for (GasProject gasProject : gasProjects) {
				Project project = projectDao.get(gasProject.getProjId());
				if (project != null) {
					gasProject.setProjectType(project.getProjectType());
				}
				// 查询所有的有效的
				// 去掉虚拟合同标记的工程
				List<String> projectSignType = new ArrayList<String>();
				projectSignType.add(ProjectSignTypeEnum.SPECIAL.getValue());
				projectSignType.add(ProjectSignTypeEnum.UNPAID.getValue());
				projectSignType.add(ProjectSignTypeEnum.INCOMPLETE_COST.getValue());
				projectSignType.add(ProjectSignTypeEnum.NO_SUP_MONEY.getValue());
				List<ProjectSign> projectSignList = projectSignDao.findByProjIdAndStatus(gasProject.getProjId(),
						IsSignEnum.IS_SIGN.getValue(), projectSignType);
				if (projectSignList != null && projectSignList.size() > 0) {
					gasProject.setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());// 是特殊工程
				}
				if (StringUtil.isNotBlank(gasProject.getAcceptType()) && "联合验收".equals(gasProject.getAcceptType())) {
					// 查询是否存在未签订补充协议的用户变更
					List<ChangeManagement> listCm = cmService.noCancelChangeManagement(gasProject.getProjId(),
							ChangeTypeEnum.USER_CHANGE.getValue());
					if (listCm != null && listCm.size() > 0) {
						gasProject.setIsHaveCM(String.valueOf(listCm.size()));
					}
				}
			}
		}

		map.put("data", gasProjects);
		return map;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String enterGasProject(GasProject gasProject) {
		if (StringUtils.isBlank(gasProject.getGprojId())) {
			gasProject.setGprojId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTTOUCH));
		}
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		// 保存通气确认
		if (gasProject.getIsBack().equals("1")) {
			gasProject.setGasProjStatus(GasProjectStatusEnum.OPEN_PROJECT.getValue());
			GasPlan gasPlan = gasPlanDao.get(gasProject.getGpId());
			// 如果是客服的删掉计划
			Department dept = departmentDao.queryDepartmentByDivide(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue(),
					loginInfo.getDeptId());
			if (dept.getDeptId().substring(0, 6).equals(gasPlan.getDeptId())) {
				gasPlanDao.delete(gasPlan.getGpId());
			}
			gasProject.setGpId("");
			//待办todo：
            Project p = projectDao.get(gasProject.getProjId());
            Staff staff = new Staff();
            staff.setStaffId(StringUtil.isNotBlank(gasProject.getApplyerId())?gasProject.getApplyerId():"");
            staff.setStaffName(StringUtil.isNotBlank(gasProject.getApplyer())?gasProject.getApplyer():"");
    		operateRecordService.cerateCurOperateRecord(p,gasProject.getGasProjStatus(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue(),gasProject.getGpId(),staff,null,true);
    		
		} else {
			gasProject.setGasProjStatus(GasProjectStatusEnum.GAS_AUDIT.getValue());
			//待办todo：
            Project p = projectDao.get(gasProject.getProjId());
    		operateRecordService.cerateCurOperateRecord(p,gasProject.getGasProjStatus(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue(),gasProject.getGpId(),new Staff(),"1",true);
    		
		}
		gasProject.setPreparer(loginInfo.getStaffId());// 填报人Id
		gasProject.setPreparDept(loginInfo.getDeptId());// 填报单位
		gasProjectDao.saveOrUpdate(gasProject);

		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	public GasProject viewGasProject(String gprojId) {
		GasProject gasProject = gasProjectDao.get(gprojId);
		// 查合同
		Contract contract = contractDao.viewContractByprojId(gasProject.getProjId());

		gasProject.setConNo(contract.getConNo());
		gasProject.setBuilder(gasProject.getGasComLegalRepresent());// 现场代表
		gasProject.setHouseholds(contract.getHousehold()); // 协议户数
		gasProject.setProjectType(projectTypeDao.get(gasProject.getProjLtypeId()).getProjTypeDes());

		return gasProject;
	}
	
	/**
     * 登记通气时间
     * @author fuliwei  
     * @date 2018年9月14日  
     * @version 1.0
     */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveGasDateRegister(GasProject gasProject) {
		if(StringUtil.isNotBlank(gasProject.getGprojId())){
			GasProject gp=gasProjectDao.get(gasProject.getGprojId());
			
			if(gp!=null){
				gp.setPlanGasDate(gasProject.getPlanGasDate());
				//判断分公司是否配置辅助流程 
				String status=workFlowService.queryAssistProgressStatusId(gp.getCorpId(), null, null, GasProjectStatusEnum.GAS_DATE_REGISTER.getValue(),
						true, WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue());
				gp.setGasProjStatus(status);
				gasProjectDao.saveOrUpdate(gp);
				
				return Constants.OPERATE_RESULT_SUCCESS;
			}
			
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	
	 /**
     * 登记交付时间
     * @author fuliwei  
     * @date 2018年9月14日  
     * @version 1.0
     */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveDeliverDateRegister(GasProject gasProject) {
		if(StringUtil.isNotBlank(gasProject.getGprojId())){
			GasProject gp=gasProjectDao.get(gasProject.getGprojId());
			
			if(gp!=null){
				gp.setDeliveryTime(gasProject.getDeliveryTime());
				gp.setReason(gasProject.getReason());
				//判断分公司是否配置辅助流程 
				String status=workFlowService.queryAssistProgressStatusId(gp.getCorpId(), null, null, GasProjectStatusEnum.DELOVER_DATE_REGISTER.getValue(),
						true, WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue());
				gp.setGasProjStatus(status);
				gasProjectDao.saveOrUpdate(gp);
				
				return Constants.OPERATE_RESULT_SUCCESS;
			}
			
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	
	/**
     * 通气单打印标记
     * @author fuliwei  
     * @date 2018年9月27日  
     * @version 1.0
     */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String signGasPrint(String gprojId) {
		GasProject gp=gasProjectDao.get(gprojId);
		if(gp!=null){
			//标记为已打印
			gp.setIsPrint(SubBudgetIsPrintEnum.ALREADY_PRINT.getValue());
			gasProjectDao.update(gp);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
}