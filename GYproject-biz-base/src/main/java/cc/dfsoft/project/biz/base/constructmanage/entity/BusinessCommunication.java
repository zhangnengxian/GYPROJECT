package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper.StandardWarningHandler;

import com.mchange.v2.async.StrandedTaskReporting;

import cc.dfsoft.project.biz.base.constructmanage.enums.BcStatusEnum;

/**
 * Model class of business_communication.
 * 业务沟通实体
 * @author generated by ERMaster
 * @version $Id$
 */
@Entity
@Table(name = "BUSINESS_COMMUNICATION")
public class BusinessCommunication implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 618755998920956791L;
	private String bcId;				//业务通知Id
	private String projId;				//工程Id
	private String projNo;				//工程编号
	private String projName;			//工程编号
	private String bcStatus;			//业务通知状态
	private String bcType;				//通知类型
	private String bcTypeDetail;		//通知细类
	private String bcInitiatorId;		//发起人Id  UNITTYPE_OF_INITIATOR
	private String bcInitiatorName;		//发起人姓名
	private String bcInitiatorUnitId;	//发起人单位ID
	private String bcInitiatorUnitName;	//发起人单位名称
	private Date noticeDate;			//通知日期
	private String noticeContent;		//通知内容
	private String unitType;			//接收者部门类型
	private String deptId;				//接收者部门
	private String bcRecipientId;		//接受者Id
	private String bcRecipientName;		//接受者姓名
	private String bcRecipientDeptName;	//接受者部门名称
	private Date replyDate;				//回复日期
	private String replyContent;		//回复内容
	private Integer version;			//版本控制
	private String bcSendType;			//业务通知发送类型-页面列表显示1：发送，2：接收
	private String bcStatusDes;			//业务通知状态列表显示1：待通知，2：待回复，3：已回复
	private String unitTypeOfInitiator;  //发起人单位类型

	private NdeRecord ndeRecord;		//无损探伤类
	private String pushFlag;			//推送状态-只读
	private String markOfTestResult;    //返修标记
	private String mark;              //标记-只读

	private String sendJpushStatus;		//发送消息状态 1 已发 其他未发

	/**
	 * Constructor.
	 */
	public BusinessCommunication() {
	}

	public void setBcId(String bcId) {
		this.bcId = bcId;
	}

	@Id
	@Column(name = "BC_ID", unique = true)
	public String getBcId() {
		return this.bcId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return this.projNo;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}
	
	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return this.projName;
	}

	public void setBcStatus(String bcStatus) {
		this.bcStatus = bcStatus;
	}
	
	@Column(name = "BC_STATUS")
	public String getBcStatus() {
		return this.bcStatus;
	}

	public void setBcType(String bcType) {
		this.bcType = bcType;
	}

	@Column(name = "BC_TYPE")
	public String getBcType() {
		return this.bcType;
	}

	public void setBcTypeDetail(String bcTypeDetail) {
		this.bcTypeDetail = bcTypeDetail;
	}

	@Column(name = "BC_TYPE_DETAIL")
	public String getBcTypeDetail() {
		return this.bcTypeDetail;
	}

	public void setBcInitiatorId(String bcInitiatorId) {
		this.bcInitiatorId = bcInitiatorId;
	}

	@Column(name = "BC_INITIATOR_ID")
	public String getBcInitiatorId() {
		return this.bcInitiatorId;
	}

	public void setBcInitiatorName(String bcInitiatorName) {
		this.bcInitiatorName = bcInitiatorName;
	}
	
	@Column(name = "BC_INITIATOR_NAME")
	public String getBcInitiatorName() {
		return this.bcInitiatorName;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "NOTICE_DATE")
	public Date getNoticeDate() {
		return this.noticeDate;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	@Column(name = "NOTICE_CONTENT")
	public String getNoticeContent() {
		return this.noticeContent;
	}

	public void setBcRecipientId(String bcRecipientId) {
		this.bcRecipientId = bcRecipientId;
	}

	@Column(name = "BC_RECIPIENT_ID")
	public String getBcRecipientId() {
		return this.bcRecipientId;
	}

	public void setBcRecipientName(String bcRecipientName) {
		this.bcRecipientName = bcRecipientName;
	}

	@Column(name = "BC_RECIPIENT_NAME")
	public String getBcRecipientName() {
		return this.bcRecipientName;
	}

	public void setBcRecipientDeptName(String bcRecipientDeptName) {
		this.bcRecipientDeptName = bcRecipientDeptName;
	}

	@Column(name = "BC_RECIPIENT_DEPT_NAME")
	public String getBcRecipientDeptName() {
		return this.bcRecipientDeptName;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REPLY_DATE")
	public Date getReplyDate() {
		return this.replyDate;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	@Column(name = "REPLY_CONTENT")
	public String getReplyContent() {
		return this.replyContent;
	}

	
	@Column(name = "UNITTYPE_OF_INITIATOR")
	public String getUnitTypeOfInitiator() {
		return unitTypeOfInitiator;
	}

	public void setUnitTypeOfInitiator(String unitTypeOfInitiator) {
		this.unitTypeOfInitiator = unitTypeOfInitiator;
	}

	@Transient
	public String getBcSendType() {
		return bcSendType;
	}

	public void setBcSendType(String bcSendType) {
		this.bcSendType = bcSendType;
	}

	
	@Transient
	public String getBcStatusDes() {
		for(BcStatusEnum e: BcStatusEnum.values()) {
	   		if(e.getValue().equals(this.bcStatus)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setBcStatusDes(String bcStatusDes) {
		this.bcStatusDes = bcStatusDes;
	}

	@Column(name = "UNIT_TYPE")
	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	@Column(name = "DEPT_ID")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Version
	@Column(name = "VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Transient
	public NdeRecord getNdeRecord() {
		return ndeRecord;
	}

	public void setNdeRecord(NdeRecord ndeRecord) {
		this.ndeRecord = ndeRecord;
	}

	@Column(name="BC_INITIATOR_UNIT_ID")
	public String getBcInitiatorUnitId() {
		return bcInitiatorUnitId;
	}

	public void setBcInitiatorUnitId(String bcInitiatorUnitId) {
		this.bcInitiatorUnitId = bcInitiatorUnitId;
	}

	@Column(name="BC_INITIATOR_UNIT_NAME")
	public String getBcInitiatorUnitName() {
		return bcInitiatorUnitName;
	}

	public void setBcInitiatorUnitName(String bcInitiatorUnitName) {
		this.bcInitiatorUnitName = bcInitiatorUnitName;
	}

	
	@Column(name="MARK_OF_TESTRESULT")
	public String getMarkOfTestResult() {
		return markOfTestResult;
	}

	public void setMarkOfTestResult(String markOfTestResult) {
		this.markOfTestResult = markOfTestResult;
	}

	@Transient
	public String getPushFlag() {
		return pushFlag;
	}

	public void setPushFlag(String pushFlag) {
		this.pushFlag = pushFlag;
	}
    
	@Transient
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	@Column(name="SEND_JPUSH_STATUS")
	public String getSendJpushStatus() {
		return sendJpushStatus;
	}

	public void setSendJpushStatus(String sendJpushStatus) {
		this.sendJpushStatus = sendJpushStatus;
	}
}
