package cc.dfsoft.project.biz.base.project.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class FallbackApplyReq extends PageSortReq{
	
	private String projId;				//工程Id
	private String projNo;				//工程编号
	private String projName;			//工程名称
	private String originalStepId;		//初始步骤
	private String fallbackStepId;		//回退步骤
	private String applyTimeStart;		//申请时间开始
	private String applyTimeEnd;		//申请时间结束
	private String applyOperatorId;		//申请人Id
	private String applyOperator;		//申请人
	private String auditState;			//审核状态
	
	private String projectType;			//工程类型
	private String contributionCode;	//出资方式
	private String menuId;				//菜单id
	private String corpId;				//分公司ID
	private String projStatusId;		//工程状态
	
	
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
	public String getOriginalStepId() {
		return originalStepId;
	}
	public void setOriginalStepId(String originalStepId) {
		this.originalStepId = originalStepId;
	}
	public String getFallbackStepId() {
		return fallbackStepId;
	}
	public void setFallbackStepId(String fallbackStepId) {
		this.fallbackStepId = fallbackStepId;
	}
	public String getApplyTimeStart() {
		return applyTimeStart;
	}
	public void setApplyTimeStart(String applyTimeStart) {
		this.applyTimeStart = applyTimeStart;
	}
	public String getApplyTimeEnd() {
		return applyTimeEnd;
	}
	public void setApplyTimeEnd(String applyTimeEnd) {
		this.applyTimeEnd = applyTimeEnd;
	}
	public String getApplyOperatorId() {
		return applyOperatorId;
	}
	public void setApplyOperatorId(String applyOperatorId) {
		this.applyOperatorId = applyOperatorId;
	}
	public String getApplyOperator() {
		return applyOperator;
	}
	public void setApplyOperator(String applyOperator) {
		this.applyOperator = applyOperator;
	}
	public String getAuditState() {
		return auditState;
	}
	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getContributionCode() {
		return contributionCode;
	}
	public void setContributionCode(String contributionCode) {
		this.contributionCode = contributionCode;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getProjStatusId() {
		return projStatusId;
	}

	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
	}
}
