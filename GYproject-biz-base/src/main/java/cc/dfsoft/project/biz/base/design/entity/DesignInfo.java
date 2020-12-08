package cc.dfsoft.project.biz.base.design.entity;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DesignInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DESIGN_INFO")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DesignInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6114213754173001210L;
	// Fields

	private String diId;					//设计ID
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private String projName;				//工程名称
	private String projAddr;				//工程地点
	private String projScaleDes;			//工程规模
	private String duId;					//设计院ID
	private String duName;					//设计院名称
	private String designer;				//设计人
	private String designerId;				//设计人id
	private Date duCompleteDate;			//设计完成日期
	private Date ocoDate;					//委托日期
	
	private String corpId;					//分公司id
	private String tenantId;				//租户id
	private String orgId;					//组织id
	private String designNo;				//协议号
	private String designDrawingNo;			//设计图号
	private BigDecimal designDrawingCopies; //图纸份数
	private BigDecimal designDrawingSheets; //每份张数
	private String dataAcqRemark;			//资料收集备注
	private String desginRemark;			//设计出图备注
	private String projectTypeDes;			//工程类型描述--只读
	private String contributionModeDes;		//出资方式描述--只读
	private String deptName;	 			//部门名称--只读
	private String corpName;				//燃气公司--只读
	
	private String custName;				//客户名称(申请单位)--只读
	private String custId;				   //客户ID(申请单位)--只读
	private String custContact;				//客户联系人--只读
	private String custPhone;				//客户联系电话--只读

	private BigDecimal designFee;			//设计费

	private String duDeadline;				//设计时限

	private String acquisitionDays;				//天数

	private String budgetType;//预算类型，集团预算正常流转，第三方预算直接流转至预算记录
	
	private String whiteMapRegisterRemark;	//白图登记备注

	private String gasSource;				//气源
	private String designContent;			//设计内容
	private String gasSupplyMode;			//供气方式
	private String households;				//设计户数
	private String diameterReq;				//管径要求
	// Constructors
	@Column(name = "BUDGET_TYPE")
	public String getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}

	/** default constructor */
	public DesignInfo() {
	}

	// Property accessors
	@Id
	@Column(name = "DI_ID", unique = true)
	public String getDiId() {
		return this.diId;
	}

	public void setDiId(String diId) {
		this.diId = diId;
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

	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return this.projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}


	@Column(name = "DU_ID")
	public String getDuId() {
		return this.duId;
	}

	public void setDuId(String duId) {
		this.duId = duId;
	}

	@Column(name = "DU_NAME")
	public String getDuName() {
		return this.duName;
	}

	public void setDuName(String duName) {
		this.duName = duName;
	}


	@Column(name = "DESIGNER")
	public String getDesigner() {
		return this.designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DU_COMPLETE_DATE")
	public Date getDuCompleteDate() {
		return this.duCompleteDate;
	}

	public void setDuCompleteDate(Date duCompleteDate) {
		this.duCompleteDate = duCompleteDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "OCO_DATE")
	public Date getOcoDate() {
		return ocoDate;
	}

	public void setOcoDate(Date ocoDate) {
		this.ocoDate = ocoDate;
	}
	
	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	
	@Column(name = "PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	
	@Column(name = "DESIGNER_ID")
	public String getDesignerId() {
		return designerId;
	}

	public void setDesignerId(String designerId) {
		this.designerId = designerId;
	}
	
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	@Column(name = "TENANT_ID")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	@Column(name = "ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Column(name = "DESIGN_NO")
	public String getDesignNo() {
		return designNo;
	}

	public void setDesignNo(String designNo) {
		this.designNo = designNo;
	}
	
	@Column(name = "DESIGN_DRAWING_NO")
	public String getDesignDrawingNo() {
		return designDrawingNo;
	}

	public void setDesignDrawingNo(String designDrawingNo) {
		this.designDrawingNo = designDrawingNo;
	}
	
	@Column(name = "DESIGN_DRAWING_COPIES")
	public BigDecimal getDesignDrawingCopies() {
		return designDrawingCopies;
	}

	public void setDesignDrawingCopies(BigDecimal designDrawingCopies) {
		this.designDrawingCopies = designDrawingCopies;
	}
	
	@Column(name = "DESIGN_DRAWING_SHEETS")
	public BigDecimal getDesignDrawingSheets() {
		return designDrawingSheets;
	}

	public void setDesignDrawingSheets(BigDecimal designDrawingSheets) {
		this.designDrawingSheets = designDrawingSheets;
	}
	
	@Column(name = "DESGIN_REMARK")
	public String getDesginRemark() {
		return desginRemark;
	}

	public void setDesginRemark(String desginRemark) {
		this.desginRemark = desginRemark;
	}
	
	@Transient
	public String getProjectTypeDes() {
		return projectTypeDes;
	}

	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}
	
	@Transient
	public String getContributionModeDes() {
		return contributionModeDes;
	}

	public void setContributionModeDes(String contributionModeDes) {
		this.contributionModeDes = contributionModeDes;
	}
	
	@Transient
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Transient
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	@Transient
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	@Transient
	public String getCustContact() {
		return custContact;
	}

	public void setCustContact(String custContact) {
		this.custContact = custContact;
	}
	
	@Transient
	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	@Column(name = "DESIGN_FEE")
	public BigDecimal getDesignFee() {
		return designFee;
	}

	public void setDesignFee(BigDecimal designFee) {
		this.designFee = designFee;
	}


	@Column(name = "DU_DEADLINE")
	public String getDuDeadline() {
		return duDeadline;
	}

	public void setDuDeadline(String duDeadline) {
		this.duDeadline = duDeadline;
	}

	@Column(name = "ACQUISITION_DAYS")
	public String getAcquisitionDays() {
		return acquisitionDays;
	}

	public void setAcquisitionDays(String acquisitionDays) {
		this.acquisitionDays = acquisitionDays;
	}
    @Transient
	public String getWhiteMapRegisterRemark() {
		return whiteMapRegisterRemark;
	}

	public void setWhiteMapRegisterRemark(String whiteMapRegisterRemark) {
		this.whiteMapRegisterRemark = whiteMapRegisterRemark;
	}
	@Column(name = "GAS_SOURCE")
	public String getGasSource() {
		return gasSource;
	}

	public void setGasSource(String gasSource) {
		this.gasSource = gasSource;
	}
	@Column(name = "DESIGN_CONTENT")
	public String getDesignContent() {
		return designContent;
	}

	public void setDesignContent(String designContent) {
		this.designContent = designContent;
	}
	@Column(name = "GAS_SUPPLY_MODE")
	public String getGasSupplyMode() {
		return gasSupplyMode;
	}

	public void setGasSupplyMode(String gasSupplyMode) {
		this.gasSupplyMode = gasSupplyMode;
	}
	@Column(name = "HOUSE_HOLDS")
	public String getHouseholds() {
		return households;
	}

	public void setHouseholds(String households) {
		this.households = households;
	}
	@Column(name = "DIAMETER_REQ")
	public String getDiameterReq() {
		return diameterReq;
	}

	public void setDiameterReq(String diameterReq) {
		this.diameterReq = diameterReq;
	}

	@Transient
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Column(name="DATA_ACR_REMARK")
	public String getDataAcqRemark() {
		return dataAcqRemark;
	}

	public void setDataAcqRemark(String dataAcqRemark) {
		this.dataAcqRemark = dataAcqRemark;
	}

	@Override
	public String toString() {
		return "DesignInfo [diId=" + diId + ", projId=" + projId + ", projNo=" + projNo + ", projName=" + projName
				+ ", projAddr=" + projAddr + ", projScaleDes=" + projScaleDes + ", duId=" + duId + ", duName=" + duName
				+ ", designer=" + designer + ", designerId=" + designerId + ", duCompleteDate=" + duCompleteDate
				+ ", ocoDate=" + ocoDate + ", corpId=" + corpId + ", tenantId=" + tenantId + ", orgId=" + orgId
				+ ", designNo=" + designNo + ", designDrawingNo=" + designDrawingNo + ", designDrawingCopies="
				+ designDrawingCopies + ", designDrawingSheets=" + designDrawingSheets + ", dataAcqRemark="
				+ dataAcqRemark + ", desginRemark=" + desginRemark + ", projectTypeDes=" + projectTypeDes
				+ ", contributionModeDes=" + contributionModeDes + ", deptName=" + deptName + ", corpName=" + corpName
				+ ", custName=" + custName + ", custId=" + custId + ", custContact=" + custContact + ", custPhone="
				+ custPhone + ", designFee=" + designFee + ", duDeadline=" + duDeadline + ", acquisitionDays="
				+ acquisitionDays + ", budgetType=" + budgetType + ", whiteMapRegisterRemark=" + whiteMapRegisterRemark
				+ ", gasSource=" + gasSource + ", designContent=" + designContent + ", gasSupplyMode=" + gasSupplyMode
				+ ", households=" + households + ", diameterReq=" + diameterReq + "]";
	}
	
	
}