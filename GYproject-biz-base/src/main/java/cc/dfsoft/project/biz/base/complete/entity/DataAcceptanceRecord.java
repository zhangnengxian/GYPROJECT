package cc.dfsoft.project.biz.base.complete.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.project.enums.DataCollectionTypeEnum;

@Entity
@Table(name = "DATA_ACCEPTANCE_RECORD")
public class DataAcceptanceRecord  implements Serializable{
	
	private static final long serialVersionUID = -6744270806452957235L;
	
	private String darId;		//资料验收记录id
	private String projId;		//工程id
	private String daId;		//资料验收id
	private String dataType;	//资料类型
	private String projectTypeId;	//工程类型id
	private String dataName;	//资料名称
	private String isComplete; //是否齐全
	
	private String caiId;		//标准id
	
	private String dataTypeDes;//
	
	public DataAcceptanceRecord(){
		
	}	
	
	@Id
	@Column(name = "DAR_ID", unique = true)
	public String getDarId() {
		return darId;
	}

	public void setDarId(String darId) {
		this.darId = darId;
	}
	
	@Column(name = "PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	@Column(name = "DA_ID")
	public String getDaId() {
		return daId;
	}

	public void setDaId(String daId) {
		this.daId = daId;
	}
	
	@Column(name = "DATA_TYPE")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Column(name = "PROJECT_TYPE_ID")
	public String getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(String projectTypeId) {
		this.projectTypeId = projectTypeId;
	}
	
	@Column(name = "DATA_NAME")
	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	
	@Column(name = "IS_COMPLETE")
	public String getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}
	
	@Transient
	public String getDataTypeDes() {
		for(DataCollectionTypeEnum e: DataCollectionTypeEnum.values()) {
	   		if(e.getValue().equals(this.dataType)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setDataTypeDes(String dataTypeDes) {
		this.dataTypeDes = dataTypeDes;
	}
	
	@Column(name = "CAI_ID")
	public String getCaiId() {
		return caiId;
	}

	public void setCaiId(String caiId) {
		this.caiId = caiId;
	}
	
}
