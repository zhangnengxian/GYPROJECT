package cc.dfsoft.project.biz.base.complete.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class DivisionalAcceptanceReq extends PageSortReq{
	
	private String projId;			//工程id
	private String projTypeId;		//工程类型id
	private String projNo;			//工程编号
	private String conNo;			//合同编号
	private String scNo;			//分包合同号
	private String projName;		//工程名称
	private String projAddr;		//工程地点
	private String applyDateStart; //申请开始日期
	private String applyDateEnd;   //申请结束日期
	private String stepId;			//步骤id
	
	private String auditState;//审核状态
	private Integer timeLimit;     		//限制完成的时间段
	
	private String acceptanceDateStart;//验收开始时间
	private String acceptanceDateEnd;  //验收结束时间
	private String acceptanceState;		//验收状态
	
	private String isPrint;				//是否打印
	private String menuId;				//菜单ID
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjTypeId() {
		return projTypeId;
	}
	public void setProjTypeId(String projTypeId) {
		this.projTypeId = projTypeId;
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
	public String getAcceptanceDateStart() {
		return acceptanceDateStart;
	}
	public void setAcceptanceDateStart(String acceptanceDateStart) {
		this.acceptanceDateStart = acceptanceDateStart;
	}
	public String getAcceptanceDateEnd() {
		return acceptanceDateEnd;
	}
	public void setAcceptanceDateEnd(String acceptanceDateEnd) {
		this.acceptanceDateEnd = acceptanceDateEnd;
	}
	public String getAcceptanceState() {
		return acceptanceState;
	}
	public void setAcceptanceState(String acceptanceState) {
		this.acceptanceState = acceptanceState;
	}
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	public String getScNo() {
		return scNo;
	}
	public void setScNo(String scNo) {
		this.scNo = scNo;
	}
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}
