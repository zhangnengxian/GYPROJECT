package cc.dfsoft.project.biz.base.maintain.entity;

import javax.persistence.*;
import java.util.Date;

/**
* @Description: 废弃数据记录
* @author zhangnx
* @date 2019/8/20 14:21
*@Version:1.0
*/
@Entity
@Table(name = "ABANDONED_RECORD")
public class AbandonedRecord {
    private String abId;                 //删除记录ID
    private String businessId;           //业务ID(关联ID)
    private String projId;               //工程ID
    private String stepId;               //步骤ID
    private String stepName;              //步骤名称
    private String operator;             //作废人
    private Date   operatingTime;        //作废时间
    private String abandonedReason;     //作废原因
    private String abandonedData;       //删除的数据
    private String insertSql;           //还原数据sql语句



    @Id
    @Column(name = "AB_ID", unique = true)
    public String getAbId() {
        return abId;
    }
    public void setAbId(String abId) {
        this.abId = abId;
    }

    @Column(name = "BUSINESS_ID", unique = true)
    public String getBusinessId() {
        return businessId;
    }
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    @Column(name = "PROJ_ID", unique = true)
    public String getProjId() {
        return projId;
    }
    public void setProjId(String projId) {
        this.projId = projId;
    }

    @Column(name = "STEP_ID", unique = true)
    public String getStepId() {
        return stepId;
    }
    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    @Column(name = "STEP_NAME", unique = true)
    public String getStepName() {
        return stepName;
    }
    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    @Column(name = "OPERATOR", unique = true)
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "OPERATING_TIME", unique = true)
    public Date getOperatingTime() {
        return operatingTime;
    }
    public void setOperatingTime(Date operatingTime) {
        this.operatingTime = operatingTime;
    }


    @Column(name = "ABANDONED_REASON", unique = true)
    public String getAbandonedReason() {
        return abandonedReason;
    }
    public void setAbandonedReason(String abandonedReason) {
        this.abandonedReason = abandonedReason;
    }

    @Column(name = "ABANDONED_DATA", unique = true)
    public String getAbandonedData() {
        return abandonedData;
    }
    public void setAbandonedData(String abandonedData) {
        this.abandonedData = abandonedData;
    }


    @Column(name = "INSERT_SQL", unique = true)
    public String getInsertSql() {
        return insertSql;
    }
    public void setInsertSql(String insertSql) {
        this.insertSql = insertSql;
    }
}
