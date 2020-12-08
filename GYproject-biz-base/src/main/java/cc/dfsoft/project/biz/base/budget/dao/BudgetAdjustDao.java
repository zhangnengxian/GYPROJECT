package cc.dfsoft.project.biz.base.budget.dao;

import cc.dfsoft.project.biz.base.budget.entity.BudgetAdjust;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 预算调整Dao
 * @author Administrator
 *
 */
public interface BudgetAdjustDao extends CommonDao<BudgetAdjust, String>{
	
	/**
	 * 根据工程id查询预算调整表
	 * @author fuliwei
	 * @createTime 2016-7-13
	 * @param projId
	 * @return BudgetAdjust
	 */
	BudgetAdjust queryByprojId(String projId);
}
