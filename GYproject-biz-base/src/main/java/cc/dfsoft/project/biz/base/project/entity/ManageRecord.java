package cc.dfsoft.project.biz.base.project.entity;

import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ManageRecord entity
 */
@Entity
@Table(name = "MANAGE_RECORD")
public class ManageRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 200284777065819672L;
	
	// Fields
	
	private String mrId;				//主键ID
	private String businessOrderId;		//业务单ID
	private String docTypeId;			//单据类型
	private String stepId;				//步骤ID
	private String projId;				//工程ID
	private String projNo;				//工程编号
	private String mrResult;			//审核结果
	private String mrAopinion;			//审核意见
	private String mrDeptId;			//审核部门
	private String mrCsr;				//审核人ID
	private Date mrTime;				//审核时间
	private String mrAuditLevel;		//本次审核级别
	private String remark;				//备注
	private String flag; 				//作废标记---用于控制审核屏样式
	
	private String stepDes;				//步骤描述---------用于列表展示
	private String docTypeDes;			//单据类型---------用于列表展示
	private String mrResultDes;			//审核结果---------用于列表展示
	private String signs;			    //签字
	private List<Signature> sign;
	private String suPrincipal;
	private String pressureType;		//压力类型

	private String mrCsrName;				//审核人---------

	
	private String planGasEndDate;		//计划开通时间
	private String planGasDate;          //计划开通结束时间
	
	private String fallbackStepId;		 //回退的步骤id
	
	private String menuId;				//菜单id

	private String pcId;

	private String longitude;// 经度

	private String latitude; //纬度



	@Transient
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Transient
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Transient
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	/**  default constructor*/
	public ManageRecord() {
	}

	// Property accessors
	@Id
	@Column(name = "MR_ID", unique = true)
	public String getMrId() {
		return this.mrId;
	}

	public void setMrId(String mrId) {
		this.mrId = mrId;
	}

	@Column(name = "BUSINESS_ORDER_ID")
	public String getBusinessOrderId() {
		return this.businessOrderId;
	}

	public void setBusinessOrderId(String businessOrderId) {
		this.businessOrderId = businessOrderId;
	}

	@Column(name = "DOC_TYPE_ID")
	public String getDocTypeId() {
		return this.docTypeId;
	}

	public void setDocTypeId(String docTypeId) {
		this.docTypeId = docTypeId;
	}

	@Column(name = "STEP_ID")
	public String getStepId() {
		return this.stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return this.projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "MR_RESULT")
	public String getMrResult() {
		return this.mrResult;
	}

	public void setMrResult(String mrResult) {
		this.mrResult = mrResult;
	}

	@Column(name = "MR_AOPINION")
	public String getMrAopinion() {
		return this.mrAopinion;
	}

	public void setMrAopinion(String mrAopinion) {
		this.mrAopinion = mrAopinion;
	}

	@Column(name = "MR_DEPT_ID")
	public String getMrDeptId() {
		return this.mrDeptId;
	}

	public void setMrDeptId(String mrDeptId) {
		this.mrDeptId = mrDeptId;
	}

	@Column(name = "MR_CSR")
	public String getMrCsr() {
		return this.mrCsr;
	}

	public void setMrCsr(String mrCsr) {
		this.mrCsr = mrCsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MR_TIME")
	public Date getMrTime() {
		return this.mrTime;
	}

	public void setMrTime(Date mrTime) {
		this.mrTime = mrTime;
	}

	@Column(name = "MR_AUDIT_LEVEL")
	public String getMrAuditLevel() {
		return this.mrAuditLevel;
	}

	public void setMrAuditLevel(String mrAuditLevel) {
		this.mrAuditLevel = mrAuditLevel;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "FLAG")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Transient
	public String getStepDes() {
		String stepName = StepEnum.getNameByCode(this.stepId);
		if (StringUtils.isBlank(stepName)){
			stepName = StepOutWorkflowEnum.getNameByCode(this.stepId);
		}
		return stepName;
	}

	public void setStepDes(String stepDes) {
		this.stepDes = stepDes;
	}
	
	@Transient
	public String getDocTypeDes() {
		return "";
	}

	public void setDocTypeDes(String docTypeDes) {
		this.docTypeDes = docTypeDes;
	}
	@Transient
	public String getMrResultDes() {
		for(MrResultEnum e: MrResultEnum.values()) {
	   		if(e.getValue().equals(this.mrResult)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setMrResultDes(String mrResultDes) {
		this.mrResultDes = mrResultDes;
	}
   
    @Transient
	public List<Signature> getSign() {
		return sign;
	}
    public void setSign(List<Signature> sign) {
		this.sign = sign;
	}
    @Transient
	public String getSigns() {
		return signs;
	}
	public void setSigns(String signs) {
		this.signs = signs;
	}
	@Transient
	public String getSuPrincipal() {
		return suPrincipal;
	}

	public void setSuPrincipal(String suPrincipal) {
		this.suPrincipal = suPrincipal;
	}

	@Transient
	public String getPressureType() {
		return pressureType;
	}

	public void setPressureType(String pressureType) {
		this.pressureType = pressureType;
	}

	@Transient
	public String getMrCsrName() {
		return mrCsrName;
	}

	public void setMrCsrName(String mrCsrName) {
		this.mrCsrName = mrCsrName;
	}
	
	@Transient
	public String getPlanGasEndDate() {
		return planGasEndDate;
	}

	public void setPlanGasEndDate(String planGasEndDate) {
		this.planGasEndDate = planGasEndDate;
	}
	
	@Transient
	public String getPlanGasDate() {
		return planGasDate;
	}

	public void setPlanGasDate(String planGasDate) {
		this.planGasDate = planGasDate;
	}
	
	@Transient
	public String getFallbackStepId() {
		return fallbackStepId;
	}

	public void setFallbackStepId(String fallbackStepId) {
		this.fallbackStepId = fallbackStepId;
	}
	
	@Transient
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	
	
}