package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.HiddenWorksReq;
import cc.dfsoft.project.biz.base.inspection.dto.TrenchBackfillReq;
import cc.dfsoft.project.biz.base.inspection.entity.HiddenWorks;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 隐蔽工程检查记录Dao
 * @author Administrator
 *
 */
public interface HiddenWorksDao extends CommonDao<HiddenWorks, String>{
	/**
	 * 按报验单ID查询
	 * @author fuliwei
	 * @createTime 2016-7-29
	 * @param pcId
	 * @return HiddenWorks
	 */
	public HiddenWorks viewHiddenWorks(String id);

	/**
	 * 分页查询记录
	 * @param hiddenWorksReq
	 * @return
	 */
	public Map<String, Object> queryHiddenWorks(
			HiddenWorksReq hiddenWorksReq);


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

	/**
	 * 分页查询记录
	 * @param hiddenWorksReq
	 * @return
	 */
	public Map<String, Object> queryTrenchBackfill(HiddenWorksReq hiddenWorksReq);

	/**
	 * 分页查询记录
	 * @param hiddenWorksReqt
	 * @return
	 */
	public Map<String, Object> queryRecords(HiddenWorksReq hiddenWorksReqt);
}
