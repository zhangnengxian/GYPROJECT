package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.WeldLineInpectReq;
import cc.dfsoft.project.biz.base.inspection.entity.WeldLineInpect;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 管道焊缝检查dao接口
 * @author liaoyq
 *
 */
public interface WeldLineInpectDao extends CommonDao<WeldLineInpect,String>{

	/**
	 * 分页查询管道焊缝检查记录
	 * @param weldLineInpectReq
	 * @return
	 */
	Map<String, Object> queryWeldLineInpect(WeldLineInpectReq weldLineInpectReq);

	/**
	 * 根据报验单ＩＤ删除焊缝检查记录
	 * @param pcId
	 */
	void deleteByPcId(String pcId);

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
	Map<String, Object> queryRecords(WeldLineInpectReq req);

	/**
	 * 置空报验pcId
	 * @param pcId
	 */
	public void updateByPcId(String pcId);
}
