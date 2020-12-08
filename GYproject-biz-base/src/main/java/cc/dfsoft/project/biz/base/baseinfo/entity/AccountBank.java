package cc.dfsoft.project.biz.base.baseinfo.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/1.
 */
@Entity
@Table(name = "ACCOUNT_BANK")
public class AccountBank {
    private String abId;
    private String bankNo;
    private String bankName;
    private String bankAdress;
    private Date bankDate;
    private String corpId;
    private String bankNumber;

    @Id
    @Column(name = "AB_ID")
    public String getAbId() {
        return abId;
    }

    public void setAbId(String abId) {
        this.abId = abId;
    }

    @Basic
    @Column(name = "BANK_NO")
    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    @Basic
    @Column(name = "BANK_NAME")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Basic
    @Column(name = "BANK_ADRESS")
    public String getBankAdress() {
        return bankAdress;
    }

    public void setBankAdress(String bankAdress) {
        this.bankAdress = bankAdress;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "BANK_DATE")
    public Date getBankDate() {
        return bankDate;
    }

    public void setBankDate(Date bankDate) {
        this.bankDate = bankDate;
    }

    @Basic
    @Column(name = "CORP_ID")
    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    @Basic
    @Column(name = "BANK_NUMBER")
    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }
}
