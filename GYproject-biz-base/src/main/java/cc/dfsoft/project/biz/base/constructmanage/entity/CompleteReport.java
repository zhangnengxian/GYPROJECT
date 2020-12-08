package cc.dfsoft.project.biz.base.constructmanage.entity;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */
@Entity
@Table(name = "complete_report")
public class CompleteReport {
    private String crId;
    private String projId;
    private String projNo;
    private String projName;
    private String projContent;
    private String remark;
    private Clob cuPm;
    private String constructionUnit;
    private String suName;
    private Clob suCse;
    private String crFlag;
    private String projAddr;
    private BigDecimal scAmount;//工程造价
    private Date scPlannedStartDate;//计划开工日期
    private String scPlannedEndDate;//计划竣工日期
    private Date realStartDate;//实际开工日期
    private Date realEndDate;//实际竣工日期
    private String scPlannedTotalDays;//计划工作天数
    private String sealTotalDays;//实际工作天数
    private String corpName;
    private Clob projectLeader;//工程负责人
    private Clob builder;//现场代表
    private Clob suJgj;
    private Clob qualitativeCheckMember;//质检员
    private Clob safetyOfficer;
    private Clob construction;

    private List<Signature> sign;		//签字

    private Integer version;			//版本控制

    private String crAttach;            //附件
    private String suView;              //审查意见
    private Date suCseDate;             //总监签字日期
    private Date cuPmDate;              //项目经理日期

    @Column(name = "CR_ATTACH")
    public String getCrAttach() {
        return crAttach;
    }

    public void setCrAttach(String crAttach) {
        this.crAttach = crAttach;
    }

    @Column(name = "SU_VIEW")
    public String getSuView() {
        return suView;
    }

    public void setSuView(String suView) {
        this.suView = suView;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "SU_CSE_DATE")
    public Date getSuCseDate() {
        return suCseDate;
    }

    public void setSuCseDate(Date suCseDate) {
        this.suCseDate = suCseDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CU_PM_DATE")
    public Date getCuPmDate() {
        return cuPmDate;
    }

    public void setCuPmDate(Date cuPmDate) {
        this.cuPmDate = cuPmDate;
    }

    @Id
    @Column(name = "CR_ID")
    public String getCrId() {
        return crId;
    }

    public void setCrId(String crId) {
        this.crId = crId;
    }

    @Basic
    @Column(name = "PROJ_ID")
    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    @Basic
    @Column(name = "PROJ_NO")
    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    @Basic
    @Column(name = "PROJ_NAME")
    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    @Basic
    @Column(name = "PROJ_CONTENT")
    public String getProjContent() {
        return projContent;
    }

    public void setProjContent(String projContent) {
        this.projContent = projContent;
    }

    @Basic
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name="CU_PM")
    public String getCuPm() {
        return ClobUtil.ClobToString(this.cuPm);
    }

    public void setCuPm(String cuPm) throws SerialException, SQLException {
        this.cuPm = ClobUtil.stringToClob(cuPm);
    }

    @Basic
    @Column(name = "CONSTRUCTION_UNIT")
    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    @Basic
    @Column(name = "SU_NAME")
    public String getSuName() {
        return suName;
    }

    public void setSuName(String suName) {
        this.suName = suName;
    }

    @Basic
    @Column(name = "SU_CSE")
    public String getSuCse() {
        return ClobUtil.ClobToString(this.suCse);
    }

    public void setSuCse(String suCse) throws SQLException {
        this.suCse = ClobUtil.stringToClob(suCse);
    }

    @Basic
    @Column(name = "CR_FLAG")
    public String getCrFlag() {
        return crFlag;
    }

    public void setCrFlag(String crFlag) {
        this.crFlag = crFlag;
    }

    @Basic
    @Column(name = "PROJ_ADDR")
    public String getProjAddr() {
        return projAddr;
    }

    public void setProjAddr(String projAddr) {
        this.projAddr = projAddr;
    }

    @Basic
    @Column(name = "SC_AMOUNT")
    public BigDecimal getScAmount() {
        return scAmount;
    }

    public void setScAmount(BigDecimal scAmount) {
        this.scAmount = scAmount;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "SC_PLANNED_START_DATE")
    public Date getScPlannedStartDate() {
        return scPlannedStartDate;
    }

    public void setScPlannedStartDate(Date scPlannedStartDate) {
        this.scPlannedStartDate = scPlannedStartDate;
    }

    @Basic
    @Column(name = "SC_PLANNED_END_DATE")
    public String getScPlannedEndDate() {
        return scPlannedEndDate;
    }

    public void setScPlannedEndDate(String scPlannedEndDate) {
        this.scPlannedEndDate = scPlannedEndDate;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "REAl_START_DATE")
    public Date getRealStartDate() {
        return realStartDate;
    }

    public void setRealStartDate(Date realStartDate) {
        this.realStartDate = realStartDate;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "REAl_END_DATE")
    public Date getRealEndDate() {
        return realEndDate;
    }

    public void setRealEndDate(Date realEndDate) {
        this.realEndDate = realEndDate;
    }

    @Basic
    @Column(name = "SC_PLANNED_TOTAL_DAYS")
    public String getScPlannedTotalDays() {
        return scPlannedTotalDays;
    }

    public void setScPlannedTotalDays(String scPlannedTotalDays) {
        this.scPlannedTotalDays = scPlannedTotalDays;
    }

    @Basic
    @Column(name = "SEAL_TOTAL_DAYS")
    public String getSealTotalDays() {
        return sealTotalDays;
    }

    public void setSealTotalDays(String sealTotalDays) {
        this.sealTotalDays = sealTotalDays;
    }

    @Basic
    @Column(name = "CORP_NAME")
    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    @Basic
    @Column(name = "PROJECT_LEADER")
    public String getProjectLeader() {
        return ClobUtil.ClobToString(this.projectLeader);
    }

    public void setProjectLeader(String projectLeader) throws SQLException {
        this.projectLeader = ClobUtil.stringToClob(projectLeader);
    }

    @Basic
    @Column(name = "BUILDER")
    public String getBuilder() {
        return ClobUtil.ClobToString(this.builder);
    }

    public void setBuilder(String builder) throws SQLException {
        this.builder = ClobUtil.stringToClob(builder);
    }

    @Column(name = "SU_JGJ")
    public String getSuJgj() {
        return ClobUtil.ClobToString(this.suJgj);
    }

    public void setSuJgj(String suJgj) throws SerialException, SQLException {
        this.suJgj = ClobUtil.stringToClob(suJgj);
    }

    @Basic
    @Column(name = "QUALITATIVE_CHECK_MEMBER")
    public String getQualitativeCheckMember() {
        return ClobUtil.ClobToString(qualitativeCheckMember);
    }

    public void setQualitativeCheckMember(String qualitativeCheckMember) throws SQLException {
        this.qualitativeCheckMember = ClobUtil.stringToClob(qualitativeCheckMember);
    }

    @Basic
    @Column(name = "SAFETY_OFFICER")
    public String getSafetyOfficer() {
        return ClobUtil.ClobToString(safetyOfficer);
    }

    public void setSafetyOfficer(String safetyOfficer) throws SQLException {
        this.safetyOfficer = ClobUtil.stringToClob(safetyOfficer);
    }

    @Basic
    @Column(name = "CONSTRUCTION")
    public String getConstruction() {
        return ClobUtil.ClobToString(construction);
    }

    public void setConstruction(String construction) throws SQLException {
        this.construction = ClobUtil.stringToClob(construction);
    }

    @Transient
    public List<Signature> getSign() {
        return sign;
    }

    public void setSign(List<Signature> sign) {
        this.sign = sign;
    }

    @Version
    @Column(name="VERSION")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
