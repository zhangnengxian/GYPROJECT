package cc.dfsoft.project.biz.base.complete.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.complete.dao.DataFeedbackDao;
import cc.dfsoft.project.biz.base.complete.entity.FilingData;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
@Repository
public class DataFeedbackDaoImpl extends NewBaseDAO<FilingData, String> implements DataFeedbackDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<FilingData> findByProjNo(String projNo) {
		StringBuffer hql = new StringBuffer();
		hql.append("from FilingData where projNo = '").append(projNo).append("'");
		return super.findByHql(hql.toString());
	}

}
