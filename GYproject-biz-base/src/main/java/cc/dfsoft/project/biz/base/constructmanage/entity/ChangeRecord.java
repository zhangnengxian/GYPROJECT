package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ChangeRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CHANGE_RECORD")
public class ChangeRecord implements Serializable {
	
	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 2809357711401677542L;
	private String chrId;			//变更记录ID
	private String changeOrderId;	//变更单ID
	private String chrType;			//类型
	private Date changeDate;		//变更日期
	private String projId;			//工程ID
	private String projNo;			//工程编号
	private String projName;		//工程名称
	private String projScaleDes;	//工程规模
	private String quId;			//工程量ID
	private String nuitProject;		//单位工程名称
	private BigDecimal chrNum;		//变更量
	private String progressNote;	//备注

	// Constructors

	/** default constructor */
	public ChangeRecord() {
	}

	// Property accessors
	@Id
	@Column(name = "CHR_ID", unique = true)
	public String getChrId() {
		return this.chrId;
	}

	public void setChrId(String chrId) {
		this.chrId = chrId;
	}

	@Column(name = "CHANGE_ORDER_ID")
	public String getChangeOrderId() {
		return this.changeOrderId;
	}

	public void setChangeOrderId(String changeOrderId) {
		this.changeOrderId = changeOrderId;
	}

	@Column(name = "CHR_TYPE")
	public String getChrType() {
		return this.chrType;
	}

	public void setChrType(String chrType) {
		this.chrType = chrType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CHANGE_DATE")
	public Date getChangeDate() {
		return this.changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return this.projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return this.projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Column(name = "PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return this.projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}

	@Column(name = "QU_ID")
	public String getQuId() {
		return this.quId;
	}

	public void setQuId(String quId) {
		this.quId = quId;
	}

	@Column(name = "NUIT_PROJECT")
	public String getNuitProject() {
		return this.nuitProject;
	}

	public void setNuitProject(String nuitProject) {
		this.nuitProject = nuitProject;
	}

	@Column(name = "CHR_NUM")
	public BigDecimal getChrNum() {
		return this.chrNum;
	}

	public void setChrNum(BigDecimal chrNum) {
		this.chrNum = chrNum;
	}

	@Column(name = "PROGRESS_NOTE")
	public String getProgressNote() {
		return this.progressNote;
	}

	public void setProgressNote(String progressNote) {
		this.progressNote = progressNote;
	}

}