package cc.dfsoft.project.biz.base.baseinfo.entity;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.baseinfo.enums.CorrelationTypeEnum;


/**
 * Correlation entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="CORRELATION")
public class Correlation  implements Serializable {
	// Fields
    /**
	 * 
	 */
	private static final long serialVersionUID = 8634400515233946150L;
	private String corId;				//主键id
    private String corType;				//关联类型
    private String correlateInfoId;		//相关信息id
    private String correlateInfoDes;	//相关信息描述
    private String correlatedInfoId;	//关联信息Id
    private String correlatedInfoDes;	//关联信息描述
    private String contributionCode;	//出资方式编码
    
    private String corTypeDes;				//关联类型
    private String acceptType;				//立项方式
    private String scaleType;				//规模类型
    
    private String corpId;					//分公司id
    // Constructors

    /** default constructor */
    public Correlation() {
    }
    
    // Property accessors
    @Id 
    @Column(name="COR_ID", unique=true)
	public String getCorId() {
		return corId;
	}
	public void setCorId(String corId) {
		this.corId = corId;
	}
	@Column(name="COR_TYPE")
	public String getCorType() {
		return corType;
	}
	public void setCorType(String corType) {
		this.corType = corType;
	}
	@Column(name="CORRELATE_INFO_ID")
	public String getCorrelateInfoId() {
		return correlateInfoId;
	}
	public void setCorrelateInfoId(String correlateInfoId) {
		this.correlateInfoId = correlateInfoId;
	}
	@Column(name="CORRELATE_INFO_DES")
	public String getCorrelateInfoDes() {
		return correlateInfoDes;
	}
	public void setCorrelateInfoDes(String correlateInfoDes) {
		this.correlateInfoDes = correlateInfoDes;
	}
	@Column(name="CORRELATED_INFO_ID")
	public String getCorrelatedInfoId() {
		return correlatedInfoId;
	}
	public void setCorrelatedInfoId(String correlatedInfoId) {
		this.correlatedInfoId = correlatedInfoId;
	}
	@Column(name="CORRELATED_INFO_DES")
	public String getCorrelatedInfoDes() {
		return correlatedInfoDes;
	}
	public void setCorrelatedInfoDes(String correlatedInfoDes) {
		this.correlatedInfoDes = correlatedInfoDes;
	}
	@Column(name="CONTRIBUTION_CODE")
	public String getContributionCode() {
		return contributionCode;
	}

	public void setContributionCode(String contributionCode) {
		this.contributionCode = contributionCode;
	}

	@Transient
	public String getCorTypeDes() {
		for(CorrelationTypeEnum e: CorrelationTypeEnum.values()) {
	   		if(e.getValue().equals(this.corType)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setCorTypeDes(String corTypeDes) {
		this.corTypeDes = corTypeDes;
	}
	
	@Column(name="ACCEPT_TYPE")
	public String getAcceptType() {
		return acceptType;
	}

	public void setAcceptType(String acceptType) {
		this.acceptType = acceptType;
	}
	
	@Column(name="SCALE_TYPE")
	public String getScaleType() {
		return scaleType;
	}

	public void setScaleType(String scaleType) {
		this.scaleType = scaleType;
	}
	
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
}