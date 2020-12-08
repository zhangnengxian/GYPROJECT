package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Progress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PROGRESS")
public class Progress implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1250907191350035383L;
	private String progressId;		//进度记录ID
	private String projId;			//工程ID
	private String projName;		//工程名称
	private String projScaleDes;	//工程规模
	private String projNo;			//工程编号
	private String quId;			//工程量ID
	private String nuitProject;		//单位工程
	private String sqUnit;			//单位
	private Double allProgressNum;	//预计总工程量
	private Double heapProgressNum;	//累计完成量
	private Double thisProgressNum;	//本次完成量
	private String finishProgress;	//完成进度(%)
	private Date registerDate;		//登记时间
	private String registePerson;	//登记人

	// Constructors

	/** default constructor */
	public Progress() {
	}

	// Property accessors
	@Id
	@Column(name = "PROGRESS_ID", unique = true)
	public String getProgressId() {
		return this.progressId;
	}

	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
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

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return this.projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
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

	@Column(name = "ALL_PROGRESS_NUM")
	public Double getAllProgressNum() {
		return this.allProgressNum;
	}

	public void setAllProgressNum(Double allProgressNum) {
		this.allProgressNum = allProgressNum;
	}

	@Column(name = "HEAP_PROGRESS_NUM")
	public Double getHeapProgressNum() {
		return this.heapProgressNum;
	}

	public void setHeapProgressNum(Double heapProgressNum) {
		this.heapProgressNum = heapProgressNum;
	}

	@Column(name = "THIS_PROGRESS_NUM")
	public Double getThisProgressNum() {
		return this.thisProgressNum;
	}

	public void setThisProgressNum(Double thisProgressNum) {
		this.thisProgressNum = thisProgressNum;
	}

	@Column(name = "FINISH_PROGRESS")
	public String getFinishProgress() {
		return this.finishProgress;
	}

	public void setFinishProgress(String finishProgress) {
		this.finishProgress = finishProgress;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REGISTER_DATE")
	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Column(name = "REGISTE_PERSON")
	public String getRegistePerson() {
		return this.registePerson;
	}

	public void setRegistePerson(String registePerson) {
		this.registePerson = registePerson;
	}
	
	@Column(name = "SQ_UNIT")
	public String getSqUnit() {
		return sqUnit;
	}

	public void setSqUnit(String sqUnit) {
		this.sqUnit = sqUnit;
	}

}