package cc.dfsoft.project.biz.base.inspection.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
/**
 * 安装汇总记录实体类 
 * @author liaoyq
 *
 */
@Entity
@Table(name="INSTALL_SUMMARY")
public class InstallSummary implements Serializable {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String isId;			//`IS_ID` varchar(30) NOT NULL COMMENT '压力管道安装汇总表记录ID',
	private String pcId;			//`PC_ID` varchar(30) NOT NULL COMMENT '报验单ID',
	private String isPipeLineNo;	//`PIPE_LINE_NO` varchar(30) DEFAULT NULL COMMENT '管线号',
	private String isPipingLevel;	// `PIPING_LEVEL` varchar(30) DEFAULT NULL COMMENT '管道级别',
	private String designPressure;	//`DESIGN_PRESSURE` varchar(30) DEFAULT NULL COMMENT '设计压力',
	private String designTemperature;// `DESIGN_TEMPERATURE` varchar(30) DEFAULT NULL COMMENT '设计温度',
	private String pumpedMedium;	//`PUMPED_MEDIUM` varchar(50) DEFAULT NULL COMMENT '输送介质',
	private String pipingMaterial;	//`PIPING_MATERIAL` varchar(50) DEFAULT NULL COMMENT '管道材质',
	private String pipingSpec;		//`PIPING_SPEC` varchar(50) DEFAULT NULL COMMENT '管道规格',
    private String isPipingLen;		//`PIPING_LEN` varchar(30) DEFAULT NULL COMMENT '管道长度',
	private String layingMethod;	//`LAYING_METHOD` varchar(50) DEFAULT NULL COMMENT '敷设方式',
	private String weldsNum;		//`WELDS_NUM` varchar(30) DEFAULT NULL COMMENT '焊口数量',
	private String testMethod;		//`TEST_METHOD` varchar(50) DEFAULT NULL COMMENT '检测方法',
	private String pressureMedium;	//`PRESSURE_MEDIUM` varchar(50) DEFAULT NULL COMMENT '耐压试验介质',
	private String pressure;		//`PRESSURE` varchar(30) DEFAULT NULL COMMENT '压力试验压力',
	private String leakagePressure;	//`LEAKGE_PRESSURE` varchar(30) DEFAULT NULL COMMENT '泄漏试验压力',
	private String blowMedium;		//`BLOW_MEDIUM` varchar(50) DEFAULT NULL COMMENT '吹除介质',
	private String preservativeMethod;// `PRESERVATIVE_METHOD` varchar(50) DEFAULT NULL COMMENT '防腐方式',
    private String insulationWay;	//`INSULATION_WAY` varchar(50) DEFAULT NULL COMMENT '保温绝热方式',
	private String projId;			//工程ID
	private Integer version;		//版本控制
	public InstallSummary() {
		super();
	}

	@Id
	@Column(name="IS_ID")
	public String getIsId() {
		return isId;
	}


	public void setIsId(String isId) {
		this.isId = isId;
	}


	@Column(name="PC_ID")
	public String getPcId() {
		return pcId;
	}


	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name="PIPE_LINE_NO")
	public String getIsPipeLineNo() {
		return isPipeLineNo;
	}


	public void setIsPipeLineNo(String isPipeLineNo) {
		this.isPipeLineNo = isPipeLineNo;
	}

	@Column(name="PIPING_lEVEL")
	public String getIsPipingLevel() {
		return isPipingLevel;
	}


	public void setIsPipingLevel(String isPipingLevel) {
		this.isPipingLevel = isPipingLevel;
	}

	@Column(name="DESIGN_PRESSURE")
	public String getDesignPressure() {
		return designPressure;
	}


	public void setDesignPressure(String designPressure) {
		this.designPressure = designPressure;
	}

	@Column(name="DESIGN_TEMPERATURE")
	public String getDesignTemperature() {
		return designTemperature;
	}


	public void setDesignTemperature(String designTemperature) {
		this.designTemperature = designTemperature;
	}

	@Column(name="PUMPED_MEDIUM")
	public String getPumpedMedium() {
		return pumpedMedium;
	}


	public void setPumpedMedium(String pumpedMedium) {
		this.pumpedMedium = pumpedMedium;
	}

	@Column(name="PIPING_MATERIAL")
	public String getPipingMaterial() {
		return pipingMaterial;
	}


	public void setPipingMaterial(String pipingMaterial) {
		this.pipingMaterial = pipingMaterial;
	}

	@Column(name="PIPING_SPEC")
	public String getPipingSpec() {
		return pipingSpec;
	}


	public void setPipingSpec(String pipingSpec) {
		this.pipingSpec = pipingSpec;
	}

	@Column(name="PIPING_LEN")
	public String getIsPipingLen() {
		return isPipingLen;
	}


	public void setIsPipingLen(String isPipingLen) {
		this.isPipingLen = isPipingLen;
	}

	@Column(name="LAYING_METHOD")
	public String getLayingMethod() {
		return layingMethod;
	}


	public void setLayingMethod(String layingMethod) {
		this.layingMethod = layingMethod;
	}

	@Column(name="WELDS_NUM")
	public String getWeldsNum() {
		return weldsNum;
	}


	public void setWeldsNum(String weldsNum) {
		this.weldsNum = weldsNum;
	}

	@Column(name="TEST_METHOD")
	public String getTestMethod() {
		return testMethod;
	}


	public void setTestMethod(String testMethod) {
		this.testMethod = testMethod;
	}

	@Column(name="PRESSURE_MEDIUM")
	public String getPressureMedium() {
		return pressureMedium;
	}


	public void setPressureMedium(String pressureMedium) {
		this.pressureMedium = pressureMedium;
	}

	@Column(name="PRESSURE")
	public String getPressure() {
		return pressure;
	}


	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	@Column(name="LEAKGE_PRESSURE")
	public String getLeakagePressure() {
		return leakagePressure;
	}


	public void setLeakagePressure(String leakagePressure) {
		this.leakagePressure = leakagePressure;
	}

	@Column(name="BLOW_MEDIUM")
	public String getBlowMedium() {
		return blowMedium;
	}


	public void setBlowMedium(String blowMedium) {
		this.blowMedium = blowMedium;
	}

	@Column(name="PRESERVATIVE_METHOD")
	public String getPreservativeMethod() {
		return preservativeMethod;
	}


	public void setPreservativeMethod(String preservativeMethod) {
		this.preservativeMethod = preservativeMethod;
	}

	@Column(name="INSULATION_WAY")
	public String getInsulationWay() {
		return insulationWay;
	}


	public void setInsulationWay(String insulationWay) {
		this.insulationWay = insulationWay;
	}

	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
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
