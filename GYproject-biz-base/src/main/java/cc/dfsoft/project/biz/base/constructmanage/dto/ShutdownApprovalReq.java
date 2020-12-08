package cc.dfsoft.project.biz.base.constructmanage.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 复工申报辅助查询类
 * 
 * @author liaoyq
 *
 */
public class ShutdownApprovalReq extends PageSortReq {
	
	private String projId;
	private String sdrId;
	private String sdrNo;
	private String sdrProcess;
	private String reworkDateStart;
	private String reworkDateEnd;
	private Integer pushStatus;
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getSdrId() {
		return sdrId;
	}
	public void setSdrId(String sdrId) {
		this.sdrId = sdrId;
	}
	public String getSdrNo() {
		return sdrNo;
	}
	public void setSdrNo(String sdrNo) {
		this.sdrNo = sdrNo;
	}
	public String getReworkDateStart() {
		return reworkDateStart;
	}
	public void setReworkDateStart(String reworkDateStart) {
		this.reworkDateStart = reworkDateStart;
	}
	public String getReworkDateEnd() {
		return reworkDateEnd;
	}
	public void setReworkDateEnd(String reworkDateEnd) {
		this.reworkDateEnd = reworkDateEnd;
	}
	public String getSdrProcess() {
		return sdrProcess;
	}
	public void setSdrProcess(String sdrProcess) {
		this.sdrProcess = sdrProcess;
	}
	public Integer getPushStatus() {
		return pushStatus;
	}
	public void setPushStatus(Integer pushStatus) {
		this.pushStatus = pushStatus;
	}
	
}
