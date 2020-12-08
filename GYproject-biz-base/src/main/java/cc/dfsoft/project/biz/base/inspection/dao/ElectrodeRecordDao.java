package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ElectrodeRecordReq;
import cc.dfsoft.project.biz.base.inspection.entity.ElectrodeRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 焊条领用Dao接口层
 * @author liaoyq
 *
 */
public interface ElectrodeRecordDao extends CommonDao<ElectrodeRecord, String> {

	/**
	 * 根据报验单id删除焊条领用记录
	 * @param pcId 报验单id
	 */
	void deleteByPcId(String pcId);

	/**
	 * 根据条件查询焊条领用记录列表
	 * @param electrodeRecordReq
	 * @return
	 */
	Map<String, Object> queryEclectrodeRecords(
			ElectrodeRecordReq electrodeRecordReq);

	/**
	 *　根据记录ID回写报验单ＩＤ
	 * @author laioyq
	 * @createTime 2017-7-20
	 * @param pcId 报验单ＩＤ
	 */
	void updatePcIdByRecordId(String id, String pcId);

	Map<String, Object> queryRecords(ElectrodeRecordReq electrodeRecordReq);

	/**
	 * 清空报验单ID
	 * @param pcId
	 */
	void updateByPcId(String pcId);

}
