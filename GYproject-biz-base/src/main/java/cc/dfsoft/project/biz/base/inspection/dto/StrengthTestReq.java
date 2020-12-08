package cc.dfsoft.project.biz.base.inspection.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 强度试验记录查询
 * @author liaoyq
 *
 */
public class StrengthTestReq extends PageSortReq {

	private String pcId;
	
	private String stPipeType;

	private String projId;
	
	private Integer flag; //报验单完成状态
	
	public String getPcId() {
		return pcId;
	}
	
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public String getStPipeType() {
		return stPipeType;
	}
	public void setStPipeType(String stPipeType) {
		this.stPipeType = stPipeType;
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
