package cc.dfsoft.project.biz.base.inspection.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Pressure entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PRESSURE")
public class Pressure implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3505895702969505183L;
	private String pressureId;		//试验记录ID
	private String pcId;			//报验单ID
	private String pressureStarteEnd;//管线起止点
	private String designPressure;	//设计压力
	private String designMedium;	//设计介质
	private String strengthPa;		//强度试验压力
	private String strengthMedium;	//强度试验介质
	private String strengthTime;	//强度试验时间
	private String strengthEndTime;	//强度试验结束时间
	private String strengthResult;	//强度试验结论
	private String rigorPa;			//严密性试验压力
	private String rigorMedium;		//严密性试验介质
	private String rigorTime;		//严密性试验时间
	private String rigorEndTime;		//严密性试验结束时间
	private String rigorResult;		//严密性试验结论
	
	// Constructors

	/** default constructor */
	public Pressure() {
	}

	// Property accessors
	@Id
	@Column(name = "PRESSURE_ID", unique = true)
	public String getPressureId() {
		return this.pressureId;
	}

	public void setPressureId(String pressureId) {
		this.pressureId = pressureId;
	}

	@Column(name = "PC_ID")
	public String getPcId() {
		return this.pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name = "PRESSURE_STARTE_END")
	public String getPressureStarteEnd() {
		return this.pressureStarteEnd;
	}

	public void setPressureStarteEnd(String pressureStarteEnd) {
		this.pressureStarteEnd = pressureStarteEnd;
	}

	@Column(name = "DESIGN_PRESSURE")
	public String getDesignPressure() {
		return this.designPressure;
	}

	public void setDesignPressure(String designPressure) {
		this.designPressure = designPressure;
	}

	@Column(name = "DESIGN_MEDIUM")
	public String getDesignMedium() {
		return this.designMedium;
	}

	public void setDesignMedium(String designMedium) {
		this.designMedium = designMedium;
	}

	@Column(name = "STRENGTH_PA")
	public String getStrengthPa() {
		return this.strengthPa;
	}

	public void setStrengthPa(String strengthPa) {
		this.strengthPa = strengthPa;
	}

	@Column(name = "STRENGTH_MEDIUM")
	public String getStrengthMedium() {
		return this.strengthMedium;
	}

	public void setStrengthMedium(String strengthMedium) {
		this.strengthMedium = strengthMedium;
	}

	@Column(name = "STRENGTH_TIME")
	public String getStrengthTime() {
		return this.strengthTime;
	}

	public void setStrengthTime(String strengthTime) {
		this.strengthTime = strengthTime;
	}

	@Column(name = "STRENGTH_RESULT")
	public String getStrengthResult() {
		return this.strengthResult;
	}

	public void setStrengthResult(String strengthResult) {
		this.strengthResult = strengthResult;
	}

	@Column(name = "RIGOR_PA")
	public String getRigorPa() {
		return this.rigorPa;
	}

	public void setRigorPa(String rigorPa) {
		this.rigorPa = rigorPa;
	}

	@Column(name = "RIGOR_MEDIUM")
	public String getRigorMedium() {
		return this.rigorMedium;
	}

	public void setRigorMedium(String rigorMedium) {
		this.rigorMedium = rigorMedium;
	}

	@Column(name = "RIGOR_TIME")
	public String getRigorTime() {
		return this.rigorTime;
	}

	public void setRigorTime(String rigorTime) {
		this.rigorTime = rigorTime;
	}

	@Column(name = "RIGOR_RESULT")
	public String getRigorResult() {
		return this.rigorResult;
	}

	public void setRigorResult(String rigorResult) {
		this.rigorResult = rigorResult;
	}
	
	@Column(name = "STRENGTH_END_TIME")
	public String getStrengthEndTime() {
		return strengthEndTime;
	}

	public void setStrengthEndTime(String strengthEndTime) {
		this.strengthEndTime = strengthEndTime;
	}
	
	@Column(name = "RIGOR_END_TIME")
	public String getRigorEndTime() {
		return rigorEndTime;
	}

	public void setRigorEndTime(String rigorEndTime) {
		this.rigorEndTime = rigorEndTime;
	}

}