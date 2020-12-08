package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.StrengthTestReq;
import cc.dfsoft.project.biz.base.inspection.entity.StrengthTest;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 强度试验dao接口
 * @author liaoyq
 *
 */
public interface StrengthTestDao extends CommonDao<StrengthTest, String> {

	/**
	 * 根据报验单Id和试验类型 分页查询试验记录
	 * @param strengthTestReq
	 * @return
	 */
	Map<String, Object> queryStrengthTest(StrengthTestReq strengthTestReq);

	/**
	 * 根据报验单id删除试验记录
	 * @param pcId
	 *
	 */
	void deleteByPcId(String pcId);

	/**
	 * 分页查询记录
	 * @param trenchBackfillReq
	 * @return
	 */
	Map<String, Object> queryRecords(StrengthTestReq req);

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

}
