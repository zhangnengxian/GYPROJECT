package cc.dfsoft.project.biz.base.settlement.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.sql.rowset.serial.SerialException;

import cc.dfsoft.project.biz.base.baseinfo.dto.WorkDayDto;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

/**
 * SettlementDeclaration entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SETTLEMENT_DECLARATION")
public class SettlementDeclaration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5617777325838130565L;
	// Fields

	private String sdId;						//报审Id
	private String projId;						//项目id
	private String projNo;						//项目编号
	private String projName;					//项目名称
	private String projScaleDes;				//工程详述
	private String projAddr;					//工程地址
	private String cmoName;						//施工管理处
	private String suName;						//监理单位
	private BigDecimal suCost;					//监理费
	private BigDecimal scAmount;				//合同金额
	private BigDecimal sendDeclarationCost;		//送审金额
	private BigDecimal otherCost;				//其他金额
	private Clob cuPrincipal;					//施工管理处长  --施工单位负责人
	private Clob cuAudit;					    //分包负责人  --施工单位审核人
	private Date ocoDate;						//报审日期
	private BigDecimal firstDeclarationCost;	//初审金额
	private BigDecimal materialCutPayment;		//材料扣款--删除
	private BigDecimal auditMinusCost;			//核减金额
	private String auditMinusRate;				//核减率
	private Clob gasPrincipal;					//工程公司负责人 --删除
	private Clob costContractPrincipal;			//造价合同处负责人--删除
	private Clob firstAuditPerson;				//初审人 
	private Date firstAuditDate;				//初审日期
	private BigDecimal endDeclarationCost;		//终审金额
	private BigDecimal endAuditMinusCost;		//核减金额
	private String endAuditMinusRate;			//核减率
	private Clob costControlPrincipal;			//成本控制部负责人  -- 预结算组长
	private Integer costControlPrincipalStatus;	//成本控制部负责人  -- 预结算组长审核状态
	private String costControlPrincipalAdvice;	//成本控制部负责人  -- 预结算组长审核意见
	private Clob costControlReAudit;			//成本控制部复核人 --  市场发展部副部长审批
	private Integer costControlReAuditStatus;	//成本控制部复核人 --  市场发展部副部长审批状态
	private String costControlReAuditAdvice;	//成本控制部复核人 --  市场发展部副部长审批意见
	private Clob costControlAudit;				//成本控制部审核人  --  预结算员
	private Integer costControlAuditStatus;		//成本控制部审核人  --  预结算员审核状态
	private Date endDeclaraDate;				//终审日期
	private String otherExplain;				//其他需说明项 --删除
	private BigDecimal quantitiesTotal;			//工程量总计
	private String level;                       //审核级别 ---只用于页面展示
	private String mrAuditLevel;                //当前审核级别 ---只用于页面展示
	private List<Signature> sign;				//签字相关数据

	
	private  String legalAmount;				//打印结算审核表时用
	
	private String cuName;						//分包单位---只用于页面展示
	private String cuPhone;						//联系方式 ---只用于页面展示
	private String scNo;						//分包合同编号 ---只用于页面展示
	private Boolean overdue;			        //是否逾期 true逾期 false未逾期
	private Integer overDay;			        //逾期天数
	// Constructors
	
	private BigDecimal contractAmount;		    //只读属性--合同金额
	private String conNo;					   //合同编号 ---只用于页面展示
	
	private String isPrint;					   //是否打印标记     0 已打印,1未打印
	
	private String compiler;                   //编制人
	private String firstAuditer;				//初审人
	private String firstAuditerId;				//初审人ID
	private String finalAuditer;				//终审人 
	private String finalAuditerId;				//终审人 ID
	
	private String compilerPhone;				//编制人联系方式
	private String firstAuditerPhone;			//初审人联系方式
	
	private String projTypeDesc;				//工程类型描述 --只用于页面显示
	private String projContributionModeDes;		//工程出资类型--只用于页面显示
	private String contributionMode;			//出资方式--只读
	private String corpId;						//工程所属 分公司ID
	private String corpName;					//工程所属 分公司名称
	private String deptId;						//工程所属 业务部门ID
	private String deptName;					//工程所属  业务部门名称
	private String tenantId;					//编制人 租户ID
	private String compilerId;					//编制人ID
	private String compilerSign;				//编制人签字
	private BigDecimal materialsCost;			//主材费
	private BigDecimal firstMaterialsCost;		//初审主材费
	private BigDecimal endMaterialsCost;		//终审主材费
	private String remark;						//备注
	
	private String sscNo;						//分包补充协议 ---只用于页面展示
	private String drawingNo;					//图号---只用于页面展示
	private String pushStatus;					//推送状态
	
	private String auditStatus;
	
	private BigDecimal auxiliaryMaterialAmount;	//破路费
	private BigDecimal managementCost;			//协调费
	private BigDecimal recoveryCost;			//恢复费
	private BigDecimal jeevesCost;				//占道费
	private BigDecimal compensateCost;			//补偿费
	
	private BigDecimal auxiliaryMaterialAmountAudit;	//破路费-终审
	private BigDecimal managementCostAudit;			//协调费-终审
	private BigDecimal recoveryCostAudit;			//恢复费-终审
	private BigDecimal jeevesCostAudit;				//占道费-终审
	private BigDecimal compensateCostAudit;			//补偿费-终审
	
	private String deptDivide;					//部门划分
	
	private String projectType;					//工程类型
	private String budgeterAudit;				//分包预算员
	
	private String supConAmount;				//补充协议金额
	private String imcAmount;					//智能表合同金额
	private String totalAmount;					//工程预收款
	private String finalAmount;					//工程实收款
	private String subBudgetCost;				//施工预算审定价
	private String subAmount;					//工程已付款
	private String subApplyAmount;				//预付款	
	private String settlementer;				//结算审核预算员
	private String settlementerId;				//结算审核预算员ID
	
	private List<SupplementalContract> supContract;//补充协议-只读
	private String isHaveCM;					//未完成补充协议的变更数
	private String isHaveEngi;					//未审核通过的签证
	private WorkDayDto workDayDto;				//时限

	private String materialIsRegister;          //物资登记结果
	private String materialRemark;				//物资登记备注
	/** default constructor */
	public SettlementDeclaration() {
	}

	// Property accessors
	@Id
	@Column(name = "SD_ID", unique = true)
	public String getSdId() {
		return this.sdId;
	}

	public void setSdId(String sdId) {
		this.sdId = sdId;
	}

	@Column(name = "IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}
	
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
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

	@Column(name = "CMO_NAME")
	public String getCmoName() {
		return this.cmoName;
	}

	public void setCmoName(String cmoName) {
		this.cmoName = cmoName;
	}

	@Column(name = "SU_NAME")
	public String getSuName() {
		return this.suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	@Column(name = "SU_COST")
	public BigDecimal getSuCost() {
		return this.suCost;
	}

	public void setSuCost(BigDecimal suCost) {
		this.suCost = suCost;
	}

	@Column(name = "SC_AMOUNT")
	public BigDecimal getScAmount() {
		return this.scAmount;
	}

	public void setScAmount(BigDecimal scAmount) {
		this.scAmount = scAmount;
	}

	@Column(name = "SEND_DECLARATION_COST")
	public BigDecimal getSendDeclarationCost() {
		return this.sendDeclarationCost;
	}

	public void setSendDeclarationCost(BigDecimal sendDeclarationCost) {
		this.sendDeclarationCost = sendDeclarationCost;
	}

	@Column(name = "OTHER_COST")
	public BigDecimal getOtherCost() {
		return this.otherCost;
	}

	public void setOtherCost(BigDecimal otherCost) {
		this.otherCost = otherCost;
	}

	@Column(name = "CU_PRINCIPAL")
	public String getCuPrincipal() {
		return ClobUtil.ClobToString(this.cuPrincipal);
	}

	public void setCuPrincipal(String cuPrincipal) throws SerialException, SQLException {
		this.cuPrincipal = ClobUtil.stringToClob(cuPrincipal);
	}

	@Column(name = "CU_AUDIT")
	public String getCuAudit() {
		return ClobUtil.ClobToString(this.cuAudit);
	}

	public void setCuAudit(String cuAudit) throws SerialException, SQLException {
		this.cuAudit = ClobUtil.stringToClob(cuAudit);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OCO_DATE")
	public Date getOcoDate() {
		return this.ocoDate;
	}

	public void setOcoDate(Date ocoDate) {
		this.ocoDate = ocoDate;
	}

	@Column(name = "FIRST_DECLARATION_COST")
	public BigDecimal getFirstDeclarationCost() {
		return this.firstDeclarationCost;
	}

	public void setFirstDeclarationCost(BigDecimal firstDeclarationCost) {
		this.firstDeclarationCost = firstDeclarationCost;
	}

	@Column(name = "MATERIAL_CUT_PAYMENT")
	public BigDecimal getMaterialCutPayment() {
		return this.materialCutPayment;
	}

	public void setMaterialCutPayment(BigDecimal materialCutPayment) {
		this.materialCutPayment = materialCutPayment;
	}

	@Column(name = "AUDIT_MINUS_COST")
	public BigDecimal getAuditMinusCost() {
		return this.auditMinusCost;
	}

	public void setAuditMinusCost(BigDecimal auditMinusCost) {
		this.auditMinusCost = auditMinusCost;
	}

	@Column(name = "AUDIT_MINUS_RATE")
	public String getAuditMinusRate() {
		return this.auditMinusRate;
	}

	public void setAuditMinusRate(String auditMinusRate) {
		this.auditMinusRate = auditMinusRate;
	}

	@Column(name = "GAS_PRINCIPAL")
	public String getGasPrincipal() {
		return ClobUtil.ClobToString(this.gasPrincipal);
	}

	public void setGasPrincipal(String gasPrincipal) throws SerialException, SQLException {
		this.gasPrincipal = ClobUtil.stringToClob(gasPrincipal);
	}

	@Column(name = "COST_CONTRACT_PRINCIPAL")
	public String getCostContractPrincipal() {
		return ClobUtil.ClobToString(this.costContractPrincipal);
	}

	public void setCostContractPrincipal(String costContractPrincipal) throws SerialException, SQLException {
		this.costContractPrincipal = ClobUtil.stringToClob(costContractPrincipal);
	}

	@Column(name = "FIRST_AUDIT_PERSON")
	public String getFirstAuditPerson() {
		return ClobUtil.ClobToString(this.firstAuditPerson);
	}

	public void setFirstAuditPerson(String firstAuditPerson) throws SerialException, SQLException {
		this.firstAuditPerson = ClobUtil.stringToClob(firstAuditPerson);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FIRST_AUDIT_DATE")
	public Date getFirstAuditDate() {
		return this.firstAuditDate;
	}

	public void setFirstAuditDate(Date firstAuditDate) {
		this.firstAuditDate = firstAuditDate;
	}

	@Column(name = "END_DECLARATION_COST")
	public BigDecimal getEndDeclarationCost() {
		return this.endDeclarationCost;
	}

	public void setEndDeclarationCost(BigDecimal endDeclarationCost) {
		this.endDeclarationCost = endDeclarationCost;
	}

	@Column(name = "END_AUDIT_MINUS_COST")
	public BigDecimal getEndAuditMinusCost() {
		return this.endAuditMinusCost;
	}

	public void setEndAuditMinusCost(BigDecimal endAuditMinusCost) {
		this.endAuditMinusCost = endAuditMinusCost;
	}

	@Column(name = "END_AUDIT_MINUS_RATE")
	public String getEndAuditMinusRate() {
		return this.endAuditMinusRate;
	}

	public void setEndAuditMinusRate(String endAuditMinusRate) {
		this.endAuditMinusRate = endAuditMinusRate;
	}

	@Column(name = "COST_CONTROL_PRINCIPAL")
	public String getCostControlPrincipal() {
		return ClobUtil.ClobToString(this.costControlPrincipal);
	}

	public void setCostControlPrincipal(String costControlPrincipal) throws SerialException, SQLException {
		this.costControlPrincipal = ClobUtil.stringToClob(costControlPrincipal);
	}

	@Column(name = "COST_CONTROL_RE_AUDIT")
	public String getCostControlReAudit() {
		return ClobUtil.ClobToString(this.costControlReAudit);
	}

	public void setCostControlReAudit(String costControlReAudit) throws SerialException, SQLException {
		this.costControlReAudit = ClobUtil.stringToClob(costControlReAudit);
	}

	@Column(name = "COST_CONTROL_AUDIT")
	public String getCostControlAudit() {
		return ClobUtil.ClobToString(this.costControlAudit);
	}

	public void setCostControlAudit(String costControlAudit) throws SerialException, SQLException {
		this.costControlAudit = ClobUtil.stringToClob(costControlAudit);
	}
	
	@Column(name="COST_CONTROL_AUDIT_STATUS")
	public Integer getCostControlAuditStatus() {
		return costControlAuditStatus;
	}

	public void setCostControlAuditStatus(Integer costControlAuditStatus) {
		this.costControlAuditStatus = costControlAuditStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DECLARA_DATE")
	public Date getEndDeclaraDate() {
		return this.endDeclaraDate;
	}

	public void setEndDeclaraDate(Date endDeclaraDate) {
		this.endDeclaraDate = endDeclaraDate;
	}

	@Transient
	public String getLegalAmount() {
		return legalAmount;
	}

	public void setLegalAmount(String legalAmount) {
		this.legalAmount = legalAmount;
	}

	@Column(name = "OTHER_EXPLAIN")
	public String getOtherExplain() {
		return otherExplain;
	}

	public void setOtherExplain(String otherExplain) {
		this.otherExplain = otherExplain;
	}

	@Column(name = "QUANTITIES_TOTAL")
	public BigDecimal getQuantitiesTotal() {
		return quantitiesTotal;
	}

	public void setQuantitiesTotal(BigDecimal quantitiesTotal) {
		this.quantitiesTotal = quantitiesTotal;
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
	public List<Signature> getSign() {
		return sign;
	}


	public void setSig(List<Signature> sign) {
		this.sign = sign;
	}
	
	@Transient
	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	
	@Transient
	public String getCuPhone() {
		return cuPhone;
	}

	public void setCuPhone(String cuPhone) {
		this.cuPhone = cuPhone;
	}
	@Transient
	public String getScNo() {
		return scNo;
	}

	public void setScNo(String scNo) {
		this.scNo = scNo;
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

	public void setOverDay(Integer overDay) {
		this.overDay = overDay;
	}
	
	@Transient
	public BigDecimal getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}
	
	@Transient
	public String getConNo() {
		return conNo;
	}

	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	
	@Column(name = "COMPILER")
	public String getCompiler() {
		return compiler;
	}

	public void setCompiler(String compiler) {
		this.compiler = compiler;
	}
	
	@Column(name = "FIRST_AUDITER")
	public String getFirstAuditer() {
		return firstAuditer;
	}

	public void setFirstAuditer(String firstAuditer) {
		this.firstAuditer = firstAuditer;
	}
	
	@Column(name = "FINAL_AUDITER")
	public String getFinalAuditer() {
		return finalAuditer;
	}

	public void setFinalAuditer(String finalAuditer) {
		this.finalAuditer = finalAuditer;
	}
	
	@Column(name = "COMPILER_PHONE")
	public String getCompilerPhone() {
		return compilerPhone;
	}

	public void setCompilerPhone(String compilerPhone) {
		this.compilerPhone = compilerPhone;
	}
	
	@Column(name = "FIRST_AUDITER_PHONE")
	public String getFirstAuditerPhone() {
		return firstAuditerPhone;
	}

	public void setFirstAuditerPhone(String firstAuditerPhone) {
		this.firstAuditerPhone = firstAuditerPhone;
	}

	@Transient
	public String getProjTypeDesc() {
		return projTypeDesc;
	}

	public void setProjTypeDesc(String projTypeDesc) {
		this.projTypeDesc = projTypeDesc;
	}

	@Transient
	public String getProjContributionModeDes() {
		return projContributionModeDes;
	}

	public void setProjContributionModeDes(String projContributionModeDes) {
		this.projContributionModeDes = projContributionModeDes;
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

	@Column(name="DEPT_NAME")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name="TENANT_ID")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Column(name="COMPILER_ID")
	public String getCompilerId() {
		return compilerId;
	}

	public void setCompilerId(String compilerId) {
		this.compilerId = compilerId;
	}

	@Column(name="MATERIALS_COST")
	public BigDecimal getMaterialsCost() {
		return materialsCost;
	}

	public void setMaterialsCost(BigDecimal materialsCost) {
		this.materialsCost = materialsCost;
	}
	
	@Column(name="FIRST_MATERIALS_COST")
	public BigDecimal getFirstMaterialsCost() {
		return firstMaterialsCost;
	}

	public void setFirstMaterialsCost(BigDecimal firstMaterialsCost) {
		this.firstMaterialsCost = firstMaterialsCost;
	}

	@Column(name="END_MATERIALS_COST")
	public BigDecimal getEndMaterialsCost() {
		return endMaterialsCost;
	}

	public void setEndMaterialsCost(BigDecimal endMaterialsCost) {
		this.endMaterialsCost = endMaterialsCost;
	}

	@Column(name="COST_CONTROL_PRINCIPAL_STATUS")
	public Integer getCostControlPrincipalStatus() {
		return costControlPrincipalStatus;
	}

	public void setCostControlPrincipalStatus(Integer costControlPrincipalStatus) {
		this.costControlPrincipalStatus = costControlPrincipalStatus;
	}

	@Column(name="COST_CONTROL_RE_AUDIT_STATUS")
	public Integer getCostControlReAuditStatus() {
		return costControlReAuditStatus;
	}

	public void setCostControlReAuditStatus(Integer costControlReAuditStatus) {
		this.costControlReAuditStatus = costControlReAuditStatus;
	}

	@Column(name="COST_CONTROL_PRINCIPAL_ADVICE")
	public String getCostControlPrincipalAdvice() {
		return costControlPrincipalAdvice;
	}

	public void setCostControlPrincipalAdvice(String costControlPrincipalAdvice) {
		this.costControlPrincipalAdvice = costControlPrincipalAdvice;
	}

	@Column(name="COST_CONTROL_RE_AUDIT_ADVICE")
	public String getCostControlReAuditAdvice() {
		return costControlReAuditAdvice;
	}

	public void setCostControlReAuditAdvice(String costControlReAuditAdvice) {
		this.costControlReAuditAdvice = costControlReAuditAdvice;
	}

	@Transient
	public String getSscNo() {
		return sscNo;
	}

	public void setSscNo(String sscNo) {
		this.sscNo = sscNo;
	}
	@Transient
	public String getDrawingNo() {
		return drawingNo;
	}

	public void setDrawingNo(String drawingNo) {
		this.drawingNo = drawingNo;
	}

	@Column(name="COMPILER_SIGN")
	public String getCompilerSign() {
		return compilerSign;
	}

	public void setCompilerSign(String compilerSign) {
		this.compilerSign = compilerSign;
	}

	@Column(name="FIRST_AUDITER_ID")
	public String getFirstAuditerId() {
		return firstAuditerId;
	}

	public void setFirstAuditerId(String firstAuditerId) {
		this.firstAuditerId = firstAuditerId;
	}

	@Column(name="FINAL_AUDITER_ID")
	public String getFinalAuditerId() {
		return finalAuditerId;
	}

	public void setFinalAuditerId(String finalAuditerId) {
		this.finalAuditerId = finalAuditerId;
	}

	@Column(name="PUSH_STATUS")
	public String getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(String pushStatus) {
		this.pushStatus = pushStatus;
	}

	@Column(name="AUDIT_STATUS")
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	@Column(name="AUXILIARY_MATERIAL_AMOUNT")
	public BigDecimal getAuxiliaryMaterialAmount() {
		return auxiliaryMaterialAmount;
	}

	public void setAuxiliaryMaterialAmount(BigDecimal auxiliaryMaterialAmount) {
		this.auxiliaryMaterialAmount = auxiliaryMaterialAmount;
	}

	@Column(name="MANAGEMENT_COST")
	public BigDecimal getManagementCost() {
		return managementCost;
	}

	public void setManagementCost(BigDecimal managementCost) {
		this.managementCost = managementCost;
	}

	@Column(name="RECOVERY_COST")
	public BigDecimal getRecoveryCost() {
		return recoveryCost;
	}

	public void setRecoveryCost(BigDecimal recoveryCost) {
		this.recoveryCost = recoveryCost;
	}

	@Column(name="JEEVES_COST")
	public BigDecimal getJeevesCost() {
		return jeevesCost;
	}

	public void setJeevesCost(BigDecimal jeevesCost) {
		this.jeevesCost = jeevesCost;
	}

	@Column(name="COMPENSATE_COST")
	public BigDecimal getCompensateCost() {
		return compensateCost;
	}

	public void setCompensateCost(BigDecimal compensateCost) {
		this.compensateCost = compensateCost;
	}

	@Column(name="AUXILIARY_MATERIAL_AMOUNT_AUDIT")
	public BigDecimal getAuxiliaryMaterialAmountAudit() {
		return auxiliaryMaterialAmountAudit;
	}

	public void setAuxiliaryMaterialAmountAudit(BigDecimal auxiliaryMaterialAmountAudit) {
		this.auxiliaryMaterialAmountAudit = auxiliaryMaterialAmountAudit;
	}

	@Column(name="MANAGEMENT_COST_AUDIT")
	public BigDecimal getManagementCostAudit() {
		return managementCostAudit;
	}

	public void setManagementCostAudit(BigDecimal managementCostAudit) {
		this.managementCostAudit = managementCostAudit;
	}

	@Column(name="RECOVERY_COST_AUDIT")
	public BigDecimal getRecoveryCostAudit() {
		return recoveryCostAudit;
	}

	public void setRecoveryCostAudit(BigDecimal recoveryCostAudit) {
		this.recoveryCostAudit = recoveryCostAudit;
	}

	@Column(name="JEEVES_COST_AUDIT")
	public BigDecimal getJeevesCostAudit() {
		return jeevesCostAudit;
	}

	public void setJeevesCostAudit(BigDecimal jeevesCostAudit) {
		this.jeevesCostAudit = jeevesCostAudit;
	}

	@Column(name="COMPENSATE_COST_AUDIT")
	public BigDecimal getCompensateCostAudit() {
		return compensateCostAudit;
	}

	public void setCompensateCostAudit(BigDecimal compensateCostAudit) {
		this.compensateCostAudit = compensateCostAudit;
	}
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getBudgeterAudit() {
		return budgeterAudit;
	}

	public void setBudgeterAudit(String budgeterAudit) {
		this.budgeterAudit = budgeterAudit;
	}

	@Transient
	public String getSupConAmount() {
		return supConAmount;
	}

	public void setSupConAmount(String supConAmount) {
		this.supConAmount = supConAmount;
	}

	@Transient
	public String getImcAmount() {
		return imcAmount;
	}

	public void setImcAmount(String imcAmount) {
		this.imcAmount = imcAmount;
	}

	@Transient
	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Transient
	public String getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(String finalAmount) {
		this.finalAmount = finalAmount;
	}

	@Transient
	public String getSubBudgetCost() {
		return subBudgetCost;
	}

	public void setSubBudgetCost(String subBudgetCost) {
		this.subBudgetCost = subBudgetCost;
	}

	@Transient
	public String getSubAmount() {
		return subAmount;
	}

	public void setSubAmount(String subAmount) {
		this.subAmount = subAmount;
	}

	@Transient
	public String getSubApplyAmount() {
		return subApplyAmount;
	}

	public void setSubApplyAmount(String subApplyAmount) {
		this.subApplyAmount = subApplyAmount;
	}

	@Transient
	public String getSettlementer() {
		return settlementer;
	}

	public void setSettlementer(String settlementer) {
		this.settlementer = settlementer;
	}

	@Transient
	public String getSettlementerId() {
		return settlementerId;
	}

	public void setSettlementerId(String settlementerId) {
		this.settlementerId = settlementerId;
	}

	@Transient
	public String getContributionMode() {
		return contributionMode;
	}

	public void setContributionMode(String contributionMode) {
		this.contributionMode = contributionMode;
	}

	@Transient
	public List<SupplementalContract> getSupContract() {
		return supContract;
	}

	public void setSupContract(List<SupplementalContract> supContract) {
		this.supContract = supContract;
	}

	@Transient
	public String getIsHaveCM() {
		return isHaveCM;
	}

	public void setIsHaveCM(String isHaveCM) {
		this.isHaveCM = isHaveCM;
	}

	@Transient
	public WorkDayDto getWorkDayDto() {
		return workDayDto;
	}

	public void setWorkDayDto(WorkDayDto workDayDto) {
		this.workDayDto = workDayDto;
	}

	@Transient
	public String getIsHaveEngi() {
		return isHaveEngi;
	}

	public void setIsHaveEngi(String isHaveEngi) {
		this.isHaveEngi = isHaveEngi;
	}

	@Transient
	public String getMaterialIsRegister() {
		return materialIsRegister;
	}

	public void setMaterialIsRegister(String materialIsRegister) {
		this.materialIsRegister = materialIsRegister;
	}

	@Transient
	public String getMaterialRemark() {
		return materialRemark;
	}

	public void setMaterialRemark(String materialRemark) {
		this.materialRemark = materialRemark;
	}
	
}