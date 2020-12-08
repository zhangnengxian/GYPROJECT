package cc.dfsoft.project.biz.base.baseinfo.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.CustomerQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Customer;

/**
 * @author pengtt
 * @createTime 2016-07-22
 * 客户服务接口
 */
public interface CustomerService {
	
	/**
	 * 保存cust对象
	 * @author pengtt
	 * @createTime 2016-07-22
	 * @param customer
	 */
	public String saveCust(Customer customer);
	
	/**
	 * 列表查询
	 * @author pengtt
	 * @createTime 2016-07-22
	 * @param customerQueryReq
	 * @return
	 */
	public Map<String,Object> queryCustomer(CustomerQueryReq customerQueryReq);
	
	/**
	 * 查询客户信息
	 * @author zhangjj
	 * @createTime 2016-07-25
	 * @param id
	 * @return
	 */
	public Customer queryCustomerById(String id);

	/**
	 * 获取客户编码、地区编码
	 * @param areaCode
	 * @return
	 */
	Customer getCustCode(Customer customer);

	/**
	 * 根据客户信息查询，如果已存在该客户，则返回客户信息，否则，增加客户
	 * @author liaoyq
	 * @createTime 2018年8月16日
	 * @param customer
	 * @return
	 */
	public Customer queryOrInsertCustomer(Customer customer);

	
}




