package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.IndoorPocketWatchReq;
import cc.dfsoft.project.biz.base.inspection.entity.IndoorPocketWatch;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 户内挂表dao接口层
 * @author liaoyq
 * @createTime 2017年7月24日
 */
public interface IndoorPocketWatchDao extends CommonDao<IndoorPocketWatch, String>{

	/**
	 * 根据提条件分页查询户内挂表记录
	 * @author liaoyq
	 * @createTime 2017年7月24日
	 * @param indoorPocketWatchReq 户内挂表查询辅助类
	 * @return 户内挂表记录 
	 */
	Map<String, Object> queryRecords(IndoorPocketWatchReq indoorPocketWatchReq);

	/**
	 * 删除已报验的记录
	 * @author liaoyq
	 * @createTime 2017年7月24日
	 * @param pcId 报验单ID
	 */
	void deletePcIdIsNull(String pcId);

	/**
	 * 删除未报验的记录
	 * @author liaoyq
	 * @createTime 2017年7月24日
	 */
	void deletePcIdIsNull();

	/**
	 *  置空户内挂表报验记录的pcId
	 *  @author liaoyq
	 *  @createTime 2017年7月24日
	 *  @param pcId 报验单ID
	 */
	void updateByPcId(String pcId);

	/**
	 * 根据记录表ID回写报验单ID
	 * @author liaoyq
	 * @createTime 2017年7月24日
	 * @param id 户内挂表记录ID
	 * @param pcId 报验单ID
	 */
	void updatePcIdByRecordId(String id, String pcId);

	/**
	 * 查询表字段
	 * @return
	 */
	List<Map<String, Object>> showTableColumns();

	/**
	 * 增加字段
	 * @param str
	 * @return 
	 */
	boolean addTableColumns(String str);
	/**
	 *分页查询户内挂表记录
	 * @author liaoyq
	 * @createTime 2017年7月24日
	 * @param indoorPocketWatchReq 户内挂表记录查询辅助类
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryRecordsBySql(IndoorPocketWatchReq indoorPocketWatchReq);

}
