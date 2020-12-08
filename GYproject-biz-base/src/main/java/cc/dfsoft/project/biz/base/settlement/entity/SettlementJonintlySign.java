package cc.dfsoft.project.biz.base.settlement.entity;

import java.io.Serializable;
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
/**
 * 结算汇签单
 * @author fuliwei
 *
 */
@Entity
@Table(name = "SETTLEMENT_JONINTLY_SIGN")
public class SettlementJonintlySign implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 955714597950666444L;
	
	private String 	  sjsId;			//主键id
	private String   projId;			//工程id
	private String   projNo;			//工程编号
	private String   projName;			//工程名称
	private String   projStatusId;		//工程状态
	private String   projScaleDes;		//工程规模
	private String   projectTypeDes;	//工程类型
	private String   cuName;			//施工单位
	private String   conContent;		//建设范围
	
	private String   contractMethod;	//承包方式
	private String   materialProvide;	//材料提供
	
	private BigDecimal  budgetCost;		//预算价
	private BigDecimal    sendDeclarationCost;//报审价
	private BigDecimal    endDeclarationCost;//竣工结算值
	private String   budegterAuditOpinion;   //预算员审核意见
	private Clob  	  budegterSign;			  //预算员签字
	private Date     budegterSignDate;	      //预算员签字日期
	private Clob     builderSign;			  //现场代表
	private Clob     groupLeaderSign;		  //预算组长
	private Clob     viceMinisterSign;		  //分管
	
	
	private String receiveNumber;			  //领料数
	private String writeOffNumber;			  //核销数
	private String supplementNumber;		  //退（补）料数
	private String materialOpinion;			  //审批意见
	private Clob     materialManagerSign;	  //物资部领导
	private Clob     materialFinanceSign;	  //物资部财务
	
	
	private Clob     archivesSign;			  //技术装备部负责人
	private String   archivesOpinion;	      //技术装备部意见
	
	
	private Clob     sujgjSign;				  //现场监理
	private Clob     sucseSign;				  //总监
	private String   suOpinion;	     		  //监理意见
	
	private Clob     dataCenterSign;		  //数据中心
	private String   dataCenterOpinion;		  //数据中心意见
	
	private String financeOpinion;			  //财务意见
    private Clob financeSign;			      //财务审核人
    private Clob financeManageSign;			  //财务负责人
	
	
	private String    corpId;				  //分公司id
	private String   isComplete;			  //竣工资料是否齐全
	
	private String isPrint;					//是否打印标记     0 已打印,1未打印
	
	
	private String contributionModeDes;		//出资方式
	private String corpName;				//分公司名称
	private String deptName;				//业务部门
	private String contractType;		    //用于页面显示-只读
	
	private String flag;					//保存还是推送
	private String signStatus;				//1 是保存 0 是已完成
	
	private Integer version;				//版本控制
	
	private List<Signature> sign;				//签字相关数据
	private Date finishDate;					//汇签时间
	
	private String nailMaterial;                //甲供材料
	
	private String projectType;					//工程类型-只读
	private String endDCLegalAmount;			//结算金额大写-只读

	private String remark;                //备注
	
	@Id
	@Column(name = "SJS_ID", unique = true)
	public String getSjsId() {
		return sjsId;
	}
	public void setSjsId(String sjsId) {
		this.sjsId = sjsId;
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
	
	@Column(name = "PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return projScaleDes;
	}
	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	
	@Column(name = "PROJECT_TYPE_DES")
	public String getProjectTypeDes() {
		return projectTypeDes;
	}
	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}
	
	@Column(name = "CU_NAME")
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	
	@Column(name = "CON_CONTENT")
	public String getConContent() {
		return conContent;
	}
	public void setConContent(String conContent) {
		this.conContent = conContent;
	}
	
	@Column(name = "CONTRACMT_METHOD")
	public String getContractMethod() {
		return contractMethod;
	}
	public void setContractMethod(String contractMethod) {
		this.contractMethod = contractMethod;
	}
	
	@Column(name = "MATERIAL_PROVIDE")
	public String getMaterialProvide() {
		return materialProvide;
	}
	public void setMaterialProvide(String materialProvide) {
		this.materialProvide = materialProvide;
	}
	
	@Column(name = "BUDGET_COST")
	public BigDecimal getBudgetCost() {
		return budgetCost;
	}
	public void setBudgetCost(BigDecimal budgetCost) {
		this.budgetCost = budgetCost;
	}
	
	@Column(name = "SEND_DECLARATION_COST")
	public BigDecimal getSendDeclarationCost() {
		return sendDeclarationCost;
	}
	public void setSendDeclarationCost(BigDecimal sendDeclarationCost) {
		this.sendDeclarationCost = sendDeclarationCost;
	}
	
	@Column(name = "END_DECLARATION_COST")
	public BigDecimal getEndDeclarationCost() {
		return endDeclarationCost;
	}
	public void setEndDeclarationCost(BigDecimal endDeclarationCost) {
		this.endDeclarationCost = endDeclarationCost;
	}
	
	@Column(name = "BUDEGTER_AUDIT_OPINION")
	public String getBudegterAuditOpinion() {
		return budegterAuditOpinion;
	}
	public void setBudegterAuditOpinion(String budegterAuditOpinion) {
		this.budegterAuditOpinion = budegterAuditOpinion;
	}
	
	@Column(name = "BUDEGTER_SIGN")
	public String getBudegterSign() {
		return ClobUtil.ClobToString(this.budegterSign);
	}
	public void setBudegterSign(String budegterSign) throws SerialException, SQLException {
		this.budegterSign = ClobUtil.stringToClob(budegterSign);
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "BUDEGTER_SIGN_DATE")
	public Date getBudegterSignDate() {
		return budegterSignDate;
	}
	public void setBudegterSignDate(Date budegterSignDate) {
		this.budegterSignDate = budegterSignDate;
	}
	
	
	@Column(name = "BUILDER_SIGN")
	public String getBuilderSign() {
		return ClobUtil.ClobToString(this.builderSign);
		
	}
	public void setBuilderSign(String builderSign) throws SerialException, SQLException {
		this.builderSign = ClobUtil.stringToClob(builderSign);
	}
	
	@Column(name = "GROUP_LEADER_SIGN")
	public String getGroupLeaderSign() {
		return ClobUtil.ClobToString(this.groupLeaderSign);
	}
	public void setGroupLeaderSign(String groupLeaderSign) throws SerialException, SQLException {
		this.groupLeaderSign = ClobUtil.stringToClob(groupLeaderSign);
	}
	
	
	@Column(name = "VICE_MINISTER_SIGN")
	public String getViceMinisterSign() {
		return ClobUtil.ClobToString(this.viceMinisterSign);
	}
	public void setViceMinisterSign(String viceMinisterSign) throws SerialException, SQLException {
		this.viceMinisterSign = ClobUtil.stringToClob(viceMinisterSign);
	}
	
	@Column(name = "MATERIAL_MANAGER_SIGN")
	public String getMaterialManagerSign() {
		return ClobUtil.ClobToString(this.materialManagerSign);
	}
	public void setMaterialManagerSign(String materialManagerSign) throws SerialException, SQLException {
		this.materialManagerSign = ClobUtil.stringToClob(materialManagerSign);
	}
	
	
	@Column(name = "MATERIAL_FINANCE_SIGN")
	public String getMaterialFinanceSign() {
		return ClobUtil.ClobToString(this.materialFinanceSign);
	}
	public void setMaterialFinanceSign(String materialFinanceSign) throws SerialException, SQLException {
		this.materialFinanceSign = ClobUtil.stringToClob(materialFinanceSign);
	}
	
	@Column(name = "ARCHIVES_SIGN")
	public String getArchivesSign() {
		return ClobUtil.ClobToString(this.archivesSign);
	}
	public void setArchivesSign(String archivesSign) throws SerialException, SQLException {
		this.archivesSign = ClobUtil.stringToClob(archivesSign);
	}
	
	@Column(name = "SUJGJ_SIGN")
	public String getSujgjSign() {
		return ClobUtil.ClobToString(this.sujgjSign);
	}
	public void setSujgjSign(String sujgjSign) throws SerialException, SQLException {
		this.sujgjSign = ClobUtil.stringToClob(sujgjSign);
	}
	
	@Column(name = "SUCSE_SIGN")
	public String getSucseSign() {
		return ClobUtil.ClobToString(this.sucseSign);
	}
	public void setSucseSign(String sucseSign) throws SerialException, SQLException {
		this.sucseSign = ClobUtil.stringToClob(sucseSign);
	}
	
	
	@Column(name = "DATA_CENTER_SIGN")
	public String getDataCenterSign() {
		return ClobUtil.ClobToString(this.dataCenterSign);
	}
	public void setDataCenterSign(String dataCenterSign) throws SerialException, SQLException {
		this.dataCenterSign = ClobUtil.stringToClob(dataCenterSign);
	}
	
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	@Column(name = "IS_COMPLETE")
	public String getIsComplete() {
		return isComplete;
	}
	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}
	
	@Column(name = "IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	
	@Transient
	public String getContributionModeDes() {
		return contributionModeDes;
	}
	public void setContributionModeDes(String contributionModeDes) {
		this.contributionModeDes = contributionModeDes;
	}
	
	@Transient
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	@Transient
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Transient
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
	@Transient
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Column(name = "SIGN_STATUS")
	public String getSignStatus() {
		return signStatus;
	}
	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}
	
	
	@Column(name = "RECEIVE_NUMBER")
	public String getReceiveNumber() {
		return receiveNumber;
	}
	public void setReceiveNumber(String receiveNumber) {
		this.receiveNumber = receiveNumber;
	}
	
	@Column(name = "WRITE_OFF_NUMBER")
	public String getWriteOffNumber() {
		return writeOffNumber;
	}
	public void setWriteOffNumber(String writeOffNumber) {
		this.writeOffNumber = writeOffNumber;
	}
	
	@Column(name = "SUPPLEMENT_NUMBER")
	public String getSupplementNumber() {
		return supplementNumber;
	}
	public void setSupplementNumber(String supplementNumber) {
		this.supplementNumber = supplementNumber;
	}
	
	@Column(name = "MATERIAL_OPINION")
	public String getMaterialOpinion() {
		return materialOpinion;
	}
	public void setMaterialOpinion(String materialOpinion) {
		this.materialOpinion = materialOpinion;
	}
	
	@Column(name = "VERSION")
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@Column(name = "ARCHIVES_OPINION")
	public String getArchivesOpinion() {
		return archivesOpinion;
	}
	public void setArchivesOpinion(String archivesOpinion) {
		this.archivesOpinion = archivesOpinion;
	}
	
	@Column(name = "SU_OPINION")
	public String getSuOpinion() {
		return suOpinion;
	}
	public void setSuOpinion(String suOpinion) {
		this.suOpinion = suOpinion;
	}
	
	@Column(name = "DATA_CENTER_OPINION")
	public String getDataCenterOpinion() {
		return dataCenterOpinion;
	}
	public void setDataCenterOpinion(String dataCenterOpinion) {
		this.dataCenterOpinion = dataCenterOpinion;
	}
	
	@Column(name = "FINANCE_OPINION")
	public String getFinanceOpinion() {
		return financeOpinion;
	}
	public void setFinanceOpinion(String financeOpinion) {
		this.financeOpinion = financeOpinion;
	}
	
	@Column(name = "FINANCE_SIGN")
	public String getFinanceSign() {
		return ClobUtil.ClobToString(this.financeSign);
	}
	public void setFinanceSign(String financeSign) throws SerialException, SQLException {
		this.financeSign = ClobUtil.stringToClob(financeSign);
	}
	
	
	@Column(name = "FINANCE_MANAGE_SIGN")
	public String getFinanceManageSign() {
		return ClobUtil.ClobToString(this.financeManageSign);
	}
	public void setFinanceManageSign(String financeManageSign) throws SerialException, SQLException {
		this.financeManageSign = ClobUtil.stringToClob(financeManageSign);
	}
	@Transient
	public List<Signature> getSign() {
		return sign;
	}
	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FINISH_DATE")
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	
	@Transient
	public String getNailMaterial() {
		return nailMaterial;
	}
	public void setNailMaterial(String nailMaterial) {
		this.nailMaterial = nailMaterial;
	}

	@Transient
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	@Transient
	public String getEndDCLegalAmount() {
		return endDCLegalAmount;
	}
	public void setEndDCLegalAmount(String endDCLegalAmount) {
		this.endDCLegalAmount = endDCLegalAmount;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Transient
	public String getProjStatusId() {
		return projStatusId;
	}
	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
	}
	
}
