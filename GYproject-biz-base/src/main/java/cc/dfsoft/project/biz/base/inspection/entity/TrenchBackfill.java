package cc.dfsoft.project.biz.base.inspection.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * TrenchBackfill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TRENCH_BACKFILL")
public class TrenchBackfill implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7979748532424606164L;
	private String tbId;			//`TB_ID` varchar(30) NOT NULL DEFAULT '' COMMENT '沟槽回填ID',
	private String projId;			//工程ID 
	private String pcId;			// `PC_ID` varchar(30) NOT NULL COMMENT '报验单ID',
	private String pipeSectionNo;	//`PIPE_SECTION_NO` varchar(30) DEFAULT NULL COMMENT '管段编号',
	private String pileNo;			//  `PILE_NO` varchar(30) DEFAULT NULL COMMENT '桩号',
	private String backfillSoil;	//    `BACKFILL_SOIL` varchar(50) DEFAULT NULL COMMENT '回填土质',
	 
	private String tampingMethod;	//   `TAMPING_METHOD` varchar(50) DEFAULT NULL COMMENT '夯实方法',
	private String orPavementStruc;	// `OR_PAVEMENT_STRUC` varchar(20) DEFAULT NULL COMMENT '原路面结构',
	private String orPavementThick;	//  `OR_PAVEMENT_THICK` varchar(20) DEFAULT NULL COMMENT '原路面厚度',
	private String rePavementStruc;	//  `RE_PAVEMENT_STRUC` varchar(20) DEFAULT NULL COMMENT '路面恢复结构',
	private String rePavementThick;	//RE_PAVEMENT_THICK` varchar(20) DEFAULT NULL COMMENT '路面恢复厚度',
	private Integer version;		//版本控制
	// Constructors

	/** default constructor */
	public TrenchBackfill() {
	}

	// Property accessors
	@Id
	@Column(name = "TB_ID", unique = true,nullable = false)
	public String getTbId() {
		return this.tbId;
	}

	public void setTbId(String tbId) {
		this.tbId = tbId;
	}
	
	@Column(name = "PC_ID")
	public String getPcId() {
		return this.pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name="PROJ_ID" ,nullable = false)
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

	@Column(name="BACKFILL_SOIL")
	public String getBackfillSoil() {
		return backfillSoil;
	}

	public void setBackfillSoil(String backfillSoil) {
		this.backfillSoil = backfillSoil;
	}

	@Column(name="TAMPING_METHOD")
	public String getTampingMethod() {
		return tampingMethod;
	}

	public void setTampingMethod(String tampingMethod) {
		this.tampingMethod = tampingMethod;
	}

	@Column(name="OR_PAVEMENT_STRUC")
	public String getOrPavementStruc() {
		return orPavementStruc;
	}

	public void setOrPavementStruc(String orPavementStruc) {
		this.orPavementStruc = orPavementStruc;
	}

	@Column(name="OR_PAVEMENT_THICK")
	public String getOrPavementThick() {
		return orPavementThick;
	}

	public void setOrPavementThick(String orPavementThick) {
		this.orPavementThick = orPavementThick;
	}

	@Column(name="RE_PAVEMENT_STRUC")
	public String getRePavementStruc() {
		return rePavementStruc;
	}

	public void setRePavementStruc(String rePavementStruc) {
		this.rePavementStruc = rePavementStruc;
	}

	@Column(name="RE_PAVEMENT_THICK")
	public String getRePavementThick() {
		return rePavementThick;
	}

	public void setRePavementThick(String rePavementThick) {
		this.rePavementThick = rePavementThick;
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