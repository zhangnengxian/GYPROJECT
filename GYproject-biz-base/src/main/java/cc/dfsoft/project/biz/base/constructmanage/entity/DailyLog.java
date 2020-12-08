package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.sql.rowset.serial.SerialException;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

/**
 * DailyLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DAILY_LOG")
public class DailyLog implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7358729616798829213L;
	private String dlId;					//日志记录ID
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private String projName;				//工程名称
	private String projAddr;				//工程地点
	private String constructionUnit;		//施工单位
	private String dlRecorder;				//记录人
	private Date dlDate;					//登记日期
	private String dlWeather;				//天气
	private String dlTemperature;			//气温
	private String dlWind;					//风力
	private String constructOrganization;	//施工组织记录
	private String quality;					//质量记录
	private String safetyRecord;			//安全记录
	private String checkRecord;				//管理层检查记录
	private Clob fieldPrincipal;			//现场负责人
	private String dlRecorderId;			//记录人ID
	private String dlRecorderPost;			//记录人职务
	private List<Signature> sign;			//签字相关数据
	

	// Constructors

	/** default constructor */
	public DailyLog() {
	}

	// Property accessors
	@Id
	@Column(name = "DL_ID", unique = true)
	public String getDlId() {
		return this.dlId;
	}

	public void setDlId(String dlId) {
		this.dlId = dlId;
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

	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return this.projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	@Column(name = "CONSTRUCTION_UNIT")
	public String getConstructionUnit() {
		return this.constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	@Column(name = "DL_RECORDER")
	public String getDlRecorder() {
		return this.dlRecorder;
	}

	public void setDlRecorder(String dlRecorder) {
		this.dlRecorder = dlRecorder;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DL_DATE")
	public Date getDlDate() {
		return this.dlDate;
	}

	public void setDlDate(Date dlDate) {
		this.dlDate = dlDate;
	}

	@Column(name = "DL_WEATHER")
	public String getDlWeather() {
		return this.dlWeather;
	}

	public void setDlWeather(String dlWeather) {
		this.dlWeather = dlWeather;
	}

	@Column(name = "DL_TEMPERATURE")
	public String getDlTemperature() {
		return this.dlTemperature;
	}

	public void setDlTemperature(String dlTemperature) {
		this.dlTemperature = dlTemperature;
	}

	@Column(name = "DL_WIND")
	public String getDlWind() {
		return this.dlWind;
	}

	public void setDlWind(String dlWind) {
		this.dlWind = dlWind;
	}

	@Column(name = "CONSTRUCT_ORGANIZATION")
	public String getConstructOrganization() {
		return this.constructOrganization;
	}

	public void setConstructOrganization(String constructOrganization) {
		this.constructOrganization = constructOrganization;
	}

	@Column(name = "QUALITY")
	public String getQuality() {
		return this.quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Column(name = "SAFETY_RECORD")
	public String getSafetyRecord() {
		return this.safetyRecord;
	}

	public void setSafetyRecord(String safetyRecord) {
		this.safetyRecord = safetyRecord;
	}

	@Column(name = "CHECK_RECORD")
	public String getCheckRecord() {
		return this.checkRecord;
	}

	public void setCheckRecord(String checkRecord) {
		this.checkRecord = checkRecord;
	}

	@Column(name = "FIELD_PRINCIPAL")
	public String getFieldPrincipal() {
		return ClobUtil.ClobToString(this.fieldPrincipal);
	}

	public void setFieldPrincipal(String fieldPrincipal) throws SerialException, SQLException{
		this.fieldPrincipal = ClobUtil.stringToClob(fieldPrincipal);
	}
	
	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSignature(List<Signature> sign) {
		this.sign = sign;
	}

	@Column(name = "DL_RECORDER_ID")
	public String getDlRecorderId() {
		return dlRecorderId;
	}

	public void setDlRecorderId(String dlRecorderId) {
		this.dlRecorderId = dlRecorderId;
	}

	@Column(name = "DL_RECORDER_POST")
	public String getDlRecorderPost() {
		return dlRecorderPost;
	}

	public void setDlRecorderPost(String dlRecorderPost) {
		this.dlRecorderPost = dlRecorderPost;
	}

}