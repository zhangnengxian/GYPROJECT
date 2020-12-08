package cc.dfsoft.project.biz.base.complete.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class PreinspectionReq  extends PageSortReq {
	
	private String projId;			//工程ID
	private String projStatusId;	//工程状态
	
	private String projNo;			//工程编号
	private String projName;		//工程名称
	private String projAddr;		//工程地点
	private String conNo;			//安装合同
	private String scNo;			//施工合同
	
	private String cuName;			//
	private String isPrint;			//是否打印标记     0 已打印,1未打印
	
	private String piDateStart;		//预验收日期
	private String piDateEnd;		//预验收日期
	
	
	
	private String silDateStart;	//自检开始日期
	private String silDateEnd;		//自检结束日期
	private String menuId;		//菜单ID
	
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjStatusId() {
		return projStatusId;
	}
	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
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
	public String getPiDateStart() {
		return piDateStart;
	}
	public void setPiDateStart(String piDateStart) {
		this.piDateStart = piDateStart;
	}
	public String getPiDateEnd() {
		return piDateEnd;
	}
	public void setPiDateEnd(String piDateEnd) {
		this.piDateEnd = piDateEnd;
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
	public String getProjAddr() {
		return projAddr;
	}
	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	public String getSilDateStart() {
		return silDateStart;
	}
	public void setSilDateStart(String silDateStart) {
		this.silDateStart = silDateStart;
	}
	public String getSilDateEnd() {
		return silDateEnd;
	}
	public void setSilDateEnd(String silDateEnd) {
		this.silDateEnd = silDateEnd;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	
}
