package cc.dfsoft.project.biz.base.complete.entity;

import java.math.BigDecimal;
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
import javax.sql.rowset.serial.SerialException;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import cc.dfsoft.project.biz.base.complete.enums.ConfrimStateEnum;


@Entity
@Table(name = "GAS_APPLY")
public class GasApply implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -962455711751014086L;
	private String gaId;			//主键id
	private String projId;			//项目id
	private String projNo;			//项目编号
	private String projName;		//工程名称
	
	private String gasApplyCsr;		//通气申请人
	private String applyCsrId;		//通气申请人iD
	private Date gasApplyTime;		//通气申请时间
	private String confrimGasCsr;	//确认通气人
	private Date confirmGasTime;	//确认通气时间
	private String confrimCsrid;	//确认通气人ID
	private String gasDes;			//通气反馈
	private String gasType;			//1正常通气、0特殊通气
	private String cuName;			//分包单位
	private String confrimState;	//反馈状态
	
	private BigDecimal payGasAmount;//通气款-------页面显示
	private String projScaleDes;	//工程规模-------页面显示
	private String projAddr;		//工程地点-------页面显示
	private String areaDes;			//区域描述-------页面显示
	private String builder;			//施工员-------页面显示
	private String cuPm;			//项目经理-------页面显示
	private BigDecimal scAmount;	//协议价款-------页面显示
	private String confrimStateDes;	//反馈状态-------页面显示
	
	private String custName;		//客户名称(申请单位)-页面显示
	private String custContact;		//客户联系人---------页面显示
	private String custPhone;		//客户联系电话-------页面显示
	
	
	private String custOpinion;               	//建设单位意见
	private Clob custDeputy;					//建设单位签字
	private Clob cuPmSign;						//项目经理
	private Clob builderSign;					//甲方代表
	private String managementOfficeOpinion;		//施工处意见
	private Clob managementOfficeChief;			//施工处长
	private String financeDept;					//财务处意见
	private Clob financeChief;					//财务处长
	private String businessManageDept;			//企业管理处意见
	private Clob businessManageOhief;			//企业管理处长
	private String projCom;						//工程公司意见
	private Clob vicePresident;					//工程公司签字
	
	private List<Signature> sign;				//签字相关数据
	
	private String flag;//
	
	
	@Id
	@Column(name = "GA_ID", unique = true)
	public String getGaId() {
		return gaId;
	}
	public void setGaId(String gaId) {
		this.gaId = gaId;
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
	
	@Column(name = "GAS_APPLY_CSR")
	public String getGasApplyCsr() {
		return gasApplyCsr;
	}
	public void setGasApplyCsr(String gasApplyCsr) {
		this.gasApplyCsr = gasApplyCsr;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "GAS_APPLY_TIME")
	public Date getGasApplyTime() {
		return gasApplyTime;
	}
	public void setGasApplyTime(Date gasApplyTime) {
		this.gasApplyTime = gasApplyTime;
	}
	
	@Column(name = "CONFRIM_GAS_CSR")
	public String getConfrimGasCsr() {
		return confrimGasCsr;
	}
	public void setConfrimGasCsr(String confrimGasCsr) {
		this.confrimGasCsr = confrimGasCsr;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CONFIRM_GAS_TIME")
	public Date getConfirmGasTime() {
		return confirmGasTime;
	}
	public void setConfirmGasTime(Date confirmGasTime) {
		this.confirmGasTime = confirmGasTime;
	}
	
	@Column(name = "GAS_DES")
	public String getGasDes() {
		return gasDes;
	}
	public void setGasDes(String gasDes) {
		this.gasDes = gasDes;
	}
	
	@Column(name = "GAS_TYPE")
	public String getGasType() {
		return gasType;
	}
	public void setGasType(String gasType) {
		this.gasType = gasType;
	}
	
	@Column(name = "APPLY_CSR_ID")
	public String getApplyCsrId() {
		return applyCsrId;
	}
	public void setApplyCsrId(String applyCsrId) {
		this.applyCsrId = applyCsrId;
	}
	
	@Column(name = "CONFRIM_CSR_ID")
	public String getConfrimCsrid() {
		return confrimCsrid;
	}
	public void setConfrimCsrid(String confrimCsrid) {
		this.confrimCsrid = confrimCsrid;
	}
	
	@Column(name = "CU_NAME")
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	
	@Transient
	public BigDecimal getPayGasAmount() {
		return payGasAmount;
	}
	public void setPayGasAmount(BigDecimal payGasAmount) {
		this.payGasAmount = payGasAmount;
	}
	
	@Column(name = "CUST_OPINION")
	public String getCustOpinion() {
		return custOpinion;
	}
	public void setCustOpinion(String custOpinion) {
		this.custOpinion = custOpinion;
	}
	
	@Column(name = "MANAGEMENT_OFFICE_OPINION")
	public String getManagementOfficeOpinion() {
		return managementOfficeOpinion;
	}
	public void setManagementOfficeOpinion(String managementOfficeOpinion) {
		this.managementOfficeOpinion = managementOfficeOpinion;
	}
	
	@Column(name = "FINANCE_DEPT")
	public String getFinanceDept() {
		return financeDept;
	}
	public void setFinanceDept(String financeDept) {
		this.financeDept = financeDept;
	}
	
	@Column(name = "BUSINESS_MANAGE_DEPT")
	public String getBusinessManageDept() {
		return businessManageDept;
	}
	public void setBusinessManageDept(String businessManageDept) {
		this.businessManageDept = businessManageDept;
	}
	
	@Column(name = "PROJ_COM")
	public String getProjCom() {
		return projCom;
	}
	public void setProjCom(String projCom) {
		this.projCom = projCom;
	}
	
	@Column(name = "CUST_DEPUTY")
	public String getCustDeputy() {
		return ClobUtil.ClobToString(this.custDeputy);
	}
	public void setCustDeputy(String custDeputy) throws SerialException, SQLException {
		this.custDeputy = ClobUtil.stringToClob(custDeputy);
	}
	
	
	
	@Column(name = "MANAGEMENT_OFFICE_CHIEF")
	public String getManagementOfficeChief() {
		return ClobUtil.ClobToString(this.managementOfficeChief);
	}
	public void setManagementOfficeChief(String managementOfficeChief) throws SerialException, SQLException {
		this.managementOfficeChief = ClobUtil.stringToClob(managementOfficeChief);
	}
	
	@Column(name = "FINANCE_CHIEF")
	public String getFinanceChief() {
		return ClobUtil.ClobToString(this.financeChief);
	}
	public void setFinanceChief(String financeChief) throws SerialException, SQLException {
		this.financeChief = ClobUtil.stringToClob(financeChief);
	}
	
	@Column(name = "BUSINESS_MANAGE_CHIEF")
	public String getBusinessManageOhief() {
		return ClobUtil.ClobToString(this.businessManageOhief);
	}
	public void setBusinessManageOhief(String businessManageOhief) throws SerialException, SQLException {
		this.businessManageOhief =ClobUtil.stringToClob(businessManageOhief);
	}
	
	@Column(name = "VICE_PRESIDENT")
	public String getVicePresident() {
		return ClobUtil.ClobToString(this.vicePresident);
	}
	public void setVicePresident(String vicePresident) throws SerialException, SQLException {
		this.vicePresident = ClobUtil.stringToClob(vicePresident);
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
	
	@Column(name = "CU_PM_SIGN")
	public String getCuPmSign() {
		return ClobUtil.ClobToString(this.cuPmSign);
	}
	public void setCuPmSign(String cuPmSign) throws SerialException, SQLException {
		this.cuPmSign = ClobUtil.stringToClob(cuPmSign);
	}
	
	@Column(name = "BUILDER_SIGN")
	public String getBuilderSign() {
		return ClobUtil.ClobToString(this.builderSign);
	}
	public void setBuilderSign(String builderSign) throws SerialException, SQLException {
		this.builderSign = ClobUtil.stringToClob(builderSign);
	}
	
	
	@Transient
	public String getProjScaleDes() {
		return projScaleDes;
	}
	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	
	@Transient
	public String getBuilder() {
		return builder;
	}
	public void setBuilder(String builder) {
		this.builder = builder;
	}
	
	@Transient
	public String getCuPm() {
		return cuPm;
	}
	public void setCuPm(String cuPm) {
		this.cuPm = cuPm;
	}
	
	@Transient
	public BigDecimal getScAmount() {
		return scAmount;
	}
	public void setScAmount(BigDecimal scAmount) {
		this.scAmount = scAmount;
	}
	
	@Column(name = "CONFRIM_STATE")
	public String getConfrimState() {
		return confrimState;
	}
	public void setConfrimState(String confrimState) {
		this.confrimState = confrimState;
	}
	
	@Transient
	public String getConfrimStateDes() {
		for(ConfrimStateEnum e: ConfrimStateEnum.values()) {
	   		if(e.getValue().equals(this.confrimState)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}
	public void setConfrimStateDes(String confrimStateDes) {
		this.confrimStateDes = confrimStateDes;
	}
	@Transient
	public String getProjAddr() {
		return projAddr;
	}
	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	@Transient
	public String getAreaDes() {
		return areaDes;
	}
	public void setAreaDes(String areaDes) {
		this.areaDes = areaDes;
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
	
}
