package cc.dfsoft.project.biz.base.subpackage.dto;

import java.util.List;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class SubContractQueryReq extends PageSortReq {

	private String scNo;					//分包合同编号
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private String projName;				//工程名称
	private String projAddr;				//工程地点
	private String cuName;					//乙方名称
	private String projStatusId;			//工程状态
	private String scSignDateStart;			//签订日期开始
	private String scSignDateEnd;			//签定日期结束
	private Integer timeLimit;     		    //限制完成的时间段
	private String  stepId;	                //步骤
	private List<String> projStuList;		//工程状态集合
	private String payType;					//支付方式
	private String isPrint;					//是否打印标记     0 已打印,1未打印
	private String modifyState;				//合同修改状态
	
	private String menuId;					//菜单id
	
	public Integer getTimeLimit() {
		return timeLimit;
	}

	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
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
	public String getScNo() {
		return scNo;
	}
	public void setScNo(String scNo) {
		this.scNo = scNo;
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
	public String getProjStatusId() {
		return projStatusId;
	}
	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
	}
	public String getScSignDateStart() {
		return scSignDateStart;
	}
	public void setScSignDateStart(String scSignDateStart) {
		this.scSignDateStart = scSignDateStart;
	}
	public String getScSignDateEnd() {
		return scSignDateEnd;
	}
	public void setScSignDateEnd(String scSignDateEnd) {
		this.scSignDateEnd = scSignDateEnd;
	}
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	public List<String> getProjStuList() {
		return projStuList;
	}
	public void setProjStuList(List<String> projStuList) {
		this.projStuList = projStuList;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getModifyState() {
		return modifyState;
	}

	public void setModifyState(String modifyState) {
		this.modifyState = modifyState;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	
}
