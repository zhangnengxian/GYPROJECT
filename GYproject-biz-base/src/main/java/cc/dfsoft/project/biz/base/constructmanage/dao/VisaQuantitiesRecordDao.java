package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.EngineeringVisaQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.VisaQuantitiesRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 签证标准记录
 * @author fuliwei
 *
 */
public interface VisaQuantitiesRecordDao extends CommonDao<VisaQuantitiesRecord,String>{
	
	/**
	 * 签证标准记录列表查询
	 * @author fuliwei
	 * @createTime 2017年2月4日
	 * @param  EngineeringVisaQueryReq
	 * @return Map<String,Object>
	 */
	public Map<String, Object> queryVisaQuantitiesRecord(EngineeringVisaQueryReq req);
	
	/**
	 * 根据id删除原来的签证工程量
	 * @author fuliwei
	 * @createTime 2017年2月9日
	 * @param evId
	 * @return
	 */
	public void deleteById(String evId);
}
