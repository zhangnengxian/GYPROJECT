package cc.dfsoft.uexpress.biz.sys.auth.service;

import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleDto;
import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleQueryReq;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Role;
import cc.dfsoft.uexpress.biz.sys.auth.entity.RoleMenu;

import java.util.List;
import java.util.Map;

/**
 * 角色管理服务接口
 * @author 1919
 *
 */
public interface RoleService {

	/**
	 * 查询角色和菜单信息
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
	
	/**
	 * 查询所有角色
	 * @return
	 */
	public Map<String, Object> queryAllRole(int draw);
	
	/**
	 * 新增或修改角色信息
	 * @param roleDto
	 * @param menuIds
	 * @param deptIds 
	 */
	public String addOrUpdateRole(RoleDto roleDto, String menuIds, String deptIds);
	
	/**
	 * 删除角色信息
	 * @param tenantId
	 * @param roleId
	 */
	public void deleteRole(String tenantId, String roleId);
	
	/**
	 * 根据角色编号查找
	 * @param roleCode
	 */
	public List<Role> findByRoleCode(String roleCode);
	/**
	 * 根据角色查找菜单
	 * @param roleCode
	 */
	public RoleMenu queryRoleMenu(String tenantId, String roleId) ;

    Map<String, Object> queryRoleListByInCorpId(List<String> corpIds,String roleIds);
}
