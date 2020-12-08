package cc.dfsoft.project.biz.base.baseinfo.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.dto.CustomerQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Customer;
import cc.dfsoft.project.biz.base.baseinfo.service.CustomerService;
import cc.dfsoft.project.biz.base.project.enums.AreaEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.ExceptionUtil;

/**
 * 
 * 描述:客户信息controller
 * @author liaoyq
 * @createTime 2017年12月5日
 */
@Controller
@RequestMapping(value="/customer")
public class CustomerController {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(CustomerController.class);
	
	@Resource
	CustomerService customerService;

	
	/**
	 * 打开主页面
	 * @author liaoyq
	 *  
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("baseinfo/cust/customer");
		return modelView;
	}
	/**
	 * 打开查询页面
	 * @author liaoyq
	 *  
	 */
	@RequestMapping(value="/customerSearchPopPage")
	public ModelAndView customerSearchPopPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("baseinfo/cust/customerSearchPopPage");
		return modelView;
	}
	
	/**
	 * 查询客户列表
	 * @return
	 */
	@RequestMapping(value="/queryCustomers")
	@ResponseBody
	public Map<String, Object> queryCustomers(HttpServletRequest request,CustomerQueryReq customerQueryReq){
		customerQueryReq.setSortInfo(request);
		
		if(StringUtils.isBlank(customerQueryReq.getCorpId())){
			LoginInfo loginInfo=SessionUtil.getLoginInfo();
			customerQueryReq.setCorpId(loginInfo.getCorpId());
		}
		
		Map<String, Object> map = customerService.queryCustomer(customerQueryReq);
		return map;
	}
	
	/**
	 * 查询客户详述
	 * @return
	 */
	@RequestMapping(value="/viewById")
	@ResponseBody
	public Customer viewById(String id){
		Customer customer = customerService.queryCustomerById(id);
		return customer;
	}
	
	/**
	 * 查询客户详述
	 * @return
	 */
	@RequestMapping(value="/viewCustomerPage")
	public ModelAndView viewCustomerPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("areaCodeEnum",AreaEnum.values());
		modelView.setViewName("baseinfo/cust/customerRight");
		return modelView;
	}
	/**
	 * 保存客户信息
	 * @return
	 */
	@RequestMapping(value="saveCustomers")
	public String saveCustomers(@RequestBody(required = true) Customer customer){
		try {
			return customerService.saveCust(customer);
		} catch (Exception e) {
			logger.error("保存客户信息失败", ExceptionUtil.getMessage(e));
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
}
