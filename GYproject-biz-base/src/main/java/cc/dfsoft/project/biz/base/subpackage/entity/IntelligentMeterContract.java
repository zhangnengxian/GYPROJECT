package cc.dfsoft.project.biz.base.subpackage.entity;

import cc.dfsoft.project.biz.base.contract.entity.PayType;
import cc.dfsoft.project.biz.base.contract.enums.ModifyStatusEnum;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 描述:智能表合同信息
 * @author liaoyq
 * @createTime 2017年9月16日
 */
@Entity
@Table(name="INTELLIGENT_METER_CONTRACT")
public class IntelligentMeterContract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8179160037337795067L;
	
	private String imcId;						//智能表合同ID
	private String imcNo;						//智能表合同编号
	private String projId;						//工程ID
	private String projNo;						//工程编号
	private String projName;					//工程名称
	private String conName;                     //合同名称
	private String projAddr;					//工程地点
	private String gasConNo;					//用气合同编号
	private String gasCustName;					//用气用户单位
	private String installNums;					//安装户数
	private BigDecimal unitCost;				//每户金额
	private BigDecimal totalCost;				//共计工程款
	private String deptName;					//部门名称
	private String grantRepresent;				//甲方授权代表人
	private String fPartyConAgent;				//甲方经办人
	private String fPartyConAgentId;			//甲方经办人ID
	private String fPartyBankName;				//甲方开户行
	private String fPartyBankAccount;			//甲方开户帐号
	private String fPartyTelNumber;				//甲方电话
	private String fPartyAddr;					//甲方地址
	private String corpId;						//分公司ID
	private String corpName;					//分公司名称
	private String deptId;						//部门ID
	private String sPartyId;					//乙方单位ID
	private String sPartyName;					//乙方单位名称
	private String sPartyLegalRepresent;		//乙方法定代表人
	private String sPartyAgent;					//乙方经办人
	private String sPartyTelNumber;				//乙方联系电话
	private String sPartyBankName;				//乙方开户行
	private String sPartyBankAccount;			//乙方开户帐号
	private String sPartyAddr;					//乙方地址
	private Date imcSignDate;					//签订日期
	private String isPrint;						//打印标记：0-未打印，1-已打印
	
	private String projScaleDes;				//工程规模
	private String projContent;				   //工程内容
	
	private String isIntelligentMeter;			//是否是智能表 --只读
	
	private String flag;						//推送标记:0-未推送，1-已推送
	private String payType;						//付款方式
	private BigDecimal firstPayment;			//首付款
	private BigDecimal incrementAmount;			//增值税金额
	private String invoiceType;					//发票类型
	private BigDecimal secondPayment;			//阶段款（2）
	private BigDecimal thirdPayment;			//阶段款（3）
	private String paymentRatio1;           //首付款比列
	private String paymentRatio2;           //中期付款比例
	private String paymentRatio3;           //后期付款比例
	private String increment;					//税率
	private List<PayType> payTypes;				//
	
	private String totalCostLegalAmount;		//共计工程款大写金额，合同打印时使用
	private String unitCostLegalAmount;			//每户单价大写金额，合同打印时使用
	private String firstPayMentLeaglAmount;		//首付款大写金额，合同打印使用
	private String secondPaymentLeaglAmount;	//阶段款（2）大写金额，合同打印使用
	private String thirdPaymentLeaglAmount;		//阶段款（3）大写金额，合同打印使用
	
	private String houseName;					//楼盘名称
	private String houseAddr;					//楼盘地址
	private Date predictCompleteDate;			//预计完成日期

	private String modifyStatus;			//null:无修改，0:修改审批中，1：修改审核已通过，2：修改审核未通过
	private String signBack;				//未通过标示-合同修改审核中控制底纹

	private String level;					//几级审核------只读属性，用于合同审核页面显示按钮
	private String projectTypeDes;			//工程类型描述---------
	private String contributionModeDes;		//出资方式描述---------
	private String mrAuditLevel;			//已审核级别----只读属性

	private String modifyStatusDes;			//修改状态--只读，列表显示
	
	private String projectType;				//工程类型-只读
	private String isCashFlow;				//已有收款标记-只读
	private String supplementClause;		//补充条款
	private String projStatusDes;		//补充条款

	public IntelligentMeterContract() {
		super();
	}

	@Id
	@Column(name="IMC_ID",unique = true, nullable = false)
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

	/*@Column(name="SECOND_PARTY_UNIT_ID")
	public String getCuId() {
		return cuId;
	}
	public void setCuId(String cuId) {
		this.cuId = cuId;
	}

	@Column(name="SECOND_PARTY_UNIT_NAME")
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}*/

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

	@Transient
	public String getProjScaleDes() {
		return projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}

	@Transient
	public String getIsIntelligentMeter() {
		return isIntelligentMeter;
	}

	public void setIsIntelligentMeter(String isIntelligentMeter) {
		this.isIntelligentMeter = isIntelligentMeter;
	}

	@Column(name="FLAG")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Transient
	public String getTotalCostLegalAmount() {
		return totalCostLegalAmount;
	}

	public void setTotalCostLegalAmount(String totalCostLegalAmount) {
		this.totalCostLegalAmount = totalCostLegalAmount;
	}

	@Transient
	public String getUnitCostLegalAmount() {
		return unitCostLegalAmount;
	}

	public void setUnitCostLegalAmount(String unitCostLegalAmount) {
		this.unitCostLegalAmount = unitCostLegalAmount;
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

	@Transient
	public List<PayType> getPayTypes() {
		return payTypes;
	}

	public void setPayTypes(List<PayType> payTypes) {
		this.payTypes = payTypes;
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

	@Transient
	public String getFirstPayMentLeaglAmount() {
		return firstPayMentLeaglAmount;
	}

	public void setFirstPayMentLeaglAmount(String firstPayMentLeaglAmount) {
		this.firstPayMentLeaglAmount = firstPayMentLeaglAmount;
	}

	@Column(name="SECOND_PARTY_ID")
	public String getsPartyId() {
		return sPartyId;
	}

	public void setsPartyId(String sPartyId) {
		this.sPartyId = sPartyId;
	}

	
	@Column(name="CON_NAME")
	public String getConName() {
		return conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
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
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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
	public String getMrAuditLevel() {
		return mrAuditLevel;
	}

	public void setMrAuditLevel(String mrAuditLevel) {
		this.mrAuditLevel = mrAuditLevel;
	}

	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Transient
	public String getIsCashFlow() {
		return isCashFlow;
	}

	public void setIsCashFlow(String isCashFlow) {
		this.isCashFlow = isCashFlow;
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

	@Transient
	public String getSecondPaymentLeaglAmount() {
		return secondPaymentLeaglAmount;
	}

	public void setSecondPaymentLeaglAmount(String secondPaymentLeaglAmount) {
		this.secondPaymentLeaglAmount = secondPaymentLeaglAmount;
	}

	@Transient
	public String getThirdPaymentLeaglAmount() {
		return thirdPaymentLeaglAmount;
	}

	public void setThirdPaymentLeaglAmount(String thirdPaymentLeaglAmount) {
		this.thirdPaymentLeaglAmount = thirdPaymentLeaglAmount;
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

	@Transient
	public String getProjStatusDes() {
		return projStatusDes;
	}
	public void setProjStatusDes(String projStatusDes) {
		this.projStatusDes = projStatusDes;
	}
}
