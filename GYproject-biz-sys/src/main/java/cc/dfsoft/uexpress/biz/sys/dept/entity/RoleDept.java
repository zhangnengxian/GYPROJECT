package cc.dfsoft.uexpress.biz.sys.dept.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色-工程受理部门权限配置表
 * 主要用于工程过滤
 * @author liaoyq
 * @createTime 2018年8月6日
 */
@Entity
@Table(name = "ROLE_DEPT")
public class RoleDept implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7410362143557692070L;
	
	private String roleId;		//角色ID
	private String deptIds;		//部门ID
	
	public RoleDept() {
		super();
	}

	@Id
	@Column(name="ROLE_ID")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name="DEPT_IDS")
	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}
	
	

}
