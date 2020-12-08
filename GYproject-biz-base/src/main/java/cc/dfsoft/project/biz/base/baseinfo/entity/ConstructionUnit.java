package cc.dfsoft.project.biz.base.baseinfo.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 分包单位. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONSTRUCTION_UNIT")
public class ConstructionUnit implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5812619529327095140L;
	
	private String cuId;       		//主键ID
	private String cuName;     		//分包单位名称
	private String shortName;		//简称
	private String cuDirector;		//分包单位负责人
	private String cuPhone;		//负责人联系电话
	private String cuMobile;         //手机
	private Date cuFoundDate;		//成立日期
	private BigDecimal cuTotalNum;	//总人数
	private String cuQualification; //资质
	
	/** default constructor */
	public ConstructionUnit() {
	}
	// Property accessors
	@Id
	@Column(name = "CU_ID", unique = true)
	public String getCuId() {
		return this.cuId;
	}

	public void setCuId(String cuId) {
		this.cuId = cuId;
	}

	@Column(name = "CU_NAME")
	public String getCuName() {
		return this.cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}

	@Column(name = "SHORT_NAME")
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "CU_DIRECTOR")
	public String getCuDirector() {
		return this.cuDirector;
	}

	public void setCuDirector(String cuDirector) {
		this.cuDirector = cuDirector;
	}

	@Column(name = "CU_PHONE")
	public String getCuPhone() {
		return this.cuPhone;
	}

	public void setCuPhone(String cuPhone) {
		this.cuPhone = cuPhone;
	}

	@Column(name = "CU_MOBILE")
	public String getCuMobile() {
		return this.cuMobile;
	}

	public void setCuMobile(String cuMobile) {
		this.cuMobile = cuMobile;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CU_FOUND_DATE")
	public Date getCuFoundDate() {
		return this.cuFoundDate;
	}

	public void setCuFoundDate(Date cuFoundDate) {
		this.cuFoundDate = cuFoundDate;
	}

	@Column(name = "CU_TOTAL_NUM")
	public BigDecimal getCuTotalNum() {
		return this.cuTotalNum;
	}

	public void setCuTotalNum(BigDecimal cuTotalNum) {
		this.cuTotalNum = cuTotalNum;
	}

	@Column(name = "CU_QUALIFICATION")
	public String getCuQualification() {
		return this.cuQualification;
	}

	public void setCuQualification(String cuQualification) {
		this.cuQualification = cuQualification;
	}

}