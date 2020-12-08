package cc.dfsoft.project.biz.base.budget.dao.impl;

import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.budget.dao.BudgetCostingDao;
import cc.dfsoft.project.biz.base.budget.entity.BudgetCosting;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

@Repository
public class BudgetCostingDaoImpl extends NewBaseDAO<BudgetCosting, String> implements BudgetCostingDao {
	
}
