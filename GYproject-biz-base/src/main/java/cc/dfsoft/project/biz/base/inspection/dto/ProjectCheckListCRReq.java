package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.ClearRecord;

/**
 * 清扫记录保存辅助类
 * @author liaoyq
 * @createTime 2017年7月25日
 */
public class ProjectCheckListCRReq {
	private String pcId;				//报验单ＩＤ
	private String recordsId;			//记录ＩＤ组合
	private String projId;				//工程ＩＤ
	private List<ClearRecord> list;		//清扫记录
	
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
	public List<ClearRecord> getList() {
		return list;
	}
	public void setList(List<ClearRecord> list) {
		this.list = list;
	}
	
	
	
}
