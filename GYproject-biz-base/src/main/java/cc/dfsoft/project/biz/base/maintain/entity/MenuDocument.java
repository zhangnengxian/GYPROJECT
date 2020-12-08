package cc.dfsoft.project.biz.base.maintain.entity;

import javax.persistence.*;
import java.util.Date;

/**
* @Description: 功能描述
* @author zhangnx
* @date 2019/9/1 2:34
*/
@Entity
@Table(name = "MENU_DOCUMENT")
public class MenuDocument {
    private String menuId;              //菜单ID
    private String document;           //文档


    @Id
    @Column(name = "MENU_ID", unique = true)
    public String getMenuId() {
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Column(name = "DOCUMENT", unique = true)
    public String getDocument() {
        return document;
    }
    public void setDocument(String document) {
        this.document = document;
    }
}
