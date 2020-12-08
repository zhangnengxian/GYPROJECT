package cc.dfsoft.project.biz.base.project.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class RelationShipReq extends PageSortReq{
	
	private String typeId;//类型id

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
}
