package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.CustomerDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.CustomerQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Customer;
import cc.dfsoft.project.biz.base.baseinfo.service.CustomerService;
import cc.dfsoft.project.biz.base.project.enums.AreaEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * @author pengtt
 * @createTime 2016-07-22
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CustomerServiceImpl implements CustomerService {
	
	/**客户dao*/
	@Resource
	CustomerDao customerDao;
	@Resource
	DepartmentDao departmentDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveCust(Customer customer) {
		
		LoginInfo login=SessionUtil.getLoginInfo();
		customer.setCorpId(login.getCorpId());
		
		
		if(StringUtil.isBlank(customer.getCustId())){
			customer.setCustId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
			//默认贵阳-生成单位编号
			customer = getCustCode(customer);
		}
		//客户判断唯一：客户名称+分公司ID
		//同步到NC，会先根据客户名称去判断是否已存在客户，不存在则新增
		if(StringUtil.isNotBlank(customer.getCustName())){
			Customer cm = customerDao.queryByCustName(customer.getCustName(),null,null,customer.getCorpId());
			if(cm!=null){
				return "exist";
			}
		}
		//查看客户编号是否已存在
		if(StringUtil.isNotBlank(customer.getCustCode())){
			Customer cm = customerDao.queryByCustCode(null,customer.getCustCode());
			if(cm!=null){
				return "exist";
			}
		}
		
		customerDao.save(customer);
		return customer.getCustId();
	}

	@Override
	public Map<String, Object> queryCustomer(CustomerQueryReq customerQueryReq) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		customerQueryReq.setCorpId(loginInfo.getCorpId());   //传入当前登录人的公司ID，根据公司ID查询客户名称
		return customerDao.queryCustomer(customerQueryReq);
	}

	@Override
	public Customer queryCustomerById(String id) {
		return customerDao.get(id);
	}
	
	/**
	 * 生成客户编码
	 * 地区编码默认为贵阳，否则为分公司编码
	 * @param coprId
	 * @return
	 */
	@Override
	public Customer getCustCode(Customer customer){
		String areaCode="";
		//默认值
		if(StringUtil.isBlank(customer.getCorpId())){
			customer.setCorpId("1101");
		}
		Department department = departmentDao.get(customer.getCorpId());
		if(department==null){
			areaCode = AreaEnum.GUIYANG.getValue();
		}else{
			//分公司编码-作为地区编码
			areaCode = department.getDeptOutCode();
		}
		//客户编码前缀
		String strPrex = Constants.CUSTOMER_CODE +areaCode;
		String custCode = strPrex;
		Customer customer1 = customerDao.queryByCustCodePrex(strPrex);
		if(customer1!=null && StringUtil.isNotBlank(customer1.getCustCode())){
			BigDecimal bigDecimal = new BigDecimal(customer1.getCustCode().substring(strPrex.length(), customer1.getCustCode().length()));
			custCode = custCode + bigDecimal.add(new BigDecimal(1));
		}else{
			custCode = custCode+"1";
		}
		//客户编码
		customer.setCustCode(custCode);
		//客户地区编码
		customer.setAreaCode(areaCode);
		return customer;
	}

	@Override
	public Customer queryOrInsertCustomer(Customer customer) {
		Customer old = customerDao.queryCustomer(customer);
		if(old!=null){
			return old;
		}
		if(StringUtil.isBlank(customer.getCustId())){
			customer.setCustId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
			//默认贵阳-生成单位编号
			customer = getCustCode(customer);
		}
		customerDao.save(customer);
		return customer;
	}
}
