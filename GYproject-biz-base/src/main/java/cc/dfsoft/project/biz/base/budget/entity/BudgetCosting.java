package cc.dfsoft.project.biz.base.budget.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BudgetCosting entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BUDGET_COSTING")
public class BudgetCosting implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3927003541195446701L;
	private String bcId;					//主键ID
	private String budgetId;				//预算总表ID
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private String costCalssify;			//造价分类
	private Double laborCost;				//人工费
	private Double mainMaterialCost;		//主材费
	private Double auxiliaryMaterialCost;	//辅材费
	private Double machineCost;				//机械使用费
	private Double indirectCost;			//其他间接费
	private Double indirectCosting;			//间接成本
	private Double bcTotal;					//合计
	private String remark;					//备注

	// Constructors

	/** default constructor */
	public BudgetCosting() {
	}

	// Property accessors
	@Id
	@Column(name = "BC_ID", unique = true)
	public String getBcId() {
		return this.bcId;
	}

	public void setBcId(String bcId) {
		this.bcId = bcId;
	}

	@Column(name = "BUDGET_ID")
	public String getBudgetId() {
		return this.budgetId;
	}

	public void setBudgetId(String budgetId) {
		this.budgetId = budgetId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return this.projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "COST_CALSSIFY")
	public String getCostCalssify() {
		return this.costCalssify;
	}

	public void setCostCalssify(String costCalssify) {
		this.costCalssify = costCalssify;
	}

	@Column(name = "LABOR_COST")
	public Double getLaborCost() {
		return this.laborCost;
	}

	public void setLaborCost(Double laborCost) {
		this.laborCost = laborCost;
	}

	@Column(name = "MAIN_MATERIAL_COST")
	public Double getMainMaterialCost() {
		return this.mainMaterialCost;
	}

	public void setMainMaterialCost(Double mainMaterialCost) {
		this.mainMaterialCost = mainMaterialCost;
	}

	@Column(name = "AUXILIARY_MATERIAL_COST")
	public Double getAuxiliaryMaterialCost() {
		return this.auxiliaryMaterialCost;
	}

	public void setAuxiliaryMaterialCost(Double auxiliaryMaterialCost) {
		this.auxiliaryMaterialCost = auxiliaryMaterialCost;
	}

	@Column(name = "MACHINE_COST")
	public Double getMachineCost() {
		return this.machineCost;
	}

	public void setMachineCost(Double machineCost) {
		this.machineCost = machineCost;
	}

	@Column(name = "INDIRECT_COST")
	public Double getIndirectCost() {
		return this.indirectCost;
	}

	public void setIndirectCost(Double indirectCost) {
		this.indirectCost = indirectCost;
	}

	@Column(name = "INDIRECT_COSTING")
	public Double getIndirectCosting() {
		return this.indirectCosting;
	}

	public void setIndirectCosting(Double indirectCosting) {
		this.indirectCosting = indirectCosting;
	}

	@Column(name = "BC_TOTAL")
	public Double getBcTotal() {
		return this.bcTotal;
	}

	public void setBcTotal(Double bcTotal) {
		this.bcTotal = bcTotal;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}