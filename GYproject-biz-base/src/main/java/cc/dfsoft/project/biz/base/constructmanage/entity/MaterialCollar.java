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
 * MaterialCollar entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MATERIAL_COLLAR")
public class MaterialCollar implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2744213062588660785L;
	private String mcId;			//领用记录ID
	private String projId;			//工程ID
	private String bmNo;			//材料编号
	private String bmName;			//材料名称
	private String bmSpec;			//规格型号
	private String bmUnit;			//单位
	private BigDecimal bmPrice;		//单价
	private BigDecimal mcNum;		//领用数量
	private String mcAmount;		//合计金额
	private String materialGetter;	//领用人
	private Date mgDate;			//领用日期
	
	private String materialId;		//材料清单id------只读属性
	
	private String billNo;			//单据编号
	private String billDate;		//单据日期
	private String operateType;			//操作类型
	private String collarUnit;		//领料单位
	private String mcType;			//材料类型-主材，变更材料
	private String orgCode;			//物资公司编码
	private String flag;		  //是否从物资公司购买：1-是
	private String supname;			//物料供应商名称

	// Constructors

	/** default constructor */
	public MaterialCollar() {
	}

	@Id
	@Column(name = "MC_ID", unique = true)
	public String getMcId() {
		return this.mcId;
	}

	public void setMcId(String mcId) {
		this.mcId = mcId;
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

	@Column(name = "MC_NUM")
	public BigDecimal getMcNum() {
		return this.mcNum;
	}

	public void setMcNum(BigDecimal mcNum) {
		this.mcNum = mcNum;
	}

	@Column(name = "MC_AMOUNT")
	public String getMcAmount() {
		return this.mcAmount;
	}

	public void setMcAmount(String mcAmount) {
		this.mcAmount = mcAmount;
	}

	@Column(name = "MATERIAL_GETTER")
	public String getMaterialGetter() {
		return this.materialGetter;
	}

	public void setMaterialGetter(String materialGetter) {
		this.materialGetter = materialGetter;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MG_DATE")
	public Date getMgDate() {
		return this.mgDate;
	}

	public void setMgDate(Date mgDate) {
		this.mgDate = mgDate;
	}
	
	@Transient
	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	@Column(name="BILL_NO")
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	@Column(name="BILL_DATE")
	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	@Column(name="OPERATE_TYPE")
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name="COLLAR_UNIT")
	public String getCollarUnit() {
		return collarUnit;
	}
	public void setCollarUnit(String collarUnit) {
		this.collarUnit = collarUnit;
	}

	@Column(name="MC_TYPE")
	public String getMcType() {
		return mcType;
	}

	public void setMcType(String mcType) {
		this.mcType = mcType;
	}

	@Column(name="ORG_CODE")
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name="FLAG")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name="SUP_NAME")
	public String getSupname() {
		return supname;
		
	}
	public void setSupname(String supname) {
		this.supname=supname;
		
	}
	
}