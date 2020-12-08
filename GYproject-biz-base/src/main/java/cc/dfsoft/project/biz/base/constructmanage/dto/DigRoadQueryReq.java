package cc.dfsoft.project.biz.base.constructmanage.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;


public class DigRoadQueryReq  extends PageSortReq {


	private String tpId;    //碰口方案id
	private String drRoads; //路况
	
	
	public String getTpId() {
		return tpId;
	}
	public void setTpId(String tpId) {
		this.tpId = tpId;
	}
	public String getDrRoads() {
		return drRoads;
	}
	public void setDrRoads(String drRoads) {
		this.drRoads = drRoads;
	}


	
}