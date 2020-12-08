package cc.dfsoft.uexpress.biz.sys.auth.dao;

import cc.dfsoft.uexpress.biz.sys.auth.entity.NoticeRoleMenu;

public interface NoticeRoleMenuDao {
	
	/**
	 * 新增角色菜单
	 * @param roleMenu
	 */
	public void insertRoleMenu(NoticeRoleMenu roleMenu);
	
	/**
	 * 查询角色菜单
	 * @param tenantId
	 * @param roleId
	 * @return
	 */
	public NoticeRoleMenu queryRoleMenu(String tenantId, String roleId);
	
	
	/**
	 * 修改角色菜单
	 * @param roleMenu
	 */
	public void updateRoleMenu(NoticeRoleMenu roleMenu);

	/**
	 * 删除角色菜单记录
	 * @param roleId
	 */
	public void deleteRoleMenu(String roleId);
}
