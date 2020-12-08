package cc.dfsoft.project.biz.base.subpackage.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.subpackage.dao.SubContractLogDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContractLog;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
/**
 * 
 * 描述:分合同日志dao实现
 * @author liaoyq
 * @createTime 2018年1月23日
 */
@Repository
public class SubContractLogDaoImpl extends NewBaseDAO<SubContractLog, String> implements
		SubContractLogDao {

	@Override
	public SubContractLog findByModifystate(String modifyState, String orlId) {
		Criteria c = super.getCriteria();
		c.add(Restrictions.eq("modifyState", modifyState));
		c.add(Restrictions.eq("orlId", orlId));
		List<SubContractLog> list = this.findByCriteria(c);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
