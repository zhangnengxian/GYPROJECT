package cc.dfsoft.project.biz.base.budget.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BudgetAdjust entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BUDGET_ADJUST")
public class BudgetAdjust implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2128754038040001965L;
	private String budgetAdjustId;			//主键ID
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private BigDecimal budgetTotalCost;		//调整造价
	private BigDecimal civilCost;		//土建工程费用
	private BigDecimal yardInstallCost;	//庭院安装费用
	private BigDecimal homeInstallCost;	//户内安装费用
	private BigDecimal inspectionCost;			//监检费
	private BigDecimal suCost;					//监理费
	private BigDecimal storeCost;					//工储备资金
	private BigDecimal shantyTownCost;			//棚户区安装费
	private BigDecimal otherCost1;				//其他费用一

	// Constructors

	/** default constructor */
	public BudgetAdjust() {
	}

	// Property accessors
	@Id
	@Column(name = "BUDGET_ADJUST_ID", unique = true)
	public String getBudgetAdjustId() {
		return this.budgetAdjustId;
	}

	public void setBudgetAdjustId(String budgetAdjustId) {
		this.budgetAdjustId = budgetAdjustId;
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


	@Column(name = "INSPECTION_COST", precision = 15, scale = 0)
	public BigDecimal getInspectionCost() {
		return this.inspectionCost;
	}

	public void setInspectionCost(BigDecimal inspectionCost) {
		this.inspectionCost = inspectionCost;
	}

	@Column(name = "SU_COST", precision = 15, scale = 0)
	public BigDecimal getSuCost() {
		return this.suCost;
	}

	public void setSuCost(BigDecimal suCost) {
		this.suCost = suCost;
	}

	@Column(name = "STORE_COST", precision = 15, scale = 0)
	public BigDecimal getStoreCost() {
		return this.storeCost;
	}

	public void setStoreCost(BigDecimal storeCost) {
		this.storeCost = storeCost;
	}

	@Column(name = "SHANTY_TOWN_COST", precision = 15, scale = 0)
	public BigDecimal getShantyTownCost() {
		return this.shantyTownCost;
	}

	public void setShantyTownCost(BigDecimal shantyTownCost) {
		this.shantyTownCost = shantyTownCost;
	}

	@Column(name = "OTHER_COST1", precision = 15, scale = 0)
	public BigDecimal getOtherCost1() {
		return this.otherCost1;
	}

	public void setOtherCost1(BigDecimal otherCost1) {
		this.otherCost1 = otherCost1;
	}
	
	@Column(name = "YARD_INSTALL_COST")
	public BigDecimal getYardInstallCost() {
		return yardInstallCost;
	}

	public void setYardInstallCost(BigDecimal yardInstallCost) {
		this.yardInstallCost = yardInstallCost;
	}
	
	@Column(name = "HOME_INSTALL_COST")
	public BigDecimal getHomeInstallCost() {
		return homeInstallCost;
	}

	public void setHomeInstallCost(BigDecimal homeInstallCost) {
		this.homeInstallCost = homeInstallCost;
	}
	
	@Column(name = "CIVIL_COST")
	public BigDecimal getCivilCost() {
		return civilCost;
	}

	public void setCivilCost(BigDecimal civilCost) {
		this.civilCost = civilCost;
	}
	
	@Column(name = "BUDGET_TOTAL_COST")
	public BigDecimal getBudgetTotalCost() {
		return budgetTotalCost;
	}

	public void setBudgetTotalCost(BigDecimal budgetTotalCost) {
		this.budgetTotalCost = budgetTotalCost;
	}

}