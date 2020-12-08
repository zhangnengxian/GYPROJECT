package cc.dfsoft.project.biz.base.budget.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class BudgetReq extends PageSortReq{
	
	public String projId;
	public String projNo;//工程编号

	public String costType;
	public String isAdjust;
	public String cmId;
	public String mcType;
	public String budgeter;//预算员
	public String budgetTypeId;//预算类型 0正常预算 1变更预算
	public String isSignSuContrct;//是否签订补充协议 1是 0 否
	public String changeState;//变更处理状态 0 未处理 1 已处理
	public String projName;
	public String projAddr;
	public String custName;//客户名称
	public String signDateStart;			//签定日期开始
	public String signDateEnd;				//签定日期结束
	private String designChangeType;//设置变更状态
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getSignDateStart() {
		return signDateStart;
	}
	public void setSignDateStart(String signDateStart) {
		this.signDateStart = signDateStart;
	}
	public String getSignDateEnd() {
		return signDateEnd;
	}
	public void setSignDateEnd(String signDateEnd) {
		this.signDateEnd = signDateEnd;
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
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getIsSignSuContrct() {
		return isSignSuContrct;
	}
	public void setIsSignSuContrct(String isSignSuContrct) {
		this.isSignSuContrct = isSignSuContrct;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	public String getIsAdjust() {
		return isAdjust;
	}
	public String getBudgeter() {
		return budgeter;
	}
	public void setBudgeter(String budgeter) {
		this.budgeter = budgeter;
	}
	public void setIsAdjust(String isAdjust) {
		this.isAdjust = isAdjust;
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
	public String getBudgetTypeId() {
		return budgetTypeId;
	}
	public void setBudgetTypeId(String budgetTypeId) {
		this.budgetTypeId = budgetTypeId;
	}
	public String getChangeState() {
		return changeState;
	}
	public void setChangeState(String changeState) {
		this.changeState = changeState;
	}
	public String getDesignChangeType() {
		return designChangeType;
	}
	public void setDesignChangeType(String designChangeType) {
		this.designChangeType = designChangeType;
	}
	
	
}
