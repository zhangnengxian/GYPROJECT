package cc.dfsoft.uexpress.biz.sys.auth.dao;

import cc.dfsoft.uexpress.biz.sys.auth.entity.MenuTenant;

/**
 * 租户菜单处理DAO
 * @author 1919
 *
 */
public interface MenuTenantDao {

	/**
	 * 新增租户菜单
	 * @param menuTenant
	 */
	public void insertMenuTenant(MenuTenant menuTenant);
	
	/**
	 * 查询租户菜单
	 * @return
	 */
	public MenuTenant queryMenuTenant(String tenantId);
}
