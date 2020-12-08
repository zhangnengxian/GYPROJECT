package cc.dfsoft.project.biz.base.budget.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.budget.dao.QuantitiesDao;
import cc.dfsoft.project.biz.base.budget.entity.Quantities;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

@Repository
public class QuantitiesDaoImpl extends NewBaseDAO<Quantities, String> implements QuantitiesDao {

	@SuppressWarnings("unchecked")
	@Override
	public Quantities findByprojId(String projId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Quantities where projId = '").append(projId).append("'");
		List<Quantities> list=super.findByHql(hql.toString());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
