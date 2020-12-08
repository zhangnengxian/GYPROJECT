package cc.dfsoft.project.biz.base.constructmanage.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class InspectionListQueryReq extends PageSortReq {
	
	private String inspector;		//检查人
	private String checkDateStart;	//检查开始日期
	private String checkDateEnd;	//检查结束日期
	private String totalStart;		//扣分下限
	private String totalEnd;		//扣分上限
	private String projId;			//工程ID
	private String type;			//检查单类型
	
	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getCheckDateStart() {
		return checkDateStart;
	}

	public void setCheckDateStart(String checkDateStart) {
		this.checkDateStart = checkDateStart;
	}

	public String getCheckDateEnd() {
		return checkDateEnd;
	}

	public void setCheckDateEnd(String checkDateEnd) {
		this.checkDateEnd = checkDateEnd;
	}

	public String getTotalStart() {
		return totalStart;
	}

	public void setTotalStart(String totalStart) {
		this.totalStart = totalStart;
	}

	public String getTotalEnd() {
		return totalEnd;
	}

	public void setTotalEnd(String totalEnd) {
		this.totalEnd = totalEnd;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
