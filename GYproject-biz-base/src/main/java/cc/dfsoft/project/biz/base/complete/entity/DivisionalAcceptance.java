package cc.dfsoft.project.biz.base.complete.entity;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
/**
 * 分部验收
 * @author fuliwei
 *
 */
@Entity
@Table(name = "DIVISIONAL_ACCEPTANCE")
public class DivisionalAcceptance implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8022784579482604034L;
	
	private String daId;						//验收单id
	private String daaId;						//申请单id
	private String projId;                     	//工程id
	private String projNo;                     //工程编号
	private String projName;                   //工程名称
	private String custName;                   //建设方名称
	private String projAddr;                   //项目地址
	private String projScaleDes;               //工程规模详述
	private String conNo;                      //合同编号 
	private String scNo;                       //协议编号 
	private String cuPm;                       //项目经理
	private String suName;                     //监理单位
	private String cuName;						//施工单位
	
	private String suReport;					//监理报告有无
	private String strengthTest;				//试验报告有无
	private String workLetter;					//工作联系函有无
	private String selfCheckTable;				//自检表有无
	private String acceptanceTable;				//分部验收表有无
	private String completionDrawing;			//三份竣工图有无
	private String isWholeComplete;				//是否整体完工
	private String purgeRecord;				    //吹扫记录有无
	private Date   acceptanceDate;				//验收时间
	
	private String transmissionOpinion;			//输配公司意见
	private String customerServiceOpinion;		//客服中心意见（综合组）
	private String managementQaeOpinion;        //施工单位意见
	private String metrologyOfficeOpinion;		//计量所意见
	private String remark;						//备注
    private String regulatClassOpinion;         //巡线调压班意见 
    private Clob regulatClass;                  //巡线员签字 
	private Date regulatClassSignDate;          //巡线员签字日期
	
	private Clob cuSpdPrincipal;				//现场代表
	private Clob duDeputy;						//设计代表
	private Clob suFieldJgj;					//现场监理
	private Clob managementQae;					//施工员
	private Clob organizationMan;				//组织人
	
	private String corpName;		    		//分公司名称--只读
	private String corpId;                      //分公司ID--只读
	private String deptName;	 				//部门名称--只读
	private String projectTypeDes;				//工程类型描述
	private String contributionModeDes;			//出资方式描述--只读
	
	private List<Signature> sign;				//签字相关数据

	private String flag;						//保存状态 0 保存 1推送
	private String isPrint;						//是否打印

	private String thisAcceptanceContent;		//本次验收内容
	private Clob custCenterSign;               //客服签字（综合组）
	private Clob transCompanySign;             //输配签字
	private Clob measurementSign;              //计量所签字
	
	private String acceptanceState;            //验收状态 1 已验收 0未验收
	private String builder;                     //现场代表
	private String suRepresentative;			//监理代表
	private String managementQae1;				//施工员
	private String duDesigner;					//设计员
	private String managementWacf;				//材料员
	private String saftyOfficer;				//安全员
	private String technician;					//技术员
	private String suJgj;						//监理
	private Clob ceneralEngineer;              //总工程师签字
	private Date  ceneralEngineerSignDate;     //总工程师签字日期

	private String isGas;						//是否带气
	
	private Integer version;					//版本控制
	
	private String trAcceptanceSituat;          //输配验收情况
	private String trRectificationOpinion;      //输配整改意见
	private Date trRectificationDate;           //输配整改日期
	private String cuAcceptanceSituat;          //客服验收情况
	private String cuRectificationOpinion;      //客服整改意见
	private Date cuRectificationDate;           //客服整改日期
	private String meAcceptanceSituat;          //计量所验收情况
	private String meRectificationOpinion;      //计量所整改意见
	private Date meRectificationDate;           //计量所整改日期
	
	private String pdUnitDeviceOpinion;         //设计公司意见
	private String suNameDeviceOpinion;         //监理公司意见
	private String fieldPrincipalDeviceOpinion; //工程技术部意见
	private String marketDevDevice;             //质量安全部结论
	private String marketDevDeviceOpinion;      //质量全部意见
	private Date transCompanySignDate;         //输配公司签字日期 
	private Date custCenterSignDate;           //客服部签字日期
	private Date pdUnitSignDate;               //设计公司签字日期
	private Date suNameSignDate;               //监理公司签字日期
	private Date fieldPrincipalSignDate;       //工程技术部签字日期
	private Date marketDevSignDate;            //质量安全部签字日期
	private Date managementQaeSignDate;        //施工单位施工员签字日期
	
	private String projectType;					//工程类型-只读
	/**市场发展部门意见*/
	private String marketOpinion;
	/**市场发展部门签字人*/
	private String marketSign;
	
	@Column(name = "PD_UNIT_DEVICEOPINION")
	public String getpdUnitDeviceOpinion() {
		return pdUnitDeviceOpinion;
	}

	public void setpdUnitDeviceOpinion(String pdUnitDeviceOpinion) {
		this.pdUnitDeviceOpinion = pdUnitDeviceOpinion;
	}

	
	@Column(name = "CENERAL_ENGINEER")
	public String getCeneralEngineer() {
		return ClobUtil.ClobToString(this.ceneralEngineer);
	}

	public void setCeneralEngineer(String ceneralEngineer) throws SerialException, SQLException {
		this.ceneralEngineer = ClobUtil.stringToClob(ceneralEngineer);
	}
	
	
	@Column(name = "REGULAT_CLASS_OPINION")
	public String getRegulatClassOpinion() {
		return regulatClassOpinion;
	}

	public void setRegulatClassOpinion(String regulatClassOpinion) {
		this.regulatClassOpinion = regulatClassOpinion;
	}

	@Column(name = "REGULAT_CLASS")
	public String getRegulatClass() {
		return ClobUtil.ClobToString(this.regulatClass);
	}

	public void setRegulatClass(String regulatClass) throws SerialException, SQLException {
		this.regulatClass = ClobUtil.stringToClob(regulatClass);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REGULAT_CLASS_SIGNDATE")
	public Date getRegulatClassSignDate() {
		return regulatClassSignDate;
	}

	public void setRegulatClassSignDate(Date regulatClassSignDate) {
		this.regulatClassSignDate = regulatClassSignDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CENERAL_ENGINEER_SIGNDATE")
	public Date getCeneralEngineerSignDate() {
		return ceneralEngineerSignDate;
	}

	public void setCeneralEngineerSignDate(Date ceneralEngineerSignDate) {
		this.ceneralEngineerSignDate = ceneralEngineerSignDate;
	}

	@Column(name = "SU_NAME_DEVICEOPINION")
	public String getsuNameDeviceOpinion() {
		return suNameDeviceOpinion;
	}

	public void setsuNameDeviceOpinion(String suNameDeviceOpinion) {
		this.suNameDeviceOpinion = suNameDeviceOpinion;
	}

	@Column(name = "FIELD_PRINCIPAL_DEVICEOPINION")
	public String getfieldPrincipalDeviceOpinion() {
		return fieldPrincipalDeviceOpinion;
	}

	public void setfieldPrincipalDeviceOpinion(String fieldPrincipalDeviceOpinion) {
		this.fieldPrincipalDeviceOpinion = fieldPrincipalDeviceOpinion;
	}

	@Column(name = "MARKET_DEVDEVICE")
	public String getmarketDevDevice() {
		return marketDevDevice;
	}

	public void setmarketDevDevice(String marketDevDevice) {
		this.marketDevDevice = marketDevDevice;
	}
	
	@Column(name = "MARKET_DEVDEVICEOPINION")
	public String getmarketDevDeviceOpinion() {
		return marketDevDeviceOpinion;
	}

	public void setmarketDevDeviceOpinion(String marketDevDeviceOpinion) {
		this.marketDevDeviceOpinion = marketDevDeviceOpinion;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "TRANSCOMPANY_SIGNDATE")
	public Date gettransCompanySignDate() {
		return transCompanySignDate;
	}

	public void settransCompanySignDate(Date transCompanySignDate) {
		this.transCompanySignDate = transCompanySignDate;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "CUSTCENTER_SIGNDATE")
	public Date getcustCenterSignDate() {
		return custCenterSignDate;
	}

	public void setcustCenterSignDate(Date custCenterSignDate) {
		this.custCenterSignDate = custCenterSignDate;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "PDUNIT_SIGNDATE")
	public Date getpdUnitSignDate() {
		return pdUnitSignDate;
	}

	public void setpdUnitSignDate(Date pdUnitSignDate) {
		this.pdUnitSignDate = pdUnitSignDate;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "SUNAME_SIGNDATE")
	public Date getsuNameSignDate() {
		return suNameSignDate;
	}

	public void setsuNameSignDate(Date suNameSignDate) {
		this.suNameSignDate = suNameSignDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FIELDPRINCIPAL_SIGNDATE")
	public Date getFieldPrincipalSignDate() {
		return fieldPrincipalSignDate;
	}

	public void setFieldPrincipalSignDate(Date fieldPrincipalSignDate) {
		this.fieldPrincipalSignDate = fieldPrincipalSignDate;
	}

	
	@Temporal(TemporalType.DATE)
	@Column(name = "MARKETDEV_SIGNDATE")
	public Date getMarketDevSignDate() {
		return marketDevSignDate;
	}

	public void setMarketDevSignDate(Date marketDevSignDate) {
		this.marketDevSignDate = marketDevSignDate;
	}
	
	@Column(name = "MANAGEMENTQAE_OPINION")
	public String getManagementQaeOpinion() {
		return managementQaeOpinion;
	}

	public void setManagementQaeOpinion(String managementQaeOpinion) {
		this.managementQaeOpinion = managementQaeOpinion;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "MANAGEMENTQAE_SIGNDATE")
	public Date getManagementQaeSignDate() {
		return managementQaeSignDate;
	}

	public void setManagementQaeSignDate(Date managementQaeSignDate) {
		this.managementQaeSignDate = managementQaeSignDate;
	}



	@Column(name = "TR_ACCEPTANCE_SITUAT")
	public String getTrAcceptanceSituat() {
		return trAcceptanceSituat;
	}

	public void setTrAcceptanceSituat(String trAcceptanceSituat) {
		this.trAcceptanceSituat = trAcceptanceSituat;
	}
	
	
	@Column(name = "TR_RECTIFICATION_OPINION")
	public String getTrRectificationOpinion() {
		return trRectificationOpinion;
	}

	public void setTrRectificationOpinion(String trRectificationOpinion) {
		this.trRectificationOpinion = trRectificationOpinion;
	}
	
	
	@Column(name = "TR_RECTIFICATION_DATE")
	public Date getTrRectificationDate() {
		return trRectificationDate;
	}

	public void setTrRectificationDate(Date trRectificationDate) {
		this.trRectificationDate = trRectificationDate;
	}
	
	@Column(name = "CU_ACCEPTANCE_SITUAT")
	public String getCuAcceptanceSituat() {
		return cuAcceptanceSituat;
	}

	public void setCuAcceptanceSituat(String cuAcceptanceSituat) {
		this.cuAcceptanceSituat = cuAcceptanceSituat;
	}
	
	@Column(name = "CU_RECTIFICATION_OPINION")
	public String getCuRectificationOpinion() {
		return cuRectificationOpinion;
	}

	public void setCuRectificationOpinion(String cuRectificationOpinion) {
		this.cuRectificationOpinion = cuRectificationOpinion;
	}
	
	
	@Column(name = "CU_RECTIFICATION_DATE")
	public Date getCuRectificationDate() {
		return cuRectificationDate;
	}

	public void setCuRectificationDate(Date cuRectificationDate) {
		this.cuRectificationDate = cuRectificationDate;
	}
	
	
	@Column(name = "ME_ACCEPTANCE_SITUAT")
	public String getMeAcceptanceSituat() {
		return meAcceptanceSituat;
	}
	public void setMeAcceptanceSituat(String meAcceptanceSituat) {
		this.meAcceptanceSituat = meAcceptanceSituat;
	}
	
	
	@Column(name = "ME_RECTIFICATION_OPINION")
	public String getMeRectificationOpinion() {
		return meRectificationOpinion;
	}

	public void setMeRectificationOpinion(String meRectificationOpinion) {
		this.meRectificationOpinion = meRectificationOpinion;
	}
	
	
	@Column(name = "ME_RECTIFICATION_DATE")
	public Date getMeRectificationDate() {
		return meRectificationDate;
	}

	public void setMeRectificationDate(Date meRectificationDate) {
		this.meRectificationDate = meRectificationDate;
	}

	public DivisionalAcceptance(){
	}

	@Id
	@Column(name = "DA_ID", unique = true)
	public String getDaId() {
		return daId;
	}

	public void setDaId(String daId) {
		this.daId = daId;
	}
	
	@Column(name = "PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Column(name = "CUST_NAME")
	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
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

	@Column(name = "CON_NO")
	public String getConNo() {
		return conNo;
	}

	
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}

	@Column(name = "SU_NAME")
	public String getSuName() {
		return suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	@Column(name = "CU_NAME")
	public String getCuName() {
		return cuName;
	}


	public void setCuName(String cuName) {
		this.cuName = cuName;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Column(name = "PROJECT_TYPE_DES")
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
	
	@Column(name = "DAA_ID")
	public String getDaaId() {
		return daaId;
	}

	public void setDaaId(String daaId) {
		this.daaId = daaId;
	}
	
	@Column(name = "SC_NO")
	public String getScNo() {
		return scNo;
	}

	public void setScNo(String scNo) {
		this.scNo = scNo;
	}
	
	@Column(name = "CU_PM")
	public String getCuPm() {
		return cuPm;
	}

	public void setCuPm(String cuPm) {
		this.cuPm = cuPm;
	}
	
	@Column(name = "SU_REPORT")
	public String getSuReport() {
		return suReport;
	}

	public void setSuReport(String suReport) {
		this.suReport = suReport;
	}
	
	@Column(name = "STRENGTH_TEST")
	public String getStrengthTest() {
		return strengthTest;
	}

	public void setStrengthTest(String strengthTest) {
		this.strengthTest = strengthTest;
	}
	
	@Column(name = "WORK_LETTER")
	public String getWorkLetter() {
		return workLetter;
	}

	public void setWorkLetter(String workLetter) {
		this.workLetter = workLetter;
	}
	
	@Column(name = "ACCEPTANCE_TABLE")
	public String getAcceptanceTable() {
		return acceptanceTable;
	}

	public void setAcceptanceTable(String acceptanceTable) {
		this.acceptanceTable = acceptanceTable;
	}
	
	@Column(name = "SELF_CHECK_TABLE")
	public String getSelfCheckTable() {
		return selfCheckTable;
	}

	public void setSelfCheckTable(String selfCheckTable) {
		this.selfCheckTable = selfCheckTable;
	}
	
	@Column(name = "COMPLETION_DRAWING")
	public String getCompletionDrawing() {
		return completionDrawing;
	}

	public void setCompletionDrawing(String completionDrawing) {
		this.completionDrawing = completionDrawing;
	}
	
	@Column(name = "IS_WHOLE_COMPLETE")
	public String getIsWholeComplete() {
		return isWholeComplete;
	}

	public void setIsWholeComplete(String isWholeComplete) {
		this.isWholeComplete = isWholeComplete;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ACCEPTANCE_DATE")
	public Date getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}
	
	@Column(name = "TRANSMISSION_OPINION")
	public String getTransmissionOpinion() {
		return transmissionOpinion;
	}

	public void setTransmissionOpinion(String transmissionOpinion) {
		this.transmissionOpinion = transmissionOpinion;
	}
	
	@Column(name = "CUSTOMER_SERVICE_OPINION")
	public String getCustomerServiceOpinion() {
		return customerServiceOpinion;
	}

	public void setCustomerServiceOpinion(String customerServiceOpinion) {
		this.customerServiceOpinion = customerServiceOpinion;
	}
	
	@Column(name = "METROLOGY_OFFICE_OPINION")
	public String getMetrologyOfficeOpinion() {
		return metrologyOfficeOpinion;
	}

	public void setMetrologyOfficeOpinion(String metrologyOfficeOpinion) {
		this.metrologyOfficeOpinion = metrologyOfficeOpinion;
	}
	
	@Column(name = "CU_SPD_PRINCIPAL")
	public String getCuSpdPrincipal() {
		return ClobUtil.ClobToString(this.cuSpdPrincipal);
	}

	public void setCuSpdPrincipal(String cuSpdPrincipal) throws SerialException, SQLException {
		this.cuSpdPrincipal = ClobUtil.stringToClob(cuSpdPrincipal);
	}
	
	@Column(name = "DU_DEPUTY")
	public String getDuDeputy() {
		return ClobUtil.ClobToString(this.duDeputy);
	}

	public void setDuDeputy(String duDeputy) throws SerialException, SQLException {
		this.duDeputy = ClobUtil.stringToClob(duDeputy);
	}
	
	@Column(name = "SU_FIELD_JGJ")
	public String getSuFieldJgj() {
		return ClobUtil.ClobToString(this.suFieldJgj);
	}

	public void setSuFieldJgj(String suFieldJgj) throws SerialException, SQLException {
		this.suFieldJgj = ClobUtil.stringToClob(suFieldJgj);
	}
	
	@Column(name = "MANAGEMENT_QAE")
	public String getManagementQae() {
		return ClobUtil.ClobToString(this.managementQae);
	}

	public void setManagementQae(String managementQae) throws SerialException, SQLException {
		this.managementQae = ClobUtil.stringToClob(managementQae);
	}
	
	@Column(name = "ORGANIZATION_MAN")
	public String getOrganizationMan() {
		return ClobUtil.ClobToString(this.organizationMan);
	}

	public void setOrganizationMan(String organizationMan) throws SerialException, SQLException {
		this.organizationMan = ClobUtil.stringToClob(organizationMan);
	}
	
	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}
	
	@Transient
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Column(name = "IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	@Column(name = "PURGE_RECORD")
	public String getPurgeRecord() {
		return purgeRecord;
	}

	public void setPurgeRecord(String purgeRecord) {
		this.purgeRecord = purgeRecord;
	}

	@Column(name = "THIS_ACCEPTANCE_CONTENT")
	public String getThisAcceptanceContent() {
		return thisAcceptanceContent;
	}

	public void setThisAcceptanceContent(String thisAcceptanceContent) {
		this.thisAcceptanceContent = thisAcceptanceContent;
	}
	
	@Column(name = "CUST_CENTER_SIGN")
	public String getCustCenterSign() {
		return ClobUtil.ClobToString(this.custCenterSign);
	}

	public void setCustCenterSign(String custCenterSign) throws SerialException, SQLException {
		this.custCenterSign = ClobUtil.stringToClob(custCenterSign);
	}
	
	@Column(name = "TRANS_COMPANY_SIGN")
	public String getTransCompanySign() {
		return ClobUtil.ClobToString(this.transCompanySign);
	}

	public void setTransCompanySign(String transCompanySign) throws SQLException {
		this.transCompanySign = ClobUtil.stringToClob(transCompanySign);
	}
	
	
	@Column(name = "MEASUREMENT_SIGN")
	public String getMeasurementSign() {
		return ClobUtil.ClobToString(this.measurementSign);
	}

	public void setMeasurementSign(String measurementSign) throws SQLException {
		this.measurementSign = ClobUtil.stringToClob(measurementSign);
	}
	
	@Column(name = "ACCEPTANCE_STATE")
	public String getAcceptanceState() {
		return acceptanceState;
	}

	public void setAcceptanceState(String acceptanceState) {
		this.acceptanceState = acceptanceState;
	}
	@Transient
	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}
	@Transient
	public String getSuRepresentative() {
		return suRepresentative;
	}

	public void setSuRepresentative(String suRepresentative) {
		this.suRepresentative = suRepresentative;
	}
	@Transient
	public String getManagementQae1() {
		return managementQae1;
	}

	public void setManagementQae1(String managementQae1) {
		this.managementQae1 = managementQae1;
	}
	@Transient
	public String getDuDesigner() {
		return duDesigner;
	}

	public void setDuDesigner(String duDesigner) {
		this.duDesigner = duDesigner;
	}
	@Transient
	public String getManagementWacf() {
		return managementWacf;
	}

	public void setManagementWacf(String managementWacf) {
		this.managementWacf = managementWacf;
	}
	@Transient
	public String getSaftyOfficer() {
		return saftyOfficer;
	}

	public void setSaftyOfficer(String saftyOfficer) {
		this.saftyOfficer = saftyOfficer;
	}
	@Transient
	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}
	@Transient
	public String getSuJgj() {
		return suJgj;
	}

	public void setSuJgj(String suJgj) {
		this.suJgj = suJgj;
	}

	@Column(name = "IS_GAS")
	public String getIsGas() {
		return isGas;
	}

	public void setIsGas(String isGas) {
		this.isGas = isGas;
	}
	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column (name = "MARKET_OPINION")
	public String getMarketOpinion() {
		return marketOpinion;
	}

	public void setMarketOpinion(String marketOpinion) {
		this.marketOpinion = marketOpinion;
	}

	@Column (name = "MARKET_SIGN")
	public String getMarketSign() {
		return marketSign;
	}

	public void setMarketSign(String marketSign) {
		this.marketSign = marketSign;
	}
	
	
	
}
