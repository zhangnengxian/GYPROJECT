package cc.dfsoft.project.biz.base.constructmanage.entity;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/27.
 */
@Entity
@Table(name = "project_contacts")
public class ProjectContacts {
    private String pcsId;
    private String projId;
    private String projNo;
    private String projName;
    private String projAddr;
    private String duName;
    private String contactContent;
    private String disposeOpinion;
    private Date contactDate;
    private Clob contacter;//联系人
    private Clob disposer;//处理人
    private Clob builder;//现场代表
    private Clob suJgj;//现场监理
    private Clob cuPm;//项目经理
    private Clob construction;//施工员

    private List<Signature> sign;		//签字

    private Integer version;			//版本控制
    @Id
    @Column(name = "PCS_ID")
    public String getPcsId() {
        return pcsId;
    }

    public void setPcsId(String pcsId) {
        this.pcsId = pcsId;
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
    @Column(name = "PROJ_ADDR")
    public String getProjAddr() {
        return projAddr;
    }

    public void setProjAddr(String projAddr) {
        this.projAddr = projAddr;
    }

    @Basic
    @Column(name = "DU_NAME")
    public String getDuName() {
        return duName;
    }

    public void setDuName(String duName) {
        this.duName = duName;
    }

    @Basic
    @Column(name = "CONTACT_CONTENT")
    public String getContactContent() {
        return contactContent;
    }

    public void setContactContent(String contactContent) {
        this.contactContent = contactContent;
    }

    @Basic
    @Column(name = "DISPOSE_OPINION")
    public String getDisposeOpinion() {
        return disposeOpinion;
    }

    public void setDisposeOpinion(String disposeOpinion) {
        this.disposeOpinion = disposeOpinion;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "CONTACT_DATE")
    public Date getContactDate() {
        return contactDate;
    }

    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }

    @Basic
    @Column(name = "CONTACTER")
    public String getContacter() {
        return ClobUtil.ClobToString(this.contacter);
    }

    public void setContacter(String contacter) throws SQLException {
        this.contacter = ClobUtil.stringToClob(contacter);
    }

    @Basic
    @Column(name = "DISPOSER")
    public String getDisposer() {
        return ClobUtil.ClobToString(this.disposer);
    }

    public void setDisposer(String disposer) throws SQLException {
        this.disposer = ClobUtil.stringToClob(disposer);
    }

    @Basic
    @Column(name = "BUILDER")
    public String getBuilder() {
        return ClobUtil.ClobToString(this.builder);
    }

    public void setBuilder(String builder) throws SQLException {
        this.builder = ClobUtil.stringToClob(builder);
    }

    @Basic
    @Column(name = "SU_JGJ")
    public String getSuJgj() {
        return ClobUtil.ClobToString(this.suJgj);
    }

    public void setSuJgj(String suJgj) throws SQLException {
        this.suJgj = ClobUtil.stringToClob(suJgj);
    }

    @Basic
    @Column(name = "CU_PM")
    public String getCuPm() {
        return ClobUtil.ClobToString(this.cuPm);
    }

    public void setCuPm(String cuPm) throws SQLException {
        this.cuPm = ClobUtil.stringToClob(cuPm);
    }

    @Basic
    @Column(name = "CONSTRUCTION")
    public String getConstruction() {
        return ClobUtil.ClobToString(this.construction);
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
