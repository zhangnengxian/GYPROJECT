package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;
import cc.dfsoft.project.biz.base.inspection.entity.Purge;
/**
 * 保存记录区辅助类
 * @author liaoyq
 *
 */
public class ProjectCheckListPReq {

	private String pcId;
	private String recordsId;
	private String projId;
	private List<Purge> list;
	
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<Purge> getList() {
		return list;
	}
	public void setList(List<Purge> list) {
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
