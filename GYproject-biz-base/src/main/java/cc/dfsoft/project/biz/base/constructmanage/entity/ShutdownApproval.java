package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import cc.dfsoft.project.biz.base.project.entity.Signature;
/**
 * 复工申报实体类
 * @author liaoyq
 *
 */
@Entity
@Table(name="SHUTDOWN_APPROVAL")
public class ShutdownApproval implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sdaId;//`SDA_ID` varchar(30) NOT NULL COMMENT '复工申报记录ID',
	private String sdrId;// `SDR_ID` varchar(30) NOT NULL COMMENT '停复工记录表ID',
	private String sdrNo;// 停工编号
	private String projId;//工程id
	private String reworkDate;//  `REWORK_DATE` datetime DEFAULT NULL COMMENT '复工日期',
	private String cuManager;//  `CU_MANAGER` longtext COMMENT '项目经理',
	private Date cuApprovalDate;//  `CU_APPROVAL_DATE` datetime DEFAULT NULL COMMENT '报审日期',
	private String suAdvice;//  `SU_ADVICE` varchar(200) DEFAULT NULL COMMENT '监理公司审查意见',
	private String suCes;// `SUCES` longtext COMMENT '总监理工程师',
	private Date suCesReviewDate;//  `SUCES_REVIEW_DATE` datetime DEFAULT NULL COMMENT '监理审查日期',
	private String custAdvice;//  `CUST_ADVICE` varchar(200) DEFAULT NULL COMMENT '建设单位审批意见',
	private String custRepresent;//  `CUST_REPRESENT` longtext COMMENT '建设单位代表',
	private Date custAuditDate;//  `CUST_AUDIT_DATE` datetime DEFAULT NULL COMMENT '建设单位审批日期',
	private String corpName;	//建设单位
	private Integer pushStatus;//推送状态
	private Date pushDate;	   //推送日期
	private String pushStaffId;//推送人ID
	
	
	private ShutDownRecord shutDownRecord;//只用于存储数据，不增加到数据库
	
	private List<Signature> sign;
	private Integer version;			//版本控制
	public ShutdownApproval() {
		super();
	}
	
	@Id
	@Column(name="SDA_ID", unique = true, nullable = false)
	public String getSdaId() {
		return sdaId;
	}
	public void setSdaId(String sdaId) {
		this.sdaId = sdaId;
	}
	
	@Column(name="SDR_ID")
	public String getSdrId() {
		return sdrId;
	}
	public void setSdrId(String sdrId) {
		this.sdrId = sdrId;
	}
	
	@Column(name="REWORK_DATE")
	public String getReworkDate() {
		return reworkDate;
	}
	public void setReworkDate(String reworkDate) {
		this.reworkDate = reworkDate;
	}
	
	@Column(name="CU_MANAGER")
	public String getCuManager() {
		return cuManager;
	}
	public void setCuManager(String cuManager) {
		this.cuManager = cuManager;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="CU_APPROVAL_DATE")
	public Date getCuApprovalDate() {
		return cuApprovalDate;
	}
	public void setCuApprovalDate(Date cuApprovalDate) {
		this.cuApprovalDate = cuApprovalDate;
	}
	
	@Column(name="SU_ADVICE")
	public String getSuAdvice() {
		return suAdvice;
	}
	public void setSuAdvice(String suAdvice) {
		this.suAdvice = suAdvice;
	}
	
	@Column(name="SUCES")
	public String getSuCes() {
		return suCes;
	}
	public void setSuCes(String suCes) {
		this.suCes = suCes;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="SUCES_REVIEW_DATE")
	public Date getSuCesReviewDate() {
		return suCesReviewDate;
	}
	public void setSuCesReviewDate(Date suCesReviewDate) {
		this.suCesReviewDate = suCesReviewDate;
	}
	
	@Column(name="CUST_ADVICE")
	public String getCustAdvice() {
		return custAdvice;
	}
	public void setCustAdvice(String custAdvice) {
		this.custAdvice = custAdvice;
	}
	
	@Column(name="CUST_REPRESENT")
	public String getCustRepresent() {
		return custRepresent;
	}
	public void setCustRepresent(String custRepresent) {
		this.custRepresent = custRepresent;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="CUST_AUDIT_DATE")
	public Date getCustAuditDate() {
		return custAuditDate;
	}

	public void setCustAuditDate(Date custAuditDate) {
		this.custAuditDate = custAuditDate;
	}
	
	@Column(name="SDR_NO")
	public String getSdrNo() {
		return sdrNo;
	}

	public void setSdrNo(String sdrNo) {
		this.sdrNo = sdrNo;
	}

	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}

	@Transient
	public ShutDownRecord getShutDownRecord() {
		return shutDownRecord;
	}

	public void setShutDownRecord(ShutDownRecord shutDownRecord) {
		this.shutDownRecord = shutDownRecord;
	}

	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name="PUSH_STATUS")
	public Integer getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(Integer pushStatus) {
		this.pushStatus = pushStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PUSH_DATE")
	public Date getPushDate() {
		return pushDate;
	}

	public void setPushDate(Date pushDate) {
		this.pushDate = pushDate;
	}

	@Column(name="PUSH_STAFF_ID")
	public String getPushStaffId() {
		return pushStaffId;
	}

	public void setPushStaffId(String pushStaffId) {
		this.pushStaffId = pushStaffId;
	}

	@Column(name="CORP_NAME")
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
