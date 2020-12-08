package cc.dfsoft.project.biz.base.withgas.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/8.
 */
@Entity
@Table(name = "gas_plan")
public class GasPlan {
    private String gpId;
    private String gpNo;
    private String gpName;
    private String creater;
    private Date createDate;
    private String remark;
    private String deptId;
    private String corpId;

    @Id
    @Column(name = "GP_ID")
    public String getGpId() {
        return gpId;
    }

    public void setGpId(String gpId) {
        this.gpId = gpId;
    }

    @Basic
    @Column(name = "GP_NO")
    public String getGpNo() {
        return gpNo;
    }

    public void setGpNo(String gpNo) {
        this.gpNo = gpNo;
    }

    @Basic
    @Column(name = "GP_NAME")
    public String getGpName() {
        return gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName;
    }

    @Basic
    @Column(name = "CREATER")
    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "DEPT_ID")
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Column(name = "CORP_ID")
    public String getCorpId() {
        return corpId;
    }
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GasPlan gasPlan = (GasPlan) o;

        if (gpId != null ? !gpId.equals(gasPlan.gpId) : gasPlan.gpId != null) return false;
        if (gpName != null ? !gpName.equals(gasPlan.gpName) : gasPlan.gpName != null) return false;
        if (creater != null ? !creater.equals(gasPlan.creater) : gasPlan.creater != null) return false;
        if (createDate != null ? !createDate.equals(gasPlan.createDate) : gasPlan.createDate != null) return false;
        if (remark != null ? !remark.equals(gasPlan.remark) : gasPlan.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gpId != null ? gpId.hashCode() : 0;
        result = 31 * result + (gpName != null ? gpName.hashCode() : 0);
        result = 31 * result + (creater != null ? creater.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
