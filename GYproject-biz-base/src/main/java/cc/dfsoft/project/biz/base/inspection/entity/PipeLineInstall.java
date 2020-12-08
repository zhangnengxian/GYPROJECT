package cc.dfsoft.project.biz.base.inspection.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 管道安装记录实体类
 * @author liaoyq
 *
 */
@Entity
@Table(name="PIPE_LINE_INSTALL")
public class PipeLineInstall implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pliId;		//PLI_ID` varchar(30) NOT NULL COMMENT '管道安装记录表ID',
	private String pcId;		//PC_ID` varchar(30) NOT NULL COMMENT '报验单表ID',
	private String pipeLineNo;		//PIPE_LINE_NO` varchar(30) DEFAULT NULL COMMENT '管线号',
	private String weldLineNo;		//WELD_LINE_NO` varchar(30) DEFAULT NULL COMMENT '焊缝编号',
	private String pipeMaterial;		//PIPE_MATERIAL` varchar(30) DEFAULT NULL COMMENT '管道材质',
	private String bakingNo;		//BAKING_NO` varchar(20) DEFAULT NULL COMMENT '烘烤编号',
	private String weldMethod;		//WELD_METHOD` varchar(20) DEFAULT NULL COMMENT '焊接方法',
	private String weldMaterialNo;		//WELD_MATERIAL_NO` varchar(30) DEFAULT NULL COMMENT '焊接材料牌号',
	private String weldMaterialSize;		//WELD_MATERIAL_SIZE` varchar(30) DEFAULT NULL COMMENT '焊接材料规格',
	private String weldLayer;		//WELD_LAYER` varchar(20) DEFAULT NULL COMMENT '焊接层次',
	private String powerStage;		//POWER_STAGE` varchar(30) DEFAULT NULL COMMENT '电源性级',
	private String weldCurrent;		//WELD_CURRENT` String DEFAULT NULL COMMENT '焊接电流',
	private String arvoltage;		//ARVOLTAGE` String DEFAULT NULL COMMENT '电弧电压',
	private String weldSpeed;		//WELD_SPEED` String DEFAULT NULL COMMENT '焊接速度',
	private String weldTime;		//WELD_TIME` String DEFAULT NULL COMMENT '焊接时间',
	private String isInspect;		//是否探伤
	private Date weldingDate;		//焊接日期

	private String projId;			//工程ID

	private Integer version;		//版本控制

	public PipeLineInstall() {
		super();
	}

	@Id
	@Column(name = "PLI_ID" ,unique = true , nullable = false)
	public String getPliId() {
		return pliId;
	}

	public void setPliId(String pliId) {
		this.pliId = pliId;
	}
    
	@Column(name = "PC_ID")
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name= "PIPE_LINE_NO")
	public String getPipeLineNo() {
		return pipeLineNo;
	}

	public void setPipeLineNo(String pipeLineNo) {
		this.pipeLineNo = pipeLineNo;
	}

	@Column(name = "WELD_LINE_NO")
	public String getWeldLineNo() {
		return weldLineNo;
	}

	public void setWeldLineNo(String weldLineNo) {
		this.weldLineNo = weldLineNo;
	}

	@Column(name= "PIPE_MATERIAL")
	public String getPipeMaterial() {
		return pipeMaterial;
	}

	public void setPipeMaterial(String pipeMaterial) {
		this.pipeMaterial = pipeMaterial;
	}

	@Column(name = "BAKING_NO")
	public String getBakingNo() {
		return bakingNo;
	}

	public void setBakingNo(String bakingNo) {
		this.bakingNo = bakingNo;
	}

	@Column(name = "WELD_METHOD")
	public String getWeldMethod() {
		return weldMethod;
	}

	public void setWeldMethod(String weldMethod) {
		this.weldMethod = weldMethod;
	}

	@Column(name = "WELD_MATERIAL_NO")
	public String getWeldMaterialNo() {
		return weldMaterialNo;
	}

	public void setWeldMaterialNo(String weldMaterialNo) {
		this.weldMaterialNo = weldMaterialNo;
	}

	@Column(name = "WELD_MATERIAL_SIZE")
	public String getWeldMaterialSize() {
		return weldMaterialSize;
	}

	public void setWeldMaterialSize(String weldMaterialSize) {
		this.weldMaterialSize = weldMaterialSize;
	}

	@Column(name = "WELD_LAYER")
	public String getWeldLayer() {
		return weldLayer;
	}

	public void setWeldLayer(String weldLayer) {
		this.weldLayer = weldLayer;
	}

	@Column(name="POWER_STAGE")
	public String getPowerStage() {
		return powerStage;
	}

	public void setPowerStage(String powerStage) {
		this.powerStage = powerStage;
	}

	@Column(name = "WELD_CURRENT")
	public String getWeldCurrent() {
		return weldCurrent;
	}

	public void setWeldCurrent(String weldCurrent) {
		this.weldCurrent = weldCurrent;
	}

	@Column(name = "ARVOLTAGE")
	public String getArvoltage() {
		return arvoltage;
	}

	public void setArvoltage(String arvoltage) {
		this.arvoltage = arvoltage;
	}

	@Column( name = "WELD_SPEED")
	public String getWeldSpeed() {
		return weldSpeed;
	}

	public void setWeldSpeed(String weldSpeed) {
		this.weldSpeed = weldSpeed;
	}

	@Column(name = "WELD_TIME")
	public String getWeldTime() {
		return weldTime;
	}

	public void setWeldTime(String weldTime) {
		this.weldTime = weldTime;
	}

	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name="IS_INSPECT")
	public String getIsInspect() {
		return isInspect;
	}

	public void setIsInspect(String isInspect) {
		this.isInspect = isInspect;
	}

	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="WELDING_DATE")
	public Date getWeldingDate() {
		return weldingDate;
	}

	public void setWeldingDate(Date weldingDate) {
		this.weldingDate = weldingDate;
	}
}
