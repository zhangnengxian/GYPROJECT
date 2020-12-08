package cc.dfsoft.uexpress.biz.sys.dept.dao.impl;

import org.springframework.stereotype.Repository;

import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffNoticeRoleDao;
import cc.dfsoft.uexpress.biz.sys.dept.entity.StaffNoticeRole;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
@Repository
public class StaffNoticeRoleDaoImpl extends NewBaseDAO<StaffNoticeRole,String> implements StaffNoticeRoleDao{
	
	/**
	 * 查询员工通知角色
	 * @param tenantId
	 * @param staffId
	 * @return
	 */
	@Override
	public StaffNoticeRole queryStaffRoleInfo(String tenantId, String staffId) {
		String hql = "from StaffNoticeRole where staffId=?";
		StaffNoticeRole staffNoticeRole = this.findClassByHql(hql, new Object[] { staffId });
		return staffNoticeRole;
	}

}
