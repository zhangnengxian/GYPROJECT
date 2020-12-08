package cc.dfsoft.project.biz.base.inspection.entity;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * PepipeWelding entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PEPIPE_WELDING")
public class PepipeWelding implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6418074634119898173L;
	private String peId;				//PE管焊接质量记录ID
	private String pcId;				//报验单ID
	private String peWeldNo;			//焊缝编号
	private Date peWeldingDate;			//焊接日期
	private String peSize;				//管道规格
	private String peWeldingMethod;		//焊接方法
	private String temperature;			//环境温度
	private String peBaseType;			//接头型式
	private String peHeatingOc;			//加热温度
	private String peHeatingTime;		//加热时间
	private String peCoolingTime;		//冷却时间
	private String peDragPressure;		//拖动压力
	private String peWeldingPressure;	//焊接压力
	private String peWelderName;		//焊工姓名
	
	/**增加字段*/
	private String pePipePosition;		//管位PE_PIPE_POSITION
	private String peSectionLen;		//管段长度PE_SECTION_LEN
	private String otherInpect;			//其他检查OTHER_INPECT
	private Clob builder;			    //施工员BUILDER
	private Clob firstParty;			//甲方FIRST_PARTY
	private Clob supervision;			//监理SUPERVISION
	private String jointNum;			//接口数JOINT_NUM
	private String holdingTime;			//保压时间HOLDING_TIME
	private String projId;	//工程ID
	
	private List<Signature> sign;		//签字
	private String menuId;

	private Integer version;		//版本控制
	private String isFinishSign;//是否完成签字 1是，0否
	// Constructors

	/** default constructor */
	public PepipeWelding() {
	}

	// Property accessors
	@Id
	@Column(name = "PE_ID", unique = true)
	public String getPeId() {
		return this.peId;
	}

	public void setPeId(String peId) {
		this.peId = peId;
	}

	@Column(name = "PC_ID")
	public String getPcId() {
		return this.pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name = "PE_WELD_NO")
	public String getPeWeldNo() {
		return this.peWeldNo;
	}

	public void setPeWeldNo(String peWeldNo) {
		this.peWeldNo = peWeldNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PE_WELDING_DATE")
	public Date getPeWeldingDate() {
		return this.peWeldingDate;
	}

	public void setPeWeldingDate(Date peWeldingDate) {
		this.peWeldingDate = peWeldingDate;
	}

	@Column(name = "PE_SIZE")
	public String getPeSize() {
		return this.peSize;
	}

	public void setPeSize(String peSize) {
		this.peSize = peSize;
	}

	@Column(name = "PE_WELDING_METHOD")
	public String getPeWeldingMethod() {
		return this.peWeldingMethod;
	}

	public void setPeWeldingMethod(String peWeldingMethod) {
		this.peWeldingMethod = peWeldingMethod;
	}

	@Column(name = "TEMPERATURE")
	public String getTemperature() {
		return this.temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	@Column(name = "PE_BASE_TYPE")
	public String getPeBaseType() {
		return this.peBaseType;
	}

	public void setPeBaseType(String peBaseType) {
		this.peBaseType = peBaseType;
	}

	@Column(name = "PE_HEATING_OC")
	public String getPeHeatingOc() {
		return this.peHeatingOc;
	}

	public void setPeHeatingOc(String peHeatingOc) {
		this.peHeatingOc = peHeatingOc;
	}

	@Column(name = "PE_HEATING_TIME")
	public String getPeHeatingTime() {
		return this.peHeatingTime;
	}

	public void setPeHeatingTime(String peHeatingTime) {
		this.peHeatingTime = peHeatingTime;
	}

	@Column(name = "PE_COOLING_TIME")
	public String getPeCoolingTime() {
		return this.peCoolingTime;
	}

	public void setPeCoolingTime(String peCoolingTime) {
		this.peCoolingTime = peCoolingTime;
	}

	@Column(name = "PE_DRAG_PRESSURE")
	public String getPeDragPressure() {
		return this.peDragPressure;
	}

	public void setPeDragPressure(String peDragPressure) {
		this.peDragPressure = peDragPressure;
	}

	@Column(name = "PE_WELDING_PRESSURE")
	public String getPeWeldingPressure() {
		return this.peWeldingPressure;
	}

	public void setPeWeldingPressure(String peWeldingPressure) {
		this.peWeldingPressure = peWeldingPressure;
	}

	@Column(name = "PE_WELDER_NAME")
	public String getPeWelderName() {
		return this.peWelderName;
	}

	public void setPeWelderName(String peWelderName) {
		this.peWelderName = peWelderName;
	}

	@Column(name="PE_PIPE_POSITION")
	public String getPePipePosition() {
		return pePipePosition;
	}

	public void setPePipePosition(String pePipePosition) {
		this.pePipePosition = pePipePosition;
	}

	@Column(name="PE_SECTION_LEN")
	public String getPeSectionLen() {
		return peSectionLen;
	}

	public void setPeSectionLen(String peSectionLen) {
		this.peSectionLen = peSectionLen;
	}

	@Column(name="OTHER_INPECT")
	public String getOtherInpect() {
		return otherInpect;
	}

	public void setOtherInpect(String otherInpect) {
		this.otherInpect = otherInpect;
	}

	@Column(name="BUILDER")
	public String getBuilder() {
		return ClobUtil.ClobToString(this.builder);
	}

	public void setBuilder(String builder) throws SerialException, SQLException {
		this.builder = ClobUtil.stringToClob(builder);
	}

	@Column(name="FIRST_PARTY")
	public String getFirstParty() {
		return ClobUtil.ClobToString(this.firstParty);
	}

	public void setFirstParty(String firstParty) throws SerialException, SQLException {
		this.firstParty = ClobUtil.stringToClob(firstParty);
	}

	@Column(name="SUPERVISION")
	public String getSupervision() {
		return ClobUtil.ClobToString(this.supervision);
	}

	public void setSupervision(String supervision) throws SerialException, SQLException {
		this.supervision = ClobUtil.stringToClob(supervision);
	}

	@Column(name="JOINT_NUM")
	public String getJointNum() {
		return jointNum;
	}

	public void setJointNum(String jointNum) {
		this.jointNum = jointNum;
	}

	@Column(name="HOLDING_TIME")
	public String getHoldingTime() {
		return holdingTime;
	}

	public void setHoldingTime(String holdingTime) {
		this.holdingTime = holdingTime;
	}

	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}

	@Transient
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	@Column(name="IS_FINISH_SIGN")
	public String getIsFinishSign() {
		return isFinishSign;
	}

	public void setIsFinishSign(String isFinishSign) {
		this.isFinishSign = isFinishSign;
	}
}