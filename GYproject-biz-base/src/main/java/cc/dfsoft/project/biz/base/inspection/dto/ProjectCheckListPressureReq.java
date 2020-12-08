package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.Pressure;

public class ProjectCheckListPressureReq {
	
	private String pcId;        		//报验单id
	private List<Pressure> list;		//试压记录对象list
	
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<Pressure> getList() {
		return list;
	}
	public void setList(List<Pressure> list) {
		this.list = list;
	}
}
