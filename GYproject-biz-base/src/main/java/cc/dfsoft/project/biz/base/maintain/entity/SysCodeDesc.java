package cc.dfsoft.project.biz.base.maintain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/3/25 22:07
 * @Version:1.0
 */

@Entity
@Table(name = "SysCodeDesc")
public class SysCodeDesc {
    private String id;
   private String tableName;
   private String type;
   private String code;
   private String desc;
   private String configField;
   private String remark;
    private Integer sort;

    @Id
    @Column(name = "id", unique = true)
    public String getId() {
        return id;
    }
    @Column(name = "tableName")
    public String getTableName() {
        return tableName;
    }
    @Column(name = "type")
    public String getType() {
        return type;
    }
    @Column(name = "code")
    public String getCode() {
        return code;
    }
    @Column(name = "desc")
    public String getDesc() {
        return desc;
    }
    @Column(name = "configField")
    public String getConfigField() {
        return configField;
    }
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }
    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setConfigField(String configField) {
        this.configField = configField;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
