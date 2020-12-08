package cc.dfsoft.project.biz.base.charge.dto;

import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;

public class ChargeDto {
	private String arId;		//应收应付记录id
	private CashFlow cashFlow;  //收付流水对象
	public  AccrualsRecord accrualsRecord;
	private String overChange;   //超收标志
	
	public String getArId() {
		return arId;
	}
	public void setArId(String arId) {
		this.arId = arId;
	}
	public CashFlow getCashFlow() {
		return cashFlow;
	}
	public void setCashFlow(CashFlow cashFlow) {
		this.cashFlow = cashFlow;
	}
	public AccrualsRecord getAccrualsRecord() {
		return accrualsRecord;
	}
	public void setAccrualsRecord(AccrualsRecord accrualsRecord) {
		this.accrualsRecord = accrualsRecord;
	}
	public String getOverChange() {
		return overChange;
	}
	public void setOverChange(String overChange) {
		this.overChange = overChange;
	}


}
