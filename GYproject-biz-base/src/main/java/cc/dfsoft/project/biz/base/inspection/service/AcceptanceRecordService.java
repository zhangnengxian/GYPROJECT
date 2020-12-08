package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ElectrodeRecordReq;
import cc.dfsoft.project.biz.base.inspection.entity.AcceptanceRecord;
import cc.dfsoft.project.biz.base.inspection.entity.PipeWeldRecord;

/**
 * 验收登记
 * @author fulw
 *
 */
public interface AcceptanceRecordService {
	
	/**
	 * 分页查询验收登记记录
	 * @author fuliwei  
	 * @date 2018年12月4日  
	 * @version 1.0
	 */
	Map<String, Object> queryRecords(ElectrodeRecordReq electrodeRecordReq);
	
	/**
	 * 保存验收登记记录
	 * @author fuliwei  
	 * @date 2018年12月4日  
	 * @version 1.0
	 */
	String saveRecord(AcceptanceRecord record);
	
	/**
	 * 根据记录ID删验收登记记录
	 * @author fuliwei  
	 * @date 2018年12月4日  
	 * @version 1.0
	 */
	void deleteRecordById(String arId);
	
	/**
	 * 根据记录ID回写报验单ID
	 * @author fuliwei  
	 * @date 2018年12月4日  
	 * @version 1.0
	 */
	void updatePcIdByRecordsId(String recordsId, String pcId, String projId, String projNo);
	
	/**
	 * 查询详述
	 * @author fuliwei  
	 * @date 2018年12月9日  
	 * @version 1.0
	 */
	AcceptanceRecord viewRecordById(String id);
}
