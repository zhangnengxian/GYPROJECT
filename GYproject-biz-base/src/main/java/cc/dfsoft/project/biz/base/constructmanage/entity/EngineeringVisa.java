package cc.dfsoft.project.biz.base.constructmanage.entity;

import cc.dfsoft.project.biz.base.constructmanage.enums.StageProjectApplicationEnum;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * EngineeringVisa entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ENGINEERING_VISA")
public class EngineeringVisa implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4546702916835903606L;
	private String evId;				//工程签证ID
	private String projId;				//工程ID
	private String projNo;				//工程编号
	private String projName;			//工程名称
	private String projScaleDes;		//工程规模
	private String custName;			//建设单位
	private String constructionUnit;	//施工单位
	private String evContent;			//签证事由及内容
	private Date visaDate;				//签证日期
	private Clob suFieldPrincipal;		//-----备用字段
	private Clob suPrincipal;			//分包负责人
	private Clob cmoPrincipal;			//现场代表
	private Clob builder;				//施工员
	private Clob suJgj;					//监理工程师
	private Clob custPal;				//业主签字--------备用字段
	private Clob suCse;					//监理总工程师--------备用字段
	private Clob costControlDepartment;	//成本控制部	--------备用字段
	private String conNo;				//合同编号
	private String evState;				//签证状态
	private Date suAuditDate;			//监理审核日期
	private Date custAuditDate;			//项目负责人审核日期
	private Date ccdAuditDate;			//成本控制部审核日期---------备用字段
	private List<Signature> sign;		//签字相关数据
	private String drawName;			//简图名称
	private String menuDes; 			//菜单描述
	private String drawUrl;				//简图路径
	private Date builderAuditDate;		//现场代表签字时间
	private String mrAuditLevel;		//当前审核级别
	private String level;				//几级审核-----只读属性，用于审核页面显示按钮
	private String tenantId;			//租户ID
	private String orgId;				//数据权限ID
	private String evNo;				//签证编号
	private String evPosition;			//签证部位
	private String evReason;			//签证原因
	private String drawingNo;			//施工图号
	private List<VisaQuantitiesRecord> list;//页面--只读
	private String auditState;				//审核状态
	private String auditStateDes;			//审核状态描述---只读
	private BigDecimal quantitiesTotal;		//签证金额
	private BigDecimal submitAmount ;		//报送金额
	private String evType;					//签证类型
	private String backReason;				//退回原因
	private String suName;					//监理单位
	private String flag;					//签证有无审核不通过标记
	
	private String budgetId;				//预算调整ID--只读
	private String budgeter;				//调整预算人
	private String budgetAdjustDate;		//调整预日期
	private String remark;					//预算调整备注
	private Integer version;				//版本控制
	private String isSignComplete;			//是否完成签字 0:未完成，1：完成
	private String corpId;					//分公司id
	
	private String fileUrl;					//图片路径
	
	private String suOpinion;           //监理意见
	private String suResult;            //监理审核结果
	private String cmoPrincipalOpinion; //现场代表意见
	private String cmoPrincipalResult;  //现场代表审核结果
	private String isPass;              //是否通过
	
	private String projectType;			//工程类型-只读
	private String evTypeDesc;			//签证类型描述-只读
	private String budgeterAudit;      //预算审核人-只读
	
	private String cuReState;			//施工单位确认状态：-1-未确认，0-确认重新审核，1-确认无异议
	private String cuReReason;			//施工单位确认有异议原因
	
	private boolean isUploadFile;		//是否以上传附件
	private Date pushDate;				//签证推送日期
	private Date crontabDate;			//签证定时任务日期-用于 与系统当前日期相比较，超过有效时限则系统自动跳过施工单位签证1级审核
	private Date evBudgetDate;			//预算员审定签证金额日期
	private String evOverDes;			//签证审核超期描述
	private long overDay;				//剩余时长（天）
	/**是否上传预算书*/
	private String isBudgetBook;
	/**待办人*/
	private String todoer;
	
	@Column(name = "SU_OPINION")
	public String getSuOpinion() {
		return suOpinion;
	}

	public void setSuOpinion(String suOpinion) {
		this.suOpinion = suOpinion;
	}

	@Column(name = "SU_RESULT")
	public String getSuResult() {
		return suResult;
	}

	public void setSuResult(String suResult) {
		this.suResult = suResult;
	}

	@Column(name = "CMO_PRINCIPAL_OPINION")
	public String getCmoPrincipalOpinion() {
		return cmoPrincipalOpinion;
	}

	public void setCmoPrincipalOpinion(String cmoPrincipalOpinion) {
		this.cmoPrincipalOpinion = cmoPrincipalOpinion;
	}

	@Column(name = "CMO_PRINCIPAL_RESULT")
	public String getCmoPrincipalResult() {
		return cmoPrincipalResult;
	}

	public void setCmoPrincipalResult(String cmoPrincipalResult) {
		this.cmoPrincipalResult = cmoPrincipalResult;
	}

	@Column(name = "IS_PASS")
	public String getIsPass() {
		return isPass;
	}

	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}

	/** default constructor */
	public EngineeringVisa() {
	}
	
	// Property accessors
	@Id
	@Column(name = "EV_ID", unique = true)
	public String getEvId() {
		return this.evId;
	}

	public void setEvId(String evId) {
		this.evId = evId;
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

	@Column(name = "CUST_NAME")
	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Column(name = "CONSTRUCTION_UNIT")
	public String getConstructionUnit() {
		return this.constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	@Column(name = "EV_CONTENT")
	public String getEvContent() {
		return this.evContent;
	}

	public void setEvContent(String evContent) {
		this.evContent = evContent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VISA_DATE")
	public Date getVisaDate() {
		return this.visaDate;
	}

	public void setVisaDate(Date visaDate) {
		this.visaDate = visaDate;
	}

	@Lob
	@Column(name = "SU_FIELD_PRINCIPAL")
	public String getSuFieldPrincipal() {
		return ClobUtil.ClobToString(this.suFieldPrincipal);
	}

	public void setSuFieldPrincipal(String suFieldPrincipal) throws SerialException, SQLException {
		this.suFieldPrincipal = ClobUtil.stringToClob(suFieldPrincipal);
	}

	@Lob
	@Column(name = "CUST_PAL")
	public String getCustPal() {
		return ClobUtil.ClobToString(this.custPal);
	}

	public void setCustPal(String custPal) throws SerialException, SQLException {
		this.custPal = ClobUtil.stringToClob(custPal);
	}
	
	@Lob
	@Column(name = "SU_PRINCIPAL")
	public String getSuPrincipal() {
		return ClobUtil.ClobToString(this.suPrincipal);
	}

	public void setSuPrincipal(String suPrincipal) throws SerialException, SQLException {
		this.suPrincipal = ClobUtil.stringToClob(suPrincipal);
	}

	@Lob
	@Column(name = "CMO_PRINCIPAL")
	public String getCmoPrincipal() {
		return ClobUtil.ClobToString(this.cmoPrincipal);
	}

	public void setCmoPrincipal(String cmoPrincipal) throws SerialException, SQLException {
		this.cmoPrincipal = ClobUtil.stringToClob(cmoPrincipal);
	}

	@Lob
	@Column(name = "SU_JGJ")
	public String getSuJgj() {
		return ClobUtil.ClobToString(this.suJgj);
	}

	public void setSuJgj(String suJgj) throws SerialException, SQLException {
		this.suJgj = ClobUtil.stringToClob(suJgj);
	}

	@Lob
	@Column(name = "SU_CSE")
	public String getSuCse() {
		return ClobUtil.ClobToString(this.suCse);
	}

	public void setSuCse(String suCse) throws SerialException, SQLException {
		this.suCse = ClobUtil.stringToClob(suCse);
	}
	@Lob
	@Column(name = "COST_CONTROL_DEPARTMENT")
	public String getCostControlDepartment() {
		return ClobUtil.ClobToString(this.costControlDepartment);
	}

	public void setCostControlDepartment(String costControlDepartment)throws SerialException, SQLException {
		this.costControlDepartment = ClobUtil.stringToClob(costControlDepartment);
	}

	@Column(name = "CON_NO")
	public String getConNo() {
		return conNo;
	}

	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	
	@Column(name = "EV_STATE")
	public String getEvState() {
		return evState;
	}

	public void setEvState(String evState) {
		this.evState = evState;
	}
	
	@Column(name = "DRAW_NAME")
	public String getDrawName() {
		return drawName;
	}

	public void setDrawName(String drawName) {
		this.drawName = drawName;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "SU_AUDIT_DATE")
	public Date getSuAuditDate() {
		return suAuditDate;
	}

	public void setSuAuditDate(Date suAuditDate) {
		this.suAuditDate = suAuditDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CUST_AUDIT_DATE")
	public Date getCustAuditDate() {
		return custAuditDate;
	}

	public void setCustAuditDate(Date custAuditDate) {
		this.custAuditDate = custAuditDate;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "CCD_AUDIT_DATE")
	public Date getCcdAuditDate() {
		return ccdAuditDate;
	}

	public void setCcdAuditDate(Date ccdAuditDate) {
		this.ccdAuditDate = ccdAuditDate;
	}

	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSignature(List<Signature> sign) {
		this.sign = sign;
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
	
	@Transient
	public List<VisaQuantitiesRecord> getList() {
		return list;
	}

	public void setList(List<VisaQuantitiesRecord> list) {
		this.list = list;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "BUILDER_AUDIT_DATE")
	public Date getBuilderAuditDate() {
		return builderAuditDate;
	}

	public void setBuilderAuditDate(Date builderAuditDate) {
		this.builderAuditDate = builderAuditDate;
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
	
	@Column(name = "AUDIT_STATE")	
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	
	@Column(name = "QUANTITIES_TOTAL")	
	public BigDecimal getQuantitiesTotal() {
		return quantitiesTotal;
	}

	public void setQuantitiesTotal(BigDecimal quantitiesTotal) {
		this.quantitiesTotal = quantitiesTotal;
	}

	@Column(name = "SUBMIT_AMOUNT")
	public BigDecimal getSubmitAmount() {
		return submitAmount;
	}

	public void setSubmitAmount(BigDecimal submitAmount) {
		this.submitAmount = submitAmount;
	}

	@Column(name="TENANT_ID")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Column(name="ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name="EV_NO")
	public String getEvNo() {
		return evNo;
	}

	public void setEvNo(String evNo) {
		this.evNo = evNo;
	}

	@Column(name="EV_POSITION")
	public String getEvPosition() {
		return evPosition;
	}

	public void setEvPosition(String evPosition) {
		this.evPosition = evPosition;
	}

	@Column(name="EV_REASON")
	public String getEvReason() {
		return evReason;
	}

	public void setEvReason(String evReason) {
		this.evReason = evReason;
	}

	@Column(name="DRAWING_NO")
	public String getDrawingNo() {
		return drawingNo;
	}

	public void setDrawingNo(String drawingNo) {
		this.drawingNo = drawingNo;
	}
	
	@Lob
	@Column(name="BUILDER")
	public String getBuilder() {
		return ClobUtil.ClobToString(this.builder);
	}

	public void setBuilder(String builder)throws SerialException, SQLException {
		this.builder = ClobUtil.stringToClob(builder);
	}
	
	@Transient
	public String getAuditStateDes() {
		for(StageProjectApplicationEnum e: StageProjectApplicationEnum.values()) {
	   		if(e.getValue().equals(this.auditState)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setAuditStateDes(String auditStateDes) {
		this.auditStateDes = auditStateDes;
	}

	@Column(name="EV_TYPE")
	public String getEvType() {
		return evType;
	}

	public void setEvType(String evType) {
		this.evType = evType;
	}

	@Column(name="BACK_REASON")
	public String getBackReason() {
		return backReason;
	}

	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}

	@Column(name="SU_NAME")
	public String getSuName() {
		return suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	@Transient
	public String getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(String budgetId) {
		this.budgetId = budgetId;
	}

	@Transient
	public String getBudgeter() {
		return budgeter;
	}

	public void setBudgeter(String budgeter) {
		this.budgeter = budgeter;
	}

	@Transient
	public String getBudgetAdjustDate() {
		return budgetAdjustDate;
	}

	public void setBudgetAdjustDate(String budgetAdjustDate) {
		this.budgetAdjustDate = budgetAdjustDate;
	}

	@Transient
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name="FLAG")
	public String getFlag() {
		return flag;
	}
	
	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name="IS_SIGN_COMPLETE")
	public String getIsSignComplete() {
		return isSignComplete;
	}

	public void setIsSignComplete(String isSignComplete) {
		this.isSignComplete = isSignComplete;
	}
	
	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Transient
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Transient
	public String getEvTypeDesc() {
		return evTypeDesc;
	}

	public void setEvTypeDesc(String evTypeDesc) {
		this.evTypeDesc = evTypeDesc;
	}
	
	@Transient
	public String getBudgeterAudit() {
		return budgeterAudit;
	}

	public void setBudgeterAudit(String budgeterAudit) {
		this.budgeterAudit = budgeterAudit;
	}

	@Column(name="CU_RE_STATE")
	public String getCuReState() {
		return cuReState;
	}

	public void setCuReState(String cuReState) {
		this.cuReState = cuReState;
	}

	@Column(name="CU_RE_REASON")
	public String getCuReReason() {
		return cuReReason;
	}

	public void setCuReReason(String cuReReason) {
		this.cuReReason = cuReReason;
	}

	@Transient
	public boolean getIsUploadFile() {
		return isUploadFile;
	}

	public void setIsUploadFile(boolean isUploadFile) {
		this.isUploadFile = isUploadFile;
	}

	@Column(name="PUSH_DATE")
	@Temporal(TemporalType.DATE)
	public Date getPushDate() {
		return pushDate;
	}

	public void setPushDate(Date pushDate) {
		this.pushDate = pushDate;
	}

	@Column(name="CRONTAB_DATE")
	@Temporal(TemporalType.DATE)
	public Date getCrontabDate() {
		return crontabDate;
	}

	public void setCrontabDate(Date crontabDate) {
		this.crontabDate = crontabDate;
	}
	@Transient
	public String getIsBudgetBook() {
		return isBudgetBook;
	}

	public void setIsBudgetBook(String isBudgetBook) {
		this.isBudgetBook = isBudgetBook;
	}

	@Transient
	public Date getEvBudgetDate() {
		return evBudgetDate;
	}

	public void setEvBudgetDate(Date evBudgetDate) {
		this.evBudgetDate = evBudgetDate;
	}

	@Transient
	public String getEvOverDes() {
		return evOverDes;
	}

	public void setEvOverDes(String evOverDes) {
		this.evOverDes = evOverDes;
	}

	@Transient
	public long getOverDay() {
		return overDay;
	}

	public void setOverDay(long overDay) {
		this.overDay = overDay;
	}

	@Transient
	public String getTodoer() {
		return todoer;
	}

	public void setTodoer(String todoer) {
		this.todoer = todoer;
	}
	
}