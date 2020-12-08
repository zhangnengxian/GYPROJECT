package cc.dfsoft.uexpress.biz.sys.auth.dao.impl;

import org.springframework.stereotype.Repository;

import cc.dfsoft.uexpress.biz.sys.auth.dao.RoleMenuDao;
import cc.dfsoft.uexpress.biz.sys.auth.entity.RoleMenu;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

/**
 * 角色菜单处理DAO实现
 * @author 1919
 *
 */
@Repository
public class RoleMenuDaoImpl extends NewBaseDAO<RoleMenu, String> implements RoleMenuDao{

	@Override
	public void insertRoleMenu(RoleMenu roleMenu) {
		this.save(roleMenu);
	}

	@Override
	public RoleMenu queryRoleMenu(String tenantId, String roleId) {
		//String hql = "from RoleMenu where tenantId=? and roleId=?";
		String hql = "from RoleMenu where roleId=?";
		RoleMenu roleMenu = this.findClassByHql(hql, new Object[] { roleId });
		return roleMenu;
	}

	@Override
	public void updateRoleMenu(RoleMenu roleMenu) {
		this.update(roleMenu);
	}

	

}
