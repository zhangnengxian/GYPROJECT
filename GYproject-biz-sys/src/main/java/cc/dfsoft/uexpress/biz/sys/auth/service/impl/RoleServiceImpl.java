package cc.dfsoft.uexpress.biz.sys.auth.service.impl;

import cc.dfsoft.uexpress.biz.sys.auth.converter.RoleDtoConverter;
import cc.dfsoft.uexpress.biz.sys.auth.dao.RoleDao;
import cc.dfsoft.uexpress.biz.sys.auth.dao.RoleMenuDao;
import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleDto;
import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleQueryReq;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Role;
import cc.dfsoft.uexpress.biz.sys.auth.entity.RoleMenu;
import cc.dfsoft.uexpress.biz.sys.auth.service.RoleService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.RoleDeptDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.RoleDept;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 角色管理服务接口实现类
 * @author 1919
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class RoleServiceImpl implements RoleService{

	/** 角色处理DAO */
	@Resource
	private RoleDao roleDao;
	/** 角色菜单处理DAO */
	@Resource
	private RoleMenuDao roleMenuDao;
	
	@Resource
	private RoleDeptDao roleDeptDao;
	
	@Override
	public RoleDto queryRoleAndMenus(String tenantId, String roleId) {
		return roleDao.queryRoleAndMenus(tenantId, roleId);
	}
	@Override
	public RoleMenu queryRoleMenu(String tenantId, String roleId) {
		return roleMenuDao.queryRoleMenu(tenantId, roleId);
	}

	@Override
	public Map<String, Object> queryRoleListByInCorpId(List<String> corpIds,String roleIds) {
		return roleDao.queryRoleListByInCorpId(corpIds,roleIds);
	}


	@Override
	public Map<String, Object> queryRoleList(RoleQueryReq roleQueryReq) {
		return roleDao.queryRoleList(roleQueryReq);
	}
	
	@Override
	public Map<String, Object> queryAllRole(int draw) {
		return roleDao.queryAllRole(draw);
	}
	
	@Override
	public List<Role> findByRoleCode(String roleCode){
		return roleDao.findByRoleCode(roleCode);
	}
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String addOrUpdateRole(RoleDto roleDto, String menuIds, String deptIds) {
		//根据ID判断是新增还是修改
		roleDto.setTenantId(SessionUtil.getTenantId());
		Role role = RoleDtoConverter.convert(roleDto);

		if(StringUtil.isNotBlank(role.getRoleId())){
			roleDao.updateRole(role);

			RoleMenu roleMenu = roleMenuDao.get(role.getRoleId());
			if (roleMenu!=null) {//修改角色菜单
				roleMenu.setMenuIds(menuIds);
				roleMenuDao.updateRoleMenu(roleMenu);
			}else {//新增角色菜单
				roleMenuDao.insertRoleMenu(getRoleMenu(role, menuIds));
			}
			//修改角色-受理部门信息
			//roleDeptDao.updateRoleDept(getRoleDept(role, deptIds));
		}else{
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			if(loginInfo == null){
				return "false";
			}
			//角色号已经存在
			if(this.findByRoleCode(role.getRoleCode()).size()>0){
				return "exist";
			}
			role.setCreateStaffId(loginInfo.getStaffId());
			role.setRoleId(role.getRoleCode());
			role.setCreateTime(new Date());
			roleDao.insertRole(role);
			
			//新增角色菜单信息
			roleMenuDao.insertRoleMenu(getRoleMenu(role, menuIds));
			//roleDeptDao.insertRoleDept(getRoleDept(role, deptIds));
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
	/**
	 * 角色菜单信息
	 * @param role
	 * @param menuIds
	 * @return
	 */
	private RoleMenu getRoleMenu(Role role, String menuIds){
		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setRoleId(role.getRoleId());
		//roleMenu.setTenantId(role.getTenantId());
		roleMenu.setMenuIds(menuIds);
		return roleMenu;
	}
	
	/**
	 * 角色受理部门信息
	 * @param role
	 * @param menuIds
	 * @return
	 */
	private RoleDept getRoleDept(Role role, String deptIds){
		RoleDept roleDept = new RoleDept();
		roleDept.setRoleId(role.getRoleId());
		roleDept.setDeptIds(deptIds);
		return roleDept;
	}
	
	@Override
	public void deleteRole(String tenantId, String roleId) {
		roleDao.deleteRole(tenantId, roleId);

		RoleMenu roleMenu = roleMenuDao.get(roleId);
		if (roleMenu!=null){
			roleMenuDao.delete(roleMenu.getRoleId());
		}
		RoleDept roleDept = roleDeptDao.get(roleId);
		if (roleDept!=null){
			roleDeptDao.delete(roleDept.getRoleId());
		}

	}

}
