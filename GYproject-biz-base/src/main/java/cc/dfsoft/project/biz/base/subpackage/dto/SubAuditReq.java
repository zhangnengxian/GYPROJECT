package cc.dfsoft.project.biz.base.subpackage.dto;

import java.math.BigDecimal;
import java.util.List;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class SubAuditReq extends PageSortReq{
	private String scNo;
	private String projId;
	private String projNo;
	private String projName;
	private String signDateStart;			//签定日期开始
	private String signDateEnd;				//签定日期结束
	private Integer timeLimit;     		    //限制完成的时间段
	private String  stepId;	                //步骤
	private List<String> projStuList;		//工程状态集合
	private String projStatusId;			//工程状态

	private String operateDateStart;//实际签订开始时间
	private String operateDateEnd;//实际签订结束时间

	public String getOperateDateStart() {
		return operateDateStart;
	}

	public void setOperateDateStart(String operateDateStart) {
		this.operateDateStart = operateDateStart;
	}

	public String getOperateDateEnd() {
		return operateDateEnd;
	}

	public void setOperateDateEnd(String operateDateEnd) {
		this.operateDateEnd = operateDateEnd;
	}

	private String deptId;//部门ID

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getProjStatusId() {
		return projStatusId;
	}
	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
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
	public List<String> getProjStuList() {
		return projStuList;
	}
	public void setProjStuList(List<String> projStuList) {
		this.projStuList = projStuList;
	}
	
	
	
}
