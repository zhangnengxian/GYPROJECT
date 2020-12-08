package cc.dfsoft.project.biz.base.settlement.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.project.entity.Project;
@Entity
@Table(name = "DRAWING_APPLY")
public class DrawingApply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5502747544685797755L;
	
	private String acdId;				//主键id
	private String projId;				//工程id
	private String projStatusId;		//状态id
	private String corpId;				//分公司id
	private String projName;			//工程名称
	private String projNo;				//工程编号
	private String projScaleDes;		//工程规模描述--只读
	private String projAddr;			//工程地点--只读
	private String applyer;				//申请人
	private String applyerId;			//申请人id
	private String applyNo;				//申请单编号
	private Date applyDate;				//申请时间
	private String applyReason;			//申请事由
	private String auditState;			//审核状态
	
	private String corpName;		    //分公司名称--只读
	private String deptName;	 		//部门名称--只读
	private String projectTypeDes;		//工程类型描述--只读
	private String contributionModeDes;//出资方式描述--只读
	
	private String cuName;				//施工单位--只读
	private String suName;				//监理单位--只读
	private String duName;				//设计单位--只读
	
	private String mrAuditLevel; 		//已审核级别----只读属性，用于接气方案审核屏
	private String level;        		//几级审核------只读属性，用于方案审核页面显示按钮
	private Project project;     		//工程对象------只读属性，用于审核屏详述
	private Boolean overdue;			 //是否逾期 true逾期 false未逾期
	
	
	private String signBack;			 //审核状态
	private String tenantId;			 //租户id
	private String orgId;				 //组织id
	
	private String projectType;			 //工程类型-只读
	
	public DrawingApply() {
	}
	
	@Id
	@Column(name = "ACD_ID", unique = true)
	public String getAcdId() {
		return acdId;
	}
	public void setAcdId(String acdId) {
		this.acdId = acdId;
	}
	
	@Column(name = "PROJ_ID")
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	@Column(name = "PROJ_STATUS_ID")
	public String getProjStatusId() {
		return projStatusId;
	}
	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
	}
	
	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	
	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
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
	public void setApplyerId(String applyerId) {
		this.applyerId = applyerId;
	}
	
	@Column(name = "APPLY_NO")
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	
	@Column(name = "APPLY_REASON")
	public String getApplyReason() {
		return applyReason;
	}
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	
	@Column(name = "AUDIT_STATE")
	public String getAuditState() {
		return auditState;
	}
	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	@Transient
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	@Transient
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	
	@Transient
	public String getProjScaleDes() {
		return projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	
	@Transient
	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	
	@Transient
	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	
	@Transient
	public String getSuName() {
		return suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "APPLY_DATE")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
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
	
	@Transient
	public String getDuName() {
		return duName;
	}

	public void setDuName(String duName) {
		this.duName = duName;
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

	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

}
