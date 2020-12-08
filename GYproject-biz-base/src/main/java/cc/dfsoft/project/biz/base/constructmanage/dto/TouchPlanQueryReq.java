package cc.dfsoft.project.biz.base.constructmanage.dto;

import java.util.Date;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class TouchPlanQueryReq extends PageSortReq{
	
	private String tpId;                                //碰口id
	private String projId;                              //项目id
	private String projName;                            //项目名称
	private String conNo;					            //合同编号
	private String constructionUnit;                    //施工单位
	private String applyTpDate;                           //申请碰口时间
	private String tpDate;                                //确认碰口时间
	private String projStatusId;						//工程状态
	
	private String applyTpDateStart;					//申请碰口日期
	private String applyTpDateEnd;
	
	private String tpDateStart;							//确认碰口日期
	private String tpDateEnd;
	
	private String projNo;								//合同编号
	public String getTpId() {
		return tpId;
	}
	public void setTpId(String tpId) {
		this.tpId = tpId;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	public String getConstructionUnit() {
		return constructionUnit;
	}
	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}
	public String getApplyTpDate() {
		return applyTpDate;
	}
	public void setApplyTpDate(String applyTpDate) {
		this.applyTpDate = applyTpDate;
	}
	public String getTpDate() {
		return tpDate;
	}
	public void setTpDate(String tpDate) {
		this.tpDate = tpDate;
	}
	public String getProjStatusId() {
		return projStatusId;
	}
	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
	}
	public String getApplyTpDateStart() {
		return applyTpDateStart;
	}
	public void setApplyTpDateStart(String applyTpDateStart) {
		this.applyTpDateStart = applyTpDateStart;
	}
	public String getApplyTpDateEnd() {
		return applyTpDateEnd;
	}
	public void setApplyTpDateEnd(String applyTpDateEnd) {
		this.applyTpDateEnd = applyTpDateEnd;
	}
	public String getTpDateStart() {
		return tpDateStart;
	}
	public void setTpDateStart(String tpDateStart) {
		this.tpDateStart = tpDateStart;
	}
	public String getTpDateEnd() {
		return tpDateEnd;
	}
	public void setTpDateEnd(String tpDateEnd) {
		this.tpDateEnd = tpDateEnd;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	
	
	
}
