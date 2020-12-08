package cc.dfsoft.project.biz.base.constructmanage.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class MaterialPlanReq  extends PageSortReq{
	private String projNo;
	private String projId;
	private String projName;//工程名称
	private String cuName;//分包单位
	private String constructionUnit;//施工管理处
	private String constructionPm;//项目经理
	private String builder;//甲方代表
	private String isFeedBack;//状态
	
	private String applicationDateStart;  //申请开始日期
	private String applicationDateEnd;	  //申请结束日期
	private String planReceiveDateStart; //计划领用开始日期
	private String planReceiveDateEnd;   //计划领用结束日期
	
	private String feedBacker;  //反馈人
	private String isExport;	//是否反馈 0已反馈 1 未反馈
	
	public String getApplicationDateStart() {
		return applicationDateStart;
	}

	public void setApplicationDateStart(String applicationDateStart) {
		this.applicationDateStart = applicationDateStart;
	}

	public String getApplicationDateEnd() {
		return applicationDateEnd;
	}

	public void setApplicationDateEnd(String applicationDateEnd) {
		this.applicationDateEnd = applicationDateEnd;
	}

	public String getPlanReceiveDateStart() {
		return planReceiveDateStart;
	}

	public void setPlanReceiveDateStart(String planReceiveDateStart) {
		this.planReceiveDateStart = planReceiveDateStart;
	}

	public String getPlanReceiveDateEnd() {
		return planReceiveDateEnd;
	}

	public void setPlanReceiveDateEnd(String planReceiveDateEnd) {
		this.planReceiveDateEnd = planReceiveDateEnd;
	}

	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getIsFeedBack() {
		return isFeedBack;
	}

	public void setIsFeedBack(String isFeedBack) {
		this.isFeedBack = isFeedBack;
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

	public String getConstructionUnit() {
		return constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	public String getConstructionPm() {
		return constructionPm;
	}

	public void setConstructionPm(String constructionPm) {
		this.constructionPm = constructionPm;
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public String getFeedBacker() {
		return feedBacker;
	}

	public void setFeedBacker(String feedBacker) {
		this.feedBacker = feedBacker;
	}

	public String getIsExport() {
		return isExport;
	}

	public void setIsExport(String isExport) {
		this.isExport = isExport;
	}
}
