package cc.dfsoft.project.biz.base.subpackage.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.subpackage.dto.SubSupplyContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubSupplyContract;

public interface SubSupplyContractService {
	
	/**
	 * 保存分包补充协议
	 * @param subSupplyContract
	 * @return
	 * @author ht.hu
	 */
	public void saveSubSpplyContract(SubSupplyContract subSupplyContract);

	/**
	 * 分包补充协议条件查询
	 * @param subSupplyContractQueryReq
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> querySubSupplyContract(SubSupplyContractQueryReq subSupplyContractQueryReq) throws ParseException;

	/**
	 * 分包补充协议标记
	 * @param projId
	 */
	public void signSubAgreementPrint(String projId);
}
