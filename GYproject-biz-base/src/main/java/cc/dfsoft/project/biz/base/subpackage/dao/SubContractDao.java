package cc.dfsoft.project.biz.base.subpackage.dao;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.subpackage.dto.SubContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface SubContractDao extends CommonDao<SubContract,String>{
	/**
	 * 按合同编号查询
	 * @param scNo
	 * @return
	 */
	public List<SubContract> findByScNo(String scNo);
	/**
	 * 分包合同列表条件查询
	 * @param subContractQueryReq
	 * @return
	 */
	public Map<String, Object> querySubContract(SubContractQueryReq subContractQueryReq)throws ParseException;
	/**
	 * 根据工程ID查分包合同
	 * @param projId
	 * @return
	 */
	public SubContract findSubContractByprojId(String projId)throws ParseException;
	
	/**
	 * 根据工程编号查分包合同
	 * @param projNo
	 * @return
	 */
	List<SubContract> findByProjNo(String id);
	/**
	 * 根据工程ID查询分包合同信息
	 * @param projId
	 * @return
	 */
	public SubContract findByProjId(String projId);
}
