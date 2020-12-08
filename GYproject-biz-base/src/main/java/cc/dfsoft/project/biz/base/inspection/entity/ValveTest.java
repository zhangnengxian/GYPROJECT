package cc.dfsoft.project.biz.base.inspection.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
/**
 * 埋地检查实体类
 * @author liaoyq
 *
 */

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="VALVE_TEST")
public class ValveTest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String vtId;			//`VT_ID` varchar(30) NOT NULL COMMENT '阀门试验记录ID',
	private String pcId;			//  `PC_ID` varchar(30) NOT NULL COMMENT '报验单ID',
	private String valveName;		//  `VALVE_NAME` varchar(200) DEFAULT NULL COMMENT '名称',
	private String typeSpecification;//  `TYPE_SPECIFICATION` varchar(50) DEFAULT NULL COMMENT '型号规格',
	private String filler;			//  `FILLER` varchar(50) DEFAULT NULL COMMENT '填料',
	private String number;			// `NUMBER` varchar(30) DEFAULT NULL COMMENT '数量',
	private String nominalPressure;	//  `NOMINAL_PRESSURE` varchar(50) DEFAULT NULL COMMENT '公称压力',
	private String strengthMedium;	//  `STRENGTH_MEDIUM` varchar(50) DEFAULT NULL COMMENT '强度试验介质',
	private String strengthPressure;//  `STRENGTH_PRESSURE` varchar(30) DEFAULT NULL COMMENT '强度试验时压力',
	private String strengthTime;	// `STRENGTH_TIME` varchar(30) DEFAULT NULL COMMENT '强度试验时间(分钟)',
	private String tightnessMedium;	//  `TIGHTNESS_MEDIUM` varchar(50) DEFAULT NULL COMMENT '严密性试验介质',
	private String tightnessPressure;//  `TIGHTNESS_PRESSURE` varchar(30) DEFAULT NULL COMMENT '严密性试验压力',
	private String tightnessTime;	// `TIGHTNESS_TIME` varchar(30) DEFAULT NULL COMMENT '严密性试验时间',
	private String visualInspcet;	//  `VISUAL_INSPECT` varchar(200) DEFAULT NULL COMMENT '试验情况外观检查',
	private String projId;			//工程ID
	private Integer version;		//版本控制
	public ValveTest() {
		super();
	}

	@Id
	@Column(name="VT_ID")
	public String getVtId() {
		return vtId;
	}

	public void setVtId(String vtId) {
		this.vtId = vtId;
	}
	@Column(name="PC_ID")
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name="VALVE_NAME")
	public String getValveName() {
		return valveName;
	}

	public void setValveName(String valveName) {
		this.valveName = valveName;
	}

	@Column(name="TYPE_SPECIFICATION")
	public String getTypeSpecification() {
		return typeSpecification;
	}

	public void setTypeSpecification(String typeSpecification) {
		this.typeSpecification = typeSpecification;
	}

	@Column(name="FILLER")
	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	@Column(name="NUMBER")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name="NOMINAL_PRESSURE")
	public String getNominalPressure() {
		return nominalPressure;
	}

	public void setNominalPressure(String nominalPressure) {
		this.nominalPressure = nominalPressure;
	}

	@Column(name="STRENGTH_MEDIUM")
	public String getStrengthMedium() {
		return strengthMedium;
	}

	public void setStrengthMedium(String strengthMedium) {
		this.strengthMedium = strengthMedium;
	}

	@Column(name="STRENGTH_PRESSURE")
	public String getStrengthPressure() {
		return strengthPressure;
	}

	public void setStrengthPressure(String strengthPressure) {
		this.strengthPressure = strengthPressure;
	}

	@Column(name="STRENGTH_TIME")
	public String getStrengthTime() {
		return strengthTime;
	}

	public void setStrengthTime(String strengthTime) {
		this.strengthTime = strengthTime;
	}

	@Column(name="TIGHTNESS_MEDIUM")
	public String getTightnessMedium() {
		return tightnessMedium;
	}

	public void setTightnessMedium(String tightnessMedium) {
		this.tightnessMedium = tightnessMedium;
	}

	@Column(name="TIGHTNESS_PRESSURE")
	public String getTightnessPressure() {
		return tightnessPressure;
	}

	public void setTightnessPressure(String tightnessPressure) {
		this.tightnessPressure = tightnessPressure;
	}

	@Column(name="TIGHTNESS_TIME")
	public String getTightnessTime() {
		return tightnessTime;
	}

	public void setTightnessTime(String tightnessTime) {
		this.tightnessTime = tightnessTime;
	}

	@Column(name="VISUAL_INSPECT")
	public String getVisualInspcet() {
		return visualInspcet;
	}

	public void setVisualInspcet(String visualInspcet) {
		this.visualInspcet = visualInspcet;
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
