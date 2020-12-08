package cc.dfsoft.project.biz.base.common.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Version entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VERSION")
public class Version implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1692048426882374384L;
	// Fields

	private String veId;		//版本Id
	private String veNo;		//版本号
	private Date veStartTime;	//版本开始应用时间
	private Date veEndTime;		//版本结束应用时间
	private Date greatTime;		//创建时间
	private String greatPerson;	//创建人
	private String veType;		//版本类型
	private String lastId;		//上一个版本的id
	// Constructors

	/** default constructor */
	public Version() {
	}

	// Property accessors
	@Id
	@Column(name = "VE_ID", unique = true)
	public String getVeId() {
		return this.veId;
	}

	public void setVeId(String veId) {
		this.veId = veId;
	}

	@Column(name = "VE_NO")
	public String getVeNo() {
		return this.veNo;
	}

	public void setVeNo(String veNo) {
		this.veNo = veNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VE_START_TIME")
	public Date getVeStartTime() {
		return this.veStartTime;
	}

	public void setVeStartTime(Date veStartTime) {
		this.veStartTime = veStartTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VE_END_TIME")
	public Date getVeEndTime() {
		return this.veEndTime;
	}

	public void setVeEndTime(Date veEndTime) {
		this.veEndTime = veEndTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GREAT_TIME")
	public Date getGreatTime() {
		return this.greatTime;
	}

	public void setGreatTime(Date greatTime) {
		this.greatTime = greatTime;
	}

	@Column(name = "GREAT_PERSON")
	public String getGreatPerson() {
		return this.greatPerson;
	}

	public void setGreatPerson(String greatPerson) {
		this.greatPerson = greatPerson;
	}

	@Column(name = "VE_TYPE")
	public String getVeType() {
		return veType;
	}

	public void setVeType(String veType) {
		this.veType = veType;
	}
	@Transient
	public String getLastId() {
		return lastId;
	}

	public void setLastId(String lastId) {
		this.lastId = lastId;
	}

}