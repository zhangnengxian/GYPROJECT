package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.sql.rowset.serial.SerialException;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

/**
 * ConnectRecordOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONNECT_RECORD_ORDER")
public class ConnectRecordOrder implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6854122729539322054L;
	private String croId;				//碰口记录ID
	private String projId;				//工程ID
	private String projName;			//工程名称
	private String projAddr;			//工程地点
	private String touchAddr;			//碰口地点
	private Date tpDate;				//碰口日期
	private String constructionUnit;	//施工单位
	private String suName;				//监理单位
	private String projManager;			//项目经理
	private String fieldJgj;			//现场监理
	private Clob fieldPrincipal;		//现场负责人
	private Clob constructionCustodian;//施工部监护人员
	private Clob fieldJgjC;			//现场监理
	private Clob ongcDeputy;			//天然气分公司签字
	private Date touchEndDate;			//碰口完成时间
	private String touchEndDes;			//碰口完成时间（临时值）
	private Date instructionTime;		//指令下达时间
	private String instructTimeDes;		//指令下达时间（临时值）

	private List<ConnectRecord> connectRecords;  //---------多条碰口记录
	private String welderId1;		//---------焊工证号1
	private Clob welder1;			//---------焊工签字1
	private String welderId2;		//---------焊工证号2
	private Clob welder2;			//---------焊工签字2
	private String welderId3;		//---------焊工证号3
	private Clob welder3;			//---------焊工签字3
	private String longitude;					//经度
	private String latitude;					//纬度
	private List<Signature> sign;	//---------签字相关数据

	private Clob fieldPrincipal1;		//现场负责人二签
	private Clob constructionCustodian1;//施工部监护人员二签
	private Clob fieldJgjC1;			//现场监理二签
	private Clob ongcDeputy1;			//天然气分公司签字二签
	
	@Column(name = "FIELD_PRINCIPAL1")
	public String getFieldPrincipal1() {
		return ClobUtil.ClobToString(fieldPrincipal1);
	}

	public void setFieldPrincipal1(String fieldPrincipal1) throws SerialException, SQLException {
		this.fieldPrincipal1 = ClobUtil.stringToClob(fieldPrincipal1);
	}

	@Column(name = "CONSTRUCTION_CUSTODIAN1")
	public String getConstructionCustodian1() {
		return ClobUtil.ClobToString(constructionCustodian1);
	}

	public void setConstructionCustodian1(String constructionCustodian1) throws SerialException, SQLException {
		this.constructionCustodian1 = ClobUtil.stringToClob(constructionCustodian1);
	}

	@Column(name = "FIELD_JGJ_C1")
	public String getFieldJgjC1() {
		return ClobUtil.ClobToString(fieldJgjC1);
	}

	public void setFieldJgjC1(String fieldJgjC1) throws SerialException, SQLException {
		this.fieldJgjC1 = ClobUtil.stringToClob(fieldJgjC1);
	}

	@Column(name = "ONGC_DEPUTY1")
	public String getOngcDeputy1() {
		return ClobUtil.ClobToString(ongcDeputy1);
	}

	public void setOngcDeputy1(String ongcDeputy1) throws SerialException, SQLException {
		this.ongcDeputy1 = ClobUtil.stringToClob(ongcDeputy1);
	}

	@Transient
	public List<ConnectRecord> getConnectRecords() {
		return connectRecords;
	}

	public void setConnectRecords(List<ConnectRecord> connectRecords) {
		this.connectRecords = connectRecords;
	}

	/** default constructor */
	public ConnectRecordOrder() {
	}

	/** minimal constructor */
	public ConnectRecordOrder(String croId) {
		this.croId = croId;
	}

	// Property accessors
	@Id
	@Column(name = "CRO_ID", unique = true)
	public String getCroId() {
		return this.croId;
	}

	public void setCroId(String croId) {
		this.croId = croId;
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

	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return this.projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	@Column(name = "TOUCH_ADDR")
	public String getTouchAddr() {
		return this.touchAddr;
	}

	public void setTouchAddr(String touchAddr) {
		this.touchAddr = touchAddr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TP_DATE")
	public Date getTpDate() {
		return this.tpDate;
	}

	public void setTpDate(Date tpDate) {
		this.tpDate = tpDate;
	}

	@Column(name = "CONSTRUCTION_UNIT")
	public String getConstructionUnit() {
		return this.constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	@Column(name = "SU_NAME")
	public String getSuName() {
		return this.suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	@Column(name = "PROJ_MANAGER")
	public String getProjManager() {
		return this.projManager;
	}

	public void setProjManager(String projManager) {
		this.projManager = projManager;
	}

	@Column(name = "FIELD_JGJ")
	public String getFieldJgj() {
		return this.fieldJgj;
	}

	public void setFieldJgj(String fieldJgj) {
		this.fieldJgj = fieldJgj;
	}

	@Lob
	@Column(name = "FIELD_PRINCIPAL")
	public String getFieldPrincipal() {
		return ClobUtil.ClobToString(this.fieldPrincipal);
	}

	public void setFieldPrincipal(String fieldPrincipal) throws SerialException, SQLException {
		this.fieldPrincipal = ClobUtil.stringToClob(fieldPrincipal);
	}

	@Column(name = "CONSTRUCTION_CUSTODIAN")
	public String getConstructionCustodian() {
		return ClobUtil.ClobToString(this.constructionCustodian);
	}

	public void setConstructionCustodian(String constructionCustodian) throws SerialException, SQLException {
		this.constructionCustodian = ClobUtil.stringToClob(constructionCustodian);
	}

	@Column(name = "FIELD_JGJ_C")
	public String getFieldJgjC() {
		return ClobUtil.ClobToString(this.fieldJgjC);
	}

	public void setFieldJgjC(String fieldJgjC) throws SerialException, SQLException {
		this.fieldJgjC = ClobUtil.stringToClob(fieldJgjC);
	}

	@Column(name = "ONGC_DEPUTY")
	public String getOngcDeputy() {
		return ClobUtil.ClobToString(this.ongcDeputy);
	}

	public void setOngcDeputy(String ongcDeputy) throws SerialException, SQLException {
		this.ongcDeputy = ClobUtil.stringToClob(ongcDeputy);
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TOUCH_END_DATE")
	public Date getTouchEndDate() {
		return this.touchEndDate;
	}

	public void setTouchEndDate(Date touchEndDate) {
		this.touchEndDate = touchEndDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSTRUCTION_TIME")
	public Date getInstructionTime() {
		return this.instructionTime;
	}

	
	public void setInstructionTime(Date instructionTime) {
		this.instructionTime = instructionTime;
	}

	@Column(name = "LONGITUDE")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "LATITUDE")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	
	@Transient
	public String getWelderId1() {
		return this.welderId1;
	}
	
	public void setWelderId1(String welderId1) {
		this.welderId1 = welderId1;
	}

	@Lob
	@Transient
	public String getWelder1() {
		return ClobUtil.ClobToString(this.welder1);
	}

	public void setWelder1(String welder1) throws SerialException, SQLException {
		this.welder1 = ClobUtil.stringToClob(welder1);
	}

	@Transient
	public String getWelderId2() {
		return this.welderId2;
	}

	public void setWelderId2(String welderId2) {
		this.welderId2 = welderId2;
	}

	@Lob
	@Transient
	public String getWelder2() {
		return ClobUtil.ClobToString(this.welder2);
	}

	public void setWelder2(String welder2) throws SerialException, SQLException {
		this.welder2 = ClobUtil.stringToClob(welder2);
	}
	
	@Transient
	public String getWelderId3() {
		return this.welderId3;
	}

	public void setWelderId3(String welderId3) {
		this.welderId3 = welderId3;
	}

	@Lob
	@Transient
	public String getWelder3() {
		return ClobUtil.ClobToString(this.welder3);
	}

	public void setWelder3(String welder3) throws SerialException, SQLException {
		this.welder3 = ClobUtil.stringToClob(welder3);
	}
	@Transient
	public String getInstructTimeDes() {
		return instructTimeDes;
	}

	public void setInstructTimeDes(String instructTimeDes) {
		this.instructTimeDes = instructTimeDes;
	}

	
	@Transient
	public String getTouchEndDes() {
		return touchEndDes;
	}

	public void setTouchEndDes(String touchEndDes) {
		this.touchEndDes = touchEndDes;
	}
	
	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSignature(List<Signature> sign) {
		this.sign = sign;
	}
	
}