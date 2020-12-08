package cc.dfsoft.project.biz.base.inspection.dao;
import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.GrooveRecordReq;
import cc.dfsoft.project.biz.base.inspection.entity.GrooveRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 沟槽记录dao
 * @author zhangjj
 * @createTime 2016-07-21
 */
public interface GrooveRecordDao extends CommonDao<GrooveRecord, String>{
	/**
	 * 查询沟槽记录
	 * @author zhangjj
	 * @createTime 2016-07-21
	 * @param altimetricSurveyReq
	 * @return Map
	 */
	public Map<String, Object> queryGrooveRecord(GrooveRecordReq dtoReq);
	
	/**
	 * 根据pcId删除所有的沟槽记录
	 * @author zhangjj
	 * @createTime 2016-07-21
	 * @param pcId
	 * @return
	 */
	public void deleteByPcId(String pcId);

	public Map<String, Object> queryRecords(GrooveRecordReq grReq);

	/**
	 *　根据记录ID回写报验单ＩＤ
	 * @author laioyq
	 * @createTime 2017-7-20
	 * @param pcId 报验单ＩＤ
	 */
	public void updatePcIdByRecordId(String id, String pcId);

	/**
	 * 清空pcID
	 * @param pcId
	 */
	public void updateByPcId(String pcId);
}
