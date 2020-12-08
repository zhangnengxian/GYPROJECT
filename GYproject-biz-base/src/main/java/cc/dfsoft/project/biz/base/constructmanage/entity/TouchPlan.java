package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.sql.rowset.serial.SerialException;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

/**
 * TouchPlan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TOUCH_PLAN")
public class TouchPlan implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3226616117580574515L;
	private String tpId;								//碰口id
	private String projId;                              //项目id
	private String projName;                            //项目名称
	private String projNo;								//工程编号
	private String conNo;					            //合同编号
	private String touchAddr;                           //碰口施工地点
	private String constructionUnit;                    //施工单位
	private String projScaleDes;                        //工程规模
	private String projManager;                         //项目经理
	private String projManagerPh;                       //项目经理电话
	private String fieldPrincipal;                      //现场负责人
	private String fieldPrincipalPh;                    //负责人电话
	private String touchPart;                           //碰口部位
	private String touchPartAddtion;                    //碰口部位说明
	private Date applyTpDate;                           //申请碰口时间
	private Date applyDigDate;                          //申请开挖时间
	
	private Date tpDate;                                //确认碰口时间
	private String changeTpDate;						 //确认碰口时间--只读
	private Date digDate;                               //确认开挖时间
	private String changeDigDate;   					 //确认开挖时间--只读
	private Clob suJgj;                                 //监理负责人签字
	private String suView;                              //监理意见
	private Date suJgjDate;                             //监理签字日期
	private Clob cuSpdPrincipal;                        //安全生产处签字
	private String cuSpdView;						 	//安全生产处意见
	private Date cuSpdDate;								//安全生产处签证日期
	private Clob ongcPrincipal;					    	//天然气公司签字
	private String ongcView;							//天然气公司意见
	private Date ongcDate;								//天然气公司签字日期
	private Clob constructionPrincipal;				    //施工负责人
	private Date constructionDate;						//施工负责人签字日期
	private String remark;								//备注
	private String drawName;							//简图名称
	private String drawUrl;                             //简图路径
	
	private BigDecimal vehicleRoad;						//机动车道(米)
	private BigDecimal nonVehicleRoad;					//非机动车道(米)
	private BigDecimal coordinateRoad;					//协调道路(米)
	private BigDecimal greenRoad;						//破坏绿地(米)
	private String acceptanceView;						//验收处意见
	private Date acceptanceDate;						//验收处审核日期
	private Clob appeptanceJgj; 						//验收处负责人签字
	private String longitude;							//经度
	private String latitude;							//纬度
	
	private List<DigRoad> digRoads;						//路况------用于保存方法字段
	private List<Signature> sign; 						//签字相关数据
	
	private String mrAuditLevel;						//当前审核级别
	private String menuDes;
	
	
	/** default constructor */
	public TouchPlan() {
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "APPLY_DIG_DATE")
	public Date getApplyDigDate() {
		return this.applyDigDate;
	}

	// Constructors

	@Temporal(TemporalType.DATE)
	@Column(name = "APPLY_TP_DATE")
	public Date getApplyTpDate() {
		return this.applyTpDate;
	}

	@Column(name = "CON_NO")
	public String getConNo() {
		return conNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CONSTRUCTION_DATE")
	public Date getConstructionDate() {
		return this.constructionDate;
	}

	@Column(name = "CONSTRUCTION_PRINCIPAL")
	public String getConstructionPrincipal() {
		return ClobUtil.ClobToString(this.constructionPrincipal);
	}

	@Column(name = "CONSTRUCTION_UNIT")
	public String getConstructionUnit() {
		return this.constructionUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CU_SPD_DATE")
	public Date getCuSpdDate() {
		return this.cuSpdDate;
	}

	
	@Column(name = "CU_SPD_PRINCIPAL")
	public String getCuSpdPrincipal() {
		return ClobUtil.ClobToString(this.cuSpdPrincipal);
	}

	@Column(name = "CU_SPD_VIEW")
	public String getCuSpdView() {
		return this.cuSpdView;
	}

	

	@Column(name = "FIELD_PRINCIPAL")
	public String getFieldPrincipal() {
		return this.fieldPrincipal;
	}

	@Column(name = "FIELD_PRINCIPAL_PH")
	public String getFieldPrincipalPh() {
		return this.fieldPrincipalPh;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ONGC_DATE")
	public Date getOngcDate() {
		return this.ongcDate;
	}

	@Column(name = "ONGC_PRINCIPAL")
	public String getOngcPrincipal() {
		return ClobUtil.ClobToString(this.ongcPrincipal);
	}

	@Column(name = "ONGC_VIEW")
	public String getOngcView() {
		return this.ongcView;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	@Column(name = "PROJ_MANAGER")
	public String getProjManager() {
		return this.projManager;
	}

	@Column(name = "PROJ_MANAGER_PH")
	public String getProjManagerPh() {
		return this.projManagerPh;
	}

	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return this.projName;
	}

	@Column(name = "PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return this.projScaleDes;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	@Column(name = "SU_JGJ")
	public String getSuJgj() {
		return ClobUtil.ClobToString(this.suJgj);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SU_JGJ_DATE")
	public Date getSuJgjDate() {
		return this.suJgjDate;
	}

	@Column(name = "SU_VIEW")
	public String getSuView() {
		return this.suView;
	}

	@Column(name = "TOUCH_ADDR")
	public String getTouchAddr() {
		return this.touchAddr;
	}

	@Column(name = "TOUCH_PART")
	public String getTouchPart() {
		return this.touchPart;
	}

	@Column(name = "TOUCH_PART_ADDTION")
	public String getTouchPartAddtion() {
		return this.touchPartAddtion;
	}

	

	// Property accessors
	@Id
	@Column(name = "TP_ID")
	public String getTpId() {
		return this.tpId;
	}
	
	@Column(name = "VEHICLE_ROAD")
	public BigDecimal getVehicleRoad() {
		return vehicleRoad;
	}
	public void setVehicleRoad(BigDecimal vehicleRoad) {
		this.vehicleRoad = vehicleRoad;
	}
	
	@Column(name = "NON_VEHICLE_ROAD")
	public BigDecimal getNonVehicleRoad() {
		return nonVehicleRoad;
	}
	public void setNonVehicleRoad(BigDecimal nonVehicleRoad) {
		this.nonVehicleRoad = nonVehicleRoad;
	}
	
	@Column(name = "GREEN_ROAD")
	public BigDecimal getGreenRoad() {
		return greenRoad;
	}
	public void setGreenRoad(BigDecimal greenRoad) {
		this.greenRoad = greenRoad;
	}
	
	@Column(name = "COORDINATE_ROAD")
	public BigDecimal getCoordinateRoad() {
		return coordinateRoad;
	}
	
	@Column(name = "ACCEPTANCE_VIEW")
	public String getAcceptanceView() {
		return acceptanceView;
	}
	public void setAcceptanceView(String acceptanceView) {
		this.acceptanceView = acceptanceView;
	}
	
	
	@Column(name = "DRAW_NAME")
	public String getDrawName() {
		return drawName;
	}
	public void setDrawName(String drawName) {
		this.drawName = drawName;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ACCEPTANCE_Date")
	public Date getAcceptanceDate() {
		return acceptanceDate;
	}
	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}
	public void setCoordinateRoad(BigDecimal coordinateRoad) {
		this.coordinateRoad = coordinateRoad;
	}
	
	public void setApplyDigDate(Date applyDigDate) {
		this.applyDigDate = applyDigDate;
	}
	
	public void setApplyTpDate(Date applyTpDate) {
		this.applyTpDate = applyTpDate;
	}
	
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	
	public void setConstructionDate(Date constructionDate) {
		this.constructionDate = constructionDate;
	}

	public void setConstructionPrincipal(String constructionPrincipal) throws SerialException, SQLException {
		this.constructionPrincipal = ClobUtil.stringToClob(constructionPrincipal);
	}
	
	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	public void setCuSpdDate(Date cuSpdDate) {
		this.cuSpdDate = cuSpdDate;
	}

	public void setCuSpdPrincipal(String cuSpdPrincipal) throws SerialException, SQLException {
		this.cuSpdPrincipal = ClobUtil.stringToClob(cuSpdPrincipal);
	}

	public void setCuSpdView(String cuSpdView) {
		this.cuSpdView = cuSpdView;
	}


	public void setFieldPrincipal(String fieldPrincipal) {
		this.fieldPrincipal = fieldPrincipal;
	}

	public void setFieldPrincipalPh(String fieldPrincipalPh) {
		this.fieldPrincipalPh = fieldPrincipalPh;
	}

	public void setOngcDate(Date ongcDate) {
		this.ongcDate = ongcDate;
	}

	public void setOngcPrincipal(String ongcPrincipal) throws SerialException, SQLException {
		this.ongcPrincipal = ClobUtil.stringToClob(ongcPrincipal);
	}

	public void setOngcView(String ongcView) {
		this.ongcView = ongcView;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public void setProjManager(String projManager) {
		this.projManager = projManager;
	}

	public void setProjManagerPh(String projManagerPh) {
		this.projManagerPh = projManagerPh;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSuJgj(String suJgj) throws SerialException, SQLException {
		this.suJgj = ClobUtil.stringToClob(suJgj);
	}

	public void setSuJgjDate(Date suJgjDate) {
		this.suJgjDate = suJgjDate;
	}

	public void setSuView(String suView) {
		this.suView = suView;
	}

	public void setTouchAddr(String touchAddr) {
		this.touchAddr = touchAddr;
	}

	public void setTouchPart(String touchPart) {
		this.touchPart = touchPart;
	}

	public void setTouchPartAddtion(String touchPartAddtion) {
		this.touchPartAddtion = touchPartAddtion;
	}


	public void setTpId(String tpId) {
		this.tpId = tpId;
	}
	
	@Column(name="ACCEPTANCE_JGJ")
	public String getAppeptanceJgj() {
		return ClobUtil.ClobToString(this.appeptanceJgj);
	}
	
	public void setAppeptanceJgj(String appeptanceJgj) throws SerialException, SQLException {
		this.appeptanceJgj = ClobUtil.stringToClob(appeptanceJgj);
	}
	
	@Transient
	public List<DigRoad> getDigRoads() {
		return digRoads;
	}
	public void setDigRoads(List<DigRoad> digRoads) {
		this.digRoads = digRoads;
	}
	
	@Transient
	public String getMrAuditLevel() {
		return mrAuditLevel;
	}
	public void setMrAuditLevel(String mrAuditLevel) {
		this.mrAuditLevel = mrAuditLevel;
	}
	@Column(name="PROJ_NO")
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	@Column(name = "LONGITUDE")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "LATITUDE")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSignature(List<Signature> sign) {
		this.sign = sign;
	}
	@Transient
	public String getMenuDes() {
		return menuDes;
	}
	public void setMenuDes(String menuDes) {
		this.menuDes = menuDes;
	}
	
	@Transient
	public String getDrawUrl() {
		return drawUrl;
	}

	public void setDrawUrl(String drawUrl) {
		this.drawUrl = drawUrl;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TP_DATE")
	public Date getTpDate() {
		return tpDate;
	}
	public void setTpDate(Date tpDate) {
		this.tpDate = tpDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DIG_DATE")
	public Date getDigDate() {
		return digDate;
	}
	public void setDigDate(Date digDate) {
		this.digDate = digDate;
	}
	
	@Transient
	public String getChangeTpDate() {
		return changeTpDate;
	}
	public void setChangeTpDate(String changeTpDate) {
		this.changeTpDate = changeTpDate;
	}
	
	@Transient
	public String getChangeDigDate() {
		return changeDigDate;
	}
	public void setChangeDigDate(String changeDigDate) {
		this.changeDigDate = changeDigDate;
	}
	
}