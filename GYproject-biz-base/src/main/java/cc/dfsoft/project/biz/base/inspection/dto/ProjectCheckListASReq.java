package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.AltimetricSurvey;

public class ProjectCheckListASReq {
	
	private String pcId;        		//报验单id
	private List<AltimetricSurvey> list;//高程测量
	

	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<AltimetricSurvey> getList() {
		return list;
	}
	public void setList(List<AltimetricSurvey> list) {
		this.list = list;
	}
	
} 
