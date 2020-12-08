package cc.dfsoft.project.biz.base.budget.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.budget.dto.BudgetTotalQueryReq;
import cc.dfsoft.project.biz.base.budget.entity.BudgetTotalTable;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface BudgetTotalTableDao extends CommonDao<BudgetTotalTable, String>{
	
	public List<BudgetTotalTable> queryBudgetTotalTableList(BudgetTotalQueryReq req);
	
	public Map<String, Object> queryBudgetTotal(BudgetTotalQueryReq budgetTotalQueryReq);
}
