package cc.dfsoft.project.biz.base.baseinfo.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 关联关系Dao
 * @author Yuanyx
 *
 */
public interface CorrelationDao extends CommonDao<Correlation, String>{

	/**
	 * 关联关系列表条件查询
	 * @param correlationReq
	 * @return
	 */
	Map<String, Object> queryCorrelation(CorrelationReq correlationReq);

	/**
	 * 根据关联类型和相关信息查关联信息
	 * @param corType
	 * @param correlateInfoId
	 * @return
	 */
	List<Correlation> findCorType(CorrelationReq req);

	/**
	 * 查工程类型
	 * @param corType
	 * @param list
	 * @return
	 */
	List<Correlation> findProjType(String corType, List<String> list);

	/**
	 * 获取出资方式编码
	 * @param corType
	 * @param projType
	 * @param contributionModelId
	 * @param corpId 
	 * @return
	 */
	Correlation findCode(String corType, String projType, String contributionModelId, String corpId);
	
	/**
	 * 通过编号查询
	 * @author fuliwei
	 * @createTime 2017年9月8日
	 * @param 
	 * @return
	 */
	Correlation findByContriubbtionCode(String code);
}
