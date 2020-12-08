package cc.dfsoft.project.biz.base.inspection.entity;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

/**
 * Purge entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PURGE_RECORD")
public class Purge implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3161908167632514158L;
	private String purgeId;			//吹扫记录ID
	private String pcId;			//报验单ID
	private String purgePressure;	//压力
	private String purgeMedium;		//吹扫介质
	private String purgeRemark;		//备注
	private String projId;			//工程ID
	private String pipeSectionNo;	//管段编号
	private String pileNo;			//桩号
	private String purgeSpec;		//规格
	private String purgeLen;		//长度
	private Clob checker;			//检查人
	private String purgeRecord;		//吹扫记录
	
	private List<Signature> sign;   //签字
	private String menuId;			//

	private Integer version;		//版本控制
	private String isFinishSign;//是否完成签字 1是，0否
	// Constructors

	/** default constructor */
	public Purge() {
	}

	// Property accessors
	@Id
	@Column(name = "PURGE_ID", unique = true)
	public String getPurgeId() {
		return this.purgeId;
	}

	public void setPurgeId(String purgeId) {
		this.purgeId = purgeId;
	}

	@Column(name = "PC_ID")
	public String getPcId() {
		return this.pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	
	@Column(name = "PURGE_PRESSURE")
	public String getPurgePressure() {
		return this.purgePressure;
	}

	public void setPurgePressure(String purgePressure) {
		this.purgePressure = purgePressure;
	}

	@Column(name = "PURGE_MEDIUM")
	public String getPurgeMedium() {
		return this.purgeMedium;
	}

	public void setPurgeMedium(String purgeMedium) {
		this.purgeMedium = purgeMedium;
	}

	@Column(name = "PURGE_REMARK")
	public String getPurgeRemark() {
		return this.purgeRemark;
	}

	public void setPurgeRemark(String purgeRemark) {
		this.purgeRemark = purgeRemark;
	}

	@Column(name="PROJ_ID" , nullable = false)
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name="PIPE_SECTION_NO")
	public String getPipeSectionNo() {
		return pipeSectionNo;
	}

	public void setPipeSectionNo(String pipeSectionNo) {
		this.pipeSectionNo = pipeSectionNo;
	}

	@Column(name="PILE_NO")
	public String getPileNo() {
		return pileNo;
	}

	public void setPileNo(String pileNo) {
		this.pileNo = pileNo;
	}

	@Column(name="PURGE_SPCE")
	public String getPurgeSpec() {
		return purgeSpec;
	}

	public void setPurgeSpec(String purgeSpec) {
		this.purgeSpec = purgeSpec;
	}

	@Column(name="PURGE_LEN")
	public String getPurgeLen() {
		return purgeLen;
	}

	public void setPurgeLen(String purgeLen) {
		this.purgeLen = purgeLen;
	}

	@Column(name="CHECKER")
	public String getChecker() {
		return ClobUtil.ClobToString(this.checker);
	}

	public void setChecker(String checker) throws SerialException, SQLException {
		this.checker = ClobUtil.stringToClob(checker);
	}

	@Column(name="PURGE_RECORD")
	public String getPurgeRecord() {
		return purgeRecord;
	}

	public void setPurgeRecord(String purgeRecord) {
		this.purgeRecord = purgeRecord;
	}

	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}

	@Transient
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
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