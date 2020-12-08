package cc.dfsoft.project.biz.base.inspection.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 报验信息
 * @author 王梦远
 *
 */
@Entity
@Table(name="CHECK_LIST")
public class CheckList {
	private String clId;
	private String projId;
	private String isCheck;
	private String remark;
	private String checkTypeId;
	
	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	@Column(name="IS_CHECK")
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="CHECK_TYPE_ID")
	public String getCheckTypeId() {
		return checkTypeId;
	}
	public void setCheckTypeId(String checkTypeId) {
		this.checkTypeId = checkTypeId;
	}
	@Id
	@Column(name="ID")
	public String getClId() {
		return clId;
	}
	public void setClId(String clId) {
		this.clId = clId;
	}
	
	
	
	
	
}
