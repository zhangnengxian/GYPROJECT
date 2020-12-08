package cc.dfsoft.uexpress.biz.sys.dept.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;

/**
 * 部门信息
 * @author 1919
 *
 */
@Entity
@Table(name = "department")
public class Department implements java.io.Serializable {

	private static final long serialVersionUID = 837665215715192348L;
	
	private String deptId;
	private String deptInnerCode;
	private String deptOutCode;
	private String deptName;
	private String deptTypeName;
	private String location;
	private String deptType;
	private String principal;
	private String phone;
	private String fax;
	private String deptPath;
	
	private String businessType;//业务类型
	/*private String proContructType;//工程建设类型
*/	
	private String orgId;		//组织id
	private String tenantId;	//租户id
	
	private String deptDivide;	//部门划分

	private String isAcceptDept;	//网络平台默认受理部门
	@Id
	@Column(name = "dept_id")
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "dept_inner_code")
	public String getDeptInnerCode() {
		return deptInnerCode;
	}

	public void setDeptInnerCode(String deptInnerCode) {
		this.deptInnerCode = deptInnerCode;
	}

	@Column(name = "dept_out_code")
	public String getDeptOutCode() {
		return deptOutCode;
	}

	public void setDeptOutCode(String deptOutCode) {
		this.deptOutCode = deptOutCode;
	}
	
	@Column(name = "dept_name")
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "location")
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "dept_type")
	public String getDeptType() {
		return this.deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	@Column(name = "principal")
	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "fax")
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Transient
	public String getDeptTypeDes() {
		if(StringUtils.isNotBlank(this.deptType)){
			return DeptTypeEnum.valueof(this.deptType).getMessage();
		}
		return "";
	}
	
	@Transient
	public String getDeptTypeName() {
		return deptTypeName;
	}

	public void setDeptTypeName(String deptTypeName) {
		this.deptTypeName = deptTypeName;
	}
	
	@Column(name = "business_type")
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	/*@Column(name="PRO_CONTRUCT_TYPE")
	public String getProContructType() {
		return proContructType;
	}

	public void setProContructType(String proContructType) {
		this.proContructType = proContructType;
	}*/
	
	@Column(name="ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name="TENANT_ID")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	@Column(name="DEPT_DIVIDE")
	public String getDeptDivide() {
		return deptDivide;
	}

	public void setDeptDivide(String deptDivide) {
		this.deptDivide = deptDivide;
	}

	@Column(name="DEPT_PATH")
	public String getDeptPath() {
		return deptPath;
	}

	public void setDeptPath(String deptPath) {
		this.deptPath = deptPath;
	}

	@Column(name="IS_ACCEPT_DEPT")
	public String getIsAcceptDept() {
		return isAcceptDept;
	}

	public void setIsAcceptDept(String isAcceptDept) {
		this.isAcceptDept = isAcceptDept;
	}
	
}