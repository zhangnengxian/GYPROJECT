package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ElectrodeRecordReq;
import cc.dfsoft.project.biz.base.inspection.entity.AcceptanceRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface AcceptanceRecordDao extends CommonDao<AcceptanceRecord, String>{
	/**
	 * 分页查询验收登记记录
	 * @author fuliwei  
	 * @date 2018年12月4日  
	 * @version 1.0
	 */
	Map<String, Object> queryRecords(ElectrodeRecordReq electrodeRecordReq);
	
	/**
	 * 根据报验单ID删验收登记记录
	 * @author fuliwei  
	 * @date 2018年12月4日  
	 * @version 1.0
	 */
	void deleteByPcId(String pcId);
	
	/**
	 * 根据记录ID回写报验单
	 * @author fuliwei  
	 * @date 2018年12月4日  
	 * @version 1.0
	 */
	void updatePcIdByRecordId(String id, String pcId);
	
	/**
	 * 清空pcId
	 * @param pcId
	 */
	public void updateByPcId(String pcId);
}
