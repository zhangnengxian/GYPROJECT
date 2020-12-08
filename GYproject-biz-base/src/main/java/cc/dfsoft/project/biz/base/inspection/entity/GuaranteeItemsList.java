package cc.dfsoft.project.biz.base.inspection.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 保证项目
 * Created by Administrator on 2017/2/7 0007.
 */
@Entity
@Table(name = "GUARANTEE_ITEMS_LIST")
public class GuaranteeItemsList {
    private String gilId; 				//主键id
    private String giId;				//保证项目ID
    private String giDes;				//保证项目描述
    private String pcId;				//报验单ID
    private String qualityCondition;	//质量情况
    private String tbType;	//分项工程类型

    @Id
    @Column(name = "GIL_ID")
    public String getGilId() {
        return gilId;
    }

    public void setGilId(String gilId) {
        this.gilId = gilId;
    }

    
    @Column(name = "GI_ID")
    public String getGiId() {
        return giId;
    }

    public void setGiId(String giId) {
        this.giId = giId;
    }

    
    @Column(name = "PC_ID")
    public String getPcId() {
        return pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    
    @Column(name = "QUALITY_CONDITION")
    public String getQualityCondition() {
        return qualityCondition;
    }

    public void setQualityCondition(String qualityCondition) {
        this.qualityCondition = qualityCondition;
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
