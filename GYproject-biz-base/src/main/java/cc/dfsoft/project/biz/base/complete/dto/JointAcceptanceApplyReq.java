package cc.dfsoft.project.biz.base.complete.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 
 * 描述:联合验收申请辅助类
 * @author liaoyq
 * @createTime 2018年9月9日
 */
public class JointAcceptanceApplyReq extends PageSortReq {
	private String auditState;
	private String projId;
	private String projNo;
	private String jaaId;
	public String getAuditState() {
		return auditState;
	}
	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getJaaId() {
		return jaaId;
	}
	public void setJaaId(String jaaId) {
		this.jaaId = jaaId;
	}
	

}
