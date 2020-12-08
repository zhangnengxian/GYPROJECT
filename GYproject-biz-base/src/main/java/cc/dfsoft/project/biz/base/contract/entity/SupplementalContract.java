
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

import org.hibernate.annotations.Columns;

import cc.dfsoft.project.biz.base.contract.enums.ModifyStatusEnum;

/**
 * SupplementalContract entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SUPPLEMENTAL_CONTRACT")
public class SupplementalContract implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5415207408403426993L;
	private String scId;					//协议ID
	private String scNo;					//协议编号
	private String conId;					//安装合同ID
	private String conNo;					//安装合同编号
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private String projName;				//工程名称
	private String projScaleDes;			//工程规模描述
	private String projAddr;				//工程地点
	private String custName;				//甲方客户名称
	private String custPhone;				//甲方联系方式
	private String gasComp;					//乙方燃气公司
	private String gasCompPhone;			//乙方联系方式
	private BigDecimal scAmount;			//协议价款
	private Date signDate;					//签订日期
	private String conAgent;				//经办人
	private String budgetId;				//预算id
	private Integer houseNum;				//户数
	private BigDecimal houseAmount;			//每户金额
	private String houseAddr;				//地址
	private String invoiceType;				//发票类型
	private String increment;				//税率
	private String priceDocument;			//价格文件
	private String scType;					//协议类型
	private String isPrint;					//是否打印标记     0 已打印,1未打印
	private String isAudit;					//是否审核通过 1推送 2审核通过
	private String modifyStatus;			//null:无修改，0:修改审批中，1：修改审核已通过，2：修改审核未通过
	private String modifyStatusDes;			//修改状态--只读，列表显示
	private String legalScAmount;			//打印协议时用
	private String projectTypeDes;			//工程类型描述--只读
	private String contributionModeDes;		//出资方式描述--只读
	private String deptName;			    //业务部门-只读
	private String flag;					//保存标志-只读
	private String isSignSuContrct;			//是否签订补充协议 1是 0 否 
	private String level;
	private String mrAuditLevel;			//已审核级别----只读属性，用于合同审核屏
	private Boolean overdue;				//是否逾期 true逾期 false未逾期
	private Integer overDay;				//逾期天数
	private String projectType;			    //工程类型
	private String payType;                 //付款方式
	private String paymentRatio1;           //首付比例
	private String paymentRatio2;           //二期比例
	private String paymentRatio3;           //三期比例
	private BigDecimal firstPayment;        //首付金额
	private BigDecimal secondPayment;       //二期付款金额
	private BigDecimal thirdPayment;        //三期付款
	
	// Constructors

	@Column(name="PAY_TYPE")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
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

	@Column(name="FIRST_PAYMENT")
	public BigDecimal getFirstPayment() {
		return firstPayment;
	}

	public void setFirstPayment(BigDecimal firstPayment) {
		this.firstPayment = firstPayment;
	}

	@Column(name="SECOND_PAYMENT")
	public BigDecimal getSecondPayment() {
		return secondPayment;
	}

	public void setSecondPayment(BigDecimal secondPayment) {
		this.secondPayment = secondPayment;
	}

	@Column(name="THRID_PAYMENT")
	public BigDecimal getThirdPayment() {
		return thirdPayment;
	}

	public void setThirdPayment(BigDecimal thirdPayment) {
		this.thirdPayment = thirdPayment;
	}

	/** default constructor */
	public SupplementalContract() {
	}

	// Property accessors
	@Id
	@Column(name = "SC_ID", unique = true)
	public String getScId() {
		return this.scId;
	}

	public void setScId(String scId) {
		this.scId = scId;
	}

	@Column(name = "SC_NO")
	public String getScNo() {
		return this.scNo;
	}

	public void setScNo(String scNo) {
		this.scNo = scNo;
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

	@Column(name = "SC_AMOUNT")
	public BigDecimal getScAmount() {
		return this.scAmount;
	}

	public void setScAmount(BigDecimal scAmount) {
		this.scAmount = scAmount;
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
	
	@Column(name = "BUDGET_ID")
	public String getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(String budgetId) {
		this.budgetId = budgetId;
	}
	
	@Column(name = "HOUSE_NUM")
	public Integer getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}

	@Column(name = "HOUSE_AMOUNT")
	public BigDecimal getHouseAmount() {
		return houseAmount;
	}

	public void setHouseAmount(BigDecimal houseAmount) {
		this.houseAmount = houseAmount;
	}

	@Column(name = "HOUSE_ADDR")
	public String getHouseAddr() {
		return houseAddr;
	}

	public void setHouseAddr(String houseAddr) {
		this.houseAddr = houseAddr;
	}

	@Column(name = "INVOICE_TYPE")
	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	@Column(name = "INCREMENT")
	public String getIncrement() {
		return increment;
	}

	public void setIncrement(String increment) {
		this.increment = increment;
	}

	@Column(name = "PRICE_DOCUMENT")
	public String getPriceDocument() {
		return priceDocument;
	}

	public void setPriceDocument(String priceDocument) {
		this.priceDocument = priceDocument;
	}

	@Transient
	public String getLegalScAmount() {
		return legalScAmount;
	}

	public void setLegalScAmount(String legalScAmount) {
		this.legalScAmount = legalScAmount;
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
	
	@Transient
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Transient
	public String getIsSignSuContrct() {
		return isSignSuContrct;
	}

	public void setIsSignSuContrct(String isSignSuContrct) {
		this.isSignSuContrct = isSignSuContrct;
	}

	@Column(name = "SC_TYPE")
	public String getScType() {
		return scType;
	}

	public void setScType(String scType) {
		this.scType = scType;
	}
	
	@Column(name="IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	@Transient
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	@Transient
	public String getMrAuditLevel() {
		return mrAuditLevel;
	}

	public void setMrAuditLevel(String mrAuditLevel) {
		this.mrAuditLevel = mrAuditLevel;
	}
	@Transient
	public Boolean getOverdue() {
		return overdue;
	}

	public void setOverdue(Boolean overdue) {
		this.overdue = overdue;
	}
	@Transient
	public Integer getOverDay() {
		return overDay;
	}

	public void setOverDay(int overDay) {
		this.overDay = overDay;
	}
	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	@Column(name="IS_AUDIT")
	public String getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
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
		if(this.modifyStatus==null || this.modifyStatus.equals("")){
			return ModifyStatusEnum.NO_MODIFY.getMessage();
		}else{
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
	

	
}