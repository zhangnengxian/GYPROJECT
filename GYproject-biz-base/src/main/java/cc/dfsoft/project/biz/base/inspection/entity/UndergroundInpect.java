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
@Table(name="UNDERGROUND_INPECT")
public class UndergroundInpect implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ugiId;		//  `UGI_ID` varchar(30) NOT NULL COMMENT '埋地检查记录ID',
	private String pcId;		// `PC_ID` varchar(30) NOT NULL COMMENT '报验单ID',
	private String pipeNo;		//  `PIPE_NO` varchar(30) DEFAULT NULL COMMENT '管道编号',
	private String pileNo;		//  `PILE_NO` varchar(30) DEFAULT NULL COMMENT '桩号',
	private String topElevation;//  `TOP_ELEVATION` varchar(30) DEFAULT NULL COMMENT '管顶标高',
	private String soilThick;	// `SOIL_THICK` varchar(30) DEFAULT NULL COMMENT '复土厚度',
	private Integer isDamage;	//  `IS_DAMAGE` int(1) DEFAULT NULL COMMENT '防腐层损坏:0-否，1-是',
	private String damageNum;	//  `DAMAGE_NUM` varchar(20) DEFAULT NULL COMMENT '损害处',
	private String damageArea;	//  `DAMAGE_AREA` varchar(20) DEFAULT NULL COMMENT '损坏面积',
	private String repairNum;	//  `REPAIR_NUM` varchar(20) DEFAULT NULL COMMENT '防腐层修补处',
	private String repairArea;	//  `REPAIR_AREA` varchar(20) DEFAULT NULL COMMENT '修补面积',
	private String projId;		//工程ID
	private String cuDate;		//施工时间
	private Integer version;	//版本控制
	public UndergroundInpect() {
		super();
	}

	@Id
	@Column(name="UGI_ID")
	public String getUgiId() {
		return ugiId;
	}

	public void setUgiId(String ugiId) {
		this.ugiId = ugiId;
	}
	
	@Column(name="PC_ID")
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name="PIPE_NO")
	public String getPipeNo() {
		return pipeNo;
	}

	public void setPipeNo(String pipeNo) {
		this.pipeNo = pipeNo;
	}

	@Column(name="PILE_NO")
	public String getPileNo() {
		return pileNo;
	}

	public void setPileNo(String pileNo) {
		this.pileNo = pileNo;
	}

	@Column(name="TOP_ELEVATION")
	public String getTopElevation() {
		return topElevation;
	}

	public void setTopElevation(String topElevation) {
		this.topElevation = topElevation;
	}

	@Column(name="SOIL_THICK")
	public String getSoilThick() {
		return soilThick;
	}

	public void setSoilThick(String soilThick) {
		this.soilThick = soilThick;
	}

	@Column(name="IS_DAMAGE")
	public Integer getIsDamage() {
		return isDamage;
	}

	public void setIsDamage(Integer isDamage) {
		this.isDamage = isDamage;
	}
	
	@Column(name="DAMAGE_NUM")
	public String getDamageNum() {
		return damageNum;
	}

	public void setDamageNum(String damageNum) {
		this.damageNum = damageNum;
	}

	@Column(name="DAMAGE_AREA")
	public String getDamageArea() {
		return damageArea;
	}

	public void setDamageArea(String damageArea) {
		this.damageArea = damageArea;
	}

	@Column(name="REPAIR_NUM")
	public String getRepairNum() {
		return repairNum;
	}

	public void setRepairNum(String repairNum) {
		this.repairNum = repairNum;
	}

	@Column(name="REPAIR_AREA")
	public String getRepairArea() {
		return repairArea;
	}

	public void setRepairArea(String repairArea) {
		this.repairArea = repairArea;
	}

	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name="CU_DATE")
	public String getCuDate() {
		return cuDate;
	}

	public void setCuDate(String cuDate) {
		this.cuDate = cuDate;
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
