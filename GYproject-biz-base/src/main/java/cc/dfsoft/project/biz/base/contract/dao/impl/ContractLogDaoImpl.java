package cc.dfsoft.project.biz.base.contract.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.contract.dao.ContractLogDao;
import cc.dfsoft.project.biz.base.contract.entity.ContractLog;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

@Repository
public class ContractLogDaoImpl extends NewBaseDAO<ContractLog, String> implements ContractLogDao{

	@Override
	public ContractLog findByModifystate(String modifystate, String orlId) {
		Criteria c = super.getCriteria();
		c.add(Restrictions.eq("modifystate", modifystate));
		c.add(Restrictions.eq("orlId", orlId));
		List<ContractLog> list = this.findByCriteria(c);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
