package cc.dfsoft.project.biz.base.budget.entity;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BudgetTotalTableId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BUDGET_TOTAL_TABLE")
public class BudgetTotalTable implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4095089831859474460L;
	/**
	 * 
	 */
	// Fields

	private String btId;
	private String projId;
	private String projNo;
	private String serialNo; 		//序号
	private String subitemName;		//分项名称
	private BigDecimal amount; 			//金额
	private String budgetId;        //预算总表id
	private String budgetType;		//总表类型 1调整 0正常
	private String cmId;			//变更记录id
	// Constructors

	/** default constructor */
	public BudgetTotalTable() {
	}

	@Id
	@Column(name = "BT_ID")
	public String getBtId() {
		return this.btId;
	}

	public void setBtId(String btId) {
		this.btId = btId;
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

	@Column(name = "SERIAL_NO")
	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name = "SUBITEM_NAME")
	public String getSubitemName() {
		return this.subitemName;
	}

	public void setSubitemName(String subitemName) {
		this.subitemName = subitemName;
	}

	@Column(name = "AMOUNT")
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Column(name = "BUDGET_ID")
	public String getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(String budgetId) {
		this.budgetId = budgetId;
	}
	
	@Column(name = "BUDGET_TYPE")
	public String getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}

	@Column(name = "CM_ID")
	public String getCmId() {
		return cmId;
	}

	public void setCmId(String cmId) {
		this.cmId = cmId;
	}
}
