package cc.dfsoft.project.biz.base.subpackage.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.common.entity.Version;
import cc.dfsoft.project.biz.base.subpackage.entity.PricedBoq;

public class PriceVersionDto {
	
	private Version vs;//价格版本
	private List<PricedBoq> list;//工程量标准
	public Version getVs() {
		return vs;
	}
	public void setVs(Version vs) {
		this.vs = vs;
	}
	public List<PricedBoq> getList() {
		return list;
	}
	public void setList(List<PricedBoq> list) {
		this.list = list;
	}
	
    
}
