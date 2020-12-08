package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.PipeWeldRecord;

/**
 * 焊口记录保存辅助类
 * @author liaoyq
 * @createTime 2017年7月25日
 */
public class ProjectCheckListPWRReq {

	private String pcId;
	private String projId;
	private List<PipeWeldRecord> list;
	
	private String recordsId;	//页面删除了的记录Id
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public List<PipeWeldRecord> getList() {
		return list;
	}
	public void setList(List<PipeWeldRecord> list) {
		this.list = list;
	}
	public String getRecordsId() {
		return recordsId;
	}
	public void setRecordsId(String recordsId) {
		this.recordsId = recordsId;
	}
}
