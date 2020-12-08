package cc.dfsoft.project.biz.base.subpackage.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.contract.entity.PayType;
import cc.dfsoft.project.biz.base.subpackage.dto.IntelligentMeterContractReq;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 
 * 描述:智能表合同dao接口
 * @author liaoyq
 * @createTime 2017年9月16日
 */
public interface IntelligentMeterContractDao extends CommonDao<IntelligentMeterContract, String>{

	/**
	 * 根据工程ID查询智能表合同信息
	 * @param projId
	 * @return
	 */
	IntelligentMeterContract findContractByprojId(String projId);

	/**
	 * 根据合同编号查询合同信息
	 * @param imcNo
	 * @return
	 */
	List<IntelligentMeterContract> findByImcNo(String imcNo);

	/**
	 * 分页查询智能表合同信息
	 * @param imcReq
	 * @return
	 * @throws ParseException 
	 */
	Map<String, Object> queryContract(IntelligentMeterContractReq imcReq) throws ParseException;


	/**
	 * 分页查询智能表合同工程列表
	 * @param imcReq
	 * @return
	 */
	Map<String, Object> queryImContractBySql(IntelligentMeterContractReq imcReq);

}
