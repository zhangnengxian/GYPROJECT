package cc.dfsoft.project.biz.base.constructmanage.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.InspectionRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.InspectionRecord;
/**
 * 安全质量检查记录
 * @author Administrator
 *
 */
public interface InspectionRecordService {
	/**
	 * 安全质量检查记录条件查询
	 * @param inspectionRecordQueryReq
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> queryInspectionRecord(InspectionRecordQueryReq inspectionRecordQueryReq) throws Exception;
	/**
	 * 安全质量检查记录详述
	 * @param id
	 * @return
	 */
	InspectionRecord viewInspectionRecordResult(String id);
	/**
	 * 保存安全检查记录保存
	 * @param list
	 */
	void saveInfringeRecord(List<InspectionRecord> list);
	/**
	 * 保存多条违规记录更新数据库信息
	 * @param mapIns
	 */
	void reSaveInspctionRecords(Map<String, Object> mapIns);
	
	/**
	 * 安全质量检查TOP10
	 * @return
	 */
	List<Map<String, Object>> inspectionTop();

}
