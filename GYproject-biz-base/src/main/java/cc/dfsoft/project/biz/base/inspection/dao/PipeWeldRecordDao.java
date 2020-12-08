package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.PipeWeldRecordReq;
import cc.dfsoft.project.biz.base.inspection.entity.PipeWeldRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 焊口记录dao接口
 * @author liaoyq
 * @createTime 2017年7月25日
 */
public interface PipeWeldRecordDao extends CommonDao<PipeWeldRecord, String> {

	/**
	 * 根据报验ID 置空报验的pcId
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param pcId 报验ID
	 */
	void updateByPcId(String pcId);

	/**
	 * 根据记录ID回写报验ID
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param id 记录ID
	 * @param pcId 报验ID
	 */
	void updatePcIdByRecordId(String id, String pcId);

	/**
	 * 分页查询记录
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param pipeWeldRecordReq 焊口记录查询辅助类
	 * @return Map<String, Object>
	 */
	Map<String, Object> queryRecords(PipeWeldRecordReq pipeWeldRecordReq);

	/**
	 * 根据报验单ID删除已报验记录和未报验记录
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param pcId 报验单ID
	 */
	void deletePcIdIsNull(String pcId);

	/**
	 * 根据工程ID 删除未报验的记录
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
