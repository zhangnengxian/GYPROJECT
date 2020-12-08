package cc.dfsoft.project.biz.base.subpackage.entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/9.
 */
@Entity
@Table(name = "intelligent_meter_contract_log")
public class IntelligentMeterContractLog {
    private String imclogId;
    private String imcId;
    private String imcNo;
    private String projId;
    private String projNo;
    private String projName;
	private String conName;                     //合同名称
    private String projAddr;
    private String gasConNo;
    private String gasCustName;
    private String installNums;
    private BigDecimal unitCost;
    private BigDecimal totalCost;
    private String deptName;
    private String grantRepresent;
    private String fPartyConAgent;				//甲方经办人
    private String fPartyConAgentId;			//甲方经办人ID
    private String fPartyBankName;				//甲方开户行
    private String fPartyBankAccount;			//甲方开户帐号
    private String fPartyTelNumber;				//甲方电话
    private String fPartyAddr;					//甲方地址
    private String corpId;
    private String corpName;
    private String deptId;
    private String sPartyId;					//乙方单位ID
    private String sPartyName;					//乙方单位名称
    private String sPartyLegalRepresent;		//乙方法定代表人
    private String sPartyAgent;					//乙方经办人
    private String sPartyTelNumber;				//乙方联系电话
    private String sPartyBankName;				//乙方开户行
    private String sPartyBankAccount;			//乙方开户帐号
    private String sPartyAddr;					//乙方地址
    private Date imcSignDate;
    private String isPrint;
    private String flag;
    private BigDecimal firstPayment;
    private BigDecimal incrementAmount;
    private String invoiceType;
    private BigDecimal secondPayment;
    private BigDecimal thirdPayment;
    private String increment;
    private String payType;
    private String houseName;
    private String houseAddr;
    private Date predictCompleteDate;
    private String modifyState;
    private String orlId;
	private String paymentRatio1;           //首付款比列
	private String paymentRatio2;           //中期付款比例
	private String paymentRatio3;           //后期付款比例
	private String projContent;           //工程内容
	private String supplementClause;       //补充条款

    @Id
    @Column(name = "IMCLOG_ID")
    public String getImclogId() {
        return imclogId;
    }

    public void setImclogId(String imclogId) {
        this.imclogId = imclogId;
    }

    @Basic
    @Column(name = "IMC_ID")
    public String getImcId() {
        return imcId;
    }

    public void setImcId(String imcId) {
        this.imcId = imcId;
    }

    @Column(name="IMC_NO")
    public String getImcNo() {
        return imcNo;
    }
    public void setImcNo(String imcNo) {
        this.imcNo = imcNo;
    }

    @Column(name="PROJ_ID")
    public String getProjId() {
        return projId;
    }
    public void setProjId(String projId) {
        this.projId = projId;
    }

    @Column(name="PROJ_NO")
    public String getProjNo() {
        return projNo;
    }
    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    @Column(name="PROJ_NAME")
    public String getProjName() {
        return projName;
    }
    public void setProjName(String projName) {
        this.projName = projName;
    }

    @Column(name="PROJ_ADDR")
    public String getProjAddr() {
        return projAddr;
    }
    public void setProjAddr(String projAddr) {
        this.projAddr = projAddr;
    }

    @Column(name="GAS_CON_NO")
    public String getGasConNo() {
        return gasConNo;
    }
    public void setGasConNo(String gasConNo) {
        this.gasConNo = gasConNo;
    }

    @Column(name="GAS_CUST_NAME")
    public String getGasCustName() {
        return gasCustName;
    }
    public void setGasCustName(String gasCustName) {
        this.gasCustName = gasCustName;
    }

    @Column(name="INSTALL_NUMS")
    public String getInstallNums() {
        return installNums;
    }
    public void setInstallNums(String installNums) {
        this.installNums = installNums;
    }

    @Column(name="UNIT_COST")
    public BigDecimal getUnitCost() {
        return unitCost;
    }
    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    @Column(name="TOTAL_COST")
    public BigDecimal getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Column(name="DEPT_NAME")
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Column(name="GRANT_REPRESENT")
    public String getGrantRepresent() {
        return grantRepresent;
    }
    public void setGrantRepresent(String grantRepresent) {
        this.grantRepresent = grantRepresent;
    }

    @Column(name="FIRST_PARTY_CON_AGENT")
    public String getfPartyConAgent() {
        return fPartyConAgent;
    }
    public void setfPartyConAgent(String fPartyConAgent) {
        this.fPartyConAgent = fPartyConAgent;
    }

    @Column(name="FIRST_PARTY_BANK_NAME")
    public String getfPartyBankName() {
        return fPartyBankName;
    }
    public void setfPartyBankName(String fPartyBankName) {
        this.fPartyBankName = fPartyBankName;
    }

    @Column(name="FIRST_PARTY_BANK_ACCOUNT")
    public String getfPartyBankAccount() {
        return fPartyBankAccount;
    }
    public void setfPartyBankAccount(String fPartyBankAccount) {
        this.fPartyBankAccount = fPartyBankAccount;
    }

    @Column(name="FIRST_PARTY_TEL_NUMBER")
    public String getfPartyTelNumber() {
        return fPartyTelNumber;
    }
    public void setfPartyTelNumber(String fPartyTelNumber) {
        this.fPartyTelNumber = fPartyTelNumber;
    }

    @Column(name="FIRST_PARTY_ADDR")
    public String getfPartyAddr() {
        return fPartyAddr;
    }
    public void setfPartyAddr(String fPartyAddr) {
        this.fPartyAddr = fPartyAddr;
    }

    @Column(name="CORP_ID")
    public String getCorpId() {
        return corpId;
    }
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    @Column(name="CORP_NAME")
    public String getCorpName() {
        return corpName;
    }
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    @Column(name="DEPT_ID")
    public String getDeptId() {
        return deptId;
    }
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Column(name="SECOND_PARTY_NAME")
    public String getsPartyName() {
        return sPartyName;
    }
    public void setsPartyName(String sPartyName) {
        this.sPartyName = sPartyName;
    }

    @Column(name="SECOND_PARTY_LEGAL_REPRESENT")
    public String getsPartyLegalRepresent() {
        return sPartyLegalRepresent;
    }
    public void setsPartyLegalRepresent(String sPartyLegalRepresent) {
        this.sPartyLegalRepresent = sPartyLegalRepresent;
    }

    @Column(name="SECOND_PARTY_AGENT")
    public String getsPartyAgent() {
        return sPartyAgent;
    }
    public void setsPartyAgent(String sPartyAgent) {
        this.sPartyAgent = sPartyAgent;
    }

    @Column(name="SECOND_PARTY_TEL_NUMBER")
    public String getsPartyTelNumber() {
        return sPartyTelNumber;
    }
    public void setsPartyTelNumber(String sPartyTelNumber) {
        this.sPartyTelNumber = sPartyTelNumber;
    }

    @Column(name="SECOND_PARTY_BANK_NAME")
    public String getsPartyBankName() {
        return sPartyBankName;
    }
    public void setsPartyBankName(String sPartyBankName) {
        this.sPartyBankName = sPartyBankName;
    }

    @Column(name="SECOND_PARTY_BANK_ACCOUNT")
    public String getsPartyBankAccount() {
        return sPartyBankAccount;
    }


    public void setsPartyBankAccount(String sPartyBankAccount) {
        this.sPartyBankAccount = sPartyBankAccount;
    }

    @Column(name="SECOND_PARTY_ADDR")
    public String getsPartyAddr() {
        return sPartyAddr;
    }
    public void setsPartyAddr(String sPartyAddr) {
        this.sPartyAddr = sPartyAddr;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="IMC_SIGN_DATE")
    public Date getImcSignDate() {
        return imcSignDate;
    }
    public void setImcSignDate(Date imcSignDate) {
        this.imcSignDate = imcSignDate;
    }

    @Column(name="IS_PRINT")
    public String getIsPrint() {
        return isPrint;
    }
    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint;
    }

    @Column(name="FLAG")
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Column(name="FIRST_PARTY_CON_AGENT_ID")
    public String getfPartyConAgentId() {
        return fPartyConAgentId;
    }

    public void setfPartyConAgentId(String fPartyConAgentId) {
        this.fPartyConAgentId = fPartyConAgentId;
    }

    @Column(name="PAY_TYPE")
    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Column(name = "FIRST_PAYMENT")
    public BigDecimal getFirstPayment() {
        return firstPayment;
    }

    public void setFirstPayment(BigDecimal firstPayment) {
        this.firstPayment = firstPayment;
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

    @Column(name="INCREMENT")
    public String getIncrement() {
        return increment;
    }

    public void setIncrement(String increment) {
        this.increment = increment;
    }

    @Column(name="SECOND_PARTY_ID")
    public String getsPartyId() {
        return sPartyId;
    }

    public void setsPartyId(String sPartyId) {
        this.sPartyId = sPartyId;
    }

    @Column(name="HOUSE_NAME")
    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    @Column(name="HOUSE_ADDR")
    public String getHouseAddr() {
        return houseAddr;
    }

    public void setHouseAddr(String houseAddr) {
        this.houseAddr = houseAddr;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="PREDICT_COMPLETE_DATE")
    public Date getPredictCompleteDate() {
        return predictCompleteDate;
    }

    public void setPredictCompleteDate(Date predictCompleteDate) {
        this.predictCompleteDate = predictCompleteDate;
    }

    @Basic
    @Column(name = "MODIFY_STATE")
    public String getModifyState() {
        return modifyState;
    }

    public void setModifyState(String modifyState) {
        this.modifyState = modifyState;
    }

    @Basic
    @Column(name = "ORL_ID")
    public String getOrlId() {
        return orlId;
    }

    public void setOrlId(String orlId) {
        this.orlId = orlId;
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
	@Column(name="CON_NAME")
	public String getConName() {
		return conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
	}
	@Column(name="PROJ_CONTENT")
	public String getProjContent() {
		return projContent;
	}

	public void setProjContent(String projContent) {
		this.projContent = projContent;
	}
	@Column(name = "SUPPLEMENT_CLAUSE")
	public String getSupplementClause() {
		return supplementClause;
	}

	public void setSupplementClause(String supplementClause) {
		this.supplementClause = supplementClause;
	}
}
