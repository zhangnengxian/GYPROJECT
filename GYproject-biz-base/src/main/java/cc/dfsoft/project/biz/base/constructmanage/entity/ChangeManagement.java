package cc.dfsoft.project.biz.base.constructmanage.entity;

import cc.dfsoft.project.biz.base.constructmanage.enums.ChangeTypeEnum;
import cc.dfsoft.project.biz.base.design.enums.DesignChangeStateEnum;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * ChangeManagement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CHANGE_MANAGEMENT")
public class ChangeManagement implements Serializable {
	
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4655991273098576088L;
	private String cmId;			//变更记录ID
	private String projId;			//工程ID
	private String projNo;			//工程编号
	private String projName;		//工程名称
	private String projAddr;		//施工地点
	private String projScaleDes;	//工程规模
	private String cmNo;			//变更编号
	private Date cmDate;			//变更日期
	private String cuReason;		//变更原因
	private String cuProposal;		//变更方案及建议
	private String constructionUnit;//施工单位
	private Clob cuPm;				//项目经理签字
	private String cuPhone;			//联系电话
	private String duOpinion;		//设计单位意见
	private Clob duPrincipal;		//设计单位签字
	private String suOpinion;		//监理单位意见
	private String suAuditResult;		//监理审核结果
	private Clob suPrincipal;		//监理单位签字
	private String custOpinion;		//建设单位意见
	private Clob custPrincipal;		//建设单位签字	
	private String projLtypeId;     //工程大类  用于页面预算调整页面
	private String cmState;		    //变更状态
	
	private String changeType;	    //变更类型
	
	private String changeContent;   //变更内容
	private String designer;        //设计人
	private String designerId;      //设计人Id
	private Clob checker;           //核对人
	private Clob auditer;           //审核人
	private Clob approvaler;        //项目批准人

	private String corpName;		//建设单位
	private String major;			//专业
	private List<Signature> sign;	//签字相关数据
	private String menuDes; 		//菜单描述
	private String drawName;        //简图名称
	private String drawUrl;         //简图路径
	
	private String managementOpinion;//施工管理处意见
	private Clob cmoPrincipal;		//施工管理处签字
	private String auditState;		//审核状态
	private Date auditDate;			//变更日期
	
	private String changeTypeDes;	//变更类型描述---页面显示
	private String mrAuditLevel;	//当前审核级别
	private Boolean overdue;		//是否逾期 true逾期 false未逾期
	
	
	private String drawFileType;		//附件类型
	private String cmReceiveUnit;		//变更接收单位
	private String cmAdvanceUnit;		//变更提出单位
	private String cmProjQuantity;		//工程量
	private String cmCost;				//花费
	private String cmTimeLimit;			//工期变化
	private String cuManagerDept;		//施工经理部
    private String duName;				//设计单位
	private String duLeader;			//设计单位负责人
	private String suName;				//监理单位
	private String suCes;				//总监理工程师
	private String custLeader;			//建设单位负责人
	private String custLeaderOpinion;			//建设单位负责人意见
	private String custLeaderAuditResult;			//建设单位审核结果

	private String duChief;				//设计公司所长
	private String ducAdvice;			//设计公司所长审批意见
	private String ducAuditStatus;		//设计公司所长审批结果
    private Date ducAuditDate;			//设计公司所长审批日期
    
    private String cmAdvanceStaffId;	//变更提出人ID
    private String cmAdvanceStaffName;	//变更提出人
    private Integer version;			//版本控制
    
    private String designChangeType;		//-1废弃,1未推送、2确认中、3待预算调整、4待签补充协议、5已签补充协议、6、已完成、
    private String designChangeTypeDes;		//-只读
    private String projectTypeDes;			//工程类型描述--只读
	private String contributionModeDes;		//出资方式描述--只读
	private String deptName;			    //业务部门
	private Date  applyDate;				//申请日期
	private String  applyReason;			//申请原因

	private String accListId;				//附件id-只读
	
	private String projectType;				//工程类型-只读
	private String cancelRemark;			//废弃备注
	private Date cancelDate;                //废弃日期
	private String cancelStaffName;         //废弃申请人
	private String cancelStaffId;          //废弃申请人Id
	
	private String changeMaterialFlag;		//是否需要设计变更材料： 0-不需要，1-需要
	
	private String materialFlag;			//是否已上传材料的标记-只读
	private String construction;     //施工员签字
	private String toDoer;     //代办人
	
	
	@Column(name = "CANCEL_STAFF_ID")
	public String getCancelStaffId() {
		return cancelStaffId;
	}

	public void setCancelStaffId(String cancelStaffId) {
		this.cancelStaffId = cancelStaffId;
	}

	// Constructors
	@Temporal(TemporalType.DATE)
	@Column(name = "CANCEL_DATE")
	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	@Column(name = "CANCEL_STAFF_NAME")
	public String getCancelStaffName() {
		return cancelStaffName;
	}

	public void setCancelStaffName(String cancelStaffName) {
		this.cancelStaffName = cancelStaffName;
	}

	/** default constructor */
	public ChangeManagement() {
	}

	// Property accessors
	@Id
	@Column(name = "CM_ID", unique = true)
	public String getCmId() {
		return this.cmId;
	}

	public void setCmId(String cmId) {
		this.cmId = cmId;
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

	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return this.projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	@Column(name = "PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return this.projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}

	@Column(name = "CM_NO", length = 30)
	public String getCmNo() {
		return this.cmNo;
	}

	public void setCmNo(String cmNo) {
		this.cmNo = cmNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CM_DATE")
	public Date getCmDate() {
		return this.cmDate;
	}

	public void setCmDate(Date cmDate) {
		this.cmDate = cmDate;
	}

	@Column(name = "CU_REASON")
	public String getCuReason() {
		return this.cuReason;
	}

	public void setCuReason(String cuReason) {
		this.cuReason = cuReason;
	}

	@Column(name = "CU_PROPOSAL")
	public String getCuProposal() {
		return this.cuProposal;
	}

	public void setCuProposal(String cuProposal) {
		this.cuProposal = cuProposal;
	}

	@Column(name = "CONSTRUCTION_UNIT")
	public String getConstructionUnit() {
		return this.constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	@Column(name = "CU_PHONE")
	public String getCuPhone() {
		return this.cuPhone;
	}

	public void setCuPhone(String cuPhone) {
		this.cuPhone = cuPhone;
	}

	@Column(name = "DU_OPINION")
	public String getDuOpinion() {
		return this.duOpinion;
	}

	public void setDuOpinion(String duOpinion) {
		this.duOpinion = duOpinion;
	}

	@Column(name = "CU_PM")
	public String getCuPm() {
		return ClobUtil.ClobToString(this.cuPm);
	}
	
	public void setCuPm(String cuPm) throws SerialException, SQLException {
		this.cuPm = ClobUtil.stringToClob(cuPm);
	}
	
	@Column(name = "DU_PRINCIPAL")
	public String getDuPrincipal() {
		return ClobUtil.ClobToString(this.duPrincipal);
	}

	public void setDuPrincipal(String duPrincipal) throws SerialException, SQLException {
		this.duPrincipal = ClobUtil.stringToClob(duPrincipal);
	}

	@Column(name = "SU_OPINION")
	public String getSuOpinion() {
		return this.suOpinion;
	}

	public void setSuOpinion(String suOpinion) {
		this.suOpinion = suOpinion;
	}

	@Column(name = "SU_PRINCIPAL")
	public String getSuPrincipal() {
		return ClobUtil.ClobToString(this.suPrincipal);
	}

	public void setSuPrincipal(String suPrincipal) throws SerialException, SQLException {
		this.suPrincipal = ClobUtil.stringToClob(suPrincipal);
	}

	@Column(name = "CUST_OPINION")
	public String getCustOpinion() {
		return this.custOpinion;
	}

	public void setCustOpinion(String custOpinion) {
		this.custOpinion = custOpinion;
	}

	@Column(name = "CUST_PRINCIPAL")
	public String getCustPrincipal() {
		return ClobUtil.ClobToString(this.custPrincipal);
	}

	public void setCustPrincipal(String custPrincipal) throws SerialException, SQLException {
		this.custPrincipal = ClobUtil.stringToClob(custPrincipal);
	}
	
	@Transient
	public String getProjLtypeId() {
		return projLtypeId;
	}

	public void setProjLtypeId(String projLtypeId) {
		this.projLtypeId = projLtypeId;
	}
	@Column(name = "CM_STATE")
	public String getCmState() {
		return cmState;
	}

	public void setCmState(String cmState) {
		this.cmState = cmState;
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
	@Column(name = "DRAW_NAME")
	public String getDrawName() {
		return drawName;
	}

	public void setDrawName(String drawName) {
		this.drawName = drawName;
	}
	@Transient
	public String getDrawUrl() {
		return drawUrl;
	}

	public void setDrawUrl(String drawUrl) {
		this.drawUrl = drawUrl;
	}

	@Column(name = "MANAGEMENT_OPINION")
	public String getManagementOpinion() {
		return managementOpinion;
	}

	public void setManagementOpinion(String managementOpinion) {
		this.managementOpinion = managementOpinion;
	}

	@Column(name = "CMO_PRINCIPAL")
	public String getCmoPrincipal() {
		return ClobUtil.ClobToString(this.cmoPrincipal);
	}

	public void setCmoPrincipal(String cmoPrincipal)throws SerialException, SQLException {
		this.cmoPrincipal = ClobUtil.stringToClob(cmoPrincipal);
	}

	@Column(name = "AUDIT_STATE")
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AUDIT_DATE")
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	@Column(name="DRAW_TYPE")
	public String getDrawFileType() {
		return drawFileType;
	}

	public void setDrawFileType(String drawFileType) {
		this.drawFileType = drawFileType;
	}
	
	@Column(name="CM_RECEIVE_UNIT")
	public String getCmReceiveUnit() {
		return cmReceiveUnit;
	}

	public void setCmReceiveUnit(String cmReceiveUnit) {
		this.cmReceiveUnit = cmReceiveUnit;
	}
	
	@Column(name="CM_ADVANCE_UNIT")
	public String getCmAdvanceUnit() {
		return cmAdvanceUnit;
	}

	public void setCmAdvanceUnit(String cmAdvanceUnit) {
		this.cmAdvanceUnit = cmAdvanceUnit;
	}

	@Column(name="CM_PROJ_QUANTITY")
	public String getCmProjQuantity() {
		return cmProjQuantity;
	}

	public void setCmProjQuantity(String cmProjQuantity) {
		this.cmProjQuantity = cmProjQuantity;
	}

	@Column(name="CM_COST")
	public String getCmCost() {
		return cmCost;
	}

	public void setCmCost(String cmCost) {
		this.cmCost = cmCost;
	}

	@Column(name="CM_TIME_LIMIT")
	public String getCmTimeLimit() {
		return cmTimeLimit;
	}

	public void setCmTimeLimit(String cmTimeLimit) {
		this.cmTimeLimit = cmTimeLimit;
	}

	@Column(name="CU_MANAGER_DEPT")
	public String getCuManagerDept() {
		return cuManagerDept;
	}

	public void setCuManagerDept(String cuManagerDept) {
		this.cuManagerDept = cuManagerDept;
	}

	@Column(name="DU_NAME")
	public String getDuName() {
		return duName;
	}

	public void setDuName(String duName) {
		this.duName = duName;
	}

	@Column(name="DU_LEADER")
	public String getDuLeader() {
		return duLeader;
	}

	public void setDuLeader(String duLeader) {
		this.duLeader = duLeader;
	}

	@Column(name="SU_NAME")
	public String getSuName() {
		return suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	@Column(name="SU_CES")
	public String getSuCes() {
		return suCes;
	}

	public void setSuCes(String suCes) {
		this.suCes = suCes;
	}

	@Column(name="CUST_LEADER")
	public String getCustLeader() {
		return custLeader;
	}

	public void setCustLeader(String custLeader) {
		this.custLeader = custLeader;
	}

	@Column(name="DU_CHIEF")
	public String getDuChief() {
		return duChief;
	}

	public void setDuChief(String duChief) {
		this.duChief = duChief;
	}

	@Column(name="DUC_ADVICE")
	public String getDucAdvice() {
		return ducAdvice;
	}

	public void setDucAdvice(String ducAdvice) {
		this.ducAdvice = ducAdvice;
	}

	@Column(name="DUC_AUDIT_STATUS")
	public String getDucAuditStatus() {
		return ducAuditStatus;
	}

	public void setDucAuditStatus(String ducAuditStatus) {
		this.ducAuditStatus = ducAuditStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="DUC_AUDIT_DATE")
	public Date getDucAuditDate() {
		return ducAuditDate;
	}
	
	public void setDucAuditDate(Date ducAuditDate) {
		this.ducAuditDate = ducAuditDate;
	}
	
	@Transient
	public String getChangeTypeDes() {
		for(ChangeTypeEnum e: ChangeTypeEnum.values()) {
	   		if(e.getValue().equals(this.changeType)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setChangeTypeDes(String changeTypeDes) {
		this.changeTypeDes = changeTypeDes;
	}

	@Transient
	public String getMrAuditLevel() {
		return mrAuditLevel;
	}

	public void setMrAuditLevel(String mrAuditLevel) {
		this.mrAuditLevel = mrAuditLevel;
	}

	@Transient
	public Boolean getOverdue() {
		return overdue;
	}

	public void setOverdue(Boolean overdue) {
		this.overdue = overdue;
	}

	@Column(name="CM_ADVANCE_STAFF_ID")
	public String getCmAdvanceStaffId() {
		return cmAdvanceStaffId;
	}

	public void setCmAdvanceStaffId(String cmAdvanceStaffId) {
		this.cmAdvanceStaffId = cmAdvanceStaffId;
	}

	@Column(name="CM_ADVANCE_STAFF_NAME")
	public String getCmAdvanceStaffName() {
		return cmAdvanceStaffName;
	}

	public void setCmAdvanceStaffName(String cmAdvanceStaffName) {
		this.cmAdvanceStaffName = cmAdvanceStaffName;
	}

	@Column(name = "CORP_NAME")
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	@Column(name = "MAJOR")
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "CHANGE_TYPE")
	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	@Column(name = "CHANGE_CONTENT")
	public String getChangeContent() {
		return changeContent;
	}

	public void setChangeContent(String changeContent) {
		this.changeContent = changeContent;
	}

	@Column(name = "DESIGNER")
	public String getDesigner() {
		return designer;
	}

	public void setDesigner(String designer){
		this.designer = designer;
	}

	@Column(name = "CHECKER")
	public String getChecker() {
		return ClobUtil.ClobToString(this.checker);
	}

	public void setChecker(String checker) throws SerialException, SQLException {
		this.checker = ClobUtil.stringToClob(checker);
	}

	@Column(name = "AUDITER")
	public String getAuditer() {
		return ClobUtil.ClobToString(this.auditer);
	}

	public void setAuditer(String auditer) throws SerialException, SQLException {
		this.auditer = ClobUtil.stringToClob(auditer);
	}


	@Column(name = "APPROVALER")
	public String getApprovaler() {
		return ClobUtil.ClobToString(this.approvaler);
	}

	public void setApprovaler(String approvaler) throws SerialException, SQLException {
		this.approvaler = ClobUtil.stringToClob(approvaler);
	}

	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@Column(name="DESIGN_CHANGE_TYPE")
	public String getDesignChangeType() {
		return designChangeType;
	}

	public void setDesignChangeType(String designChangeType) {
		this.designChangeType = designChangeType;
	}

	@Transient
	public String getProjectTypeDes() {
		return projectTypeDes;
	}

	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}
	@Transient
	public String getContributionModeDes() {
		return contributionModeDes;
	}

	public void setContributionModeDes(String contributionModeDes) {
		this.contributionModeDes = contributionModeDes;
	}
	@Transient
	public String getDesignChangeTypeDes() {
		for(DesignChangeStateEnum e: DesignChangeStateEnum.values()) {
	   		if(e.getValue().equals(this.designChangeType)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setDesignChangeTypeDes(String designChangeTypeDes) {
		this.designChangeTypeDes = designChangeTypeDes;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="APPLY_DATE")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	@Column(name="APPLY_REASON")
	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	
	@Transient
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Column(name="DESIGNER_ID")
	public String getDesignerId() {
		return designerId;
	}

	public void setDesignerId(String designerId) {
		this.designerId = designerId;
	}
	
	@Transient
	public String getAccListId() {
		return accListId;
	}

	public void setAccListId(String accListId) {
		this.accListId = accListId;
	}

	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(name="CANCEL_REMARK")
	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}

	@Column(name="CHANGE_MATERIAL_FLAG")
	public String getChangeMaterialFlag() {
		return changeMaterialFlag;
	}

	public void setChangeMaterialFlag(String changeMaterialFlag) {
		this.changeMaterialFlag = changeMaterialFlag;
	}

	@Column(name="SU_AUDIT_RESULT")
	public String getSuAuditResult() {
		return suAuditResult;
	}

	public void setSuAuditResult(String suAuditResult) {
		this.suAuditResult = suAuditResult;
	}

	@Column(name="CUSTLEADER_OPINION")
	public String getCustLeaderOpinion() {
		return custLeaderOpinion;
	}

	public void setCustLeaderOpinion(String custLeaderOpinion) {
		this.custLeaderOpinion = custLeaderOpinion;
	}
	@Column(name="CUSTLEADER_AUDIT_RESULT")
	public String getCustLeaderAuditResult() {
		return custLeaderAuditResult;
	}

	public void setCustLeaderAuditResult(String custLeaderAuditResult) {
		this.custLeaderAuditResult = custLeaderAuditResult;
	}

	@Column(name="CONSTRUCTION")
	public String getConstruction() {
		return construction;
	}

	public void setConstruction(String construction) {
		this.construction = construction;
	}

	@Transient
	public String getMaterialFlag() {
		return materialFlag;
	}

	public void setMaterialFlag(String materialFlag) {
		this.materialFlag = materialFlag;
	}

	@Transient
	public String getToDoer() {
		return toDoer;
	}

	public void setToDoer(String toDoer) {
		this.toDoer = toDoer;
	}
	
}