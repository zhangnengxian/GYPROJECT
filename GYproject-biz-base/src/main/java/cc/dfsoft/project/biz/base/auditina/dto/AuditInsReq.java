package cc.dfsoft.project.biz.base.auditina.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class AuditInsReq extends PageSortReq {

    private String projId;

    private String projNo;

    private String process;//工序

    private Integer timeLimit;	         //时间限制

    private String stepId;

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }
}
