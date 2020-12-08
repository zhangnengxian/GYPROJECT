package cc.dfsoft.project.biz.base.inspection.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 焊条领用查询条件辅助类
 * @author liaoyq
 *
 */
public class ElectrodeRecordReq extends PageSortReq{

	private String pcId;//报验单id
	
	private String projId;//工程ID
	
	private Integer flag;//报验单完成标记

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}
