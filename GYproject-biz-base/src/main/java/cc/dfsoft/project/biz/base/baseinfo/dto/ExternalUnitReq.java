package cc.dfsoft.project.biz.base.baseinfo.dto;



import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class ExternalUnitReq extends PageSortReq{
	
	private String unitId;               //主键
	private String unitName;             //单位名称
	private String unitType;             //单位类型
	private String unitDirector;         //负责人
	private String unitFoundDateStart;     //成立日期范围开始
	private String unitFoundDateEnd;     	 //成立日期范围结束
	private String unitQualification;    //单位资质
	private String shortName;            //简称
	
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getUnitDirector() {
		return unitDirector;
	}
	public void setUnitDirector(String unitDirector) {
		this.unitDirector = unitDirector;
	}
	public String getUnitFoundDateStart() {
		return unitFoundDateStart;
	}
	public void setUnitFoundDateStart(String unitFoundDateStart) {
		this.unitFoundDateStart = unitFoundDateStart;
	}
	public String getUnitFoundDateEnd() {
		return unitFoundDateEnd;
	}
	public void setUnitFoundDateEnd(String unitFoundDateEnd) {
		this.unitFoundDateEnd = unitFoundDateEnd;
	}
	public String getUnitQualification() {
		return unitQualification;
	}
	public void setUnitQualification(String unitQualification) {
		this.unitQualification = unitQualification;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
}
