package cc.dfsoft.project.biz.base.subpackage.dto;

import java.math.BigDecimal;
import java.util.List;

import cc.dfsoft.project.biz.base.subpackage.entity.FeeApplyList;
import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class PaymentApplyReq extends PageSortReq{
	
	private String paId;    			//申请单id
	private String projId;  			//工程id
	private Integer timeLimit;     		//限制完成的时间段
	private String stepId;				//步骤id
	private String auditState;			//审核状态
	
	private String applyNo;				//申请编号
	private String projNo;				//工程编号
	private String projName;			//工程名称
	private String projAddr;			//工程地点
	private String applyStartDate;		//申请开始日期
	private String applyEndDate;		//申请结束日期
	private String requestFoundsUnit;   //请款单位
	private String applyDeptName;		 //申请部门
	private String payType;             //付款类型
	
	private String feeType;				//费用类型 1 工程费 2设计费 3 监理费 4探伤费
	private String applyFeeType;		//费用类型 1 施工费 2第三方
	
	private String applyDeptId;			//申请部门id
	private String applyer;				//申请人
	private String applyerId;           //申请人ID
	private String applyRemark;			//申请备注
	private String isDispatch;			//是否派遣 0 未派遣 1 已派遣
	private String auditUnit;			//审核部门
	
	private List<FeeApplyList> list;	//付款申请记录
	private BigDecimal applyAmount;			//申请款
	private String projLtypeId;			//工程类型
	private String menuId;				//菜单ID
	private List<String> auditList;		//审核状态list
	public String getPaId() {
		return paId;
	}
	public void setPaId(String paId) {
		this.paId = paId;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public Integer getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getAuditState() {
		return auditState;
	}
	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getApplyStartDate() {
		return applyStartDate;
	}
	public void setApplyStartDate(String applyStartDate) {
		this.applyStartDate = applyStartDate;
	}
	public String getApplyEndDate() {
		return applyEndDate;
	}
	public void setApplyEndDate(String applyEndDate) {
		this.applyEndDate = applyEndDate;
	}
	public String getRequestFoundsUnit() {
		return requestFoundsUnit;
	}
	public void setRequestFoundsUnit(String requestFoundsUnit) {
		this.requestFoundsUnit = requestFoundsUnit;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getProjAddr() {
		return projAddr;
	}
	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getApplyDeptId() {
		return applyDeptId;
	}
	public void setApplyDeptId(String applyDeptId) {
		this.applyDeptId = applyDeptId;
	}
	public String getApplyDeptName() {
		return applyDeptName;
	}
	public void setApplyDeptName(String applyDeptName) {
		this.applyDeptName = applyDeptName;
	}
	public String getApplyer() {
		return applyer;
	}
	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}
	public String getApplyerId() {
		return applyerId;
	}
	public void setApplyerId(String applyerId) {
		this.applyerId = applyerId;
	}
	public String getApplyRemark() {
		return applyRemark;
	}
	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}
	public String getIsDispatch() {
		return isDispatch;
	}
	public void setIsDispatch(String isDispatch) {
		this.isDispatch = isDispatch;
	}
	public String getAuditUnit() {
		return auditUnit;
	}
	public void setAuditUnit(String auditUnit) {
		this.auditUnit = auditUnit;
	}
	public String getApplyFeeType() {
		return applyFeeType;
	}
	public void setApplyFeeType(String applyFeeType) {
		this.applyFeeType = applyFeeType;
	}
	public List<FeeApplyList> getList() {
		return list;
	}
	public void setList(List<FeeApplyList> list) {
		this.list = list;
	}
	public BigDecimal getApplyAmount() {
		return applyAmount;
	}
	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}
	public String getProjLtypeId() {
		return projLtypeId;
	}
	public void setProjLtypeId(String projLtypeId) {
		this.projLtypeId = projLtypeId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public List<String> getAuditList() {
		return auditList;
	}
	public void setAuditList(List<String> auditList) {
		this.auditList = auditList;
	}
	
}
