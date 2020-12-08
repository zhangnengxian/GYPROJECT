package cc.dfsoft.project.biz.base.inspection.dao;


import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.PipeWeldingReq;
import cc.dfsoft.project.biz.base.inspection.entity.PipeWelding;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface PipeWeldingDao extends CommonDao<PipeWelding, String>{
	/**
	 * 查询钢管焊接记录
	 * @param altimetricSurveyReq
	 * @return
	 */
	public Map<String,Object> queryPipeWelding(PipeWeldingReq pipeWeldingReq);
	
	/**
	 * 删除所有的钢管焊接记录
	 * @author fuliwei
	 * @createTime 2016-08-29
	 * @param pcId
	 * @return
	 */
	public void deleteByPcId(String pcId);

	public Map<String, Object> queryRecords(PipeWeldingReq pipeWeldingReq);

	/**
	 *　根据记录ID回写报验单ＩＤ
	 * @author laioyq
	 * @createTime 2017-7-20
	 * @param pcId 报验单ＩＤ
	 */
	public void updatePcIdByRecordId(String id, String pcId);

	/**
	 * 清空pcId
	 * @param pcId
	 */
	public void updateByPcId(String pcId);
}
