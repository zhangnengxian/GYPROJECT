package cc.dfsoft.project.biz.base.inspection.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 吹扫记录查询辅助类
 * @author liaoyq
 *
 */
public class PurgeReq extends PageSortReq{
	private String pcId;       			//报验单id
	
	private String projId;				//工程Id
	
	private Integer flag;				//报验单完成状态

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
