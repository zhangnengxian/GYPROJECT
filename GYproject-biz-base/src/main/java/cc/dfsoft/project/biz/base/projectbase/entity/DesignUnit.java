package cc.dfsoft.project.biz.base.projectbase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 设计院 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DESIGN_UNIT", schema = "XJPROJECT")
public class DesignUnit implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6032038078795155928L;
	// Fields

	
	private String duId;		//主键ID
	private String duName;		//设计院名称
	private String shortName;	//简称
	private String duDirector;	//负责人
	private String duPhone;	    //负责人电话
	private String duMobile;	//手机
    
	/** default constructor */
	public DesignUnit() {
	}

	// Property accessors
	@Id
	@Column(name = "DU_ID", unique = true)
	public String getDuId() {
		return this.duId;
	}

	public void setDuId(String duId) {
		this.duId = duId;
	}

	@Column(name = "DU_NAME")
	public String getDuName() {
		return this.duName;
	}

	public void setDuName(String duName) {
		this.duName = duName;
	}

	@Column(name = "SHORT_NAME")
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "DU_DIRECTOR")
	public String getDuDirector() {
		return this.duDirector;
	}

	public void setDuDirector(String duDirector) {
		this.duDirector = duDirector;
	}

	@Column(name = "DU_PHONE")
	public String getDuPhone() {
		return this.duPhone;
	}

	public void setDuPhone(String duPhone) {
		this.duPhone = duPhone;
	}

	@Column(name = "DU_MOBILE")
	public String getDuMobile() {
		return this.duMobile;
	}

	public void setDuMobile(String duMobile) {
		this.duMobile = duMobile;
	}

}