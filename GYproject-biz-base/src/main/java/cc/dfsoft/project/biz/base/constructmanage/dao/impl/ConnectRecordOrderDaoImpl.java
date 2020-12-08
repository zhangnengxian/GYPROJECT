package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.TouchRecordOrderDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectRecordOrder;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

@Repository
public class ConnectRecordOrderDaoImpl extends NewBaseDAO<ConnectRecordOrder, String> implements TouchRecordOrderDao {

	@Override
	public List<ConnectRecordOrder> findbyProjId(String projId) {
		StringBuffer hql = new StringBuffer("from ConnectRecordOrder where projId='").append(projId).append("'");
		List<ConnectRecordOrder> result = super.findByHql(hql.toString());
		return result;
	}

	
}
