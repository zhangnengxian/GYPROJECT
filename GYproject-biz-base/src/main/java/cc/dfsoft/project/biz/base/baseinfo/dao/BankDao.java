package cc.dfsoft.project.biz.base.baseinfo.dao;

import cc.dfsoft.project.biz.base.baseinfo.dto.BankQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.AccountBank;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.Map;

/**
 * 银行账户dao
 * @author cui
 *
 */
public interface BankDao extends CommonDao<AccountBank,String>{
	
	/**
	 * 列表查询
	 * @author cui
	 * @CreateTime 2017-11-1
	 * @param bankQueryReq
	 * @return
	 */
	Map<String,Object> queryBank(BankQueryReq bankQueryReq);
}
