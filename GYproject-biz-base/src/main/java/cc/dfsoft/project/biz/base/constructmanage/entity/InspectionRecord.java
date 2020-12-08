package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * InspectionRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INSPECTION_RECORD")
public class InspectionRecord implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6511318846142837242L;
	private String irId;					//质量检查记录ID
	private String projId;					//工程ID
	private String projNo;					//工程ID
	private String unqualityPointId;		//扣分大项
	private String unqualityPointContent;	//扣分项说明
	private Double fraction;				//扣分
	private String ilId;					//质量检查单ID
	private String saId;					//扣分项ID
	private String id;

	// Constructors

	/** default constructor */
	public InspectionRecord() {
	}

	// Property accessors
	@Id
	@Column(name = "IR_ID", unique = true)
	public String getIrId() {
		return this.irId;
	}

	public void setIrId(String irId) {
		this.irId = irId;
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

	@Column(name = "UNQUALITY_POINT_ID")
	public String getUnqualityPointId() {
		return this.unqualityPointId;
	}

	public void setUnqualityPointId(String unqualityPointId) {
		this.unqualityPointId = unqualityPointId;
	}

	@Column(name = "UNQUALITY_POINT_CONTENT")
	public String getUnqualityPointContent() {
		return this.unqualityPointContent;
	}

	public void setUnqualityPointContent(String unqualityPointContent) {
		this.unqualityPointContent = unqualityPointContent;
	}

	@Column(name = "FRACTION")
	public Double getFraction() {
		return this.fraction;
	}

	public void setFraction(Double fraction) {
		this.fraction = fraction;
	}

	@Column(name = "IL_ID")
	public String getIlId() {
		return this.ilId;
	}

	public void setIlId(String ilId) {
		this.ilId = ilId;
	}

	@Column(name = "SA_ID")
	public String getSaId() {
		return saId;
	}

	public void setSaId(String saId) {
		this.saId = saId;
	}

	@Transient
	public String getId() {
		String fraction = (""+this.fraction).substring(0, (""+this.fraction).length());
		return this.saId+"@@"+this.unqualityPointContent+"@@"+fraction+"@@"+this.unqualityPointId;
	}

	public void setId(String id) {
		this.id = id;
	}
}