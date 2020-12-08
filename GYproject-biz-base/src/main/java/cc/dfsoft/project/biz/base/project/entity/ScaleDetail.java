package cc.dfsoft.project.biz.base.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * ScaleDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SCALE_DETAIL")
public class ScaleDetail implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4079692035781335709L;
	// Fields

	private String scaleId;		  //规模ID
	private String projId;		  //工程ID
	private Integer houseNum;	  //户数（数量）
	private Integer searNum;	  //座数
	private Integer num;		  //台数
	private Double tonnage;	  //吨位
	private Double gasConsumption;//预计用气量（Nm3/h）
	private String projNo;		  //工程编号
	private String projLtypeId;	  //工程大类
	private String projStypeId;   //工程二类
	private String projStypeDes;  //工程二类名称
	
	private BigDecimal scaleAmount;	  //规模金额

	private String pipeDiameter;	  //管径
	private BigDecimal pipeLength;		  //项目长度
	private BigDecimal finishLength;	  //完成长度
	private String constructionRatio; //建设比例

	// Constructors

	/** default constructor */
	public ScaleDetail() {
	}

	// Property accessors
	@Id
	@Column(name = "SCALE_ID", unique = true, nullable = false )
	public String getScaleId() {
		return this.scaleId;
	}

	public void setScaleId(String scaleId) {
		this.scaleId = scaleId;
	}

	@Column(name = "PROJ_ID", nullable = false )
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "HOUSE_NUM" )
	public Integer getHouseNum() {
		return this.houseNum;
	}

	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}

	@Column(name = "SEAR_NUM")
	public Integer getSearNum() {
		return this.searNum;
	}

	public void setSearNum(Integer searNum) {
		this.searNum = searNum;
	}

	@Column(name = "NUM")
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "TONNAGE")
	public Double getTonnage() {
		return this.tonnage;
	}

	public void setTonnage(Double tonnage) {
		this.tonnage = tonnage;
	}

	@Column(name = "GAS_CONSUMPTION")
	public Double getGasConsumption() {
		return this.gasConsumption;
	}

	public void setGasConsumption(Double gasConsumption) {
		this.gasConsumption = gasConsumption;
	}

	@Column(name = "PROJ_NO", nullable = false)
	public String getProjNo() {
		return this.projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "PROJ_LTYPE_ID",nullable = false )
	public String getProjLtypeId() {
		return this.projLtypeId;
	}

	public void setProjLtypeId(String projLtypeId) {
		this.projLtypeId = projLtypeId;
	}

	@Column(name = "PROJ_STYPE_ID")
	public String getProjStypeId() {
		return this.projStypeId;
	}

	public void setProjStypeId(String projStypeId) {
		this.projStypeId = projStypeId;
	}
	
	@Column(name = "PROJ_STYPE_DES")
	public String getProjStypeDes() {
		return projStypeDes;
	}

	public void setProjStypeDes(String projStypeDes) {
		this.projStypeDes = projStypeDes;
	}
	
	@Column(name = "SCALE_AMOUNT")
	public BigDecimal getScaleAmount() {
		return scaleAmount;
	}

	public void setScaleAmount(BigDecimal scaleAmount) {
		this.scaleAmount = scaleAmount;
	}

	@Column(name = "PIPE_DIAMETER")
	public String getPipeDiameter() {
		return pipeDiameter;
	}

	public void setPipeDiameter(String pipeDiameter) {
		this.pipeDiameter = pipeDiameter;
	}

	@Column(name = "PIPE_LENGTH")
	public BigDecimal getPipeLength() {
		return pipeLength;
	}

	public void setPipeLength(BigDecimal pipeLength) {
		this.pipeLength = pipeLength;
	}

	@Column(name = "FINISH_LENGTH")
	public BigDecimal getFinishLength() {
		return finishLength;
	}

	public void setFinishLength(BigDecimal finishLength) {
		this.finishLength = finishLength;
	}

	@Column(name = "CONSTRUCTION_RATIO")
	public String getConstructionRatio() {
		return constructionRatio;
	}

	public void setConstructionRatio(String constructionRatio) {
		this.constructionRatio = constructionRatio;
	}
}