package cc.dfsoft.project.biz.base.station.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

import java.math.BigDecimal;
import java.util.Date;

//未用到
public class StationProceduresReq extends PageSortReq{
	private String orgId;			//
	private String tenantId;
	private String projId;			//工程ID
	private String projScaleDes;	//总体规模描述
	private String projLtypeId;		//工程大类ID
	private String projNo;			//工程编号
	private String projName;		//工程名称
	private String projStatusDes;	//工程状态
	private String projAddr;		//工程地点
	private String corpName;		//燃气公司
	private String deptId;			//申请部门
	private Date acceptDate;		//申请时间
	private String projectType;		//工程类型
	private String contributionMode;//出资方式
	private BigDecimal totalInvestment;		//总投资
	private Date protocolStartingDate;//拟开工日期
	private String projectDuration;		//工期
	private String acceptReason;		//立项理由

	//地勘信息（不统计-存入工程表了）
	private String explorationUnit;			    //地勘单位
	private String euNo;						//地勘合同协议号
	private BigDecimal euAmount;				//地勘合同金额
	private String euDeadline;					//地勘时限
	private Date euDate;						//地勘完成时间
	//设计
	private String duId;					//设计院ID
	private String duName;					//设计院名称
	private String duDeadline;				//设计时限
	private Date duCompleteDate;			//设计完成日期
	//施工合同
	private String cuId;					//分包单位ID
	private String cuName;					//乙方名称
	private BigDecimal scAmount;			//协议价款
	private String scPlannedTotalDays;		//计划天数
	private Date scSignDate;				//签订日期
	//建审资料-存工程表
	private String acceptData;              //立项资料
	private String proceduresData;			//建审资料
	private String contructionData;			//施工资料
	private String settlementData;			//结算资料
	private String finalAccountData;		//决算资料
	//工程进度
	private String nuitProject;				//单位工程（工序）
	private String finishProgress;			//完成进度(%)
	//验收信息
	private Date jaDate;                    //验收时间
	private Date pilotRunDate;              //试运行时间
	//结算信息
	private BigDecimal endDeclarationCost;		//结算金额
//	private Date endDeclaraDate;				//结算审核日期
	private Date settlementDate;				//结算日期

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjStatusDes() {
		return projStatusDes;
	}

	public void setProjStatusDes(String projStatusDes) {
		this.projStatusDes = projStatusDes;
	}

	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	public String getProjScaleDes() {
		return projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}

	public String getProjLtypeId() {
		return projLtypeId;
	}

	public void setProjLtypeId(String projLtypeId) {
		this.projLtypeId = projLtypeId;
	}

	public String getExplorationUnit() {
		return explorationUnit;
	}

	public void setExplorationUnit(String explorationUnit) {
		this.explorationUnit = explorationUnit;
	}

	public String getEuNo() {
		return euNo;
	}

	public void setEuNo(String euNo) {
		this.euNo = euNo;
	}

	public BigDecimal getEuAmount() {
		return euAmount;
	}

	public void setEuAmount(BigDecimal euAmount) {
		this.euAmount = euAmount;
	}

	public Date getEuDate() {
		return euDate;
	}

	public void setEuDate(Date euDate) {
		this.euDate = euDate;
	}

	public String getEuDeadline() {
		return euDeadline;
	}

	public void setEuDeadline(String euDeadline) {
		this.euDeadline = euDeadline;
	}

	public String getDuId() {
		return duId;
	}

	public void setDuId(String duId) {
		this.duId = duId;
	}

	public String getDuName() {
		return duName;
	}

	public void setDuName(String duName) {
		this.duName = duName;
	}

	public String getDuDeadline() {
		return duDeadline;
	}

	public void setDuDeadline(String duDeadline) {
		this.duDeadline = duDeadline;
	}

	public Date getDuCompleteDate() {
		return duCompleteDate;
	}

	public void setDuCompleteDate(Date duCompleteDate) {
		this.duCompleteDate = duCompleteDate;
	}

	public String getCuId() {
		return cuId;
	}

	public void setCuId(String cuId) {
		this.cuId = cuId;
	}

	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}

	public BigDecimal getScAmount() {
		return scAmount;
	}

	public void setScAmount(BigDecimal scAmount) {
		this.scAmount = scAmount;
	}

	public String getScPlannedTotalDays() {
		return scPlannedTotalDays;
	}

	public void setScPlannedTotalDays(String scPlannedTotalDays) {
		this.scPlannedTotalDays = scPlannedTotalDays;
	}

	public Date getScSignDate() {
		return scSignDate;
	}

	public void setScSignDate(Date scSignDate) {
		this.scSignDate = scSignDate;
	}

	public String getProceduresData() {
		return proceduresData;
	}

	public void setProceduresData(String proceduresData) {
		this.proceduresData = proceduresData;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getContructionData() {
		return contructionData;
	}

	public void setContructionData(String contructionData) {
		this.contructionData = contructionData;
	}

	public String getSettlementData() {
		return settlementData;
	}

	public void setSettlementData(String settlementData) {
		this.settlementData = settlementData;
	}

	public String getFinalAccountData() {
		return finalAccountData;
	}

	public void setFinalAccountData(String finalAccountData) {
		this.finalAccountData = finalAccountData;
	}

	public String getNuitProject() {
		return nuitProject;
	}

	public void setNuitProject(String nuitProject) {
		this.nuitProject = nuitProject;
	}

	public String getFinishProgress() {
		return finishProgress;
	}

	public void setFinishProgress(String finishProgress) {
		this.finishProgress = finishProgress;
	}

	public Date getJaDate() {
		return jaDate;
	}

	public void setJaDate(Date jaDate) {
		this.jaDate = jaDate;
	}

	public Date getPilotRunDate() {
		return pilotRunDate;
	}

	public void setPilotRunDate(Date pilotRunDate) {
		this.pilotRunDate = pilotRunDate;
	}

	public BigDecimal getEndDeclarationCost() {
		return endDeclarationCost;
	}

	public void setEndDeclarationCost(BigDecimal endDeclarationCost) {
		this.endDeclarationCost = endDeclarationCost;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getContributionMode() {
		return contributionMode;
	}

	public void setContributionMode(String contributionMode) {
		this.contributionMode = contributionMode;
	}

	public BigDecimal getTotalInvestment() {
		return totalInvestment;
	}

	public void setTotalInvestment(BigDecimal totalInvestment) {
		this.totalInvestment = totalInvestment;
	}

	public Date getProtocolStartingDate() {
		return protocolStartingDate;
	}

	public void setProtocolStartingDate(Date protocolStartingDate) {
		this.protocolStartingDate = protocolStartingDate;
	}

	public String getProjectDuration() {
		return projectDuration;
	}

	public void setProjectDuration(String projectDuration) {
		this.projectDuration = projectDuration;
	}

	public String getAcceptReason() {
		return acceptReason;
	}

	public void setAcceptReason(String acceptReason) {
		this.acceptReason = acceptReason;
	}

	public String getAcceptData() {
		return acceptData;
	}

	public void setAcceptData(String acceptData) {
		this.acceptData = acceptData;
	}
}
