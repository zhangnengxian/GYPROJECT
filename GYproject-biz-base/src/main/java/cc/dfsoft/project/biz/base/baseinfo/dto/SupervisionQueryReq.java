package cc.dfsoft.project.biz.base.baseinfo.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 监理单位查询
 * @author cui
 * @createTime 2016-07-11
 *
 */
public class SupervisionQueryReq extends PageSortReq{
	
	private String suName;				//监理单位名称
	private String shortName;			//简称
	private String suDirector;			//负责人
	private String suMobile;
	public String getSuName() {
		return suName;
	}
	public void setSuName(String suName) {
		this.suName = suName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getSuDirector() {
		return suDirector;
	}
	public void setSuDirector(String suDirector) {
		this.suDirector = suDirector;
	}
	public String getSuMobile() {
		return suMobile;
	}
	public void setSuMobile(String suMobile) {
		this.suMobile = suMobile;
	}
	
	
	
	
	
}
