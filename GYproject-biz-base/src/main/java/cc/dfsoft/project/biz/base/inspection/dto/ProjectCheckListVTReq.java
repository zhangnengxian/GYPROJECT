package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.ValveTest;

/**
 * 阀门试验保存辅助类
 * @author liaoyq
 *
 */
public class ProjectCheckListVTReq {

	private String pcId;
	private List<ValveTest> list;
	private String projId;
	private String recordsId;
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<ValveTest> getList() {
		return list;
	}
	public void setList(List<ValveTest> list) {
		this.list = list;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getRecordsId() {
		return recordsId;
	}
	public void setRecordsId(String recordsId) {
		this.recordsId = recordsId;
	}
	
}
