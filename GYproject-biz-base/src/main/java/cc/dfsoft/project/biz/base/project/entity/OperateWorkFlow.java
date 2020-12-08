package cc.dfsoft.project.biz.base.project.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import cc.dfsoft.project.biz.base.project.enums.StepEnum;
/*
 * 操作流标准表
 */
@Entity
@Table(name = "OPERATE_WORK_FLOW")
public class OperateWorkFlow implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1323975334987473525L;
	private String owfId;  			//主键id
	private String stepId;			//步骤id
	private String stepName;		//步骤
	private String opereaterId;		//操作人员id
	private String opereater;		//操作人员名称
	private String grade;			//审核级别
	private String projectType;		//工程类型
	private String contributionMode;//出资方式
	private String corpId;			//分公司id
	private String corpName;	    //分公司名称
	private String opType;			//类型
	
	private String projectTypeDes;	//工程类型-只读
	private String contributionModeDes;//出资方式-只读
	private String stepDes;			//步骤
	
	
	@Id
    @Column(name="OWF_ID", unique = true)
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

	public void setStepDes(String stepDes) {
		this.stepDes = stepDes;
	}
	
	@Column(name="CORP_NAME")
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	@Column(name="STEP_NAME")
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	
}
