package cc.dfsoft.project.biz.base.accept.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PROJ_INFO_MODIFY")
public class ProjInfoModify implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3583168022486717977L;
	private String modifyId;			//主键ID
	private String projId;				//工程id
	private String modifyDes;			//修改内容
	private String proposer;			//提出人
	private String proposerId;			//提出人id
	private Date   proposeDate;		    //提出日期
	
	@Id
	@Column(name = "MODIFY_ID")
	public String getModifyId() {
		return modifyId;
	}
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}
	
	@Column(name = "PROJ_ID")
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	@Column(name = "MODIFY_DES")
	public String getModifyDes() {
		return modifyDes;
	}
	public void setModifyDes(String modifyDes) {
		this.modifyDes = modifyDes;
	}
	
	@Column(name = "PROPOSER")
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PROPOSE_DATE")
	public Date getProposeDate() {
		return proposeDate;
	}
	public void setProposeDate(Date proposeDate) {
		this.proposeDate = proposeDate;
	}
	
	@Column(name = "PROPOSER_ID")
	public String getProposerId() {
		return proposerId;
	}
	public void setProposerId(String proposerId) {
		this.proposerId = proposerId;
	}
	
	
	
	
}
