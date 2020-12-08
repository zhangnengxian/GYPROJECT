package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.EquipmentInstall;
/**
 * 设备安装记录保存辅助类
 * @author liaoyq
 *
 */
public class ProjectCheckListEIReq {

	private String pcId;
	
	private List<EquipmentInstall> list;

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public List<EquipmentInstall> getList() {
		return list;
	}

	public void setList(List<EquipmentInstall> list) {
		this.list = list;
	}
	
}
