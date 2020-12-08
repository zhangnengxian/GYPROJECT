package cc.dfsoft.project.biz.base.subpackage.dao;


import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.subpackage.dto.SubSupplyContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubSupplyContract;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface SubSupplyContractDao extends CommonDao<SubSupplyContract,String>{
	
	/**
	 * @author pengtt
	 * @createTime 2016-09-20 
	 * @param projId
	 * @return
	 */
	public SubSupplyContract findByProjId(String projId);

	/**
	 * 分包协议查询
	 * @author cui
	 * @createTime 2016-09-21 
	 * @param subSupplyContractQueryReq
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> querySubSupplyContract(SubSupplyContractQueryReq subSupplyContractQueryReq) throws ParseException;
	
}
