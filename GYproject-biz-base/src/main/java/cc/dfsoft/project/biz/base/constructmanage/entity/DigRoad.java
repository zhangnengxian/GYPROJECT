package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DigRoad entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DIG_ROAD")
public class DigRoad implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7152105810085103932L;

	private String drId;    //开挖路况id
	private String tpId;    //碰口方案id
	private String drRoads; //路况
	private BigDecimal drLength;//开挖长度
	private BigDecimal drWidth; //宽
	private BigDecimal drDepth; //深

	// Constructors

	/** default constructor */
	public DigRoad() {
	}

	// Property accessors
	@Id
	@Column(name = "DR_ID")
	public String getDrId() {
		return this.drId;
	}

	public void setDrId(String drId) {
		this.drId = drId;
	}

	@Column(name = "TP_ID")
	public String getTpId() {
		return this.tpId;
	}

	public void setTpId(String tpId) {
		this.tpId = tpId;
	}

	@Column(name = "DR_ROADS")
	public String getDrRoads() {
		return this.drRoads;
	}

	public void setDrRoads(String drRoads) {
		this.drRoads = drRoads;
	}

	@Column(name = "DR_LENGTH")
	public BigDecimal getDrLength() {
		return this.drLength;
	}

	public void setDrLength(BigDecimal drLength) {
		this.drLength = drLength;
	}

	@Column(name = "DR_WIDTH")
	public BigDecimal getDrWidth() {
		return this.drWidth;
	}

	public void setDrWidth(BigDecimal drWidth) {
		this.drWidth = drWidth;
	}

	@Column(name = "DR_DEPTH")
	public BigDecimal getDrDepth() {
		return this.drDepth;
	}

	public void setDrDepth(BigDecimal drDepth) {
		this.drDepth = drDepth;
	}

}