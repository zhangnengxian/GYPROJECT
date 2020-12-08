package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.AntisepsisSpec;

public class ProjectCheckListAntisepsisSpecReq {
	
	private String pcId;        				//报验单id
	private List<AntisepsisSpec> list;	//除锈防腐记录对象list
	
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<AntisepsisSpec> getList() {
		return list;
	}
	public void setList(List<AntisepsisSpec> list) {
		this.list = list;
	}
}
