package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.DailyLogQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.DailyLog;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 工程日志Dao
 * @author pengtt
 * @createTime 2016-07-21
 *
 */
public interface DailyLogDao extends CommonDao<DailyLog,String>{
	
	/**
	 * 工程日志
	 * @param reviewRecordQueryReq
	 * @return
	 */
	public Map<String, Object> queryDailyLog(DailyLogQueryReq dailyLogQueryReq);

	/**
	 * 根据工程ID查询所有施工日志
	 * @param projId
	 * @return
	 */
	public List<DailyLog> findByProjId(String projId);
	
	
}
