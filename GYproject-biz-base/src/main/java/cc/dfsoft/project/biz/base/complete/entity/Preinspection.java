package cc.dfsoft.project.biz.base.complete.entity;

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
import javax.sql.rowset.serial.SerialException;

import cc.dfsoft.project.biz.base.project.entity.SignNoticeStandard;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;
/**
 * 
 * @author Yuanyx
 *
 */
@Entity
@Table(name = "PRE_INSPECTION")
public class Preinspection implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6744670806452957235L;
	private String piId;			//预验收ID
	private String projId;			//工程ID
	private String projNo;			//工程编号
	private String projAddr;		//工程地点
	private String projName;		//工程名称
	private String projScaleDes;	//工程规模--只读
	private String cmoName;			//施工单位
	private String suName;			//监理公司
	private Date piDate;			//预验日期
	
	private String sirOpinion;		//验收意见
	
	private Clob cmoDirector;		//现场代表
	private Clob projManager;		//项目经理
	private Clob suFieldJgj;		//现场监理
	
	private String conNo;			//合同编号
	private String scNo;			//施工合同编号
	
	private Date pmDate;			//项目经理签字日期
	private Date comDate;			//现场代表签字日期
	private Date cesDate;			//监理总工签字日期
	
	private String flag;			//保存时标记，0---暂存，1---保存并推送
	private String builder;			//施工员----页面显示
	
	private Date cpArriveDate;		//计划下达日期--只读
	private Date plannedStartDate;	//开工日期--只读
	private String planCompleteDate;	//计划竣工日期--只读
	private Date actualCompleteDate;//实际竣工日期
	
	private String deptName;	 	//部门名称--只读
	private String projectTypeDes;	//工程类型描述--只读
	private String contributionModeDes;//出资方式描述--只读
	private String corpName;	 	//分公司名称--只读
	
	private List<PreInspectionRecord> preInspectionRecords;//用于预验收记录保存
	private List<SignNoticeStandard>  signNoticeStandards;//用于返回页面签字post根据签字标准配置匹配
	private List<Signature> sign;		//签字
	private String isPrint;				//是否打印标记     0 已打印,1未打印
	
	private String isDelay;	   			//延期说明是否真实、准确
	private String delayRemark;			//延期备注
	
	private String isChange;			//设计变更手续是否齐全
	private String changeRemark;		//变更说明
	private String isBack;              //回退整改
	private String backRemark;          //回退整改备注
	
	private String projectType;			//工程类型-只读
	private String isDel;				//删除标记0-删除，1-未删除
	private String corpId;              //公司ID---只读
	private String isBuilderSign;		//有现场代表签字
	public Preinspection() {
	}
	
	@Id
	@Column(name = "PI_ID", unique = true)
	public String getPiId() {
		return piId;
	}
	public void setPiId(String piId) {
		this.piId = piId;
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
	
	@Column(name = "CMO_NAME")
	public String getCmoName() {
		return cmoName;
	}
	public void setCmoName(String cmoName) {
		this.cmoName = cmoName;
	}
	
	@Column(name = "SU_NAME")
	public String getSuName() {
		return suName;
	}
	public void setSuName(String suName) {
		this.suName = suName;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PI_DATE")
	public Date getPiDate() {
		return piDate;
	}
	public void setPiDate(Date piDate) {
		this.piDate = piDate;
	}
	
	
	
	@Column(name = "SIR_OPINION")
	public String getSirOpinion() {
		return sirOpinion;
	}
	public void setSirOpinion(String sirOpinion) {
		this.sirOpinion = sirOpinion;
	}
	
	@Column(name = "CMO_DIRECTOR")
	public String getCmoDirector() {
		return ClobUtil.ClobToString(this.cmoDirector);
	}
	public void setCmoDirector(String cmoDirector) throws SerialException, SQLException {
		this.cmoDirector = ClobUtil.stringToClob(cmoDirector);
	}
	
	@Column(name = "PROJ_MANAGER")
	public String getProjManager() {
		return ClobUtil.ClobToString(this.projManager);
	}
	public void setProjManager(String projManager) throws SerialException, SQLException {
		this.projManager = ClobUtil.stringToClob(projManager);
	}
	
	@Column(name = "SU_FIELD_JGJ")
	public String getSuFieldJgj() {
		return ClobUtil.ClobToString(this.suFieldJgj);
	}
	public void setSuFieldJgj(String suFieldJgj) throws SerialException, SQLException {
		this.suFieldJgj = ClobUtil.stringToClob(suFieldJgj);
	}
	
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PM_DATE")
	public Date getPmDate() {
		return pmDate;
	}

	public void setPmDate(Date pmDate) {
		this.pmDate = pmDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "COM_DATE")
	public Date getComDate() {
		return comDate;
	}

	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CES_DATE")
	public Date getCesDate() {
		return cesDate;
	}
	
	public void setCesDate(Date cesDate) {
		this.cesDate = cesDate;
	}
	
	
	
	@Transient
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Transient
	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}
	
	@Transient
	public Date getCpArriveDate() {
		return cpArriveDate;
	}

	public void setCpArriveDate(Date cpArriveDate) {
		this.cpArriveDate = cpArriveDate;
	}
	
	@Transient
	public Date getPlannedStartDate() {
		return plannedStartDate;
	}

	public void setPlannedStartDate(Date plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}
	
	@Transient
	public String getPlanCompleteDate() {
		return planCompleteDate;
	}

	public void setPlanCompleteDate(String planCompleteDate) {
		this.planCompleteDate = planCompleteDate;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ACTUAL_COMPLETE_DATE")
	public Date getActualCompleteDate() {
		return actualCompleteDate;
	}
	
	public void setActualCompleteDate(Date actualCompleteDate) {
		this.actualCompleteDate = actualCompleteDate;
	}
	
	@Transient
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	@Transient
	public String getProjScaleDes() {
		return projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	
	@Transient
	public List<PreInspectionRecord> getPreInspectionRecords() {
		return preInspectionRecords;
	}

	public void setPreInspectionRecords(List<PreInspectionRecord> preInspectionRecords) {
		this.preInspectionRecords = preInspectionRecords;
	}
	
	
	@Column(name = "IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	
	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	
	@Column(name = "CON_NO")
	public String getConNo() {
		return conNo;
	}

	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	
	@Column(name = "SC_NO")
	public String getScNo() {
		return scNo;
	}

	public void setScNo(String scNo) {
		this.scNo = scNo;
	}
	
	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}
	
	@Column(name = "IS_DELAY")
	public String getIsDelay() {
		return isDelay;
	}

	public void setIsDelay(String isDelay) {
		this.isDelay = isDelay;
	}
	
	@Column(name = "DELAY_REMARK")
	public String getDelayRemark() {
		return delayRemark;
	}

	public void setDelayRemark(String delayRemark) {
		this.delayRemark = delayRemark;
	}
	
	@Column(name = "IS_CHANGE")
	public String getIsChange() {
		return isChange;
	}

	public void setIsChange(String isChange) {
		this.isChange = isChange;
	}	
	
	@Column(name = "CHANGE_REMARK")
	public String getChangeRemark() {
		return changeRemark;
	}

	public void setChangeRemark(String changeRemark) {
		this.changeRemark = changeRemark;
	}

	@Column(name="IS_BACK")
	public String getIsBack() {
		return isBack;
	}

	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}

	@Column(name="BACK_REMARK")
	public String getBackRemark() {
		return backRemark;
	}

	public void setBackRemark(String backRemark) {
		this.backRemark = backRemark;
	}

	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(name="IS_DEL")
	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	@Transient
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Transient
	public String getIsBuilderSign() {
		return isBuilderSign;
	}

	public void setIsBuilderSign(String isBuilderSign) {
		this.isBuilderSign = isBuilderSign;
	}

	@Transient
	public List<SignNoticeStandard> getSignNoticeStandards() {
		return signNoticeStandards;
	}

	public void setSignNoticeStandards(List<SignNoticeStandard> signNoticeStandards) {
		this.signNoticeStandards = signNoticeStandards;
	}
	
	
	
}
