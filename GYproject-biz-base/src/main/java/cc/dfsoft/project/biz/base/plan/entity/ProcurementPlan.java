package cc.dfsoft.project.biz.base.plan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.constructmanage.enums.MaterialPlanFeedBackEnum;
import cc.dfsoft.project.biz.base.plan.enums.ProjectOperateEnum;

/**
 * ProcurementPlan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PROCUREMENT_PLAN")
public class ProcurementPlan implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7416734560404555447L;
	private String procurPlanId;	//采购计划ID
	private String projId;			//工程ID
	private String cpId;			//计划ID
	private String projNo;			//工程编号
	private String projName;		//工程名称
	private String managementId;	//施工管理处ID
	private String managementOffice;//施工管理处
	private Date projStartDate;		//开工日期
	private Date createTime;		//创建日期
	private String createStaffId;	//创建人ID
	private String createStaffName;	//创建人名称
	private String status;			//创建时工程操作
	private String businessOrderId;	//业务单Id
	private String isExport;		//是否已导出
	private String statusDes;		//创建时工程操作---只用于页面表格显示
	private String projTypeDes;		//工程类型描述--------页面显示
	
	// Constructors

	/** default constructor */
	public ProcurementPlan() {
	}

	// Property accessors
	@Id
	@Column(name = "PROCUR_PLAN_ID", unique = true)
	public String getProcurPlanId() {
		return this.procurPlanId;
	}

	public void setProcurPlanId(String procurPlanId) {
		this.procurPlanId = procurPlanId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "CP_ID")
	public String getCpId() {
		return this.cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return this.projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return this.projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Column(name = "MANAGEMENT_ID")
	public String getManagementId() {
		return this.managementId;
	}

	public void setManagementId(String managementId) {
		this.managementId = managementId;
	}

	@Column(name = "MANAGEMENT_OFFICE")
	public String getManagementOffice() {
		return this.managementOffice;
	}

	public void setManagementOffice(String managementOffice) {
		this.managementOffice = managementOffice;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PROJ_START_DATE")
	public Date getProjStartDate() {
		return this.projStartDate;
	}

	public void setProjStartDate(Date projStartDate) {
		this.projStartDate = projStartDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_STAFF_ID")
	public String getCreateStaffId() {
		return this.createStaffId;
	}

	public void setCreateStaffId(String createStaffId) {
		this.createStaffId = createStaffId;
	}

	@Column(name = "CREATE_STAFF_NAME")
	public String getCreateStaffName() {
		return this.createStaffName;
	}

	public void setCreateStaffName(String createStaffName) {
		this.createStaffName = createStaffName;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "BUSINESS_ORDER_ID")
	public String getBusinessOrderId() {
		return businessOrderId;
	}

	public void setBusinessOrderId(String businessOrderId) {
		this.businessOrderId = businessOrderId;
	}
	@Column(name = "IS_EXPORT")
	public String getIsExport() {
		return isExport;
	}

	public void setIsExport(String isExport) {
		this.isExport = isExport;
	}

	@Transient
	public String getStatusDes() {
		for(ProjectOperateEnum e: ProjectOperateEnum.values()) {
	   		if(e.getValue().equals(this.status)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setStatusDes(String statusDes) {
		this.statusDes = statusDes;
	}

	@Transient
	public String getProjTypeDes() {
		return projTypeDes;
	}

	public void setProjTypeDes(String projTypeDes) {
		this.projTypeDes = projTypeDes;
	}
}