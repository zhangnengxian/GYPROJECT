package cc.dfsoft.project.biz.base.project.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Notice entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NOTICE")
public class Notice implements java.io.Serializable {

	// Fields

	private String noticeId;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeTime;
	private Date generateTime;
	private String noticeStaffId;
	private String noticeStaffName;
	private String projId;
	private String actionType;
	private String area;
	private String noticeState;
	private String noticeType;		//通知类型 1 都能看到 2 成本控制部看到
	private String evId;			//签证id
	// Constructors
	private String grade;			//审核级别
	private String corpId;			//分公司id
	private String auditType;		//审核类型
	
	private String businessOrderId;//业务单Id
	
	private String signType;		//签字类型
	private String url;				//跳转
	private String projNo;          //工程编号
	private String projName;          //工程名称
	
	/** default constructor */
	public Notice() {
	}

	
	// Property accessors
	@Id
	@Column(name = "NOTICE_ID")
	public String getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	@Column(name = "NOTICE_TITLE")
	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	@Column(name = "NOTICE_CONTENT")
	public String getNoticeContent() {
		return this.noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NOTICE_TIME")
	public Date getNoticeTime() {
		return this.noticeTime;
	}

	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}

	@Column(name = "NOTICE_STAFF_ID")
	public String getNoticeStaffId() {
		return this.noticeStaffId;
	}

	public void setNoticeStaffId(String noticeStaffId) {
		this.noticeStaffId = noticeStaffId;
	}

	@Column(name = "NOTICE_STAFF_NAME")
	public String getNoticeStaffName() {
		return this.noticeStaffName;
	}

	public void setNoticeStaffName(String noticeStaffName) {
		this.noticeStaffName = noticeStaffName;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return projId;
	}


	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "ACTION_TYPE")
	public String getActionType() {
		return actionType;
	}


	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	@Column(name = "AREA")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	@Column(name = "NOTICE_STATE")
	public String getNoticeState() {
		return noticeState;
	}

	public void setNoticeState(String noticeState) {
		this.noticeState = noticeState;
	}

	@Column(name = "NOTICE_TYPE")
	public String getNoticeType() {
		return noticeType;
	}


	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	
	@Column(name = "EV_ID")
	public String getEvId() {
		return evId;
	}

	public void setEvId(String evId) {
		this.evId = evId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GENERATE_TIME")
	public Date getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}

	@Column(name = "GRADE")
	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}


	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Column(name = "AUDIT_TYPE")
	public String getAuditType() {
		return auditType;
	}


	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	@Column(name = "BUSINESS_ORDER_ID")
	public String getBusinessOrderId() {
		return businessOrderId;
	}


	public void setBusinessOrderId(String businessOrderId) {
		this.businessOrderId = businessOrderId;
	}

	
	@Column(name = "SIGN_TYPE")
	public String getSignType() {
		return signType;
	}


	public void setSignType(String signType) {
		this.signType = signType;
	}

	
	@Transient
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	@Transient
	public String getProjNo() {
		return projNo;
	}


	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Transient
	public String getProjName() {
		return projName;
	}


	public void setProjName(String projName) {
		this.projName = projName;
	}
	
	
}