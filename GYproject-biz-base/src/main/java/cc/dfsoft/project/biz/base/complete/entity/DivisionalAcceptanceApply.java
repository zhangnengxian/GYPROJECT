package cc.dfsoft.project.biz.base.complete.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.complete.enums.AcceptanceAtateEnum;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
@Entity
@Table(name = "DIVISIONAL_ACCEPTANCE_APPLY")
public class DivisionalAcceptanceApply implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -340675206219186431L;
	private String daaId;//
	private String projId;                     	//工程id
	private String projNo;                     //工程编号
	private String projName;                   //工程名称
	private String custName;                   //建设方名称
	private String projAddr;                   //项目地址
	private String projScaleDes;               //工程规模详述
	private String conNo;                      //合同编号 
	private String suName;                     //监理单位
	private String cuName;						//施工单位
	private Date acceptDate;					//报建时间
	private Date ocoDate;						//设计委托时间
	private Date cpArriveDate;					//施工任务下发时间
	private String consDrawingSituation;		//有无施工图
	private String plannedEndDate;				//合同约定完工时间
	private Date startDate;						//施工单位进场时间
	
	private String finishedSituation;			//合同工程量完成情况
	private String selfCheckSituation;			//施工单位自检情况
	private String testRecord;					//强度、气密性情况
	private String checkSituation;				//检查情况
	
	private String completedDrawingSituation;   //是否有竣工图
	private String modelSelectSituation;		//选型要求告知情况
	private String signSituation;				//安全、供气协议的签订情况
	private String remark;						//备注
	private String auditState;					//审核状态
	private String auditStateDes;				//审核状态-只读
	
	private String corpName;		    		//分公司名称--只读
	private String deptName;	 				//部门名称--只读
	private String projectTypeDes;				//工程类型描述--只读
	private String contributionModeDes;			//出资方式描述--只读

	private Date applyDate;					    //申请日期
	private String applyer;						//申请人
	private String applyerId;					//申请人id
	//贵安分部验收申请增加字段
	private Date planAcceptDate;				//计划验收日期
	private String isCompleteReport;			//是否有竣工报告
	private String isPreInspection;			    //是否有预验收记录
	
	private String mrAuditLevel; 				//已审核级别----只读属性
	private String level;        				//几级审核------只读属性
	private Project project;     				//工程对象------只读属性，用于审核屏详述
	private Boolean overdue;	 				//是否逾期 true逾期 false未逾期

	private String projStateDes;				//工程状态-只读
	private String acceptanceState;            //验收状态
	private String acceptanceStateDes;			//验收状态
	
	private String corpId;						//分公司id
	private String signBack;					//退回标记
	private String tenantId;					//租户id
	private String orgId;						//组织id

	private String supervisorOpinion;           //监理评定
	private String thisAcceptanceContent;		//本次验收内容
	
	private String isSpecialSign;					//是否特殊工程 1 是 0 否
	
	private String projectType;						//工程类型-只读
	private String isPrint = "1";                           //是否打印 ,默认为1，1表示未打印，0表示已打印
	
	@Transient
	public String getIsSpecialSign() {
		return isSpecialSign;
	}

	public void setIsSpecialSign(String isSpecialSign) {
		this.isSpecialSign = isSpecialSign;
	}

	public DivisionalAcceptanceApply(){
		
	}

	@Id
	@Column(name = "DAA_ID", unique = true)
	public String getDaaId() {
		return daaId;
	}


	public void setDaaId(String daaId) {
		this.daaId = daaId;
	}

	
	@Column(name = "PROJ_ID")
	public String getProjId() {
		return projId;
	}


	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return projNo;
	}


	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return projName;
	}


	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Column(name = "CUST_NAME")
	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return projAddr;
	}


	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	@Column(name = "PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return projScaleDes;
	}


	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}

	@Column(name = "CON_NO")
	public String getConNo() {
		return conNo;
	}

	
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}

	@Column(name = "SU_NAME")
	public String getSuName() {
		return suName;
	}


	public void setSuName(String suName) {
		this.suName = suName;
	}

	@Column(name = "CU_NAME")
	public String getCuName() {
		return cuName;
	}


	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ACCEPT_DATE")
	public Date getAcceptDate() {
		return acceptDate;
	}


	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "OCO_DATE")
	public Date getOcoDate() {
		return ocoDate;
	}


	public void setOcoDate(Date ocoDate) {
		this.ocoDate = ocoDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CP_ARRIVE_DATE")
	public Date getCpArriveDate() {
		return cpArriveDate;
	}


	public void setCpArriveDate(Date cpArriveDate) {
		this.cpArriveDate = cpArriveDate;
	}

	@Column(name = "CONS_DRAWING_SITUATION")
	public String getConsDrawingSituation() {
		return consDrawingSituation;
	}


	public void setConsDrawingSituation(String consDrawingSituation) {
		this.consDrawingSituation = consDrawingSituation;
	}
	
	@Column(name = "PLANNED_END_DATE")
	public String getPlannedEndDate() {
		return plannedEndDate;
	}


	public void setPlannedEndDate(String plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "FINISHED_SITUATION")
	public String getFinishedSituation() {
		return finishedSituation;
	}


	public void setFinishedSituation(String finishedSituation) {
		this.finishedSituation = finishedSituation;
	}

	@Column(name = "SELF_CHECK_SITUATION")
	public String getSelfCheckSituation() {
		return selfCheckSituation;
	}


	public void setSelfCheckSituation(String selfCheckSituation) {
		this.selfCheckSituation = selfCheckSituation;
	}

	@Column(name = "TEST_RECORD")
	public String getTestRecord() {
		return testRecord;
	}


	public void setTestRecord(String testRecord) {
		this.testRecord = testRecord;
	}

	@Column(name = "COMPLETED_DRAWING_SITUATION")
	public String getCompletedDrawingSituation() {
		return completedDrawingSituation;
	}


	public void setCompletedDrawingSituation(String completedDrawingSituation) {
		this.completedDrawingSituation = completedDrawingSituation;
	}

	@Column(name = "MODEL_SELECT_SITUATION")
	public String getModelSelectSituation() {
		return modelSelectSituation;
	}


	public void setModelSelectSituation(String modelSelectSituation) {
		this.modelSelectSituation = modelSelectSituation;
	}

	@Column(name = "SIGN_SITUATION")
	public String getSignSituation() {
		return signSituation;
	}


	public void setSignSituation(String signSituation) {
		this.signSituation = signSituation;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "AUDIT_STATE")
	public String getAuditState() {
		return auditState;
	}


	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	
	
	@Transient
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Transient
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	@Transient
	public String getProjectTypeDes() {
		return projectTypeDes;
	}

	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}
	
	@Transient
	public String getContributionModeDes() {
		return contributionModeDes;
	}

	public void setContributionModeDes(String contributionModeDes) {
		this.contributionModeDes = contributionModeDes;
	}
		
	
	@Temporal(TemporalType.DATE)
	@Column(name = "APPLY_DATE")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	@Column(name = "APPLYER")
	public String getApplyer() {
		return applyer;
	}

	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}
	
	@Column(name = "APPLYER_ID")
	public String getApplyerId() {
		return applyerId;
	}

	
	@Column(name = "IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	public void setApplyerId(String applyerId) {
		this.applyerId = applyerId;
	}
	
	@Transient
	public String getMrAuditLevel() {
		return mrAuditLevel;
	}

	public void setMrAuditLevel(String mrAuditLevel) {
		this.mrAuditLevel = mrAuditLevel;
	}
	
	@Transient
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	@Transient
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	@Transient
	public Boolean getOverdue() {
		return overdue;
	}

	public void setOverdue(Boolean overdue) {
		this.overdue = overdue;
	}
	
	@Column(name = "CHECK_SITUATION")
	public String getCheckSituation() {
		return checkSituation;
	}

	public void setCheckSituation(String checkSituation) {
		this.checkSituation = checkSituation;
	}
	
	@Transient
	public String getAuditStateDes() {
		for(AuditResultEnum e: AuditResultEnum.values()) {
	   		if(e.getValue().equals(this.auditState)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setAuditStateDes(String auditStateDes) {
		this.auditStateDes = auditStateDes;
	}
	
	@Transient
	public String getProjStateDes() {
		return projStateDes;
	}

	public void setProjStateDes(String projStateDes) {
		this.projStateDes = projStateDes;
	}
	
	@Transient
	public String getAcceptanceStateDes() {
		for(AcceptanceAtateEnum e: AcceptanceAtateEnum.values()) {
	   		if(e.getValue().equals(this.acceptanceState)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setAcceptanceStateDes(String acceptanceStateDes) {
		this.acceptanceStateDes = acceptanceStateDes;
	}
	
	
	@Column(name = "ACCEPTANCE_STATE")
	public String getAcceptanceState() {
		return acceptanceState;
	}

	public void setAcceptanceState(String acceptanceState) {
		this.acceptanceState = acceptanceState;
	}
	
	@Column(name = "SIGN_BACK")
	public String getSignBack() {
		return signBack;
	}

	public void setSignBack(String signBack) {
		this.signBack = signBack;
	}
	
	@Column(name = "TENANT_ID")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	@Column(name = "ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Column(name = "SUPERVISOR_OPINION")
	public String getSupervisorOpinion() {
		return supervisorOpinion;
	}

	public void setSupervisorOpinion(String supervisorOpinion) {
		this.supervisorOpinion = supervisorOpinion;
	}

	@Column(name = "THIS_ACCEPTANCE_CONTENT")
	public String getThisAcceptanceContent() {
		return thisAcceptanceContent;
	}

	public void setThisAcceptanceContent(String thisAcceptanceContent) {
		this.thisAcceptanceContent = thisAcceptanceContent;
	}

	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	@Column(name="IS_COMPLETE_REPORT")
	public String getIsCompleteReport() {
		return isCompleteReport;
	}
	public void setIsCompleteReport(String isCompleteReport) {
		this.isCompleteReport = isCompleteReport;
	}
	@Column(name="IS_PREINSPECTION")
	public String getIsPreInspection() {
		return isPreInspection;
	}
	public void setIsPreInspection(String isPreInspection) {
		this.isPreInspection = isPreInspection;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="PLAN_ACCEPT_DATE")
	public Date getPlanAcceptDate() {
		return planAcceptDate;
	}
	public void setPlanAcceptDate(Date planAcceptDate) {
		this.planAcceptDate = planAcceptDate;
	}
}
