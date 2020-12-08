package cc.dfsoft.project.biz.base.budget.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class BudgetTotalQueryReq extends PageSortReq{
	public String projId;
	public String projNo;
	public String budgetId;
	public String budgetType;
	public String cmId;
	
	public String getBudgetType() {
		return budgetType;
	}
	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
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
	
	public String getBudgetId() {
		return budgetId;
	}
	public void setBudgetId(String budgetId) {
		this.budgetId = budgetId;
	}
	public String getCmId() {
		return cmId;
	}
	public void setCmId(String cmId) {
		this.cmId = cmId;
	}
}
