package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;








import cc.dfsoft.project.biz.base.inspection.dto.TrenchBackfillReq;
import cc.dfsoft.project.biz.base.inspection.entity.TrenchBackfill;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface TrenchBackfillDao  extends CommonDao<TrenchBackfill, String>{
	
	public TrenchBackfill queryTrenchBackfillById(String id);

	/**
	 * 分页查询沟槽回填记录
	 * @param trenchBackfillReq
	 * @return
	 */
	public Map<String, Object> queryRecords(
			TrenchBackfillReq trenchBackfillReq);


	/**
	 * 删除pcId为空的记录，即未报验的记录
	 */
	public void deletePcIdIsNull();

	/**
	 * 置空报验pcId
	 * @param pcId
	 */
	public void updateByPcId(String pcId);

	/**
	 * 根据pcId删除记录
	 * @param pcId
	 */
	public void deleteByPcId(String pcId);

	/**
	 * 根据记录id回写pcId
	 * @param id
	 * @param pcId
	 */
	public void updatePcIdByRecordId(String id, String pcId);

	/**
	 * 删除已报验记录和未报验记录
	 * @param pcId
	 */
	public void deletePcIdIsNull(String pcId);
}
