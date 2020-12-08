package cc.dfsoft.uexpress.biz.sys.auth.dao;

import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleDto;
import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleQueryReq;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色管理处理DAO
 * @author 1919
 *
 */
public interface RoleDao {

	/**
	 * 新增角色
	 * @param role
	 */
	public void insertRole(Role role);
	
	/**
	 * 查询角色
	 * @param tenantId
	 * @param roleId
	 * @return
	 */
	public Role queryRole(String tenantId, String roleId);
	
	/**
	 * 查询角色和菜单
	 * @param tenantId
	 * @param roleId
	 * @return
	 */
	public RoleDto queryRoleAndMenus(String tenantId, String roleId);
	
	/**
	 * 查询角色列表信息
	 * @param roleQueryReq
	 * @return
	 */
	public Map<String, Object> queryRoleList(RoleQueryReq roleQueryReq);
	
	/** 查询所有角色
	 * @return
	 */
	public Map<String, Object> queryAllRole(int draw);
	
	/**
	 * 修改角色
	 * @param role
	 */
	public void updateRole(Role role);
	
	/**
	 * 删除角色
	 * @param tenantId
	 * @param roleId
	 */
	public void deleteRole(String tenantId, String roleId);
	
	/**
	 * 根据角色编号查找
	 * @param roleCode
	 */
	public List<Role> findByRoleCode(String roleCode);

    Map<String, Object> queryRoleListByInCorpId(List<String> corpIds,String roleIds);
}
