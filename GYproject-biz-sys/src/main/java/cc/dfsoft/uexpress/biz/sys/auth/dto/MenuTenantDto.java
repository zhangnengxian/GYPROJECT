package cc.dfsoft.uexpress.biz.sys.auth.dto;

/**
 * 租户菜单信息
 * @author 1919
 *
 */
public class MenuTenantDto {

	/** 租户ID */
	private String tenantId;
	/** 菜单IDS */
	private String menuIds;
	
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	
	
}
