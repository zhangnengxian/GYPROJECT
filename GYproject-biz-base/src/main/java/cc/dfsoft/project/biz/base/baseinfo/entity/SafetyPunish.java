package cc.dfsoft.project.biz.base.baseinfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SafetyPunish entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SAFETY_PUNISH")
public class SafetyPunish implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4644195940590161906L;
	private String id;               //id
	private String des;              //安全质量细则名称
	private Double deduction;        //分值
	private String remark;           //备注
	private String type;
	private String corpId;			//分公司id
	// Constructors

	/** default constructor */
	public SafetyPunish() {
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "DES")
	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Column(name = "DEDUCTION")
	public Double getDeduction() {
		return this.deduction;
	}

	public void setDeduction(Double deduction) {
		this.deduction = deduction;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
}