package cc.dfsoft.project.biz.base.plan.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.plan.dao.ProcurementPlanDetailDao;
import cc.dfsoft.project.biz.base.plan.dto.ProcurementPlanDetailReq;
import cc.dfsoft.project.biz.base.plan.entity.ProcurementPlanDetail;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class ProcurementPlanDetailDaoImpl extends NewBaseDAO<ProcurementPlanDetail, String> implements ProcurementPlanDetailDao{
	
	@Override
	public Map<String, Object> queryProcurementPlan(ProcurementPlanDetailReq procurementPlanDetailReq)
			throws ParseException {
		Criteria c = super.getCriteria();
		String proId = procurementPlanDetailReq.getProcurPlanId();
		 //采购计划id
		 if(StringUtils.isNotBlank(procurementPlanDetailReq.getProcurPlanId())){
			 c.add(Restrictions.eq("procurPlanId",procurementPlanDetailReq.getProcurPlanId()));
		 }
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<ProcurementPlanDetail> procurementPlanList = this.findBySortCriteria(c, procurementPlanDetailReq);
		
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, procurementPlanDetailReq.getDraw(),procurementPlanList);
	}

	@Override
	public List<ProcurementPlanDetail> findByProcurPlanId(String procurPlanId) throws ParseException {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(procurPlanId)){
		c.add(Restrictions.eq("procurPlanId",procurPlanId));
		c.addOrder(Order.asc("procurPlanDetailId"));
		List<ProcurementPlanDetail> procurementPlanDetails =this.findByCriteria(c);
		return procurementPlanDetails;
		}
		return null;
	}

	@Override
	public List<ProcurementPlanDetail> findByProjId(String projId){
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
		c.add(Restrictions.eq("projId",projId));
		List<ProcurementPlanDetail> procurementPlanDetails =this.findByCriteria(c);
		return procurementPlanDetails;
		}
		return null;
	}

	@Override
	public List<ProcurementPlanDetail> findByProjIdAggregate(String projId) {
		// TODO Auto-generated method stub
		return null;
	}

}
