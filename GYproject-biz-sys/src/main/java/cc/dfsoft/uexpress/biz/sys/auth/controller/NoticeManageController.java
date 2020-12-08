package cc.dfsoft.uexpress.biz.sys.auth.controller;

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

import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleDto;
import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleQueryReq;
import cc.dfsoft.uexpress.biz.sys.auth.service.NoticeRoleService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;

@Controller
@RequestMapping("/noticeManage")
public class NoticeManageController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(NoticeManageController.class);
	
	/** 角色服务接口 */
	@Resource
	private NoticeRoleService roleService;
	
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	/**
	 * 初始化角色管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/main")
	public String meterInfo(Model model) {
		return "/notice/roleInfo";
	}
	
	/**
	 * 初始化搜索角色信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/roleSearchView")
	public String roleSearchView(Model model) {
		return "/notice/roleSearch";
	}
	
	/**
	 * 查询角色列表
	 * @return
	 */
	@RequestMapping(value = "/queryRoleList")
	@ResponseBody
	public Map<String, Object> queryRoleList(HttpServletRequest request, RoleQueryReq roleQueryReq){
		try{
			//设置排序信息
			roleQueryReq.setSortInfo(request);
			LoginInfo loginInfo=SessionUtil.getLoginInfo();
			roleQueryReq.setCorpId(loginInfo.getCorpId());
			Map<String, Object> resultMap = roleService.queryRoleList(roleQueryReq);
			return resultMap;
		}catch(Exception e){
			logger.error("查询角色列表发生异常。", e);
			return null;
		}
	}
	
	/**
	 * 查询所有角色信息
	 * @return
	 */
	@RequestMapping(value = "/queryAllRole")
	@ResponseBody
	public Map<String, Object> queryAllRole(int draw){
		try{
			Map<String, Object> resultMap = roleService.queryAllRole(draw);
			return resultMap;
		}catch(Exception e){
			logger.error("查询所有角色信息发生异常。", e);
			return null;
		}
	}
	
	
	/**
	 * 查询角色信息
	 * @return
	 */
	@RequestMapping(value = "/queryRole")
	@ResponseBody
	public RoleDto queryRole(String id){
		try{
			RoleDto roleDto = roleService.queryRoleAndMenus(SessionUtil.getTenantId(), id);
			return roleDto;
		}catch(Exception e){
			logger.error("查询角色信息发生异常。", e);
			return null;
		}
	}
	
	/**
	 * 业务操作页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewPage")
	public String viewPage(Model model) {
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		model.addAttribute("corpId",loginInfo.getCorpId());
		model.addAttribute("corp", departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		return "/notice/roleRight";
	}
	
	/**
	 * 修改角色信息
	 * @param staffDto
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdateRole")
	@ResponseBody
	public String updateRole(@RequestBody(required = true) RoleDto roleDto, String menuIds){
		try{
			/*boolean flag = roleService.addOrUpdateRole(roleDto, menuIds);
			return String.valueOf(flag);*/
			return roleService.addOrUpdateRole(roleDto, menuIds);
		}catch(Exception e){
			logger.error("新增或修改角色信息发生异常。", e);
			return "false";
		}
	}
	
	/**
	 * 删除角色信息
	 * @param staffDto
	 * @return
	 */
	@RequestMapping(value = "/deleteRole")
	@ResponseBody
	public String deleteRole(String id){
		try{
			roleService.deleteRole(SessionUtil.getTenantId(), id);
			return "true";
		}catch(Exception e){
			logger.error("删除角色信息发生异常。roleId="+id, e);
			return "false";
		}
	}
}
