package cc.dfsoft.project.biz.base.contract.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the supplemental_contract_modifyhistory database table.
 * wang.hui.jun
 */
@Entity
@Table(name="supplemental_contract_log")
@NamedQuery(name="SupplementalContractModifyhistory.findAll", query="SELECT s FROM SupplementalContractModifyhistory s")
public class SupplementalContractModifyhistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SCM_ID")
	private String scmId;    //修改协议编号

	@Column(name="CUST_NAME")
	private String custName;   //客户名称

	@Column(name="CUST_PHONE")
	private String custPhone;   //客户联系电话

	@Column(name="GAS_COMP")
	private String gasComp;     //乙方燃气公司

	@Column(name="GAS_COMP_PHONE")
	private String gasCompPhone;  //乙方燃气公司联系电话

	@Column(name="HOUSE_ADDR")
	private String houseAddr;     // 地址

	@Column(name="HOUSE_AMOUNT")
	private BigDecimal houseAmount;    //每户金额

	@Column(name="HOUSE_NUM")
	private int houseNum;      //户数
    
	@Column(name="INCREMENT")
	private String increment;   //税率

	@Column(name="INVOICE_TYPE")
	private String invoiceType;   //发票类型
    
	@Column(name="MODIFIER")
	private String modifier;      //修改人

	@Column(name="PRICE_DOCUMENT")
	private String priceDocument;   //文件价格

	@Column(name="PROJ_NAME")
	private String projName;    //工程名称

	@Column(name="PROJ_NO")
	private String projNo;   //工程编号

	@Column(name="SC_AMOUNT")
	private BigDecimal scAmount;   //协议价

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SIGN_DATE")    //签订日期
	private Date signDate;

	@Column(name="SC_ID")
	private String scId; //补充协议Id
	
	@Column(name="ORL_ID")
	private String orlId;//操作记录ID
    
	@Column(name="MODIFY_STATE")
	private String modifyState;//修改前后标记
	
	@Column(name="PAY_TYPE")
	private String payType;                 //付款方式
	
	@Column(name="PAYMENT_RATIO1")
	private String paymentRatio1;           //首付比例
	
	@Column(name="PAYMENT_RATIO2")
	private String paymentRatio2;           //二期比例
	
	@Column(name="PAYMENT_RATIO3")
	private String paymentRatio3;           //三期比例
	
	@Column(name="FIRST_PAYMENT")
	private BigDecimal firstPayment;            //首付金额
	
	@Column(name="SECOND_PAYMENT")
	private BigDecimal secondPayment;           //二期付款金额
	
	@Column(name="THRID_PAYMENT")
	private BigDecimal thirdPayment;            //三期付款
	
	
	
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	
	public String getPaymentRatio1() {
		return paymentRatio1;
	}

	public void setPaymentRatio1(String paymentRatio1) {
		this.paymentRatio1 = paymentRatio1;
	}

	
	public String getPaymentRatio2() {
		return paymentRatio2;
	}

	public void setPaymentRatio2(String paymentRatio2) {
		this.paymentRatio2 = paymentRatio2;
	}

	
	public String getPaymentRatio3() {
		return paymentRatio3;
	}

	public void setPaymentRatio3(String paymentRatio3) {
		this.paymentRatio3 = paymentRatio3;
	}

	
	public BigDecimal getFirstPayment() {
		return firstPayment;
	}

	public void setFirstPayment(BigDecimal firstPayment) {
		this.firstPayment = firstPayment;
	}

	
	public BigDecimal getSecondPayment() {
		return secondPayment;
	}

	public void setSecondPayment(BigDecimal secondPayment) {
		this.secondPayment = secondPayment;
	}


	public BigDecimal getThirdPayment() {
		return thirdPayment;
	}

	public void setThirdPayment(BigDecimal thirdPayment) {
		this.thirdPayment = thirdPayment;
	}
	
	@Transient
	private String fromatDate;
	
	public String getFromatDate() {
		return fromatDate;
	}

	public void setFromatDate(String fromatDate) {
		this.fromatDate = fromatDate;
	}

	public SupplementalContractModifyhistory() {
	}
    
	public String getScId() {
		return scId;
	}

	public void setScId(String scId) {
		this.scId = scId;
	}

	public String getScmId() {
		return this.scmId;
	}

	public void setScmId(String scmId) {
		this.scmId = scmId;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustPhone() {
		return this.custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getGasComp() {
		return this.gasComp;
	}

	public void setGasComp(String gasComp) {
		this.gasComp = gasComp;
	}

	public String getGasCompPhone() {
		return this.gasCompPhone;
	}

	public void setGasCompPhone(String gasCompPhone) {
		this.gasCompPhone = gasCompPhone;
	}

	public String getHouseAddr() {
		return this.houseAddr;
	}

	public void setHouseAddr(String houseAddr) {
		this.houseAddr = houseAddr;
	}

	public BigDecimal getHouseAmount() {
		return this.houseAmount;
	}

	public void setHouseAmount(BigDecimal houseAmount) {
		this.houseAmount = houseAmount;
	}

	public int getHouseNum() {
		return this.houseNum;
	}

	public void setHouseNum(int houseNum) {
		this.houseNum = houseNum;
	}

	public String getIncrement() {
		return this.increment;
	}

	public void setIncrement(String increment) {
		this.increment = increment;
	}

	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getPriceDocument() {
		return this.priceDocument;
	}

	public void setPriceDocument(String priceDocument) {
		this.priceDocument = priceDocument;
	}

	public String getProjName() {
		return this.projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjNo() {
		return this.projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	public BigDecimal getScAmount() {
		return this.scAmount;
	}

	public void setScAmount(BigDecimal scAmount) {
		this.scAmount = scAmount;
	}

	public Date getSignDate() {
		return this.signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getOrlId() {
		return orlId;
	}

	public void setOrlId(String orlId) {
		this.orlId = orlId;
	}

	public String getModifyState() {
		return modifyState;
	}

	public void setModifyState(String modifyState) {
		this.modifyState = modifyState;
	}
	
}