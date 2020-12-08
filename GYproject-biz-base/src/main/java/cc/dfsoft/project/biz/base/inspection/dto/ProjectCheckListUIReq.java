package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;
/**
 * 埋地检查记录保存辅助类
 */
import cc.dfsoft.project.biz.base.inspection.entity.UndergroundInpect;

public class ProjectCheckListUIReq {

	private String pcId;
	private List<UndergroundInpect> list;
	
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<UndergroundInpect> getList() {
		return list;
	}
	public void setList(List<UndergroundInpect> list) {
		this.list = list;
	}
	
}
