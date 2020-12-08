package cc.dfsoft.project.biz.base.plan.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.plan.dto.ProcurementPlanReq;
import cc.dfsoft.project.biz.base.plan.entity.ProcurementPlan;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public  interface ProcurementPlanDao extends CommonDao<ProcurementPlan, String>{

	/**
	 * 采购计划条件列表查询
	 * @param procurementPlanReq
	 * @return
	 */
	Map<String, Object> queryProcurementPlan(ProcurementPlanReq procurementPlanReq)throws ParseException;

	ProcurementPlan findByBusinessOrder(String businessOrderId, String status);
	
	List<ProcurementPlan> queryProcuPlanById(String projId);
	
	/**
	 * 工程交底 根据工程id和状态
	 * @author
	 * @createTime 2017-1-28
	 * @param 
	 * @return
	 */
	ProcurementPlan findBypProjIdAndStatus(String projId, String status);
}
