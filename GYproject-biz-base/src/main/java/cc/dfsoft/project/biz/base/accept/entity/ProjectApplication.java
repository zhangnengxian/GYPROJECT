package cc.dfsoft.project.biz.base.accept.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ProjectApplication entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PROJECT_APPLICATION")
public class ProjectApplication implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2211255492967706619L;
	private String paId;			//主键ID
	private String paNo;			//受理单号
	private String projId;			//工程ID
	private String projNo;			//工程编号
	private String projName;		//工程名称
	private String paCsr;			//申请联系人
	private String paPhone;			//联系方式
	private String projAddr;		//工程地点
	private String projStage;		//主体工程建设阶段
	private String acceptView;		//受理单位意见
	private String chargePerson;	//负责人签字
	private String upAuditOpinion;	//上级主管部门审核意见
	private String upChargePerson;	//负责人签字
	private Date paDate;			//签字日期
	private String paName;			//申请人名称

	// Constructors

	/** default constructor */
	public ProjectApplication() {
	}


	// Property accessors
	@Id
	@Column(name = "PA_ID")
	public String getPaId() {
		return this.paId;
	}

	public void setPaId(String paId) {
		this.paId = paId;
	}

	@Column(name = "PA_NO")
	public String getPaNo() {
		return this.paNo;
	}

	public void setPaNo(String paNo) {
		this.paNo = paNo;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return this.projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return this.projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Column(name = "PA_CSR")
	public String getPaCsr() {
		return this.paCsr;
	}

	public void setPaCsr(String paCsr) {
		this.paCsr = paCsr;
	}

	@Column(name = "PA_PHONE")
	public String getPaPhone() {
		return this.paPhone;
	}

	public void setPaPhone(String paPhone) {
		this.paPhone = paPhone;
	}

	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return this.projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	@Column(name = "PROJ_STAGE")
	public String getProjStage() {
		return this.projStage;
	}

	public void setProjStage(String projStage) {
		this.projStage = projStage;
	}

	@Column(name = "ACCEPT_VIEW")
	public String getAcceptView() {
		return this.acceptView;
	}

	public void setAcceptView(String acceptView) {
		this.acceptView = acceptView;
	}

	@Column(name = "CHARGE_PERSON")
	public String getChargePerson() {
		return this.chargePerson;
	}

	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}

	@Column(name = "UP_AUDIT_OPINION")
	public String getUpAuditOpinion() {
		return this.upAuditOpinion;
	}

	public void setUpAuditOpinion(String upAuditOpinion) {
		this.upAuditOpinion = upAuditOpinion;
	}

	@Column(name = "UP_CHARGE_PERSON")
	public String getUpChargePerson() {
		return this.upChargePerson;
	}

	public void setUpChargePerson(String upChargePerson) {
		this.upChargePerson = upChargePerson;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PA_DATE")
	public Date getPaDate() {
		return this.paDate;
	}

	public void setPaDate(Date paDate) {
		this.paDate = paDate;
	}

	@Column(name = "PA_NAME")
	public String getPaName() {
		return this.paName;
	}

	public void setPaName(String paName) {
		this.paName = paName;
	}

}