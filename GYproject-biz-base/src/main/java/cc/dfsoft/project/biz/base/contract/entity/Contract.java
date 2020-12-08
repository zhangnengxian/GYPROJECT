package cc.dfsoft.project.biz.base.contract.entity;

import cc.dfsoft.project.biz.base.baseinfo.dto.WorkDayDto;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.contract.enums.ModifyStatusEnum;
import cc.dfsoft.project.biz.base.project.entity.ScaleDetail;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Contract entity. @author MyEclipse Persistence Tools
 */
/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "CONTRACT")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Contract implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -561332753988429655L;
	private String conId;					//合同ID
	private String conNo;					//合同编号
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private String projName;				//工程名称
	private String projScaleDes;			//工程规模描述
	private String projAddr;				//工程地点
	private String custName;				//甲方客户名称
	private String custId;				    //甲方客户ID
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
	private String supplementClause;		//补充条款 其他条款
	private BigDecimal firstPayment;		//首付款
	private BigDecimal incrementAmount;		//增值税金额
	private String invoiceType;				//发票类型
	private Date houseDate;					//交房日期
	private String contractType;			//合同类型
	private String contractMethod;			//承包方式
	private String contractMethodOther;		//其他承包方式
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
	private String agentUnitAddress;		//单位地址-代建单位
	private String dutyParagraph;			//税号
	private String timeLimit;     			//工期
	private String isPrint;					//是否打印标记     0 已打印,1未打印
	private String remark;					//备注
	private String modifyStatus;			//null:无修改，0:修改审批中，1：修改审核已通过，2：修改审核未通过
	private String signBack;				//未通过标示-合同修改审核中控制底纹
	private String gasCorpAddr;				//燃气公司地址
	private String isPay="0";                   //是否已收款 0是未收款，1是已收款
	private String mrAuditLevel;			//已审核级别----只读属性，用于合同审核屏
	private String level;					//几级审核------只读属性，用于合同审核页面显示按钮
	private String legalAmount;				//打印合同时用
	private String legalFirstPayment;		//首付款大写
	private String legalSecondPaymentAmount;//打印合同时用
	private String legalThirdPaymentAmount;	//打印合同时用
	private String legalFourPaymentAmount;	//打印合同时用
	private String legalIncrementAmount;	    //打印合同时用
	private String legalHoseAmount;	    //每户金额大写
	private String legalContractAmount;		//合同价大写
	private String legalBudgetCost;       //预算造价大写

	private String flag;           			//保存时标记，0---暂存，1---保存并推送
	private Boolean overdue;				//是否逾期 true逾期 false未逾期
	private Integer overDay;				//逾期天数
	private String projectTypeDes;			//工程类型描述
	private String contributionModeDes;		//出资方式描述
	private String deptName;	 			//部门名称
	private String paymentRatio1;           //首付款比列
	private String paymentRatio2;           //中期付款比例
	private String paymentRatio3;           //后期付款比例
	private String surveyer;	 			//勘察员
	private String modifyStatusDes;			//修改状态--只读，列表显示
	private String deptDivide;				//部门划分
	private String projectType;			    //工程类型
	private List<ScaleDetail> scaleDetails; //多条规模明细
	private String materialIsRegister = "0"; //物资登记
	private String materialRemark;			//物资登记备注
	private String materialDes;            //物资登记描述

	private String isSpecial;              //是否特殊工程
	private String payPlat;                //付款比例
	
	private String isSpecialSign;			//是否特殊工程 1 是 0 否
	
	private List<CashFlow> cashFlows;       //实收付流水
	private String cashFlowRemark;          //实收付流水
	private List<AccrualsRecord> accRecords;//应收付
	private String costRemark;				//造价备注
	
	private WorkDayDto workDayDto;			//只读
	private String corpId;					//分公司id
	
	//安顺非居
	private String budgetRule;				//预算制度
	private BigDecimal gasLoss;				//气损费
	private String  responsibility;			//甲、乙方的职责与义务
	private String  contractCopies;			//合同份数
	private String fisrtPartyCopies;        //甲方份数
	private String secondPartyCopies;		//乙方份数
	private BigDecimal budgetTotalCost;		//工程预算总价
	private BigDecimal govAuditCost;		//政府预算审定价
	private String legalBudgetTotalCost;		//工程预算总价大写
	/**阶段款4*/
    private String fourPayment;
    /**付款比例(4)*/
    private String paymentPaytio4;
	/** default constructor */
	public Contract() {
	}

	// Property accessors
	@Id
	@Column(name = "CON_ID", unique = true)
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

	@Column(name = "PAYMENT_RATIO1")
	public String getPaymentRatio1() {
		return paymentRatio1;
	}

	public void setPaymentRatio1(String paymentRatio1) {
		this.paymentRatio1 = paymentRatio1;
	}
	@Column(name = "PAYMENT_RATIO2")
	public String getPaymentRatio2() {
		return paymentRatio2;
	}

	public void setPaymentRatio2(String paymentRatio2) {
		this.paymentRatio2 = paymentRatio2;
	}
	@Column(name = "PAYMENT_RATIO3")
	public String getPaymentRatio3() {
		return paymentRatio3;
	}

	public void setPaymentRatio3(String paymentRatio3) {
		this.paymentRatio3 = paymentRatio3;
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
   @Transient
	public List<ScaleDetail> getScaleDetails() {
		return scaleDetails;
	}

	public void setScaleDetails(List<ScaleDetail> scaleDetails) {
		this.scaleDetails = scaleDetails;
	}

	@Transient
	public String getMrAuditLevel() {
		return mrAuditLevel;
	}

	public void setMrAuditLevel(String mrAuditLevel) {
		this.mrAuditLevel = mrAuditLevel;
	}
	
	@Transient
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Transient
	public Boolean getOverdue() {
		return overdue;
	}

	public void setOverdue(Boolean overdue) {
		this.overdue = overdue;
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

	
	@Transient
	public Integer getOverDay() {
		return overDay;
	}

	public void setOverDay(Integer overDay) {
		this.overDay = overDay;
	}
	@Transient
	public String getLegalAmount() {
		return legalAmount;
	}

	public void setLegalAmount(String legalAmount) {
		this.legalAmount = legalAmount;
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
	@Transient
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Column(name="IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
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

	@Column(name="MODIFY_STATUS")
	public String getModifyStatus() {
		return modifyStatus;
	}

	public void setModifyStatus(String modifyStatus) {
		this.modifyStatus = modifyStatus;
	}

	@Transient
	public String getModifyStatusDes() {
		if(null==this.modifyStatus||""==this.modifyStatus){
			return ModifyStatusEnum.NO_MODIFY.getMessage();
		}else {
			for(ModifyStatusEnum e: ModifyStatusEnum.values()) {
		   		if(e.getValue().equals(this.modifyStatus)) {
		   			return e.getMessage();
		   		}
		   	}
			return "";
		}
	}
	public void setModifyStatusDes(String modifyStatusDes) {
		this.modifyStatusDes = modifyStatusDes;
	}

	@Column(name="SIGN_BACK")
	public String getSignBack() {
		return signBack;
	}

	public void setSignBack(String signBack) {
		this.signBack = signBack;
	}

	@Transient
	public String getDeptDivide() {
		return deptDivide;
	}

	public void setDeptDivide(String deptDivide) {
		this.deptDivide = deptDivide;
	}
	
	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Transient
	public String getLegalFirstPayment() {
		return legalFirstPayment;
	}

	public void setLegalFirstPayment(String legalFirstPayment) {
		this.legalFirstPayment = legalFirstPayment;
	}

	@Transient
	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	@Transient
	public String getLegalSecondPaymentAmount() {
		return legalSecondPaymentAmount;
	}

	public void setLegalSecondPaymentAmount(String legalSecondPaymentAmount) {
		this.legalSecondPaymentAmount = legalSecondPaymentAmount;
	}

	@Transient
	public String getLegalThirdPaymentAmount() {
		return legalThirdPaymentAmount;
	}

	public void setLegalThirdPaymentAmount(String legalThirdPaymentAmount) {
		this.legalThirdPaymentAmount = legalThirdPaymentAmount;
	}

	@Column(name="GAS_CORP_ADDR")
	public String getGasCorpAddr() {
		return gasCorpAddr;
	}

	public void setGasCorpAddr(String gasCorpAddr) {
		this.gasCorpAddr = gasCorpAddr;
	}
	@Column(name="MATERIAL_IS_REGISTER")
	public String getMaterialIsRegister() {
		return materialIsRegister;
	}

	public void setMaterialIsRegister(String materialIsRegister) {
		this.materialIsRegister = materialIsRegister;
	}
	@Column(name="MATERIAL_REMARK")
	public String getMaterialRemark() {
		return materialRemark;
	}
	
	public void setMaterialRemark(String materialRemark) {
		this.materialRemark = materialRemark;
	}
	@Transient
	public String getMaterialDes() {
		return materialDes;
	}

	public void setMaterialDes(String materialDes) {
		this.materialDes = materialDes;
	}

	@Column(name="IS_SPECIAL")
	public String getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(String isSpecial) {
		this.isSpecial = isSpecial;
	}

	@Column(name="PAY_PLAT")
	public String getPayPlat() {
		return payPlat;
	}

	public void setPayPlat(String payPlat) {
		this.payPlat = payPlat;
	}
	
	@Transient
	public String getIsSpecialSign() {
		return isSpecialSign;
	}

	public void setIsSpecialSign(String isSpecialSign) {
		this.isSpecialSign = isSpecialSign;
	}

	@Transient
	public List<CashFlow> getCashFlows() {
		return cashFlows;
	}

	public void setCashFlows(List<CashFlow> cashFlows) {
		this.cashFlows = cashFlows;
	}

	@Transient
	public List<AccrualsRecord> getAccRecords() {
		return accRecords;
	}

	public void setAccRecords(List<AccrualsRecord> accRecords) {
		this.accRecords = accRecords;
	}

	@Transient
	public String getCashFlowRemark() {
		return cashFlowRemark;
	}

	public void setCashFlowRemark(String cashFlowRemark) {
		this.cashFlowRemark = cashFlowRemark;
	}

	@Column(name="CUST_ID")
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Transient
	public String getCostRemark() {
		return costRemark;
	}

	public void setCostRemark(String costRemark) {
		this.costRemark = costRemark;
	}

	@Transient
	public WorkDayDto getWorkDayDto() {
		return workDayDto;
	}

	public void setWorkDayDto(WorkDayDto workDayDto) {
		this.workDayDto = workDayDto;
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
	
	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
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

	@Column(name="CONTRACT_METHOD_OTHER")
	public String getContractMethodOther() {
		return contractMethodOther;
	}

	public void setContractMethodOther(String contractMethodOther) {
		this.contractMethodOther = contractMethodOther;
	}
	@Column(name="RESPONSIBILITY")
	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	@Column(name="CONTRACT_COPIES")
	public String getContractCopies() {
		return contractCopies;
	}

	public void setContractCopies(String contractCopies) {
		this.contractCopies = contractCopies;
	}
	@Column(name="FISRT_PARTY_COPIES")
	public String getFisrtPartyCopies() {
		return fisrtPartyCopies;
	}

	public void setFisrtPartyCopies(String fisrtPartyCopies) {
		this.fisrtPartyCopies = fisrtPartyCopies;
	}
	@Column(name="SECOND_PARTY_COPIES")
	public String getSecondPartyCopies() {
		return secondPartyCopies;
	}

	public void setSecondPartyCopies(String secondPartyCopies) {
		this.secondPartyCopies = secondPartyCopies;
	}

	@Transient
	public String getLegalIncrementAmount() {
		return legalIncrementAmount;
	}
	public void setLegalIncrementAmount(String legalIncrementAmount) {
		this.legalIncrementAmount = legalIncrementAmount;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	@Transient
	public String getLegalHoseAmount() {
		return legalHoseAmount;
	}
	public void setLegalHoseAmount(String legalHoseAmount) {
		this.legalHoseAmount = legalHoseAmount;
	}

	@Transient
	public String getLegalContractAmount() {
		return legalContractAmount;
	}
	public void setLegalContractAmount(String legalContractAmount) {
		this.legalContractAmount = legalContractAmount;
	}
	@Transient
	public String getLegalBudgetCost() {
		return legalBudgetCost;
	}
	public void setLegalBudgetCost(String legalBudgetCost) {
		this.legalBudgetCost = legalBudgetCost;
	}

	@Column(name = "FOUR_PAYMENT")
	public String getFourPayment() {
		return fourPayment;
	}

	public void setFourPayment(String fourPayment) {
		this.fourPayment = fourPayment;
	}

	@Column(name = "PAYMENT_PAYTIO4")
	public String getPaymentPaytio4() {
		return paymentPaytio4;
	}

	public void setPaymentPaytio4(String paymentPaytio4) {
		this.paymentPaytio4 = paymentPaytio4;
	}

	@Transient
	public String getLegalFourPaymentAmount() {
		return legalFourPaymentAmount;
	}

	public void setLegalFourPaymentAmount(String legalFourPaymentAmount) {
		this.legalFourPaymentAmount = legalFourPaymentAmount;
	}

	@Transient
	public BigDecimal getBudgetTotalCost() {
		return budgetTotalCost;
	}

	public void setBudgetTotalCost(BigDecimal budgetTotalCost) {
		this.budgetTotalCost = budgetTotalCost;
	}

	@Transient
	public String getLegalBudgetTotalCost() {
		return legalBudgetTotalCost;
	}

	public void setLegalBudgetTotalCost(String legalBudgetTotalCost) {
		this.legalBudgetTotalCost = legalBudgetTotalCost;
	}

	@Transient
	public BigDecimal getGovAuditCost() {
		return govAuditCost;
	}

	public void setGovAuditCost(BigDecimal govAuditCost) {
		this.govAuditCost = govAuditCost;
	}
	
	
}