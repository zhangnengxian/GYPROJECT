package cc.dfsoft.project.biz.base.charge.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 收费查询条件
 * @author zhangjj
 *
 */
public class ChargeReq extends PageSortReq{
	
	private String projId;//工程id
	private String projNo;//工程id
	//应收应付记录相关
	private String arFlag;//收付标志
	private String arAtatus;//状态
	private String arType;//收款类型
	private String cfFlag;//收付标志
	private String cfStatus;//状态
	private String cfType;//收款类型
	private String billType;//状态
	private String billState;//收款类型
	private String projName;//工程名称
	private String projAddr;//工程地址
	private String projStatusId;//状态
	private String custName;//客户名称
	//收付流水相关
	
	private String arOver;//收费完成标志
	private String overDaysStart;//超时时长开始时间
	private String overDaysEnd;//超时时长结束时间
	private String amountScale;
	private String menuId;		//菜单ID
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getArFlag() {
		return arFlag;
	}
	public void setArFlag(String arFlag) {
		this.arFlag = arFlag;
	}
	public String getArAtatus() {
		return arAtatus;
	}
	public void setArAtatus(String arAtatus) {
		this.arAtatus = arAtatus;
	}
	public String getCfFlag() {
		return cfFlag;
	}
	public void setCfFlag(String cfFlag) {
		this.cfFlag = cfFlag;
	}
	public String getArType() {
		return arType;
	}
	public void setArType(String arType) {
		this.arType = arType;
	}
	public String getArOver() {
		return arOver;
	}
	public void setArOver(String arOver) {
		this.arOver = arOver;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	public String getCfType() {
		return cfType;
	}
	public void setCfType(String cfType) {
		this.cfType = cfType;
	}
	public String getCfStatus() {
		return cfStatus;
	}
	public void setCfStatus(String cfStatus) {
		this.cfStatus = cfStatus;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getBillState() {
		return billState;
	}
	public void setBillState(String billState) {
		this.billState = billState;
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
	public String getProjStatusId() {
		return projStatusId;
	}
	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getAmountScale() {
		return amountScale;
	}
	public void setAmountScale(String amountScale) {
		this.amountScale = amountScale;
	}
	public String getOverDaysStart() {
		return overDaysStart;
	}
	public void setOverDaysStart(String overDaysStart) {
		this.overDaysStart = overDaysStart;
	}
	public String getOverDaysEnd() {
		return overDaysEnd;
	}
	public void setOverDaysEnd(String overDaysEnd) {
		this.overDaysEnd = overDaysEnd;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	

}
