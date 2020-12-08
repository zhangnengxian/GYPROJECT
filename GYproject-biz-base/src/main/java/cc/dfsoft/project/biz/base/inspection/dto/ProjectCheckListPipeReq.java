package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.PipeWelding;

public class ProjectCheckListPipeReq {
	
	private String pcId;        		//报验单id
	private List<PipeWelding> list;		//钢管焊接对象list
	private String recordsId;					//
	private String projId;
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<PipeWelding> getList() {
		return list;
	}
	public void setList(List<PipeWelding> list) {
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
