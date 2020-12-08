package cc.dfsoft.project.biz.base.budget.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class SubBudgetReq extends PageSortReq{
	
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private String projName;				//工程名称
	private String projAddr;				//工程地点
	private String sbDateStart;				//预算日期开始
	private String sbDateEnd;				//预算日期结束
	private String isprint;					//是否打印标记     0 已打印,1未打印
	private String auditState;				//是否通过审核  1通过
	private String menuId;                   //菜单ID
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
	public String getSbDateStart() {
		return sbDateStart;
	}
	public void setSbDateStart(String sbDateStart) {
		this.sbDateStart = sbDateStart;
	}
	public String getSbDateEnd() {
		return sbDateEnd;
	}
	public void setSbDateEnd(String sbDateEnd) {
		this.sbDateEnd = sbDateEnd;
	}
	public String getIsprint() {
		return isprint;
	}
	public void setIsprint(String isprint) {
		this.isprint = isprint;
	}
	public String getAuditState() {
		return auditState;
	}
	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}
