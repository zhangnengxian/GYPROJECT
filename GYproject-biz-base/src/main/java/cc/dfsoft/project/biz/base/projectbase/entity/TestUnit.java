package cc.dfsoft.project.biz.base.projectbase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TestUnit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TEST_UNIT", schema = "XJPROJECT")
public class TestUnit implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2162175570083435262L;
	private String tuId;       //主键ID
	private String tuName;	   //检测单位名称
	private String shortName;  //简称
	private String tuDirector; //负责人
	private String tuPhone;	   //联系电话
	private String tuMobile;   //手机
	
	/** default constructor */
	public TestUnit() {
	}

	// Property accessors
	@Id
	@Column(name = "TU_ID", unique = true)
	public String getTuId() {
		return this.tuId;
	}

	public void setTuId(String tuId) {
		this.tuId = tuId;
	}

	@Column(name = "TU_NAME")
	public String getTuName() {
		return this.tuName;
	}

	public void setTuName(String tuName) {
		this.tuName = tuName;
	}

	@Column(name = "SHORT_NAME")
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "TU_DIRECTOR")
	public String getTuDirector() {
		return this.tuDirector;
	}

	public void setTuDirector(String tuDirector) {
		this.tuDirector = tuDirector;
	}

	@Column(name = "TU_PHONE")
	public String getTuPhone() {
		return this.tuPhone;
	}

	public void setTuPhone(String tuPhone) {
		this.tuPhone = tuPhone;
	}

	@Column(name = "TU_MOBILE")
	public String getTuMobile() {
		return this.tuMobile;
	}

	public void setTuMobile(String tuMobile) {
		this.tuMobile = tuMobile;
	}

}