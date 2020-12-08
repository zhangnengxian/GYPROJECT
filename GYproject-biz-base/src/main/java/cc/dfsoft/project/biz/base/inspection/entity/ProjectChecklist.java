package cc.dfsoft.project.biz.base.inspection.entity;

import cc.dfsoft.project.biz.base.inspection.enums.GrDiggingTypeEnum;
import cc.dfsoft.project.biz.base.inspection.enums.GrSoilTypeEnum;
import cc.dfsoft.project.biz.base.inspection.enums.InspectionClumEnum;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectCheckListFlagEnum;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ProjectChecklist entity
 */
@Entity
@Table(name = "PROJECT_CHECKLIST")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ProjectChecklist implements Serializable {

	private static final long serialVersionUID = 7155452522901890965L;
	private String pcId;				//工程报验单ID
	private String pcDesId;				//检验单类型ID
	private String projId;				//工程ID
	private String projNo;				//工程编号
	private String projName;			//工程名称
	private String projAddr;			//工程名称
	private String suName;				//监理单位
	private Clob suJgj;					//监理工程师
	private String suOpinion;			//监理单位意见
	private String constructionUnit;	//施工单位
	private Clob constructionPrincipal;	//施工负责人
	private Clob constructionQc;		//施工单位质检员
	private Clob constructionQae;		//施工单位质保师
	private Clob constructionSatety;	//施工单位安全员
	private String slPart;				//测试放线部位
	private String slBasePage;			//依据材料页数
	private String slResultPage;		//成果页数
	private String asPoint;				//测量基准点
	private Clob asPerson;				//测量人
	private String grStartStop;			//沟槽起止位置
	private String grCaliber;			//管径
	private String grDigging;			//开挖方式
	private String grDiggingDes;
	private String grSoil;				//土壤类别
	private String grSoilDes;
	private String grUpWide;			//上口宽
	private String grDownWide;			//下口宽
	private String grDepth;				//埋深
	private String dpPipeSize;			//管道规格
	private String dpNum;				//防腐数量
	private String dpReplayNum;			//补扣防腐数量
	private String dpKv;				//检测电压
	private Clob dpPrincipal;			//除锈负责人
	private Clob pPrincipal;			//防腐负责人
	private String pipeItemName;		//分项工程名称
	private String pipeLine;			//管线位置
	private String pipeNdtMethod;		//无损检验方法
	private String pipeNdtScale;		//无损检验比例
	private String peItem;				//检查项目
	private Clob safetyOfficer;			//安全员
	private String meterial;			//材质
	private String process;				//施工工序
	private Date inspectionDate;		//报验日期
	private String inspectionResult;	//检查结果
	private String inspectionClum;		//检查结论
	private String inspectionClumDes;	//检查结论
	private Date checkDate;				//检查日期
	private Date liningDate;			//测量日期
	private String targetCheck;			//靶板检查
	private Clob otherPerson;			//其他人
	private Date otherCheckDate;		//其他人检查日期
	private Date selfCheckDate;			//自检日期
	
	private String length;				//管道长度
	private Clob tester;				//监检人
	private Date testDate;				//监检日期
	private String testUnit;			//监检单位
	
	private List<Signature> sign;		//签字
	// Constructors
	
	
	private String systemCategory;		//系统类别	
    private String drawingNo;			//图号
    private Date checkoutDate;			//测试日期
    private String model;				//型号
    private String weatherCondition;	//天气情况
    private String roomTemperature;		//室温
    private String voltage;				//电压
    private String qualityStandard;		//质量标准
    private String unitMeasurement;		//计量单位
    
    private String testResult;			//测试结果
    private Clob testControler;			//测试人员
    
    private String groundingType;		//接地种类
    private String underLinearType;		//引下线型式
    private String layoutDiagram;		//接地极、测试点位置布置简图  也作为简图名称
	private String menuDes; 			//菜单描述--简图显示
	private String drawUrl;          	//简图路径--简图显示
	private String menuId;				//菜单ID

    private Clob debugger;				//调试人员
    private Date debugDate;				//调试日期
    private String debugUnit;			//调试单位
    private String designUnit;			//设计单位
    private String debugDes;			//系统调试情况
    
    private String remainingProblems;	//遗留问题
    private Clob technician;			//技术员
	
    private BigDecimal standardItem;		//保证项目达标项
    private BigDecimal notMeet;				//保证项目未达标项
    private BigDecimal basicItem;			//基本项目检查项
    private BigDecimal excellentItem;		//优良项
    private String excellentRate;			//优良率
    private BigDecimal actualMeasurementPoint;//允许偏差实测点
    private BigDecimal qualifiedPoint;		//合格点
    private String qualifiedRate;			//合格率
    
    private String qrCodePath;//二维码生成路径
    
    private String corpName;	//燃气公司
    
    /**焊条领用增加字段*/
   
    private Clob recorder;					// 记录人签字
    private Date recorderDate;				//记录日期
    private Clob reviewer;					//复核人签字
    private Date reviewerDate;				//复核日期
    
    /**焊条烘烤增加字段*/
    private String bakingEquipment;         //烘烤设备
    private String holdingEquipment;         //保温设备
    /**管道安装增加字段*/
    private Clob welder;		//焊接人员
    private Date welderSignDate;		//焊接人员签字日期
    private Clob technicalLeader;		//技术负责人
    private Date tlSignDate;		//技术负责人签字日期
    private Clob auditOfficer;		//审核负责人
    private Date aoSignDate;		//审核负责人签字日期
    
    /**钢管焊接增加字段*/
    private Clob checker;		//检查人
    private Date checkerSignDate; //检查人签字日期
    private Clob projectLeader;		//项目负责人
    private Date plSignDate;		//项目负责人签字日期
    /**防腐记录增加字段*/
    private String preservativeType;	//防腐记录类型
    /**防腐检查增加字段*/
    private Clob fieldsRepresent;		//现场代表
    private Date fieldsRepresentDate;	//现场代表签字日期
    //private Clob suJgj;			//现场监理
    private Date suJgjSignDate;			//现场监理签字日期
   // private Clob constructionQc;	//质检人
    private Date cuQcSignDate;		//质检人签字日期
    private Clob builder;			//施工员
    private Date builderSignDate; //施工人员签字日期
    private String custName;	 // 建设单位
    
    /**埋地检查增加字段*/
    private Date cupSignDate;	//施工负责人签字日期CUP_SIGN_DATE
    private Date cuDate;		//施工日期 CU_DATE
    /**阀门试验增加字段*/
    private Clob testLeader;	//试验班(组)长 TEST_LEADER
    private Date testlSignDate;	//试验班(组)长 TESTL_SIGN_DATE
    
    /**安装汇总增加字段*/
    private String completeUnitName;	//`COMPLETE_UNIT_NAME` varchar(50) DEFAULT NULL COMMENT '交工单元名称',
    private String completeUnitNo;		//`COMPLETE_UNIT_NO` varchar(50) DEFAULT NULL COMMENT '交工单元编号',
    private Date installStartDate;		//`INSTALL_START_DATE` datetime DEFAULT NULL COMMENT '安装开工日期',
    private Date installCompleteDate;	//INSTALL_COMPLETE_DATE` datetime DEFAULT NULL COMMENT '安装竣工日期',
    private String pipingLevel;			//`PIPING_LEVEL` varchar(20) DEFAULT NULL COMMENT '管道级别',
    private String pipingLen;			//`PIPING_LEN` varchar(20) DEFAULT NULL COMMENT '管道长度',
    private String duAptitudeNo;		//`DU_APTITUDE_NO` varchar(50) DEFAULT NULL COMMENT '设计单位资质编号',
    private String suAptitudeNo;		//`SU_APTITUDE_NO` varchar(50) DEFAULT NULL COMMENT '监理单位资质编号',
    private String ndtUnit;				//`NDT_UNIT` varchar(100) DEFAULT NULL COMMENT '无损检测单位',
    private String ndtAptitudeNo;		//`NDT_APTITUDE_NO` varchar(50) DEFAULT NULL COMMENT '无损检测单位资质编号',
    private String installMonitorUnit;	//`INSTALL_MONITOR_UNIT` varchar(100) DEFAULT NULL COMMENT '安装监检单位',
    private String useUnit;				//`USE_UNIT` varchar(100) DEFAULT NULL COMMENT '使用单位',
    private String installUnit;			//`INSTALL_UNIT` varchar(100) DEFAULT NULL COMMENT '安装单位',
    private Date iuSealDate;			//`IU_SEAL_DATE` datetime DEFAULT NULL COMMENT '安装单位盖章日期',
    private String licensNo;			//`LICENS_NO` varchar(50) DEFAULT NULL COMMENT '特种设备安装许可证编号',
    private Date cuQaeSignDate;			//`CUQAE_SIGN_DATE` datetime DEFAULT NULL COMMENT '质保师签字日期',
    
    /**通球记录增加字段*/
    private Clob participant;			//参与人员
    private Date participantSignDate;	//参与人员签字日期
    private Clob custRepresent;			//甲方代表
    private Date custRSignDate;			//甲方代表签字日期
    
    /**强度试验增加字段*/
    private String stPipeType;			// `ST_PIPE_TYPE` varchar(4) DEFAULT NULL COMMENT '强度试验管道类型',
    private String stMedium;			//`ST_MEDIUM` varchar(100) DEFAULT NULL COMMENT '强度试验介质',
    private String stRange;				//`ST_RANGE` varchar(50) DEFAULT NULL COMMENT '强度试验范围',
    private String stBuildingNo;		// `ST_BUILDING_NO` varchar(50) DEFAULT NULL COMMENT '强度试验楼栋号',
    private String stHouseHolds;		//`ST_HOUSE_HOLDS` varchar(50) DEFAULT NULL COMMENT '强度试验户数',
    private String stInstruction;		//`ST_HOUSE_HOLDS`char(50) DEFAULT NULL COMMENT '强度试验户数',
    private Date soSignDate;			//`SO_SIGN_DATE` datetime DEFAULT NULL COMMENT '安全员签字日期',		
    
    /**热熔对接增加字段*/
    private String welderIDNo;			//`WELDER_ID_NO` varchar(50) DEFAULT NULL COMMENT '焊工证号',
    private String welderName;			//  `WELDER_NAME` varchar(200) DEFAULT NULL COMMENT '焊工姓名',
    
    /**管道焊缝检查增加字段*/
    private Clob weldLeader;			//焊接责任人
    private Date weldLeaderSignDate;	//焊接人员签字日期
    private Clob qualityLeader;			//质量责任师
    private Date quLeaderSignDate;		//质量责任师签字日期
    
    /**PE管焊缝检查增加字段*/
    private String meltConnectType;		//`MELT_CONNECT_TYPE` varchar(4) DEFAULT NULL COMMENT 'PE管焊缝检查热熔电熔连接类型:1-热熔,2-电熔',
    private String peWeldSpec;			//`PE_WELD_SPEC` varchar(50) DEFAULT NULL COMMENT '焊件规格',
    private String pipeManufactor;		//`PIPE_MANUFACTOR` varchar(200) DEFAULT NULL COMMENT '管材厂家',
    
    /**吹扫记录增加字段*/
    private Clob operator;		//操作人
    private Date operatorSignDate;//操作人签字日期
    
    /**户内挂表增加字段*/
    private String ipwPatchCode;	//`IPW_PATCH_CODE` varchar(50) DEFAULT NULL COMMENT '户内挂表片区号',
    private String curtyardNo;		//`COURTYARD_NO` varchar(50) DEFAULT NULL COMMENT '庭院管号',
    private String drawBuildingNo;	//`DRAW_BUILDING_NO` varchar(50) DEFAULT NULL COMMENT '图纸楼号',
    private String protocalNo;		//`PROTOCAL_NO` varchar(50) DEFAULT NULL COMMENT '协议号',
    private Date ipwDate;			//`IPW_DATE` datetime DEFAULT NULL COMMENT '户内挂表日期',
    private String installAddr;		// `INSTALL_ADDR` varchar(200) DEFAULT NULL COMMENT '安装地址',
    private Clob businessOwner;		// `BUSINESS_OWNER` longtext COMMENT '业主签字',
    private Date boSignDate;		//`BO_SIGN_DATE` datetime DEFAULT NULL COMMENT '业主签字日期',
    
    private Clob cuPm;				//施工经理
    private Date cuPmSignDate;		//施工经理签字日期
    
    private Integer flag;			//完成标记:0-未完成,1-已完成
    private String flagDes;			//完成标记描述
    private Integer version;				//版本控制
    private String finishedDate;			//完成日期累计，以逗号隔开
    private String finishedOpr;			    //完成操作人累计，以逗号隔开
    /**焊口记录增加字段*/
   
    
    /**报验单与防腐检查一一对应*/
    private PreservativeInpect preservativeInpect;
    
    private String recordsId; 		//存储页面选择记录的ID组合,以“,”隔开
    
    private String type;		//类型
    private String accListId;	//附件id-只读
    private String pcFlag;    //删除标记

	private String level;//审核级别
	private String mrAuditLevel;//

	private String resetDate;//重置时间
	private String resetReason;//重置原因

	private String isFinishSign;//是否完成签字 1是，0否
	@Column(name = "RESET_DATE")
	public String getResetDate() {
		return resetDate;
	}

	public void setResetDate(String resetDate) {
		this.resetDate = resetDate;
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

	/** default constructor */
	public ProjectChecklist() {
	}

	@Id
	@Column(name = "PC_ID", unique = true)
	public String getPcId() {
		return this.pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name = "PC_DES_ID")
	public String getPcDesId() {
		return this.pcDesId;
	}

	public void setPcDesId(String pcDesId) {
		this.pcDesId = pcDesId;
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

	@Column(name = "SU_NAME")
	public String getSuName() {
		return this.suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}
	@Column(name = "SU_JGJ")
	public String getSuJgj() {
		return ClobUtil.ClobToString(this.suJgj);
	}

	public void setSuJgj(String suJgj) throws SerialException, SQLException {
		this.suJgj = ClobUtil.stringToClob(suJgj);
	}

	@Column(name = "SU_OPINION")
	public String getSuOpinion() {
		return this.suOpinion;
	}

	public void setSuOpinion(String suOpinion) {
		this.suOpinion = suOpinion;
	}

	@Column(name = "CONSTRUCTION_UNIT")
	public String getConstructionUnit() {
		return this.constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	@Column(name = "CONSTRUCTION_PRINCIPAL")
	public String getConstructionPrincipal() {
		return ClobUtil.ClobToString(this.constructionPrincipal);
	}

	public void setConstructionPrincipal(String constructionPrincipal) throws SerialException, SQLException {
		this.constructionPrincipal = ClobUtil.stringToClob(constructionPrincipal);
	}

	@Column(name = "CONSTRUCTION_QC")
	public String getConstructionQc() {
		return ClobUtil.ClobToString(this.constructionQc);
	}

	public void setConstructionQc(String constructionQc) throws SerialException, SQLException {
		this.constructionQc = ClobUtil.stringToClob(constructionQc);
	}

	@Column(name = "CONSTRUCTION_QAE")
	public String getConstructionQae() {
		return ClobUtil.ClobToString(this.constructionQae);
	}

	public void setConstructionQae(String constructionQae) throws SerialException, SQLException {
		this.constructionQae = ClobUtil.stringToClob(constructionQae);
	}

	@Column(name = "CONSTRUCTION_SATETY")
	public String getConstructionSatety() {
		return ClobUtil.ClobToString(this.constructionSatety);
	}

	public void setConstructionSatety(String constructionSatety) throws SerialException, SQLException {
		this.constructionSatety = ClobUtil.stringToClob(constructionSatety);
	}

	@Column(name = "SL_PART")
	public String getSlPart() {
		return this.slPart;
	}

	public void setSlPart(String slPart) {
		this.slPart = slPart;
	}

	@Column(name = "SL_BASE_PAGE")
	public String getSlBasePage() {
		return this.slBasePage;
	}

	public void setSlBasePage(String slBasePage) {
		this.slBasePage = slBasePage;
	}

	@Column(name = "SL_RESULT_PAGE")
	public String getSlResultPage() {
		return this.slResultPage;
	}

	public void setSlResultPage(String slResultPage) {
		this.slResultPage = slResultPage;
	}

	@Column(name = "AS_POINT")
	public String getAsPoint() {
		return this.asPoint;
	}

	public void setAsPoint(String asPoint) {
		this.asPoint = asPoint;
	}

	@Column(name = "AS_PERSON")
	public String getAsPerson() {
		return ClobUtil.ClobToString(this.asPerson);
	}

	public void setAsPerson(String asPerson) throws SerialException, SQLException {
		this.asPerson = ClobUtil.stringToClob(asPerson);
	}

	@Column(name = "GR_START_STOP")
	public String getGrStartStop() {
		return this.grStartStop;
	}

	public void setGrStartStop(String grStartStop) {
		this.grStartStop = grStartStop;
	}

	@Column(name = "GR_CALIBER")
	public String getGrCaliber() {
		return this.grCaliber;
	}

	public void setGrCaliber(String grCaliber) {
		this.grCaliber = grCaliber;
	}

	@Column(name = "GR_DIGGING")
	public String getGrDigging() {
		return this.grDigging;
	}

	public void setGrDigging(String grDigging) {
		this.grDigging = grDigging;
	}

	@Column(name = "GR_SOIL")
	public String getGrSoil() {
		return this.grSoil;
	}

	public void setGrSoil(String grSoil) {
		this.grSoil = grSoil;
	}

	@Column(name = "GR_UP_WIDE")
	public String getGrUpWide() {
		return this.grUpWide;
	}

	public void setGrUpWide(String grUpWide) {
		this.grUpWide = grUpWide;
	}

	@Column(name = "GR_DOWN_WIDE")
	public String getGrDownWide() {
		return this.grDownWide;
	}

	public void setGrDownWide(String grDownWide) {
		this.grDownWide = grDownWide;
	}

	@Column(name = "GR_DEPTH")
	public String getGrDepth() {
		return this.grDepth;
	}

	public void setGrDepth(String grDepth) {
		this.grDepth = grDepth;
	}

	@Column(name = "DP_PIPE_SIZE")
	public String getDpPipeSize() {
		return this.dpPipeSize;
	}

	public void setDpPipeSize(String dpPipeSize) {
		this.dpPipeSize = dpPipeSize;
	}

	@Column(name = "DP_NUM")
	public String getDpNum() {
		return this.dpNum;
	}

	public void setDpNum(String dpNum) {
		this.dpNum = dpNum;
	}

	@Column(name = "DP_REPLAY_NUM")
	public String getDpReplayNum() {
		return this.dpReplayNum;
	}

	public void setDpReplayNum(String dpReplayNum) {
		this.dpReplayNum = dpReplayNum;
	}

	@Column(name = "DP_KV")
	public String getDpKv() {
		return this.dpKv;
	}

	public void setDpKv(String dpKv) {
		this.dpKv = dpKv;
	}

	@Column(name = "DP_PRINCIPAL")
	public String getDpPrincipal() {
		return ClobUtil.ClobToString(this.dpPrincipal);
	}


	public void setDpPrincipal(String dpPrincipal) throws SerialException, SQLException {
		this.dpPrincipal = ClobUtil.stringToClob(dpPrincipal);
	}
	
	@Column(name = "P_PRINCIPAL")
	public String getpPrincipal() {
		return ClobUtil.ClobToString(this.pPrincipal);
	}


	public void setpPrincipal(String pPrincipal ) throws SerialException, SQLException {
		this.pPrincipal = ClobUtil.stringToClob(pPrincipal);
	}
	
	@Column(name = "PIPE_ITEM_NAME")
	public String getPipeItemName() {
		return this.pipeItemName;
	}

	public void setPipeItemName(String pipeItemName) {
		this.pipeItemName = pipeItemName;
	}

	@Column(name = "PIPE_LINE")
	public String getPipeLine() {
		return this.pipeLine;
	}

	public void setPipeLine(String pipeLine) {
		this.pipeLine = pipeLine;
	}

	@Column(name = "PIPE_NDT_METHOD")
	public String getPipeNdtMethod() {
		return this.pipeNdtMethod;
	}

	public void setPipeNdtMethod(String pipeNdtMethod) {
		this.pipeNdtMethod = pipeNdtMethod;
	}

	@Column(name = "PIPE_NDT_SCALE")
	public String getPipeNdtScale() {
		return this.pipeNdtScale;
	}

	public void setPipeNdtScale(String pipeNdtScale) {
		this.pipeNdtScale = pipeNdtScale;
	}

	@Column(name = "PE_ITEM")
	public String getPeItem() {
		return this.peItem;
	}

	public void setPeItem(String peItem) {
		this.peItem = peItem;
	}

	@Column(name = "SAFETY_OFFICER")
	public String getSafetyOfficer() {
		return ClobUtil.ClobToString(this.safetyOfficer);
	}

	public void setSafetyOfficer(String safetyOfficer) throws SerialException, SQLException {
		this.safetyOfficer = ClobUtil.stringToClob(safetyOfficer);
	}

	@Column(name = "METERIAL")
	public String getMeterial() {
		return this.meterial;
	}

	public void setMeterial(String meterial) {
		this.meterial = meterial;
	}

	@Column(name = "PROCESS")
	public String getProcess() {
		return this.process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INSPECTION_DATE")
	public Date getInspectionDate() {
		return this.inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	@Column(name = "INSPECTION_RESULT")
	public String getInspectionResult() {
		return this.inspectionResult;
	}

	public void setInspectionResult(String inspectionResult) {
		this.inspectionResult = inspectionResult;
	}

	@Column(name = "INSPECTION_CLUM")
	public String getInspectionClum() {
		return this.inspectionClum;
	}

	public void setInspectionClum(String inspectionClum) {
		this.inspectionClum = inspectionClum;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CHECK_DATE")
	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	@Transient
	public String getInspectionClumDes() {
		InspectionClumEnum enums=InspectionClumEnum.valueof(this.inspectionClum);
	    if(null!= enums){
		   return enums.getMessage();
	    };
	    return "";
	}


	public void setInspectionClumDes(String inspectionClumDes) {
		this.inspectionClumDes = inspectionClumDes;
	}
	@Column(name = "TARGET_CHECK")
	public String getTargetCheck() {
		return targetCheck;
	}


	public void setTargetCheck(String targetCheck) {
		this.targetCheck = targetCheck;
	}
	@Column(name = "OTHER_PERSON")
	public String getOtherPerson() {
		return ClobUtil.ClobToString(this.otherPerson);
	}


	public void setOtherPerson(String otherPerson) throws SerialException, SQLException {
		this.otherPerson = ClobUtil.stringToClob(otherPerson);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OTHER_CHECK_DATE")
	public Date getOtherCheckDate() {
		return otherCheckDate;
	}


	public void setOtherCheckDate(Date otherCheckDate) {
		this.otherCheckDate = otherCheckDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SELF_CHECK_DATE")
	public Date getSelfCheckDate() {
		return selfCheckDate;
	}


	public void setSelfCheckDate(Date selfCheckDate) {
		this.selfCheckDate = selfCheckDate;
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "LINING_DATE")
	public Date getLiningDate() {
		return liningDate;
	}

	
	public void setLiningDate(Date liningDate) {
		this.liningDate = liningDate;
	}

	@Column(name = "LENGTH")
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	@Column(name = "TESTER")
	public String getTester() {
		return ClobUtil.ClobToString(this.tester);
	}


	public void setTester(String tester) throws SerialException, SQLException{
		this.tester = ClobUtil.stringToClob(tester);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TEST_DATE")
	public Date getTestDate() {
		return testDate;
	}


	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	@Column(name = "TEST_UNIT")
	public String getTestUnit() {
		return testUnit;
	}


	public void setTestUnit(String testUnit) {
		this.testUnit = testUnit;
	}

	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return projAddr;
	}


	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	@Transient
	public String getGrDiggingDes() {
	    for(GrDiggingTypeEnum e: GrDiggingTypeEnum.values()) {
	   		if(e.getValue().equals(this.grDigging)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}


	public void setGrDiggingDes(String grDiggingDes) {
		this.grDiggingDes = grDiggingDes;
	}

	@Transient
	public String getGrSoilDes() {
	    for(GrSoilTypeEnum e: GrSoilTypeEnum.values()) {
	   		if(e.getValue().equals(this.grSoil)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}


	public void setGrSoilDes(String grSoilDes) {
		this.grSoilDes = grSoilDes;
	}

	@Transient
	public List<Signature> getSign() {
		return sign;
	}


	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}

	
    @Column(name = "SYSTEM_CATEGORY")
    public String getSystemCategory() {
        return systemCategory;
    }

    public void setSystemCategory(String systemCategory) {
        this.systemCategory = systemCategory;
    }

    
    @Column(name = "DRAWING_NO")
    public String getDrawingNo() {
        return drawingNo;
    }

    public void setDrawingNo(String drawingNo) {
        this.drawingNo = drawingNo;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CHECKOUT_DATE")
    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    
    @Column(name = "MODEL")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    
    @Column(name = "WEATHER_CONDITION")
    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    
    @Column(name = "ROOM_TEMPERATURE")
    public String getRoomTemperature() {
        return roomTemperature;
    }

    public void setRoomTemperature(String roomTemperature) {
        this.roomTemperature = roomTemperature;
    }

    
    @Column(name = "VOLTAGE")
    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    
    @Column(name = "QUALITY_STANDARD")
    public String getQualityStandard() {
        return qualityStandard;
    }

    public void setQualityStandard(String qualityStandard) {
        this.qualityStandard = qualityStandard;
    }

    
    @Column(name = "UNIT_MEASUREMENT")
    public String getUnitMeasurement() {
        return unitMeasurement;
    }

    public void setUnitMeasurement(String unitMeasurement) {
        this.unitMeasurement = unitMeasurement;
    }

    
    @Column(name = "GROUNDING_TYPE")
    public String getGroundingType() {
        return groundingType;
    }

    public void setGroundingType(String groundingType) {
        this.groundingType = groundingType;
    }

    
    @Column(name = "UNDER_LINEAR_TYPE")
    public String getUnderLinearType() {
        return underLinearType;
    }

    public void setUnderLinearType(String underLinearType) {
        this.underLinearType = underLinearType;
    }

    
    @Column(name = "LAYOUT_DIAGRAM")
    public String getLayoutDiagram() {
        return layoutDiagram;
    }

    public void setLayoutDiagram(String layoutDiagram) {
        this.layoutDiagram = layoutDiagram;
    }

    
    @Column(name = "DEBUGGER")
    public String getDebugger() {
    	return ClobUtil.ClobToString(this.debugger);
    }

    public void setDebugger(String debugger) throws SerialException, SQLException{
    	this.debugger = ClobUtil.stringToClob(debugger);
    }

    
    @Column(name = "DEBUG_UNIT")
    public String getDebugUnit() {
        return debugUnit;
    }

    public void setDebugUnit(String debugUnit) {
        this.debugUnit = debugUnit;
    }

    
    @Column(name = "DESIGN_UNIT")
    public String getDesignUnit() {
        return designUnit;
    }

    public void setDesignUnit(String designUnit) {
        this.designUnit = designUnit;
    }

    
    @Column(name = "DEBUG_DES")
    public String getDebugDes() {
        return debugDes;
    }

    public void setDebugDes(String debugDes) {
        this.debugDes = debugDes;
    }

    
    @Column(name = "REMAINING_PROBLEMS")
    public String getRemainingProblems() {
        return remainingProblems;
    }

    public void setRemainingProblems(String remainingProblems) {
        this.remainingProblems = remainingProblems;
    }

    
    @Column(name = "TECHNICIAN")
    public String getTechnician() {
    	return ClobUtil.ClobToString(this.technician);
    }

    public void setTechnician(String technician) throws SerialException, SQLException{
    	this.technician = ClobUtil.stringToClob(technician);
    }
    
    @Column(name = "TEST_RESULT")
	public String getTestResult() {
		return testResult;
	}
	
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	
	 @Column(name = "TEST_CONTROLER")
	public String  getTestControler() {
		return ClobUtil.ClobToString(this.testControler);
	}

	public void setTestControler(String testControler) throws SerialException, SQLException{
		this.testControler = ClobUtil.stringToClob(testControler);
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DEBUG_DATE")
	public Date getDebugDate() {
		return debugDate;
	}

	public void setDebugDate(Date debugDate) {
		this.debugDate = debugDate;
	}
	@Transient
	public String getMenuDes() {
		return menuDes;
	}

	public void setMenuDes(String menuDes) {
		this.menuDes = menuDes;
	}

	@Transient
	public String getDrawUrl() {
		return drawUrl;
	}
	
	public void setDrawUrl(String drawUrl) {
		this.drawUrl = drawUrl;
	}
	
	@Column(name = "STANDARD_ITEM")
	public BigDecimal getStandardItem() {
		return standardItem;
	}
	
	public void setStandardItem(BigDecimal standardItem) {
		this.standardItem = standardItem;
	}
	
	@Column(name = "NOT_MEET")
	public BigDecimal getNotMeet() {
		return notMeet;
	}
	
	public void setNotMeet(BigDecimal notMeet) {
		this.notMeet = notMeet;
	}
	
	@Column(name = "BASIC_ITEM")
	public BigDecimal getBasicItem() {
		return basicItem;
	}

	public void setBasicItem(BigDecimal basicItem) {
		this.basicItem = basicItem;
	}
	
	@Column(name = "EXCELLENT_ITEM")
	public BigDecimal getExcellentItem() {
		return excellentItem;
	}

	public void setExcellentItem(BigDecimal excellentItem) {
		this.excellentItem = excellentItem;
	}
	
	@Column(name = "EXCELLENT_RATE")
	public String getExcellentRate() {
		return excellentRate;
	}
	
	
	public void setExcellentRate(String excellentRate) {
		this.excellentRate = excellentRate;
	}
	
	@Column(name = "ACTUAL_MEASUREMENT_POINT")
	public BigDecimal getActualMeasurementPoint() {
		return actualMeasurementPoint;
	}

	public void setActualMeasurementPoint(BigDecimal actualMeasurementPoint) {
		this.actualMeasurementPoint = actualMeasurementPoint;
	}
	
	@Column(name = "QUALIFIED_POINT")
	public BigDecimal getQualifiedPoint() {
		return qualifiedPoint;
	}

	public void setQualifiedPoint(BigDecimal qualifiedPoint) {
		this.qualifiedPoint = qualifiedPoint;
	}
	
	@Column(name = "QUALIFIED_RATE")
	public String getQualifiedRate() {
		return qualifiedRate;
	}

	public void setQualifiedRate(String qualifiedRate) {
		this.qualifiedRate = qualifiedRate;
	}
	
	@Transient
	public String getQrCodePath() {
		return qrCodePath;
	}

	public void setQrCodePath(String qrCodePath) {
		this.qrCodePath = qrCodePath;
	}

	@Column(name = "RECORDER")
	public String getRecorder() {
		return ClobUtil.ClobToString(this.recorder);
	}

	public void setRecorder(String recorder) throws SerialException, SQLException {
		this.recorder = ClobUtil.stringToClob(recorder);
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "RECORDER_DATE")
	public Date getRecorderDate() {
		return recorderDate;
	}

	public void setRecorderDate(Date recorderDate) {
		this.recorderDate = recorderDate;
	}

	@Column(name = "REVIEWER")
	public String getReviewer() {
		return ClobUtil.ClobToString(this.reviewer);
	}

	public void setReviewer(String reviewer) throws SerialException, SQLException {
		this.reviewer = ClobUtil.stringToClob(reviewer);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REVIEWER_DATE")
	public Date getReviewerDate() {
		return reviewerDate;
	}

	public void setReviewerDate(Date reviewerDate) {
		this.reviewerDate = reviewerDate;
	}

	@Column(name ="BAKING_EQUIPEMNT")
	public String getBakingEquipment() {
		return bakingEquipment;
	}

	public void setBakingEquipment(String bakingEquipment) {
		this.bakingEquipment = bakingEquipment;
	}

	@Column(name = "HOLDING_EQUIPMENT")
	public String getHoldingEquipment() {
		return holdingEquipment;
	}

	public void setHoldingEquipment(String holdingEquipment) {
		this.holdingEquipment = holdingEquipment;
	}

	@Column(name= " WELDER")
	public String getWelder() {
		return ClobUtil.ClobToString(this.welder);
	}
	public void setWelder(String welder) throws SerialException, SQLException {
		this.welder = ClobUtil.stringToClob(welder);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "WELDER_SIGN_DATE")
	public Date getWelderSignDate() {
		return welderSignDate;
	}

	public void setWelderSignDate(Date welderSignDate) {
		this.welderSignDate = welderSignDate;
	}

	@Column(name = "TECHNIAL_LEADER")
	public String getTechnicalLeader() {
		return  ClobUtil.ClobToString(this.technicalLeader);
	}

	public void setTechnicalLeader(String technicalLeader) throws SerialException, SQLException {
		this.technicalLeader = ClobUtil.stringToClob(technicalLeader);
	}

	@Column(name = "AUDIT_OFFICER")
	public String getAuditOfficer() {
		return ClobUtil.ClobToString(this.auditOfficer);
	}

	public void setAuditOfficer(String auditOfficer) throws SerialException, SQLException {
		this.auditOfficer = ClobUtil.stringToClob(auditOfficer);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AO_SIGN_DATE")
	public Date getAoSignDate() {
		return aoSignDate;
	}
	
	public void setAoSignDate(Date aoSignDate) {
		this.aoSignDate = aoSignDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="TL_SIGN_DATE")
	public Date getTlSignDate() {
		return tlSignDate;
	}

	public void setTlSignDate(Date tlSignDate) {
		this.tlSignDate = tlSignDate;
	}

	@Column(name="CHECKER")
	public String getChecker() {
		return ClobUtil.ClobToString(this.checker);
	}

	public void setChecker(String checker) throws SerialException, SQLException {
		this.checker = ClobUtil.stringToClob(checker);
	}

	@Temporal(TemporalType.DATE)
	@Column(name="CHECKER_SIGN_DATE")
	public Date getCheckerSignDate() {
		return checkerSignDate;
	}

	public void setCheckerSignDate(Date checkerSignDate) {
		this.checkerSignDate = checkerSignDate;
	}

	//
	@Column(name="FIELDS_REPRESENT")
	public String getFieldsRepresent() {
		return ClobUtil.ClobToString(this.fieldsRepresent);
	}

	public void setFieldsRepresent(String fieldsRepresent) throws SerialException, SQLException {
		this.fieldsRepresent = ClobUtil.stringToClob(fieldsRepresent);
	}

	@Temporal(TemporalType.DATE)
	@Column(name="FIELDS_REPRESENT_DATE")
	public Date getFieldsRepresentDate() {
		return fieldsRepresentDate;
	}

	public void setFieldsRepresentDate(Date fieldsRepresentDate) {
		this.fieldsRepresentDate = fieldsRepresentDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="SUJGJ_SIGN_DATE")
	public Date getSuJgjSignDate() {
		return suJgjSignDate;
	}

	public void setSuJgjSignDate(Date suJgjSignDate) {
		this.suJgjSignDate = suJgjSignDate;
	}

	@Column(name="PROJECT_LEADER")
	public String getProjectLeader() {
		return ClobUtil.ClobToString(this.projectLeader);
	}

	public void setProjectLeader(String projectLeader) throws SerialException, SQLException {
		this.projectLeader = ClobUtil.stringToClob(projectLeader);
	}

	@Temporal(TemporalType.DATE)
	@Column(name="PL_SIGN_DATE")
	public Date getPlSignDate() {
		return plSignDate;
	}

	public void setPlSignDate(Date plSignDate) {
		this.plSignDate = plSignDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="CUQC_SIGN_DATE")
	public Date getCuQcSignDate() {
		return cuQcSignDate;
	}

	public void setCuQcSignDate(Date cuQcSignDate) {
		this.cuQcSignDate = cuQcSignDate;
	}

	@Column(name="BUILDER")
	public String getBuilder() {
		return ClobUtil.ClobToString(this.builder);
	}

	public void setBuilder(String builder) throws SerialException, SQLException {
		this.builder = ClobUtil.stringToClob(builder);
	}

	@Temporal(TemporalType.DATE)
	@Column(name="BUILDER_SIGN_DATE")
	public Date getBuilderSignDate() {
		return builderSignDate;
	}

	public void setBuilderSignDate(Date builderSignDate) {
		this.builderSignDate = builderSignDate;
	}
	

	@Column(name="CUST_NAME")
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Transient
	public PreservativeInpect getPreservativeInpect() {
		return preservativeInpect;
	}

	public void setPreservativeInpect(PreservativeInpect preservativeInpect) {
		this.preservativeInpect = preservativeInpect;
	}

	@Column(name="TEST_LEADER")
	public String getTestLeader() {
		return ClobUtil.ClobToString(this.testLeader);
	}

	public void setTestLeader(String testLeader) throws SerialException, SQLException {
		this.testLeader = ClobUtil.stringToClob(testLeader);
	}

	@Temporal(TemporalType.DATE)
	@Column(name="TESTL_SIGN_DATE")
	public Date getTestlSignDate() {
		return testlSignDate;
	}

	public void setTestlSignDate(Date testlSignDate) {
		this.testlSignDate = testlSignDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="CUP_SIGN_DATE")
	public Date getCupSignDate() {
		return cupSignDate;
	}

	public void setCupSignDate(Date cupSignDate) {
		this.cupSignDate = cupSignDate;
	}

	@Column(name="COMPLETE_UNIT_NAME")
	public String getCompleteUnitName() {
		return completeUnitName;
	}

	public void setCompleteUnitName(String completeUnitName) {
		this.completeUnitName = completeUnitName;
	}

	@Column(name="COMPLETE_UNIT_NO")
	public String getCompleteUnitNo() {
		return completeUnitNo;
	}

	public void setCompleteUnitNo(String completeUnitNo) {
		this.completeUnitNo = completeUnitNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="INSTALL_START_DATE")
	public Date getInstallStartDate() {
		return installStartDate;
	}

	public void setInstallStartDate(Date installStartDate) {
		this.installStartDate = installStartDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="INSTALL_COMPLETE_DATE")
	public Date getInstallCompleteDate() {
		return installCompleteDate;
	}

	public void setInstallCompleteDate(Date installCompleteDate) {
		this.installCompleteDate = installCompleteDate;
	}

	@Column(name="PIPING_LEVEL")
	public String getPipingLevel() {
		return pipingLevel;
	}

	public void setPipingLevel(String pipingLevel) {
		this.pipingLevel = pipingLevel;
	}

	@Column(name="PIPING_LEN")
	public String getPipingLen() {
		return pipingLen;
	}

	public void setPipingLen(String pipingLen) {
		this.pipingLen = pipingLen;
	}
	@Column(name="PC_FLAG")
	public String getPcFlag() {
		return pcFlag;
	}

	public void setPcFlag(String pcFlag) {
		this.pcFlag = pcFlag;
	}

	@Column(name="DU_APTITUDE_NO")
	public String getDuAptitudeNo() {
		return duAptitudeNo;
	}

	public void setDuAptitudeNo(String duAptitudeNo) {
		this.duAptitudeNo = duAptitudeNo;
	}

	@Column(name="SU_APTITUDE_NO")
	public String getSuAptitudeNo() {
		return suAptitudeNo;
	}

	public void setSuAptitudeNo(String suAptitudeNo) {
		this.suAptitudeNo = suAptitudeNo;
	}

	@Column(name="NDT_UNIT")
	public String getNdtUnit() {
		return ndtUnit;
	}

	public void setNdtUnit(String ndtUnit) {
		this.ndtUnit = ndtUnit;
	}

	@Column(name="NDT_APTITUDE_NO")
	public String getNdtAptitudeNo() {
		return ndtAptitudeNo;
	}

	public void setNdtAptitudeNo(String ndtAptitudeNo) {
		this.ndtAptitudeNo = ndtAptitudeNo;
	}

	@Column(name="INSTALL_MONITOR_UNIT")
	public String getInstallMonitorUnit() {
		return installMonitorUnit;
	}

	public void setInstallMonitorUnit(String installMonitorUnit) {
		this.installMonitorUnit = installMonitorUnit;
	}

	@Column(name="USE_UNIT")
	public String getUseUnit() {
		return useUnit;
	}

	public void setUseUnit(String useUnit) {
		this.useUnit = useUnit;
	}

	@Column(name="INSTALL_UNIT")
	public String getInstallUnit() {
		return installUnit;
	}

	public void setInstallUnit(String installUnit) {
		this.installUnit = installUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="IU_SEAL_DATE")
	public Date getIuSealDate() {
		return iuSealDate;
	}

	public void setIuSealDate(Date iuSealDate) {
		this.iuSealDate = iuSealDate;
	}

	@Column(name="LICENS_NO")
	public String getLicensNo() {
		return licensNo;
	}

	public void setLicensNo(String licensNo) {
		this.licensNo = licensNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="CUQAE_SIGN_DATE")
	public Date getCuQaeSignDate() {
		return cuQaeSignDate;
	}

	public void setCuQaeSignDate(Date cuQaeSignDate) {
		this.cuQaeSignDate = cuQaeSignDate;
	}

	@Column(name="PARTICIPANT")
	public String getParticipant() {
		return ClobUtil.ClobToString(this.participant);
	}

	public void setParticipant(String participant) throws SerialException, SQLException {
		this.participant = ClobUtil.stringToClob(participant);
	}

	@Temporal(TemporalType.DATE)
	@Column(name="PARTICIPANT_SIGN_DATE")
	public Date getParticipantSignDate() {
		return participantSignDate;
	}

	public void setParticipantSignDate(Date participantSignDate) {
		this.participantSignDate = participantSignDate;
	}

	@Column(name="CUST_REPRESENT")
	public String getCustRepresent() {
		return ClobUtil.ClobToString(this.custRepresent);
	}

	public void setCustRepresent(String custRepresent) throws SerialException, SQLException {
		this.custRepresent = ClobUtil.stringToClob(custRepresent);
	}

	@Temporal(TemporalType.DATE)
	@Column(name="CUSTR_SIGN_DATE")
	public Date getCustRSignDate() {
		return custRSignDate;
	}

	public void setCustRSignDate(Date custRSignDate) {
		this.custRSignDate = custRSignDate;
	}

	@Column(name="ST_PIPE_TYPE")
	public String getStPipeType() {
		return stPipeType;
	}

	public void setStPipeType(String stPipeType) {
		this.stPipeType = stPipeType;
	}

	@Column(name="ST_MEDIUM")
	public String getStMedium() {
		return stMedium;
	}

	public void setStMedium(String stMedium) {
		this.stMedium = stMedium;
	}

	@Column(name="ST_RANGE")
	public String getStRange() {
		return stRange;
	}

	public void setStRange(String stRange) {
		this.stRange = stRange;
	}

	@Column(name="ST_BUILDING_NO")
	public String getStBuildingNo() {
		return stBuildingNo;
	}

	public void setStBuildingNo(String stBuildingNo) {
		this.stBuildingNo = stBuildingNo;
	}

	@Column(name="ST_HOUSE_HOLDS")
	public String getStHouseHolds() {
		return stHouseHolds;
	}

	public void setStHouseHolds(String stHouseHolds) {
		this.stHouseHolds = stHouseHolds;
	}

	@Column(name="ST_INSTRUCTION")
	public String getStInstruction() {
		return stInstruction;
	}

	public void setStInstruction(String stInstruction) {
		this.stInstruction = stInstruction;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="SO_SIGN_DATE")
	public Date getSoSignDate() {
		return soSignDate;
	}

	public void setSoSignDate(Date soSignDate) {
		this.soSignDate = soSignDate;
	}

	@Column(name="WELDER_ID_NO")
	public String getWelderIDNo() {
		return welderIDNo;
	}

	public void setWelderIDNo(String welderIDNo) {
		this.welderIDNo = welderIDNo;
	}

	@Column(name="WELDER_NAME")
	public String getWelderName() {
		return welderName;
	}

	public void setWelderName(String welderName) {
		this.welderName = welderName;
	}

	@Column(name="WELD_LEADER")
	public String getWeldLeader() {
		return ClobUtil.ClobToString(this.weldLeader);
	}

	public void setWeldLeader(String weldLeader) throws SerialException, SQLException {
		this.weldLeader = ClobUtil.stringToClob(weldLeader);
	}

	@Temporal(TemporalType.DATE)
	@Column(name="WELD_LEADER_SIGN_DATE")
	public Date getWeldLeaderSignDate() {
		return weldLeaderSignDate;
	}

	public void setWeldLeaderSignDate(Date weldLeaderSignDate) {
		this.weldLeaderSignDate = weldLeaderSignDate;
	}

	@Column(name="QUALITY_LEADER")
	public String getQualityLeader() {
		return ClobUtil.ClobToString(this.qualityLeader);
	}

	public void setQualityLeader(String qualityLeader) throws SerialException, SQLException {
		this.qualityLeader = ClobUtil.stringToClob(qualityLeader);
	}

	@Temporal(TemporalType.DATE)
	@Column(name="QU_LEADER_SIGN_DATE")
	public Date getQuLeaderSignDate() {
		return quLeaderSignDate;
	}

	public void setQuLeaderSignDate(Date quLeaderSignDate) {
		this.quLeaderSignDate = quLeaderSignDate;
	}

	@Column(name="MELT_CONNECT_TYPE")
	public String getMeltConnectType() {
		return meltConnectType;
	}

	public void setMeltConnectType(String meltConnectType) {
		this.meltConnectType = meltConnectType;
	}

	@Column(name="PE_WELD_SPEC")
	public String getPeWeldSpec() {
		return peWeldSpec;
	}

	public void setPeWeldSpec(String peWeldSpec) {
		this.peWeldSpec = peWeldSpec;
	}

	@Column(name="PIPE_MANUFACTOR")
	public String getPipeManufactor() {
		return pipeManufactor;
	}

	public void setPipeManufactor(String pipeManufactor) {
		this.pipeManufactor = pipeManufactor;
	}

	@Column(name="PRESERVATIVE_TYPE")
	public String getPreservativeType() {
		return preservativeType;
	}

	public void setPreservativeType(String preservativeType) {
		this.preservativeType = preservativeType;
	}

	@Transient
	public String getRecordsId() {
		String recordsId=this.recordsId;
		if(StringUtil.isNotBlank(this.recordsId)){  //判断this.recordsId是否为空
			recordsId="";
			String[] records = this.recordsId.split(",");  //按逗号分隔字符串
			Set<String> set = new HashSet<>();
			for(int i=0;i<records.length;i++){    //利用set集合去重
				set.add(records[i]);
			}
			for (String str : set) {   //遍历已去重的集合拼接为字符串并返回
				recordsId+=str+",";    
			}
		}
		return recordsId;
	}

	public void setRecordsId(String recordsId) {
		this.recordsId = recordsId;
	}

	@Column(name="OPERATOR")
	public String getOperator() {
		return ClobUtil.ClobToString(this.operator);
	}

	public void setOperator(String operator) throws SerialException, SQLException {
		this.operator = ClobUtil.stringToClob(operator);
	}

	@Temporal(TemporalType.DATE)
	@Column(name="OPERATOR_SIGN_DATE")
	public Date getOperatorSignDate() {
		return operatorSignDate;
	}

	public void setOperatorSignDate(Date operatorSignDate) {
		this.operatorSignDate = operatorSignDate;
	}

	@Column(name="IPW_PATCH_CODE")
	public String getIpwPatchCode() {
		return ipwPatchCode;
	}

	public void setIpwPatchCode(String ipwPatchCode) {
		this.ipwPatchCode = ipwPatchCode;
	}
	@Column(name="COURTYARD_NO")
	public String getCurtyardNo() {
		return curtyardNo;
	}

	public void setCurtyardNo(String curtyardNo) {
		this.curtyardNo = curtyardNo;
	}
	@Column(name="DRAW_BUILDING_NO")
	public String getDrawBuildingNo() {
		return drawBuildingNo;
	}

	public void setDrawBuildingNo(String drawBuildingNo) {
		this.drawBuildingNo = drawBuildingNo;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="IPW_DATE")
	public Date getIpwDate() {
		return ipwDate;
	}

	public void setIpwDate(Date ipwDate) {
		this.ipwDate = ipwDate;
	}

	@Column(name="INSTALL_ADDR")
	public String getInstallAddr() {
		return installAddr;
	}

	public void setInstallAddr(String installAddr) {
		this.installAddr = installAddr;
	}

	@Column(name="PROTOCAL_NO")
	public String getProtocalNo() {
		return protocalNo;
	}

	public void setProtocalNo(String protocalNo) {
		this.protocalNo = protocalNo;
	}
	@Column(name="BUSINESS_OWNER")
	public String getBusinessOwner() {
		return ClobUtil.ClobToString(this.businessOwner);
	}

	public void setBusinessOwner(String businessOwner) throws SerialException, SQLException {
		this.businessOwner = ClobUtil.stringToClob(businessOwner);
	}
	@Temporal(TemporalType.DATE)
	@Column(name="BO_SIGN_DATE")
	public Date getBoSignDate() {
		return boSignDate;
	}
	
	public void setBoSignDate(Date boSignDate) {
		this.boSignDate = boSignDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="CU_DATE")
	public Date getCuDate() {
		return cuDate;
	}

	public void setCuDate(Date cuDate) {
		this.cuDate = cuDate;
	}

	@Transient
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Column(name="CORP_NAME")
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	@Column(name="CU_PM")
	public String getCuPm() {
		return ClobUtil.ClobToString(this.cuPm);
	}

	public void setCuPm(String cuPm) throws SerialException, SQLException {
		this.cuPm = ClobUtil.stringToClob(cuPm);
	}

	@Temporal(TemporalType.DATE)
	@Column(name="CU_PM_SIGN_DATE")
	public Date getCuPmSignDate() {
		return cuPmSignDate;
	}

	public void setCuPmSignDate(Date cuPmSignDate) {
		this.cuPmSignDate = cuPmSignDate;
	}


	@Column(name="RESET_REASON")
	public String getResetReason() {
		return resetReason;
	}

	public void setResetReason(String resetReason) {
		this.resetReason = resetReason;
	}

	@Column(name="FLAG")
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Transient
	public String getFlagDes() {
		for(ProjectCheckListFlagEnum e: ProjectCheckListFlagEnum.values()) {
	   		if(e.getValue().equals(this.flag)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setFlagDes(String flagDes) {
		this.flagDes = flagDes;
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
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Transient
	public String getAccListId() {
		return accListId;
	}

	public void setAccListId(String accListId) {
		this.accListId = accListId;
	}

	@Column(name="FINISHED_DATE")
	public String getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(String finishedDate) {
		this.finishedDate = finishedDate;
	}

	@Column(name="FINISHED_OPR")
	public String getFinishedOpr() {
		return finishedOpr;
	}

	public void setFinishedOpr(String finishedOpr) {
		this.finishedOpr = finishedOpr;
	}
	@Column(name="IS_FINISH_SIGN")
	public String getIsFinishSign() {
		return isFinishSign;
	}

	public void setIsFinishSign(String isFinishSign) {
		this.isFinishSign = isFinishSign;
	}
	
}