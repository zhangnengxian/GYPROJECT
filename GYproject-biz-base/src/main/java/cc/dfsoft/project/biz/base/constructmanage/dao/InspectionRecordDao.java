package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.InspectionRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.InspectionRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface InspectionRecordDao extends CommonDao<InspectionRecord, String> {
	/**
	 * 安全质量检查记录条件查询
	 * @param inspectionRecordQueryReq
	 * @return
	 * @throws ParseException
	 */
	Map<String,Object> queryInspectionRecord(InspectionRecordQueryReq inspectionRecordQueryReq) throws ParseException;
	/**
	 * 根据ilId删除违规记录
	 * @param object
	 */
	void deleByIlId(String ilId);
	
	/**
	 * 安全质量检查TOP10
	 * @author ht.hu
	 */
	List<Map<String, Object>> queryInspectionRecordTop();

}
