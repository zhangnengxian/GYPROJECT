package cc.dfsoft.uexpress.biz.sys.dept.service;

import java.util.List;

import cc.dfsoft.uexpress.biz.sys.dept.entity.RoleDept;

/**
 * 角色-工程受理部门权限配置业务层接口
 * @author liaoyq
 * @createTime 2018年8月6日
 */
public interface RoleDeptService {

	/**
	 * 根据角色ID获取授权立项部门
	 * @author liaoyq
	 * @createTime 2018年8月6日
	 * @param roleId
	 * @return
	 */
	List<RoleDept> findDepts(String roleId);

}
