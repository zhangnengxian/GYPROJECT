package cc.dfsoft.project.biz.base.project.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

import java.util.List;

public class OperateWorkFlowReq extends PageSortReq{
	
	private String stepId;			//步骤id
	private List<String> stepIds;	//步骤ids
	private String stepName;        //步骤名称
	private String grade;			//审核级别
	private String projectType;		//工程类型
	private String contributionMode;//出资方式
	private String corpId;			//分公司
	private String opType;			//类型
	private String projId;			//工程id
	
	private String operaterId;		//操作人id
	private String operater;   //操作人
	private String modifyStatus;	//状态 代办 未办 已办
	
	private String owfId;			//主键id
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public List<String> getStepIds() {
		return stepIds;
	}
	public void setStepIds(List<String> stepIds) {
		this.stepIds = stepIds;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getContributionMode() {
		return contributionMode;
	}
	public void setContributionMode(String contributionMode) {
		this.contributionMode = contributionMode;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getOperaterId() {
		return operaterId;
	}
	public void setOperaterId(String operaterId) {
		this.operaterId = operaterId;
	}
	public String getModifyStatus() {
		return modifyStatus;
	}
	public void setModifyStatus(String modifyStatus) {
		this.modifyStatus = modifyStatus;
	}
	public String getOwfId() {
		return owfId;
	}
	public void setOwfId(String owfId) {
		this.owfId = owfId;
	}
	public String getOperater() {
		return operater;
	}
	public void setOperater(String operater) {
		this.operater = operater;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	
	
	
	
}
