package cc.dfsoft.project.biz.base.inspection.dao;

import cc.dfsoft.project.biz.base.inspection.dto.GrooveRecordReq;
import cc.dfsoft.project.biz.base.inspection.entity.GrooveRecord;
import cc.dfsoft.project.biz.base.inspection.entity.PressureRecords;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.Map;

/**
 * 沟槽记录dao
 * @author zhangjj
 * @createTime 2016-07-21
 */
public interface PressureRecordsDao extends CommonDao<PressureRecords, String>{
	/**
	 * 查询复压记录
	 * @author wangang
	 * @createTime 2019-01-07
	 * @return Map
	 */
	public Map<String, Object> queryPressureRecords(GrooveRecordReq dtoReq);
	
	/**
	 * 根据pcId删除所有的复压记录
	 * @author wangang
	 * @createTime 2019-01-07
	 * @param pcId
	 * @return
	 */
	public void deleteByPcId(String pcId);

	public Map<String, Object> queryRecords(GrooveRecordReq grReq);

	/**
	 *　根据记录ID回写报验单ID
	 * @author wangang
	 * @createTime 2019-01-07
	 * @param pcId 报验单ID
	 */
	public void updatePcIdByRecordId(String id, String pcId);

	/**
	 * 清空pcID
	 * @param pcId
	 */
	public void updateByPcId(String pcId);
}
