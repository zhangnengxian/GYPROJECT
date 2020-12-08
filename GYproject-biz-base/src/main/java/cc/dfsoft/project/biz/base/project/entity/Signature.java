package cc.dfsoft.project.biz.base.project.entity;

import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;

/**
 * Signature entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SIGNATURE")
public class Signature implements java.io.Serializable {

	private static final long serialVersionUID = 8115396479974349950L;
	// Fields
	private String signatureId;
	private String businessOrderId;
	private String signatureName;
	private Clob signClob;
	private Date signTime;
	private String altitude;
	private String longitude;
	private String latitude;
	private String imgUrl;
	private String entityName;
	private String fieldName;
	private String projId;
	private String postType;
	private String unitId;
	private String unitDes;
	private String menuId;
	private String menuDes;
	
	private String signClobStr;

	private String signerId;
	private String dataType;//签字单据-只读

	// Constructors

	/** default constructor */
	public Signature() {
	}

	// Property accessors
	@Id
	@Column(name = "SIGNATURE_ID")
	public String getSignatureId() {
		return this.signatureId;
	}

	public void setSignatureId(String signatureId) {
		this.signatureId = signatureId;
	}

	@Column(name = "BUSINESS_ORDER_ID")
	public String getBusinessOrderId() {
		return this.businessOrderId;
	}

	public void setBusinessOrderId(String businessOrderId) {
		this.businessOrderId = businessOrderId;
	}

	@Column(name = "SIGNATURE_NAME")
	public String getSignatureName() {
		return this.signatureName;
	}

	public void setSignatureName(String signatureName) {
		this.signatureName = signatureName;
	}
	
	@Transient
	public String getSignClobStr(){
		return ClobUtil.ClobToString(this.signClob);
	}
	public void setSignClobStr(String signClobStr) {
		this.signClobStr = signClobStr;
	}
	
	@Column(name = "SIGN_CLOB")
	public String getSignClob() {
		return "";
	}
	public void setSignClob(String signClob) throws SerialException, SQLException {
		this.signClob = ClobUtil.stringToClob(signClob);
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SIGN_TIME")
	public Date getSignTime() {
		return this.signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	@Column(name = "ALTITUDE")
	public String getAltitude() {
		return this.altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	@Column(name = "LONGITUDE")
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "LATITUDE")
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "IMG_URL")
	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Column(name = "ENTITY_NAME")
	public String getEntityName() {
		return this.entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	@Column(name = "FIELD_NAME")
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Column(name = "PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	@Column(name = "POST_TYPE")
	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}
	
	@Column(name = "UNIT_ID")
	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	@Column(name = "UNIT_DES")
	public String getUnitDes() {
		return unitDes;
	}

	public void setUnitDes(String unitDes) {
		this.unitDes = unitDes;
	}
	
	@Column(name = "MENU_ID")
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	@Column(name = "MENU_DES")
	public String getMenuDes() {
		return menuDes;
	}

	public void setMenuDes(String menuDes) {
		this.menuDes = menuDes;
	}

	@Column(name = "SIGNER_ID")
	public String getSignerId() {
		return signerId;
	}

	public void setSignerId(String signerId) {
		this.signerId = signerId;
	}
	
	@Transient
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}