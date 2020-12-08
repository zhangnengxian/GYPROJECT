package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.ClearRecord;
import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 清扫记录查询辅助类
 * @author liaoyq
 * @createTime 2017年7月25日
 */
public class ClearRecordReq extends PageSortReq {

	private String projId;			//工程ID
	private String pcId;			//报验单ID
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
