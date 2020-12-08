package cc.dfsoft.project.biz.base.inspection.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/2/7 0007.
 */
@Entity
@Table(name = "GUARANTEE_ITEMS")
public class GuaranteeItems {
    private String giId;	//主键ID
    private String giDes;	//保证项目描述
    private String tbType;	//分项工程类型


    @Id
    @Column(name = "GI_ID")
    public String getGiId() {
        return giId;
    }

    public void setGiId(String giId) {
        this.giId = giId;
    }

    
    @Column(name = "GI_DES")
    public String getGiDes() {
        return giDes;
    }

    public void setGiDes(String giDes) {
        this.giDes = giDes;
    }

    
    @Column(name = "TB_TYPE")
    public String getTbType() {
        return tbType;
    }

    public void setTbType(String tbType) {
        this.tbType = tbType;
    }

}
