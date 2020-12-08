package cc.dfsoft.project.biz.base.constructmanage.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 竣工报告Dto
 * @author Yuanyx
 *
 */
public class CompleteReportReq extends PageSortReq {
	
	private String projId;	//工程ID

	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
}