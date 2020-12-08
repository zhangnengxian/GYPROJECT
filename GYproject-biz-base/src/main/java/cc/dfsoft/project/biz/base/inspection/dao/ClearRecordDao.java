package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ClearRecordReq;
import cc.dfsoft.project.biz.base.inspection.entity.ClearRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 清扫记录dao接口
 * @author liaoyq
 * @createTime 2017年7月25日
 */
public interface  ClearRecordDao extends CommonDao<ClearRecord, String> {

	/**
	 * 根据pcId将记录的报验单ID置空
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param pcId 报验单ID
	 */
	void updateByPcId(String pcId);

	/**
	 * 根据记录ID回写报验单ID
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param id 记录ID
	 * @param pcId 报验单ID
	 */
	void updatePcIdByRecordId(String id, String pcId);

	/**
	 * 分页查询记录
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param clearRecordReq 查询条件dto
	 * @return Map<String, Object>
	 */
	Map<String, Object> queryRecords(ClearRecordReq clearRecordReq);

	/**
	 * 根据报验单ID删除已报验和未报验的记录
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param pcId 报验单ID
	 */
	void deletePcIdIsNull(String pcId);
	
	/**
	 * 根据工程ID删除未报验的记录
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param projId 工程ID
	 */
	void deletePcIdIsNullByProjId(String projId);

	/**
	 * 根据报验ID删除记录
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param pcId 报验ID
	 */
	void deleteByPcId(String pcId);

}
