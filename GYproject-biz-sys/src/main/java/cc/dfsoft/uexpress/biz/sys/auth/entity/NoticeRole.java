package cc.dfsoft.uexpress.biz.sys.auth.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 通知角色
 * @author lenovo
 *
 */
@Entity
@Table(name = "NOTICE_ROLE")
public class NoticeRole implements java.io.Serializable {
	
	private static final long serialVersionUID = 2842077801842538543L;
	
	private String nrId;
	private String nrCode;
	private String nrName;
	private String createStaffId;
	private Date createTime;
	private String corpId;//分公司id
	@Id
	@Column(name = "NR_ID")
	public String getNrId() {
		return nrId;
	}

	public void setNrId(String nrId) {
		this.nrId = nrId;
	}

	@Column(name = "NR_CODE")
	public String getNrCode() {
		return nrCode;
	}

	public void setNrCode(String nrCode) {
		this.nrCode = nrCode;
	}

	@Column(name = "NR_NAME")
	public String getNrName() {
		return nrName;
	}

	public void setNrName(String nrName) {
		this.nrName = nrName;
	}

	@Column(name = "CREATE_STAFF_ID")
	public String getCreateStaffId() {
		return createStaffId;
	}

	public void setCreateStaffId(String createStaffId) {
		this.createStaffId = createStaffId;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
}
