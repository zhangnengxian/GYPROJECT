package cc.dfsoft.project.biz.base.charge.entity;

import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.BillStateEnum;
import cc.dfsoft.project.biz.base.charge.enums.BillTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.entity.PaymentApply;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * CashFlow entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CASH_FLOW")
public class CashFlow  implements Serializable {


    // Fields    

    /**
	 * 收付流水对象
	 */
	 private static final long serialVersionUID = -8907358948205031555L;
	 private String cfId;				//主键ID
     private String projId;				//工程ID
     private String projNo;				//工程编号
     private String projName;			//工程名称
     private String projCustId;			//客户编号
     private String projCustName;		//客户名称
     private String cfType;				//收付款类型
     private Integer cfFlag;			//收付标志
     private String cfAbstract;			//收付款摘要
     private BigDecimal cfAmount;		//金额
     private Date cfDate;				//收付款时间
    // private String cfOperator;		//收付款人
    // private String cfCompany;		//收付款部门ID
     private String remark;				//备注
     private Staff staff;				//员工
     private Department department;		//部门
 	 private String cfTypeDes;			//收款类型描述
  	 private String account;			//部门
  	 private String openBank;			//开户行
  	 private String custId;				//客户id
  	 private String unitAddress;		//单位地址
  	 private String dutyParagraph;		//税号
  	 private String cfFlagDes;			//收款类型描述
  	 private String billStateDes;		//1已开,0未开

  	 private String invoice;			//是否开票
  	 private BigDecimal invoiceAmount;	//发票金额
  	 private String invoiceNo;			//发票号
  	 private Date invoiceDate;			//开票日期
  	 
 	 private BigDecimal receiveAmount;	//实收款
 	 private String arId;	            //应收记录id	 
     private String  billType;          //票据类型
     private Date recInvoiceDate;		//收票日期	
  	 private String receiveInvoice;		//收票状态
  	 private String  billTypeDes;       //票据类型
  	private String  isValid;			//是否有效 0：无效效，1：有效（取消收款时翻转成无效）
    // Constructors
  	 
  	 private String fullAmount;			//全额收款-只读
  	 private String gasType;			//通气类型 --只读

	private String cashAccount;					//收款账号
	private String increment;					//税率
	private Date cashDate;               	    //收款时间

	private String contractType;			    //是否为智能表合同

	private String billStatus;			//是否是虚拟发票：0-否，1-是

	private String resultFlag;				//付款成功标记；0-成功
	private String resultFlagDes;			//付款成功标记描述；0-成功
	
	private String conNo;					//合同编号
	private String contractAmount;			//合同金额-只用于页面显示
	private PaymentApply paymentApply;		//只用于页面显示
	
	@Column(name="CONTRACT_TYPE")
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	@Column(name="CASH_ACCOUNT")
	public String getCashAccount() {
		return cashAccount;
	}

	public void setCashAccount(String cashAccount) {
		this.cashAccount = cashAccount;
	}

	@Column(name="INCREMENT")
	public String getIncrement() {
		return increment;
	}

	public void setIncrement(String increment) {
		this.increment = increment;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="CASH_DATE")
	public Date getCashDate() {
		return cashDate;
	}

	public void setCashDate(Date cashDate) {
		this.cashDate = cashDate;
	}

	@Column(name="INVOICE")
	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	@Column(name="INVOICE_AMOUNT")
	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	@Column(name="INVOICE_NO")
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="INVOICE_DATE")
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/** default constructor */
    public CashFlow() {
    }

    // Property accessors
    @Id 
    @Column(name="CF_ID", unique=true)
    public String getCfId() {
        return this.cfId;
    }
    
    public void setCfId(String cfId) {
        this.cfId = cfId;
    }
    
    @Column(name="PROJ_ID")
    public String getProjId() {
        return this.projId;
    }
    
    public void setProjId(String projId) {
        this.projId = projId;
    }
    
    @Column(name="PROJ_NO")
    public String getProjNo() {
        return this.projNo;
    }
    
    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }
    
    @Column(name="PROJ_NAME")
    public String getProjName() {
        return this.projName;
    }
    
    public void setProjName(String projName) {
        this.projName = projName;
    }
    
    @Column(name="PROJ_CUST_Id")
    public String getProjCustId() {
        return this.projCustId;
    }
    
    public void setProjCustId(String projCustId) {
        this.projCustId = projCustId;
    }
    
    @Column(name="PROJ_CUST_NAME")
    public String getProjCustName() {
        return this.projCustName;
    }
    
    public void setProjCustName(String projCustName) {
        this.projCustName = projCustName;
    }
    
    @Column(name="CF_TYPE")
    public String getCfType() {
        return this.cfType;
    }
    
    public void setCfType(String cfType) {
        this.cfType = cfType;
    }
    
    @Column(name="CF_FLAG")
    public Integer getCfFlag() {
        return this.cfFlag;
    }
    
    public void setCfFlag(Integer cfFlag) {
        this.cfFlag = cfFlag;
    }
    
    @Column(name="CF_ABSTRACT")
    public String getCfAbstract() {
        return this.cfAbstract;
    }
    
    public void setCfAbstract(String cfAbstract) {
        this.cfAbstract = cfAbstract;
    }
    
    @Column(name="CF_AMOUNT")
    public BigDecimal getCfAmount() {
        return this.cfAmount;
    }
    
    public void setCfAmount(BigDecimal cfAmount) {
        this.cfAmount = cfAmount;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CF_DATE")
    public Date getCfDate() {
        return this.cfDate;
    }
    
    public void setCfDate(Date cfDate) {
        this.cfDate = cfDate;
    }
    
    @Column(name="REMARK")
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    @ManyToOne(optional=true)
    @JoinColumn(name="CF_OPERATOR")
	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	@ManyToOne(optional=true)
	@JoinColumn(name="CF_COMPANY")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Transient
	public String getCfTypeDes() {
		if(this.cfType!=null){
		return CollectionTypeEnum.valueof(this.cfType).getMessage();
		}else{
			return null;
		}
	}

	public void setCfTypeDes(String cfTypeDes) {
		this.cfTypeDes = cfTypeDes;
	}
	@Transient
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	@Transient
	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	@Transient
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
	@Transient
	public String getCfFlagDes() {
		if(this.cfFlag!=null){
			return ARFlagEnum.valueof(this.cfFlag.toString()).getMessage();
			}else{
				return null;
			}
	}

	public void setCfFlagDes(String cfFlagDes) {
		this.cfFlagDes = cfFlagDes;
	}
	@Transient
	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}

	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}
	@Column(name="AR_ID")
	public String getArId() {
		return arId;
	}

	public void setArId(String arId) {
		this.arId = arId;
	}
	@Column(name="BILL_TYPE")
	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}
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
	public String getFullAmount() {
		return fullAmount;
	}

	public void setFullAmount(String fullAmount) {
		this.fullAmount = fullAmount;
	}
	
	@Transient
	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	
	@Transient
	public String getDutyParagraph() {
		return dutyParagraph;
	}

	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph;
	}
	
	@Transient
	public String getGasType() {
		return gasType;
	}

	public void setGasType(String gasType) {
		this.gasType = gasType;
	}

	@Column(name="IS_VALID")
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	@Column(name = "RESULT_FLAG")
	public String getResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(String resultFlag) {
		this.resultFlag = resultFlag;
	}
	
	@Column(name="CON_NO")
	public String getConNo() {
		return conNo;
	}

	public void setConNo(String conNo) {
		this.conNo = conNo;
	}

	@Transient
	public String getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}

	@Transient
	public PaymentApply getPaymentApply() {
		return paymentApply;
	}

	public void setPaymentApply(PaymentApply paymentApply) {
		this.paymentApply = paymentApply;
	}
	
	@Column(name="BILL_STATUS")
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	@Transient
	public String getResultFlagDes() {
		return resultFlagDes;
	}

	public void setResultFlagDes(String resultFlagDes) {
		this.resultFlagDes = resultFlagDes;
	}
	
	
}