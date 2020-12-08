package cc.dfsoft.project.biz.base.design.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EMPLOYEE", schema = "XJPROJECT")
public class Employee implements java.io.Serializable {

	// Fields

	private String csrId;
	private String csrNo;
	private String csrName;
	private String csrOfficialPhone;
	private String csrMobile;
	private String csrPhone;
	private String csrUnitType;
	private String csrDeptId;
	private String csrJobTitle;
	private String csrStatus;
	private String csrAccount;
	private String csrPassword;
	private Date csrLoginErrDate;
	private Integer csrLoginErrcount;
	private String csrsdflag;
	private String orgId;
	private String unitType;

	// Constructors

	/** default constructor */
	public Employee() {
	}

	
	// Property accessors
	@Id
	@Column(name = "CSR_ID", unique = true, nullable = false)
	public String getCsrId() {
		return this.csrId;
	}

	public void setCsrId(String csrId) {
		this.csrId = csrId;
	}

	@Column(name = "CSR_NO")
	public String getCsrNo() {
		return this.csrNo;
	}

	public void setCsrNo(String csrNo) {
		this.csrNo = csrNo;
	}

	@Column(name = "CSR_NAME")
	public String getCsrName() {
		return this.csrName;
	}

	public void setCsrName(String csrName) {
		this.csrName = csrName;
	}

	@Column(name = "CSR_OFFICIAL_PHONE")
	public String getCsrOfficialPhone() {
		return this.csrOfficialPhone;
	}

	public void setCsrOfficialPhone(String csrOfficialPhone) {
		this.csrOfficialPhone = csrOfficialPhone;
	}

	@Column(name = "CSR_MOBILE")
	public String getCsrMobile() {
		return this.csrMobile;
	}

	public void setCsrMobile(String csrMobile) {
		this.csrMobile = csrMobile;
	}

	@Column(name = "CSR_PHONE")
	public String getCsrPhone() {
		return this.csrPhone;
	}

	public void setCsrPhone(String csrPhone) {
		this.csrPhone = csrPhone;
	}

	@Column(name = "CSR_UNIT_TYPE")
	public String getCsrUnitType() {
		return this.csrUnitType;
	}

	public void setCsrUnitType(String csrUnitType) {
		this.csrUnitType = csrUnitType;
	}

	@Column(name = "CSR_DEPT_ID")
	public String getCsrDeptId() {
		return this.csrDeptId;
	}

	public void setCsrDeptId(String csrDeptId) {
		this.csrDeptId = csrDeptId;
	}

	@Column(name = "CSR_JOB_TITLE")
	public String getCsrJobTitle() {
		return this.csrJobTitle;
	}

	public void setCsrJobTitle(String csrJobTitle) {
		this.csrJobTitle = csrJobTitle;
	}

	@Column(name = "CSR_STATUS")
	public String getCsrStatus() {
		return this.csrStatus;
	}

	public void setCsrStatus(String csrStatus) {
		this.csrStatus = csrStatus;
	}

	@Column(name = "CSR_ACCOUNT")
	public String getCsrAccount() {
		return this.csrAccount;
	}

	public void setCsrAccount(String csrAccount) {
		this.csrAccount = csrAccount;
	}

	@Column(name = "CSR_PASSWORD")
	public String getCsrPassword() {
		return this.csrPassword;
	}

	public void setCsrPassword(String csrPassword) {
		this.csrPassword = csrPassword;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CSR_LOGIN_ERR_DATE")
	public Date getCsrLoginErrDate() {
		return this.csrLoginErrDate;
	}

	public void setCsrLoginErrDate(Date csrLoginErrDate) {
		this.csrLoginErrDate = csrLoginErrDate;
	}

	@Column(name = "CSR_LOGIN_ERRCOUNT", precision = 5, scale = 0)
	public Integer getCsrLoginErrcount() {
		return this.csrLoginErrcount;
	}

	public void setCsrLoginErrcount(Integer csrLoginErrcount) {
		this.csrLoginErrcount = csrLoginErrcount;
	}

	@Column(name = "CSRSDFLAG")
	public String getCsrsdflag() {
		return this.csrsdflag;
	}

	public void setCsrsdflag(String csrsdflag) {
		this.csrsdflag = csrsdflag;
	}

	@Column(name = "ORG_ID")
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "UNIT_TYPE")
	public String getUnitType() {
		return this.unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

}