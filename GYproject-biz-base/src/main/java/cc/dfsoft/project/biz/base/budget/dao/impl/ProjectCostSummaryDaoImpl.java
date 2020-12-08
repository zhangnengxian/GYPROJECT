package cc.dfsoft.project.biz.base.budget.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.budget.dao.ProjectCostSummaryDao;
import cc.dfsoft.project.biz.base.budget.dto.ProjCostSummaryReq;
import cc.dfsoft.project.biz.base.budget.entity.ProjectCostSummary;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class ProjectCostSummaryDaoImpl extends NewBaseDAO<ProjectCostSummary, String> implements ProjectCostSummaryDao {

	@Override
	public Map<String,Object> queryCostSummary(ProjCostSummaryReq projCostSummaryReq) {
		 Criteria c = super.getCriteria();
			
		 //工程id
		 if(StringUtils.isNotBlank(projCostSummaryReq.getProjId())){
			 c.add(Restrictions.eq("projId",projCostSummaryReq.getProjId()));
		 }
		 //费用类型
		 if(StringUtils.isNotBlank(projCostSummaryReq.getCostType())){
			 c.add(Restrictions.eq("costType",projCostSummaryReq.getCostType()));
		 }
		 //费用名称
		 if(StringUtils.isNotBlank(projCostSummaryReq.getCostName())){
			 c.add(Restrictions.like("costName","%"+projCostSummaryReq.getCostName()+"%"));
		 }
		 //费用名称
		 if(StringUtils.isNotBlank(projCostSummaryReq.getBudgetId())){
			 c.add(Restrictions.like("budgetId",projCostSummaryReq.getBudgetId()));
		 }
		//是否调整
		 if(StringUtils.isNotBlank(projCostSummaryReq.getIsAdjust())){
			 c.add(Restrictions.eq("isAdjust",projCostSummaryReq.getIsAdjust()));
		 }
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<ProjectCostSummary> list = this.findBySortCriteria(c, projCostSummaryReq);
			
		 // 返回结果
		 return ResultUtil.pageResult(filterCount, projCostSummaryReq.getDraw(),list);
		
	}

	@Override
	public List<ProjectCostSummary> queryCostSummaryList(ProjCostSummaryReq projCostSummaryReq) {
		 Criteria c = super.getCriteria();
		 //费用类型
		 if(StringUtils.isNotBlank(projCostSummaryReq.getCostType())){
			 c.add(Restrictions.eq("costType",projCostSummaryReq.getCostType()));
			
		 }
		 if(StringUtils.isNotBlank(projCostSummaryReq.getBudgetId())){
			 c.add(Restrictions.eq("budgetId",projCostSummaryReq.getBudgetId()));
			
		 }
		 if(StringUtils.isNotBlank(projCostSummaryReq.getIsAdjust())){
			 c.add(Restrictions.eq("isAdjust",projCostSummaryReq.getIsAdjust()));
			
		 }
		 // 根据条件获取查询信息
		 List<ProjectCostSummary> list = this.findBySortCriteria(c, projCostSummaryReq);
		 return list;
	}
	
}
