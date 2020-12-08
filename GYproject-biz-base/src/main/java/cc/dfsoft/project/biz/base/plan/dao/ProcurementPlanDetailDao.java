package cc.dfsoft.project.biz.base.plan.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.plan.dto.ProcurementPlanDetailReq;
import cc.dfsoft.project.biz.base.plan.entity.ProcurementPlanDetail;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public  interface ProcurementPlanDetailDao extends CommonDao<ProcurementPlanDetail, String>{

	/**
	 * 采购计划明细条件查询
	 * @param procurementPlanDetailReq
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> queryProcurementPlan(ProcurementPlanDetailReq procurementPlanDetailReq)throws ParseException;

	/**
	 * 根据采购计划Id查采购计划明细
	 * @param procurPlanId
	 * @return
	 */
	List<ProcurementPlanDetail> findByProcurPlanId(String procurPlanId) throws ParseException;

	/**
	 * 根据工程Id查采购计划明细
	 * @param projId
	 * @return
	 * @throws ParseException
	 */
	List<ProcurementPlanDetail> findByProjId(String projId);
	/**
	 * 根据工程Id查采购计划明细
	 * @param projId
	 * @return
	 * @throws ParseException
	 */
	List<ProcurementPlanDetail> findByProjIdAggregate(String projId);
	
}