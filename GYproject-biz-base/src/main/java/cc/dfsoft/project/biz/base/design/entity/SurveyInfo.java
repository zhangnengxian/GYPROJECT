package cc.dfsoft.project.biz.base.design.entity;

import cc.dfsoft.project.biz.base.baseinfo.dto.WorkDayDto;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ScaleDetail;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 勘察信息 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SURVEY_INFO")
public class SurveyInfo implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -9113796098322990609L;
	
    /**主键ID*/
	private String surveyId;
	/**工程ID*/
	private String projId;
	/**工程编号*/
	private String projNo;	
	/**工程名称*/
	private String projName;			
	/**公司ID*/
	private String corpId;				
	/**踏勘日期*/
	private Date surveyDate;     		
	/**踏勘人*/
	private String surveyer;     		
	/**踏勘人ID*/
	private String surveyerId;   		
	/**设计人*/
	private String designer;			
	/**设计人ID*/
	private String designerId;			
	/**安装户数*/
	private String installNums;			
	/**干线长度*/
	private String trunkLength;			
	/**片区号*/
	private String area;				
	/**客户现状情况*/
	private String customerSituation;	
	/**带气次数*/
	private String withGasFrequency;	
	/**使用气源*/
	private String gasSourceUse;	 	
	/**破路长度*/
	private String destroyRoadLength;	
	/**是否调压设施*/
	private String isNewBuild;			
	/**是否有电子版资料*/
	private String isElectronicData; 	
	/**电子版资料必须有*/
	private String mustElectronicData;	
	/**租户ID*/
	private String tenantId;			
	/**电子版资料必须提供*/
	private String betterElectronicData;
	/**是否达到进场条件*/
	private String approachCondition;	
	/**道路预计交付时*/
	private Date estimateDeliverDate;	
	/**踏勘结果*/
	private String surveyDes;			
	/**踏勘结果登记日期-贵阳-贵安*/
	private Date surveyDesDate;			
	/**接气方案描述*/
	private String connectGasDes;		
	/**技术建议*/
	private String technicalSuggestion;	
	/**技术建议日期-贵阳-贵安*/
	private Date techSugDate;			
	/**其他部门建议*/
	private String otherSuggestion;	
	/**其他部门建议*/
	private Date otherSugDate;			
	/**版本控制*/
	private Integer version;			
	/**客户名称*/
	private String custName;			
	/**客户联系人*/
	private String custContact;			
	/**客户ID*/
	private String custId;			   
	/**客户联系电话*/
	private String custPhone;			
	/**出资方式*/
	private String contributionModeDes;	
	/**分公司名称*/
	private String corpName;			
	/**业务部门*/
	private String deptName;			
	/**工程大类ID*/
	private String projectType;			
	/**工程大类描述*/
	private String projLtypeDes;		
	/**出资方式ID*/
	private String contributionMode;	
	/**工程地点*/
	private String projAddr;			
	/**工程规模*/
	private String projScaleDes;		
	/**退单或接单*/
	private String isBack;				
	/**退单原因*/
	private String backReason;			
	/**已审核级别----只读属性，用于接气方案审核屏*/
	private String mrAuditLevel; 
	/**几级审核--只读属性，用于审核页面显示按钮*/
	private String level;				
	/**工程对象---只读属性，用于审核屏详述*/
	private Project project;			
	/**是否逾期,'true'是，'false'否*/
	private Boolean overdue;	 		
	/**维度--只读属性*/
	private String projLatitude;		
	/**经度--只读属性*/
	private String projLongitude;		
	/**用于页面显示--只读*/
	private String contractType;		
	/**是否移交*/
	private String projectChange;		
	/**备注-只读*/
	private String remark;				
	/**部门划分-只读，现场踏勘时用来判断加载设计员的标记*/
	private String divide;				
	/**工程所属部门ID--只读，现场踏勘时用来加载设计员的标记*/
	private String deptId;				
	/**智能表工程；普通表安装户数*/
	private String generalMeterNum;		
	/**智能表安装户数*/
	private String intelligentMeterNum; 
	/**是否是智能表标记：--只读*/
	private String isIntelligentMeter;	
	/**多条规模明细*/
	private List<ScaleDetail> scaleDetails; 
	/**派遣踏勘人日期*/
	private Date disSurveyDate;			
	/**退单备注*/
	private String backRemarks;			
	/**时限-只读*/
	private WorkDayDto workDayDto;		
	/**设计员签字*/
	private Clob duDeputy;				
	/**工程部签字*/
	private Clob engineering;			
	/**市场部签字*/
	private Clob market;				
	/**用户签字*/
	private Clob customer;	
	/**用户意见*/
	private String custOpinon;			
	/**打印标识，默认值'1','1'未打印，'0'已打印*/
	private String signs = ContractIsPrintEnum.HAVE_NOT_PRINT.getValue();
	/**是否有挂表区*/
	private String isSupe;				
	/**签字相关数据*/
	private List<Signature> sign;		
	
	/**报装内容*/
	private String installContent;		
	/**用气情况*/
	private String gasContent;	 		
	/**能耗情况*/
	private String energyContent;		
	/**开票类型*/
	private String billingType;			
	/**安装要求*/
	private String installaRequirements;
	/**市政破路*/
	private String municipalRoad;		
	/**是否超长*/
	private String isTooLong;			
	/**超长米数*/
	private String exceedLong;			
	/**高位架空*/
	private String highOverhead;		
	/**高位架空米数*/
	private String highOverheadMeters; 
	/**施工所需工期*/
	private String conTimeLimit;		
	/**参与踏勘现场代表ID-只读*/
	private String surveyBuilderId;		
	/**参与现场踏勘代表--只读*/
	private String surveyBuilder;      
	/**受理人*/
	private String accepter;			
	/**受理人ID*/
	private String accepterId;			
	/**受理人电话*/
	private String accepterPhone;		
	
	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}

	@Column(name = "IS_SUPE")
	public String getIsSupe() {
		return isSupe;
	}

	public void setIsSupe(String isSupe) {
		this.isSupe = isSupe;
	}
	@Column(name = "SIGNS")
	public String getSigns() {
		return signs;
	}

	public void setSigns(String signs) {
		this.signs = signs;
	}

	@Column(name = "DU_DEPUTY")
	public String getDuDeputy() {
		return ClobUtil.ClobToString(this.duDeputy);
	}

	public void setDuDeputy(String duDeputy) throws SQLException {
		this.duDeputy = ClobUtil.stringToClob(duDeputy);
	}
	@Column(name = "ENGINEERING")
	public String getEngineering() {
		return ClobUtil.ClobToString(this.engineering);
	}

	public void setEngineering(String engineering) throws SQLException {
		this.engineering =  ClobUtil.stringToClob(engineering);
	}
	@Column(name = "MARKET")
	public String getMarket() {
		return ClobUtil.ClobToString(this.market);
	}

	public void setMarket(String market) throws SQLException {
		this.market =  ClobUtil.stringToClob(market);
	}

	/** default constructor */
	public SurveyInfo() {
	}

	// Property accessors
	@Id
	@Column(name = "SURVEY_ID", unique = true)
	public String getSurveyId() {
		return this.surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
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


	@Column(name = "CONNECT_GAS_DES")
	public String getConnectGasDes() {
		return this.connectGasDes;
	}

	public void setConnectGasDes(String connectGasDes) {
		this.connectGasDes = connectGasDes;
	}

	@Column(name = "SURVEY_DES")
	public String getSurveyDes() {
		return this.surveyDes;
	}

	public void setSurveyDes(String surveyDes) {
		this.surveyDes = surveyDes;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SURVEY_DATE")
	public Date getSurveyDate() {
		return this.surveyDate;
	}

	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}

	@Column(name = "SURVEYER")
	public String getSurveyer() {
		return this.surveyer;
	}

	public void setSurveyer(String surveyer) {
		this.surveyer = surveyer;
	}
	

	@Transient
	public String getMrAuditLevel() {
		return mrAuditLevel;
	}

	public void setMrAuditLevel(String mrAuditLevel) {
		this.mrAuditLevel = mrAuditLevel;
	}
	
	@Transient
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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
	
	@Transient
	public String getBackReason() {
		return backReason;
	}

	public void setBackReason(String backReason) {
		this.backReason = backReason;
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
	@Transient
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Transient
	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	
	@Transient
	public String getIsBack() {
		return isBack;
	}

	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}
	
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	@Transient
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	@Column(name = "SURVEYER_ID")
	public String getSurveyerId() {
		return surveyerId;
	}

	public void setSurveyerId(String surveyerId) {
		this.surveyerId = surveyerId;
	}
	
	@Column(name = "INSTALL_NUMS")
	public String getInstallNums() {
		return installNums;
	}

	public void setInstallNums(String installNums) {
		this.installNums = installNums;
	}
	
	@Column(name = "TRUNK_LENGTH")
	public String getTrunkLength() {
		return trunkLength;
	}

	public void setTrunkLength(String trunkLength) {
		this.trunkLength = trunkLength;
	}
	
	@Column(name = "AREA")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Column(name = "CUSTOMER_SITUATION")
	public String getCustomerSituation() {
		return customerSituation;
	}

	public void setCustomerSituation(String customerSituation) {
		this.customerSituation = customerSituation;
	}
	
	@Column(name = "WITH_GAS_FREQUENCY")
	public String getWithGasFrequency() {
		return withGasFrequency;
	}

	public void setWithGasFrequency(String withGasFrequency) {
		this.withGasFrequency = withGasFrequency;
	}
	
	@Column(name = "GAS_SOURCE_USE")
	public String getGasSourceUse() {
		return gasSourceUse;
	}

	public void setGasSourceUse(String gasSourceUse) {
		this.gasSourceUse = gasSourceUse;
	}
	
	@Column(name = "DESTROY_ROAD_LENGTH")
	public String getDestroyRoadLength() {
		return destroyRoadLength;
	}

	public void setDestroyRoadLength(String destroyRoadLength) {
		this.destroyRoadLength = destroyRoadLength;
	}
	
	@Column(name = "IS_NEW_BUILD")
	public String getIsNewBuild() {
		return isNewBuild;
	}

	public void setIsNewBuild(String isNewBuild) {
		this.isNewBuild = isNewBuild;
	}
	
	@Column(name = "IS_ELECTRONIC_DATA")
	public String getIsElectronicData() {
		return isElectronicData;
	}

	public void setIsElectronicData(String isElectronicData) {
		this.isElectronicData = isElectronicData;
	}
	
	@Column(name = "MUST_ELECTRONIC_DATA")
	public String getMustElectronicData() {
		return mustElectronicData;
	}

	public void setMustElectronicData(String mustElectronicData) {
		this.mustElectronicData = mustElectronicData;
	}
	
	@Column(name = "BETTER_ELECTRONIC_DATA")
	public String getBetterElectronicData() {
		return betterElectronicData;
	}

	public void setBetterElectronicData(String betterElectronicData) {
		this.betterElectronicData = betterElectronicData;
	}
	
	@Column(name = "APPROACH_CONDITION")
	public String getApproachCondition() {
		return approachCondition;
	}

	public void setApproachCondition(String approachCondition) {
		this.approachCondition = approachCondition;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ESTIMATE_DELIVER_DATE")
	public Date getEstimateDeliverDate() {
		return estimateDeliverDate;
	}

	public void setEstimateDeliverDate(Date estimateDeliverDate) {
		this.estimateDeliverDate = estimateDeliverDate;
	}
	
	@Column(name = "TECHNICAL_SUGGESTION")
	public String getTechnicalSuggestion() {
		return technicalSuggestion;
	}

	public void setTechnicalSuggestion(String technicalSuggestion) {
		this.technicalSuggestion = technicalSuggestion;
	}
	
	@Column(name = "DESIGNER")
	public String getDesigner() {
		return designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}
	
	@Column(name = "DESIGNER_ID")
	public String getDesignerId() {
		return designerId;
	}

	public void setDesignerId(String designerId) {
		this.designerId = designerId;
	}
	
	@Transient
	public String getProjScaleDes() {
		return projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	
	@Transient
	public String getProjLtypeDes() {
		return projLtypeDes;
	}

	public void setProjLtypeDes(String projLtypeDes) {
		this.projLtypeDes = projLtypeDes;
	}
	
	@Transient
	public String getContributionModeDes() {
		return contributionModeDes;
	}

	public void setContributionModeDes(String contributionModeDes) {
		this.contributionModeDes = contributionModeDes;
	}
	
	
	@Transient
	public String getProjLatitude() {
		return projLatitude;
	}

	public void setProjLatitude(String projLatitude) {
		this.projLatitude = projLatitude;
	}

	@Transient
	public String getProjLongitude() {
		return projLongitude;
	}

	public void setProjLongitude(String projLongitude) {
		this.projLongitude = projLongitude;
	}
	
	@Transient
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	@Column(name = "TENANT_ID")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	@Transient
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
	@Transient
	public String getProjectChange() {
		return projectChange;
	}

	public void setProjectChange(String projectChange) {
		this.projectChange = projectChange;
	}
	
	@Transient
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Transient
	public String getDivide() {
		return divide;
	}

	public void setDivide(String divide) {
		this.divide = divide;
	}

	@Transient
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name="GENERAL_METER_NUM")
	public String getGeneralMeterNum() {
		return generalMeterNum;
	}

	public void setGeneralMeterNum(String generalMeterNum) {
		this.generalMeterNum = generalMeterNum;
	}

	@Column(name="INTELLIGENT_METER_NUM")
	public String getIntelligentMeterNum() {
		return intelligentMeterNum;
	}

	public void setIntelligentMeterNum(String intelligentMeterNum) {
		this.intelligentMeterNum = intelligentMeterNum;
	}

	@Transient
	public String getIsIntelligentMeter() {
		return isIntelligentMeter;
	}

	public void setIsIntelligentMeter(String isIntelligentMeter) {
		this.isIntelligentMeter = isIntelligentMeter;
	}

	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	@Transient
	public List<ScaleDetail> getScaleDetails() {
		return scaleDetails;
	}

	public void setScaleDetails(List<ScaleDetail> scaleDetails) {
		this.scaleDetails = scaleDetails;
	}

	@Transient
	@Temporal(TemporalType.DATE)
	public Date getDisSurveyDate() {
		return disSurveyDate;
	}

	public void setDisSurveyDate(Date disSurveyDate) {
		this.disSurveyDate = disSurveyDate;
	}

	@Transient
	public String getBackRemarks() {
		return backRemarks;
	}

	public void setBackRemarks(String backRemarks) {
		this.backRemarks = backRemarks;
	}

	@Transient
	public WorkDayDto getWorkDayDto() {
		return workDayDto;
	}

	public void setWorkDayDto(WorkDayDto workDayDto) {
		this.workDayDto = workDayDto;
	}
	
	@Transient
	public String getContributionMode() {
		return contributionMode;
	}

	public void setContributionMode(String contributionMode) {
		this.contributionMode = contributionMode;
	}
	@Column(name="INSTALL_CONTENT")
	public String getInstallContent() {
		return installContent;
	}

	public void setInstallContent(String installContent) {
		this.installContent = installContent;
	}
	@Column(name="GAS_CONTENT")
	public String getGasContent() {
		return gasContent;
	}

	public void setGasContent(String gasContent) {
		this.gasContent = gasContent;
	}
	@Column(name="ENERGY_CONTENT")
	public String getEnergyContent() {
		return energyContent;
	}

	public void setEnergyContent(String energyContent) {
		this.energyContent = energyContent;
	}
	@Column(name="INSTALLA_REQUIREMENTS")
	public String getInstallaRequirements() {
		return installaRequirements;
	}

	public void setInstallaRequirements(String installaRequirements) {
		this.installaRequirements = installaRequirements;
	}
	@Column(name="IS_TOO_LONG")
	public String getIsTooLong() {
		return isTooLong;
	}

	public void setIsTooLong(String isTooLong) {
		this.isTooLong = isTooLong;
	}
	@Column(name="EXCEED_LONG")
	public String getExceedLong() {
		return exceedLong;
	}

	public void setExceedLong(String exceedLong) {
		this.exceedLong = exceedLong;
	}
	@Column(name="HIGH_OVER_HEAD")
	public String getHighOverhead() {
		return highOverhead;
	}

	public void setHighOverhead(String highOverhead) {
		this.highOverhead = highOverhead;
	}
	@Column(name="CON_TIME_LIMIT")
	public String getConTimeLimit() {
		return conTimeLimit;
	}

	public void setConTimeLimit(String conTimeLimit) {
		this.conTimeLimit = conTimeLimit;
	}
	@Column(name="BILLING_TYPE")
	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}
	@Column(name="MUNICIPAL_ROAD")
	public String getMunicipalRoad() {
		return municipalRoad;
	}

	public void setMunicipalRoad(String municipalRoad) {
		this.municipalRoad = municipalRoad;
	}
	@Column(name="HIGH_OVERHEAD_METERS")
	public String getHighOverheadMeters() {
		return highOverheadMeters;
	}

	public void setHighOverheadMeters(String highOverheadMeters) {
		this.highOverheadMeters = highOverheadMeters;
	}

	@Column(name = "CUSTOMER")
	public String getCustomer() {
		return ClobUtil.ClobToString(this.customer);
	}

	public void setCustomer(String customer) throws SQLException {
		this.customer =  ClobUtil.stringToClob(customer);
	}
	
	@Column(name = "CUST_OPINON")
	public String getCustOpinon() {
		return custOpinon;
	}

	public void setCustOpinon(String custOpinon) {
		this.custOpinon = custOpinon;
	}

	@Transient
	public String getSurveyBuilderId() {
		return surveyBuilderId;
	}

	public void setSurveyBuilderId(String surveyBuilderId) {
		this.surveyBuilderId = surveyBuilderId;
	}

	@Transient
	public String getSurveyBuilder() {
		return surveyBuilder;
	}

	public void setSurveyBuilder(String surveyBuilder) {
		this.surveyBuilder = surveyBuilder;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="SURVEY_DES_DATE")
	public Date getSurveyDesDate() {
		return surveyDesDate;
	}

	public void setSurveyDesDate(Date surveyDesDate) {
		this.surveyDesDate = surveyDesDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="TECH_SUG_DATE")
	public Date getTechSugDate() {
		return techSugDate;
	}

	public void setTechSugDate(Date techSugDate) {
		this.techSugDate = techSugDate;
	}
	
	@Transient
	public String getAccepter() {
		return accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}
	@Transient
	public String getAccepterId() {
		return accepterId;
	}

	public void setAccepterId(String accepterId) {
		this.accepterId = accepterId;
	}
	@Transient
	public String getAccepterPhone() {
		return accepterPhone;
	}

	public void setAccepterPhone(String accepterPhone) {
		this.accepterPhone = accepterPhone;
	}

	@Column(name="OTHER_SUGGESTION")
	public String getOtherSuggestion() {
		return otherSuggestion;
	}

	public void setOtherSuggestion(String otherSuggestion) {
		this.otherSuggestion = otherSuggestion;
	}

	@Column(name="OTHER_SUGDATE")
	public Date getOtherSugDate() {
		return otherSugDate;
	}

	public void setOtherSugDate(Date otherSugDate) {
		this.otherSugDate = otherSugDate;
	}
	
	
}