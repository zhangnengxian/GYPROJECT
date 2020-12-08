package cc.dfsoft.project.biz.base.inspection.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 防腐检查查询辅助类
 * @author liaoyq
 *
 */
public class PreservativeInpectReq extends PageSortReq {

	private String pcId;

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	
}
