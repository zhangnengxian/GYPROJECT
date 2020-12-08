package cc.dfsoft.project.biz.base.design.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DispatchInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DISPATCH_INFO")
public class DispatchInfo implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3803134856660387772L;
	private String disId;			//记录Id
	private String projId;			//工程Id
	private String projNo;			//工程编号
	private Date disSurveyDate;		//派遣勘察人日期
	private String surveyerId;		//勘察人Id
	private String surveyer;		//勘察人
	private Date disDesignerDate;	//派遣设计人日期
	private String designerId;		//设计人Id
	private String designer;		//设计人
	private String projName;		//工程名称

	// Constructors

	/** default constructor */
	public DispatchInfo() {
	}

	// Property accessors
	@Id
	@Column(name = "DIS_ID", unique = true)
	public String getDisId() {
		return this.disId;
	}

	public void setDisId(String disId) {
		this.disId = disId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return this.projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DIS_SURVEY_DATE")
	public Date getDisSurveyDate() {
		return this.disSurveyDate;
	}

	public void setDisSurveyDate(Date disSurveyDate) {
		this.disSurveyDate = disSurveyDate;
	}

	@Column(name = "SURVEYER_ID")
	public String getSurveyerId() {
		return this.surveyerId;
	}

	public void setSurveyerId(String surveyerId) {
		this.surveyerId = surveyerId;
	}

	@Column(name = "SURVEYER")
	public String getSurveyer() {
		return this.surveyer;
	}

	public void setSurveyer(String surveyer) {
		this.surveyer = surveyer;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DIS_DESIGNER_DATE")
	public Date getDisDesignerDate() {
		return this.disDesignerDate;
	}

	public void setDisDesignerDate(Date disDesignerDate) {
		this.disDesignerDate = disDesignerDate;
	}

	@Column(name = "DESIGNER_ID")
	public String getDesignerId() {
		return this.designerId;
	}

	public void setDesignerId(String designerId) {
		this.designerId = designerId;
	}

	@Column(name = "DESIGNER")
	public String getDesigner() {
		return this.designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}

	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return this.projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

}