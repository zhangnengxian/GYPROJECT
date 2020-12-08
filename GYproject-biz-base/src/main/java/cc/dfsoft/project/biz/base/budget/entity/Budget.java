package cc.dfsoft.project.biz.base.budget.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.contract.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.project.entity.Signature;

/**
 * Budget entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BUDGET")
public class Budget implements Serializable {

	// Fields
	private static final long serialVersionUID = 7029013652829338068L;
	private String budgetId;			//主键ID
	private String projId;				//工程ID
	private String projNo;				//工程编号
	private BigDecimal budgetTotalCost;	//总造价 -- xx
	private BigDecimal civilCost;		//土建工程费用
	private BigDecimal yardInstallCost;	//庭院安装费用-- xx
	private BigDecimal homeInstallCost;	//户内安装费用-- xx
	private BigDecimal inspectionCost;	//监检费    --带气作业费
	private BigDecimal suCost;			//监理费
	private BigDecimal storeCost;		//工储备资金
	private BigDecimal shantyTownCost;	//棚户区安装费 -- xx
	private String remark;                //备注
	
	private String budgeter;			//预算员
	private String budgeterId;			//预算员ID
	
	private String projName;			//工程名称--
	private String projScaleDes;		//工程规模--
	private String projAddr;			//工程地点--

	
	private String budgetTypeId;		//预算类型 -- xx
	private String cmId;				//变更记录Id-- xx
	private String mcType;				//变更类型-- xx
	private Date budgetAdjustDate;      //变更日期-- xx
	private BigDecimal boilerMeter; 	//锅炉仪表-- xx
	private BigDecimal technology;		//工艺-- xx
	private BigDecimal yardEarthwork;	//庭院土方-- xx
	private String     isUpdatePro;		//庭院土方-- xx
	private String projLtypeId;			//工程大类 用于预算调整页面显示
	private String contractType;		//合同类型-- xx
	private String adjustmentMethod;	//包干调整方法-- xx
	private String adjustMethod;		//可调调整方法-- xx
	
	private String paNo;				//受理单号-- xx
	private String surveyer;		    //勘察人
	private String designer;	        //设计人
	
	private String isSignSuContrct;    //是否签订补充协议 1是 0 否 -- xx
	private String isSignSuContrctDes; //只读
	
	private List<Signature> sign;    //签字相关数据

	/**增加字段*/
	private String drawName;		//`DRAW_NAME` varchar(255) DEFAULT NULL COMMENT '附件名称',
	private String menuDes; 		//菜单描述   --
	private String stepId;			//菜单ID  --
	private String drawUrl;          //简图路径  --
	private String custContact;		//客户联系人 --
	private String custName;		//客户名称--
	private String custPhone;		//客户联系人电话--
	private String projectTypeDes;	//工程类型描述--
	private String contributionModeDes;//出资方式描述--
	private String corpName;		//分公司名称
	private String deptName;		//业务部门名称
	private String corpId;			//分公司ID
	private String deptId;			//业务部门ID
	private Date budgetDate;		//预算登记日期
	
	private BigDecimal authorizedCost;	 //审定价格
	private String auditorId;			//预算审核人ID
	private String auditorName;			//预算审核人
	private String tenantId;			//租户ID
	
	private BigDecimal materialCost;	//主材费
	private BigDecimal designCost;		//设计费
	private BigDecimal unforeseenCost; //设计费

	private String gasTimes;			//带气次数
	private String isAudit;				//--只读属性
	
	private String projectType;			//工程类型-只读
	private String auditResult;			//审核结果-只读
	private String auditOpinion;		//审核意见-只读
	private String menuId;				//菜单ID

	private String isPrint;  //是否打印
	private BigDecimal annunciatorCost;//报警器费用
	// Constructors

	@Column(name = "GAS_TIMES")
	public String getGasTimes() {
		return gasTimes;
	}

	public void setGasTimes(String gasTimes) {
		this.gasTimes = gasTimes;
	}

	/** default constructor */
	public Budget() {
	}

	// Property accessors
	@Id
	@Column(name = "BUDGET_ID", unique = true)
	public String getBudgetId() {
		return this.budgetId;
	}

	public void setBudgetId(String budgetId) {
		this.budgetId = budgetId;
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

	@Column(name = "BUDGET_TOTAL_COST")
	public BigDecimal getBudgetTotalCost() {
		return this.budgetTotalCost;
	}

	public void setBudgetTotalCost(BigDecimal budgetTotalCost) {
		this.budgetTotalCost = budgetTotalCost;
	}

	@Column(name = "CIVIL_COST")
	public BigDecimal getCivilCost() {
		return this.civilCost;
	}

	public void setCivilCost(BigDecimal civilCost) {
		this.civilCost = civilCost;
	}

	@Column(name = "YARD_INSTALL_COST")
	public BigDecimal getYardInstallCost() {
		return this.yardInstallCost;
	}

	public void setYardInstallCost(BigDecimal yardInstallCost) {
		this.yardInstallCost = yardInstallCost;
	}

	@Column(name = "HOME_INSTALL_COST")
	public BigDecimal getHomeInstallCost() {
		return this.homeInstallCost;
	}

	public void setHomeInstallCost(BigDecimal homeInstallCost) {
		this.homeInstallCost = homeInstallCost;
	}

	@Column(name = "INSPECTION_COST")
	public BigDecimal getInspectionCost() {
		return this.inspectionCost;
	}

	public void setInspectionCost(BigDecimal inspectionCost) {
		this.inspectionCost = inspectionCost;
	}

	@Column(name = "SU_COST")
	public BigDecimal getSuCost() {
		return this.suCost;
	}

	public void setSuCost(BigDecimal suCost) {
		this.suCost = suCost;
	}

	@Column(name = "STORE_COST")
	public BigDecimal getStoreCost() {
		return this.storeCost;
	}

	public void setStoreCost(BigDecimal storeCost) {
		this.storeCost = storeCost;
	}

	@Column(name = "SHANTY_TOWN_COST")
	public BigDecimal getShantyTownCost() {
		return this.shantyTownCost;
	}

	public void setShantyTownCost(BigDecimal shantyTownCost) {
		this.shantyTownCost = shantyTownCost;
	}

	
	@Column(name = "CM_ID")
	public String getCmId() {
		return cmId;
	}

	public void setCmId(String cmId) {
		this.cmId = cmId;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "BUDGET_ADJUST_DATE")
	public Date getBudgetAdjustDate() {
		return budgetAdjustDate;
	}

	public void setBudgetAdjustDate(Date budgetAdjustDate) {
		this.budgetAdjustDate = budgetAdjustDate;
	}

	@Column(name = "BUDGET_TYPE_ID")
	public String getBudgetTypeId() {
		return budgetTypeId;
	}

	public void setBudgetTypeId(String budgetTypeId) {
		this.budgetTypeId = budgetTypeId;
	}
	
	@Transient
	public String getProjLtypeId() {
		return projLtypeId;
	}

	public void setProjLtypeId(String projLtypeId) {
		this.projLtypeId = projLtypeId;
	}
	@Column(name = "BOILER_METER")
	public BigDecimal getBoilerMeter() {
		return boilerMeter;
	}

	public void setBoilerMeter(BigDecimal boilerMeter) {
		this.boilerMeter = boilerMeter;
	}
	@Column(name = "TECHNOLOGY")
	public BigDecimal getTechnology() {
		return technology;
	}

	public void setTechnology(BigDecimal technology) {
		this.technology = technology;
	}
	@Column(name = "YARD_EARTHWORK")
	public BigDecimal getYardEarthwork() {
		return yardEarthwork;
	}

	public void setYardEarthwork(BigDecimal yardEarthwork) {
		this.yardEarthwork = yardEarthwork;
	}
	@Transient
	public String getIsUpdatePro() {
		return isUpdatePro;
	}

	public void setIsUpdatePro(String isUpdatePro) {
		this.isUpdatePro = isUpdatePro;
	}
	@Column(name = "MC_TYPE")
	public String getMcType() {
		return mcType;
	}

	public void setMcType(String mcType) {
		this.mcType = mcType;
	}
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name="BUDGETER")
	public String getBudgeter() {
		return budgeter;
	}

	public void setBudgeter(String budgeter) {
		this.budgeter = budgeter;
	}
	
	@Column(name="BUDGETER_ID")
	public String getBudgeterId() {
		return budgeterId;
	}

	public void setBudgeterId(String budgeterId) {
		this.budgeterId = budgeterId;
	}
	
	@Transient
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}
	
	@Transient
	public String getProjScaleDes() {
		return projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	
	@Transient
	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	
	@Column(name = "CONTRACT_TYPE")
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
	
	@Column(name = "ADJUSTMENT_METHOD")
	public String getAdjustmentMethod() {
		return adjustmentMethod;
	}

	public void setAdjustmentMethod(String adjustmentMethod) {
		this.adjustmentMethod = adjustmentMethod;
	}
	
	@Column(name = "ADJUST_METHOD")
	public String getAdjustMethod() {
		return adjustMethod;
	}
	
	public void setAdjustMethod(String adjustMethod) {
		this.adjustMethod = adjustMethod;
	}
	
	@Transient
	public String getPaNo() {
		return paNo;
	}

	public void setPaNo(String paNo) {
		this.paNo = paNo;
	}
	
	@Transient
	public String getSurveyer() {
		return surveyer;
	}

	public void setSurveyer(String surveyer) {
		this.surveyer = surveyer;
	}
	
	@Transient
	public String getDesigner() {
		return designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}
	
	@Column(name = "IS_SIGN_SU_CONTRCT")
	public String getIsSignSuContrct() {
		return isSignSuContrct;
	}

	public void setIsSignSuContrct(String isSignSuContrct) {
		this.isSignSuContrct = isSignSuContrct;
	}

	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}

	@Column(name="DRAW_NAME")
	public String getDrawName() {
		return drawName;
	}

	public void setDrawName(String drawName) {
		this.drawName = drawName;
	}

	@Transient
	public String getMenuDes() {
		return menuDes;
	}

	public void setMenuDes(String menuDes) {
		this.menuDes = menuDes;
	}

	@Transient
	public String getDrawUrl() {
		return drawUrl;
	}

	public void setDrawUrl(String drawUrl) {
		this.drawUrl = drawUrl;
	}
	
	@Transient
	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	
	@Column(name="AUDITOR_ID")
	public String getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	@Column(name="AUDITOR_NAME")
	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	@Transient
	public String getCustContact() {
		return custContact;
	}

	public void setCustContact(String custContact) {
		this.custContact = custContact;
	}

	@Transient
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Transient
	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
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

	@Column(name="CORP_NAME")
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	@Column(name="DEPT_NAME")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Column(name="DEPT_ID")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name="TENANT_ID")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BUDGET_DATE")
	public Date getBudgetDate() {
		return budgetDate;
	}

	public void setBudgetDate(Date budgetDate) {
		this.budgetDate = budgetDate;
	}

	@Column(name="MATERIAL_COST")
	public BigDecimal getMaterialCost() {
		return materialCost;
	}

	public void setMaterialCost(BigDecimal materialCost) {
		this.materialCost = materialCost;
	}

	@Column(name="DESIGN_COST")
	public BigDecimal getDesignCost() {
		return designCost;
	}

	public void setDesignCost(BigDecimal designCost) {
		this.designCost = designCost;
	}

	@Column(name="UNFORESEEN_COST")
	public BigDecimal getUnforeseenCost() {
		return unforeseenCost;
	}

	public void setUnforeseenCost(BigDecimal unforeseenCost) {
		this.unforeseenCost = unforeseenCost;
	}
	@Column(name="AUTHORIZED_COST")
	public BigDecimal getAuthorizedCost() {
		return authorizedCost;
	}

	public void setAuthorizedCost(BigDecimal authorizedCost) {
		this.authorizedCost = authorizedCost;
	}
	
	@Transient
	public String getIsSignSuContrctDes() {
		for(IsSignEnum e: IsSignEnum.values()) {
	   		if(e.getValue().equals(this.isSignSuContrct)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setIsSignSuContrctDes(String isSignSuContrctDes) {
		this.isSignSuContrctDes = isSignSuContrctDes;
	}
	
	@Transient
	public String getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	@Transient
	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	@Transient
	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	@Transient
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Column(name="IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	@Column(name="ANNUNCIATOR_COST")
	public BigDecimal getAnnunciatorCost() {
		return annunciatorCost;
	}

	public void setAnnunciatorCost(BigDecimal annunciatorCost) {
		this.annunciatorCost = annunciatorCost;
	}
}