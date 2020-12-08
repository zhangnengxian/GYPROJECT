package cc.dfsoft.project.biz.base.project.entity;

import cc.dfsoft.project.biz.base.baseinfo.dto.WorkDayDto;
import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.project.enums.AreaEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjSourceEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Project entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PROJECT")
@DynamicInsert(true) /**无值字段不参与插入*/
@DynamicUpdate(true) /**值未改变的字段，不进行update**/
public class Project implements java.io.Serializable {

	
	private static final long serialVersionUID = -198439679487895000L;
	/**公建工程类型*/
	public static final String PRO_TYPE_GJ = "12";  
	public static final String PRO_TYPE_DES_GJ = "公建户工程";  //公建工程类型
	public static final String PRO_CON_MODE_GJ = "1";//用户出资
	public static final String PRO_CON_MODE_DES_GJ = "用户出资";
	public static final String PRO_DEPTID_GY_GJ = "11010104";//贵阳营销中心部门ID
	public static final String PRO_DEPT_NAME_GY_GJ = "营销中心";//贵阳营销中心部门名
	
	// Fields

	private String planId;						//计划ID
	private String projId;						//工程ID
	private String projNo;						//工程编号
	private String insideProjNo;				//内部工程编号
	private String projName;					//工程名称
	private String projLtypeId;					//工程大类ID
	private String projSubConSta;				//主体建设阶段
	private String projScaleDes;				//总体规模描述
	private String custNo;						//客户编号
	private String custName;					//客户名称(申请单位)
	private String custId;						//客户id（申请单位）
	private String custContact;					//客户联系人
	private String custPhone;					//客户联系电话
	private String projAddr;					//工程地点
	private String projLatitude;				//纬度
	private String projLongitude;				//经度
	private String projInfoFlag;				//告知书是否发放
	private String isFunds;						//是否募集资金
	private String projSource;					//受理来源
	private String projSourceDes;				//受理来源-只读
	private Date acceptDate;					//受理日期
	
	private String accepter;					//受理人
	private String accepterId;					//受理人id
	private String accepterPhone;				//受理人电话-只读
	private String projStatusId;				//状态
	private String deductible;					//营改增项目
	private String surveyDes;					//勘察结果
	private Date surveyDate;					//勘察日期

	//private String dispatchSurveyer; 			//实际勘察人
	private String surveyer;					//勘察人
	private String surveyerId;					//勘察人ID


	private String designStation;				//设计所
	private String designStationId; 			//设计所ID
	private String designStationDirector;		//负责人

	private String duName;						//设计单位名称
	private String duId;           			    //设计单位ID
	private String designer;					//设计人
	private String designerId;					//设计人ID
	private Date duCompleteDate;				//设计完成日期

	private String technicalSuggestion;			//技术建议--只读属性
	private String connectGasDes;	   			//接气方案

	private BigDecimal budgetTotalCost;			//工程总造价
	private Date budgetDate;					//预算时间
	private String budgeter;					//预算员
	private String budgeterId;					//预算员ID
	private BigDecimal confirmTotalCost;		//确定总造价
	private String costMember;					//造价员
	private String costMemberId;				//造价员
	private BigDecimal contractAmount;			//合同金额
	private String contractPricingType;			//价格形式
	private Date signDate;						//签订日期
	private String operator;					//经办人
	private Date acceptanceDate;				//验收日期
	private String acceptanceResult;			//验收结果
	private String acceptanceDirector;			//验收负责人
	private Date settlementDate;				//结算日期
	private String settlementer;				//结算人
	private String settlementerId;  			//
	
	private String changeReason;	//变动原因
	private String remark;			//备注
	private String costRemark;      //造价备注
	private String area;			//区域
	private String paNo;		 	//受理单号
	private String backReason;		//退单原因
	private String backRemarks;		//退单备注
	private Date startDate;			//计划开工日期
	private Date completedDate;		//竣工日期
	private Date finishedDate;      //结束日期
	private Date affirmCostDate;	//造价确认日期
	private Date backDate;			//退单日期
	private BigDecimal submitAmount;//施工预算金额合计
	private BigDecimal firstSubmitAmount;//初始施工预算金额合计
	private String totalProgress;	//工程总进度
	private String surveyFeedBack;	//接气方案反馈信息

	private String projStatusDes;				//状态-------只用于页面表格显示
	private String areaDes;						//区域------只用于页面表格显示
	private String chargeBackReason;			//退单原因-------只用于页面显示
	private Boolean overdue;					//是否逾期 true逾期 false未逾期
	private Integer overDay;					//是否逾期 true逾期 false未逾期
	private List<ScaleDetail> scaleDetails; 	//多条规模明细
	private String mrAuditLevel; 				//已审核级别----只读属性，用于图纸审核屏

	private String level;       				//几级审核------只读属性，用于方案图纸审核页面显示按钮
	private String suName;						//监理单位
	private String suId;						//监理单位ID
	private String managementOffice;			//施工管理处----只读属性
	private String budiler;						//现场代表
	private String builderId;                   //现场代表ID
	private String conNo;	 					//合同编号-------只读
	private String conAgent;					//经办人
	private List<Signature> signs;				//签字记录
	private Boolean backInform;					//退单通知
	private String signBack;

	private String cuName; 						//分包单位名称
	private String pictureUrl;					//图片路径
	private String pictureStr; 					//图片内容string
	private String flag;       					//保存还是推送 0---保存，1---推送

	private BigDecimal inspectionCost;			//监检费--只读 --带气作业费
	private String gasTimes;					//带气次数
	private BigDecimal suCost;					//监理费--只读
	private BigDecimal storeCost;				//工储备资金--只读
	private BigDecimal materialCost;			//主材费 --只读
	private BigDecimal designCost;				//设计费--只读
	private BigDecimal unforeseenCost;			//设计费--只读
	private String reduceGasTimes;				//带气次数

	private String costManager;		      		//造价经理
	private String costMangeerId;		   		//造价经理id

	private String isModify;					//是否更正 1 更正 0或其他 未更正
	private String contractProjType;			//工程类型1普通 0特殊
	private String builder;					    //施工员(注--施工员有两个请仔细找)
	private String scNo;	 					//施工合同编号-------只读


	private String corpId;		 				//分公司id
	private String corpName;		    		//分公司名称
	private String contributionMode;			//出资方式
	private String deptId;		 				//部门id
	private String deptTransfer;		 		//转移部门
	private String deptName;	 				//部门名称
	private String tenantId;	 				//租户id
	private String orgId;						//组织id
	private String projectType;	 				//工程类型
	private String projectTypeDes;				//工程类型描述
	private String contributionModeDes;			//出资方式描述

	private String budgeterAudit;				//施工预算审核员
	private String budgeterAuditId;				//施工预算审核员ID
	private Date budgeterAuditDate;				//施工预算审核员审核通过日期-只用于页面显示

	private Date applyDate;						//申请时间--只读
	private String note;						//申请备注--只读
	private String daId;						//资料申请id--只读

	private String auditStatus;					//审核状态id--只读
	private String auditStatusDes;				//审核状态--只读

	private String projCostAuditType;			//工程造价审核级别--只读
	private String projCostConfig;			    //工程造价配置--只读

	private String contractType;				//合同类型-用于页面显示
	private String acceptType;					//立项方式 1 受理申请 2 计划立项

	private BigDecimal totalInvestment;			//总投资（万元）
	private Date protocolStartingDate;			//拟开工日期
	private String projectDuration;				//工期
	private String acceptReason;			    //立项理由
	private String explorationUnit;			    //地勘单位
	private String euNo;						//地勘合同协议号
	private BigDecimal euAmount;				//地勘合同金额
	private Date euDate;						//地勘完成时间
	private String euDeadline;					//地勘时限

	private String acceptData;					//立项资料
	private String proceduresData;				//建审资料
	private String contructionData;				//施工资料
	private String settlementData;				//结算资料
	private String finalAccountData;			//决算资料
	
	private String post;						//职务-只读
	private String deptDivide;					//部门划分-只读
	private Date ocoDate;						//设计委托日期-只读
	private String acquisitionPlDays;			//原计划设计委托天数-只读
	private String acquisitionDays;				//设计委托天数-只读
	
	private String isIntelligentMeter;			//是否是智能表标记
	private String designDrawingNo;				//设计图号
	
	private Date duPlCompleteDate;				//计划设计完成日期
	
	private Date acStartDate;					//实际开工日期
	private String planCompleteDate;			//计划竣工日期-只读
	private Date jointAcceptanceDate;			//联合验收日期
	private String projectRemark;			    //受理信息备注
	
	private String suBudgeter;					//分包单位预算员-只读
	private Date suBudgeterDate;			    //分包单位预算日期-只读
	
	private String managementQae;				//施工员(注--施工员有两个请仔细找)
	private String managementWacf;				//材料员
	private String managementWacfId;				//材料员ID
	private String documenter;                  //资料员
	private String documenterId;                  //资料员ID
	private String testLeader;                  //班组长
	private String testLeaderId;                 //班组长ID
	private String welder;                      //焊工
    private String welderId;                    //焊工ID
	private String saftyOfficer;				//安全员
	private String saftyOfficerID;				//安全员ID
	private String technician;					//质量技术员
	private String technicianId;			    //质量技术员ID
	private String suCse;						//总监理工程师
	private String suCseId;                     //总监理工程师ID
	private String suJgj;						//现场监理工程师
	private String suJgjId;                      //现场监理工程师ID
	private String isGas;						//是否带气状态
	
	private String paId;						//请款单ID,在付款审核时使用-只读
	private BigDecimal budgetCost;				    //分包预算价
	private BigDecimal budgetSettlementCost;	    //分包预算审定价
	private BigDecimal endSettlementCost;			//结算审定价
	private BigDecimal sendSettlementCost;         //结算报审价
	private BigDecimal govBudgetCost;				//政府预算审定价
	private BigDecimal govSettlementCost;			//政府结算审定价
	
	private String isSpecialSign;					//是否特殊工程 1 是 0 否
	private String materialFlag;					//是否存在借料 1-存在
	private String isBidding;						//是否招投标
	private String isIndoor ;						//是否户内

	private String projChanges;					    //整改状态
	private String projChangesDes;					//整改状态描述----
	private WorkDayDto workDayDto;					//用于时限进度条展示
	
	private String projConStatusDes;				//工程施工状态描述
	private String projConStatus;				    //工程施工状态
	private String cuId;                             //施工单位ID
	private String cuLegalRepresentId;               //施工单位负责人ID
	private String cuLegalRepresent;                 //施工单位负责人
	private String cuPmId;                           //施工单位项目经理ID
	private String cuPm;						     //项目经理
	private String managementQaeId;                  //施工员ID 
    private String suDirector;                       //监理负责人
    private String suDirectorId;                     //监理负责人ID
	private String suRepresentative;                 //总监代表
	private String suRepresentativeId;                //总监代表ID
	private String suProfessionalId;                  //监理师ID
	private String suProfessional;                    //监理师名字
	private String isBack;                           //是否审核不通过 （可以复用）
	private String surveyBuilderId;					//参与踏勘的现场代表ID
	private String surveyBuilder;                   //参与踏勘的现场代表
	private String budgetMethod;					//0-第三方预算，1-自己单位做预算，2-集团预算
	private String budgetMethodReMark;			//预算确认备注
	private String budgetMethodDes;				//0-第三方预算，1-自己单位做预算，2-集团预算-只读
	private String transfeFlag;					//是否立项转办
	private String isMeasurement;                   //是否有计量表--贵安公司专用 ，1表示具有，0表示没有
	
	private Date cpArriveDate;				//计划下达日期-用于排序


	private String saveType;						//保存/推送标识
	private JointAcceptance jointAcceptance;		//只读


	private String uploadFlag;  					//资料上传
	private String toDoer;							//待办人
	private Date raiseMoneyApplyDate;					//提资申请日期-只读
	private Date raiseMoneyResponseDate;				//提资申请日期-只读
	private Date raiseMoneyAuditDate;					//提资申请日期-只读
	private Date acceptAuditDate;					    //受理审核日期-只读
	private Date surveyDisDate;					        //踏勘派工日期-只读
	
	private String whiteMapRegisterRemark;				//白图登记备注
	private String constructionProcRemark;				//报审手续
	private String monitoringReportRemark;				//监检报告
	

	private Date constructionProcPreDate;//报建手续上一步操作时间-只读
	
	private String instTaskNote;//安装任务备注
	private String meterType;//安装任务表具型号
	private String orderMan;   //安装任务下单人
	private Date instTaskDate;//安装任务下单时间


	private BigDecimal arCost; //应收首付款
	private BigDecimal receiveAmount;//实收首付款

	@Transient
	public String getInstTaskNote() {
		return instTaskNote;
	}

	public void setInstTaskNote(String instTaskNote) {
		this.instTaskNote = instTaskNote;
	}

	@Transient
	public Date getInstTaskDate() {
		return instTaskDate;
	}

	public void setInstTaskDate(Date instTaskDate) {
		this.instTaskDate = instTaskDate;
	}

	@Transient
	public String getSaveType() {
		return saveType;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	@Column(name="SU_ID")
	public String getSuId() {
		return suId;
	}

	public void setSuId(String suId) {
		this.suId = suId;
	}

	
	@Column(name="BUILDER_ID")
	public String getBuilderId() {
		return builderId;
	}

	public void setBuilderId(String builderId) {
		this.builderId = builderId;
	}
	
	@Column(name="MANAGEMENT_WACF_ID")
	public String getManagementWacfId() {
		return managementWacfId;
	}

	public void setManagementWacfId(String managementWacfId) {
		this.managementWacfId = managementWacfId;
	}
    
	@Column(name="DOCUMENTER_ID")
	public String getDocumenterId() {
		return documenterId;
	}

	public void setDocumenterId(String documenterId) {
		this.documenterId = documenterId;
	}

	@Column(name="TEST_LEADER_ID")
	public String getTestLeaderId() {
		return testLeaderId;
	}

	public void setTestLeaderId(String testLeaderId) {
		this.testLeaderId = testLeaderId;
	}
   
	
	@Column(name="WELDER_ID")
	public String getWelderId() {
		return welderId;
	}

	public void setWelderId(String welderId) {
		this.welderId = welderId;
	}

	@Column(name="SAFTY_OFFICER_ID")
	public String getSaftyOfficerID() {
		return saftyOfficerID;
	}

	public void setSaftyOfficerID(String saftyOfficerID) {
		this.saftyOfficerID = saftyOfficerID;
	}
	
	@Column(name="TECHNICIAN_ID")
	public String getTechnicianId() {
		return technicianId;
	}

	public void setTechnicianId(String technicianId) {
		this.technicianId = technicianId;
	}

	@Column(name="SU_CSE_ID")
	public String getSuCseId() {
		return suCseId;
	}

	public void setSuCseId(String suCseId) {
		this.suCseId = suCseId;
	}
	
	@Column(name="SU_JGJ_ID")
	public String getSuJgjId() {
		return suJgjId;
	}

	public void setSuJgjId(String suJgjId) {
		this.suJgjId = suJgjId;
	}
    
	@Column(name="CU_ID")
	public String getCuId() {
		return cuId;
	}

	public void setCuId(String cuId) {
		this.cuId = cuId;
	}
	
	@Column(name="CU_LEGAL_REPRESENT_ID")
	public String getCuLegalRepresentId() {
		return cuLegalRepresentId;
	}

	public void setCuLegalRepresentId(String cuLegalRepresentId) {
		this.cuLegalRepresentId = cuLegalRepresentId;
	}
 
	@Column(name="CU_LEGAL_REPRESENT")
	public String getCuLegalRepresent() {
		return cuLegalRepresent;
	}

	public void setCuLegalRepresent(String cuLegalRepresent) {
		this.cuLegalRepresent = cuLegalRepresent;
	}
	@Column(name="CU_PM_ID")
	public String getCuPmId() {
		return cuPmId;
	}

	public void setCuPmId(String cuPmId) {
		this.cuPmId = cuPmId;
	}

	@Column(name="MANAGEMENT_QAE_ID")
	public String getManagementQaeId() {
		return managementQaeId;
	}

	public void setManagementQaeId(String managementQaeId) {
		this.managementQaeId = managementQaeId;
	}

	@Column(name="SU_DIRECTOR")
	public String getSuDirector() {
		return suDirector;
	}

	public void setSuDirector(String suDirector) {
		this.suDirector = suDirector;
	}
	@Column(name="SU_DIRECTOR_ID")
	public String getSuDirectorId() {
		return suDirectorId;
	}

	public void setSuDirectorId(String suDirectorId) {
		this.suDirectorId = suDirectorId;
	}
	@Column(name="SU_REPRESENTATIVE")
	public String getSuRepresentative() {
		return suRepresentative;
	}

	public void setSuRepresentative(String suRepresentative) {
		this.suRepresentative = suRepresentative;
	}
	@Column(name="SU_REPRESENTATIVE_ID")
	public String getSuRepresentativeId() {
		return suRepresentativeId;
	}

	public void setSuRepresentativeId(String suRepresentativeId) {
		this.suRepresentativeId = suRepresentativeId;
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

	public void setContractProjType(String contractProjType) {
		this.contractProjType = contractProjType;
	}

	@Column(name = "PROJECT_REMARK")
	public String getProjectRemark() {
		return projectRemark;
	}

	public void setProjectRemark(String projectRemark) {
		this.projectRemark = projectRemark;
	}

	/** default constructor */
	public Project() {
	}

	// Property accessors
	@Id
	@Column(name = "PROJ_ID", unique = true, nullable = false )
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NO", nullable = false )
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

	@Column(name = "PROJ_LTYPE_ID", nullable = false)
	public String getProjLtypeId() {
		return this.projLtypeId;
	}

	public void setProjLtypeId(String projLtypeId) {
		this.projLtypeId = projLtypeId;
	}

	@Column(name = "PROJ_SUB_CON_STA")
	public String getProjSubConSta() {
		return this.projSubConSta;
	}

	public void setProjSubConSta(String projSubConSta) {
		this.projSubConSta = projSubConSta;
	}

	@Column(name = "PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return this.projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}

	@Column(name = "CUST_NO")
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "CUST_NAME")
	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Column(name = "CUST_CONTACT")
	public String getCustContact() {
		return this.custContact;
	}

	public void setCustContact(String custContact) {
		this.custContact = custContact;
	}

	@Column(name = "CUST_PHONE")
	public String getCustPhone() {
		return this.custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return this.projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	@Column(name = "PROJ_LATITUDE")
	public String getProjLatitude() {
		return this.projLatitude;
	}

	public void setProjLatitude(String projLatitude) {
		this.projLatitude = projLatitude;
	}

	@Column(name = "PROJ_LONGITUDE")
	public String getProjLongitude() {
		return this.projLongitude;
	}

	public void setProjLongitude(String projLongitude) {
		this.projLongitude = projLongitude;
	}

	@Column(name = "PROJ_INFO_FLAG")
	public String getProjInfoFlag() {
		return this.projInfoFlag;
	}

	public void setProjInfoFlag(String projInfoFlag) {
		this.projInfoFlag = projInfoFlag;
	}

	@Column(name = "PROJ_SOURCE")
	public String getProjSource() {
		return this.projSource;
	}

	public void setProjSource(String projSource) {
		this.projSource = projSource;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ACCEPT_DATE")
	public Date getAcceptDate() {
		return this.acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	@Column(name = "PROJ_STATUS_ID")
	public String getProjStatusId() {
		return this.projStatusId;
	}

	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
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

	@Column(name = "DESIGNER_ID")
	public String getDesignerId() {
		return this.designerId;
	}

	public void setDesignerId(String designerId) {
		this.designerId = designerId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DU_COMPLETE_DATE")
	public Date getDuCompleteDate() {
		return this.duCompleteDate;
	}

	public void setDuCompleteDate(Date duCompleteDate) {
		this.duCompleteDate = duCompleteDate;
	}


	@Column(name = "BUDGET_TOTAL_COST" )
	public BigDecimal getBudgetTotalCost() {
		return this.budgetTotalCost;
	}

	public void setBudgetTotalCost(BigDecimal budgetTotalCost) {
		this.budgetTotalCost = budgetTotalCost;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BUDGET_DATE")
	public Date getBudgetDate() {
		return this.budgetDate;
	}

	public void setBudgetDate(Date budgetDate) {
		this.budgetDate = budgetDate;
	}

	@Column(name = "BUDGETER")
	public String getBudgeter() {
		return this.budgeter;
	}

	public void setBudgeter(String budgeter) {
		this.budgeter = budgeter;
	}

	@Column(name = "CONFIRM_TOTAL_COST" )
	public BigDecimal getConfirmTotalCost() {
		return this.confirmTotalCost;
	}

	public void setConfirmTotalCost(BigDecimal confirmTotalCost) {
		this.confirmTotalCost = confirmTotalCost;
	}

	@Column(name = "COST_MEMBER")
	public String getCostMember() {
		return this.costMember;
	}

	public void setCostMember(String costMember) {
		this.costMember = costMember;
	}

	@Column(name = "CONTRACT_AMOUNT" )
	public BigDecimal getContractAmount() {
		return this.contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	@Column(name = "CONTRACT_PRICING_TYPE")
	public String getContractPricingType() {
		return this.contractPricingType;
	}

	public void setContractPricingType(String contractPricingType) {
		this.contractPricingType = contractPricingType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SIGN_DATE")
	public Date getSignDate() {
		return this.signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	@Column(name = "OPERATOR")
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ACCEPTANCE_DATE")
	public Date getAcceptanceDate() {
		return this.acceptanceDate;
	}

	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	@Column(name = "ACCEPTANCE_RESULT")
	public String getAcceptanceResult() {
		return this.acceptanceResult;
	}

	public void setAcceptanceResult(String acceptanceResult) {
		this.acceptanceResult = acceptanceResult;
	}

	@Column(name = "ACCEPTANCE_DIRECTOR")
	public String getAcceptanceDirector() {
		return this.acceptanceDirector;
	}

	public void setAcceptanceDirector(String acceptanceDirector) {
		this.acceptanceDirector = acceptanceDirector;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SETTLEMENT_DATE")
	public Date getSettlementDate() {
		return this.settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	@Column(name = "SETTLEMENTER")
	public String getSettlementer() {
		return this.settlementer;
	}

	public void setSettlementer(String settlementer) {
		this.settlementer = settlementer;
	}

	@Column(name = "SURVEYER_ID")
	public String getSurveyerId() {
		return surveyerId;
	}

	public void setSurveyerId(String surveyerId) {
		this.surveyerId = surveyerId;
	}

	@Column(name = "CHANGE_REASON")
	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "COST_REMARK")
	public String getCostRemark() {
		return costRemark;
	}

	public void setCostRemark(String costRemark) {
		this.costRemark = costRemark;
	}

	@Column(name = "AREA")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	@Transient
	public String getChargeBackReason() {
		for(BackReasonEnum e: BackReasonEnum.values()) {
	   		if(e.getValue().equals(this.backReason)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setChargeBackReason(String chargeBackReason) {
		this.chargeBackReason = chargeBackReason;
	}

	@Transient
	public String getAreaDes() {
		for(AreaEnum e: AreaEnum.values()) {
	   		if(e.getValue().equals(this.area)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setAreaDes(String areaDes) {
		this.areaDes = areaDes;
	}
	
	@Transient
	public List<ScaleDetail> getScaleDetails() {
		return scaleDetails;
	}

	public void setScaleDetails(List<ScaleDetail> scaleDetails) {
		this.scaleDetails = scaleDetails;
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
	public String getPaNo() {
		return paNo;
	}

	public void setPaNo(String paNo) {
		this.paNo = paNo;
	}
	@Transient
	public Boolean getOverdue() {
		return overdue;
	}

	public void setOverdue(Boolean overdue) {
		this.overdue = overdue;
	}

	@Column(name = "DU_ID")
	public String getDuId() {
		return duId;
	}

	public void setDuId(String duId) {
		this.duId = duId;
	}

	@Column(name = "BUDGETER_ID")
	public String getBudgeterId() {
		return budgeterId;
	}

	public void setBudgeterId(String budgeterId) {
		this.budgeterId = budgeterId;
	}

	@Column(name = "CUST_ID")
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Column(name = "DEDUCTIBLE")
	public String getDeductible() {
		return deductible;
	}

	public void setDeductible(String deductible) {
		this.deductible = deductible;
	}

	@Column(name = "INSIDE_PROJ_NO")
	public String getInsideProjNo() {
		return insideProjNo;
	}

	public void setInsideProjNo(String insideProjNo) {
		this.insideProjNo = insideProjNo;
	}
	@Column(name = "BACK_REASON")
	public String getBackReason() {
		return backReason;
	}

	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "COMPLETED_DATE")
	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AFFIRM_COST_DATE")
	public Date getAffirmCostDate() {
		return affirmCostDate;
	}

	public void setAffirmCostDate(Date affirmCostDate) {
		this.affirmCostDate = affirmCostDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FINISHED_DATE")
	public Date getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}
	@Column(name = "SUBMIT_AMOUNT")
	public BigDecimal getSubmitAmount() {
		return submitAmount;
	}

	public void setSubmitAmount(BigDecimal submitAmount) {
		this.submitAmount = submitAmount;
	}
	@Column(name = "FIRST_SUBMIT_AMOUNT")
	public BigDecimal getFirstSubmitAmount() {
		return firstSubmitAmount;
	}

	public void setFirstSubmitAmount(BigDecimal firstSubmitAmount) {
		this.firstSubmitAmount = firstSubmitAmount;
	}

    @Column(name="SU_NAME")
	public String getSuName() {
		return suName;
	}


	public void setSuName(String suName) {
		this.suName = suName;
	}
	@Transient
	public String getManagementOffice() {
		return managementOffice;
	}

	public void setManagementOffice(String managementOffice) {
		this.managementOffice = managementOffice;
	}
    @Transient
	public Integer getOverDay() {
		return overDay;
	}

	public void setOverDay(Integer overDay) {
		this.overDay = overDay;
	}

	@Transient
	public String getConNo() {
		return conNo;
	}

	public void setConNo(String conNo) {
		this.conNo = conNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BACK_DATE")
	public Date getBackDate() {
		return backDate;
	}

	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}

	@Column(name = "TOTAL_PROGRESS")
	public String getTotalProgress() {
		return totalProgress;
	}

	public void setTotalProgress(String totalProgress) {
		this.totalProgress = totalProgress;
	}

	@Column(name = "SURVEY_FEED_BACK")
	public String getSurveyFeedBack() {
		return surveyFeedBack;
	}

	public void setSurveyFeedBack(String surveyFeedBack) {
		this.surveyFeedBack = surveyFeedBack;
	}

	@Transient
	public String getConAgent() {
		return conAgent;
	}

	public void setConAgent(String conAgent) {
		this.conAgent = conAgent;
	}

	@Column(name="DESIGN_STATION")
	public String getDesignStation() {
		return designStation;
	}

	public void setDesignStation(String designStation) {
		this.designStation = designStation;
	}

	@Column(name="DESIGN_STATION_ID")
	public String getDesignStationId() {
		return designStationId;
	}

	public void setDesignStationId(String designStationId) {
		this.designStationId = designStationId;
	}


	@Column(name="DESIGN_STATION_DIRECTOR")
	public String getDesignStationDirector() {
		return designStationDirector;
	}

	public void setDesignStationDirector(String designStationDirector) {
		this.designStationDirector = designStationDirector;
	}

	@Transient
	public List<Signature> getSigns() {
		return signs;
	}

	public void setSigns(List<Signature> signs) {
		this.signs = signs;
	}
	@Column(name="SIGN_BACK")
	public String getSignBack() {
		return signBack;
	}

	public void setSignBack(String signBack) {
		this.signBack = signBack;
	}

	@Column(name="BACK_INFORM")
	public Boolean getBackInform() {
		return backInform;
	}

	public void setBackInform(Boolean backInform) {
		this.backInform = backInform;
	}

	/*@Column(name="DISPATCH_SURVEYER")
	public String getDispatchSurveyer() {
		return dispatchSurveyer;
	}

	public void setDispatchSurveyer(String dispatchSurveyer) {
		this.dispatchSurveyer = dispatchSurveyer;
	}*/

	@Column(name="CU_NAME")
	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	@Transient
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	@Transient
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Transient
	public BigDecimal getInspectionCost() {
		return inspectionCost;
	}

	public void setInspectionCost(BigDecimal inspectionCost) {
		this.inspectionCost = inspectionCost;
	}

	@Transient
	public BigDecimal getSuCost() {
		return suCost;
	}

	public void setSuCost(BigDecimal suCost) {
		this.suCost = suCost;
	}

	@Transient
	public BigDecimal getStoreCost() {
		return storeCost;
	}

	public void setStoreCost(BigDecimal storeCost) {
		this.storeCost = storeCost;
	}

	@Column(name="COST_MANAGER")
	public String getCostManager() {
		return costManager;
	}

	public void setCostManager(String costManager) {
		this.costManager = costManager;
	}

	@Column(name="SETTLEMENTER_ID")
	public String getSettlementerId() {
		return settlementerId;
	}

	public void setSettlementerId(String settlementerId) {
		this.settlementerId = settlementerId;
	}

	@Column(name="COST_MANGEER_ID")
	public String getCostMangeerId() {
		return costMangeerId;
	}


	public void setCostMangeerId(String costMangeerId) {
		this.costMangeerId = costMangeerId;
	}

	@Column(name="IS_MODIFY")
	public String getIsModify() {
		return isModify;
	}

	public void setIsModify(String isModify) {
		this.isModify = isModify;
	}

	@Column(name="BUILDER")
	public String getBudiler() {
		return budiler;
	}

	public void setBudiler(String budiler) {
		this.budiler = budiler;
	}

	@Transient
	public String getContractProjType() {
		return contractProjType;
	}


	@Column(name="CU_PM")
	public String getCuPm() {
		return cuPm;
	}

	public void setCuPm(String cuPm) {
		this.cuPm = cuPm;
	}

    @Transient
	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}


	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Column(name = "CORP_NAME")
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	@Column(name = "CONTRIBUTION_MODE")
	public String getContributionMode() {
		return contributionMode;
	}

	public void setContributionMode(String contributionMode) {
		this.contributionMode = contributionMode;
	}

	@Column(name = "DEPT_ID")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}


	@Column(name = "DEPT_TRANSFER")
	public String getDeptTransfer() {
		return deptTransfer;
	}
	public void setDeptTransfer(String deptTransfer) {
		this.deptTransfer = deptTransfer;
	}


	@Column(name = "TENANT_ID")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Column(name = "PROJECT_TYPE")
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(name = "PROJECT_TYPE_DES")
	public String getProjectTypeDes() {
		return projectTypeDes;
	}

	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}

	@Column(name = "CONTRIBUTION_MODE_DES")
	public String getContributionModeDes() {
		return contributionModeDes;
	}

	public void setContributionModeDes(String contributionModeDes) {
		this.contributionModeDes = contributionModeDes;
	}

	@Column(name = "DEPT_NAME")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Transient
	public String getTechnicalSuggestion() {
		return technicalSuggestion;
	}

	public void setTechnicalSuggestion(String technicalSuggestion) {
		this.technicalSuggestion = technicalSuggestion;
	}

	@Transient
	public String getConnectGasDes() {
		return connectGasDes;
	}

	public void setConnectGasDes(String connectGasDes) {
		this.connectGasDes = connectGasDes;
	}

	@Column(name = "BUDGETER_AUDIT")
	public String getBudgeterAudit() {
		return budgeterAudit;
	}

	public void setBudgeterAudit(String budgeterAudit) {
		this.budgeterAudit = budgeterAudit;
	}

	@Column(name = "BUDGETER_AUDIT_ID")
	public String getBudgeterAuditId() {
		return budgeterAuditId;
	}

	public void setBudgeterAuditId(String budgeterAuditId) {
		this.budgeterAuditId = budgeterAuditId;
	}

	@Transient
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	@Transient
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Transient
	public String getAuditStatusDes() {
		for(AuditResultEnum e: AuditResultEnum.values()) {
	   		if(e.getValue().equals(this.auditStatus)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setAuditStatusDes(String auditStatusDes) {
		this.auditStatusDes = auditStatusDes;
	}

	@Transient
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	@Transient
	public String getDaId() {
		return daId;
	}

	public void setDaId(String daId) {
		this.daId = daId;
	}

	@Column(name="COST_MEMBER_ID")
	public String getCostMemberId() {
		return costMemberId;
	}

	public void setCostMemberId(String costMemberId) {
		this.costMemberId = costMemberId;
	}

	@Column(name="PLAN_ID")
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	@Column(name="IS_FUNDS")
	public String getIsFunds() {
		return isFunds;
	}

	public void setIsFunds(String isFunds) {
		this.isFunds = isFunds;
	}

	@Transient
	public String getProjCostAuditType() {
		return projCostAuditType;
	}

	public void setProjCostAuditType(String projCostAuditType) {
		this.projCostAuditType = projCostAuditType;
	}
	
	@Column(name="ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Transient
	public String getScNo() {
		return scNo;
	}

	public void setScNo(String scNo) {
		this.scNo = scNo;
	}
	
	@Transient
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
	@Column(name="ACCEPT_TYPE")
	public String getAcceptType() {
		return acceptType;
	}

	public void setAcceptType(String acceptType) {
		this.acceptType = acceptType;
	}

	@Column(name="TOTAL_INVESTMENT")
	public BigDecimal getTotalInvestment() {
		return totalInvestment;
	}

	public void setTotalInvestment(BigDecimal totalInvestment) {
		this.totalInvestment = totalInvestment;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="PROTOCOL_STARTING_DATE")
	public Date getProtocolStartingDate() {
		return protocolStartingDate;
	}

	public void setProtocolStartingDate(Date protocolStartingDate) {
		this.protocolStartingDate = protocolStartingDate;
	}

	@Column(name="PROJECT_DURATION")
	public String getProjectDuration() {
		return projectDuration;
	}

	public void setProjectDuration(String projectDuration) {
		this.projectDuration = projectDuration;
	}

	@Column(name="ACCEPT_REASON")
	public String getAcceptReason() {
		return acceptReason;
	}

	public void setAcceptReason(String acceptReason) {
		this.acceptReason = acceptReason;
	}

	@Column(name="ACCEPT_DATA")
	public String getAcceptData() {
		return acceptData;
	}

	public void setAcceptData(String acceptData) {
		this.acceptData = acceptData;
	}

	@Column(name="EXPLORATION_UNIT")
	public String getExplorationUnit() {
		return explorationUnit;
	}

	public void setExplorationUnit(String explorationUnit) {
		this.explorationUnit = explorationUnit;
	}

	@Column(name="EU_NO")
	public String getEuNo() {
		return euNo;
	}

	public void setEuNo(String euNo) {
		this.euNo = euNo;
	}

	@Column(name="EU_AMOUNT")
	public BigDecimal getEuAmount() {
		return euAmount;
	}

	public void setEuAmount(BigDecimal euAmount) {
		this.euAmount = euAmount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="EU_DATE")
	public Date getEuDate() {
		return euDate;
	}

	public void setEuDate(Date euDate) {
		this.euDate = euDate;
	}

	@Column(name="EU_DEADLINE")
	public String getEuDeadline() {
		return euDeadline;
	}

	public void setEuDeadline(String euDeadline) {
		this.euDeadline = euDeadline;
	}

	@Column(name="PROCEDURES_DATA")
	public String getProceduresData() {
		return proceduresData;
	}

	public void setProceduresData(String proceduresData) {
		this.proceduresData = proceduresData;
	}

	@Column(name="CONTRUCTION_DATA")
	public String getContructionData() {
		return contructionData;
	}

	public void setContructionData(String contructionData) {
		this.contructionData = contructionData;
	}

	@Column(name="SETTLEMENT_DATA")
	public String getSettlementData() {
		return settlementData;
	}

	public void setSettlementData(String settlementData) {
		this.settlementData = settlementData;
	}

	@Column(name="FINAL_ACCOUNT_DATA")
	public String getFinalAccountData() {
		return finalAccountData;
	}

	public void setFinalAccountData(String finalAccountData) {
		this.finalAccountData = finalAccountData;
	}

	@Column(name="BACK_REMARKS")
	public String getBackRemarks() {
		return backRemarks;
	}

	public void setBackRemarks(String backRemarks) {
		this.backRemarks = backRemarks;
	}

	@Transient
	public BigDecimal getMaterialCost() {
		return materialCost;
	}

	public void setMaterialCost(BigDecimal materialCost) {
		this.materialCost = materialCost;
	}

	@Transient
	public BigDecimal getDesignCost() {
		return designCost;
	}

	public void setDesignCost(BigDecimal designCost) {
		this.designCost = designCost;
	}

	@Transient
	public BigDecimal getUnforeseenCost() {
		return unforeseenCost;
	}

	public void setUnforeseenCost(BigDecimal unforeseenCost) {
		this.unforeseenCost = unforeseenCost;
	}
	
	@Transient
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Transient
	public String getDeptDivide() {
		return deptDivide;
	}

	public void setDeptDivide(String deptDivide) {
		this.deptDivide = deptDivide;
	}

	@Column(name="IS_INTELLIGENT_METER")
	public String getIsIntelligentMeter() {
		return isIntelligentMeter;
	}

	public void setIsIntelligentMeter(String isIntelligentMeter) {
		this.isIntelligentMeter = isIntelligentMeter;
	}

	@Transient
	public Date getOcoDate() {
		return ocoDate;
	}

	public void setOcoDate(Date ocoDate) {
		this.ocoDate = ocoDate;
	}

	@Transient
	public String getAcquisitionDays() {
		return acquisitionDays;
	}

	public void setAcquisitionDays(String acquisitionDays) {
		this.acquisitionDays = acquisitionDays;
	}
	
	@Transient
	public String getDesignDrawingNo() {
		return designDrawingNo;
	}

	public void setDesignDrawingNo(String designDrawingNo) {
		this.designDrawingNo = designDrawingNo;
	}

	@Column(name="DU_PL_COMPLETE_DATE")
	public Date getDuPlCompleteDate() {
		return duPlCompleteDate;
	}

	public void setDuPlCompleteDate(Date duPlCompleteDate) {
		this.duPlCompleteDate = duPlCompleteDate;
	}

	@Transient
	public Date getBudgeterAuditDate() {
		return budgeterAuditDate;
	}

	public void setBudgeterAuditDate(Date budgeterAuditDate) {
		this.budgeterAuditDate = budgeterAuditDate;
	}

	@Transient
	public String getAcquisitionPlDays() {
		return acquisitionPlDays;
	}

	public void setAcquisitionPlDays(String acquisitionPlDays) {
		this.acquisitionPlDays = acquisitionPlDays;
	}

	@Transient
	public String getGasTimes() {
		return gasTimes;
	}

	public void setGasTimes(String gasTimes) {
		this.gasTimes = gasTimes;
	}

	@Transient
	public String getReduceGasTimes() {
		return reduceGasTimes;
	}

	public void setReduceGasTimes(String reduceGasTimes) {
		this.reduceGasTimes = reduceGasTimes;
	}
	
	@Transient
	public Date getAcStartDate() {
		return acStartDate;
	}

	public void setAcStartDate(Date acStartDate) {
		this.acStartDate = acStartDate;
	}

	@Transient
	public String getPlanCompleteDate() {
		return planCompleteDate;
	}

	public void setPlanCompleteDate(String planCompleteDate) {
		this.planCompleteDate = planCompleteDate;
	}

	@Transient
	public String getSuBudgeter() {
		return suBudgeter;
	}

	public void setSuBudgeter(String suBudgeter) {
		this.suBudgeter = suBudgeter;
	}

	@Transient
	public Date getSuBudgeterDate() {
		return suBudgeterDate;
	}

	public void setSuBudgeterDate(Date suBudgeterDate) {
		this.suBudgeterDate = suBudgeterDate;
	}
	
	@Column(name = "MANAGEMENT_QAE")
	public String getManagementQae() {
		return managementQae;
	}

	public void setManagementQae(String managementQae) {
		this.managementQae = managementQae;
	}
	
	@Column(name="MANAGEMENT_WACF")
	public String getManagementWacf() {
		return managementWacf;
	}

	public void setManagementWacf(String managementWacf) {
		this.managementWacf = managementWacf;
	}

	@Column(name="DOCUMENTER")
	public String getDocumenter() {
		return documenter;
	}

	public void setDocumenter(String documenter) {
		this.documenter = documenter;
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

	@Column(name="SAFTY_OFFICER")
	public String getSaftyOfficer() {
		return saftyOfficer;
	}

	public void setSaftyOfficer(String saftyOfficer) {
		this.saftyOfficer = saftyOfficer;
	}
	
	@Column(name="TECHNICIAN")
	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}

	@Column(name="SU_CSE")
	public String getSuCse() {
		return suCse;
	}

	public void setSuCse(String suCse) {
		this.suCse = suCse;
	}
	
    @Column(name="SU_JGJ")
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

	@Transient
	public String getPaId() {
		return paId;
	}

	public void setPaId(String paId) {
		this.paId = paId;
	}
	@Transient
	public BigDecimal getBudgetCost() {
		return budgetCost;
	}

	public void setBudgetCost(BigDecimal budgetCost) {
		this.budgetCost = budgetCost;
	}
	@Transient
	public BigDecimal getBudgetSettlementCost() {
		return budgetSettlementCost;
	}

	public void setBudgetSettlementCost(BigDecimal budgetSettlementCost) {
		this.budgetSettlementCost = budgetSettlementCost;
	}
	@Transient
	public BigDecimal getEndSettlementCost() {
		return endSettlementCost;
	}

	public void setEndSettlementCost(BigDecimal endSettlementCost) {
		this.endSettlementCost = endSettlementCost;
	}
	@Transient
	public BigDecimal getSendSettlementCost() {
		return sendSettlementCost;
	}

	public void setSendSettlementCost(BigDecimal sendSettlementCost) {
		this.sendSettlementCost = sendSettlementCost;
	}
	@Transient
	public BigDecimal getGovBudgetCost() {
		return govBudgetCost;
	}

	public void setGovBudgetCost(BigDecimal govBudgetCost) {
		this.govBudgetCost = govBudgetCost;
	}
	@Transient
	public BigDecimal getGovSettlementCost() {
		return govSettlementCost;
	}

	public void setGovSettlementCost(BigDecimal govSettlementCost) {
		this.govSettlementCost = govSettlementCost;
	}
	
	@Transient
	public String getIsSpecialSign() {
		return isSpecialSign;
	}

	public void setIsSpecialSign(String isSpecialSign) {
		this.isSpecialSign = isSpecialSign;
	}
	@Transient
	public Date getJointAcceptanceDate() {
		return jointAcceptanceDate;
	}

	public void setJointAcceptanceDate(Date jointAcceptanceDate) {
		this.jointAcceptanceDate = jointAcceptanceDate;
	}

	@Column(name="MATERIAL_FLAG")
	public String getMaterialFlag() {
		return materialFlag;
	}

	public void setMaterialFlag(String materialFlag) {
		this.materialFlag = materialFlag;
	}


	@Column(name="PROJ_CHANGES")
	public String getProjChanges() {
		return projChanges;
	}

	public void setProjChanges(String projChanges) {
		this.projChanges = projChanges;
	}

	@Transient
	public String getProjChangesDes() {
		return projChangesDes;
	}

	public void setProjChangesDes(String projChangesDes) {
		this.projChangesDes = projChangesDes;
	}

	@Transient
	public String getProjConStatusDes() {
		return projConStatusDes;
	}

	public void setProjConStatusDes(String projConStatusDes) {
		this.projConStatusDes = projConStatusDes;
	}

	@Transient
	public String getProjConStatus() {
		return projConStatus;
	}

	public void setProjConStatus(String projConStatus) {
		this.projConStatus = projConStatus;
	}

	@Transient
	public WorkDayDto getWorkDayDto() {
		return workDayDto;
	}

	public void setWorkDayDto(WorkDayDto workDayDto) {
		this.workDayDto = workDayDto;
	}

	@Column(name="IS_BIDDING")
	public String getIsBidding() {
		return isBidding;
	}

	public void setIsBidding(String isBidding) {
		this.isBidding = isBidding;
	}
	@Column(name="IS_INDOOR")
	public String getIsIndoor() {
		return isIndoor;
	}

	public void setIsIndoor(String isIndoor) {
		this.isIndoor = isIndoor;
	}

	@Transient
	public String getProjSourceDes() {
		for(ProjSourceEnum e: ProjSourceEnum.values()) {
	   		if(e.getValue().equals(this.projSource)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setProjSourceDes(String projSourceDes) {
		this.projSourceDes = projSourceDes;
	}

	@Transient
	public String getProjCostConfig() {
		return projCostConfig;
	}

	public void setProjCostConfig(String projCostConfig) {
		this.projCostConfig = projCostConfig;
	}
	
	@Column(name="ACCEPTER")
	public String getAccepter() {
		return accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}
	
	@Column(name="ACCEPTER_ID")
	public String getAccepterId() {
		return accepterId;
	}

	public void setAccepterId(String accepterId) {
		this.accepterId = accepterId;
	}
	
    @Transient
	public String getIsBack() {
		return isBack;
	}

	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}

	@Column(name="SURVEY_BUILDER_ID")
	public String getSurveyBuilderId() {
		return surveyBuilderId;
	}

	public void setSurveyBuilderId(String surveyBuilderId) {
		this.surveyBuilderId = surveyBuilderId;
	}

	@Column(name="SURVEY_BUILDER")
	public String getSurveyBuilder() {
		return surveyBuilder;
	}

	public void setSurveyBuilder(String surveyBuilder) {
		this.surveyBuilder = surveyBuilder;
	}

	@Column(name="BUDGET_METHOD")
	public String getBudgetMethod() {
		return budgetMethod;
	}

	public void setBudgetMethod(String budgetMethod) {
		this.budgetMethod = budgetMethod;
	}
	@Column(name="BUDGET_METHOD_REMARK")
	public String getBudgetMethodReMark() {
		return budgetMethodReMark;
	}
	public void setBudgetMethodReMark(String budgetMethodReMark) {
		this.budgetMethodReMark = budgetMethodReMark;
	}

	@Column(name="TRANSFE_FLAG")
	public String getTransfeFlag() {
		return transfeFlag;
	}

	public void setTransfeFlag(String transfeFlag) {
		this.transfeFlag = transfeFlag;
	}

	@Transient
	public String getBudgetMethodDes() {
		return budgetMethodDes;
	}

	public void setBudgetMethodDes(String budgetMethodDes) {
		this.budgetMethodDes = budgetMethodDes;
	}

	@Transient
	public JointAcceptance getJointAcceptance() {
		return jointAcceptance;
	}

	public void setJointAcceptance(JointAcceptance jointAcceptance) {
		this.jointAcceptance = jointAcceptance;
	}

	@Transient
	public String getUploadFlag() {
		return uploadFlag;
	}

	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}
	
	@Transient
	public String getAccepterPhone() {
		return accepterPhone;
	}

	public void setAccepterPhone(String accepterPhone) {
		this.accepterPhone = accepterPhone;
	}

	
	@Transient
	public String getIsMeasurement() {
		return isMeasurement;
	}

	public void setIsMeasurement(String isMeasurement) {
		this.isMeasurement = isMeasurement;
	}
	@Column(name="TO_DOER")
	public String getToDoer() {
		return toDoer;
	}

	public void setToDoer(String toDoer) {
		this.toDoer = toDoer;
	}
	


	@Transient
	@Temporal(TemporalType.DATE)
	public Date getRaiseMoneyApplyDate() {
		return raiseMoneyApplyDate;
	}

	public void setRaiseMoneyApplyDate(Date raiseMoneyApplyDate) {
		this.raiseMoneyApplyDate = raiseMoneyApplyDate;
	}

	@Transient
	@Temporal(TemporalType.DATE)
	public Date getRaiseMoneyResponseDate() {
		return raiseMoneyResponseDate;
	}

	public void setRaiseMoneyResponseDate(Date raiseMoneyResponseDate) {
		this.raiseMoneyResponseDate = raiseMoneyResponseDate;
	}

	@Transient
	@Temporal(TemporalType.DATE)
	public Date getRaiseMoneyAuditDate() {
		return raiseMoneyAuditDate;
	}

	public void setRaiseMoneyAuditDate(Date raiseMoneyAuditDate) {
		this.raiseMoneyAuditDate = raiseMoneyAuditDate;
	}

	@Transient
	@Temporal(TemporalType.DATE)
	public Date getAcceptAuditDate() {
		return acceptAuditDate;
	}

	public void setAcceptAuditDate(Date acceptAuditDate) {
		this.acceptAuditDate = acceptAuditDate;
	}

	@Transient
	@Temporal(TemporalType.DATE)
	public Date getSurveyDisDate() {
		return surveyDisDate;
	}

	public void setSurveyDisDate(Date surveyDisDate) {
		this.surveyDisDate = surveyDisDate;
	}
	
	@Column(name="WHITE_MAP_REGISTER_REMARK")
	public String getWhiteMapRegisterRemark() {
		return whiteMapRegisterRemark;
	}
	
	public void setWhiteMapRegisterRemark(String whiteMapRegisterRemark) {
		this.whiteMapRegisterRemark = whiteMapRegisterRemark;
	}
	@Column(name="CONSTRUCTION_PROC_REMARK")
	public String getConstructionProcRemark() {
		return constructionProcRemark;
	}

	public void setConstructionProcRemark(String constructionProcRemark) {
		this.constructionProcRemark = constructionProcRemark;
	}
	@Column(name="MONITORING_REPORT_REMARK")
	public String getMonitoringReportRemark() {
		return monitoringReportRemark;
	}

	public void setMonitoringReportRemark(String monitoringReportRemark) {
		this.monitoringReportRemark = monitoringReportRemark;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CP_ARRIVE_DATE")
	public Date getCpArriveDate() {
		return cpArriveDate;
	}

	public void setCpArriveDate(Date cpArriveDate) {
		this.cpArriveDate = cpArriveDate;
	}

	@Transient
	public BigDecimal getArCost() {
		return arCost;
	}

	public void setArCost(BigDecimal arCost) {
		this.arCost = arCost;
	}

	@Transient
	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}

	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	@Transient
	@Temporal(TemporalType.DATE)
	public Date getConstructionProcPreDate() {
		return constructionProcPreDate;
	}

	public void setConstructionProcPreDate(Date constructionProcPreDate) {
		this.constructionProcPreDate = constructionProcPreDate;
	}

	@Transient
	public String getOrderMan() {
		return orderMan;
	}

	public void setOrderMan(String orderMan) {
		this.orderMan = orderMan;
	}

	@Transient
	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	@Override
	public String toString() {
		return "Project [planId=" + planId + ", projId=" + projId + ", projNo=" + projNo + ", insideProjNo="
				+ insideProjNo + ", projName=" + projName + ", projLtypeId=" + projLtypeId + ", projSubConSta="
				+ projSubConSta + ", projScaleDes=" + projScaleDes + ", custNo=" + custNo + ", custName=" + custName
				+ ", custId=" + custId + ", custContact=" + custContact + ", custPhone=" + custPhone + ", projAddr="
				+ projAddr + ", projLatitude=" + projLatitude + ", projLongitude=" + projLongitude + ", projInfoFlag="
				+ projInfoFlag + ", isFunds=" + isFunds + ", projSource=" + projSource + ", projSourceDes="
				+ projSourceDes + ", acceptDate=" + acceptDate + ", accepter=" + accepter + ", accepterId=" + accepterId
				+ ", accepterPhone=" + accepterPhone + ", projStatusId=" + projStatusId + ", deductible=" + deductible
				+ ", surveyDes=" + surveyDes + ", surveyDate=" + surveyDate + ", surveyer=" + surveyer + ", surveyerId="
				+ surveyerId + ", designStation=" + designStation + ", designStationId=" + designStationId
				+ ", designStationDirector=" + designStationDirector + ", duName=" + duName + ", duId=" + duId
				+ ", designer=" + designer + ", designerId=" + designerId + ", duCompleteDate=" + duCompleteDate
				+ ", technicalSuggestion=" + technicalSuggestion + ", connectGasDes=" + connectGasDes
				+ ", budgetTotalCost=" + budgetTotalCost + ", budgetDate=" + budgetDate + ", budgeter=" + budgeter
				+ ", budgeterId=" + budgeterId + ", confirmTotalCost=" + confirmTotalCost + ", costMember=" + costMember
				+ ", costMemberId=" + costMemberId + ", contractAmount=" + contractAmount + ", contractPricingType="
				+ contractPricingType + ", signDate=" + signDate + ", operator=" + operator + ", acceptanceDate="
				+ acceptanceDate + ", acceptanceResult=" + acceptanceResult + ", acceptanceDirector="
				+ acceptanceDirector + ", settlementDate=" + settlementDate + ", settlementer=" + settlementer
				+ ", settlementerId=" + settlementerId + ", changeReason=" + changeReason + ", remark=" + remark
				+ ", costRemark=" + costRemark + ", area=" + area + ", paNo=" + paNo + ", backReason=" + backReason
				+ ", backRemarks=" + backRemarks + ", startDate=" + startDate + ", completedDate=" + completedDate
				+ ", finishedDate=" + finishedDate + ", affirmCostDate=" + affirmCostDate + ", backDate=" + backDate
				+ ", submitAmount=" + submitAmount + ", firstSubmitAmount=" + firstSubmitAmount + ", totalProgress="
				+ totalProgress + ", surveyFeedBack=" + surveyFeedBack + ", projStatusDes=" + projStatusDes
				+ ", areaDes=" + areaDes + ", chargeBackReason=" + chargeBackReason + ", overdue=" + overdue
				+ ", overDay=" + overDay + ", scaleDetails=" + scaleDetails + ", mrAuditLevel=" + mrAuditLevel
				+ ", level=" + level + ", suName=" + suName + ", suId=" + suId + ", managementOffice="
				+ managementOffice + ", budiler=" + budiler + ", builderId=" + builderId + ", conNo=" + conNo
				+ ", conAgent=" + conAgent + ", signs=" + signs + ", backInform=" + backInform + ", signBack="
				+ signBack + ", cuName=" + cuName + ", pictureUrl=" + pictureUrl + ", pictureStr=" + pictureStr
				+ ", flag=" + flag + ", inspectionCost=" + inspectionCost + ", gasTimes=" + gasTimes + ", suCost="
				+ suCost + ", storeCost=" + storeCost + ", materialCost=" + materialCost + ", designCost=" + designCost
				+ ", unforeseenCost=" + unforeseenCost + ", reduceGasTimes=" + reduceGasTimes + ", costManager="
				+ costManager + ", costMangeerId=" + costMangeerId + ", isModify=" + isModify + ", contractProjType="
				+ contractProjType + ", builder=" + builder + ", scNo=" + scNo + ", corpId=" + corpId + ", corpName="
				+ corpName + ", contributionMode=" + contributionMode + ", deptId=" + deptId + ", deptTransfer="
				+ deptTransfer + ", deptName=" + deptName + ", tenantId=" + tenantId + ", orgId=" + orgId
				+ ", projectType=" + projectType + ", projectTypeDes=" + projectTypeDes + ", contributionModeDes="
				+ contributionModeDes + ", budgeterAudit=" + budgeterAudit + ", budgeterAuditId=" + budgeterAuditId
				+ ", budgeterAuditDate=" + budgeterAuditDate + ", applyDate=" + applyDate + ", note=" + note + ", daId="
				+ daId + ", auditStatus=" + auditStatus + ", auditStatusDes=" + auditStatusDes + ", projCostAuditType="
				+ projCostAuditType + ", projCostConfig=" + projCostConfig + ", contractType=" + contractType
				+ ", acceptType=" + acceptType + ", totalInvestment=" + totalInvestment + ", protocolStartingDate="
				+ protocolStartingDate + ", projectDuration=" + projectDuration + ", acceptReason=" + acceptReason
				+ ", explorationUnit=" + explorationUnit + ", euNo=" + euNo + ", euAmount=" + euAmount + ", euDate="
				+ euDate + ", euDeadline=" + euDeadline + ", acceptData=" + acceptData + ", proceduresData="
				+ proceduresData + ", contructionData=" + contructionData + ", settlementData=" + settlementData
				+ ", finalAccountData=" + finalAccountData + ", post=" + post + ", deptDivide=" + deptDivide
				+ ", ocoDate=" + ocoDate + ", acquisitionPlDays=" + acquisitionPlDays + ", acquisitionDays="
				+ acquisitionDays + ", isIntelligentMeter=" + isIntelligentMeter + ", designDrawingNo="
				+ designDrawingNo + ", duPlCompleteDate=" + duPlCompleteDate + ", acStartDate=" + acStartDate
				+ ", planCompleteDate=" + planCompleteDate + ", jointAcceptanceDate=" + jointAcceptanceDate
				+ ", projectRemark=" + projectRemark + ", suBudgeter=" + suBudgeter + ", suBudgeterDate="
				+ suBudgeterDate + ", managementQae=" + managementQae + ", managementWacf=" + managementWacf
				+ ", managementWacfId=" + managementWacfId + ", documenter=" + documenter + ", documenterId="
				+ documenterId + ", testLeader=" + testLeader + ", testLeaderId=" + testLeaderId + ", welder=" + welder
				+ ", welderId=" + welderId + ", saftyOfficer=" + saftyOfficer + ", saftyOfficerID=" + saftyOfficerID
				+ ", technician=" + technician + ", technicianId=" + technicianId + ", suCse=" + suCse + ", suCseId="
				+ suCseId + ", suJgj=" + suJgj + ", suJgjId=" + suJgjId + ", isGas=" + isGas + ", paId=" + paId
				+ ", budgetCost=" + budgetCost + ", budgetSettlementCost=" + budgetSettlementCost
				+ ", endSettlementCost=" + endSettlementCost + ", sendSettlementCost=" + sendSettlementCost
				+ ", govBudgetCost=" + govBudgetCost + ", govSettlementCost=" + govSettlementCost + ", isSpecialSign="
				+ isSpecialSign + ", materialFlag=" + materialFlag + ", isBidding=" + isBidding + ", isIndoor="
				+ isIndoor + ", projChanges=" + projChanges + ", projChangesDes=" + projChangesDes + ", workDayDto="
				+ workDayDto + ", projConStatusDes=" + projConStatusDes + ", projConStatus=" + projConStatus + ", cuId="
				+ cuId + ", cuLegalRepresentId=" + cuLegalRepresentId + ", cuLegalRepresent=" + cuLegalRepresent
				+ ", cuPmId=" + cuPmId + ", cuPm=" + cuPm + ", managementQaeId=" + managementQaeId + ", suDirector="
				+ suDirector + ", suDirectorId=" + suDirectorId + ", suRepresentative=" + suRepresentative
				+ ", suRepresentativeId=" + suRepresentativeId + ", suProfessionalId=" + suProfessionalId
				+ ", suProfessional=" + suProfessional + ", isBack=" + isBack + ", surveyBuilderId=" + surveyBuilderId
				+ ", surveyBuilder=" + surveyBuilder + ", budgetMethod=" + budgetMethod + ", budgetMethodReMark="
				+ budgetMethodReMark + ", budgetMethodDes=" + budgetMethodDes + ", transfeFlag=" + transfeFlag
				+ ", isMeasurement=" + isMeasurement + ", cpArriveDate=" + cpArriveDate + ", saveType=" + saveType
				+ ", jointAcceptance=" + jointAcceptance + ", uploadFlag=" + uploadFlag + ", toDoer=" + toDoer
				+ ", raiseMoneyApplyDate=" + raiseMoneyApplyDate + ", raiseMoneyResponseDate=" + raiseMoneyResponseDate
				+ ", raiseMoneyAuditDate=" + raiseMoneyAuditDate + ", acceptAuditDate=" + acceptAuditDate
				+ ", surveyDisDate=" + surveyDisDate + ", whiteMapRegisterRemark=" + whiteMapRegisterRemark
				+ ", constructionProcRemark=" + constructionProcRemark + ", monitoringReportRemark="
				+ monitoringReportRemark + ", constructionProcPreDate=" + constructionProcPreDate + ", instTaskNote="
				+ instTaskNote + ", meterType=" + meterType + ", orderMan=" + orderMan + ", instTaskDate="
				+ instTaskDate + ", arCost=" + arCost + ", receiveAmount=" + receiveAmount + "]";
	}
	
	
}
