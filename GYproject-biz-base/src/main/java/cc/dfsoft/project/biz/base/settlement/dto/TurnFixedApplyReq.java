package cc.dfsoft.project.biz.base.settlement.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class TurnFixedApplyReq extends PageSortReq{
	private String projNo;			//工程编号
	private String stepId;			//步骤id
	private String projStatus;		//工程状态
	private String projId;			//工程id
	private String projName;		//工程名称
	private String cuName;			//施工单位
	private String applyDateStart;	//转固开始日期
	private String applyDateEnd;	//转固开始日期
	private Integer timeLimit;		//限制完成的时间段
	private String isPrint;			//转固开始日期
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
	public Integer getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getProjStatus() {
		return projStatus;
	}
	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	
}
