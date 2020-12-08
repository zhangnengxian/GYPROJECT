package cc.dfsoft.project.biz.base.constructmanage.entity;

import cc.dfsoft.project.biz.base.project.entity.Signature;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * WorkReport entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WORK_REPORT")
public class WorkReport implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8081613844125452218L;
	private String wrId;			//`WR_ID` varchar(30) NOT NULL COMMENT '开工报告ID',
	private String projId;			//`PROJ_ID` varchar(30) NOT NULL COMMENT '工程ID',
	private String projNo;			//`PROJ_NO` varchar(20) NOT NULL COMMENT '工程编号',
	private String projName;		//`PROJ_NAME` varchar(200) DEFAULT NULL COMMENT '工程名称',
	private String projAddr;		//`PROJ_ADDR` varchar(200) DEFAULT NULL COMMENT '工程地点',
	private String projScaleDes;	//`PROJ_SCALE_DES` varchar(200) DEFAULT NULL COMMENT '工程规模',
	private String projType;		//`PROJ_TYPE` varchar(30) DEFAULT NULL COMMENT '工程类型',
	private String projQuantities;	//用户工程量
	
	private String conNo;			//`CON_NO` varchar(50) DEFAULT NULL COMMENT '合同编号',
	private Date plannedStartDate;	//`PLANNED_START_DATE` datetime DEFAULT NULL COMMENT '计划开工日期',
	private String plannedEndDate;	//`PLANNED_END_DATE` datetime DEFAULT NULL COMMENT '计划竣工日期',
	private String projectStartCase;// `PROJECT_START_CASE` varchar(200) DEFAULT NULL COMMENT '开工条件具备情况',
	private String remark;			// `REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
	private Date suDate;			//`SU_DATE` datetime DEFAULT NULL COMMENT '创建日期',
	
	private String suName;			//`SU_NAME` varchar(50) DEFAULT NULL COMMENT '监理单位',
	private String suCes;				//`SU_CES` longtext COMMENT '总监理工程师',
	private Date suCheckDate;		//`SU_CHECK_DATE` datetime DEFAULT NULL COMMENT '检查日期',
	
	private String constructionUnit;// `CONSTRUCTION_UNIT` varchar(50) DEFAULT NULL COMMENT '施工单位',
	private String cuPm;				// `CU_PM` longtext COMMENT '项目经理',
	private Date inspectionDate;		//`INSPECTION_DATE` datetime DEFAULT NULL COMMENT '报审日期',
	
	
	private String patchCode;		//  `PATCH_CODE` varchar(50) DEFAULT NULL COMMENT '片区号',
	private String courtyardNo;		//  `COURTYARD_NO` varchar(50) DEFAULT NULL COMMENT '庭院管号',
	private String drawingNo;		//  `DRAWING_NO` varchar(50) DEFAULT NULL COMMENT '施工图号',
	private String timeLimit;		//  `TIME_LIMIT` varchar(30) DEFAULT NULL COMMENT '工期',
	private String trunkInstall;	//  `TRUNK_INSTALL` varchar(255) DEFAULT NULL COMMENT '干线安装',
	private String courtyardInstall;//  `COURTYARD_INSTALL` varchar(255) DEFAULT NULL COMMENT '庭院安装',
	private String indoorInstall;	//  `INDOOR_INSTALL` varchar(255) DEFAULT NULL COMMENT '户内安装',
	private String equipmentInstall;//  `EQUIPMENT_INSTALL` varchar(255) DEFAULT NULL COMMENT '设备安装',
	private String custName;		// `CUST_NAME` varchar(200) DEFAULT NULL COMMENT '用户单位名称',
	
	private String viceGeneralManager;		// 副总-安顺
	private String projectLeader;		//  `PROJECT_LEADER` longtext COMMENT '建设单位项目负责人',
	private String projectLeaderTel;		//  `PROJECT_LEADER_TEL` varchar(11) DEFAULT NULL COMMENT '建设单位项目负责人',
	private String fieldRepresent;	// `FIELD_REPRESENT` longtext COMMENT '现场代表',
	private String fieldRepresentTel;//  `FIELD_REPRESENT_TEL` varchar(11) DEFAULT NULL COMMENT '现场代表电话',
	private Date corpDate;			//  `CORP_DATE` datetime DEFAULT NULL COMMENT '建设单位检查日期',
	
	private String cuPmTel;			//  `CU_PM_TEL` varchar(11) DEFAULT NULL COMMENT '施工负责人电话',
	private String builder;			//  `BUILDER` longtext COMMENT '施工员',
	private String builderTel;		//  `BUILDER_TEL` varchar(11) DEFAULT NULL COMMENT '施工员电话',
	private String constructionQc;	//  `CONSTRUCTION_QC` longtext COMMENT '质检员',
	private String safetyOfficer;	//  `SAFETY_OFFICER` longtext COMMENT '安全员',
	
	private String suCesTel;		// `SU_CES_TEL`  varchar(11) NULL DEFAULT NULL COMMENT '总监理工程师电话',
	private String suJgj;			// `SU_JGJ`  longtext NULL COMMENT '现场监理',
	private String suJgjTel;		// `SU_JGJ_TEL`  varchar(11) NULL DEFAULT NULL COMMENT '现场监理电话' ;
	
	private String custContact;		//`CUST_CONTACT` varchar(200) DEFAULT NULL COMMENT '工地用户联系人',
	private String custContactTel;	// `CUST_CONTACT_TEL` varchar(11) DEFAULT NULL COMMENT '工地用户联系人电话',
	
	
	private String corpName;		//`CORP_NAME` varchar(200) DEFAULT NULL COMMENT '甲方(建设单位名称)',
	private String corpId;			//`CORP_ID` varchar(30) DEFAULT NULL COMMENT '分公司ID',
	private String deptId;			// `DEPT_ID` varchar(30) DEFAULT NULL COMMENT '部门ID',
	private String tenantId;		//  `TENANT_ID` varchar(30) DEFAULT NULL COMMENT '租户ID',


	private String projQuantities1;
	private String projQuantities2;
	private String projQuantities3;
	
	private List<Signature> sign;	//签字相关数据
	private Integer version;				//版本控制
	
	private String signState;		//控制是否可进行签字
	private Date cwDate;			//会审交底日期
	
	private String cptUrl;			//报表版本cpt-只读

	private String auditResult;			//审核结果
	private String abandonedReason;		//未通过原因

	
	// Constructors

	/** default constructor */
	public WorkReport() {
	}

	// Property accessors
	@Id
	@Column(name = "WR_ID", unique = true)
	public String getWrId() {
		return this.wrId;
	}
	public void setWrId(String wrId) {
		this.wrId = wrId;
	}
	@Column(name="VICE_GENERAL_MANAGER")
	public String getViceGeneralManager() {
		return viceGeneralManager;
	}

	public void setViceGeneralManager(String viceGeneralManager) {
		this.viceGeneralManager = viceGeneralManager;
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

	@Column(name = "PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return this.projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}

	@Column(name = "CON_NO")
	public String getConNo() {
		return this.conNo;
	}

	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "PLANNED_START_DATE")
	public Date getPlannedStartDate() {
		return this.plannedStartDate;
	}

	public void setPlannedStartDate(Date plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}

	@Column(name = "PLANNED_END_DATE")
	public String getPlannedEndDate() {
		return this.plannedEndDate;
	}

	public void setPlannedEndDate(String plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}

	@Column(name = "PROJECT_START_CASE")
	public String getProjectStartCase() {
		return this.projectStartCase;
	}

	public void setProjectStartCase(String projectStartCase) {
		this.projectStartCase = projectStartCase;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SU_DATE")
	public Date getSuDate() {
		return this.suDate;
	}

	public void setSuDate(Date suDate) {
		this.suDate = suDate;
	}

	@Column(name = "SU_NAME")
	public String getSuName() {
		return this.suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	@Column(name = "SU_CES")
	public String getSuCes() {
		return this.suCes;
	}

	public void setSuCes(String suCes) {
		this.suCes =suCes;
	}

	@Column(name = "CONSTRUCTION_UNIT")
	public String getConstructionUnit() {
		return this.constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	@Column(name = "CU_PM")
	public String getCuPm() {
		return this.cuPm;
	}

	public void setCuPm(String cuPm) {
		this.cuPm = cuPm;
	}
	
	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INSPECTION_DATE")
	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	
	@Column(name="PROJ_TYPE")
	public String getProjType() {
		return projType;
	}

	public void setProjType(String projType) {
		this.projType = projType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="SU_CHECK_DATE")
	public Date getSuCheckDate() {
		return suCheckDate;
	}

	public void setSuCheckDate(Date suCheckDate) {
		this.suCheckDate = suCheckDate;
	}

	@Column(name="PATCH_CODE")
	public String getPatchCode() {
		return patchCode;
	}

	public void setPatchCode(String patchCode) {
		this.patchCode = patchCode;
	}
	
	@Column(name="COURTYARD_NO")
	public String getCourtyardNo() {
		return courtyardNo;
	}

	public void setCourtyardNo(String courtyardNo) {
		this.courtyardNo = courtyardNo;
	}

	@Column(name="DRAWING_NO")
	public String getDrawingNo() {
		return drawingNo;
	}

	public void setDrawingNo(String drawingNo) {
		this.drawingNo = drawingNo;
	}

	@Column(name="TIME_LIMIT")
	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Column(name="TRUNK_INSTALL")
	public String getTrunkInstall() {
		return trunkInstall;
	}

	public void setTrunkInstall(String trunkInstall) {
		this.trunkInstall = trunkInstall;
	}

	@Column(name="COURTYARD_INSTALL")
	public String getCourtyardInstall() {
		return courtyardInstall;
	}

	public void setCourtyardInstall(String courtyardInstall) {
		this.courtyardInstall = courtyardInstall;
	}

	@Column(name="INDOOR_INSTALL")
	public String getIndoorInstall() {
		return indoorInstall;
	}

	public void setIndoorInstall(String indoorInstall) {
		this.indoorInstall = indoorInstall;
	}

	@Column(name="EQUIPMENT_INSTALL")
	public String getEquipmentInstall() {
		return equipmentInstall;
	}

	public void setEquipmentInstall(String equipmentInstall) {
		this.equipmentInstall = equipmentInstall;
	}

	@Column(name="CUST_NAME")
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Column(name="PROJECT_LEADER")
	public String getProjectLeader() {
		return projectLeader;
	}

	public void setProjectLeader(String projectLeader) {
		this.projectLeader = projectLeader;
	}

	@Column(name="PROJECT_LEADER_TEL")
	public String getProjectLeaderTel() {
		return projectLeaderTel;
	}

	public void setProjectLeaderTel(String projectLeaderTel) {
		this.projectLeaderTel = projectLeaderTel;
	}

	@Column(name="FIELD_REPRESENT")
	public String getFieldRepresent() {
		return fieldRepresent;
	}

	public void setFieldRepresent(String fieldRepresent) {
		this.fieldRepresent = fieldRepresent;
	}

	@Column(name="FIELD_REPRESENT_TEL")
	public String getFieldRepresentTel() {
		return fieldRepresentTel;
	}

	public void setFieldRepresentTel(String fieldRepresentTel) {
		this.fieldRepresentTel = fieldRepresentTel;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="CORP_DATE")
	public Date getCorpDate() {
		return corpDate;
	}

	public void setCorpDate(Date corpDate) {
		this.corpDate = corpDate;
	}

	@Column(name="CU_PM_TEL")
	public String getCuPmTel() {
		return cuPmTel;
	}

	public void setCuPmTel(String cuPmTel) {
		this.cuPmTel = cuPmTel;
	}
	@Column(name="BUILDER")
	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	@Column(name="BUILDER_TEL")
	public String getBuilderTel() {
		return builderTel;
	}

	public void setBuilderTel(String builderTel) {
		this.builderTel = builderTel;
	}

	@Column(name="CONSTRUCTION_QC")
	public String getConstructionQc() {
		return constructionQc;
	}

	public void setConstructionQc(String constructionQc) {
		this.constructionQc = constructionQc;
	}

	@Column(name="SAFETY_OFFICER")
	public String getSafetyOfficer() {
		return safetyOfficer;
	}

	public void setSafetyOfficer(String safetyOfficer) {
		this.safetyOfficer = safetyOfficer;
	}
	
	@Column(name="SU_CES_TEL")
	public String getSuCesTel() {
		return suCesTel;
	}

	public void setSuCesTel(String suCesTel) {
		this.suCesTel = suCesTel;
	}

	@Column(name="SU_JGJ")
	public String getSuJgj() {
		return suJgj;
	}

	public void setSuJgj(String suJgj) {
		this.suJgj = suJgj;
	}

	@Column(name="SU_JGJ_TEL")
	public String getSuJgjTel() {
		return suJgjTel;
	}

	public void setSuJgjTel(String suJgjTel) {
		this.suJgjTel = suJgjTel;
	}
	
	@Column(name="CORP_NAME")
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Column(name="DEPT_ID")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name="TENANT_ID")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	@Column(name="CUST_CONTACT")
	public String getCustContact() {
		return custContact;
	}

	public void setCustContact(String custContact) {
		this.custContact = custContact;
	}

	@Column(name="CUST_CONTACT_TEL")
	public String getCustContactTel() {
		return custContactTel;
	}

	public void setCustContactTel(String custContactTel) {
		this.custContactTel = custContactTel;
	}

	@Column(name="PROJ_QUANTITIES")
	public String getProjQuantities() {
		return projQuantities;
	}

	public void setProjQuantities(String projQuantities) {
		this.projQuantities = projQuantities;
	}

	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}
	
	@Column(name="PROJ_QUANTITIES1")
	public String getProjQuantities1() {
		return projQuantities1;
	}
	
	
	public void setProjQuantities1(String projQuantities1) {
		this.projQuantities1 = projQuantities1;
	}
	
	@Column(name="PROJ_QUANTITIES2")
	public String getProjQuantities2() {
		return projQuantities2;
	}

	public void setProjQuantities2(String projQuantities2) {
		this.projQuantities2 = projQuantities2;
	}
	
	@Column(name="PROJ_QUANTITIES3")
	public String getProjQuantities3() {
		return projQuantities3;
	}

	public void setProjQuantities3(String projQuantities3) {
		this.projQuantities3 = projQuantities3;
	}
	
	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@Column(name="SIGN_STATE")
	public String getSignState() {
		return signState;
	}

	public void setSignState(String signState) {
		this.signState = signState;
	}

	@Transient
	@Temporal(TemporalType.DATE)
	public Date getCwDate() {
		return cwDate;
	}

	public void setCwDate(Date cwDate) {
		this.cwDate = cwDate;
	}

	@Transient
	public String getCptUrl() {
		return cptUrl;
	}

	public void setCptUrl(String cptUrl) {
		this.cptUrl = cptUrl;
	}


	@Transient
	public String getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	@Transient
	public String getAbandonedReason() {
		return abandonedReason;
	}
	public void setAbandonedReason(String abandonedReason) {
		this.abandonedReason = abandonedReason;
	}

}