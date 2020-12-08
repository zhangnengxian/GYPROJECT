package cc.dfsoft.project.biz.base.contract.dto;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/7/26 15:11
 * @Version:1.0
 */

public class IntelligentSupplementReq extends ProjectQueryReq {
    private String isId;                    //协议ID
    private String isNo;                    //协议编号
    private BigDecimal isAmount;            //协议金额
    private String invoiceType;            //发票类型
    private String increment;               //税率
    private String isPrint;                 //是否打印
    private String isStatus;                //0:待签,1:待推送,2:审核中,3:已通过,4:未通过,-1:已作废
    private String isStatusDes;             //0:待签,1:待推送,2:审核中,3:已通过,4:未通过,-1:已作废
    private String payType;                 //付款方式
    private String paymentRatio1;           //首付比例
    private BigDecimal firstPayment;         //首付金额
    private String paymentRatio2;           //二期付款比例
    private BigDecimal secondPayment;       //二期付款金额
    private String paymentRatio3;           //三期付款比例
    private BigDecimal thridPayment;        //三期付款金额
    private String changeReasons;           //变更原因
    private Date signDate;                  //签订日期
    private Date changeTime;                 //变更日期
    private String agent;                   //经办人
    private String modifyRemark;        //修改原因
    private String isValid;             //有效：1，无效：0
    private String imcId;                    //智能表合同ID
    private String projId;                 //工程ID
    private String imcNo;                //智能表合同编号


    public String getIsId() {
        return isId;
    }

    public void setIsId(String isId) {
        this.isId = isId;
    }

    public String getIsNo() {
        return isNo;
    }

    public void setIsNo(String isNo) {
        this.isNo = isNo;
    }

    public BigDecimal getIsAmount() {
        return isAmount;
    }

    public void setIsAmount(BigDecimal isAmount) {
        this.isAmount = isAmount;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getIncrement() {
        return increment;
    }

    public void setIncrement(String increment) {
        this.increment = increment;
    }

    public String getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint;
    }

    public String getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(String isStatus) {
        this.isStatus = isStatus;
    }

    public String getIsStatusDes() {
        return isStatusDes;
    }

    public void setIsStatusDes(String isStatusDes) {
        this.isStatusDes = isStatusDes;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPaymentRatio1() {
        return paymentRatio1;
    }

    public void setPaymentRatio1(String paymentRatio1) {
        this.paymentRatio1 = paymentRatio1;
    }

    public BigDecimal getFirstPayment() {
        return firstPayment;
    }

    public void setFirstPayment(BigDecimal firstPayment) {
        this.firstPayment = firstPayment;
    }

    public String getPaymentRatio2() {
        return paymentRatio2;
    }

    public void setPaymentRatio2(String paymentRatio2) {
        this.paymentRatio2 = paymentRatio2;
    }

    public BigDecimal getSecondPayment() {
        return secondPayment;
    }

    public void setSecondPayment(BigDecimal secondPayment) {
        this.secondPayment = secondPayment;
    }

    public String getPaymentRatio3() {
        return paymentRatio3;
    }

    public void setPaymentRatio3(String paymentRatio3) {
        this.paymentRatio3 = paymentRatio3;
    }

    public BigDecimal getThridPayment() {
        return thridPayment;
    }

    public void setThridPayment(BigDecimal thridPayment) {
        this.thridPayment = thridPayment;
    }

    public String getChangeReasons() {
        return changeReasons;
    }

    public void setChangeReasons(String changeReasons) {
        this.changeReasons = changeReasons;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getModifyRemark() {
        return modifyRemark;
    }

    public void setModifyRemark(String modifyRemark) {
        this.modifyRemark = modifyRemark;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getImcId() {
        return imcId;
    }

    public void setImcId(String imcId) {
        this.imcId = imcId;
    }

    @Override
    public String getProjId() {
        return projId;
    }

    @Override
    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getImcNo() {
        return imcNo;
    }

    public void setImcNo(String imcNo) {
        this.imcNo = imcNo;
    }
}
