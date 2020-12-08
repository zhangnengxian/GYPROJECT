package cc.dfsoft.project.biz.base.plan.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.change.entity.MaterialChange;
import cc.dfsoft.project.biz.base.plan.dto.ProcurementPlanDetailReq;
import cc.dfsoft.project.biz.base.plan.entity.ProcurementPlan;
import cc.dfsoft.project.biz.base.plan.entity.ProcurementPlanDetail;

public interface ProcurementPlanDetailService {

	/**
	 * 采购计划明细条件查询
	 * @param procurementPlanDetailReq
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> queryProcurementPlanDetail(ProcurementPlanDetailReq procurementPlanDetailReq)throws ParseException;
	
	/**
	 * 根据采购计划Id查采购计划明细
	 * @param procurPlanId
	 * @return
	 */
	List<ProcurementPlanDetail> findByProcurPlanId(String procurPlanId)throws ParseException;
	/**
	 * 根据工程Id查采购计划明细
	 * @param projId
	 * @return
	 * @throws ParseException
	 */
	List<ProcurementPlanDetail> findByPrrojId(String projId)throws ParseException;
	
	public void saveDetail(ProcurementPlan procurementPlan);

	void savePlanDetail(ProcurementPlan procurementPlan,List<MaterialChange> materialChanges);

	void saveProcurementPlanDetail(ProcurementPlan procurementPlan);
	
}
