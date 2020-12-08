package cc.dfsoft.project.biz.base.charge.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ARStatusEnum;
import cc.dfsoft.project.biz.base.charge.enums.BillStateEnum;
import cc.dfsoft.project.biz.base.charge.enums.BillTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.entity.PaymentApply;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
/**
 * AccrualsRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACCRUALS_RECORD")
public class AccrualsRecord implements Serializable {

	// Fields

	/**
	 * 应收应付流水对象
	 */
	private static final long serialVersionUID = -3704271626445243862L;
	private String arId;			//主键ID
	private String cfId;			//收付流水ID
	private String projId;			//工程ID
	private String projNo;			//工程编号
	private String custId;			//客户ID
	private String projCustName;	//客户名称
	private String arType;			//收款类型
	private Integer arFlag;			//收付标志
	private BigDecimal arCost;		//金额
	private Date arDate;			//产生时间
//	private String arOperate;		//操作人
	private String arNote;			//备注
	private String arStatus;		//状态
	private Staff staff;			//员工
	private String arTypeDes;		//收款类型描述
	private String arFlagDes;		//收付标志
	private String arStatusDes;     //状态
	private String billTypeDes;     //状态

	private String invoice;		    //是否开票
	private BigDecimal invoiceAmount;	//发票金额
 	private String invoiceNo;		    //发票号
 	private Date invoiceDate;		    //开票日期	
 	private BigDecimal receiveAmount;	//流水对应的金额-超收时，将前一次收款的超收记录到本次流水
 	private String  billType;           //票据类型
 	
	private Date recInvoiceDate;		//收票日期	
	private String receiveInvoice;		//收票状态
	private String billStateDes;
	// Constructors

	private String contractType;			    //是否为智能表合同
	private String paId;				//付款申请单ID
	
	private String increment;			//税率--只用于页面显示
	private String conNo;				//合同号
	private String contractAmount;		//合同金额--只用于页面显示
	private String billStatus;			//是否是虚拟发票
	
	private PaymentApply paymentApply;	//只用于页面显示数据

	@Column(name = "CONTRACT_TYPE")
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	/** default constructor */
	public AccrualsRecord() {
	}
	// Property accessors	
	@Id
	@Column(name = "AR_ID", unique = true)
	public String getArId() {
		return this.arId;
	}

	public void setArId(String arId) {
		this.arId = arId;
	}

	@Column(name = "CF_ID")
	public String getCfId() {
		return this.cfId;
	}

	public void setCfId(String cfId) {
		this.cfId = cfId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return this.projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "CUST_ID")
	public String getCustId() {
		return this.custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Column(name = "PROJ_CUST_NAME")
	public String getProjCustName() {
		return this.projCustName;
	}

	public void setProjCustName(String projCustName) {
		this.projCustName = projCustName;
	}

	@Column(name = "AR_TYPE")
	public String getArType() {
		return this.arType;
	}

	public void setArType(String arType) {
		this.arType = arType;
	}

	@Column(name = "AR_FLAG")
	public Integer getArFlag() {
		return this.arFlag;
	}

	public void setArFlag(Integer arFlag) {
		this.arFlag = arFlag;
	}

	@Column(name = "AR_COST")
	public BigDecimal getArCost() {
		return this.arCost;
	}

	public void setArCost(BigDecimal arCost) {
		this.arCost = arCost;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AR_DATE")
	public Date getArDate() {
		return this.arDate;
	}

	public void setArDate(Date arDate) {
		this.arDate = arDate;
	}

	@Column(name = "AR_NOTE")
	public String getArNote() {
		return this.arNote;
	}

	public void setArNote(String arNote) {
		this.arNote = arNote;
	}

	@Column(name = "AR_STATUS")
	public String getArStatus() {
		return this.arStatus;
	}

	public void setArStatus(String arStatus) {
		this.arStatus = arStatus;
	}

	@ManyToOne(optional=true)
    @JoinColumn(name="AR_OPERATE")
	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Transient
	public String getArTypeDes() {
		if(this.arType!=null){
			return CollectionTypeEnum.valueof(this.arType).getMessage();
		}else{
			return null;
		}
	}


	public void setArTypeDes(String arTypeDes) {
		this.arTypeDes = arTypeDes;
	}

	@Transient
	public String getArFlagDes() {
		if(this.arFlag!=null){
			return ARFlagEnum.valueof(this.arFlag.toString()).getMessage();
		}else{
			return null;
		}
	}


	public void setArFlagDes(String arFlagDes) {
		this.arFlagDes = arFlagDes;
	}

	@Transient
	public String getArStatusDes() {
		
		if(this.arStatus!=null){
			return ARStatusEnum.valueof(this.arStatus).getMessage();
		}else{
			return "";
		}
	}

	public void setArStatusDes(String arStatusDes) {
		this.arStatusDes = arStatusDes;
	}
	
	//发票信息
	@Column(name = "INVOICE")
	public String getInvoice() {
		return invoice;
	}


	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	@Column(name = "INVOICE_AMOUNT")
	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}


	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	@Column(name = "INVOICE_NO")
	public String getInvoiceNo() {
		return invoiceNo;
	}


	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INVOICE_DATE")
	public Date getInvoiceDate() {
		return invoiceDate;
	}


	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@Column(name = "RECEIVE_AMOUNT")
	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}


	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}
	@Column(name="BILL_TYPE")
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="REC_INVOICE_DATE")
	public Date getRecInvoiceDate() {
		return recInvoiceDate;
	}
	public void setRecInvoiceDate(Date recInvoiceDate) {
		this.recInvoiceDate = recInvoiceDate;
	}
	@Column(name="RECEIVE_INVOICE")
	public String getReceiveInvoice() {
		return receiveInvoice;
	}
	public void setReceiveInvoice(String receiveInvoice) {
		this.receiveInvoice = receiveInvoice;
	}
	@Transient
	public String getBillTypeDes() {
		if(this.billType!=null){
			return BillTypeEnum.valueof(this.billType).getMessage();
		}else{
			return "";
		}
	}
	public void setBillTypeDes(String billTypeDes) {
		this.billTypeDes = billTypeDes;
	}
	
    //发票和收据统称为票据状态
	@Transient
	public String getBillStateDes() {
		if(this.invoice!=null&&this.invoice.equals("1")||this.receiveInvoice!=null&&this.receiveInvoice.equals("1")){
				return BillStateEnum.valueof("1").getMessage();	
			}else{
				return BillStateEnum.valueof("0").getMessage();	
			}
		}
	

	public void setBillStateDes(String billStateDes) {
		this.billStateDes = billStateDes;
	}

	@Transient
	public String getIncrement() {
		return increment;
	}

	public void setIncrement(String increment) {
		this.increment = increment;
	}

	@Column(name="CON_NO")
	public String getConNo() {
		return conNo;
	}

	public void setConNo(String conNo) {
		this.conNo = conNo;
	}

	@Column(name="PA_ID")
	public String getPaId() {
		return paId;
	}

	public void setPaId(String paId) {
		this.paId = paId;
	}

	@Transient
	public PaymentApply getPaymentApply() {
		return paymentApply;
	}

	public void setPaymentApply(PaymentApply paymentApply) {
		this.paymentApply = paymentApply;
	}

	@Transient
	public String getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}

	@Transient
	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
}