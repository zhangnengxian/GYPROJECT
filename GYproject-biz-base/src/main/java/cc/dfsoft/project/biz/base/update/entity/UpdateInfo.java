package cc.dfsoft.project.biz.base.update.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author dn
 * 系统更新内容
 */


@Entity
@Table(name = "UPDATE_INFO")
public class UpdateInfo  implements Serializable {

	/**
	 * 
	 */
	/** default constructor */
	public UpdateInfo() {
	}
	
	private static final long serialVersionUID = -1692048426882374384L;
	// Fields

	private String updateId;		//更新Id

	private String updateNo;		//更新号
	
	private Date updateTime;	//更新时间
	
	private String updateContent;		//更新内容
	
	private String updateNumber;		//更新次数
	
	private String operater;			//操作人
	private String operaterId;			//操作人id
	private String drawName;			//附件名称
	private String corpId;				//分公司id
	private String corpName;			//分公司名称
	

	@Id
	@Column(name = "UPDATE_ID", unique = true)
	public String getUpdateId() {
		return updateId;
	}
	
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	
	@Column(name = "UPDATE_NO")
	public String getUpdateNo() {
		return updateNo;
	}
	
	public void setUpdateNo(String updateNo) {
		this.updateNo = updateNo;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDTAE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UPDATE_CONTENT")
	public String getUpdateContent() {
		return updateContent;
	}
	
	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	@Column(name = "UPDATE_NUMBER")
	public String getUpdateNumber() {
		return updateNumber;
	}

	public void setUpdateNumber(String updateNumber) {
		this.updateNumber = updateNumber;
	}
	
	@Column(name = "OPERATER")
	public String getOperater() {
		return operater;
	}
	
	public void setOperater(String operater) {
		this.operater = operater;
	}
	
	@Column(name = "OPERATER_ID")
	public String getOperaterId() {
		return operaterId;
	}

	public void setOperaterId(String operaterId) {
		this.operaterId = operaterId;
	}

	@Column(name = "DRAW_NAME")
	public String getDrawName() {
		return drawName;
	}

	public void setDrawName(String drawName) {
		this.drawName = drawName;
	}
	
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	@Column(name = "CORP_NAME")
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
}