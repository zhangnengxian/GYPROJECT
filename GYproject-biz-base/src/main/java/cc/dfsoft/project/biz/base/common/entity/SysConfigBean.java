package cc.dfsoft.project.biz.base.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_SYS_CONFIG")
public class SysConfigBean {
	 private String keyId;
	    private String projTypeId;
	    private String projTypeName;
	    private String corpId;
	    private String corpName;
	    private String menuId;
	    private String menuName;
	    private String cnname;
	    private String cnvalue;
	    @Id
	    @Column(name = "KEY_ID", unique = true)
	    public String getKeyId() {
	        return keyId;
	    }

	    public void setKeyId(String keyId) {
	        this.keyId = keyId;
	    }
	    @Column(name = "PROJ_TYPE_ID")
	    public String getProjTypeId() {
	        return projTypeId;
	    }

	    public void setProjTypeId(String projTypeId) {
	        this.projTypeId = projTypeId;
	    }
	    @Column(name = "PROJ_TYPE_NAME")
	    public String getProjTypeName() {
	        return projTypeName;
	    }

	    public void setProjTypeName(String projTypeName) {
	        this.projTypeName = projTypeName;
	    }
	    @Column(name = "CORP_ID")
	    public String getCorpId() {
	        return corpId;
	    }

	    public void setCorpId(String corpId) {
	        this.corpId = corpId;
	    }
	    @Column(name = "CORP_NAME")
	    public String getCorpName() {
	        return corpName;
	    }

	    public void setCorpName(String corpName) {
	        this.corpName = corpName;
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
	    @Column(name = "CNNAME")
	    public String getCnname() {
	        return cnname;
	    }

	    public void setCnname(String cnname) {
	        this.cnname = cnname;
	    }
	    @Column(name = "CNVALUE")
	    public String getCnvalue() {
	        return cnvalue;
	    }

	    public void setCnvalue(String cnvalue) {
	        this.cnvalue = cnvalue;
	    }
}
