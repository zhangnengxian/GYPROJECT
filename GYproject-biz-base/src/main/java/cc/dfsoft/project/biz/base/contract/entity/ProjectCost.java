package cc.dfsoft.project.biz.base.contract.entity;

import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.contract.enums.ProjCostAuditTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;

import javax.annotation.Resource;
import javax.persistence.*;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper.StandardWarningHandler;
import org.springframework.transaction.annotation.Propagation;

import com.alibaba.druid.util.Histogram;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
/**
 * 
 * 描述:工程总造价实体类
 * @author liaoyq
 * @createTime 2017年8月17日
 */
@Entity
@Table(name="PROJECT_COST")
public class ProjectCost implements Serializable {
	

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 工程造价记录ID. */
	private String pcId;

	/** 工程ID. */
	private String projId;

	/** 工程编号. */
	private String projNo;

	/** 工程总造价. */
	private BigDecimal confirmTotalCost;

	/** 总造价员. */
	private String costMember;

	/** 总造价员ID. */
	private String costMemberId;

	/** 造价确认日期. */
	private Date affirmCostDate;

	/** 分公司ID. */
	private String corpId;

	/** 分公司名称. */
	private String corpName;

	/** 部门ID. */
	private String deptId;

	/** 租户ID. */
	private String tenantId;

	/** 总造价审核类型. */
	private String projCostAuditType;

	/** 总造价审核级别. */
	private String auditLevel;

	/** 总造价审核人. */
	private String projCostAuditor;

	/** 总造价审核人ID. */
	private String projCostAuditorId;

	/** 总造价审核日期. */
	private Date auditDate;

	private String projCostAuditTypeDes;	//总造价审核类型描述--
	private String projName;				//工程名称
	private String projectTypeDes;	//工程类型描述--
	private String contributionModeDes;//出资方式描述--
	
	private Project project;		//工程信息类
	private Budget budget;          //预算信息
	
	private String remark;			//备注

	private String reduceGasTimes;			//减免带气次数

	@Column(name="REDUCE_GAS_TIMES")
	public String getReduceGasTimes() {
		return reduceGasTimes;
	}

	public void setReduceGasTimes(String reduceGasTimes) {
		this.reduceGasTimes = reduceGasTimes;
	}

	/**
	 * Constructor.
	 */
	public ProjectCost() {
	}

	/**
	 * Set the 工程造价记录ID.
	 * 
	 * @param pcId
	 *            工程造价记录ID
	 */
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	/**
	 * Get the 工程造价记录ID.
	 * 
	 * @return 工程造价记录ID
	 */
	@Id
	@Column(name="PC_ID", unique = true, nullable = false)
	public String getPcId() {
		return this.pcId;
	}

	/**
	 * Set the 工程ID.
	 * 
	 * @param projId
	 *            工程ID
	 */
	public void setProjId(String projId) {
		this.projId = projId;
	}

	/**
	 * Get the 工程ID.
	 * 
	 * @return 工程ID
	 */
	@Column(name="PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	/**
	 * Set the 工程编号.
	 * 
	 * @param projNo
	 *            工程编号
	 */
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	/**
	 * Get the 工程编号.
	 * 
	 * @return 工程编号
	 */
	@Column(name="PROJ_NO")
	public String getProjNo() {
		return this.projNo;
	}

	/**
	 * Set the 工程总造价.
	 * 
	 * @param confirmTotalCost
	 *            工程总造价
	 */
	public void setConfirmTotalCost(BigDecimal confirmTotalCost) {
		this.confirmTotalCost = confirmTotalCost;
	}

	/**
	 * Get the 工程总造价.
	 * 
	 * @return 工程总造价
	 */
	@Column(name="CONFIRM_TOTAL_COST")
	public BigDecimal getConfirmTotalCost() {
		return this.confirmTotalCost;
	}

	/**
	 * Set the 总造价员.
	 * 
	 * @param costMember
	 *            总造价员
	 */
	public void setCostMember(String costMember) {
		this.costMember = costMember;
	}

	/**
	 * Get the 总造价员.
	 * 
	 * @return 总造价员
	 */
	@Column(name="COST_MEMBER")
	public String getCostMember() {
		return this.costMember;
	}

	/**
	 * Set the 总造价员ID.
	 * 
	 * @param costMemberId
	 *            总造价员ID
	 */
	public void setCostMemberId(String costMemberId) {
		this.costMemberId = costMemberId;
	}

	/**
	 * Get the 总造价员ID.
	 * 
	 * @return 总造价员ID
	 */
	@Column(name="COST_MEMBER_ID")
	public String getCostMemberId() {
		return this.costMemberId;
	}

	/**
	 * Set the 造价确认日期.
	 * 
	 * @param affirmCostDate
	 *            造价确认日期
	 */
	public void setAffirmCostDate(Date affirmCostDate) {
		this.affirmCostDate = affirmCostDate;
	}

	/**
	 * Get the 造价确认日期.
	 * 
	 * @return 造价确认日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="AFFIRM_COST_DATE")
	public Date getAffirmCostDate() {
		return this.affirmCostDate;
	}

	/**
	 * Set the 分公司ID.
	 * 
	 * @param corpId
	 *            分公司ID
	 */
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	/**
	 * Get the 分公司ID.
	 * 
	 * @return 分公司ID
	 */
	@Column(name="CORP_ID")
	public String getCorpId() {
		return this.corpId;
	}

	/**
	 * Set the 分公司名称.
	 * 
	 * @param corpName
	 *            分公司名称
	 */
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	/**
	 * Get the 分公司名称.
	 * 
	 * @return 分公司名称
	 */
	@Column(name="CORP_NAME")
	public String getCorpName() {
		return this.corpName;
	}

	/**
	 * Set the 部门ID.
	 * 
	 * @param deptId
	 *            部门ID
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * Get the 部门ID.
	 * 
	 * @return 部门ID
	 */
	@Column(name="DEPT_ID")
	public String getDeptId() {
		return this.deptId;
	}

	/**
	 * Set the 租户ID.
	 * 
	 * @param tenantId
	 *            租户ID
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * Get the 租户ID.
	 * 
	 * @return 租户ID
	 */
	@Column(name="TENANT_ID")
	public String getTenantId() {
		return this.tenantId;
	}

	/**
	 * Set the 总造价审核类型.
	 * 
	 * @param projCostAuditType
	 *            总造价审核类型
	 */
	public void setProjCostAuditType(String projCostAuditType) {
		this.projCostAuditType = projCostAuditType;
	}

	/**
	 * Get the 总造价审核类型.
	 * 
	 * @return 总造价审核类型
	 */
	@Column(name="PROJ_COST_AUDIT_TYPE")
	public String getProjCostAuditType() {
		return this.projCostAuditType;
	}

	/**
	 * Set the 总造价审核级别.
	 * 
	 * @param auditLevel
	 *            总造价审核级别
	 */
	public void setAuditLevel(String auditLevel) {
		this.auditLevel = auditLevel;
	}

	/**
	 * Get the 总造价审核级别.
	 * 
	 * @return 总造价审核级别
	 */
	@Column(name="AUDIT_LEVEL")
	public String getAuditLevel() {
		return this.auditLevel;
	}

	/**
	 * Set the 总造价审核人.
	 * 
	 * @param projCostAuditor
	 *            总造价审核人
	 */
	public void setProjCostAuditor(String projCostAuditor) {
		this.projCostAuditor = projCostAuditor;
	}

	/**
	 * Get the 总造价审核人.
	 * 
	 * @return 总造价审核人
	 */
	@Column(name="PROJ_COST_AUDITOR")
	public String getProjCostAuditor() {
		return this.projCostAuditor;
	}

	/**
	 * Set the 总造价审核人ID.
	 * 
	 * @param projCostAuditorId
	 *            总造价审核人ID
	 */
	public void setProjCostAuditorId(String projCostAuditorId) {
		this.projCostAuditorId = projCostAuditorId;
	}

	/**
	 * Get the 总造价审核人ID.
	 * 
	 * @return 总造价审核人ID
	 */
	@Column(name="PROJ_COST_AUDITOR_ID")
	public String getProjCostAuditorId() {
		return this.projCostAuditorId;
	}

	/**
	 * Set the 总造价审核日期.
	 * 
	 * @param auditDate
	 *            总造价审核日期
	 */
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	/**
	 * Get the 总造价审核日期.
	 * 
	 * @return 总造价审核日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="AUDIT_DATE")
	public Date getAuditDate() {
		return this.auditDate;
	}

	@Transient
	public String getProjCostAuditTypeDes() {
		String menuId = "110401";
		List<DataFilerSetUpDto> auditRange= Constants.getDataFilterMapByKey(project.getCorpId()+"_"+project.getProjectType()+"_"+menuId);  //根据公司Id菜单ID查找审批范围
		if(auditRange != null && auditRange.size() >0){
		     for (DataFilerSetUpDto dataFilerSetUpDto : auditRange) {
				if(dataFilerSetUpDto.getSupSql().equals(this.projCostAuditType)){
					return 	dataFilerSetUpDto.getRemark();
			}
		}
		
	}
		return null;
}
	public void setProjCostAuditTypeDes(String projCostAuditTypeDes) {
		this.projCostAuditTypeDes = projCostAuditTypeDes;
	}

	@Transient
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
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
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Transient
	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
