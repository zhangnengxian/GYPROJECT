package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.PurgeQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.PurgeReq;
import cc.dfsoft.project.biz.base.inspection.entity.Purge;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface PurgeDao extends CommonDao<Purge, String>{
	
	/**
	 * 吹扫记录列表查询
	 * @author fuliwei
	 * @createTime 2016-7-20
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryPurge(PurgeQueryReq purgeQueryReq);
	
	/**
	 * 删除所有的吹扫记录
	 * @author
	 * @createTime 2016-09-08
	 * @param
	 * @return
	 */
	public void deleteByPcId(String pcId);

	/**
	 * 置空报验pcId
	 * @author liaoyq
	 * @param pcId
	 */
	public void updateByPcId(String pcId);

	/**
	 *  根据记录id回写pcId
	 *  @author liaoyq
	 * @param id
	 * @param pcId
	 */
	public void updatePcIdByRecordId(String id, String pcId);

	/**
	 * @author liaoyq
	 * 删除已报验记录和未报验记录
	 * @param pcId
	 */
	public void deletePcIdIsNull(String pcId);

	/**
	 * @author liaoyq
	 *  删除pcId为空的记录，即未报验的记录
	 */
	public void deletePcIdIsNull();

	/**
	 * 分页查询记录
	 * @author liaoyq
	 * @param purgeReq
	 * @return
	 */
	public Map<String, Object> queryRecords(PurgeReq purgeReq);

	public void clearPurgeId(String projId, String projNo, String pcId);

	public void updateBIdByProjIdAndNo(String projId, String id, String pcId);
}
