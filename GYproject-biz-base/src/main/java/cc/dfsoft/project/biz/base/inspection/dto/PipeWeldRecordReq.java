package cc.dfsoft.project.biz.base.inspection.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 焊口记录查询辅助类
 * @author lenovo
 * @createTime 2017年7月25日
 */
public class PipeWeldRecordReq extends PageSortReq{

	private String pcId;		//报验单ID
	private String projId;		//工程ID
	private Integer flag;
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
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}
