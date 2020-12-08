package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.TrenchBackfill;

/**
 * 沟槽回填检查记录保存辅助类
 * @author liaoyq
 *
 */
public class ProjectCheckListTBReq {

	private String pcId;
	private String recordsId;
	private String projId;
	private List<TrenchBackfill> list;
	
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<TrenchBackfill> getList() {
		return list;
	}
	public void setList(List<TrenchBackfill> list) {
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
