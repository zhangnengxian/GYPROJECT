package cc.dfsoft.project.biz.base.complete.entity;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.sql.rowset.serial.SerialException;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;


/**
 * FilingData entity.
 * 
 * @author cui
 */
@Entity
@Table(name = "FILING_DATA")
public class FilingData implements java.io.Serializable {

	private static final long serialVersionUID = 1028917960219226680L;
	/**
	 * 
	 */
	private String fdId;                        //归档资料ID
	private String projId;                      //项目id
	private String projNo;                      //项目编号
	private String projName;                    //工程名称
	
	private String projAddr;					//工程地点
	private String projScaleDes;				//工程规模
	
	private String fdCmo;                      //施工部门
	private String fdFileOpinion;              //资料归档信息
	private Clob fdAuditor;                    //审核人签字
	private Date fdDate;                       //归档日期
	
	private String fdFileNo;                   //工程档号
	private String fdConnectOpinion;           //资料交接信息
	private Date fdEndDate;                    //审核完毕日期
	
	private Clob fdCheck;                      //验收处签字
	private Clob fdSpotLeader;                 //现场负责人
	private Date fdConnectDate;                //交接日期
	
	private List<Signature> sign;               //签字相关数据------
	
	private String paNo;						//受理单号
	private String isPrint;					//是否打印标记     0 已打印,1未打印
	
	
	
	private String corpId;					//分公司id
	private String projectTypeDes;			//工程类型描述--只读
	private String contributionModeDes;		//出资方式描述--只读
	private String deptName;	 			//部门名称--只读
	private String corpName;				//燃气公司--只读
	private String menuId;					//菜单ID-只读
	
	
	@Column(name = "IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	@Column(name = "FD_CONNECT_OPINION")
	public String getFdConnectOpinion() {
		return fdConnectOpinion;
	}
	public void setFdConnectOpinion(String fdConnectOpinion) {
		this.fdConnectOpinion = fdConnectOpinion;
	}
	
	@Id
	@Column(name = "FD_ID", unique = true)
	public String getFdId() {
		return fdId;
	}
	public void setFdId(String fdId) {
		this.fdId = fdId;
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
	@Column(name = "FD_CMO")
	public String getFdCmo() {
		return fdCmo;
	}
	public void setFdCmo(String fdCmo) {
		this.fdCmo = fdCmo;
	}
	@Column(name = "FD_FILE_OPINION")
	public String getFdFileOpinion() {
		return fdFileOpinion;
	}
	public void setFdFileOpinion(String fdFileOpinion) {
		this.fdFileOpinion = fdFileOpinion;
	}
	
	@Column(name = "FD_AUDITOR")
	public String getFdAuditor() {
		return ClobUtil.ClobToString(fdAuditor);
	}
	public void setFdAuditor(String fdAuditor) throws SerialException, SQLException {
		this.fdAuditor = ClobUtil.stringToClob(fdAuditor);
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FD_DATE")
	public Date getFdDate() {
		return fdDate;
	}
	public void setFdDate(Date fdDate) {
		this.fdDate = fdDate;
	}
	
	@Column(name = "FD_FILE_NO")
	public String getFdFileNo() {
		return fdFileNo;
	}
	public void setFdFileNo(String fdFileNo) {
		this.fdFileNo = fdFileNo;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FD_END_DATE")
	public Date getFdEndDate() {
		return fdEndDate;
	}
	public void setFdEndDate(Date fdEndDate) {
		this.fdEndDate = fdEndDate;
	}
	
	@Column(name = "FD_CHECK")
	public String getFdCheck() {
		return ClobUtil.ClobToString(fdCheck);
	}
	public void setFdCheck(String fdCheck) throws SerialException, SQLException {
		this.fdCheck = ClobUtil.stringToClob(fdCheck);
	}
	
	@Column(name = "FD_SPOT_LEADER")
	public String getFdSpotLeader() {
		return ClobUtil.ClobToString(fdSpotLeader);
	}
	public void setFdSpotLeader(String fdSpotLeader) throws SerialException, SQLException {
		this.fdSpotLeader = ClobUtil.stringToClob(fdSpotLeader);
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FD_CONNECT_DATE")
	public Date getFdConnectDate() {
		return fdConnectDate;
	}
	public void setFdConnectDate(Date fdConnectDate) {
		this.fdConnectDate = fdConnectDate;
	}
	@Transient
	public List<Signature> getSign() {
		return sign;
	}
	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}
	
	@Transient
	public String getPaNo() {
		return paNo;
	}
	public void setPaNo(String paNo) {
		this.paNo = paNo;
	}
	
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	@Transient
	public String getProjectTypeDes() {
		return projectTypeDes;
	}
	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}
	
	@Transient
	public String getContributionModeDes() {
		return contributionModeDes;
	}
	public void setContributionModeDes(String contributionModeDes) {
		this.contributionModeDes = contributionModeDes;
	}
	
	@Transient
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Transient
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	@Transient
	public String getProjAddr() {
		return projAddr;
	}
	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	
	@Transient
	public String getProjScaleDes() {
		return projScaleDes;
	}
	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	@Transient
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	
	
	
}
	
	