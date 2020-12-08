package cc.dfsoft.project.biz.base.constructmanage.dto;

import java.util.List;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class EngineeringVisaQueryReq extends PageSortReq{

	private String evId;        //签证id
	private String projId;		//工程ID
	private String projName;	//工程名称
	private String projNo;		//工程编号
	private String visaDate;	//签证日期
	private String visaDateStart;	//签证日期
	private String visaDateEnd;	//签证日期
	private List evState;	    //签证状态
	private String evContent;	//签证事由
	private List auditState;//审核级别
	private String auditStatus;//单个审核状态
	private String menuId;	//菜单id
	private String evNo;  //签证编号
	public String getEvId() {
		return evId;
	}
	public String getProjId() {
		return projId;
	}
	public String getProjName() {
		return projName;
	}
	public String getProjNo() {
		return projNo;
	}
	public String getVisaDate() {
		return visaDate;
	}
	public void setEvId(String evId) {
		this.evId = evId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public void setVisaDate(String visaDate) {
		this.visaDate = visaDate;
	}
	public List getEvState() {
		return evState;
	}
	public void setEvState(List<String> evState) {
		this.evState = evState;
	}
	public String getEvContent() {
		return evContent;
	}
	public void setEvContent(String evContent) {
		this.evContent = evContent;
	}
	public String getVisaDateStart() {
		return visaDateStart;
	}
	public void setVisaDateStart(String visaDateStart) {
		this.visaDateStart = visaDateStart;
	}
	public String getVisaDateEnd() {
		return visaDateEnd;
	}
	public void setVisaDateEnd(String visaDateEnd) {
		this.visaDateEnd = visaDateEnd;
	}
	public List getAuditState() {
		return auditState;
	}
	public void setAuditState(List<String> auditState) {
		this.auditState = auditState;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getEvNo() {
		return evNo;
	}
	public void setEvNo(String evNo) {
		this.evNo = evNo;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
}
