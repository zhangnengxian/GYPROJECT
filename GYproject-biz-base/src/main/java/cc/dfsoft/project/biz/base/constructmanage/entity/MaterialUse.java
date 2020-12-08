package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * MaterialUse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MATERIAL_USE")
public class MaterialUse implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 589427849540635605L;
	private String muId;		//使用记录ID
	private String projId;		//工程ID
	private String bmNo;		//材料编号
	private String bmName;		//材料名称
	private String bmSpec;		//规格型号
	private String bmUnit;		//单位
	private BigDecimal bmPrice;	//单价
	private BigDecimal miNum;	//使用数量
	private BigDecimal aiNum;   //实际安装量
	private BigDecimal miAmount;//合计金额
	private Date useDate;		//使用日期
	private String useStaffId;	//使用人
	
	private String materialId;	//材料清单id------用于材料报验功能实现

	// Constructors

	/** default constructor */
	public MaterialUse() {
	}

	// Property accessors
	@Id
	@Column(name = "MU_ID", unique = true)
	public String getMuId() {
		return this.muId;
	}

	public void setMuId(String muId) {
		this.muId = muId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "BM_NO")
	public String getBmNo() {
		return this.bmNo;
	}

	public void setBmNo(String bmNo) {
		this.bmNo = bmNo;
	}

	@Column(name = "BM_NAME")
	public String getBmName() {
		return this.bmName;
	}

	public void setBmName(String bmName) {
		this.bmName = bmName;
	}

	@Column(name = "BM_SPEC")
	public String getBmSpec() {
		return this.bmSpec;
	}

	public void setBmSpec(String bmSpec) {
		this.bmSpec = bmSpec;
	}

	@Column(name = "BM_UNIT")
	public String getBmUnit() {
		return this.bmUnit;
	}

	public void setBmUnit(String bmUnit) {
		this.bmUnit = bmUnit;
	}

	@Column(name = "BM_PRICE")
	public BigDecimal getBmPrice() {
		return this.bmPrice;
	}

	public void setBmPrice(BigDecimal bmPrice) {
		this.bmPrice = bmPrice;
	}

	@Column(name = "MI_NUM")
	public BigDecimal getMiNum() {
		return this.miNum;
	}

	public void setMiNum(BigDecimal miNum) {
		this.miNum = miNum;
	}
	
	@Column(name = "AI_NUM")
	public BigDecimal getAiNum() {
		return aiNum;
	}

	public void setAiNum(BigDecimal aiNum) {
		this.aiNum = aiNum;
	}

	@Column(name = "MI_AMOUNT")
	public BigDecimal getMiAmount() {
		return this.miAmount;
	}

	public void setMiAmount(BigDecimal miAmount) {
		this.miAmount = miAmount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "USE_DATE")
	public Date getUseDate() {
		return this.useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	@Column(name = "USE_STAFF_ID")
	public String getUseStaffId() {
		return this.useStaffId;
	}

	public void setUseStaffId(String useStaffId) {
		this.useStaffId = useStaffId;
	}
	
	@Transient
	public String getMaterialId() {
		return materialId;
	}


	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
}