package cc.dfsoft.project.biz.base.project.dto;

import java.util.List;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class SignNoticeReq extends PageSortReq{
	
	private String post;               //岗位
	private String postName;           //岗位
	private String dataType;           //单据类型
	private String dataTypeName;       //单据类型
	private String sortNo;             //签字顺序
	private String generateType;		//生成类型
	private List<String> signPost;		//签字职务
	private String corpId;
	private String projType;
	private String contributionMode;
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getProjType() {
		return projType;
	}

	public void setProjType(String projType) {
		this.projType = projType;
	}

	public String getContributionMode() {
		return contributionMode;
	}

	public void setContributionMode(String contributionMode) {
		this.contributionMode = contributionMode;
	}

	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public String getDataTypeName() {
		return dataTypeName;
	}
	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}
	
	public String getSortNo() {
		return sortNo;
	}
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	
	public String getGenerateType() {
		return generateType;
	}
	public void setGenerateType(String generateType) {
		this.generateType = generateType;
	}
	public List<String> getSignPost() {
		return signPost;
	}
	public void setSignPost(List<String> signPost) {
		this.signPost = signPost;
	}
}	
