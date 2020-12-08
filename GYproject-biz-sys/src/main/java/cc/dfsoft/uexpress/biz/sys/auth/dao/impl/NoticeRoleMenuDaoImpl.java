package cc.dfsoft.uexpress.biz.sys.auth.dao.impl;

import org.springframework.stereotype.Repository;

import cc.dfsoft.uexpress.biz.sys.auth.dao.NoticeRoleMenuDao;
import cc.dfsoft.uexpress.biz.sys.auth.entity.NoticeRoleMenu;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
@Repository
public class NoticeRoleMenuDaoImpl extends NewBaseDAO<NoticeRoleMenu, String> implements NoticeRoleMenuDao {
	
	@Override
	public void insertRoleMenu(NoticeRoleMenu roleMenu) {
		this.save(roleMenu);
	}

	@Override
	public NoticeRoleMenu queryRoleMenu(String tenantId, String roleId) {
		//String hql = "from RoleMenu where tenantId=? and roleId=?";
		String hql = "from NoticeRoleMenu where nrId=?";
		NoticeRoleMenu roleMenu = this.findClassByHql(hql, new Object[] { roleId });
		return roleMenu;
	}

	@Override
	public void updateRoleMenu(NoticeRoleMenu roleMenu) {
		this.update(roleMenu);
	}

	@Override
	public void deleteRoleMenu(String roleId) {
		this.delete(roleId);
	}
}
