package cc.dfsoft.project.biz.base.messagesync.hongju.pojo;

import java.util.Date;

/**
 *@ Description: 开工报告同步信息
 *@ author: zhangnx
 *@ Date: 2019/11/22 16:51
 *@ Version:1.0
 * */
public class WorkReportInfo {
    private String wrId;                //开工报告ID
    private String projNo;              //工程编号
    private String plannedStartDate;      //开工日期
    private String plannedEndDate;      //计划竣工日期
    private String patchCode;           //片区号
    private String courtyardNo;		    // 庭院管号
    private String timeLimit;		    //  工期
    private String trunkInstall;	    // 干线安装
    private String courtyardInstall;    //庭院安装
    private String indoorInstall;   	// 户内安装
    private String equipmentInstall;    //设备安装
    private String projectStartCase;    //开工条件具备情况
    private String remark;			    //备注
    private String suDate;			    //创建日期

    public String getWrId() {
        return wrId;
    }

    public void setWrId(String wrId) {
        this.wrId = wrId;
    }

    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    public String getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(String plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public String getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(String plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public String getPatchCode() {
        return patchCode;
    }

    public void setPatchCode(String patchCode) {
        this.patchCode = patchCode;
    }

    public String getCourtyardNo() {
        return courtyardNo;
    }

    public void setCourtyardNo(String courtyardNo) {
        this.courtyardNo = courtyardNo;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getTrunkInstall() {
        return trunkInstall;
    }

    public void setTrunkInstall(String trunkInstall) {
        this.trunkInstall = trunkInstall;
    }

    public String getCourtyardInstall() {
        return courtyardInstall;
    }

    public void setCourtyardInstall(String courtyardInstall) {
        this.courtyardInstall = courtyardInstall;
    }

    public String getIndoorInstall() {
        return indoorInstall;
    }

    public void setIndoorInstall(String indoorInstall) {
        this.indoorInstall = indoorInstall;
    }

    public String getEquipmentInstall() {
        return equipmentInstall;
    }

    public void setEquipmentInstall(String equipmentInstall) {
        this.equipmentInstall = equipmentInstall;
    }

    public String getProjectStartCase() {
        return projectStartCase;
    }

    public void setProjectStartCase(String projectStartCase) {
        this.projectStartCase = projectStartCase;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSuDate() {
        return suDate;
    }

    public void setSuDate(String suDate) {
        this.suDate = suDate;
    }

    @Override
    public String toString() {
        return "WorkReportInfo{" +
                "projNo='" + projNo + '\'' +
                ", plannedStartDate=" + plannedStartDate +
                ", plannedEndDate='" + plannedEndDate + '\'' +
                ", patchCode='" + patchCode + '\'' +
                ", courtyardNo='" + courtyardNo + '\'' +
                ", timeLimit='" + timeLimit + '\'' +
                ", trunkInstall='" + trunkInstall + '\'' +
                ", courtyardInstall='" + courtyardInstall + '\'' +
                ", indoorInstall='" + indoorInstall + '\'' +
                ", equipmentInstall='" + equipmentInstall + '\'' +
                ", projectStartCase='" + projectStartCase + '\'' +
                ", remark='" + remark + '\'' +
                ", suDate=" + suDate +
                '}';
    }
}
