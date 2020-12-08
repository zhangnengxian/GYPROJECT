package cc.dfsoft.project.biz.base.project.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 操作记录查询
 * @author pengtt
 * @createTime 2016-07-13
 *
 */
public class OperateRecordQueryReq extends PageSortReq {
	
	private String projId;				//工程id
	private String projNo;				//工程编号
	private String stepId;				//步骤ID
	
	private String grade;			//审核级别
	private String projectType;		//工程类型
	private String contributionMode;//出资方式
	private String corpId;			//分公司
	private String opType;			//类型
	
	private String operaterId;		//操作人id
	private String modifyStatus;	//状态 代办 未办 已办
	private String isValid;			//是否有效 1 有效 0无效
	private String businessOrderId; // 业务单Id
	
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
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

	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getBusinessOrderId() {
		return businessOrderId;
	}
	public void setBusinessOrderId(String businessOrderId) {
		this.businessOrderId = businessOrderId;
	}
	@Override
	public String toString() {
		return "OperateRecordQueryReq [projId=" + projId + ", projNo=" + projNo + ", stepId=" + stepId + ", grade="
				+ grade + ", projectType=" + projectType + ", contributionMode=" + contributionMode + ", corpId="
				+ corpId + ", opType=" + opType + ", operaterId=" + operaterId + ", modifyStatus=" + modifyStatus
				+ ", isValid=" + isValid + ", businessOrderId=" + businessOrderId + "]";
	}

	
	
	
}
