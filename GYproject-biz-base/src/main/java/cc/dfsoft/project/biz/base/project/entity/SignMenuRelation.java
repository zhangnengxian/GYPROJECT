package cc.dfsoft.project.biz.base.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SIGN_MENU_RELATION")
public class SignMenuRelation implements java.io.Serializable {

    private String signTypeId;  //签字单据类型 如交底
    private String menuId;      //菜单id
    private String menuName;    //菜单名称
    @Id
    @Column(name = "SIGN_TYPE_ID")
    public String getSignTypeId() {
        return signTypeId;
    }

    public void setSignTypeId(String signTypeId) {
        this.signTypeId = signTypeId;
    }
    @Column(name = "MENU_ID")
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    @Column(name = "MENU_NAME")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
