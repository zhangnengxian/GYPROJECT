package cc.dfsoft.project.biz.base.complete.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class DataAcceptanceReq extends PageSortReq{
	
	private String projTypeId;//工程类型id
	private String dataType;  //资料类型
	private String projId;    //工程id
	
	public String getProjTypeId() {
		return projTypeId;
	}
	public void setProjTypeId(String projTypeId) {
		this.projTypeId = projTypeId;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	
	
}
