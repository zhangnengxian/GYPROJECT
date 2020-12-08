package cc.dfsoft.project.biz.base.complete.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.complete.enums.CheckTypeEnum;
import cc.dfsoft.project.biz.base.complete.enums.TypeEnum;

/**
 * CheckItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CHECK_ITEM")
public class CheckItem implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2445651186051025790L;
	private String id;			//自检项ID
	private String type;		//自检类型
	private String checkType;	//检查类型
	private String des;			//描述
	
	private String typeDes; //用于前台展示文字
	private String checkTypeDes; //用于前台展示文字
	
	private String corpId;		//分公司id
	
	// Constructors

	/** default constructor */
	public CheckItem() {
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

	@Column(name = "CHECK_TYPE")
	public String getCheckType() {
		return this.checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
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
		for(TypeEnum e : TypeEnum.values()){
			if(e.getValue().equals(this.type)){
				return e.getMessage();
			}
		}
		return "";
	}

	public void setTypeDes(String typeDes) {
		this.typeDes = typeDes;
	}
	
	@Transient
	public String getCheckTypeDes() {
		for(CheckTypeEnum e : CheckTypeEnum.values()){
			if(e.getValue().equals(this.checkType)){
				return e.getMessage();
			}
		}
		return "";
	}

	public void setCheckTypeDes(String checkTypeDes) {
		this.checkTypeDes = checkTypeDes;
	}
	
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

}