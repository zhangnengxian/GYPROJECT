package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.ElectrodeBaking;

/**
 * 焊条烘烤记录辅助类
 * @author liaoyq
 *
 */
public class ProjectCheckListEBReq {

	private String pcId;        		//报验单id
	private List<ElectrodeBaking> list;//焊条烘烤记录
	private String recordsId;					//
	private String projId;
	
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<ElectrodeBaking> getList() {
		return list;
	}
	public void setList(List<ElectrodeBaking> list) {
		this.list = list;
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
	
}
