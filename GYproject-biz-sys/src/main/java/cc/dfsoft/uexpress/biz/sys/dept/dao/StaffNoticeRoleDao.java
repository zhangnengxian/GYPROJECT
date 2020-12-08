package cc.dfsoft.uexpress.biz.sys.dept.dao;

import cc.dfsoft.uexpress.biz.sys.dept.entity.StaffNoticeRole;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 员工通知角色
 * @author fuliwei
 *
 */
public interface StaffNoticeRoleDao extends CommonDao<StaffNoticeRole,String>{
	
	/**
	 * 查询员工通知角色
	 * @param tenantId
	 * @param staffId
	 * @return
	 */
	public StaffNoticeRole queryStaffRoleInfo(String tenantId, String staffId);
}
