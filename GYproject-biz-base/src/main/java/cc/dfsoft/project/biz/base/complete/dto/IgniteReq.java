package cc.dfsoft.project.biz.base.complete.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * Created by cui on 2017/8/8.
 */
public class IgniteReq extends PageSortReq {

    private String projNo;
    private String projAddr;
    private String projName;
    private String projectType;
    private String cuName;
    private String scNo;
    private String managerDateStart;
    private String managerDateEnd;
    private String isPrint;		//是否打印标记     0 已打印,1未打印

    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    public String getProjAddr() {
        return projAddr;
    }

    public void setProjAddr(String projAddr) {
        this.projAddr = projAddr;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getCuName() {
        return cuName;
    }

    public void setCuName(String cuName) {
        this.cuName = cuName;
    }

    public String getScNo() {
        return scNo;
    }

    public void setScNo(String scNo) {
        this.scNo = scNo;
    }

    public String getManagerDateStart() {
        return managerDateStart;
    }

    public void setManagerDateStart(String managerDateStart) {
        this.managerDateStart = managerDateStart;
    }

    public String getManagerDateEnd() {
        return managerDateEnd;
    }

    public void setManagerDateEnd(String managerDateEnd) {
        this.managerDateEnd = managerDateEnd;
    }

    public String getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint;
    }
}
