package cc.dfsoft.project.biz.base.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 描述:工程标记信息表
 * @author liaoyq
 * @createTime 2017年12月11日
 */
@Entity
@Table(name="PROJECT_SIGN")
public class ProjectSign {

	private int psId;			//表主键-自增
	private String projId;		//工程ID
	private String signType;	//标记类型
	private String status;		//标记值
	
	public ProjectSign() {
		super();
	}
	@Id
	@Column(name="PS_ID")
	public int getPsId() {
		return psId;
	}
	public void setPsId(int psId) {
		this.psId = psId;
	}
	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	@Column(name="SIGN_TYPE")
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
