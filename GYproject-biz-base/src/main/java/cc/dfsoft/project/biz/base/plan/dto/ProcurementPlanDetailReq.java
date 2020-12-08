package cc.dfsoft.project.biz.base.plan.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class ProcurementPlanDetailReq extends PageSortReq{
	
	private String procurPlanId;		//采购计划ID

	public String getProcurPlanId() {
		return procurPlanId;
	}

	public void setProcurPlanId(String procurPlanId) {
		this.procurPlanId = procurPlanId;
	}
	
	
}
