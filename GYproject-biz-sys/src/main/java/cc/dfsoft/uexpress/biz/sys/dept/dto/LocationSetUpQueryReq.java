package cc.dfsoft.uexpress.biz.sys.dept.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class LocationSetUpQueryReq extends PageSortReq{
	private String ssDes;                   //评分描述                SS_DES
	private String ssScore;                 //分数                        SS_SCORE
	private String deptId;                  //部门Id      DEPT_ID
	private String unitType;                //单位类型                 UNIT_TYPE
	private String projId;                  //工程id
	
	public String getSsDes() {
		return ssDes;
	}
	public void setSsDes(String ssDes) {
		this.ssDes = ssDes;
	}
	public String getSsScore() {
		return ssScore;
	}
	public void setSsScore(String ssScore) {
		this.ssScore = ssScore;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
}
