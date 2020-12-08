package cc.dfsoft.project.biz.base.baseinfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BackReason entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BACK_REASON")
public class BackReason implements java.io.Serializable {

	// Fields

	private String id;
	private String des;

	// Constructors

	/** default constructor */
	public BackReason() {
	}

	

	// Property accessors
	@Id
	@Column(name = "ID")
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

}