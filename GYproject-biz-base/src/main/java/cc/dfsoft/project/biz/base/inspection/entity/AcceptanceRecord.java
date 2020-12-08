package cc.dfsoft.project.biz.base.inspection.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 验收登记
 * @author fulw
 *
 */
@Entity
@Table(name = "ACCEPTANCE_RECORD")
public class AcceptanceRecord  implements Serializable {
	
	private String arId;		//主键id
	private String pcId;		//报验单ID
	private String meterNo;		//表编号
	private String meterModel;  //表型号
	private String manufactor;  //厂家
	private String baseNumber;   //表底数
	
	private String sealNo;		//铅封号
	private String equipment;	//设备
	private String validityTerm;//有效期
	private String projId;		//工程id
	
	@Id
	@Column(name="AR_ID",unique = true, nullable =false)
	public String getArId() {
		return arId;
	}
	public void setArId(String arId) {
		this.arId = arId;
	}
	@Column(name="PC_ID")
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	@Column(name="METER_NO")
	public String getMeterNo() {
		return meterNo;
	}
	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}
	@Column(name="METER_MODEL")
	public String getMeterModel() {
		return meterModel;
	}
	public void setMeterModel(String meterModel) {
		this.meterModel = meterModel;
	}
	
	@Column(name="MANUFACTOR")
	public String getManufactor() {
		return manufactor;
	}
	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}
	
	@Column(name="SEAL_NO")
	public String getSealNo() {
		return sealNo;
	}
	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}
	
	@Column(name="EQUIPMENT")
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	
	@Column(name="VALIDITY_TERM")
	public String getValidityTerm() {
		return validityTerm;
	}
	public void setValidityTerm(String validityTerm) {
		this.validityTerm = validityTerm;
	}
	@Column(name="BASE_NUMBER")
	public String getBaseNumber() {
		return baseNumber;
	}
	public void setBaseNumber(String baseNumber) {
		this.baseNumber = baseNumber;
	}
	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
}
