package cc.dfsoft.project.biz.base.complete.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class JointAcceptanceReq  extends PageSortReq {
	
	private String projId;		//工程ID
	private String projNo;		//工程编号
	private String conNo;      //合同编号
	private String projName;	//工程名称
	private String projAddr;	//工程地点
	private String custName;	//客户名称
	private String suName;      //监理单位
	private String jaDateStart;	//验收日期开始
	private String jaDateEnd;	//验收日期结束
	private String isPrint;		//是否打印标记     0 已打印,1未打印
	private String auditState;	//审核状态
	
	private String acceptanceType;//验收单
	private String applyDateStart; //申请日期开始
	private String applyDateEnd;  //申请日期结束
	private String menuId;  //菜单ID
	
	
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
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
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
	public String getJaDateStart() {
		return jaDateStart;
	}
	public void setJaDateStart(String jaDateStart) {
		this.jaDateStart = jaDateStart;
	}
	public String getJaDateEnd() {
		return jaDateEnd;
	}
	public void setJaDateEnd(String jaDateEnd) {
		this.jaDateEnd = jaDateEnd;
	}
	public String getAcceptanceType() {
		return acceptanceType;
	}
	public void setAcceptanceType(String acceptanceType) {
		this.acceptanceType = acceptanceType;
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
