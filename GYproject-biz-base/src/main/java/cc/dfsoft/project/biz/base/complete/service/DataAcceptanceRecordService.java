package cc.dfsoft.project.biz.base.complete.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.project.biz.base.project.dto.AccessoryQueryReq;

public interface DataAcceptanceRecordService {
	
	/**
	 * 查询资料验收记录
	 * @author fuliwei
	 * @createTime 2017年8月3日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryDataAcceptanceRecord(HttpServletRequest request,AccessoryQueryReq req);
}
