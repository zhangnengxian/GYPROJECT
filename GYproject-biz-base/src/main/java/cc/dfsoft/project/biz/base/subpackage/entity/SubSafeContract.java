package cc.dfsoft.project.biz.base.subpackage.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SubSafeContract entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SUB_SAFE_CONTRACT")
public class SubSafeContract implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2820058793472251970L;
	// Fields

	private String ssId;					//分包安全协议ID   
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private String projName;				//工程名称
	private String projAddr;				//工程地点
	
	
	private String projCompDirector;		//甲方委托代理人
	private String projCompPm;				//甲方项目经理
	private String gasComLegalRepresent;	//甲方代表
	private String gasComPhone;				//甲方联系电话
	
	private String cuId;					//分包单位ID
	private String cuName;					//乙方名称
	private String cuLegalRepresent;		//法定代表
	private String cuDirector;				//乙方委托代表
	private String cuResponsiblePerson;		//乙方现场负责人
	private String cuPmPhone;				//乙方联系电话
	
	
	private String signAddr;				//签订地址
	private String safeManager;				//安全管理员
	private String ssAccount;				//注册账号
	private Date ssSignDate;				//签订日期
	// Constructors

	/** default constructor */
	public SubSafeContract() {
	}

	// Property accessors
	@Id
	@Column(name = "SS_ID", unique = true)
	public String getSsId() {
		return this.ssId;
	}

	public void setSsId(String ssId) {
		this.ssId = ssId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return this.projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return this.projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	
	@Column(name = "PROJ_COMP_DIRECTOR")
	public String getProjCompDirector() {
		return this.projCompDirector;
	}

	public void setProjCompDirector(String projCompDirector) {
		this.projCompDirector = projCompDirector;
	}

	@Column(name = "PROJ_COMP_PM")
	public String getProjCompPm() {
		return this.projCompPm;
	}

	public void setProjCompPm(String projCompPm) {
		this.projCompPm = projCompPm;
	}

	@Column(name = "GAS_COM_LEGAL_REPRESENT")
	public String getGasComLegalRepresent() {
		return this.gasComLegalRepresent;
	}

	public void setGasComLegalRepresent(String gasComLegalRepresent) {
		this.gasComLegalRepresent = gasComLegalRepresent;
	}

	@Column(name = "GAS_COM_PHONE")
	public String getGasComPhone() {
		return this.gasComPhone;
	}

	public void setGasComPhone(String gasComPhone) {
		this.gasComPhone = gasComPhone;
	}

	@Column(name = "CU_NAME")
	public String getCuName() {
		return this.cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}

	@Column(name = "CU_LEGAL_REPRESENT")
	public String getCuLegalRepresent() {
		return this.cuLegalRepresent;
	}

	public void setCuLegalRepresent(String cuLegalRepresent) {
		this.cuLegalRepresent = cuLegalRepresent;
	}

	@Column(name = "CU_DIRECTOR")
	public String getCuDirector() {
		return this.cuDirector;
	}

	public void setCuDirector(String cuDirector) {
		this.cuDirector = cuDirector;
	}

	@Column(name = "CU_PM_PHONE")
	public String getCuPmPhone() {
		return this.cuPmPhone;
	}

	public void setCuPmPhone(String cuPmPhone) {
		this.cuPmPhone = cuPmPhone;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SS_SIGN_DATE")
	public Date getSsSignDate() {
		return this.ssSignDate;
	}

	public void setSsSignDate(Date ssSignDate) {
		this.ssSignDate = ssSignDate;
	}
	@Column(name = "CU_ID")
	public String getCuId() {
		return cuId;
	}

	public void setCuId(String cuId) {
		this.cuId = cuId;
	}

	@Column(name = "CU_RESPONSIBLE_PERSON")
	public String getCuResponsiblePerson() {
		return cuResponsiblePerson;
	}

	public void setCuResponsiblePerson(String cuResponsiblePerson) {
		this.cuResponsiblePerson = cuResponsiblePerson;
	}

	@Column(name = "SIGN_ADDR")
	public String getSignAddr() {
		return signAddr;
	}

	public void setSignAddr(String signAddr) {
		this.signAddr = signAddr;
	}

	@Column(name = "SAFE_MANAGER")
	public String getSafeManager() {
		return safeManager;
	}

	public void setSafeManager(String safeManager) {
		this.safeManager = safeManager;
	}

	@Column(name = "SS_ACCOUNT")
	public String getSsAccount() {
		return ssAccount;
	}

	public void setSsAccount(String ssAccount) {
		this.ssAccount = ssAccount;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	
	
}