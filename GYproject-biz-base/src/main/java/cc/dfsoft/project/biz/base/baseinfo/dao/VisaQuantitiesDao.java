package cc.dfsoft.project.biz.base.baseinfo.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.ConnectContentReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.VisaQuantities;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 签证工程量标准
 * @author fuliwei
 *
 */
public interface VisaQuantitiesDao extends CommonDao<VisaQuantities,String>{
	
	/**
	 * 查询签证工程量标准列表
	 * @author fuliwei
	 * @createTime 2017-2-4
	 * @param ConnectContentReq
	 * @return Map<String,Object> 
	 */
	public  Map<String,Object> queryVisaQuanlities(ConnectContentReq connectContentReq);
}
