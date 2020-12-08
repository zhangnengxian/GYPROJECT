package cc.dfsoft.project.biz.base.inspection.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * pe管焊缝检查记录查询辅助类
 * @author liaoyq
 *
 */
public class PeWeldLineInpectReq extends PageSortReq{

	private  String pcId;
	private String projId;
	private String meltConnectType;//连接类型
	private Integer flag;
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getMeltConnectType() {
		return meltConnectType;
	}

	public void setMeltConnectType(String meltConnectType) {
		this.meltConnectType = meltConnectType;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
}
