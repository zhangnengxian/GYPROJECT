package cc.dfsoft.project.biz.base.constructmanage.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 工程日志查询
 * @author pengtt
 * @createTime 2016-07-21
 *
 */
public class DailyLogQueryReq extends PageSortReq{
	
	private String projId;				//工程id
	

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	
}
