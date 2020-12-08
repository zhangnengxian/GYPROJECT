package cc.dfsoft.project.biz.base.subpackage.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * SubQuantities entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SUB_QUANTITIES")
public class SubQuantities implements Serializable {

	// Fields

	/**
	 *  
	 */
	private static final long serialVersionUID = -5411754350642197431L;
	private String sqId;			 	//主键ID
	private String sbId;				//施工预算ID
	private String sqStandardId;		//工程量标准ID
	private String sqBranchProjName;	//分步分项工程名称
	private String sqUnit;				//单位
	private String sqWorkContent;		//工作内容及项目特征
	private BigDecimal sqLabourPrice; 		//劳务单价
	private Double sqNum;				//数量
	private Double changeAnum;			//审批量
	private Double sqAmount;			//金额
	private String id;
	
	/**结算增加*/
	private Integer sqStatus;			//工程量状态：施工预算，结算

	// Constructors

	/** default constructor */
	public SubQuantities() {
	}

	// Property accessors
	@Id
	@Column(name = "SQ_ID", unique = true)
	public String getSqId() {
		return this.sqId;
	}

	public void setSqId(String sqId) {
		this.sqId = sqId;
	}

	@Column(name = "SQ_STANDARD_ID")
	public String getSqStandardId() {
		return this.sqStandardId;
	}

	public void setSqStandardId(String sqStandardId) {
		this.sqStandardId = sqStandardId;
	}

	@Column(name = "SQ_BRANCH_PROJ_NAME")
	public String getSqBranchProjName() {
		return this.sqBranchProjName;
	}

	public void setSqBranchProjName(String sqBranchProjName) {
		this.sqBranchProjName = sqBranchProjName;
	}

	@Column(name = "SQ_UNIT")
	public String getSqUnit() {
		return this.sqUnit;
	}

	public void setSqUnit(String sqUnit) {
		this.sqUnit = sqUnit;
	}

	@Column(name = "SQ_WORK_CONTENT")
	public String getSqWorkContent() {
		return this.sqWorkContent;
	}

	public void setSqWorkContent(String sqWorkContent) {
		this.sqWorkContent = sqWorkContent;
	}

	@Column(name = "SQ_LABOUR_PRICE")
	public BigDecimal getSqLabourPrice() {
		return this.sqLabourPrice;
	}

	public void setSqLabourPrice(BigDecimal sqLabourPrice) {
		this.sqLabourPrice = sqLabourPrice;
	}

	@Column(name = "SQ_NUM")
	public Double getSqNum() {
		return this.sqNum;
	}

	public void setSqNum(Double sqNum) {
		this.sqNum = sqNum;
	}

	@Column(name = "CHANGE_ANUM")
	public Double getChangeAnum() {
		return this.changeAnum;
	}

	public void setChangeAnum(Double changeAnum) {
		this.changeAnum = changeAnum;
	}

	@Column(name = "SQ_AMOUNT")
	public Double getSqAmount() {
		return this.sqAmount;
	}

	public void setSqAmount(Double sqAmount) {
		this.sqAmount = sqAmount;
	}
    
	@Transient
	public String getId() {
		return this.sqStandardId+"@@"+this.sqBranchProjName+"@@"+this.sqUnit+"@@"+this.sqLabourPrice;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "SB_ID")
	public String getSbId() {
		return sbId;
	}

	public void setSbId(String sbId) {
		this.sbId = sbId;
	}

	@Column(name="SQ_STATUS")
	public Integer getSqStatus() {
		return sqStatus;
	}

	public void setSqStatus(Integer sqStatus) {
		this.sqStatus = sqStatus;
	}

	
}