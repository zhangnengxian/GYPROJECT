package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.InstallSummary;
/**
 * 安装汇总记录辅助类
 * @author liaoyq
 *
 */
public class ProjectCheckListISReq {
	
	private String pcId; //报验单Id
	private List<InstallSummary> list; //安装汇总记录
	private String projId;
	private String recordsId;
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<InstallSummary> getList() {
		return list;
	}
	public void setList(List<InstallSummary> list) {
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
