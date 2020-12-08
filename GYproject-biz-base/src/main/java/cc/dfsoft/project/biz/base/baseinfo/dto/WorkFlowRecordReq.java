package cc.dfsoft.project.biz.base.baseinfo.dto;

import java.util.List;

public class WorkFlowRecordReq {
	
	private String wfId;        		//工程流程Id
	private List<WorkFlowDto> list;	//流程步骤
	
	public String getWfId() {
		return wfId;
	}
	public void setWfId(String wfId) {
		this.wfId = wfId;
	}
	public List<WorkFlowDto> getList() {
		return list;
	}
	public void setList(List<WorkFlowDto> list) {
		this.list = list;
	}
}
