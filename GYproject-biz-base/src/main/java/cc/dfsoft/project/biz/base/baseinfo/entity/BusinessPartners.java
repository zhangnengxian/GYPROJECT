package cc.dfsoft.project.biz.base.baseinfo.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * ExternalUnit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BUSINESS_PARTNERS")
public class BusinessPartners implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6028053891486564373L;
	// Fields

	private String unitId;               //主键
	private String unitName;             //单位名称
	private String unitType;             //单位类型
	private String shortName;            //简称
	private String unitDirector;         //负责人
	private String unitPhone;            //电话
	private String unitMobile;           //手机
	private Date unitFoundDate;          //成立日期
	private String unitQualification;    //单位资质
	
	private String unitManager;          //项目经理
	private String qualificationName;    //资格证名称
	private String qualificationNo;      //资格证编号
	
	private String unitCode;			//对照编码
	
	private String corpId;				//分公司id
	private String corpName;			//分公司名称

	// Constructors

	@Column(name = "UNIT_MANAGER")
	public String getUnitManager() {
		return unitManager;
	}
	public void setUnitManager(String unitManager) {
		this.unitManager = unitManager;
	}

	@Column(name = "QUALIFICATION_NAME")
	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}

	@Column(name = "QUALIFICATION_NO")
	public String getQualificationNo() {
		return qualificationNo;
	}

	public void setQualificationNo(String qualificationNo) {
		this.qualificationNo = qualificationNo;
	}

	/** default constructor */
	public BusinessPartners() {
	}

	// Property accessors
	@Id
	@Column(name = "UNIT_ID", unique = true)
	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	@Column(name = "UNIT_NAME")
	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Column(name = "UNIT_TYPE")
	public String getUnitType() {
		return unitType;

	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	@Column(name = "SHORT_NAME")
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "UNIT_DIRECTOR")
	public String getUnitDirector() {
		return this.unitDirector;
	}

	public void setUnitDirector(String unitDirector) {
		this.unitDirector = unitDirector;
	}

	@Column(name = "UNIT_PHONE")
	public String getUnitPhone() {
		return this.unitPhone;
	}

	public void setUnitPhone(String unitPhone) {
		this.unitPhone = unitPhone;
	}

	@Column(name = "UNIT_MOBILE")
	public String getUnitMobile() {
		return this.unitMobile;
	}

	public void setUnitMobile(String unitMobile) {
		this.unitMobile = unitMobile;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UNIT_FOUND_DATE")
	public Date getUnitFoundDate() {
		return this.unitFoundDate;
	}

	public void setUnitFoundDate(Date unitFoundDate) {
		this.unitFoundDate = unitFoundDate;
	}

	@Column(name = "UNIT_QUALIFICATION")
	public String getUnitQualification() {
		return this.unitQualification;
	}

	public void setUnitQualification(String unitQualification) {
		this.unitQualification = unitQualification;
	}

	@Column(name="UNIT_CODE")
	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	
	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	@Column(name="CORP_NAME")
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	

}