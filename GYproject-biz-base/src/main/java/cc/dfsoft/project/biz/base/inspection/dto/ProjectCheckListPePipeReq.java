package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.PepipeWelding;

public class ProjectCheckListPePipeReq {
	
	private String pcId;        		//报验单id
	private List<PepipeWelding> list;   //PE管焊接对象list
	private String recordsId;					//
	private String projId;
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<PepipeWelding> getList() {
		return list;
	}
	public void setList(List<PepipeWelding> list) {
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
