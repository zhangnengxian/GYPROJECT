package cc.dfsoft.project.biz.base.baseinfo.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 节假日查询辅助类
 * @author liaoyq
 * @createTime 2018年5月8日
 */
public class FestivalReq extends PageSortReq{

	private String festivalName;
	private String orgId;
	private String isDel;
	private String isValid;
	private String dayType;
	
	public String getFestivalName() {
		return festivalName;
	}
	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getDayType() {
		return dayType;
	}
	public void setDayType(String dayType) {
		this.dayType = dayType;
	}
	
}
