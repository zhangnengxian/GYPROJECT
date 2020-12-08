package cc.dfsoft.project.biz.base.contract.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 查询工程造价审核保存按钮时否显示和表单是否可编辑
 * 
 * @author wanghuijun
 * @createTime 2018年9月2日
 */
@Entity
@Table(name = "button_display")
public class ButtonDisplay implements Serializable {
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private String corpId; // 公司Id
	private String isDisplay; // 是否显示
	private String auditLevel; // 审核级别为多少时可编辑
	
	@Id
	@Column(name="CORP_ID", unique = true, nullable = false)
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	@Column(name="IS_DISPLAY")
	public String getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}
	
	@Column(name="AUDIT_LEVEL")
	public String getAuditLevel() {
		return auditLevel;
	}
	public void setAuditLevel(String auditLevel) {
		this.auditLevel = auditLevel;
	}
    
}
