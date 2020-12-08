package cc.dfsoft.project.biz.base.maintain.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/3/25 22:23
 * @Version:1.0
 */
public class SysCodeDescReq extends PageSortReq {
    private String tableName;
    private String type;
    private String code;
    private String desc;
    private String configField;
    private String remark;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getConfigField() {
        return configField;
    }

    public void setConfigField(String configField) {
        this.configField = configField;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
