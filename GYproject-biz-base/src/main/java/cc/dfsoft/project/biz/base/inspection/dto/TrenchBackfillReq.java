package cc.dfsoft.project.biz.base.inspection.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 沟槽回填记录查询辅助类
 * @author lenovo
 * @createTime 2017年7月20日
 */
public class TrenchBackfillReq extends PageSortReq{
	
	
	private String pcId;       			//报验单id
	
	private String projId;				//工程Id

	private Integer flag; 				//报验单完成状态
	
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
