package cc.dfsoft.project.biz.base.subpackage.entity;

import cc.dfsoft.project.biz.base.plan.enums.IsDispatchEnum;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.subpackage.enums.FeeTypeEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 付款申请
 * @author fuliwei
 *
 */
@Entity
@Table(name = "PAYMENT_APPLY")
public class PaymentApply implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2820058793472251970L;
	
	
	private String paId;					//付款申请ID   
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private String projName;				//工程名称
	private String projAddr;			    //工程地点
	private String projScaleDes;			//工程规模
	
	private String projectTypeDes;			//工程类型
	private String contributionModeDes;		//出资方式
	private String corpName;			    //分公司
	private String corpId;			        //分公司id
	private String deptName;			    //业务部门-只读
	
	private String applyNo;			        //申请单号
	private String applyer;					//申请人
	private String applyerId;               //申请人ID
	private Date applyDate;			    	//申请时间
	private String applyDeptName;		    //申请部门
	private String requestFoundsUnit;		//请款单位
	private String applyReason;			    //申请原因
	private String applyReasonDes;			//申请原因--只读
	
	private String sdType;			    	//结算方式
	private BigDecimal contractAmount;		//合同款(主合同、补充该协议)
	private BigDecimal payAmount;			//已付合同款
	private BigDecimal surplusAmount;		//剩余合同款
	private BigDecimal scAmount;			//分包合同款
	private BigDecimal payScAmount;			//已付分包款
	private BigDecimal applyAmount;			//申请款
	private String invoiceNo;			    //发票号
	
	
	private String mrAuditLevel; 				//已审核级别----只读属性
	private String level;        				//几级审核------只读属性
	private Boolean overdue;	 				//是否逾期 true逾期 false未逾期
	
	
	private String auditState;					//审核状态
	private String auditStateDes;				//审核状态-只读
	
	private String signBack;					//审核状态
	private String tenantId;					//租户id
	private String orgId;						//组织id
	private String projectType;					//工程类型

	private String payType;   					//请款单位

	private BigDecimal suAmount;   				//申请监理费--作废
	private BigDecimal deAmount;   				//申请检测费--作废
	
	private String feeType;						//1 工程费 2设计费 3 监理费 4探伤费
	
	private String feeTypeDes;					//只读
	
	private String applyFeeType;				//费用类型 1 施工费 2第三方
	
	private String applyDeptId;					//申请部门id
	private String applyRemark;					//申请备注
	
	private String isDispatch;					//是否派遣 0 未派遣 1 已派遣
	private String isDispatchDes;				//是否派遣 -只读
	private String  paAuditerId;				//审核人id
	private String  paAuditer;					//审核人
	private String auditUnit;					//审核部门
	
	private String devide;						//
	private String menuId;						//菜单ID
	private String legalApplyAmount;      		//请款金额大写
	private String deleteReson;      		//作废原因
	private String deletePer;      			//作废人
	private Date deleteDate;      			//作废日期
	private String contractDes;      		//用户合同描述
	private String receiveMoneyDes;      	//用户已缴费描述
	private String payMoneyDes;      		//已申请付费描述


	public PaymentApply(){
		
	}
	
	@Id
	@Column(name = "PA_ID", unique = true)
	public String getPaId() {
		return paId;
	}

	public void setPaId(String paId) {
		this.paId = paId;
	}
	
	@Column(name = "PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	
	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}
	
	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	
	@Column(name = "PROJECT_TYPE_DES")
	public String getProjectTypeDes() {
		return projectTypeDes;
	}

	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}
	
	@Column(name = "CONTRIBUTION_MODE_DES")
	public String getContributionModeDes() {
		return contributionModeDes;
	}

	public void setContributionModeDes(String contributionModeDes) {
		this.contributionModeDes = contributionModeDes;
	}
	
	@Transient
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	@Transient
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Column(name = "APPLY_NO")
	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "APPLY_DATE")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	@Column(name = "APPLY_DEPT_NAME")
	public String getApplyDeptName() {
		return applyDeptName;
	}

	public void setApplyDeptName(String applyDeptName) {
		this.applyDeptName = applyDeptName;
	}
	
	@Column(name = "REQUEST_FOUNDS_UNIT")
	public String getRequestFoundsUnit() {
		return requestFoundsUnit;
	}

	public void setRequestFoundsUnit(String requestFoundsUnit) {
		this.requestFoundsUnit = requestFoundsUnit;
	}
	
	@Column(name = "APPLY_REASON")
	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	
	@Column(name = "SD_TYPE")
	public String getSdType() {
		return sdType;
	}

	public void setSdType(String sdType) {
		this.sdType = sdType;
	}
	
	@Column(name = "CONTRACT_AMOUNT")
	public BigDecimal getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}
	
	@Column(name = "PAY_AMOUNT")
	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	
	@Column(name = "SURPLUS_AMOUNT")
	public BigDecimal getSurplusAmount() {
		return surplusAmount;
	}

	public void setSurplusAmount(BigDecimal surplusAmount) {
		this.surplusAmount = surplusAmount;
	}

	@Column(name = "SC_AMOUNT")
	public BigDecimal getScAmount() {
		return scAmount;
	}

	public void setScAmount(BigDecimal scAmount) {
		this.scAmount = scAmount;
	}

	@Column(name = "APPLY_AMOUNT")
	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	@Column(name = "INVOICE_NO")
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	@Column(name = "PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	
	@Column(name = "PAY_SC_AMOUNT")
	public BigDecimal getPayScAmount() {
		return payScAmount;
	}

	public void setPayScAmount(BigDecimal payScAmount) {
		this.payScAmount = payScAmount;
	}
	
	@Column(name = "APPLYER")
	public String getApplyer() {
		return applyer;
	}

	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}
	
	
	@Column (name = "APPLYER_ID")
	public String getApplyerId() {
		return applyerId;
	}

	public void setApplyerId(String applyerId) {
		this.applyerId = applyerId;
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
	
	@Column(name = "AUDIT_STATE")
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	
	@Transient
	public String getAuditStateDes() {
		for(AuditResultEnum e: AuditResultEnum.values()) {
	   		if(e.getValue().equals(this.auditState)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setAuditStateDes(String auditStateDes) {
		this.auditStateDes = auditStateDes;
	}
	
	@Column(name = "SIGN_BACK")
	public String getSignBack() {
		return signBack;
	}

	public void setSignBack(String signBack) {
		this.signBack = signBack;
	}
	
	@Column(name = "TENANT_ID")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	@Column(name = "ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(name = "PAY_TYPE")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Column(name = "SU_AMOUNT")
	public BigDecimal getSuAmount() {
		return suAmount;
	}

	public void setSuAmount(BigDecimal suAmount) {
		this.suAmount = suAmount;
	}

	@Column(name = "DE_AMOUNT")
	public BigDecimal getDeAmount() {
		return deAmount;
	}

	public void setDeAmount(BigDecimal deAmount) {
		this.deAmount = deAmount;
	}
	
	@Column(name = "FEE_TYPE")
	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	
	@Column(name = "APPLY_DEPT_ID")
	public String getApplyDeptId() {
		return applyDeptId;
	}

	public void setApplyDeptId(String applyDeptId) {
		this.applyDeptId = applyDeptId;
	}
	
	@Column(name = "APPLY_REMARK")
	public String getApplyRemark() {
		return applyRemark;
	}

	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}
	
	@Column(name = "IS_DISPATCH")
	public String getIsDispatch() {
		return isDispatch;
	}

	public void setIsDispatch(String isDispatch) {
		this.isDispatch = isDispatch;
	}
	
	@Column(name = "PA_AUDITER_ID")
	public String getPaAuditerId() {
		return paAuditerId;
	}

	public void setPaAuditerId(String paAuditerId) {
		this.paAuditerId = paAuditerId;
	}

	@Transient
	public String getApplyReasonDes() {
		for(CollectionTypeEnum e: CollectionTypeEnum.values()) {
	   		if(e.getValue().equals(this.applyReason)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setApplyReasonDes(String applyReasonDes) {
		this.applyReasonDes = applyReasonDes;
	}
	
	@Transient
	public String getIsDispatchDes() {
		for(IsDispatchEnum e: IsDispatchEnum.values()) {
	   		if(e.getValue().equals(this.isDispatch)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setIsDispatchDes(String isDispatchDes) {
		this.isDispatchDes = isDispatchDes;
	}
	
	@Column(name = "AUDIT_UNIT")
	public String getAuditUnit() {
		return auditUnit;
	}

	public void setAuditUnit(String auditUnit) {
		this.auditUnit = auditUnit;
	}
	
	@Transient
	public String getPaAuditer() {
		return paAuditer;
	}

	public void setPaAuditer(String paAuditer) {
		this.paAuditer = paAuditer;
	}
	@Transient
	public String getDevide() {
		return devide;
	}

	public void setDevide(String devide) {
		this.devide = devide;
	}
	
	@Column(name = "APPLY_FEE_TYPE")
	public String getApplyFeeType() {
		return applyFeeType;
	}

	public void setApplyFeeType(String applyFeeType) {
		this.applyFeeType = applyFeeType;
	}
	
	@Transient
	public String getFeeTypeDes() {
		for(FeeTypeEnum e: FeeTypeEnum.values()) {
	   		if(e.getValue().equals(this.feeType)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setFeeTypeDes(String feeTypeDes) {
		this.feeTypeDes = feeTypeDes;
	}


	@Column(name = "DELETE_RESON")

	public String getDeleteReson() {
		return deleteReson;
	}
	public void setDeleteReson(String deleteReson) {
		this.deleteReson = deleteReson;
	}

	@Column(name = "DELETEPER")
	public String getDeletePer() {
		return deletePer;
	}
	public void setDeletePer(String deletePer) {
		this.deletePer = deletePer;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DELETE_DATE")
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	@Transient
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Transient
	public String getLegalApplyAmount() {
		return legalApplyAmount;
	}

	public void setLegalApplyAmount(String legalApplyAmount) {
		this.legalApplyAmount = legalApplyAmount;
	}

	@Transient
	public String getContractDes() {
		return contractDes;
	}

	public void setContractDes(String contractDes) {
		this.contractDes = contractDes;
	}

	@Transient
	public String getReceiveMoneyDes() {
		return receiveMoneyDes;
	}

	public void setReceiveMoneyDes(String receiveMoneyDes) {
		this.receiveMoneyDes = receiveMoneyDes;
	}

	@Transient
	public String getPayMoneyDes() {
		return payMoneyDes;
	}

	public void setPayMoneyDes(String payMoneyDes) {
		this.payMoneyDes = payMoneyDes;
	}
	
}
