package cc.dfsoft.project.biz.base.budget.dao;

import cc.dfsoft.project.biz.base.budget.entity.Quantities;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author pengtt
 * @CreateTime 2016-06-29
 *
 */
public interface QuantitiesDao extends CommonDao<Quantities, String>{
	/**
	 * 根据工程Id查预算工程量
	 * @param projId
	 * @return
	 */
	Quantities findByprojId(String projId);
	
}
