package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.PeWeldLineInpectReq;
import cc.dfsoft.project.biz.base.inspection.entity.PeWeldLineInspect;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * pe管焊缝检查dao接口
 * @author liaoyq
 *
 */
public interface PeWeldLineInspectDao extends CommonDao<PeWeldLineInspect, String>{

	/**
	 * 根据报验单ID删除pe管焊缝检查记录
	 * @param pcId
	 */
	void deleteByPcId(String pcId);

	/**
	 * 根据pcId分页查询pe管焊缝检查记录
	 * @param peWeldLineInpectReq
	 * @return
	 */
	Map<String, Object> queryPeWeldLineInpect(
			PeWeldLineInpectReq peWeldLineInpectReq);

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
	 * 删除已报验记录和未报验记录
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
	Map<String, Object> queryRecords(PeWeldLineInpectReq req);

}
