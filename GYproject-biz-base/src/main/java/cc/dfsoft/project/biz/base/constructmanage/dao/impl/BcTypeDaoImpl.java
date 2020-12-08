package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.BcTypeDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.BcType;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

@Repository
public class BcTypeDaoImpl extends NewBaseDAO<BcType, String> implements BcTypeDao {

	@Override
	public List<BcType> findBcType(String typeId, String relationTypeId) {
		Criteria criteria = super.getCriteria();
		 //类型Id
		if(StringUtils.isNotBlank(typeId)){
			criteria.add(Restrictions.eq("typeId",typeId));
		}
		//关联类型Id
		criteria.add(Restrictions.eq("relationTypeId",relationTypeId));
		List<BcType> list = this.findByCriteria(criteria);
		return list;
	}

	@Override
	public List<BcType> findAllBcType() {
		Criteria criteria = super.getCriteria();
		List<BcType> list = this.findByCriteria(criteria);
		return list;
	}
}
