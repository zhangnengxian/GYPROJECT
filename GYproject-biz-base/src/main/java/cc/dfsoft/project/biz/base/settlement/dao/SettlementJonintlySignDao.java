package cc.dfsoft.project.biz.base.settlement.dao;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.settlement.dto.SettlementDeclarationReq;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementJonintlySign;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface SettlementJonintlySignDao extends CommonDao<SettlementJonintlySign, String>{
	
	/**
	 * 结算汇签单列表
	 * @author fuliwei
	 * @createTime 2017年9月26日
	 * @param 
	 * @return
	 */
	public Map<String,Object> querySettlementJonintlySign(SettlementDeclarationReq req)throws ParseException;
	
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年9月26日
	 * @param 
	 * @return
	 */
	public SettlementJonintlySign findById(String projId);

}
