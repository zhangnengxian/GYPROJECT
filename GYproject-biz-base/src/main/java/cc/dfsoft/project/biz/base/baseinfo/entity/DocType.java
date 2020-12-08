package cc.dfsoft.project.biz.base.baseinfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import cc.dfsoft.project.biz.base.project.enums.AreaEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;

/**
 * DocType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DOC_TYPE")
public class DocType implements java.io.Serializable {

	// Fields

	private String id;
	private String des;
	private String grade;  
	private String stepId;      //审核级别
	
	private String stepDes;
	
	private String corpId;		//分公司id
	private String corpName;	//分公司名称
	
	private String projType;	//工程类型id
	private String projTypeDes;	//工程类型名称
	private String contributionCode;	//出资方式id
	private String contributionCodeDes;	//出资方式-名称
	
	private String standardType;		//查询标准 1是标准
	// Constructors
	private String menuId;//菜单id
	/** default constructor */
	public DocType() {
	}

	// Property accessors

	@Column(name = "MENU_ID")
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Id
	@Column(name = "ID")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "DES")
	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Column(name = "GRADE")
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "STEP_ID")
	public String getStepId() {
		return this.stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	
	@Transient
	public String getStepDes() {
		if(StringUtils.isNotBlank(this.stepId)){
			for(StepEnum e: StepEnum.values()) {
		   		if(e.getValue().equals(this.stepId)) {
		   			return e.getMessage();
		   		}
		   	}
			return "";
		}
		return stepDes;
	}

	public void setStepDes(String stepDes) {
		this.stepDes = stepDes;
	}
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	@Column(name = "CORP_NAME")
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	@Column(name = "PROJ_TYPE")
	public String getProjType() {
		return projType;
	}

	public void setProjType(String projType) {
		this.projType = projType;
	}
	
	@Column(name = "CONTRIBUTION_CODE")
	public String getContributionCode() {
		return contributionCode;
	}

	public void setContributionCode(String contributionCode) {
		this.contributionCode = contributionCode;
	}

	@Column(name = "PROJ_TYPE_DES")
	public String getProjTypeDes() {
		return projTypeDes;
	}

	public void setProjTypeDes(String projTypeDes) {
		this.projTypeDes = projTypeDes;
	}
	
	@Column(name = "CONTRIBUTION_CODE_DES")
	public String getContributionCodeDes() {
		return contributionCodeDes;
	}

	public void setContributionCodeDes(String contributionCodeDes) {
		this.contributionCodeDes = contributionCodeDes;
	}
	
	@Column(name = "STANDARD_TYPE")
	public String getStandardType() {
		return standardType;
	}

	public void setStandardType(String standardType) {
		this.standardType = standardType;
	}

}