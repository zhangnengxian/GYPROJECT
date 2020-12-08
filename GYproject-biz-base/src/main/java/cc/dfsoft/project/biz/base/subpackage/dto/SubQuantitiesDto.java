package cc.dfsoft.project.biz.base.subpackage.dto;

import java.math.BigDecimal;
import java.util.List;

import cc.dfsoft.project.biz.base.subpackage.entity.SubQuantities;

public class SubQuantitiesDto{

	private String projId;			//工程id
	private String projNo;			//工程编号
	private String projName;		//工程名称
	private String projScaleDes;	//工程规模
	private BigDecimal submitAmount;//申报金额合计
	private String settlementState;	//结算状态
	private String sdId;			//结算ID
    private List<SubQuantities> list;
    private String flag;//0 暂存，1推送
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
	public List<SubQuantities> getList() {
		return list;
	}
	public void setList(List<SubQuantities> list) {
		this.list = list;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getProjScaleDes() {
		return projScaleDes;
	}
	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	public BigDecimal getSubmitAmount() {
		return submitAmount;
	}
	public void setSubmitAmount(BigDecimal submitAmount) {
		this.submitAmount = submitAmount;
	}
	public String getSettlementState() {
		return settlementState;
	}
	public void setSettlementState(String settlementState) {
		this.settlementState = settlementState;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getSdId() {
		return sdId;
	}
	public void setSdId(String sdId) {
		this.sdId = sdId;
	}
}
