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

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

/**
 * SelfInspectionList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SELF_INSPECTION_LIST")
public class SelfInspectionList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9075342843231935526L;
	// Fields

	private String silId;				//自检单ID
	private String projId;				//工程ID
	private String projNo;				//工程编号
	private String projName;			//工程名称
	private String cmoName;				//施工管理处 --由施工单位改为分包单位  
	private Date silDate;				//自验日期
	private String projManagerOpinion;	//项目经理意见  --由项目经理意见改为--施工单位意见
	private Clob projManager;			//项目经理 
	
	private List<SelfInspectionRecord> selfInspectionRecords;//用于自检记录保存
	private List<Signature> sign;		//签字相关数据
	
	private String flag;				//0--暂存，1--推送
	
	private String suName;				//监理单位
	private String projAddr;			//工程地点
	private String projScaleDes;		//工程规模
	
	private String deptName;	 		//部门名称---只读
	private String projectTypeDes;		//工程类型描述---只读
	private String contributionModeDes;	//出资方式描述---只读
	private String corpName;	 		//分公司名称---只读
	
	private String isPrint;				//是否打印标记 -0 已打印,1未打印
	private Date pushDate;				//推送日期，用于为竣工报告指定默认时间
	// Constructors
	
	private String projectType;			//工程类型-只读
	private String isBack;				//预验整改回退状态-只读
	private String backRemark;			//预验整改回退备注-只读
	private String isDel;				//删除标记0-已删除，1-未删除
	
	/** default constructor */
	public SelfInspectionList() {
	}

	// Property accessors
	@Id
	@Column(name = "SIL_ID", unique = true)
	public String getSilId() {
		return this.silId;
	}

	public void setSilId(String silId) {
		this.silId = silId;
	}

	@Column(name = "PROJ_ID", nullable = false)
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

	@Column(name = "CMO_NAME")
	public String getCmoName() {
		return this.cmoName;
	}

	public void setCmoName(String cmoName) {
		this.cmoName = cmoName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SIL_DATE")
	public Date getSilDate() {
		return this.silDate;
	}

	public void setSilDate(Date silDate) {
		this.silDate = silDate;
	}

	@Column(name = "PROJ_MANAGER_OPINION")
	public String getProjManagerOpinion() {
		return this.projManagerOpinion;
	}

	public void setProjManagerOpinion(String projManagerOpinion) {
		this.projManagerOpinion = projManagerOpinion;
	}

	@Column(name = "PROJ_MANAGER")
	public String getProjManager() {
		return ClobUtil.ClobToString(this.projManager);
	}

	public void setProjManager(String projManager) throws SerialException, SQLException {
		this.projManager = ClobUtil.stringToClob(projManager);
	}
	
	


	@Transient
	public List<SelfInspectionRecord> getSelfInspectionRecords() {
		return selfInspectionRecords;
	}

	public void setSelfInspectionRecords(List<SelfInspectionRecord> selfInspectionRecords) {
		this.selfInspectionRecords = selfInspectionRecords;
	}
	
	@Transient
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}
	

	@Column(name = "SU_NAME")
	public String getSuName() {
		return suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}
	
	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	
	@Transient
	public String getProjScaleDes() {
		return projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
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
	
	@Column(name = "IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="PUSH_DATE")
	public Date getPushDate() {
		return pushDate;
	}

	public void setPushDate(Date pushDate) {
		this.pushDate = pushDate;
	}

	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Transient
	public String getIsBack() {
		return isBack;
	}

	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}

	@Transient
	public String getBackRemark() {
		return backRemark;
	}

	public void setBackRemark(String backRemark) {
		this.backRemark = backRemark;
	}

	@Column(name="IS_DEL")
	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	
	
}