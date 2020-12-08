package cc.dfsoft.project.biz.base.subpackage.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class PricedBoqReq extends PageSortReq{
	
	private String sqStandardId;	//工程量标准
	private String subitemName;		//分部分项名称
	private String qsId;			//Id
	private String costType;		//造价类型
	private String veId;			//版本Id
	private String corpId;			//分公司id
	
	public String getQsId() {
		return qsId;
	}

	public void setQsId(String qsId) {
		this.qsId = qsId;
	}

	public String getSqStandardId() {
		return sqStandardId;
	}

	public void setSqStandardId(String sqStandardId) {
		this.sqStandardId = sqStandardId;
	}

	public String getSubitemName() {
		return subitemName;
	}

	public void setSubitemName(String subitemName) {
		this.subitemName = subitemName;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getVeId() {
		return veId;
	}

	public void setVeId(String veId) {
		this.veId = veId;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
}
