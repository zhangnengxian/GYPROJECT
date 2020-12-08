package cc.dfsoft.uexpress.biz.sys.dept.dao;

import java.util.List;

import cc.dfsoft.uexpress.biz.sys.auth.entity.RoleMenu;
import cc.dfsoft.uexpress.biz.sys.dept.entity.RoleDept;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 角色-工程受理部门权限配置Dao接口
 * @author liaoyq
 * @createTime 2018年8月6日
 */
public interface RoleDeptDao extends CommonDao<RoleDept, String> {

	/**
	 * 根据角色ID查询-受理部门权限配置
	 * @author liaoyq
	 * @createTime 2018年8月6日
	 * @param roleId
	 * @return
	 */
	List<RoleDept> findDepts(String roleId);

	/**
	 * 插入角色-受理部门权限配置信息
	 * @author liaoyq
	 * @createTime 2018年8月7日
	 * @param roleDept
	 */
	void insertRoleDept(RoleDept roleDept);

	/**
	 * 修改角色-受理部门权限配置信息
	 * @author liaoyq
	 * @createTime 2018年8月7日
	 * @param roleDept
	 */
	void updateRoleDept(RoleDept roleDept);

}
