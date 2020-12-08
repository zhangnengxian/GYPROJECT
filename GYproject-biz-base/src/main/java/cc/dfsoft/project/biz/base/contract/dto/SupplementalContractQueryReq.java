package cc.dfsoft.project.biz.base.contract.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class SupplementalContractQueryReq extends PageSortReq {

	private String projId;			 //工程ID
	private String projNo;			 //工程编号
	private String scNo;			 //协议编号
	private String projName;		 //工程名称
	private String projAddr;		 //工程地点
	private String custName;		 //甲方名称
	private String signDateStart;	 //签订日期开始
	private String signDateEnd;		 //签定日期结束
	private String isPrint;			 //是否打印
	private String modifyStatus;     //null:无修改，0:修改审批中，1：修改审核已通过，2：修改审核未通过
	private String isAudit;			 //1 推送 2审核通过
	private Integer timeLimit;     		    //限制完成的时间段
	private String  stepId;	                //步骤
	private  String corpId;			//分公司ID
	private  String menuId;			//菜单ID
	
	
	private String cmId;//
	private String mcType;//
	
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
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getSignDateStart() {
		return signDateStart;
	}
	public void setSignDateStart(String scSignDateStart) {
		this.signDateStart = scSignDateStart;
	}
	public String getSignDateEnd() {
		return signDateEnd;
	}
	public void setSignDateEnd(String scSignDateEnd) {
		this.signDateEnd = scSignDateEnd;
	}
	public String getScNo() {
		return scNo;
	}
	public void setScNo(String scNo) {
		this.scNo = scNo;
	}
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	public String getCmId() {
		return cmId;
	}
	public void setCmId(String cmId) {
		this.cmId = cmId;
	}
	public String getMcType() {
		return mcType;
	}
	public void setMcType(String mcType) {
		this.mcType = mcType;
	}
	public Integer getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getIsAudit() {
		return isAudit;
	}
	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}
	public String getProjAddr() {
		return projAddr;
	}
	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	public String getModifyStatus() {
		return modifyStatus;
	}
	public void setModifyStatus(String modifyStatus) {
		this.modifyStatus = modifyStatus;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}
