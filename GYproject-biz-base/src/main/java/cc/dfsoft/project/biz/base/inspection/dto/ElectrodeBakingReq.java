package cc.dfsoft.project.biz.base.inspection.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 焊条烘烤查询条件辅助类
 * @author liaoyq
 *
 */
public class ElectrodeBakingReq extends PageSortReq{

	private String pcId;//报验单id
	
	private String recordsId;	//记录id组合
	
	private String projId;		//工程ID
	
	private Integer flag;
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

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}
