package cc.dfsoft.project.biz.base.baseinfo.service;

import cc.dfsoft.project.biz.base.baseinfo.dto.BankQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.AccountBank;

import java.util.Map;

/**
 * @author cui
 * @createTime 2017-11-1
 * 开户账户服务接口
 */
public interface BankService {

	/**
	 * 列表查询
	 * @author cui
	 * @createTime 2017-11-1
	 * @param bankQueryReq
	 * @return
	 */
	Map<String,Object> queryBank(BankQueryReq bankQueryReq);

	/**
	 * 开户行详述
	 * @author wangang
	 * @createTime 2019-1-2
	 */
	AccountBank viewOpenBankById(String id);

	/**
	 * 开户行保存
	 * @author wangang
	 * @createTime 2019-1-2
	 */
	String saveOpenBank(AccountBank accountBank);

	/**
	 * 开户行删除
	 * @author wangang
	 * @createTime 2019-1-2
	 */
	void deleteOpenBank(String id);

	boolean saveOrUpdateBankAccount(AccountBank accountBank);
}




