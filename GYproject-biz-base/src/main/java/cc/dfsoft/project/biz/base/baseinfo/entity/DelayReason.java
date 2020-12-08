package cc.dfsoft.project.biz.base.baseinfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DELAY_REASON")
public class DelayReason {
	
	private String delayReasonId;
	private String delayReasonDes;
	
	@Id
	@Column(name="DELAYREASON_ID")
	public String getDelayReasonId() {
		return delayReasonId;
	}
	public void setDelayReasonId(String delayReasonId) {
		this.delayReasonId = delayReasonId;
	}
	
	@Column(name="DELAYREASON_DES")
	public String getDelayReasonDes() {
		return delayReasonDes;
	}
	public void setDelayReasonDes(String delayReasonDes) {
		this.delayReasonDes = delayReasonDes;
	}
	
}
