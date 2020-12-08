package cc.dfsoft.project.biz.base.complete.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SelfInspectionRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SELF_INSPECTION_RECORD")
public class SelfInspectionRecord implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3799348289548812776L;
	private String sirId;		//自检记录ID
	private String silId;		//自检单ID
	private String projId;		//工程ID
	private String sirType;		//自检类型
	private String ciId;		//自检项ID
	private String sirResult;	//自检结果
	private String sirNum;		//份数
	private String sirRemark;	//备注（检查意见）

	// Constructors

	/** default constructor */
	public SelfInspectionRecord() {
	}
	
	// Property accessors
	@Id
	@Column(name = "SIR_ID", unique = true)
	public String getSirId() {
		return this.sirId;
	}

	public void setSirId(String sirId) {
		this.sirId = sirId;
	}

	@Column(name = "SIL_ID")
	public String getSilId() {
		return this.silId;
	}

	public void setSilId(String silId) {
		this.silId = silId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "SIR_TYPE")
	public String getSirType() {
		return this.sirType;
	}

	public void setSirType(String sirType) {
		this.sirType = sirType;
	}

	@Column(name = "CI_ID")
	public String getCiId() {
		return this.ciId;
	}

	public void setCiId(String ciId) {
		this.ciId = ciId;
	}

	@Column(name = "SIR_RESULT")
	public String getSirResult() {
		return this.sirResult;
	}

	public void setSirResult(String sirResult) {
		this.sirResult = sirResult;
	}

	@Column(name = "SIR_NUM")
	public String getSirNum() {
		return this.sirNum;
	}

	public void setSirNum(String sirNum) {
		this.sirNum = sirNum;
	}

	@Column(name = "SIR_REMARK")
	public String getSirRemark() {
		return this.sirRemark;
	}

	public void setSirRemark(String sirRemark) {
		this.sirRemark = sirRemark;
	}

}