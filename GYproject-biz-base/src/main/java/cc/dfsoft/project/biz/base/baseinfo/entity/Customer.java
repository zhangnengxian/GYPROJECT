package cc.dfsoft.project.biz.base.baseinfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CUSTOMER" )
public class Customer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5347403483724243505L;
	// Fields

	private String custId;        	//客户ID
	private String custName;	  	//客户名称
	private String custShortName; 	//客户简称
	private String custContcat;	    //联系人
	private String custPhone;      //联系电话
	private String custMobile;	    //手机
	private String openBank;	    //开户行
	private String account;		    //账号
	
	private String unitAddress;    //单位地址
	private String dutyParagraph;  //税号
	private String idNumber;		//身份证号
	
	private String custCode;		//客户单位编码
	
	private String areaCode;		//地区编码
	private String custNo;			//报装用户号
	private String corpId;			//分公司id
/** default constructor */
	public Customer() {
	}

	// Property accessors
	@Id
	@Column(name = "CUST_ID", unique = true)
	public String getCustId() {
		return this.custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Column(name = "CUST_NAME")
	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Column(name = "CUST_MOBILE")
	public String getCustMobile() {
		return this.custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	@Column(name = "CUST_SHORT_NAME")
	public String getCustShortName() {
		return this.custShortName;
	}

	public void setCustShortName(String custShortName) {
		this.custShortName = custShortName;
	}

	@Column(name = "CUST_CONTCAT")
	public String getCustContcat() {
		return this.custContcat;
	}

	public void setCustContcat(String custContcat) {
		this.custContcat = custContcat;
	}

	@Column(name = "CUST_PHONE")
	public String getCustPhone() {
		return this.custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	
	@Column(name = "OPEN_BANK")
	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	
	@Column(name = "ACCOUNT")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	@Column(name = "UNIT_ADDRESS")
	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	@Column(name = "DUTY_OARAGRAPH")
	public String getDutyParagraph() {
		return dutyParagraph;
	}

	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph;
	}
	
	@Column(name = "ID_NUMBER")
	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	@Column(name="CUST_CODE")
	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	@Column(name="AREA_CODE")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	@Column(name="CUST_NO")
	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	
}