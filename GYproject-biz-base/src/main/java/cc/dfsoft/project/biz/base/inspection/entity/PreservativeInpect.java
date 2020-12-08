package cc.dfsoft.project.biz.base.inspection.entity;

import javax.persistence.*;
import java.io.Serializable;
/**
 * 防腐检查实体类
 * @author liaoyq
 */
@Entity
@Table(name="PRESERVATIVE_INPECT")
public class PreservativeInpect implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String piId;				//防腐检查表ID
	private String pcId;				//报验单ID
	private Integer preservativeType;	//PRESERVATIVE_TYPE` int(2) DEFAULT NULL COMMENT '防腐类型：0-钢制管道及接头防腐，1-钢制管道油漆防腐',
	private String preservativeMeasure;	//`PRESERVATIVE_MEASURE` varchar(100) DEFAULT NULL COMMENT '补口防腐措施',
	private String jointSpecification;	//JOINT_SPECIFICATION` varchar(50) DEFAULT NULL COMMENT '接头规格及数量',
	private String preservativeRange;	//`PRESERVATIVE_RANGE` varchar(100) DEFAULT NULL COMMENT '防腐等级',
	private String jointPreservativeRange;	//JOINT_PRESERVATIVE_RANGE` varchar(50) DEFAULT NULL COMMENT '接头防腐防腐等级',
	private String jointAsphaltgrade;		//JOINT_ASPHALTGRADE` varchar(50) DEFAULT NULL COMMENT '接头防腐沥青标号',
	private String jointFiberglassCloth;	//JOINT_FIBERGLASS_CLOTH` varchar(50) DEFAULT NULL COMMENT '接头防腐玻纤布规格',
	private String primerRatio;				//PRIMER_RATIO 油漆配比
	private Integer jointMetallicLuster;	//JOINT_IS_METALLIC_LUSTER` int(2) DEFAULT NULL COMMENT '接头防腐除锈见光泽：1-是，0-否',
	private Integer jointIsPrimer;			//JOINT_IS_PRIMER` int(2) DEFAULT NULL COMMENT '接头防腐是否刷底漆',
	private String jointVshart;				//JOINT_VSHORT` varchar(50) DEFAULT NULL COMMENT '接头防腐电火花检测电压',
	private Integer jointElectricSpark;		//JOINT_ELECTRIC_SPARK` int(2) DEFAULT NULL COMMENT '接头防腐有无电火花产生: 0-没有，1-有',
	private String jointRepairNum;			//JOINT_REPAIR_NUM` varchar(20) DEFAULT NULL COMMENT '接头防腐修补处数量',
	private Integer pipeSupplyUnit;			//PIPE_SUPPLY_UNIT` int(1) DEFAULT NULL COMMENT '管道防腐供货单位：0-自购，1-甲方',
	private String pipePreservativeRange;	//PIPE_PRESERVATIVE_RANGE` varchar(50) DEFAULT NULL COMMENT '管道防腐防腐等级',
	private String pipeSpecification;		//PIPE_SPECIFICATION` varchar(50) DEFAULT NULL COMMENT '管道防腐规格及数量',
	private String pipeVshort;				//PIPE_VSHORT` varchar(50) DEFAULT NULL COMMENT '管道防腐现场电火花电压',
	private String pipeDamagedNum;			//PIPE_DAMAGED_NUM` varchar(20) DEFAULT NULL COMMENT '破损处',
	private String pipeRepairNum;			//PIPE_REPAIR_NUM` varchar(20) DEFAULT NULL COMMENT '管道防腐修补处',
	private String electricSparkNum;		//ELECTRIC_SPARK_NUM` varchar(20) DEFAULT NULL COMMENT '电火花检测处',
	private Integer isLeakageWeld;			//IS_LEAKAGE_WELD` int(1) DEFAULT NULL COMMENT '目视检查管道外观表面平整、无气泡、麻面、皱纹、瘸子:0-否，1-是',
	private Integer pipeElectricSpark;		//PIPE_ELECTRIC_SPARK` int(2) DEFAULT NULL COMMENT '有无电火花产生：0-无，1-有',
    private String projId;					//工程ID
//	private Integer version;		//版本控制
	
	public PreservativeInpect() {
		super();
	}

	@Id
	@Column(name = "PI_ID")
	public String getPiId() {
		return piId;
	}


	public void setPiId(String piId) {
		this.piId = piId;
	}

	@Column(name = "PC_ID")
	public String getPcId() {
		return pcId;
	}


	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name = "PRESERVATIVE_TYPE")
	public Integer getPreservativeType() {
		return preservativeType;
	}


	public void setPreservativeType(Integer preservativeType) {
		this.preservativeType = preservativeType;
	}

	@Column(name = "JOINT_SPECIFICATION")
	public String getJointSpecification() {
		return jointSpecification;
	}


	public void setJointSpecification(String jointSpecification) {
		this.jointSpecification = jointSpecification;
	}

	@Column(name = "JOINT_PRESERVATIVE_RANGE")
	public String getJointPreservativeRange() {
		return jointPreservativeRange;
	}


	public void setJointPreservativeRange(String jointPreservativeRange) {
		this.jointPreservativeRange = jointPreservativeRange;
	}

	@Column(name = "JOINT_ASPHALTGRADE")
	public String getJointAsphaltgrade() {
		return jointAsphaltgrade;
	}


	public void setJointAsphaltgrade(String jointAsphaltgrade) {
		this.jointAsphaltgrade = jointAsphaltgrade;
	}

	@Column(name = "JOINT_FIBERGLASS_CLOTH")
	public String getJointFiberglassCloth() {
		return jointFiberglassCloth;
	}


	public void setJointFiberglassCloth(String jointFiberglassCloth) {
		this.jointFiberglassCloth = jointFiberglassCloth;
	}

	@Column(name = "JOINT_IS_METALLIC_LUSTER")
	public Integer getJointMetallicLuster() {
		return jointMetallicLuster;
	}


	public void setJointMetallicLuster(Integer jointMetallicLuster) {
		this.jointMetallicLuster = jointMetallicLuster;
	}

	@Column(name = "JOINT_IS_PRIMER")
	public Integer getJointIsPrimer() {
		return jointIsPrimer;
	}

	
	public void setJointIsPrimer(Integer jointIsPrimer) {
		this.jointIsPrimer = jointIsPrimer;
	}

	@Column(name = "JOINT_VSHORT")
	public String getJointVshart() {
		return jointVshart;
	}


	public void setJointVshart(String jointVshart) {
		this.jointVshart = jointVshart;
	}

	@Column(name = "JOINT_ELECTRIC_SPARK")
	public Integer getJointElectricSpark() {
		return jointElectricSpark;
	}


	public void setJointElectricSpark(Integer jointElectricSpark) {
		this.jointElectricSpark = jointElectricSpark;
	}

	@Column(name = "JOINT_REPAIR_NUM")
	public String getJointRepairNum() {
		return jointRepairNum;
	}


	public void setJointRepairNum(String jointRepairNum) {
		this.jointRepairNum = jointRepairNum;
	}

	@Column(name = "PIPE_SUPPLY_UNIT")
	public Integer getPipeSupplyUnit() {
		return pipeSupplyUnit;
	}


	public void setPipeSupplyUnit(Integer pipeSupplyUnit) {
		this.pipeSupplyUnit = pipeSupplyUnit;
	}

	@Column(name = "PIPE_PRESERVATIVE_RANGE")
	public String getPipePreservativeRange() {
		return pipePreservativeRange;
	}


	public void setPipePreservativeRange(String pipePreservativeRange) {
		this.pipePreservativeRange = pipePreservativeRange;
	}

	@Column(name = "PIPE_SPECIFICATION")
	public String getPipeSpecification() {
		return pipeSpecification;
	}


	public void setPipeSpecification(String pipeSpecification) {
		this.pipeSpecification = pipeSpecification;
	}

	@Column(name = "PIPE_VSHORT")
	public String getPipeVshort() {
		return pipeVshort;
	}


	public void setPipeVshort(String pipeVshort) {
		this.pipeVshort = pipeVshort;
	}

	@Column(name = "PIPE_DAMAGED_NUM")
	public String getPipeDamagedNum() {
		return pipeDamagedNum;
	}


	public void setPipeDamagedNum(String pipeDamagedNum) {
		this.pipeDamagedNum = pipeDamagedNum;
	}

	@Column(name = "PIPE_REPAIR_NUM")
	public String getPipeRepairNum() {
		return pipeRepairNum;
	}


	public void setPipeRepairNum(String pipeRepairNum) {
		this.pipeRepairNum = pipeRepairNum;
	}

	@Column(name = "ELECTRIC_SPARK_NUM")
	public String getElectricSparkNum() {
		return electricSparkNum;
	}


	public void setElectricSparkNum(String electricSparkNum) {
		this.electricSparkNum = electricSparkNum;
	}

	@Column(name = "IS_LEAKAGE_WELD")
	public Integer getIsLeakageWeld() {
		return isLeakageWeld;
	}


	public void setIsLeakageWeld(Integer isLeakageWeld) {
		this.isLeakageWeld = isLeakageWeld;
	}

	@Column(name = "PIPE_ELECTRIC_SPARK")
	public Integer getPipeElectricSpark() {
		return pipeElectricSpark;
	}


	public void setPipeElectricSpark(Integer pipeElectricSpark) {
		this.pipeElectricSpark = pipeElectricSpark;
	}

	@Column(name="PRIMER_RATIO")
	public String getPrimerRatio() {
		return primerRatio;
	}

	public void setPrimerRatio(String primerRatio) {
		this.primerRatio = primerRatio;
	}

	@Column(name="PRESERVATIVE_MEASURE")
	public String getPreservativeMeasure() {
		return preservativeMeasure;
	}

	public void setPreservativeMeasure(String preservativeMeasure) {
		this.preservativeMeasure = preservativeMeasure;
	}

	@Column(name="PRESERVATIVE_RANGE")
	public String getPreservativeRange() {
		return preservativeRange;
	}

	public void setPreservativeRange(String preservativeRange) {
		this.preservativeRange = preservativeRange;
	}

	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	 
//	@Version
//	@Column(name="VERSION")
//	public Integer getVersion() {
//		return version;
//	}
//
//	public void setVersion(Integer version) {
//		this.version = version;
//	}
}
