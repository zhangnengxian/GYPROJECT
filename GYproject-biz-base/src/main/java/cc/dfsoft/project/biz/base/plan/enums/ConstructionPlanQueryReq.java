package cc.dfsoft.project.biz.base.plan.enums;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

import java.util.List;

/**
 * 计划记录查询
 * @author pengtt
 * @createTime 2016-07-06
 *
 */
public class ConstructionPlanQueryReq extends PageSortReq{
	
	private String projId;				//工程id
	private String projNo;				//工程编号
	private String projStatus;			//工程状态
	private String cpId;				//计划ID
	private String projName;			//工程名称
	private String projAddr;			//工程地点
	private String cpDocumenter;		//编制人
	private String cpArriveDateStart;	//编制日期开始
	private String cpArriveDateEnd;		//编制日期结束
	private String plannedStartDateStart;//开工日期开始
	private String plannedStartDateEnd;	//开工日期结束
	private Integer timeLimit;	         //时间限制
	private String  stepId;	             //步骤
	private List<String> projStuList;    //工程状态集合
	private String  suJgjId;     //现场监理ID

	private String isPrint;				 //是否打印标记     0 已打印,1未打印
	private String suIsPrint;			 //是否打印标记     0 已打印,1未打印

	private String cuIsDispatch;		 //施工是否派遣
	private String suIsDispatch;	     //监理是否派遣
	private String menuId;				//菜单ID
	private String projLType;				//工程类型
	private String cuPm;					//项目经理
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
	public String getCpId() {
		return cpId;
	}
	public void setCpId(String cpId) {
		this.cpId = cpId;
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
	public String getCpDocumenter() {
		return cpDocumenter;
	}
	public void setCpDocumenter(String cpDocumenter) {
		this.cpDocumenter = cpDocumenter;
	}
	public String getCpArriveDateStart() {
		return cpArriveDateStart;
	}
	public void setCpArriveDateStart(String cpArriveDateStart) {
		this.cpArriveDateStart = cpArriveDateStart;
	}
	public String getCpArriveDateEnd() {
		return cpArriveDateEnd;
	}
	public void setCpArriveDateEnd(String cpArriveDateEnd) {
		this.cpArriveDateEnd = cpArriveDateEnd;
	}
	public String getPlannedStartDateStart() {
		return plannedStartDateStart;
	}
	public void setPlannedStartDateStart(String plannedStartDateStart) {
		this.plannedStartDateStart = plannedStartDateStart;
	}
	public String getPlannedStartDateEnd() {
		return plannedStartDateEnd;
	}
	public void setPlannedStartDateEnd(String plannedStartDateEnd) {
		this.plannedStartDateEnd = plannedStartDateEnd;
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

	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	public String getSuIsPrint() {
		return suIsPrint;
	}
	public void setSuIsPrint(String suIsPrint) {
		this.suIsPrint = suIsPrint;
	}

	public String getCuIsDispatch() {
		return cuIsDispatch;
	}

	public void setCuIsDispatch(String cuIsDispatch) {
		this.cuIsDispatch = cuIsDispatch;
	}

	public String getSuIsDispatch() {
		return suIsDispatch;
	}

	public void setSuIsDispatch(String suIsDispatch) {
		this.suIsDispatch = suIsDispatch;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getSuJgjId() {
		return suJgjId;
	}
	public void setSuJgjId(String suJgjId) {
		this.suJgjId = suJgjId;
	}
	public String getProjLType() {
		return projLType;
	}
	public void setProjLType(String projLType) {
		this.projLType = projLType;
	}
	public String getCuPm() {
		return cuPm;
	}
	public void setCuPm(String cuPm) {
		this.cuPm = cuPm;
	}
	
}
