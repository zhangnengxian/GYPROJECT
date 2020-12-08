package cc.dfsoft.project.biz.base.inspection.entity;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 管沟开挖记录实体类
 * @author liaoyq
 *
 */
@Entity
@Table(name = "GROOVE_RECORD")
public class GrooveRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1053353115797706646L;
	private String grId;				//沟槽验收记录ID
	private String pcId;				//报验单ID
	private Date grDate;				//日期
	private String pipePosition;		//管位
	private String pipeBedding;			//管基
	private String pipeLength;			//管沟长度
	private String pipeDepth;			//管沟深度
	private String grSlope;				//坡向、坡度
	private String grBackfill;			//回填（管顶）
	private String hinderSituation;   	//障碍情况
	private Clob grBuilder;				//施工员
	private Clob firstParty;     		//甲方
	private Clob supervision;			//监理
	
	private String projId;				//工程id
	
	List<Signature> sign;				//签字信息
	private String menuDes;				//菜单描述
	private String menuId;				//菜单ID

	private Integer version;		//版本控制
	private String isFinishSign;//是否完成签字 1是，0否
	public GrooveRecord() {
		super();
	}
	
	@Id
	@Column( name ="GR_ID" , unique = true, nullable = false)
	public String getGrId() {
		return grId;
	}
	public void setGrId(String grId) {
		this.grId = grId;
	}
	
	@Column(name = "PC_ID")
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "GR_DATE")
	public Date getGrDate() {
		return grDate;
	}
	public void setGrDate(Date grDate) {
		this.grDate = grDate;
	}
	
	@Column(name = "PIPE_POSITION")
	public String getPipePosition() {
		return pipePosition;
	}
	public void setPipePosition(String pipePosition) {
		this.pipePosition = pipePosition;
	}
	
	@Column(name = "PIPE_BEDDING")
	public String getPipeBedding() {
		return pipeBedding;
	}
	public void setPipeBedding(String pipeBedding) {
		this.pipeBedding = pipeBedding;
	}
	
	@Column(name = "PIPE_LENGTH")
	public String getPipeLength() {
		return pipeLength;
	}
	public void setPipeLength(String pipeLength) {
		this.pipeLength = pipeLength;
	}
	
	@Column(name = "PIPE_DEPTH")
	public String getPipeDepth() {
		return pipeDepth;
	}
	public void setPipeDepth(String pipeDepth) {
		this.pipeDepth = pipeDepth;
	}
	
	@Column(name = "GR_SLOPE")
	public String getGrSlope() {
		return grSlope;
	}
	public void setGrSlope(String grSlope) {
		this.grSlope = grSlope;
	}
	
	@Column(name = "GR_BACKFILL")
	public String getGrBackfill() {
		return grBackfill;
	}
	public void setGrBackfill(String grBackfill) {
		this.grBackfill = grBackfill;
	}
	
	@Column(name = "HINDER_SITUATION")
	public String getHinderSituation() {
		return hinderSituation;
	}
	public void setHinderSituation(String hinderSituation) {
		this.hinderSituation = hinderSituation;
	}
	
	@Column(name = "GR_BUILDER")
	public String getGrBuilder() {
		return ClobUtil.ClobToString(this.grBuilder);
	}
	public void setGrBuilder(String grBuilder) throws SerialException, SQLException {
		this.grBuilder = ClobUtil.stringToClob(grBuilder);
	}
	
	@Column(name = "FIRST_PARTY")
	public String getFirstParty() {
		return ClobUtil.ClobToString(this.firstParty);
	}
	public void setFirstParty(String firstParty) throws SerialException, SQLException{
		this.firstParty = ClobUtil.stringToClob(firstParty);
	}
	
	@Column(name="SUPERVISION")
	public String getSupervision() {
		return ClobUtil.ClobToString(this.supervision);
	}
	public void setSupervision(String supervision) throws SerialException, SQLException{
		this.supervision = ClobUtil.stringToClob(supervision);
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

	@Transient
	public String getMenuDes() {
		return menuDes;
	}

	public void setMenuDes(String menuDes) {
		this.menuDes = menuDes;
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
