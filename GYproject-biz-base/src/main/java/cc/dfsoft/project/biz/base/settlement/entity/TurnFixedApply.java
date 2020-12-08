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
import javax.persistence.Version;
import javax.sql.rowset.serial.SerialException;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

/**
 * TurnFixedApply entity.
 * 转固
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TURN_FIXED_APPLY")
public class TurnFixedApply implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2728118886111194903L;
	private String tfaId;				//转固主键Id
	private String projId;				//工程Id
	private String projNo;				//工程编号
	private String projName;			//工程名称
	private String projAddr;			//工程地点
	private String cuName;				//施工单位
	private String scNo;				//施工合同编号
	private Date tfaDate;				//申请日期
	private BigDecimal projTotalCost;	//总工程款
	private BigDecimal projCost;		//工程款
	private BigDecimal brokenCost;		//破路费
	private BigDecimal materialCost;	//材料费
	private BigDecimal inspectionCost;	//工程检测费
	private BigDecimal interest;		//利息
	private BigDecimal constructionCost;//建管费
	private BigDecimal otherCost;		//其他费用
	private String materialQuality;		//管道材质
	private String diameter;			//管径大小
	private String thickness;			//壁厚 
	private String length;				//长度
	private String remark;				//备注
	private String corpId;				//分公司id
	private String corpName;			//分公司名称
	private String projectTypeDes;		//工程类型
	private String contributionModeDes;	//出资方式描述
	private String deptName;			//部门名称
	private Clob treasurer;				//财务部签字
	private Integer version;			//版本控制
	private String isPrint; 			//是否打印
	
	private String mrAuditLevel; 		//已审核级别----只读属性
	private String level;				//几级审核------只读属性
	private Boolean overdue;			//是否逾期 true逾期 false未逾期
	private List<Signature> sign;		//签字相关数据
	private String flag;				//0:保存，1：推送
	
	private String ProjectType;			//工程类型-只读
	
	/** default constructor */
	public TurnFixedApply() {
	}

	// Property accessors
	@Id
	@Column(name = "TFA_ID", unique = true)
	public String getTfaId() {
		return this.tfaId;
	}

	public void setTfaId(String tfaId) {
		this.tfaId = tfaId;
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

	@Column(name = "CU_NAME")
	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}

	@Column(name = "SC_NO")
	public String getScNo() {
		return scNo;
	}

	public void setScNo(String scNo) {
		this.scNo = scNo;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "TFA_DATE")
	public Date getTfaDate() {
		return tfaDate;
	}

	public void setTfaDate(Date tfaDate) {
		this.tfaDate = tfaDate;
	}
	
	@Column(name = "PROJ_TOTAL_COST")
	public BigDecimal getProjTotalCost() {
		return projTotalCost;
	}

	public void setProjTotalCost(BigDecimal projTotalCost) {
		this.projTotalCost = projTotalCost;
	}
	
	@Column(name = "PROJ_COST")
	public BigDecimal getProjCost() {
		return projCost;
	}

	public void setProjCost(BigDecimal projCost) {
		this.projCost = projCost;
	}

	@Column(name = "BROKEN_COST")
	public BigDecimal getBrokenCost() {
		return brokenCost;
	}

	public void setBrokenCost(BigDecimal brokenCost) {
		this.brokenCost = brokenCost;
	}

	@Column(name = "MATERIAL_COST")
	public BigDecimal getMaterialCost() {
		return materialCost;
	}

	public void setMaterialCost(BigDecimal materialCost) {
		this.materialCost = materialCost;
	}

	@Column(name = "INSPECTION_COST")
	public BigDecimal getInspectionCost() {
		return inspectionCost;
	}

	public void setInspectionCost(BigDecimal inspectionCost) {
		this.inspectionCost = inspectionCost;
	}

	@Column(name = "INTEREST")
	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	@Column(name = "CONSTRUCTION_COST")
	public BigDecimal getConstructionCost() {
		return constructionCost;
	}

	public void setConstructionCost(BigDecimal constructionCost) {
		this.constructionCost = constructionCost;
	}
	
	@Column(name = "OTHER_COST")
	public BigDecimal getOtherCost() {
		return this.otherCost;
	}

	public void setOtherCost(BigDecimal otherCost) {
		this.otherCost = otherCost;
	}

	@Column(name = "MATERIAL_QUALITY")
	public String getMaterialQuality() {
		return materialQuality;
	}

	public void setMaterialQuality(String materialQuality) {
		this.materialQuality = materialQuality;
	}

	@Column(name = "DIAMETER")
	public String getDiameter() {
		return diameter;
	}

	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}

	@Column(name = "THICKNESS")
	public String getThickness() {
		return thickness;
	}

	public void setThickness(String thickness) {
		this.thickness = thickness;
	}

	@Column(name = "LENGTH")
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "CORP_NAME")
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	@Column(name = "CONTRIBUTION_MODE_DES")
	public String getContributionModeDes() {
		return contributionModeDes;
	}

	public void setContributionModeDes(String contributionModeDes) {
		this.contributionModeDes = contributionModeDes;
	}

	@Column(name = "DEPT_NAME")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Column(name = "PROJECT_TYPE_DES")
	public String getProjectTypeDes() {
		return projectTypeDes;
	}

	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}

	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
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

	@Transient
	public Boolean getOverdue() {
		return overdue;
	}

	public void setOverdue(Boolean overdue) {
		this.overdue = overdue;
	}

	@Column(name = "TREASURER")
	public String getTreasurer() {
		return ClobUtil.ClobToString(treasurer);
	}

	public void setTreasurer(String treasurer) throws SerialException, SQLException {
		this.treasurer = ClobUtil.stringToClob(treasurer);
	}

	@Version
	@Column(name = "VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSignature(List<Signature> sign) {
		this.sign = sign;
	}

	@Transient
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	@Transient
	public String getProjectType() {
		return ProjectType;
	}

	public void setProjectType(String projectType) {
		ProjectType = projectType;
	}
	
}