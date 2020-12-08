package cc.dfsoft.uexpress.biz.sys.dept.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 员工角色信息
 * @author 1919
 *
 */
@Entity
@Table(name = "staff_role")
public class StaffRole implements java.io.Serializable{

	private static final long serialVersionUID = -6245027967044601043L;
	
	private String staffId;
	private String roleIds;	
	//private String tenantId;
	
	@Id
	@Column(name = "staff_id")
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	@Column(name = "role_ids")
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	
	
	/*@Column(name = "tenant_id")
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}*/
	
}
