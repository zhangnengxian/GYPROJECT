package cc.dfsoft.uexpress.biz.sys.dept.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class ProjectTotalStatictisReq extends PageSortReq{

	private String operate_log_id;

	public String getOperate_log_id() {
		return operate_log_id;
	}

	public void setOperate_log_id(String operate_log_id) {
		this.operate_log_id = operate_log_id;
	}
}
