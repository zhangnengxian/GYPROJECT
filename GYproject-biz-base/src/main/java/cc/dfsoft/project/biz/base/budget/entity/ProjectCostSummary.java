package cc.dfsoft.project.biz.base.budget.entity;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.budget.enums.BudgetCostTypeEnum;

/**
 * ProjectCostSummary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PROJECT_COST_SUMMARY")
public class ProjectCostSummary implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7668828494982115619L;
	private String pcsId;		//主键ID
	private String cmId;		//业务单id
	private String mcType;	    //变更类型
	private String pcsNum;		//序号
	private String costName;	//费用名称
	private String costDes;		//取费说明
	private BigDecimal rate;	//费率
	private BigDecimal amount;	//金额
	private String costType;	//费用类型
	private String projId;	    //工程id
	private String isAdjust;    //是否调整
	private String budgetId;    //预算总表ID	
	
	private  String costTypeDes; //费用类型----用于列表展示

	// Constructors

	/** default constructor */
	public ProjectCostSummary() {
	}


	// Property accessors
	@Id
	@Column(name = "PCS_ID", unique = true)
	public String getPcsId() {
		return this.pcsId;
	}

	public void setPcsId(String pcsId) {
		this.pcsId = pcsId;
	}

	@Column(name = "PCS_NUM")
	public String getPcsNum() {
		return this.pcsNum;
	}

	public void setPcsNum(String pcsNum) {
		this.pcsNum = pcsNum;
	}

	@Column(name = "COST_NAME")
	public String getCostName() {
		return this.costName;
	}

	public void setCostName(String costName) {
		this.costName = costName;
	}

	@Column(name = "COST_DES")
	public String getCostDes() {
		return this.costDes;
	}

	public void setCostDes(String costDes) {
		this.costDes = costDes;
	}

	@Column(name = "RATE")
	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Column(name = "AMOUNT")
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name = "COST_TYPE")
	public String getCostType() {
		return this.costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return projId;
	}


	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "IS_ADJUST")
	public String getIsAdjust() {
		return isAdjust;
	}


	public void setIsAdjust(String isAdjust) {
		this.isAdjust = isAdjust;
	}

	@Transient
	public String getCostTypeDes() {
		for(BudgetCostTypeEnum e: BudgetCostTypeEnum.values()) {
	   		if(e.getValue().equals(this.costType)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}


	public void setCostTypeDes(String costTypeDes) {
		this.costTypeDes = costTypeDes;
	}

	@Column(name = "BUDGET_ID")
	public String getBudgetId() {
		return budgetId;
	}


	public void setBudgetId(String budgetId) {
		this.budgetId = budgetId;
	}

	@Column(name = "CM_ID")
	public String getCmId() {
		return cmId;
	}


	public void setCmId(String cmId) {
		this.cmId = cmId;
	}

	@Column(name = "MC_TYPE")
	public String getMcType() {
		return mcType;
	}


	public void setMcType(String mcType) {
		this.mcType = mcType;
	}
	
	
}