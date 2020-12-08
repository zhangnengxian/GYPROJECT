package cc.dfsoft.project.biz.base.messagesync.hongju.pojo;

import java.math.BigDecimal;

/**
 *@ Description: 结算同步信息
 *@ author: zhangnx
 *@ Date: 2019/11/22 17:06
 *@ Version:1.0
 * */
public class SettlementDeclarationInfo {
    private BigDecimal sendDeclarationCost;		//送审金额
    private BigDecimal materialsCost;			//主材费
    private String compiler;                   //编制人
    private String ocoDate;						//报审日期
    private String settlementer;				//结算审核预算员
    private BigDecimal endDeclarationCost;		//终审金额
    private BigDecimal endMaterialsCost;		//终审主材费
    private String endDeclaraDate;				//终审日期

    public BigDecimal getSendDeclarationCost() {
        return sendDeclarationCost;
    }

    public void setSendDeclarationCost(BigDecimal sendDeclarationCost) {
        this.sendDeclarationCost = sendDeclarationCost;
    }

    public BigDecimal getMaterialsCost() {
        return materialsCost;
    }

    public void setMaterialsCost(BigDecimal materialsCost) {
        this.materialsCost = materialsCost;
    }

    public String getCompiler() {
        return compiler;
    }

    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    public String getOcoDate() {
        return ocoDate;
    }

    public void setOcoDate(String ocoDate) {
        this.ocoDate = ocoDate;
    }

    public String getSettlementer() {
        return settlementer;
    }

    public void setSettlementer(String settlementer) {
        this.settlementer = settlementer;
    }

    public BigDecimal getEndDeclarationCost() {
        return endDeclarationCost;
    }

    public void setEndDeclarationCost(BigDecimal endDeclarationCost) {
        this.endDeclarationCost = endDeclarationCost;
    }

    public BigDecimal getEndMaterialsCost() {
        return endMaterialsCost;
    }

    public void setEndMaterialsCost(BigDecimal endMaterialsCost) {
        this.endMaterialsCost = endMaterialsCost;
    }

    public String getEndDeclaraDate() {
        return endDeclaraDate;
    }

    public void setEndDeclaraDate(String endDeclaraDate) {
        this.endDeclaraDate = endDeclaraDate;
    }

    @Override
    public String toString() {
        return "SettlementDeclarationInfo{" +
                "sendDeclarationCost=" + sendDeclarationCost +
                ", materialsCost=" + materialsCost +
                ", compiler='" + compiler + '\'' +
                ", ocoDate=" + ocoDate +
                ", settlementer='" + settlementer + '\'' +
                ", endDeclarationCost=" + endDeclarationCost +
                ", endMaterialsCost=" + endMaterialsCost +
                ", endDeclaraDate=" + endDeclaraDate +
                '}';
    }
}
