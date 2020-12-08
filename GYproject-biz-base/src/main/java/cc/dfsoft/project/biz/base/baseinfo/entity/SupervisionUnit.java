package cc.dfsoft.project.biz.base.baseinfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 监理单位 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SUPERVISION_UNIT")
public class SupervisionUnit implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 575490110092336611L;
	// Fields

	private String suId;       //主键ID
	private String suName;	   //监理单位名称
	private String shortName;  //简称
	private String suDirector; //负责人
	private String suPhone;    //负责人联系电话
	private String suMobile;   //手机
	
	/** default constructor */
	public SupervisionUnit() {
	}
    
	// Property accessors
	@Id
	@Column(name = "SU_ID", unique = true)
	public String getSuId() {
		return this.suId;
	}

	public void setSuId(String suId) {
		this.suId = suId;
	}

	@Column(name = "SU_NAME")
	public String getSuName() {
		return this.suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	@Column(name = "SHORT_NAME")
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "SU_DIRECTOR")
	public String getSuDirector() {
		return this.suDirector;
	}

	public void setSuDirector(String suDirector) {
		this.suDirector = suDirector;
	}

	@Column(name = "SU_PHONE")
	public String getSuPhone() {
		return this.suPhone;
	}

	public void setSuPhone(String suPhone) {
		this.suPhone = suPhone;
	}

	@Column(name = "SU_MOBILE")
	public String getSuMobile() {
		return this.suMobile;
	}

	public void setSuMobile(String suMobile) {
		this.suMobile = suMobile;
	}
}