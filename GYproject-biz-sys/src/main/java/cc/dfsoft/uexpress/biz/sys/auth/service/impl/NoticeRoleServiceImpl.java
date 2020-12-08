package cc.dfsoft.uexpress.biz.sys.auth.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.uexpress.biz.sys.auth.converter.NoticeRoleDtoConverter;
import cc.dfsoft.uexpress.biz.sys.auth.dao.NoticeRoleDao;
import cc.dfsoft.uexpress.biz.sys.auth.dao.NoticeRoleMenuDao;
import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleDto;
import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleQueryReq;
import cc.dfsoft.uexpress.biz.sys.auth.entity.NoticeRole;
import cc.dfsoft.uexpress.biz.sys.auth.entity.NoticeRoleMenu;
import cc.dfsoft.uexpress.biz.sys.auth.service.NoticeRoleService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class NoticeRoleServiceImpl implements NoticeRoleService{
	/** 角色处理DAO */
	@Resource
	private NoticeRoleDao roleDao;
	/** 角色菜单处理DAO */
	@Resource
	private NoticeRoleMenuDao roleMenuDao;

	@Override
	public RoleDto queryRoleAndMenus(String tenantId, String roleId) {
		return roleDao.queryRoleAndMenus(tenantId, roleId);
	}
	@Override
	public  NoticeRoleMenu queryRoleMenu(String tenantId, String roleId) {
		return roleMenuDao.queryRoleMenu(tenantId, roleId);
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
	public List<NoticeRole> findByRoleCode(String roleCode){
		return roleDao.findByRoleCode(roleCode);
	}
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String addOrUpdateRole(RoleDto roleDto, String menuIds) {
		//根据ID判断是新增还是修改
		roleDto.setTenantId(SessionUtil.getTenantId());
		NoticeRole role = NoticeRoleDtoConverter.convert(roleDto);
		
		if(StringUtil.isNotBlank(role.getNrId())){
			roleDao.updateRole(role);
			
			//修改角色菜单信息
			roleMenuDao.updateRoleMenu(getRoleMenu(role, menuIds));
		}else{
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			if(loginInfo == null){
				return "false";
			}
			//角色号已经存在
			if(this.findByRoleCode(role.getNrCode()).size()>0){
				return "exist";
			}
			role.setCreateStaffId(loginInfo.getStaffId());
			role.setNrId(role.getNrCode());
			role.setCreateTime(new Date());
			roleDao.insertRole(role);
			
			//新增角色菜单信息
			roleMenuDao.insertRoleMenu(getRoleMenu(role, menuIds));
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
	/**
	 * 角色菜单信息
	 * @param role
	 * @param menuIds
	 * @return
	 */
	private NoticeRoleMenu getRoleMenu(NoticeRole role, String menuIds){
		NoticeRoleMenu roleMenu = new NoticeRoleMenu();
		roleMenu.setNrId(role.getNrId());
		//roleMenu.setTenantId(role.getTenantId());
		roleMenu.setNrMenuIds(menuIds);
		return roleMenu;
	}
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRole(String tenantId, String roleId) {
		roleDao.deleteRole(tenantId, roleId);
		roleMenuDao.deleteRoleMenu(roleId);
	}
}
