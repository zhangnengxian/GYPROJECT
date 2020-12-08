package cc.dfsoft.project.biz.base.inspection.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 基本项目检查
 * Created by Administrator on 2017/2/7 0007.
 */
@Entity
@Table(name = "BASIC_PROJECT_CHECK_ITEM")
public class BasicProjectCheckItem {
    private String bpciId;				//主键id
    private String pcId;			  	//报验单id
    private String bpcId;				//基本项目检查内容id
    private String item;			  	//项目名称
    private String qualitySituation1;	//质量情况1
    private String qualitySituation2;	//质量情况2
    private String qualitySituation3;	//质量情况3
    private String qualitySituation4;	//质量情况4
    private String qualitySituation5;	//质量情况5
    private String qualitySituation6;	//质量情况6
    private String qualitySituation7;	//质量情况7
    private String qualitySituation8;	//质量情况8
    private String qualitySituation9;	//质量情况9
    private String qualitySituation10;	//质量情况10
    private String rank;				//等级
    private String itemType;			//类型1.管内穿线基本项目 2.管内穿线允许偏差项目3.抵押电器安装项目

    @Id
    @Column(name = "BPCI_ID")
    public String getBpciId() {
        return bpciId;
    }

    public void setBpciId(String bpciId) {
        this.bpciId = bpciId;
    }

    
    @Column(name = "PC_ID")
    public String getPcId() {
        return pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    
    @Column(name = "ITEM")
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    
    @Column(name = "QUALITY_SITUATION_1")
    public String getQualitySituation1() {
        return qualitySituation1;
    }

    public void setQualitySituation1(String qualitySituation1) {
        this.qualitySituation1 = qualitySituation1;
    }

    
    @Column(name = "QUALITY_SITUATION_2")
    public String getQualitySituation2() {
        return qualitySituation2;
    }

    public void setQualitySituation2(String qualitySituation2) {
        this.qualitySituation2 = qualitySituation2;
    }

    
    @Column(name = "QUALITY_SITUATION_3")
    public String getQualitySituation3() {
        return qualitySituation3;
    }

    public void setQualitySituation3(String qualitySituation3) {
        this.qualitySituation3 = qualitySituation3;
    }

    
    @Column(name = "QUALITY_SITUATION_4")
    public String getQualitySituation4() {
        return qualitySituation4;
    }

    public void setQualitySituation4(String qualitySituation4) {
        this.qualitySituation4 = qualitySituation4;
    }

    
    @Column(name = "QUALITY_SITUATION_5")
    public String getQualitySituation5() {
        return qualitySituation5;
    }

    public void setQualitySituation5(String qualitySituation5) {
        this.qualitySituation5 = qualitySituation5;
    }

    
    @Column(name = "QUALITY_SITUATION_6")
    public String getQualitySituation6() {
        return qualitySituation6;
    }

    public void setQualitySituation6(String qualitySituation6) {
        this.qualitySituation6 = qualitySituation6;
    }

    
    @Column(name = "QUALITY_SITUATION_7")
    public String getQualitySituation7() {
        return qualitySituation7;
    }

    public void setQualitySituation7(String qualitySituation7) {
        this.qualitySituation7 = qualitySituation7;
    }

    
    @Column(name = "QUALITY_SITUATION_8")
    public String getQualitySituation8() {
        return qualitySituation8;
    }

    public void setQualitySituation8(String qualitySituation8) {
        this.qualitySituation8 = qualitySituation8;
    }

    
    @Column(name = "QUALITY_SITUATION_9")
    public String getQualitySituation9() {
        return qualitySituation9;
    }

    public void setQualitySituation9(String qualitySituation9) {
        this.qualitySituation9 = qualitySituation9;
    }

    
    @Column(name = "QUALITY_SITUATION_10")
    public String getQualitySituation10() {
        return qualitySituation10;
    }

    public void setQualitySituation10(String qualitySituation10) {
        this.qualitySituation10 = qualitySituation10;
    }

    
    @Column(name = "RANK")
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    
    @Column(name = "ITEM_TYPE")
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    
    @Column(name = "BPC_ID")
	public String getBpcId() {
		return bpcId;
	}

	public void setBpcId(String bpcId) {
		this.bpcId = bpcId;
	}

}
