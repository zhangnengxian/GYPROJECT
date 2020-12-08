package cc.dfsoft.project.biz.base.baseinfo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "GRADE")
public class Grade {

	private String gradeId;                     //id
	private String gradeDes;                   //评分描述SS_DES
	private String gradeScore;                 //分数SS_SCORE
//	private String deptId;                  //部门IdDEPT_ID
	private String unitType;                //单位类型 UNIT_TYPE
	private String deptId;					//
	private String grade;
	private String grader;
	private String graderId;
	private Date graderDate;
	private String projId;
	private String ssId;
	
	
	@Id
	@Column(name = "GRADE_ID")
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
	@Column(name = "GRADE_DES")
	public String getGradeDes() {
		return gradeDes;
	}
	public void setGradeDes(String gradeDes) {
		this.gradeDes = gradeDes;
	}
	
	@Column(name = "GRADE_SCORE")
	public String getGradeScore() {
		return gradeScore;
	}
	public void setGradeScore(String gradeScore) {
		this.gradeScore = gradeScore;
	}
	
	@Column(name = "UNIT_TYPE")
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	
	@Column(name = "DEPT_ID")
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Column(name = "GRADE")
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Column(name = "GRADER")
	public String getGrader() {
		return grader;
	}
	public void setGrader(String grader) {
		this.grader = grader;
	}
	
	@Column(name = "GRADER_ID")
	public String getGraderId() {
		return graderId;
	}
	public void setGraderId(String graderId) {
		this.graderId = graderId;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "GRADE_DATE")
	public Date getGraderDate() {
		return graderDate;
	}
	public void setGraderDate(Date graderDate) {
		this.graderDate = graderDate;
	}
	
	@Column(name = "PROJ_ID")
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	@Column(name = "SS_ID")
	public String getSsId() {
		return ssId;
	}
	public void setSsId(String ssId) {
		this.ssId = ssId;
	}
	
	
	
}
