package cc.dfsoft.project.biz.base.constructmanage.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class InspectionRecordQueryReq extends PageSortReq {
	
	private String ilId;					//质量检查单ID

	public String getIlId() {
		return ilId;
	}

	public void setIlId(String ilId) {
		this.ilId = ilId;
	}
	
}
