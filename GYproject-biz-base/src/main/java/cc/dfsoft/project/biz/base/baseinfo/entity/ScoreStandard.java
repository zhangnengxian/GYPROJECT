package cc.dfsoft.project.biz.base.baseinfo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;

/**
 * DocType entity.
 * 
 * @author cui
 */
@Entity
@Table(name = "Score_STANDARD")
public class ScoreStandard implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3646558284738654634L;
	private String ssId;                     //id
	private String ssDes;                   //评分描述SS_DES
	private String ssScore;                 //分数SS_SCORE
//	private String deptId;                  //部门IdDEPT_ID
	private String unitType;                //单位类型 UNIT_TYPE
	
	private Department dept;                //部门
	private String departmentId;            //部门Id----------
	
	private String grade;//打分
	// Property accessors
	@Id
	@Column(name = "SS_ID")
	public String getSsId() {
		return ssId;
	}
	public void setSsId(String ssId) {
		this.ssId = ssId;
	}
	
	@Column(name = "SS_DES")
	public String getSsDes() {
		return ssDes;
	}
	public void setSsDes(String ssDes) {
		this.ssDes = ssDes;
	}
	
	@Column(name = "SS_SCORE")
	public String getSsScore() {
		return ssScore;
	}
	public void setSsScore(String ssScore) {
		this.ssScore = ssScore;
	}
	
//	@Column(name = "DEPT_ID")
//	public String getDeptId() {
//		return deptId;
//	}
//	public void setDeptId(String deptId) {
//		this.deptId = deptId;
//	}
	
	@Column(name = "UNIT_TYPE")
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} ) 
	@JoinColumn(name = "DEPT_ID")
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	
	@Transient
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	@Transient
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}