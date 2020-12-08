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

@Entity
@Table(name = "CONTRIBUTION_MODE")
public class ContributionMode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String COMPANY_TYPE=",2,5,8,";//公司出资的出资方式常量-和数据库ID要对应 公司出资、募投项目、政府专项基金
	
	public static final String CUST_TYPE="1";//用户出资和数据可ID对应
	public static final String INVESTMENT_CODE_TYPE="2";//幕投类项目的出资类型编码
	
	private String id;
	private String contributionDes;//出资方式
	/* private String projTypeId;*/
	private String code;			//编码
	/*private String projTypeDes;		//工程类型描述
*/	
	
	public ContributionMode(){
		
	}
	
	@Id
	@Column(name = "ID", unique = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "CONTRIBUTION_DES")
	public String getContributionDes() {
		return contributionDes;
	}

	public void setContributionDes(String contributionDes) {
		this.contributionDes = contributionDes;
	}
	
	
	
	@Column(name = "CM_CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	/*
	@Column(name="PROJ_TYPE_ID")
	public String getProjTypeId() {
		return projTypeId;
	}

	public void setProjTypeId(String projTypeId) {
		this.projTypeId = projTypeId;
	}
	
	
	@Transient
	public String getProjTypeDes() {
		return projTypeDes;
	}

	public void setProjTypeDes(String projTypeDes) {
		this.projTypeDes = projTypeDes;
	}*/
	
	
}
