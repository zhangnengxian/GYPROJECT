package cc.dfsoft.project.biz.base.baseinfo.dao.impl;

import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.TimeLimitDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.TimeLimit;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

@Repository
public class TimeLimitDaoImpl extends NewBaseDAO<TimeLimit,String> implements TimeLimitDao {

	@Override
	public TimeLimit findByType(String tlType) {
		return this.get("tlType", tlType);
	}
}
