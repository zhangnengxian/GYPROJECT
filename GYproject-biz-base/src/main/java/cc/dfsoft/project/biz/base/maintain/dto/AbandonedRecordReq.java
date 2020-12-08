package cc.dfsoft.project.biz.base.maintain.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

import java.util.Date;

/**
* @Description: 废弃数据记录
* @author zhangnx
* @date 2019/8/20 14:21
*@Version:1.0
*/

public class AbandonedRecordReq extends PageSortReq {
    private String abId;                 //删除记录ID
    private String businessId;           //业务ID(关联ID)
    private String projId;               //工程ID
    private String stepId;               //步骤ID
    private String operator;             //作废人
    private Date   operatingTime;        //作废时间
    private String abandonedReason;     //作废原因
    private String abandonedData;       //删除的数据
    private String menuId;               //菜单ID


    public String getAbId() {
        return abId;
    }

    public void setAbId(String abId) {
        this.abId = abId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(Date operatingTime) {
        this.operatingTime = operatingTime;
    }

    public String getAbandonedReason() {
        return abandonedReason;
    }

    public void setAbandonedReason(String abandonedReason) {
        this.abandonedReason = abandonedReason;
    }

    public String getAbandonedData() {
        return abandonedData;
    }

    public void setAbandonedData(String abandonedData) {
        this.abandonedData = abandonedData;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
