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
 * 设备安装实体类
 * @author liaoyq
 *
 */
@Entity
@Table(name="EQUIPMENT_INSTALL")
public class EquipmentInstall implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String eiId;			// `EI_ID` varchar(30) NOT NULL COMMENT '设备安装记录ID',
	private String pcId;			//  `PC_ID` varchar(30) NOT NULL COMMENT '报验单ID',
	private String pipeSectionNo;	//  `PIPE_SECTION_NO` varchar(30) DEFAULT NULL COMMENT '管段编号',
	private String pipingSpec;		//  `PIPING_SPEC` varchar(30) DEFAULT NULL COMMENT '管道规格',
	private String pipeLen;			//  `PIPE_LEN` varchar(30) DEFAULT NULL COMMENT '长度(M)',
	private String installPosition;	//  `INSTALL_POSITION` varchar(50) DEFAULT NULL COMMENT '安装位置(桩号)',
	private String pipeTopLevel;	// `PIPE_TOP_LEVEL` varchar(30) DEFAULT NULL COMMENT '管顶标高',
	private String pipeSlope;		//  `PIPE_SLOPE` varchar(30) DEFAULT NULL COMMENT '坡度',
	private String buriedDepth;		//  `BURIED_DEPTH` varchar(30) DEFAULT NULL COMMENT '埋深',
	private String equipmentName;	//  `EQUIPMENT_NAME` varchar(50) DEFAULT NULL COMMENT '设备名称',
	private String equipmentSpec;	//  `EQUIPMENT_SPEC` varchar(50) DEFAULT NULL COMMENT '设备规格',
	private String equipmentPileNo;	//  `EQUIPMENT_PILE_NO` varchar(30) DEFAULT NULL COMMENT '设备位置桩号',
	private String equipmentBuriedDepth;//  `EQUIPMENT_BURIED_DEPTH` varchar(30) DEFAULT NULL COMMENT '设备埋深',
	private Date installDate;		//  `INSTALL_DATE` datetime DEFAULT NULL COMMENT '安装日期',
	private String projId;			//工程ID
	private Integer version;		//版本控制
	public EquipmentInstall() {
		super();
	}
	
	@Id
	@Column(name="EI_ID",unique = true, nullable =false)
	public String getEiId() {
		return eiId;
	}
	public void setEiId(String eiId) {
		this.eiId = eiId;
	}
	@Column(name="PC_ID")
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	
	@Column(name="PIPE_SECTION_NO")
	public String getPipeSectionNo() {
		return pipeSectionNo;
	}
	public void setPipeSectionNo(String pipeSectionNo) {
		this.pipeSectionNo = pipeSectionNo;
	}
	
	@Column(name="PIPING_SPEC")
	public String getPipingSpec() {
		return pipingSpec;
	}
	public void setPipingSpec(String pipingSpec) {
		this.pipingSpec = pipingSpec;
	}
	
	@Column(name="PIPE_LEN")
	public String getPipeLen() {
		return pipeLen;
	}
	public void setPipeLen(String pipeLen) {
		this.pipeLen = pipeLen;
	}
	
	@Column(name="INSTALL_POSITION")
	public String getInstallPosition() {
		return installPosition;
	}
	public void setInstallPosition(String installPosition) {
		this.installPosition = installPosition;
	}
	
	@Column(name="PIPE_TOP_LEVEL")
	public String getPipeTopLevel() {
		return pipeTopLevel;
	}
	public void setPipeTopLevel(String pipeTopLevel) {
		this.pipeTopLevel = pipeTopLevel;
	}
	
	@Column(name="PIPE_SLOPE")
	public String getPipeSlope() {
		return pipeSlope;
	}
	public void setPipeSlope(String pipeSlope) {
		this.pipeSlope = pipeSlope;
	}
	
	@Column(name="BURIED_DEPTH")
	public String getBuriedDepth() {
		return buriedDepth;
	}
	public void setBuriedDepth(String buriedDepth) {
		this.buriedDepth = buriedDepth;
	}
	
	@Column(name="EQUIPMENT_NAME")
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
	@Column(name="EQUIPMENT_SPEC")
	public String getEquipmentSpec() {
		return equipmentSpec;
	}
	public void setEquipmentSpec(String equipmentSpec) {
		this.equipmentSpec = equipmentSpec;
	}
	@Column(name="EQUIPMENT_PILE_NO")
	public String getEquipmentPileNo() {
		return equipmentPileNo;
	}
	public void setEquipmentPileNo(String equipmentPileNo) {
		this.equipmentPileNo = equipmentPileNo;
	}
	
	@Column(name="EQUIPMENT_BURIED_DEPTH")
	public String getEquipmentBuriedDepth() {
		return equipmentBuriedDepth;
	}
	public void setEquipmentBuriedDepth(String equipmentBuriedDepth) {
		this.equipmentBuriedDepth = equipmentBuriedDepth;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="INSTALL_DATE")
	public Date getInstallDate() {
		return installDate;
	}
	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
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
