package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.MonomerSetUp;

public class ProjectCheckListMSUReq {
	
	private String pcId;        		//报验单id
	private List<MonomerSetUp> list;//高程测量
	

	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<MonomerSetUp> getList() {
		return list;
	}
	public void setList(List<MonomerSetUp> list) {
		this.list = list;
	}
	
} 
