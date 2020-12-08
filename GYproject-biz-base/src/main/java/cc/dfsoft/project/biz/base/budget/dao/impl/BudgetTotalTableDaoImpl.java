package cc.dfsoft.project.biz.base.budget.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.budget.dao.BudgetTotalTableDao;
import cc.dfsoft.project.biz.base.budget.dto.BudgetTotalQueryReq;
import cc.dfsoft.project.biz.base.budget.entity.BudgetTotalTable;
import cc.dfsoft.project.biz.base.budget.entity.ProjectCostSummary;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
@Repository
public class BudgetTotalTableDaoImpl extends NewBaseDAO<BudgetTotalTable, String> implements BudgetTotalTableDao{

	@Override
	public List<BudgetTotalTable> queryBudgetTotalTableList(BudgetTotalQueryReq req) {
		 Criteria c = super.getCriteria();
		 //费用类型
		 if(StringUtils.isNotBlank(req.getProjId())){
			 c.add(Restrictions.eq("projId",req.getProjId()));
			
		 }
		 if(StringUtils.isNotBlank(req.getBudgetId())){
			 c.add(Restrictions.eq("budgetId",req.getBudgetId()));
			
		 }
		 if(StringUtils.isNotBlank(req.getCmId())){
			 c.add(Restrictions.eq("cmId",req.getCmId()));
			
		 }
		 if(StringUtils.isNotBlank(req.getBudgetType())){
			 c.add(Restrictions.eq("budgetType",req.getBudgetType()));
		 }
		 
		 // 根据条件获取查询信息
		 List<BudgetTotalTable> list = this.findBySortCriteria(c, req);
		 return list;
	}

	@Override
	public Map<String, Object> queryBudgetTotal(BudgetTotalQueryReq budgetTotalQueryReq) {
		Criteria c = super.getCriteria();
		if(StringUtil.isNotBlank(budgetTotalQueryReq.getProjId())){
			c.add(Restrictions.eq("projId", budgetTotalQueryReq.getProjId()));
		}
		if(StringUtil.isNotBlank(budgetTotalQueryReq.getProjNo())){
			c.add(Restrictions.eq("projNo", budgetTotalQueryReq.getProjNo()));
		}
		if(StringUtil.isNotBlank(budgetTotalQueryReq.getCmId())){
			c.add(Restrictions.eq("cmId", budgetTotalQueryReq.getCmId()));
		}
		if(StringUtils.isNotBlank(budgetTotalQueryReq.getBudgetType())){
			 c.add(Restrictions.eq("budgetType",budgetTotalQueryReq.getBudgetType()));
		 } 
		
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<BudgetTotalTable> list = this.findBySortCriteria(c, budgetTotalQueryReq);
			
		// 返回结果
		return ResultUtil.pageResult(filterCount, budgetTotalQueryReq.getDraw(),list);
	}

}
