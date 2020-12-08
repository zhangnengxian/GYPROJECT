package cc.dfsoft.project.biz.base.project.entity;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;

/**
 * 申请延期
 * @author zhangjj
 *
 */
@Entity
@Table(name = "APPLY_DELAY")

public class ApplyDelay{


    // Fields

     private String adId;           	//延期记录ID
     private String projId;         	//工程ID
     private String stepId;         	//工程步骤ID
     private String stepIdDes;			//工程步骤
     private BigDecimal originalPeriod;	//原时段
     private BigDecimal delayPeriod;   	//延期时段
     private String delayReason;    	//延期原因id
     private String delayReasonDes;		//延期原因
     private Date optTime;          	//操作时间
     
     private String projNo;				//工程编号
     private String projName;      		//工程名称
     private String projAddr;     		//工程地点
     private String projScaleDes;		//工程规模
     private String corpId;				//分公司id
     private String corpName;			//分公司名称
     private String projectTypeDes;		//工程类型
     private String contributionModeDes;//出资方式
     private String deptName;			//部门名称
     private String adOperatorId;		//操作人ID
     private String adOperator;        //操作人
     private String  auditState;		//审核状态
     private String notPassReason;		//审核不通过原因
     private String auditStateDes;		//审核状态
     
     private String signBack;			//退回标记
     
     private String delayRemark;		//延期备注
    // private String adOperator;       //操作人ID
     
    private String mrAuditLevel; 				//已审核级别----只读属性
 	private String level;        				//几级审核------只读属性
 	private Boolean overdue;	 				//是否逾期 true逾期 false未逾期 
 	
 	private String projectType;					//工程类型-只读
	private String contributionMode;			//出资方式id-只读
    @Id
    @Column(name="AD_ID", nullable=false)
    public String getAdId() {
        return this.adId;
    }
    
    public void setAdId(String adId) {
        this.adId = adId;
    }

    @Column(name="PROJ_ID", nullable=false)
    public String getProjId() {
        return this.projId;
    }
    
    public void setProjId(String projId) {
        this.projId = projId;
    }

    @Column(name="STEP_ID", nullable=false)
    public String getStepId() {
        return this.stepId;
    }
    
    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    @Column(name="ORIGINAL_PERIOD")
    public BigDecimal getOriginalPeriod() {
        return this.originalPeriod;
    }
    
    public void setOriginalPeriod(BigDecimal originalPeriod) {
        this.originalPeriod = originalPeriod;
    }

    @Column(name="DELAY_PERIOD")
    public BigDecimal getDelayPeriod() {
        return this.delayPeriod;
    }
    
    public void setDelayPeriod(BigDecimal delayPeriod) {
        this.delayPeriod = delayPeriod;
    }

    @Column(name="DELAY_REASON")
    public String getDelayReason() {
        return this.delayReason;
    }
    
    public void setDelayReason(String delayReason) {
        this.delayReason = delayReason;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="OPT_TIME")
    public Date getOptTime() {
        return this.optTime;
    }
    
    public void setOptTime(Date optTime) {
        this.optTime = optTime;
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
	
	@Column(name="PROJ_ADDR")
	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	
	@Column(name="PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	
	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	@Column(name="CORP_NAME")
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	@Column(name="PROJECT_TYPE_DES")
	public String getProjectTypeDes() {
		return projectTypeDes;
	}

	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}
	
	@Column(name="CONTRIBUTION_MODE_DES")
	public String getContributionModeDes() {
		return contributionModeDes;
	}

	public void setContributionModeDes(String contributionModeDes) {
		this.contributionModeDes = contributionModeDes;
	}
	
	@Column(name="DEPT_NAME")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Column(name="AD_OPERATOR_ID")
	public String getAdOperatorId() {
		return adOperatorId;
	}

	public void setAdOperatorId(String adOperatorId) {
		this.adOperatorId = adOperatorId;
	}
	
	@Column(name="AD_OPERATOR")
	public String getAdOperator() {
		return adOperator;
	}

	public void setAdOperator(String adOperator) {
		this.adOperator = adOperator;
	}
	
	@Column(name="AUDIT_STATE")
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	
	@Column(name="NOT_PASS_REASON")
	public String getNotPassReason() {
		return notPassReason;
	}

	public void setNotPassReason(String notPassReason) {
		this.notPassReason = notPassReason;
	}
	
	@Transient
	public String getDelayReasonDes() {
		return delayReasonDes;
	}

	public void setDelayReasonDes(String delayReasonDes) {
		this.delayReasonDes = delayReasonDes;
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
	
	
	@Column(name="DELAY_REMARK")
	public String getDelayRemark() {
		return delayRemark;
	}

	public void setDelayRemark(String delayRemark) {
		this.delayRemark = delayRemark;
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
	public Boolean getOverdue() {
		return overdue;
	}

	public void setOverdue(Boolean overdue) {
		this.overdue = overdue;
	}
	
	@Column(name="SIGN_BACK")
	public String getSignBack() {
		return signBack;
	}

	public void setSignBack(String signBack) {
		this.signBack = signBack;
	}
	
	@Transient
	public String getStepIdDes() {
		for(StepEnum e: StepEnum.values()) {
	   		if(e.getValue().equals(this.stepId)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setStepIdDes(String stepIdDes) {
		this.stepIdDes = stepIdDes;
	}

	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	@Transient
	public String getContributionMode() {
		return contributionMode;
	}

	public void setContributionMode(String contributionMode) {
		this.contributionMode = contributionMode;
	}
	
	
}