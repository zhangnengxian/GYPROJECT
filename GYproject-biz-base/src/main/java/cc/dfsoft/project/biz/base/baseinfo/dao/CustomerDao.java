package cc.dfsoft.project.biz.base.baseinfo.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.CustomerQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Customer;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 客户dao
 * @author pengtt
 *
 */
public interface CustomerDao extends CommonDao<Customer,String>{
	
	/**
	 * 列表查询
	 * @author pengtt
	 * @CreateTime 2016-07-22
	 * @param customerQueryReq
	 * @return
	 */
	public Map<String, Object> queryCustomer(CustomerQueryReq customerQueryReq);

	/**
	 * 根据客户编码查询客户信息
	 * @param strPrex
	 * @param string 
	 * @return
	 */
	public Customer queryByCustCode(String strPrex, String string);

	/**
	 * 根据客户编码模糊查询客户信息
	 * @param strPrex
	 * @return
	 */
	public Customer queryByCustCodePrex(String strPrex);

	/**
	 * 根据客户编码查询客户信息
	 * @param deptOutCode
	 * @return
	 */
	public Customer queryByCustCode(String deptOutCode);

	/**
	 * 
	 * @author liaoyq
	 * @createTime 2018年4月22日
	 * @param custName
	 * @param custContcat 
	 * @param custPhone 
	 * @return
	 */
	public Customer queryByCustName(String custName, String custPhone, String custContcat,String corpId);

	/**
	 * 查询是否存在此用户
	 * @author liaoyq
	 * @createTime 2018年8月16日
	 * @param customer
	 * @return
	 */
	public Customer queryCustomer(Customer customer);
	
	
}
