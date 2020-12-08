package cc.dfsoft.project.biz.base.inspection.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 除锈防腐查询dto
 * @author Administrator
 *
 */
public class DerustingPreservativeQueryReq extends PageSortReq{
	
	private String pcId;//报验单id

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	
}
