package cc.dfsoft.project.biz.base.annualplan.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/8/15.
 */
@Entity
@Table(name = "annual_plan")
public class AnnualPlan {
    private String planId;
    private String planNo;					//计划编号
    private String planName;				//计划名称
    private String planAddr;				//计划地点
    private String affiliatedCompany;		//燃气公司ID
    private String corpName;				//燃气公司
    private String pipeDiameter;			//管径
    private BigDecimal pipeLength;			//项目长度(千米)
    private BigDecimal finishLength;		//完成长度(千米)
    private String completionSchedule;		//完成进度
    private String projectType;				//项目类别
    private BigDecimal investmentScale;		//预算价(万元)
    private BigDecimal alradyInvestment;	//已投资(万元)
    private BigDecimal planInvestment;		//计划投资(万元)
    private String constructionRatio;		//建设比例

    private String projectTypeDes;

    private String deptId;                  //业务组
    private String contributionMode;        //出资方式

    @Column(name = "DEPT_ID")
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Column(name = "CONTRIBUTION_MODE")
    public String getContributionMode() {
        return contributionMode;
    }

    public void setContributionMode(String contributionMode) {
        this.contributionMode = contributionMode;
    }

    @Column(name = "CORP_NAME")
    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    @Transient
    public String getProjectTypeDes() {
        return projectTypeDes;
    }

    public void setProjectTypeDes(String projectTypeDes) {
        this.projectTypeDes = projectTypeDes;
    }

    @Id
    @Column(name = "PLAN_ID")
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    @Basic
    @Column(name = "PLAN_NO")
    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo;
    }

    @Basic
    @Column(name = "PLAN_NAME")
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @Basic
    @Column(name = "PLAN_ADDR")
    public String getPlanAddr() {
        return planAddr;
    }

    public void setPlanAddr(String planAddr) {
        this.planAddr = planAddr;
    }

    @Basic
    @Column(name = "AFFILIATED_COMPANY")
    public String getAffiliatedCompany() {
        return affiliatedCompany;
    }

    public void setAffiliatedCompany(String affiliatedCompany) {
        this.affiliatedCompany = affiliatedCompany;
    }

    @Basic
    @Column(name = "PIPE_DIAMETER")
    public String getPipeDiameter() {
        return pipeDiameter;
    }

    public void setPipeDiameter(String pipeDiameter) {
        this.pipeDiameter = pipeDiameter;
    }

    @Basic
    @Column(name = "PIPE_LENGTH")
    public BigDecimal getPipeLength() {
        return pipeLength;
    }

    public void setPipeLength(BigDecimal pipeLength) {
        this.pipeLength = pipeLength;
    }

    @Basic
    @Column(name = "FINISH_LENGTH")
    public BigDecimal getFinishLength() {
        return finishLength;
    }

    public void setFinishLength(BigDecimal finishLength) {
        this.finishLength = finishLength;
    }

    @Basic
    @Column(name = "COMPLETION_SCHEDULE")
    public String getCompletionSchedule() {
        return completionSchedule;
    }

    public void setCompletionSchedule(String completionSchedule) {
        this.completionSchedule = completionSchedule;
    }

    @Basic
    @Column(name = "PROJECT_TYPE")
    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    @Basic
    @Column(name = "INVESTMENT_SCALE")
    public BigDecimal getInvestmentScale() {
        return investmentScale;
    }

    public void setInvestmentScale(BigDecimal investmentScale) {
        this.investmentScale = investmentScale;
    }

    @Basic
    @Column(name = "ALRADY_INVESTMENT")
    public BigDecimal getAlradyInvestment() {
        return alradyInvestment;
    }

    public void setAlradyInvestment(BigDecimal alradyInvestment) {
        this.alradyInvestment = alradyInvestment;
    }

    @Basic
    @Column(name = "PLAN_INVESTMENT")
    public BigDecimal getPlanInvestment() {
        return planInvestment;
    }

    public void setPlanInvestment(BigDecimal planInvestment) {
        this.planInvestment = planInvestment;
    }

    @Basic
    @Column(name = "CONSTRUCTION_RATIO")
    public String getConstructionRatio() {
        return constructionRatio;
    }

    public void setConstructionRatio(String constructionRatio) {
        this.constructionRatio = constructionRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnnualPlan that = (AnnualPlan) o;

        if (planId != null ? !planId.equals(that.planId) : that.planId != null) return false;
        if (planNo != null ? !planNo.equals(that.planNo) : that.planNo != null) return false;
        if (planName != null ? !planName.equals(that.planName) : that.planName != null) return false;
        if (planAddr != null ? !planAddr.equals(that.planAddr) : that.planAddr != null) return false;
        if (affiliatedCompany != null ? !affiliatedCompany.equals(that.affiliatedCompany) : that.affiliatedCompany != null)
            return false;
        if (pipeDiameter != null ? !pipeDiameter.equals(that.pipeDiameter) : that.pipeDiameter != null) return false;
        if (pipeLength != null ? !pipeLength.equals(that.pipeLength) : that.pipeLength != null) return false;
        if (finishLength != null ? !finishLength.equals(that.finishLength) : that.finishLength != null) return false;
        if (completionSchedule != null ? !completionSchedule.equals(that.completionSchedule) : that.completionSchedule != null)
            return false;
        if (projectType != null ? !projectType.equals(that.projectType) : that.projectType != null) return false;
        if (investmentScale != null ? !investmentScale.equals(that.investmentScale) : that.investmentScale != null)
            return false;
        if (alradyInvestment != null ? !alradyInvestment.equals(that.alradyInvestment) : that.alradyInvestment != null)
            return false;
        if (planInvestment != null ? !planInvestment.equals(that.planInvestment) : that.planInvestment != null)
            return false;
        if (constructionRatio != null ? !constructionRatio.equals(that.constructionRatio) : that.constructionRatio != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = planId != null ? planId.hashCode() : 0;
        result = 31 * result + (planNo != null ? planNo.hashCode() : 0);
        result = 31 * result + (planName != null ? planName.hashCode() : 0);
        result = 31 * result + (planAddr != null ? planAddr.hashCode() : 0);
        result = 31 * result + (affiliatedCompany != null ? affiliatedCompany.hashCode() : 0);
        result = 31 * result + (pipeDiameter != null ? pipeDiameter.hashCode() : 0);
        result = 31 * result + (pipeLength != null ? pipeLength.hashCode() : 0);
        result = 31 * result + (finishLength != null ? finishLength.hashCode() : 0);
        result = 31 * result + (completionSchedule != null ? completionSchedule.hashCode() : 0);
        result = 31 * result + (projectType != null ? projectType.hashCode() : 0);
        result = 31 * result + (investmentScale != null ? investmentScale.hashCode() : 0);
        result = 31 * result + (alradyInvestment != null ? alradyInvestment.hashCode() : 0);
        result = 31 * result + (planInvestment != null ? planInvestment.hashCode() : 0);
        result = 31 * result + (constructionRatio != null ? constructionRatio.hashCode() : 0);
        return result;
    }


}
