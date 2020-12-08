package cc.dfsoft.project.biz.base.plan.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.plan.dto.ProcurementPlanReq;
import cc.dfsoft.project.biz.base.plan.entity.ProcurementPlan;

public interface ProcurementPlanService {

	/**
	 * 采购计划条件查询
	 * @param procurementPlanReq
	 * @return
	 */
	Map<String, Object> queryProcurementPlan(ProcurementPlanReq procurementPlanReq)throws ParseException;
	public void saveProcurementPlan(ProcurementPlan procurementPlan);
	
	public List<ProcurementPlan> queryProcuPlanById(String projId);
	ProcurementPlan findByBusiness(String businessOrderId);
	/**
	 * 采购计划导出标记
	 * @param procurPlanId
	 * @return
	 */
	String signprocurementPlanExport(String procurPlanId);
}
