package cc.dfsoft.project.biz.base.plan.dto;

import java.math.BigDecimal;

public class ConstructionPlanDTO {
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private String projName;				//工程名称
	private String projScaleDes;			//工程规模描述
	private String projAddr;				//工程地点
	private String custLegalRepresent;	    //甲方代表
	private BigDecimal contractAmount;		//合同价
	private BigDecimal downPayment;		//首付款
	
	private String custContact;				//用户联系人
	private String custPhone;				//用户联系电话
	private String costBudgeter;		        //预算员
	private String costMember;		        //造价 员
	private String duDesigner;			    //设计员
	private String duName;				    //设计单位
	
	private String duDirector;			    //设计单位负责人
	
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
	public String getProjAddr() {
		return projAddr;
	}
	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	public String getCustContact() {
		return custContact;
	}
	public void setCustContact(String custContact) {
		this.custContact = custContact;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustLegalRepresent() {
		return custLegalRepresent;
	}
	public void setCustLegalRepresent(String custLegalRepresent) {
		this.custLegalRepresent = custLegalRepresent;
	}
	public BigDecimal getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}
	
	public BigDecimal getDownPayment() {
		return downPayment;
	}
	public void setDownPayment(BigDecimal downPayment) {
		this.downPayment = downPayment;
	}

	public String getCostBudgeter() {
		return costBudgeter;
	}
	public void setCostBudgeter(String costBudgeter) {
		this.costBudgeter = costBudgeter;
	}
	public String getCostMember() {
		return costMember;
	}
	public void setCostMember(String costMember) {
		this.costMember = costMember;
	}
	public String getDuDesigner() {
		return duDesigner;
	}
	public void setDuDesigner(String duDesigner) {
		this.duDesigner = duDesigner;
	}
	public String getDuName() {
		return duName;
	}
	public void setDuName(String duName) {
		this.duName = duName;
	}
	public String getDuDirector() {
		return duDirector;
	}
	public void setDuDirector(String duDirector) {
		this.duDirector = duDirector;
	}
	
}
