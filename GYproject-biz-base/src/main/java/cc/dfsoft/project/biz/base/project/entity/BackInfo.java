package cc.dfsoft.project.biz.base.project.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * 回退后历史数据保存
 * @author fuliwei
 *
 */
@Entity
@Table(name = "BACK_INFO")
public class BackInfo implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2556600332510818938L;
	
	
	private String biId;					//主键id
    private String projId;					//工程ID
    private String projNo;					//工程编号
    private String projName;				//工程名称
    
    private Date duCompleteDate;			//设计完成日期
	private String designDrawingNo;			//设计图号
	private BigDecimal designDrawingCopies; //图纸份数
	private BigDecimal designDrawingSheets; //每份张数
	private Date ocoDate;					//设计委托日期
	private String acquisitionDays;         //设计委托天数
    
	private String  budgeter;				//预算员
	private String  budgeterId;				//预算员ID
	private BigDecimal  budgetTotalCost;	//预算总造价
	private Date  budgetDate;				//预算日期
	
	private BigDecimal  confirmTotalCost;	//确定造价
	private String   changeReason;         //变动原因
	private String  costRemark;            //造价备注
	private Date  affirmCostDate;	   		//确定日期
	private String   costMember; 			// 造价员
	private String   costMemberId; 			// 造价员ID
	
	private BigDecimal authorizedCost;		//审定价格
	private Date authorizedCostDate;		//审定价登记日期
	private String gacStaffId;      		//审定价登记人ID
	
	private BigDecimal contractAmount;		//合同金额
	private Date signDate;					//签订日期
	private String operator;               //经办人
	
	
	private String budgeterAudit;		   	//施工预算审核员
	private String budgeterAuditId;	 		//施工预算审核员Id
	
	private BigDecimal firstSubmitAmount;	//初始施工预算合计清单计价
	private BigDecimal submitAmount;		//施工预算合计清单计价
	
	private String settlementer;			//结算员
	private String settlementerId;			//结算员ID
	private Date settlementDate;           //结算时间
	private BigDecimal settlementEndCost;	//结算审定金额
	private BigDecimal settlementSendCost;   //结算报送价
	
	
	private Date createDate;           		//生成时间
	private String creater;           		//生成人
	private String createrId;           	//生成人ID
	private String budgetMethod;           	//预算方式
	private String whiteMapRegisterRemark;  //白图登记备注
	
	@Id
    @Column(name="BI_ID", unique = true)
	public String getBiId() {
		return biId;
	}
	public void setBiId(String biId) {
		this.biId = biId;
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
	@Temporal(TemporalType.DATE)
	@Column(name="DU_COMPLETE_DATE")
	public Date getDuCompleteDate() {
		return duCompleteDate;
	}
	public void setDuCompleteDate(Date duCompleteDate) {
		this.duCompleteDate = duCompleteDate;
	}
	
	@Column(name="DESIGN_DRAWING_NO")
	public String getDesignDrawingNo() {
		return designDrawingNo;
	}
	public void setDesignDrawingNo(String designDrawingNo) {
		this.designDrawingNo = designDrawingNo;
	}
	
	@Column(name="DESIGN_DRAWING_COPIES")
	public BigDecimal getDesignDrawingCopies() {
		return designDrawingCopies;
	}
	public void setDesignDrawingCopies(BigDecimal designDrawingCopies) {
		this.designDrawingCopies = designDrawingCopies;
	}
	
	@Column(name="DESIGN_DRAWING_SHEETS")
	public BigDecimal getDesignDrawingSheets() {
		return designDrawingSheets;
	}
	public void setDesignDrawingSheets(BigDecimal designDrawingSheets) {
		this.designDrawingSheets = designDrawingSheets;
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
	
	@Column(name="BUDGET_TOTAL_COST")
	public BigDecimal getBudgetTotalCost() {
		return budgetTotalCost;
	}
	public void setBudgetTotalCost(BigDecimal budgetTotalCost) {
		this.budgetTotalCost = budgetTotalCost;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="BUDGET_DATE")
	public Date getBudgetDate() {
		return budgetDate;
	}
	public void setBudgetDate(Date budgetDate) {
		this.budgetDate = budgetDate;
	}
	
	@Column(name="CONFIRM_TOTAL_COST")
	public BigDecimal getConfirmTotalCost() {
		return confirmTotalCost;
	}
	public void setConfirmTotalCost(BigDecimal confirmTotalCost) {
		this.confirmTotalCost = confirmTotalCost;
	}
	
	@Column(name="CHANGE_REASON")
	public String getChangeReason() {
		return changeReason;
	}
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
	@Column(name="COST_REMARK")
	public String getCostRemark() {
		return costRemark;
	}
	public void setCostRemark(String costRemark) {
		this.costRemark = costRemark;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="AFFIRM_COST_DATE")
	public Date getAffirmCostDate() {
		return affirmCostDate;
	}
	public void setAffirmCostDate(Date affirmCostDate) {
		this.affirmCostDate = affirmCostDate;
	}
	
	@Column(name="COST_MEMBER")
	public String getCostMember() {
		return costMember;
	}
	public void setCostMember(String costMember) {
		this.costMember = costMember;
	}
	
	@Column(name="COST_MEMBER_ID")
	public String getCostMemberId() {
		return costMemberId;
	}
	public void setCostMemberId(String costMemberId) {
		this.costMemberId = costMemberId;
	}
	
	@Column(name="AUTHORIZED_COST")
	public BigDecimal getAuthorizedCost() {
		return authorizedCost;
	}
	public void setAuthorizedCost(BigDecimal authorizedCost) {
		this.authorizedCost = authorizedCost;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="AUTHORIZED_COST_DATE")
	public Date getAuthorizedCostDate() {
		return authorizedCostDate;
	}
	public void setAuthorizedCostDate(Date authorizedCostDate) {
		this.authorizedCostDate = authorizedCostDate;
	}
	
	@Column(name="GAC_STAFF_ID")
	public String getGacStaffId() {
		return gacStaffId;
	}
	public void setGacStaffId(String gacStaffId) {
		this.gacStaffId = gacStaffId;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name="CREATER")
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	@Column(name="CREATER_ID")
	public String getCreaterId() {
		return createrId;
	}
	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}
	
	@Column(name="CONTRACT_AMOUNT")
	public BigDecimal getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="SIGN_DATE")
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	
	@Column(name="OPERATOR")
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@Column(name="BUDGETER_AUDIT")
	public String getBudgeterAudit() {
		return budgeterAudit;
	}
	public void setBudgeterAudit(String budgeterAudit) {
		this.budgeterAudit = budgeterAudit;
	}
	
	@Column(name="BUDGETER_AUDIT_ID")
	public String getBudgeterAuditId() {
		return budgeterAuditId;
	}
	public void setBudgeterAuditId(String budgeterAuditId) {
		this.budgeterAuditId = budgeterAuditId;
	}
	
	
	@Column(name="FIRST_SUBMIT_AMOUNT")
	public BigDecimal getFirstSubmitAmount() {
		return firstSubmitAmount;
	}
	public void setFirstSubmitAmount(BigDecimal firstSubmitAmount) {
		this.firstSubmitAmount = firstSubmitAmount;
	}
	
	@Column(name="SUBMIT_AMOUNT")
	public BigDecimal getSubmitAmount() {
		return submitAmount;
	}
	public void setSubmitAmount(BigDecimal submitAmount) {
		this.submitAmount = submitAmount;
	}
	@Column(name="SETTLEMENTER")
	public String getSettlementer() {
		return settlementer;
	}
	public void setSettlementer(String settlementer) {
		this.settlementer = settlementer;
	}
	
	@Column(name="SETTLEMENTER_ID")
	public String getSettlementerId() {
		return settlementerId;
	}
	public void setSettlementerId(String settlementerId) {
		this.settlementerId = settlementerId;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="SETTLEMENT_DATE")
	public Date getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="OCO_DATE")
	public Date getOcoDate() {
		return ocoDate;
	}
	public void setOcoDate(Date ocoDate) {
		this.ocoDate = ocoDate;
	}
	@Column(name="ACQUISITION_DAYS")
	public String getAcquisitionDays() {
		return acquisitionDays;
	}
	public void setAcquisitionDays(String acquisitionDays) {
		this.acquisitionDays = acquisitionDays;
	}
	@Column(name="SETTLEMENT_END_COST")
	public BigDecimal getSettlementEndCost() {
		return settlementEndCost;
	}
	public void setSettlementEndCost(BigDecimal settlementEndCost) {
		this.settlementEndCost = settlementEndCost;
	}
	@Column(name="SETTLEMENT_SEND_COST")
	public BigDecimal getSettlementSendCost() {
		return settlementSendCost;
	}
	public void setSettlementSendCost(BigDecimal settlementSendCost) {
		this.settlementSendCost = settlementSendCost;
	}
	@Column(name="BUDGET_METHOD")
	public String getBudgetMethod() {
		return budgetMethod;
	}
	public void setBudgetMethod(String budgetMethod) {
		this.budgetMethod = budgetMethod;
	}
	@Column(name="WHITE_MAP_REGISTER_REMARK")
	public String getWhiteMapRegisterRemark() {
		return whiteMapRegisterRemark;
	}
	public void setWhiteMapRegisterRemark(String whiteMapRegisterRemark) {
		this.whiteMapRegisterRemark = whiteMapRegisterRemark;
	}
	
}
