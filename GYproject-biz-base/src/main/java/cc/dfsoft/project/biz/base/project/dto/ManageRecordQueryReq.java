package cc.dfsoft.project.biz.base.project.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 管理记录查询
 * @author pengtt
 * @createTime 2016-06-27
 *
 */
public class ManageRecordQueryReq extends PageSortReq{
	
	private String projId;				//工程id
	private String projNo;				//工程编号
	private String projStatus;			//工程状态
	private String stepId;				//步骤ID
	private String businessOrderId;		//业务单ID
	private String settlementState;		//报审确认状态
	private String level;				//审核级别（-1表示审核级别不能为空）
	private String flag;               //审核记录是否作废
	private String mrResult;			//审核结果
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
	public String getProjStatus() {
		return projStatus;
	}
	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getBusinessOrderId() {
		return businessOrderId;
	}
	public void setBusinessOrderId(String businessOrderId) {
		this.businessOrderId = businessOrderId;
	}
	public String getSettlementState() {
		return settlementState;
	}
	public void setSettlementState(String settlementState) {
		this.settlementState = settlementState;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMrResult() {
		return mrResult;
	}
	public void setMrResult(String mrResult) {
		this.mrResult = mrResult;
	}
	
}
