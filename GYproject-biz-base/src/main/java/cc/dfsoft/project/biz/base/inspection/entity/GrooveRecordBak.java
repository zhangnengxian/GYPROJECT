package cc.dfsoft.project.biz.base.inspection.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GrooveRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "GROOVE_RECORD_BAK")
public class GrooveRecordBak implements Serializable {
	private static final long serialVersionUID = -7771345805636103805L;
	private String grId;			//沟槽验收记录ID
	private String pcId;			//报验单ID
	private String grScope;			//沟槽范围
	private String grLevelError;	//平整度误差
	private String grLevelResult;	//平整度结果
	private String grVerticalError;	//直度中心偏差
	private String grVerticalResult;//直度结果
	private String grSlope;			//坡度
	private String grRemark;		//备注	
	private String pressureType;//压力类型
	
	/** default constructor */
	public GrooveRecordBak() {
	}
	@Id
	@Column(name = "GR_ID", unique = true)
	public String getGrId() {
		return this.grId;
	}

	public void setGrId(String grId) {
		this.grId = grId;
	}

	@Column(name = "PC_ID")
	public String getPcId() {
		return this.pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name = "GR_SCOPE")
	public String getGrScope() {
		return this.grScope;
	}

	public void setGrScope(String grScope) {
		this.grScope = grScope;
	}

	@Column(name = "GR_LEVEL_ERROR")
	public String getGrLevelError() {
		return this.grLevelError;
	}

	public void setGrLevelError(String grLevelError) {
		this.grLevelError = grLevelError;
	}

	@Column(name = "GR_LEVEL_RESULT")
	public String getGrLevelResult() {
		return this.grLevelResult;
	}

	public void setGrLevelResult(String grLevelResult) {
		this.grLevelResult = grLevelResult;
	}

	@Column(name = "GR_VERTICAL_ERROR")
	public String getGrVerticalError() {
		return this.grVerticalError;
	}

	public void setGrVerticalError(String grVerticalError) {
		this.grVerticalError = grVerticalError;
	}

	@Column(name = "GR_VERTICAL_RESULT")
	public String getGrVerticalResult() {
		return this.grVerticalResult;
	}

	public void setGrVerticalResult(String grVerticalResult) {
		this.grVerticalResult = grVerticalResult;
	}

	@Column(name = "GR_SLOPE")
	public String getGrSlope() {
		return this.grSlope;
	}

	public void setGrSlope(String grSlope) {
		this.grSlope = grSlope;
	}

	@Column(name = "GR_REMARK")
	public String getGrRemark() {
		return this.grRemark;
	}

	public void setGrRemark(String grRemark) {
		this.grRemark = grRemark;
	}
	@Column(name = "PRESSURE_TYPE")
	public String getPressureType() {
		return pressureType;
	}

	public void setPressureType(String pressureType) {
		this.pressureType = pressureType;
	}

}