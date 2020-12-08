package cc.dfsoft.project.biz.base.baseinfo.dao.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.CustomerDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.CustomerQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Customer;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CustomerDaoImpl extends NewBaseDAO<Customer,String> implements CustomerDao{

	@Override
	public Map<String, Object> queryCustomer(CustomerQueryReq customerQueryReq) {

		 Criteria c = super.getCriteria();
		 c.add(Restrictions.isNotNull("custName"));
		 //客户编码
		 if(StringUtils.isNotBlank(customerQueryReq.getCustCode())){
			 c.add(Restrictions.like("custCode","%"+customerQueryReq.getCustCode()+"%"));
		 }
		 //客户名称
		 if(StringUtils.isNotBlank(customerQueryReq.getCustName())){
			 c.add(Restrictions.like("custName","%"+customerQueryReq.getCustName()+"%"));
		 }
		 //联系人
		 if(StringUtils.isNotBlank(customerQueryReq.getCustContcat())){
			 c.add(Restrictions.like("custContcat","%"+customerQueryReq.getCustContcat()+"%"));
		 }
		 //分公司Id
		 if(StringUtils.isNotBlank(customerQueryReq.getCorpId())){
			 c.add(Restrictions.eq("corpId",customerQueryReq.getCorpId()));
		 }
		 
		 c.addOrder(Order.desc("custId"));
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<Customer> customerList = this.findBySortCriteria(c, customerQueryReq);
		
		// 返回结果
		return ResultUtil.pageResult( filterCount, customerQueryReq.getDraw(),customerList);
	}

	/**
	 * 根据客户编号查询
	 */
	@Override
	public Customer queryByCustCode(String custId,String strPrex) {
		 Criteria c = super.getCriteria();
		 //客户ID
		 if(StringUtils.isNotBlank(custId)){
			 c.add(Restrictions.eq("custId",custId));
		 }
		 //客户编码
		 if(StringUtils.isNotBlank(strPrex)){
			 c.add(Restrictions.eq("custCode",strPrex));
		 }
		 List<Customer> customerList = this.findByCriteria(c);
		 if(customerList!=null && customerList.size()>0){
			 return customerList.get(0);
		 }
	
		return null;
	}

	/**
	 * 根据客户编号模糊查询,查看每一类地区中的最大编码
	 */
	@Override
	public Customer queryByCustCodePrex(String strPrex) {
		 String sql="select CUST_CODE as custCode from customer where CUST_CODE like'"+strPrex+"%' order by CAST(substring(CUST_CODE,6) AS DECIMAL) desc "; 
		 /*Criteria c = super.getCriteria();
		 //客户编码
		 if(StringUtils.isNotBlank(strPrex)){
			 c.add(Restrictions.like("custCode",strPrex+"%"));
		 }
		 c.add(Restrictions.sqlRestriction("order by CAST(substring(CUST_CODE,5) AS DECIMAL) desc"));
		 c.addOrder(Order.desc("custCode"));
		 List<Customer> customerList = this.findByCriteria(c);*/
		 List<Map<String,Object>> customerList = this.findListBySql(sql);
		 if(customerList!=null && customerList.size()>0){
			 Map<String, Object> obj = customerList.get(0);
			 Customer customer = new Customer();
			 customer.setCustCode(obj.get("custCode").toString());
			 return customer;
		 }
	
		return null;
	}

	@Override
	public Customer queryByCustCode(String custCode) {
		Criteria c = super.getCriteria();
		 //客户编码
		 if(StringUtils.isNotBlank(custCode)){
			 c.add(Restrictions.eq("custCode",custCode));
		 }
		 List<Customer> customerList = this.findByCriteria(c);
		 if(customerList!=null && customerList.size()>0){
			 return customerList.get(0);
		 }
	
		return null;
	}

	@Override
	public Customer queryByCustName(String custName,String custPhone,String custContcat,String corpId) {
		Criteria c = super.getCriteria();
		 //客户名称
		 if(StringUtils.isNotBlank(custName)){
			 c.add(Restrictions.eq("custName",custName));
		 }
		 //客户联系人
		 if(StringUtils.isNotBlank(custPhone)){
			 c.add(Restrictions.eq("custPhone",custPhone));
		 }
		 //客户联系人电话
		 if(StringUtils.isNotBlank(custContcat)){
			 c.add(Restrictions.eq("custContcat",custContcat));
		 }
		 //分公司ID
		 if(StringUtils.isNotBlank(corpId)){
			 c.add(Restrictions.eq("corpId",corpId));
		 }
		 List<Customer> customerList = this.findByCriteria(c);
		 if(customerList!=null && customerList.size()>0){
			 return customerList.get(0);
		 }
		return null;
	}

	@Override
	public Customer queryCustomer(Customer customer) {
		Criteria c = super.getCriteria();
		 c.add(Restrictions.isNotNull("custName"));
		 //客户编码
		 if(StringUtils.isNotBlank(customer.getCustCode())){
			 c.add(Restrictions.eq("custCode",customer.getCustCode()));
		 }
		 //客户名称
		 if(StringUtils.isNotBlank(customer.getCustName())){
			 c.add(Restrictions.eq("custName",customer.getCustName()));
		 }
		 //联系人
		 if(StringUtils.isNotBlank(customer.getCustContcat())){
			 c.add(Restrictions.eq("custContcat",customer.getCustContcat()));
		 }
		 //联系人电话
		 if(StringUtils.isNotBlank(customer.getCustPhone())){
			 c.add(Restrictions.eq("custPhone",customer.getCustPhone()));
		 }
		 //报装用户号
		 if(StringUtils.isNotBlank(customer.getCustNo())){
			 c.add(Restrictions.eq("custNo",customer.getCustNo()));
		 }
		//报装用户所属分公司
		 if(StringUtils.isNotBlank(customer.getCorpId())){
			 c.add(Restrictions.eq("corpId",customer.getCorpId()));
		 }
		 List<Customer> customerList = this.findByCriteria(c);
		 if(customerList!=null && customerList.size()>0){
			 return customerList.get(0);
		 }
		return null;
	}
	
}
