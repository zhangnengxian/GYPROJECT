package cc.dfsoft.project.biz.base.inspection.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 除锈/防腐检查规格
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ANTISEPSIS_SPEC")
public class AntisepsisSpec implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private String asId;//主键id
	private String pcId;//报验单id
	private String dpPipeSize;//管道规格
	private String dpNum;//防腐数量
	private String asType;//类型

	// Constructors

	/** default constructor */
	public AntisepsisSpec() {
	}

	/** full constructor */
	public AntisepsisSpec(String asId, String pcId, String dpPipeSize,
			String dpNum, String asType) {
		this.asId = asId;
		this.pcId = pcId;
		this.dpPipeSize = dpPipeSize;
		this.dpNum = dpNum;
		this.asType = asType;
	}

	// Property accessors
	@Id
	@Column(name = "AS_ID", unique = true, nullable = false)
	public String getAsId() {
		return this.asId;
	}

	public void setAsId(String asId) {
		this.asId = asId;
	}

	@Column(name = "PC_ID", nullable = false)
	public String getPcId() {
		return this.pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name = "DP_PIPE_SIZE")
	public String getDpPipeSize() {
		return this.dpPipeSize;
	}

	public void setDpPipeSize(String dpPipeSize) {
		this.dpPipeSize = dpPipeSize;
	}

	@Column(name = "DP_NUM")
	public String getDpNum() {
		return this.dpNum;
	}

	public void setDpNum(String dpNum) {
		this.dpNum = dpNum;
	}

	@Column(name = "AS_TYPE")
	public String getAsType() {
		return this.asType;
	}

	public void setAsType(String asType) {
		this.asType = asType;
	}

}