package cc.dfsoft.project.biz.base.budget.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.budget.dao.BudgetAdjustDao;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.entity.BudgetAdjust;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
/**
 * 预算调整Dao实现
 * @author Administrator
 *
 */
@Repository
public class BudgetAdjustDaoImpl extends NewBaseDAO<BudgetAdjust, String> implements BudgetAdjustDao {

	/**
	 * 根据工程id查询预算调整表
	 * @author fuliwei
	 * @createTime 2016-7-13
	 * @param projId
	 * @return BudgetAdjust
	 */
	@Override
	public BudgetAdjust queryByprojId(String projId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from BudgetAdjust e where e.projId='").append(projId).append("'");
		List<BudgetAdjust>list = super.findByHql(hql.toString());
		if(null!=list&&list.size()>0){
		return list.get(0);
		}
		return null;
	}

}
