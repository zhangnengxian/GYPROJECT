package cc.dfsoft.project.biz.base.subpackage.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class SubQuantitiesReq extends PageSortReq{
	
	private String sqStandardId;	//工程量标准
	private String sqBranchProjName;//分部分项名称
	private String sbId;			//施工预算Id
	
	private String projId;			//工程ID
	private Integer sqStatus;		//工程量类型
	
	public String getSqStandardId() {
		return sqStandardId;
	}

	public void setSqStandardId(String sqStandardId) {
		this.sqStandardId = sqStandardId;
	}

	public String getSqBranchProjName() {
		return sqBranchProjName;
	}

	public void setSqBranchProjName(String sqBranchProjName) {
		this.sqBranchProjName = sqBranchProjName;
	}

	public String getSbId() {
		return sbId;
	}

	public void setSbId(String sbId) {
		this.sbId = sbId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public Integer getSqStatus() {
		return sqStatus;
	}

	public void setSqStatus(Integer sqStatus) {
		this.sqStatus = sqStatus;
	}
}
