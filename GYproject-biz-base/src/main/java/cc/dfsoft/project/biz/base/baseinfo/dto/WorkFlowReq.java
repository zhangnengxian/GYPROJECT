package cc.dfsoft.project.biz.base.baseinfo.dto;



import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class WorkFlowReq extends PageSortReq{
	
	private String wfId;	//主键
	private String corpId;	//分公司Id
    private String projType;//工程类型

	public String getWfId() {
		return wfId;
	}
	public void setWfId(String wfId) {
		this.wfId = wfId;
	}
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


}
