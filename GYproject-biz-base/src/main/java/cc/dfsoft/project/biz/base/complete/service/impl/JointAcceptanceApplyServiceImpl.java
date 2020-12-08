package cc.dfsoft.project.biz.base.complete.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.complete.dao.JointAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.complete.enums.AcceptanceTypeEnum;
import cc.dfsoft.project.biz.base.complete.service.JointAcceptanceApplyService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectSignDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectSign;
import cc.dfsoft.project.biz.base.project.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 描述:联合验收
 * @author liaoyq
 * @createTime 2018年9月9日
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class JointAcceptanceApplyServiceImpl implements
		JointAcceptanceApplyService {
	@Resource
	ProjectDao projectDao;
	@Resource
	JointAcceptanceDao jointAcceptanceDao;
	@Resource
	WorkFlowService workFlowService;
	@Resource
	OperateRecordService operateRecordService;
	@Resource
	ProjectService projectService;
	@Resource
	SystemSetDao systemSetDao;
	@Resource
	ProjectSignDao projectSignDao;
	@Resource
	DocTypeService docTypeService;
	@Resource
	ManageRecordDao manageRecordDao;

	@Override
	public JointAcceptance findById(String projId) {
		Project project = projectDao.get(projId);
		JointAcceptance acceptance = null;
		if(StringUtil.isNotBlank(projId)){
			acceptance = jointAcceptanceDao.get("projId", projId);
			acceptance = this.getProjInfo(acceptance,project);
		}
		if(acceptance==null){
			
			acceptance = this.getProjInfo(acceptance,project);
		}
		acceptance.setApplyDate(projectDao.getDatabaseDate());//申请日期
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		acceptance.setApplyer(loginInfo.getStaffName());//申请人
		acceptance.setApplyerId(loginInfo.getStaffId());//
		return acceptance;
	}

	/**
	 * 组装工程基本信息并返回
	 * @return
	 */
	private JointAcceptance getProjInfo(JointAcceptance acceptance,Project project){
		if(acceptance == null){
				acceptance = new JointAcceptance();
		}
		acceptance.setProjId(project.getProjId());
		acceptance.setProjNo(project.getProjNo());
		acceptance.setProjName(project.getProjName());
		acceptance.setProjAddr(project.getProjAddr());
		acceptance.setProjScaleDes(project.getProjScaleDes());
		acceptance.setSuName(project.getSuName());
		acceptance.setCuName(project.getCuName());
		acceptance.setCustName(project.getCustName());
		acceptance.setContributionModeDes(project.getContributionModeDes());
		acceptance.setDeptName(project.getDeptName());
		acceptance.setProjectTypeDes(project.getProjectTypeDes());
		acceptance.setCorpName(project.getCorpName());
		acceptance.setCorpId(project.getCorpId());
		return acceptance;
	}
	@Override
	@Transactional(readOnly = false , propagation =Propagation.REQUIRED)
	public void saveJointAcceptanceApply(
			JointAcceptance jointAcceptance) {
		if(StringUtils.isBlank(jointAcceptance.getJaId())){
			jointAcceptance.setJaId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		}
		jointAcceptance.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());	   //未打印
		jointAcceptance.setAcceptanceType(AcceptanceTypeEnum.JOINT_ACCEPTANCE.getValue());//联合验收
		jointAcceptance.setAuditState(jointAcceptance.getFlag());
		//1推送，修改工程状态-并生成操作日期
		if(AuditResultEnum.APPLYING.getValue().equals(jointAcceptance.getFlag())){
			String stepId = StepEnum.UNION_PRE_INSPECTION_APPLY.getValue();//联合验收申请
	        Project project = projectDao.get(jointAcceptance.getProjId());
	        //推送日期
	        jointAcceptance.setApplyDate(projectDao.getDatabaseDate());
	        if(project!=null){
	        	String status = workFlowService.queryProjStatusId(project.getCorpId(), project.getProjectType(), project.getContributionMode(), stepId, Boolean.valueOf(true));
	        	project.setProjStatusId(status);

	        	String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET);
				String toder = operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepId, StepEnum.valueof(stepId).getMessage(), "");
				project.setToDoer(toder);//待办人
				projectDao.update(project);
			}
	    }
		jointAcceptanceDao.saveOrUpdate(jointAcceptance);
	}

	@Override
	public Map<String, Object> queryJointAcceptanceAudit(ProjectQueryReq req) throws ParseException {
		String grade=null;
		//DocType docType= docTypeDao.findByStepId(StepOutWorkflowEnum.DIVISIONAL_ACCEPTANCE_AUDIT.getValue());
		//grade=docType==null?"":docType.getGrade();	
		DocType docType=new DocType();
		//SystemSet systemSet=systemSetDao.get("stepId", req.getStepId());
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
        SystemSet systemSet=systemSetDao.querySystemSetByStepId(req.getStepId(),loginInfo.getCorpId());
		if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
			req.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		}
		
		Map<String, Object> result = projectService.queryProjectByTime(req);
		List<Project> data = (List<Project>) result.get("data");
		
		if(data!=null && data.size()>0){
			for(int i = 0;i<data.size();i++){
				
				//查询所有的有效的
				List<ProjectSign> projectSignList=projectSignDao.findByProjIdAndStatus(data.get(i).getProjId(),IsSignEnum.IS_SIGN.getValue());
				
				if(projectSignList!=null && projectSignList.size()>0){
					data.get(i).setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//是特殊工程
				}
				
				Project proj=data.get(i);
				JointAcceptance acceptance = jointAcceptanceDao.get("projId", proj.getProjId());
				if(acceptance!=null){
					data.get(i).setJointAcceptance(acceptance);
				}
				docType = docTypeService.findByStepId(StepEnum.UNION_PRE_INSPECTION_AUDIT.getValue(),proj.getCorpId(),proj.getProjectType(),proj.getContributionMode());
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					grade=docType.getGrade();
				}else{
					grade="0";
				}
				data.get(i).setLevel(grade);// 设置审核总级数（合同审核2级审核）
				Map<String,String> levelBtn = new HashMap();
				//以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for(int n=1;n<Integer.parseInt(grade)+1;n++){
					if(n==1){
						levelBtn.put("level"+n, "2");// 待审
					}else{
						levelBtn.put("level"+n, "-1");//未审
					}
				}
				List<ManageRecord> mrls = manageRecordDao.findByStepIdBusinessOrderId(acceptance.getJaId(),StepEnum.UNION_PRE_INSPECTION_AUDIT.getValue(),MrResultEnum.PASSED.getValue());
				if(mrls!=null && mrls.size()>0){
					String size = mrls.size()+"";
					if(mrls.size()<Integer.parseInt(grade)){
						//遍历循环，获取审核是否通过
						for(ManageRecord mr:mrls){
							levelBtn.put("level"+mr.getMrAuditLevel(),mr.getMrResult());
						}
						if(mrls.size()<Integer.parseInt(grade)){
							levelBtn.put("level"+(mrls.size()+1), "2");
						}
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		}
		return result;
	}
}
