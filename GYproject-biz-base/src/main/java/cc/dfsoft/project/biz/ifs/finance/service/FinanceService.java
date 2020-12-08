package cc.dfsoft.project.biz.ifs.finance.service;

import javax.jws.WebService;

/**
 * 
 * 描述:工程系统提供的财务接口
 * @author liaoyq
 * @createTime 2017年11月15日
 */

@WebService
public interface FinanceService {

	/**
	 * 付款成功后的接口，需要提供给用友系统进行调用
	 * @param userName
	 * @param password
	 * @param paymentStr
	 * @return
	 */
	public String paymentDone(String userName,String password,String paymentStr);
	
}
