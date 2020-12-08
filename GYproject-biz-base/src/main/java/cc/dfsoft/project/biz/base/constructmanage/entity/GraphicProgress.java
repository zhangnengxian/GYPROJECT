package cc.dfsoft.project.biz.base.constructmanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 形象进度
 * @author fuliwei
 *
 */
@Entity
@Table(name = "GRAPHIC_PROGRESS")
public class GraphicProgress {
	private String gpId;
	private String gpName;
	private Double gpVal;
	private String type;
	public GraphicProgress() {
	}
	@Id
	@Column(name = "GP_ID", unique = true)
	public String getGpId() {
		return gpId;
	}
	public void setGpId(String gpId) {
		this.gpId = gpId;
	}
	
	@Column(name = "GP_NAME")
	public String getGpName() {
		return gpName;
	}
	public void setGpName(String gpName) {
		this.gpName = gpName;
	}
	
	@Column(name = "GP_VAL")
	public Double getGpVal() {
		return gpVal;
	}
	public void setGpVal(Double gpVal) {
		this.gpVal = gpVal;
	}
	
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
