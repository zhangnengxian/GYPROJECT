package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.InspectionListQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.InspectionList;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface InspectionListDao extends CommonDao<InspectionList, String> {
	/**
	 * 安全质量检查单条件查询
	 * @param inspectionRecordQueryReq
	 * @return
	 * @throws ParseException
	 */
	Map<String,Object> queryInspectionList(InspectionListQueryReq inspectionListQueryReq) throws Exception;
	
	/**
	 * 近三年施工部安全质量检查对比
	 * @author ht.hu
	 * @return
	 */
	List<Map<String, Object>> queryInspectionList(String param);

}
