package cc.dfsoft.project.biz.base.subpackage.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/9.
 * 已作废
 */
@Entity
@Table(name = "cost_apply")
public class CostApply {
    private String caId;
    private String caNo;
    private Date caDate;
    private String caUnit;
    private String caUnitId;
    private String applyerId;
    private String applyer;
    private String caType;
    private String caRemark;
    private String caStatus;

    @Id
    @Column(name = "CA_ID")
    public String getCaId() {
        return caId;
    }

    public void setCaId(String caId) {
        this.caId = caId;
    }

    @Basic
    @Column(name = "CA_NO")
    public String getCaNo() {
        return caNo;
    }

    public void setCaNo(String caNo) {
        this.caNo = caNo;
    }

    @Basic
    @Column(name = "CA_DATE")
    @Temporal(TemporalType.DATE)
    public Date getCaDate() {
        return caDate;
    }

    public void setCaDate(Date caDate) {
        this.caDate = caDate;
    }

    @Basic
    @Column(name = "CA_UNIT")
    public String getCaUnit() {
        return caUnit;
    }

    public void setCaUnit(String caUnit) {
        this.caUnit = caUnit;
    }

    @Basic
    @Column(name = "CA_UNIT_ID")
    public String getCaUnitId() {
        return caUnitId;
    }

    public void setCaUnitId(String caUnitId) {
        this.caUnitId = caUnitId;
    }

    @Basic
    @Column(name = "APPLYER_ID")
    public String getApplyerId() {
        return applyerId;
    }

    public void setApplyerId(String applyerId) {
        this.applyerId = applyerId;
    }

    @Basic
    @Column(name = "APPLYER")
    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    @Basic
    @Column(name = "CA_TYPE")
    public String getCaType() {
        return caType;
    }

    public void setCaType(String caType) {
        this.caType = caType;
    }

    @Basic
    @Column(name = "CA_REMARK")
    public String getCaRemark() {
        return caRemark;
    }

    public void setCaRemark(String caRemark) {
        this.caRemark = caRemark;
    }

    @Column(name = "CA_STATUS")
    public String getCaStatus() {
        return caStatus;
    }

    public void setCaStatus(String caStatus) {
        this.caStatus = caStatus;
    }
}
