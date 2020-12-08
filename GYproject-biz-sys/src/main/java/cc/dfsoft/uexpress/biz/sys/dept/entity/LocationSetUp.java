package cc.dfsoft.uexpress.biz.sys.dept.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cc.dfsoft.uexpress.biz.sys.dept.enums.UnitTypeEnum;

@Entity
@Table(name = "LOCATION_SET_UP")
public class LocationSetUp implements java.io.Serializable {
	
	private String lsuId;
	private String deptType;
	private Integer locationDuration;
	
	private String deptName;//
	
	@Id
	@Column(name = "LSU_ID")
	public String getLsuId() {
		return lsuId;
	}
	public void setLsuId(String lsuId) {
		this.lsuId = lsuId;
	}
	
	@Column(name = "DEPT_TYPE")
	public String getDeptType() {
		return deptType;
	}
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
	
	@Column(name = "LOCATION_DURATION")
	public Integer getLocationDuration() {
		return locationDuration;
	}
	public void setLocationDuration(Integer locationDuration) {
		this.locationDuration = locationDuration;
	}
	
	@Transient
	public String getDeptName() {
		for(UnitTypeEnum e: UnitTypeEnum.values()) {
	   		if(e.getValue().equals(this.deptType)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
