package cc.dfsoft.project.biz.base.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.dto.BankQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.dto.CustomerQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Customer;
import cc.dfsoft.project.biz.base.baseinfo.service.BankService;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.baseinfo.service.CustomerService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentReq;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 单位人员相关弹出选择屏
 * @author pengtt
 *
 */

@Controller
@RequestMapping(value="/popup")
public class UnitPopUpController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(UnitPopUpController.class);
	
	/**人员服务接口*/
	@Resource
	StaffService staffService;
	
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
	/**业务合作伙伴服务接口*/
	@Resource
	BusinessPartnersService businessPartnersService;
	
	/**客户（申报单位）服务接口*/
	@Resource
	CustomerService customerService;

	@Resource
	BankService bankService;

	/**
	 * 获取单位（弹窗页面）
	 * @author pengtt
	 * @createTime 2016-7-8
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/businessPartnersPop")
	public ModelAndView constructionUnitPop(HttpServletRequest request){
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("unitType",request.getParameter("unitType"));//单位类型
		modelview.setViewName("common/businessPartnersPop");
		return modelview;
	}
	
	/**
	 * 业务合作伙伴列表查询
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryBusinessPartners")
	@ResponseBody
	public Map<String, Object> queryExternalUnitUnit(HttpServletRequest request,BusinessPartnersReq businessPartnersReq,String unitType) {
		try {
			businessPartnersReq.setSortInfo(request);
			businessPartnersReq.setUnitType(unitType);
			LoginInfo login=SessionUtil.getLoginInfo();
			businessPartnersReq.setCorpId(login.getCorpId());
			Map<String, Object> map = businessPartnersService.queryBusinessPartners(businessPartnersReq);
		    return map;
		} catch (Exception e) {
			logger.error("业务合作伙伴列表查询失败！", e);
			return null;
		}
	}
	
	
	/**
	 * 获取人员（弹窗页面）
	 * @author pengtt
	 * @createTime 2016-7-8
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/staffPop")
	public ModelAndView staffPop(HttpServletRequest request,String post,String unitType,String deptId,String corpId){
		
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("post", post);			//职务
		modelview.addObject("unitType",unitType);	//单位类型
		modelview.addObject("deptId",deptId);		//部门ID
		modelview.addObject("corpId",corpId);		//所属分公司ID
		modelview.setViewName("common/staffPop");
		return modelview;
	}
	
	@RequestMapping(value = "/operStaffPop")  //工程流程查找人员专用
	public ModelAndView operStaffPop(HttpServletRequest request,String post,String unitType,String deptId,String corpId){
		
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("post", post);			//职务
		modelview.addObject("unitType",unitType);	//单位类型
		modelview.addObject("deptId",deptId);		//部门ID
		modelview.addObject("corpId",SessionUtil.getLoginInfo().getCorpId());		//所属分公司ID
		modelview.setViewName("common/operStaffPop");
		return modelview;
	}
	/**
	 * 人员选择列表查询
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryStaff")
	@ResponseBody
	public Map<String, Object> queryStaff(HttpServletRequest request,StaffQueryReq staffQueryReq) {
		try {
			staffQueryReq.setSortInfo(request);
			if("postDes".equals(staffQueryReq.getSortName())){
				staffQueryReq.setSortName("post");
			}
			Map<String, Object> map = staffService.queryStaffList(staffQueryReq);
		    return map;
		} catch (Exception e) {
			logger.error("人员选择列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 获取部门（弹窗页面）
	 * @author pengtt
	 * @createTime 2016-7-8
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/deptPop")
	public ModelAndView deptPop(String deptType){
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("deptType", deptType);
		modelview.setViewName("common/deptPop");
		return modelview;
	}
	
	
	/**
	 * 部门选择列表查询
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryDept")
	@ResponseBody
	public Map<String, Object> queryDept(HttpServletRequest request,DepartmentReq departmentReq) {
		try {
			departmentReq.setSortInfo(request);
			Map<String, Object> map = departmentService.queryDepartmentList(departmentReq);
		    return map;
		} catch (Exception e) {
			logger.error("部门选择列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 关联操作----申请单位增加页面
	 * @return
	 */
	@RequestMapping(value = "/custPop")
	public ModelAndView custPop(){
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("baseinfo/cust/addCustPop");
		return modelview;
	}
	
	/**
	 * 申请单位选择页面
	 * @author pengtt
	 * @return
	 */
	@RequestMapping(value = "/custListPop")
	public ModelAndView custListPop(){
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("common/customerPop");
		return modelview;
	}

	/**
	 * 开户行选择页面
	 * @author cui
	 * @return
	 */
	@RequestMapping(value = "/bankPop")
	public ModelAndView bankPop(){
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("common/bankPop");
		return modelview;
	}

	/**
	 * 开户行
	 * @param request
	 * @param bankQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryBank")
	@ResponseBody
	public Map<String, Object> queryBank(HttpServletRequest request,BankQueryReq bankQueryReq) {
		//获取当前用户信息
		LoginInfo loginInfo=SessionUtil.getLoginInfo();

		bankQueryReq.setCorpId(loginInfo.getCorpId());
		try {
			bankQueryReq.setSortInfo(request);
			Map<String, Object> map = bankService.queryBank(bankQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("开户行选择列表查询失败！", e);
			return null;
		}
	}

	/**
	 * 设计员选择页面
	 * @return
	 */
	@RequestMapping(value = "/designerListPop")
	public ModelAndView designerPop(){
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("common/designerPop");
		return modelview;
	}
	
	/**
	 * 申报单位
	 * @param request
	 * @param customerQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryCustomer")
	@ResponseBody
	public Map<String, Object> queryCustomer(HttpServletRequest request,CustomerQueryReq customerQueryReq) {
		try {
			customerQueryReq.setSortInfo(request);
			Map<String, Object> map = customerService.queryCustomer(customerQueryReq);
		    return map;
		} catch (Exception e) {
			logger.error("客户（申报单位）选择列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 客户（申报单位）新增
	 * @author pengtt
	 * @createTime 2016-07-22
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/saveCustomer")
	@ResponseBody
	public String saveCustomer(@RequestBody Customer customer) {
		try {
			return customerService.saveCust(customer);
		} catch (Exception e) {
			logger.error("客户保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 查询施工单位
	 * @author flw
	 * @createTime 2017-1-16
	 * @param  staffId
	 * @return  Map<String,Object>
	 */
	@RequestMapping(value = "/queryManagementOffice")
	@ResponseBody
	public Map<String,Object> queryManagementOffice(@RequestBody(required = true) String staffId){
		try {
			return departmentService.queryManagementOffice(staffId);
		} catch (Exception e) {
			logger.error("查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询项目经理id
	 * @author flw
	 * @createTime 2017-1-16
	 * @param
	 * @return  String
	 */
	@RequestMapping(value = "/queryCuLegalRepresentId")
	@ResponseBody
	public String queryCuLegalRepresentId(String staffName,String deptId){
		try {
			return departmentService.queryCuLegalRepresentId(staffName,deptId);
		} catch (Exception e) {
			logger.error("查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 人员选择列表查询
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryManageStaff")
	@ResponseBody
	public Map<String, Object> queryManageStaff(HttpServletRequest request,StaffQueryReq staffQueryReq) {
		try {
			staffQueryReq.setSortInfo(request);
			if("postDes".equals(staffQueryReq.getSortName())){
				staffQueryReq.setSortName("post");
			}
			Map<String, Object> map = staffService.queryManageStaffList(staffQueryReq);
		    return map;
		} catch (Exception e) {
			logger.error("人员选择列表查询失败！", e);
			return null;
		}
	}
	
	
	@RequestMapping(value = "/queryOperateStaff")
	@ResponseBody
	public Map<String, Object> queryOperateStaff(HttpServletRequest request,StaffQueryReq staffQueryReq) {
		try {
			staffQueryReq.setSortInfo(request);
			if("postDes".equals(staffQueryReq.getSortName())){
				staffQueryReq.setSortName("post");
			}
			Map<String, Object> map = staffService.queryOperateStaff(staffQueryReq);
		    return map;
		} catch (Exception e) {
			logger.error("人员选择列表查询失败！", e);
			return null;
		}
	}
	/**
	 * 获取人员（弹窗页面）
	 * @author pengtt
	 * @createTime 2016-7-8
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/manageStaffPop")
	public ModelAndView manageStaffPop(HttpServletRequest request,String post,String corpId){
		
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("post", post);			//职务
		modelview.addObject("corpId",corpId);		//分公司Id
		modelview.setViewName("common/manageStaffPop");
		return modelview;
	}
	
	/**
	 * 查询操作人--操作流程设置查询使用
	 * @param request
	 * @param staffQueryReq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operateStaffPop")
	public ModelAndView operateStaffPop(HttpServletRequest request,String post,String unitType,String deptId,String corpId){
		
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("post", post);			//职务
		modelview.addObject("unitType",unitType);	//单位类型
		modelview.addObject("deptId",deptId);		//部门ID
		modelview.addObject("corpId",corpId);		//所属分公司ID
		modelview.setViewName("common/operateStaffPop");
		return modelview;
	}
}
