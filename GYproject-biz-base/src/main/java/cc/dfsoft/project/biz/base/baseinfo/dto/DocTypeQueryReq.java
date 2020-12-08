package cc.dfsoft.project.biz.base.baseinfo.dto;



import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class DocTypeQueryReq extends PageSortReq{
	
	private String corpId;				//分公司id
	private String projType;			//工程类型id
	private String contributionCode;	//出资方式id"contributionMode"
	private String stepId;				//步骤id
	private String contributionMode; 
	
	
	
	public String getContributionMode() {
		return contributionMode;
	}

	public void setContributionMode(String contributionMode) {
		this.contributionMode = contributionMode;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getProjType() {
		return projType;
	}

	public void setProjType(String projType) {
		this.projType = projType;
	}

	public String getContributionCode() {
		return contributionCode;
	}

	public void setContributionCode(String contributionCode) {
		this.contributionCode = contributionCode;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	
}
