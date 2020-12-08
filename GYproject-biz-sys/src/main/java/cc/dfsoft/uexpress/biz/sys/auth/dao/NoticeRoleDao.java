package cc.dfsoft.uexpress.biz.sys.auth.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleDto;
import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleQueryReq;
import cc.dfsoft.uexpress.biz.sys.auth.entity.NoticeRole;

public interface NoticeRoleDao {
	
	/**
	 * 新增角色
	 * @param role
	 */
	public void insertRole(NoticeRole role);
	
	/**
	 * 查询角色
	 * @param tenantId
	 * @param roleId
	 * @return
	 */
	public NoticeRole queryRole(String tenantId, String roleId);
	
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
	public void updateRole(NoticeRole role);
	
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
	public List<NoticeRole> findByRoleCode(String roleCode);
}
