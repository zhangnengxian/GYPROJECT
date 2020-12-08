package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.ElectrodeRecord;
/**
 * 焊条记录辅助类
 * @author liaoyq
 *
 */
public class ProjectCheckListERReq {

	private String pcId;        		//报验单id
	private List<ElectrodeRecord> list;//焊条领用记录
	
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<ElectrodeRecord> getList() {
		return list;
	}
	public void setList(List<ElectrodeRecord> list) {
		this.list = list;
	}
	
	
}
