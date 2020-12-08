package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.GroundResistanceTest;

public class ProjectCheckListGRTReq {
	
	private String pcId;        		//报验单id
	private List<GroundResistanceTest> list;//接地测试
	

	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<GroundResistanceTest> getList() {
		return list;
	}
	public void setList(List<GroundResistanceTest> list) {
		this.list = list;
	}
	
} 
