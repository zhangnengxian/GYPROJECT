package cc.dfsoft.project.biz.base.design.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Depart entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DEPART", schema = "XJPROJECT")
public class Depart implements java.io.Serializable {

	// Fields

	private String deptId;
	private String deptNo;
	private String deptName;
	private String unitType;
	private String deptDirector;
	private String deptPhone;
	private String deptIsunit;
	private String deptIsmaintain;
	private String deptIsgas;
	private String deptIsproject;
	private String superiorId;
	private String orgId;

	// Constructors

	/** default constructor */
	public Depart() {
	}

	
	// Property accessors
	@Id
	@Column(name = "DEPT_ID", unique = true, nullable = false)
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "DEPT_NO")
	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	@Column(name = "DEPT_NAME")
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "UNIT_TYPE")
	public String getUnitType() {
		return this.unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	@Column(name = "DEPT_DIRECTOR")
	public String getDeptDirector() {
		return this.deptDirector;
	}

	public void setDeptDirector(String deptDirector) {
		this.deptDirector = deptDirector;
	}

	@Column(name = "DEPT_PHONE")
	public String getDeptPhone() {
		return this.deptPhone;
	}

	public void setDeptPhone(String deptPhone) {
		this.deptPhone = deptPhone;
	}

	@Column(name = "DEPT_ISUNIT")
	public String getDeptIsunit() {
		return this.deptIsunit;
	}

	public void setDeptIsunit(String deptIsunit) {
		this.deptIsunit = deptIsunit;
	}

	@Column(name = "DEPT_ISMAINTAIN")
	public String getDeptIsmaintain() {
		return this.deptIsmaintain;
	}

	public void setDeptIsmaintain(String deptIsmaintain) {
		this.deptIsmaintain = deptIsmaintain;
	}

	@Column(name = "DEPT_ISGAS")
	public String getDeptIsgas() {
		return this.deptIsgas;
	}

	public void setDeptIsgas(String deptIsgas) {
		this.deptIsgas = deptIsgas;
	}

	@Column(name = "DEPT_ISPROJECT")
	public String getDeptIsproject() {
		return this.deptIsproject;
	}

	public void setDeptIsproject(String deptIsproject) {
		this.deptIsproject = deptIsproject;
	}

	@Column(name = "SUPERIOR_ID")
	public String getSuperiorId() {
		return this.superiorId;
	}

	public void setSuperiorId(String superiorId) {
		this.superiorId = superiorId;
	}

	@Column(name = "ORG_ID")
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}