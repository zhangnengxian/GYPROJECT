package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.AnodeInstall;

/**
 * 阳极安装保存辅助类
 * @author liaoyq
 *
 */
public class ProjectCheckListAIReq {

	private String pcId;
	
	private List<AnodeInstall> list;

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public List<AnodeInstall> getList() {
		return list;
	}

	public void setList(List<AnodeInstall> list) {
		this.list = list;
	}
	
}
