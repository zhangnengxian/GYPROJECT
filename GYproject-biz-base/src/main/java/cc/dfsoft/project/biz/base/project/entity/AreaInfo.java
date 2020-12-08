package cc.dfsoft.project.biz.base.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * 描述:地区编码及描述
 * @author liaoyq
 * @createTime 2017年12月4日
 */
@Entity
@Table(name = "AREAENUM")
public class AreaInfo {
	
	private String areaId;
	private String areaName;
	private String areaDesc;
	private String areaCode;
	
	@Id
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	@Column(name="AREA_NAME")
	public String getAreaName() {
		return areaName;
	}
	
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name="AREA_DES")
	public String getAreaDesc() {
		return areaDesc;
	}
	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}
	
	@Column(name="AREA_CODE")
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	

}
