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

import org.apache.commons.lang3.StringUtils;

import cc.dfsoft.project.biz.base.project.enums.OperateWorkFlowEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
@Entity
@Table(name = "OPERATE_WORK_FLOW_RECORD")
public class OperateWorkFlowRecord implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2905249740919317445L;
	private String owfrid;			//主键id
	private String owfId;  			//操作流id
	private String stepId;			//步骤id
	private String stepDes;			//只读
	private String opereaterId;		//操作人员id
	private String opereater;		//操作人员名称
	private Date operateTime;		//操作时间
	private String grade;			//审核级别
	private String projectType;		//工程类型
	private String contributionMode;//出资方式
	private String corpId;			//分公司
	private String opType;			//类型
	private String projNo;			//工程编号
	private String projName;		//工程名称
	private String projId;			//工程id
	private String modifyStatus;	//状态状态   1已办 0 未办 2待办
	private String modifyStatusDes;	//只读
	@Id
	@Column(name="OWFR_ID", unique = true)
	public String getOwfrid() {
		return owfrid;
	}
	public void setOwfrid(String owfrid) {
		this.owfrid = owfrid;
	}
	
	
	@Column(name="OWF_ID")
	public String getOwfId() {
		return owfId;
	}
	public void setOwfId(String owfId) {
		this.owfId = owfId;
	}
	
    @Column(name="STEP_ID")
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	 @Column(name="OPEREATER_ID")
	public String getOpereaterId() {
		return opereaterId;
	}
	public void setOpereaterId(String opereaterId) {
		this.opereaterId = opereaterId;
	}
	 @Column(name="OPEREATER")
	public String getOpereater() {
		return opereater;
	}
	public void setOpereater(String opereater) {
		this.opereater = opereater;
	}
	 @Column(name="GRADE")
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	 @Column(name="PROJECT_TYPE")
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	 @Column(name="CONTRIBUTION_MODE")
	public String getContributionMode() {
		return contributionMode;
	}
	public void setContributionMode(String contributionMode) {
		this.contributionMode = contributionMode;
	}
	 @Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	 @Column(name="OP_TYPE")
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	
	@Column(name="PROJ_NO")
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	@Column(name="MODIFY_STATUS")
	public String getModifyStatus() {
		return modifyStatus;
	}
	public void setModifyStatus(String modifyStatus) {
		this.modifyStatus = modifyStatus;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OPERATE_TIME")
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	@Transient
	public String getStepDes() {
		if(StringUtils.isNotBlank(this.stepId)){
			for(StepEnum e: StepEnum.values()) {
		   		if(e.getValue().equals(this.stepId)) {
		   			return e.getMessage();
		   		}
		   	}
			return "";
		}
		return stepDes;
	}
	
	@Column(name="PROJ_NAME")
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	
	@Transient
	public String getModifyStatusDes() {
		for(OperateWorkFlowEnum e: OperateWorkFlowEnum.values()) {
	   		if(e.getValue().equals(this.modifyStatus)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}
	public void setModifyStatusDes(String modifyStatusDes) {
		this.modifyStatusDes = modifyStatusDes;
	}

}
