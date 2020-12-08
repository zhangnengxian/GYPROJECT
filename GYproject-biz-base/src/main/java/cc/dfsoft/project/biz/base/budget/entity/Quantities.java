package cc.dfsoft.project.biz.base.budget.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Quantities entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "QUANTITIES")
public class Quantities implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1574565308181310109L;
	private String quId;			//主键ID
	private String budgetId;		//预算总表ID
	private String projId;			//工程ID
	private String projNo;			//工程编号
	private String quStandardId;	//工程量标准ID
	private String quCostType;		//造价类型
	private String quBranchProjName;//分步分项工程名称
	private String quUnit;			//单位
	private String quWorkContent;	//工作内容及项目特征
	private Double quLabourPrice;	//劳务单价
	private Double quNum;			//数量
	private BigDecimal quAmount;	//金额
	

	// Constructors

	/** default constructor */
	public Quantities() {
	}

	// Property accessors
	@Id
	@Column(name = "QU_ID", unique = true)
	public String getQuId() {
		return this.quId;
	}

	public void setQuId(String quId) {
		this.quId = quId;
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

	@Column(name = "QU_STANDARD_ID")
	public String getQuStandardId() {
		return this.quStandardId;
	}

	public void setQuStandardId(String quStandardId) {
		this.quStandardId = quStandardId;
	}

	@Column(name = "QU_COST_TYPE")
	public String getQuCostType() {
		return this.quCostType;
	}

	public void setQuCostType(String quCostType) {
		this.quCostType = quCostType;
	}

	@Column(name = "QU_BRANCH_PROJ_NAME")
	public String getQuBranchProjName() {
		return this.quBranchProjName;
	}

	public void setQuBranchProjName(String quBranchProjName) {
		this.quBranchProjName = quBranchProjName;
	}

	@Column(name = "QU_UNIT")
	public String getQuUnit() {
		return this.quUnit;
	}

	public void setQuUnit(String quUnit) {
		this.quUnit = quUnit;
	}

	@Column(name = "QU_WORK_CONTENT")
	public String getQuWorkContent() {
		return this.quWorkContent;
	}

	public void setQuWorkContent(String quWorkContent) {
		this.quWorkContent = quWorkContent;
	}

	@Column(name = "QU_LABOUR_PRICE")
	public Double getQuLabourPrice() {
		return this.quLabourPrice;
	}

	public void setQuLabourPrice(Double quLabourPrice) {
		this.quLabourPrice = quLabourPrice;
	}

	@Column(name = "QU_NUM")
	public Double getQuNum() {
		return this.quNum;
	}

	public void setQuNum(Double quNum) {
		this.quNum = quNum;
	}

	@Column(name = "QU_AMOUNT")
	public BigDecimal getQuAmount() {
		return this.quAmount;
	}

	public void setQuAmount(BigDecimal quAmount) {
		this.quAmount = quAmount;
	}

}