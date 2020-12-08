package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.project.entity.Signature;

/**
 * ReviewRecord entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "REVIEW_RECORD")
public class ReviewRecord implements java.io.Serializable {


	private static final long serialVersionUID = -4429773287198644138L;
	
	// Fields
	private String rrId;			//会审记录ID
	private String drId;			//会审ID
	private String drawingNo;		//设计图编号
	private String drawingName;		//设计图名称
	private String foreardUnit;		//提出单位
	private String drawingQuestion;	//图纸疑问
	private String answerQuestion;	//答疑
	
	// Constructors

	/** default constructor */
	public ReviewRecord() {
	}

	/** minimal constructor */
	public ReviewRecord(String rrId) {
		this.rrId = rrId;
	}

	/** full constructor */
	public ReviewRecord(String rrId, String drId, String drawingNo,
			String drawingName, String foreardUnit, String drawingQuestion,
			String answerQuestion) {
		this.rrId = rrId;
		this.drId = drId;
		this.drawingNo = drawingNo;
		this.drawingName = drawingName;
		this.foreardUnit = foreardUnit;
		this.drawingQuestion = drawingQuestion;
		this.answerQuestion = answerQuestion;
	}

	// Property accessors
	@Id
	@Column(name = "RR_ID", unique = true, nullable = false, length = 30)
	public String getRrId() {
		return this.rrId;
	}

	public void setRrId(String rrId) {
		this.rrId = rrId;
	}

	@Column(name = "DR_ID", length = 30)
	public String getDrId() {
		return this.drId;
	}

	public void setDrId(String drId) {
		this.drId = drId;
	}

	@Column(name = "DRAWING_NO", length = 20)
	public String getDrawingNo() {
		return this.drawingNo;
	}

	public void setDrawingNo(String drawingNo) {
		this.drawingNo = drawingNo;
	}

	@Column(name = "DRAWING_NAME", length = 50)
	public String getDrawingName() {
		return this.drawingName;
	}

	public void setDrawingName(String drawingName) {
		this.drawingName = drawingName;
	}

	@Column(name = "FOREARD_UNIT", length = 200)
	public String getForeardUnit() {
		return this.foreardUnit;
	}

	public void setForeardUnit(String foreardUnit) {
		this.foreardUnit = foreardUnit;
	}

	@Column(name = "DRAWING_QUESTION", length = 200)
	public String getDrawingQuestion() {
		return this.drawingQuestion;
	}

	public void setDrawingQuestion(String drawingQuestion) {
		this.drawingQuestion = drawingQuestion;
	}

	@Column(name = "ANSWER_QUESTION", length = 200)
	public String getAnswerQuestion() {
		return this.answerQuestion;
	}

	public void setAnswerQuestion(String answerQuestion) {
		this.answerQuestion = answerQuestion;
	}
	
}