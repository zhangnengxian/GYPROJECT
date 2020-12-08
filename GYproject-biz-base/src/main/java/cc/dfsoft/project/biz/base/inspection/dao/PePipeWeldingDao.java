package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.PePipeWeldingReq;
import cc.dfsoft.project.biz.base.inspection.dto.PipeWeldingReq;
import cc.dfsoft.project.biz.base.inspection.entity.PepipeWelding;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface PePipeWeldingDao  extends CommonDao<PepipeWelding, String>{
	
	/**
	 * 查询PE管焊接记录
	 * @param altimetricSurveyReq
	 * @return
	 */
	public Map<String,Object> queryPePipeWelding(PePipeWeldingReq pePipeWeldingReq);
	
	/**
	 * 删除pe管焊接记录
	 * @author
	 * @createTime 2016-09-07
	 * @param
	 * @return
	 */
	public void deleteByPcId(String pcId);

	public Map<String, Object> queryRecords(PePipeWeldingReq pipeWeldingReq);

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
