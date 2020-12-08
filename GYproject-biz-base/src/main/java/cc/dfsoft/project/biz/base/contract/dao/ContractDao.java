package cc.dfsoft.project.biz.base.contract.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.contract.dto.ContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author fuliwei
 * @createTime 2016-6-28
 */
public interface ContractDao extends CommonDao<Contract, String>{

	/**
	 * 按合同编号查询
	 * @param conNo
	 * @return
	 */
	public List<Contract> findByConNo(String conNo);
	/**
	 * 根据工程ID查询合同
	 * @param id
	 * @return
	 */
	public Contract viewContractByprojId(String id);
	
	/**
	 * 合同列表查询查询
	 * @param contractQueryReq
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> queryContract(ContractQueryReq contractQueryReq) throws ParseException;
	public Map<String, Object> queryContractPrint(ContractQueryReq contractQueryReq)throws ParseException;
	/**
	 * 已经审核通过合同列表查询查询
	 * @param contractQueryReq
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> queryPassContract(ContractQueryReq contractQueryReq) throws ParseException;
	
}
