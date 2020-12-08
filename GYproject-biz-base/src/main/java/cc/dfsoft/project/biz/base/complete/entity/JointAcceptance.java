package cc.dfsoft.project.biz.base.complete.entity;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

/**
 * JointAcceptance entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JOINT_ACCEPTANCE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class JointAcceptance implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2880909961690474630L;
	private String jaId;                       //联合验收单id
	private String projId;                     //项目id
	private String projNo;                     //项目编号
	private String projName;                   //项目名称
	private String custName;                   //建设方名称
	private String projAddr;                   //项目地址
	private String projScaleDes;               //工程规模详述
	private String conNo;                      //合同编号 
	private String suName;                     //监理单位
	private Date jaDate;                       //验收时间
	private String jaContent;                  //验收内容
	private String jaClum;                     //验收结论
	private Clob custSign;                     //用户签字
	private Clob duNameSign;                   //设计单位签字
	private Clob constructionQae;              //质保师
	private Clob ongcDeputy;					//天然气公司签字
	private String leaveOverQuestion;			//遗留问题
	private String longitude;					//经度
	private String latitude;					//纬度
	private String dataType;					//资料类型 1工艺 2报警

	private Date completedDate;              	//竣工日期-------显示
	private String alarmProj;					//有无报警记录-------标志1：有；0：没有
	private List<Signature> sign;				//签字相关数据
	
	private Clob comPrincipal;					//新增甲方代表签字
	private String isPrint;					    //是否打印标记     0 已打印,1未打印

	private String custCenterDevice;           //客服
	private Clob custCenterSign;               //签字
	private Date custCenterSignDate;           //验收时间
	private String transCompanyDevice;         //输配公司
	private Clob transCompanySign;             //签字
	private Date transCompanySignDate;         //验收时间
	private String measurementDevice;          //计量所
	private Clob measurementSign;              //签字
	private Date measurementSignDate;          //验收时间
	private String pdUnitDevice;               //设计公司
	private Clob pdUnitSign;                   //签字
	private Date pdUnitSignDate;               //验收时间
	private String fieldPrincipalDevice;       //现场代表 - 工程部
	private Clob fieldPrincipalSign;           //签字- 工程部
	private Date fieldPrincipalSignDate;       //验收时间- 工程部
	private String suNameDevice;               //监理公司
	private Clob suNameSign;                   //签字
	private Date suNameSignDate;               //验收时间
	private String informCenterDevice;         //管网数据中心 - 惠水质安部
	private Clob informCenterSign;             //签字 - 惠水质安部
	private Date informCenterSignDate;         //验收时间 - 惠水质安部
	private String techEquipmentDevice;        //技装部
	private Clob techEquipmentSign;            //签字
	private Date techEquipmentSignDate;        //验收时间
	private String economicDevice;             //经济部
	private Clob economicSign;                 //签字
	private Date economicSignDate;             //验收时间
	private String marketDevDevice;            //市场发展部  "1"合格，“2”整改合格,"3"验收中，“0”整改
	private Clob marketDevSign;                //签字
	private Date marketDevSignDate;            //验收时间
	private String cuNameDevice;               //施工单位
	private Clob cuNameSign;                   //签字
	private Date cuNameSignDate;               //验收时间

	//2018-1-29增加意见和时间
	private String custCenterDeviceOpinion;       //客服整改意见
	private Date custCenterDeviceDate;            //客服整改时间
	private String transCompanyDeviceOpinion;       //输配整改意见
	private Date transCompanyDeviceDate;            //输配整改时间
	private String measurementDeviceOpinion;       //计量所整改意见
	private Date measurementDeviceDate;            //计量所整改时间
	private String pdUnitDeviceOpinion;       //设计整改意见
	private Date pdUnitDeviceDate;            //设计整改时间
	private String fieldPrincipalDeviceOpinion;       //市场发展部整改意见
	private Date fieldPrincipalDeviceDate;            //市场发展部整改时间
	private String suNameDeviceOpinion;       //监理整改意见
	private Date suNameDeviceDate;            //监理整改时间
	private String informCenterDeviceOpinion;       //管网整改意见  - 惠水质安部
	private Date informCenterDeviceDate;            //管网整改时间
	private String techEquipmentDeviceOpinion;       //技装部整改意见
	private Date techEquipmentDeviceDate;            //技装部整改时间
	private String marketDevDeviceOpinion;       //市场发展部质安组整改意见
	private Date marketDevDeviceDate;            //市场发展部质安组整改时间
	private String economicDeviceOpinion;       //其他部门整改意见
	private Date economicDeviceDate;            //其他部门整改时间
	private Integer version;					//版本控制


	private String scNo;                          	  //分合同编号
	private String cuName;                            //施工单位
	private String corpName;                          //分公司名-只读
	private String projectTypeDes;					//工程类型-只读
	private String contributionModeDes;				//出资方式-只读
	private String contributionMode;               //出资方式
    private String deptName;						//业务部门-只读
	
	private String acceptanceType;			    //验收类型 1 联合验收 2 一站式验收
	private Date pilotRunDate;                        //试运行时间

	private String projectType;					//工程类型-只读
	private String corpId ;                     //公司Id
	//联合验收申请信息
	private String applyer;		  //申请人
	private String applyerId;	  //申请人ID
	private Date applyDate;		  //申请日期
	private Date planAcceptDate;  //计划验收日期
	private String isStrenthTest;  //是否有试压记录
	private String isCompleteReport;  //是否有竣工报告
	private String isPreInspection;  //是否有预验收报告
	private String applyRemark;  //申请备注
	private String auditState;		//审核状态：0-待申请，1-待审核，2-审核通过
	private String flag;           //推送状态-只读
	private Date scPlannedStartDate;   //计划开工日期---只读
	private String  scPlannedEndDate;     //计划竣工日期---只读
	private Date startDate;            //实际开工日期---只读
	/**是否参与验收，'1'是参与验收，0不参与验收*/
	private String whetherAcceptance; 
	/**财务部意见*/
	private String financialAdvice;
	/**财务签字人*/
	private String financialSign;
	/**财务签字日期*/
	private Date financialSignDate;
	
	@Column(name = "CUST_CENTER_DEVICE_OPINION")
	public String getCustCenterDeviceOpinion() {
		return custCenterDeviceOpinion;
	}

	public void setCustCenterDeviceOpinion(String custCenterDeviceOpinion) {
		this.custCenterDeviceOpinion = custCenterDeviceOpinion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CUST_CENTER_DEVICE_DATE")
	public Date getCustCenterDeviceDate() {
		return custCenterDeviceDate;
	}

	public void setCustCenterDeviceDate(Date custCenterDeviceDate) {
		this.custCenterDeviceDate = custCenterDeviceDate;
	}

	@Temporal(TemporalType.DATE)
	@Transient
	public Date getScPlannedStartDate() {
		return scPlannedStartDate;
	}

	public void setScPlannedStartDate(Date scPlannedStartDate) {
		this.scPlannedStartDate = scPlannedStartDate;
	}
	
    @Transient
	public String  getScPlannedEndDate() {
		return scPlannedEndDate;
	}

	public void setScPlannedEndDate(String scPlannedEndDate) {
		this.scPlannedEndDate = scPlannedEndDate;
	}
	
	@Temporal(TemporalType.DATE)
    @Transient
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "TRANS_COMPANY_DEVICE_OPINION")
	public String getTransCompanyDeviceOpinion() {
		return transCompanyDeviceOpinion;
	}

	public void setTransCompanyDeviceOpinion(String transCompanyDeviceOpinion) {
		this.transCompanyDeviceOpinion = transCompanyDeviceOpinion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TRANS_COMPANY_DEVICE_DATE")
	public Date getTransCompanyDeviceDate() {
		return transCompanyDeviceDate;
	}

	public void setTransCompanyDeviceDate(Date transCompanyDeviceDate) {
		this.transCompanyDeviceDate = transCompanyDeviceDate;
	}

	@Column(name = "ME_ASUREMENT_DEVICE_OPINION")
	public String getMeasurementDeviceOpinion() {
		return measurementDeviceOpinion;
	}

	public void setMeasurementDeviceOpinion(String measurementDeviceOpinion) {
		this.measurementDeviceOpinion = measurementDeviceOpinion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ME_ASUREMENT_DEVICE_DATE")
	public Date getMeasurementDeviceDate() {
		return measurementDeviceDate;
	}

	public void setMeasurementDeviceDate(Date measurementDeviceDate) {
		this.measurementDeviceDate = measurementDeviceDate;
	}

	@Column(name = "PD_UNIT_DEVICE_OPINION")
	public String getPdUnitDeviceOpinion() {
		return pdUnitDeviceOpinion;
	}

	public void setPdUnitDeviceOpinion(String pdUnitDeviceOpinion) {
		this.pdUnitDeviceOpinion = pdUnitDeviceOpinion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PD_UNIT_DEVICE_DATE")
	public Date getPdUnitDeviceDate() {
		return pdUnitDeviceDate;
	}

	public void setPdUnitDeviceDate(Date pdUnitDeviceDate) {
		this.pdUnitDeviceDate = pdUnitDeviceDate;
	}

	@Column(name = "FIELD_PRINCIPAL_DEVICE_OPINION")
	public String getFieldPrincipalDeviceOpinion() {
		return fieldPrincipalDeviceOpinion;
	}

	public void setFieldPrincipalDeviceOpinion(String fieldPrincipalDeviceOpinion) {
		this.fieldPrincipalDeviceOpinion = fieldPrincipalDeviceOpinion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FIELD_PRINCIPAL_DEVICE_DATE")
	public Date getFieldPrincipalDeviceDate() {
		return fieldPrincipalDeviceDate;
	}

	public void setFieldPrincipalDeviceDate(Date fieldPrincipalDeviceDate) {
		this.fieldPrincipalDeviceDate = fieldPrincipalDeviceDate;
	}

	@Column(name = "SU_NAME_DEVICE_OPINION")
	public String getSuNameDeviceOpinion() {
		return suNameDeviceOpinion;
	}

	public void setSuNameDeviceOpinion(String suNameDeviceOpinion) {
		this.suNameDeviceOpinion = suNameDeviceOpinion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SU_NAME_DEVICE_DATE")
	public Date getSuNameDeviceDate() {
		return suNameDeviceDate;
	}

	public void setSuNameDeviceDate(Date suNameDeviceDate) {
		this.suNameDeviceDate = suNameDeviceDate;
	}

	@Column(name = "INFORM_CENTER_DEVICE_OPINION")
	public String getInformCenterDeviceOpinion() {
		return informCenterDeviceOpinion;
	}

	public void setInformCenterDeviceOpinion(String informCenterDeviceOpinion) {
		this.informCenterDeviceOpinion = informCenterDeviceOpinion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INFORM_CENTER_DEVICE_DATE")
	public Date getInformCenterDeviceDate() {
		return informCenterDeviceDate;
	}

	public void setInformCenterDeviceDate(Date informCenterDeviceDate) {
		this.informCenterDeviceDate = informCenterDeviceDate;
	}

	@Column(name = "TECH_EQUIPMENT_DEVICE_OPINION")
	public String getTechEquipmentDeviceOpinion() {
		return techEquipmentDeviceOpinion;
	}

	public void setTechEquipmentDeviceOpinion(String techEquipmentDeviceOpinion) {
		this.techEquipmentDeviceOpinion = techEquipmentDeviceOpinion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TECH_EQUIPMENT_DEVICE_DATE")
	public Date getTechEquipmentDeviceDate() {
		return techEquipmentDeviceDate;
	}

	public void setTechEquipmentDeviceDate(Date techEquipmentDeviceDate) {
		this.techEquipmentDeviceDate = techEquipmentDeviceDate;
	}

	@Column(name = "MARKET_DEV_DEVICE_OPINION")
	public String getMarketDevDeviceOpinion() {
		return marketDevDeviceOpinion;
	}

	public void setMarketDevDeviceOpinion(String marketDevDeviceOpinion) {
		this.marketDevDeviceOpinion = marketDevDeviceOpinion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MARKET_DEV_DEVICE_DATE")
	public Date getMarketDevDeviceDate() {
		return marketDevDeviceDate;
	}

	public void setMarketDevDeviceDate(Date marketDevDeviceDate) {
		this.marketDevDeviceDate = marketDevDeviceDate;
	}

	@Column(name = "ECONOMIC_DEVICE_OPINION")
	public String getEconomicDeviceOpinion() {
		return economicDeviceOpinion;
	}

	public void setEconomicDeviceOpinion(String economicDeviceOpinion) {
		this.economicDeviceOpinion = economicDeviceOpinion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ECONOMIC_DEVICE_DATE")
	public Date getEconomicDeviceDate() {
		return economicDeviceDate;
	}

	public void setEconomicDeviceDate(Date economicDeviceDate) {
		this.economicDeviceDate = economicDeviceDate;
	}

	@Column(name = "CUST_CENTER_DEVICE")
	public String getCustCenterDevice() {
		return custCenterDevice;
	}

	public void setCustCenterDevice(String custCenterDevice) {
		this.custCenterDevice = custCenterDevice;
	}

	@Column(name = "CUST_CENTER_SIGN")
	public String getCustCenterSign() {
		return ClobUtil.ClobToString(this.custCenterSign);
	}

	public void setCustCenterSign(String custCenterSign) throws SerialException, SQLException {
		this.custCenterSign = ClobUtil.stringToClob(custCenterSign);
	}

	@Column(name = "CUST_CENTER_SIGN_DATE")
	@Temporal(TemporalType.DATE)
	public Date getCustCenterSignDate() {
		return custCenterSignDate;
	}

	public void setCustCenterSignDate(Date custCenterSignDate) {
		this.custCenterSignDate = custCenterSignDate;
	}

	@Column(name = "TRANS_COMPANY_DEVICE")
	public String getTransCompanyDevice() {
		return transCompanyDevice;
	}

	public void setTransCompanyDevice(String transCompanyDevice) {
		this.transCompanyDevice = transCompanyDevice;
	}

	@Column(name = "TRANS_COMPANY_SIGN")
	public String getTransCompanySign() {
		return ClobUtil.ClobToString(this.transCompanySign);
	}

	public void setTransCompanySign(String transCompanySign) throws SQLException {
		this.transCompanySign = ClobUtil.stringToClob(transCompanySign);
	}

	@Column(name = "TRANS_COMPANY_SIGN_DATE")
	@Temporal(TemporalType.DATE)
	public Date getTransCompanySignDate() {
		return transCompanySignDate;
	}

	public void setTransCompanySignDate(Date transCompanySignDate) {
		this.transCompanySignDate = transCompanySignDate;
	}

	@Column(name = "MEASUREMENT_DEVICE")
	public String getMeasurementDevice() {
		return measurementDevice;
	}

	public void setMeasurementDevice(String measurementDevice) {
		this.measurementDevice = measurementDevice;
	}

	@Column(name = "MEASUREMENT_SIGN")
	public String getMeasurementSign() {
		return ClobUtil.ClobToString(this.measurementSign);
	}

	public void setMeasurementSign(String measurementSign) throws SQLException {
		this.measurementSign = ClobUtil.stringToClob(measurementSign);
	}

	@Column(name = "MEASUREMENT_SIGN_DATE")
	@Temporal(TemporalType.DATE)
	public Date getMeasurementSignDate() {
		return measurementSignDate;
	}

	public void setMeasurementSignDate(Date measurementSignDate) {
		this.measurementSignDate = measurementSignDate;
	}

	@Column(name = "PD_UNIT_DEVICE")
	public String getPdUnitDevice() {
		return pdUnitDevice;
	}

	public void setPdUnitDevice(String pdUnitDevice) {
		this.pdUnitDevice = pdUnitDevice;
	}

	@Column(name = "PD_UNIT_SIGN")
	public String getPdUnitSign() {
		return ClobUtil.ClobToString(this.pdUnitSign);
	}

	public void setPdUnitSign(String pdUnitSign) throws SQLException {
		this.pdUnitSign = ClobUtil.stringToClob(pdUnitSign);
	}

	@Column(name = "PD_UNIT_SIGN_DATE")
	@Temporal(TemporalType.DATE)
	public Date getPdUnitSignDate() {
		return pdUnitSignDate;
	}

	public void setPdUnitSignDate(Date pdUnitSignDate) {
		this.pdUnitSignDate = pdUnitSignDate;
	}

	@Column(name = "FIELD_PRINCIPAL_DEVICE")
	public String getFieldPrincipalDevice() {
		return fieldPrincipalDevice;
	}

	public void setFieldPrincipalDevice(String fieldPrincipalDevice) {
		this.fieldPrincipalDevice = fieldPrincipalDevice;
	}

	@Column(name = "FIELD_PRINCIPAL_SIGN")
	public String getFieldPrincipalSign() {
		return ClobUtil.ClobToString(this.fieldPrincipalSign);
	}

	public void setFieldPrincipalSign(String fieldPrincipalSign) throws SQLException {
		this.fieldPrincipalSign = ClobUtil.stringToClob(fieldPrincipalSign);
	}

	@Column(name = "FIELD_PRINCIPAL_SIGN_DATE")
	@Temporal(TemporalType.DATE)
	public Date getFieldPrincipalSignDate() {
		return fieldPrincipalSignDate;
	}

	public void setFieldPrincipalSignDate(Date fieldPrincipalSignDate) {
		this.fieldPrincipalSignDate = fieldPrincipalSignDate;
	}

	@Column(name = "SU_NAME_DEVICE")
	public String getSuNameDevice() {
		return suNameDevice;
	}

	public void setSuNameDevice(String suNameDevice) {
		this.suNameDevice = suNameDevice;
	}

	@Column(name = "SU_NAME_SIGN")
	public String getSuNameSign() {
		return ClobUtil.ClobToString(this.suNameSign);
	}

	public void setSuNameSign(String suNameSign) throws SQLException {
		this.suNameSign = ClobUtil.stringToClob(suNameSign);
	}

	@Column(name = "SU_NAME_SIGN_DATE")
	@Temporal(TemporalType.DATE)
	public Date getSuNameSignDate() {
		return suNameSignDate;
	}

	public void setSuNameSignDate(Date suNameSignDate) {
		this.suNameSignDate = suNameSignDate;
	}

	@Column(name = "INFORM_CENTER_DEVICE")
	public String getInformCenterDevice() {
		return informCenterDevice;
	}

	public void setInformCenterDevice(String informCenterDevice) {
		this.informCenterDevice = informCenterDevice;
	}

	@Column(name = "INFORM_CENTER_SIGN")
	public String getInformCenterSign() {
		return ClobUtil.ClobToString(this.informCenterSign);
	}

	public void setInformCenterSign(String informCenterSign) throws SQLException {
		this.informCenterSign = ClobUtil.stringToClob(informCenterSign);
	}

	@Column(name = "INFORM_CENTER_SIGN_DATE")
	@Temporal(TemporalType.DATE)
	public Date getInformCenterSignDate() {
		return informCenterSignDate;
	}

	public void setInformCenterSignDate(Date informCenterSignDate) {
		this.informCenterSignDate = informCenterSignDate;
	}

	@Column(name = "TECH_EQUIPMENT_DEVICE")
	public String getTechEquipmentDevice() {
		return techEquipmentDevice;
	}

	public void setTechEquipmentDevice(String techEquipmentDevice) {
		this.techEquipmentDevice = techEquipmentDevice;
	}

	@Column(name = "TECH_EQUIPMENT_SIGN")
	public String getTechEquipmentSign() {
		return ClobUtil.ClobToString(this.techEquipmentSign);
	}

	public void setTechEquipmentSign(String techEquipmentSign) throws SQLException {
		this.techEquipmentSign = ClobUtil.stringToClob(techEquipmentSign);
	}

	@Column(name = "TECH_EQUIPMENT_SIGN_DATE")
	@Temporal(TemporalType.DATE)
	public Date getTechEquipmentSignDate() {
		return techEquipmentSignDate;
	}

	public void setTechEquipmentSignDate(Date techEquipmentSignDate) {
		this.techEquipmentSignDate = techEquipmentSignDate;
	}

	@Column(name = "ECONOMIC_DEVICE")
	public String getEconomicDevice() {
		return economicDevice;
	}

	public void setEconomicDevice(String economicDevice) {
		this.economicDevice = economicDevice;
	}

	@Column(name = "ECONOMIC_SIGN")
	public String getEconomicSign() {
		return ClobUtil.ClobToString(this.economicSign);
	}

	public void setEconomicSign(String economicSign) throws SQLException {
		this.economicSign = ClobUtil.stringToClob(economicSign);
	}

	@Column(name = "ECONOMIC_SIGN_DATE")
	@Temporal(TemporalType.DATE)
	public Date getEconomicSignDate() {
		return economicSignDate;
	}

	public void setEconomicSignDate(Date economicSignDate) {
		this.economicSignDate = economicSignDate;
	}

	@Column(name = "MARKET_DEV_DEVICE")
	public String getMarketDevDevice() {
		return marketDevDevice;
	}

	public void setMarketDevDevice(String marketDevDevice) {
		this.marketDevDevice = marketDevDevice;
	}

	@Column(name = "MARKET_DEV_SIGN")
	public String getMarketDevSign() {
		return ClobUtil.ClobToString(this.marketDevSign);
	}

	public void setMarketDevSign(String marketDevSign) throws SQLException {
		this.marketDevSign = ClobUtil.stringToClob(marketDevSign);
	}

	@Column(name = "MARKET_DEV_SIGN_DATE")
	@Temporal(TemporalType.DATE)
	public Date getMarketDevSignDate() {
		return marketDevSignDate;
	}

	public void setMarketDevSignDate(Date marketDevSignDate) {
		this.marketDevSignDate = marketDevSignDate;
	}

	// Constructors

	/** default constructor */
	public JointAcceptance() {
	}

	@Transient
	public String getAlarmProj() {
		return alarmProj;
	}


	public void setAlarmProj(String alarmProj) {
		this.alarmProj = alarmProj;
	}


	@Transient
	public Date getCompletedDate() {
		return completedDate;
	}


	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	@Column(name = "IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	// Property accessors
	@Id
	@Column(name = "JA_ID", unique = true)
	public String getJaId() {
		return this.jaId;
	}

	public void setJaId(String jaId) {
		this.jaId = jaId;
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

	@Column(name = "CUST_NAME")
	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return this.projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
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

	@Column(name = "SU_NAME")
	public String getSuName() {
		return this.suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "JA_DATE")
	public Date getJaDate() {
		return this.jaDate;
	}

	public void setJaDate(Date jaDate) {
		this.jaDate = jaDate;
	}

	@Column(name = "JA_CONTENT")
	public String getJaContent() {
		return this.jaContent;
	}

	public void setJaContent(String jaContent) {
		this.jaContent = jaContent;
	}

	@Column(name = "JA_CLUM")
	public String getJaClum() {
		return this.jaClum;
	}

	public void setJaClum(String jaClum) {
		this.jaClum = jaClum;
	}

	@Column(name = "CUST_SIGN")
	public String getCustSign() {
		return ClobUtil.ClobToString(this.custSign);
	}

	public void setCustSign(String custSign) throws SerialException, SQLException {
		this.custSign = ClobUtil.stringToClob(custSign);
	}

	@Column(name = "DU_NAME_SIGN")
	public String getDuNameSign() {
		return ClobUtil.ClobToString(this.duNameSign);
	}

	public void setDuNameSign(String duNameSign) throws SerialException, SQLException {
		this.duNameSign = ClobUtil.stringToClob(duNameSign);
	}

	@Column(name = "CONSTRUCTION_QAE")
	public String getConstructionQae() {
		return ClobUtil.ClobToString(this.constructionQae);
	}

	public void setConstructionQae(String constructionQae) throws SerialException, SQLException {
		this.constructionQae = ClobUtil.stringToClob(constructionQae);
	}

	@Column(name = "ONGC_DEPUTY")
	public String getOngcDeputy() {
		return ClobUtil.ClobToString(this.ongcDeputy);
	}

	public void setOngcDeputy(String ongcDeputy) throws SerialException, SQLException {
		this.ongcDeputy = ClobUtil.stringToClob(ongcDeputy);
	}

	@Column(name = "LEAVE_OVER_QUESTION")
	public String getLeaveOverQuestion() {
		return this.leaveOverQuestion;
	}

	public void setLeaveOverQuestion(String leaveOverQuestion) {
		this.leaveOverQuestion = leaveOverQuestion;
	}

	@Column(name = "LONGITUDE")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "LATITUDE")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}

	@Column(name = "COM_PRINCIPAL")
	public String getComPrincipal() {
		return ClobUtil.ClobToString(this.comPrincipal);
	}

	public void setComPrincipal(String comPrincipal) throws SerialException, SQLException {
		this.comPrincipal = ClobUtil.stringToClob(comPrincipal);
	}

	@Column(name = "DATA_TYPE")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Transient
	public String getScNo() {
		return scNo;
	}

	public void setScNo(String scNo) {
		this.scNo = scNo;
	}

	@Transient
	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}


	@Column(name = "ACCEPTANCE_TYPE")
	public String getAcceptanceType() {
		return acceptanceType;
	}

	public void setAcceptanceType(String acceptanceType) {
		this.acceptanceType = acceptanceType;
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "PILOT_RUN_DATE")
	public Date getPilotRunDate() {
		return pilotRunDate;
	}

	public void setPilotRunDate(Date pilotRunDate) {
		this.pilotRunDate = pilotRunDate;
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

	@Column(name = "CU_NAME_DEVICE")
	public String getCuNameDevice() {
		return cuNameDevice;
	}

	public void setCuNameDevice(String cuNameDevice) {
		this.cuNameDevice = cuNameDevice;
	}

	@Column(name = "CU_NAME_SIGN")
	public String getCuNameSign() {
		return ClobUtil.ClobToString(this.cuNameSign);
	}

	public void setCuNameSign(String cuNameSign) throws SerialException, SQLException {
		this.cuNameSign = ClobUtil.stringToClob(cuNameSign);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CU_NAME_SIGN_DATE")
	public Date getCuNameSignDate() {
		return cuNameSignDate;
	}

	public void setCuNameSignDate(Date cuNameSignDate) {
		this.cuNameSignDate = cuNameSignDate;
	}

	@Column(name="APPLYER")
	public String getApplyer() {
		return applyer;
	}
	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}
	@Column(name="APPLYER_ID")
	public String getApplyerId() {
		return applyerId;
	}
	public void setApplyerId(String applyerId) {
		this.applyerId = applyerId;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="APPLY_DATE")
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="PLAN_ACCEPT_DATE")
	public Date getPlanAcceptDate() {
		return planAcceptDate;
	}
	public void setPlanAcceptDate(Date planAcceptDate) {
		this.planAcceptDate = planAcceptDate;
	}
	@Column(name="IS_STRENTH_TEST")
	public String getIsStrenthTest() {
		return isStrenthTest;
	}
	public void setIsStrenthTest(String isStrenthTest) {
		this.isStrenthTest = isStrenthTest;
	}
	@Column(name="IS_COMPLETE_REPORT")
	public String getIsCompleteReport() {
		return isCompleteReport;
	}
	public void setIsCompleteReport(String isCompleteReport) {
		this.isCompleteReport = isCompleteReport;
	}
	@Column(name="IS_PREINSPECTION")
	public String getIsPreInspection() {
		return isPreInspection;
	}
	public void setIsPreInspection(String isPreInspection) {
		this.isPreInspection = isPreInspection;
	}
	@Column(name="APPLY_REMARK")
	public String getApplyRemark() {
		return applyRemark;
	}
	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}
	@Column(name="AUDIT_STATE")
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	@Transient
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

	@Transient
	public String getContributionModeDes() {
		return contributionModeDes;
	}

	public void setContributionModeDes(String contributionModeDes) {
		this.contributionModeDes = contributionModeDes;
	}
	@Transient
	public String getContributionMode() {
		return contributionMode;
	}

	public void setContributionMode(String contributionMode) {
		this.contributionMode = contributionMode;
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
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	
	@Column (name = "WHETHER_ACCEPTANCE")
	public String getWhetherAcceptance() {
		return whetherAcceptance;
	}

	public void setWhetherAcceptance(String whetherAcceptance) {
		this.whetherAcceptance = whetherAcceptance;
	}
    @Column (name = "FINANCIAL_ADVICE")
	public String getFinancialAdvice() {
		return financialAdvice;
	}

	public void setFinancialAdvice(String financialAdvice) {
		this.financialAdvice = financialAdvice;
	}

	@Column (name = "FINANCIAL_SIGN")
	public String getFinancialSign() {
		return financialSign;
	}

	public void setFinancialSign(String financialSign) {
		this.financialSign = financialSign;
	}
    
	@Temporal(TemporalType.DATE)
	@Column (name = "FINANCIAL_SIGN_DATE")
	public Date getFinancialSignDate() {
		return financialSignDate;
	}

	public void setFinancialSignDate(Date financialSignDate) {
		this.financialSignDate = financialSignDate;
	}

	@Override
	public String toString() {
		return "JointAcceptance [jaId=" + jaId + ", projId=" + projId + ", projNo=" + projNo + ", projName=" + projName
				+ ", custName=" + custName + ", projAddr=" + projAddr + ", projScaleDes=" + projScaleDes + ", conNo="
				+ conNo + ", suName=" + suName + ", jaDate=" + jaDate + ", jaContent=" + jaContent + ", jaClum="
				+ jaClum + ", custSign=" + custSign + ", duNameSign=" + duNameSign + ", constructionQae="
				+ constructionQae + ", ongcDeputy=" + ongcDeputy + ", leaveOverQuestion=" + leaveOverQuestion
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", dataType=" + dataType + ", completedDate="
				+ completedDate + ", alarmProj=" + alarmProj + ", sign=" + sign + ", comPrincipal=" + comPrincipal
				+ ", isPrint=" + isPrint + ", custCenterDevice=" + custCenterDevice + ", custCenterSign="
				+ custCenterSign + ", custCenterSignDate=" + custCenterSignDate + ", transCompanyDevice="
				+ transCompanyDevice + ", transCompanySign=" + transCompanySign + ", transCompanySignDate="
				+ transCompanySignDate + ", measurementDevice=" + measurementDevice + ", measurementSign="
				+ measurementSign + ", measurementSignDate=" + measurementSignDate + ", pdUnitDevice=" + pdUnitDevice
				+ ", pdUnitSign=" + pdUnitSign + ", pdUnitSignDate=" + pdUnitSignDate + ", fieldPrincipalDevice="
				+ fieldPrincipalDevice + ", fieldPrincipalSign=" + fieldPrincipalSign + ", fieldPrincipalSignDate="
				+ fieldPrincipalSignDate + ", suNameDevice=" + suNameDevice + ", suNameSign=" + suNameSign
				+ ", suNameSignDate=" + suNameSignDate + ", informCenterDevice=" + informCenterDevice
				+ ", informCenterSign=" + informCenterSign + ", informCenterSignDate=" + informCenterSignDate
				+ ", techEquipmentDevice=" + techEquipmentDevice + ", techEquipmentSign=" + techEquipmentSign
				+ ", techEquipmentSignDate=" + techEquipmentSignDate + ", economicDevice=" + economicDevice
				+ ", economicSign=" + economicSign + ", economicSignDate=" + economicSignDate + ", marketDevDevice="
				+ marketDevDevice + ", marketDevSign=" + marketDevSign + ", marketDevSignDate=" + marketDevSignDate
				+ ", cuNameDevice=" + cuNameDevice + ", cuNameSign=" + cuNameSign + ", cuNameSignDate=" + cuNameSignDate
				+ ", custCenterDeviceOpinion=" + custCenterDeviceOpinion + ", custCenterDeviceDate="
				+ custCenterDeviceDate + ", transCompanyDeviceOpinion=" + transCompanyDeviceOpinion
				+ ", transCompanyDeviceDate=" + transCompanyDeviceDate + ", measurementDeviceOpinion="
				+ measurementDeviceOpinion + ", measurementDeviceDate=" + measurementDeviceDate
				+ ", pdUnitDeviceOpinion=" + pdUnitDeviceOpinion + ", pdUnitDeviceDate=" + pdUnitDeviceDate
				+ ", fieldPrincipalDeviceOpinion=" + fieldPrincipalDeviceOpinion + ", fieldPrincipalDeviceDate="
				+ fieldPrincipalDeviceDate + ", suNameDeviceOpinion=" + suNameDeviceOpinion + ", suNameDeviceDate="
				+ suNameDeviceDate + ", informCenterDeviceOpinion=" + informCenterDeviceOpinion
				+ ", informCenterDeviceDate=" + informCenterDeviceDate + ", techEquipmentDeviceOpinion="
				+ techEquipmentDeviceOpinion + ", techEquipmentDeviceDate=" + techEquipmentDeviceDate
				+ ", marketDevDeviceOpinion=" + marketDevDeviceOpinion + ", marketDevDeviceDate=" + marketDevDeviceDate
				+ ", economicDeviceOpinion=" + economicDeviceOpinion + ", economicDeviceDate=" + economicDeviceDate
				+ ", version=" + version + ", scNo=" + scNo + ", cuName=" + cuName + ", corpName=" + corpName
				+ ", projectTypeDes=" + projectTypeDes + ", contributionModeDes=" + contributionModeDes
				+ ", contributionMode=" + contributionMode + ", deptName=" + deptName + ", acceptanceType="
				+ acceptanceType + ", pilotRunDate=" + pilotRunDate + ", projectType=" + projectType + ", corpId="
				+ corpId + ", applyer=" + applyer + ", applyerId=" + applyerId + ", applyDate=" + applyDate
				+ ", planAcceptDate=" + planAcceptDate + ", isStrenthTest=" + isStrenthTest + ", isCompleteReport="
				+ isCompleteReport + ", isPreInspection=" + isPreInspection + ", applyRemark=" + applyRemark
				+ ", auditState=" + auditState + ", flag=" + flag + ", scPlannedStartDate=" + scPlannedStartDate
				+ ", scPlannedEndDate=" + scPlannedEndDate + ", startDate=" + startDate + ", whetherAcceptance="
				+ whetherAcceptance + ", financialAdvice=" + financialAdvice + ", financialSign=" + financialSign
				+ ", financialSignDate=" + financialSignDate + "]";
	}


	
	
}