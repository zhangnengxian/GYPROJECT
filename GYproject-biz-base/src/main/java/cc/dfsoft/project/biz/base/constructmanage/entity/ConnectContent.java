package cc.dfsoft.project.biz.base.constructmanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.CostTypeEnum;

/**
 * ConnectContent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONNECT_CONTENT")
public class ConnectContent implements java.io.Serializable {

	// Fields

	private String id;	//内容id
	private String type;//单位类型
	private String des;	//描述

	private String typeDes;//单位类型枚举
	// Constructors

	/** default constructor */
	public ConnectContent() {
	}


	// Property accessors
	@Id
	@Column(name = "ID", unique = true)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "TYPE")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "DES")
	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Transient
	public String getTypeDes() {
		for(UnitTypeEnum e: UnitTypeEnum.values()) {
	   		if(e.getValue().equals(this.type)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}


	public void setTypeDes(String typeDes) {
		this.typeDes = typeDes;
	}

	
}