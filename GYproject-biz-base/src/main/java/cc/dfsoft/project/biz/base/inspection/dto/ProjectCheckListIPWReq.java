package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.IndoorPocketWatch;

/**
 * 户内挂表保存辅助类
 * @author liaoyq
 * @createTime 2017年7月24日
 */
public class ProjectCheckListIPWReq {

	private String pcId;
	private String recordsId;
	private String projId;
	private List<IndoorPocketWatch> list;
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public String getRecordsId() {
		return recordsId;
	}
	public void setRecordsId(String recordsId) {
		this.recordsId = recordsId;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public List<IndoorPocketWatch> getList() {
		return list;
	}
	public void setList(List<IndoorPocketWatch> list) {
		this.list = list;
	}
	
}
