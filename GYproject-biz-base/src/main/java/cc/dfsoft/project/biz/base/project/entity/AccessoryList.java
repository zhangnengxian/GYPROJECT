package cc.dfsoft.project.biz.base.project.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.project.enums.StepEnum;

/**
 * AccessoryList entity
 */
@Entity
@Table(name = "ACCESSORY_LIST")
public class AccessoryList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2451567398017200985L;
	
	// Fields
	
	private String alId;			//主键ID 
	private String projId;			//工程ID
	private String projLtypeId;		//工程大类
	private String projStypeId;		//工程二类
	private String projNo;			//工程编号
	private String alTypeId;		//文档类型ID
	private String stepId;			//步骤名称--因现在存的是步骤名称,不做更改
	private String step;			//后加的字段，存的是工程步骤id
	private String caiId;			//附件项
	private String alName;			//附件名称
	private String alPath;			//路径
	private String alOperateCsrId;	//操作人ID
	private String alOperateCsr;	//操作人名称
	private Date alOperateTime;		//操作时间
	private String sourceType;      //附件来源 0 采集 1 拍照 2 变更 
	private String busRecordId;     //业务单id
	private String pictureStr;		//首页图片信息
	private String stepDes;			//步骤描述
	private String stepDesTemp;		//步骤描述
	
	private String encryption;//是否加密 1是 0 否
	// Constructors

	/** default constructor */
	public AccessoryList() {
	}


	// Property accessors
	@Id
	@Column(name = "AL_ID", unique = true)
	public String getAlId() {
		return this.alId;
	}

	public void setAlId(String alId) {
		this.alId = alId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_LTYPE_ID")
	public String getProjLtypeId() {
		return this.projLtypeId;
	}

	public void setProjLtypeId(String projLtypeId) {
		this.projLtypeId = projLtypeId;
	}

	@Column(name = "PROJ_STYPE_ID")
	public String getProjStypeId() {
		return this.projStypeId;
	}

	public void setProjStypeId(String projStypeId) {
		this.projStypeId = projStypeId;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return this.projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "AL_TYPE_ID")
	public String getAlTypeId() {
		return this.alTypeId;
	}

	public void setAlTypeId(String alTypeId) {
		this.alTypeId = alTypeId;
	}

	@Column(name = "STEP_ID")
	public String getStepId() {
		return this.stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	@Column(name = "CAI_ID")
	public String getCaiId() {
		return this.caiId;
	}

	public void setCaiId(String caiId) {
		this.caiId = caiId;
	}

	@Column(name = "AL_NAME")
	public String getAlName() {
		return this.alName;
	}

	public void setAlName(String alName) {
		this.alName = alName;
	}

	@Column(name = "AL_PATH")
	public String getAlPath() {
		return this.alPath;
	}

	public void setAlPath(String alPath) {
		this.alPath = alPath;
	}

	@Column(name = "AL_OPERATE_CSR_ID")
	public String getAlOperateCsrId() {
		return this.alOperateCsrId;
	}

	public void setAlOperateCsrId(String alOperateCsrId) {
		this.alOperateCsrId = alOperateCsrId;
	}

	@Column(name = "AL_OPERATE_CSR")
	public String getAlOperateCsr() {
		return this.alOperateCsr;
	}

	public void setAlOperateCsr(String alOperateCsr) {
		this.alOperateCsr = alOperateCsr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AL_OPERATE_TIME")
	public Date getAlOperateTime() {
		return this.alOperateTime;
	}

	public void setAlOperateTime(Date alOperateTime) {
		this.alOperateTime = alOperateTime;
	}


	@Transient
	public String getStepDes() {
		return stepDes;
	}


	public void setStepDes(String stepDes) {
		this.stepDes = stepDes;
	}

	@Column(name = "SOURCE_TYPE")
	public String getSourceType() {
		return sourceType;
	}


	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	@Column(name = "BUS_RECORD_ID")
	public String getBusRecordId() {
		return busRecordId;
	}


	public void setBusRecordId(String busRecordId) {
		this.busRecordId = busRecordId;
	}

	@Transient
	public String getPictureStr() {
		return pictureStr;
	}


	public void setPictureStr(String pictureStr) {
		this.pictureStr = pictureStr;
	}

	@Transient
	public String getStepDesTemp() {
		return stepDesTemp;
	}


	public void setStepDesTemp(String stepDesTemp) {
		this.stepDesTemp = stepDesTemp;
	}

	@Column(name="ENCRYPTION")
	public String getEncryption() {
		return encryption;
	}
	public void setEncryption(String encryption) {
		this.encryption = encryption;
	}

	
	@Column(name="STEP")
	public String getStep() {
		return step;
	}


	public void setStep(String step) {
		this.step = step;
	}

	
}