package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.DerustingPreservativeQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.PreservativeReq;
import cc.dfsoft.project.biz.base.inspection.entity.DerustingPreservative;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 除锈防腐Dao
 * @author Administrator
 *
 */
public interface DerustingPreservativeDao extends CommonDao<DerustingPreservative, String>{
	
	/**
	 * 除锈防腐列表查询
	 * @author fuliwei
	 * @createTime 2016-7-26
	 * @param dpQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryDerusting(DerustingPreservativeQueryReq dpQueryReq);


	/**
	 * 分页查询防腐记录
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param preservativeReq
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryRecords(PreservativeReq preservativeReq);
	/**
	 * 删除已报验(pcId)和未报验的记录
	 * @author laioyq
	 * @createTime 2017-7-20
	 * @param pcId 报验单ＩＤ
	 */
	public void deletePcIdIsNull(String pcId);
	/**
	 * 删除未报验的记录
	 * @author laioyq
	 * @createTime 2017-7-20
	 * @param pcId 报验单ＩＤ
	 */
	public void deletePcIdIsNull();
	/**
	 * 根据报验单ID删除记录
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param pcId 报验单ID
	 */
	public void deleteByPcId(String pcId);
	/**
	 * 置空记录表中的报验单ID
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param pcId 报验单ID
	 */
	public void updateByPcId(String pcId);
	/**
	 * 根据记录ID回写报验单ID
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param id 记录Id
	 * @param pcId 报验单ID
	 */
	public void updatePcIdByRecordId(String id, String pcId);

	/**
	 * 根据工程ID删除记录
	 * @author liaoyq
	 * @createTime 2017-7-25
	 * @param projId 工程ID
	 */
	public void deleteByProjId(String projId);

	/**
	 * 根据报验单ID和防腐记录类型删除记录
	 * @author liaoyq
	 * @createTime 2017-7-25
	 * @param pcId
	 * @param preservativeType
	 */
	public void deleteByPcIdAndType(String pcId, String preservativeType);

	/**
	 * 根据工程ID和防腐记录类型删除记录
	 * @author liaoyq
	 * @createTime 2017-7-25
	 * @param projId
	 * @param preservativeType
	 */
	public void deleteByProjIdAndType(String projId, String preservativeType);
	
}
