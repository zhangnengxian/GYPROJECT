package cc.dfsoft.uexpress.biz.sys.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 租户菜单
 * @author 1919
 *
 */
@Entity
@Table(name = "menu_tenant")
public class MenuTenant implements java.io.Serializable{

	private static final long serialVersionUID = 5635205260151827662L;
	private String tenantId;
	private String menuIds;
	
	@Id
	@Column(name = "tenant_id")
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	@Column(name = "menu_ids")
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	
	
}
