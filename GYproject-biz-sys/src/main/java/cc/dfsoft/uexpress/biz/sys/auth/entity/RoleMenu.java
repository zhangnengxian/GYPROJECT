package cc.dfsoft.uexpress.biz.sys.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色菜单
 * @author 1919
 *
 */
@Entity
@Table(name = "role_menu")
public class RoleMenu implements java.io.Serializable{

	private static final long serialVersionUID = 2077546946550094526L;
	
	private String roleId;
	private String menuIds;
	//private String tenantId;
	
	@Id
	@Column(name = "role_id")
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	@Column(name = "menu_ids")
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	
	/*@Column(name = "tenant_id")
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}*/
	
}
