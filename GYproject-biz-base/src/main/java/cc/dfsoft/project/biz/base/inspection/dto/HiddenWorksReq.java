package cc.dfsoft.project.biz.base.inspection.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class HiddenWorksReq extends PageSortReq{
	
	
	private String pcId;       			//报验单id
	private String recordsId;	//记录id组合
	private String projId;				//工程Id
	private Integer flag;
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

	public String getRecordsId() {
		return recordsId;
	}

	public void setRecordsId(String recordsId) {
		this.recordsId = recordsId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
}
