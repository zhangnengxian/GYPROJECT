package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.BallRecordReq;
import cc.dfsoft.project.biz.base.inspection.entity.BallRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 通球记录dao接口
 * @author liaoyq
 *
 */
public interface BallRecordDao extends CommonDao<BallRecord, String> {

	/**
	 * 根据报验单id分页查询通球记录
	 * @param ballRecordReq
	 * @return
	 */
	Map<String, Object> queryBallRecord(BallRecordReq ballRecordReq);

	/**
	 * 根据pcId删除通球记录
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
	 * 分页查询通球记录
	 * @param ballRecordReq
	 * @return
	 */
	Map<String, Object> queryRecords(BallRecordReq req);

	/**
	 * 删除已报验记录和未报验记录
	 * @param pcId
	 */
	void deletePcIdIsNull(String pcId);

	/**
	 * 删除pcId为空的记录，即未报验的记录
	 */
	void deletePcIdIsNull();

}
