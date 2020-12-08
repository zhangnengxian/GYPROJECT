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
 * 热熔对接实体类
 * @author liaoyq
 *
 */
@Entity
@Table(name="HOTMELT_DOCKING")
public class HotMeltDocking implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String hdId;			//`HD_ID` varchar(30) NOT NULL COMMENT '热熔对接记录ID',
	private String pcId;			//`PC_ID` varchar(30) NOT NULL COMMENT '报验单ID',
	private String jointNo;			//`JOINT_NO` varchar(50) DEFAULT NULL COMMENT '焊口编号',
	private String appearanceInpect;// `APPEARANCE_INPECT` varchar(4) DEFAULT NULL COMMENT '宏观(外观)检查',
	private String bendTest ;		//  `BEND_TEST` varchar(4) DEFAULT NULL COMMENT '翻边背弯试验',
	private Date cuDate;			//  `CU_DATE` datetime DEFAULT NULL COMMENT '施工日期',
	private Date inpectionDate;	//  `INPECTION_DATE` datetime DEFAULT NULL COMMENT '检查日期',
	
	private String projId;	//工程ID
	private Integer version;		//版本控制
	public HotMeltDocking() {
		super();
	}

	@Id
	@Column(name="HD_ID",unique=true,nullable = false)
	public String getHdId() {
		return hdId;
	}


	public void setHdId(String hdId) {
		this.hdId = hdId;
	}

	@Column(name="PC_ID")
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name="JOINT_NO")
	public String getJointNo() {
		return jointNo;
	}

	public void setJointNo(String jointNo) {
		this.jointNo = jointNo;
	}

	@Column(name="APPEARANCE_INPECT")
	public String getAppearanceInpect() {
		return appearanceInpect;
	}

	public void setAppearanceInpect(String appearanceInpect) {
		this.appearanceInpect = appearanceInpect;
	}

	@Column(name="BEND_TEST")
	public String getBendTest() {
		return bendTest;
	}

	public void setBendTest(String bendTest) {
		this.bendTest = bendTest;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="CU_DATE")
	public Date getCuDate() {
		return cuDate;
	}

	public void setCuDate(Date cuDate) {
		this.cuDate = cuDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="INPECTION_DATE")
	public Date getInpectionDate() {
		return inpectionDate;
	}

	public void setInpectionDate(Date inpectionDate) {
		this.inpectionDate = inpectionDate;
	}

	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
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
