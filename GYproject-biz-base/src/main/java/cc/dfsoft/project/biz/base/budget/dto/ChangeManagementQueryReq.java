package cc.dfsoft.project.biz.base.budget.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

import java.util.List;

public class ChangeManagementQueryReq extends PageSortReq{
 
	private String projId;			//工程ID
	private String projName;		//工程名称
	private String projAddr;		//工程地点
	private String projNo;			//工程编号
	private String cmNo;			//变更编号
	private String cmDateStart; 	//变更开始日期
	private String cmDateEnd;		//变更结束日期
	private String applyDateStart;	//变更开始日期
	private String applyDateEnd;	//变更结束日期
	private String cuReason;		//变更原因
	private String cmState;			//变更状态
	private String changeType;		//变更类型
	private List<String> auditState;//审核状态
	
	private String designChangeType;//设置变更状态

	private String menuId;//菜单


	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getCmDateStart() {
		return cmDateStart;
	}

	public void setCmDateStart(String cmDateStart) {
		this.cmDateStart = cmDateStart;
	}

	public String getCmDateEnd() {
		return cmDateEnd;
	}

	public void setCmDateEnd(String cmDateEnd) {
		this.cmDateEnd = cmDateEnd;
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

	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	public String getCmNo() {
		return cmNo;
	}

	public void setCmNo(String cmNo) {
		this.cmNo = cmNo;
	}

	public String getCuReason() {
		return cuReason;
	}

	public void setCuReason(String cuReason) {
		this.cuReason = cuReason;
	}

	public String getCmState() {
		return cmState;
	}

	public void setCmState(String cmState) {
		this.cmState = cmState;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public List<String> getAuditState() {
		return auditState;
	}

	public void setAuditState(List<String> auditState) {
		this.auditState = auditState;
	}

	public String getDesignChangeType() {
		return designChangeType;
	}

	public void setDesignChangeType(String designChangeType) {
		this.designChangeType = designChangeType;
	}

	public String getApplyDateStart() {
		return applyDateStart;
	}

	public void setApplyDateStart(String applyDateStart) {
		this.applyDateStart = applyDateStart;
	}

	public String getApplyDateEnd() {
		return applyDateEnd;
	}

	public void setApplyDateEnd(String applyDateEnd) {
		this.applyDateEnd = applyDateEnd;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}
