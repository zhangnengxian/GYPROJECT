package cc.dfsoft.project.biz.base.subpackage.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class SubSupplyContractQueryReq extends PageSortReq {

	private String sscNo;					//补充协议编号
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private String projName;				//工程名称
	private String projAddr;				//工程地点
	private String projStatusId;			//工程状态
	private String sscSignDateStart;			//签订日期开始
	private String sscSignDateEnd;			//签定日期结束
	private String isPrint;					//是否打印标记     0 已打印,1未打印
	
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	public String getSscNo() {
		return sscNo;
	}
	public void setSscNo(String sscNo) {
		this.sscNo = sscNo;
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
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getProjAddr() {
		return projAddr;
	}
	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	public String getProjStatusId() {
		return projStatusId;
	}
	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
	}
	public String getSscSignDateStart() {
		return sscSignDateStart;
	}
	public void setSscSignDateStart(String sscSignDateStart) {
		this.sscSignDateStart = sscSignDateStart;
	}
	public String getSscSignDateEnd() {
		return sscSignDateEnd;
	}
	public void setSscSignDateEnd(String sscSignDateEnd) {
		this.sscSignDateEnd = sscSignDateEnd;
	}
}
