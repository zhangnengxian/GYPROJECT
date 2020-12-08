package cc.dfsoft.project.biz.base.inspection.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
/**
 * pe管焊缝检查实体类
 * @author liaoyq
 *
 */
@Entity
@Table(name="PE_WELD_LINE_INSPECT")
public class PeWeldLineInspect implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  peWliId;		//`PE_WLI_ID` varchar(30) NOT NULL COMMENT 'PE管焊缝检查记录ID',
	private String pcId;			//  `PC_ID` varchar(30) NOT NULL COMMENT '报验单ID',
	private String peWeldLineNo;	//  `PE_WELD_LINE_NO` varchar(50) DEFAULT NULL COMMENT '焊缝编号',
	private Date cuDate;			// `CU_DATE` datetime DEFAULT NULL COMMENT '施工日期',
	private String isComplete;		// `IS_COMPLETE` varchar(2) DEFAULT NULL COMMENT '管材管件是否完整',
	private String isHaveMelts;		// `IS_HAVE_MELTS` varchar(2) DEFAULT NULL COMMENT '焊接表面是否有熔融物',
	private String isCoaxial;		//  `IS_COAXIAL` varchar(2) DEFAULT NULL COMMENT '管件承插口是否与管材同轴',
	private String isScratch;		//  `IS_SCRATCH` varchar(2) DEFAULT NULL COMMENT '整个圆周是否存在刮削痕迹',
	private String isHoleCharging;	//  `IS_HOLE_CHARGING` varchar(2) DEFAULT NULL COMMENT '是否观察孔冒料',
	private String isLeakageWeld;	//  `IS_LEAKAGE_WELD` varchar(2) DEFAULT NULL COMMENT '有无漏焊',
	private String isCurlDefect;	//  `IS_CURL_DEFECT` varchar(2) DEFAULT NULL COMMENT '卷边是否有明显的缺陷',
	private String curlCheck;		//  `CURL_CHECK` varchar(30) DEFAULT NULL COMMENT '卷边切除检查',
	private String weldRingWidthB;	//  `WELD_RING_WIDTH_B` varchar(30) DEFAULT NULL COMMENT '焊环检查环的宽度B',
	private String weldRingHeightH;	//  `WELD_RING_HEIGHT_H` varchar(30) DEFAULT NULL COMMENT '焊环检查环的高度H',
	private String weldRingSeamH;	//  `WELD_RING_SEAM_H` varchar(30) DEFAULT NULL COMMENT '焊环检查环缝高度h',
	private String wrongEdge;		//  `WRONG_EDGE` varchar(20) DEFAULT NULL COMMENT '错边量',
	private String meltConnectType;	//连接类型：1-热熔连接，2-电熔连接
	private String projId;			//工程ID
	private Integer version;		//版本控制
	public PeWeldLineInspect() {
		super();
	}
	
	@Id
	@Column(name="PE_WLI_ID",unique = true, nullable = false)
	public String getPeWliId() {
		return peWliId;
	}
	public void setPeWliId(String peWliId) {
		this.peWliId = peWliId;
	}
	@Column(name="PC_ID")
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	@Column(name="PE_WELD_LINE_NO")
	public String getPeWeldLineNo() {
		return peWeldLineNo;
	}
	public void setPeWeldLineNo(String peWeldLineNo) {
		this.peWeldLineNo = peWeldLineNo;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="CU_DATE")
	public Date getCuDate() {
		return cuDate;
	}
	public void setCuDate(Date cuDate) {
		this.cuDate = cuDate;
	}
	@Column(name="IS_COMPLETE")
	public String getIsComplete() {
		return isComplete;
	}
	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}
	@Column(name="IS_HAVE_MELTS")
	public String getIsHaveMelts() {
		return isHaveMelts;
	}
	public void setIsHaveMelts(String isHaveMelts) {
		this.isHaveMelts = isHaveMelts;
	}
	@Column(name="IS_COAXIAL")
	public String getIsCoaxial() {
		return isCoaxial;
	}
	public void setIsCoaxial(String isCoaxial) {
		this.isCoaxial = isCoaxial;
	}
	@Column(name="IS_SCRATCH")
	public String getIsScratch() {
		return isScratch;
	}
	public void setIsScratch(String isScratch) {
		this.isScratch = isScratch;
	}
	@Column(name="IS_HOLE_CHARGING")
	public String getIsHoleCharging() {
		return isHoleCharging;
	}
	public void setIsHoleCharging(String isHoleCharging) {
		this.isHoleCharging = isHoleCharging;
	}
	@Column(name="IS_LEAKAGE_WELD")
	public String getIsLeakageWeld() {
		return isLeakageWeld;
	}
	public void setIsLeakageWeld(String isLeakageWeld) {
		this.isLeakageWeld = isLeakageWeld;
	}
	@Column(name="IS_CURL_DEFECT")
	public String getIsCurlDefect() {
		return isCurlDefect;
	}
	public void setIsCurlDefect(String isCurlDefect) {
		this.isCurlDefect = isCurlDefect;
	}
	@Column(name="CURL_CHECK")
	public String getCurlCheck() {
		return curlCheck;
	}
	public void setCurlCheck(String curlCheck) {
		this.curlCheck = curlCheck;
	}
	@Column(name="WELD_RING_WIDTH_B")
	public String getWeldRingWidthB() {
		return weldRingWidthB;
	}
	public void setWeldRingWidthB(String weldRingWidthB) {
		this.weldRingWidthB = weldRingWidthB;
	}
	@Column(name="WELD_RING_HEIGHT_H")
	public String getWeldRingHeightH() {
		return weldRingHeightH;
	}
	public void setWeldRingHeightH(String weldRingHeightH) {
		this.weldRingHeightH = weldRingHeightH;
	}
	@Column(name="WELD_RING_SEAM_H")
	public String getWeldRingSeamH() {
		return weldRingSeamH;
	}
	public void setWeldRingSeamH(String weldRingSeamH) {
		this.weldRingSeamH = weldRingSeamH;
	}
	@Column(name="WRONG_EDGE")
	public String getWrongEdge() {
		return wrongEdge;
	}
	public void setWrongEdge(String wrongEdge) {
		this.wrongEdge = wrongEdge;
	}

	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name="MELT_CONNECT_TYPE")
	public String getMeltConnectType() {
		return meltConnectType;
	}

	public void setMeltConnectType(String meltConnectType) {
		this.meltConnectType = meltConnectType;
	}
	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
