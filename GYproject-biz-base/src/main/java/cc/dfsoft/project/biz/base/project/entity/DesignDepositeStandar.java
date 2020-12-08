package cc.dfsoft.project.biz.base.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DesignDepositeStandar entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DESIGN_DEPOSITE_STANDAR")
public class DesignDepositeStandar implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4265947476191922955L;
	private String id;			//标准ID
	private String projSubtype;	//工程二类ID
	private String des;			//标准描述
	private Double deposite;	//设计定金

	// Constructors

	/** default constructor */
	public DesignDepositeStandar() {
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

	@Column(name = "PROJ_SUBTYPE")
	public String getProjSubtype() {
		return this.projSubtype;
	}

	public void setProjSubtype(String projSubtype) {
		this.projSubtype = projSubtype;
	}

	@Column(name = "DES")
	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Column(name = "DEPOSITE")
	public Double getDeposite() {
		return this.deposite;
	}

	public void setDeposite(Double deposite) {
		this.deposite = deposite;
	}

}