package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.PeWeldLineInspect;

/**
 * pe管焊缝检查记录保存辅助类
 * @author liaoyq
 *
 */
public class ProjectCheckListPEWLIReq {

	private String pcId ;
	private List<PeWeldLineInspect> list;
	
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<PeWeldLineInspect> getList() {
		return list;
	}
	public void setList(List<PeWeldLineInspect> list) {
		this.list = list;
	}
	
	
}
