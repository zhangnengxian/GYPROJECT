package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.WeldLineInpect;

/**
 * 
 * @author liaoyq
 *
 */
public class ProjectCheckListWLIReq {

	private String pcId;
	private List<WeldLineInpect> list;
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<WeldLineInpect> getList() {
		return list;
	}
	public void setList(List<WeldLineInpect> list) {
		this.list = list;
	}
	
}
