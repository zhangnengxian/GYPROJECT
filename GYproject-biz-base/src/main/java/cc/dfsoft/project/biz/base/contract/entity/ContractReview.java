package cc.dfsoft.project.biz.base.contract.entity;

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
import javax.print.DocFlavor.STRING;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;
import sun.print.resources.serviceui;

/**
 * 合同评审表
 * 
 * @author wanghuijun 20190629
 */
@Entity
@Table(name = "CONTRACT_REVIEW")
@DynamicUpdate(true)
@DynamicInsert(true)
public class ContractReview implements Serializable {

	private static final long serialVersionUID = 2880909961698743249L;
	/** 合同评审表主键ID */
	private String crId;
	/** 工程主键ID */
	private String projId;
	/** 工程编号 */
	private String projNo;
	/**公司ID*/
	private String corpId;
	/** 合同编号 */
	private String conNo;
	/** 工程名称 */
	private String projName;
	/** 工程地点 */
	private String projAddr;
	/** 工程类型描述 */
	private String projectTypeDes;
	/** 工程类型 */
	private String projectType;
	/**
	 * 合同类型,'1'表示买卖合同，‘2’工程合同，‘3’经济合同，‘4’租赁合同，‘5’劳务合同，‘6’服务合同，‘7’运输合同，‘8’承包合同，‘9
	 * ’其他合同'
	 */
	private String contractType;
	/** 合同概述 */
	private String contractSummary;
	/** 经办人Id */
	private String operatorId;
	/** 经办人名字 */
	private String operator;
	/** 经办人签字 */
	private Clob operatorSign;
	/** 经办人签字时间 */
	private Date operatorTime;
	/** 合同概要 */
	private String contractOutline;
	/** 经办部门负责人意见 */
	private String operatorDeptOpinion;
	/** 经办部门负责人签字时间 */
	private Date operatorDeptOptime;
	/** 经办部门负责人签字 */
	private Clob operatorDeptOpertor;
	/** 财务部门负责人意见 */
	private String financialDeptOpinon;
	/** 财务部门签字时间 */
	private Date financialDeptTime;
	/** 财务部门负责人签字 */
	private Clob financialDeptSign;
	/** 分管领导意见 */
	private String leaderDivisionOpinion;
	/** 分管领导签字时间 */
	private Date leaderDivisionTime;
	/** 分管领导签字 */
	private Clob leaderDivisionSign;
	/** 财务总监意见 */
	private String financialCfoOpinion;
	/** 财务总监签字时间 */
	private Date financialCfoTime;
	/** 财务总监签字 */
	private Clob financialCfoSign;
	/** 副总经理意见 */
	private String generalManagerOpintion;
	/** 副总经理签字时间 */
	private Date generalManagerTime;
	/** 副总经理签字 */
	private Clob generalManagerSign;
	/** 总经理意见 */
	private String directManagerOperion;
	/** 总经理签字时间 */
	private Date directManagerTime;
	/** 总经理签字 */
	private Clob directManagerSign;
	/** 推送时间 */
	private Date pushTime;
	/** 推送状态,1表示已推送,0表示未推送 */
	private String pushStatus = "0";
	/** 删除状态,'0'是删除，'1'是未删除 */
	private String delStatus = "1"; // 默认是未删除
	/** 删除人Id */
	private String delPersonal;
	/** 删除时间 */
	private Date delTime;
	/** 版本号 */
	private int version;
	/** 是否推送标志 */
	private String isPush;
	/** 打印标志,0表示未打印，1表示已打印，默认是0打印 */
	private String isPrint = "0";
	/** 签字相关数据 */
	private List<Signature> sign;

	@Id
	@Column(name = "CR_ID")
	public String getCrId() {
		return crId;
	}

	public void setCrId(String crId) {
		this.crId = crId;
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

	
	@Transient
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Transient
	public String getConNo() {
		return conNo;
	}

	public void setConNo(String conNo) {
		this.conNo = conNo;
	}

	@Transient
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Transient
	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	@Transient
	public String getProjectTypeDes() {
		return projectTypeDes;
	}

	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}

	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(name = "CONTRACT_TYPE")
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	@Column(name = "CONTRACT_SUMMARY")
	public String getContractSummary() {
		return contractSummary;
	}

	public void setContractSummary(String contractSummary) {
		this.contractSummary = contractSummary;
	}

	@Column(name = "OPERATOR_ID")
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	@Column(name = "OPERATOR")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATOR_SIGN")
	public String getOperatorSign() {
		return ClobUtil.ClobToString(this.operatorSign);
	}

	public void setOperatorSign(String operatorSign) throws SerialException, SQLException {
		this.operatorSign = ClobUtil.stringToClob(operatorSign);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATOR_TIME")
	public Date getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}

	@Column(name = "CONTRACT_OUTLINE")
	public String getContractOutline() {
		return contractOutline;
	}

	public void setContractOutline(String contractOutline) {
		this.contractOutline = contractOutline;
	}

	@Column(name = "OPERATOR_DEPT_OPINION")
	public String getOperatorDeptOpinion() {
		return operatorDeptOpinion;
	}

	public void setOperatorDeptOpinion(String operatorDeptOpinion) {
		this.operatorDeptOpinion = operatorDeptOpinion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATOR_DEPT_OPTIME")
	public Date getOperatorDeptOptime() {
		return operatorDeptOptime;
	}

	public void setOperatorDeptOptime(Date operatorDeptOptime) {
		this.operatorDeptOptime = operatorDeptOptime;
	}

	@Column(name = "OPERATOR_DEPT_OPERTOR")
	public String getOperatorDeptOpertor() {
		return ClobUtil.ClobToString(operatorDeptOpertor);
	}

	public void setOperatorDeptOpertor(String operatorDeptOpertor) throws SerialException, SQLException {
		this.operatorDeptOpertor = ClobUtil.stringToClob(operatorDeptOpertor);
	}

	@Column(name = "FINANCIAL_DEPT_OPINON")
	public String getFinancialDeptOpinon() {
		return financialDeptOpinon;
	}

	public void setFinancialDeptOpinon(String financialDeptOpinon) {
		this.financialDeptOpinon = financialDeptOpinon;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FINANCIAL_DEPT_TIME")
	public Date getFinancialDeptTime() {
		return financialDeptTime;
	}

	public void setFinancialDeptTime(Date financialDeptTime) {
		this.financialDeptTime = financialDeptTime;
	}

	@Column(name = "FINANCIAL_DEPT_SIGN")
	public String getFinancialDeptSign() {
		return ClobUtil.ClobToString(financialDeptSign);
	}

	public void setFinancialDeptSign(String financialDeptSign) throws SerialException, SQLException {
		this.financialDeptSign = ClobUtil.stringToClob(financialDeptSign);
	}

	@Column(name = "LEADER_DIVISION_OPINION")
	public String getLeaderDivisionOpinion() {
		return leaderDivisionOpinion;
	}

	public void setLeaderDivisionOpinion(String leaderDivisionOpinion) {
		this.leaderDivisionOpinion = leaderDivisionOpinion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LEADER_DIVISION_TIME")
	public Date getLeaderDivisionTime() {
		return leaderDivisionTime;
	}

	public void setLeaderDivisionTime(Date leaderDivisionTime) {
		this.leaderDivisionTime = leaderDivisionTime;
	}

	@Column(name = "LEADER_DIVISION_SIGN")
	public String getLeaderDivisionSign() {
		return ClobUtil.ClobToString(leaderDivisionSign);
	};

	public void setLeaderDivisionSign(String leaderDivisionSign) throws SerialException, SQLException {
		this.leaderDivisionSign = ClobUtil.stringToClob(leaderDivisionSign);
	}

	@Column(name = "FINANCIAL_CFO_OPINION")
	public String getFinancialCfoOpinion() {
		return financialCfoOpinion;
	}

	public void setFinancialCfoOpinion(String financialCfoOpinion) {
		this.financialCfoOpinion = financialCfoOpinion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FINANCIAL_CFO_TIME")
	public Date getFinancialCfoTime() {
		return financialCfoTime;
	}

	public void setFinancialCfoTime(Date financialCfoTime) {
		this.financialCfoTime = financialCfoTime;
	}

	@Column(name = "FINANCIAL_CFO_SIGN")
	public String getFinancialCfoSign() {
		return ClobUtil.ClobToString(financialCfoSign);
	}

	public void setFinancialCfoSign(String financialCfoSign) throws SerialException, SQLException {
		this.financialCfoSign = ClobUtil.stringToClob(financialCfoSign);
	}

	@Column(name = "GENERAL_MANAGER_OPINTION")
	public String getGeneralManagerOpintion() {
		return generalManagerOpintion;
	}

	public void setGeneralManagerOpintion(String generalManagerOpintion) {
		this.generalManagerOpintion = generalManagerOpintion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GENERAL_MANAGER_TIME")
	public Date getGeneralManagerTime() {
		return generalManagerTime;
	}

	public void setGeneralManagerTime(Date generalManagerTime) {
		this.generalManagerTime = generalManagerTime;
	}

	@Column(name = "GENERAL_MANAGER_SIGN")
	public String getGeneralManagerSign() {
		return ClobUtil.ClobToString(generalManagerSign);
	}

	public void setGeneralManagerSign(String generalManagerSign) throws SerialException, SQLException {
		this.generalManagerSign = ClobUtil.stringToClob(generalManagerSign);
	}

	@Column(name = "DIRECT_MANAGER_OPERION")
	public String getDirectManagerOperion() {
		return directManagerOperion;
	}

	public void setDirectManagerOperion(String directManagerOperion) {
		this.directManagerOperion = directManagerOperion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DIRECT_MANAGER_TIME")
	public Date getDirectManagerTime() {
		return directManagerTime;
	}

	public void setDirectManagerTime(Date directManagerTime) {
		this.directManagerTime = directManagerTime;
	}

	@Column(name = "DIRECT_MANAGER_SIGN")
	public String getDirectManagerSign() {
		return ClobUtil.ClobToString(directManagerSign);
	}

	public void setDirectManagerSign(String directManagerSign) throws SQLException, SerialException {
		this.directManagerSign = ClobUtil.stringToClob(directManagerSign);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PUSH_TIME")
	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	@Column(name = "PUSH_STATUS")
	public String getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(String pushStatus) {
		this.pushStatus = pushStatus;
	}

	@Column(name = "DEL_STATUS")
	public String getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}

	@Column(name = "DEL_PERSONAL")
	public String getDelPersonal() {
		return delPersonal;
	}

	public void setDelPersonal(String delPersonal) {
		this.delPersonal = delPersonal;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DEL_TIME")
	public Date getDelTime() {
		return delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	@Version
	@Column(name = "VERSION")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Transient
	public String getIsPush() {
		return isPush;
	}

	public void setIsPush(String isPush) {
		this.isPush = isPush;
	}

	@Column(name = "IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}

}
