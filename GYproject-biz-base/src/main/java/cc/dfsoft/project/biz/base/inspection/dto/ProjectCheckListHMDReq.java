package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.HotMeltDocking;

/**
 * 热熔对接保存辅助类
 * @author liaoyq
 *
 */
public class ProjectCheckListHMDReq {

	private String pcId;
	private List<HotMeltDocking> list;
	private String projId;
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<HotMeltDocking> getList() {
		return list;
	}
	public void setList(List<HotMeltDocking> list) {
		this.list = list;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
}
