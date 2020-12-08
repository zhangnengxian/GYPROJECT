package cc.dfsoft.project.biz.base.complete.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class GasApplyReq extends PageSortReq{

	private String projNo;				//项目编号
	private String projName;			//工程名称
	private String gasApplyCsr;			//通气申请人
	private String gasApplyTimeStart;	//通气申请时间开始
	private String gasApplyTimeEnd;		//通气申请时间结束
	private String confrimGasCsr;		//确认通气人
	private String confirmGasTimeStart;	//确认通气时间开始
	private String confirmGasTimeEnd;	//确认通气时间结束
	private String gasType;				//1正常通气、0特殊通气
	private String cuName;				//分包单位
	private String confrimState;		//反馈状态
	
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
	public String getGasApplyCsr() {
		return gasApplyCsr;
	}
	public void setGasApplyCsr(String gasApplyCsr) {
		this.gasApplyCsr = gasApplyCsr;
	}
	public String getGasApplyTimeStart() {
		return gasApplyTimeStart;
	}
	public void setGasApplyTimeStart(String gasApplyTimeStart) {
		this.gasApplyTimeStart = gasApplyTimeStart;
	}
	public String getGasApplyTimeEnd() {
		return gasApplyTimeEnd;
	}
	public void setGasApplyTimeEnd(String gasApplyTimeEnd) {
		this.gasApplyTimeEnd = gasApplyTimeEnd;
	}
	public String getConfrimGasCsr() {
		return confrimGasCsr;
	}
	public void setConfrimGasCsr(String confrimGasCsr) {
		this.confrimGasCsr = confrimGasCsr;
	}
	public String getConfirmGasTimeStart() {
		return confirmGasTimeStart;
	}
	public void setConfirmGasTimeStart(String confirmGasTimeStart) {
		this.confirmGasTimeStart = confirmGasTimeStart;
	}
	public String getConfirmGasTimeEnd() {
		return confirmGasTimeEnd;
	}
	public void setConfirmGasTimeEnd(String confirmGasTimeEnd) {
		this.confirmGasTimeEnd = confirmGasTimeEnd;
	}
	public String getGasType() {
		return gasType;
	}
	public void setGasType(String gasType) {
		this.gasType = gasType;
	}
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	public String getConfrimState() {
		return confrimState;
	}
	public void setConfrimState(String confrimState) {
		this.confrimState = confrimState;
	}
}
