package cc.dfsoft.uexpress.biz.sys.auth.dao;

import cc.dfsoft.uexpress.biz.sys.auth.entity.RoleMenu;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 角色菜单处理DAO
 * @author 1919
 *
 */
public interface RoleMenuDao extends CommonDao<RoleMenu, String>{

	/**
	 * 新增角色菜单
	 * @param roleMenu
	 */
	public void insertRoleMenu(RoleMenu roleMenu);
	
	/**
	 * 查询角色菜单
	 * @param tenantId
	 * @param roleId
	 * @return
	 */
	public RoleMenu queryRoleMenu(String tenantId, String roleId);
	
	
	/**
	 * 修改角色菜单
	 * @param roleMenu
	 */
	public void updateRoleMenu(RoleMenu roleMenu);
}
