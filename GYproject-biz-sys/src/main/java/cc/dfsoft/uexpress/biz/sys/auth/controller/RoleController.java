package cc.dfsoft.uexpress.biz.sys.auth.controller;

import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleDto;
import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleQueryReq;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Role;
import cc.dfsoft.uexpress.biz.sys.auth.service.RoleService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 员工管理控制器
 * @author 1919
 *
 */
@Controller
@RequestMapping("/auth")
public class RoleController {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(RoleController.class);

	/** 角色服务接口 */
	@Resource
	private RoleService roleService;

	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;

	@Resource
	StaffService staffService;
	/**
	 * 初始化角色管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/main")
	public String meterInfo(Model model) {
		return "/auth/roleInfo";
	}

	/**
	 * 初始化搜索角色信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/roleSearchView")
	public String roleSearchView(Model model) {
		return "/auth/roleSearch";
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
			List<Role>  roleList= (List<Role>) resultMap.get("data");

			if (roleList!=null && roleList.size()>0){
				return resultMap;
			}else {
				roleQueryReq.setCorpId("0");
				return roleService.queryRoleList(roleQueryReq);
			}
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
	public Map<String, Object> queryAllRole(RoleQueryReq req){

		boolean isExist=staffService.isExistStaff(req.getStaffId());
		if (isExist){
			return roleService.queryRoleList(req);
		}else {//第三方人员
			List<String> corpIds=new ArrayList<>();
			corpIds.add("0");
			if (StringUtil.isNotBlank(req.getBelongCorpId())) {
				String[] split = req.getBelongCorpId().split(",");
				for (String s:split){
					corpIds.add(s);
				}
			}
			return roleService.queryRoleListByInCorpId(corpIds, req.getRoleIds());
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
		model.addAttribute("groupCompany",DeptTypeEnum.GROUP_COMPANY.getInitVal());
		model.addAttribute("corp", departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		return "/auth/roleRight";
	}

	/**
	 * 修改角色信息
	 * @param staffDto
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdateRole")
	@ResponseBody
	public String updateRole(@RequestBody(required = true) RoleDto roleDto, String menuIds, String deptIds){
		try{
			isCommon(roleDto);//是否为通用菜单
			return roleService.addOrUpdateRole(roleDto, menuIds, deptIds);
		}catch(Exception e){
			logger.error("新增或修改角色信息发生异常。", e);
			return "false";
		}
	}


	public void isCommon(RoleDto roleDto){
		String roleId = roleDto.getRoleId();
		if (StringUtil.isNotBlank(roleId)){
			String substring = roleId.substring(0, 2);
			if (substring.equals("0-")){
				roleDto.setCorpId("0");
			}
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
