package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * MaterialInspection entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MATERIAL_INSPECTION")
public class MaterialInspection implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7379167185061847804L;
	private String miId;				//报验记录ID
	private String projId;				//工程ID
	private String bmNo;				//材料编号
	private String bmName;				//材料名称
	private String bmSpec;				//规格型号
	private String bmUnit;				//单位
	private BigDecimal bmPrice;			//单价
	private BigDecimal miNum;			//报验数量
	private BigDecimal miAmount;		//合计金额
	private String mcInspection;		//报验结果
	private String materialInspector;	//报验人
	private Date miDate;				//报验日期
	
	private String materialId;			//材料清单id------用于材料报验功能实现

	// Constructors

	/** default constructor */
	public MaterialInspection() {
	}


	// Property accessors
	@Id
	@Column(name = "MI_ID", unique = true)
	public String getMiId() {
		return this.miId;
	}

	public void setMiId(String miId) {
		this.miId = miId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "BM_NO")
	public String getBmNo() {
		return this.bmNo;
	}

	public void setBmNo(String bmNo) {
		this.bmNo = bmNo;
	}

	@Column(name = "BM_NAME")
	public String getBmName() {
		return this.bmName;
	}

	public void setBmName(String bmName) {
		this.bmName = bmName;
	}

	@Column(name = "BM_SPEC")
	public String getBmSpec() {
		return this.bmSpec;
	}

	public void setBmSpec(String bmSpec) {
		this.bmSpec = bmSpec;
	}

	@Column(name = "BM_UNIT")
	public String getBmUnit() {
		return this.bmUnit;
	}

	public void setBmUnit(String bmUnit) {
		this.bmUnit = bmUnit;
	}

	@Column(name = "BM_PRICE")
	public BigDecimal getBmPrice() {
		return this.bmPrice;
	}

	public void setBmPrice(BigDecimal bmPrice) {
		this.bmPrice = bmPrice;
	}

	@Column(name = "MI_NUM")
	public BigDecimal getMiNum() {
		return this.miNum;
	}

	public void setMiNum(BigDecimal miNum) {
		this.miNum = miNum;
	}

	@Column(name = "MI_AMOUNT")
	public BigDecimal getMiAmount() {
		return this.miAmount;
	}

	public void setMiAmount(BigDecimal miAmount) {
		this.miAmount = miAmount;
	}

	@Column(name = "MC_INSPECTION")
	public String getMcInspection() {
		return this.mcInspection;
	}

	public void setMcInspection(String mcInspection) {
		this.mcInspection = mcInspection;
	}

	@Column(name = "MATERIAL_INSPECTOR")
	public String getMaterialInspector() {
		return this.materialInspector;
	}

	public void setMaterialInspector(String materialInspector) {
		this.materialInspector = materialInspector;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MI_DATE")
	public Date getMiDate() {
		return this.miDate;
	}

	public void setMiDate(Date miDate) {
		this.miDate = miDate;
	}

	@Transient
	public String getMaterialId() {
		return materialId;
	}


	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

}