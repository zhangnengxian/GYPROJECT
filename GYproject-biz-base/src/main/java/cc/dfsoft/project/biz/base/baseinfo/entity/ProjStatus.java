package cc.dfsoft.project.biz.base.baseinfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProjStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PROJ_STATUS")
public class ProjStatus implements java.io.Serializable {

	// Fields

	private String id;
	private String des;

	// Constructors

	/** default constructor */
	public ProjStatus() {
	}

	/** minimal constructor */
	public ProjStatus(String id) {
		this.id = id;
	}

	/** full constructor */
	public ProjStatus(String id, String des) {
		this.id = id;
		this.des = des;
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

	@Column(name = "DES", length = 30)
	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

}