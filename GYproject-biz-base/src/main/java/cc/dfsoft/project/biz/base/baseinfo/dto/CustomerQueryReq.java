package cc.dfsoft.project.biz.base.baseinfo.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 申报单位查询
 * @author pengtt
 * @createTime 2016-07-22
 *
 */
public class CustomerQueryReq extends PageSortReq{
	
	private String custName;	//客户名称
	private String custContcat;	//联系人
	private String areaCode;    //地区编码
	private String custCode;	//单位编码
	private String corpId;		//分公司id
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustContcat() {
		return custContcat;
	}
	public void setCustContcat(String custContcat) {
		this.custContcat = custContcat;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
}
