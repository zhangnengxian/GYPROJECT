package cc.dfsoft.project.biz.base.inspection.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AltimetricSurvey entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ALTIMETRIC_SURVEY")
public class AltimetricSurvey implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5954773964000146656L;
	private String asId;			//高程测量记录ID
	private String pcId;			//报验单ID
	private String pcStation;		//桩号（标号）
	private String pcOldElevation;	//原地面标高
	private String pcNewElevation;	//现地面标高
	private String pcFootElevation;	//沟底标高
	private String pcDepth;			//埋深

	private String pressureType;//管道压力类型
	
	// Constructors

	/** default constructor */
	public AltimetricSurvey() {
	}

	// Property accessors
	@Id
	@Column(name = "AS_ID", unique = true)
	public String getAsId() {
		return this.asId;
	}

	public void setAsId(String asId) {
		this.asId = asId;
	}

	@Column(name = "PC_ID")
	public String getPcId() {
		return this.pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name = "PC_STATION")
	public String getPcStation() {
		return this.pcStation;
	}

	public void setPcStation(String pcStation) {
		this.pcStation = pcStation;
	}

	@Column(name = "PC_OLD_ELEVATION")
	public String getPcOldElevation() {
		return this.pcOldElevation;
	}

	public void setPcOldElevation(String pcOldElevation) {
		this.pcOldElevation = pcOldElevation;
	}

	@Column(name = "PC_NEW_ELEVATION")
	public String getPcNewElevation() {
		return this.pcNewElevation;
	}

	public void setPcNewElevation(String pcNewElevation) {
		this.pcNewElevation = pcNewElevation;
	}

	@Column(name = "PC_FOOT_ELEVATION")
	public String getPcFootElevation() {
		return this.pcFootElevation;
	}

	public void setPcFootElevation(String pcFootElevation) {
		this.pcFootElevation = pcFootElevation;
	}

	@Column(name = "PC_DEPTH")
	public String getPcDepth() {
		return this.pcDepth;
	}

	public void setPcDepth(String pcDepth) {
		this.pcDepth = pcDepth;
	}
	@Column(name = "PRESSURE_TYPE")
	public String getPressureType() {
		return pressureType;
	}

	public void setPressureType(String pressureType) {
		this.pressureType = pressureType;
	}
	
	
	
}