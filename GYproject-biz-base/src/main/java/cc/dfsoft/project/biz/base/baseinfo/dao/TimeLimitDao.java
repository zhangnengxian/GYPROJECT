package cc.dfsoft.project.biz.base.baseinfo.dao;

import cc.dfsoft.project.biz.base.baseinfo.entity.TimeLimit;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 时长限制Dao
 * @author Yuanyx
 *
 */
public interface TimeLimitDao extends CommonDao<TimeLimit,String>{
	/**
	 * 根据类型查时限记录
	 * @param tlType
	 * @return
	 */
	TimeLimit findByType(String tlType);
}
