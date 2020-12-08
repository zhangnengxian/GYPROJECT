package cc.dfsoft.project.biz.base.subpackage.entity;

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

import com.fr.function.DATETIME;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.subpackage.enums.CostTypeEnum;
import cc.dfsoft.uexpress.common.util.ClobUtil;

/**
 * SubBudget entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SUB_BUDGET")
public class SubBudget implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7650267859833435575L;
	// Fields
	
	private String sbId;							//主键ID
	private String projId;							//工程Id
	private String projNo;							//工程编号
	private String projName;						//工程名称
	private String projAddr;						//工程地点
	private String projScaleDes;					//工程规模
	private String cuName;							//施工单位
	private String cuLegalRepresent;				//施工单位负责人
	private String cuPhone;							//施工单位负责人电话
	private Date sbDate;							//预算日期
	private BigDecimal totalAmount;					//合计
	private BigDecimal mainMaterialAmount;			//主材费
	private BigDecimal auxiliaryMaterialAmount;		//破路费
	private String costType;						//造价类型
	private BigDecimal totalCost;					//清单计价总造价
	private BigDecimal totalQuota;					//定额总造价
	private BigDecimal laborCost;					//工程费
	private BigDecimal managementCost;				//协调费
	private BigDecimal profit;						//其他费用
	private String remark;							//备注
	private String auditState;						//审核状态
	private String isprint;							//是否打印
	private BigDecimal mainMaterialAmountList;		//清单计价主材费
	private BigDecimal mainMaterialAmountListAudit;	//审核清单主材费
	private BigDecimal totalCostAudit;				//审核清单总造价
	private BigDecimal totalQuotaAudit;				//审核定额总造价
	private BigDecimal mainMaterialAmountAudit;		//审核定额主材费
	
	private String suBudgeterId;					//分包预算员Id
	private String suBudgeter;						//分包预算员
	
	private List<SubQuantities> list;				//分包工程量
	private String flag;							//推送标记
	private String audit;							//审核保存标记
	private String costTypeDes;						//计价类型----页面列表显示
	private String uploadFlag;						//上传预算书标记----页面列表显示
	private String stepId;							//步骤ID----页面列表显示
	private BigDecimal contractAmount;				//安装合同金额
	private String deptDivide;						//部门划分-只读
	// Constructors
	
	private String projectType;						//工程类型-只读
	private Date operateDate;						//实际预算编制日期
	
	private Clob compilerSign;						//编制人签字
	private Clob cuAudit;					    	//审核人签字
	private Clob cuPrincipal;						//负责人签字
	private List<Signature> sign;					//签字相关数据
	private String operaterId;                      //操作人ID
	private String operater;                        //操作人姓名
	private String isConstructionProc;              //是否需要报审手续-只读:1-需要，0-不需要
	private String menuId;             				//菜单ID
	/** default constructor */
	public SubBudget() {
	}

	// Property accessors
	@Id
	@Column(name = "SB_ID", unique = true)
	public String getSbId() {
		return this.sbId;
	}

	public void setSbId(String sbId) {
		this.sbId = sbId;
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

	@Column(name = "PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}

	@Column(name = "CU_NAME")
	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}

	@Column(name = "CU_LEGAL_REPRESENT")
	public String getCuLegalRepresent() {
		return cuLegalRepresent;
	}

	public void setCuLegalRepresent(String cuLegalRepresent) {
		this.cuLegalRepresent = cuLegalRepresent;
	}

	@Column(name = "CU_PHONE")
	public String getCuPhone() {
		return cuPhone;
	}

	public void setCuPhone(String cuPhone) {
		this.cuPhone = cuPhone;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SB_DATE")
	public Date getSbDate() {
		return sbDate;
	}

	public void setSbDate(Date sbDate) {
		this.sbDate = sbDate;
	}

	@Column(name = "TOTAL_AMOUNT")
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "MAIN_MATERIAL_AMOUNT")
	public BigDecimal getMainMaterialAmount() {
		return mainMaterialAmount;
	}

	public void setMainMaterialAmount(BigDecimal mainMaterialAmount) {
		this.mainMaterialAmount = mainMaterialAmount;
	}

	@Column(name = "AUXILIARY_MATERIAL_AMOUNT")
	public BigDecimal getAuxiliaryMaterialAmount() {
		return auxiliaryMaterialAmount;
	}

	public void setAuxiliaryMaterialAmount(BigDecimal auxiliaryMaterialAmount) {
		this.auxiliaryMaterialAmount = auxiliaryMaterialAmount;
	}

	@Column(name = "COST_TYPE")
	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	@Transient
	public List<SubQuantities> getList() {
		return list;
	}

	public void setList(List<SubQuantities> list) {
		this.list = list;
	}

	@Transient
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "TOTAL_COST")
	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	@Column(name = "TOTAL_QUOTA")
	public BigDecimal getTotalQuota() {
		return totalQuota;
	}

	public void setTotalQuota(BigDecimal totalQuota) {
		this.totalQuota = totalQuota;
	}

	@Column(name = "LABOR_COST")
	public BigDecimal getLaborCost() {
		return laborCost;
	}

	public void setLaborCost(BigDecimal laborCost) {
		this.laborCost = laborCost;
	}

	@Column(name = "MANAGEMENT_COST")
	public BigDecimal getManagementCost() {
		return managementCost;
	}

	public void setManagementCost(BigDecimal managementCost) {
		this.managementCost = managementCost;
	}

	@Column(name = "PROFIT")
	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "AUDIT_STATE")
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	@Column(name = "ISPRINT")
	public String getIsprint() {
		return isprint;
	}

	public void setIsprint(String isprint) {
		this.isprint = isprint;
	}

	@Column(name = "MAIN_MATERIAL_AMOUNT_LIST")
	public BigDecimal getMainMaterialAmountList() {
		return mainMaterialAmountList;
	}

	public void setMainMaterialAmountList(BigDecimal mainMaterialAmountList) {
		this.mainMaterialAmountList = mainMaterialAmountList;
	}

	@Column(name = "MAIN_MATERIAL_AMOUNT_LIST_AUDIT")
	public BigDecimal getMainMaterialAmountListAudit() {
		return mainMaterialAmountListAudit;
	}

	public void setMainMaterialAmountListAudit(BigDecimal mainMaterialAmountListAudit) {
		this.mainMaterialAmountListAudit = mainMaterialAmountListAudit;
	}

	@Column(name = "TOTAL_COST_AUDIT")
	public BigDecimal getTotalCostAudit() {
		return totalCostAudit;
	}

	public void setTotalCostAudit(BigDecimal totalCostAudit) {
		this.totalCostAudit = totalCostAudit;
	}

	@Column(name = "TOTAL_QUOTA_AUDIT")
	public BigDecimal getTotalQuotaAudit() {
		return totalQuotaAudit;
	}

	public void setTotalQuotaAudit(BigDecimal totalQuotaAudit) {
		this.totalQuotaAudit = totalQuotaAudit;
	}

	@Column(name = "MAIN_MATERIAL_AMOUNT_AUDIT")
	public BigDecimal getMainMaterialAmountAudit() {
		return mainMaterialAmountAudit;
	}

	public void setMainMaterialAmountAudit(BigDecimal mainMaterialAmountAudit) {
		this.mainMaterialAmountAudit = mainMaterialAmountAudit;
	}

	@Column(name="SU_BUDGETER_ID")
	public String getSuBudgeterId() {
		return suBudgeterId;
	}

	public void setSuBudgeterId(String suBudgeterId) {
		this.suBudgeterId = suBudgeterId;
	}

	@Column(name="SU_BUDGETER")
	public String getSuBudgeter() {
		return suBudgeter;
	}

	public void setSuBudgeter(String suBudgeter) {
		this.suBudgeter = suBudgeter;
	}

	@Transient
	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	@Transient
	public String getCostTypeDes() {
		for(CostTypeEnum e: CostTypeEnum.values()) {
	   		if(e.getValue().equals(this.costType)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setCostTypeDes(String costTypeDes) {
		this.costTypeDes = costTypeDes;
	}

	@Transient
	public String getUploadFlag() {
		return uploadFlag;
	}

	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}

	@Transient
	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	@Transient
	public BigDecimal getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
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

	@Column(name="OPERATE_DATE")
	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	@Column(name="COMPILER_SIGN")
	public String getCompilerSign() {
		return ClobUtil.ClobToString(this.compilerSign);
	}

	public void setCompilerSign(String compilerSign) throws SerialException, SQLException {
		this.compilerSign = ClobUtil.stringToClob(compilerSign);
	}
	@Column(name="CU_AUDIT")
	public String getCuAudit() {
		return ClobUtil.ClobToString(this.cuAudit);
	}

	public void setCuAudit(String cuAudit) throws SerialException, SQLException {
		this.cuAudit = ClobUtil.stringToClob(cuAudit);
	}
	@Column(name="CU_PRINCIPAL")
	public String getCuPrincipal() {
		return ClobUtil.ClobToString(this.cuPrincipal);
	}

	public void setCuPrincipal(String cuPrincipal) throws SerialException, SQLException {
		this.cuPrincipal = ClobUtil.stringToClob(cuPrincipal);
	}
	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}

	
	@Column(name = "OPERATER_ID")
	public String getOperaterId() {
		return operaterId;
	}

	
	public void setOperaterId(String operaterId) {
		this.operaterId = operaterId;
	}

	
	@Column(name = "OPERATER")
	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

	@Transient
	public String getIsConstructionProc() {
		return isConstructionProc;
	}

	public void setIsConstructionProc(String isConstructionProc) {
		this.isConstructionProc = isConstructionProc;
	}

	@Transient
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}