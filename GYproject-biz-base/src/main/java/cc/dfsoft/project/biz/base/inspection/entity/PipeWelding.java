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
 * PipeWelding entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PIPE_WELDING")
public class PipeWelding implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3096122422925948224L;
	private String pipeId;				//钢管焊接记录ID
	private String pcId;				//报验单ID
	private String pipeWorkNo;			//作业号
	private String pipeWeldNo;			//焊缝编号
	private String pipeShootNo;			//拍片号
	private String pipeBaseType;		//接头类型
	private String pipeMaterial;		//管道材质
	private String pipeSize;			//管道规格
	private String pipeWeldingWay;		//固定或转动焊口
	private String pipeWeldingMethod;	//焊接方法
	private String pipeWeldingMaterial;	//焊接材料规格
	private String pipePreOc;			//预热温度
	private String pipeWelderName;		//焊工姓名
	private Date pipeWeldingDate;		//施焊日期
	private String pipeOutsideResult;	//外观质量检查结果
	private String pipeNdtResult;		//无损检验结果
	
	/**修改增加字段*/
	
	private String pipePosition;		//管位
	private String pipeSectionLen;		//管段长度
	private String jointNum;			//焊口数量
	private String weldParams;			//焊接参数
	private Integer isInspect;			//是否探伤
	private Clob builder;				//施工员
	private Clob firstParty;			//甲方
	private Clob supervision;			//监理
	private String remarks;				//备注
	
	private String projId;	//工程ID
	
	private List<Signature> sign; //签字
	private String menuId;

	private Integer version;		//版本控制
	// Constructors

	/** default constructor */
	public PipeWelding() {
	}

	// Property accessors
	@Id
	@Column(name = "PIPE_ID", unique = true, nullable = false)
	public String getPipeId() {
		return this.pipeId;
	}

	public void setPipeId(String pipeId) {
		this.pipeId = pipeId;
	}

	@Column(name = "PC_ID")
	public String getPcId() {
		return this.pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name = "PIPE_WORK_NO")
	public String getPipeWorkNo() {
		return this.pipeWorkNo;
	}

	public void setPipeWorkNo(String pipeWorkNo) {
		this.pipeWorkNo = pipeWorkNo;
	}

	@Column(name = "PIPE_WELD_NO")
	public String getPipeWeldNo() {
		return this.pipeWeldNo;
	}

	public void setPipeWeldNo(String pipeWeldNo) {
		this.pipeWeldNo = pipeWeldNo;
	}

	@Column(name = "PIPE_SHOOT_NO")
	public String getPipeShootNo() {
		return this.pipeShootNo;
	}

	public void setPipeShootNo(String pipeShootNo) {
		this.pipeShootNo = pipeShootNo;
	}

	@Column(name = "PIPE_BASE_TYPE")
	public String getPipeBaseType() {
		return this.pipeBaseType;
	}

	public void setPipeBaseType(String pipeBaseType) {
		this.pipeBaseType = pipeBaseType;
	}

	@Column(name = "PIPE_MATERIAL")
	public String getPipeMaterial() {
		return this.pipeMaterial;
	}

	public void setPipeMaterial(String pipeMaterial) {
		this.pipeMaterial = pipeMaterial;
	}

	@Column(name = "PIPE_SIZE")
	public String getPipeSize() {
		return this.pipeSize;
	}

	public void setPipeSize(String pipeSize) {
		this.pipeSize = pipeSize;
	}

	@Column(name = "PIPE_WELDING_WAY")
	public String getPipeWeldingWay() {
		return this.pipeWeldingWay;
	}

	public void setPipeWeldingWay(String pipeWeldingWay) {
		this.pipeWeldingWay = pipeWeldingWay;
	}

	@Column(name = "PIPE_WELDING_METHOD")
	public String getPipeWeldingMethod() {
		return this.pipeWeldingMethod;
	}

	public void setPipeWeldingMethod(String pipeWeldingMethod) {
		this.pipeWeldingMethod = pipeWeldingMethod;
	}

	@Column(name = "PIPE_WELDING_MATERIAL")
	public String getPipeWeldingMaterial() {
		return this.pipeWeldingMaterial;
	}

	public void setPipeWeldingMaterial(String pipeWeldingMaterial) {
		this.pipeWeldingMaterial = pipeWeldingMaterial;
	}

	@Column(name = "PIPE_PRE_OC")
	public String getPipePreOc() {
		return this.pipePreOc;
	}

	public void setPipePreOc(String pipePreOc) {
		this.pipePreOc = pipePreOc;
	}

	@Column(name = "PIPE_WELDER_NAME")
	public String getPipeWelderName() {
		return this.pipeWelderName;
	}

	public void setPipeWelderName(String pipeWelderName) {
		this.pipeWelderName = pipeWelderName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PIPE_WELDING_DATE")
	public Date getPipeWeldingDate() {
		return this.pipeWeldingDate;
	}

	public void setPipeWeldingDate(Date pipeWeldingDate) {
		this.pipeWeldingDate = pipeWeldingDate;
	}

	@Column(name = "PIPE_OUTSIDE_RESULT")
	public String getPipeOutsideResult() {
		return this.pipeOutsideResult;
	}

	public void setPipeOutsideResult(String pipeOutsideResult) {
		this.pipeOutsideResult = pipeOutsideResult;
	}

	@Column(name = "PIPE_NDT_RESULT")
	public String getPipeNdtResult() {
		return this.pipeNdtResult;
	}

	public void setPipeNdtResult(String pipeNdtResult) {
		this.pipeNdtResult = pipeNdtResult;
	}

	@Column(name = "PIPE_POSITION")
	public String getPipePosition() {
		return pipePosition;
	}

	public void setPipePosition(String pipePosition) {
		this.pipePosition = pipePosition;
	}

	@Column(name = "PIPE_SECTION_LEN")
	public String getPipeSectionLen() {
		return pipeSectionLen;
	}

	public void setPipeSectionLen(String pipeSectionLen) {
		this.pipeSectionLen = pipeSectionLen;
	}

	@Column(name = "JOINT_NUM")
	public String getJointNum() {
		return jointNum;
	}

	public void setJointNum(String jointNum) {
		this.jointNum = jointNum;
	}

	@Column(name = "WELD_PARAMS")
	public String getWeldParams() {
		return weldParams;
	}

	public void setWeldParams(String weldParams) {
		this.weldParams = weldParams;
	}

	@Column(name = "IS_INSPECT")
	public Integer getIsInspect() {
		return isInspect;
	}

	public void setIsInspect(Integer isInspect) {
		this.isInspect = isInspect;
	}

	@Column(name = "BUILDER")
	public String getBuilder() {
		return ClobUtil.ClobToString(this.builder);
	}

	public void setBuilder(String builder) throws SerialException, SQLException {
		this.builder = ClobUtil.stringToClob(builder);
	}

	@Column(name = "FIRST_PARTY")
	public String getFirstParty() {
		return ClobUtil.ClobToString(firstParty);
	}

	public void setFirstParty(String firstParty) throws SerialException, SQLException {
		this.firstParty = ClobUtil.stringToClob(firstParty);
	}

	
	@Column(name = "SUPERVISION")
	public String getSupervision() {
		return ClobUtil.ClobToString(this.supervision);
	}

	public void setSupervision(String supervision) throws SerialException, SQLException {
		this.supervision = ClobUtil.stringToClob(supervision);
	}

	@Column(name = "REMARKS")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
}