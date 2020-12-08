package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ElectrodeBakingReq;
import cc.dfsoft.project.biz.base.inspection.entity.ElectrodeBaking;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 焊条烘烤dao接口层
 * @author liaoyq
 *
 */
public interface ElectrodeBakingDao extends CommonDao<ElectrodeBaking, String> {

	/**
	 * 根据条件分页 查询焊条烘烤记录列表
	 * @param electrodeBakingReq
	 * @return
	 */
	Map<String, Object> queryEclectrodeBakings(
			ElectrodeBakingReq electrodeBakingReq);

	/**
	 * 根据报验单ID删除焊条烘烤记录信息
	 * @param pcId
	 */
	void deleteByPcId(String pcId);

	Map<String, Object> queryRecords(ElectrodeBakingReq ebReq);

	/**
	 *　根据记录ID回写报验单ＩＤ
	 * @author laioyq
	 * @createTime 2017-7-20
	 * @param pcId 报验单ＩＤ
	 */
	void updatePcIdByRecordId(String id, String pcId);

	/**
	 * 清空报验单ID
	 * @param pcId
	 */
	void updateByPcId(String pcId);

}
