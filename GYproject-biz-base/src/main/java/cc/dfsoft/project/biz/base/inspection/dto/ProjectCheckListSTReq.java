package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.StrengthTest;

/**
 * 保存强度试验辅助类
 * @author liaoyq
 *
 */
public class ProjectCheckListSTReq {

	private String pcId;
	private List<StrengthTest> list;
	private String recordsId;					//
	private String projId;
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<StrengthTest> getList() {
		return list;
	}
	public void setList(List<StrengthTest> list) {
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
