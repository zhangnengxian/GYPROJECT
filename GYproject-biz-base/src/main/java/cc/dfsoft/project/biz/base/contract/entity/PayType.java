package cc.dfsoft.project.biz.base.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PAY_TYPE")
public class PayType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3792039989110318851L;
	private String ptId;		//主键id
	private String contractType;//合同类型
	private String payTypeDes;	//付款方式描述
	private String payTypeMode;	//付款方式-付款次数

	private String projTypeId;		    //工程类型id
	private String payType;             //进度款方式
	private String scType;	            //合同款方式
	private String progressType;		//进度款方式
	private String corpId;				//分公司ID

	@Column(name = "PROJ_TYPE_ID")
	public String getProjTypeId() {
		return projTypeId;
	}

	public void setProjTypeId(String projTypeId) {
		this.projTypeId = projTypeId;
	}

	@Column(name = "PAY_TYPE")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Column(name = "SC_TYPE")
	public String getScType() {
		return scType;
	}

	public void setScType(String scType) {
		this.scType = scType;
	}

	@Column(name = "PROGRESS_TYPE")
	public String getProgressType() {
		return progressType;
	}

	public void setProgressType(String progressType) {
		this.progressType = progressType;
	}

	//----
	public PayType() {
	}

	@Id
	@Column(name = "PT_ID", unique = true)
	public String getPtId() {
		return this.ptId;
	}

	public void setPtId(String ptId) {
		this.ptId = ptId;
	}

	@Column(name = "CONTRACT_TYPE")
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	@Column(name = "PAY_TYPE_DES")
	public String getPayTypeDes() {
		return payTypeDes;
	}

	public void setPayTypeDes(String payTypeDes) {
		this.payTypeDes = payTypeDes;
	}
	@Column(name = "PAY_TYPE_MODE")
	public String getPayTypeMode() {
		return payTypeMode;
	}
	
	public void setPayTypeMode(String payTypeMode) {
		this.payTypeMode = payTypeMode;
	}

	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
}