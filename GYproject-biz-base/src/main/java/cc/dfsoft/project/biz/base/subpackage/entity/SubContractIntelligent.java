package cc.dfsoft.project.biz.base.subpackage.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.contract.entity.PayType;
/**
 * 
 * 描述:智能表分合同实体类
 * @author liaoyq
 * @createTime 2017年10月11日
 */
@Entity
@Table(name="sub_contract_intelligent")
public class SubContractIntelligent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1811825347866054102L;
	
	private String itScId;					//主键ID
	private String itScNo;					//合同编号
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private String projName;				//工程名称
	private String projAddr;				//工程地点
	private String projContent;				//工程内容
	
	private String corpName;				//燃气公司名称-甲方-发包方
	private String corpId;					//分公司ID-甲方-发包方
	private String fPartyRepresent;			//甲方现场代表
	private String fPartySuJgj;				//甲方现场监理
	private String grantRepresent;			//甲方授权代表人
	private String fPartyConAgent;			//甲方经办人
	private String fPartyConAgentId;			//甲方经办人
	
	private String sPartyId;			//乙方单位ID-表厂ID-承包方
	private String sPartyName;				//乙方单位名称-表厂名称-承包方
	private String sPartyCuPm;				//乙方项目经理
	private String sPartyBuilder;			//乙方现场施工员
	private String sPartyGrantRepresent;	//乙方授权代表人
	private String sPartyAgent;			    //乙方经办人
	
	private Date signDate;					//合同签订日期
	private BigDecimal totalCost;           //工程总造价
	private BigDecimal unitCost;			//每户金额
	private String scType;					//支付方式 -合同款
	private String payType; 				//支付方式 -预付款
	private List<PayType> payTypes;			//支付方式（预付款、进度款、合同款）-----------页面select显示

	private String progressType; 				//支付方式 -进度款
	private String flag;					//保存状态：0-暂存，1-已保存
	
	private String scPlannedStartDate;		//计划开工日期
	private String scPlannedEndDate;		//计划竣工日期
	private String projTimeLimit;			//工期-只用于页面显示
	
	private String isIntelligentMeter;     	//是否是智能表标记（1：是）-只用于页面显示
	private String installNums;				//安装户数-只用于页面显示
	private String contributionModeDes;		//出资方式-只用于页面显示
	private String projectTypeDes;			//工程类型-只用于页面显示
	private String departmentName;	    	//业务部门-只用于页面显示
	private String projectType;				//工程类型-只用于页面显示
	private String projScaleDes;			//工程规模-只用于页面显示
	private BigDecimal firstPayment;		//首付款
	private BigDecimal secondPayment;		//阶段款（2）
	private BigDecimal thirdPayment;		//阶段款（3）
	private String increment;				//税率
	
	public SubContractIntelligent() {
		super();
	}
	
	@Id
	@Column(name="IT_SC_ID")
	public String getItScId() {
		return itScId;
	}
	public void setItScId(String itScId) {
		this.itScId = itScId;
	}
	
	@Column(name="IT_SC_NO")
	public String getItScNo() {
		return itScNo;
	}
	public void setItScNo(String itScNo) {
		this.itScNo = itScNo;
	}
	
	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	@Column(name="PROJ_NO")
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	
	@Column(name="PROJ_NAME")
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	
	@Column(name="PROJ_ADDR")
	public String getProjAddr() {
		return projAddr;
	}
	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	
	@Column(name="PROJ_CONTENT")
	public String getProjContent() {
		return projContent;
	}
	public void setProjContent(String projContent) {
		this.projContent = projContent;
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
	
	@Column(name="FPARTY_REPRESENT")
	public String getfPartyRepresent() {
		return fPartyRepresent;
	}
	public void setfPartyRepresent(String fPartyRepresent) {
		this.fPartyRepresent = fPartyRepresent;
	}
	
	@Column(name="FPARTY_SUJGJ")
	public String getfPartySuJgj() {
		return fPartySuJgj;
	}
	public void setfPartySuJgj(String fPartySuJgj) {
		this.fPartySuJgj = fPartySuJgj;
	}
	
	@Column(name="GRANT_REPRESENT")
	public String getGrantRepresent() {
		return grantRepresent;
	}
	public void setGrantRepresent(String grantRepresent) {
		this.grantRepresent = grantRepresent;
	}
	
	@Column(name="FPARTY_CON_AGENT")
	public String getfPartyConAgent() {
		return fPartyConAgent;
	}
	public void setfPartyConAgent(String fPartyConAgent) {
		this.fPartyConAgent = fPartyConAgent;
	}
	
	@Column(name="SPARTY_NAME")
	public String getsPartyName() {
		return sPartyName;
	}
	public void setsPartyName(String sPartyName) {
		this.sPartyName = sPartyName;
	}
	
	@Column(name="SPARTY_CUPM")
	public String getsPartyCuPm() {
		return sPartyCuPm;
	}
	public void setsPartyCuPm(String sPartyCuPm) {
		this.sPartyCuPm = sPartyCuPm;
	}
	
	@Column(name="SPARTY_BUILDER")
	public String getsPartyBuilder() {
		return sPartyBuilder;
	}
	public void setsPartyBuilder(String sPartyBuilder) {
		this.sPartyBuilder = sPartyBuilder;
	}
	
	@Column(name="SPARTY_GRANT_REPRESENT")
	public String getsPartyGrantRepresent() {
		return sPartyGrantRepresent;
	}
	public void setsPartyGrantRepresent(String sPartyGrantRepresent) {
		this.sPartyGrantRepresent = sPartyGrantRepresent;
	}
	
	@Column(name="SPARTY_AGENT")
	public String getsPartyAgent() {
		return sPartyAgent;
	}
	public void setsPartyAgent(String sPartyAgent) {
		this.sPartyAgent = sPartyAgent;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="SIGN_DATE")
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	@Column(name="SC_PLANNED_START_DATE")
	public String getScPlannedStartDate() {
		return scPlannedStartDate;
	}
	public void setScPlannedStartDate(String scPlannedStartDate) {
		this.scPlannedStartDate = scPlannedStartDate;
	}
	@Column(name="SC_PLANNED_END_DATE")
	public String getScPlannedEndDate() {
		return scPlannedEndDate;
	}
	public void setScPlannedEndDate(String scPlannedEndDate) {
		this.scPlannedEndDate = scPlannedEndDate;
	}
	@Column(name = "SC_TYPE")
	public String getScType() {
		return this.scType;
	}

	public void setScType(String scType) {
		this.scType = scType;
	}
	@Column(name = "PAY_TYPE")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	@Column(name = "PROGRESS_TYPE")
	public String getProgressType() {
		return progressType;
	}

	public void setProgressType(String progressType) {
		this.progressType = progressType;
	}

	@Transient
	public List<PayType> getPayTypes() {
		return payTypes;
	}

	public void setPayTypes(List<PayType> payTypes) {
		this.payTypes = payTypes;
	}
	@Transient
	public String getIsIntelligentMeter() {
		return isIntelligentMeter;
	}
	public void setIsIntelligentMeter(String isIntelligentMeter) {
		this.isIntelligentMeter = isIntelligentMeter;
	}
	@Transient
	public String getContributionModeDes() {
		return contributionModeDes;
	}

	public void setContributionModeDes(String contributionModeDes) {
		this.contributionModeDes = contributionModeDes;
	}
	
	@Transient
	public String getProjectTypeDes() {
		return projectTypeDes;
	}

	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}
	
	@Transient
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	@Column(name="TOTAL_COST")
	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	@Column(name="UNIT_COST")
	public BigDecimal getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
	}

	@Column(name="INSTALL_NUMS")
	public String getInstallNums() {
		return installNums;
	}

	public void setInstallNums(String installNums) {
		this.installNums = installNums;
	}
	@Transient
	public String getProjScaleDes() {
		return projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}

	@Column(name="FLAG")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name="FPARTY_CON_AGENT_ID")
	public String getfPartyConAgentId() {
		return fPartyConAgentId;
	}

	public void setfPartyConAgentId(String fPartyConAgentId) {
		this.fPartyConAgentId = fPartyConAgentId;
	}

	@Column(name="SECOND_PARTY_ID")
	public String getsPartyId() {
		return sPartyId;
	}

	public void setsPartyId(String sPartyId) {
		this.sPartyId = sPartyId;
	}

	@Column(name = "FIRST_PAYMENT")
	public BigDecimal getFirstPayment() {
		return firstPayment;
	}

	public void setFirstPayment(BigDecimal firstPayment) {
		this.firstPayment = firstPayment;
	}

	@Column(name="SECOND_PAYMENT")
	public BigDecimal getSecondPayment() {
		return secondPayment;
	}

	public void setSecondPayment(BigDecimal secondPayment) {
		this.secondPayment = secondPayment;
	}

	@Column(name="THIRD_PAYMENT")
	public BigDecimal getThirdPayment() {
		return thirdPayment;
	}

	public void setThirdPayment(BigDecimal thirdPayment) {
		this.thirdPayment = thirdPayment;
	}

	@Column(name="INCREMENT")
	public String getIncrement() {
		return increment;
	}

	public void setIncrement(String increment) {
		this.increment = increment;
	}

	
}
