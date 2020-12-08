package cc.dfsoft.uexpress.biz.sys.auth.dto;

import java.util.Date;

/**
 * 角色信息
 * @author 1919
 *
 */
public class RoleDto {

	/** 菜单id */
	private String roleId;
	/** 角色编号 */
	private String roleCode;
	/** 角色名称 */
	private String roleName;
	/** 创建人 */
	private String createStaffId;
	/** 创建时间 */
	private Date createTime;
	/** 租户id */
	private String tenantId;
	/** 菜单ids */
	private String menuIds;
	
	private String nrId;  //通知角色id
	private String nrCode;//通知角色编码
	private String nrName;//通知角色名称
	
	private String corpId;//分公司id
	
	private String deptIds;//受理部门ID
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCreateStaffId() {
		return createStaffId;
	}
	public void setCreateStaffId(String createStaffId) {
		this.createStaffId = createStaffId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
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
	public String getNrId() {
		return nrId;
	}
	public void setNrId(String nrId) {
		this.nrId = nrId;
	}
	public String getNrCode() {
		return nrCode;
	}
	public void setNrCode(String nrCode) {
		this.nrCode = nrCode;
	}
	public String getNrName() {
		return nrName;
	}
	public void setNrName(String nrName) {
		this.nrName = nrName;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public String getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}
	
}
