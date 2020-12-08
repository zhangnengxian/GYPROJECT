package cc.dfsoft.project.biz.base.contract.dto;

import java.util.List;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class ContractQueryReq extends PageSortReq{
	
	private String conNo;					//合同编号
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private String projName;				//工程名称
	private String projAddr;				//工程地点
	private String custName;				//甲方客户名称
	private String payType;					//付款方式
	private String fundSource;				//资源来源
	private String projStatusId;			//工程状态
	private String signDateStart;			//签定日期开始
	private String signDateEnd;				//签定日期结束
	private List<String> projStuList;		//工程状态集合
	private Integer timeLimit;     		    //限制完成的时间段
	private String  stepId;	                //步骤
	
	private String paNo;					//受理单号
	private String isPrint;					//是否打印标记     0 已打印,1未打印
	private String isPass;					//是否通过审核  1通过
	private String modifyStatus;			//null或3:无修改，0:修改审批中，1：修改审核已通过，2：修改审核未通过
	private String materialIsRegister;     //物资是否登记
	private String menuId;                 //菜单ID
	
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
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
	public String getProjStatusId() {
		return projStatusId;
	}
	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
	}
	public String getFundSource() {
		return fundSource;
	}
	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}
	public String getSignDateStart() {
		return signDateStart;
	}
	public void setSignDateStart(String signDateStart) {
		this.signDateStart = signDateStart;
	}
	public String getSignDateEnd() {
		return signDateEnd;
	}
	public void setSignDateEnd(String signDateEnd) {
		this.signDateEnd = signDateEnd;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public List<String> getProjStuList() {
		return projStuList;
	}
	public void setProjStuList(List<String> projStuList) {
		this.projStuList = projStuList;
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
	public String getPaNo() {
		return paNo;
	}
	public void setPaNo(String paNo) {
		this.paNo = paNo;
	}
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	public String getIsPass() {
		return isPass;
	}
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}
	public String getModifyStatus() {
		return modifyStatus;
	}
	public void setModifyStatus(String modifyStatus) {
		this.modifyStatus = modifyStatus;
	}
	public String getMaterialIsRegister() {
		return materialIsRegister;
	}
	public void setMaterialIsRegister(String materialIsRegister) {
		this.materialIsRegister = materialIsRegister;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	
}
