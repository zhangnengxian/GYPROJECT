package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.PreservativeInpectReq;
import cc.dfsoft.project.biz.base.inspection.entity.PreservativeInpect;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 防腐检查dao
 * @author liaoyq
 *
 */
public interface PreservativeInpectDao extends CommonDao<PreservativeInpect, String> {

	/**
	 * 根据报验单id删除防腐检查信息
	 * @param pcId
	 */
	void deleteByPcId(String pcId);

	/**
	 * 查询防腐检查信息
	 * @param preservativeInpect
	 * @return
	 */
	Map<String, Object> queryPreservativeInpect(
			PreservativeInpectReq preservativeInpect);

	/**
	 * 根据报验单Id查询防腐检查信息
	 * @param pcId
	 * @return
	 */
	PreservativeInpect queryByPcId(String pcId);

}
