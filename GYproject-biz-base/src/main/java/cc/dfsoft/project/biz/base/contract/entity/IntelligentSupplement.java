package cc.dfsoft.project.biz.base.contract.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/7/26 15:11
 * @Version:1.0
 */
@Entity
@Table(name="INTELLIGENT_SUPPLEMENT")
public class IntelligentSupplement {
    private String isId;                //协议ID
    private String isNo;                //协议编号
    private BigDecimal isAmount;        //协议金额
    private String invoiceType;         //发票类型
    private String increment;           //税率
    private String isPrint;             //是否打印
    private String isStatus;            //0:待签,1:待推送,2:审核中,3:已通过,4:未通过,5：待修改，-1:已作废
    private String payType;             //付款方式
    private String paymentRatio1;       //首付比例
    private BigDecimal firstPayment;    //首付金额
    private String paymentRatio2;       //二期付款比例
    private BigDecimal secondPayment;   //二期付款金额
    private String paymentRatio3;       //三期付款比例
    private BigDecimal thridPayment;    //三期付款金额
    private String changeReasons;       //变更原因
    private Date signDate;              //签订日期
    private Date changeTime;            //变更日期
    private String agent;               //经办人
    private String modifyRemark;        //修改原因
    private Date modifyDate;            //修改日期
    private String isValid;             //有效：1，无效：0
    private String  remark;             //备注
    private String imcId;               //智能表合同ID
    private String projId;              //工程ID
    private String imcNo;               //智能表合同编号
    private String flag;                //标志：0:新增,1:修改,2:推送,3：审核，5：修改申请，-1:作废
    private String menuId;              //菜单ID




    @Id
    @Column(name = "IS_ID", unique = true)
    public String getIsId() {
        return isId;
    }
    public void setIsId(String isId) {
        this.isId = isId;
    }

    @Column(name = "IS_NO")
    public String getIsNo() {
        return isNo;
    }
    public void setIsNo(String isNo) {
        this.isNo = isNo;
    }

    @Column(name = "IS_AMOUNT")
    public BigDecimal getIsAmount() {
        return isAmount;
    }
    public void setIsAmount(BigDecimal isAmount) {
        this.isAmount = isAmount;
    }

    @Column(name = "INVOICE_TYPE")
    public String getInvoiceType() {
        return invoiceType;
    }
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    @Column(name = "INCREMENT")
    public String getIncrement() {
        return increment;
    }
    public void setIncrement(String increment) {
        this.increment = increment;
    }

    @Column(name = "IS_PRINT")
    public String getIsPrint() {
        return isPrint;
    }
    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint;
    }

    @Column(name = "IS_STATUS")
    public String getIsStatus() {
        return isStatus;
    }
    public void setIsStatus(String isStatus) {
        this.isStatus = isStatus;
    }

    @Column(name = "PAY_TYPE")
    public String getPayType() {
        return payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Column(name = "PAYMENT_RATIO1")
    public String getPaymentRatio1() {
        return paymentRatio1;
    }
    public void setPaymentRatio1(String paymentRatio1) {
        this.paymentRatio1 = paymentRatio1;
    }

    @Column(name = "FIRST_PAYMENT")
    public BigDecimal getFirstPayment() {
        return firstPayment;
    }
    public void setFirstPayment(BigDecimal firstPayment) {
        this.firstPayment = firstPayment;
    }

    @Column(name = "PAYMENT_RATIO2")
    public String getPaymentRatio2() {
        return paymentRatio2;
    }
    public void setPaymentRatio2(String paymentRatio2) {
        this.paymentRatio2 = paymentRatio2;
    }

    @Column(name = "SECOND_PAYMENT")
    public BigDecimal getSecondPayment() {
        return secondPayment;
    }
    public void setSecondPayment(BigDecimal secondPayment) {
        this.secondPayment = secondPayment;
    }

    @Column(name = "PAYMENT_RATIO3")
    public String getPaymentRatio3() {
        return paymentRatio3;
    }
    public void setPaymentRatio3(String paymentRatio3) {
        this.paymentRatio3 = paymentRatio3;
    }

    @Column(name = "THRID_PAYMENT")
    public BigDecimal getThridPayment() {
        return thridPayment;
    }
    public void setThridPayment(BigDecimal thridPayment) {
        this.thridPayment = thridPayment;
    }

    @Column(name = "CHANGE_REASONS")
    public String getChangeReasons() {
        return changeReasons;
    }
    public void setChangeReasons(String changeReasons) {
        this.changeReasons = changeReasons;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "SIGN_DATE")
    public Date getSignDate() {
        return signDate;
    }
    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CHANGE_TIME")
    public Date getChangeTime() {
        return changeTime;
    }
    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    @Column(name = "AGENT")
    public String getAgent() {
        return agent;
    }
    public void setAgent(String agent) {
        this.agent = agent;
    }

    @Column(name = "IMC_ID")
    public String getImcId() {
        return imcId;
    }
    public void setImcId(String imcId) {
        this.imcId = imcId;
    }

    @Column(name = "PROJ_ID")
    public String getProjId() {
        return projId;
    }
    public void setProjId(String projId) {
        this.projId = projId;
    }


    @Column(name = "MODIFY_REMARK")
    public String getModifyRemark() {
        return modifyRemark;
    }
    public void setModifyRemark(String modifyRemark) {
        this.modifyRemark = modifyRemark;
    }

    @Column(name = "MODIFY_DATE")
    public Date getModifyDate() {
        return modifyDate;
    }
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Column(name = "IS_VALID")
    public String getIsValid() {
        return isValid;
    }
    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    @Transient
    public String getImcNo() {
        return imcNo;
    }
    public void setImcNo(String imcNo) {
        this.imcNo = imcNo;
    }

    @Transient
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Transient
    public String getMenuId() {
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
