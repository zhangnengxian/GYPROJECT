package cc.dfsoft.project.biz.base.project.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 工程规模明细查询
 * @author pengtt
 * @createTime 2016-06-22
 *
 */
public class ScaleDetailQueryReq extends PageSortReq{
	
	private String projLtypeId;
	private String projId;

	
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjLtypeId() {
		return projLtypeId;
	}

	public void setProjLtypeId(String projLtypeId) {
		this.projLtypeId = projLtypeId;
	}
	
}
