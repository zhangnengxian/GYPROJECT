package cc.dfsoft.project.biz.base.complete.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.complete.entity.DataAcceptanceRecord;
import cc.dfsoft.project.biz.base.project.dto.AccessoryQueryReq;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface DataAcceptanceRecordDao extends CommonDao<DataAcceptanceRecord, String>{
	
	/**
	 * 查询资料验收记录
	 * @author fuliwei
	 * @createTime 2017年8月3日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryDataAcceptanceRecord(AccessoryQueryReq req);
	
	/**
	 * 资料验收申请页面删除
	 * @author fuliwei
	 * @createTime 2017年8月8日
	 * @param 
	 * @return
	 */
	public void deleteByDaId(String daId);
}
