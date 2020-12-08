package cc.dfsoft.project.biz.base.project.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;

/**
 * 回退申请
 * @author Yuanyx
 *
 */
@Entity
@Table(name = "FALLBACK_APPLY")

public class FallbackApply implements Serializable{

     /**
	 * 
	 */
	private static final long serialVersionUID = 8630873172293766251L;
	// Fields
	private String faId;			//回退申请Id
    private String projId;			//工程ID
    private String projNo;			//工程编号
    private String projName;		//工程名称
    private String originalStepId;	//初始步骤
    private String originalStepIdDes;	//初始步骤描述-只读
    private String fallbackStepId;	//回退步骤
    private String fallbackStepIdDes;	//回退步骤描述-只读
    private String fallbackReason;	//回退原因
    private Date applyTime;			//申请时间
    private Date fallbackTime;		//回退时间
    private String applyOperatorId;	//申请人Id
    private String applyOperator;	//申请人
    private String auditState;		//审核状态
    private String auditInfo;		//审核信息
    
    private String auditStateDes;	//审核信息--列表显示
    
    private String mrAuditLevel; 	//已审核级别----只读属性，用于图纸审核屏

	private String level;       	//几级审核------只读属性，用于方案图纸审核页面显示按钮
	
	private String projScaleDes;	//工程规模
	private String projectType;     //工程类型
	private String constructionMode;//出资方式
	private String projStatusDes;	//工程状态
	private String projStatusId;	//工程状态
	private String overdue;			//超时
	private String mbsId;			//配置表ID
	private String isAudit;			//是否需要审核
	
	private String corpId;			//分公司id
	private String todoer;			//待办人
	
    @Id
    @Column(name="FA_ID", unique = true)
    public String getFaId() {
        return this.faId;
    }
    
    public void setFaId(String faId) {
        this.faId = faId;
    }

    @Column(name="PROJ_ID")
    public String getProjId() {
        return this.projId;
    }
    
    public void setProjId(String projId) {
        this.projId = projId;
    }

    @Column(name="PROJ_NO")
	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name="PROJ_NAME")
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}
	@Column(name="ORIGINAL_STEP_ID")
	public String getOriginalStepId() {
		return originalStepId;
	}

	public void setOriginalStepId(String originalStepId) {
		this.originalStepId = originalStepId;
	}

	@Column(name="FALLBACK_STEP_ID")
	public String getFallbackStepId() {
		return fallbackStepId;
	}

	public void setFallbackStepId(String fallbackStepId) {
		this.fallbackStepId = fallbackStepId;
	}

	@Column(name="FALLBACK_REASON")
	public String getFallbackReason() {
		return fallbackReason;
	}

	public void setFallbackReason(String fallbackReason) {
		this.fallbackReason = fallbackReason;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="APPLY_TIME")
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="FALLBACK_TIME")
	public Date getFallbackTime() {
		return fallbackTime;
	}

	public void setFallbackTime(Date fallbackTime) {
		this.fallbackTime = fallbackTime;
	}

	@Column(name="APPLY_OPERATOR_ID")
	public String getApplyOperatorId() {
		return applyOperatorId;
	}

	public void setApplyOperatorId(String applyOperatorId) {
		this.applyOperatorId = applyOperatorId;
	}

	@Column(name="APPLY_OPERATOR")
	public String getApplyOperator() {
		return applyOperator;
	}

	public void setApplyOperator(String applyOperator) {
		this.applyOperator = applyOperator;
	}
	
	@Column(name="AUDIT_STATE")
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	
	@Column(name="AUDIT_INFO")
	public String getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
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
	public String getProjScaleDes() {
		return projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	
	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	@Transient
	public String getConstructionMode() {
		return constructionMode;
	}

	public void setConstructionMode(String constructionMode) {
		this.constructionMode = constructionMode;
	}
	
	@Transient
	public String getProjStatusDes() {
		for(ProjStatusEnum e: ProjStatusEnum.values()) {
	   		if(e.getValue().equals(this.projStatusId)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setProjStatusDes(String projStatusDes) {
		this.projStatusDes = projStatusDes;
	}
	
	@Transient
	public String getProjStatusId() {
		return projStatusId;
	}

	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
	}
	
	@Transient
	public String getOverdue() {
		return overdue;
	}

	public void setOverdue(String overdue) {
		this.overdue = overdue;
	}

	@Column(name="MBS_ID")
	public String getMbsId() {
		return mbsId;
	}

	public void setMbsId(String mbsId) {
		this.mbsId = mbsId;
	}

	@Transient
	public String getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	@Transient
	public String getOriginalStepIdDes() {
		return originalStepIdDes;
	}

	public void setOriginalStepIdDes(String originalStepIdDes) {
		this.originalStepIdDes = originalStepIdDes;
	}

	@Transient
	public String getFallbackStepIdDes() {
		return fallbackStepIdDes;
	}

	public void setFallbackStepIdDes(String fallbackStepIdDes) {
		this.fallbackStepIdDes = fallbackStepIdDes;
	}

	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Transient
	public String getTodoer() {
		return todoer;
	}

	public void setTodoer(String todoer) {
		this.todoer = todoer;
	}
	
	
	
	
	
}