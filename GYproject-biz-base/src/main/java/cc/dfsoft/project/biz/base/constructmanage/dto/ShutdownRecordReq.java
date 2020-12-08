package cc.dfsoft.project.biz.base.constructmanage.dto;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 停复工查询辅助类
 * @author liaoyq
 *
 */
public class ShutdownRecordReq extends PageSortReq {
	
	private String projId; //工程id
	
	private String sdrNo;//停复工编号
	
	private String sdrId;//停复工记录Id
	
	private Integer sdrStatus ;
	private String sdrProcess ;
	private String sdrType; //
	private String shutdownDateStart; //停复工起始日期
	private String shutdownDateEnd;   //停复工截至日期
	
	private Integer pushStatus;//推送状态

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getSdrNo() {
		return sdrNo;
	}

	public void setSdrNo(String sdrNo) {
		this.sdrNo = sdrNo;
	}

	public String getSdrId() {
		return sdrId;
	}

	public void setSdrId(String sdrId) {
		this.sdrId = sdrId;
	}

	public String getShutdownDateStart() {
		return shutdownDateStart;
	}

	public void setShutdownDateStart(String shutdownDateStart) {
		this.shutdownDateStart = shutdownDateStart;
	}

	public String getShutdownDateEnd() {
		return shutdownDateEnd;
	}

	public void setShutdownDateEnd(String shutdownDateEnd) {
		this.shutdownDateEnd = shutdownDateEnd;
	}

	public String getSdrType() {
		return sdrType;
	}

	public void setSdrType(String sdrType) {
		this.sdrType = sdrType;
	}

	public Integer getSdrStatus() {
		return sdrStatus;
	}

	public void setSdrStatus(Integer sdrStatus) {
		this.sdrStatus = sdrStatus;
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
