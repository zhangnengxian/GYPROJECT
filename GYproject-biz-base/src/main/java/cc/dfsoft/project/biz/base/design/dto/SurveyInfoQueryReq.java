package cc.dfsoft.project.biz.base.design.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 待方案审核列表查询条件
 * @author pengtt
 * @createTime 2016-06-27
 *
 */
public class SurveyInfoQueryReq  extends PageSortReq{
	
	private String projNo;				//工程编号
	private String projStatus;			//工程状态
	private String projName;			//工程名称
	private String surveyDateStart;		//勘察开始日期
	private String surveyDateEnd;		//勘察开始日期
	private String surveyer;     		//勘察人
	private Integer timeLimit;     		//限制完成的时间段
	private String stepId;     			//步骤id
	private String deptId;     			//部门Id
	private String signs;//是否打印
	
	private String menuId;				//菜单id
	
	public String getSigns() {
		return signs;
	}

	public void setSigns(String signs) {
		this.signs = signs;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getSurveyDateStart() {
		return surveyDateStart;
	}

	public void setSurveyDateStart(String surveyDateStart) {
		this.surveyDateStart = surveyDateStart;
	}

	public String getSurveyDateEnd() {
		return surveyDateEnd;
	}

	public void setSurveyDateEnd(String surveyDateEnd) {
		this.surveyDateEnd = surveyDateEnd;
	}

	public String getSurveyer() {
		return surveyer;
	}

	public void setSurveyer(String surveyer) {
		this.surveyer = surveyer;
	}

	public String getProjStatus() {
		return projStatus;
	}

	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}

	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
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

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}


}
