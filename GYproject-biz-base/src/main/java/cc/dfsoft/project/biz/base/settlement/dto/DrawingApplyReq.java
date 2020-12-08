package cc.dfsoft.project.biz.base.settlement.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class DrawingApplyReq extends PageSortReq{
	private String projNo;	//工程编号
	private String stepId; //步骤id
	private String projId;	//工程id
	private String applyNo;//申请单编号
	private String projName;//工程名称
	private String applyDateStart;		//申请开始日期
	private String applyDateEnd;		//申请开始日期
	
	private String auditState;//审核状态
	private Integer timeLimit;     		//限制完成的时间段
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
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public String getApplyDateStart() {
		return applyDateStart;
	}
	public void setApplyDateStart(String applyDateStart) {
		this.applyDateStart = applyDateStart;
	}
	public String getApplyDateEnd() {
		return applyDateEnd;
	}
	public void setApplyDateEnd(String applyDateEnd) {
		this.applyDateEnd = applyDateEnd;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getAuditState() {
		return auditState;
	}
	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	public Integer getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	
}
