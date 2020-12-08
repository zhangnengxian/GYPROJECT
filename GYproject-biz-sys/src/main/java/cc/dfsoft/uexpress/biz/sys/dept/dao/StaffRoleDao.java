package cc.dfsoft.uexpress.biz.sys.dept.dao;

import java.util.List;

import cc.dfsoft.uexpress.biz.sys.dept.entity.StaffRole;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 员工角色处理DAO
 * @author 1919
 *
 */
public interface StaffRoleDao extends CommonDao<StaffRole, String>{

	/**
	 * 新增员工角色
	 * @param staffRole
	 */
	public void insertStaffRole(StaffRole staffRole);
	
	/**
	 * 修改员工角色
	 * @param staffRole
	 */
	public void updateStaffRole(StaffRole staffRole);

	/**
	 * 查询员工角色
	 * @param tenantId
	 * @param staffId
	 * @return
	 */
	public StaffRole queryStaffRoleInfo(String tenantId, String staffId);
	
	/**
	 * 查找施工、报验查看权限
	 * @author flw
	 * @createTime 2017-1-12
	 * @param staffId
	 * @return String
	 */
	public String queryCheckRole(String staffId);

	/**
	 * 删除员工所配置角色
	 * @param staffId
	 * @return 
	 */
	public boolean deleteStaffRole(String staffId);
	
	
}
