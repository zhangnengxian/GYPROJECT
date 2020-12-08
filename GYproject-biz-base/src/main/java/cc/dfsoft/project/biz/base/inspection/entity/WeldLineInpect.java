package cc.dfsoft.project.biz.base.inspection.entity;

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
import javax.persistence.Version;
import javax.sql.rowset.serial.SerialException;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

/**
 * 管道焊缝检查实体类
 * @author liaoyq
 *
 */
@Entity
@Table(name="WELD_LINE_INPECT")
public class WeldLineInpect {

	private String wliId;			// `WLI_ID` varchar(30) NOT NULL COMMENT '管道焊缝检查记录ID',
	private String pcId;			//`PC_ID` varchar(30) NOT NULL COMMENT '报验单ID',
	private String weldJointNo;		// `WELD_JOINT_NO` varchar(30) DEFAULT NULL COMMENT '焊接接头编号',
	private String crackle;			//  `CRACKLE` varchar(20) DEFAULT NULL COMMENT '裂纹',
	private String stoma;			//  `STOMA` varchar(20) DEFAULT NULL COMMENT '气孔',
	private String slag;			//  `SLAG` varchar(20) DEFAULT NULL COMMENT '夹渣',
	private String fusionSplash;	// `FUSION_SPLASH` varchar(20) DEFAULT NULL COMMENT '融合性飞溅',
	private String undercut;		//  `UNDERCUT` varchar(20) DEFAULT NULL COMMENT '咬边',
	private String wrongSide;		//  `WRONG_SIDE` varchar(20) DEFAULT NULL COMMENT '错边',
	private String surfaceDepression;// `SURFACE_DEPRESSION` varchar(20) DEFAULT NULL COMMENT '对接表面凹陷',
	private String conclusion;		//  `CONCLUSION` varchar(20) DEFAULT NULL COMMENT '结论',
	private Date inpectDate;		//  `INPECT_DATE` datetime DEFAULT NULL COMMENT '检查日期',
	private Clob welder;			// `WELDER` varchar(100) DEFAULT NULL COMMENT '检查人(焊接人)',
	private String projId;	//工程ID
	
	private List<Signature> sign;   //签字
	private Integer version;		//版本控制
	private String isFinishSign;//是否完成签字 1是，0否
	public WeldLineInpect() {
		super();
	}

	@Id
	@Column(name="WLI_ID")
	public String getWliId() {
		return wliId;
	}

	public void setWliId(String wliId) {
		this.wliId = wliId;
	}

	@Column(name="PC_ID")
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name="WELD_JOINT_NO")
	public String getWeldJointNo() {
		return weldJointNo;
	}

	public void setWeldJointNo(String weldJointNo) {
		this.weldJointNo = weldJointNo;
	}

	@Column(name="CRACKLE")
	public String getCrackle() {
		return crackle;
	}

	public void setCrackle(String crackle) {
		this.crackle = crackle;
	}

	@Column(name="STOMA")
	public String getStoma() {
		return stoma;
	}

	public void setStoma(String stoma) {
		this.stoma = stoma;
	}

	@Column(name="SLAG")
	public String getSlag() {
		return slag;
	}

	public void setSlag(String slag) {
		this.slag = slag;
	}

	@Column(name="FUSION_SPLASH")
	public String getFusionSplash() {
		return fusionSplash;
	}

	public void setFusionSplash(String fusionSplash) {
		this.fusionSplash = fusionSplash;
	}

	@Column(name="UNDERCUT")
	public String getUndercut() {
		return undercut;
	}

	public void setUndercut(String undercut) {
		this.undercut = undercut;
	}

	@Column(name="WRONG_SIDE")
	public String getWrongSide() {
		return wrongSide;
	}

	public void setWrongSide(String wrongSide) {
		this.wrongSide = wrongSide;
	}

	@Column(name="SURFACE_DEPRESSION")
	public String getSurfaceDepression() {
		return surfaceDepression;
	}

	public void setSurfaceDepression(String surfaceDepression) {
		this.surfaceDepression = surfaceDepression;
	}

	@Column(name="CONCLUSION")
	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="INPECT_DATE")
	public Date getInpectDate() {
		return inpectDate;
	}

	public void setInpectDate(Date inpectDate) {
		this.inpectDate = inpectDate;
	}

	@Column(name="WELDER")
	public String getWelder() {
		return ClobUtil.ClobToString(this.welder);
	}

	public void setWelder(String welder) throws SerialException, SQLException {
		this.welder = ClobUtil.stringToClob(welder);
	}

	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
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

}
