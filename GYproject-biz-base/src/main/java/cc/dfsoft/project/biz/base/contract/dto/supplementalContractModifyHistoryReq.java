package cc.dfsoft.project.biz.base.contract.dto;

import java.math.BigDecimal;
import java.util.Date;
import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class supplementalContractModifyHistoryReq extends PageSortReq {
	private String scmId;    //修改协议编号
	private String custName;   //客户名称
	private String custPhone;   //客户联系电话
	private String gasComp;     //乙方燃气公司
	private String gasCompPhone;  //乙方燃气公司联系电话
	private String houseAddr;     // 地址
	private BigDecimal houseAmount;    //每户金额
	private int houseNum;      //户数
	private String increment;   //税率
	private String invoiceType;   //发票类型
	private String modifier;      //修改人
	private String priceDocument;   //文件价格
	private String projName;    //工程名称
	private String projNo;   //工程编号
	private BigDecimal scAmount;   //协议价
	private Date signDate;  //签订日期
	private String scId; //补充协议Id 
	public String getScmId() {
		return scmId;
	}
	public void setScmId(String scmId) {
		this.scmId = scmId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getGasComp() {
		return gasComp;
	}
	public void setGasComp(String gasComp) {
		this.gasComp = gasComp;
	}
	public String getGasCompPhone() {
		return gasCompPhone;
	}
	public void setGasCompPhone(String gasCompPhone) {
		this.gasCompPhone = gasCompPhone;
	}
	public String getHouseAddr() {
		return houseAddr;
	}
	public void setHouseAddr(String houseAddr) {
		this.houseAddr = houseAddr;
	}
	public BigDecimal getHouseAmount() {
		return houseAmount;
	}
	public void setHouseAmount(BigDecimal houseAmount) {
		this.houseAmount = houseAmount;
	}
	public int getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(int houseNum) {
		this.houseNum = houseNum;
	}
	public String getIncrement() {
		return increment;
	}
	public void setIncrement(String increment) {
		this.increment = increment;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getPriceDocument() {
		return priceDocument;
	}
	public void setPriceDocument(String priceDocument) {
		this.priceDocument = priceDocument;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public BigDecimal getScAmount() {
		return scAmount;
	}
	public void setScAmount(BigDecimal scAmount) {
		this.scAmount = scAmount;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public String getScId() {
		return scId;
	}
	public void setScId(String scId) {
		this.scId = scId;
	}
	
}
