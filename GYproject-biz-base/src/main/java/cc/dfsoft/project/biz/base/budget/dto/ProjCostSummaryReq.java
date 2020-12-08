package cc.dfsoft.project.biz.base.budget.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 单位工程费用汇总
 * @author zhangjj
 *
 */
public class ProjCostSummaryReq extends PageSortReq{
   public String projId;
   public String costType;
   public String budgetId;
   public String costName;
   public String isAdjust; //是否调整
   
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
	public String getCostName() {
		return costName;
	}
	public void setCostName(String costName) {
		this.costName = costName;
	}
	public String getIsAdjust() {
		return isAdjust;
	}
	public void setIsAdjust(String isAdjust) {
		this.isAdjust = isAdjust;
	}
	public String getBudgetId() {
		return budgetId;
	}
	public void setBudgetId(String budgetId) {
		this.budgetId = budgetId;
	}

}
