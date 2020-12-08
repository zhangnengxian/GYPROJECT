package cc.dfsoft.project.biz.base.complete.entity;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.sql.rowset.serial.SerialException;

import org.apache.http.client.utils.CloneUtils;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

/**
 * CompletionTransfer entity.
 * 
 * @author cui
 */
@Entity
@Table(name = "COMPLETION_TRANSFER")
public class CompletionTransfer implements java.io.Serializable {

	private static final long serialVersionUID = -1591948565792885839L;
	/**
	 * 
	 */
	private String ctId;                        //联合验收单id
	private String projId;                      //项目id
	private String projNo;                      //项目编号
	private String projName;                    //项目名称
	private String cmoName;                     //施工单位
	private String suName;                      //监理公司
	private String govOpinion;                  //燃气公司意见
	private Clob pipeNetwork;                   //管网工艺员签字
	private Date reciveDate;                    //接收日期
	
	private String pmOpinion;                   //项目经理意见
	private Clob suPm;                          //项目经理签字
	private Date suReciveDate;                  //接收日期
	private Date suAuditCompleted;              //审核完毕日期
	
	private String cmoOpinion;                  //施工管理处意见
	private Clob cmoAuditor;                    //审核人
	private Clob cmoPrincipal;                  //负责人
	private Date cmoAuditCompleted;             //审核完毕日期
	
	private String adOpinion;                   //验收处意见
	private Clob adArea;                        //片区审核人
	private String adReviewOpioion;              //复核意见
	private Clob adReviewPerson;                //复核人
	private Date filingDate;                    //归档日期
	
	private List<Signature> sign;               //签字相关数据------
	private String mrAuditLevel;			    //当前审核级别--------
	
	private String projAddr;                    //工程地点
	private String projScaleDes;                //工程规模
	
	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	@Column(name = "PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}

	@Transient
	public String getMrAuditLevel() {
		return mrAuditLevel;
	}

	public void setMrAuditLevel(String mrAuditLevel) {
		this.mrAuditLevel = mrAuditLevel;
	}

	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}

	// Constructors

	/** default constructor */
	public CompletionTransfer() {
	}

	@Id
	@Column(name = "CT_ID", unique = true)
	public String getCtId() {
		return ctId;
	}

	public void setCtId(String ctId) {
		this.ctId = ctId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Column(name = "CMO_NAME")
	public String getCmoName() {
		return cmoName;
	}

	public void setCmoName(String cmoName) {
		this.cmoName = cmoName;
	}

	@Column(name = "SU_NAME")
	public String getSuName() {
		return suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	@Column(name = "GOV_OPINION")
	public String getGovOpinion() {
		return govOpinion;
	}

	public void setGovOpinion(String govOpinion) {
		this.govOpinion = govOpinion;
	}

	@Column(name = "PIPE_NETWORK")
	public String getPipeNetwork() {
		return ClobUtil.ClobToString(pipeNetwork);
	}

	public void setPipeNetwork(String pipeNetwork) throws SerialException, SQLException {
		this.pipeNetwork = ClobUtil.stringToClob(pipeNetwork);
	}

	@Column(name = "RECIVE_DATE")
	public Date getReciveDate() {
		return reciveDate;
	}

	public void setReciveDate(Date reciveDate) {
		this.reciveDate = reciveDate;
	}

	@Column(name = "PM_OPINION")
	public String getPmOpinion() {
		return pmOpinion;
	}

	public void setPmOpinion(String pmOpinion) {
		this.pmOpinion = pmOpinion;
	}

	@Column(name = "SU_PM")
	public String getSuPm() {
		return ClobUtil.ClobToString(suPm);
	}

	public void setSuPm(String suPm) throws SerialException, SQLException {
		this.suPm = ClobUtil.stringToClob(suPm);
	}

	@Column(name = "SU_RECIVE_DATE")
	public Date getSuReciveDate() {
		return suReciveDate;
	}

	public void setSuReciveDate(Date suReciveDate) {
		this.suReciveDate = suReciveDate;
	}

	@Column(name = "SU_AUDIT_COMPLETED")
	public Date getSuAuditCompleted() {
		return suAuditCompleted;
	}

	public void setSuAuditCompleted(Date suAuditCompleted) {
		this.suAuditCompleted = suAuditCompleted;
	}

	@Column(name = "CMO_OPINION")
	public String getCmoOpinion() {
		return cmoOpinion;
	}

	public void setCmoOpinion(String cmoOpinion) {
		this.cmoOpinion = cmoOpinion;
	}

	@Column(name = "CMO_AUDITOR")
	public String getCmoAuditor() {
		return ClobUtil.ClobToString(cmoAuditor);
	}

	public void setCmoAuditor(String cmoAuditor) throws SerialException, SQLException {
		this.cmoAuditor = ClobUtil.stringToClob(cmoAuditor);
	}

	@Column(name = "CMO_AUDIT_COMPLETED")
	public Date getCmoAuditCompleted() {
		return cmoAuditCompleted;
	}

	public void setCmoAuditCompleted(Date cmoAuditCompleted) {
		this.cmoAuditCompleted = cmoAuditCompleted;
	}

	@Column(name = "AD_OPINION")
	public String getAdOpinion() {
		return adOpinion;
	}

	public void setAdOpinion(String adOpinion) {
		this.adOpinion = adOpinion;
	}

	@Column(name = "AD_AREA")
	public String getAdArea() {
		return ClobUtil.ClobToString(adArea);
	}

	public void setAdArea(String adArea) throws SerialException, SQLException {
		this.adArea = ClobUtil.stringToClob(adArea);
	}

	@Column(name = "CMO_PRINCIPAL")
	public String getCmoPrincipal() {
		return ClobUtil.ClobToString(cmoPrincipal);
	}

	public void setCmoPrincipal(String cmoPrincipal) throws SerialException, SQLException {
		this.cmoPrincipal = ClobUtil.stringToClob(cmoPrincipal);
	}

	@Column(name = "AD_REVIEW_OPIOION")
	public String getAdReviewOpioion() {
		return adReviewOpioion;
	}

	public void setAdReviewOpioion(String adReviewOpioion) {
		this.adReviewOpioion = adReviewOpioion;
	}

	@Column(name = "AD_REVIEW_PERSON")
	public String getAdReviewPerson() {
		return ClobUtil.ClobToString(adReviewPerson);
	}

	public void setAdReviewPerson(String adReviewPerson) throws SerialException, SQLException {
		this.adReviewPerson = ClobUtil.stringToClob(adReviewPerson);
	}
	
	@Column(name = "FILING_DATE")
	public Date getFilingDate() {
		return filingDate;
	}

	public void setFilingDate(Date filingDate) {
		this.filingDate = filingDate;
	}


}