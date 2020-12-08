package cc.dfsoft.project.biz.base.inspection.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="BALL_RECORD")
public class BallRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String brId; 			//通球记录ID
	private String pcId; 			//报验单ID
	private String riserNo;			//立管编号
	private String riserReducing;	// 通球变径
	private String segmentLen;		// 每段长度
	private String ballDiameter;	// 通球直径
	private String projId;			//工程ID
	private Integer version;		//版本控制
	public BallRecord() {
		super();
	}

	@Id
	@Column(name="BR_ID")
	public String getBrId() {
		return brId;
	}

	public void setBrId(String brId) {
		this.brId = brId;
	}

	@Column(name="PC_ID")
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name="RISER_REDUCING")
	public String getRiserReducing() {
		return riserReducing;
	}

	public void setRiserReducing(String riserReducing) {
		this.riserReducing = riserReducing;
	}

	@Column(name="SEGMENT_LEN")
	public String getSegmentLen() {
		return segmentLen;
	}

	public void setSegmentLen(String segmentLen) {
		this.segmentLen = segmentLen;
	}

	@Column(name="BALL_DIAMETER")
	public String getBallDiameter() {
		return ballDiameter;
	}

	public void setBallDiameter(String ballDiameter) {
		this.ballDiameter = ballDiameter;
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

	@Column(name="RISER_NO")
	public String getRiserNo() {
		return riserNo;
	}

	public void setRiserNo(String riserNo) {
		this.riserNo = riserNo;
	}
	
}

