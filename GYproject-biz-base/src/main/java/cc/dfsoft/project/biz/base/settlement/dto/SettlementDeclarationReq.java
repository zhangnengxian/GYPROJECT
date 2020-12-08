package cc.dfsoft.project.biz.base.settlement.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class SettlementDeclarationReq  extends PageSortReq {
	
	private String projId;				//工程ID
	private String projNo;				//工程编号
	private String projName;			//工程名称
	private String projAddr;			//工程地点
	private String custName;			//客户名称
	private String suName;      		//监理单位
	private String ocoDateStart;		//送审日期开始
	private String ocoDateEnd;			//送审日期结束
	private String firstAuditDateStart;	//初审日期开始
	private String firstAuditDateEnd;	//初审日期结束
	private String endDeclaraDateStart;	//终审日期开始
	private String endDeclaraDateEnd;	//终审日期结束
	private String projStatusId;	    //工程状态
	private String stepId;	            //工程步骤
	private String confirmState;	    //确认状态
	private Integer timeLimit;
	private String isPrint;				//是否打印标记     0 已打印,1未打印
	
	private String finishDateStart;		//结算汇签完成开始时间
	private String finishDateEnd;		//结算汇签完成结束时间
	
	
	private String signStatus;			//汇签状态 0 未完成 1已完成
	private String auditStatus;         //审核状态
	private String conNo;//合同编号
	private String menuId;				//菜单ID
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
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
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getSuName() {
		return suName;
	}
	public void setSuName(String suName) {
		this.suName = suName;
	}
	public String getOcoDateStart() {
		return ocoDateStart;
	}
	public void setOcoDateStart(String ocoDateStart) {
		this.ocoDateStart = ocoDateStart;
	}
	public String getOcoDateEnd() {
		return ocoDateEnd;
	}
	public void setOcoDateEnd(String ocoDateEnd) {
		this.ocoDateEnd = ocoDateEnd;
	}
	public String getFirstAuditDateStart() {
		return firstAuditDateStart;
	}
	public void setFirstAuditDateStart(String firstAuditDateStart) {
		this.firstAuditDateStart = firstAuditDateStart;
	}
	public String getFirstAuditDateEnd() {
		return firstAuditDateEnd;
	}
	public void setFirstAuditDateEnd(String firstAuditDateEnd) {
		this.firstAuditDateEnd = firstAuditDateEnd;
	}
	public String getEndDeclaraDateStart() {
		return endDeclaraDateStart;
	}
	public void setEndDeclaraDateStart(String endDeclaraDateStart) {
		this.endDeclaraDateStart = endDeclaraDateStart;
	}
	public String getEndDeclaraDateEnd() {
		return endDeclaraDateEnd;
	}
	public void setEndDeclaraDateEnd(String endDeclaraDateEnd) {
		this.endDeclaraDateEnd = endDeclaraDateEnd;
	}
	public String getProjStatusId() {
		return projStatusId;
	}
	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getConfirmState() {
		return confirmState;
	}
	public void setConfirmState(String confirmState) {
		this.confirmState = confirmState;
	}
	public Integer getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	public String getFinishDateStart() {
		return finishDateStart;
	}
	public void setFinishDateStart(String finishDateStart) {
		this.finishDateStart = finishDateStart;
	}
	public String getFinishDateEnd() {
		return finishDateEnd;
	}
	public void setFinishDateEnd(String finishDateEnd) {
		this.finishDateEnd = finishDateEnd;
	}
	public String getSignStatus() {
		return signStatus;
	}
	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}


	
}
