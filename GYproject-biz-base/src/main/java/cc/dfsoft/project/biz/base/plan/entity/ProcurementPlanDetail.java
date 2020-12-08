package cc.dfsoft.project.biz.base.plan.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProcurementPlanDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PROCUREMENT_PLAN_DETAIL")
public class ProcurementPlanDetail implements Serializable {
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1833233687016579760L;
	private String procurPlanDetailId;	//明细ID
	private String procurPlanId;		//采购计划ID
	private String projId;				//工程ID
	private String materialName;		//材料名称
	private String materialSpec;		//规格型号
	private String materialUnit;		//单位
	private BigDecimal materialPrice;	//单价
	private BigDecimal materialNum;		//数量
	private String materialId;			//材料清单ID
	// Constructors

	/** default constructor */
	public ProcurementPlanDetail() {
	}

	// Property accessors
	@Id
	@Column(name = "PROCUR_PLAN_DETAIL_ID", unique = true)
	public String getProcurPlanDetailId() {
		return this.procurPlanDetailId;
	}

	public void setProcurPlanDetailId(String procurPlanDetailId) {
		this.procurPlanDetailId = procurPlanDetailId;
	}

	@Column(name = "PROCUR_PLAN_ID")
	public String getProcurPlanId() {
		return this.procurPlanId;
	}

	public void setProcurPlanId(String procurPlanId) {
		this.procurPlanId = procurPlanId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "MATERIAL_NAME")
	public String getMaterialName() {
		return this.materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	@Column(name = "MATERIAL_SPEC")
	public String getMaterialSpec() {
		return this.materialSpec;
	}

	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}

	@Column(name = "MATERIAL_UNIT")
	public String getMaterialUnit() {
		return this.materialUnit;
	}

	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}

	@Column(name = "MATERIAL_PRICE")
	public BigDecimal getMaterialPrice() {
		return this.materialPrice;
	}

	public void setMaterialPrice(BigDecimal materialPrice) {
		this.materialPrice = materialPrice;
	}

	@Column(name = "MATERIAL_NUM")
	public BigDecimal getMaterialNum() {
		return this.materialNum;
	}

	public void setMaterialNum(BigDecimal materialNum) {
		this.materialNum = materialNum;
	}

	@Column(name = "MATERIAL_ID")
	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	
}