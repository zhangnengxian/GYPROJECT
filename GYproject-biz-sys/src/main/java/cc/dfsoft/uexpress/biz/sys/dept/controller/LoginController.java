package cc.dfsoft.uexpress.biz.sys.dept.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fr.report.core.A.r;

import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.service.OperateLogService;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.controller.BaseController;
import cc.dfsoft.uexpress.common.enums.ResultCodeEnum;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * 登录控制器
 * @author 1919
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	/** 员工服务接口 */
	@Resource
	private StaffService staffService;

	/** 操作日志接口 */
	@Resource
	private OperateLogService operateLogService;

	/**
	 * 初始化登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/main")
	public String meterInfo(Model model) {
		return "/index";
	}

	/**
	 * 员工登录
	 * @param userId
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request, String loginAccount, String password,String device,String registrationID){
		//用户名不能为空
		if(StringUtil.isBlank(loginAccount)){
			return resultMap(ResultCodeEnum.FAIL.getValue(), "账号不能为空！");
		}
		if(StringUtil.isBlank(password)){
			return resultMap(ResultCodeEnum.FAIL.getValue(), "密码不能为空！");
		}

		try{
			String tenantId = Constants.TENANT_ID_VALUE;//先临时获取，后续需要从session中得到
			Map<String, Object> resultMap = staffService.staffLogin(tenantId, loginAccount, password,registrationID);
			if(resultMap.get(Constants.RESULT_CODE).equals(ResultCodeEnum.SUCCESS.getValue())){
				LoginInfo loginInfo = (LoginInfo) resultMap.get(Constants.RESULT);


				/*HttpSession session = request.getSession();
				Object obj = session.getAttribute(Constants.SESSION_LOGININFO);

				if(obj!=null){
					return resultMap(ResultCodeEnum.FAIL.getValue(), "该浏览器已登录一个账户！");
				}*/


				request.getSession().setAttribute(Constants.SESSION_LOGININFO, loginInfo);
				if(StringUtil.isNotBlank(request.getParameter("longitude"))){
					loginInfo.setLongitude(request.getParameter("longitude"));
				}
				if(StringUtil.isNotBlank(request.getParameter("latitude"))){
					loginInfo.setLatitude(request.getParameter("latitude"));
				}
				operateLogService.saveOperteLog(loginInfo,device);
			}
			return resultMap;
		} catch (Exception e) {
			logger.error("员工登录发生异常", e);
			e.printStackTrace();
			return resultMap(ResultCodeEnum.FAIL.getValue(), "系统异常");
		}
	}
	/**
	 * 员工登录
	 * @param userId
	 * @return
	 */
	@RequestMapping("/staffLogin")
	@ResponseBody
	public Map<String, Object> staffLogin(HttpServletRequest request, String loginAccount, String password){
		//用户名不能为空
		if(StringUtil.isBlank(loginAccount)){
			return resultMap(ResultCodeEnum.FAIL.getValue(), "账号不能为空！");
		}
		if(StringUtil.isBlank(password)){
			return resultMap(ResultCodeEnum.FAIL.getValue(), "密码不能为空！");
		}
		try{
			String tenantId = Constants.TENANT_ID_VALUE;//先临时获取，后续需要从session中得到
			Map<String, Object> resultMap = staffService.staffLogin(tenantId, loginAccount, password,null);
			if(resultMap.get(Constants.RESULT_CODE).equals(ResultCodeEnum.SUCCESS.getValue())){
				LoginInfo loginInfo =  (LoginInfo) resultMap.get(Constants.RESULT);
				request.getSession().setAttribute(Constants.SESSION_LOGININFO, loginInfo);
			}
			return resultMap;
		} catch (Exception e) {
			logger.error("员工登录发生异常", e);
			e.printStackTrace();
			return resultMap(ResultCodeEnum.FAIL.getValue(), "系统异常");
		}
	}
	/**
	 * 员工退出
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/staffLogout")
	@ResponseBody
	public String staffLogout(HttpServletRequest request){
		try{
			//staffService.clearCurStaffRegistrationId(SessionUtil.getLoginInfo().getStaffId(),SessionUtil.getLoginInfo().getRegistrationId());
			request.getSession().removeAttribute(Constants.SESSION_LOGININFO);
			return ResultCodeEnum.SUCCESS.getValue();
		}catch(Exception e){
			logger.error("员工退出发生异常。", e);
			e.printStackTrace();
			return ResultCodeEnum.FAIL.getValue();
		}
	}

	/**
	 * 员工退出
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout")
	@ResponseBody
	public String logout(HttpServletRequest request,String device){
		try{
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			//staffService.clearCurStaffRegistrationId(SessionUtil.getLoginInfo().getStaffId(),SessionUtil.getLoginInfo().getRegistrationId());
			request.getSession().removeAttribute(Constants.SESSION_LOGININFO);
			operateLogService.saveOperteLogOut(loginInfo,device);
			return ResultCodeEnum.SUCCESS.getValue();
		}catch(Exception e){
			logger.error("员工退出发生异常。", e);
			e.printStackTrace();
			return ResultCodeEnum.FAIL.getValue();
		}
	}




	/**
	 * 查询当前登录人是否发生变化
	 * @author fuliwei
	 * @createTime 2017年12月18日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryLoginCheck")
	@ResponseBody
	public Map<String,Object> queryManagementOffice(@RequestBody(required = true) String staffId){
		try {
			LoginInfo login=SessionUtil.getLoginInfo();
			Map map=new HashMap();
			map.put("data", login);
			return map;
		} catch (Exception e) {
			logger.error("查询失败！", e);
			e.printStackTrace();
			return null;
		}
	}



	/**
	 * 更新登录设备id
	 * @author fuliwei
	 * @date 2019/8/27
	 * @param
	 * @return
	*/
	@RequestMapping("/updateLoginReId")
	@ResponseBody
	public String updateLoginReId(@RequestBody(required = true)String registrationID){
		try{
			return staffService.updateStaffRegiId(registrationID);
		} catch (Exception e) {
			logger.error("更新登录设备id发生异常", e);
			e.printStackTrace();
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

}
