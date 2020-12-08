package cc.dfsoft.project.biz.base.project.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.project.enums.DataCollectionTypeEnum;

/**
 * CollectAccessoryItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COLLECT_ACCESSORY_ITEM")
public class CollectAccessoryItem implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5763197521073806341L;
	private String caiId;			//主键id
	private String accessoryName;	//资料名称
	
	 private ProjectType projType; //工程类型实体
     private String projTypeId; 	//工程类型id
     
     private String dataType;	 	//资料类型
     private String dataTypeDes;	//资料类型描述-只读属性
     
     private String corpId;			//分公司id
     
	// Constructors

	/** default constructor */
	public CollectAccessoryItem() {
	}

	// Property accessors
	@Id
	@Column(name = "CAI_ID", unique = true)
	public String getCaiId() {
		return this.caiId;
	}

	public void setCaiId(String caiId) {
		this.caiId = caiId;
	}


	@Column(name = "ACCESSORY_NAME")
	public String getAccessoryName() {
		return this.accessoryName;
	}

	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}
	/*@Transient
	public String getProjLtypeIdDes() {
		for(ProjLtypeEnum e: ProjLtypeEnum.values()) {
	   		if(e.getValue().equals(this.projLtypeId)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setProjLtypeIdDes(String projLtypeIdDes) {
		this.caiId = projLtypeIdDes;
	}*/
	 @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )  
	    @JoinColumn(name="PROJ_LTYPE_ID")
	    public ProjectType getProjType() {
	        return this.projType;
	    }    
	    public void setProjType(ProjectType projType) {
	        this.projType = projType;
	    }
	    @Transient
		public String getProjTypeId() {
			return projTypeId;
		}

		public void setProjTypeId(String projTypeId) {
			this.projTypeId = projTypeId;
		}
		
		@Column(name = "DATA_TYPE")
		public String getDataType() {
			return dataType;
		}

		public void setDataType(String dataType) {
			this.dataType = dataType;
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
		
		
		@Column(name = "CORP_ID")
		public String getCorpId() {
			return corpId;
		}

		public void setCorpId(String corpId) {
			this.corpId = corpId;
		}

}