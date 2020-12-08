package cc.dfsoft.project.biz.base.baseinfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_audit_step")
public class ProjectAuditStep {

	private String stepId;	//步骤id
	private String stepDes;//步骤描述
	
	
	@Id
	@Column(name = "STEP_ID")
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	
	@Column(name = "STEP_DES")
	public String getStepDes() {
		return stepDes;
	}
	public void setStepDes(String stepDes) {
		this.stepDes = stepDes;
	}
	
	
	
	
}
