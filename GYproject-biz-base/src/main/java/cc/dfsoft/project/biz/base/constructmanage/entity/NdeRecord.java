package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.io.Serializable;
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

import cc.dfsoft.project.biz.base.constructmanage.enums.TestResultEnum;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;
/**
 * 
 * 描述:无损检测记录实体类
 * @author liaoyq
 * @createTime 2017年9月27日
 */
@Entity
@Table(name="NDE_RECORD")
public class NdeRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7566263316989053799L;
	private String nrId;			//探伤检测记录
	private String tubing;			//管材
	private String nozzleNum;		//管口数量
	private String bcId;			//业务沟通单ID
	private String conNo;			//安装合同号
	private String scNo;			//协议号
	private String testItem;		//检测项目
	private String pipeLineNum;		//管线编号
	private String surfaceState;	//表面状态
	private String totalWeldsNum;	//总焊口数
	private String spec;			//规格
	private String qualifiedLevel;	//合格级别
	private String grooveForm;		//坡口形式
	private String acceptStandard;	//验收标准
	private String welderNo;		//焊工证号
	private String weldingMethod;	//焊接方法
	private String drawingData;		//图纸资料
	private String reportNo;		//报告编号
	private String noticeNo;		//检测通知单编号
	private String testResult;		//检测结果
	private String defectNature;	//缺陷性质
	private String defectLen;		//缺陷长度
	private String repairedPieces;	//返修片数
	private Date shootDate;			//拍片日期
	private String testSituation;	//检测情况
	private String repairPositionNo;//返修部位编号
	private Clob bcInitiator;		//通知人签字
	private Date bcInitiatorSignDate;//通知人签字日期
	private Clob bcRecipient;		//接受人签字
	private Date bcPrecipientSignDate;//接收人签字日期
	private String projId;			//工程ID
	private Integer version;		//版本控制
	private String testLevel;		//检测等级
	private String testRate;		//检测比例
	private String suJgj;		   //委托人签字-监理
	private String suCse;			//委托负责人签字-总监
	private String ndeRemarks;		//备注
	
	
	private String cuName;			 //施工单位
	private String projectTypeDes;	//工程类型描述
	private String testResultDes;	//检测结果描述
	private List<Signature> sign;	//签字
	private String menuId;			//菜单ID-只读
	private String pushFlag;		//无损检测单推送状态-只读
	
	public NdeRecord() {
		super();
	}

	@Id
	@Column(name="NR_ID",unique = true, nullable = false)
	public String getNrId() {
		return nrId;
	}

	public void setNrId(String nrId) {
		this.nrId = nrId;
	}

	@Column(name="TUBING")
	public String getTubing() {
		return tubing;
	}

	public void setTubing(String tubing) {
		this.tubing = tubing;
	}

	@Column(name="NOZZLE_NUM")
	public String getNozzleNum() {
		return nozzleNum;
	}

	public void setNozzleNum(String nozzleNum) {
		this.nozzleNum = nozzleNum;
	}

	@Column(name="BC_ID")
	public String getBcId() {
		return bcId;
	}

	public void setBcId(String bcId) {
		this.bcId = bcId;
	}

	@Column(name="CON_NO")
	public String getConNo() {
		return conNo;
	}

	public void setConNo(String conNo) {
		this.conNo = conNo;
	}

	@Column(name="SC_NO")
	public String getScNo() {
		return scNo;
	}

	public void setScNo(String scNo) {
		this.scNo = scNo;
	}

	@Column(name="TEST_ITEM")
	public String getTestItem() {
		return testItem;
	}

	public void setTestItem(String testItem) {
		this.testItem = testItem;
	}

	@Column(name="PIPE_LINE_NUM")
	public String getPipeLineNum() {
		return pipeLineNum;
	}

	public void setPipeLineNum(String pipeLineNum) {
		this.pipeLineNum = pipeLineNum;
	}

	@Column(name="SURFACE_STATE")
	public String getSurfaceState() {
		return surfaceState;
	}

	public void setSurfaceState(String surfaceState) {
		this.surfaceState = surfaceState;
	}

	@Column(name="TOTAL_WELDS_NUM")
	public String getTotalWeldsNum() {
		return totalWeldsNum;
	}

	public void setTotalWeldsNum(String totalWeldsNum) {
		this.totalWeldsNum = totalWeldsNum;
	}

	@Column(name="SPEC")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	@Column(name="QUALIFIED_LEVEL")
	public String getQualifiedLevel() {
		return qualifiedLevel;
	}

	public void setQualifiedLevel(String qualifiedLevel) {
		this.qualifiedLevel = qualifiedLevel;
	}

	@Column(name="GROOVE_FROM")
	public String getGrooveForm() {
		return grooveForm;
	}

	public void setGrooveForm(String grooveForm) {
		this.grooveForm = grooveForm;
	}

	@Column(name="ACCEPT_STANDARD")
	public String getAcceptStandard() {
		return acceptStandard;
	}

	public void setAcceptStandard(String acceptStandard) {
		this.acceptStandard = acceptStandard;
	}

	@Column(name="WELDER_NO")
	public String getWelderNo() {
		return welderNo;
	}

	public void setWelderNo(String welderNo) {
		this.welderNo = welderNo;
	}

	@Column(name="WELDING_METHOD")
	public String getWeldingMethod() {
		return weldingMethod;
	}

	public void setWeldingMethod(String weldingMethod) {
		this.weldingMethod = weldingMethod;
	}

	@Column(name="DRAWING_DATA")
	public String getDrawingData() {
		return drawingData;
	}

	public void setDrawingData(String drawingData) {
		this.drawingData = drawingData;
	}

	@Column(name="REPORT_NO")
	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	@Column(name="NOTICE_NO")
	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	@Column(name="TEST_RESULT")
	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	@Column(name="DEFECT_NATURE")
	public String getDefectNature() {
		return defectNature;
	}

	public void setDefectNature(String defectNature) {
		this.defectNature = defectNature;
	}

	@Column(name="DEFECT_LEN")
	public String getDefectLen() {
		return defectLen;
	}

	public void setDefectLen(String defectLen) {
		this.defectLen = defectLen;
	}

	@Column(name="REPAIRED_PIECES")
	public String getRepairedPieces() {
		return repairedPieces;
	}

	public void setRepairedPieces(String repairedPieces) {
		this.repairedPieces = repairedPieces;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="SHOOT_DATE")
	public Date getShootDate() {
		return shootDate;
	}

	public void setShootDate(Date shootDate) {
		this.shootDate = shootDate;
	}

	@Column(name="TEST_SITUATION")
	public String getTestSituation() {
		return testSituation;
	}

	public void setTestSituation(String testSituation) {
		this.testSituation = testSituation;
	}

	@Column(name="REPAIR_POSITION_NO")
	public String getRepairPositionNo() {
		return repairPositionNo;
	}

	public void setRepairPositionNo(String repairPositionNo) {
		this.repairPositionNo = repairPositionNo;
	}

	@Column(name="BC_INITIATOR")
	public String getBcInitiator() {
		return ClobUtil.ClobToString(this.bcInitiator);
	}

	public void setBcInitiator(String bcInitiator) throws SerialException, SQLException {
		this.bcInitiator = ClobUtil.stringToClob(bcInitiator);
	}

	@Temporal(TemporalType.DATE)
	@Column(name="BC_INITIATOR_SIGN_DATE")
	public Date getBcInitiatorSignDate() {
		return bcInitiatorSignDate;
	}

	public void setBcInitiatorSignDate(Date bcInitiatorSignDate) {
		this.bcInitiatorSignDate = bcInitiatorSignDate;
	}

	@Column(name="BC_RECIPIENT")
	public String getBcRecipient() {
		return ClobUtil.ClobToString(this.bcRecipient);
	}

	public void setBcRecipient(String bcRecipient) throws SerialException, SQLException {
		this.bcRecipient = ClobUtil.stringToClob(bcRecipient);
	}

	@Temporal(TemporalType.DATE)
	@Column(name="BC_RECIPIENT_SIGN_DATE")
	public Date getBcPrecipientSignDate() {
		return bcPrecipientSignDate;
	}

	public void setBcPrecipientSignDate(Date bcPrecipientSignDate) {
		this.bcPrecipientSignDate = bcPrecipientSignDate;
	}

	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name="CU_NAME")
	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}

	@Column(name="TEST_LEVEL")
	public String getTestLevel() {
		return testLevel;
	}

	public void setTestLevel(String testLevel) {
		this.testLevel = testLevel;
	}

	@Column(name="TEST_RATE")
	public String getTestRate() {
		return testRate;
	}

	public void setTestRate(String testRate) {
		this.testRate = testRate;
	}

	@Column(name="SU_JGJ")
	public String getSuJgj() {
		return suJgj;
	}

	public void setSuJgj(String suJgj) {
		this.suJgj = suJgj;
	}

	@Column(name="SU_CSE")
	public String getSuCse() {
		return suCse;
	}

	public void setSuCse(String suCse) {
		this.suCse = suCse;
	}

	@Transient
	public String getProjectTypeDes() {
		return projectTypeDes;
	}

	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}

	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name="NDE_REMARKS")
	public String getNdeRemarks() {
		return ndeRemarks;
	}

	public void setNdeRemarks(String ndeRemarks) {
		this.ndeRemarks = ndeRemarks;
	}

	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}

	@Transient
	public String getTestResultDes() {
		for(TestResultEnum e: TestResultEnum.values()) {
	   		if(e.getValue().equals(this.testResult)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setTestResultDes(String testResultDes) {
		this.testResultDes = testResultDes;
	}

	@Transient
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Transient
	public String getPushFlag() {
		return pushFlag;
	}

	public void setPushFlag(String pushFlag) {
		this.pushFlag = pushFlag;
	}
	
	
}
