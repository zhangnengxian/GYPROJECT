package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.GrooveRecord;
import cc.dfsoft.project.biz.base.inspection.entity.PressureRecords;

public class ProjectCheckListGrReq {
	
	private String pcId;        		//报验单id
	private List<GrooveRecord> list;	//沟槽记录对象list
	private List<PressureRecords> listPressureRecords;	//复压记录对象list
	private String recordsId;					//
	private String projId;
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<GrooveRecord> getList() {
		return list;
	}
	public void setList(List<GrooveRecord> list) {
		this.list = list;
	}
	public List<PressureRecords> getListPressureRecords() {
		return listPressureRecords;
	}
	public void setListPressureRecords(List<PressureRecords> listPressureRecords) {
		this.listPressureRecords = listPressureRecords;
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
