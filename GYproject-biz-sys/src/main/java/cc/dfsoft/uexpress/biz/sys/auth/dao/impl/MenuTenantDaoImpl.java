package cc.dfsoft.uexpress.biz.sys.auth.dao.impl;

import org.springframework.stereotype.Repository;
import cc.dfsoft.uexpress.biz.sys.auth.dao.MenuTenantDao;
import cc.dfsoft.uexpress.biz.sys.auth.entity.MenuTenant;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

/**
 * 租户菜单处理DAO实现
 * 
 * @author 1919
 *
 */
@Repository
public class MenuTenantDaoImpl extends NewBaseDAO<MenuTenant, String> implements MenuTenantDao {

	@Override
	public void insertMenuTenant(MenuTenant menuTenant) {
		this.save(menuTenant);
	}

	@Override
	public MenuTenant queryMenuTenant(String tenantId) {
		String hql = "from MenuTenant where tenantId=?";
		MenuTenant MenuTenant = this.findClassByHql(hql, new Object[]{tenantId});
		return MenuTenant;
	}

}
