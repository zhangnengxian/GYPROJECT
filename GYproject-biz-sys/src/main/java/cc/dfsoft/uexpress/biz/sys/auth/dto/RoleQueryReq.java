package cc.dfsoft.uexpress.biz.sys.auth.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 角色查询请求参数
 * @author 1919
 *
 */
public class RoleQueryReq extends PageSortReq{
	
	private String roleCode;

	private String roleName;
	private String nrCode;//角色编码
	private String nrName;//角色名称

	private String staffId;//人员id
	private String corpId;//分公司id
	private String belongCorpId;
	private String roleIds;

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
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

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getBelongCorpId() {
		return belongCorpId;
	}

	public void setBelongCorpId(String belongCorpId) {
		this.belongCorpId = belongCorpId;
	}
}
