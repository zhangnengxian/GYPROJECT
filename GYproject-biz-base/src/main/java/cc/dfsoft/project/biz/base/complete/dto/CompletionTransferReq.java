package cc.dfsoft.project.biz.base.complete.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class CompletionTransferReq extends PageSortReq{
	
	private String fdId;			                    //交接单ID
	private String projId;                              //项目id
	private String projName;                            //项目名称
	private String projNo;					            //项目编号
	private String fdDateStart;	                        //归档日期开始
	private String fdDateEnd;	                        //归档日期结束
	private String fdConnectDateStart;	                //交接日期开始
	private String fdConnectDateEnd;	                //交接日期结束
	private String isPrint;					//是否打印标记     0 已打印,1未打印
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	public String getFdId() {
		return fdId;
	}
	public void setFdId(String fdId) {
		this.fdId = fdId;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getFdDateStart() {
		return fdDateStart;
	}
	public void setFdDateStart(String fdDateStart) {
		this.fdDateStart = fdDateStart;
	}
	public String getFdDateEnd() {
		return fdDateEnd;
	}
	public void setFdDateEnd(String fdDateEnd) {
		this.fdDateEnd = fdDateEnd;
	}
	public String getFdConnectDateStart() {
		return fdConnectDateStart;
	}
	public void setFdConnectDateStart(String fdConnectDateStart) {
		this.fdConnectDateStart = fdConnectDateStart;
	}
	public String getFdConnectDateEnd() {
		return fdConnectDateEnd;
	}
	public void setFdConnectDateEnd(String fdConnectDateEnd) {
		this.fdConnectDateEnd = fdConnectDateEnd;
	}
	
	
}
