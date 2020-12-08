package cc.dfsoft.project.biz.base.withgas.dto;

import cc.dfsoft.project.biz.base.withgas.entity.GasPlan;
import cc.dfsoft.project.biz.base.withgas.entity.GasProject;
import cc.dfsoft.uexpress.common.dto.PageSortReq;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by cui on 2017/8/8.
 */
public class GasPlanReq extends PageSortReq {

    private String gpId;
    private String gpNo;
    private String gpName;
    private String creater;
    private Timestamp createDate;

    private GasPlan gasPlan;                 //----------
    private List<GasProject> gasProjects;    //----------

    public GasPlan getGasPlan() {
        return gasPlan;
    }

    public void setGasPlan(GasPlan gasPlan) {
        this.gasPlan = gasPlan;
    }

    public List<GasProject> getGasProjects() {
        return gasProjects;
    }

    public void setGasProjects(List<GasProject> gasProjects) {
        this.gasProjects = gasProjects;
    }

    public String getGpId() {
        return gpId;
    }

    public void setGpId(String gpId) {
        this.gpId = gpId;
    }

    public String getGpNo() {
        return gpNo;
    }

    public void setGpNo(String gpNo) {
        this.gpNo = gpNo;
    }

    public String getGpName() {
        return gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
