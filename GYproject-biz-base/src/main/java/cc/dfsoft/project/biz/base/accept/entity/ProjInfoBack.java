package cc.dfsoft.project.biz.base.accept.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 回退信息
 * @author fuliwei
 *
 */
@Entity
@Table(name = "PROJ_INFO_BACK")
public class ProjInfoBack implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8972767459576174326L;
	private String pibId;			    //主键ID
	private String projId;				//工程id
	private String projNo;				//工程编号
	private String projName;			//工程名称
	private String corpId;			    //分公司id
	private String applyer;				//申请人
	private String applyerId;			//申请人id
	private Date applyDate;				//申请时间
	private String backDes;				//退回备注
	private String confirmer;			//确认人
	private String confirmerId;			//确认人id
	private Date confirmDate;			//确认日期
	private String confirmDes;			//确认备注
	
	@Id
	@Column(name = "PIB_ID")
	public String getPibId() {
		return pibId;
	}
	public void setPibId(String pibId) {
		this.pibId = pibId;
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
	
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	
	@Column(name = "APPLYER")
	public String getApplyer() {
		return applyer;
	}
	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}
	
	@Column(name = "APPLYER_ID")
	public String getApplyerId() {
		return applyerId;
	}
	public void setApplyerId(String applyerId) {
		this.applyerId = applyerId;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "APPLY_DATE")
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	@Column(name = "BACK_DES")
	public String getBackDes() {
		return backDes;
	}
	public void setBackDes(String backDes) {
		this.backDes = backDes;
	}
	
	@Column(name = "CONFIRMER")
	public String getConfirmer() {
		return confirmer;
	}
	public void setConfirmer(String confirmer) {
		this.confirmer = confirmer;
	}
	
	@Column(name = "CONFIRMER_ID")
	public String getConfirmerId() {
		return confirmerId;
	}
	public void setConfirmerId(String confirmerId) {
		this.confirmerId = confirmerId;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CONFIRM_DATE")
	public Date getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	
	@Column(name = "CONFIRM_DES")
	public String getConfirmDes() {
		return confirmDes;
	}
	public void setConfirmDes(String confirmDes) {
		this.confirmDes = confirmDes;
	}
	
	
	
	

}
