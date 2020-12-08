package cc.dfsoft.project.biz.base.baseinfo.service;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;

/**
 * 关联关系接口
 * @author Yuanyx
 *
 */
public interface CorrelationService {

	/**
	 * 关联关系列表条件查询
	 * @param correlationReq
	 * @return
	 */
	Map<String, Object> queryCorrelation(CorrelationReq correlationReq);

	/**
	 * 查相关信息及关联信息
	 * @param corType
	 * @return
	 */
	Map<String, List<Map<String, String>>> queryCorrelateInfoId(String corType);

	/**
	 * 保存或更新关联记录
	 * @param correlation
	 */
	void saveOrUpdateCorrelation(Correlation correlation);

	/**
	 * 删除关联关系
	 * @param id
	 * @return
	 */
	String deleteCorrelation(String id);

	/**
	 * 根据关联类型和相关信息查关联信息
	 * @param corType
	 * @param correlateInfoId
	 * @return
	 */
	List<Correlation> findCorType(CorrelationReq req);

	/**
	 * 查询工程类型
	 * @param value
	 * @param department
	 * @return
	 */
	List<Correlation> findProjType(String corType, List<Correlation> department);

	/**
	 * 获取出资方式编码
	 * @param value
	 * @param projType
	 * @param contributionModelId
	 * @param corpId 
	 * @return
	 */
	Correlation findCode(String value, String projType, String contributionModelId, String corpId);
	
	/**
	 * 通过编号查询
	 * @author fuliwei
	 * @createTime 2017年9月8日
	 * @param 
	 * @return
	 */
	Correlation findByContriubbtionCode(String code);
	
	/**
	 * 查询所有的辅助流程
	 * @author fuliwei  
	 * @date 2018年9月21日  
	 * @version 1.0
	 */
	List<Correlation> queryAssistWorkFlow();
}
