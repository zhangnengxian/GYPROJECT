package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.PipeLineInstall;

public class ProjectCheckListPLIReq {
	
	private String pcId;        		//报验单id
	private List<PipeLineInstall> list;//高程测量
	private String recordsId;					//
	private String projId;

	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<PipeLineInstall> getList() {
		return list;
	}
	public void setList(List<PipeLineInstall> list) {
		this.list = list;
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
	
} 
