package cc.dfsoft.project.biz.base.project.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class ApplyDelayReq extends PageSortReq{
	
	private String projId;
	private String projNo;				//工程编号
	private String projName;			//工程名称
	private String projAddr;			//工程地点
	private String applyDateStart;		//申请开始日期
	private String applyDateEnd;		//申请结束日期
	private String stepId;	            //步骤
	private String deptId;				//部门id
	private String corpId;				//分公司id
	private String auditState;			//审核状态
	private Integer timeLimit;     		//限制完成的时间段
	private String menuId;				//菜单id
	
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
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getProjAddr() {
		return projAddr;
	}
	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
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
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
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

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}
