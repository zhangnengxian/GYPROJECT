package cc.dfsoft.project.biz.base.contract.dto;

import cc.dfsoft.project.biz.base.project.entity.ScaleDetail;
import cc.dfsoft.uexpress.common.dto.PageSortReq;

import java.math.BigDecimal;
import java.util.List;

/**
 * 更新工程造价界面
 * @author Administrator
 *
 */
public class ProjectCostReq extends PageSortReq {
	private String projId;
	private String projNo;
	private BigDecimal  confirmTotalCost;
	private String changeReason;
	private String costRemark;
	private String backReason;
	private String backRemarks;         //退单备注
	private String note;                //备注
	//private String contractType;		//合同类型
	//private String adjustmentMethod;	//调整方法
	//private String adjustMethod;	//可调调整方法
	private List<ScaleDetail> scaleDetails; //多条规模明细
	private String projLtypeId;//工程规模id
	
	private String projCostAuditType; 	//工程造价审批范围
	private String reduceGasTimes; 	//减免次数
	private String projCostConfig;	//造价配置：有值，则不填入造价审批范围，按照doc_Type表获取，否则的话，按照造价审批范围
	private String stepId;//步骤id

	public String getReduceGasTimes() {
		return reduceGasTimes;
	}

	public void setReduceGasTimes(String reduceGasTimes) {
		this.reduceGasTimes = reduceGasTimes;
	}

	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public BigDecimal getConfirmTotalCost() {
		return confirmTotalCost;
	}
	public void setConfirmTotalCost(BigDecimal confirmTotalCost) {
		this.confirmTotalCost = confirmTotalCost;
	}
	public String getChangeReason() {
		return changeReason;
	}
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public String getCostRemark() {
		return costRemark;
	}

	public void setCostRemark(String costRemark) {
		this.costRemark = costRemark;
	}

	public String getBackReason() {
		return backReason;
	}
	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	/*public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public String getAdjustmentMethod() {
		return adjustmentMethod;
	}
	public void setAdjustmentMethod(String adjustmentMethod) {
		this.adjustmentMethod = adjustmentMethod;
	}
	public String getAdjustMethod() {
		return adjustMethod;
	}
	public void setAdjustMethod(String adjustMethod) {
		this.adjustMethod = adjustMethod;
	}*/
	public List<ScaleDetail> getScaleDetails() {
		return scaleDetails;
	}
	public void setScaleDetails(List<ScaleDetail> scaleDetails) {
		this.scaleDetails = scaleDetails;
	}
	public String getProjLtypeId() {
		return projLtypeId;
	}
	public void setProjLtypeId(String projLtypeId) {
		this.projLtypeId = projLtypeId;
	}
	public String getProjCostAuditType() {
		return projCostAuditType;
	}
	public void setProjCostAuditType(String projCostAuditType) {
		this.projCostAuditType = projCostAuditType;
	}

	public String getBackRemarks() {
		return backRemarks;
	}

	public void setBackRemarks(String backRemarks) {
		this.backRemarks = backRemarks;
	}

	public String getProjCostConfig() {
		return projCostConfig;
	}

	public void setProjCostConfig(String projCostConfig) {
		this.projCostConfig = projCostConfig;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
}
