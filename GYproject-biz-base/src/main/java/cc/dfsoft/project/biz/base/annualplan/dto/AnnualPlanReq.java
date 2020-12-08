package cc.dfsoft.project.biz.base.annualplan.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * Created by cui on 2017/8/8.
 */
public class AnnualPlanReq extends PageSortReq {
    private String planId;
    private String planNo;
    private String planName;
    private String planAddr;
//    private String affiliatedCompany;
//    private String pipeDiameter;
//    private BigDecimal pipeLength;
//    private BigDecimal finishLength;
//    private String completionSchedule;
    private String projectType;
//    private BigDecimal investmentScale;
//    private BigDecimal alradyInvestment;
//    private BigDecimal planInvestment;
//    private String constructionRatio;


    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanAddr() {
        return planAddr;
    }

    public void setPlanAddr(String planAddr) {
        this.planAddr = planAddr;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
}
