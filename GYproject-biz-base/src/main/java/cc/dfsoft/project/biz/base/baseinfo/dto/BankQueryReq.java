package cc.dfsoft.project.biz.base.baseinfo.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 申报单位查询
 * @author cui
 * @createTime 2017-10-31
 *
 */
public class BankQueryReq extends PageSortReq{
	
	private String bankName;	//开户姓名账户
	private String bankNo;	    //账号
	private String bankAdress;	//开户行
	private String corpId;	//分公司ID


	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankAdress() {
		return bankAdress;
	}

	public void setBankAdress(String bankAdress) {
		this.bankAdress = bankAdress;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
}
