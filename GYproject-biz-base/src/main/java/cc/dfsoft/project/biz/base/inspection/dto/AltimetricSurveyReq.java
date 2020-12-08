package cc.dfsoft.project.biz.base.inspection.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class AltimetricSurveyReq extends PageSortReq{
	
	private String pcId;       			//报验单id

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}


	
	
}
