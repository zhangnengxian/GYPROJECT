package cc.dfsoft.uexpress.biz.sys.dept.dto;

/**
 * 部门信息
 * @author 1919
 *
 */
public class DepartmentDto {

	/** 部门id */
	private String deptId;
	/** 部门内部编号 */
	private String deptInnerCode;
	/** 部门外部编号 */
	private String deptOutCode;
	/** 部门名称 */
	private String deptName;
	/** 所在地点 */
	private String location;
	/** 单位类型 */
	private String deptType;
	/** 单位类型名称 */
	private String deptTypeName;
	/** 负责人 */
	private String principal;
	/** 联系电话	 */
	private String phone;
	/** 传真号 */
	private String fax;
	/** 租户id */
	private String tenantId;
	/** 父节点部门id */
	private String parentDeptId;
	/** 父节点部门类型 */
	private String parentDeptType;
	
	
	private String businessType;//业务类型
	private String projContructType;//工程建设类型
	private String deptDivide;		//部门划分
	private String orgId;			//组织id

	private String isAcceptDept;	//是否网络平台受理
	
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptInnerCode() {
		return deptInnerCode;
	}
	public void setDeptInnerCode(String deptInnerCode) {
		this.deptInnerCode = deptInnerCode;
	}
	public String getDeptOutCode() {
		return deptOutCode;
	}
	public void setDeptOutCode(String deptOutCode) {
		this.deptOutCode = deptOutCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDeptType() {
		return deptType;
	}
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
	public String getDeptTypeName() {
		return deptTypeName;
	}
	public void setDeptTypeName(String deptTypeName) {
		this.deptTypeName = deptTypeName;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getParentDeptId() {
		return parentDeptId;
	}
	public void setParentDeptId(String parentDeptId) {
		this.parentDeptId = parentDeptId;
	}
	public String getParentDeptType() {
		return parentDeptType;
	}
	public void setParentDeptType(String parentDeptType) {
		this.parentDeptType = parentDeptType;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getProjContructType() {
		return projContructType;
	}
	public void setProjContructType(String projContructType) {
		this.projContructType = projContructType;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getDeptDivide() {
		return deptDivide;
	}
	public void setDeptDivide(String deptDivide) {
		this.deptDivide = deptDivide;
	}
	public String getIsAcceptDept() {
		return isAcceptDept;
	}
	public void setIsAcceptDept(String isAcceptDept) {
		this.isAcceptDept = isAcceptDept;
	}
	
}
