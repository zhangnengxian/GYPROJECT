package cc.dfsoft.uexpress.biz.sys.dept.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 员工通知角色
 * @author fuliwei
 *
 */
@Entity
@Table(name = "STAFF_NOTICE_ROLE")
public class StaffNoticeRole implements java.io.Serializable {
	
	private static final long serialVersionUID = -5155387846398669688L;
	
	private String staffId;//员工id
	private String roleIds;//员工角色	

	@Id
	@Column(name = "STAFF_ID")
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	@Column(name = "ROLE_IDS")
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
}
