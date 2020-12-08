package cc.dfsoft.project.biz.base.subpackage.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 费用申请工程清单
 * @author fuliwei
 *
 */
@Entity
@Table(name = "FEE_APPLY_LIST")
public class FeeApplyList implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1412642648029782334L;
	
	
	private String falId;				//主键id
	private String projId;				//工程id
	private String paId;				//申请单id
	private String sortNo;				//序号
	private String projNo;				//工程编号
	private String projName;			//工程名称
	private String projAddr;			//工程地点
	private String projScaleDes;		//工程规模
	private BigDecimal applyAmount;		//申请金额
	private BigDecimal endAmount;       //质保金
	private String feeType;				//1 工程费 2设计费 3 监理费 4探伤费
	private BigDecimal endSettlementCost;//结算审定价
	private String isSpecialSign;		//特殊标记-只读
	
	@Id
    @Column(name = "FAL_ID")
	public String getFalId() {
		return falId;
	}
	public void setFalId(String falId) {
		this.falId = falId;
	}
	
	@Column(name = "SORT_NO")
	public String getSortNo() {
		return sortNo;
	}
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	
	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	
	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	
	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return projAddr;
	}
	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	
	
	@Column(name = "PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return projScaleDes;
	}
	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	
	@Column(name = "APPLY_AMOUNT")
	public BigDecimal getApplyAmount() {
		return applyAmount;
	}
	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}
	
	@Column(name = "PROJ_ID")
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	@Column(name = "PA_ID")
	public String getPaId() {
		return paId;
	}
	public void setPaId(String paId) {
		this.paId = paId;
	}
	
	@Column(name = "FEE_TYPE")
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	@Column(name="END_AMOUNT")
	public BigDecimal getEndAmount() {
		return endAmount;
	}
	public void setEndAmount(BigDecimal endAmount) {
		this.endAmount = endAmount;
	}
	@Transient
	public BigDecimal getEndSettlementCost() {
		return endSettlementCost;
	}
	public void setEndSettlementCost(BigDecimal endSettlementCost) {
		this.endSettlementCost = endSettlementCost;
	}
	
	@Transient
	public String getIsSpecialSign() {
		return isSpecialSign;
	}
	public void setIsSpecialSign(String isSpecialSign) {
		this.isSpecialSign = isSpecialSign;
	}
	
	
	
}
