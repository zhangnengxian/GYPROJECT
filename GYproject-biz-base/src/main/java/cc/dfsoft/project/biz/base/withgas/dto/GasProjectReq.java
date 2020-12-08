package cc.dfsoft.project.biz.base.withgas.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by cui on 2017/8/8.
 */
public class GasProjectReq extends PageSortReq {

    private String gpId;
    private String projId;
    private String projNo;
    private String projAddr;
    private String projName;
    private String projScaleDes;
    private String projLtypeId;
    private String deptName;
    private String cuName;
    private String scNo;
    private String gasComLegalRepresent;
    private String preparer;
    private Timestamp preparDate;
    private String preparDept;
    private Timestamp planGasDate;
    private String pipeMaterial;
    private String pipeSize;
    private String pipeRating;
    private String gasContent;
    private String gasRemark;
    private String custName;
    private String jaClum;

    private String gasProjStatus;

    private String preparDateStart;
    private String preparDateEnd;
    private String isSignIgnite;		 //是否签订点火单 0 未完成 1 已完成
	private List<String> gasStatusList;	 //状态集合
	private String menuId;				 //菜单id
	private String isprint;					//是否打印标记     0 已打印,1未打印
    private String isUploadAccessory;     //是否上传竣工单确认单  1已上传  0未上传
    public String getPreparDateStart() {
        return preparDateStart;
    }

    public void setPreparDateStart(String preparDateStart) {
        this.preparDateStart = preparDateStart;
    }

    public String getPreparDateEnd() {
        return preparDateEnd;
    }

    public void setPreparDateEnd(String preparDateEnd) {
        this.preparDateEnd = preparDateEnd;
    }

    public String getGpId() {
        return gpId;
    }

    public void setGpId(String gpId) {
        this.gpId = gpId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

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

    public String getProjScaleDes() {
        return projScaleDes;
    }

    public void setProjScaleDes(String projScaleDes) {
        this.projScaleDes = projScaleDes;
    }

    public String getProjLtypeId() {
        return projLtypeId;
    }

    public void setProjLtypeId(String projLtypeId) {
        this.projLtypeId = projLtypeId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public String getGasComLegalRepresent() {
        return gasComLegalRepresent;
    }

    public void setGasComLegalRepresent(String gasComLegalRepresent) {
        this.gasComLegalRepresent = gasComLegalRepresent;
    }

    public String getPreparer() {
        return preparer;
    }

    public void setPreparer(String preparer) {
        this.preparer = preparer;
    }

    public Timestamp getPreparDate() {
        return preparDate;
    }

    public void setPreparDate(Timestamp preparDate) {
        this.preparDate = preparDate;
    }

    public String getPreparDept() {
        return preparDept;
    }

    public void setPreparDept(String preparDept) {
        this.preparDept = preparDept;
    }

    public Timestamp getPlanGasDate() {
        return planGasDate;
    }

    public void setPlanGasDate(Timestamp planGasDate) {
        this.planGasDate = planGasDate;
    }

    public String getPipeMaterial() {
        return pipeMaterial;
    }

    public void setPipeMaterial(String pipeMaterial) {
        this.pipeMaterial = pipeMaterial;
    }

    public String getPipeSize() {
        return pipeSize;
    }

    public void setPipeSize(String pipeSize) {
        this.pipeSize = pipeSize;
    }

    public String getPipeRating() {
        return pipeRating;
    }

    public void setPipeRating(String pipeRating) {
        this.pipeRating = pipeRating;
    }

    public String getGasContent() {
        return gasContent;
    }

    public void setGasContent(String gasContent) {
        this.gasContent = gasContent;
    }

    public String getGasRemark() {
        return gasRemark;
    }

    public void setGasRemark(String gasRemark) {
        this.gasRemark = gasRemark;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getJaClum() {
        return jaClum;
    }

    public void setJaClum(String jaClum) {
        this.jaClum = jaClum;
    }

    public String getGasProjStatus() {
        return gasProjStatus;
    }

    public void setGasProjStatus(String gasProjStatus) {
        this.gasProjStatus = gasProjStatus;
    }

	public String getIsSignIgnite() {
		return isSignIgnite;
	}

	public void setIsSignIgnite(String isSignIgnite) {
		this.isSignIgnite = isSignIgnite;
	}

	public List<String> getGasStatusList() {
		return gasStatusList;
	}

	public void setGasStatusList(List<String> gasStatusList) {
		this.gasStatusList = gasStatusList;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getIsprint() {
		return isprint;
	}

	public void setIsprint(String isprint) {
		this.isprint = isprint;
	}

    public String getIsUploadAccessory() {
        return isUploadAccessory;
    }

    public void setIsUploadAccessory(String isUploadAccessory) {
        this.isUploadAccessory = isUploadAccessory;
    }
}
