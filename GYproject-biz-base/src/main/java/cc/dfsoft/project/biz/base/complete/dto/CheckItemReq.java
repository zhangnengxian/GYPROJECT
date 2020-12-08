package cc.dfsoft.project.biz.base.complete.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class CheckItemReq extends PageSortReq{
	private String id;			//自检项ID
	private String type;		//自检类型
	private String checkType;	//检查类型
	private String corpId;		//分公司id
	
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	private String des;			//描述
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

}
