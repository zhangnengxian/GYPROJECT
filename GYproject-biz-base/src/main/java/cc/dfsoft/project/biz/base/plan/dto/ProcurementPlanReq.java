package cc.dfsoft.project.biz.base.plan.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class ProcurementPlanReq extends PageSortReq{
	
	private String projId;			//工程id
	private String projNo;			//工程编号
	private String projName;		//工程名称
	private String managementOffice;//施工管理处
	private String projStartDateStar;//开工开始日期
	private String projStartDateEnd;//开工截止日期
	private String createTimeStar;	//创建开始日期
	private String createTimeEnd;	//创建截止日期
	private String createStaffName;	//创建人名称
	private String status;			//创建时工程操作
	private String isExport;		//是否已导出
	private String projType;//工程类型
	private String isFeedBack;//是否反馈
	
	
	
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
	public String getManagementOffice() {
		return managementOffice;
	}
	public void setManagementOffice(String managementOffice) {
		this.managementOffice = managementOffice;
	}
	public String getCreateStaffName() {
		return createStaffName;
	}
	public void setCreateStaffName(String createStaffName) {
		this.createStaffName = createStaffName;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjStartDateStar() {
		return projStartDateStar;
	}
	public void setProjStartDateStar(String projStartDateStar) {
		this.projStartDateStar = projStartDateStar;
	}
	public String getProjStartDateEnd() {
		return projStartDateEnd;
	}
	public void setProjStartDateEnd(String projStartDateEnd) {
		this.projStartDateEnd = projStartDateEnd;
	}
	public String getCreateTimeStar() {
		return createTimeStar;
	}
	public void setCreateTimeStar(String createTimeStar) {
		this.createTimeStar = createTimeStar;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsExport() {
		return isExport;
	}
	public void setIsExport(String isExport) {
		this.isExport = isExport;
	}
	public String getProjType() {
		return projType;
	}
	public void setProjType(String projType) {
		this.projType = projType;
	}
	public String getIsFeedBack() {
		return isFeedBack;
	}
	public void setIsFeedBack(String isFeedBack) {
		this.isFeedBack = isFeedBack;
	}
	
}
