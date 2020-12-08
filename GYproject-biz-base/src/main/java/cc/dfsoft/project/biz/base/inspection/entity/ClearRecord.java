package cc.dfsoft.project.biz.base.inspection.entity;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.sql.rowset.serial.SerialException;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;
/**
 * 清扫记录实体类
 * @author liaoyq
 * @createTime 2017年7月25日
 */
@Entity
@Table(name="CLEAR_RECORD")
public class ClearRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String crId;			//`CR_ID` varchar(30) NOT NULL COMMENT '清扫记录ID',
	private String pcId;			//  `PC_ID` varchar(30) DEFAULT NULL COMMENT '报验单ID',
	private String projId;			//  `PROJ_ID` varchar(30) NOT NULL COMMENT '工程id',
	private String pipeSectionNo; 	//  `PIPE_SECTION_NO` varchar(30) DEFAULT NULL COMMENT '管段编号',
	private String pileNo;			//  `PILE_NO` varchar(30) DEFAULT NULL COMMENT '桩号',
	private String crSpec;			// `CR_SPCE` varchar(50) DEFAULT NULL COMMENT '规格',
	private String crLen;			//  `CR_LEN` varchar(30) DEFAULT NULL COMMENT '长度(M)',
	private String crSituation ;	// `CR_SITUATION` varchar(200) DEFAULT NULL COMMENT '清扫记录',
	private Clob checker;			//  `CHECKER` varchar(200) DEFAULT NULL COMMENT '检查人',
	private String remarks;			//  `REMARKS` varchar(200) DEFAULT NULL COMMENT '备注',
	
	private List<Signature> sign;   //签字
	private String menuId;
	private Integer version;		//版本控制

	private String isFinishSign;//是否完成签字 1是，0否
	public ClearRecord() {
		super();
	}
	
	@Id
	@Column(name="CR_ID",unique = true,nullable = false)
	public String getCrId() {
		return crId;
	}
	public void setCrId(String crId) {
		this.crId = crId;
	}
	
	@Column(name="PC_ID")
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	
	@Column(name="PROJ_ID",nullable = false)
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
	@Column(name="CR_SPCE")
	public String getCrSpec() {
		return crSpec;
	}
	public void setCrSpec(String crSpec) {
		this.crSpec = crSpec;
	}
	@Column(name="CR_LEN")
	public String getCrLen() {
		return crLen;
	}
	public void setCrLen(String crLen) {
		this.crLen = crLen;
	}
	@Column(name="CR_SITUATION")
	public String getCrSituation() {
		return crSituation;
	}
	public void setCrSituation(String crSituation) {
		this.crSituation = crSituation;
	}
	@Column(name="CHECKER")
	public String getChecker() {
		return ClobUtil.ClobToString(this.checker);
	}
	public void setChecker(String checker) throws SerialException, SQLException {
		this.checker = ClobUtil.stringToClob(checker);
	}
	@Column(name="REMARKS")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
