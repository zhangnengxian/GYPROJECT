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
 * 阳极安装实体类
 * @author liaoyq
 *
 */
@Entity
@Table(name="ANODE_INSTALL")
public class AnodeInstall implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String aiId;			//`AI_ID` varchar(30) NOT NULL COMMENT '阳极安装记录ID',
	private String pcId;			//`PC_ID` varchar(30) NOT NULL COMMENT '报验单ID',
	private String buriedPileNo;	//`BURIED_PILE_NO` varchar(30) DEFAULT NULL COMMENT '埋地桩号',
	private String wallSpacing;		//  `WALL_SPACING` varchar(20) DEFAULT NULL COMMENT '离管壁间距',
	private String soilResistance;	//  `SOIL_RESISTANCE` varchar(20) DEFAULT NULL COMMENT '土质电阻',
	private String anodeSpacing;	// `ANODE_SPACING` varchar(20) DEFAULT NULL COMMENT '阳极间距离',
	private String workPotential;	//  `WORK_POTENTIAL` varchar(20) DEFAULT NULL COMMENT '工作电位',
	private Date installDate;		//  `INSTALL_DATE` datetime DEFAULT NULL COMMENT '日期',
	private String buriedDepth;		//`BURIED_DEPTH` varchar(20) DEFAULT NULL COMMENT '埋深',
	private String projId;			//工程ID
	private Integer version;		//版本控制
	public AnodeInstall() {
		super();
	}

	@Id
	@Column(name="AI_ID",unique=true,nullable = false)
	public String getAiId() {
		return aiId;
	}


	public void setAiId(String aiId) {
		this.aiId = aiId;
	}

	@Column(name="PC_ID")
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name="BURIED_PILE_NO")
	public String getBuriedPileNo() {
		return buriedPileNo;
	}

	public void setBuriedPileNo(String buriedPileNo) {
		this.buriedPileNo = buriedPileNo;
	}

	@Column(name="WALL_SPACING")
	public String getWallSpacing() {
		return wallSpacing;
	}

	public void setWallSpacing(String wallSpacing) {
		this.wallSpacing = wallSpacing;
	}

	@Column(name="SOIL_RESISTANCE")
	public String getSoilResistance() {
		return soilResistance;
	}

	public void setSoilResistance(String soilResistance) {
		this.soilResistance = soilResistance;
	}

	@Column(name="ANODE_SPACING")
	public String getAnodeSpacing() {
		return anodeSpacing;
	}

	public void setAnodeSpacing(String anodeSpacing) {
		this.anodeSpacing = anodeSpacing;
	}

	@Column(name="WORK_POTENTIAL")
	public String getWorkPotential() {
		return workPotential;
	}

	public void setWorkPotential(String workPotential) {
		this.workPotential = workPotential;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="INSTALL_DATE")
	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	@Column(name="BURIED_DEPTH")
	public String getBuriedDepth() {
		return buriedDepth;
	}

	public void setBuriedDepth(String buriedDepth) {
		this.buriedDepth = buriedDepth;
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
