package cc.dfsoft.project.biz.base.inspection.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 试压记录查询dto
 * @author Administrator
 *
 */
public class PressureQueryReq extends PageSortReq{
	
	private String pcId;//工程报验单id

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
}
