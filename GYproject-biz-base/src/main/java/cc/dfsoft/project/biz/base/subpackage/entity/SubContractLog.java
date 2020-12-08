package cc.dfsoft.project.biz.base.subpackage.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * 
 * 描述:分合同修改日志实体类
 * 存储可能修改的字段
 * @author liaoyq
 * @createTime 2018年1月23日
 */
@Entity
@Table(name="SUB_CONTRACT_LOG")
public class SubContractLog {

	private String scLogId;					//分合同修改日志ID
	private String scId;					//分包合同ID   
	private String scNo;					//分包合同编号
	private String projId;					//工程ID
	private String projNo;					//工程编号

	private String deptId;		            //甲方ID
	private String deptName;				//甲方名称
	private String projCompDirector;		//甲方委托代理人
	
	private String cuId;					//分包单位ID
	private String cuName;					//乙方名称
	private String cuDirector;				//乙方委托代表
	
	private String scScope;					//承包范围
	private String scType;					//支付方式 -合同款
	private Date scPlannedStartDate;		//计划开工日期
	private Date scPlannedEndDate;			//计划竣工日期
	private BigDecimal scAmount;			//协议价款
	private String remark;					//备注
	private String payType; 				//支付方式 -预付款
	private String progressType; 			//支付方式 -进度款
	private String contractMode; 			//承包方式
	private String contractMethod;			//建筑服务
	
	private String modifyState;				//修改状态:1-修改前，2-修改后
	private String orlId;     				//操作日志Id
	
	public SubContractLog() {
		super();
	}
	
	@Id
	@Column(name="SC_LOG_ID")
	public String getScLogId() {
		return scLogId;
	}
	public void setScLogId(String scLogId) {
		this.scLogId = scLogId;
	}
	
	@Column(name="SC_ID")
	public String getScId() {
		return scId;
	}
	public void setScId(String scId) {
		this.scId = scId;
	}
	
	@Column(name="SC_NO")
	public String getScNo() {
		return scNo;
	}
	public void setScNo(String scNo) {
		this.scNo = scNo;
	}
	
	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	@Column(name="PROJ_NO")
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	
	@Column(name="DEPT_ID")
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Column(name="DEPT_NAME")
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Column(name="PROJ_COMP_DIRECTOR")
	public String getProjCompDirector() {
		return projCompDirector;
	}
	public void setProjCompDirector(String projCompDirector) {
		this.projCompDirector = projCompDirector;
	}
	
	@Column(name="CU_ID")
	public String getCuId() {
		return cuId;
	}
	public void setCuId(String cuId) {
		this.cuId = cuId;
	}
	
	@Column(name="CU_NAME")
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	
	@Column(name="CU_DIRECTOR")
	public String getCuDirector() {
		return cuDirector;
	}
	public void setCuDirector(String cuDirector) {
		this.cuDirector = cuDirector;
	}
	
	@Column(name="SC_SCOPE")
	public String getScScope() {
		return scScope;
	}
	public void setScScope(String scScope) {
		this.scScope = scScope;
	}
	
	@Column(name="SC_TYPE")
	public String getScType() {
		return scType;
	}
	public void setScType(String scType) {
		this.scType = scType;
	}
	
	@Column(name="SC_PLANNED_START_DATE")
	public Date getScPlannedStartDate() {
		return scPlannedStartDate;
	}
	public void setScPlannedStartDate(Date scPlannedStartDate) {
		this.scPlannedStartDate = scPlannedStartDate;
	}
	
	@Column(name="SC_PLANNED_END_DATE")
	public Date getScPlannedEndDate() {
		return scPlannedEndDate;
	}
	public void setScPlannedEndDate(Date scPlannedEndDate) {
		this.scPlannedEndDate = scPlannedEndDate;
	}
	
	@Column(name="SC_AMOUNT")
	public BigDecimal getScAmount() {
		return scAmount;
	}
	public void setScAmount(BigDecimal scAmount) {
		this.scAmount = scAmount;
	}
	
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name="PAY_TYPE")
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@Column(name="PROGRESS_TYPE")
	public String getProgressType() {
		return progressType;
	}
	public void setProgressType(String progressType) {
		this.progressType = progressType;
	}
	
	@Column(name="CONTRACT_MODE")
	public String getContractMode() {
		return contractMode;
	}
	public void setContractMode(String contractMode) {
		this.contractMode = contractMode;
	}
	
	@Column(name="CONTRACT_METHOD")
	public String getContractMethod() {
		return contractMethod;
	}
	public void setContractMethod(String contractMethod) {
		this.contractMethod = contractMethod;
	}
	
	@Column(name="MODIFY_STATE")
	public String getModifyState() {
		return modifyState;
	}
	public void setModifyState(String modifyState) {
		this.modifyState = modifyState;
	}
	
	@Column(name="ORL_ID")
	public String getOrlId() {
		return orlId;
	}

	public void setOrlId(String orlId) {
		this.orlId = orlId;
	}
	
}
