package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.HiddenWorks;
import cc.dfsoft.project.biz.base.inspection.entity.HiddenWorks;

/**
 * 隐蔽工程记录保存辅助类
 * @author liaoyq
 *
 */
public class ProjectCheckListHWReq {

	private String pcId;
	private String recordsId;
	private String projId;
	private List<HiddenWorks> list;
	
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<HiddenWorks> getList() {
		return list;
	}
	public void setList(List<HiddenWorks> list) {
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
