package cc.dfsoft.project.biz.base.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OperateRecordLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OPERATE_RECORD_LOG")
public class OperateRecordLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -642275219294863976L;
	
	private String orlId;			//主键ID
	private String operateType;		//操作类型
	private String businessId;		//相关表Id
	private String operateDept;		//操作部门
	private String operateCsr;		//操作人
	private Date operateTime;		//操作时间
	private String operateContent;	//操作内容
	// Constructors

	/** default constructor */
	public OperateRecordLog() {
	}


	// Property accessors
	@Id
	@Column(name = "ORL_ID")
	public String getOrlId() {
		return this.orlId;
	}

	public void setOrlId(String orlId) {
		this.orlId = orlId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPERATE_TIME")
	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TYPE")
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "BUSINESS_ID")
	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	@Column(name = "OPERATE_DEPT")
	public String getOperateDept() {
		return operateDept;
	}

	public void setOperateDept(String operateDept) {
		this.operateDept = operateDept;
	}

	@Column(name = "OPERATE_CSR")
	public String getOperateCsr() {
		return operateCsr;
	}

	public void setOperateCsr(String operateCsr) {
		this.operateCsr = operateCsr;
	}

	@Column(name = "OPERATE_CONTENT")
	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}
	
}