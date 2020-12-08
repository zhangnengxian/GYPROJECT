package cc.dfsoft.uexpress.biz.sys.dept.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ENCLOSURE entity
 */
@Entity
@Table(name = "ENCLOSURE")
public class Enclosure implements Serializable {
	
	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 8722135158046525090L;
	private String enId;			//主键ID 
	private String alTypeId;		//文档类型ID
	private String alName;			//附件名称
	private String alPath;			//路径
	private String alOperateCsrId;	//操作人ID
	private String alOperateCsr;	//操作人名称
	private Date alOperateTime;		//操作时间
	private String sourceType;      //附件来源 1 员工管理
	private String busRecordId;     //业务单id
	private String encryption;		//是否加密 1是 0 否
	// Constructors

	/** default constructor */
	public Enclosure() {
	}


	// Property accessors
	@Id
	@Column(name = "EN_ID", unique = true)
	public String getEnId() {
		return this.enId;
	}

	public void setEnId(String enId) {
		this.enId = enId;
	}

	@Column(name = "AL_TYPE_ID")
	public String getAlTypeId() {
		return this.alTypeId;
	}

	public void setAlTypeId(String alTypeId) {
		this.alTypeId = alTypeId;
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

	@Column(name="ENCRYPTION")
	public String getEncryption() {
		return encryption;
	}
	public void setEncryption(String encryption) {
		this.encryption = encryption;
	}

}