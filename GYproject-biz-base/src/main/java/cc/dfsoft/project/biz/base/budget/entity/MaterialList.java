package cc.dfsoft.project.biz.base.budget.entity;

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

import cc.dfsoft.project.biz.base.design.enums.MaterialFlagEnum;

/**
 * 材料清单
 */
@Entity
@Table(name = "MATERIAL_LIST")
public class MaterialList implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2530572627724077922L;
	private String materialId;		//主键ID
	private String projId;			//工程ID
	private String projNo;			//工程编号
	private String projName;		//工程名称
	private String materialNo;		//材料编号
	private String materialName;	//材料名称
	private String materialSpec;	//规格型号
	private String materialUnit;	//单位
	private BigDecimal materialPrice;//单价
	private BigDecimal materialNum;	//数量
	private BigDecimal materialAmount;//金额
	private BigDecimal getAnum;		 //领用总量
	private Double inspectionAnum;	//报验总量
	private Double useAnum;			//使用总量
	private BigDecimal aiAnum;      //实际安装量
	private String remark;			//备注
	
	private String flag1;            // ----- 显示

	// Constructors
	
	private BigDecimal planTotalNum;			//计划总量
	private BigDecimal planNum;				//本次计划领用量
	private BigDecimal oweNum;			//欠量
	private String certificateComplete;//合格证是否齐全
	private Date getGoodsTime;			//到货时间
	
	
	private String materialCode;     //物料编码
	private String materialStandard; //物料规格
	
	private String materialTableType;//主材表类型
	private String status;		     //领料状态
	private String flag;		  //是否从物资公司购买：1-是
	private String flagDes;		  //是否从物资公司购买描述
	private String cmId;		  //签证、变更记录ID
	
	@Transient
	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	/** default constructor */
	public MaterialList() {
	}

	@Column(name="FLAG")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Id
	@Column(name = "MATERIAL_ID", unique = true)
	public String getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
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

	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return this.projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Column(name = "MATERIAL_NO")
	public String getMaterialNo() {
		return this.materialNo;
	}

	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}

	@Column(name = "MATERIAL_NAME")
	public String getMaterialName() {
		return this.materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	@Column(name = "MATERIAL_SPEC")
	public String getMaterialSpec() {
		return this.materialSpec;
	}

	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}

	@Column(name = "MATERIAL_UNIT")
	public String getMaterialUnit() {
		return this.materialUnit;
	}

	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}

	@Column(name = "MATERIAL_PRICE")
	public BigDecimal getMaterialPrice() {
		return this.materialPrice;
	}

	public void setMaterialPrice(BigDecimal materialPrice) {
		this.materialPrice = materialPrice;
	}

	@Column(name = "MATERIAL_NUM")
	public BigDecimal getMaterialNum() {
		return this.materialNum;
	}

	public void setMaterialNum(BigDecimal integer) {
		this.materialNum = integer;
	}

	@Column(name = "MATERIAL_AMOUNT")
	public BigDecimal getMaterialAmount() {
		return this.materialAmount;
	}

	public void setMaterialAmount(BigDecimal materialAmount) {
		this.materialAmount = materialAmount;
	}

	@Column(name = "GET_ANUM")
	public BigDecimal getGetAnum() {
		return this.getAnum;
	}

	public void setGetAnum(BigDecimal getAnum) {
		this.getAnum = getAnum;
	}

	@Column(name = "INSPECTION_ANUM")
	public Double getInspectionAnum() {
		return this.inspectionAnum;
	}

	public void setInspectionAnum(Double inspectionAnum) {
		this.inspectionAnum = inspectionAnum;
	}

	@Column(name = "USE_ANUM")
	public Double getUseAnum() {
		return this.useAnum;
	}

	public void setUseAnum(Double useAnum) {
		this.useAnum = useAnum;
	}
	
	@Column(name = "AI_ANUM")
	public BigDecimal getAiAnum() {
		return aiAnum;
	}

	public void setAiAnum(BigDecimal aiAnum) {
		this.aiAnum = aiAnum;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "PLAN_TOTAL_NUM")
	public BigDecimal getPlanTotalNum() {
		return planTotalNum;
	}

	public void setPlanTotalNum(BigDecimal planTotalNum) {
		this.planTotalNum = planTotalNum;
	}
	
	@Column(name = "PLAN_NUM")
	public BigDecimal getPlanNum() {
		return planNum;
	}

	public void setPlanNum(BigDecimal planNum) {
		this.planNum = planNum;
	}
	
	@Column(name = "OWE_NUM")
	public BigDecimal getOweNum() {
		return oweNum;
	}
	public void setOweNum(BigDecimal oweNum) {
		this.oweNum = oweNum;
	}
	
	@Column(name = "CERTIFICATE_COMPLETE")
	public String getCertificateComplete() {
		return certificateComplete;
	}
	public void setCertificateComplete(String certificateComplete) {
		this.certificateComplete = certificateComplete;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "GET_GOODS_TIME")
	public Date getGetGoodsTime() {
		return getGoodsTime;
	}
	public void setGetGoodsTime(Date getGoodsTime) {
		this.getGoodsTime = getGoodsTime;
	}
	
	@Column(name = "MATERIAL_CODE")
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
	@Column(name = "MATERIAL_STANDARD")
	public String getMaterialStandard() {
		return materialStandard;
	}

	public void setMaterialStandard(String materialStandard) {
		this.materialStandard = materialStandard;
	}
	
	@Column(name = "MATERIAL_TABLE_TYPE")
	public String getMaterialTableType() {
		return materialTableType;
	}

	public void setMaterialTableType(String materialTableType) {
		this.materialTableType = materialTableType;
	}

	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
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

	@Column(name="CM_ID")
	public String getCmId() {
		return cmId;
	}

	public void setCmId(String cmId) {
		this.cmId = cmId;
	}
	
	
}