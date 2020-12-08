package cc.dfsoft.project.biz.base.subpackage.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * SubSupplyContract entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SUB_SUPPLY_CONTRACT")
public class SubSupplyContract implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 627774870615302734L;
	// Fields

	private String sscId; 				//分包补充协议ID
	private String sscNo;				//补充协议编号
	private String projId;				//工程ID
	private String projNo;				//工程编号
	private String projName;			//工程名称
	private String projScaleDes;		//工程规模
	private String projAddr;			//工程地点
	private String sscaName;			//甲方单位名称
	private String sscaAddr;			//甲方地址
	private String sscaCompPm;			//甲方项目经理
	private String sscaLegalRepresent;	//甲方法定人
	private String sscaDirector;		//甲方委托人
	private String sscaSoptLeader;		//现场负责人
	private String sscaPhone;			//联系电话
	private String sscbName;			//乙方名称
	private String sscbAddr;			//乙方地址
	private String sscbCompPm;			//乙方驻地代表
	private String sscbLegalRepresent;	//乙方法定人
	private String sscbDirector;		//乙方委托人
	private String sscbSoptLeader;		//现场负责人
	private String sscbPhone;			//联系电话
	private String sscScope;			//承包范围
	private String sscType;				//承包方式
	private Date sscPlannedStartDate;	//计划开工日期
	private Date sscPlannedEndDate;		//计划竣工日期
	private String sscPlannedTotalDays;	//协议天数
	private String sscQualityStandar;	//质量标准
	private BigDecimal sscAmount;		//协议价款
	private Date sscSignDate;			//签订日期
	private String sscConAgent;			//经办人
	private String remark;				//备注
	
	private BigDecimal endDeclarationCost;	//金额
	private String legalAmount;			//打印合同时用
	private String isPrint;					//是否打印标记     0 已打印,1未打印
	
	// Constructors

	@Column(name = "IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}


	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	@Transient
	public String getLegalAmount() {
		return legalAmount;
	}


	public void setLegalAmount(String legalAmount) {
		this.legalAmount = legalAmount;
	}


	/** default constructor */
	public SubSupplyContract() {
	}


	// Property accessors
	@Id
	@Column(name = "SSC_ID", unique = true)
	public String getSscId() {
		return this.sscId;
	}

	public void setSscId(String sscId) {
		this.sscId = sscId;
	}

	@Column(name = "SSC_NO")
	public String getSscNo() {
		return this.sscNo;
	}

	public void setSscNo(String sscNo) {
		this.sscNo = sscNo;
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

	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return this.projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	@Column(name = "SSCA_NAME")
	public String getSscaName() {
		return this.sscaName;
	}

	public void setSscaName(String sscaName) {
		this.sscaName = sscaName;
	}

	@Column(name = "SSCA_ADDR")
	public String getSscaAddr() {
		return this.sscaAddr;
	}

	public void setSscaAddr(String sscaAddr) {
		this.sscaAddr = sscaAddr;
	}

	@Column(name = "SSCA_COMP_PM")
	public String getSscaCompPm() {
		return this.sscaCompPm;
	}

	public void setSscaCompPm(String sscaCompPm) {
		this.sscaCompPm = sscaCompPm;
	}

	@Column(name = "SSCA_LEGAL_REPRESENT")
	public String getSscaLegalRepresent() {
		return this.sscaLegalRepresent;
	}

	public void setSscaLegalRepresent(String sscaLegalRepresent) {
		this.sscaLegalRepresent = sscaLegalRepresent;
	}

	@Column(name = "SSCA_DIRECTOR")
	public String getSscaDirector() {
		return this.sscaDirector;
	}

	public void setSscaDirector(String sscaDirector) {
		this.sscaDirector = sscaDirector;
	}

	@Column(name = "SSCA_SOPT_LEADER")
	public String getSscaSoptLeader() {
		return this.sscaSoptLeader;
	}

	public void setSscaSoptLeader(String sscaSoptLeader) {
		this.sscaSoptLeader = sscaSoptLeader;
	}

	@Column(name = "SSCA_PHONE")
	public String getSscaPhone() {
		return this.sscaPhone;
	}

	public void setSscaPhone(String sscaPhone) {
		this.sscaPhone = sscaPhone;
	}

	@Column(name = "SSCB_NAME")
	public String getSscbName() {
		return this.sscbName;
	}

	public void setSscbName(String sscbName) {
		this.sscbName = sscbName;
	}

	@Column(name = "SSCB_ADDR")
	public String getSscbAddr() {
		return this.sscbAddr;
	}

	public void setSscbAddr(String sscbAddr) {
		this.sscbAddr = sscbAddr;
	}

	@Column(name = "SSCB_LEGAL_REPRESENT")
	public String getSscbLegalRepresent() {
		return this.sscbLegalRepresent;
	}

	public void setSscbLegalRepresent(String sscbLegalRepresent) {
		this.sscbLegalRepresent = sscbLegalRepresent;
	}

	@Column(name = "SSCB_DIRECTOR")
	public String getSscbDirector() {
		return this.sscbDirector;
	}

	public void setSscbDirector(String sscbDirector) {
		this.sscbDirector = sscbDirector;
	}

	@Column(name = "SSCB_SOPT_LEADER")
	public String getSscbSoptLeader() {
		return this.sscbSoptLeader;
	}

	public void setSscbSoptLeader(String sscbSoptLeader) {
		this.sscbSoptLeader = sscbSoptLeader;
	}

	@Column(name = "SSCB_PHONE")
	public String getSscbPhone() {
		return this.sscbPhone;
	}

	public void setSscbPhone(String sscbPhone) {
		this.sscbPhone = sscbPhone;
	}

	@Column(name = "SSC_SCOPE")
	public String getSscScope() {
		return this.sscScope;
	}

	public void setSscScope(String sscScope) {
		this.sscScope = sscScope;
	}

	@Column(name = "SSC_TYPE")
	public String getSscType() {
		return this.sscType;
	}

	public void setSscType(String sscType) {
		this.sscType = sscType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SSC_PLANNED_START_DATE")
	public Date getSscPlannedStartDate() {
		return this.sscPlannedStartDate;
	}

	public void setSscPlannedStartDate(Date sscPlannedStartDate) {
		this.sscPlannedStartDate = sscPlannedStartDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SSC_PLANNED_END_DATE")
	public Date getSscPlannedEndDate() {
		return this.sscPlannedEndDate;
	}

	public void setSscPlannedEndDate(Date sscPlannedEndDate) {
		this.sscPlannedEndDate = sscPlannedEndDate;
	}

	@Column(name = "SSC_PLANNED_TOTAL_DAYS")
	public String getSscPlannedTotalDays() {
		return this.sscPlannedTotalDays;
	}

	public void setSscPlannedTotalDays(String sscPlannedTotalDays) {
		this.sscPlannedTotalDays = sscPlannedTotalDays;
	}

	@Column(name = "SSC_QUALITY_STANDAR")
	public String getSscQualityStandar() {
		return this.sscQualityStandar;
	}

	public void setSscQualityStandar(String sscQualityStandar) {
		this.sscQualityStandar = sscQualityStandar;
	}

	@Column(name = "SSC_AMOUNT")
	public BigDecimal getSscAmount() {
		return this.sscAmount;
	}

	public void setSscAmount(BigDecimal sscAmount) {
		this.sscAmount = sscAmount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SSC_SIGN_DATE")
	public Date getSscSignDate() {
		return this.sscSignDate;
	}

	public void setSscSignDate(Date sscSignDate) {
		this.sscSignDate = sscSignDate;
	}

	@Column(name = "SSC_CON_AGENT")
	public String getSscConAgent() {
		return this.sscConAgent;
	}

	public void setSscConAgent(String sscConAgent) {
		this.sscConAgent = sscConAgent;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "SSCB_COMP_PM")
	public String getSscbCompPm() {
		return sscbCompPm;
	}


	public void setSscbCompPm(String sscbCompPm) {
		this.sscbCompPm = sscbCompPm;
	}

	@Transient
	public BigDecimal getEndDeclarationCost() {
		return endDeclarationCost;
	}


	public void setEndDeclarationCost(BigDecimal endDeclarationCost) {
		this.endDeclarationCost = endDeclarationCost;
	}

}