package cc.dfsoft.project.biz.base.project.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 
 * @author zhangjj
 *
 */
public class ProjectScaleReq extends PageSortReq{
	
	private String psDes;	//工程规模
	
	private String contributionModeDes;//出资方式
	private String code;   //出资编码
	
	public String getcode() {
		return code;
	}

	public void setCmCode(String code) {
		this.code = code;
	}

	public String getPsDes() {
		return psDes;
	}

	public void setPsDes(String psDes) {
		this.psDes = psDes;
	}

	public String getContributionModeDes(){
		return contributionModeDes;
	}
	
	public void setContributionModeDes(String contributionModeDes){
		this.contributionModeDes=contributionModeDes;
	}
	
}
