package cc.dfsoft.project.biz.base.subpackage.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class ConstructionUnitReq extends PageSortReq{
	
	private String cuId;       			//主键ID
	private String cuName;				//分包名称
	private String shortName;			//简称
	private String cuDirector;			//分包单位负责人
	private String cuFoundDateStart;		//成立日期范围开始
	private String cuFoundDateEnd;		//成立日期范围结束
	
	
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCuDirector() {
		return cuDirector;
	}

	public void setCuDirector(String cuDirector) {
		this.cuDirector = cuDirector;
	}

	public String getCuFoundDateStart() {
		return cuFoundDateStart;
	}

	public void setCuFoundDateStart(String cuFoundDateStart) {
		this.cuFoundDateStart = cuFoundDateStart;
	}

	public String getCuFoundDateEnd() {
		return cuFoundDateEnd;
	}

	public void setCuFoundDateEnd(String cuFoundDateEnd) {
		this.cuFoundDateEnd = cuFoundDateEnd;
	}

	private String cuQualification; //资质

	public String getCuId() {
		return cuId;
	}

	public void setCuId(String cuId) {
		this.cuId = cuId;
	}

	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}

	public String getCuQualification() {
		return cuQualification;
	}

	public void setCuQualification(String cuQualification) {
		this.cuQualification = cuQualification;
	}
	
	
}
