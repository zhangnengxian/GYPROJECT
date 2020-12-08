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

@Entity
@Table(name = "MATERIAL_PLAN_DETAIL")
public class MaterialPlanDetail implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 2905115375399397067L;
	private String mpdId;       	    //主键id
	 private String projId;				//工程id
	 private String mpId;				//材料计划ID
	 private String materialId;			//材料ID
	 private String materialName;		//材料名称
	 private String materialSpec;		//规格型号
	 private String materialUnit;		//单位
	 private BigDecimal materialPrice;	//单价
	 private BigDecimal materialNum;	//设计总量
	 private BigDecimal materialAmount;	//金额
	 private BigDecimal getAnum;		//领用总量
	 private BigDecimal planTotalNum;	//计划领用总量
	 private BigDecimal planNum;		//本次计划领用量
	 private BigDecimal oweNum;			//欠量
	 private String certificateComplete;//合格证是否齐全
	 private Date getGoodsTime;			//到货时间
	 private String goodTime;//转换到货时间
	 
	 private BigDecimal  thisDayReceiveSum;//当天领用
	 public MaterialPlanDetail() {
	 }
	    
	@Id
	@Column(name = "MPD_ID", unique = true)
	public String getMpdId() {
		return mpdId;
	}
	public void setMpdId(String mpdId) {
		this.mpdId = mpdId;
	}
	
	@Column(name = "PROJ_ID")
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	@Column(name = "MP_ID")
	public String getMpId() {
		return mpId;
	}
	public void setMpId(String mpId) {
		this.mpId = mpId;
	}
	
	@Column(name = "MATERIAL_ID")
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	
	@Column(name = "MATERIAL_NAME")
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	@Column(name = "MATERIAL_SPEC")
	public String getMaterialSpec() {
		return materialSpec;
	}
	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}
	
	@Column(name = "MATERIAL_UNIT")
	public String getMaterialUnit() {
		return materialUnit;
	}
	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}
	
	@Column(name = "MATERIAL_PRICE")
	public BigDecimal getMaterialPrice() {
		return materialPrice;
	}
	public void setMaterialPrice(BigDecimal materialPrice) {
		this.materialPrice = materialPrice;
	}
	
	@Column(name = "MATERIAL_NUM")
	public BigDecimal getMaterialNum() {
		return materialNum;
	}
	public void setMaterialNum(BigDecimal materialNum) {
		this.materialNum = materialNum;
	}
	
	@Column(name = "MATERIAL_AMOUNT")
	public BigDecimal getMaterialAmount() {
		return materialAmount;
	}
	public void setMaterialAmount(BigDecimal materialAmount) {
		this.materialAmount = materialAmount;
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
	
	
	@Column(name = "GET_ANUM")
	public BigDecimal getGetAnum() {
		return getAnum;
	}
	public void setGetAnum(BigDecimal getAnum) {
		this.getAnum = getAnum;
	}
	
	@Transient
	public String getGoodTime() {
		return goodTime;
	}
	public void setGoodTime(String goodTime) {
		this.goodTime = goodTime;
	}
	
	@Transient
	public BigDecimal getThisDayReceiveSum() {
		return thisDayReceiveSum;
	}

	public void setThisDayReceiveSum(BigDecimal thisDayReceiveSum) {
		this.thisDayReceiveSum = thisDayReceiveSum;
	}
	 
	 
	 
}
