package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.Purge;
/*
 * 吹扫记录
 */
public class ProjectCheckListPurgeReq {
	
	private String pcId;
	private  List<Purge> list;
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<Purge> getList() {
		return list;
	}
	public void setList(List<Purge> list) {
		this.list = list;
	}
	
}
