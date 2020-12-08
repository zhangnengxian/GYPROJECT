package cc.dfsoft.project.biz.base.baseinfo.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class ConnectContentReq extends PageSortReq{
	
	
	private String id;
	private String type;
	private String des;
	
	
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
	
	
	
}
