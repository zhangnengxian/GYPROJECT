package cc.dfsoft.project.biz.base.baseinfo.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class DealyReasonQueryReq extends PageSortReq{

	private String delayReasonDes;//延期原因描述

	public String getDelayReasonDes() {
		return delayReasonDes;
	}

	public void setDelayReasonDes(String delayReasonDes) {
		this.delayReasonDes = delayReasonDes;
	}
	
}
