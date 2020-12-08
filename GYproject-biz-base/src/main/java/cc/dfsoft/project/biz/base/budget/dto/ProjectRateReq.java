package cc.dfsoft.project.biz.base.budget.dto;

import java.math.BigDecimal;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class ProjectRateReq extends PageSortReq{
	
	private String rateId;    //主键id
    private BigDecimal inspection; //监检费率
    private BigDecimal supervisor; //监理费率
    private BigDecimal store;      //储备金费率
    private String name;
    private BigDecimal value;
     
	public String getRateId() {
		return rateId;
	}
	public void setRateId(String rateId) {
		this.rateId = rateId;
	}
	public BigDecimal getInspection() {
		return inspection;
	}
	public void setInspection(BigDecimal inspection) {
		this.inspection = inspection;
	}
	public BigDecimal getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(BigDecimal supervisor) {
		this.supervisor = supervisor;
	}
	public BigDecimal getStore() {
		return store;
	}
	public void setStore(BigDecimal store) {
		this.store = store;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
     
}
