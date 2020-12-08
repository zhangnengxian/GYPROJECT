package cc.dfsoft.project.biz.base.constructmanage.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;


public class ConnectRecordQueryReq  extends PageSortReq {

	private String crId;
	public String getCrId() {
		return crId;
	}
	public void setCrId(String crId) {
		this.crId = crId;
	}
	private String connectContentId;
	private String projId;
	private String unitType;
	
	public String getConnectContentId() {
		return connectContentId;
	}
	public void setConnectContentId(String connectContentId) {
		this.connectContentId = connectContentId;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	
}