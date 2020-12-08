package cc.dfsoft.project.biz.base.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DATA_QUERY_ROLE")
public class DataQueryRole {
	
	private String dqrId;	//主键id
	private String unitId; //部门id
	private String unitName;//部门名称
	private String stepId; //步骤id
	
	public DataQueryRole(){
		
	}
	
	@Id
	@Column(name = "DQR_ID", unique = true)
	public String getDqrId() {
		return dqrId;
	}
	public void setDqrId(String dqrId) {
		this.dqrId = dqrId;
	}
	
	@Column(name = "UNIT_ID")
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	@Column(name = "STEP_ID")
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	
	@Column(name = "UNIT_NAME")
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	
	
}
