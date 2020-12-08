package cc.dfsoft.project.biz.base.subpackage.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.subpackage.dto.SubSafeContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubSafeContract;

public interface SubSafeContractService {

	/**
	 * 保存安全协议
	 * @param subSafeContract
	 * @return
	 */
	String saveSafeSubContract(SubSafeContract subSafeContract);

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
	 */
	Map<String, Object> querySubSafeContract(SubSafeContractQueryReq subSafeContractQueryReq) throws ParseException;
	
}
