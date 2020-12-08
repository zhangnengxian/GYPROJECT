package cc.dfsoft.project.biz.base.subpackage.entity;

import cc.dfsoft.project.biz.base.baseinfo.dto.WorkDayDto;
import cc.dfsoft.project.biz.base.contract.entity.PayType;
import cc.dfsoft.project.biz.base.contract.enums.ModifyStatusEnum;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * SubContract entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SUB_CONTRACT")
public class SubContract implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2820058793472251970L;
	// Fields

	private String scId;					//分包合同ID   
	private String scNo;					//分包合同编号
	private String projId;					//工程ID
	private String projNo;					//工程编号
	private String projName;				//工程名称
	private String projScaleDes;			//工程规模
	private String projAddr;				//工程地点

	private String deptId;		            //甲方ID
	private String deptName;				//甲方名称
	private String projCompDirector;		//甲方委托代理人
	private String projCompPm;				//甲方项目经理
	private String gasComLegalRepresent;	//甲方代表
	private String gasComPhone;				//甲方联系电话
	
	private String cuId;					//分包单位ID
	private String cuName;					//乙方名称
	private String cuLegalRepresent;		//法定代表
	private String cuDirector;				//乙方委托代表
	
	private String cuPm;					//项目经理
	private String cuPmPhone;				//联系电话
	private String certificationName;		//资格证名称
	private String certificationNo;			//资格证编号
	private String scScope;					//承包范围
	private String scType;					//支付方式 -合同款
	private Date scPlannedStartDate;		//计划开工日期
	private String scPlannedEndDate;			//计划竣工日期-有填写配合施工的 ，所以日期修改为String类型
	private String scPlannedTotalDays;		//计划天数
	private String qualityStandar;			//质量标准
	private BigDecimal scAmount;			//协议价款
	private Date scSignDate;				//签订日期-用于报表显示（先会审交底的取第一次会审交底的日期）
	private String conAgent;				//经办人
	private String remark;					//备注
	private String payType; 				//支付方式 -预付款
	private String increment;				//施工合同税率

	//分包协议页面保存分包协议
	private String unitName;				//单位名称
	private String unitDirector;			//委托代表
	private String unitPhone;   			//联系电话
	private BigDecimal quAmount;			//协议价款----只读属性，来自预算工程量
	private String legalAmount;				//打印合同时用
	
	private String mrAuditLevel; 			//已审核级别----只读属性，用于合同审核屏
	private String level;        			//几级审核------只读属性，用于合同审核页面显示按钮
	
	private Boolean overdue;				//是否逾期 true逾期 false未逾期
	private Integer overDay;				//逾期天数
	
	private String subFlag; 
	private String flag; 					//保存标记，0---只暂存分包合同，1---保存并推送同时生成记录和流水
	
	private String builder;					//甲方代表----页面显示
	
	private String cuResponsiblePerson;     //施工员(乙方现场负责人)
	private String cuResponsiblePhone; //联系方式

	private String isPrint;					//是否打印标记     0 已打印,1未打印

	private List<PayType> payTypes;			//支付方式（预付款、进度款、合同款）-----------页面select显示

	private String progressType; 				//支付方式 -进度款
	private String projLtypeId; 				//项目类型

	private String contractMode; 				//承包方式
	
	
	private String contributionModeDes;	//出资方式
	private String contributionMode;	//出资方式
	private String corpName;			//分公司名称
	private String projectTypeDes;		//工程类型
	private String departmentName;	    //业务部门
	
	private String projectType;			//工程类型
	private String contractMethod;		//建筑服务
	private String contractMethod1;		//建筑服务（专门用来分辨施工计划的是否甲供到这不可修改）
	private String modifyState;			//修改状态null-未修改，0-修改审核中，1-审核通过，2-审核不通过
	private String modifyStateDes;		//状态描述 -只读
	private String payRate;             //预付款比例
	
	private String signBack;			//标记
	private Date operateDate;			//施工合同实际签订日期
	private WorkDayDto workDayDto;		//只读
	
	private String corpId;				//分公司id
	
	@Transient
	public String getProjLtypeId() {
		return projLtypeId;
	}

	public void setProjLtypeId(String projLtypeId) {
		this.projLtypeId = projLtypeId;
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

	// Constructors

	/** default constructor */
	public SubContract() {
	}

	// Property accessors
	@Id
	@Column(name = "SC_ID", unique = true)
	public String getScId() {
		return this.scId;
	}

	public void setScId(String scId) {
		this.scId = scId;
	}

	@Column(name = "SC_NO")
	public String getScNo() {
		return this.scNo;
	}

	public void setScNo(String scNo) {
		this.scNo = scNo;
	}

	@Column(name = "IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
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

	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return this.projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	
	@Column(name = "DEPT_ID")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	@Column(name = "DEPT_NAME")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "PROJ_COMP_DIRECTOR")
	public String getProjCompDirector() {
		return this.projCompDirector;
	}

	public void setProjCompDirector(String projCompDirector) {
		this.projCompDirector = projCompDirector;
	}

	@Column(name = "PROJ_COMP_PM")
	public String getProjCompPm() {
		return this.projCompPm;
	}

	public void setProjCompPm(String projCompPm) {
		this.projCompPm = projCompPm;
	}

	@Column(name = "GAS_COM_LEGAL_REPRESENT")
	public String getGasComLegalRepresent() {
		return this.gasComLegalRepresent;
	}

	public void setGasComLegalRepresent(String gasComLegalRepresent) {
		this.gasComLegalRepresent = gasComLegalRepresent;
	}

	@Column(name = "GAS_COM_PHONE")
	public String getGasComPhone() {
		return this.gasComPhone;
	}

	public void setGasComPhone(String gasComPhone) {
		this.gasComPhone = gasComPhone;
	}

	@Column(name = "CU_NAME")
	public String getCuName() {
		return this.cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}

	@Column(name = "CU_LEGAL_REPRESENT")
	public String getCuLegalRepresent() {
		return this.cuLegalRepresent;
	}

	public void setCuLegalRepresent(String cuLegalRepresent) {
		this.cuLegalRepresent = cuLegalRepresent;
	}

	@Column(name = "CU_DIRECTOR")
	public String getCuDirector() {
		return this.cuDirector;
	}

	public void setCuDirector(String cuDirector) {
		this.cuDirector = cuDirector;
	}

	@Column(name = "CU_PM")
	public String getCuPm() {
		return this.cuPm;
	}

	public void setCuPm(String cuPm) {
		this.cuPm = cuPm;
	}

	@Column(name = "CU_PM_PHONE")
	public String getCuPmPhone() {
		return this.cuPmPhone;
	}

	public void setCuPmPhone(String cuPmPhone) {
		this.cuPmPhone = cuPmPhone;
	}

	@Column(name = "CERTIFICATION_NAME")
	public String getCertificationName() {
		return this.certificationName;
	}

	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}

	@Column(name = "CERTIFICATION_NO")
	public String getCertificationNo() {
		return this.certificationNo;
	}

	public void setCertificationNo(String certificationNo) {
		this.certificationNo = certificationNo;
	}

	@Column(name = "SC_SCOPE")
	public String getScScope() {
		return this.scScope;
	}

	public void setScScope(String scScope) {
		this.scScope = scScope;
	}

	@Column(name = "SC_TYPE")
	public String getScType() {
		return this.scType;
	}

	public void setScType(String scType) {
		this.scType = scType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SC_PLANNED_START_DATE")
	public Date getScPlannedStartDate() {
		return this.scPlannedStartDate;
	}

	public void setScPlannedStartDate(Date scPlannedStartDate) {
		this.scPlannedStartDate = scPlannedStartDate;
	}

	@Column(name = "SC_PLANNED_END_DATE")
	public String getScPlannedEndDate() {
		return this.scPlannedEndDate;
	}

	public void setScPlannedEndDate(String scPlannedEndDate) {
		this.scPlannedEndDate = scPlannedEndDate;
	}

	@Column(name = "SC_PLANNED_TOTAL_DAYS")
	public String getScPlannedTotalDays() {
		return this.scPlannedTotalDays;
	}

	public void setScPlannedTotalDays(String scPlannedTotalDays) {
		this.scPlannedTotalDays = scPlannedTotalDays;
	}

	@Column(name = "QUALITY_STANDAR")
	public String getQualityStandar() {
		return this.qualityStandar;
	}

	public void setQualityStandar(String qualityStandar) {
		this.qualityStandar = qualityStandar;
	}

	@Column(name = "SC_AMOUNT", precision = 15)
	public BigDecimal getScAmount() {
		return this.scAmount;
	}

	public void setScAmount(BigDecimal scAmount) {
		this.scAmount = scAmount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SC_SIGN_DATE")
	public Date getScSignDate() {
		return this.scSignDate;
	}

	public void setScSignDate(Date scSignDate) {
		this.scSignDate = scSignDate;
	}

	@Column(name = "CON_AGENT")
	public String getConAgent() {
		return this.conAgent;
	}

	public void setConAgent(String conAgent) {
		this.conAgent = conAgent;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "CU_ID")
	public String getCuId() {
		return cuId;
	}

	public void setCuId(String cuId) {
		this.cuId = cuId;
	}
	
	@Transient
	public String getUnitName() {
		return unitName;
	}
	
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	@Transient
	public String getUnitDirector() {
		return unitDirector;
	}

	public void setUnitDirector(String unitDirector) {
		this.unitDirector = unitDirector;
	}
	
	@Transient
	public String getUnitPhone() {
		return unitPhone;
	}
	
	public void setUnitPhone(String unitPhone) {
		this.unitPhone = unitPhone;
	}
	@Transient
	public BigDecimal getQuAmount() {
		return quAmount;
	}

	public void setQuAmount(BigDecimal quAmount) {
		this.quAmount = quAmount;
	}
	
	@Column(name = "PAY_TYPE")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@Transient
	public String getLegalAmount() {
		return legalAmount;
	}

	public void setLegalAmount(String legalAmount) {
		this.legalAmount = legalAmount;
	}

	@Transient
	public String getSubFlag() {
		return subFlag;
	}

	public void setSubFlag(String subFlag) {
		this.subFlag = subFlag;
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

	@Transient
	public Integer getOverDay() {
		return overDay;
	}

	public void setOverDay(Integer overDay) {
		this.overDay = overDay;
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
	
	@Column(name = "CU_RESPONSIBLE_PERSON")
	public String getCuResponsiblePerson() {
		return cuResponsiblePerson;
	}

	public void setCuResponsiblePerson(String cuResponsiblePerson) {
		this.cuResponsiblePerson = cuResponsiblePerson;
	}
	
	@Column(name = "CU_RESPONSIBLE_PHONE")
	public String getCuResponsiblePhone() {
		return cuResponsiblePhone;
	}

	public void setCuResponsiblePhone(String cuResponsiblePhone) {
		this.cuResponsiblePhone = cuResponsiblePhone;
	}

	@Column(name = "CONTRACT_MODE")
	public String getContractMode() {
		return contractMode;
	}

	public void setContractMode(String contractMode) {
		this.contractMode = contractMode;
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

	@Column(name = "CONTRACT_METHOD")
	public String getContractMethod() {
		return contractMethod;
	}

	public void setContractMethod(String contractMethod) {
		this.contractMethod = contractMethod;
	}
	@Transient
	public String getContractMethod1() {
		return contractMethod1;
	}

	public void setContractMethod1(String contractMethod1) {
		this.contractMethod1 = contractMethod1;
	}

	@Column(name="MODIFY_STATE")
	public String getModifyState() {
		return modifyState;
	}

	public void setModifyState(String modifyState) {
		this.modifyState = modifyState;
	}
	
	@Column(name="INCREMENT")
	public String getIncrement() {
		return increment;
	}

	public void setIncrement(String increment) {
		this.increment = increment;
	}

	@Transient
	public String getModifyStateDes() {
		if(null==this.modifyState||""==this.modifyState){
			return ModifyStatusEnum.NO_MODIFY.getMessage();
		}else {
			for(ModifyStatusEnum e: ModifyStatusEnum.values()) {
		   		if(e.getValue().equals(this.modifyState)) {
		   			return e.getMessage();
		   		}
		   	}
			return "";
		}
	}

	public void setModifyStateDes(String modifyStateDes) {
		this.modifyStateDes = modifyStateDes;
	}

	@Transient
	public String getSignBack() {
		return signBack;
	}

	public void setSignBack(String signBack) {
		this.signBack = signBack;
	}

	@Column(name="OPERATE_DATE")
	@Temporal(TemporalType.DATE)
	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name="PAY_RATE")
	public String getPayRate() {
		return payRate;
	}

	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}

	@Transient
	public WorkDayDto getWorkDayDto() {
		return workDayDto;
	}

	public void setWorkDayDto(WorkDayDto workDayDto) {
		this.workDayDto = workDayDto;
	}
	
	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Transient
	public String getContributionMode() {
		return contributionMode;
	}

	public void setContributionMode(String contributionMode) {
		this.contributionMode = contributionMode;
	}
	
	
}