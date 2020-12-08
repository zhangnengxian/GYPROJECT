package cc.dfsoft.project.biz.base.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 签字标准表
 * @author fuliwei
 *
 */
@Entity
@Table(name = "SIGN_NOTICE_STANDARD")
public class SignNoticeStandard implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2577833971824047270L;
	private String snsId;      			//主键id
	private String post;               //岗位
	private String fieldName;          //签字Name
	private String postName;           //岗位
	private String dataType;           //单据类型
	private String dataTypeName;       //单据类型
	private String sortNo;             //签字顺序
	private String generateType;		//生成单据类型1 单个 2是多个
	private String menuId;				//菜单ID
	private String corpId;
	private String projType;
	private String contributionMode;
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	@Column(name = "PROJ_TYPE")
	public String getProjType() {
		return projType;
	}

	public void setProjType(String projType) {
		this.projType = projType;
	}
	@Column(name = "CONTRIBUTION_MODE")
	public String getContributionMode() {
		return contributionMode;
	}

	public void setContributionMode(String contributionMode) {
		this.contributionMode = contributionMode;
	}

	@Id
	@Column(name = "SNS_ID", unique = true)
	public String getSnsId() {
		return snsId;
	}
	public void setSnsId(String snsId) {
		this.snsId = snsId;
	}
	
	@Column(name = "POST")
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	@Column(name = "POST_NAME")
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	@Column(name = "DATA_TYPE")
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Column(name = "DATA_TYPE_NAME")
	public String getDataTypeName() {
		return dataTypeName;
	}
	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}
	
	@Column(name = "SORT_NO")
	public String getSortNo() {
		return sortNo;
	}
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	
	
	@Column(name = "GENARATE_TYPE")
	public String getGenerateType() {
		return generateType;
	}
	public void setGenerateType(String generateType) {
		this.generateType = generateType;
	}
	
	@Column(name="MENU_ID")
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Column(name="FIELD_NAME")
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
	
}
