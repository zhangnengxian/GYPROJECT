package cc.dfsoft.project.biz.base.contract.dao.impl;

import cc.dfsoft.project.biz.base.contract.dao.ImcContractLogDao;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContractLog;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImcContractLogDaoImpl extends NewBaseDAO<IntelligentMeterContractLog, String> implements ImcContractLogDao {

	@Override
	public IntelligentMeterContractLog findByModifystate(String modifystate, String orlId) {
		Criteria c = super.getCriteria();
		c.add(Restrictions.eq("modifyState", modifystate));
		c.add(Restrictions.eq("orlId", orlId));
		List<IntelligentMeterContractLog> list = this.findByCriteria(c);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
