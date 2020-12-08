package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.HotMeltDockingReq;
import cc.dfsoft.project.biz.base.inspection.entity.HotMeltDocking;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 热熔对接dao接口
 * @author liaoyq
 *
 */
public interface HotMeltDockingDao extends CommonDao<HotMeltDocking, String> {

	/**
	 * 根据报验单ID 分页查询热熔对接记录
	 * @param hotMeltDockingReq
	 * @return
	 */
	Map<String, Object> queryHotMeltDocking(HotMeltDockingReq hotMeltDockingReq);

	/**
	 * 根据报验单ID 删除热熔对接记录
	 * @param pcId
	 */
	void deleteByPcId(String pcId);

	/**
	 * 置空报验pcId
	 * @param pcId
	 */
	void updateByPcId(String pcId);

	/**
	 * 根据记录id回写pcId
	 * @param id
	 * @param pcId
	 */
	void updatePcIdByRecordId(String id, String pcId);

	/**
	 * 根据pcId删除记录
	 * @param pcId
	 */
	void deletePcIdIsNull(String pcId);

	/**
	 * 删除pcId为空的记录，即未报验的记录
	 */
	void deletePcIdIsNull();

	/**
	 * 分页查询记录
	 * @param trenchBackfillReq
	 * @return
	 */
	Map<String, Object> queryRecords(HotMeltDockingReq req);

}
