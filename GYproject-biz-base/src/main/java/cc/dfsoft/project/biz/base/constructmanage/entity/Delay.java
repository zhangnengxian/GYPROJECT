package cc.dfsoft.project.biz.base.constructmanage.entity;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_DELAY_APPLY")
public class Delay  implements Serializable {
    private String adId;
    private String projId;
    private String progress;
    private String why;
    private String measures;
    private String numberday;
    private Date addtime;
    private String applicant;
    private Clob applicantsig;
    private List<Signature> sign;		//签字

    private String planStatus;
    private String planEnd;
    private String statusDate;

    private String state;

    @Column(name = "STATE")
    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
    }

    @Transient
    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }
    @Transient
    public String getPlanEnd() {
        return planEnd;
    }

    public void setPlanEnd(String planEnd) {
        this.planEnd = planEnd;
    }
    @Transient
    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

    @Transient
    public List<Signature> getSign() {
        return sign;
    }

    public void setSign(List<Signature> sign) {
        this.sign = sign;
    }

    @Id
    @Column(name = "AD_ID")
    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }
    @Column(name = "PROJ_ID")
    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }
    @Column(name = "PROGRESS")
    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
    @Column(name = "WHY")
    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }
    @Column(name = "MEASURES")
    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }
    @Column(name = "NUMBERDAY")
    public String getNumberday() {
        return numberday;
    }

    public void setNumberday(String numberday) {
        this.numberday = numberday;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ADDTIME")
    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
    @Column(name = "APPLICANT")
    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }
    @Column(name = "APPLICANTSIG")
    public String getApplicantsig() {
        return ClobUtil.ClobToString(applicantsig);
    }

    public void setApplicantsig(String applicantsig)  throws SerialException, SQLException  {
        this.applicantsig = ClobUtil.stringToClob(applicantsig);
    }
}
