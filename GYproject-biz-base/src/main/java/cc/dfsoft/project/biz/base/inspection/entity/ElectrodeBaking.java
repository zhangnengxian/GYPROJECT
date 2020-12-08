package cc.dfsoft.project.biz.base.inspection.entity;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 焊条烘烤实体类
 * @author liaoyq
 *
 */
@Entity
@Table(name = "ELECTRODE_BAKING")
public class ElectrodeBaking implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**焊条烘干记录id*/
	private String ebId;
	/**报验单ID*/
	private String pcId;
	/**焊条牌号/批号*/
	private String electrodeNo;
	/**烘烤温度*/
	private String bakingTemperature;
	/**烘烤时间*/
	private String bakingTime;
	/**保温温度*/
	private String holdingTemperature;
	/**保温时间*/
	private String holdingTime;
	/**烘烤日期*/
	private Date bakeDate;
	/**记录人*/
	private Clob recordor;
	private String projId;//工程ID
	
	private String menuId;//菜单ID
	
	private List<Signature> sign;//签字

	private Integer version;		//版本控制
	private String isFinishSign;//是否完成签字 1是，0否

	public ElectrodeBaking() {
		super();
	}
	@Id
	@Column(name = "EB_ID", unique = true, nullable = false)
	public String getEbId() {
		return ebId;
	}
	public void setEbId(String ebId) {
		this.ebId = ebId;
	}
	@Column(name = "PC_ID")
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	
	@Column( name ="ELECOTRDE_NO")
	public String getElectrodeNo() {
		return electrodeNo;
	}
	public void setElectrodeNo(String electrodeNo) {
		this.electrodeNo = electrodeNo;
	}
	
	@Column( name ="BAKING_TEMPERATURE")
	public String getBakingTemperature() {
		return bakingTemperature;
	}
	public void setBakingTemperature(String bakingTemperature) {
		this.bakingTemperature = bakingTemperature;
	}
	
	@Column( name = "BAKING_TIME")
	public String getBakingTime() {
		return bakingTime;
	}
	public void setBakingTime(String bakingTime) {
		this.bakingTime = bakingTime;
	}
	
	@Column( name = "HOLDING_TEMPERATURE")
	public String getHoldingTemperature() {
		return holdingTemperature;
	}
	public void setHoldingTemperature(String holdingTemperature) {
		this.holdingTemperature = holdingTemperature;
	}
	
	@Column( name = "HOLDING_TIME")
	public String getHoldingTime() {
		return holdingTime;
	}
	public void setHoldingTime(String holdingTime) {
		this.holdingTime = holdingTime;
	}
	
	@Column( name = "RECORDER")
	public String getRecordor() {
		return ClobUtil.ClobToString(this.recordor);
	}
	public void setRecordor(String recordor) throws SerialException, SQLException {
		this.recordor = ClobUtil.stringToClob(recordor);
	}
	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	@Transient
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	@Transient
	public List<Signature> getSign() {
		return sign;
	}
	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}

	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	@Column(name="IS_FINISH_SIGN")
	public String getIsFinishSign() {
		return isFinishSign;
	}

	public void setIsFinishSign(String isFinishSign) {
		this.isFinishSign = isFinishSign;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="BAKE_DATE")
	public Date getBakeDate() {
		return bakeDate;
	}

	public void setBakeDate(Date bakeDate) {
		this.bakeDate = bakeDate;
	}
}
