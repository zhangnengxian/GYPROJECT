package cc.dfsoft.project.biz.base.inspection.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 防腐记录查询辅助类
 * @author liaoyq
 * @createTime 2017年7月24日
 */
public class PreservativeReq extends PageSortReq {

	private String pcId;		//报验单ID
	
	private String recordsId;	//记录id组合
	
	private String projId;		//工程ID
	
	private String preservativeType; //防腐记录类型
	
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
	
	public String getPreservativeType() {
		return preservativeType;
	}

	public void setPreservativeType(String preservativeType) {
		this.preservativeType = preservativeType;
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
