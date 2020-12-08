package cc.dfsoft.project.biz.base.subpackage.dao;


import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.subpackage.dto.SubSafeContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubSafeContract;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface SubSafeContractDao extends CommonDao<SubSafeContract,String>{

	/**
	 * 根据工程Id查安全协议
	 * @param projId
	 * @return
	 */
	SubSafeContract findSubSafeContractByprojId(String projId)throws ParseException;

	/**
	 * 安全协议列表条件查询
	 * @param subSafeContractQueryReq
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> querySubSafeContract(SubSafeContractQueryReq subSafeContractQueryReq)throws ParseException;
	
}
