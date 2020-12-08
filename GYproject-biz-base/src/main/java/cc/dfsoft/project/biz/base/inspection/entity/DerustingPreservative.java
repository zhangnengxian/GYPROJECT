package cc.dfsoft.project.biz.base.inspection.entity;

import cc.dfsoft.project.biz.base.inspection.enums.DpCheckRequireTypeEnum;
import cc.dfsoft.project.biz.base.inspection.enums.DpCkeckMethodEnum;
import cc.dfsoft.project.biz.base.inspection.enums.DpResultEnum;
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
 * DerustingPreservative entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DERUSTING_PRESERVATIVE")
public class DerustingPreservative implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3140860633259425698L;
	private String dpId;		//除锈防腐检验结果ID
	private String pcId;		//报验单ID
	private String dpCheckItem;	//检查项目及要求
	private String dpMethod;	//检查方法
	private String dpResult;	//检查结果
	
	
	private String dpCheckItemDes;//用于页面显示
	private String dpMethodDes;
	private String dpResultDes;
	
	/**增加字段*/
	private String projId;				//工程ID
	private String preservativeType;	//防腐记录类型
	private Date dpDate;				// 防腐检查记录日期
	private String pipePosition;		//管位、图号
	private String pipeSectionLen; 		//管段长度 PIPE_SECTION_LEN
	private Integer pipeShaftInpect; 	//管身防腐检查是否合格PIPE_FITTING_INPECT
	private Integer pipeFittingInpect;	//接头、管件防腐检查
	private Integer otherInpect ;		//其他管件防腐检查
	private Clob builder;				//施工员
	private Clob firstParty;			//甲方
	private Clob supervision;			//监理
	private Integer isMetallicLuster;	//除锈见金属光泽
	private Integer isPrimer;			//是否刷底漆
	
	private List<Signature> sign;  		//签字
	private String menuId;

	private Integer version;		//版本控制
	private String isFinishSign;//是否完成签字 1是，0否
	// Constructors

	/** default constructor */
	public DerustingPreservative() {
	}


	// Property accessors
	@Id
	@Column(name = "DP_ID",unique = true,nullable = false)
	public String getDpId() {
		return this.dpId;
	}

	public void setDpId(String dpId) {
		this.dpId = dpId;
	}
	
	@Column(name="PROJ_ID",nullable = false)
	public String getProjId() {
		return projId;
	}


	public void setProjId(String projId) {
		this.projId = projId;
	}


	@Column(name = "PC_ID")
	public String getPcId() {
		return this.pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name = "DP_CHECK_ITEM")
	public String getDpCheckItem() {
		return this.dpCheckItem;
	}

	public void setDpCheckItem(String dpCheckItem) {
		this.dpCheckItem = dpCheckItem;
	}

	@Column(name = "DP_METHOD")
	public String getDpMethod() {
		return this.dpMethod;
	}

	public void setDpMethod(String dpMethod) {
		this.dpMethod = dpMethod;
	}

	@Column(name = "DP_RESULT")
	public String getDpResult() {
		return this.dpResult;
	}

	public void setDpResult(String dpResult) {
		this.dpResult = dpResult;
	}
	
	
	@Transient
	public String getDpCheckItemDes() {
		for(DpCheckRequireTypeEnum e: DpCheckRequireTypeEnum.values()) {
	   		if(e.getValue().equals(this.dpCheckItem)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setDpCheckItemDes(String dpCheckItemDes) {
		this.dpCheckItemDes = dpCheckItemDes;
	}
	
	@Transient
	public String getDpMethodDes() {
		for(DpCkeckMethodEnum e: DpCkeckMethodEnum.values()) {
	   		if(e.getValue().equals(this.dpMethod)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setDpMethodDes(String dpMethodDes) {
		this.dpMethodDes = dpMethodDes;
	}
	
	@Transient
	public String getDpResultDes() {
		for(DpResultEnum e: DpResultEnum.values()) {
	   		if(e.getValue().equals(this.dpResult)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setDpResultDes(String dpResultDes) {
		this.dpResultDes = dpResultDes;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="DP_DATE")
	public Date getDpDate() {
		return dpDate;
	}


	public void setDpDate(Date dpDate) {
		this.dpDate = dpDate;
	}


	@Column(name="PIPE_POSITION")
	public String getPipePosition() {
		return pipePosition;
	}


	public void setPipePosition(String pipePosition) {
		this.pipePosition = pipePosition;
	}


	@Column(name="PIPE_SECTION_LEN")
	public String getPipeSectionLen() {
		return pipeSectionLen;
	}


	public void setPipeSectionLen(String pipeSectionLen) {
		this.pipeSectionLen = pipeSectionLen;
	}

	@Column(name="PIPE_SHAFT_INPECT")
	public Integer getPipeShaftInpect() {
		return pipeShaftInpect;
	}


	public void setPipeShaftInpect(Integer pipeShaftInpect) {
		this.pipeShaftInpect = pipeShaftInpect;
	}


    @Column(name="PIPE_FITTING_INPECT")
	public Integer getPipeFittingInpect() {
		return pipeFittingInpect;
	}


	public void setPipeFittingInpect(Integer pipeFittingInpect) {
		this.pipeFittingInpect = pipeFittingInpect;
	}


	@Column(name="OTHER_INPECT")
	public Integer getOtherInpect() {
		return otherInpect;
	}


	public void setOtherInpect(Integer otherInpect) {
		this.otherInpect = otherInpect;
	}

	@Column(name="BUILDER")
	public String getBuilder() {
		return ClobUtil.ClobToString(this.builder);
	}


	public void setBuilder(String builder) throws SerialException, SQLException {
		this.builder = ClobUtil.stringToClob(builder);
	}

	@Column(name="FIRST_PARTY")
	public String getFirstParty() {
		return ClobUtil.ClobToString(firstParty);
	}


	public void setFirstParty(String firstParty) throws SerialException, SQLException {
		this.firstParty = ClobUtil.stringToClob(firstParty);
	}

	@Column(name="SUPERVISION")
	public String getSupervision() {
		return ClobUtil.ClobToString(supervision);
	}


	public void setSupervision(String supervision) throws SerialException, SQLException {
		this.supervision = ClobUtil.stringToClob(supervision);
	}

	@Column(name="PRESERVATIVE_TYPE")
	public String getPreservativeType() {
		return preservativeType;
	}


	public void setPreservativeType(String preservativeType) {
		this.preservativeType = preservativeType;
	}

	@Column(name="IS_METALLIC_LUSTER")
	public Integer getIsMetallicLuster() {
		return isMetallicLuster;
	}


	public void setIsMetallicLuster(Integer isMetallicLuster) {
		this.isMetallicLuster = isMetallicLuster;
	}

	@Column(name="IS_PRIMER")
	public Integer getIsPrimer() {
		return isPrimer;
	}


	public void setIsPrimer(Integer isPrimer) {
		this.isPrimer = isPrimer;
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