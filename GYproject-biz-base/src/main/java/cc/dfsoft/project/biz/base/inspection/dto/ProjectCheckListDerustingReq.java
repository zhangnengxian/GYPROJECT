package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.DerustingPreservative;

public class ProjectCheckListDerustingReq {
	
	private String pcId;        				//报验单id
	private String recordsId;					//
	private String projId;	
	private String preservativeType; //防腐记录类型
	private List<DerustingPreservative> list;	//除锈防腐记录对象list
	
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<DerustingPreservative> getList() {
		return list;
	}
	public void setList(List<DerustingPreservative> list) {
		this.list = list;
	}
	public String getRecordsId() {
		return recordsId;
	}
	public void setRecordsId(String recordsId) {
		this.recordsId = recordsId;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getPreservativeType() {
		return preservativeType;
	}
	public void setPreservativeType(String preservativeType) {
		this.preservativeType = preservativeType;
	}
	
}
