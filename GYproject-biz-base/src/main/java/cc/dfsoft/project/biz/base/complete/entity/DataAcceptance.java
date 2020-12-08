package cc.dfsoft.project.biz.base.complete.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
/**
 * 
 * @author fuliwei
 *
 */
@Entity
@Table(name = "DATA_ACCEPTANCE")
public class DataAcceptance implements Serializable{
	

	private static final long serialVersionUID = -6744670806452957235L;
	
	private String daId;			//主键ID
	private String projId;			//工程ID
	private String projNo;			//工程编号
	private String projName;		//工程名称
	private String projScaleDes;	//工程规模--只读
	private String cuName;			//施工单位
	private String suName;			//监理公司
	
	private String deptName;	 	//部门名称--只读
	private String projectTypeDes;	//工程类型描述--只读
	private String contributionModeDes;//出资方式描述--只读
	private String corpName;		   //分公司名称--只读
	private String flag;			   //0 保存  1推送
	
	
	private Date applyDate;		   	  //申请时间
	private String note;			  //备注
	
	private String caiIds;			 //资料标准id
	
	
	public DataAcceptance(){
		
	}
	
	@Id
	@Column(name = "DA_ID", unique = true)
	public String getDaId() {
		return daId;
	}

	public void setDaId(String daId) {
		this.daId = daId;
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
	
	@Transient
	public String getProjScaleDes() {
		return projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	
	@Column(name = "CU_NAME")
	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	
	@Column(name = "SU_NAME")
	public String getSuName() {
		return suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
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
	
	@Temporal(TemporalType.DATE)
	@Column(name = "APPLY_DATE")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	
	@Column(name = "NOTE")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Transient
	public String getCaiIds() {
		return caiIds;
	}

	public void setCaiIds(String caiIds) {
		this.caiIds = caiIds;
	}
	
	@Transient
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
