package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.constructmanage.enums.ShutdownStatusEnum;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
/**
 * 停复工记录实体类
 * @author liaoyq
 *
 */
@Entity
@Table(name="SHUTDOWN_RECORD")
public class ShutDownRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sdrId;//`SDR_ID` varchar(30) NOT NULL COMMENT '停复工记录ID',
	private String sdrNo;//  `SDR_NO` varchar(30) NOT NULL COMMENT '停工编号',
	private String sdrType;//  `SDR_TYPE` varchar(2) NOT NULL COMMENT '停复工类型',
	private String projNo;//  `PROJ_NO` varchar(50) NOT NULL COMMENT '工程编号',
	private String projId;//  `PROJ_ID` varchar(30) NOT NULL COMMENT '工程ID',
	private String projName;//  `PROJ_NAME` varchar(200) DEFAULT NULL COMMENT '工程名称',
	private String cuName;//  `CU_NAME` varchar(200) DEFAULT NULL COMMENT '施工单位',
	private String cuManagerDept;//  `CU_MANAGER_DEPT` varchar(50) DEFAULT NULL COMMENT '施工项目经理部',
	private Date cumSignDate;//  `CUM_SIGN_DATE` datetime DEFAULT NULL COMMENT '施工经理签字日期',
	private String cuManager;// `CU_MANAGER` longtext COMMENT '施工经理',
	private String sdrReason;//  `SDR_REASON` varchar(255) DEFAULT NULL COMMENT '停工原因',
	private String sdrProcess;//  `SDR_PROCESS` varchar(200) DEFAULT NULL COMMENT '停复工部位（工序）',
	private String sdrDate;// `SDR_DATE` datetime DEFAULT NULL COMMENT '停工日期',
	private String reWorkDate;// `REWORK_DATE` datetime DEFAULT NULL COMMENT '复工日期',
	private String sdrRequire;//  `SDR_REQUIRE` varchar(255) DEFAULT NULL COMMENT '要求',
	private String suName;//  `SU_NAME` varchar(200) DEFAULT NULL COMMENT '监理单位',
	private String suCes;// `SUCSE` longtext COMMENT '总监理工程师',
	private Date suCesSignDate;//  `SUCSE_SIGN_DATE` datetime DEFAULT NULL COMMENT '总监理工程师签字日期',
	private Integer sdrStatus;// `SDR_STATUS` int(4) DEFAULT NULL COMMENT '停复工状态',
	private Date createDate;//  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
	private String corpName;
	private Integer pushStatus; //推送状态
	private Date pushDate;	//推送日期
	private String pushStaffId;	//推送人ID
	
	private String sdrStatusDes;	//状态描述--
	
	private List<Signature> sign;//
	
	public ShutDownRecord() {
		super();
	}
	
	@Id
	@Column(name="SDR_ID",unique=true,nullable = false)
	public String getSdrId() {
		return sdrId;
	}

	public void setSdrId(String sdrId) {
		this.sdrId = sdrId;
	}

	@Column(name="SDR_NO",nullable = false)
	public String getSdrNo() {
		return sdrNo;
	}

	public void setSdrNo(String sdrNo) {
		this.sdrNo = sdrNo;
	}

	@Column(name="SDR_TYPE",nullable = false)
	public String getSdrType() {
		return sdrType;
	}

	public void setSdrType(String sdrType) {
		this.sdrType = sdrType;
	}

	@Column(name="PROJ_NO")
	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	@Column(name="PROJ_NAME")
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Column(name="CU_NAME")
	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="CUM_SIGN_DATE")
	public Date getCumSignDate() {
		return cumSignDate;
	}

	public void setCumSignDate(Date cumSignDate) {
		this.cumSignDate = cumSignDate;
	}
    
	@Column(name="CU_MANAGER_DEPT")
	public String getCuManagerDept() {
		return cuManagerDept;
	}

	public void setCuManagerDept(String cuManagerDept) {
		this.cuManagerDept = cuManagerDept;
	}

	@Column(name="CU_MANAGER")
	public String getCuManager() {
		return cuManager;
	}

	public void setCuManager(String cuManager) {
		this.cuManager = cuManager;
	}

	@Column(name="SDR_REASON")
	public String getSdrReason() {
		return sdrReason;
	}

	public void setSdrReason(String sdrReason) {
		this.sdrReason = sdrReason;
	}

	@Column(name="SDR_PROCESS")
	public String getSdrProcess() {
		return sdrProcess;
	}

	public void setSdrProcess(String sdrProcess) {
		this.sdrProcess = sdrProcess;
	}

	/*@Temporal(TemporalType.TIMESTAMP)*/
	@Column(name="SDR_DATE")
	public String getSdrDate() {
		return sdrDate;
	}

	public void setSdrDate(String sdrDate) {
		this.sdrDate = sdrDate;
	}

	@Column(name="SDR_REQUIRE")
	public String getSdrRequire() {
		return sdrRequire;
	}

	public void setSdrRequire(String sdrRequire) {
		this.sdrRequire = sdrRequire;
	}

	@Column(name="SU_NAME")
	public String getSuName() {
		return suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	@Column(name="SUCES")
	public String getSuCes() {
		return suCes;
	}

	public void setSuCes(String suCes) {
		this.suCes = suCes;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="SUCES_SIGN_DATE")
	public Date getSuCesSignDate() {
		return suCesSignDate;
	}

	public void setSuCesSignDate(Date suCesSignDate) {
		this.suCesSignDate = suCesSignDate;
	}

	@Column(name="SDR_STATUS")
	public Integer getSdrStatus() {
		return sdrStatus;
	}

	public void setSdrStatus(Integer sdrStatus) {
		this.sdrStatus = sdrStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name="REWORK_DATE")
	public String getReWorkDate() {
		return reWorkDate;
	}

	public void setReWorkDate(String reWorkDate) {
		this.reWorkDate = reWorkDate;
	}

	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}

	@Column(name="PUSH_STATUS")
	public Integer getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(Integer pushStatus) {
		this.pushStatus = pushStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PUSH_DATE")
	public Date getPushDate() {
		return pushDate;
	}

	public void setPushDate(Date pushDate) {
		this.pushDate = pushDate;
	}

	@Column(name="PUSH_STAFF_ID")
	public String getPushStaffId() {
		return pushStaffId;
	}

	public void setPushStaffId(String pushStaffId) {
		this.pushStaffId = pushStaffId;
	}

	@Transient
	public String getSdrStatusDes() {
		for(ShutdownStatusEnum e: ShutdownStatusEnum.values()) {
	   		if(e.getValue().equals(this.sdrStatus)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setSdrStatusDes(String sdrStatusDes) {
		this.sdrStatusDes = sdrStatusDes;
	}

	@Column(name="CORP_NAME")
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	
	
}
