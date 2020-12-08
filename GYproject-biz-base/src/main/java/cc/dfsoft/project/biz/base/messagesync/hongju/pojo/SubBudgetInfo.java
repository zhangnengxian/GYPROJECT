package cc.dfsoft.project.biz.base.messagesync.hongju.pojo;

import java.math.BigDecimal;

/**
 *@ Description: 施工预算
 *@ author: zhangnx
 *@ Date: 2019/11/22 16:59
 *@ Version:1.0
 * */
public class SubBudgetInfo {
    private String projNo;					        //工程编号
    private BigDecimal totalCostAudit;				//审核清单总造价
    private BigDecimal mainMaterialAmountListAudit;	//审核清单主材费
    private String operater;                        //操作人姓名
    private String operateDate;						//实际预算编制日期
    private String suBudgeter;						//分包预算员
    private String sbDate;							//预算日期
    private BigDecimal totalCost;					//清单计价总造价
    private BigDecimal mainMaterialAmountList;		//清单计价主材费

    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    public BigDecimal getTotalCostAudit() {
        return totalCostAudit;
    }

    public void setTotalCostAudit(BigDecimal totalCostAudit) {
        this.totalCostAudit = totalCostAudit;
    }

    public BigDecimal getMainMaterialAmountListAudit() {
        return mainMaterialAmountListAudit;
    }

    public void setMainMaterialAmountListAudit(BigDecimal mainMaterialAmountListAudit) {
        this.mainMaterialAmountListAudit = mainMaterialAmountListAudit;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }

    public String getSuBudgeter() {
        return suBudgeter;
    }

    public void setSuBudgeter(String suBudgeter) {
        this.suBudgeter = suBudgeter;
    }

    public String getSbDate() {
        return sbDate;
    }

    public void setSbDate(String sbDate) {
        this.sbDate = sbDate;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getMainMaterialAmountList() {
        return mainMaterialAmountList;
    }

    public void setMainMaterialAmountList(BigDecimal mainMaterialAmountList) {
        this.mainMaterialAmountList = mainMaterialAmountList;
    }

    @Override
    public String toString() {
        return "SubBudgetInfo{" +
                "projNo='" + projNo + '\'' +
                ", totalCostAudit=" + totalCostAudit +
                ", mainMaterialAmountListAudit=" + mainMaterialAmountListAudit +
                ", operater='" + operater + '\'' +
                ", operateDate=" + operateDate +
                ", suBudgeter='" + suBudgeter + '\'' +
                ", sbDate=" + sbDate +
                ", totalCost=" + totalCost +
                ", mainMaterialAmountList=" + mainMaterialAmountList +
                '}';
    }
}
