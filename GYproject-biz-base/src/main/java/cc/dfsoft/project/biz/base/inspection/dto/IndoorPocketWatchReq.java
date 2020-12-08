package cc.dfsoft.project.biz.base.inspection.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 户内挂表查询辅助类
 * @author liaoyq
 * @createTime 2017年7月24日
 */
public class IndoorPocketWatchReq extends PageSortReq {

	private String projId;
	private String pcId;
	private Integer flag;
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}
