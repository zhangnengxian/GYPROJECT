package cc.dfsoft.project.biz.base.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATUS_AND_ROLE")
public class StatusAndRole implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2667586282493237084L;
	private String roleId;//权限id
	private String statusId;//状态id
	
	public StatusAndRole() {
	}
	
	@Id
	@Column(name = "ROLE_ID", unique = true)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	@Column(name = "STATUS_ID")
	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	
}
