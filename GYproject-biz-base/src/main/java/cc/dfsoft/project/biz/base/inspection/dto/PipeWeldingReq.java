package cc.dfsoft.project.biz.base.inspection.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class PipeWeldingReq extends PageSortReq{
	
	private String pcId;       			//报验单id
	private String recordsId;					//
	private String projId;
	private Integer flag;
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public String getRecordsId() {
		return recordsId;
	}

	public void setRecordsId(String recordsId) {
		this.recordsId = recordsId;
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
