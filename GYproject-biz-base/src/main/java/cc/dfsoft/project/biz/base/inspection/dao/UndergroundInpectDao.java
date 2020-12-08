package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.UndergroundInpectReq;
import cc.dfsoft.project.biz.base.inspection.entity.UndergroundInpect;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 埋地检查dao接口
 * @author liaoyq
 *
 */
public interface UndergroundInpectDao extends CommonDao<UndergroundInpect, String> {

	
	/**
	 * 根据报验单Id 分页查询埋地检查记录
	 * @param undergroundInpectReq
	 * @return
	 */
	Map<String, Object> queryRecords(
			UndergroundInpectReq undergroundInpectReq);

	/**
	 * 根据报验ID删除埋地检查记录
	 * @param pcId
	 */
	void deleteByPcId(String pcId);

	/**
	 * @author liaoyq
	 * 删除已报验记录和未报验记录
	 * @param pcId
	 */
	void deletePcIdIsNull(String pcId);

	/**
	 * @author liaoyq
	 *  删除pcId为空的记录，即未报验的记录
	 */
	void deletePcIdIsNull();

	/**
	 *  根据记录id回写pcId
	 *  @author liaoyq
	 * @param id
	 * @param pcId
	 */
	void updatePcIdByRecordId(String id, String pcId);

	/**
	 * 置空报验pcId
	 * @author liaoyq
	 * @param pcId
	 */
	void updateByPcId(String pcId);

	Map<String, Object> queryUndergroundInpect(
			UndergroundInpectReq undergroundInpectReq);

}
