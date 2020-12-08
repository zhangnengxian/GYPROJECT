package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.BallRecord;

/**
 * 通球记录保存辅助类
 * @author liaoyq
 *
 */
public class ProjectCheckListBRReq {

	private String pcId;
	private List<BallRecord> list;
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<BallRecord> getList() {
		return list;
	}
	public void setList(List<BallRecord> list) {
		this.list = list;
	}
	
}
