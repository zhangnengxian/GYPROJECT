package cc.dfsoft.project.biz.base.messagesync.hongju.pojo;

import java.math.BigDecimal;

/**
 *@ Description: 施工合同同步信息
 *@ author: zhangnx
 *@ Date: 2019/11/22 17:19
 *@ Version:1.0
 * */
public class SubContractInfo {
    private String projNo;					//工程编号
    private BigDecimal scAmount;			//协议价款
    private String increment;				//施工合同税率
    private String scSignDate;				//签订日期-用于报表显示（先会审交底的取第一次会审交底的日期）
    private String scPlannedTotalDays;		//计划天数
    private String scPlannedStartDate;		//计划开工日期
    private String scPlannedEndDate;			//计划竣工日期-有填写配合施工的 ，所以日期修改为String类型
    private String operateDate;			//施工合同实际签订日期
    private String contractMethod;		//建筑服务

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

    public String getIncrement() {
        return increment;
    }

    public void setIncrement(String increment) {
        this.increment = increment;
    }

    public String getScSignDate() {
        return scSignDate;
    }

    public void setScSignDate(String scSignDate) {
        this.scSignDate = scSignDate;
    }

    public String getScPlannedTotalDays() {
        return scPlannedTotalDays;
    }

    public void setScPlannedTotalDays(String scPlannedTotalDays) {
        this.scPlannedTotalDays = scPlannedTotalDays;
    }

    public String getScPlannedStartDate() {
        return scPlannedStartDate;
    }

    public void setScPlannedStartDate(String scPlannedStartDate) {
        this.scPlannedStartDate = scPlannedStartDate;
    }

    public String getScPlannedEndDate() {
        return scPlannedEndDate;
    }

    public void setScPlannedEndDate(String scPlannedEndDate) {
        this.scPlannedEndDate = scPlannedEndDate;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }

    public String getContractMethod() {
        return contractMethod;
    }

    public void setContractMethod(String contractMethod) {
        this.contractMethod = contractMethod;
    }

    @Override
    public String toString() {
        return "SubContractInfo{" +
                "projNo='" + projNo + '\'' +
                ", scAmount=" + scAmount +
                ", increment='" + increment + '\'' +
                ", scSignDate=" + scSignDate +
                ", scPlannedTotalDays='" + scPlannedTotalDays + '\'' +
                ", scPlannedStartDate=" + scPlannedStartDate +
                ", scPlannedEndDate='" + scPlannedEndDate + '\'' +
                ", operateDate=" + operateDate +
                ", contractMethod='" + contractMethod + '\'' +
                '}';
    }
}
