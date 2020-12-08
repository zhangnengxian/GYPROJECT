package cc.dfsoft.project.biz.base.inspection.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * HiddenWorks entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HIDDEN_WORKS")
public class HiddenWorks implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private String hwId;		// `HW_ID` varchar(30) NOT NULL COMMENT '隐蔽工程ID',
	 private String projId;		// `PROJ_ID` varchar(50) NOT NULL COMMENT '工程ID',
	 private String pcId;		// `PC_ID` varchar(30) DEFAULT NULL COMMENT '报验单ID',
	 private String pipeSectionNo;	// `PIPE_SECTION_NO` varchar(50) DEFAULT NULL COMMENT '管段编号',
	 private String pileNo;		// `PILE_NO` varchar(20) DEFAULT NULL COMMENT '桩号',
	 private String hwSpec;		//`HW_SPEC`  varchar(30) NULL DEFAULT NULL COMMENT '规格'
	 private String weldNum;	// `WELD_NUM` varchar(20) DEFAULT NULL COMMENT '焊缝数量',
	 private String material;	// `MATERIAL` varchar(50) DEFAULT NULL COMMENT '材质',
	 private String obstacle;	// `OBSTACLE` varchar(20) DEFAULT NULL COMMENT '障碍物',
	 private String dealSituation;	// `DEAL_SITUATION` varchar(50) DEFAULT NULL COMMENT '处理情况',
	 private Date hwDate;		// `HW_DATE` datetime DEFAULT NULL COMMENT '日期',

	private Integer version;		//版本控制
	// Constructors

	/** default constructor */
	public HiddenWorks() {
	}


	// Property accessors
	@Id
	@Column(name = "HW_ID", unique = true,nullable = false)
	public String getHwId() {
		return this.hwId;
	}

	public void setHwId(String hwId) {
		this.hwId = hwId;
	}

	@Column(name = "PC_ID")
	public String getPcId() {
		return this.pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}


	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}


	public void setProjId(String projId) {
		this.projId = projId;
	}


	@Column(name="PIPE_SECTION_NO")
	public String getPipeSectionNo() {
		return pipeSectionNo;
	}


	public void setPipeSectionNo(String pipeSectionNo) {
		this.pipeSectionNo = pipeSectionNo;
	}


	@Column(name="PILE_NO")
	public String getPileNo() {
		return pileNo;
	}


	public void setPileNo(String pileNo) {
		this.pileNo = pileNo;
	}


	@Column(name="WELD_NUM")
	public String getWeldNum() {
		return weldNum;
	}


	public void setWeldNum(String weldNum) {
		this.weldNum = weldNum;
	}

	@Column(name="MATERIAL")
	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}

	@Column(name="OBSTACLE")
	public String getObstacle() {
		return obstacle;
	}


	public void setObstacle(String obstacle) {
		this.obstacle = obstacle;
	}

	@Column(name="DEAL_SITUATION")
	public String getDealSituation() {
		return dealSituation;
	}


	public void setDealSituation(String dealSituation) {
		this.dealSituation = dealSituation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="HW_DATE")
	public Date getHwDate() {
		return hwDate;
	}


	public void setHwDate(Date hwDate) {
		this.hwDate = hwDate;
	}

	@Column(name="HW_SPEC")
	public String getHwSpec() {
		return hwSpec;
	}


	public void setHwSpec(String hwSpec) {
		this.hwSpec = hwSpec;
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