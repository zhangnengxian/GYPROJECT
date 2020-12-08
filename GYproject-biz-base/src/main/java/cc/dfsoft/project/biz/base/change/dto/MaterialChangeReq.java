package cc.dfsoft.project.biz.base.change.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class MaterialChangeReq extends PageSortReq{
 
	private String projId;		//工程ID	
	private String cmId;		//变更记录id
	private String evId;		//签证记录ID
	private String flag;		//是否由物资购买
	
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getCmId() {
		return cmId;
	}

	public void setCmId(String cmId) {
		this.cmId = cmId;
	}

	public String getEvId() {
		return evId;
	}

	public void setEvId(String evId) {
		this.evId = evId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
