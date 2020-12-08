package cc.dfsoft.project.biz.base.inspection.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
/**
 * 强度试验实体类
 * @author liaoyq
 *
 */
@Entity
@Table(name="STRENGTH_TEST")
public class StrengthTest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private String stId;				// `ST_ID` varchar(30) NOT NULL COMMENT '强度试验记录ID',
	 private String pcId;				//`PC_ID` varchar(30) NOT NULL COMMENT '报验单ID',
	 private String stPressure;			// `ST_PRESSURE` varchar(30) DEFAULT NULL COMMENT '气密性试验压力',
	 private Date stDate;				// `ST_DATE` datetime DEFAULT NULL COMMENT '气密性试验日期',
	 private String  stActualPressure;//`ST_ACTUAL_PRESSURE` varchar(30) DEFAULT NULL COMMENT '强度试验实际压力',
	 private String stActualStartTime;//`ST_ACTUAL__START_TIME` datetime DEFAULT NULL COMMENT '强度试验实际稳压起始时间',
	 private String stActualEndTime;// `ST_ACTUAL__END_TIME` datetime DEFAULT NULL COMMENT '强度试验实际稳压截止时间',
	 
	 private String riserNo;			//户内立管编号
	 private String stRange;			//户强度试验范围
	 private String gasTRange;			//气密性试验范围
	 private String gasTPressure;		//气密性试验检测压力
	 private Date testDate;//户内试验日期
	 private String testResult;//户内试验结果
	 private String testMethod;//户内检测方法
	 private String projId;	//工程ID
	 private String stRegulatorStartTime;//`ST_REGULATOR_START_TIME` datetime DEFAULT NULL COMMENT '强度试验稳压起始日期',
	 private String stRegulatorEndTime;//`ST_REGULATOR_END_TIME` datetime DEFAULT NULL COMMENT '强度试验稳压截止日期',
	 private String stInspectMethod; //`ST_INSPECT_METHOD` varchar(4) DEFAULT NULL COMMENT '强度试验检测方法',
	 private String stResult;//`ST_RESULT` varchar(100) DEFAULT NULL COMMENT '强度试验试验结果',
	 private String gasTtemperature;// `GAST_TEMPERATURE` varchar(30) DEFAULT NULL COMMENT '气密性试验环境温度',
	 private Date gastDate;// `GAST_DATE` datetime DEFAULT NULL COMMENT '强度试验日期',
	 private String gastRegulatorStartTime;// `GAST_REGULATOR_START_TIME` datetime DEFAULT NULL COMMENT '气密性试验稳压起始时间',
	 private String gastRegulatorEndTime;// `GAST_REGULATOR_END_TIME` datetime DEFAULT NULL COMMENT '气密性试验稳压截止时间',
	 private String gastInspectMethod;//`GAST_INSPECT_METHOD` varchar(255) DEFAULT NULL COMMENT '气密性试验检测方法',
	 private String gastResult;// `GAST_RESULT` varchar(255) DEFAULT NULL COMMENT '气密性试验结果',
	 private String gastActualStartTime;// `GAST_ACTUAL__START_TIME` datetime DEFAULT NULL COMMENT '气密性实际稳压截止时间',
	 private String gastActualEndTime;// `GAST_ACTUAL__END_TIME` datetime DEFAULT NULL COMMENT '气密性实际稳压截止时间',
	 private String gastActualPressure;//气密性实际压力
	 private String stPipeType;// `ST_PIPE_TYPE` varchar(2) DEFAULT NULL COMMENT '管道类型:1-民用,2-干线、改管、庭院、公建',
	 private String stInstruction;//强度试验试验说明
	 private Integer version;//版本控制
	 private String stActualPressureEnd;//强度试验实际压力止
	 private String gastActualPressureEnd;//气密性试验实际压力止
	 private String gastInstruction;//气密性试验试验说明

	private String isFinishSign;//是否完成签字 1是，0否
	 public StrengthTest() {
		super();
	}
	 
	 @Id
	 @Column(name="ST_ID" , unique = true, nullable = false)
	public String getStId() {
		return stId;
	}
	public void setStId(String stId) {
		this.stId = stId;
	}
	
	@Column(name="PC_ID")
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	@Column(name="ST_PRESSURE")
	public String getStPressure() {
		return stPressure;
	}
	public void setStPressure(String stPressure) {
		this.stPressure = stPressure;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="ST_DATE")
	public Date getStDate() {
		return stDate;
	}
	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}
	
	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name="RISER_NO")
	public String getRiserNo() {
		return riserNo;
	}

	public void setRiserNo(String riserNo) {
		this.riserNo = riserNo;
	}

	@Column(name="ST_RANGE")
	public String getStRange() {
		return stRange;
	}

	public void setStRange(String stRange) {
		this.stRange = stRange;
	}

	@Column(name="GAST_RANGE")
	public String getGasTRange() {
		return gasTRange;
	}

	public void setGasTRange(String gasTRange) {
		this.gasTRange = gasTRange;
	}

	@Column(name="GAST_PRESSURE")
	public String getGasTPressure() {
		return gasTPressure;
	}

	public void setGasTPressure(String gasTPressure) {
		this.gasTPressure = gasTPressure;
	}

	@Column(name="ST_ACTUAL_PRESSURE")
	public String getStActualPressure() {
		return stActualPressure;
	}

	public void setStActualPressure(String stActualPressure) {
		this.stActualPressure = stActualPressure;
	}

	@Column(name="ST_ACTUAL__START_TIME")
	public String getStActualStartTime() {
		return stActualStartTime;
	}

	public void setStActualStartTime(String stActualStartTime) {
		this.stActualStartTime = stActualStartTime;
	}

	@Column(name="ST_ACTUAL__END_TIME")
	public String getStActualEndTime() {
		return stActualEndTime;
	}

	public void setStActualEndTime(String stActualEndTime) {
		this.stActualEndTime = stActualEndTime;
	}

	@Column(name="ST_REGULATOR_START_TIME")
	public String getStRegulatorStartTime() {
		return stRegulatorStartTime;
	}

	public void setStRegulatorStartTime(String stRegulatorStartTime) {
		this.stRegulatorStartTime = stRegulatorStartTime;
	}

	@Column(name="ST_REGULATOR_END_TIME")
	public String getStRegulatorEndTime() {
		return stRegulatorEndTime;
	}

	public void setStRegulatorEndTime(String stRegulatorEndTime) {
		this.stRegulatorEndTime = stRegulatorEndTime;
	}

	@Column(name="ST_INSPECT_METHOD")
	public String getStInspectMethod() {
		return stInspectMethod;
	}

	public void setStInspectMethod(String stInspectMethod) {
		this.stInspectMethod = stInspectMethod;
	}

	@Column(name="ST_RESULT")
	public String getStResult() {
		return stResult;
	}

	public void setStResult(String stResult) {
		this.stResult = stResult;
	}

	@Column(name="GAST_TEMPERATURE")
	public String getGasTtemperature() {
		return gasTtemperature;
	}

	public void setGasTtemperature(String gasTtemperature) {
		this.gasTtemperature = gasTtemperature;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="GAST_DATE")
	public Date getGastDate() {
		return gastDate;
	}

	public void setGastDate(Date gastDate) {
		this.gastDate = gastDate;
	}

	@Column(name="GAST_REGULATOR_START_TIME")
	public String getGastRegulatorStartTime() {
		return gastRegulatorStartTime;
	}

	public void setGastRegulatorStartTime(String gastRegulatorStartTime) {
		this.gastRegulatorStartTime = gastRegulatorStartTime;
	}

	@Column(name="GAST_REGULATOR_END_TIME")
	public String getGastRegulatorEndTime() {
		return gastRegulatorEndTime;
	}

	public void setGastRegulatorEndTime(String gastRegulatorEndTime) {
		this.gastRegulatorEndTime = gastRegulatorEndTime;
	}

	@Column(name="GAST_INSPECT_METHOD")
	public String getGastInspectMethod() {
		return gastInspectMethod;
	}

	public void setGastInspectMethod(String gastInspectMethod) {
		this.gastInspectMethod = gastInspectMethod;
	}

	@Column(name="GAST_RESULT")
	public String getGastResult() {
		return gastResult;
	}

	public void setGastResult(String gastResult) {
		this.gastResult = gastResult;
	}

	@Column(name="GAST_ACTUAL__START_TIME")
	public String getGastActualStartTime() {
		return gastActualStartTime;
	}

	public void setGastActualStartTime(String gastActualStartTime) {
		this.gastActualStartTime = gastActualStartTime;
	}

	@Column(name="GAST_ACTUAL__END_TIME")
	public String getGastActualEndTime() {
		return gastActualEndTime;
	}

	public void setGastActualEndTime(String gastActualEndTime) {
		this.gastActualEndTime = gastActualEndTime;
	}

	@Column(name="ST_PIPE_TYPE")
	public String getStPipeType() {
		return stPipeType;
	}

	public void setStPipeType(String stPipeType) {
		this.stPipeType = stPipeType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="TEST_DATE")
	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	@Column(name="TEST_RESULT")
	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	@Column(name="TEST_INSPECT_METHOD")
	public String getTestMethod() {
		return testMethod;
	}

	public void setTestMethod(String testMethod) {
		this.testMethod = testMethod;
	}

	@Column(name="GAST_ACTUAL_PRESSURE")
	public String getGastActualPressure() {
		return gastActualPressure;
	}

	public void setGastActualPressure(String gastActualPressure) {
		this.gastActualPressure = gastActualPressure;
	}

	@Column(name="ST_INSTRUCTION")
	public String getStInstruction() {
		return stInstruction;
	}

	public void setStInstruction(String stInstruction) {
		this.stInstruction = stInstruction;
	}
	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name="ST_ACTUAL_PRESSURE_END")
	public String getStActualPressureEnd() {
		return stActualPressureEnd;
	}

	public void setStActualPressureEnd(String stActualPressureEnd) {
		this.stActualPressureEnd = stActualPressureEnd;
	}

	@Column(name="GAST_ACTUAL_PRESSURE_END")
	public String getGastActualPressureEnd() {
		return gastActualPressureEnd;
	}

	public void setGastActualPressureEnd(String gastActualPressureEnd) {
		this.gastActualPressureEnd = gastActualPressureEnd;
	}

	@Column(name="GAST_INSTRUCTION")
	public String getGastInstruction() {
		return gastInstruction;
	}

	public void setGastInstruction(String gastInstruction) {
		this.gastInstruction = gastInstruction;
	}
	@Column(name="IS_FINISH_SIGN")
	public String getIsFinishSign() {
		return isFinishSign;
	}

	public void setIsFinishSign(String isFinishSign) {
		this.isFinishSign = isFinishSign;
	}
}
