package cc.dfsoft.project.biz.base.constructmanage.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import cc.dfsoft.project.biz.base.constructmanage.dto.InspectionListQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.InspectionList;
/**
 * 安全质量检查单
 * @author Administrator
 *
 */
public interface InspectionListService {
	/**
	 * 安全质量检查单条件查询
	 * @param inspectionRecordQueryReq
	 * @return
	 * @throws ParseException
	 */
	Map<String,Object> queryInspectionList(InspectionListQueryReq inspectionListQueryReq) throws Exception;
	/**
	 * 安全质量检查单详述
	 * @param id
	 * @return
	 */
	InspectionList viewInspectionListResult(String id);
	/**
	 * 保存安全质量检查单
	 * @param inspectionList
	 * @return
	 */
	Map<String, Object> saveInspectionList(InspectionList inspectionList) throws Exception;
	
	/**
	 * 近三年施工部安全质量检查对比
	 * @author ht.hu
	 * @return
	 */
	List<Map<String, Object>> queryInspectionList(String param);

}
