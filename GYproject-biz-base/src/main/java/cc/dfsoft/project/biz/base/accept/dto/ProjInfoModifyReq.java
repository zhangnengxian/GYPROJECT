package cc.dfsoft.project.biz.base.accept.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class ProjInfoModifyReq extends PageSortReq{
	
	private String projId;

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}
}
