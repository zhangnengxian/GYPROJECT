package cc.dfsoft.project.biz.base.baseinfo.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class SafetyPunishReq extends PageSortReq{

	private String id;               //id
	private String des;              //安全质量细则名称
	private Double deduction;        //分值
	private String remark;           //备注
	private String type;			//类型 0安全检查 1质量保证
	private String corpId;			//分公司id
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Double getDeduction() {
		return deduction;
	}
	public void setDeduction(Double deduction) {
		this.deduction = deduction;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
}
