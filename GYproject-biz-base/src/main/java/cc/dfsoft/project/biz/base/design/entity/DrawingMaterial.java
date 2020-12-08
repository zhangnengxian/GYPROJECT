package cc.dfsoft.project.biz.base.design.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.design.enums.MaterialFlagEnum;

/**
 * 出图材料表
 */
@Entity
@Table(name = "DRAWING_MATERIAL")
public class DrawingMaterial implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6757147894486187059L;
	private String dmId;		//材料表格ID
	private String projId;		//工程ID
	private String projNo;		//工程编号
	private String dmNo;		//材料编号
	private String dmName;		//材料名称
	private String dmSpec;		//规格型号
	private String supplierName;//厂家名称
	private String dmUnit;		//单位
	private BigDecimal dmNum;	//数量
	private String remark;		//备注
	private Integer orderNum;	//排序
	
	private String materialNum;//物料编码
	private String materialStandard;//物料规格
	private String flag;		  //是否从物资公司购买：1-是
	private String flagDes;		  //只读
	
	// Constructors

	/** default constructor */
	public DrawingMaterial() {
	}

	// Property accessors
	@Id
	@Column(name = "DM_ID", unique = true)
	public String getDmId() {
		return this.dmId;
	}

	public void setDmId(String dmId) {
		this.dmId = dmId;
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

	@Column(name = "DM_NO")
	public String getDmNo() {
		return this.dmNo;
	}

	public void setDmNo(String dmNo) {
		this.dmNo = dmNo;
	}

	@Column(name = "DM_NAME")
	public String getDmName() {
		return this.dmName;
	}

	public void setDmName(String dmName) {
		this.dmName = dmName;
	}

	@Column(name = "DM_SPEC")
	public String getDmSpec() {
		return this.dmSpec;
	}

	public void setDmSpec(String dmSpec) {
		this.dmSpec = dmSpec;
	}

	@Column(name = "SUPPLIER_NAME")
	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Column(name = "DM_UNIT")
	public String getDmUnit() {
		return this.dmUnit;
	}

	public void setDmUnit(String dmUnit) {
		this.dmUnit = dmUnit;
	}

	@Column(name = "DM_NUM")
	public BigDecimal getDmNum() {
		return this.dmNum;
	}

	public void setDmNum(BigDecimal dmNum) {
		this.dmNum = dmNum;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "ORDER_NUM")
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	@Column(name = "MATERIAL_NUM")
	public String getMaterialNum() {
		return materialNum;
	}

	public void setMaterialNum(String materialNum) {
		this.materialNum = materialNum;
	}
	
	@Column(name = "MATERIAL_STANDARD")
	public String getMaterialStandard() {
		return materialStandard;
	}

	public void setMaterialStandard(String materialStandard) {
		this.materialStandard = materialStandard;
	}

	@Column(name="FLAG")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Transient
	public String getFlagDes() {
		for(MaterialFlagEnum e: MaterialFlagEnum.values()) {
	   		if(e.getValue().equals(this.flag)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setFlagDes(String flagDes) {
		this.flagDes = flagDes;
	}

	
}