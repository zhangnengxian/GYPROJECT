package cc.dfsoft.project.biz.base.constructmanage.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.EngineeringVisaQueryReq;

/**
 * 签证标准记录
 * @author fuliwei
 *
 */
public interface VisaQuantitiesRecordService {
	

	/**
	 * 签证标准记录列表查询
	 * @author fuliwei
	 * @createTime 2017年2月5日
	 * @param req
	 * @return Map<String,Object>
	 */
	public Map<String,Object> queryVisaQuantitiesRecord(EngineeringVisaQueryReq req);
}