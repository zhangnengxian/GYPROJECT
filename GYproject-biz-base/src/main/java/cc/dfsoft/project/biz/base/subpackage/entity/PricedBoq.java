package cc.dfsoft.project.biz.base.subpackage.entity;

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

import com.alibaba.druid.stat.TableStat.Name;

import cc.dfsoft.project.biz.base.project.enums.CostTypeEnum;

/**
 * QuantityStandard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PRICED_BOQ")
public class PricedBoq implements Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 4429109215652722009L;
	
	/**主键*/
	private String qsId;			
	/**造价类型*/
	private String costType;		
	/**分部分项名称*/
	private String subitemName;		
	/**单位*/
	private String unit;			
	/**工作内容以及项目特征*/
	private String subitemContent;	
	/**劳务单价*/
	private BigDecimal unitPrice;	
	/**版本ID*/
	private String veId;			
	/**造价类型枚举字段*/
	private String costTypeDes;		
	/**发布日期*/
	private String surveyDate;		
	/**分公司ID*/
	private String corpId;			
	/**版本号*/
	private String version;  
	/**增收方式*/
	private String incIncraMode;
	// Constructors

	@Transient
	@Temporal(TemporalType.TIMESTAMP)
	public String getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(String surveyDate) {
		this.surveyDate = surveyDate;
	}

	/** default constructor */
	public PricedBoq() {
	}

	// Property accessors
	@Id
	@Column(name = "QS_ID", unique = true)
	public String getQsId() {
		return this.qsId;
	}

	public void setQsId(String qsId) {
		this.qsId = qsId;
	}

	@Column(name = "COST_TYPE")
	public String getCostType() {
		return this.costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	@Column(name = "SUBITEM_NAME")
	public String getSubitemName() {
		return this.subitemName;
	}

	public void setSubitemName(String subitemName) {
		this.subitemName = subitemName;
	}

	@Column(name = "UNIT")
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "SUBITEM_CONTENT")
	public String getSubitemContent() {
		return this.subitemContent;
	}

	public void setSubitemContent(String subitemContent) {
		this.subitemContent = subitemContent;
	}

	@Column(name = "UNIT_PRICE")
	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@Column(name = "VE_ID")
	public String getVeId() {
		return veId;
	}

	public void setVeId(String veId) {
		this.veId = veId;
	}

	@Transient
	public String getCostTypeDes() {
		for(CostTypeEnum e: CostTypeEnum.values()) {
	   		if(e.getValue().equals(this.costType)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setCostTypeDes(String costTypeDes) {
		this.costTypeDes = costTypeDes;
	}
	
	
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	@Column(name = "VERSION")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Column(name = "INCINCRAMODE")
	public String getIncIncraMode() {
		return incIncraMode;
	}

	public void setIncIncraMode(String incIncraMode) {
		this.incIncraMode = incIncraMode;
	}
	
}