package cc.dfsoft.project.biz.base.inspection.entity;

import javax.persistence.*;

/**
 * 基本项目检查内容
 * Created by Administrator on 2017/2/7 0007.
 */
@Entity
@Table(name = "BASIC_PROJECT_CONTENT")
public class BasicProjectContent {
    private String bpcId;		//主键id
    private String item;		//项目
    private String itemDes;		//项目描述
    private String itemType;	//类型1.管内穿线基本项目 2.管内穿线允许偏差项目3.抵押电器安装项目

    @Id
    @Column(name = "BPC_ID")
    public String getBpcId() {
        return bpcId;
    }

    public void setBpcId(String bpcId) {
        this.bpcId = bpcId;
    }

   
    @Column(name = "ITEM")
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

   
    @Column(name = "ITEM_DES")
    public String getItemDes() {
        return itemDes;
    }

    public void setItemDes(String itemDes) {
        this.itemDes = itemDes;
    }

   
    @Column(name = "ITEM_TYPE")
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
