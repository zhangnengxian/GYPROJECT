package cc.dfsoft.project.biz.base.budget.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class BudgetChangeReq extends PageSortReq{
 
	private String projId;		//工程ID
	private String budgetTypeId;//预算类型
	private String changeState;//变更处理状态 0 未处理 1 已处理
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
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
	
}
