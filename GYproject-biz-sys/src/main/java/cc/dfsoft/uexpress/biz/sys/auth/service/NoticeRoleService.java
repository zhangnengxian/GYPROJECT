package cc.dfsoft.uexpress.biz.sys.auth.service;

import java.util.List;
import java.util.Map;

import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleDto;
import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleQueryReq;
import cc.dfsoft.uexpress.biz.sys.auth.entity.NoticeRole;
import cc.dfsoft.uexpress.biz.sys.auth.entity.NoticeRoleMenu;

public interface NoticeRoleService {
	
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
	 */
	public String addOrUpdateRole(RoleDto roleDto, String menuIds);
	
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
	public List<NoticeRole> findByRoleCode(String roleCode);
	/**
	 * 根据角色查找菜单
	 * @param roleCode
	 */
	public NoticeRoleMenu queryRoleMenu(String tenantId, String roleId) ;
}
