package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.sql.Clob;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.sql.rowset.serial.SerialException;

import cc.dfsoft.uexpress.common.util.ClobUtil;

/**
 * ConnectRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONNECT_RECORD")
public class ConnectRecord implements java.io.Serializable {

	// Fields

	private String crId;			//碰口记录ID
	private String croId;			//碰口记录单ID
	private String projId;			//工程ID
	private String unitType;		//要求单位类型
	private String connectContentId;//要求内容ID
	private String isReady;			//是否具备
	private String welderId1;		//焊工证号1
	private Clob welder1;			//焊工签字1
	private String welderId2;		//焊工证号2
	private Clob welder2;			//焊工签字2
	private String welderId3;		//焊工证号3
	private Clob welder3;			//焊工签字3
	private String des;             //要求内容

	// Constructors

	/** default constructor */
	public ConnectRecord() {
	}

	@Transient
	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	// Property accessors
	@Id
	@Column(name = "CR_ID", unique = true)
	public String getCrId() {
		return this.crId;
	}

	public void setCrId(String crId) {
		this.crId = crId;
	}

	@Column(name = "CRO_ID")
	public String getCroId() {
		return this.croId;
	}

	public void setCroId(String croId) {
		this.croId = croId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "UNIT_TYPE")
	public String getUnitType() {
		return this.unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	@Column(name = "CONNECT_CONTENT_ID")
	public String getConnectContentId() {
		return this.connectContentId;
	}

	public void setConnectContentId(String connectContentId) {
		this.connectContentId = connectContentId;
	}

	@Column(name = "IS_READY")
	public String getIsReady() {
		return this.isReady;
	}

	public void setIsReady(String isReady) {
		this.isReady = isReady;
	}

	@Column(name = "WELDER_ID1")
	public String getWelderId1() {
		return this.welderId1;
	}

	public void setWelderId1(String welderId1) {
		this.welderId1 = welderId1;
	}

	@Lob
	@Column(name = "WELDER1")
	public String getWelder1() {
		return ClobUtil.ClobToString(this.welder1);
	}

	public void setWelder1(String welder1) throws SerialException, SQLException {
		this.welder1 = ClobUtil.stringToClob(welder1);
	}

	@Column(name = "WELDER_ID2")
	public String getWelderId2() {
		return this.welderId2;
	}

	public void setWelderId2(String welderId2) {
		this.welderId2 = welderId2;
	}

	@Lob
	@Column(name = "WELDER2")
	public String getWelder2() {
		return ClobUtil.ClobToString(this.welder2);
	}

	public void setWelder2(String welder2) throws SerialException, SQLException {
		this.welder2 = ClobUtil.stringToClob(welder2);
	}
	
	@Column(name = "WELDER_ID3")
	public String getWelderId3() {
		return this.welderId3;
	}

	public void setWelderId3(String welderId3) {
		this.welderId3 = welderId3;
	}

	@Lob
	@Column(name = "WELDER3")
	public String getWelder3() {
		return ClobUtil.ClobToString(this.welder3);
	}

	public void setWelder3(String welder3) throws SerialException, SQLException {
		this.welder3 = ClobUtil.stringToClob(welder3);
	}



}