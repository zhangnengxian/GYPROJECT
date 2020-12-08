package cc.dfsoft.project.biz.base.contract.entity;

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
 * ContractLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONTRACT_LOG")
public class ContractLog implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -561332753988429655L;
	private String conLogId;				//合同修改日志Id
	private String conId;					//合同ID
	private String conNo;					//合同编号
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private String projName;				//工程名称
	private String projScaleDes;			//工程规模描述
	private String projAddr;				//工程地点
	private String custId;				   //甲方客户ID
	private String custName;				//甲方客户名称
	private String custEntrustRepresent;	//甲方委托代表
	private String custPhone;				//甲方联系方式
	private String agentCustName;			//甲方客户名称-代建单位
	private String agentCustId;				//甲方客户ID-代建单位
	private String agentCustEntrustRepresent;//甲方委托代表-代建单位
	private String agentCustPhone;			//甲方联系方式-代建单位
	private String gasComp;					//乙方燃气公司
	private String gasCompPhone;			//乙方联系方式
	private String conScope;				//承包范围
	private Date plannedStartDate;			//计划开工日期
	private Date plannedEndDate;			//计划竣工日期
	private BigDecimal budgetCost;			//预算总造价
	private BigDecimal contractAmount;		//合同价
	private String payType;					//付款方式
	private Date signDate;					//签订日期
	private String conAgent;				//经办人
	private String supplementClause;		//补充条款
	private BigDecimal firstPayment;		//首付款
	private BigDecimal incrementAmount;		//增值税金额
	private String invoiceType;				//发票类型
	private Date houseDate;					//交房日期
	private String contractType;			//合同类型
	private String contractMethod;			//承包方式
	private String contractContent;			//合同内容
	private String household;				//户数
	private BigDecimal secondPayment;		//阶段款（2）
	private BigDecimal thirdPayment;		//阶段款（3）
	private BigDecimal hoseAmount;			//每户金额
	private String isPass;					//是否通过 1已通过
	private String openBank;				//开户行
	private String account;					//账号
	private String increment;				//税率
	private String unitAddress;				//单位地址
	private String dutyParagraph;			//税号
	private String timeLimit;     			//工期
	private String remark;					//备注
	private String modifystate;				//修改状态，1：修改前，2：修改后
	private String orlId;     				//操作日志Id
	
	private String projectTypeDes;			//工程类型描述
	private String contributionModeDes;		//出资方式描述
	private String deptName;	 			//部门名称
	private String surveyer;	 			//勘察员
              
	private String paymentRatio1;           //首付比例
	private String paymentRatio2;           //二期比例
	private String paymentRatio3;           //三期比例	
	private String agentUnitAddress;		//单位地址-代建单位
	
	private String budgetRule;				//预算制度
	private BigDecimal gasLoss;					//气损费
	// Constructors

	/** default constructor */
	public ContractLog() {
	}

	@Column(name="PAYMENT_RATIO1")
	public String getPaymentRatio1() {
		return paymentRatio1;
	}

	public void setPaymentRatio1(String paymentRatio1) {
		this.paymentRatio1 = paymentRatio1;
	}

	@Column(name="PAYMENT_RATIO2")
	public String getPaymentRatio2() {
		return paymentRatio2;
	}

	public void setPaymentRatio2(String paymentRatio2) {
		this.paymentRatio2 = paymentRatio2;
	}

	@Column(name="PAYMENT_RATIO3")
	public String getPaymentRatio3() {
		return paymentRatio3;
	}

	public void setPaymentRatio3(String paymentRatio3) {
		this.paymentRatio3 = paymentRatio3;
	}
	// Property accessors
	@Id
	@Column(name = "CON_lOG_ID", unique = true)
	public String getConLogId() {
		return this.conLogId;
	}
	
	public void setConLogId(String conLogId) {
		this.conLogId = conLogId;
	}
	
	@Column(name = "CON_ID")
	public String getConId() {
		return this.conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}

	@Column(name = "CON_NO")
	public String getConNo() {
		return this.conNo;
	}

	public void setConNo(String conNo) {
		this.conNo = conNo;
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

	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return this.projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Column(name = "PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return this.projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}

	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return this.projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	@Column(name = "CUST_NAME")
	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Column(name = "CUST_ENTRUST_REPRESENT")
	public String getCustEntrustRepresent() {
		return this.custEntrustRepresent;
	}

	public void setCustEntrustRepresent(String custEntrustRepresent) {
		this.custEntrustRepresent = custEntrustRepresent;
	}

	@Column(name = "CUST_PHONE")
	public String getCustPhone() {
		return this.custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	@Column(name = "GAS_COMP")
	public String getGasComp() {
		return this.gasComp;
	}

	public void setGasComp(String gasComp) {
		this.gasComp = gasComp;
	}

	@Column(name = "GAS_COMP_PHONE")
	public String getGasCompPhone() {
		return this.gasCompPhone;
	}

	public void setGasCompPhone(String gasCompPhone) {
		this.gasCompPhone = gasCompPhone;
	}

	@Column(name = "CON_SCOPE")
	public String getConScope() {
		return this.conScope;
	}

	public void setConScope(String conScope) {
		this.conScope = conScope;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PLANNED_START_DATE")
	public Date getPlannedStartDate() {
		return this.plannedStartDate;
	}

	public void setPlannedStartDate(Date plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "PLANNED_END_DATE")
	public Date getPlannedEndDate() {
		return this.plannedEndDate;
	}

	public void setPlannedEndDate(Date plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}

	@Column(name = "BUDGET_COST")
	public BigDecimal getBudgetCost() {
		return this.budgetCost;
	}

	public void setBudgetCost(BigDecimal budgetCost) {
		this.budgetCost = budgetCost;
	}

	@Column(name = "CONTRACT_AMOUNT")
	public BigDecimal getContractAmount() {
		return this.contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	@Column(name = "PAY_TYPE")
	public String getPayType() {
		return this.payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SIGN_DATE")
	public Date getSignDate() {
		return this.signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	@Column(name = "CON_AGENT")
	public String getConAgent() {
		return this.conAgent;
	}

	public void setConAgent(String conAgent) {
		this.conAgent = conAgent;
	}
	
	@Column(name = "SUPPLEMENT_CLAUSE")
	public String getSupplementClause() {
		return supplementClause;
	}

	public void setSupplementClause(String supplementClause) {
		this.supplementClause = supplementClause;
	}
	
	@Column(name = "FIRST_PAYMENT")
	public BigDecimal getFirstPayment() {
		return firstPayment;
	}

	public void setFirstPayment(BigDecimal firstPayment) {
		this.firstPayment = firstPayment;
	}

	@Column(name = "OPEN_BANK")
	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	@Column(name = "ACCOUNT")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "UNIT_ADDRESS")
	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}

	@Transient
	public String getDutyParagraph() {
		return dutyParagraph;
	}

	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph;
	}
	

	@Column(name="TIME_LIMIT")
	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Column(name="INCREMENT_AMOUNT")
	public BigDecimal getIncrementAmount() {
		return incrementAmount;
	}

	public void setIncrementAmount(BigDecimal incrementAmount) {
		this.incrementAmount = incrementAmount;
	}

	@Column(name="INVOICE_TYPE")
	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="HOUSE_DATE")
	public Date getHouseDate() {
		return houseDate;
	}

	public void setHouseDate(Date houseDate) {
		this.houseDate = houseDate;
	}

	@Column(name="CONTRACT_TYPE")
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	@Column(name="CONTRACT_METHOD")
	public String getContractMethod() {
		return contractMethod;
	}

	public void setContractMethod(String contractMethod) {
		this.contractMethod = contractMethod;
	}

	@Column(name="CONTRACT_CONTENT")
	public String getContractContent() {
		return contractContent;
	}

	public void setContractContent(String contractContent) {
		this.contractContent = contractContent;
	}

	@Column(name="HOUSEHOLD")
	public String getHousehold() {
		return household;
	}

	public void setHousehold(String household) {
		this.household = household;
	}

	@Column(name="SECOND_PAYMENT")
	public BigDecimal getSecondPayment() {
		return secondPayment;
	}

	public void setSecondPayment(BigDecimal secondPayment) {
		this.secondPayment = secondPayment;
	}

	@Column(name="THIRD_PAYMENT")
	public BigDecimal getThirdPayment() {
		return thirdPayment;
	}

	public void setThirdPayment(BigDecimal thirdPayment) {
		this.thirdPayment = thirdPayment;
	}

	@Column(name="HOSE_AMOUNT")
	public BigDecimal getHoseAmount() {
		return hoseAmount;
	}

	public void setHoseAmount(BigDecimal hoseAmount) {
		this.hoseAmount = hoseAmount;
	}

	@Column(name="IS_PASS")
	public String getIsPass() {
		return isPass;
	}

	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}

	@Column(name="INCREMENT")
	public String getIncrement() {
		return increment;
	}

	public void setIncrement(String increment) {
		this.increment = increment;
	}

	@Transient
	public String getProjectTypeDes() {
		return projectTypeDes;
	}

	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}

	@Transient
	public String getContributionModeDes() {
		return contributionModeDes;
	}

	public void setContributionModeDes(String contributionModeDes) {
		this.contributionModeDes = contributionModeDes;
	}

	@Transient
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Transient
	public String getSurveyer() {
		return surveyer;
	}

	public void setSurveyer(String surveyer) {
		this.surveyer = surveyer;
	}

	@Column(name = "MODIFY_STATE")
	public String getModifystate() {
		return modifystate;
	}

	public void setModifystate(String modifystate) {
		this.modifystate = modifystate;
	}

	@Column(name = "ORL_ID")
	public String getOrlId() {
		return orlId;
	}

	public void setOrlId(String orlId) {
		this.orlId = orlId;
	}

	@Column(name = "CUST_ID")
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Column(name="AGENT_CUST_NAME")
	public String getAgentCustName() {
		return agentCustName;
	}

	public void setAgentCustName(String agentCustName) {
		this.agentCustName = agentCustName;
	}

	@Column(name="AGENT_CUST_ID")
	public String getAgentCustId() {
		return agentCustId;
	}

	public void setAgentCustId(String agentCustId) {
		this.agentCustId = agentCustId;
	}

	@Column(name="AGENT_CUST_ENTRUST_REPRESENT")
	public String getAgentCustEntrustRepresent() {
		return agentCustEntrustRepresent;
	}

	public void setAgentCustEntrustRepresent(String agentCustEntrustRepresent) {
		this.agentCustEntrustRepresent = agentCustEntrustRepresent;
	}

	@Column(name="AGENT_CUST_PHONE")
	public String getAgentCustPhone() {
		return agentCustPhone;
	}

	public void setAgentCustPhone(String agentCustPhone) {
		this.agentCustPhone = agentCustPhone;
	}

	@Column(name="AGENT_UNIT_ADDRESS")
	public String getAgentUnitAddress() {
		return agentUnitAddress;
	}

	public void setAgentUnitAddress(String agentUnitAddress) {
		this.agentUnitAddress = agentUnitAddress;
	}
	@Column(name="BUDGET_RULE")
	public String getBudgetRule() {
		return budgetRule;
	}

	public void setBudgetRule(String budgetRule) {
		this.budgetRule = budgetRule;
	}

	@Column(name="GAS_LOSS")
	public BigDecimal getGasLoss() {
		return gasLoss;
	}

	public void setGasLoss(BigDecimal gasLoss) {
		this.gasLoss = gasLoss;
	}
}