package cc.dfsoft.project.biz.base.baseinfo.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.baseinfo.entity.Grade;
import cc.dfsoft.project.biz.base.baseinfo.entity.ScoreStandard;
import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class GradeQueryDReq extends PageSortReq{

	private String projId;
	private String deptId;
	private String ssId;//
	private String ssDes;//
	private String ssScore;
	private List<ScoreStandard>  list;
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getSsId() {
		return ssId;
	}
	public void setSsId(String ssId) {
		this.ssId = ssId;
	}
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
	public List<ScoreStandard> getList() {
		return list;
	}
	public void setList(List<ScoreStandard> list) {
		this.list = list;
	}
}
