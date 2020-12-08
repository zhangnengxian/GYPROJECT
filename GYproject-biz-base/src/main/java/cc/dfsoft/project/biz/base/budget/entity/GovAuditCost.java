package cc.dfsoft.project.biz.base.budget.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.project.entity.Project;


/**
 * 描述:政府审定价登记信息
 * @author liaoyq
 * @createTime 2017年9月8日
 */
@Entity
@Table(name="GOV_AUDIT_COST")
public class GovAuditCost implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String gacId;				//政府审定价记录ID
	private String projId;				//工程ID
	private String projNo;				//工程编号
	private String gacType;				//审定价类型
	private BigDecimal authorizedCost;	//审定价格
	private Date authorizedCostDate;	//审定价登记日期
	private String gacStaffId;      	//审定价登记人ID
	private String gacStaffName;		//审定价登记人姓名
	private String gacRemark;			//备注
	private String flag;				//推送标记
	private String pushStaffId;			//推送人ID
	private Date pushDate;				//推送时间
	private String drawName;  			//附件名称
	
	private Project project;			//工程信息--只读
	private String stepId;				//步骤id
	private String menuDes;				//菜单描述
	private BigDecimal endDeclarationCost;//终审金额--只读
	
	
	public GovAuditCost() {
		super();
	}
	
	@Id
	@Column(name="GAC_ID" , unique = true ,nullable = false)
	public String getGacId() {
		return gacId;
	}
	
	public void setGacId(String gacId) {
		this.gacId = gacId;
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
	
	@Column(name="GAC_TYPE")
	public String getGacType() {
		return gacType;
	}

	public void setGacType(String gacType) {
		this.gacType = gacType;
	}

	
	@Column(name="AUTHORIZED_COST")
	public BigDecimal getAuthorizedCost() {
		return authorizedCost;
	}
	public void setAuthorizedCost(BigDecimal authorizedCost) {
		this.authorizedCost = authorizedCost;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="AUTHORIZED_COST_DATE")
	public Date getAuthorizedCostDate() {
		return authorizedCostDate;
	}
	public void setAuthorizedCostDate(Date authorizedCostDate) {
		this.authorizedCostDate = authorizedCostDate;
	}
	
	@Column(name="GAC_STAFF_ID")
	public String getGacStaffId() {
		return gacStaffId;
	}

	public void setGacStaffId(String gacStaffId) {
		this.gacStaffId = gacStaffId;
	}
	
	@Column(name="GAC_STAFF_NAME")
	public String getGacStaffName() {
		return gacStaffName;
	}

	public void setGacStaffName(String gacStaffName) {
		this.gacStaffName = gacStaffName;
	}

	@Column(name="GAC_REMARK")
	public String getGacRemark() {
		return gacRemark;
	}

	public void setGacRemark(String gacRemark) {
		this.gacRemark = gacRemark;
	}

	@Column(name="FLAG")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Transient
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name="PUSH_STAFF_ID")
	public String getPushStaffId() {
		return pushStaffId;
	}

	public void setPushStaffId(String pushStaffId) {
		this.pushStaffId = pushStaffId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PUSH_DATE")
	public Date getPushDate() {
		return pushDate;
	}

	public void setPushDate(Date pushDate) {
		this.pushDate = pushDate;
	}

	@Column(name="DRAW_NAME")
	public String getDrawName() {
		return null;
	}

	public void setDrawName(String drawName) {
		this.drawName = drawName;
	}

	@Transient
	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	@Transient
	public String getMenuDes() {
		return menuDes;
	}

	public void setMenuDes(String menuDes) {
		this.menuDes = menuDes;
	}

	@Transient
	public BigDecimal getEndDeclarationCost() {
		return endDeclarationCost;
	}

	public void setEndDeclarationCost(BigDecimal endDeclarationCost) {
		this.endDeclarationCost = endDeclarationCost;
	}
	
	
}
