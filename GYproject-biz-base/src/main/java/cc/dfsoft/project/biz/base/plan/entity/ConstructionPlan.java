package cc.dfsoft.project.biz.base.plan.entity;

import cc.dfsoft.project.biz.base.baseinfo.dto.WorkDayDto;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;

import javax.persistence.*;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper.StandardWarningHandler;

import com.alibaba.druid.stat.TableStat.Name;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 施工计划
 */
@Entity
@Table(name = "CONSTRUCTION_PLAN")
public class ConstructionPlan implements Serializable {
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3827932679359843757L;
	private String cpId;				//计划ID
	private String projId;				//工程ID
	private String projNo;				//工程编号
	private String projName;			//工程名称
	private String projScaleDes;		//工程规模
	private String projAddr;			//工程地点
	private Date cpArriveDate;			//计划下达时间
	private String cpDocumentDeptid;	//计划编制部门ID
	private String cpDocumentDeptName;	//计划编制部门
	private String cpDocumenterId;		//编制人ID
	private String cpDocumenter;		//编制人
	private String projTimeLimit;		//工期
	private Date plannedStartDate;		//开工日期
	private String remark;				//备注
	private BigDecimal contractAmount;	//合同款
	private BigDecimal firstPayment;	//应收首付款
	private BigDecimal downPayment;		//实收首付款
	private String isinitialPayment;	//是否交完首付款
	private Integer version;				//版本控制
	//监理单位信息
	private String suId;				//监理单位ID
	private String suName;				//监理单位
	private String suDirector;			//监理单位负责人
	private String suPhone;				//监理负责人电话
	private String suCseId;				//总监理工程师Id
	private String suCse;				//总监理工程师
	private String suCsePhone;			//总监理工程师电话
	private String suJgjId;				//现场监理工程师Id
	private String suJgj;				//现场监理工程师
	private String suJgjPhone;			//现场监理工程师电话

	private String suProfessionalId;			//专业监理师Id
	private String suProfessional;				//专业监理
	private String suProfessionalPhone;			//专业监理师电话
	private String suRepresentativeId;			//总监代表Id
	private String suRepresentative;			//总监代表
	private String suRepresentativePhone;		//总监代表电话

	//燃气公司信息
	private String builderId;			//现场代表Id
	private String builder;				//现场代表
	private String bulTel;				//现场代表电话
	//施工单位信息
	private String cuId;				//施工单位id
	private String cuName;				//施工单位名称
	private String cuLegalRepresent;	//施工单位负责人
	private String cuPhone;				//施工单位负责人电话
	private String cuPmId;				//项目经理Id
	private String cuPm;				//项目经理
	private String cuPmTel;				//项目经理电话
	private String managementQaeId;		//施工员Id
	private String managementQae;		//施工员
	private String qaeTel;				//施工员电话
	private String managementWacfId;	//材料员Id
	private String managementWacf;		//材料员
	private String wacfTel;				//材料员电话
	private String cuLegalRepresentId;               //施工单位负责人ID
	private String documenterId;	//资料员Id
	private String documenter;		//资料员
	private String dcterTel;	   //资料员电话

	private String saftyOfficerId;		//安全员Id
	private String saftyOfficer;		//安全员
	private String saftyTel;			//安全员电话
	private String technicianId;		//质量技术员Id
	private String technician;			//质量技术员
	private String technicianTel;		//技术员电话
	//设计单位信息
	private String duName;				//设计单位---页面显示
	private String duDesigner;			//设计员---页面显示
	private String drawingType;			//设计员---页面显示
	//用户信息
	private String custContact;			//用户联系人---页面显示
	private String custPhone;			//用户联系电话
	//工程信息
	private String corpName;		    //分公司名称
	private String deptName;			//部门名称---页面显示
	private String projectTypeDes;		//工程类型描述---页面显示
	private String contributionModeDes;	//出资方式描述---页面显示
	
	private String mrAuditLevel; 		//已审核级别---只读属性，用于图纸审核屏
	private String level;        		//几级审核---只读属性，用于方案图纸审核页面显示按钮
	private Boolean overdue;			//是否逾期 true逾期 false未逾期
	private BigDecimal quAmount;		//协议价款---只读属性，用于分包合同

	private String isPrint;				 //是否打印标记     0 已打印,1未打印
	private String suIsPrint;			//监理任务单是否打印 0 已打印,1未打印
	
	private String deptId;				//部门id 
	private String projectType;			//工程类型
	private String deptDivide;			//部门划分-只读
	// Constructors
	
	private String testLeaderId;		//班组长id
	private String testLeader;			//班组长
	
	private String welder;				//焊工
	private String welderId;			//焊工id
	
	private String projStatusId;		//工程状态id
	private String projStatusDes;		//工程状态

	private String cuIsDispatch;		//施工单位是否派遣
	private String suIsDispatch;		//监理单位是否派遣
	private String firstPartyProvide;   //是否甲方供材
	
	private String isSpecialSign;		//是否特殊工程 1 是 0 否
	private WorkDayDto workDayDto;		//只读
	
	private BigDecimal applyAmount;		//申请金额
	
	private String endSettlementCost;//终审金额-只读
	private String endAmount;			//质保金额-只读
	private String corpId;				//分公司id
	
	private String menuId;				//菜单id
	private String projectjReport;        //是否上传立项报告书字段
	
	@Transient
	public String getIsSpecialSign() {
		return isSpecialSign;
	}

	public void setIsSpecialSign(String isSpecialSign) {
		this.isSpecialSign = isSpecialSign;
	}

	/** default constructor */
	public ConstructionPlan() {
	}

	// Property accessors
	@Id
	@Column(name = "CP_ID", unique = true)
	public String getCpId() {
		return this.cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}
	@Column(name="CU_LEGAL_REPRESENT_ID")
	public String getCuLegalRepresentId() {
		return cuLegalRepresentId;
	}

	public void setCuLegalRepresentId(String cuLegalRepresentId) {
		this.cuLegalRepresentId = cuLegalRepresentId;
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

	@Column(name = "PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return this.projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}

	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return this.projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CP_ARRIVE_DATE")
	public Date getCpArriveDate() {
		return this.cpArriveDate;
	}

	public void setCpArriveDate(Date cpArriveDate) {
		this.cpArriveDate = cpArriveDate;
	}

	@Column(name = "CP_DOCUMENT_DEPTID")
	public String getCpDocumentDeptid() {
		return this.cpDocumentDeptid;
	}

	public void setCpDocumentDeptid(String cpDocumentDeptid) {
		this.cpDocumentDeptid = cpDocumentDeptid;
	}

	@Column(name = "CP_DOCUMENTER")
	public String getCpDocumenter() {
		return this.cpDocumenter;
	}

	public void setCpDocumenter(String cpDocumenter) {
		this.cpDocumenter = cpDocumenter;
	}
	
	
	@Column(name = "CP_DOCUMENT_DEPT_NAME")
	public String getCpDocumentDeptName() {
		return cpDocumentDeptName;
	}

	public void setCpDocumentDeptName(String cpDocumentDeptName) {
		this.cpDocumentDeptName = cpDocumentDeptName;
	}

	@Column(name = "CP_DOCUMENTER_ID")
	public String getCpDocumenterId() {
		return cpDocumenterId;
	}

	public void setCpDocumenterId(String cpDocumenterId) {
		this.cpDocumenterId = cpDocumenterId;
	}

	@Column(name = "PROJ_TIME_LIMIT")
	public String getProjTimeLimit() {
		return this.projTimeLimit;
	}

	public void setProjTimeLimit(String projTimeLimit) {
		this.projTimeLimit = projTimeLimit;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PLANNED_START_DATE")
	public Date getPlannedStartDate() {
		return this.plannedStartDate;
	}

	public void setPlannedStartDate(Date plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "CONTRACT_AMOUNT")
	public BigDecimal getContractAmount() {
		return this.contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	@Column(name = "DOWN_PAYMENT")
	public BigDecimal getDownPayment() {
		return this.downPayment;
	}

	public void setDownPayment(BigDecimal downPayment) {
		this.downPayment = downPayment;
	}

	@Column(name = "SU_ID")
	public String getSuId() {
		return this.suId;
	}

	public void setSuId(String suId) {
		this.suId = suId;
	}

	@Column(name = "SU_NAME")
	public String getSuName() {
		return this.suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	@Column(name = "SU_DIRECTOR")
	public String getSuDirector() {
		return this.suDirector;
	}

	public void setSuDirector(String suDirector) {
		this.suDirector = suDirector;
	}

	@Column(name = "SU_PHONE")
	public String getSuPhone() {
		return this.suPhone;
	}

	public void setSuPhone(String suPhone) {
		this.suPhone = suPhone;
	}

	@Column(name = "SU_JGJ")
	public String getSuJgj() {
		return this.suJgj;
	}

	public void setSuJgj(String suJgj) {
		this.suJgj = suJgj;
	}

	@Transient
	public String getDuName() {
		return this.duName;
	}

	public void setDuName(String duName) {
		this.duName = duName;
	}

	@Transient
	public String getDuDesigner() {
		return this.duDesigner;
	}

	public void setDuDesigner(String duDesigner) {
		this.duDesigner = duDesigner;
	}

	@Column(name = "MANAGEMENT_QAE")
	public String getManagementQae() {
		return this.managementQae;
	}

	public void setManagementQae(String managementQae) {
		this.managementQae = managementQae;
	}

	@Column(name = "MANAGEMENT_WACF")
	public String getManagementWacf() {
		return this.managementWacf;
	}

	public void setManagementWacf(String managementWacf) {
		this.managementWacf = managementWacf;
	}

	@Column(name = "SAFTY_OFFICER")
	public String getSaftyOfficer() {
		return this.saftyOfficer;
	}

	public void setSaftyOfficer(String saftyOfficer) {
		this.saftyOfficer = saftyOfficer;
	}

	@Column(name = "TECHNICIAN")
	public String getTechnician() {
		return this.technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}
	
	@Transient
	public String getMrAuditLevel() {
		return mrAuditLevel;
	}

	public void setMrAuditLevel(String mrAuditLevel) {
		this.mrAuditLevel = mrAuditLevel;
	}
	
	@Transient
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	@Transient
	public Boolean getOverdue() {
		return overdue;
	}

	public void setOverdue(Boolean overdue) {
		this.overdue = overdue;
	}
	@Column(name = "CU_ID")
	public String getCuId() {
		return cuId;
	}

	public void setCuId(String cuId) {
		this.cuId = cuId;
	}
	@Column(name = "CU_NAME")
	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	@Column(name = "CU_LEGAL_REPRESENT")
	public String getCuLegalRepresent() {
		return cuLegalRepresent;
	}

	public void setCuLegalRepresent(String cuLegalRepresent) {
		this.cuLegalRepresent = cuLegalRepresent;
	}
	@Column(name = "CU_PHONE")
	public String getCuPhone() {
		return cuPhone;
	}

	public void setCuPhone(String cuPhone) {
		this.cuPhone = cuPhone;
	}
	@Column(name = "QAE_TEL")
	public String getQaeTel() {
		return qaeTel;
	}

	public void setQaeTel(String qaeTel) {
		this.qaeTel = qaeTel;
	}
	@Column(name = "WACF_TEL")
	public String getWacfTel() {
		return wacfTel;
	}

	public void setWacfTel(String wacfTel) {
		this.wacfTel = wacfTel;
	}
	
	@Column(name = "SAFTY_TEL")
	public String getSaftyTel() {
		return saftyTel;
	}

	public void setSaftyTel(String saftyTel) {
		this.saftyTel = saftyTel;
	}
	@Column(name = "TECHNICIAN_TEL")
	public String getTechnicianTel() {
		return technicianTel;
	}

	public void setTechnicianTel(String technicianTel) {
		this.technicianTel = technicianTel;
	}

	@Transient
	public BigDecimal getQuAmount() {
		return quAmount;
	}

	public void setQuAmount(BigDecimal quAmount) {
		this.quAmount = quAmount;
	}
	
	@Column(name = "BUILDER")
	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}
	
	@Column(name = "BUILDER_PHONE")
	public String getBulTel() {
		return bulTel;
	}

	public void setBulTel(String bulTel) {
		this.bulTel = bulTel;
	}
	@Column(name = "ISINITIAL_PAYMENT")
	public String getIsinitialPayment() {
		return isinitialPayment;
	}

	public void setIsinitialPayment(String isinitialPayment) {
		this.isinitialPayment = isinitialPayment;
	}
	
	@Column(name = "BUILDER_ID")
	public String getBuilderId() {
		return builderId;
	}

	public void setBuilderId(String builderId) {
		this.builderId = builderId;
	}

	@Column(name = "CU_PM")
	public String getCuPm() {
		return cuPm;
	}

	public void setCuPm(String cuPm) {
		this.cuPm = cuPm;
	}

	@Column(name = "CU_PM_ID")
	public String getCuPmId() {
		return cuPmId;
	}

	public void setCuPmId(String cuPmId) {
		this.cuPmId = cuPmId;
	}

	@Column(name = "MANAGEMENT_QAE_ID")
	public String getManagementQaeId() {
		return managementQaeId;
	}

	public void setManagementQaeId(String managementQaeId) {
		this.managementQaeId = managementQaeId;
	}

	@Column(name = "MANAGEMENT_WACF_ID")
	public String getManagementWacfId() {
		return managementWacfId;
	}

	public void setManagementWacfId(String managementWacfId) {
		this.managementWacfId = managementWacfId;
	}

	@Column(name = "SAFTY_OFFICER_ID")
	public String getSaftyOfficerId() {
		return saftyOfficerId;
	}

	public void setSaftyOfficerId(String saftyOfficerId) {
		this.saftyOfficerId = saftyOfficerId;
	}

	@Column(name = "TECHNICIAN_ID")
	public String getTechnicianId() {
		return technicianId;
	}

	public void setTechnicianId(String technicianId) {
		this.technicianId = technicianId;
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

	@Transient
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	@Transient
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	public String getDrawingType() {
		return drawingType;
	}

	public void setDrawingType(String drawingType) {
		this.drawingType = drawingType;
	}

	@Column(name = "FIRST_PAYMENT")
	public BigDecimal getFirstPayment() {
		return firstPayment;
	}

	public void setFirstPayment(BigDecimal firstPayment) {
		this.firstPayment = firstPayment;
	}
	
	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name="SU_JGJ_ID")
	public String getSuJgjId() {
		return suJgjId;
	}

	public void setSuJgjId(String suJgjId) {
		this.suJgjId = suJgjId;
	}

	@Column(name="SU_CSE_ID")
	public String getSuCseId() {
		return suCseId;
	}

	public void setSuCseId(String suCseId) {
		this.suCseId = suCseId;
	}

	@Column(name="SU_CSE")
	public String getSuCse() {
		return suCse;
	}

	public void setSuCse(String suCse) {
		this.suCse = suCse;
	}

	@Column(name="SU_CSE_PHONE")
	public String getSuCsePhone() {
		return suCsePhone;
	}

	public void setSuCsePhone(String suCsePhone) {
		this.suCsePhone = suCsePhone;
	}

	@Column(name="SU_JGJ_PHONE")
	public String getSuJgjPhone() {
		return suJgjPhone;
	}

	public void setSuJgjPhone(String suJgjPhone) {
		this.suJgjPhone = suJgjPhone;
	}

	@Column(name="CU_PM_TEL")
	public String getCuPmTel() {
		return cuPmTel;
	}

	public void setCuPmTel(String cuPmTel) {
		this.cuPmTel = cuPmTel;
	}

	@Column(name="IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	@Column(name="SU_PROFESSIONAL_ID")
	public String getSuProfessionalId() {
		return suProfessionalId;
	}

	public void setSuProfessionalId(String suProfessionalId) {
		this.suProfessionalId = suProfessionalId;
	}

	@Column(name="SU_PROFESSIONAL")
	public String getSuProfessional() {
		return suProfessional;
	}

	public void setSuProfessional(String suProfessional) {
		this.suProfessional = suProfessional;
	}

	@Column(name="SU_PROFESSIONAL_PHONE")
	public String getSuProfessionalPhone() {
		return suProfessionalPhone;
	}

	public void setSuProfessionalPhone(String suProfessionalPhone) {
		this.suProfessionalPhone = suProfessionalPhone;
	}

	@Column(name="SU_REPRESENTATIVE_ID")
	public String getSuRepresentativeId() {
		return suRepresentativeId;
	}

	public void setSuRepresentativeId(String suRepresentativeId) {
		this.suRepresentativeId = suRepresentativeId;
	}

	@Column(name="SU_REPRESENTATIVE")
	public String getSuRepresentative() {
		return suRepresentative;
	}

	public void setSuRepresentative(String suRepresentative) {
		this.suRepresentative = suRepresentative;
	}

	@Column(name="SU_REPRESENTATIVE_PHONE")
	public String getSuRepresentativePhone() {
		return suRepresentativePhone;
	}

	public void setSuRepresentativePhone(String suRepresentativePhone) {
		this.suRepresentativePhone = suRepresentativePhone;
	}
	
	@Transient
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Transient
	public String getDeptDivide() {
		return deptDivide;
	}

	public void setDeptDivide(String deptDivide) {
		this.deptDivide = deptDivide;
	}
	
	@Column(name="TEST_LEADER_ID")
	public String getTestLeaderId() {
		return testLeaderId;
	}

	public void setTestLeaderId(String testLeaderId) {
		this.testLeaderId = testLeaderId;
	}
	
	@Column(name="TEST_LEADER")
	public String getTestLeader() {
		return testLeader;
	}

	public void setTestLeader(String testLeader) {
		this.testLeader = testLeader;
	}
	
	@Column(name="WELDER")
	public String getWelder() {
		return welder;
	}

	public void setWelder(String welder) {
		this.welder = welder;
	}
	
	@Column(name="WELDER_ID")
	public String getWelderId() {
		return welderId;
	}

	public void setWelderId(String welderId) {
		this.welderId = welderId;
	}
	
	@Transient
	public String getProjStatusId() {
		return projStatusId;
	}

	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
	}
	
	@Transient
	public String getProjStatusDes() {
		for(ProjStatusEnum e: ProjStatusEnum.values()) {
	   		if(e.getValue().equals(this.projStatusId)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setProjStatusDes(String projStatusDes) {
		this.projStatusDes = projStatusDes;
	}
	@Column(name="SU_IS_PRINT")
	public String getSuIsPrint() {
		return suIsPrint;
	}

	public void setSuIsPrint(String suIsPrint) {
		this.suIsPrint = suIsPrint;
	}

	@Column(name="CU_IS_DISPATCH")
	public String getCuIsDispatch() {
		return cuIsDispatch;
	}

	public void setCuIsDispatch(String cuIsDispatch) {
		this.cuIsDispatch = cuIsDispatch;
	}

	@Column(name="SU_IS_DISPATCH")
	public String getSuIsDispatch() {
		return suIsDispatch;
	}

	public void setSuIsDispatch(String suIsDispatch) {
		this.suIsDispatch = suIsDispatch;
	}
	@Column(name="FIRST_PARTY_PROVIDE")
	public String getFirstPartyProvide() {
		return firstPartyProvide;
	}

	public void setFirstPartyProvide(String firstPartyProvide) {
		this.firstPartyProvide = firstPartyProvide;
	}
	@Column(name = "DOCUMENTER_ID")
	public String getDocumenterId() {
		return documenterId;
	}

	public void setDocumenterId(String documenterId) {
		this.documenterId = documenterId;
	}
	@Column(name = "DOCUMENTER")
	public String getDocumenter() {
		return documenter;
	}

	public void setDocumenter(String documenter) {
		this.documenter = documenter;
	}
	@Column(name = "DCTER_TEL")
	public String getDcterTel() {
		return dcterTel;
	}

	public void setDcterTel(String dcterTel) {
		this.dcterTel = dcterTel;
	}

	@Transient
	public WorkDayDto getWorkDayDto() {
		return workDayDto;
	}

	public void setWorkDayDto(WorkDayDto workDayDto) {
		this.workDayDto = workDayDto;
	}
	@Transient
	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	@Transient
	public String getEndSettlementCost() {
		return endSettlementCost;
	}

	public void setEndSettlementCost(String endSettlementCost) {
		this.endSettlementCost = endSettlementCost;
	}

	@Transient
	public String getEndAmount() {
		return endAmount;
	}

	public void setEndAmount(String endAmount) {
		this.endAmount = endAmount;
	}
	
	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	@Transient
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	@Column(name = "PROJECT_REPORT")
	public String getProjectjReport() {
		return projectjReport;
	}

	public void setProjectjReport(String projectjReport) {
		this.projectjReport = projectjReport;
	}
  
	
	
	
}